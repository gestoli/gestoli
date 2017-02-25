package es.caib.gestoli.front.spring;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.propertyeditors.CustomBooleanEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractFormController;
import org.springframework.web.servlet.mvc.BaseCommandController;
import org.springframework.web.servlet.mvc.SimpleFormController;

import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;
import es.caib.gestoli.logic.model.TipusEnvas;


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
public class TipusEnvasFormController extends SimpleFormController {
	private static Logger logger = Logger.getLogger(TipusEnvasFormController.class);
	private OliInfraestructuraEjb oliInfraestructuraEjb;
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
		TipusEnvasCommand tipusEnvas = (TipusEnvasCommand)command;
		
		Long tipusEnvaseId = tipusEnvas.getId();
		Double volum = tipusEnvas.getVolum();		
		Integer materialTipusEnvas = new Integer(tipusEnvas.getMaterialTipusEnvasId().intValue());
		Integer colorId = new Integer(tipusEnvas.getColorId().intValue());
		Boolean actiu = new Boolean(true);
		String observacions = tipusEnvas.getObservacions();
		if (observacions != null && observacions.equals("")) {
			observacions = null;
		}
		if (tipusEnvas.getActiu() == null)
			actiu = new Boolean(false);
		
		
		Map myModel = new HashMap();
		if (isCreate(request)) {
			try {
				logger.info("Creant el registre: " + tipusEnvas);
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				tipusEnvaseId = oliInfraestructuraEjb.tipusEnvasCrear(materialTipusEnvas, colorId, volum, observacions);
				tipusEnvas.setId(tipusEnvaseId);
				ControllerUtils.saveMessageInfo(request, missatge("tipusEnvas.missatge.crear.ok"));
			} catch (Exception ex) {
				logger.error("Error creant el tipusEnvas", ex);
				ControllerUtils.saveMessageError(request, missatge("tipusEnvas.missatge.crear.no"));
			}
		} else {
			try {				
				logger.info("Modificant el registre: " + tipusEnvas);
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				oliInfraestructuraEjb.tipusEnvasModificar(materialTipusEnvas, colorId, actiu, volum, observacions, tipusEnvaseId);
				ControllerUtils.saveMessageInfo(request, missatge("tipusEnvas.missatge.modificar.ok"));
				
			} catch (Exception ex) {
				logger.error("Error modificant el tipusEnvas ", ex);
				ControllerUtils.saveMessageError(request, missatge("tipusEnvas.missatge.modificar.no"));
			}
		}
		String forward = getSuccessView()+ "?id=" + tipusEnvas.getId();
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
		
		if (request.getAttribute("idAlEntrar")==null){
			if (request.getParameter("id")!=null){
				request.setAttribute("idAlEntrar",Boolean.valueOf(true));
			}else{
				request.setAttribute("idAlEntrar",Boolean.valueOf(false));
			}
		}			
		
		Map myModel = new HashMap();
		oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
		myModel.put("materialesTipusEnvas", oliInfraestructuraEjb.materialTipusEnvasCercaTots());
		oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
		myModel.put("colores", oliInfraestructuraEjb.colorCercaTots());

/*		if ((!isFormSubmission(request)) && (!isCreate(request))) {
			myModel.put("path", "modificar_tipusEnvas");
		}else{
			myModel.put("path", "crear_tipusEnvas");
		}*/
		
		if (((Boolean)request.getAttribute("idAlEntrar")).booleanValue()){
			myModel.put("path", "modificar_tipusEnvas");
		}else{
			myModel.put("path", "crear_tipusEnvas");
		}		
		
		return myModel;
	}


	/**
	 * En caso de que sea una edición retorna el objeto rellenado con
	 * los datos actuales del registro. En caso de que sea una creación
	 * retorna el objeto vacío.
	 * @see AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
	 */
	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		TipusEnvasCommand tipusEnvas = null;

		if ((!isFormSubmission(request)) && (!isCreate(request))) {
			try {
				Long codi = new Long(Long.parseLong(request.getParameter("id")));
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				TipusEnvas te = oliInfraestructuraEjb.tipusEnvasAmbId(codi);
				tipusEnvas = new TipusEnvasCommand();
				tipusEnvas.fromTipusEnvas(te);
			} catch (RemoteException ex) {
				throw new ServletException("Error cridant l'EJB", ex);
			}
		} else {
			tipusEnvas = new TipusEnvasCommand();
		}
		if (isCreate(request)) {
			tipusEnvas.setActiu(new Boolean(true));
		}

		return tipusEnvas;
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
				Long.class,
				new CustomNumberEditor(Long.class, true));
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