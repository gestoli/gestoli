package es.caib.gestoli.front.spring;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.mvc.BaseCommandController;
import org.springframework.web.servlet.mvc.SimpleFormController;

import es.caib.gestoli.front.util.Idioma;
import es.caib.gestoli.logic.interfaces.OliConsultaEjb;

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
public class ConsultaAnaliticasSimplificatController extends SimpleFormController {
	private static Logger logger = Logger.getLogger(ConsultaAnaliticasSimplificatController.class);
	private OliConsultaEjb oliConsultaEjb;
    private String rolDoControl;
	private HibernateTemplate hibernateTemplate;
	private String esAdministracioRequestKey;
	private String esDoGestorRequestKey;

	
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
        HttpSession session = request.getSession();

        try {
        	ConsultaAnaliticasSimplificatCommand consulta = (ConsultaAnaliticasSimplificatCommand) command;
        	
        	Long idEstabliment = null;
    		if (request.getAttribute(esDoGestorRequestKey) != null || request.getAttribute(esAdministracioRequestKey) != null || request.isUserInRole(rolDoControl)) {
    			if(request.getParameter("idEstabliment")!= null && !request.getParameter("idEstabliment").equals("") ){
        			idEstabliment = Long.valueOf(request.getParameter("idEstabliment"));
        		}
    		}
    		   	
    		// Cerca totes las analítiques d'un establiment
    		oliConsultaEjb.setHibernateTemplate(getHibernateTemplate());
    		Collection lista = oliConsultaEjb.findAnaliticasByEstabliment(idEstabliment);
		        
    		if(!errors.hasErrors()){
    			myModel.put("llistat", lista);
    		}
     
        	consulta.setIdEstabliment(idEstabliment);

    		myModel.put("path", "consultar_analitica");
        	
        } catch (Exception ex) {
        	logger.error("Error obtenint llistat de oli disponible", ex);
        }
        return myModel;
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

    
    /**
     * Injecció de la dependència consultaEjb
     *
     * @param consultaEjb La classe a injectar.
     */
  
    public void setOliConsultaEjb(OliConsultaEjb oliConsultaEjb) {
        this.oliConsultaEjb = oliConsultaEjb;
    }
	
    
	private String missatge(String clave, HttpServletRequest request) {
		String valor = getApplicationContext().getMessage(clave, null, Idioma.getLocale(request));
		return valor;
	}
	
	/**
	 * Injecció de la dependència esAdministracioRequestKey
	 * @param esAdministracioRequestKey La classe a injectar.
	 */
	public void setEsAdministracioRequestKey(String esAdministracioRequestKey) {
		this.esAdministracioRequestKey = esAdministracioRequestKey;
	}


	/**
	 * Injecció de la dependència esDoGestorRequestKey
	 * @param esDoGestorRequestKey La classe a injectar.
	 */
	public void setEsDoGestorRequestKey(String esDoGestorRequestKey) {
		this.esDoGestorRequestKey = esDoGestorRequestKey;
	}

	/**
	 * @param rolDoControl the rolDoControl to set
	 */
	public void setRolDoControl(String rolDoControl) {
		this.rolDoControl = rolDoControl;
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