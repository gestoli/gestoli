package es.caib.gestoli.front.spring;

import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
import org.springframework.web.servlet.mvc.BaseCommandController;
import org.springframework.web.servlet.mvc.SimpleFormController;

import es.caib.gestoli.logic.interfaces.OliProcessosEjb;



/**
 * <p>Controlador para modificar la fecha de un proceso.</p>
 * <p>Los parámetros de la petición HTTP relevantes para este
 * controlador son:
 * <ul>
 *   <li><code>id</code>
 *    - Identificador del registro. Este parámetro es el que
 *      determina si se ha de mostrar la página de creación o la
 *      página de edición.</li>
 * </ul></p>
 * <p>No hay información para la vista:
 * <p>Este controlador distingue entre las peticiones del tipo
 * GET y las de tipo POST. Si la petición es de tipo POST
 * se ejecuta la acción de creación o de edición. Si la petición es de
 * tipo GET solo se muestra la página.
 *
 *
 */
public class EdicioProcessosFormController extends SimpleFormController {
	private static Logger logger = Logger.getLogger(EdicioProcessosFormController.class);
	private OliProcessosEjb oliProcessosEjb;
	private HibernateTemplate hibernateTemplate;
	private String desqualificat;

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
		
		HttpSession session = request.getSession();
		
		EdicioProcessosCommand ep = (EdicioProcessosCommand)command;

		Long trazaId = ep.getTrazaId();
		int tipus = ep.getTipus();
		Date novaData = ep.getDataNova();
				

		Map myModel = new HashMap();	
		
		try {
			logger.info("Modificant la data del procés amb traça: " + ep.getTrazaId());	
			oliProcessosEjb.setHibernateTemplate(getHibernateTemplate());
			oliProcessosEjb.editarProces(trazaId, tipus, novaData, desqualificat);
			
			String di = request.getParameter("dataInici");
        	String df = request.getParameter("dataFi");
        	DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        	        	
        	Date dataInici = null;
        	Date dataFi = null;
        	
        	if (di != null && !di.equals("")){
        		dataInici = sdf.parse(di);
        	}
        	if (df != null && !df.equals("")){
        		dataFi = sdf.parse(df);
        	}
        	
        	myModel.put("path", "edicio_processos");
        	if (dataInici != null) myModel.put("dataInici", di);
        	if (dataFi != null) myModel.put("dataFi", df);
        	
			ControllerUtils.saveMessageInfo(request, missatge("edicioProcessos.missatge.editar.ok"));
		} catch (Exception ex) {
			logger.error("Error modificant la data del procés", ex);
			ControllerUtils.saveMessageError(request, missatge("edicioProcessos.missatge.editar.no"));
		}
		
		return new ModelAndView(getSuccessView(), myModel);
		
	}

	/**
	 * Retorna todos los datos del modelo necesarios para mostrar
	 * el formulario de inserción (LOVs y cosas de esas) si no hay.
	 * @see SimpleFormController#referenceData(javax.servlet.http.HttpServletRequest, java.lang.Object, org.springframework.validation.Errors)
	 */
	@SuppressWarnings("unchecked")
	protected Map referenceData(
			HttpServletRequest request,
			Object command,
			Errors errors) throws Exception {
		Map myModel = new HashMap();

		EdicioProcessosCommand ep = (EdicioProcessosCommand)command;

		try{
        	oliProcessosEjb.setHibernateTemplate(getHibernateTemplate());
        	Date[] dates = oliProcessosEjb.datesProces(ep.getTrazaId(), ep.getTipus(), desqualificat);
        	
        	myModel.put("path", "editar_proces");
        	ep.setDataMax(dates[1]);
        	ep.setDataMin(dates[0]);
        	ep.setDataExecucio(dates[2]);
        	
        	String di = request.getParameter("dataInici");
        	String df = request.getParameter("dataFi");
        	DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        	        	
        	Date dataInici = null;
        	Date dataFi = null;
        	
        	if (di != null && !di.equals("")){
        		dataInici = sdf.parse(di);
        	}
        	if (df != null && !df.equals("")){
        		dataFi = sdf.parse(df);
        	}
        	if (dataInici != null) myModel.put("dataInici", di);
        	if (dataFi != null) myModel.put("dataFi", df);
    	} catch (Exception ex) {
    		logger.error("Error editant el proces", ex);
    		ControllerUtils.saveMessageError(request, missatge("edicioProcessos.missatge.editar.no"));
    	}
		
		return myModel;
	}


	/**
	 * En caso de que sea una edición retorna el objeto rellenado con
	 * los datos actuales del registro. En caso de que sea una creación
	 * retorna el objeto vacío.
	 * @see AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
	 */
	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		EdicioProcessosCommand ep = new EdicioProcessosCommand();
		if (!isFormSubmission(request)) {
			try {
				Long trazaId = new Long(request.getParameter("id"));
				int tipus = new Integer(request.getParameter("tipus")).intValue();
				Date dataMax = null;
				Date dataMin = null;
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				if (request.getParameter("dataMax") != null && !request.getParameter("dataMax").equals("")){
					dataMax = sdf.parse(request.getParameter("dataMax"));
				}
				if (request.getParameter("dataMin") != null && !request.getParameter("dataMin").equals("")){
					dataMin = sdf.parse(request.getParameter("dataMin"));
				}
				ep.setTrazaId(trazaId);
				ep.setDataMax(dataMax);
				ep.setDataMin(dataMin);
				ep.setTipus(tipus);

			} catch (Exception ex) {
				throw new ServletException("Error cridant l'EJB", ex);
			}
		}
		return ep;
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
				Long.class,
				new CustomNumberEditor(Long.class, true));
		binder.registerCustomEditor(
				Boolean.class,
				new CustomBooleanEditor("S", "N", true));
		binder.registerCustomEditor(
				Date.class,
				new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), true));
	}

	public void setOliProcessosEjb(OliProcessosEjb oliProcessosEjb) {
		this.oliProcessosEjb = oliProcessosEjb;
	}

	private String missatge(String clave){
		String valor = getMessageSourceAccessor().getMessage(clave);
		return valor;
	}
	
	public void setDesqualificat(String desqualificat) {
		this.desqualificat = desqualificat;
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
