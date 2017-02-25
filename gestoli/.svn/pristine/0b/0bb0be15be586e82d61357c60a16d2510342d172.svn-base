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
import es.caib.gestoli.logic.model.QualitatControl;
import es.caib.gestoli.logic.model.QualitatDescripcioPersonal;
import es.caib.gestoli.logic.model.QualitatNoConformitat;
import es.caib.gestoli.logic.model.QualitatDepartament;
import es.caib.gestoli.logic.model.QualitatNoConformitatAccio;
import es.caib.gestoli.logic.model.QualitatPlaFormacio;

public class QualitatNoConformitatFormController extends SimpleFormController{

	private static Logger logger = Logger.getLogger(QualitatNoConformitatFormController.class);
		
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
		
		QualitatNoConformitatCommand qualitatNoConformitatCommand = (QualitatNoConformitatCommand)command;
		Long noConformitatId = qualitatNoConformitatCommand.getId();
		
		String forward = getSuccessView();
		Map myModel = new HashMap();
		
		HttpSession session = request.getSession();
		Establiment est = (Establiment)session.getAttribute(establimentSessionKey);

		QualitatNoConformitat noConformitat = null;
		
		try{
			oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
			if (isCreate(request)){
				logger.info("Creant el registre: " + qualitatNoConformitatCommand);
				noConformitat = new QualitatNoConformitat();
				noConformitat.setEstabliment(est);

				if (qualitatNoConformitatCommand.getIdControl() != null){
					QualitatControl control = oliQualitatEjb.controlAmbId(qualitatNoConformitatCommand.getIdControl());
					noConformitat.setControl(control);
				}
				
			} else {
				logger.info("Actualitzant el registre: " + qualitatNoConformitatCommand);
				noConformitat = oliQualitatEjb.noConformitatAmbId(noConformitatId);
			}
			
			noConformitat.setDataNoConformitat(qualitatNoConformitatCommand.getDataNoConformitat());
			
			if (qualitatNoConformitatCommand.getIdResponsableDeteccio() != null){
				QualitatDescripcioPersonal responsableDeteccio= oliQualitatEjb.personalAmbId(qualitatNoConformitatCommand.getIdResponsableDeteccio());
				noConformitat.setResponsableDeteccio(responsableDeteccio);
			}
			
			noConformitat.setDescripcio(qualitatNoConformitatCommand.getDescripcio());
			noConformitat.setCausa(qualitatNoConformitatCommand.getCausa());

			
			if (qualitatNoConformitatCommand.getIdDepartament() != null){
				QualitatDepartament dep = oliQualitatEjb.departamentAmbId(qualitatNoConformitatCommand.getIdDepartament());
				noConformitat.setDepartament(dep);
			}
			
			noConformitat.setDataTancament(qualitatNoConformitatCommand.getDataTancament());
			
			// Creamos la no conformidad
			noConformitat.setId(oliQualitatEjb.crearNoConformitat(noConformitat));
			
			// Añadimos la accion
			/*if (!("".equals(qualitatNoConformitatCommand.getAccio().trim()))){
				oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
				QualitatNoConformitatAccio accio = new QualitatNoConformitatAccio();
				accio.setAccio(qualitatNoConformitatCommand.getAccio());
				//QualitatNoConformitat noConf = new QualitatNoConformitat();
				//noConf.setId(noConformitatId);
				accio.setNoConformitat(noConformitat);
				oliQualitatEjb.crearNoConformitatAccio(accio);
				//noConformitat.getAccions().add(accio);
			}*/
			

			if (isCreate(request)){
				ControllerUtils.saveMessageInfo(request, missatge("qualitat.noConformitat.missatge.crear.ok"));
			}else{
				ControllerUtils.saveMessageInfo(request, missatge("qualitat.noConformitat.missatge.modificar.ok"));				
			}
			
		} catch (Exception ex) {
			if (isCreate(request)){
				logger.error("Error creant el control de noConformitat", ex);
				ControllerUtils.saveMessageError(request, missatge("qualitat.noConformitat.missatge.crear.no"));
			} else {
				logger.error("Error modificant el control de noConformitat", ex);
				ControllerUtils.saveMessageError(request, missatge("qualitat.noConformitat.missatge.modificar.no"));
			}
		}
		
		forward += "?id=" + noConformitat.getId();
		

        myModel.put("url", request.getParameter("paramURL"));
        myModel.put("params", request.getParameter("paramParams"));
		
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
		QualitatNoConformitatCommand form = (QualitatNoConformitatCommand)command;
		

		myModel.put("path", "qualitat_NoConformitat");
		myModel.put("idEstabliment", est.getId());
		myModel.put("formData", command);
	
		if (request.getParameter("idControl") != null && !"".equalsIgnoreCase(request.getParameter("idControl"))){
			Long idControl = new Long(Long.parseLong(request.getParameter("idControl")));
			myModel.put("idControl", idControl);
		}
			
		BasicData basicData;
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
        myModel.put("personal", personalArray);

    	oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());

		// DEPARTAMENTS
		Collection departamentsArray = new ArrayList();
    	oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
    	Collection<QualitatDepartament> departaments = oliQualitatEjb.departamentCercaTots();
        for (QualitatDepartament dep : departaments){
        	if (dep.getId() != null) {
        		basicData = new BasicData();
				basicData.setId(dep.getId().toString());
				basicData.setNom(missatge("qualitat.departament."+dep.getNom()).toString());
				departamentsArray.add(basicData);
			}
        }
        myModel.put("departaments", departamentsArray);
        
        myModel.put("url", request.getParameter("url"));
        myModel.put("params", request.getParameter("params"));
        
		
		oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
		//Collection accions = oliQualitatEjb.noConformitatAccioCercaTotsPerNoConformitat(form.getId());
		//myModel.put("accions", accions);
		
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
			QualitatNoConformitatCommand command;
			
			if ((!isFormSubmission(request)) && (!isCreate(request))) {
				try {
					//Establiment establiment = (Establiment)request.getSession().getAttribute(establimentSessionKey);
					Long id = new Long(Long.parseLong(request.getParameter("id")));
					oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
					QualitatNoConformitat noConformitat = oliQualitatEjb.noConformitatAmbId(id);
					command = new QualitatNoConformitatCommand();
					command.fromQualitatNoConformitat(noConformitat);

				} catch (RemoteException ex) {
					throw new ServletException("Error cridant l'EJB", ex);
				}
			} else {
				try{
					command = new QualitatNoConformitatCommand();
					if (request.getParameter("idControl") != null && !"".equalsIgnoreCase(request.getParameter("idControl"))){
						Long idControl = new Long(Long.parseLong(request.getParameter("idControl")));
						//command.setControl(OliQualitatEjbcontrol);
						oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
						QualitatControl ctl = oliQualitatEjb.controlAmbId(idControl);
						command.setControl(ctl);
						command.setIdControl(idControl);
						//oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
						//QualitatDepartament dep = oliQualitatEjb.departamentAmbId(Long.valueOf("3"));
						command.setDepartament(ctl.getDepartament());
						command.setIdDepartament(ctl.getDepartament().getId());
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
		return (("".equalsIgnoreCase(request.getParameter("id"))) || (request.getParameter("id") == null));
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
