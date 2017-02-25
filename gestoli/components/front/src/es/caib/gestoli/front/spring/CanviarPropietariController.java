/**
 * ConsultaTrazabilitatController.java
 * 
 * Creada el 28 de juny de 2006, 13:19:22
 * &copy; Limit Tecnologies 2006
 */
package es.caib.gestoli.front.spring;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;
import es.caib.gestoli.logic.model.Zona;

/**
 * <p>Controlador per a mostrar la trazabilitat d'un
 * contenidor.</p>
 * <p>Els paràmetres de la petició HTTP relevants per
 * a aquest controlador són:
 * <ul>
 *   <li>contenidorId: Identificador del contenidor.</li>
 * </ul></p>
 *
 */
public class CanviarPropietariController extends AbstractController {
	
	
	private static Logger logger = Logger.getLogger(CanviarPropietariController.class);
	private OliInfraestructuraEjb oliInfraestructuraEjb;
	private HibernateTemplate hibernateTemplate;
	


    /**
     * Se llama cuando se aceptan las modificaciones del objeto. Solo
     * se ejecuta esta función en el caso de que se haya ejecutado la
     * validación sin producir ningún error.
     * 
     * @see AbstractController#handleRequest(HttpServletRequest, HttpServletResponse)
     */
    public ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws ServletException {
    	Map myModel = new HashMap();
    	
    	String desti = "EstablimentPrincipal.html";
    	String sTipus = request.getParameter("tipus");
    	String sId = request.getParameter("id");
    	String zonaId = request.getParameter("zonaId");
    	 	
    	try  {
    		if (sId != null && !sId.equals("") && sTipus != null && !sTipus.equals("")){
    			Long id = Long.valueOf(sId);
    			Integer tipus = Integer.valueOf(sTipus);
    			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
    			oliInfraestructuraEjb.canviaPropietariOli(id, tipus);
    		}
    		if (zonaId != null && !zonaId.equals("")){
        		oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
        		Zona zona = oliInfraestructuraEjb.zonaAmbId(Long.valueOf(zonaId));
        		if (zona != null) desti += "?establimentId=" + zona.getEstabliment().getId() + "&zonaId=" + zonaId;
        	}
    	} catch (Exception ex) {
    		throw new ServletException(ex);
    	}
    	
    	return new ModelAndView("redirect:" + desti, myModel);
    }


    /**
     * Inyección de la dependencia oliInfraestructuraEjb
     * @param oliInfraestructuraEjb La clase a inyectar.
     */
    public void setOliInfraestructuraEjb(OliInfraestructuraEjb oliInfraestructuraEjb) {
        this.oliInfraestructuraEjb = oliInfraestructuraEjb;
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
