package es.caib.gestoli.front.spring;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
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

import es.caib.gestoli.front.util.Idioma;
import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;
import es.caib.gestoli.logic.model.Arxiu;
import es.caib.gestoli.logic.model.GestioInfografia;
import es.caib.gestoli.logic.model.InformacioUtil;

public class GestioInfografiaFormController  extends SimpleFormController {

	private static Logger logger = Logger.getLogger(GestioInfografiaFormController.class);
	
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
		GestioInfografiaCommand gestioInfografiaCommand = (GestioInfografiaCommand)command;

		Long gestioInfografiaId = gestioInfografiaCommand.getId();
		
		String forward = getSuccessView();
		Map myModel = new HashMap();
		
		//HttpSession session = request.getSession();
		//Establiment est = (Establiment)session.getAttribute(establimentSessionKey);

		GestioInfografia gestioInfografia = null;
		
		try{
			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
			if (isCreate(request)){
				logger.info("Creant el registre: " + gestioInfografiaCommand);
				gestioInfografia = new GestioInfografia();
				gestioInfografia.setDataAlta(new Date());
				//informacioUtil.setEstabliment(est);
			} else {
				logger.info("Actualitzant el registre: " + gestioInfografiaCommand);
				gestioInfografia = oliInfraestructuraEjb.gestioInfografiaAmbId(gestioInfografiaId);
			}
			

			gestioInfografia.setNom(gestioInfografiaCommand.getNom());
			gestioInfografia.setDescripcio(gestioInfografiaCommand.getDescripcio());
			gestioInfografia.setNomEs(gestioInfografiaCommand.getNomEs());
			gestioInfografia.setDescripcioEs(gestioInfografiaCommand.getDescripcioEs());
			
			MultipartFile[] files = getFiles(request);
			if (files != null) {
				if (files.length >= 1) {
					MultipartFile file = files[0];
					if (file.getSize() > 0) {
						Arxiu arxiuArxiu= new Arxiu();
						arxiuArxiu.setBinari(file.getBytes());
						arxiuArxiu.setMime(file.getContentType());
						arxiuArxiu.setNom(file.getOriginalFilename());
						gestioInfografiaCommand.setArxiuArxiu(arxiuArxiu);
					}
				}
				if (files.length >= 2) {
					MultipartFile file = files[1];
					if (file.getSize() > 0) {
						Arxiu arxiuImatge = new Arxiu();
						arxiuImatge.setBinari(file.getBytes());
						arxiuImatge.setMime(file.getContentType());
						arxiuImatge.setNom(file.getOriginalFilename());
						gestioInfografiaCommand.setArxiuImatge(arxiuImatge);
					}
				}
			}
			
			//Arxiu d'infografia
			Arxiu arxiuArxiu = null;
			if (gestioInfografiaCommand.getArxiuArxiu()!= null) {
				arxiuArxiu = new Arxiu();
				arxiuArxiu = gestioInfografiaCommand.getArxiuArxiu();
				arxiuArxiu.setTamany(new Integer(gestioInfografiaCommand.getArxiuArxiu().getBinari().length));
				oliInfraestructuraEjb.arxiuCrear(arxiuArxiu);
			}
			if (arxiuArxiu != null) {
				gestioInfografia.setArxiu(arxiuArxiu.getId());
			}
			
			//Arxiu d'imatge
			Arxiu arxiuImatge = null;
			if (gestioInfografiaCommand.getArxiuImatge()!= null) {
				arxiuImatge = new Arxiu();
				arxiuImatge = gestioInfografiaCommand.getArxiuImatge();
				arxiuImatge.setTamany(new Integer(gestioInfografiaCommand.getArxiuImatge().getBinari().length));
				oliInfraestructuraEjb.arxiuCrear(arxiuImatge);
			}
			if (arxiuImatge != null) {
				gestioInfografia.setImatge(arxiuImatge.getId());
			}
			
			
			// Creamos la infografia
			gestioInfografia.setId(oliInfraestructuraEjb.gestioInfografiaCrear(gestioInfografia));
			
			if (isCreate(request)){
				ControllerUtils.saveMessageInfo(request, missatge("gestioInfografia.missatge.crear.ok"));
			}else{
				ControllerUtils.saveMessageInfo(request, missatge("gestioInfografia.missatge.modificar.ok"));				
			}
		} catch (Exception ex) {
			if (isCreate(request)){
				logger.error("Error creant la gestioInfografia", ex);
				ControllerUtils.saveMessageError(request, missatge("gestioInfografia.missatge.crear.no"));
			} else {
				logger.error("Error modificant la gestioInfografia", ex);
				ControllerUtils.saveMessageError(request, missatge("gestioInfografia.missatge.modificar.no"));
			}
		}
		
		forward += "?id=" + gestioInfografia.getId();
		
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
				if (files[0].getSize() > 0 || files[1].getSize() > 0)
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
		
		String lang = Idioma.getLang(request);
		ResourceBundle bundle = ResourceBundle.getBundle("messages",new java.util.Locale(lang));
		Map myModel = new HashMap();

		//HttpSession session = request.getSession();
		//Establiment est = (Establiment)session.getAttribute(establimentSessionKey);
		
		myModel.put("path", "gestioInfografia");
		//myModel.put("idEstabliment", est.getId());
				
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
			GestioInfografiaCommand command = new GestioInfografiaCommand();
			
			if ((!isFormSubmission(request)) && (!isCreate(request))) {
				try {
					Long id = new Long(Long.parseLong(request.getParameter("id")));
					oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
					GestioInfografia gestioInfografia = oliInfraestructuraEjb.gestioInfografiaAmbId(id);
					command.fromGestioInfografia(gestioInfografia);
					
					//command.setIdArxiu(gestioInfografia.getArxiu());
					if (gestioInfografia.getArxiu() != null){
						Arxiu arxiu = oliInfraestructuraEjb.arxiuAmbId(gestioInfografia.getArxiu());
						command.setArxiuArxiu(arxiu);
					}
					
					//command.setIdImatge(gestioInfografia.getImatge());
					if (gestioInfografia.getImatge() != null){
						Arxiu imatge = oliInfraestructuraEjb.arxiuAmbId(gestioInfografia.getImatge());
						command.setArxiuImatge(imatge);
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

/*
	public void setEstablimentSessionKey(String establimentSessionKey) {
		this.establimentSessionKey = establimentSessionKey;
	}*/


}
