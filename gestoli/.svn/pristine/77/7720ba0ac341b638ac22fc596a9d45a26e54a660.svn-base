/**
 * ConsultaOliElaboratDelegate.java
 */
package es.caib.gestoli.front.spring;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.web.servlet.ModelAndView;

import es.caib.gestoli.front.util.Idioma;
import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;

/**
 * <p>Clase delegada del MultiActionController que trata
 * distintas acciones. La acción a executar está basada en el
 * contenido de un parámetro de la petición.</p>
 * <p>Los parámetros de la petición HTTP relevantes para esta
 * clase son:
 * <ul>
 *   <li><code>action</code>
 *    - determina la acción a executar.</li>
 *   <li><code>id</code>
 *    - Identificador del registro cuando la acción a ejecutar es
 *      "delete".</li>
 * </ul></p>
 * 
 */
public class CercaTrazabilitatDelegate implements ApplicationContextAware {
	private static Logger logger = Logger.getLogger(CercaTrazabilitatDelegate.class);

	private String listView;
    private OliInfraestructuraEjb oliInfraestructuraEjb;
    private ApplicationContext applicationContext;
    private String rolDoGestor;
	private String rolAdministracio;
	private String rolDoControl;
	private String campanyaSessionKey;
	private HibernateTemplate hibernateTemplate;

    /**
     * Retorna una lista con el contenido de la tabla.
     * 
     * @see org.springframework.web.servlet.mvc.Controller#handleRequest(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public ModelAndView list(
            HttpServletRequest request,
            HttpServletResponse response) {
        Map myModel = new HashMap();
        
        try {
        	Collection llistat = new ArrayList();        
        	Long campanyaId = (Long)request.getSession().getAttribute(campanyaSessionKey);
        	if (request.isUserInRole(rolDoGestor) || request.isUserInRole(rolAdministracio) || request.isUserInRole(rolDoControl)) {
        		oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
        		llistat = oliInfraestructuraEjb.establimentCercaTotsActivos(campanyaId);
        	}        	
        	myModel.put("llistat", llistat);        	
        	myModel.put("path", "lista_establecimientos_cerca_trazabilitat");
            logger.info("Obtenint llistat de establiments: " + llistat.size() + " registres trobats");
        } catch (Exception ex) {
            logger.error("Error obtenint llistat de establiments", ex);
            ControllerUtils.saveMessageError(request, missatge("establiment.missatge.llistat.no", request));
        }
        
        return new ModelAndView(listView, myModel);
    }
   
    /* Inyeccions de dependència */
    public void setListView(String listView) {
        this.listView = listView;
    }

    public void setOliInfraestructuraEjb(OliInfraestructuraEjb oliInfraestructuraEjb) {
        this.oliInfraestructuraEjb = oliInfraestructuraEjb;
    }

	public void setRolDoGestor(String rolDoGestor) {
		this.rolDoGestor = rolDoGestor;
	}
	
	public void setRolDoControl(String rolDoControl) {
		this.rolDoControl = rolDoControl;
	}
	
	public void setRolAdministracio(String rolAdministracio) {
		this.rolAdministracio = rolAdministracio;
	}
	

    public void setApplicationContext(ApplicationContext context) {
        applicationContext = context;
    }
    
	public void setCampanyaSessionKey(String campanyaSessionKey) {
		this.campanyaSessionKey = campanyaSessionKey;
	}
	
	private String missatge(String clave, HttpServletRequest request) {
    	String valor = applicationContext.getMessage(clave, null, Idioma.getLocale(request));
    	return valor;
    }

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate){
		this.hibernateTemplate = hibernateTemplate;
	}
	
	public HibernateTemplate getHibernateTemplate(){
		return this.hibernateTemplate;
	}
}

