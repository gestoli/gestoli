package es.caib.gestoli.front.spring.qualitat;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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

import es.caib.gestoli.front.spring.ControllerUtils;
import es.caib.gestoli.front.util.BasicData;
import es.caib.gestoli.logic.interfaces.OliQualitatEjb;
import es.caib.gestoli.logic.model.Establiment;
import es.caib.gestoli.logic.model.QualitatDescripcioEquip;
import es.caib.gestoli.logic.model.QualitatDescripcioPersonal;
import es.caib.gestoli.logic.model.QualitatPlaNeteja;
import es.caib.gestoli.logic.model.QualitatProveidor;
import es.caib.gestoli.logic.model.QualitatPuestoTreball;
import es.caib.gestoli.logic.model.QualitatSubministre;

public class QualitatSubministreFormController extends SimpleFormController {

	private static Logger logger = Logger.getLogger(QualitatSubministreFormController.class);
	
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
		QualitatSubministreCommand qualitatSubministreCommand = (QualitatSubministreCommand)command;
		
		Long subministreId = qualitatSubministreCommand.getCodi();
		
		String forward = getSuccessView();
		Map myModel = new HashMap();
		
		HttpSession session = request.getSession();
		Establiment est = (Establiment)session.getAttribute(establimentSessionKey);

		QualitatSubministre subministre = null;

		try{
			oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
			if (isCreate(request)){
				logger.info("Creant el registre: " + qualitatSubministreCommand);
				subministre = new QualitatSubministre();
				//subministre.setEstabliment(est);
			} else {
				logger.info("Actualitzant el registre: " + qualitatSubministreCommand);
				subministre = oliQualitatEjb.subministreAmbId(subministreId);
			}
			
			if (qualitatSubministreCommand.getProveidorId() != null){
				QualitatProveidor prov = oliQualitatEjb.proveidorAmbId(qualitatSubministreCommand.getProveidorId());
				subministre.setProveidor(prov);
			}

			subministre.setTipusSubministre(qualitatSubministreCommand.getTipusSubministre());
			subministre.setHomologacio(qualitatSubministreCommand.getHomologacio());
			subministre.setDeshomologacio(qualitatSubministreCommand.getDeshomologacio());
			subministre.setObservacions(qualitatSubministreCommand.getObservacions());

			subministre.setCodi(oliQualitatEjb.crearSubministre(subministre));
			//descPers.setCodi(personalId);
			if (isCreate(request)){
				ControllerUtils.saveMessageInfo(request, missatge("qualitat.subministre.missatge.crear.ok"));
			}else{
				ControllerUtils.saveMessageInfo(request, missatge("qualitat.subministre.missatge.modificar.ok"));				
			}
		} catch (Exception ex) {
			if (isCreate(request)){
				logger.error("Error creant el subministre", ex);
				ControllerUtils.saveMessageError(request, missatge("qualitat.subministre.missatge.crear.no"));
			} else {
				logger.error("Error creant el subministre", ex);
				ControllerUtils.saveMessageError(request, missatge("qualitat.subministre.missatge.modificar.no"));
			}
		}
		
		//if (!isCreate(request) && descPers != null && descPers.getCodi() != null) {
		forward += "?codi=" + subministre.getCodi() + "&idProveidor=" + subministre.getProveidor().getCodi();
		//}
		
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
		
		HttpSession session = request.getSession();
		Establiment est = (Establiment)session.getAttribute(establimentSessionKey);
		
		myModel.put("path", "qualitat_Subministres");
		myModel.put("idEstabliment", est.getId());
		myModel.put("formData", command);
		
		Long idProveidor = null;
		if (request.getParameter("idProveidor") != null){
			idProveidor = new Long(Long.parseLong(request.getParameter("idProveidor")));
		}
		myModel.put("idProveidor", idProveidor);
		
		 // PROVEIDORS
        Collection proveidorsArray = new ArrayList();
        if (est.getId() != null){
        	oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
        	Collection<QualitatProveidor> proveidors = oliQualitatEjb.proveidorsPerEstabliment(est.getId());
	        for (QualitatProveidor prov : proveidors){
	        	if (prov.getCodi() != null) {
	        		BasicData basicData = new BasicData();
					basicData = new BasicData();
					basicData.setId(prov.getCodi().toString());
					basicData.setNom(prov.getNom().toString());
					proveidorsArray.add(basicData);
				}
	        }
        }
        myModel.put("proveidors", proveidorsArray);
        
        /*
		if (est.getId() != null){
			Collection carrecs = new ArrayList();
			carrecs = oliQualitatEjb.puestoCercaTotsPerEstabliment(est.getId());
        	
            myModel.put("carrecs", carrecs);
		}
		*/
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
			QualitatSubministreCommand command = new QualitatSubministreCommand();
			
			if ((!isFormSubmission(request)) && (!isCreate(request))) {
				try {
					//Establiment establiment = (Establiment)request.getSession().getAttribute(establimentSessionKey);
					Long codi = new Long(Long.parseLong(request.getParameter("codi")));
					oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
					QualitatSubministre subm = oliQualitatEjb.subministreAmbId(codi);
					command.fromQualitatSubministre(subm);

				} catch (RemoteException ex) {
					throw new ServletException("Error cridant l'EJB", ex);
				}
			} else {
				if (request.getParameter("idProveidor") != null){
					command.setProveidorId(Long.valueOf(request.getParameter("idProveidor")));
					oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
					QualitatProveidor proveidor = oliQualitatEjb.proveidorAmbId(Long.valueOf(request.getParameter("idProveidor")));
					command.setProveidor(proveidor);
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
		return (request.getParameter("codi") == null);
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
