package es.caib.gestoli.front.spring.qualitat;

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
import es.caib.gestoli.logic.interfaces.OliQualitatEjb;
import es.caib.gestoli.logic.model.Establiment;
import es.caib.gestoli.logic.model.QualitatDescripcioInstalacio;

public class QualitatDescripcioInstalacioFormController extends SimpleFormController {

	private static Logger logger = Logger.getLogger(QualitatDescripcioInstalacioFormController.class);
	
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
		QualitatDescripcioInstalacioCommand qualitatDescripcioInstalacioCommand = (QualitatDescripcioInstalacioCommand)command;
		String forward = getSuccessView();
		Map myModel = new HashMap();
		
		HttpSession session = request.getSession();
		Establiment est = (Establiment)session.getAttribute(establimentSessionKey);
		QualitatDescripcioInstalacio desc = est.getDescripcio();
		try {
			QualitatDescripcioInstalacio descInst = new QualitatDescripcioInstalacio(
					est,
					qualitatDescripcioInstalacioCommand.getSales(),
					qualitatDescripcioInstalacioCommand.getPlantes(),
					qualitatDescripcioInstalacioCommand.getAmple(),
					qualitatDescripcioInstalacioCommand.getLlarg(),
					qualitatDescripcioInstalacioCommand.getVestuaris(),
					qualitatDescripcioInstalacioCommand.getBanys(),
					qualitatDescripcioInstalacioCommand.getOficines());
		
			if (desc != null) {
				descInst.setId(desc.getId());
				oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
				oliQualitatEjb.actualitzarDescripcioInstalacio(descInst);
				ControllerUtils.saveMessageInfo(request, missatge("qualitat.descripcio.instalacio.missatge.modificar.ok"));
			} else {
				oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
				oliQualitatEjb.crearDescripcioInstalacio(descInst);
				ControllerUtils.saveMessageInfo(request, missatge("qualitat.descripcio.instalacio.missatge.crear.ok"));
			}
						
			
		} catch (Exception ex) {
			if (desc!=null) {
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
		myModel.put("path", "qualitat_DescripcioInstalacio");
		
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
			QualitatDescripcioInstalacioCommand command = new QualitatDescripcioInstalacioCommand();
			
			if (!isFormSubmission(request)) {
				Establiment establiment = (Establiment)request.getSession().getAttribute(establimentSessionKey);
				if (establiment.getDescripcio() == null){
			   		oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
					QualitatDescripcioInstalacio instalacio = oliQualitatEjb.descripcioInstalacioByEstabliment(establiment.getId());
					establiment.setDescripcio(instalacio);
				}
				command.setEstabliment(establiment);
				if (establiment.getDescripcio() != null){
					Long id = establiment.getDescripcio().getId();
					oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
					QualitatDescripcioInstalacio desc = oliQualitatEjb.descripcioInstalacioAmbId(id);
					if (desc != null){
						command.setSales(desc.getSales());
						command.setPlantes(desc.getPlantes());
						command.setAmple(desc.getAmple());
						command.setLlarg(desc.getLlarg());
						command.setVestuaris(desc.getVestuaris());
						command.setBanys(desc.getBanys());
						command.setOficines(desc.getOficines());
					}
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
