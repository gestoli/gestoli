package es.caib.gestoli.front.spring;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import es.caib.gestoli.logic.model.Diposit;
import es.caib.gestoli.logic.model.Establiment;
import es.caib.gestoli.logic.util.Constants;



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
 *
 */
public class DipositFormController extends SimpleFormController {
	private static Logger logger = Logger.getLogger(DipositFormController.class);
	private OliInfraestructuraEjb oliInfraestructuraEjb;
//	private String rolProductor;
//	private String rolEnvasador;
	private String establimentSessionKey;
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
		
		HttpSession session = request.getSession();
		Establiment est = (Establiment)session.getAttribute(establimentSessionKey);
		
		DipositCommand diposit = (DipositCommand)command;

		Long dipositId = diposit.getId();
		Double capacitat = diposit.getCapacitat();
		String codiAssignant = diposit.getCodiAssignat();
		Long idZona = diposit.getIdZona();
		Integer idMaterialDiposit = diposit.getIdMaterialDiposit();
		String observaciones = diposit.getObservacions();
				
		Boolean activo;
		if(diposit.getActiu() == null){
			activo = Boolean.valueOf(false);
		} else {
			activo = diposit.getActiu();
		}
		 
		
		Boolean deEnvasadora;
		if(diposit.getDeEnvasadora() == null){
			if (est.getTipusEstabliment().getId().equals(Constants.ENV_ENVASADORA)){
				deEnvasadora = Boolean.valueOf(true);
			} else {
				deEnvasadora = Boolean.valueOf(false);
			}
		} else {
			deEnvasadora = diposit.getDeEnvasadora();
		}
		
		Boolean fictici = Boolean.valueOf(false);


		Map myModel = new HashMap();	
		
		if (isCreate(request)) {
			try {
				logger.info("Creant el registre: " + diposit);	
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				dipositId = oliInfraestructuraEjb.dipositCrear(
						capacitat, 
						codiAssignant, 
						est.getId(), 
						idZona, 
						idMaterialDiposit, 
						observaciones, 
						Boolean.valueOf(true), 
						fictici,
						deEnvasadora);
				diposit.setId(dipositId);
				ControllerUtils.saveMessageInfo(request, missatge("diposit.missatge.crear.ok"));
			} catch (Exception ex) {
				logger.error("Error creant el diposit", ex);
				ControllerUtils.saveMessageError(request, missatge("diposit.missatge.crear.no"));
			}
		} else {
			try {
				logger.info("Modificant el registre: " + diposit);
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				oliInfraestructuraEjb.dipositModificar(
						capacitat, 
						codiAssignant, 
						est.getId(), 
						idZona, 
						idMaterialDiposit, 
						observaciones, 
						activo, 
						fictici, 
						dipositId,
						deEnvasadora);
				ControllerUtils.saveMessageInfo(request, missatge("diposit.missatge.modificar.ok"));
				
			} catch (Exception ex) {
				logger.error("Error modificant el diposit ", ex);
				ControllerUtils.saveMessageError(request, missatge("diposit.missatge.modificar.no"));
			}
		}
		
		// 20100219 obarnes [at4SAC-ID#: 2010020910115] Configuració / Dipòsit / Alta de dipòsit
		// No han pedido la modificacion, pero lo preparamos: evitar diposit.getId() sigui null
		
		String forward = getSuccessView();
		if (diposit != null && diposit.getId() != null) {
			forward += "?id=" + diposit.getId();
		}
		return new ModelAndView(forward, myModel);
		
	}


	/**
	 * Retorna todos los datos del modelo necesarios para mostrar
	 * el formulario de inserción (LOVs y cosas de esas) si no hay.
	 * @see SimpleFormController#referenceData(javax.servlet.http.HttpServletRequest, java.lang.Object, org.springframework.validation.Errors)
	 */
	@SuppressWarnings("unchecked")
	protected Map referenceData(
			HttpServletRequest request,
			Object command,
			Errors errors) throws Exception {
		Map myModel = new HashMap();
		
		oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
		
		HttpSession session = request.getSession();
		Establiment est = (Establiment)session.getAttribute(establimentSessionKey);
//		est = oliInfraestructuraEjb.establimentAmbId(est.getId());
		
		myModel.put("materiales", oliInfraestructuraEjb.materialesDipCercaTots());
		myModel.put("zonas", oliInfraestructuraEjb.zonaActiusAmbEstabliment(est.getId()));
		
		if ((!isFormSubmission(request) || errors.hasErrors()) && (!isCreate(request))) {
			myModel.put("path", "modificar_diposit");
		} else {
			myModel.put("path", "crear_diposit");
		}
		
		boolean tafenv = false;
		if (est.getTipusEstabliment().getId().equals(Constants.ENV_TAFONA_ENVASADORA)) tafenv = true;
		myModel.put("tafenv", tafenv);
		myModel.put("idEstabliment", est.getId());
		myModel.put("formData", command);
		
		return myModel;
	}


	/**
	 * En caso de que sea una edición retorna el objeto rellenado con
	 * los datos actuales del registro. En caso de que sea una creación
	 * retorna el objeto vacío.
	 * @see AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
	 */
	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		DipositCommand diposit = null;
		if ((!isFormSubmission(request)) && (!isCreate(request))) {
			try {
				Long codi = new Long(Long.parseLong(request.getParameter("id")));
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				Diposit d = oliInfraestructuraEjb.dipositAmbId(codi);
				diposit = new DipositCommand();
				diposit.fromDiposit(d);

			} catch (RemoteException ex) {
				throw new ServletException("Error cridant l'EJB", ex);
			}
		} else {
			diposit = new DipositCommand();
		}
		if (isCreate(request)) {
			diposit.setActiu(Boolean.valueOf(true));
		}
		return diposit;
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
	 * Injecció de la dependència establimentSessionKey
	 *
	 * @param establimentSessionKey La classe a injectar.
	 */
	public void setEstablimentSessionKey(String establimentSessionKey) {
		this.establimentSessionKey = establimentSessionKey;
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
