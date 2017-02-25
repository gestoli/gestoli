/**
 * EtiquetatgeDelegate.java
 */
package es.caib.gestoli.front.spring;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.web.servlet.ModelAndView;

import es.caib.gestoli.front.util.Idioma;
import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;
import es.caib.gestoli.logic.model.EtiquetesLot;

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
public class EtiquetesLotDelegate implements ApplicationContextAware {
	private static Logger logger = Logger.getLogger(EtiquetesLotDelegate.class);

	private String listView;
    private String deleteView;
    private OliInfraestructuraEjb oliInfraestructuraEjb;
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
        	Long idEstabliment = Long.parseLong(request.getParameter("idEstabliment"));
        		
        	oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
        	Collection llistatLot = oliInfraestructuraEjb.findEtiquetesLotOriginalsByEstabliment(idEstabliment);
        	Collection llistat = new ArrayList();
        	
        	for(Iterator it=llistatLot.iterator(); it.hasNext();){
        		EtiquetesLot el = (EtiquetesLot)it.next();
        		
        		EtiquetesLotCommand elc = new EtiquetesLotCommand(
        				el.getEtiquetaLletra(), 
        				el.getEtiquetaInici(), 
        				el.getEtiquetaFi(), 
        				el.getCapacitat(), 
        				el.getUtilitzat(), 
        				el.getRetirat(), 
        				el.getOlivaTaula(),
        				el.getEstabliment(),
        				el.getEsEcologic());
        		
        		elc.setId(el.getId());
        		
        		oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
        		Boolean teFills = oliInfraestructuraEjb.teEtiquetesLotFills(el);
        		elc.setTeFills(teFills);
        		
        		llistat.add(elc);
        	}
        	
        	myModel.put("llistat", llistat);        	
        	myModel.put("path", "lista_etiquetesLot");
            logger.info("Obtenint llistat de establiments: " + llistat.size() + " registres trobats");
        } catch (Exception ex) {
            logger.error("Error obtenint llistat de establiments", ex);
            ControllerUtils.saveMessageError(request, missatge("establiment.missatge.llistat.no", request));
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
    	String id = request.getParameter("id");
    	String idEstabliment = request.getParameter("idEstabliment");
    	
    	try {
			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
		
	    	EtiquetesLot el = oliInfraestructuraEjb.findEtiquetesLotById(Long.parseLong(id));
	    	
	    	oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
	    	Boolean teFills = oliInfraestructuraEjb.teEtiquetesLotFills(el);
	    	
	    	if(teFills != null && !teFills){
		    	oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
		    	oliInfraestructuraEjb.eliminarEtiquetesLot(el);
		    	return new ModelAndView(deleteView+"?idEstabliment="+idEstabliment, myModel);
	    	}
       
    	} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
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

