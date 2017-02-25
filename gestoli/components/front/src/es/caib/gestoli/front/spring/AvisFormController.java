package es.caib.gestoli.front.spring;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import es.caib.gestoli.front.spring.qualitat.QualitatAPPCCFitxaControlCommand;
import es.caib.gestoli.front.util.BasicData;
import es.caib.gestoli.front.util.Idioma;
import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;
import es.caib.gestoli.logic.interfaces.OliQualitatEjb;
import es.caib.gestoli.logic.model.Avis;
import es.caib.gestoli.logic.model.Establiment;
import es.caib.gestoli.logic.model.QualitatAPPCCFitxaControlHistoric;
import es.caib.gestoli.logic.model.QualitatPlaFormacio;

public class AvisFormController extends SimpleFormController {

	private static Logger logger = Logger.getLogger(AvisFormController.class);
	
	private OliInfraestructuraEjb oliInfraestructuraEjb;
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
		AvisCommand avisCommand = (AvisCommand)command;
		
		Long avisId = avisCommand.getId();
		
		String forward = getSuccessView();
		Map myModel = new HashMap();
		
		HttpSession session = request.getSession();
		Establiment est = (Establiment)session.getAttribute(establimentSessionKey);

		Avis avis = null;
		
		try{
			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
			if (isCreate(request)){
				logger.info("Creant el registre: " + avisCommand);
				avis = new Avis();
				avis.setEstabliment(est);
			} else {
				logger.info("Actualitzant el registre: " + avisCommand);
				avis = oliInfraestructuraEjb.avisAmbId(avisId);
			}
			
			avis.setTipus(avisCommand.getTipus());
			avis.setDescripcio(avisCommand.getDescripcio());
			avis.setFrecuencia(avisCommand.getFrecuencia());
			avis.setDataSeguent(avisCommand.getDataSeguent());
			
			// Creamoe el aviso
			avis.setId(oliInfraestructuraEjb.crearAvis(avis));
			
			if (isCreate(request)){
				ControllerUtils.saveMessageInfo(request, missatge("avis.missatge.crear.ok"));
			}else{
				ControllerUtils.saveMessageInfo(request, missatge("avis.missatge.modificar.ok"));				
			}
		} catch (Exception ex) {
			if (isCreate(request)){
				logger.error("Error creant l'avis", ex);
				ControllerUtils.saveMessageError(request, missatge("avis.missatge.crear.no"));
			} else {
				logger.error("Error modificant l'avis", ex);
				ControllerUtils.saveMessageError(request, missatge("avis.missatge.modificar.no"));
			}
		}
		
		forward += "?id=" + avis.getId();
		
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
		

		myModel.put("path", "avis");
		myModel.put("idEstabliment", est.getId());
		myModel.put("formData", command);
		
		//tipus (tancamentLlibres
		ArrayList tipus = new ArrayList();
		BasicData basicData = new BasicData();
		basicData.setId("tancamentLlibres");
		basicData.setNom(bundle.getString("avis.tipus.tancamentLlibres"));
		tipus.add(basicData);
		basicData = new BasicData();
		basicData.setId("declaracioMensualTafones");
		basicData.setNom(bundle.getString("avis.tipus.declaracioMensualTafones"));
		tipus.add(basicData);
		basicData = new BasicData();
		basicData.setId("declaracioAnualProduccio");
		basicData.setNom(bundle.getString("avis.tipus.declaracioAnualProduccio"));
		tipus.add(basicData);
		basicData = new BasicData();
		basicData.setId("solicitudAutoritzacio");
		basicData.setNom(bundle.getString("avis.tipus.solicitudAutoritzacio"));
		tipus.add(basicData);
		basicData = new BasicData();
		basicData.setId("comunicacioInici");
		basicData.setNom(bundle.getString("avis.tipus.comunicacioInici"));
		tipus.add(basicData);
		basicData = new BasicData();
		basicData.setId("solicitudContraetiquetes");
		basicData.setNom(bundle.getString("avis.tipus.solicitudContraetiquetes"));
		tipus.add(basicData);
		basicData = new BasicData();
		basicData.setId("entradaOlives");
		basicData.setNom(bundle.getString("avis.tipus.entradaOlives"));
		tipus.add(basicData);
		basicData = new BasicData();
		basicData.setId("sortidesOli");
		basicData.setNom(bundle.getString("avis.tipus.sortidesOli"));
		tipus.add(basicData);
		basicData = new BasicData();
		basicData.setId("sortidesOrujo");
		basicData.setNom(bundle.getString("avis.tipus.sortidesOrujo"));
		tipus.add(basicData);
		basicData = new BasicData();
		basicData.setId("resumMensual");
		basicData.setNom(bundle.getString("avis.tipus.resumMensual"));
		tipus.add(basicData);
		basicData = new BasicData();
		basicData.setId("declaracioMensual");
		basicData.setNom(bundle.getString("avis.tipus.declaracioMensual"));
		tipus.add(basicData);
		
		myModel.put("tipus", tipus);
		
		
		//frecuencia (tri (trimestral), sem (semestral), anu (anual), ...)
		ArrayList frecuencia = new ArrayList();
		basicData = new BasicData();
		basicData.setId("1");
		basicData.setNom(bundle.getString("qualitat.frecuencia.diari"));
		frecuencia.add(basicData);
		basicData = new BasicData();
		basicData.setId("7");
		basicData.setNom(bundle.getString("qualitat.frecuencia.setmanal"));
		frecuencia.add(basicData);
		basicData = new BasicData();
		basicData.setId("15");
		basicData.setNom(bundle.getString("qualitat.frecuencia.quinzenal"));
		frecuencia.add(basicData);
		basicData = new BasicData();
		basicData.setId("30");
		basicData.setNom(bundle.getString("qualitat.frecuencia.mensual"));
		frecuencia.add(basicData);
		basicData = new BasicData();
		basicData.setId("60");
		basicData.setNom(bundle.getString("qualitat.frecuencia.bimensual"));
		frecuencia.add(basicData);
		basicData = new BasicData();
		basicData.setId("90");
		basicData.setNom(bundle.getString("qualitat.frecuencia.trimestral"));
		frecuencia.add(basicData);
		basicData = new BasicData();
		basicData.setId("180");
		basicData.setNom(bundle.getString("qualitat.frecuencia.semestral"));
		frecuencia.add(basicData);
		basicData = new BasicData();
		basicData.setId("365");
		basicData.setNom(bundle.getString("qualitat.frecuencia.anual"));
		frecuencia.add(basicData);
		basicData = new BasicData();
		basicData.setId("730");
		basicData.setNom(bundle.getString("qualitat.frecuencia.bianual"));
		frecuencia.add(basicData);
		basicData = new BasicData();
		basicData.setId("1095");
		basicData.setNom(bundle.getString("qualitat.frecuencia.trianual"));
		frecuencia.add(basicData);
		
		myModel.put("frecuencia", frecuencia);	
		
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
			AvisCommand command = new AvisCommand();
			
			if ((!isFormSubmission(request)) && (!isCreate(request))) {
				try {
					//Establiment establiment = (Establiment)request.getSession().getAttribute(establimentSessionKey);
					Long id = new Long(Long.parseLong(request.getParameter("id")));
					oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
					Avis avis = oliInfraestructuraEjb.avisAmbId(id);
					command.fromAvis(avis);

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


	public void setEstablimentSessionKey(String establimentSessionKey) {
		this.establimentSessionKey = establimentSessionKey;
	}
	
}
