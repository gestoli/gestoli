/**
 * DipositDelegate.java
 *
 * Creada el 25 de març de 2009
 * &copy; at4.net 2009
 */
package es.caib.gestoli.front.spring;

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
import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;
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
 * 
 */
public class DipositDelegate implements ApplicationContextAware {
	
	private static Logger logger = Logger.getLogger(DipositDelegate.class);
	private String listView;
    private String deleteView;
    private String rolProductor;
	private String rolEnvasador;
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
        	//if (request.isUserInRole(rolDoGestor)) {
	        	HttpSession session = request.getSession();
	    		Establiment est = (Establiment)session.getAttribute(establimentSessionKey);
		   		Long idEstablecimiento =  est.getId();
		   		oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
	        	Collection llistat = oliInfraestructuraEjb.findNoFicticiByEstabliment(idEstablecimiento);
	        	oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
	        	if (oliInfraestructuraEjb.existenZonasActiusEnEstabliment(idEstablecimiento).booleanValue()){
	        		myModel.put("existenZonasActivasEnEstabliment", "true");
	        	}
	        	myModel.put("idEstablecimiento",idEstablecimiento);
	        	myModel.put("llistat", llistat);
	        	myModel.put("path", "lista_diposits");
	            logger.info("Obtenint llistat de diposits: " + llistat.size() + " registres trobats");

        	//}
	         
        } catch (Exception ex) {
            logger.error("Error obtenint llistat de diposits", ex);
            ControllerUtils.saveMessageError(request, missatge("diposit.missatge.llistat.no", request));
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
                    if (oliInfraestructuraEjb.existenEntradasDepositoAsociadasDeposito(lid)) {
                    	ControllerUtils.saveMessageError(request, missatge("diposit.missatge.esborrar.no.entradaDiposit", request));
                    } else if (oliInfraestructuraEjb.existenAnaliticasAsociadasDeposito(lid)) {
                    	ControllerUtils.saveMessageError(request, missatge("diposit.missatge.esborrar.no.analitica", request));
                    } else if (oliInfraestructuraEjb.existenSalidasDepositoAsociadasDeposito(lid)) {
                    	ControllerUtils.saveMessageError(request, missatge("diposit.missatge.esborrar.no.sortidaDiposit", request));
                    } else if (oliInfraestructuraEjb.existenTrasladosAsociadosDeposito(lid)) {
                    	ControllerUtils.saveMessageError(request, missatge("diposit.missatge.esborrar.no.trasllat", request));
                    } else if (oliInfraestructuraEjb.existenLotesAsociadosDeposito(lid)) {
                    	ControllerUtils.saveMessageError(request, missatge("diposit.missatge.esborrar.no.lot", request));
                    } else {
                    	logger.info("Eliminant el diposit [" + id + "]");
                    	oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
                    	oliInfraestructuraEjb.dipositEsborrar(Long.valueOf(id));
                    }
                } catch (Exception ex) {
                    logger.error("Error esborrant el diposit [" + id + "]", ex);
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


	/**
	 * Injecció de la dependència rolEnvasador
	 * @param rolEnvasador La classe a injectar.
	 */
	public void setRolEnvasador(String rolEnvasador) {
		this.rolEnvasador = rolEnvasador;
	}

	
	/**
	 * Injecció de la dependència rolProductor
	 * @param rolProductor La classe a injectar.
	 */
	public void setRolProductor(String rolProductor) {
		this.rolProductor = rolProductor;
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

