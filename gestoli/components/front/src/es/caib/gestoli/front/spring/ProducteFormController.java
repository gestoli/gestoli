package es.caib.gestoli.front.spring;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
import org.springframework.web.servlet.mvc.SimpleFormController;

import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;
import es.caib.gestoli.logic.model.Establiment;
import es.caib.gestoli.logic.model.Producte;

public class ProducteFormController extends SimpleFormController {

	private final Logger logger = Logger.getLogger(getClass());
	private String establimentSessionKey;
	private OliInfraestructuraEjb oliInfraestructuraEjb;
	private HibernateTemplate hibernateTemplate;
	private Boolean esPopup;
	
	/**
	 * Funció cridada pel metode POST del formulari ProducteForm.html
	 * Llistat de Productes.
	 * 
	 * @param request
	 * @param command
	 * @param myModel
	 * @return
	 * @throws ServletException
	 */
	public ModelAndView onSubmit(
			HttpServletRequest request,
			HttpServletResponse response,
			Object command,
			BindException errors)
	throws ServletException {
		
		ProducteCommand producte = (ProducteCommand)command;
		String nom = producte.getNom();
		Boolean actiu = Boolean.valueOf(true);
		if (producte.getActiu() == null){
			actiu = Boolean.valueOf(false);
		}
		
		HttpSession session = request.getSession();
		Establiment establiment = (Establiment)session.getAttribute(establimentSessionKey);
		
		Map myModel = new HashMap();
		Long id = null;
		try {
			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
			if (isCreate(request)) {
				logger.info("Creant nou producte");
				id = oliInfraestructuraEjb.creaProducte(
						nom, 
						establiment);
				ControllerUtils.saveMessageInfo(request, missatge("producte.missatge.crear.ok"));
				myModel.put("ok", "ok");
			} else {
				id = new Long(Long.parseLong(request.getParameter("id")));
				logger.info("Modificant el producte " + id);
				oliInfraestructuraEjb.modificaProducte(
						id,
						nom, 
						actiu);
				ControllerUtils.saveMessageInfo(request, missatge("producte.missatge.modificar.ok"));
			}
		} catch (Exception ex) {
			if (isCreate(request)) {
				logger.error("Error creant el producte", ex);
				ControllerUtils.saveMessageError(request, missatge("producte.missatge.crear.no"));
			} else {
				logger.error("Error modificant el producte", ex);
				ControllerUtils.saveMessageError(request, missatge("producte.missatge.modificar.no"));
			}
		}
		
		String forward = getSuccessView()+ "?id=" + id;
		return new ModelAndView(forward, myModel);
	}

	
	/**
	 * Retorna todos los datos del modelo necesarios para mostrar
	 * el formulario de insercion (LOVs y cosas de esas) si no hay.
	 * @see SimpleFormController#referenceData(javax.servlet.http.HttpServletRequest, java.lang.Object, org.springframework.validation.Errors)
	 */
	protected Map referenceData(
			HttpServletRequest request,
			Object command,
			Errors errors) throws Exception {
		
		Map myModel = new HashMap();

		HttpSession session = request.getSession();
		Establiment establiment = (Establiment)session.getAttribute(establimentSessionKey);
		if (establiment.getId() != null){
			myModel.put("establimentId", establiment.getId());
		}

		if (isCreate(request)) {
			myModel.put("path", "crear_producte");
		}else{
			myModel.put("path", "modificar_producte");
		}

		return myModel;
	}

	/**
	 * Funcio cridada pel metode GET del formulari ProducteForm.html
	 * Llistat de Partides d'oli.
	 * 
	 * @param request
	 * @param command
	 * @param myModel
	 * @return
	 * @throws ServletException
	 */
	/**
	 * En caso de que sea una edicion retorna el objeto rellenado con
	 * los datos actuales del registro. En caso de que sea una creacion
	 * retorna el objeto vacio.
	 * @see AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
	 */
	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		ProducteCommand producte= new ProducteCommand();
		if ((!isFormSubmission(request)) && (!isCreate(request))) {
			try {
				Long codi = new Long(Long.parseLong(request.getParameter("id")));
				logger.info("Obtenint dades per a la modificacio del producte " + codi);
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				Producte p = oliInfraestructuraEjb.producteAmbId(codi);
				producte.fromProducte(p);
			} catch (Exception ex) {
				logger.error("Error obtenint la informacio del producte", ex);
				throw new ServletException("Error cridant l'EJB", ex);
			}
		}
		if (isCreate(request)){
			producte.setActiu(new Boolean(true));
		}
		return producte;
	}

	protected void initBinder(
			HttpServletRequest request,
			ServletRequestDataBinder binder)
	throws Exception {
		binder.registerCustomEditor(
	    		Date.class,
	    		new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), true));
		binder.registerCustomEditor(
				Integer.class,
	    		new CustomNumberEditor(Integer.class, true));
		binder.registerCustomEditor(
	    		Long.class,
	    		new CustomNumberEditor(Long.class, true));
		binder.registerCustomEditor(
	    		Float.class,
	    		new CustomNumberEditor(Float.class, true));
		binder.registerCustomEditor(
	    		Boolean.class,
	    		new CustomBooleanEditor("S", "N", true));
	}
	
	/**
	 * Indica si la petición es de creación o no.
	 * @param request
	 * @return true si es una petición.
	 */
	private boolean isCreate(HttpServletRequest request) {
		return (request.getParameter("id") == null);
	}
	
	public void setEstablimentSessionKey(String establimentSessionKey) {
		this.establimentSessionKey = establimentSessionKey;
	}

	public void setOliInfraestructuraEjb(OliInfraestructuraEjb oliInfraestructuraEjb) {
		this.oliInfraestructuraEjb = oliInfraestructuraEjb;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
	
	private String missatge(String clave){
		String valor = getMessageSourceAccessor().getMessage(clave);
		return valor;
	}

	public void setEsPopup(Boolean esPopup) {
		this.esPopup = esPopup;
	}
	
}
