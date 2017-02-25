package es.caib.gestoli.front.spring;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;
import es.caib.gestoli.logic.interfaces.OliProcessosEjb;
import es.caib.gestoli.logic.model.Diposit;
import es.caib.gestoli.logic.model.Establiment;

/**
 * <p>
 * Controlador para las acciones de dar de alta o editar un registro de tipos de
 * información.
 * </p>
 * <p>
 * Los parámetros de la petición HTTP relevantes para este controlador son:
 * <ul>
 * <li><code>id</code> - Identificador del registro. Este parámetro es el que
 * determina si se ha de mostrar la página de creación o la página de edición.</li>
 * </ul>
 * </p>
 * <p>
 * No hay información para la vista:
 * <p>
 * Este controlador distingue entre las peticiones del tipo GET y las de tipo
 * POST. Si la petición es de tipo POST se ejecuta la acción de creación o de
 * edición. Si la petición es de tipo GET solo se muestra la página.
 * 
 * 
 */
public class PendentAnaliticaFormController extends SimpleFormController {
	private static Logger logger = Logger
			.getLogger(PendentAnaliticaFormController.class);
	private OliInfraestructuraEjb oliInfraestructuraEjb;
	private OliProcessosEjb oliProcessosEjb;
	private String seleccioSessionKeyOrigen;

	private HibernateTemplate hibernateTemplate;
    
	/**
	 * Se llama cuando se aceptan las modificaciones del objeto. Solo se ejecuta
	 * esta función en el caso de que se haya ejecutado la validación sin
	 * producir ningún error.
	 * 
	 * @see SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse, java.lang.Object,
	 *      org.springframework.validation.BindException)
	 */
	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws ServletException {

		PendentAnaliticaCommand pendentAnaliticaCommand = (PendentAnaliticaCommand) command;
		Establiment establiment = null;
		

		if (isCreate(request)) {
			try {

				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				Diposit diposit = oliInfraestructuraEjb.dipositAmbId(pendentAnaliticaCommand.getIdDiposit());

				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				establiment = oliInfraestructuraEjb.establimentAmbId(pendentAnaliticaCommand.getIdEstabliment());
				
				diposit.setPrecintat(true);
				
				oliProcessosEjb.setHibernateTemplate(getHibernateTemplate());
				oliProcessosEjb.dipositModificar(diposit);
				
				ControllerUtils.saveMessageInfo(request, missatge("pendentAnalitica.missatge.crear.ok"));
			} catch (Exception ex) {
				logger.error("Error creant el analitica", ex);
				ControllerUtils.saveMessageError(request,
						missatge("pendentAnalitica.missatge.crear.no"));
			}
		}

		String desti = "EstablimentPrincipal.html?establimentId=" + establiment.getId();
		return new ModelAndView("redirect:" + desti);

	}

	/**
	 * Retorna todos los datos del modelo necesarios para mostrar el formulario
	 * de inserción (LOVs y cosas de esas) si no hay.
	 * 
	 * @see SimpleFormController#referenceData(javax.servlet.http.HttpServletRequest,
	 *      java.lang.Object, org.springframework.validation.Errors)
	 */
	@SuppressWarnings("unchecked")
	protected Map referenceData(HttpServletRequest request, Object command, Errors errors) throws Exception {
		Map myModel = new HashMap();

		String establimentId = request.getParameter("establimentId");
		
		Establiment establiment = null;//(Establiment) request.getSession().getAttribute(establimentSessionKey);
		oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
		if (establimentId != null && !"".equals(establimentId)) establiment = oliInfraestructuraEjb.establimentAmbId(new Long(establimentId));

		Collection origenList = (Collection) request.getSession().getAttribute(seleccioSessionKeyOrigen);

		Collection origen = null;
		Diposit diposit = null;
		if (origenList!= null && origenList.size() != 0) {
			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
			origen = oliInfraestructuraEjb.dipositsInfo(origenList.toArray());
			Iterator it = origen.iterator();
			if (it.hasNext())
				diposit = (Diposit) it.next();
		}		
		request.getSession().setAttribute("diposit", diposit);
		if(establiment!= null){
			request.getSession().setAttribute("establiment", establiment);
		}		
			
		myModel.put("path", "pendentAnalitica");
		if(establiment!= null && establiment.getNom()!= null && !establiment.getNom().equalsIgnoreCase("")){
			if(diposit!= null && diposit.getCodiAssignat()!= null && !diposit.getCodiAssignat().equals("")){
				myModel.put("path_extension1",establiment.getNom()+" - "+ diposit.getCodiAssignat());
			}
			
		}
		
		return myModel;
	}

	/**
	 * En caso de que sea una edición retorna el objeto rellenado con los datos
	 * actuales del registro. En caso de que sea una creación retorna el objeto
	 * vacío.
	 * 
	 * @see AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
	 */
	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		
		PendentAnaliticaCommand analitica = new PendentAnaliticaCommand();

		return analitica;
	}

	/**
	 * Configuración del <i>binder</i>. Si hay campos en el <i>command</i> con
	 * tipos que no sean <i>String</i> se ha de configurar su correspondiente
	 * binder aquí.
	 * 
	 * @see BaseCommandController#initBinder(javax.servlet.http.HttpServletRequest,
	 *      org.springframework.web.bind.ServletRequestDataBinder)
	 */
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
		
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
        binder.registerCustomEditor(
        		Date.class,
        		new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), true));
	}

	/**
	 * Indica si la petición es de creación o no.
	 * 
	 * @param request
	 * @return true si es una petición de creación o false si es de edición.
	 */
	private boolean isCreate(HttpServletRequest request) {
		return (request.getParameter("id") == null);
	}

	/**
	 * Inyección de la dependencia informacioEjb
	 * 
	 * @param informacioEjb
	 *            La clase a inyectar.
	 */
	public void setOliInfraestructuraEjb(
			OliInfraestructuraEjb oliInfraestructuraEjb) {
		this.oliInfraestructuraEjb = oliInfraestructuraEjb;
	}

	private String missatge(String clave) {
		String valor = getMessageSourceAccessor().getMessage(clave);
		return valor;
	}


	public void setSeleccioSessionKeyOrigen(String seleccioSessionKeyOrigen) {
		this.seleccioSessionKeyOrigen = seleccioSessionKeyOrigen;
	}

	public void setOliProcessosEjb(OliProcessosEjb oliProcessosEjb) {
		this.oliProcessosEjb = oliProcessosEjb;
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

	
}