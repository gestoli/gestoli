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
import es.caib.gestoli.logic.model.QualitatControlPlaguesVerificacio;
import es.caib.gestoli.logic.model.QualitatDescripcioPersonal;

public class QualitatControlPlaguesVerificacioFormController extends SimpleFormController {

	private static Logger logger = Logger.getLogger(QualitatControlPlaguesVerificacioFormController.class);
	
	private OliQualitatEjb oliQualitatEjb;
	private HibernateTemplate hibernateTemplate;
	private String establimentSessionKey;

	public ModelAndView onSubmit(
			HttpServletRequest request,
			HttpServletResponse response,
			Object command,
			BindException errors)
	throws ServletException {
		QualitatControlPlaguesVerificacioCommand qualitatControlPlaguesVerificacioCommand = (QualitatControlPlaguesVerificacioCommand)command;
	
		Long verificacioId = qualitatControlPlaguesVerificacioCommand.getId();
		
		String forward = getSuccessView();
		Map myModel = new HashMap();
		
		HttpSession session = request.getSession();
		Establiment est = (Establiment)session.getAttribute(establimentSessionKey);

		QualitatControlPlaguesVerificacio controlPlaguesVerificacio = null;
		
		try{
			oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
			if (isCreate(request)){
				logger.info("Creant el registre: " + qualitatControlPlaguesVerificacioCommand);
				controlPlaguesVerificacio = new QualitatControlPlaguesVerificacio();
				if (qualitatControlPlaguesVerificacioCommand.getIdPlaga() != null){
					controlPlaguesVerificacio.setControlPlaga(oliQualitatEjb.controlPlaguesAmbId(Long.valueOf(qualitatControlPlaguesVerificacioCommand.getIdPlaga())));
				}
				controlPlaguesVerificacio.setDepartament(oliQualitatEjb.departamentAmbId(Long.valueOf("10")));
			} else {
				logger.info("Actualitzant el registre: " + qualitatControlPlaguesVerificacioCommand);
				controlPlaguesVerificacio = oliQualitatEjb.controlPlaguesVerificacioAmbId(verificacioId);
			}
			
			controlPlaguesVerificacio.setObjectiu(qualitatControlPlaguesVerificacioCommand.getObjectiu());
			controlPlaguesVerificacio.setDataVerificacio(qualitatControlPlaguesVerificacioCommand.getDataVerificacio());
			
			if (qualitatControlPlaguesVerificacioCommand.getIdResponsableVerificacio() != null){
				QualitatDescripcioPersonal responsableVerificacio = oliQualitatEjb.personalAmbId(qualitatControlPlaguesVerificacioCommand.getIdResponsableVerificacio());
				controlPlaguesVerificacio.setResponsableVerificacio(responsableVerificacio);
			}
			
			Boolean isSatisfactori = qualitatControlPlaguesVerificacioCommand.getSatisfactori();
			if (isSatisfactori == null)
				isSatisfactori = new Boolean(false);
			else
				isSatisfactori = new Boolean(true);
			controlPlaguesVerificacio.setSatisfactori(isSatisfactori);
			
			controlPlaguesVerificacio.setId(oliQualitatEjb.crearControlPlaguesVerificacio(controlPlaguesVerificacio));
			
			if (isCreate(request)){
				ControllerUtils.saveMessageInfo(request, missatge("qualitat.controlPlagues.verificacio.missatge.crear.ok"));
			}else{
				ControllerUtils.saveMessageInfo(request, missatge("qualitat.controlPlagues.verificacio.missatge.modificar.ok"));				
			}
			
		} catch (Exception ex) {
			if (isCreate(request)){
				logger.error("Error creant la verificacio del control de plagues", ex);
				ControllerUtils.saveMessageError(request, missatge("qualitat.controlPlagues.verificacio.missatge.crear.no"));
			} else {
				logger.error("Error modificant la verificacio del control de plagues", ex);
				ControllerUtils.saveMessageError(request, missatge("qualitat.controlPlagues.verificacio.missatge.modificar.no"));
			}
		}

		forward += "?id=" + controlPlaguesVerificacio.getId() + "&idPlaga=" + controlPlaguesVerificacio.getControlPlaga().getId();
		
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
		

		myModel.put("path", "qualitat_ControlPlagues.verificacio");
		myModel.put("formData", command);
		
		Long idPlaga = null;
		if (request.getParameter("idPlaga") != null){
			idPlaga = new Long(Long.parseLong(request.getParameter("idPlaga")));
		}
		myModel.put("idPlaga", idPlaga);
		
		
		// PERSONAL 
		Collection personalArray = new ArrayList();
        if (est.getId() != null){
        	oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
        	Collection<QualitatDescripcioPersonal> personal = oliQualitatEjb.personalCercaTotsPerEstabliment(est.getId());
	        for (QualitatDescripcioPersonal per : personal){
	        	if (per.getCodi() != null) {
	        		BasicData basicData = new BasicData();
					basicData.setId(per.getCodi().toString());
					basicData.setNom(per.getNom().toString() + " " + per.getLlinatges().toString());
					personalArray.add(basicData);
				}
	        }
        }
        myModel.put("responsableVerificacio", personalArray);
		
        //Plaga Objectiu (roe->roedors, nov->insectes no voladors, siv->insectes voladors)
  		ArrayList plagues = new ArrayList();
  		BasicData basicData = new BasicData();
  		basicData.setId("roe");
  		basicData.setNom(bundle.getString("qualitat.controlPlagues.plagues.roedors"));
  		plagues.add(basicData);
  		basicData = new BasicData();
  		basicData.setId("nov");
  		basicData.setNom(bundle.getString("qualitat.controlPlagues.plagues.noVoladors"));
  		plagues.add(basicData);
  		basicData = new BasicData();
  		basicData.setId("siv");
  		basicData.setNom(bundle.getString("qualitat.controlPlagues.plagues.siVoladors"));
  		plagues.add(basicData);
  		myModel.put("plagaObjectiu", plagues);	

        String queryString = request.getQueryString();   // d=789
        myModel.put("url", "plaControlPlagues");
        myModel.put("params", queryString);
        
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
			QualitatControlPlaguesVerificacioCommand command = new QualitatControlPlaguesVerificacioCommand();
			
			if ((!isFormSubmission(request)) && (!isCreate(request))) {
				try {
					//Establiment establiment = (Establiment)request.getSession().getAttribute(establimentSessionKey);
					Long id = new Long(Long.parseLong(request.getParameter("id")));
					oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
					QualitatControlPlaguesVerificacio plaguesVerificacio = oliQualitatEjb.controlPlaguesVerificacioAmbId(id);
					command.fromQualitatControlPlaguesVerificacio(plaguesVerificacio);

				} catch (RemoteException ex) {
					throw new ServletException("Error cridant l'EJB", ex);
				}
			} else {
				if (request.getParameter("idPlaga") != null){
					command.setIdPlaga(Long.valueOf(request.getParameter("idPlaga")));
					oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
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
