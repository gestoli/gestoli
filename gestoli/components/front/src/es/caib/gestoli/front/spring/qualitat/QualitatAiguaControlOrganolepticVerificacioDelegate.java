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
import es.caib.gestoli.logic.model.QualitatAiguaControlOrganolepticVerificacio;

public class QualitatAiguaControlOrganolepticVerificacioDelegate  implements ApplicationContextAware {
	private static Logger logger = Logger.getLogger(QualitatAiguaControlOrganolepticVerificacioDelegate.class);
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
   		Long idPlaga = new Long(Long.parseLong(request.getParameter("idPlaga")));
   		oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
   		
   		String lang = Idioma.getLang(request);
		ResourceBundle bundle = ResourceBundle.getBundle("messages",new java.util.Locale(lang));
   		Collection llistat = oliQualitatEjb.controlPlaguesVerificacioCercaTotsPerPlaga(idPlaga);
       	
       	oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
       	myModel.put("idEstablecimiento",idEstablecimiento);
       	myModel.put("idControl", idPlaga);
       	myModel.put("llistat", llistat);
       	myModel.put("path", "qualitat_AiguaControlOrganoleptic.verificacio");
           logger.info("Obtenint llistat de AiguaControlOrganolepticVerificacio: " + llistat.size() + " registres trobats");

       } catch (Exception ex) {
           logger.error("Error obtenint llistat de AiguaControlOrganolepticVerificacio", ex);
           ControllerUtils.saveMessageError(request, missatge("qualitat.controlPlagues.verificacio.missatge.llistat.no", request));
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
                    
                	logger.info("Eliminant el AiguaControlOrganolepticVerificacio [" + id + "]");
                	oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
                    oliQualitatEjb.aiguaControlOrganolepticVerificacioEsborrar(Long.valueOf(id));

                } catch (Exception ex) {
                    logger.error("Error esborrant el AiguaControlOrganolepticVerificacio [" + id + "]", ex);
                    exito = false;
                }
            }
        }

        String forward = deleteView + "?idPlaga=" + request.getParameter("idPlaga");
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
