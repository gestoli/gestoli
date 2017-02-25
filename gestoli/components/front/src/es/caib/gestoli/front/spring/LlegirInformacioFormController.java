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
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.propertyeditors.CustomBooleanEditor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.mvc.AbstractFormController;
import org.springframework.web.servlet.mvc.BaseCommandController;
import org.springframework.web.servlet.mvc.SimpleFormController;

import es.caib.gestoli.logic.interfaces.OliArxiuEjb;
import es.caib.gestoli.logic.interfaces.OliInformacioEjb;
import es.caib.gestoli.logic.model.CategoriaInformacio;
import es.caib.gestoli.logic.model.Document;
import es.caib.gestoli.logic.model.Establiment;
import es.caib.gestoli.logic.model.Informacio;
import es.caib.gestoli.logic.model.Olivicultor;



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
public class LlegirInformacioFormController extends SimpleFormController {

    private static Logger logger = Logger.getLogger(LlegirInformacioFormController.class);
    private OliInformacioEjb oliInformacioEjb;
    private OliArxiuEjb oliArxiuEjb;
    
    private String rolProductor;
    private String rolEnvasador;
    private String rolOlivicultor;
    private String establimentSessionKey;
    
	private HibernateTemplate hibernateTemplate;


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
        
    	LlegirInformacioCommand infoCmd = (LlegirInformacioCommand) command;
    	
        Map myModel = new HashMap();

    	oliInformacioEjb.setHibernateTemplate(getHibernateTemplate());
    	Informacio info = oliInformacioEjb.informacioAmbId(infoCmd.getId());
    	
        HttpSession session = request.getSession();
		oliInformacioEjb.setHibernateTemplate(getHibernateTemplate());
		if (request.isUserInRole(rolEnvasador) || request.isUserInRole(rolProductor)){
			Establiment est = (Establiment)session.getAttribute(establimentSessionKey);
			if (est != null) {
				oliInformacioEjb.setHibernateTemplate(getHibernateTemplate());
				if (!oliInformacioEjb.establecimientoHaLeido(est.getId(), info.getId())){
					oliInformacioEjb.setHibernateTemplate(getHibernateTemplate());
					oliInformacioEjb.establecimientoLeeInfo(est.getId(),info);				
				}
			}
		} else if (request.isUserInRole(rolOlivicultor)) {
			oliInformacioEjb.setHibernateTemplate(getHibernateTemplate());
			Olivicultor oli = (Olivicultor)request.getAttribute("oliv");
			if (oli != null) {
				if (!oliInformacioEjb.olivicultorHaLeido(oli.getId(),info.getId())){
					oliInformacioEjb.setHibernateTemplate(getHibernateTemplate());
					oliInformacioEjb.olivicultorLeeInfo(oli.getId(),info);				
				}
			}
		}

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
    	myModel.put("documentos", llistatDocuments);
    
    	myModel.put("path", "llegirInformacio_formpath");
        
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
    	
    	LlegirInformacioCommand info = new LlegirInformacioCommand();
    	
    	Integer codi=null;
    	if (request.getParameter("id")!=null && !request.getParameter("id").equals("")){
    		codi = new Integer(Integer.parseInt(request.getParameter("id")));
    	}
    	
    	try{
    	
	    	oliInformacioEjb.setHibernateTemplate(getHibernateTemplate());
			Informacio i = oliInformacioEjb.informacioAmbId(codi);
	    	info.fromInformacio(i);
	    	
	    	oliInformacioEjb.setHibernateTemplate(getHibernateTemplate());
	    	CategoriaInformacio catInf = oliInformacioEjb.categoriaInformacioAmbId(i.getCategoriaInformacio().getId()); 
	    	info.setNomCategoria(catInf.getNom());
	    	
    	}catch (Exception e) {
    		logger.error("Error al obtener la informacion con id "+codi.toString(), e);
		}

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

	private String missatge(String clave){
    	String valor= getMessageSourceAccessor().getMessage(clave);
    	return valor;
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


	public String getRolProductor() {
		return rolProductor;
	}


	public void setRolProductor(String rolProductor) {
		this.rolProductor = rolProductor;
	}


	public String getRolEnvasador() {
		return rolEnvasador;
	}


	public void setRolEnvasador(String rolEnvasador) {
		this.rolEnvasador = rolEnvasador;
	}


	public String getRolOlivicultor() {
		return rolOlivicultor;
	}


	public void setRolOlivicultor(String rolOlivicultor) {
		this.rolOlivicultor = rolOlivicultor;
	}


	public String getEstablimentSessionKey() {
		return establimentSessionKey;
	}


	public void setEstablimentSessionKey(String establimentSessionKey) {
		this.establimentSessionKey = establimentSessionKey;
	}


	public OliArxiuEjb getOliArxiuEjb() {
		return oliArxiuEjb;
	}


	public void setOliArxiuEjb(OliArxiuEjb oliArxiuEjb) {
		this.oliArxiuEjb = oliArxiuEjb;
	}

	
}