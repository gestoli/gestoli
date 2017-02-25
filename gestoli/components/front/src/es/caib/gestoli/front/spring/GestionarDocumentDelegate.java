/**
 * GestionarInformacioDelegate.java
 *
 * Creada el 25 de març de 2009
 * &copy; at4.net 2009
 */
package es.caib.gestoli.front.spring;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.web.servlet.ModelAndView;

import es.caib.gestoli.front.util.Idioma;
import es.caib.gestoli.logic.interfaces.OliInformacioEjb;

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
public class GestionarDocumentDelegate implements ApplicationContextAware {
	
	private static Logger logger = Logger.getLogger(GestionarDocumentDelegate.class);
	private String listView;
    private String deleteView;
    private String deletePersonalView;
	private OliInformacioEjb oliInformacioEjb;
    private ApplicationContext applicationContext;
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
        	HttpSession session = request.getSession();
        	oliInformacioEjb.setHibernateTemplate(getHibernateTemplate());
        	Collection llistat = oliInformacioEjb.llistatInformacio();
        	myModel.put("llistat", llistat);
        	myModel.put("path", "lista_informacion");
        	logger.info("Obtenint llistat de informacions: " + llistat.size() + " registres trobats");

        } catch (Exception ex) {
        	logger.error("Error obtenint llistat de informacions", ex);
        	ControllerUtils.saveMessageError(request, missatge("informacio.missatge.llistat.no", request));
        }
        return new ModelAndView(listView, myModel);
    }


    /**
     * Borra un registro y retorna el listado.
     * @see org.springframework.web.servlet.mvc.Controller#handleRequest(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public ModelAndView delete(
            HttpServletRequest request,
            HttpServletResponse response) {
    	Map myModel = new HashMap();
        String[] ids = request.getParameterValues("id");
        String idInformacio = request.getParameter("idInformacio");
        String idPersonal = request.getParameter("idPersonal");
        for (int i=0; i<ids.length; i++) {
        	String id = ids[i];
            if (id != null && !"".equals(id)) {
                try {
                    Integer lid = new Integer(Integer.parseInt(id));
                    oliInformacioEjb.setHibernateTemplate(getHibernateTemplate());
                    
                    // Se borra directamente sin consultar si tiene nada asociado.
                    logger.info("Eliminant el document [" + lid + "]");
                    oliInformacioEjb.setHibernateTemplate(getHibernateTemplate());
                    oliInformacioEjb.documentEsborrar(lid);
                } catch (Exception ex) {
                    logger.error("Error esborrant el document [" + id + "]", ex);
                }
            }
        }
        String forward; 
        if (idInformacio != null){
        	forward = deleteView+"?id="+idInformacio;
        } else {
        	forward = deletePersonalView+"?codi="+idPersonal;
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
     * Inyección de la dependencia deleteView
     * @param deleteView la cadena a inyectar.
     */
    public void setDeleteView(String deleteView) {
        this.deleteView = deleteView;
    }

    /**
     * Inyección de la dependencia deletePersonalView
     * @param deletePersonalView la cadena a inyectar.
     */
    public void setDeletePersonalView(String deletePersonalView) {
        this.deletePersonalView = deletePersonalView;
    }

    /**
     * Inyección de la dependencia oliInformacioEjb
     * @param oliInformacioEjb La clase a inyectar.
     */
    public void setOliInformacioEjb(OliInformacioEjb oliInformacioEjb) {
        this.oliInformacioEjb = oliInformacioEjb;
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