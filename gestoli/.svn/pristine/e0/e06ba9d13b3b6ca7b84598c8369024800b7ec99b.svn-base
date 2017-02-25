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

public class QualitatPlaMantenimentControlDelegate  implements ApplicationContextAware {
	
	private static Logger logger = Logger.getLogger(QualitatPlaMantenimentControlDelegate.class);
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
	   		Long idManteniment = new Long(Long.parseLong(request.getParameter("idManteniment")));
	   		oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
	   		
	   		String lang = Idioma.getLang(request);
			ResourceBundle bundle = ResourceBundle.getBundle("messages",new java.util.Locale(lang));
	   		Collection llistat = oliQualitatEjb.mantenimentControlCercaTotsPerManteniment(idManteniment);
        	/*for (Object mant: llistat){
        		if ("dia".equals(((QualitatPlaMantenimentControl)mant).getManteniment().getFrecuencia()))
        			((QualitatPlaMantenimentControl)mant).getManteniment().setFrecuencia(bundle.getString("qualitat.frecuencia.diari"));
        		else if ("sem".equals(((QualitatPlaMantenimentControl)mant).getManteniment().getFrecuencia()))
        			((QualitatPlaMantenimentControl)mant).getManteniment().setFrecuencia(bundle.getString("qualitat.frecuencia.setmanal"));
        		else if ("qui".equals(((QualitatPlaMantenimentControl)mant).getManteniment().getFrecuencia()))
        			((QualitatPlaMantenimentControl)mant).getManteniment().setFrecuencia(bundle.getString("qualitat.frecuencia.quinzenal"));
        		else if ("men".equals(((QualitatPlaMantenimentControl)mant).getManteniment().getFrecuencia()))
        			((QualitatPlaMantenimentControl)mant).getManteniment().setFrecuencia(bundle.getString("qualitat.frecuencia.mensual"));
        		else if ("tri".equals(((QualitatPlaMantenimentControl)mant).getManteniment().getFrecuencia()))
        			((QualitatPlaMantenimentControl)mant).getManteniment().setFrecuencia(bundle.getString("qualitat.frecuencia.trimestral"));
        		else if ("seme".equals(((QualitatPlaMantenimentControl)mant).getManteniment().getFrecuencia()))
        			((QualitatPlaMantenimentControl)mant).getManteniment().setFrecuencia(bundle.getString("qualitat.frecuencia.semestral"));
        		else if ("anu".equals(((QualitatPlaMantenimentControl)mant).getManteniment().getFrecuencia()))
        			((QualitatPlaMantenimentControl)mant).getManteniment().setFrecuencia(bundle.getString("qualitat.frecuencia.anual"));
        		else if ("bia".equals(((QualitatPlaMantenimentControl)mant).getManteniment().getFrecuencia()))
        			((QualitatPlaMantenimentControl)mant).getManteniment().setFrecuencia(bundle.getString("qualitat.frecuencia.bianual"));
        		else if ("tria".equals(((QualitatPlaMantenimentControl)mant).getManteniment().getFrecuencia()))
        			((QualitatPlaMantenimentControl)mant).getManteniment().setFrecuencia(bundle.getString("qualitat.frecuencia.trianual"));
        	}*/
/*	   		
	   		if (llistat.size() > 0){
	   			((QualitatPlaMantenimentControl)llistat.iterator().next()).getManteniment().setFrecuencia(bundle.getString("qualitat.frecuencia." + ((QualitatPlaMantenimentControl)mant).getManteniment().getFrecuencia()));
	   		
		   		for (Object mant: llistat){
	        		if (((QualitatPlaMantenimentControl)mant).getManteniment().getFrecuencia() != null){
	        			((QualitatPlaMantenimentControl)mant).getManteniment().setFrecuencia(bundle.getString("qualitat.frecuencia." + ((QualitatPlaMantenimentControl)mant).getManteniment().getFrecuencia()));
	        		}
	        	}
	   		
	   		}*/
        	
        	
        	oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
        	myModel.put("idEstablecimiento",idEstablecimiento);
        	myModel.put("idManteniment", idManteniment);
        	myModel.put("llistat", llistat);
        	myModel.put("path", "qualitat_PlaManteniment.control");
            logger.info("Obtenint llistat de PlaMantenimentControl: " + llistat.size() + " registres trobats");

        } catch (Exception ex) {
            logger.error("Error obtenint llistat de PlaMantenimentControl", ex);
            ControllerUtils.saveMessageError(request, missatge("qualitat.PlaManteniment.control.missatge.llistat.no", request));
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
                    
                	logger.info("Eliminant el PlaMantenimentControl [" + id + "]");
                	oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
                    oliQualitatEjb.mantenimentControlEsborrar(Long.valueOf(id));

                } catch (Exception ex) {
                    logger.error("Error esborrant el PlaMantenimentControl [" + id + "]", ex);
                    exito = false;
                }
            }
        }

        String forward = deleteView + "?idManteniment=" + request.getParameter("idManteniment");
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
