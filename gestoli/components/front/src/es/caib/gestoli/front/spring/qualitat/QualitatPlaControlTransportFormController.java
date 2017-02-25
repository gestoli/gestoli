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
import es.caib.gestoli.logic.model.QualitatDescripcioEquip;
import es.caib.gestoli.logic.model.QualitatDescripcioPersonal;
import es.caib.gestoli.logic.model.QualitatPlaControlTransport;

public class QualitatPlaControlTransportFormController extends SimpleFormController {

	private static Logger logger = Logger.getLogger(QualitatPlaControlTransportFormController.class);
	
	private OliQualitatEjb oliQualitatEjb;
	private HibernateTemplate hibernateTemplate;
	private String establimentSessionKey;

	public ModelAndView onSubmit(
			HttpServletRequest request,
			HttpServletResponse response,
			Object command,
			BindException errors)
	throws ServletException {
		
		QualitatPlaControlTransportCommand qualitatPlaControlTransportCommand = (QualitatPlaControlTransportCommand)command;
		Long plaId = qualitatPlaControlTransportCommand.getId();
		
		String forward = getSuccessView();
		Map myModel = new HashMap();
		
		HttpSession session = request.getSession();
		Establiment est = (Establiment)session.getAttribute(establimentSessionKey);

		QualitatPlaControlTransport plaControlTransport = null;
		
		try{
			oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
			if (isCreate(request)){
				logger.info("Creant el registre: " + qualitatPlaControlTransportCommand);
				plaControlTransport = new QualitatPlaControlTransport();
				plaControlTransport.setEstabliment(est);
				plaControlTransport.setDepartament(oliQualitatEjb.departamentAmbId(Long.valueOf("13")));
			} else {
				logger.info("Actualitzant el registre: " + qualitatPlaControlTransportCommand);
				plaControlTransport = oliQualitatEjb.plaControlTransportAmbId(plaId);
			}
			
			if (qualitatPlaControlTransportCommand.getResponsableVerificacioId() != null){
				QualitatDescripcioPersonal responsableVerificacio = oliQualitatEjb.personalAmbId(qualitatPlaControlTransportCommand.getResponsableVerificacioId());
				plaControlTransport.setResponsableVerificacio(responsableVerificacio);
			}
			
			if (qualitatPlaControlTransportCommand.getVehicleId() != null){
				QualitatDescripcioEquip vehicle = oliQualitatEjb.getQualitatDescripcioEquipById(qualitatPlaControlTransportCommand.getVehicleId());
				plaControlTransport.setVehicle(vehicle);
			}
				
			plaControlTransport.setDataVerificacio(qualitatPlaControlTransportCommand.getDataVerificacio());
			
			
			Boolean estibaCorrecta = qualitatPlaControlTransportCommand.getEstibaCorrecta();
			if (estibaCorrecta == null){
				estibaCorrecta = new Boolean(false);
			} else {
				estibaCorrecta = new Boolean(true);
			}
			plaControlTransport.setEstibaCorrecta(estibaCorrecta);
			
			Boolean netejaCorrecta = qualitatPlaControlTransportCommand.getNetejaCorrecta();
			if (netejaCorrecta == null){
				netejaCorrecta = new Boolean(false);
			} else {
				netejaCorrecta = new Boolean(true);
			}
			plaControlTransport.setNetejaCorrecta(netejaCorrecta);
			
			plaControlTransport.setSatisfactori(estibaCorrecta && netejaCorrecta); 
			plaControlTransport.setNoConformitats(qualitatPlaControlTransportCommand.getNoConformitats());
			
			
			// Cream el pla de neteja
			plaControlTransport.setId(oliQualitatEjb.crearPlaControlTransport(plaControlTransport));
			
			if (isCreate(request)){
				ControllerUtils.saveMessageInfo(request, missatge("qualitat.plaControlTransport.missatge.crear.ok"));
			}else{
				ControllerUtils.saveMessageInfo(request, missatge("qualitat.plaControlTransport.missatge.modificar.ok"));				
			}
			
		} catch (Exception ex) {
			if (isCreate(request)){
				logger.error("Error creant el pla de neteja", ex);
				ControllerUtils.saveMessageError(request, missatge("qualitat.plaControlTransport.missatge.crear.no"));
			} else {
				logger.error("Error modificant el pla de neteja", ex);
				ControllerUtils.saveMessageError(request, missatge("qualitat.plaControlTransport.missatge.modificar.no"));
			}
		}
	
		forward += "?id=" + plaControlTransport.getId();
		
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
		

		myModel.put("path", "qualitat_PlaControlTransport");
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
        myModel.put("personal", personalArray);
        
        
     // EQUIPS
        Collection equipArray = new ArrayList();
        if (est.getId() != null){
        	oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
        	Collection<QualitatDescripcioEquip> equips = oliQualitatEjb.vehiclesByEstabliment(est.getId());
	        for (QualitatDescripcioEquip equ : equips){
	        	if (equ.getCodi() != null) {
	        		BasicData basicData = new BasicData();
					basicData = new BasicData();
					basicData.setId(equ.getCodi().toString());
					basicData.setNom(equ.getNom().toString() + " - " + equ.getNumSerie().toString());
					equipArray.add(basicData);
				}
	        }
        }
        myModel.put("equips", equipArray);
        

        String queryString = request.getQueryString();   // d=789
        myModel.put("url", "plaControlTransport");
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
			QualitatPlaControlTransportCommand command = new QualitatPlaControlTransportCommand();
			
			if ((!isFormSubmission(request)) && (!isCreate(request))) {
				try {
					//Establiment establiment = (Establiment)request.getSession().getAttribute(establimentSessionKey);
					Long id = new Long(Long.parseLong(request.getParameter("id")));
					oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
					QualitatPlaControlTransport plaControlTransport = oliQualitatEjb.plaControlTransportAmbId(id);
					command.fromQualitatPlaControlTransport(plaControlTransport);

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
