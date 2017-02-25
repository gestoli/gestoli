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
import es.caib.gestoli.logic.model.QualitatDescripcioPersonal;
import es.caib.gestoli.logic.model.QualitatFormacioEvaluacio;
import es.caib.gestoli.logic.model.QualitatNoConformitat;
import es.caib.gestoli.logic.model.QualitatPlaFormacio;

public class QualitatFormacioEvaluacioFormController extends SimpleFormController {

	private static Logger logger = Logger.getLogger(QualitatFormacioEvaluacioFormController.class);
	
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
		QualitatFormacioEvaluacioCommand qualitatFormacioEvaluacioCommand = (QualitatFormacioEvaluacioCommand)command;

		Long formacioId = qualitatFormacioEvaluacioCommand.getId();
		
		String forward = getSuccessView();
		Map myModel = new HashMap();
		
		HttpSession session = request.getSession();
		Establiment est = (Establiment)session.getAttribute(establimentSessionKey);

		QualitatPlaFormacio plaFormacio = null;
	
		if (formacioId != null){
			try{
				oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
				logger.info("Actualitzant el registre: " + qualitatFormacioEvaluacioCommand);
				
				plaFormacio = oliQualitatEjb.formacioAmbId(formacioId);
				if (qualitatFormacioEvaluacioCommand.getSupervisorId() != null){
					QualitatDescripcioPersonal supervisor= oliQualitatEjb.personalAmbId(qualitatFormacioEvaluacioCommand.getSupervisorId());
					plaFormacio.setSupervisorFormacio(supervisor);
				}
				
				plaFormacio.setActivitatSupervisio(qualitatFormacioEvaluacioCommand.getActivitatSupervisio());
				plaFormacio.setDataSupervisio(qualitatFormacioEvaluacioCommand.getDataSupervisio());
				
				oliQualitatEjb.crearPlaFormacio(plaFormacio);
				
				//Actualizamos los usuarios del plan.
				for (QualitatFormacioEvaluacio evaluacio : qualitatFormacioEvaluacioCommand.getEvaluacions()){
					oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
					QualitatFormacioEvaluacio ev = oliQualitatEjb.evaluacioAmbId(evaluacio.getId());
					ev.setObjectiu(qualitatFormacioEvaluacioCommand.getActivitatSupervisio());
					ev.setDataVerificacio(qualitatFormacioEvaluacioCommand.getDataSupervisio());
					if (qualitatFormacioEvaluacioCommand.getSupervisorId() != null){
						QualitatDescripcioPersonal supervisor= oliQualitatEjb.personalAmbId(qualitatFormacioEvaluacioCommand.getSupervisorId());
						ev.setResponsableVerificacio(supervisor);
					}
					Boolean isSatisfactori = evaluacio.getSatisfactori();
					if (isSatisfactori == null)
						isSatisfactori = new Boolean(false);
					else
						isSatisfactori = new Boolean(true);			
					ev.setSatisfactori(isSatisfactori);

					logger.info("Actualitzant el registre: " + ev);
					oliQualitatEjb.crearEvaluacio(ev);
				}
				
				ControllerUtils.saveMessageInfo(request, missatge("qualitat.plaFormacio.missatge.modificar.ok"));
				
			}catch (Exception ex) {
				logger.error("Error modificant el pla de formacio", ex);
				ControllerUtils.saveMessageError(request, missatge("qualitat.plaFormacio.missatge.modificar.no"));
			}
			
			forward += "?id=" + plaFormacio.getId();		
		
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
		QualitatFormacioEvaluacioCommand form = (QualitatFormacioEvaluacioCommand)command;
		

		myModel.put("path", "qualitat_PlaFormacio");
		myModel.put("formData", command);
	
		BasicData basicData;
		// PERSONAL ASSISTENT
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
        myModel.put("personalArray", personalArray);
        
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
		
		oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
		Collection evaluacions = /*form.getEvaluacions();*/oliQualitatEjb.evaluacioCercaTotsPerFormacio(form.getId());
		myModel.put("evaluacions", evaluacions);
		
		myModel.put("personal", personal);

        String queryString = request.getQueryString();   // d=789
        myModel.put("url", "plaFormacio");
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
		QualitatFormacioEvaluacioCommand evaluacio = null;
		try {
			oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
			Collection evaluacions = oliQualitatEjb.evaluacioCercaTotsPerFormacio(Long.parseLong(request.getParameter("id")));
			QualitatFormacioEvaluacioCommand command = new QualitatFormacioEvaluacioCommand(evaluacions.size());
			if ((!isFormSubmission(request)) && (!isCreate(request))) {
				//Establiment establiment = (Establiment)request.getSession().getAttribute(establimentSessionKey);
				Long id = new Long(Long.parseLong(request.getParameter("id")));
				oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
				QualitatPlaFormacio formacio = oliQualitatEjb.formacioAmbId(id);
				evaluacio = new QualitatFormacioEvaluacioCommand();
				command.fromQualitatPlaFormacio(formacio,evaluacions);
				
				/*for (QualitatFormacioEvaluacio eval : command.getEvaluacions()){
					eval.setNoConformitats(oliQualitatEjb.noConformitatCercaTotsPerControl(eval.getId()));
				}*/
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
