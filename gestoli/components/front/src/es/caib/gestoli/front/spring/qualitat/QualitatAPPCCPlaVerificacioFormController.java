package es.caib.gestoli.front.spring.qualitat;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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
import es.caib.gestoli.front.util.BasicData;
import es.caib.gestoli.front.util.Idioma;
import es.caib.gestoli.logic.interfaces.OliQualitatEjb;
import es.caib.gestoli.logic.model.Establiment;
import es.caib.gestoli.logic.model.QualitatAPPCCPlaVerificacio;
import es.caib.gestoli.logic.model.QualitatControlPlagues;
import es.caib.gestoli.logic.model.QualitatDescripcioPersonal;

public class QualitatAPPCCPlaVerificacioFormController  extends SimpleFormController{

	private static Logger logger = Logger.getLogger(QualitatAPPCCPlaVerificacioFormController.class);
		
	private OliQualitatEjb oliQualitatEjb;
	private HibernateTemplate hibernateTemplate;
	private String establimentSessionKey;

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
		
		QualitatAPPCCPlaVerificacioCommand qualitatAppccPlaVerificacioCommand = (QualitatAPPCCPlaVerificacioCommand)command;
		Long plaVerificacioId = qualitatAppccPlaVerificacioCommand.getId();
		
		String forward = getSuccessView();
		Map myModel = new HashMap();
		
		HttpSession session = request.getSession();
		Establiment est = (Establiment)session.getAttribute(establimentSessionKey);

		QualitatAPPCCPlaVerificacio plaVerificacio = null;
		
		try{
			oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
			if (isCreate(request)){
				logger.info("Creant el registre: " + qualitatAppccPlaVerificacioCommand);
				plaVerificacio = new QualitatAPPCCPlaVerificacio();
				plaVerificacio.setEstabliment(est);
			} else {
				logger.info("Actualitzant el registre: " + qualitatAppccPlaVerificacioCommand);
				plaVerificacio = oliQualitatEjb.aPPCCPlaVerificacioAmbId(plaVerificacioId);
			}
			
			if (qualitatAppccPlaVerificacioCommand.getIdResponsable() != null){
				plaVerificacio.setResponsable(oliQualitatEjb.personalAmbId(qualitatAppccPlaVerificacioCommand.getIdResponsable()));
			}
			plaVerificacio.setDataRealitzacio(qualitatAppccPlaVerificacioCommand.getDataRealitzacio());
			
			plaVerificacio.setP1(qualitatAppccPlaVerificacioCommand.getP1());
			if (qualitatAppccPlaVerificacioCommand.getP1()){
				plaVerificacio.setP11(qualitatAppccPlaVerificacioCommand.getP11());
				plaVerificacio.setP12(qualitatAppccPlaVerificacioCommand.getP12());
				plaVerificacio.setP12_comments(qualitatAppccPlaVerificacioCommand.getP12_comments());
				plaVerificacio.setP13(qualitatAppccPlaVerificacioCommand.getP13());
				plaVerificacio.setP13_comments(qualitatAppccPlaVerificacioCommand.getP13_comments());
				plaVerificacio.setP14(qualitatAppccPlaVerificacioCommand.getP14());
				plaVerificacio.setP15(qualitatAppccPlaVerificacioCommand.getP15());
			} else {
				plaVerificacio.setP11(null);
				plaVerificacio.setP12(null);
				plaVerificacio.setP12_comments(null);
				plaVerificacio.setP13(null);
				plaVerificacio.setP13_comments(null);
				plaVerificacio.setP14(null);
				plaVerificacio.setP15(null);				
			}
			
			plaVerificacio.setP2(qualitatAppccPlaVerificacioCommand.getP2());
			if (qualitatAppccPlaVerificacioCommand.getP2()){
				plaVerificacio.setP21(qualitatAppccPlaVerificacioCommand.getP21());
				plaVerificacio.setP22(qualitatAppccPlaVerificacioCommand.getP22());
				plaVerificacio.setP22_comments(qualitatAppccPlaVerificacioCommand.getP22_comments());
			} else {
				plaVerificacio.setP21(null);
				plaVerificacio.setP22(null);
				plaVerificacio.setP22_comments(null);
			}
			
			plaVerificacio.setP31(qualitatAppccPlaVerificacioCommand.getP31());
			plaVerificacio.setP31_comments(qualitatAppccPlaVerificacioCommand.getP31_comments());
			plaVerificacio.setP32(qualitatAppccPlaVerificacioCommand.getP32());
			if (qualitatAppccPlaVerificacioCommand.getP32()){
				plaVerificacio.setP321(qualitatAppccPlaVerificacioCommand.getP321());
				plaVerificacio.setP321_comments(qualitatAppccPlaVerificacioCommand.getP321_comments());
			} else {
				plaVerificacio.setP321(null);
				plaVerificacio.setP321_comments(null);
			}
			plaVerificacio.setP33(qualitatAppccPlaVerificacioCommand.getP33());
			if (qualitatAppccPlaVerificacioCommand.getP33()){
				plaVerificacio.setP331(qualitatAppccPlaVerificacioCommand.getP331());
			} else {
				plaVerificacio.setP331(null);
			}
			plaVerificacio.setP34(qualitatAppccPlaVerificacioCommand.getP34());
			if (qualitatAppccPlaVerificacioCommand.getP34()){
				plaVerificacio.setP341(qualitatAppccPlaVerificacioCommand.getP341());
			} else {
				plaVerificacio.setP341(null);
			}
			plaVerificacio.setP35(qualitatAppccPlaVerificacioCommand.getP35());
			if (qualitatAppccPlaVerificacioCommand.getP35()){
				plaVerificacio.setP351(qualitatAppccPlaVerificacioCommand.getP351());
			} else {
				plaVerificacio.setP351(null);
			}
			
			plaVerificacio.setP4(qualitatAppccPlaVerificacioCommand.getP4());
			plaVerificacio.setP4_comments(qualitatAppccPlaVerificacioCommand.getP4_comments());
			
			plaVerificacio.setP5(qualitatAppccPlaVerificacioCommand.getP5());
			plaVerificacio.setP5_comments(qualitatAppccPlaVerificacioCommand.getP5_comments());
			
			plaVerificacio.setP6(qualitatAppccPlaVerificacioCommand.getP6());
			
			plaVerificacio.setId(oliQualitatEjb.crearAPPCCPlaVerificacio(plaVerificacio));
		
			if (isCreate(request)){
				ControllerUtils.saveMessageInfo(request, missatge("qualitat.appcc.plaVerificacio.missatge.crear.ok"));
			}else{
				ControllerUtils.saveMessageInfo(request, missatge("qualitat.appcc.plaVerificacio.missatge.modificar.ok"));				
			}
			
		} catch (Exception ex) {
			if (isCreate(request)){
				logger.error("Error creant l'plaVerificacio de l'APPCC", ex);
				ControllerUtils.saveMessageError(request, missatge("qualitat.appcc.plaVerificacio.missatge.crear.no"));
			} else {
				logger.error("Error modificant l'plaVerificacio de l'APPCC", ex);
				ControllerUtils.saveMessageError(request, missatge("qualitat.appcc.plaVerificacio.missatge.modificar.no"));
			}
		}
		
		forward += "?id=" + plaVerificacio.getId();
		
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
		

		myModel.put("path", "qualitat_APPCC.PlaVerificacio");
		myModel.put("idEstabliment", est.getId());
		myModel.put("formData", command);
		
		BasicData basicData;
		
	    Collection personal = new ArrayList();
		if (est.getId() != null){
			oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
			Collection itResponsablesInterns = oliQualitatEjb.personalCercaTotsPerEstabliment(est.getId());
			for (Iterator it = itResponsablesInterns.iterator(); it.hasNext();){
				QualitatDescripcioPersonal responsableIntern = (QualitatDescripcioPersonal)it.next();
				if (responsableIntern.getCodi() != null) {
					basicData = new BasicData();
					basicData.setId(responsableIntern.getCodi().toString());
					basicData.setNom(responsableIntern.getNom().toString() + " " + responsableIntern.getLlinatges().toString());
					personal.add(basicData);
				}
			}
		}
		myModel.put("personal", personal);
		
  		
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
			QualitatAPPCCPlaVerificacioCommand command = new QualitatAPPCCPlaVerificacioCommand();
			
			if ((!isFormSubmission(request)) && (!isCreate(request))) {
				try {
					//Establiment establiment = (Establiment)request.getSession().getAttribute(establimentSessionKey);
					Long id = new Long(Long.parseLong(request.getParameter("id")));
					oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
					QualitatAPPCCPlaVerificacio plaVerificacio = oliQualitatEjb.aPPCCPlaVerificacioAmbId(id);
					command.fromQualitatAPPCCPlaVerificacio(plaVerificacio);

				} catch (RemoteException ex) {
					throw new ServletException("Error cridant l'EJB", ex);
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
