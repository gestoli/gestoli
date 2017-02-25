/**
 * MarcaDelegate.java
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
 * @author Oriol Barnés (obarnes@at4.net)
 */
public class MarcaDelegate implements ApplicationContextAware {
	private static Logger logger = Logger.getLogger(MarcaDelegate.class);

	private String listView;
	private String deleteView;
	private String rolDoGestor;
	private OliInfraestructuraEjb oliInfraestructuraEjb;
	private ApplicationContext applicationContext;
	private Integer tipusEstablimentEnvasadora;
	private Integer tipusEstablimentTafonaEnvasadora;

	private HibernateTemplate hibernateTemplate;


	/**
	 * Retorna una lista con el contenido de la tabla.
	 * @see org.springframework.web.servlet.mvc.Controller#handleRequest(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ModelAndView list(
			HttpServletRequest request,
			HttpServletResponse response) {
		Map myModel = new HashMap();
		try {
			if (request.isUserInRole(rolDoGestor)) {
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				Collection llistat = oliInfraestructuraEjb.marcaCercaTots();
				myModel.put("llistat", llistat);
				myModel.put("path", "lista_marcas");        	
				logger.info("Obtenint llistat de marcas: " + llistat.size() + " registres trobats");								
			}
		} catch (Exception ex) {
			logger.error("Error obtenint llistat de marcas", ex);
			ControllerUtils.saveMessageError(request, missatge("marca.missatge.llistat.no", request));
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
		for (int i=0; i<ids.length; i++) {
			String id = ids[i];
			if (id != null && !"".equals(id)) {
				try {
					Long lid = new Long(Long.parseLong(id));
					logger.info("Eliminant la marca [" + lid + "]");
					//ANTES DE BORRARLA COMPROBAMOS QUE NO ESTA ASOCIADA A NINGUN ESTABLECIMIENTO DE TIPO ENVASADORA O TAFONA/ENVASADORA

					oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
					Boolean existenEnvasadoresActivos = oliInfraestructuraEjb.getExistenEnvasadoresActivoByMarca(lid,null,tipusEstablimentTafonaEnvasadora, tipusEstablimentEnvasadora);
					if(existenEnvasadoresActivos.booleanValue()){
						ControllerUtils.saveMessageError(request, missatge("marca.missatge.borrar.no", request));
					}else{
						oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
						oliInfraestructuraEjb.marcaEsborrar(lid);
					}					
					
				} catch (Exception ex) {
					logger.error("Error esborrant la marca [" + id + "]", ex);
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
	 * Injecció de la dependència rolDoGestor
	 * @param rolDoGestor La classe a injectar.
	 */
	public void setRolDoGestor(String rolDoGestor) {
		this.rolDoGestor = rolDoGestor;
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
	 /**
     * Injecció de la dependència tipusEstablimentEnvasadora
     *
     * @param tipusEstablimentEnvasadora La classe a injectar.
     */
    public void setTipusEstablimentEnvasadora(Integer tipusEstablimentEnvasadora) {
        this.tipusEstablimentEnvasadora = tipusEstablimentEnvasadora;
    }
    
    /**
     * Injecció de la dependència tipusEstablimentTafonaEnvasadora
     *
     * @param tipusEstablimentTafonaEnvasadora La classe a injectar.
     */
    public void setTipusEstablimentTafonaEnvasadora(Integer tipusEstablimentTafonaEnvasadora) {
        this.tipusEstablimentTafonaEnvasadora = tipusEstablimentTafonaEnvasadora;
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
