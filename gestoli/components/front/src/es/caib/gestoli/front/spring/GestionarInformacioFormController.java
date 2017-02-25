/**
 * GestionarInformacioFormController.java
 */
package es.caib.gestoli.front.spring; 

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

import es.caib.gestoli.front.util.BasicData;
import es.caib.gestoli.logic.interfaces.OliArxiuEjb;
import es.caib.gestoli.logic.interfaces.OliInformacioEjb;
import es.caib.gestoli.logic.model.CategoriaInformacio;
import es.caib.gestoli.logic.model.Document;
import es.caib.gestoli.logic.model.Informacio;
import es.caib.gestoli.logic.model.Usuari;



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
public class GestionarInformacioFormController extends SimpleFormController {

    private static Logger logger = Logger.getLogger(GestionarInformacioFormController.class);
    private OliInformacioEjb oliInformacioEjb;
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
    	
    	GestionarInformacioCommand info = (GestionarInformacioCommand)command;
    	
    	Map myModel = new HashMap();
    	
    	Informacio informacio = null;
    	
    	String forward = "";
		if (isCreate(request)) {
			try{
				informacio = new Informacio();
				oliInformacioEjb.setHibernateTemplate(getHibernateTemplate());
				CategoriaInformacio categoria = oliInformacioEjb.categoriaInformacioAmbId(info.getIdCategoria());
				
				informacio.setCategoriaInformacio(categoria);
				informacio.setData(info.getData());
				informacio.setTexte(info.getTexte());
				informacio.setTitol(info.getTitol());
				
				oliInformacioEjb.setHibernateTemplate(getHibernateTemplate());
				oliInformacioEjb.informacioCrear(informacio);
				
				ControllerUtils.saveMessageInfo(request, missatge("informacio.missatge.crear.ok"));
				
				forward = getSuccessView()+ "?id=" + informacio.getId();
				
	    	} catch (Exception e) {
	    		 logger.error("Error en la gestió de la informació", e);
	             ControllerUtils.saveMessageError(request, missatge("informacio.missatge.crear.no"));
			}
		} else {
			try{
				oliInformacioEjb.setHibernateTemplate(getHibernateTemplate());
				informacio = oliInformacioEjb.informacioAmbId(info.getId());
				
				oliInformacioEjb.setHibernateTemplate(getHibernateTemplate());
				CategoriaInformacio categoria = oliInformacioEjb.categoriaInformacioAmbId(info.getIdCategoria());
				
				informacio.setCategoriaInformacio(categoria);
				informacio.setData(info.getData());
				informacio.setTexte(info.getTexte());
				informacio.setTitol(info.getTitol());
				
				oliInformacioEjb.setHibernateTemplate(getHibernateTemplate());
				oliInformacioEjb.informacioModificar(informacio, info.getInformar());
				
				ControllerUtils.saveMessageInfo(request, missatge("informacio.missatge.modificar.ok"));
				
				forward = "redirect:GestionarInformacio.html";
				
	    	} catch (Exception e) {
	    		 logger.error("Error en la gestió de la informació", e);
	             ControllerUtils.saveMessageError(request, missatge("informacio.missatge.modificar.no"));
			}
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
        
    	GestionarInformacioCommand info = (GestionarInformacioCommand) command;
    	
        Map myModel = new HashMap();
        
        // CATEGORIAS
        List categorias = new ArrayList();
        oliInformacioEjb.setHibernateTemplate(getHibernateTemplate());
        Iterator itCategorias = oliInformacioEjb.categoriaInformacioFindAll().iterator();
        while (itCategorias.hasNext()) {
        	CategoriaInformacio categoria = (CategoriaInformacio) itCategorias.next();
        	BasicData basic = new BasicData();
        	basic.setId(String.valueOf(categoria.getId()));
        	basic.setNom(categoria.getNom());
        	categorias.add(basic);
        }
        
        myModel.put("categorias", categorias);
        
        // DOCUMENTOS ASIGNADOS
        if (info.getId() != null) {
        	List llistatDocuments = new ArrayList();
        	oliInformacioEjb.setHibernateTemplate(getHibernateTemplate());
        	Iterator itDocumentos = oliInformacioEjb.documentsByInformacio(info.getId()).iterator();
        	while (itDocumentos.hasNext()) {
        		GestionarDocumentCommand document = new GestionarDocumentCommand();
        		document.fromDocument((Document) itDocumentos.next());
        		oliArxiuEjb.setHibernateTemplate(getHibernateTemplate());
				document.setArxiuObject(oliArxiuEjb.arxiuCercaId(document.getArxiu()));
				llistatDocuments.add(document);
        	}
        	myModel.put("llistatDocuments", llistatDocuments);
        }
        
        if (info.getId() != null) {
        	myModel.put("path", "modificar_informacion");
        } else {
        	myModel.put("path", "crear_informacion");
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
    	
    	GestionarInformacioCommand info = null;
    	
    	UsuariCommand usuari = null;
    	if ((!isFormSubmission(request)) && (!isCreate(request))) {
    		try {
    			Integer codi = new Integer(Integer.parseInt(request.getParameter("id")));
    			oliInformacioEjb.setHibernateTemplate(getHibernateTemplate());
    			Informacio i = oliInformacioEjb.informacioAmbId(codi);
    			info = new GestionarInformacioCommand();
    			info.fromInformacio(i);

    		} catch (Exception e) {
    			logger.error("EXCEPTION", e);
    		}
    	} else {
    		info = new GestionarInformacioCommand();
    		info.setData(new Date());
    	}
//    	if (isCreate(request)) {
//    		info.setData(new Date());
//		}
    	return info;

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
        		Date.class,
        		new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), true));
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

	
}