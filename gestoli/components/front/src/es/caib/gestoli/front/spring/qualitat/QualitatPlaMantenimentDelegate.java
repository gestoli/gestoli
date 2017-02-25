package es.caib.gestoli.front.spring.qualitat;

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

import es.caib.gestoli.front.spring.ControllerUtils;
import es.caib.gestoli.front.util.Idioma;
import es.caib.gestoli.logic.interfaces.OliQualitatEjb;
import es.caib.gestoli.logic.model.Establiment;
import es.caib.gestoli.logic.model.QualitatControlPlagues;
import es.caib.gestoli.logic.model.QualitatPlaManteniment;
import es.caib.gestoli.logic.model.QualitatPlaMantenimentControl;

public class QualitatPlaMantenimentDelegate  implements ApplicationContextAware {
	
	private static Logger logger = Logger.getLogger(QualitatPlaMantenimentDelegate.class);
	private String listView;
    private String deleteView;
	private OliQualitatEjb oliQualitatEjb;
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
	   		oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
	   		String lang = Idioma.getLang(request);
			
	   		ResourceBundle bundle = ResourceBundle.getBundle("messages",new java.util.Locale(lang));
	   		Collection llistat = oliQualitatEjb.mantenimentCercaTotsPerEstabliment(idEstablecimiento);
        	/*for (Object mant: llistat){
        		if ("dia".equals(((QualitatPlaManteniment)mant).getFrecuencia()))
        			((QualitatPlaManteniment)mant).setFrecuencia(bundle.getString("qualitat.frecuencia.diari"));
        		else if ("sem".equals(((QualitatPlaManteniment)mant).getFrecuencia()))
        			((QualitatPlaManteniment)mant).setFrecuencia(bundle.getString("qualitat.frecuencia.setmanal"));
        		else if ("qui".equals(((QualitatPlaManteniment)mant).getFrecuencia()))
        			((QualitatPlaManteniment)mant).setFrecuencia(bundle.getString("qualitat.frecuencia.quinzenal"));
        		else if ("men".equals(((QualitatPlaManteniment)mant).getFrecuencia()))
        			((QualitatPlaManteniment)mant).setFrecuencia(bundle.getString("qualitat.frecuencia.mensual"));
        		else if ("tri".equals(((QualitatPlaManteniment)mant).getFrecuencia()))
        			((QualitatPlaManteniment)mant).setFrecuencia(bundle.getString("qualitat.frecuencia.trimestral"));
        		else if ("seme".equals(((QualitatPlaManteniment)mant).getFrecuencia()))
        			((QualitatPlaManteniment)mant).setFrecuencia(bundle.getString("qualitat.frecuencia.semestral"));
        		else if ("anu".equals(((QualitatPlaManteniment)mant).getFrecuencia()))
        			((QualitatPlaManteniment)mant).setFrecuencia(bundle.getString("qualitat.frecuencia.anual"));
        		else if ("bia".equals(((QualitatPlaManteniment)mant).getFrecuencia()))
        			((QualitatPlaManteniment)mant).setFrecuencia(bundle.getString("qualitat.frecuencia.bianual"));
        		else if ("tria".equals(((QualitatPlaManteniment)mant).getFrecuencia()))
        			((QualitatPlaManteniment)mant).setFrecuencia(bundle.getString("qualitat.frecuencia.trianual"));
        	}*/
	   		for (Object mant: llistat){
        		if (((QualitatPlaManteniment)mant).getFrecuencia() != null){
        			((QualitatPlaManteniment)mant).setFrecuencia(bundle.getString("qualitat.frecuencia." + ((QualitatPlaManteniment)mant).getFrecuencia()));
        		}
        	}
        	
        	oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
        	myModel.put("idEstablecimiento",idEstablecimiento);
        	myModel.put("llistat", llistat);
        	myModel.put("path", "qualitat_PlaManteniment");
            logger.info("Obtenint llistat de PlaManteniment: " + llistat.size() + " registres trobats");

        } catch (Exception ex) {
            logger.error("Error obtenint llistat de PlaManteniment", ex);
            ControllerUtils.saveMessageError(request, missatge("qualitat.PlaManteniment.missatge.llistat.no", request));
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
                	oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
                    
                	logger.info("Eliminant el PlaManteniment [" + id + "]");
                	oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
                    oliQualitatEjb.mantenimentEsborrar(Long.valueOf(id));

                } catch (Exception ex) {
                    logger.error("Error esborrant el PlaManteniment [" + id + "]", ex);
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
   public void setOliQualitatEjb(OliQualitatEjb oliQualitatEjb) {
       this.oliQualitatEjb = oliQualitatEjb;
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
