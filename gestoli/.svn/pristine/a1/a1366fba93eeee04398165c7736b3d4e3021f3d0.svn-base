package es.caib.gestoli.front.spring.qualitat;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

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

import es.caib.gestoli.front.spring.ControllerUtils;
import es.caib.gestoli.front.util.BasicData;
import es.caib.gestoli.front.util.Idioma;
import es.caib.gestoli.logic.interfaces.OliQualitatEjb;
import es.caib.gestoli.logic.model.Establiment;
import es.caib.gestoli.logic.model.QualitatAiguaControlAnalitic;
import es.caib.gestoli.logic.model.QualitatAiguaControlOrganoleptic;
import es.caib.gestoli.logic.model.QualitatAiguaControlOrganolepticVerificacio;
import es.caib.gestoli.logic.model.QualitatDescripcioPersonal;
import es.caib.gestoli.logic.model.QualitatPlaNetejaVerificacio;

public class QualitatAiguaControlOrganolepticFormController extends SimpleFormController {

	private static Logger logger = Logger.getLogger(QualitatAiguaControlOrganolepticFormController.class);
	
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
		QualitatAiguaControlOrganolepticCommand qualitatAiguaControlOrganolepticCommand = (QualitatAiguaControlOrganolepticCommand)command;
		String forward = getSuccessView();
		Map myModel = new HashMap();
		
		HttpSession session = request.getSession();
		Establiment est = (Establiment)session.getAttribute(establimentSessionKey);
		QualitatAiguaControlOrganoleptic desc = est.getAiguaControlOrganoleptic();
		
		String action = request.getParameter("action");
		
		try {
			
			
			if (action != null && action.equals("delete")){
				String idControl = request.getParameter("idControl");
				String sId = request.getParameter("id");
				Long id = Long.valueOf(sId);
				
				oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
				oliQualitatEjb.aiguaControlOrganolepticVerificacioEsborrar(id);
				ControllerUtils.saveMessageInfo(request, missatge("qualitat.plaAigua.missatge.esborrar.ok"));
			} else {
				
				QualitatAiguaControlOrganoleptic control= new QualitatAiguaControlOrganoleptic();
				
					if (qualitatAiguaControlOrganolepticCommand.getResponsableId()!=null){
						control.setResponsable(oliQualitatEjb.personalAmbId(qualitatAiguaControlOrganolepticCommand.getResponsableId()));}
					control.setFrequencia(qualitatAiguaControlOrganolepticCommand.getFrequencia());
					control.setEstabliment(est);
				
			
				if (desc != null) {
					control.setId(desc.getId());
					oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
					oliQualitatEjb.actualitzarAiguaControlOrganoleptic(control);
					ControllerUtils.saveMessageInfo(request, missatge("qualitat.plaAigua.missatge.crear.ok"));
				} else {
					oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
					oliQualitatEjb.crearAiguaControlOrganoleptic(control);
					ControllerUtils.saveMessageInfo(request, missatge("qualitat.plaAigua.missatge.modificar.ok"));
				}
			}
						
			
		} catch (Exception ex) {
			if (action.equals("delete")){
				logger.error("Error eliminant la descripció", ex);
				ControllerUtils.saveMessageError(request, missatge("qualitat.plaAigua.missatge.esborrar.no"));
			} else if (desc!=null) {
				logger.error("Error modificant la descripció de la instal.lació", ex);
				ControllerUtils.saveMessageError(request, missatge("qualitat.plaAigua.missatge.modificar.no"));
			} else {
				logger.error("Error modificant la descripció de la instal.lació", ex);
				ControllerUtils.saveMessageError(request, missatge("qualitat.plaAigua.missatge.modificar.no"));
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
		
		String lang = Idioma.getLang(request);
		ResourceBundle bundle = ResourceBundle.getBundle("messages",new java.util.Locale(lang));
		Map myModel = new HashMap();
		HttpSession session = request.getSession();
		Establiment est = (Establiment)session.getAttribute(establimentSessionKey);
	
		myModel.put("path", "qualitat_PlaAbastamentAigua");
		myModel.put("idEstabliment", est.getId());
		myModel.put("formData", command);
		
		// PERSONAL 
		Collection personalArray = new ArrayList();
        if (est.getId() != null){
        	oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
        	Collection<QualitatDescripcioPersonal> personal = oliQualitatEjb.personalCercaTotsPerEstabliment(est.getId());
	        for (QualitatDescripcioPersonal per : personal){
	        	if (per.getCodi() != null) {
	        		BasicData basicData = new BasicData();
					basicData = new BasicData();
					basicData.setId(per.getCodi().toString());
					basicData.setNom(per.getNom().toString() + " " + per.getLlinatges().toString());
					personalArray.add(basicData);
				}
	        }
        }
        myModel.put("responsable", personalArray);
        
      //frecuencia 
		ArrayList frecuencia = new ArrayList();
		BasicData basicData = new BasicData();
		basicData.setId("diaria");
		basicData.setNom(bundle.getString("qualitat.plaNeteja.frecuencia.diaria"));
		frecuencia.add(basicData);
		basicData = new BasicData();
		basicData.setId("setmanal");
		basicData.setNom(bundle.getString("qualitat.plaNeteja.frecuencia.setmanal"));
		frecuencia.add(basicData);
		basicData = new BasicData();
		basicData.setId("mensual");
		basicData.setNom(bundle.getString("qualitat.plaNeteja.frecuencia.mensual"));
		frecuencia.add(basicData);
		myModel.put("frequencia", frecuencia);	
		
		//Resultats dels controls
		Collection<QualitatAiguaControlOrganolepticVerificacio> resultats = new ArrayList();
		if (est.getAiguaControlOrganoleptic()!= null) {
		oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
    	resultats = oliQualitatEjb.aiguaControlOrganolepticVerificacioPerControl(est.getAiguaControlOrganoleptic().getId());
		}
		for (Object control: resultats){
    		if (((Boolean)true).equals(((QualitatAiguaControlOrganolepticVerificacio)control).getOlor()))
    			((QualitatAiguaControlOrganolepticVerificacio)control).setOlorString("OK");
    		else ((QualitatAiguaControlOrganolepticVerificacio)control).setOlorString("No OK");
    		if (((Boolean)true).equals(((QualitatAiguaControlOrganolepticVerificacio)control).getColor()))
    			((QualitatAiguaControlOrganolepticVerificacio)control).setColorString("OK");
    		else ((QualitatAiguaControlOrganolepticVerificacio)control).setColorString("No OK");
    		if (((Boolean)true).equals(((QualitatAiguaControlOrganolepticVerificacio)control).getSabor()))
    			((QualitatAiguaControlOrganolepticVerificacio)control).setSaborString("OK");
    		else ((QualitatAiguaControlOrganolepticVerificacio)control).setSaborString("No OK");
    		if (((Boolean)true).equals(((QualitatAiguaControlOrganolepticVerificacio)control).getTerbolesa()))
    			((QualitatAiguaControlOrganolepticVerificacio)control).setTerbolesaString("OK");
    		else ((QualitatAiguaControlOrganolepticVerificacio)control).setTerbolesaString("No OK");
		}
    	myModel.put("resultats", resultats);        
		
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
			QualitatAiguaControlOrganolepticCommand command = new QualitatAiguaControlOrganolepticCommand();
			if (!isFormSubmission(request)) {
				try {
					Establiment establiment = (Establiment)request.getSession().getAttribute(establimentSessionKey);
					
					if (establiment.getAiguaControlOrganoleptic()!=null) {
						Long id = establiment.getAiguaControlOrganoleptic().getId();
						oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
						QualitatAiguaControlOrganoleptic aiguaControlOrganoleptic = oliQualitatEjb.aiguaControlOrganolepticAmbId(id);
						command.fromQualitatAiguaControlOrganoleptic(aiguaControlOrganoleptic);
					}
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