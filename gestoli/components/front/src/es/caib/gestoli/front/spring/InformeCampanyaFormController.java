package es.caib.gestoli.front.spring;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractFormController;
import org.springframework.web.servlet.mvc.BaseCommandController;
import org.springframework.web.servlet.mvc.SimpleFormController;

import es.caib.gestoli.front.util.BasicData;
import es.caib.gestoli.front.util.Idioma;
import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;
import es.caib.gestoli.logic.model.Arxiu;
import es.caib.gestoli.logic.model.Campanya;
import es.caib.gestoli.logic.model.InformeCampanya;
import es.caib.gestoli.logic.model.QualitatDescripcioPersonal;

public class InformeCampanyaFormController  extends SimpleFormController {

	private static Logger logger = Logger.getLogger(InformeCampanyaFormController.class);
	
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
		InformeCampanyaCommand informeCampanyaCommand = (InformeCampanyaCommand)command;

		Long informeCampanyaId = informeCampanyaCommand.getId();
		
		String forward = getSuccessView();
		Map myModel = new HashMap();
		
		InformeCampanya informeCampanya = null;
		
		try{
			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
			if (isCreate(request)){
				logger.info("Creant el registre: " + informeCampanyaCommand);
				informeCampanya = new InformeCampanya();
				informeCampanya.setDataAlta(new Date());
			} else {
				logger.info("Actualitzant el registre: " + informeCampanyaCommand);
				informeCampanya = oliInfraestructuraEjb.informeCampanyaAmbId(informeCampanyaId);
			}
			
			Boolean tipus = informeCampanyaCommand.getTipus();
			if (tipus == null){
				tipus = new Boolean(false);
				informeCampanya.setCampanya(informeCampanyaCommand.getCampanya());
				/*if (informeCampanyaCommand.getIdCampanya() != null){
					oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
					informeCampanya.setCampanya(oliInfraestructuraEjb.campanyaAmbId(informeCampanyaCommand.getIdCampanya().intValue()));
				}*/
				informeCampanya.setAny(null);
			} else {
				tipus = new Boolean(true);
				informeCampanya.setCampanya(null);
				informeCampanya.setAny(informeCampanyaCommand.getAny());
			}
			informeCampanya.setTipus(tipus);

			MultipartFile[] files = getFiles(request);
			if (files != null) {
				if (files.length >= 1) {
					MultipartFile file = files[0];
					if (file.getSize() > 0) {
						Arxiu arxiu = new Arxiu();
						arxiu.setBinari(file.getBytes());
						arxiu.setMime(file.getContentType());
						arxiu.setNom(file.getOriginalFilename());
						informeCampanyaCommand.setArxiuDocument(arxiu);
					}
				}
			}
			
			//ImagenPrincipal
			Arxiu documentArxiu = null;
			if (informeCampanyaCommand.getArxiuDocument()!= null) {
				documentArxiu = new Arxiu();
				documentArxiu = informeCampanyaCommand.getArxiuDocument();
				documentArxiu.setTamany(new Integer(informeCampanyaCommand.getArxiuDocument().getBinari().length));
				oliInfraestructuraEjb.arxiuCrear(documentArxiu);
			}
			if (documentArxiu != null) {
				informeCampanya.setDocument(documentArxiu.getId());
			}
		
			// Creamos el infome de campanya
			informeCampanya.setId(oliInfraestructuraEjb.informeCampanyaCrear(informeCampanya));
			
			if (isCreate(request)){
				ControllerUtils.saveMessageInfo(request, missatge("informeCampanya.missatge.crear.ok"));
			}else{
				ControllerUtils.saveMessageInfo(request, missatge("informeCampanya.missatge.modificar.ok"));				
			}
		} catch (Exception ex) {
			if (isCreate(request)){
				logger.error("Error creant l'informe de campanya", ex);
				ControllerUtils.saveMessageError(request, missatge("informeCampanya.missatge.crear.no"));
			} else {
				logger.error("Error modificant l'informe de campanya", ex);
				ControllerUtils.saveMessageError(request, missatge("informeCampanya.missatge.modificar.no"));
			}
		}
		
		forward += "?id=" + informeCampanya.getId();
		
		return new ModelAndView(forward, myModel);
	}
	
	/*
	 * Retorna els arxius associats a aquesta peticio o null si no n'hi ha
	 */
	private MultipartFile[] getFiles(HttpServletRequest request) {
		if (request instanceof MultipartHttpServletRequest) {
			MultipartHttpServletRequest mpRequest = (MultipartHttpServletRequest) request;
			Map mFiles = mpRequest.getFileMap();
			if (mFiles.size() > 0) {
				MultipartFile[] files = new MultipartFile[mFiles.size()];
				int i = 0;
				for (Iterator it = mFiles.keySet().iterator(); it.hasNext();) {
					files[i++] = mpRequest.getFile((String) it.next());
				}
				if (files[0].getSize() > 0)
					return files;
				else
					return null;
			}
		}
		return null;
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

		Collection campanyes = new ArrayList();
		/*oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
		Collection itCampanyes = oliInfraestructuraEjb.campanyaCercaTotes();
		BasicData basicData;
		for (Iterator it = itCampanyes.iterator(); it.hasNext();){
			Campanya campanya = (Campanya)it.next();
			if (campanya.getId() != null) {
				basicData = new BasicData();
				basicData.setId(campanya.getId().toString());
				basicData.setNom(campanya.getNom().toString());
				campanyes.add(basicData);
			}
		}*/

		Collection anys = new ArrayList();
		oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        //Calendar dataActual = Calendar.getInstance();
        Calendar firstDate = Calendar.getInstance();
        firstDate.set(2000, 1, 1);
        BasicData basicDataAny;
        BasicData basicDataCampanya;
        Calendar any2 = Calendar.getInstance();
        any2.add(Calendar.YEAR, 1);
        for (Calendar any = Calendar.getInstance(); any.getTime().compareTo(firstDate.getTime()) > 0; any.add(Calendar.YEAR, -1)){
        	basicDataAny = new BasicData();
        	basicDataAny.setId(sdf.format(any.getTime()));
        	basicDataAny.setNom(sdf.format(any.getTime()));

        	basicDataCampanya = new BasicData();
        	basicDataCampanya.setNom(sdf.format(any.getTime()) + "/" + sdf.format(any2.getTime()));
        	basicDataCampanya.setId(basicDataCampanya.getNom());
			anys.add(basicDataAny);
			campanyes.add(basicDataCampanya);
			any2.add(Calendar.YEAR, -1);
		}
		myModel.put("anys", anys);
		myModel.put("campanyes", campanyes);
		
		myModel.put("path", "informeCampanya");
				
		myModel.put("formData", command);

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
		try {
			InformeCampanyaCommand command = new InformeCampanyaCommand();
			
			if ((!isFormSubmission(request)) && (!isCreate(request))) {
				try {
					Long id = new Long(Long.parseLong(request.getParameter("id")));
					oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
					InformeCampanya informeCampanya = oliInfraestructuraEjb.informeCampanyaAmbId(id);
					command.fromInformeCampanya(informeCampanya);
					
					command.setDocument(informeCampanya.getDocument());
					if (informeCampanya.getDocument() != null){
						Arxiu arxiu = oliInfraestructuraEjb.arxiuAmbId(informeCampanya.getDocument());
						command.setArxiuDocument(arxiu);
					}
					
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
        binder.registerCustomEditor(byte[].class,
				new ByteArrayMultipartFileEditor());
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


}
