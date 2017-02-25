package es.caib.gestoli.front.spring.qualitat;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
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

import es.caib.gestoli.front.spring.ControllerUtils;
import es.caib.gestoli.front.util.BasicData;
import es.caib.gestoli.front.util.Idioma;
import es.caib.gestoli.logic.interfaces.OliQualitatEjb;
import es.caib.gestoli.logic.model.Establiment;
import es.caib.gestoli.logic.model.QualitatAPPCCEtapaPerill;
import es.caib.gestoli.logic.model.QualitatControlPlaguesVerificacio;
import es.caib.gestoli.logic.model.QualitatDescripcioPersonal;

public class QualitatAPPCCEtapaPerillFormController extends SimpleFormController {

	private static Logger logger = Logger.getLogger(QualitatAPPCCEtapaPerillFormController.class);
	
	private OliQualitatEjb oliQualitatEjb;
	private HibernateTemplate hibernateTemplate;
	private String establimentSessionKey;
	
	public ModelAndView onSubmit(
			HttpServletRequest request,
			HttpServletResponse response,
			Object command,
			BindException errors)
	throws ServletException {
		QualitatAPPCCEtapaPerillCommand qualitatAPPCCEtapaPerillCommand = (QualitatAPPCCEtapaPerillCommand)command;
	
		Long perillId = qualitatAPPCCEtapaPerillCommand.getId();
		
		String forward = getSuccessView();
		Map myModel = new HashMap();
		
		HttpSession session = request.getSession();
		Establiment est = (Establiment)session.getAttribute(establimentSessionKey);

		QualitatAPPCCEtapaPerill perill = null;
	
		try{
			oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
			if (isCreate(request)){
				logger.info("Creant el registre: " + qualitatAPPCCEtapaPerillCommand);
				perill = new QualitatAPPCCEtapaPerill();
				if (qualitatAPPCCEtapaPerillCommand.getIdEtapa() != null){
					perill.setEtapa(oliQualitatEjb.aPPCCEtapaAmbId(Long.valueOf(qualitatAPPCCEtapaPerillCommand.getIdEtapa())));
				}
			} else {
				logger.info("Actualitzant el registre: " + qualitatAPPCCEtapaPerillCommand);
				perill = oliQualitatEjb.aPPCCEtapaPerillAmbId(perillId);
			}
			
			perill.setTipus(qualitatAPPCCEtapaPerillCommand.getTipus());
			perill.setDetall(qualitatAPPCCEtapaPerillCommand.getDetall());
			perill.setCausa(qualitatAPPCCEtapaPerillCommand.getCausa());
			perill.setPrevencio(qualitatAPPCCEtapaPerillCommand.getPrevencio());
			perill.setProbabilitat(qualitatAPPCCEtapaPerillCommand.getProbabilitat());
			perill.setGravetat(qualitatAPPCCEtapaPerillCommand.getGravetat());
			
			perill.setId(oliQualitatEjb.crearAPPCCEtapaPerill(perill));
			
			if (isCreate(request)){
				ControllerUtils.saveMessageInfo(request, missatge("qualitat.appcc.etapa.perill.missatge.crear.ok"));
			}else{
				ControllerUtils.saveMessageInfo(request, missatge("qualitat.appcc.etapa.perill.missatge.modificar.ok"));				
			}
			
		} catch (Exception ex) {
			if (isCreate(request)){
				logger.error("Error creant el perill de l'etapa de l'APPCC", ex);
				ControllerUtils.saveMessageError(request, missatge("qualitat.appcc.etapa.perill.missatge.crear.no"));
			} else {
				logger.error("Error modificant el perill de l'etapa de l'APPCC", ex);
				ControllerUtils.saveMessageError(request, missatge("qualitat.appcc.etapa.perill.missatge.modificar.no"));
			}
		}

		forward += "?id=" + perill.getId() + "&idEtapa=" + perill.getEtapa().getId();
		
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
		

		myModel.put("path", "qualitat_APPCC.Etapa.Perill");
		myModel.put("formData", command);
		
		Long idEtapa = null;
		if (request.getParameter("idEtapa") != null){
			idEtapa = new Long(Long.parseLong(request.getParameter("idEtapa")));
		}
		myModel.put("idEtapa", idEtapa);
		
		//Tipus (b ->biológics, f->físics, q->químics)
  		ArrayList tipus = new ArrayList();
  		BasicData basicData = new BasicData();
  		basicData.setId("b");
  		basicData.setNom(bundle.getString("qualitat.appcc.etapa.perill.tipus.b"));
  		tipus.add(basicData);
  		basicData = new BasicData();
  		basicData.setId("f");
  		basicData.setNom(bundle.getString("qualitat.appcc.etapa.perill.tipus.f"));
  		tipus.add(basicData);
  		basicData = new BasicData();
  		basicData.setId("q");
  		basicData.setNom(bundle.getString("qualitat.appcc.etapa.perill.tipus.q"));
  		tipus.add(basicData);
  		myModel.put("tipus", tipus);	
  		
  		//Medidas Preventivas (ca ->control agua, pr->proveedores, ma->mantenimiento, fo->formación, nd->limpieza y desinfección, tr->trazabilidad, cr->control recepción)
  		ArrayList prevencions = new ArrayList();
  		basicData = new BasicData();
  		basicData.setId("ca");
  		basicData.setNom(bundle.getString("qualitat.appcc.etapa.perill.prevencio.ca"));
  		prevencions.add(basicData);
  		basicData = new BasicData();
  		basicData.setId("pr");
  		basicData.setNom(bundle.getString("qualitat.appcc.etapa.perill.prevencio.pr"));
  		prevencions.add(basicData);
  		basicData = new BasicData();
  		basicData.setId("ma");
  		basicData.setNom(bundle.getString("qualitat.appcc.etapa.perill.prevencio.ma"));
  		prevencions.add(basicData);
  		basicData = new BasicData();
  		basicData.setId("fo");
  		basicData.setNom(bundle.getString("qualitat.appcc.etapa.perill.prevencio.fo"));
  		prevencions.add(basicData);
  		basicData = new BasicData();
  		basicData.setId("nd");
  		basicData.setNom(bundle.getString("qualitat.appcc.etapa.perill.prevencio.pl"));
  		prevencions.add(basicData);
  		basicData = new BasicData();
  		basicData.setId("pl");
  		basicData.setNom(bundle.getString("qualitat.appcc.etapa.perill.prevencio.nd"));
  		prevencions.add(basicData);
  		basicData = new BasicData();
  		basicData.setId("tr");
  		basicData.setNom(bundle.getString("qualitat.appcc.etapa.perill.prevencio.tr"));
  		prevencions.add(basicData);
  		basicData = new BasicData();
  		basicData.setId("cr");
  		basicData.setNom(bundle.getString("qualitat.appcc.etapa.perill.prevencio.cr"));
  		prevencions.add(basicData);
  		myModel.put("prevencions", prevencions);	
  		
        
  		//Probabilitat (1 ->molt baixa, 2->baixa, 3->alta, 4->molt alta)
  		ArrayList probabilitats = new ArrayList();
  		basicData = new BasicData();
  		basicData.setId("1");
  		basicData.setNom(bundle.getString("qualitat.appcc.etapa.perill.probabilitat.1"));
  		probabilitats.add(basicData);
  		basicData = new BasicData();
  		basicData.setId("2");
  		basicData.setNom(bundle.getString("qualitat.appcc.etapa.perill.probabilitat.2"));
  		probabilitats.add(basicData);
  		basicData = new BasicData();
  		basicData.setId("3");
  		basicData.setNom(bundle.getString("qualitat.appcc.etapa.perill.probabilitat.3"));
  		probabilitats.add(basicData);
  		basicData = new BasicData();
  		basicData.setId("4");
  		basicData.setNom(bundle.getString("qualitat.appcc.etapa.perill.probabilitat.4"));
  		probabilitats.add(basicData);
  		myModel.put("probabilitats", probabilitats);	
        
  		//Gravetat (1 ->trivial, 2->leve, 3->grave)
  		ArrayList gravetats = new ArrayList();
  		basicData = new BasicData();
  		basicData.setId("1");
  		basicData.setNom(bundle.getString("qualitat.appcc.etapa.perill.gravetat.1"));
  		gravetats.add(basicData);
  		basicData = new BasicData();
  		basicData.setId("2");
  		basicData.setNom(bundle.getString("qualitat.appcc.etapa.perill.gravetat.2"));
  		gravetats.add(basicData);
  		basicData = new BasicData();
  		basicData.setId("3");
  		basicData.setNom(bundle.getString("qualitat.appcc.etapa.perill.gravetat.3"));
  		gravetats.add(basicData);
  		myModel.put("gravetats", gravetats);	
        
        
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
			QualitatAPPCCEtapaPerillCommand command = new QualitatAPPCCEtapaPerillCommand();
			
			if ((!isFormSubmission(request)) && (!isCreate(request))) {
				try {
					//Establiment establiment = (Establiment)request.getSession().getAttribute(establimentSessionKey);
					Long id = new Long(Long.parseLong(request.getParameter("id")));
					oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
					QualitatAPPCCEtapaPerill perill = oliQualitatEjb.aPPCCEtapaPerillAmbId(id);
					command.fromQualitatAPPCCEtapaPerill(perill);

				} catch (RemoteException ex) {
					throw new ServletException("Error cridant l'EJB", ex);
				}
			} else {
				if (request.getParameter("idEtapa") != null){
					oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
					command.setIdEtapa(Long.valueOf(request.getParameter("idEtapa")));
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


	public void setOliQualitatEjb(OliQualitatEjb oliQualitatEjb) {
		this.oliQualitatEjb = oliQualitatEjb;
	}


	public void setEstablimentSessionKey(String establimentSessionKey) {
		this.establimentSessionKey = establimentSessionKey;
	}

}
