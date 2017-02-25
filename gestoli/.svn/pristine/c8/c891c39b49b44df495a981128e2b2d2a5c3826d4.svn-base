package es.caib.gestoli.front.spring.qualitat;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
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
import es.caib.gestoli.front.util.BasicData;
import es.caib.gestoli.front.util.Idioma;
import es.caib.gestoli.logic.interfaces.OliQualitatEjb;
import es.caib.gestoli.logic.model.Establiment;
import es.caib.gestoli.logic.model.QualitatDescripcioEquip;
import es.caib.gestoli.logic.model.QualitatDescripcioPersonal;
import es.caib.gestoli.logic.model.QualitatPlaManteniment;

public class QualitatPlaMantenimentFormController extends SimpleFormController {

	private static Logger logger = Logger.getLogger(QualitatPlaFormacioFormController.class);
	
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
		QualitatPlaMantenimentCommand qualitatPlaMantenimentCommand = (QualitatPlaMantenimentCommand)command;

		Long mantenimentId = qualitatPlaMantenimentCommand.getId();
		
		String forward = getSuccessView();
		Map myModel = new HashMap();
		
		HttpSession session = request.getSession();
		Establiment est = (Establiment)session.getAttribute(establimentSessionKey);

		QualitatPlaManteniment plaManteniment = null;
		
		try{
			oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
			if (isCreate(request)){
				logger.info("Creant el registre: " + qualitatPlaMantenimentCommand);
				plaManteniment = new QualitatPlaManteniment();
			} else {
				logger.info("Actualitzant el registre: " + qualitatPlaMantenimentCommand);
				plaManteniment = oliQualitatEjb.mantenimentAmbId(mantenimentId);
			}
			
			if (qualitatPlaMantenimentCommand.getEquipId() != null){
				QualitatDescripcioEquip equip = oliQualitatEjb.getQualitatDescripcioEquipById(qualitatPlaMantenimentCommand.getEquipId());
				plaManteniment.setEquip(equip);
			}
				
			plaManteniment.setAccions(qualitatPlaMantenimentCommand.getAccions());
			plaManteniment.setFrecuencia(qualitatPlaMantenimentCommand.getFrecuencia());
			
			Boolean isResponsableIntern = qualitatPlaMantenimentCommand.getIsResponsableIntern();
			if (isResponsableIntern == null)
				isResponsableIntern = new Boolean(false);
			else
				isResponsableIntern = new Boolean(true);
			plaManteniment.setIsResponsableIntern(isResponsableIntern);
			
			if (qualitatPlaMantenimentCommand.getResponsableInternId() != null){
				QualitatDescripcioPersonal responsableIntern= oliQualitatEjb.personalAmbId(qualitatPlaMantenimentCommand.getResponsableInternId());
				plaManteniment.setResponsableIntern(responsableIntern);
			}
			
			plaManteniment.setResponsableExtern(qualitatPlaMantenimentCommand.getResponsableExtern());
			plaManteniment.setVerificacioFrecuencia(qualitatPlaMantenimentCommand.getVerificacioFrecuencia());

			if (qualitatPlaMantenimentCommand.getVerificacioResponsableId() != null){
				QualitatDescripcioPersonal verificacioResponsable = oliQualitatEjb.personalAmbId(qualitatPlaMantenimentCommand.getVerificacioResponsableId());
				plaManteniment.setVerificacioResponsable(verificacioResponsable);
			}
			
			Boolean actiu = qualitatPlaMantenimentCommand.getActiu();
			if (actiu == null){
				actiu = new Boolean(false);
			}else{
				actiu = new Boolean(true);
			}
			plaManteniment.setActiu(actiu);
			
			// Creamos el plan de mantenimiento
			plaManteniment.setId(oliQualitatEjb.crearPlaManteniment(plaManteniment));
			
			if (isCreate(request)){
				ControllerUtils.saveMessageInfo(request, missatge("qualitat.plaManteniment.missatge.crear.ok"));
			}else{
				ControllerUtils.saveMessageInfo(request, missatge("qualitat.plaManteniment.missatge.modificar.ok"));				
			}
			
		} catch (Exception ex) {
			if (isCreate(request)){
				logger.error("Error creant el pla de manteniment", ex);
				ControllerUtils.saveMessageError(request, missatge("qualitat.plaManteniment.missatge.crear.no"));
			} else {
				logger.error("Error modificant el pla de manteniment", ex);
				ControllerUtils.saveMessageError(request, missatge("qualitat.plaManteniment.missatge.modificar.no"));
			}
		}
		
		forward += "?id=" + plaManteniment.getId();
		
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
		

		myModel.put("path", "qualitat_PlaManteniment");
		myModel.put("formData", command);
		
		//frecuencia (tri (trimestral), sem (semestral), anu (anual),...)
		ArrayList frecuencia = new ArrayList();
		BasicData basicData = new BasicData();
		basicData.setId("diari");
		basicData.setNom(bundle.getString("qualitat.frecuencia.diari"));
		frecuencia.add(basicData);
		basicData = new BasicData();
		basicData.setId("setmanal");
		basicData.setNom(bundle.getString("qualitat.frecuencia.setmanal"));
		frecuencia.add(basicData);
		basicData = new BasicData();
		basicData.setId("quinzenal");
		basicData.setNom(bundle.getString("qualitat.frecuencia.quinzenal"));
		frecuencia.add(basicData);
		basicData = new BasicData();
		basicData.setId("mensual");
		basicData.setNom(bundle.getString("qualitat.frecuencia.mensual"));
		frecuencia.add(basicData);
		basicData = new BasicData();
		basicData.setId("bimensual");
		basicData.setNom(bundle.getString("qualitat.frecuencia.bimensual"));
		frecuencia.add(basicData);
		basicData = new BasicData();
		basicData.setId("trimestral");
		basicData.setNom(bundle.getString("qualitat.frecuencia.trimestral"));
		frecuencia.add(basicData);
		basicData = new BasicData();
		basicData.setId("semestral");
		basicData.setNom(bundle.getString("qualitat.frecuencia.semestral"));
		frecuencia.add(basicData);
		basicData = new BasicData();
		basicData.setId("anual");
		basicData.setNom(bundle.getString("qualitat.frecuencia.anual"));
		frecuencia.add(basicData);
		basicData = new BasicData();
		basicData.setId("bianual");
		basicData.setNom(bundle.getString("qualitat.frecuencia.bianual"));
		frecuencia.add(basicData);
		basicData = new BasicData();
		basicData.setId("trianual");
		basicData.setNom(bundle.getString("qualitat.frecuencia.trianual"));
		frecuencia.add(basicData);
		myModel.put("frecuencia", frecuencia);	
		
		
		//verificacioFrecuencia (tri (trimestral), sem (semestral), anu (anual),...)
		ArrayList verificacioFrecuencia = new ArrayList();
		/*basicData = new BasicData();
		basicData.setId("dia");
		basicData.setNom(bundle.getString("qualitat.frecuencia.diari"));
		verificacioFrecuencia.add(basicData);
		basicData = new BasicData();
		basicData.setId("sem");
		basicData.setNom(bundle.getString("qualitat.frecuencia.setmanal"));
		verificacioFrecuencia.add(basicData);
		basicData = new BasicData();
		basicData.setId("men");
		basicData.setNom(bundle.getString("qualitat.frecuencia.mensual"));
		verificacioFrecuencia.add(basicData);
		basicData = new BasicData();
		basicData.setId("tri");
		basicData.setNom(bundle.getString("qualitat.frecuencia.trimestral"));
		verificacioFrecuencia.add(basicData);*/
		myModel.put("verificacioFrecuencia", frecuencia);	
		
		
		// PERSONAL 
		Collection personalArray = new ArrayList();
        if (est.getId() != null){
        	oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
        	Collection<QualitatDescripcioPersonal> personal = oliQualitatEjb.personalCercaTotsPerEstabliment(est.getId());
	        for (QualitatDescripcioPersonal per : personal){
	        	if (per.getCodi() != null) {
					basicData = new BasicData();
					basicData.setId(per.getCodi().toString());
					basicData.setNom(per.getNom().toString() + " " + per.getLlinatges().toString());
					personalArray.add(basicData);
				}
	        }
        }
        myModel.put("responsableIntern", personalArray);
		myModel.put("verificacioResponsable", personalArray);
		
		
		// PERSONAL 
		Collection equipArray = new ArrayList();
        if (est.getId() != null){
        	oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
        	Collection<QualitatDescripcioEquip> equips = oliQualitatEjb.getQualitatDescripcioEquipsByEstabliment(est.getId());
	        for (QualitatDescripcioEquip equ : equips){
	        	if (equ.getCodi() != null) {
					basicData = new BasicData();
					basicData.setId(equ.getCodi().toString());
					basicData.setNom(equ.getNom().toString());
					equipArray.add(basicData);
				}
	        }
        }
        myModel.put("equip", equipArray);
		
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
			QualitatPlaMantenimentCommand command = new QualitatPlaMantenimentCommand();
			
			if ((!isFormSubmission(request)) && (!isCreate(request))) {
				try {
					//Establiment establiment = (Establiment)request.getSession().getAttribute(establimentSessionKey);
					Long id = new Long(Long.parseLong(request.getParameter("id")));
					oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
					QualitatPlaManteniment manteniment = oliQualitatEjb.mantenimentAmbId(id);
					command.fromQualitatPlaManteniment(manteniment);

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
