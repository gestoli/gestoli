/**
 * GestionarInformacioDelegate.java
 *
 * Creada el 25 de març de 2009
 * &copy; at4.net 2009
 */
package es.caib.gestoli.front.spring;

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
 * 
 */
public class DocumentInspeccioDelegate implements ApplicationContextAware {
	
	private static Logger logger = Logger.getLogger(DocumentInspeccioDelegate.class);
	private String listView;
    private String deleteOlivicultorView;
    private String deleteEstablimentView;
	private OliInfraestructuraEjb oliInfraestructuraEjb;
    private ApplicationContext applicationContext;
	private HibernateTemplate hibernateTemplate;

    /**
     * Borra un registro y retorna el listado.
     * @see org.springframework.web.servlet.mvc.Controller#handleRequest(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public ModelAndView delete(
            HttpServletRequest request,
            HttpServletResponse response) {
    	Map myModel = new HashMap();
        String[] ids = request.getParameterValues("id");
        String idEstabliment = request.getParameter("idEstabliment");
        String idOlivicultor = request.getParameter("idOlivicultor");
        for (int i=0; i<ids.length; i++) {
        	String id = ids[i];
            if (id != null && !"".equals(id)) {
                try {
                    Long lid = new Long(Long.valueOf(id));
                    
                    // Se borra directamente sin consultar si tiene nada asociado.
                    logger.info("Eliminant el documentInspeccio [" + lid + "]");
                    oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
                    oliInfraestructuraEjb.documentInspeccioEsborrar(lid);
                } catch (Exception ex) {
                    logger.error("Error esborrant el documentInspeccio [" + id + "]", ex);
                }
            }
        }
        String forward; 
        if (idEstabliment != null){
        	forward = deleteEstablimentView+"?id="+idEstabliment;
        } else {
        	forward = deleteOlivicultorView+"?id="+idOlivicultor;
        }
        return new ModelAndView(forward, myModel);
    }

    /**
     * Inyección de la dependencia listView
     * @param listView la cadena a inyectar.
     */
    public void setListView(String listView) {
        this.listView = listView;
    }

    /**
     * Inyección de la dependencia deleteOlivicultorView
     * @param deleteOlivicultorView la cadena a inyectar.
     */
    public void setDeleteOlivicultorView(String deleteOlivicultorView) {
        this.deleteOlivicultorView = deleteOlivicultorView;
    }

    /**
     * Inyección de la dependencia deleteEstablimentView
     * @param deleteEstablimentView la cadena a inyectar.
     */
    public void setDeleteEstablimentView(String deleteEstablimentView) {
        this.deleteEstablimentView = deleteEstablimentView;
    }

    /**
     * Inyección de la dependencia oliInfraestructuraEjb
     * @param oliInfraestructuraEjb La clase a inyectar.
     */
    public void setOliInfraestructuraEjb(OliInfraestructuraEjb oliInfraestructuraEjb) {
        this.oliInfraestructuraEjb = oliInfraestructuraEjb;
    }

	public void setApplicationContext(ApplicationContext context) {
        applicationContext = context;
    }

    private String missatge(String clave, HttpServletRequest request) {
    	String valor = applicationContext.getMessage(clave, null, Idioma.getLocale(request));
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

	
}