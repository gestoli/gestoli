/**
 * PlantacioDelegate.java
 *
 * Creada el 25 de març de 2009
 * &copy; at4.net 2009
 */
package es.caib.gestoli.front.spring;

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
import es.caib.gestoli.logic.model.Olivicultor;

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
public class PlantacioDelegate implements ApplicationContextAware {
	private static Logger logger = Logger.getLogger(PlantacioDelegate.class);

	private String listView;
    private String deleteView;
	private String rolDoGestor;
	private String rolDoControlador;
	private String rolOlivicultor;
	
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
        	if (request.isUserInRole(rolDoGestor) || request.isUserInRole(rolDoControlador)) {
        		String idOlivicultor = request.getParameter("idOlivicultor");
        		String idFinca = request.getParameter("idFinca");
        		Collection llistat = new ArrayList();
        		if (idOlivicultor != null && !idOlivicultor.equals("")){
        			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
        			if ((idFinca != null) && (!idFinca.equals(""))) {
        				Collection plantacionesDeFinca = oliInfraestructuraEjb.getPlantacionesDeFinca(Long.parseLong(idFinca));
        				if (plantacionesDeFinca.size() > 0) {
        					llistat.addAll(plantacionesDeFinca);
        				}
        			}
        		}
        		myModel.put("llistat", llistat);
        		myModel.put("path", "lista_plantacio");
        		myModel.put("idOlivicultor", idOlivicultor);
        		logger.info("Obtenint llistat de plantaciones: " + llistat.size() + " registres trobats");

        	} else if (request.isUserInRole(rolOlivicultor)) {
        		Olivicultor oliv = (Olivicultor) request.getAttribute("oliv");
        		String idOlivicultor = oliv.getId().toString();
        		Collection llistat = new ArrayList();
        		if (idOlivicultor != null && !idOlivicultor.equals("")){
        			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
        			Collection fincasId = oliInfraestructuraEjb.fincasIdOlivicultorCercaTots(oliv.getId());
        			for (Iterator it = fincasId.iterator(); it.hasNext();  ){
        				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
        				Collection plantacionesDeFinca = oliInfraestructuraEjb.getPlantacionesDeFinca((Long) it.next());
        				if (plantacionesDeFinca.size() > 0) {
        					llistat.addAll(plantacionesDeFinca);
        				}
        			}
        		}
        		myModel.put("llistat", llistat);
        		myModel.put("path", "lista_plantacio");
        		myModel.put("idOlivicultor", idOlivicultor);
        		logger.info("Obtenint llistat de plantaciones: " + llistat.size() + " registres trobats");
        	}

        } catch (Exception ex) {
            logger.error("Error obtenint llistat de plantaciones", ex);
            ControllerUtils.saveMessageError(request, missatge("plantacio.missatge.llistat.no", request));
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
                    logger.info("Eliminant la plantacio [" + lid + "]");
                    oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
                    oliInfraestructuraEjb.plantacioEsborrar(lid);
                } catch (Exception ex) {
                	logger.error("Error esborrant la plantacio [" + id + "]", ex);
                	try {
                		Long lid = new Long(Long.parseLong(id));
                		logger.info("Donant de baixa la plantacio [" + lid + "]");
                		oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
                		oliInfraestructuraEjb.plantacioBaixa(lid);
                	} catch (Exception ex2) {
                		logger.error("Error donant de baixa la plantacio [" + id + "]", ex);
                		ControllerUtils.saveMessageError(request, missatge("plantacio.missatge.borrar.no", request));
                        exito = false;	
                	}
                }
            }
        }
        
        if(request.isUserInRole(rolOlivicultor)){
        	deleteView = "redirect:FincasForm.html?id=" +request.getParameter("fromFinca")+"&idOlivicultor=" + request.getParameter("idOlivicultor");
        }else{
        	deleteView = "redirect:Plantacio.html?idOlivicultor=" + request.getParameter("idOlivicultor") + "&idFinca=" + request.getParameter("idFinca");
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
	
	public void setRolDoControlador(String rolDoControlador) {
		this.rolDoControlador = rolDoControlador;
	}


	/**
	 * Injecció de la dependència rolOlivicultor
	 * @param rolOlivicultor La classe a injectar.
	 */
	public void setRolOlivicultor(String rolOlivicultor) {
		this.rolOlivicultor = rolOlivicultor;
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