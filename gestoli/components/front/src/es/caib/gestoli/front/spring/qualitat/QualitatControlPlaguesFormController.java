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
import es.caib.gestoli.logic.model.QualitatControlPlagues;
import es.caib.gestoli.logic.model.QualitatDescripcioPersonal;

public class QualitatControlPlaguesFormController extends SimpleFormController{

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
		
		QualitatControlPlaguesCommand qualitatControlPlaguesCommand = (QualitatControlPlaguesCommand)command;
		Long plaguesId = qualitatControlPlaguesCommand.getId();
		
		String forward = getSuccessView();
		Map myModel = new HashMap();
		
		HttpSession session = request.getSession();
		Establiment est = (Establiment)session.getAttribute(establimentSessionKey);

		QualitatControlPlagues controlPlagues = null;
		
		try{
			oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
			if (isCreate(request)){
				logger.info("Creant el registre: " + qualitatControlPlaguesCommand);
				controlPlagues = new QualitatControlPlagues();
				controlPlagues.setEstabliment(est);
			} else {
				logger.info("Actualitzant el registre: " + qualitatControlPlaguesCommand);
				controlPlagues = oliQualitatEjb.controlPlaguesAmbId(plaguesId);
			}
			
			controlPlagues.setUbicacio(qualitatControlPlaguesCommand.getUbicacio());
			controlPlagues.setElementControl(qualitatControlPlaguesCommand.getElementControl());
			controlPlagues.setFrecuencia(qualitatControlPlaguesCommand.getFrecuencia());
			
			Boolean isResponsableIntern = qualitatControlPlaguesCommand.getIsResponsableIntern();
			if (isResponsableIntern == null){
				isResponsableIntern = new Boolean(false);
				controlPlagues.setIsResponsableIntern(isResponsableIntern);
				controlPlagues.setResponsableIntern(null);
				controlPlagues.setEmpresaExterna(qualitatControlPlaguesCommand.getEmpresaExterna());
				controlPlagues.setIniciContracte(qualitatControlPlaguesCommand.getIniciContracte());
				controlPlagues.setFiContracte(qualitatControlPlaguesCommand.getFiContracte());
				controlPlagues.setPlagaObjectiu(qualitatControlPlaguesCommand.getPlagaObjectiu());
				controlPlagues.setFrecSeguiment(qualitatControlPlaguesCommand.getFrecSeguiment());
			}
			else{
				isResponsableIntern = new Boolean(true);
				controlPlagues.setIsResponsableIntern(isResponsableIntern);
				if (qualitatControlPlaguesCommand.getIdResponsableIntern() != null){
					QualitatDescripcioPersonal responsableIntern= oliQualitatEjb.personalAmbId(qualitatControlPlaguesCommand.getIdResponsableIntern());
					controlPlagues.setResponsableIntern(responsableIntern);
				}
				controlPlagues.setEmpresaExterna(null);
				controlPlagues.setIniciContracte(null);
				controlPlagues.setFiContracte(null);
				controlPlagues.setPlagaObjectiu(null);
				controlPlagues.setFrecSeguiment(null);
			}
			
			
			// Creamos el plan de mantenimiento
			controlPlagues.setId(oliQualitatEjb.crearControlPlagues(controlPlagues));
			
			if (isCreate(request)){
				ControllerUtils.saveMessageInfo(request, missatge("qualitat.controlPlagues.missatge.crear.ok"));
			}else{
				ControllerUtils.saveMessageInfo(request, missatge("qualitat.controlPlagues.missatge.modificar.ok"));				
			}
			
		} catch (Exception ex) {
			if (isCreate(request)){
				logger.error("Error creant el control de plagues", ex);
				ControllerUtils.saveMessageError(request, missatge("qualitat.controlPlagues.missatge.crear.no"));
			} else {
				logger.error("Error modificant el control de plagues", ex);
				ControllerUtils.saveMessageError(request, missatge("qualitat.controlPlagues.missatge.modificar.no"));
			}
		}
		
		forward += "?id=" + controlPlagues.getId();
		
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
		

		myModel.put("path", "qualitat_ControlPlagues");
		myModel.put("idEstabliment", est.getId());
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
		
		
		//frecuenciaSeguiment (tri (trimestral), sem (semestral), anu (anual),...)
		/*ArrayList frecuenciaSeguiment = new ArrayList();
		basicData = new BasicData();
		basicData.setId("men");
		basicData.setNom(bundle.getString("qualitat.frecuencia.mensual"));
		frecuenciaSeguiment.add(basicData);
		basicData = new BasicData();
		basicData.setId("bim");
		basicData.setNom(bundle.getString("qualitat.frecuencia.bimensual"));
		frecuenciaSeguiment.add(basicData);
		basicData = new BasicData();
		basicData.setId("tri");
		basicData.setNom(bundle.getString("qualitat.frecuencia.trimestral"));
		frecuenciaSeguiment.add(basicData);
		basicData = new BasicData();
		basicData.setId("seme");
		basicData.setNom(bundle.getString("qualitat.frecuencia.semestral"));
		frecuenciaSeguiment.add(basicData);
		basicData = new BasicData();
		basicData.setId("anu");
		basicData.setNom(bundle.getString("qualitat.frecuencia.anual"));
		frecuenciaSeguiment.add(basicData);*/
		myModel.put("frecSeguiment", frecuencia);	
		
		
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
		
        
      //Plaga Objectiu (roe->roedors, nov->insectes no voladors, siv->insectes voladors)
		ArrayList plagues = new ArrayList();
		basicData = new BasicData();
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
			QualitatControlPlaguesCommand command = new QualitatControlPlaguesCommand();
			
			if ((!isFormSubmission(request)) && (!isCreate(request))) {
				try {
					//Establiment establiment = (Establiment)request.getSession().getAttribute(establimentSessionKey);
					Long id = new Long(Long.parseLong(request.getParameter("id")));
					oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
					QualitatControlPlagues plagues = oliQualitatEjb.controlPlaguesAmbId(id);
					command.fromQualitatControlPlagues(plagues);

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
