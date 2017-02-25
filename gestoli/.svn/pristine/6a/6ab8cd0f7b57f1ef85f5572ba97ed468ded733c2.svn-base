/**
 * GestionarDocumentFormController.java
 */
package es.caib.gestoli.front.spring; 

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import es.caib.gestoli.logic.interfaces.OliArxiuEjb;
import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;
import es.caib.gestoli.logic.model.Arxiu;
import es.caib.gestoli.logic.model.Document;
import es.caib.gestoli.logic.model.DocumentInspeccio;
import es.caib.gestoli.logic.model.Establiment;
import es.caib.gestoli.logic.model.Informacio;
import es.caib.gestoli.logic.model.Olivicultor;
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
public class DocumentInspeccioFormController extends SimpleFormController {

    private static Logger logger = Logger.getLogger(DocumentInspeccioFormController.class);
    private OliInfraestructuraEjb oliInfraestructuraEjb;
    private OliArxiuEjb oliArxiuEjb;
    
	private HibernateTemplate hibernateTemplate;
	
	private String successOlivicultorView;
	private String successEstablimentView;
    
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
    	
    	DocumentInspeccioCommand documentCommand = (DocumentInspeccioCommand)command;
    	
    	Long documentInspeccioId = documentCommand.getId();
    	
    	Map myModel = new HashMap();
    	
    	DocumentInspeccio document = null;
		
    	try{
			if (isCreate(request)) {
				document = new DocumentInspeccio();
				if (documentCommand.getIdEstabliment() != null){
					logger.info("Creant el registre: " + documentCommand);
					oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
					Establiment establiment = oliInfraestructuraEjb.establimentAmbId(documentCommand.getIdEstabliment());
					document.setEstabliment(establiment);
				} else if (documentCommand.getIdOlivicultor() != null){
					oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
					Olivicultor olivicultor = oliInfraestructuraEjb.olivicultorAmbId(documentCommand.getIdOlivicultor());
					document.setOlivicultor(olivicultor);
				}
		
			} else {
				logger.info("Actualitzant el registre: " + documentCommand);
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				document = oliInfraestructuraEjb.documentInspeccioAmbId(documentInspeccioId);
			}
			
			document.setTipus(documentCommand.getTipus());
			document.setData(documentCommand.getData());

			// Recollim arxiu
			Arxiu arxiu = null;
			if (documentCommand.getFitxer() != null && documentCommand.getFitxer().length > 0) {
				MultipartFile[] files = getFiles(request);
				if (files != null) {
					if(files.length>=1){
						MultipartFile file = files[0];
						if(file.getSize()>0){
							arxiu = new Arxiu();
							arxiu.setId(documentCommand.getArxiu());
							arxiu.setBinari(file.getBytes());
							arxiu.setMime(file.getContentType());
							arxiu.setNom(file.getOriginalFilename());
							arxiu.setTamany(new Integer(file.getBytes().length));
						}
					}
				}
			} else if (documentCommand.getArxiu() != null){
				oliArxiuEjb.setHibernateTemplate(getHibernateTemplate());
				arxiu = oliArxiuEjb.arxiuCercaId(documentCommand.getArxiu());
			}
			
			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
			oliInfraestructuraEjb.arxiuCrear(arxiu);
			document.setArxiu(arxiu.getId());
			
			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
			document.setId(oliInfraestructuraEjb.documentInspeccioCrear(document));
			
			if (isCreate(request)){
				ControllerUtils.saveMessageInfo(request, missatge("documentInspeccio.missatge.crear.ok"));
			}else{
				ControllerUtils.saveMessageInfo(request, missatge("documentInspeccio.missatge.modificar.ok"));				
			}
			
    	} catch (Exception ex) {
			if (isCreate(request)){
				logger.error("Error creant el document d'inspecció", ex);
				ControllerUtils.saveMessageError(request, missatge("documentInspeccio.missatge.crear.no"));
			} else {
				logger.error("Error modificant el document d'inspecció", ex);
				ControllerUtils.saveMessageError(request, missatge("documentInspeccio.missatge.modificar.no"));
			}
		}
        
		String forward = null;
		if (documentCommand.getIdOlivicultor() != null){
			forward = getSuccessOlivicultorView() + "?id=" + document.getOlivicultor().getId();
		}else{
			forward = getSuccessEstablimentView() + "?id=" + document.getEstabliment().getId();
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
        
    	String lang = Idioma.getLang(request);
		ResourceBundle bundle = ResourceBundle.getBundle("messages",new java.util.Locale(lang));
        Map myModel = new HashMap();
        
       	myModel.put("path", "modificar_document");
        
       	ArrayList tipusInspeccio = new ArrayList();
       	BasicData basicData = new BasicData();
       	basicData.setId("acta");
       	basicData.setNom(bundle.getString("documentInspeccio.tipusInspeccio.acta"));
       	tipusInspeccio.add(basicData);
       	basicData = new BasicData();
       	basicData.setId("informe");
       	basicData.setNom(bundle.getString("documentInspeccio.tipusInspeccio.informe"));
       	tipusInspeccio.add(basicData);
       	myModel.put("tipusInspeccio", tipusInspeccio);
       	
       	Long idEstabliment = null;
		if (request.getParameter("idEstabliment") != null){
			idEstabliment = new Long(Long.parseLong(request.getParameter("idEstabliment")));
		}
		myModel.put("idEstabliment", idEstabliment);
		
	 	Long idOlivicultor = null;
		if (request.getParameter("idOlivicultor") != null){
			idOlivicultor = new Long(Long.parseLong(request.getParameter("idOlivicultor")));
		}
		myModel.put("idOlivicultor", idOlivicultor);
		
		
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
    	
    	try{
    		DocumentInspeccioCommand documentCommand = new DocumentInspeccioCommand();
    		
    		if ((!isFormSubmission(request)) && (!isCreate(request))) {
    			Long id = new Long(Long.valueOf(request.getParameter("id")));
    			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
    			DocumentInspeccio document = oliInfraestructuraEjb.documentInspeccioAmbId(id);
    			documentCommand.fromDocumentInspeccio(document);
    			if (document.getArxiu() != null) {
    				oliArxiuEjb.setHibernateTemplate(getHibernateTemplate());
    				documentCommand.setArxiuObject(oliArxiuEjb.arxiuCercaId(documentCommand.getArxiu()));
    			}
    		} else {
    			String idOlivicultor = request.getParameter("idOlivicultor");
        		String idEstabliment = request.getParameter("idEstabliment");
        		
        		if (idOlivicultor!= null && !"".equals(idOlivicultor)) {
        			Olivicultor olivicultor = new Olivicultor();
        			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
        			olivicultor = oliInfraestructuraEjb.olivicultorAmbId(new Long(idOlivicultor));
        			documentCommand.setOlivicultor(olivicultor);
        			documentCommand.setIdOlivicultor(Long.valueOf(idOlivicultor));
        		}else if (idEstabliment != null && !"".equals(idEstabliment)) {
        			Establiment establiment = new Establiment();
        			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
        			establiment = oliInfraestructuraEjb.establimentAmbId(new Long(idEstabliment));
        			documentCommand.setEstabliment(establiment);
        			documentCommand.setIdEstabliment(Long.valueOf(idEstabliment));
        		}
    		}

    		return documentCommand;

    	} catch (Exception ex) {
    		throw new ServletException("Error cridant l'EJB", ex);
     	}

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
        binder.registerCustomEditor(
        		Date.class,
        		new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), true));
    }
 
    

	/**
	 * @param oliInfraestructuraEjb the oliInfraestructuraEjb to set
	 */
	public void setOliInfraestructuraEjb(OliInfraestructuraEjb oliInfraestructuraEjb) {
		this.oliInfraestructuraEjb = oliInfraestructuraEjb;
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

	 /**
     * Inyección de la dependencia successOlivicultorView
     * @param successOlivicultorView la cadena a inyectar.
     */
    public void setSuccessOlivicultorView(String successOlivicultorView) {
        this.successOlivicultorView = successOlivicultorView;
    }
    
	/**
	 * Return the name of the view that should be shown on successful submit.
	 */
	public final String getSuccessOlivicultorView() {
		return this.successOlivicultorView;
	}
	
	 /**
     * Inyección de la dependencia successEstablimentView
     * @param successEstablimentView la cadena a inyectar.
     */
    public void setSuccessEstablimentView(String successEstablimentView) {
        this.successEstablimentView = successEstablimentView;
    }
    
	/**
	 * Return the name of the view that should be shown on successful submit.
	 */
	public final String getSuccessEstablimentView() {
		return this.successEstablimentView;
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