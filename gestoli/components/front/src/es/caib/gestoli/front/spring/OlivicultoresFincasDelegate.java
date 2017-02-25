/**
 * OlivicultoresFincasDelegate.java
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
public class OlivicultoresFincasDelegate implements ApplicationContextAware {
	private static Logger logger = Logger.getLogger(OlivicultorDelegate.class);

	private String listView;
    private String deleteView;
    private String rolDoGestor;
//	private String rolOrgan;
//	private String rolOlivicultor;
	private OliInfraestructuraEjb oliInfraestructuraEjb;
    private ApplicationContext applicationContext;
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
        
        HttpSession session = request.getSession();
        try {
        	Long campanyaId = (Long)session.getAttribute(campanyaSessionKey);
        	oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
        	Collection llistat = oliInfraestructuraEjb.findAllOlivicultorsByCampanyaOrderedByCodiDODesc(campanyaId);
        	myModel.put("llistat", llistat);
        	myModel.put("path", "lista_olivicultoresfincas");
            logger.info("Obtenint llistat de olivicultores: " + llistat.size() + " registres trobats");
        } catch (Exception ex) {
            logger.error("Error obtenint llistat de olivicultores", ex);
            ControllerUtils.saveMessageError(request, missatge("olivicultor.missatge.llistat.no", request));
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
//        String[] ids = request.getParameterValues("id");
//        boolean exito = true;
//        for (int i=0; i<ids.length; i++) {
//        	String id = ids[i];
//            if (id != null && !"".equals(id)) {
//                
//            	try {
//                    Long lid = new Long(Long.parseLong(id));
//                    logger.info("Eliminant el olivicultor [" + lid + "]");
//                    oliInfraestructuraEjb.olivicultorEsborrar(lid);
//                } catch (Exception ex) {
//                    logger.error("Error esborrant olivicultor [" + id + "]", ex);
//                    exito = false;
//                }
//                
//            }
//        }
//
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


    /**
     * Inyección de la dependencia oliInfraestructuraEjb
     * @param oliInfraestructuraEjb La clase a inyectar.
     */
   
    public void setOliInfraestructuraEjb(OliInfraestructuraEjb oliInfraestructuraEjb) {
        this.oliInfraestructuraEjb = oliInfraestructuraEjb;
    }


//    /**
//     * Injecció de la dependència rolAdmin
//     * @param rolAdmin La classe a injectar.
//     */
//    public void setRolAdmin(String rolAdmin) {
//        this.rolAdmin = rolAdmin;
//    }
//
//    /**
//     * Injecció de la dependència rolAdmin
//     * @param rolAdmin La classe a injectar.
//     */
//    public void setRolOlivicultor(String rolOlivicultor) {
//        this.rolOlivicultor = rolOlivicultor;
//    }
//
//    
//
//    /**
//     * Injecció de la dependència rolOrgan
//     * @param rolOrgan La classe a injectar.
//     */
//    public void setRolOrgan(String rolOrgan) {
//        this.rolOrgan = rolOrgan;
//    }


	/**
	 * Injecció de la dependència rolDoGestor
	 * @param rolDoGestor La classe a injectar.
	 */
	public void setRolDoGestor(String rolDoGestor) {
		this.rolDoGestor = rolDoGestor;
	}
    

    public void setApplicationContext(ApplicationContext context) {
        applicationContext = context;
    }
    
    /**
	 * Injecció de la dependència usuariSessionKey
	 *
	 * @param campanyaSessionKey La classe a injectar.
	 */
	public void setCampanyaSessionKey(String campanyaSessionKey) {
		this.campanyaSessionKey = campanyaSessionKey;
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