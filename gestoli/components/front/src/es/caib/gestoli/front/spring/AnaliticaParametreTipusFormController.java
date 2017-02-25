package es.caib.gestoli.front.spring;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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

import es.caib.gestoli.front.util.BasicData;
import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;
import es.caib.gestoli.logic.model.AnaliticaParametreTipus;
import es.caib.gestoli.logic.model.Establiment;
import es.caib.gestoli.logic.util.Constants;

/**
 
 * 
 */
public class AnaliticaParametreTipusFormController extends SimpleFormController {
	private static Logger logger = Logger.getLogger(AnaliticaParametreTipusFormController.class);
	
	private OliInfraestructuraEjb oliInfraestructuraEjb;
	
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

		AnaliticaParametreTipusCommand analiticaCommand = (AnaliticaParametreTipusCommand) command;
		
		AnaliticaParametreTipus analitica = new AnaliticaParametreTipus(
													analiticaCommand.getNom(),
													analiticaCommand.getOrdre(),
													analiticaCommand.getTipus(),
													analiticaCommand.getTipusControl(),
													analiticaCommand.getActiu(),
													analiticaCommand.getNomCast());
		
		//Si no es actiu ho posam a false
		if(analitica.getActiu() == null){
			analitica.setActiu(false);
		}
		
		if(analiticaCommand.getId() != null){
			analitica.setId(analiticaCommand.getId());
		}
		
		Map myModel = new HashMap();

		
		try {
			if (isCreate(request)) {
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				oliInfraestructuraEjb.analiticaParametreTipusCrear(analitica);
				ControllerUtils.saveMessageInfo(request, missatge("analiticaParametreTipus.missatge.crear.ok"));
			} else {
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				oliInfraestructuraEjb.analiticaParametreTipusModificar(analitica);
				ControllerUtils.saveMessageInfo(request, missatge("analiticaParametreTipus.missatge.modificar.ok"));
			}
			
		} catch (Exception ex) {
			logger.error("Error creant el analitica", ex);
			ControllerUtils.saveMessageError(request,
					missatge("analiticaParametreTipus.missatge.crear.no"));
		}
		

		return new ModelAndView(getSuccessView(), myModel);
	}

	/**
	 * Retorna todos los datos del modelo necesarios para mostrar el formulario
	 * de inserción (LOVs y cosas de esas) si no hay.
	 * 
	 * @see SimpleFormController#referenceData(javax.servlet.http.HttpServletRequest,
	 *      java.lang.Object, org.springframework.validation.Errors)
	 */
	protected Map referenceData(HttpServletRequest request, Object command,
			Errors errors) throws Exception {
		Map myModel = new HashMap();

		HttpSession session = request.getSession();
		
		AnaliticaParametreTipusCommand analitica = (AnaliticaParametreTipusCommand) command;

		List<BasicData> tipus = new ArrayList<BasicData>(); 
		tipus.add(new BasicData(String.valueOf(Integer.valueOf(Constants.PARAMETRE_TIPUS_STRING)), missatge("analiticaParametreTipus.tipus"+Constants.PARAMETRE_TIPUS_STRING)));
		tipus.add(new BasicData(String.valueOf(Integer.valueOf(Constants.PARAMETRE_TIPUS_SENCER)), missatge("analiticaParametreTipus.tipus"+Constants.PARAMETRE_TIPUS_SENCER)));
		tipus.add(new BasicData(String.valueOf(Integer.valueOf(Constants.PARAMETRE_TIPUS_DECIMAL)), missatge("analiticaParametreTipus.tipus"+Constants.PARAMETRE_TIPUS_DECIMAL)));
		//tipus.add(new BasicData(String.valueOf(Integer.valueOf(Constants.PARAMETRE_TIPUS_BOOLEAN)), missatge("analiticaParametreTipus.tipus"+Constants.PARAMETRE_TIPUS_BOOLEAN)));
		
		myModel.put("tipus", tipus);
		
		List<BasicData> tipusControl = new ArrayList<BasicData>(); 
		tipusControl.add(new BasicData(String.valueOf(Integer.valueOf(Constants.PARAMETRE_TIPUS_ANALISI_SENSORIAL)), missatge("analiticaParametreTipus.tipusControl"+Constants.PARAMETRE_TIPUS_ANALISI_SENSORIAL)));
		tipusControl.add(new BasicData(String.valueOf(Integer.valueOf(Constants.PARAMETRE_TIPUS_ANALISI_FISICOQUIMIC)), missatge("analiticaParametreTipus.tipusControl"+Constants.PARAMETRE_TIPUS_ANALISI_FISICOQUIMIC)));
		
		myModel.put("tipusControl", tipusControl);
		

		if ((!isFormSubmission(request)) && (!isCreate(request))) {
			myModel.put("path", "modificar_analiticaParametreTipus");
		} else {
			myModel.put("path", "introducir_analiticaParametreTipus");
			//myModel.put("path_extension1",establiment.getNom()+" - "+ diposit.getCodiAssignat());
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
		
		AnaliticaParametreTipusCommand analitica = null;
		if ((!isFormSubmission(request)) && (!isCreate(request))) {
			try {
				Long codi = new Long(Long.parseLong(request.getParameter("id")));
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				AnaliticaParametreTipus a = oliInfraestructuraEjb.analiticaParametreTipusAmbId(codi);
				analitica = new AnaliticaParametreTipusCommand();
				analitica.fromAnaliticaParametreTipus(a);				


			} catch (RemoteException ex) {
				throw new ServletException("Error cridant l'EJB", ex);
			}
		} else {
			analitica = new AnaliticaParametreTipusCommand();
		}
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
		return (request.getParameter("id") == null || 
			   (request.getParameter("id") != null && request.getParameter("id").equals("")));
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