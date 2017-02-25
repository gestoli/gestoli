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
import es.caib.gestoli.logic.model.QualitatPlaNeteja;
import es.caib.gestoli.logic.model.QualitatPlaNetejaVerificacio;

public class QualitatPlaNetejaVerificacioFormController extends SimpleFormController {

	private static Logger logger = Logger.getLogger(QualitatPlaNetejaVerificacioFormController.class);
	
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
		QualitatPlaNetejaVerificacioCommand qualitatPlaNetejaVerificacioCommand = (QualitatPlaNetejaVerificacioCommand)command;

		Long netejaVerificacioId = qualitatPlaNetejaVerificacioCommand.getId();
		
		String forward = getSuccessView();
		Map myModel = new HashMap();
		
		HttpSession session = request.getSession();
		Establiment est = (Establiment)session.getAttribute(establimentSessionKey);

		QualitatPlaNetejaVerificacio plaNetejaVerificacio = null;
		
		try{
			oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
			if (isCreate(request)){
				logger.info("Creant el registre: " + qualitatPlaNetejaVerificacioCommand);
				plaNetejaVerificacio = new QualitatPlaNetejaVerificacio();
				if (qualitatPlaNetejaVerificacioCommand.getNetejaId() != null){
					plaNetejaVerificacio.setNeteja(oliQualitatEjb.plaNetejaAmbId(Long.valueOf(qualitatPlaNetejaVerificacioCommand.getNetejaId())));
				}
				// Introducimos el responsable por defecto.
				plaNetejaVerificacio.setDepartament(oliQualitatEjb.departamentAmbId(Long.valueOf("5")));
			} else {
				logger.info("Actualitzant el registre: " + qualitatPlaNetejaVerificacioCommand);
				plaNetejaVerificacio = oliQualitatEjb.netejaVerificacioAmbId(netejaVerificacioId);
			}
			

			// Buscamos la fecha del último control realizado a este mantenimiento.
			if (isCreate(request) && (qualitatPlaNetejaVerificacioCommand.getNetejaId() != null)){
				Collection mant = oliQualitatEjb.netejaVerificacioPerPlaNeteja(qualitatPlaNetejaVerificacioCommand.getNetejaId());
				if (!mant.isEmpty()){
					Iterator it = mant.iterator();
					plaNetejaVerificacio.setDataAnterior(((QualitatPlaNetejaVerificacio)(it.next())).getDataVerificacio());
				}
			}
			
			plaNetejaVerificacio.setDataVerificacio(qualitatPlaNetejaVerificacioCommand.getDataVerificacio());
			plaNetejaVerificacio.setSatisfactori(qualitatPlaNetejaVerificacioCommand.getSatisfactori());
			
			if (qualitatPlaNetejaVerificacioCommand.getResponsableId() != null){
				QualitatDescripcioPersonal responsable= oliQualitatEjb.personalAmbId(qualitatPlaNetejaVerificacioCommand.getResponsableId());
				plaNetejaVerificacio.setResponsableVerificacio(responsable);
				plaNetejaVerificacio.setResponsableVerificacio(responsable);

			}
			
						
			plaNetejaVerificacio.setId(oliQualitatEjb.crearPlaNetejaVerificacio(plaNetejaVerificacio));
			
			if (isCreate(request)){
				ControllerUtils.saveMessageInfo(request, missatge("qualitat.plaNeteja.verificacio.missatge.crear.ok"));
			}else{
				ControllerUtils.saveMessageInfo(request, missatge("qualitat.plaNeteja.verificacio.missatge.modificar.ok"));				
			}
			
		} catch (Exception ex) {
			if (isCreate(request)){
				logger.error("Error creant el pla de netejaVerificacio", ex);
				ControllerUtils.saveMessageError(request, missatge("qualitat.plaNeteja.verificacio.missatge.crear.no"));
			} else {
				logger.error("Error modificant el pla de netejaVerificacio", ex);
				ControllerUtils.saveMessageError(request, missatge("qualitat.plaNeteja.verificacio.missatge.modificar.no"));
			}
		}
		
		forward += "?id=" + plaNetejaVerificacio.getId() + "&idNeteja=" + plaNetejaVerificacio.getNeteja().getCodi();
		
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
		

		myModel.put("path", "qualitat_PlaNeteja.verificacio");
		myModel.put("formData", command);
		
		Long idNeteja = null;
		if (request.getParameter("idNeteja") != null){
			idNeteja = new Long(Long.parseLong(request.getParameter("idNeteja")));
		}
		myModel.put("idNeteja", idNeteja);
		
		
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
        myModel.put("responsable", personalArray);
		
        
        ArrayList accions = new ArrayList();
        
        if (est.getId() != null){
        	oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
        	Collection<QualitatPlaNeteja> netejas = oliQualitatEjb.plansNetejaPerEstabliment(est.getId());
        	for (QualitatPlaNeteja mant : netejas){
	        	BasicData basicData = new BasicData();
		        basicData.setId(mant.getAccio());
		        basicData.setNom(mant.getAccio());
		        accions.add(basicData);
        	}
        }
        
        myModel.put("accions", accions);	
        
        String queryString = request.getQueryString();   // d=789
        myModel.put("url", "plaNeteja");
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
			QualitatPlaNetejaVerificacioCommand command = new QualitatPlaNetejaVerificacioCommand();
			
			if ((!isFormSubmission(request)) && (!isCreate(request))) {
				try {
					//Establiment establiment = (Establiment)request.getSession().getAttribute(establimentSessionKey);
					Long id = new Long(Long.parseLong(request.getParameter("id")));
					oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
					QualitatPlaNetejaVerificacio netejaVerificacio = oliQualitatEjb.netejaVerificacioAmbId(id);
					command.fromQualitatPlaNetejaVerificacio(netejaVerificacio);

				} catch (RemoteException ex) {
					throw new ServletException("Error cridant l'EJB", ex);
				}
			} else {
				if (request.getParameter("idNeteja") != null){
					command.setNetejaId(Long.valueOf(request.getParameter("idNeteja")));
					oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
					QualitatPlaNeteja neteja = oliQualitatEjb.plaNetejaAmbId(Long.valueOf(request.getParameter("idNeteja")));
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
