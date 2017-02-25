/**
 * TancamentLlibresDelegate.java
 */
package es.caib.gestoli.front.spring;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.web.servlet.ModelAndView;

import es.caib.gestoli.front.util.Idioma;
import es.caib.gestoli.logic.ejb.OliProcessosEjbBean;
import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;
import es.caib.gestoli.logic.interfaces.OliProcessosEjb;
import es.caib.gestoli.logic.model.Establiment;

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
public class TancamentLlibresDelegate implements ApplicationContextAware {
	private static Logger logger = Logger.getLogger(TancamentLlibresDelegate.class);

	private String listView;
    private OliProcessosEjb oliProcessosEjb;
    private OliInfraestructuraEjb oliInfraestructuraEjb;
    private ApplicationContext applicationContext;
    private String establimentSessionKey;

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
        	Establiment establiment = (Establiment) request.getSession().getAttribute(establimentSessionKey);
        	
        	Collection diposits = new ArrayList();
        	Collection lots = new ArrayList();
        	
        	oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
//        	diposits = oliInfraestructuraEjb.dipositCercaTotsBuits(establiment.getId());
        	diposits = oliInfraestructuraEjb.dipositCercaTotsNoFicticisPerEstabliment(establiment.getId());
        	
        	oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
//        	lots = oliInfraestructuraEjb.lotCercaTotsBuits(establiment.getId());
        	lots = oliInfraestructuraEjb.lotCercaTotsPerEstabliment(establiment.getId());
        	
        	myModel.put("llistatDepositos", diposits);
        	myModel.put("llistatLotes", lots);
        	myModel.put("path", "tancament_llibres");
        	logger.info("Obtenint llistat de diposits: " + diposits.size() + " registres trobats");
        	logger.info("Obtenint llistat de lots: " + lots.size() + " registres trobats");
        } catch (Exception ex) {
            logger.error("Error obtenint llistats de tancament de llibres", ex);
            ControllerUtils.saveMessageError(request, missatge("establiment.missatge.llistat.no", request));
        }
        
        return new ModelAndView(listView, myModel);
    }


   
    /**
     * Inyección de la dependencia listView
     * @param listView la cadena a inyectar.
     */
    public void setListView(String listView) {
        this.listView = listView;
    }

    public void setApplicationContext(ApplicationContext context) {
        applicationContext = context;
    }

	private String missatge(String clave, HttpServletRequest request) {
    	String valor = applicationContext.getMessage(clave, null, Idioma.getLocale(request));
    	return valor;
    }



	public void setOliProcessosEjb(OliProcessosEjb oliProcessosEjb) {
		this.oliProcessosEjb = oliProcessosEjb;
	}



	public void setEstablimentSessionKey(String establimentSessionKey) {
		this.establimentSessionKey = establimentSessionKey;
	}



	public void setOliInfraestructuraEjb(OliInfraestructuraEjb oliInfraestructuraEjb) {
		this.oliInfraestructuraEjb = oliInfraestructuraEjb;
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

