/**
 * MarcaDelegate.java
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
import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;
import es.caib.gestoli.logic.model.AnaliticaParametre;

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
 * @author Carlos Pérez (cperez@at4.net)
 */
public class AnaliticaParametreTipusDelegate implements ApplicationContextAware {
	private static Logger logger = Logger.getLogger(AnaliticaParametreTipusDelegate.class);

	private String listView;
	private String deleteView;
	private OliInfraestructuraEjb oliInfraestructuraEjb;
	private ApplicationContext applicationContext;

	private HibernateTemplate hibernateTemplate;
	


	/**
	 * Retorna una lista con el contenido de la tabla.
	 * @see org.springframework.web.servlet.mvc.Controller#handleRequest(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ModelAndView list(
			HttpServletRequest request,
			HttpServletResponse response) {
		Map myModel = new HashMap();
		HttpSession session = request.getSession();
		try {
			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
			Collection llistat = oliInfraestructuraEjb.analiticaParametreTipusLlistat();
			myModel.put("llistat", llistat);
			myModel.put("path", "lista_analiticasEstabliment");
//			myModel.put("path_extension1", nombreDiposit);
			       	
			logger.info("Obtenint llistat de analiticas: " + llistat.size() + " registres trobats");								
			
		} catch (Exception ex) {
			logger.error("Error obtenint llistat de analiticas", ex);
			ControllerUtils.saveMessageError(request, missatge("analitica.missatge.llistat.no", request));
		}
		return new ModelAndView(listView, myModel);
	}


    public ModelAndView delete(
            HttpServletRequest request,
            HttpServletResponse response) {
    	Map myModel = new HashMap();
        String id = request.getParameter("id");
        if (id != null && !"".equals(id)) {
            try {
            	Long lid = new Long(Long.parseLong(id));
            	
            	//Esborram primer els valors associats
            	oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
            	Collection parametres = oliInfraestructuraEjb.analiticaParametreByParametreTipus(lid);
            	
            	for(Iterator it=parametres.iterator(); it.hasNext();){
            		AnaliticaParametre ap = (AnaliticaParametre)it.next();
            		oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
                    oliInfraestructuraEjb.analiticaParametreEsborrar(ap.getId());
            	}
            	
            	//Esborram el tipus de paràmetre d'analítica
            	oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
                oliInfraestructuraEjb.analiticaParametreTipusEsborrar(lid);
            } catch (Exception ex) {
                logger.error("Error esborrant el paràmetre de l'analítica [" + id + "]", ex);
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