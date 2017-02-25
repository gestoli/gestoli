package es.caib.gestoli.front.spring.qualitat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.propertyeditors.CustomBooleanEditor;
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
import es.caib.gestoli.logic.interfaces.OliQualitatEjb;
import es.caib.gestoli.logic.model.Establiment;
import es.caib.gestoli.logic.model.QualitatAiguaPuntSortida;
import es.caib.gestoli.logic.model.QualitatAiguaSubministre;
import es.caib.gestoli.logic.model.QualitatDescripcioPersonal;

public class QualitatAiguaSubministreFormController extends SimpleFormController {

	private static Logger logger = Logger.getLogger(QualitatAiguaSubministreFormController.class);
	
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
		QualitatAiguaSubministreCommand qualitatAiguaSubministreCommand = (QualitatAiguaSubministreCommand)command;
		String forward = getSuccessView();
		Map myModel = new HashMap();
		
		HttpSession session = request.getSession();
		Establiment est = (Establiment)session.getAttribute(establimentSessionKey);
		try {
			QualitatAiguaSubministre subm;
			/*if (est.getDescripcioSubministreAigua() == null){
				subm = new QualitatAiguaSubministre();
			} else {*/
				oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
				subm = oliQualitatEjb.aiguaSubministreByEstabliment(est.getId());
			//}
			if (subm == null){
				subm = new QualitatAiguaSubministre();
			}
			
			if (qualitatAiguaSubministreCommand.getResponsableId()!=null){
				subm.setResponsablePla(oliQualitatEjb.personalAmbId(qualitatAiguaSubministreCommand.getResponsableId()));}
				
			if (qualitatAiguaSubministreCommand.getNetejaInstalacio()!=null) subm.setNetejaInstalacio(true);
			else subm.setNetejaInstalacio(false);
			if (qualitatAiguaSubministreCommand.getHigienePersonal()!=null) subm.setHigienePersonal(true);
			else subm.setHigienePersonal(false);
			if (qualitatAiguaSubministreCommand.getNetejaRoba()!=null) subm.setNetejaRoba(true);
			else subm.setNetejaRoba(false);
			if (qualitatAiguaSubministreCommand.getElaboracioProductes()!=null) subm.setElaboracioProductes(true);
			else subm.setElaboracioProductes(false);
			subm.setAltresUsos(qualitatAiguaSubministreCommand.getAltresUsos());
			if (qualitatAiguaSubministreCommand.getXarxaPublica()!=null) subm.setXarxaPublica(true);
			else subm.setXarxaPublica(false);
			if (qualitatAiguaSubministreCommand.getAbastamentPropi()!=null) subm.setAbastamentPropi(true);
			else subm.setAbastamentPropi(false);
			if (qualitatAiguaSubministreCommand.getCloracio()!=null) subm.setCloracio(true);
			else subm.setCloracio(false);
			if (qualitatAiguaSubministreCommand.getOzonitzacio()!=null) subm.setOzonitzacio(true);
			else subm.setOzonitzacio(false);
			if (qualitatAiguaSubministreCommand.getFiltracio()!=null) subm.setFiltracio(true);
			else subm.setFiltracio(false);
			if (qualitatAiguaSubministreCommand.getDescalcificacio()!=null) subm.setDescalcificacio(true);
			else subm.setDescalcificacio(false);
			subm.setAltresTractaments(qualitatAiguaSubministreCommand.getAltresTractaments());
			
			subm.setEstabliment(est);
			
			//subm.setId(desc.getId());
			oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
			oliQualitatEjb.crearAiguaSubministre(subm);
			
			if (est.getDescripcioSubministreAigua() != null){
				ControllerUtils.saveMessageInfo(request, missatge("qualitat.descripcio.instalacio.missatge.modificar.ok"));
			} else {
				//oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
				//oliQualitatEjb.crearAiguaSubministre(subm);
				ControllerUtils.saveMessageInfo(request, missatge("qualitat.descripcio.instalacio.missatge.crear.ok"));
			}
						
			
		} catch (Exception ex) {
			if (est.getDescripcioSubministreAigua()!=null) {
				logger.error("Error modificant la descripció de la instal.lació", ex);
				ControllerUtils.saveMessageError(request, missatge("qualitat.descripcio.instalacio.missatge.modificar.no"));
			} else {
				logger.error("Error modificant la descripció de la instal.lació", ex);
				ControllerUtils.saveMessageError(request, missatge("qualitat.descripcio.instalacio.missatge.crear.no"));
			}
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
		
		Map myModel = new HashMap();
		HttpSession session = request.getSession();
		Establiment est = (Establiment)session.getAttribute(establimentSessionKey);
		

		myModel.put("path", "qualitat_PlaAbastamentAigua");
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
        myModel.put("responsable", personalArray);
        
        
        // PUNTS DE SORTIDA D'AIGUA
        	oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
        	Collection<QualitatAiguaPuntSortida> sortides = oliQualitatEjb.aiguaPuntsSortidaPerEstabliment(est.getId());
        myModel.put("sortides", sortides);
		
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
			QualitatAiguaSubministreCommand command = new QualitatAiguaSubministreCommand();
			if (!isFormSubmission(request)) {
				Establiment establiment = (Establiment)request.getSession().getAttribute(establimentSessionKey);
				//if (establiment.getDescripcioSubministreAigua() == null){
			   //		oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
				//	subministre = oliQualitatEjb.aiguaSubministreByEstabliment(establiment.getId());
					//establiment.setDescripcioSubministreAigua(subministre);
				//} else {
				//	oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
				//	Long id = establiment.getDescripcio().getId();
				oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
				QualitatAiguaSubministre subministre = oliQualitatEjb.aiguaSubministreByEstabliment(establiment.getId());

				if (subministre != null){
					command.fromQualitatAiguaSubministre(subministre);
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