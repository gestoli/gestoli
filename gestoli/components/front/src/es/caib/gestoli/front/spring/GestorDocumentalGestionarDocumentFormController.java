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
import es.caib.gestoli.logic.model.GestorDocumentalDocument;
import es.caib.gestoli.logic.model.GestorDocumentalInformacio;
import es.caib.gestoli.logic.model.Informacio;



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
public class GestorDocumentalGestionarDocumentFormController extends SimpleFormController {

    private static Logger logger = Logger.getLogger(GestorDocumentalGestionarDocumentFormController.class);
    private OliQualitatEjb oliQualitatEjb;
    private OliArxiuEjb oliArxiuEjb;
    
	private HibernateTemplate hibernateTemplate;
    
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
    	
    	GestorDocumentalGestionarDocumentCommand docum = (GestorDocumentalGestionarDocumentCommand)command;
    	
    	Map myModel = new HashMap();
    	
    	GestorDocumentalDocument document = null;
		if (isCreate(request)) {
			try{
				document = new GestorDocumentalDocument();
				oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
				GestorDocumentalInformacio informacio = oliQualitatEjb.informacioAmbId(docum.getGestorDocumentalInformacio().getId());
				
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
				document.setGestorDocumentalInformacio(informacio);
//				document.setArxiu(docum.getArxiu());
				
				oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
				oliQualitatEjb.documentCrear(document, arxiu);
				
				ControllerUtils.saveMessageInfo(request, missatge("document.missatge.crear.ok"));
				
	    	} catch (Exception e) {
	    		 logger.error("Error en la gestió del document", e);
	             ControllerUtils.saveMessageError(request, missatge("document.missatge.crear.no"));
			}
		} else {
			try{
				document = new GestorDocumentalDocument();
				oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
				GestorDocumentalInformacio informacio = oliQualitatEjb.informacioAmbId(docum.getGestorDocumentalInformacio().getId());
				
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
				document.setGestorDocumentalInformacio(informacio);
				document.setArxiu(docum.getArxiu());
				
				oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
				oliQualitatEjb.documentModificar(document, arxiu);
				
				ControllerUtils.saveMessageInfo(request, missatge("document.missatge.modificar.ok"));
				
	    	} catch (Exception e) {
	    		 logger.error("Error en la gestió del document", e);
	             ControllerUtils.saveMessageError(request, missatge("document.missatge.modificar.no"));
			}
		}
        
		String forward = getSuccessView() + "?id=" + document.getGestorDocumentalInformacio().getId();
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
        
    	GestorDocumentalGestionarDocumentCommand docum = (GestorDocumentalGestionarDocumentCommand) command;
    	
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
    	
    	GestorDocumentalGestionarDocumentCommand docum = null;
    	
    	try{
    		if ((!isFormSubmission(request)) && (!isCreate(request))) {
    			Integer codi = new Integer(Integer.parseInt(request.getParameter("id")));
    			oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
    			GestorDocumentalDocument d = oliQualitatEjb.documentAmbId(codi);
    			docum = new GestorDocumentalGestionarDocumentCommand();
    			docum.fromDocument(d);
    			if (docum.getArxiu() != null) {
    				oliArxiuEjb.setHibernateTemplate(getHibernateTemplate());
    				docum.setArxiuObject(oliArxiuEjb.arxiuCercaId(docum.getArxiu()));
    			}
    		} else {
    			String idInformacio = request.getParameter("idInformacio");
        		GestorDocumentalInformacio info = new GestorDocumentalInformacio();
        		
        		docum = new GestorDocumentalGestionarDocumentCommand();
        		
        		if (idInformacio != null && !"".equals(idInformacio)) {
        			oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
        			info = oliQualitatEjb.informacioAmbId(new Integer(idInformacio));
        		}
        		docum.setGestorDocumentalInformacio(info);
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
 
    

	public void setOliQualitatEjb(OliQualitatEjb oliQualitatEjb) {
		this.oliQualitatEjb = oliQualitatEjb;
	}

	/**
	 * @param oliArxiuEjb the oliArxiuEjb to set
	 */
	public void setOliArxiuEjb(OliArxiuEjb oliArxiuEjb) {
		this.oliArxiuEjb = oliArxiuEjb;
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