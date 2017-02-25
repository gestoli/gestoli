/**
 * ConsultaAnaliticasDelegate.java
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
public class ConsultaEtiquetesDelegate implements ApplicationContextAware {
	private static Logger logger = Logger.getLogger(ConsultaEtiquetesDelegate.class);

	private String listView;
    private OliInfraestructuraEjb oliInfraestructuraEjb;
    private ApplicationContext applicationContext;
    private String rolDoGestor;
	private String rolAdministracio;
	private String rolDoControl;
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
        	Long campanyaId = (Long)request.getSession().getAttribute(campanyaSessionKey);        	
        	if (request.isUserInRole(rolDoGestor) || request.isUserInRole(rolAdministracio) || request.isUserInRole(rolDoControl) ) {
        		oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
        		llistat = oliInfraestructuraEjb.establimentCercaTotsActivos(campanyaId);
        	}        	
        	
        	String establimentNom = "";
        	String cerca = request.getParameter("cerca");
        	if (cerca != null && !cerca.equals("")){
        		if (cerca.matches("^\\w+\\s\\d+$")){
	        		String[] eti = cerca.split (" ");
	        		String lletres = eti[0];
	        		Integer numero = Integer.valueOf(eti[1]);
	        		EtiquetesLot etiqueta = oliInfraestructuraEjb.findEtiquetesLotByEtiqueta(lletres, numero);
	        		if (etiqueta != null){
	        			establimentNom = etiqueta.getEstabliment().getNom();
	        		} else {
	        			establimentNom = missatge("consulta.etiquetes.no.trobat", request);
	        		}
        		} else {
        			establimentNom = missatge("consulta.etiquetes.format.incorrecte", request);
        		}
        	}
			
			myModel.put("establimentNom", establimentNom);
        	myModel.put("llistat", llistat);
        	myModel.put("path", "lista_establecimientos_analiticas");
            logger.info("Obtenint llistat de establiments: " + llistat.size() + " registres trobats");
        } catch (Exception ex) {
            logger.error("Error obtenint llistat de establiments", ex);
            ControllerUtils.saveMessageError(request, missatge("establiment.missatge.llistat.no", request));
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
	 * Injecció de la dependència tipusEstablimentTafona
	 * @param tipusEstablimentTafona La classe a injectar.
	 */
	public void setTipusEstablimentTafona(Integer tipusEstablimentTafona) {
		this.tipusEstablimentTafona = tipusEstablimentTafona;
	}
	
	 /**
	 * Injecció de la dependència tipusEstablimentTafonaEnvasadora
	 * @param tipusEstablimentTafona La classe a injectar.
	 */
	public void setTipusEstablimentTafonaEnvasadora(Integer tipusEstablimentTafonaEnvasadora) {
		this.tipusEstablimentTafonaEnvasadora = tipusEstablimentTafonaEnvasadora;
	}

	
	/**
	 * Injecció de la dependència rolOlivicultor
	 * @param rolAdministracio La classe a injectar.
	 */
	public void setRolAdministracio(String rolAdministracio) {
		this.rolAdministracio = rolAdministracio;
	}
	
	/**
	 * @param rolDoControl the rolDoControl to set
	 */
	public void setRolDoControl(String rolDoControl) {
		this.rolDoControl = rolDoControl;
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

	
}

