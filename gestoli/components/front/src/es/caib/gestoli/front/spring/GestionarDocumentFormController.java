/**
 * GestionarDocumentFormController.java
 */
package es.caib.gestoli.front.spring; 

import java.util.HashMap;
import java.util.Iterator;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractFormController;
import org.springframework.web.servlet.mvc.BaseCommandController;
import org.springframework.web.servlet.mvc.SimpleFormController;

import es.caib.gestoli.logic.interfaces.OliArxiuEjb;
import es.caib.gestoli.logic.interfaces.OliInformacioEjb;
import es.caib.gestoli.logic.interfaces.OliQualitatEjb;
import es.caib.gestoli.logic.model.Arxiu;
import es.caib.gestoli.logic.model.Document;
import es.caib.gestoli.logic.model.Informacio;
import es.caib.gestoli.logic.model.QualitatDescripcioPersonal;



/**
 * <p>Controlador per a l'acció d'executar un proces d'entrada
 * de oliva.</p>
 * <p>Aquest controlador distingueix entre les peticions del
 * tipus GET y les de tipus POST. Si la petición es de tipus POST
 * s'executa l'acció de creació o d'edició. Si la petició es de
 * tipo GET només mostra la página.
 *
 * @author cperez <cperez@at4.net>
 */
public class GestionarDocumentFormController extends SimpleFormController {

    private static Logger logger = Logger.getLogger(GestionarDocumentFormController.class);
    private OliInformacioEjb oliInformacioEjb;
    private OliArxiuEjb oliArxiuEjb;
    private OliQualitatEjb oliQualitatEjb;
    
	private HibernateTemplate hibernateTemplate;
	
	private String successPersonalView;
    
    /**
     * Es crida quan s'accepten les modificacions a l'objecte. Només
     * s'executa aquesta funció en el cas de que s'hagi executat la
     * validació sense produïr cap error.
     * 
     * @see SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.validation.BindException)
     */
    public ModelAndView onSubmit(
            HttpServletRequest request,
            HttpServletResponse response,
            Object command,
            BindException errors)
    throws ServletException {
    	
    	GestionarDocumentCommand docum = (GestionarDocumentCommand)command;
    	
    	Map myModel = new HashMap();
    	
    	Document document = null;
		if (isCreate(request)) {
			try{
				document = new Document();
				Informacio informacio = new Informacio();
				QualitatDescripcioPersonal personal = new QualitatDescripcioPersonal();
				if (docum.getInformacio().getId() != null){
					oliInformacioEjb.setHibernateTemplate(getHibernateTemplate());
					informacio = oliInformacioEjb.informacioAmbId(docum.getInformacio().getId());
					document.setInformacio(informacio);
				}else{
					oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
					personal = oliQualitatEjb.personalAmbId(docum.getPersonal().getCodi());
					document.setPersonal(personal);
				}
					
				
				// Recollim arxiu
				Arxiu arxiu = null;
				MultipartFile[] files = getFiles(request);
				if (files != null) {
					if(files.length>=1){
						MultipartFile file = files[0];
						if(file.getSize()>0){
							arxiu = new Arxiu();
							arxiu.setBinari(file.getBytes());
							arxiu.setMime(file.getContentType());
							arxiu.setNom(file.getOriginalFilename());
							arxiu.setTamany(new Integer(file.getBytes().length));
						}
					}
				}
				document.setTitol(docum.getTitol());
//				document.setArxiu(docum.getArxiu());
				
				oliInformacioEjb.setHibernateTemplate(getHibernateTemplate());
				oliInformacioEjb.documentCrear(document, arxiu);
				
				ControllerUtils.saveMessageInfo(request, missatge("document.missatge.crear.ok"));
				
	    	} catch (Exception e) {
	    		 logger.error("Error en la gestió del document", e);
	             ControllerUtils.saveMessageError(request, missatge("document.missatge.crear.no"));
			}
		} else {
			try{
				document = new Document();
				Informacio informacio = new Informacio();
				QualitatDescripcioPersonal personal = new QualitatDescripcioPersonal();
				if (docum.getInformacio().getId() != null){
					oliInformacioEjb.setHibernateTemplate(getHibernateTemplate());
					informacio = oliInformacioEjb.informacioAmbId(docum.getInformacio().getId());
					document.setInformacio(informacio);
				}else{
					oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
					personal = oliQualitatEjb.personalAmbId(docum.getPersonal().getCodi());
					document.setPersonal(personal);
				}
				
				// Recollim arxiu
				Arxiu arxiu = null;
				if (docum.getFitxer() != null && docum.getFitxer().length > 0) {
					MultipartFile[] files = getFiles(request);
					if (files != null) {
						if(files.length>=1){
							MultipartFile file = files[0];
							if(file.getSize()>0){
								arxiu = new Arxiu();
								arxiu.setId(docum.getArxiu());
								arxiu.setBinari(file.getBytes());
								arxiu.setMime(file.getContentType());
								arxiu.setNom(file.getOriginalFilename());
								arxiu.setTamany(new Integer(file.getBytes().length));
							}
						}
					}
				} else {
					oliArxiuEjb.setHibernateTemplate(getHibernateTemplate());
					arxiu = oliArxiuEjb.arxiuCercaId(docum.getArxiu());
				}
				document.setId(docum.getId());
				document.setTitol(docum.getTitol());
				document.setArxiu(docum.getArxiu());
				
				oliInformacioEjb.setHibernateTemplate(getHibernateTemplate());
				oliInformacioEjb.documentModificar(document, arxiu);
				
				ControllerUtils.saveMessageInfo(request, missatge("document.missatge.modificar.ok"));
				
	    	} catch (Exception e) {
	    		 logger.error("Error en la gestió del document", e);
	             ControllerUtils.saveMessageError(request, missatge("document.missatge.modificar.no"));
			}
		}
        
		String forward = null;
		if (docum.getInformacio().getId() != null){
			forward = getSuccessView() + "?id=" + document.getInformacio().getId();
		}else{
			forward = getSuccessPersonalView() + "?codi=" + document.getPersonal().getCodi();
		}
		return new ModelAndView(forward, myModel);
    }

    /**
     * Retorna totes les dades del model necessàries per a mostrar
     * el formulari d'inserció (LOVs y coses per l'estil) si no
     * n'hi ha.
     * 
     * @see SimpleFormController#referenceData(javax.servlet.http.HttpServletRequest, java.lang.Object, org.springframework.validation.Errors)
     */
    protected Map referenceData(
            HttpServletRequest request,
            Object command,
            Errors errors) throws Exception {
        
    	GestionarDocumentCommand docum = (GestionarDocumentCommand) command;
    	
        Map myModel = new HashMap();
        
        if (docum.getId() != null) {
        	myModel.put("path", "modificar_document");
        } else {
        	myModel.put("path", "crear_document");
        }
        
        return myModel;
    }

    
    /**
     * En el cas de que sigui una edició retorna l'objecte omplit amb
     * les dades actuals del registre. En caso de que sigui una creació
     * retorna l'objecte buit.
     * 
     * @see AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
     */
    protected Object formBackingObject(HttpServletRequest request) throws ServletException {
    	
    	GestionarDocumentCommand docum = null;
    	
    	try{
    		if ((!isFormSubmission(request)) && (!isCreate(request))) {
    			Integer codi = new Integer(Integer.parseInt(request.getParameter("id")));
    			oliInformacioEjb.setHibernateTemplate(getHibernateTemplate());
    			Document d = oliInformacioEjb.documentAmbId(codi);
    			docum = new GestionarDocumentCommand();
    			docum.fromDocument(d);
    			if (docum.getArxiu() != null) {
    				oliArxiuEjb.setHibernateTemplate(getHibernateTemplate());
    				docum.setArxiuObject(oliArxiuEjb.arxiuCercaId(docum.getArxiu()));
    			}
    		} else {
    			String idInformacio = request.getParameter("idInformacio");
        		Informacio info = new Informacio();
        		String idPersonal = request.getParameter("idPersonal");
        		QualitatDescripcioPersonal personal = new QualitatDescripcioPersonal();
        		
        		docum = new GestionarDocumentCommand();
        		
        		if (idInformacio != null && !"".equals(idInformacio)) {
        			oliInformacioEjb.setHibernateTemplate(getHibernateTemplate());
        			info = oliInformacioEjb.informacioAmbId(new Integer(idInformacio));
        		}else if (idPersonal != null && !"".equals(idPersonal)) {
        			oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
        			personal = oliQualitatEjb.personalAmbId(new Long(idPersonal));
        		}
        		docum.setInformacio(info);
        		docum.setPersonal(personal);
    		}
//    		if (isCreate(request)) {
//    			
//    		}
    	} catch (Exception ex) {
			logger.error("EXCEPTION", ex);
    	}
    	return docum;

    }

    /**
     * Configuració del <i>binder</i>. Si hi ha camps en el
     * <i>command</i> amb tipus que no siguin <i>String</i>
     * s'ha de configurar el seu corresponent binder aquí.
     * 
     * @see BaseCommandController#initBinder(javax.servlet.http.HttpServletRequest, org.springframework.web.bind.ServletRequestDataBinder)
     */
    protected void initBinder(
            HttpServletRequest request,
            ServletRequestDataBinder binder)
    throws Exception {
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
				byte[].class,
				new ByteArrayMultipartFileEditor());
    }
 
    

	/**
	 * @param oliInformacioEjb the oliInformacioEjb to set
	 */
	public void setOliInformacioEjb(OliInformacioEjb oliInformacioEjb) {
		this.oliInformacioEjb = oliInformacioEjb;
	}

	/**
	 * @param oliArxiuEjb the oliArxiuEjb to set
	 */
	public void setOliArxiuEjb(OliArxiuEjb oliArxiuEjb) {
		this.oliArxiuEjb = oliArxiuEjb;
	}

	/**
	 * @param oliQualitatEjb the oliQualitatEjb to set
	 */
	public void setOliQualitatEjb(OliQualitatEjb oliQualitatEjb) {
		this.oliQualitatEjb = oliQualitatEjb;
	}
	
	private String missatge(String clave){
    	String valor= getMessageSourceAccessor().getMessage(clave);
    	return valor;
    }
    
    /**
	 * Indica si la petición es de creación o no.
	 * @param request
	 * @return true si es una petición de creación o false si es de edición.
	 */
	private boolean isCreate(HttpServletRequest request) {
		return (request.getParameter("id") == null);
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

	 /**
     * Inyección de la dependencia successPersonalView
     * @param successPersonalView la cadena a inyectar.
     */
    public void setSuccessPersonalView(String successPersonalView) {
        this.successPersonalView = successPersonalView;
    }
    
	/**
	 * Return the name of the view that should be shown on successful submit.
	 */
	public final String getSuccessPersonalView() {
		return this.successPersonalView;
	}
	
	/*
     * Retorna els arxius associats a aquesta peticio o null
     * si no n'hi ha 
     */
    private MultipartFile[] getFiles(HttpServletRequest request) {
    	if (request instanceof MultipartHttpServletRequest) {
        	MultipartHttpServletRequest mpRequest = (MultipartHttpServletRequest)request;
        	Map mFiles = mpRequest.getFileMap();
        	if (mFiles.size() > 0) {
	        	MultipartFile[] files = new MultipartFile[mFiles.size()];
	        	int i = 0;
	        	for (Iterator it = mFiles.keySet().iterator(); it.hasNext();) {
	        		files[i++] = mpRequest.getFile((String)it.next());
	        	}
	        	if (files[0].getSize() > 0 || files[1].getSize()>0)
	        		return files;
	        	else
	        		return null;
        	}
    	}
        return null;
    }
	
}