/**
 * AltresConsultesTrazabilidadLoteController.java
 * 
 * Creada el 28 de juny de 2006, 13:19:22
 * &copy; Limit Tecnologies 2006
 */
package es.caib.gestoli.front.spring;

import java.util.Collections;
import java.util.Comparator;
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

import es.caib.gestoli.logic.interfaces.OliConsultaEjb;
import es.caib.gestoli.logic.model.Lot;

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
public class AltresConsultesTrazabilidadLoteController extends AbstractController {
	
	
	private static Logger logger = Logger.getLogger(AltresConsultesTrazabilidadLoteController.class);
	private OliConsultaEjb oliConsultaEjb;
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
    
    	String id = request.getParameter("id");
    	 	
    	try  {
	    		oliConsultaEjb.setHibernateTemplate(getHibernateTemplate());
	    		List lotes =  oliConsultaEjb.lotesElaboracio(id);
	    		LoteComparatorDecreciente loteComparatorDecreciente = new LoteComparatorDecreciente();
	    		Collections.sort(lotes,loteComparatorDecreciente );
	    		
	    		myModel.put("lotes", lotes); 
	    		myModel.put("path", "consulta_trazabilidad_lotes");
    		
    		
    	} catch (Exception ex) {
    		throw new ServletException(ex);
    	}
    	    	
    	
    	
    	return new ModelAndView("AltresConsultesTrazabilidadLote", myModel);
    }



    /**
     * Injecció de la dependència consultaEjb
     *
     * @param consultaEjb La classe a injectar.
     */
  
    public void setOliConsultaEjb(OliConsultaEjb oliConsultaEjb) {
        this.oliConsultaEjb = oliConsultaEjb;
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
	
   
	 private String missatge(String clave){
	    	String valor= getMessageSourceAccessor().getMessage(clave);
	    	return valor;
	  }
 
	 class LoteComparatorDecreciente implements Comparator {

		  public int compare(Object o1, Object o2) {
		    Lot u1 = (Lot) o1;
		    Lot u2 = (Lot) o2;
		    return - u1.getData().compareTo(u2.getData());
		  }

		  public boolean equals(Object o) {
		    return this == o;
		  }
		  
	}
}


