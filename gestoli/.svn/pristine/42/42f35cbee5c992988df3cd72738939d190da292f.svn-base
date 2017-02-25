package es.caib.gestoli.front.spring;

import java.util.ArrayList;
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
import es.caib.gestoli.logic.interfaces.OliConsultaEjb;
import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;

public class ConsultaHaMunicipiDelegate implements ApplicationContextAware {
	
	private static Logger logger = Logger.getLogger(ConsultaHaMunicipiDelegate.class);
	private String listView;
    private String deleteView;
	private OliConsultaEjb oliConsultaEjb;
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
	   		
        	oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
        	Long campanyaId = oliInfraestructuraEjb.campanyaCercaActual();

			oliConsultaEjb.setHibernateTemplate(getHibernateTemplate());
			Collection llistat = new ArrayList(oliConsultaEjb.getSuperficiesVarietats(campanyaId));
	   		//String lang = Idioma.getLang(request);
			//ResourceBundle bundle = ResourceBundle.getBundle("messages",new java.util.Locale(lang));
	   		//Collection llistat = oliConsultaEjb.getSuperficiesVarietats(idEstablecimiento);
	   		/*for (Object etapa: llistat){
        		if ("".equals(((ConsultaHaMunicipi)etapa).getPuntControl())){
        			((ConsultaHaMunicipi)etapa).setPuntControl(bundle.getString("qualitat.appcc.control.pendent"));
        			((ConsultaHaMunicipi)etapa).setPerillControlat("No");
        		}
	   		}*/
        		
	   		//oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
        	//myModel.put("idEstablecimiento",idEstablecimiento);
        	myModel.put("llistat", llistat);
        	myModel.put("path", "consulta_haMunicipi");
            logger.info("Obtenint llistat d'HA per municipi: " + llistat.size() + " registres trobats");

        } catch (Exception ex) {
            logger.error("Error obtenint llistat d'HA per municipi", ex);
            ControllerUtils.saveMessageError(request, missatge("consulta.haMunicipi.missatge.llistat.no", request));
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

    /**
     * Inyección de la dependencia oliConsultaEjb
     * @param oliConsultaEjb La clase a inyectar.
     */
    public void setOliConsultaEjb(OliConsultaEjb oliConsultaEjb) {
        this.oliConsultaEjb = oliConsultaEjb;
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
