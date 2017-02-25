package es.caib.gestoli.front.spring.qualitat;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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
import es.caib.gestoli.logic.model.QualitatProveidor;
import es.caib.gestoli.logic.model.QualitatSubministre;

public class QualitatProveidorFormController extends SimpleFormController {

	private static Logger logger = Logger.getLogger(QualitatProveidorFormController.class);
	
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
		QualitatProveidorCommand qualitatProveidorCommand = (QualitatProveidorCommand)command;
		
		Long proveidorId = qualitatProveidorCommand.getCodi();
		
		String forward = getSuccessView();
		Map myModel = new HashMap();
		
		HttpSession session = request.getSession();
		Establiment est = (Establiment)session.getAttribute(establimentSessionKey);

		QualitatProveidor proveidor = null;
		
		String action = request.getParameter("action");

		try{
			
			/*if (action != null && action.equals("delete")){
				String sIdProveidor = request.getParameter("idProveidor");
				String sId = request.getParameter("idSubministre");
				Long id = Long.valueOf(sId);
				Long idProveidor = Long.valueOf(sIdProveidor);
				
				oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
				oliQualitatEjb.subministreEsborrar(id);
				ControllerUtils.saveMessageInfo(request, missatge("qualitat.proveidor.missatge.esborrar.ok"));
				forward += "?idProveidor=" + idProveidor;
			} else {*/
				
				oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
				if (isCreate(request)){
					logger.info("Creant el registre: " + qualitatProveidorCommand);
					proveidor = new QualitatProveidor();
				} else {
					logger.info("Actualitzant el registre: " + qualitatProveidorCommand);
					proveidor = oliQualitatEjb.proveidorAmbId(proveidorId);
				}
				
				
				if (isCreate(request)){
					proveidor.setEstabliment(est);
				}
				proveidor.setNom(qualitatProveidorCommand.getNom());
				proveidor.setDireccio(qualitatProveidorCommand.getDireccio());
				proveidor.setTelefon(qualitatProveidorCommand.getTelefon());
				proveidor.setPersonaContacte(qualitatProveidorCommand.getPersonaContacte());
				proveidor.setObservacions(qualitatProveidorCommand.getObservacions());
	
				proveidor.setCodi(oliQualitatEjb.proveidorCrear(proveidor));
				//descPers.setCodi(personalId);
				if (isCreate(request)){
					ControllerUtils.saveMessageInfo(request, missatge("qualitat.proveidor.missatge.crear.ok"));
				}else{
					ControllerUtils.saveMessageInfo(request, missatge("qualitat.proveidor.missatge.modificar.ok"));				
				}
				forward += "?codi=" + proveidor.getCodi();
			//}
		} catch (Exception ex) {
			if (action.equals("delete")){
				logger.error("Error eliminant la descripció del subministre", ex);
				ControllerUtils.saveMessageError(request, missatge("qualitat.proveidor.missatge.esborrar.no"));
			} else if (isCreate(request)){
				logger.error("Error creant la descripció del proveidor", ex);
				ControllerUtils.saveMessageError(request, missatge("qualitat.proveidor.missatge.crear.no"));
			} else {
				logger.error("Error modificant la descripció del proveidor", ex);
				ControllerUtils.saveMessageError(request, missatge("qualitat.proveidor.missatge.modificar.no"));
			}
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
		
		QualitatProveidorCommand provCommand = (QualitatProveidorCommand) command;
		
		Map myModel = new HashMap();
		
		HttpSession session = request.getSession();
		Establiment est = (Establiment)session.getAttribute(establimentSessionKey);
		
		oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
		List llistatSubministres = new ArrayList();
		
		if (provCommand.getCodi()!=null) {
			QualitatProveidor prov = oliQualitatEjb.proveidorAmbId(provCommand.getCodi());
		
			
    		oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
    		Iterator itSubministres = oliQualitatEjb.subministrePerProveidor(prov.getCodi()).iterator();
    		while (itSubministres.hasNext()) {
    			QualitatSubministreCommand subministre = new QualitatSubministreCommand();
    			subministre.fromQualitatSubministre((QualitatSubministre) itSubministres.next());
    			oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
    			llistatSubministres.add(subministre);
    		}
		}		
    	myModel.put("subministres", llistatSubministres);
    	logger.info("Obtenint llistat de subministres: " + llistatSubministres.size() + " registres trobats");
		
    	oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
    	List llistatRGSAs = new ArrayList();
		
    	
    	
    	myModel.put("path", "qualitat_Proveidors");
		myModel.put("idEstabliment", est.getId());
		myModel.put("formData", command);
		
		
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
			QualitatProveidorCommand command = new QualitatProveidorCommand();
			
			if ((!isFormSubmission(request)) && (!isCreate(request))) {
				try {
//					Establiment establiment = (Establiment)request.getSession().getAttribute(establimentSessionKey);
					Long codi = new Long(Long.parseLong(request.getParameter("codi")));
					oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
					QualitatProveidor prov = oliQualitatEjb.proveidorAmbId(codi);
					command.fromQualitatProveidor(prov);

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
