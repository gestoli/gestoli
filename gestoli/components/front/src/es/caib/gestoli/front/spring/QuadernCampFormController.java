package es.caib.gestoli.front.spring;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

import es.caib.gestoli.front.util.BasicData;
import es.caib.gestoli.front.util.Idioma;
import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;
import es.caib.gestoli.logic.model.Olivicultor;
import es.caib.gestoli.logic.model.Plantacio;
import es.caib.gestoli.logic.model.QuadernCamp;
import es.caib.gestoli.logic.model.Usuari;

public class QuadernCampFormController extends SimpleFormController {

	private static Logger logger = Logger.getLogger(QuadernCampFormController.class);
	
	private OliInfraestructuraEjb oliInfraestructuraEjb;
	private HibernateTemplate hibernateTemplate;
	private String usuariSessionKey;


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
		QuadernCampCommand quadernCampCommand = (QuadernCampCommand)command;
		
		Long quadernCampId = quadernCampCommand.getId();
		
		String forward = getSuccessView();
		Map myModel = new HashMap();
		
		HttpSession session = request.getSession();
		Usuari usuari = (Usuari)session.getAttribute(usuariSessionKey);

		QuadernCamp quadernCamp = null;
		
		try{
			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
			if (isCreate(request)){
				logger.info("Creant el registre: " + quadernCampCommand);
				quadernCamp = new QuadernCamp();
				quadernCamp.setOlivicultor(oliInfraestructuraEjb.olivicultorUsuari(usuari.getId()));
			} else {
				logger.info("Actualitzant el registre: " + quadernCampCommand);
				quadernCamp = oliInfraestructuraEjb.quadernCampAmbId(quadernCampId);
			}
			
			quadernCamp.setData(quadernCampCommand.getData());

			if (quadernCampCommand.getIdPlantacio() != null){
				Plantacio plantacio= oliInfraestructuraEjb.plantacioAmbId(quadernCampCommand.getIdPlantacio());
				quadernCamp.setPlantacio(plantacio);
			}
			
			quadernCamp.setTractament(quadernCampCommand.getTractament());
			quadernCamp.setMateriaActiva(quadernCampCommand.getMateriaActiva());
			quadernCamp.setMarca(quadernCampCommand.getMarca());
			quadernCamp.setDosi(quadernCampCommand.getDosi());
			quadernCamp.setLitresBrou(quadernCampCommand.getLitresBrou());
			quadernCamp.setTerminiSeguretat(quadernCampCommand.getTerminiSeguretat());
			quadernCamp.setObservacions(quadernCampCommand.getObservacions());
			
			// Creamos el aviso
			quadernCamp.setId(oliInfraestructuraEjb.crearQuadernCamp(quadernCamp));
		
			if (isCreate(request)){
				ControllerUtils.saveMessageInfo(request, missatge("quadernCamp.missatge.crear.ok"));
			}else{
				ControllerUtils.saveMessageInfo(request, missatge("quadernCamp.missatge.modificar.ok"));				
			}
				
		} catch (Exception ex) {
			if (isCreate(request)){
				logger.error("Error creant l'avis", ex);
				ControllerUtils.saveMessageError(request, missatge("quadernCamp.missatge.crear.no"));
			} else {
				logger.error("Error modificant l'avis", ex);
				ControllerUtils.saveMessageError(request, missatge("quadernCamp.missatge.modificar.no"));
			}
		}
		
		forward += "?id=" + quadernCamp.getId();
		
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
		Usuari usuari = (Usuari)session.getAttribute(usuariSessionKey);
		

		myModel.put("path", "avis");
		myModel.put("idOlivicultor", oliInfraestructuraEjb.olivicultorUsuari(usuari.getId()).getId());
		myModel.put("formData", command);
		
		//plantacions (tancamentLlibres)
		ArrayList plantacions = new ArrayList();
		//plantacions = oliInfraestructuraEjb.PlantacioActivasByIdOlivicultor(usuari.getId());
		
		BasicData basicData;
		for (Object plantacio : oliInfraestructuraEjb.PlantacioActivasByIdOlivicultor(oliInfraestructuraEjb.olivicultorUsuari(usuari.getId()).getId())){
			basicData = new BasicData();
			basicData.setId(((Plantacio)plantacio).getId().toString());
			basicData.setNom( ((Plantacio)plantacio).getFinca().getNom().toString() + " - " + ((Plantacio)plantacio).getNomComplet().toString());
			plantacions.add(basicData);
		}
		
		Collections.sort(
				plantacions,
				new Comparator() {
					public int compare(Object obj1, Object obj2) {
						String nom1 = ((BasicData)obj1).getNom();
						String nom2 = ((BasicData)obj2).getNom();
						
						if (nom1 == null && nom2 == null) return 0;
						if (nom1 == null) return -1;
						if (nom2 == null) return 1;
						
						return nom1.compareTo(nom2);
						
					}
				});
		
		myModel.put("plantacions", plantacions);
		
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
			QuadernCampCommand command = new QuadernCampCommand();
			
			if ((!isFormSubmission(request)) && (!isCreate(request))) {
				try {
					//Establiment establiment = (Establiment)request.getSession().getAttribute(establimentSessionKey);
					Long id = new Long(Long.parseLong(request.getParameter("id")));
					oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
					QuadernCamp quadernCamp = oliInfraestructuraEjb.quadernCampAmbId(id);
					command.fromQuadernCamp(quadernCamp);

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


	public void setOliInfraestructuraEjb(OliInfraestructuraEjb oliInfraestructuraEjb) {
		this.oliInfraestructuraEjb = oliInfraestructuraEjb;
	}

    /**
	 * Injecció de la dependència usuariSessionKey
	 *
	 * @param usuariSessionKey La classe a injectar.
	 */
	public void setUsuariSessionKey(String usuariSessionKey) {
		this.usuariSessionKey = usuariSessionKey;
	}
	
}
