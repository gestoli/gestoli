/**
 * TrasllatDelegate.java
 *
 * Creada el 25 de març de 2009
 * &copy; at4.net 2009
 */
package es.caib.gestoli.front.spring.qualitat;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.web.servlet.ModelAndView;

import es.caib.gestoli.front.spring.ControllerUtils;
import es.caib.gestoli.front.util.Idioma;
import es.caib.gestoli.logic.interfaces.OliQualitatEjb;
import es.caib.gestoli.logic.model.Establiment;
import es.caib.gestoli.logic.model.QualitatControlPlagues;

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
public class QualitatAvisosDelegate implements ApplicationContextAware {
	
	private static Logger logger = Logger.getLogger(QualitatAvisosDelegate.class);
	private String listView;
    private OliQualitatEjb oliQualitatEjb;
    private ApplicationContext applicationContext;
    private String establimentSessionKey;
	private HibernateTemplate hibernateTemplate;
	private MessageSource messageSource;

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
		   		
		   		// Plan de limpieza y desinfección
		   			// Realización
		   			Collection netejaRealitzacioPendents = oliQualitatEjb.netejaRealitzacioCercaPendentsPerEstabliment(est.getId());
		   			// Verificación
		   			Collection netejaVerificacioPendents = oliQualitatEjb.netejaVerificacioCercaPendentsPerEstabliment(est.getId());
		   		
		   		// Plan de mantenimiento
		   			// Control
		   			Collection mantenimentControlPendents = oliQualitatEjb.mantenimentRealitzacioCercaPendentsPerEstabliment(est.getId());
		   			// Verificación
		   			Collection mantenimentVerificacioPendents = oliQualitatEjb.mantenimentVerificacioCercaPendentsPerEstabliment(est.getId());
		   		
		   		// Plan de control de plagas
		   			// Verificación
		   			Collection plaguesVerificacioPendents = oliQualitatEjb.controlPlaguesVerificacioCercaPendentsPerEstabliment(est.getId());
		   			for (Object plag: plaguesVerificacioPendents){
		        		
		        		if (!((QualitatControlPlagues)plag).getIsResponsableIntern()){
		        			((QualitatControlPlagues)plag).setFrecuencia(((QualitatControlPlagues)plag).getFrecSeguiment());
			        		((QualitatControlPlagues)plag).setElementControl( ((QualitatControlPlagues)plag).getPlagaObjectiu());
			        		((QualitatControlPlagues)plag).setResponsableIntern(null);
			        		((QualitatControlPlagues)plag).setUbicacio(null);
		        		}
		        		
		        	}
		   			
		   		// Plan de control de proveedores
		   			// Evaluación
		   			Collection proveidorVerificacioPendents = oliQualitatEjb.proveidorsVerificacioCercaPendentsPerEstabliment(est.getId());
		   		
		   		// Plan de control abastecimiento de agua
		   			// Verificació
		   			Collection aiguaVerificacioPendents = oliQualitatEjb.controlAiguaVerificacioCercaPendentsPerEstabliment(est.getId());
		   		
		   		// APPCC
		   			// Control
		   			Collection appccControlPendents = oliQualitatEjb.APPCCControlCercaPendentsPerEstabliment(est.getId());
		   			// Verificación
		   			Collection appccVerificacioPendents = oliQualitatEjb.APPCCVerificacioCercaPendentsPerEstabliment(est.getId());
		   		
		   		
		   		// No Conformidades
		   			// Abiertas
		   			Collection noConformitatsPendents = oliQualitatEjb.noConformitatsObertesPerEstabliment(est.getId());
		   			
		   		
	        	myModel.put("netejaRealitzacioPendents", netejaRealitzacioPendents);
	        	myModel.put("netejaVerificacioPendents", netejaVerificacioPendents);
	        	myModel.put("mantenimentControlPendents", mantenimentControlPendents);
	        	myModel.put("mantenimentVerificacioPendents", mantenimentVerificacioPendents);
	        	myModel.put("plaguesVerificacioPendents", plaguesVerificacioPendents);
	        	myModel.put("proveidorVerificacioPendents", proveidorVerificacioPendents);
	        	myModel.put("aiguaVerificacioPendents", aiguaVerificacioPendents);
	        	myModel.put("appccControlPendents", appccControlPendents);
	        	myModel.put("appccVerificacioPendents", appccVerificacioPendents);
	        	myModel.put("noConformitatsPendents", noConformitatsPendents);
	        	
	        	
	        	myModel.put("path", "tareas_pendientes");
	            logger.info("Obtenemos el listado de tareas pendientes ");  
	            if(netejaRealitzacioPendents.size()== 0 ){//&& llistatPendientesDevolver.size() == 0 && llistatPendientesAceptarDevolver.size() == 0){
	            	ControllerUtils.saveMessageError(request, missatge("manteniment.tareasPendientes.no", request));
	            }
	         
        } catch (Exception ex) {
            logger.error("Error obtenint llistat de diposits", ex);
            ControllerUtils.saveMessageError(request, missatge("diposit.missatge.llistat.no", request));
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
     * Inyección de la dependencia oliQualitatEjb
     * @param oliQualitatEjb La clase a inyectar.
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

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
}

