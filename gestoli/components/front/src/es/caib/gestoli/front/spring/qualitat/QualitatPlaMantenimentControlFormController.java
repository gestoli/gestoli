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
import es.caib.gestoli.logic.model.QualitatPlaManteniment;
import es.caib.gestoli.logic.model.QualitatPlaMantenimentControl;

public class QualitatPlaMantenimentControlFormController extends SimpleFormController {

	private static Logger logger = Logger.getLogger(QualitatPlaMantenimentControlFormController.class);
	
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
		QualitatPlaMantenimentControlCommand qualitatPlaMantenimentControlCommand = (QualitatPlaMantenimentControlCommand)command;

		Long mantenimentControlId = qualitatPlaMantenimentControlCommand.getId();
		
		String forward = getSuccessView();
		Map myModel = new HashMap();
		
		HttpSession session = request.getSession();
		Establiment est = (Establiment)session.getAttribute(establimentSessionKey);

		QualitatPlaMantenimentControl plaMantenimentControl = null;
		
		try{
			oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
			if (isCreate(request)){
				logger.info("Creant el registre: " + qualitatPlaMantenimentControlCommand);
				plaMantenimentControl = new QualitatPlaMantenimentControl();
				if (qualitatPlaMantenimentControlCommand.getIdManteniment() != null){
					plaMantenimentControl.setManteniment(oliQualitatEjb.mantenimentAmbId(Long.valueOf(qualitatPlaMantenimentControlCommand.getIdManteniment())));
				}
				// Introducimos el responsable por defecto de la verificación.
				plaMantenimentControl.setResponsableVerificacio(plaMantenimentControl.getManteniment().getVerificacioResponsable());
				plaMantenimentControl.setDepartament(oliQualitatEjb.departamentAmbId(Long.valueOf("4")));
			} else {
				logger.info("Actualitzant el registre: " + qualitatPlaMantenimentControlCommand);
				plaMantenimentControl = oliQualitatEjb.mantenimentControlAmbId(mantenimentControlId);
			}
			
			plaMantenimentControl.setAccioRealitzada(qualitatPlaMantenimentControlCommand.getAccioRealitzada());
			plaMantenimentControl.setAltresAccions(qualitatPlaMantenimentControlCommand.getAltresAccions());

			// Buscamos la fecha del último control realizado a este mantenimiento.
			if (isCreate(request) && (qualitatPlaMantenimentControlCommand.getIdManteniment() != null)){
				Collection mant = oliQualitatEjb.mantenimentControlCercaTotsPerManteniment(qualitatPlaMantenimentControlCommand.getIdManteniment());
				if (!mant.isEmpty()){
					Iterator it = mant.iterator();
					plaMantenimentControl.setDataAnterior(((QualitatPlaMantenimentControl)(it.next())).getDataRealitzacio());
				}
			}
			
			plaMantenimentControl.setDataRealitzacio(qualitatPlaMantenimentControlCommand.getDataRealitzacio());
			
			Boolean isResponsableIntern = qualitatPlaMantenimentControlCommand.getIsResponsableIntern();
			if (isResponsableIntern == null)
				isResponsableIntern = new Boolean(false);
			else
				isResponsableIntern = new Boolean(true);
			plaMantenimentControl.setIsResponsableIntern(isResponsableIntern);
			
			if (qualitatPlaMantenimentControlCommand.getIdResponsableIntern() != null){
				QualitatDescripcioPersonal responsableIntern= oliQualitatEjb.personalAmbId(qualitatPlaMantenimentControlCommand.getIdResponsableIntern());
				plaMantenimentControl.setResponsableIntern(responsableIntern);
			}
			
			plaMantenimentControl.setResponsableExtern(qualitatPlaMantenimentControlCommand.getResponsableExtern());
						
			// Creamos el control del plan de mantenimiento
			plaMantenimentControl.setId(oliQualitatEjb.crearPlaMantenimentControl(plaMantenimentControl));
			
			if (isCreate(request)){
				ControllerUtils.saveMessageInfo(request, missatge("qualitat.plaManteniment.control.missatge.crear.ok"));
			}else{
				ControllerUtils.saveMessageInfo(request, missatge("qualitat.plaManteniment.control.missatge.modificar.ok"));				
			}
			
		} catch (Exception ex) {
			if (isCreate(request)){
				logger.error("Error creant el pla de mantenimentControl", ex);
				ControllerUtils.saveMessageError(request, missatge("qualitat.plaManteniment.control.missatge.crear.no"));
			} else {
				logger.error("Error modificant el pla de mantenimentControl", ex);
				ControllerUtils.saveMessageError(request, missatge("qualitat.plaManteniment.control.missatge.modificar.no"));
			}
		}
		
		forward += "?id=" + plaMantenimentControl.getId() + "&idManteniment=" + plaMantenimentControl.getManteniment().getId();
		
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
		

		myModel.put("path", "qualitat_PlaManteniment.control");
		myModel.put("formData", command);
		
		Long idManteniment = null;
		if (request.getParameter("idManteniment") != null){
			idManteniment = new Long(Long.parseLong(request.getParameter("idManteniment")));
		}
		myModel.put("idManteniment", idManteniment);
		
		
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
        myModel.put("responsableIntern", personalArray);
		
        
        ArrayList accions = new ArrayList();
        
        if (est.getId() != null){
        	oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
        	Collection<QualitatPlaManteniment> manteniments = oliQualitatEjb.mantenimentCercaTotsPerEstabliment(est.getId());
        	for (QualitatPlaManteniment mant : manteniments){
	        	BasicData basicData = new BasicData();
		        basicData.setId(mant.getAccions());
		        basicData.setNom(mant.getAccions());
		        accions.add(basicData);
        	}
        }
        
        myModel.put("accioRealitzada", accions);	
        
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
			QualitatPlaMantenimentControlCommand command = new QualitatPlaMantenimentControlCommand();
			
			if ((!isFormSubmission(request)) && (!isCreate(request))) {
				try {
					//Establiment establiment = (Establiment)request.getSession().getAttribute(establimentSessionKey);
					Long id = new Long(Long.parseLong(request.getParameter("id")));
					oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
					QualitatPlaMantenimentControl mantenimentControl = oliQualitatEjb.mantenimentControlAmbId(id);
					command.fromQualitatPlaMantenimentControl(mantenimentControl);

				} catch (RemoteException ex) {
					throw new ServletException("Error cridant l'EJB", ex);
				}
			} else {
				if (request.getParameter("idManteniment") != null){
					command.setIdManteniment(Long.valueOf(request.getParameter("idManteniment")));
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
