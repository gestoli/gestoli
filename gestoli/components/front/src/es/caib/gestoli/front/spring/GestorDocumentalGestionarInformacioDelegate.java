/**
 * GestionarInformacioDelegate.java
 *
 * Creada el 25 de març de 2009
 * &copy; at4.net 2009
 */
package es.caib.gestoli.front.spring;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
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
import es.caib.gestoli.logic.interfaces.OliQualitatEjb;
import es.caib.gestoli.logic.model.GestorDocumentalDocument;
import es.caib.gestoli.logic.model.GestorDocumentalInformacio;

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
public class GestorDocumentalGestionarInformacioDelegate implements ApplicationContextAware {
	
	private static Logger logger = Logger.getLogger(GestorDocumentalGestionarInformacioDelegate.class);
	private String listView;
    private String deleteView;
	private OliQualitatEjb oliQualitatEjb;
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
        	oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
        	Collection llistat = oliQualitatEjb.llistatInformacio();
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
        
        for (int i=0; i<ids.length; i++) {
        	String id = ids[i];
            if (id != null && !"".equals(id)) {
                try {
                    Integer lid = new Integer(Integer.parseInt(id));
                    oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
                    GestorDocumentalInformacio informacio = oliQualitatEjb.informacioAmbId(lid);
                    
                    Iterator itDocuments = informacio.getDocuments().iterator();
                    while (itDocuments.hasNext()) {
                    	GestorDocumentalDocument document = (GestorDocumentalDocument) itDocuments.next();
                    	logger.info("Eliminant el document [" + document.getId() + "]");
                    	oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
                    	oliQualitatEjb.documentEsborrar(document.getId());
                    }
                    
                    oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
                    oliQualitatEjb.informacioEsborrar(informacio);
                    logger.info("Eliminant la informació [" + lid + "]");
                } catch (Exception ex) {
                    logger.error("Error esborrant la informació [" + id + "]", ex);
                }
            }
        }

        return new ModelAndView(deleteView, myModel);
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

	public void setApplicationContext(ApplicationContext context) {
        applicationContext = context;
    }

    public void setOliQualitatEjb(OliQualitatEjb oliQualitatEjb) {
		this.oliQualitatEjb = oliQualitatEjb;
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