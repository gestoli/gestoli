/**
 * EstablimentDelegate.java
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
import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;

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
public class EstablimentDelegate implements ApplicationContextAware {
	private static Logger logger = Logger.getLogger(EstablimentDelegate.class);

	private String listView;
    private String deleteView;
    private OliInfraestructuraEjb oliInfraestructuraEjb;
    private ApplicationContext applicationContext;
    private String rolDoGestor;
    private String rolDoControl;
	private String rolAdministracio;
	private String campanyaSessionKey;
	
	private Integer tipusEstablimentTafona;
	private Integer tipusEstablimentTafonaEnvasadora;

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
        	Collection llistat = new ArrayList();
        	String vistaEstabliment = request.getParameter("vista");
        	String analitica = request.getParameter("analitica");
        	String pendentAnalitica = request.getParameter("pendentAnalitica");
        	String desferPendentAnalitica = request.getParameter("desferPendentAnalitica");
        	String eliminats = request.getParameter("eliminats");
        	Long campanyaId = (Long)request.getSession().getAttribute(campanyaSessionKey);
        	
        	if((request.isUserInRole(rolDoGestor) || request.isUserInRole(rolAdministracio) || request.isUserInRole(rolDoControl))&& vistaEstabliment!= null && !vistaEstabliment.equalsIgnoreCase("")){
        		oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
        		llistat = oliInfraestructuraEjb.establimentCercaTotsActivos(campanyaId);
        		myModel.put("path", "lista_establecimientos_vista");
        		myModel.put("vista", "true");
        	}else{
        		oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
        		llistat = oliInfraestructuraEjb.establimentCercaTotsByCampanyaOrderedByRbRc(campanyaId);
        		myModel.put("path", "lista_establecimientos");
        	}
        	if (request.getParameter("consulta") != null) {
        		List tipos = new ArrayList();
            	tipos.add(tipusEstablimentTafona);
            	tipos.add(tipusEstablimentTafonaEnvasadora);
            	
        		oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
        		llistat = oliInfraestructuraEjb.establimentCercaTotsActivosByTipos(campanyaId, tipos.toArray());
        		myModel.put("path", "consultar_entradesOliva");
        		myModel.put("consulta", "true");
        	}
        	if(request.isUserInRole(rolDoControl)&& analitica!= null && !analitica.equalsIgnoreCase("")){
        		oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
        		llistat = oliInfraestructuraEjb.establimentCercaTotsActivos(campanyaId);
        		myModel.put("path", "lista_establecimientos_analitica");
        		myModel.put("analitica", "true");
        	}
        	
        	if(request.isUserInRole(rolDoControl)&& pendentAnalitica!= null && !pendentAnalitica.equalsIgnoreCase("")){
        		oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
        		llistat = oliInfraestructuraEjb.establimentCercaTotsActivos(campanyaId);
        		myModel.put("path", "lista_establecimientos_pendent_analitica");
        		myModel.put("pendentAnalitica", "true");
        	}
        	
        	if(request.isUserInRole(rolDoControl)&& desferPendentAnalitica!= null && !desferPendentAnalitica.equalsIgnoreCase("")){
        		oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
        		llistat = oliInfraestructuraEjb.establimentCercaTotsActivos(campanyaId);
        		myModel.put("path", "lista_establecimientos_desfer_pendent_analitica");
        		myModel.put("desferPendentAnalitica", "true");
        	}
        	
    		if(request.isUserInRole(rolDoGestor) && eliminats != null && eliminats.equals("true")){
        		oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
        		llistat.addAll(oliInfraestructuraEjb.establimentCercaTotsEliminats());
        		myModel.put("eliminats", true);
    		}
    		
        	myModel.put("llistat", llistat);
        	
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
        String[] ids = request.getParameterValues("id");
        boolean exito = true;
        for (int i=0; i<ids.length; i++) {
        	String id = ids[i];
            if (id != null && !"".equals(id)) {
                
            	try {
                    Long lid = new Long(Long.parseLong(id));
                    oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
                    if (oliInfraestructuraEjb.existenZonasAsociadasEstablecimientos(lid)) {
                    	ControllerUtils.saveMessageError(request, missatge("establiment.missatge.esborrar.no", request));
                    } else {
                    	logger.info("Eliminant l'establiment [" + lid + "]");
                    	oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
                    	oliInfraestructuraEjb.establimentEsborrar(lid);
                    }
                } catch (Exception ex) {
                    logger.error("Error esborrant l'establiment [" + id + "]", ex);
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
	 * Injecció de la dependència rolDoGestor
	 * @param rolDoGestor La classe a injectar.
	 */
	public void setRolDoGestor(String rolDoGestor) {
		this.rolDoGestor = rolDoGestor;
	}

	
	/**
	 * @param rolDoControl the rolDoControl to set
	 */
	public void setRolDoControl(String rolDoControl) {
		this.rolDoControl = rolDoControl;
	}


	/**
	 * Injecció de la dependència rolOlivicultor
	 * @param rolAdministracio La classe a injectar.
	 */
	public void setRolAdministracio(String rolAdministracio) {
		this.rolAdministracio = rolAdministracio;
	}
	

    public void setApplicationContext(ApplicationContext context) {
        applicationContext = context;
    }
    
    /**
	 * Injecció de la dependència campanyaSessionKey
	 *
	 * @param campanyaSessionKey La classe a injectar.
	 */
	public void setCampanyaSessionKey(String campanyaSessionKey) {
		this.campanyaSessionKey = campanyaSessionKey;
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


	public void setTipusEstablimentTafona(Integer tipusEstablimentTafona) {
		this.tipusEstablimentTafona = tipusEstablimentTafona;
	}


	public void setTipusEstablimentTafonaEnvasadora(
			Integer tipusEstablimentTafonaEnvasadora) {
		this.tipusEstablimentTafonaEnvasadora = tipusEstablimentTafonaEnvasadora;
	}

	
	
}

