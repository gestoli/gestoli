package es.caib.gestoli.front.spring;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

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
import es.caib.gestoli.logic.model.Avis;
import es.caib.gestoli.logic.model.Establiment;

public class AvisDelegate implements ApplicationContextAware {
	
	private static Logger logger = Logger.getLogger(AvisDelegate.class);
	private String listView;
    private String deleteView;
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
        	HttpSession session = request.getSession();
    		Establiment est = (Establiment)session.getAttribute(establimentSessionKey);
	   		Long idEstablecimiento =  est.getId();
	   		oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
        	
        	String lang = Idioma.getLang(request);
			ResourceBundle bundle = ResourceBundle.getBundle("messages",new java.util.Locale(lang));
        	Collection llistat = oliInfraestructuraEjb.avisCercaTotsPerEstabliment(idEstablecimiento);
        	for (Object avis: llistat){
        		if ("1".equals(((Avis)avis).getFrecuencia()))
        			((Avis)avis).setFrecuencia(bundle.getString("qualitat.frecuencia.diari"));
        		else if ("7".equals(((Avis)avis).getFrecuencia()))
        			((Avis)avis).setFrecuencia(bundle.getString("qualitat.frecuencia.setmanal"));
        		else if ("15".equals(((Avis)avis).getFrecuencia()))
        			((Avis)avis).setFrecuencia(bundle.getString("qualitat.frecuencia.quinzenal"));
        		else if ("30".equals(((Avis)avis).getFrecuencia()))
        			((Avis)avis).setFrecuencia(bundle.getString("qualitat.frecuencia.mensual"));
        		else if ("60".equals(((Avis)avis).getFrecuencia()))
        			((Avis)avis).setFrecuencia(bundle.getString("qualitat.frecuencia.bimensual"));
        		else if ("90".equals(((Avis)avis).getFrecuencia()))
        			((Avis)avis).setFrecuencia(bundle.getString("qualitat.frecuencia.trimestral"));
        		else if ("180".equals(((Avis)avis).getFrecuencia()))
        			((Avis)avis).setFrecuencia(bundle.getString("qualitat.frecuencia.semestral"));
        		else if ("365".equals(((Avis)avis).getFrecuencia()))
        			((Avis)avis).setFrecuencia(bundle.getString("qualitat.frecuencia.anual"));
        		else if ("730".equals(((Avis)avis).getFrecuencia()))
        			((Avis)avis).setFrecuencia(bundle.getString("qualitat.frecuencia.bianual"));
        		else if ("1095".equals(((Avis)avis).getFrecuencia()))
        			((Avis)avis).setFrecuencia(bundle.getString("qualitat.frecuencia.trianual"));

        		((Avis)avis).setTipus(bundle.getString("avis.tipus."+((Avis)avis).getTipus()).toString());
        	
        	}
        	
        	myModel.put("idEstablecimiento",idEstablecimiento);
        	myModel.put("llistat", llistat);
        	myModel.put("path", "avis");
            logger.info("Obtenint llistat d'avisos: " + llistat.size() + " registres trobats");

        } catch (Exception ex) {
            logger.error("Error obtenint llistat d'avisos", ex);
            ControllerUtils.saveMessageError(request, missatge("avis.missatge.llistat.no", request));
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
        boolean exito = true;
        for (int i=0; i<ids.length; i++) {
        	String id = ids[i];
            if (id != null && !"".equals(id)) {
                try {
                	Long lid = new Long(Long.parseLong(id));
                	oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
                    
                	logger.info("Eliminant l'avis [" + id + "]");
                	oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
                    oliInfraestructuraEjb.avisEsborrar(Long.valueOf(id));

                } catch (Exception ex) {
                    logger.error("Error esborrant l'avis [" + id + "]", ex);
                    exito = false;
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
	 * Injecció de la dependència establimentSessionKey
	 *
	 * @param establimentSessionKey La classe a injectar.
	 */
	public void setEstablimentSessionKey(String establimentSessionKey) {
		this.establimentSessionKey = establimentSessionKey;
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
