package es.caib.gestoli.front.spring.qualitat;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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

import es.caib.gestoli.front.spring.ControllerUtils;
import es.caib.gestoli.front.util.BasicData;
import es.caib.gestoli.front.util.Idioma;
import es.caib.gestoli.logic.interfaces.OliQualitatEjb;
import es.caib.gestoli.logic.model.Establiment;
import es.caib.gestoli.logic.model.QualitatAPPCCControl;
import es.caib.gestoli.logic.model.QualitatAPPCCEtapa;
import es.caib.gestoli.logic.model.QualitatAPPCCFitxaControl;
import es.caib.gestoli.logic.model.QualitatAPPCCFitxaControlHistoric;
import es.caib.gestoli.logic.model.QualitatDescripcioPersonal;

public class QualitatAPPCCFitxaControlFormController extends SimpleFormController{

	private static Logger logger = Logger.getLogger(QualitatAPPCCEtapaFormController.class);
		
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
		
		QualitatAPPCCFitxaControlCommand qualitatAppccFitxaControlCommand = (QualitatAPPCCFitxaControlCommand)command;
		Long fitxaHistoricId = qualitatAppccFitxaControlCommand.getId();
				
		String forward = getSuccessView();
		Map myModel = new HashMap();
		
		HttpSession session = request.getSession();
		//Establiment est = (Establiment)session.getAttribute(establimentSessionKey);

		QualitatAPPCCFitxaControlHistoric historic = null;
		
		try{
			// Siempre creamos un nuevo registro de ficha de control para guardar el historial.
			oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
			logger.info("Creant el registre: " + qualitatAppccFitxaControlCommand);
			historic = new QualitatAPPCCFitxaControlHistoric();
			//historic.setEstabliment(est);
			historic.setMesuresPrevencio(qualitatAppccFitxaControlCommand.getMesuresPrevencio());
			if (qualitatAppccFitxaControlCommand.getIdResponsablePrevencio() != null){
				historic.setResponsablePrevencio(oliQualitatEjb.personalAmbId(qualitatAppccFitxaControlCommand.getIdResponsablePrevencio()));
			}
			historic.setVigilancia(qualitatAppccFitxaControlCommand.getVigilancia());
			if (qualitatAppccFitxaControlCommand.getIdResponsableVigilancia() != null){
				historic.setResponsableVigilancia(oliQualitatEjb.personalAmbId(qualitatAppccFitxaControlCommand.getIdResponsableVigilancia()));
		    }
			historic.setRegistre(qualitatAppccFitxaControlCommand.getRegistre());
		    historic.setLimits(qualitatAppccFitxaControlCommand.getLimits());
		    historic.setFreqVigilancia(qualitatAppccFitxaControlCommand.getFreqVigilancia());
		    historic.setMesuresCorreccio(qualitatAppccFitxaControlCommand.getMesuresCorreccio());
		    if (qualitatAppccFitxaControlCommand.getIdResponsableCorreccio() != null){
				historic.setResponsableCorreccio(oliQualitatEjb.personalAmbId(qualitatAppccFitxaControlCommand.getIdResponsableCorreccio()));
		    }
		    if (qualitatAppccFitxaControlCommand.getIdFitxa() != null){
		    	historic.setFitxa(oliQualitatEjb.aPPCCFitxaControlAmbId(qualitatAppccFitxaControlCommand.getIdFitxa()));
		    } else {
		    	QualitatAPPCCFitxaControl fitxa = new QualitatAPPCCFitxaControl();
		    	if (qualitatAppccFitxaControlCommand.getIdControl() != null){
		    		fitxa.setControl(oliQualitatEjb.aPPCCControlAmbId(qualitatAppccFitxaControlCommand.getIdControl()));
		    	}
		    	fitxa.setId(oliQualitatEjb.crearAPPCFitxaCControl(fitxa));
		    	// Igual hay que hacer un getFitxaControlAmbId!!!
		    	historic.setFitxa(fitxa);
		    }
		    historic.setDataModificacio(new Date());
		    	
		    historic.setId(oliQualitatEjb.crearAPPCCFitxaControlHistoric(historic));
				
			ControllerUtils.saveMessageInfo(request, missatge("qualitat.appcc.fitxaControl.missatge.crear.ok"));
			
		} catch (Exception ex) {
			logger.error("Error creant la fitxa de Control PCC", ex);
			ControllerUtils.saveMessageError(request, missatge("qualitat.appcc.fitxaControl.missatge.crear.no"));
		}
		
		forward += "?id=" + historic.getFitxa().getControl().getId();
		
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

		myModel.put("path", "qualitat_APPCC.FitxaControl");
		myModel.put("formData", command);
		
		BasicData basicData;
		
	    Collection personal = new ArrayList();
		if (est.getId() != null){
			oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
			Collection itResponsablesInterns = oliQualitatEjb.personalCercaTotsPerEstabliment(est.getId());
			for (Iterator it = itResponsablesInterns.iterator(); it.hasNext();){
				QualitatDescripcioPersonal responsableIntern = (QualitatDescripcioPersonal)it.next();
				if (responsableIntern.getCodi() != null) {
					basicData = new BasicData();
					basicData.setId(responsableIntern.getCodi().toString());
					basicData.setNom(responsableIntern.getNom().toString() + " " + responsableIntern.getLlinatges().toString());
					personal.add(basicData);
				}
			}
		}
		myModel.put("personal", personal);
			
		//frecuencia (tri (trimestral), sem (semestral), anu (anual),...)
		ArrayList frecuencia = new ArrayList();
		basicData = new BasicData();
		basicData.setId("diari");
		basicData.setNom(bundle.getString("qualitat.frecuencia.diari"));
		frecuencia.add(basicData);
		basicData = new BasicData();
		basicData.setId("setmanal");
		basicData.setNom(bundle.getString("qualitat.frecuencia.setmanal"));
		frecuencia.add(basicData);
		basicData = new BasicData();
		basicData.setId("quinzenal");
		basicData.setNom(bundle.getString("qualitat.frecuencia.quinzenal"));
		frecuencia.add(basicData);
		basicData = new BasicData();
		basicData.setId("mensual");
		basicData.setNom(bundle.getString("qualitat.frecuencia.mensual"));
		frecuencia.add(basicData);
		basicData = new BasicData();
		basicData.setId("bimensual");
		basicData.setNom(bundle.getString("qualitat.frecuencia.bimensual"));
		frecuencia.add(basicData);
		basicData = new BasicData();
		basicData.setId("trimestral");
		basicData.setNom(bundle.getString("qualitat.frecuencia.trimestral"));
		frecuencia.add(basicData);
		basicData = new BasicData();
		basicData.setId("semestral");
		basicData.setNom(bundle.getString("qualitat.frecuencia.semestral"));
		frecuencia.add(basicData);
		basicData = new BasicData();
		basicData.setId("anual");
		basicData.setNom(bundle.getString("qualitat.frecuencia.anual"));
		frecuencia.add(basicData);
		basicData = new BasicData();
		basicData.setId("bianual");
		basicData.setNom(bundle.getString("qualitat.frecuencia.bianual"));
		frecuencia.add(basicData);
		basicData = new BasicData();
		basicData.setId("trianual");
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
			QualitatAPPCCFitxaControlCommand command = new QualitatAPPCCFitxaControlCommand();
			
			QualitatAPPCCFitxaControlHistoric historic = null;
			Long idControl = null;
			if (request.getParameter("id")!=null){
				idControl = new Long(Long.parseLong(request.getParameter("id")));
				oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
				historic = oliQualitatEjb.aPPCCFitxaControlPerControl(idControl);
		
				if ( !isFormSubmission(request) && (historic != null) ) {
					command.fromQualitatAPPCCFitxaControlHistoric(historic);
				} else {
					command.setIdControl(idControl);
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
