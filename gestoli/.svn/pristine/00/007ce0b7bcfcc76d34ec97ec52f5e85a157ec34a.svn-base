package es.caib.gestoli.front.spring.frontOffice;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import es.caib.gestoli.front.spring.ConsultaTrazabilitatResumidaController;
import es.caib.gestoli.logic.interfaces.OliFrontOfficeEjb;

public class FrontOfficeInformacioOliGeneralController extends AbstractController {
	
	private static Logger logger = Logger.getLogger(ConsultaTrazabilitatResumidaController.class);

	private OliFrontOfficeEjb oliFrontOfficeEjb;
	private HibernateTemplate hibernateTemplate;
	private Integer tipusEstablimentEnvasadora;
	private Integer tipusEstablimentTafonaEnvasadora;
	
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
    	String idioma = request.getParameter("idioma");
    	
    	List tipos = new ArrayList();
    	tipos.add(tipusEstablimentEnvasadora);
    	tipos.add(tipusEstablimentTafonaEnvasadora);
		
    	
    	try{
	    	oliFrontOfficeEjb.setHibernateTemplate(getHibernateTemplate());
	    	Long idCampanya = oliFrontOfficeEjb.campanyaCercaActual();
	    	oliFrontOfficeEjb.setHibernateTemplate(getHibernateTemplate());
	    	Collection envasadores = oliFrontOfficeEjb.establimentCercaTotsActivosByTipos(idCampanya, tipos.toArray());
			myModel.put("envasadores", envasadores);
    	} catch (Exception e){
    		
    	}
    	
    	/*String id = request.getParameter("id");
    	
    	try {
			oliFrontOfficeEjb.setHibernateTemplate(getHibernateTemplate());
    	} catch (Exception ex) {
    		throw new ServletException(ex);
    	}
	*/
    	return new ModelAndView("web/cercageneral."+idioma+".jsp?idioma="+idioma, myModel);
    	
    }
    	
    	/**
         * Injecció de la dependència consultaEjb
         *
         * @param consultaEjb La classe a injectar.
         */
      
        public void setOliFrontOfficeEjb(OliFrontOfficeEjb oliFrontOfficeEjb) {
            this.oliFrontOfficeEjb = oliFrontOfficeEjb;
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
    	

    	public void setTipusEstablimentEnvasadora(Integer tipusEstablimentEnvasadora) {
    		this.tipusEstablimentEnvasadora = tipusEstablimentEnvasadora;
    	}

    	public void setTipusEstablimentTafonaEnvasadora(
    			Integer tipusEstablimentTafonaEnvasadora) {
    		this.tipusEstablimentTafonaEnvasadora = tipusEstablimentTafonaEnvasadora;
    	}
        
}
