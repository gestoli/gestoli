package es.caib.gestoli.front.spring;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.mvc.AbstractFormController;
import org.springframework.web.servlet.mvc.BaseCommandController;
import org.springframework.web.servlet.mvc.SimpleFormController;

import es.caib.gestoli.front.util.Idioma;
import es.caib.gestoli.logic.interfaces.OliInformacioEjb;
import es.caib.gestoli.logic.model.Establiment;
import es.caib.gestoli.logic.model.Informacio;
import es.caib.gestoli.logic.model.Olivicultor;

/**
 * <p>Controlador para las acciones de dar de alta o editar un
 * registro de tipos de información.</p>
 * <p>Los parámetros de la petición HTTP relevantes para este
 * controlador son:
 * <ul>
 *   <li><code>id</code>
 *    - Identificador del registro. Este parámetro es el que
 *      determina si se ha de mostrar la página de creación o la
 *      página de edición.</li>
 * </ul></p>
 * <p>No hay información para la vista:
 * <p>Este controlador distingue entre las peticiones del tipo
 * GET y las de tipo POST. Si la petición es de tipo POST
 * se ejecuta la acción de creación o de edición. Si la petición es de
 * tipo GET solo se muestra la página.
 *
 */
public class LlegirInformacioLlistatController extends SimpleFormController {

	private static Logger logger = Logger.getLogger(LlegirInformacioLlistatController.class);

	OliInformacioEjb oliInformacioEjb;
	
    private String rolProductor;
    private String rolEnvasador;
    private String rolOlivicultor;
    private String establimentSessionKey;

	private HibernateTemplate hibernateTemplate;
    
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

        try {
        	
            Collection llistat=null;
            
	    	oliInformacioEjb.setHibernateTemplate(getHibernateTemplate());
	    	Collection categorias = oliInformacioEjb.categoriaInformacioFindAll();
	    	myModel.put("categorias", categorias);
	    	
	    	oliInformacioEjb.setHibernateTemplate(getHibernateTemplate());
	    	if (request.getParameter("idCat")!=null && !request.getParameter("idCat").equals("")){
	    		Integer filtre = new Integer(request.getParameter("idCat"));
	    		llistat = oliInformacioEjb.llistatInformacioFiltre(filtre);
	    	}else{
	    		llistat = oliInformacioEjb.llistatInformacio();
	    	}
	    	
	    	Collection llistatExtentido = new ArrayList(llistat.size());
	    	Iterator iteLlistat = llistat.iterator();
	    	HttpSession session = request.getSession();
	    	while (iteLlistat.hasNext()){
	    		
	    		Informacio inf = (Informacio)iteLlistat.next();
	    		LlegirInformacioCommand llCmd = new LlegirInformacioCommand();
	    		llCmd.fromInformacio(inf);
	    		
	    		oliInformacioEjb.setHibernateTemplate(getHibernateTemplate());
	    		if (request.isUserInRole(rolEnvasador) || request.isUserInRole(rolProductor)){
	    			Establiment est = (Establiment)session.getAttribute(establimentSessionKey);
	    			if (est != null) {
	    				llCmd.setLlegit(new Boolean(oliInformacioEjb.establecimientoHaLeido(est.getId(),llCmd.getId())));
	    			} else {
	    				llCmd.setLlegit(Boolean.FALSE);
	    			}
	    		}else if (request.isUserInRole(rolOlivicultor)){
	    			Olivicultor oli = (Olivicultor)request.getAttribute("oliv");
	    			if (oli != null) {
	    				llCmd.setLlegit(new Boolean(oliInformacioEjb.olivicultorHaLeido(oli.getId(),llCmd.getId())));
	    			} else {
	    				llCmd.setLlegit(Boolean.FALSE);
	    			}
	    		}
	    		
	    		llistatExtentido.add(llCmd);
	    	}
	   		
	    	myModel.put("llistat", llistatExtentido);
	    	myModel.put("path", "lista_llegirInformacio");
	        logger.info("Obtenint llistat de informacio: " + llistat.size() + " registres trobats");
                

        }catch (Exception e) {
            logger.error("Error obtenint llistat de informacio", e);
            ControllerUtils.saveMessageError(request, missatge("llegirInformacio.missatge.llistat.no", request));
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
    	
    	LlegirInformacioCommand info = new LlegirInformacioCommand();
    	
    	if (request.getParameter("idCat")!=null && !request.getParameter("idCat").equals("")){
    		Integer filtre = new Integer(request.getParameter("idCat"));
    		info.setIdCategoria(filtre);
    	}
    	
    	return info;

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
    	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    	CustomDateEditor dateEditor = new CustomDateEditor(sdf, true);
    	binder.registerCustomEditor(java.util.Date.class, dateEditor);
    }

    

    
	private String missatge(String clave, HttpServletRequest request) {
		String valor = getApplicationContext().getMessage(clave, null, Idioma.getLocale(request));
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

	public OliInformacioEjb getOliInformacioEjb() {
		return oliInformacioEjb;
	}

	public void setOliInformacioEjb(OliInformacioEjb oliInformacioEjb) {
		this.oliInformacioEjb = oliInformacioEjb;
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

	
}
