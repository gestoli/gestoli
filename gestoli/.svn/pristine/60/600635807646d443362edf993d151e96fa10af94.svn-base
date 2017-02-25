package es.caib.gestoli.front.spring;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
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
import es.caib.gestoli.logic.model.Diposit;
import es.caib.gestoli.logic.model.Establiment;
import es.caib.gestoli.logic.model.Lot;

/**
 * <p>Controlador para las acciones de dar de alta o editar un
 * registro de tipos de información.</p>
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
 * @author Carlos Pérez (cperez@at4.net)
 */
public class TancamentLlibresLlistatFormController extends SimpleFormController {
	private static Logger logger = Logger.getLogger(TancamentLlibresLlistatFormController.class);

	private String establimentSessionKey;
	private String formView;
	private OliInfraestructuraEjb oliInfraestructuraEjb;

	private HibernateTemplate hibernateTemplate;

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
		TancamentLlibresCommand tancament = (TancamentLlibresCommand)command;
		Map myModel = new HashMap();
		try{
			
			boolean bTotsDiposits = false;
			boolean bTotsLots = false;
			Collection diposits = new ArrayList();
			Collection lots = new ArrayList();
			
			if (request.getParameter("totsDiposits")!=null){
				if(request.getParameter("totsDiposits").equals("S")){
					bTotsDiposits=true;
				}
			}
			if (request.getParameter("totsLots")!=null){
				if(request.getParameter("totsLots").equals("S")){
					bTotsLots=true;
				}
			}
			
			if (bTotsDiposits == false){
				String nomParam;
				Enumeration params = request.getParameterNames();
				while (params.hasMoreElements()){
					nomParam=(String)params.nextElement();
					String splitParam[]=nomParam.split("_");
					if (splitParam.length==2){
						if (splitParam[0].equals("dipositsSeleccionats")){
							diposits.add(splitParam[1]);
						}
					}
				}
			}
			
			logger.info("Obtenint llistat de diposits: " + diposits.size() + " registres trobats");
			
			if (bTotsLots == false){
				String nomParam;
				Enumeration params = request.getParameterNames();
				while (params.hasMoreElements()){
					nomParam=(String)params.nextElement();
					String splitParam[]=nomParam.split("_");
					if (splitParam.length==2){
						if (splitParam[0].equals("lotsSeleccionats")){
							lots.add(splitParam[1]);
						}
					}
				}
			}
			logger.info("Obtenint llistat de lots: " + lots.size() + " registres trobats");
				
			if (diposits.isEmpty() && lots.isEmpty() && !bTotsDiposits && !bTotsLots){
//				setSuccessView("forward:TancamentLlibres.html");
				logger.info("Tancament: No s'havien seleccionat dipòsits ni lots");    					
			} else {
	        	myModel.put("totsDiposits", bTotsDiposits);
	        	myModel.put("totsLots", bTotsLots);
	        	myModel.put("diposits", diposits);
	        	myModel.put("lots", lots);
	        	setSuccessView("redirect:TancamentLlibresForm.html");
			}
        } catch (Exception ex) {
            logger.error("Error obtenint llistats de tancament de llibres", ex);
            ControllerUtils.saveMessageError(request, missatge("establiment.missatge.llistat.no"));
        }

        return new ModelAndView(getSuccessView(), myModel);
	}

	/**
	 * Retorna todos los datos del modelo necesarios para mostrar
	 * el formulario de inserción (LOVs y cosas de esas) si no hay.
	 * @see SimpleFormController#referenceData(javax.servlet.http.HttpServletRequest, java.lang.Object, org.springframework.validation.Errors)
	 */
	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.SimpleFormController#referenceData(javax.servlet.http.HttpServletRequest, java.lang.Object, org.springframework.validation.Errors)
	 */
	protected Map referenceData(
			HttpServletRequest request,
			Object command,
			Errors errors) throws Exception {
		Map myModel = new HashMap();
		try {
			Establiment establiment = (Establiment) request.getSession().getAttribute(establimentSessionKey);
        	
        	Collection diposits = new ArrayList();
        	Collection lots = new ArrayList();
        	
        	oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
        	diposits = oliInfraestructuraEjb.dipositCercaTotsNoFicticisPerEstabliment(establiment.getId());
        	
        	oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
//        	lots = oliInfraestructuraEjb.lotCercaTotsPerEstabliment(establiment.getId());
        	lots = oliInfraestructuraEjb.lotCercaTotsBuits(establiment.getId());
        	
        	myModel.put("llistatDepositos", diposits);
        	myModel.put("llistatLotes", lots);
        	myModel.put("path", "tancament_llibres");
        	logger.info("Obtenint llistat de diposits: " + diposits.size() + " registres trobats");
        	logger.info("Obtenint llistat de lots: " + lots.size() + " registres trobats");
        } catch (Exception ex) {
            logger.error("Error obtenint llistats de tancament de llibres", ex);
            ControllerUtils.saveMessageError(request, missatge("establiment.missatge.llistat.no"));
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
		TancamentLlibresCommand tancament = null;
		tancament = new TancamentLlibresCommand();
		return tancament;
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
		NumberFormat nf = NumberFormat.getNumberInstance();
		binder.registerCustomEditor(Integer.class, null, new CustomNumberEditor(Integer.class, nf, true));
	}

	/**
	 * Inyección de la dependencia informacioEjb
	 * @param informacioEjb La clase a inyectar.
	 */
	public void setOliInfraestructuraEjb(OliInfraestructuraEjb oliInfraestructuraEjb) {
		this.oliInfraestructuraEjb = oliInfraestructuraEjb;
	}

	/**
	 * @param establimentSessionKey the establimentSessionKey to set
	 */
	public void setEstablimentSessionKey(String establimentSessionKey) {
		this.establimentSessionKey = establimentSessionKey;
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
}