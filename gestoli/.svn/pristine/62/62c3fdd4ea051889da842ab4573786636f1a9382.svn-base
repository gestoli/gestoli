package es.caib.gestoli.front.spring;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;
import es.caib.gestoli.logic.model.Campanya;
import es.caib.gestoli.logic.model.RendimentVarietat;
import es.caib.gestoli.logic.model.VarietatOliva;

public class RendimentVarietatFormController extends SimpleFormController {
	
	private static Logger logger = Logger.getLogger(RendimentVarietatFormController.class);
	private OliInfraestructuraEjb oliInfraestructuraEjb;
	private HibernateTemplate hibernateTemplate;
	private String rolDoGestor;

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
		RendimentVarietatCommand rendimentVarietatCommand = (RendimentVarietatCommand)command;
		String forward = getSuccessView();
		Map myModel = new HashMap();

		String idCampanya = request.getParameter("idCampanya");
		idCampanya = (idCampanya != null)?idCampanya:"";
		
		try {
			if (idCampanya.equals("")) {
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				idCampanya = ""+oliInfraestructuraEjb.campanyaCercaActual();
			}
			
			String tipusRendiment = request.getParameter("tipusRendimentCampanya");
			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
			Campanya campanya = oliInfraestructuraEjb.campanyaAmbId(new Integer(idCampanya));
			
			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
			oliInfraestructuraEjb.creaRendimentsVarietatsCampanya(campanya, tipusRendiment, rendimentVarietatCommand.getRendiments());
			
			ControllerUtils.saveMessageInfo(request, missatge("accio.rendiment.varietat.missatge.crear.ok"));
		} catch (Exception ex) {
			logger.error("Error creant els nous rendiments ", ex);
			ControllerUtils.saveMessageError(request, missatge("accio.rendiment.varietat.missatge.crear.no"));
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
		myModel.put("path", "nou_rendiment");
		
		String idCampanya = request.getParameter("idCampanya");
		idCampanya = (idCampanya != null)?idCampanya:"";
		
		try {
			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
			Collection varietats = oliInfraestructuraEjb.varietatsOlivaActiu();
			
			if (idCampanya.equals("")) {
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				idCampanya = ""+oliInfraestructuraEjb.campanyaCercaActual();
			}
			
			Long id = new Long(idCampanya);
			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
			Collection rendiments = oliInfraestructuraEjb.rendimentVarietatPerCampanya(id);
			if ((rendiments != null) && (rendiments.size() > 0)) {
				myModel.put("varietats", rendiments);
			} else {
				
				Collection rendimentTransformat = new ArrayList();
				
				for(Object o: varietats){
					VarietatOliva varietat = (VarietatOliva)o;
					
					RendimentVarietat rendiment = new RendimentVarietat();
					rendiment.setVarietatOliva(varietat);
					rendiment.setIdVarietatOliva(varietat.getId().longValue());
					
					rendimentTransformat.add(rendiment);
				}
				
				myModel.put("varietats", rendimentTransformat);
			}
			
		} catch (Exception ex) {
			logger.error("Error obtenint les dades per crear els nous rendiments ", ex);
		}
		
		myModel.put("idCampanya", idCampanya);
		
		Collection tipusRendiment = new ArrayList();
		HashMap<String, String> hectarea = new HashMap<String, String>();
		hectarea.put("value", "hectarea");
		hectarea.put("label", "Hectarea");
		tipusRendiment.add(hectarea);
		HashMap<String, String> arbre = new HashMap<String, String>();
		arbre.put("value", "arbre");
		arbre.put("label", "Arbre");
		tipusRendiment.add(arbre);
        myModel.put("tipusRendiment", tipusRendiment);
		
		return myModel;
	}


	/**
	 * En caso de que sea una edición retorna el objeto rellenado con
	 * los datos actuales del registro. En caso de que sea una creación
	 * retorna el objeto vacío.
	 * @see AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
	 */
	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		RendimentVarietatCommand rendimentVarietatCommand = null;
		try {
			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
			Collection varietats = oliInfraestructuraEjb.varietatsOlivaActiu();
			
			String idCampanya = request.getParameter("idCampanya");
			idCampanya = (idCampanya != null)?idCampanya:"";
			
			if (idCampanya.equals("")) {
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				idCampanya = ""+oliInfraestructuraEjb.campanyaCercaActual();
			}
			
			Long id = new Long(idCampanya);
			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
			Collection rendiments = oliInfraestructuraEjb.rendimentVarietatPerCampanya(id);
			if ((rendiments != null) && (rendiments.size() > 0)) {
				rendimentVarietatCommand = new RendimentVarietatCommand(rendiments, true);
			} else {
				rendimentVarietatCommand = new RendimentVarietatCommand(varietats, false);
			}
		} catch (Exception ex) {
			throw new ServletException("Error cridant l'EJB", ex);
		}
		return rendimentVarietatCommand;
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
	 * Inyección de la dependencia informacioEjb
	 * @param informacioEjb La clase a inyectar.
	 */
	public void setOliInfraestructuraEjb(OliInfraestructuraEjb oliInfraestructuraEjb) {
		this.oliInfraestructuraEjb = oliInfraestructuraEjb;
	}

	private String missatge(String clave){
		String valor = getMessageSourceAccessor().getMessage(clave);
		return valor;
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

	public void setRolDoGestor(String rolDoGestor) {
		this.rolDoGestor = rolDoGestor;
	}
}