/**
 * GestionarInformacioFormController.java
 */
package es.caib.gestoli.front.spring; 

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import es.caib.gestoli.logic.interfaces.OliQualitatEjb;
import es.caib.gestoli.logic.model.GestorDocumentalCategoriaInformacio;
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
public class GestorDocumentalGestionarInformacioFormController extends SimpleFormController {

    private static Logger logger = Logger.getLogger(GestorDocumentalGestionarInformacioFormController.class);
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
    	
    	GestorDocumentalGestionarInformacioCommand info = (GestorDocumentalGestionarInformacioCommand)command;
    	
    	Map myModel = new HashMap();
    	
    	GestorDocumentalInformacio informacio = null;
    	
    	String forward = "";
		if (isCreate(request)) {
			try{
				informacio = new GestorDocumentalInformacio();
				oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
				GestorDocumentalCategoriaInformacio categoria = oliQualitatEjb.categoriaInformacioAmbId(info.getIdCategoria());
				
				informacio.setGestorDocumentalCategoriaInformacio(categoria);
				informacio.setData(info.getData());
				informacio.setTexte(info.getTexte());
				informacio.setTitol(info.getTitol());
				informacio.setEstat(info.getEstat());
				
				oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
				oliQualitatEjb.informacioCrear(informacio);
				
				ControllerUtils.saveMessageInfo(request, missatge("informacio.missatge.crear.ok"));
				
				forward = getSuccessView()+ "?id=" + informacio.getId();
				
	    	} catch (Exception e) {
	    		 logger.error("Error en la gestio de la informacio", e);
	             ControllerUtils.saveMessageError(request, missatge("informacio.missatge.crear.no"));
			}
		} else {
			try{
				oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
				informacio = oliQualitatEjb.informacioAmbId(info.getId());
				
				oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
				GestorDocumentalCategoriaInformacio categoria = oliQualitatEjb.categoriaInformacioAmbId(info.getIdCategoria());
				
				informacio.setGestorDocumentalCategoriaInformacio(categoria);
				informacio.setData(info.getData());
				informacio.setTexte(info.getTexte());
				informacio.setTitol(info.getTitol());
				informacio.setEstat(info.getEstat());
				
				oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
				oliQualitatEjb.informacioModificar(informacio, info.getInformar());
				
				ControllerUtils.saveMessageInfo(request, missatge("informacio.missatge.modificar.ok"));
				
				forward = "redirect:GestorDocumentalGestionarInformacio.html";
				
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
        
    	GestorDocumentalGestionarInformacioCommand info = (GestorDocumentalGestionarInformacioCommand) command;
    	
        Map myModel = new HashMap();
        
        // CATEGORIAS
        List categorias = new ArrayList();
        oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
        Iterator itCategorias = oliQualitatEjb.categoriaInformacioFindAll().iterator();
        while (itCategorias.hasNext()) {
        	GestorDocumentalCategoriaInformacio categoria = (GestorDocumentalCategoriaInformacio) itCategorias.next();
        	BasicData basic = new BasicData();
        	basic.setId(String.valueOf(categoria.getId()));
        	basic.setNom(categoria.getNom());
        	categorias.add(basic);
        }
        
        myModel.put("categorias", categorias);
        
    	//Estat
		 List estats = new ArrayList();
		 estats.add(new BasicData("1", missatge("qualitat.informacio.estat.1")));
		 estats.add(new BasicData("2", missatge("qualitat.informacio.estat.2")));
		 estats.add(new BasicData("3", missatge("qualitat.informacio.estat.3")));
		 if (info.getEstat() != null && (info.getEstat() == 3 || info.getEstat() == 4)){
			 estats.add(new BasicData("4", missatge("qualitat.informacio.estat.4")));
		 }
		 
		 //Object fila = new Object();
		 
		 //Float c=Double.valueOf(fila.toString()).floatValue();
		 //Object a = Long.valueOf(3);
		 //Integer b = Integer.parseInt(a.toString());
		 
	     myModel.put("estats",estats);
        
        // DOCUMENTOS ASIGNADOS
        if (info.getId() != null) {
        	List llistatDocuments = new ArrayList();
        	oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
        	Iterator itDocumentos = oliQualitatEjb.documentsByInformacio(info.getId()).iterator();
        	while (itDocumentos.hasNext()) {
        		GestorDocumentalGestionarDocumentCommand document = new GestorDocumentalGestionarDocumentCommand();
        		document.fromDocument((GestorDocumentalDocument) itDocumentos.next());
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
    	
    	GestorDocumentalGestionarInformacioCommand info = null;
    	
    	UsuariCommand usuari = null;
    	if ((!isFormSubmission(request)) && (!isCreate(request))) {
    		try {
    			Integer codi = new Integer(Integer.parseInt(request.getParameter("id")));
    			oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
    			GestorDocumentalInformacio i = oliQualitatEjb.informacioAmbId(codi);
    			info = new GestorDocumentalGestionarInformacioCommand();
    			info.fromInformacio(i);

    		} catch (Exception e) {
    			logger.error("EXCEPTION", e);
    		}
    	} else {
    		info = new GestorDocumentalGestionarInformacioCommand();
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


	/**
	 * @param oliArxiuEjb the oliArxiuEjb to set
	 */
	public void setOliArxiuEjb(OliArxiuEjb oliArxiuEjb) {
		this.oliArxiuEjb = oliArxiuEjb;
	}

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

	
}