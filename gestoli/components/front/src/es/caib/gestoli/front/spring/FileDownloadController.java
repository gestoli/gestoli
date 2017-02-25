 /**
 * FileDownloadController.java *
 *
 */
package es.caib.gestoli.front.spring;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.mvc.SimpleFormController;

import es.caib.gestoli.logic.interfaces.OliArxiuEjb;
import es.caib.gestoli.logic.model.Arxiu;


/**
 * FileDownloadController
 * @author obarnes
 *
 */
public class FileDownloadController implements Controller {
    private OliArxiuEjb oliArxiuEjb;
	private HibernateTemplate hibernateTemplate;

    /**
     * Se llama cuando se aceptan las modificaciones del objeto. Solo
     * se ejecuta esta función en el caso de que se haya ejecutado la
     * validación sin producir ningún error.
     * @see SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.validation.BindException)
     */
    public ModelAndView handleRequest(
            HttpServletRequest request,
            HttpServletResponse response)
    throws ServletException {
    	
    	String id = request.getParameter("id");
    	if (id != null && !"".equals(id)) {
	        Arxiu ar = null;
	        try {
	        	oliArxiuEjb.setHibernateTemplate(getHibernateTemplate());
	           ar = oliArxiuEjb.arxiuCercaId(Long.valueOf(id));
	           if (ar != null) {
	        	   response.setContentType(ar.getMime());
		           if (request.getParameter("download") != null) {
		        	   response.setHeader("Content-Disposition", "attachment; filename=\"" + ar.getNom() + "\"");
		           } else {
		        	   response.setHeader("Content-Disposition", "inline; filename=\"" + ar.getNom() + "\"");
		        	   // 20090803 obarnes: evitamos que las imágenes se queden en cache (el mapa del establecimiento no se visualizaba
		        	   response.setHeader("Cache-Control", "no-cache");
		        	   response.setHeader("Pragma", "no-cache");
		        	   response.setHeader("Expires", "-1");
		           }
		       	   //si no se desea q lo abra el navegador en lugar de inline se pone attachment
		       	   
		       	   response.getOutputStream().write(ar.getBinari());
	           }
	        } catch (Exception ex) {
	            throw new ServletException(ex);
	        }
    	}
    	
        return null;
    }

    /**
     * Inyección de la dependencia modelManager
     * 
     * @param modelManager la clase a inyectar.
     */
   public void setOliArxiuEjb(OliArxiuEjb oliArxiuEjb) {
        this.oliArxiuEjb = oliArxiuEjb;
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
