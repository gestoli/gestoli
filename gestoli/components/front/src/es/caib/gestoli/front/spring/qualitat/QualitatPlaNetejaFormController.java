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
import es.caib.gestoli.logic.model.QualitatPlaNeteja;
import es.caib.gestoli.logic.model.QualitatSubministre;

public class QualitatPlaNetejaFormController extends SimpleFormController {

	private static Logger logger = Logger.getLogger(QualitatPlaNetejaFormController.class);
	
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
		QualitatPlaNetejaCommand qualitatPlaNetejaCommand = (QualitatPlaNetejaCommand)command;

		Long plaId = qualitatPlaNetejaCommand.getCodi();
		
		String forward = getSuccessView();
		Map myModel = new HashMap();
		
		HttpSession session = request.getSession();
		Establiment est = (Establiment)session.getAttribute(establimentSessionKey);

		QualitatPlaNeteja plaNeteja = null;
		
		try{
			oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
			if (isCreate(request)){
				logger.info("Creant el registre: " + qualitatPlaNetejaCommand);
				plaNeteja = new QualitatPlaNeteja();
				plaNeteja.setEstabliment(est);
			} else {
				logger.info("Actualitzant el registre: " + qualitatPlaNetejaCommand);
				plaNeteja = oliQualitatEjb.plaNetejaAmbId(plaId);
			}
			
			Boolean esGeneral = qualitatPlaNetejaCommand.getEsGeneral();
			if (esGeneral == null)
				esGeneral = new Boolean(false);
			else
				esGeneral = new Boolean(true);
			plaNeteja.setEsGeneral(esGeneral);
			
			if (qualitatPlaNetejaCommand.getEquipId() != null){
				QualitatDescripcioEquip equip = oliQualitatEjb.getQualitatDescripcioEquipById(qualitatPlaNetejaCommand.getEquipId());
				plaNeteja.setEquip(equip);
			} else {
				plaNeteja.setEquip(null);
			}
				
			plaNeteja.setElementPlanta(qualitatPlaNetejaCommand.getElementPlanta());
			
			plaNeteja.setAccio(qualitatPlaNetejaCommand.getAccio());
			
			if (qualitatPlaNetejaCommand.getProducteId() != null){
				QualitatSubministre prod = oliQualitatEjb.subministreAmbId(qualitatPlaNetejaCommand.getProducteId());
				plaNeteja.setProducte(prod);
			}
			
			plaNeteja.setDosis(qualitatPlaNetejaCommand.getDosis());
			plaNeteja.setFrequencia(qualitatPlaNetejaCommand.getFrequencia());
			
			
			if (qualitatPlaNetejaCommand.getResponsableId() != null){
				QualitatDescripcioPersonal pers = oliQualitatEjb.personalAmbId(qualitatPlaNetejaCommand.getResponsableId());
				plaNeteja.setResponsable(pers);
			}
			
			if (qualitatPlaNetejaCommand.getRespVerificacioId() != null){
				QualitatDescripcioPersonal pers = oliQualitatEjb.personalAmbId(qualitatPlaNetejaCommand.getRespVerificacioId());
				plaNeteja.setRespVerificacio(pers);
			}
			
			plaNeteja.setPeriodicitatVerificacio(qualitatPlaNetejaCommand.getPeriodicitatVerificacio());

						
			// Cream el pla de neteja
			plaNeteja.setCodi(oliQualitatEjb.crearPlaNeteja(plaNeteja));
			
			if (isCreate(request)){
				ControllerUtils.saveMessageInfo(request, missatge("qualitat.plaNeteja.missatge.crear.ok"));
			}else{
				ControllerUtils.saveMessageInfo(request, missatge("qualitat.plaNeteja.missatge.modificar.ok"));				
			}
			
		} catch (Exception ex) {
			if (isCreate(request)){
				logger.error("Error creant el pla de neteja", ex);
				ControllerUtils.saveMessageError(request, missatge("qualitat.plaNeteja.missatge.crear.no"));
			} else {
				logger.error("Error modificant el pla de neteja", ex);
				ControllerUtils.saveMessageError(request, missatge("qualitat.plaNeteja.missatge.modificar.no"));
			}
		}
		
		forward += "?codi=" + plaNeteja.getCodi();
		
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
		

		myModel.put("path", "qualitat_PlaNeteja");
		myModel.put("idEstabliment", est.getId());
		myModel.put("formData", command);
		
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
		basicData.setId("quinzenal");
		basicData.setNom(bundle.getString("qualitat.plaNeteja.frecuencia.quinzenal"));
		frecuencia.add(basicData);
		basicData = new BasicData();
		basicData.setId("mensual");
		basicData.setNom(bundle.getString("qualitat.plaNeteja.frecuencia.mensual"));
		frecuencia.add(basicData);
		basicData = new BasicData();
		basicData.setId("bimensual");
		basicData.setNom(bundle.getString("qualitat.plaNeteja.frecuencia.bimensual"));
		frecuencia.add(basicData);
		basicData = new BasicData();
		basicData.setId("trimestral");
		basicData.setNom(bundle.getString("qualitat.plaNeteja.frecuencia.trimestral"));
		frecuencia.add(basicData);
		basicData = new BasicData();
		basicData.setId("semestral");
		basicData.setNom(bundle.getString("qualitat.plaNeteja.frecuencia.semestral"));
		frecuencia.add(basicData);
		basicData = new BasicData();
		basicData.setId("anual");
		basicData.setNom(bundle.getString("qualitat.plaNeteja.frecuencia.anual"));
		frecuencia.add(basicData);
		basicData = new BasicData();
		basicData.setId("necessitat");
		basicData.setNom(bundle.getString("qualitat.plaNeteja.frecuencia.necesaria"));
		frecuencia.add(basicData);
		myModel.put("frequencia", frecuencia);	
		
		//ELEMENTS PLANTA 
		ArrayList elementsPlanta = new ArrayList();
		BasicData basicData2 = new BasicData();
		basicData2.setId("terres");
		basicData2.setNom(bundle.getString("qualitat.plaNeteja.elementPlanta.terres"));
		elementsPlanta.add(basicData2);
		basicData2 = new BasicData();
		basicData2.setId("parets");
		basicData2.setNom(bundle.getString("qualitat.plaNeteja.elementPlanta.parets"));
		elementsPlanta.add(basicData2);
		basicData2 = new BasicData();
		basicData2.setId("sostres");
		basicData2.setNom(bundle.getString("qualitat.plaNeteja.elementPlanta.sostres"));
		elementsPlanta.add(basicData2);
		myModel.put("elementsPlanta", elementsPlanta);	
		
		
		// PRODUCTES
		oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
		Collection productes = oliQualitatEjb.subministresPerEstabliment(est.getId());
		myModel.put("productes", productes);
		

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
        
        
     // EQUIPS
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
        myModel.put("equips", equipArray);
        

		
		/*Collection tipusResponsables = new ArrayList();
		basicData = new BasicData();
		basicData.setId("i");
		basicData.setNom(bundle.getString("qualitat.plaNeteja.tipusResponsable.intern"));
		tipusResponsables.add(basicData);
		basicData = new BasicData();
		basicData.setId("e");
		basicData.setNom(bundle.getString("qualitat.plaNeteja.tipusResponsable.extern"));
		tipusResponsables.add(basicData);
	
		myModel.put("tipusResponsables", tipusResponsables);*/
		
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
			QualitatPlaNetejaCommand command = new QualitatPlaNetejaCommand();
			
			if ((!isFormSubmission(request)) && (!isCreate(request))) {
				try {
					//Establiment establiment = (Establiment)request.getSession().getAttribute(establimentSessionKey);
					Long id = new Long(Long.parseLong(request.getParameter("codi")));
					oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
					QualitatPlaNeteja plaNeteja = oliQualitatEjb.plaNetejaAmbId(id);
					command.fromQualitatPlaNeteja(plaNeteja);

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
		return (request.getParameter("codi") == null);
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
