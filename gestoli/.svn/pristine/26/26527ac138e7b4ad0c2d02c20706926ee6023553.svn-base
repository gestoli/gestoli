package es.caib.gestoli.front.spring.qualitat;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.web.servlet.ModelAndView;

import es.caib.gestoli.logic.interfaces.OliQualitatEjb;

public class QualitatAiguaPuntSortidaDelegate implements ApplicationContextAware {
	
	private static Logger logger = Logger.getLogger(QualitatAiguaPuntSortidaDelegate.class);
    private String deleteView;
	private OliQualitatEjb oliQualitatEjb;
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
        boolean exito = true;
        for (int i=0; i<ids.length; i++) {
        	String id = ids[i];
            if (id != null && !"".equals(id)) {
                try {
                	Long lid = new Long(Long.parseLong(id));
                    
                	logger.info("Eliminant l'Etapa [" + id + "]");
                	oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
                    oliQualitatEjb.aiguaPuntSortidaEsborrar(Long.valueOf(id));

                } catch (Exception ex) {
                    logger.error("Error esborrant l'etapa [" + id + "]", ex);
                    exito = false;
                }
            }
        }

        String forward = deleteView;
        return new ModelAndView(forward, myModel);
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
    public void setOliQualitatEjb(OliQualitatEjb oliQualitatEjb) {
        this.oliQualitatEjb = oliQualitatEjb;
    }

    public void setApplicationContext(ApplicationContext context) {
        applicationContext = context;
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
