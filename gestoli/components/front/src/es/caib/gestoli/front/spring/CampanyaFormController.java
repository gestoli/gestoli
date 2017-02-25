package es.caib.gestoli.front.spring;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.propertyeditors.CustomBooleanEditor;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractFormController;
import org.springframework.web.servlet.mvc.BaseCommandController;
import org.springframework.web.servlet.mvc.SimpleFormController;

import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;
import es.caib.gestoli.logic.model.Campanya;
import es.caib.gestoli.logic.model.Trasllat;



/**
 * <p>Controlador para las acciones de dar de alta o editar un
 * registro de tipos de información.</p>
 * <p>Los parámetros de la petición HTTP relevantes para este
 * controlador son:
 * <ul>
 *   <li><code>id</code>
 *    - Identificador del registro. Este parámetro es el que
 *      determina si se ha de mostrar la página de creación o la
 *      página de edición.</li>
 * </ul></p>
 * <p>No hay información para la vista:
 * <p>Este controlador distingue entre las peticiones del tipo
 * GET y las de tipo POST. Si la petición es de tipo POST
 * se ejecuta la acción de creación o de edición. Si la petición es de
 * tipo GET solo se muestra la página.
 *
 * @author Oriol Barnés (obarnes@at4.net)
 */
public class CampanyaFormController extends SimpleFormController {
	private static Logger logger = Logger.getLogger(CampanyaFormController.class);
	private OliInfraestructuraEjb oliInfraestructuraEjb;
	private String campanyaSessionKey;
	private String novaCampanya;
	private HibernateTemplate hibernateTemplate;


	/**
	 * Se llama cuando se aceptan las modificaciones del objeto. Solo
	 * se ejecuta esta función en el caso de que se haya ejecutado la
	 * validación sin producir ningún error.
	 * @see SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.validation.BindException)
	 */
	public ModelAndView onSubmit(
			HttpServletRequest request,
			HttpServletResponse response,
			Object command,
			BindException errors)
	throws ServletException {
		CampanyaCommand camp = (CampanyaCommand)command;
		Campanya campanya = new Campanya(camp.getNom(), new Date());
		campanya.setObservacions(camp.getObservacions());
		
		//logger.info("nom: " + campanya.getNom() + " obs: " + campanya.getObservacions());
		
		Long campanyaId = campanya.getId();
		String forward = getSuccessView();
		Map myModel = new HashMap();
		
		if (isCreate(request)) {
			try {
				
//				if (oliInfraestructuraEjb.findTrasllatsNoCompletados().size() != 0) {
//					ControllerUtils.saveMessageError(request, missatge("accio.campanya.missatge.crear.no.trasllatsPendents"));
//				} else {
					logger.info("Creant nova campanya: " + campanya);
					request.getSession().removeAttribute(campanyaSessionKey);
					oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
					campanyaId = oliInfraestructuraEjb.campanyaCrear(campanya, novaCampanya);
					campanya.setId(campanyaId);
					request.getSession().setAttribute(campanyaSessionKey, campanya.getId());
					forward += "?id=" + campanya.getId();
					if (camp.getGenerarPrefactures() != null && camp.getGenerarPrefactures().booleanValue()) {
						oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
						Long numDesc = oliInfraestructuraEjb.getNumeroDescomposicionsActivesCampanyaActual();
						if (numDesc.intValue()<1){
							ControllerUtils.saveMessageError(request, missatge("pdf.factura.noPrefactura"));
						}else{
							forward += "&generarPrefactures=" + camp.getGenerarPrefactures();
						}

					}
					ControllerUtils.saveMessageInfo(request, missatge("accio.campanya.missatge.crear.ok"));
//				}
			} catch (Exception ex) {
				logger.error("Error creant nova campanya ", ex);
				ControllerUtils.saveMessageError(request, missatge("accio.campanya.missatge.crear.no"));
			}
		}
		return new ModelAndView(forward, myModel);
	}


	/**
	 * Retorna todos los datos del modelo necesarios para mostrar
	 * el formulario de inserción (LOVs y cosas de esas) si no hay.
	 * @see SimpleFormController#referenceData(javax.servlet.http.HttpServletRequest, java.lang.Object, org.springframework.validation.Errors)
	 */
	protected Map referenceData(
			HttpServletRequest request,
			Object command,
			Errors errors) throws Exception {
		
		Map myModel = new HashMap();
		myModel.put("path", "nova_campanya");
		return myModel;
	}


	/**
	 * En caso de que sea una edición retorna el objeto rellenado con
	 * los datos actuales del registro. En caso de que sea una creación
	 * retorna el objeto vacío.
	 * @see AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
	 */
	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		CampanyaCommand campanya = null;
		if ((!isFormSubmission(request)) && (!isCreate(request))) {
			try {
				Integer id = new Integer(request.getParameter("id"));
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				Campanya cam = oliInfraestructuraEjb.campanyaAmbId(id);
				if (cam != null) {
					campanya = new CampanyaCommand();
					campanya.fromCampanya(cam);
					if (request.getParameter("generarPrefactures") != null) {
						campanya.setGenerarPrefactures(Boolean.valueOf(request.getParameter("generarPrefactures")));
					}
				}
			} catch (RemoteException ex) {
				throw new ServletException("Error cridant l'EJB", ex);
			}
		} else {
			campanya = new CampanyaCommand();
			campanya.setNomPerDefecte();
		}
		return campanya;
	}


	/**
	 * Configuración del <i>binder</i>. Si hay campos en el <i>command</i>
	 * con tipos que no sean <i>String</i> se ha de configurar su
	 * correspondiente binder aquí.
	 * @see BaseCommandController#initBinder(javax.servlet.http.HttpServletRequest, org.springframework.web.bind.ServletRequestDataBinder)
	 */
	protected void initBinder(
			HttpServletRequest request,
			ServletRequestDataBinder binder)
	throws Exception {
		binder.registerCustomEditor(
				Boolean.class,
				new CustomBooleanEditor("S", "N", true));
	}


	/**
	 * Indica si la petición es de creación o no.
	 * @param request
	 * @return true si es una petición de creación o false si es de edición.
	 */
	private boolean isCreate(HttpServletRequest request) {
		return (request.getParameter("id") == null);
	}


	/**
	 * Inyección de la dependencia informacioEjb
	 * @param informacioEjb La clase a inyectar.
	 */
	public void setOliInfraestructuraEjb(OliInfraestructuraEjb oliInfraestructuraEjb) {
		this.oliInfraestructuraEjb = oliInfraestructuraEjb;
	}

	 /**
	 * Injecció de la dependència campanyaSessionKey
	 *
	 * @param campanyaSessionKey La classe a injectar.
	 */
	public void setCampanyaSessionKey(String campanyaSessionKey) {
		this.campanyaSessionKey = campanyaSessionKey;
	}

	/**
	 * @param novaCampanya the novaCampanya to set
	 */
	public void setNovaCampanya(String novaCampanya) {
		this.novaCampanya = novaCampanya;
	}


	private String missatge(String clave){
		String valor = getMessageSourceAccessor().getMessage(clave);
		return valor;
	}


	/**
	 * set the hibernate template.
	 * @param hibernateTemplate the hibernate spring template.
	 * @spring.property ref="hibernateTemplate"
	 */
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate){
		this.hibernateTemplate = hibernateTemplate;
	}

	
	/**
	 * get the hibernate template.
	 * @return the hibernate spring template.
	 */
	public HibernateTemplate getHibernateTemplate(){
		return this.hibernateTemplate;
	}

	
}