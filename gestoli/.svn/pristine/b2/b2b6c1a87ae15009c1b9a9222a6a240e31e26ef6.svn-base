package es.caib.gestoli.front.spring.qualitat;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.propertyeditors.CustomBooleanEditor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractFormController;
import org.springframework.web.servlet.mvc.BaseCommandController;
import org.springframework.web.servlet.mvc.SimpleFormController;

import es.caib.gestoli.front.spring.ControllerUtils;
import es.caib.gestoli.front.util.Idioma;
import es.caib.gestoli.logic.interfaces.OliQualitatEjb;
import es.caib.gestoli.logic.model.Establiment;
import es.caib.gestoli.logic.model.QualitatAPPCCControl;
import es.caib.gestoli.logic.model.QualitatAPPCCEtapaPerill;

public class QualitatAPPCCControlFormController extends SimpleFormController {

	private static Logger logger = Logger.getLogger(QualitatAPPCCControlFormController.class);
	
	private OliQualitatEjb oliQualitatEjb;
	private HibernateTemplate hibernateTemplate;
	private String establimentSessionKey;
	
	
	public ModelAndView onSubmit(
			HttpServletRequest request,
			HttpServletResponse response,
			Object command,
			BindException errors)
	throws ServletException {
		QualitatAPPCCControlCommand qualitatAPPCCControlCommand = (QualitatAPPCCControlCommand)command;
	
		Long controlId = qualitatAPPCCControlCommand.getId();
		
		String forward = getSuccessView();
		Map myModel = new HashMap();
		
		HttpSession session = request.getSession();
		Establiment est = (Establiment)session.getAttribute(establimentSessionKey);

		QualitatAPPCCControl control = null;
	
		try{
			oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
			if (isCreate(request)){
				logger.info("Creant el registre: " + qualitatAPPCCControlCommand);
				control = new QualitatAPPCCControl();
				if (qualitatAPPCCControlCommand.getIdEtapa() != null){
					control.setEtapa(oliQualitatEjb.aPPCCEtapaAmbId(Long.valueOf(qualitatAPPCCControlCommand.getIdEtapa())));
				}
				if (qualitatAPPCCControlCommand.getIdPerill() != null){
					control.setPerill(oliQualitatEjb.aPPCCEtapaPerillAmbId(Long.valueOf(qualitatAPPCCControlCommand.getIdPerill())));
				}
				control.setDepartament(oliQualitatEjb.departamentAmbId(Long.valueOf("3")));
			} else {
				logger.info("Actualitzant el registre: " + qualitatAPPCCControlCommand);
				control = oliQualitatEjb.aPPCCControlAmbId(controlId);
			}
			
			control.setP1(qualitatAPPCCControlCommand.getP1());
			control.setP2(qualitatAPPCCControlCommand.getP2());
			control.setP3(qualitatAPPCCControlCommand.getP3());
			control.setP4(qualitatAPPCCControlCommand.getP4());
			control.setP5(qualitatAPPCCControlCommand.getP5());
			control.setPuntControl(qualitatAPPCCControlCommand.getPuntControl());
			control.setPerillControlat(qualitatAPPCCControlCommand.getPerillControlat());
			
			control.setId(oliQualitatEjb.crearAPPCCControl(control));
			
			if (isCreate(request)){
				ControllerUtils.saveMessageInfo(request, missatge("qualitat.appcc.control.missatge.crear.ok"));
			}else{
				ControllerUtils.saveMessageInfo(request, missatge("qualitat.appcc.control.missatge.modificar.ok"));				
			}
			
		} catch (Exception ex) {
			if (isCreate(request)){
				logger.error("Error creant el control del perill de l'APPCC", ex);
				ControllerUtils.saveMessageError(request, missatge("qualitat.appcc.control.missatge.crear.no"));
			} else {
				logger.error("Error modificant el control del perill de l'APPCC", ex);
				ControllerUtils.saveMessageError(request, missatge("qualitat.appcc.control.missatge.modificar.no"));
			}
		}

		forward += "?id=" + control.getId();
		
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
		
		String lang = Idioma.getLang(request);
		ResourceBundle bundle = ResourceBundle.getBundle("messages",new java.util.Locale(lang));
		Map myModel = new HashMap();

		HttpSession session = request.getSession();
		Establiment est = (Establiment)session.getAttribute(establimentSessionKey);
		

		myModel.put("path", "qualitat_APPCC.Control");
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
		try {
			QualitatAPPCCControlCommand command = new QualitatAPPCCControlCommand();
			
			if ((!isFormSubmission(request)) && (!isCreate(request))) {
				try {
					//Establiment establiment = (Establiment)request.getSession().getAttribute(establimentSessionKey);
					Long id = new Long(Long.parseLong(request.getParameter("id")));
					oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
					QualitatAPPCCControl control = oliQualitatEjb.aPPCCControlAmbId(id);
					command.fromQualitatAPPCCControl(control);

				} catch (RemoteException ex) {
					throw new ServletException("Error cridant l'EJB", ex);
				}
			} else {
				if (request.getParameter("idEtapa") != null){
					oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
					command.setIdEtapa(Long.valueOf(request.getParameter("idEtapa")));
				}
				if (request.getParameter("idPerill") != null){
					oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
					command.setIdPerill(Long.valueOf(request.getParameter("idPerill")));
				}
			}
			
			return command;
		} catch (Exception ex) {
			throw new ServletException("Error cridant l'EJB", ex);
		}
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
		binder.registerCustomEditor(
	    		Double.class,
	    		new CustomNumberEditor(Double.class, true));
		binder.registerCustomEditor(
	    		Long.class,
	    		new CustomNumberEditor(Long.class, true));
		binder.registerCustomEditor(
	    		Integer.class,
	    		new CustomNumberEditor(Integer.class, true));
        binder.registerCustomEditor(
        		Date.class,
        		new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), true));
	}

	
	/**
	 * Indica si la petición es de creación o no.
	 * @param request
	 * @return true si es una petición de creación o false si es de edición.
	 */
	private boolean isCreate(HttpServletRequest request) {
		return (request.getParameter("id") == null);
	}
	
	private String missatge(String clave){
		return getMessageSourceAccessor().getMessage(clave);
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


	public void setOliQualitatEjb(OliQualitatEjb oliQualitatEjb) {
		this.oliQualitatEjb = oliQualitatEjb;
	}


	public void setEstablimentSessionKey(String establimentSessionKey) {
		this.establimentSessionKey = establimentSessionKey;
	}


}
