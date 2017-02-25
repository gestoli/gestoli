package es.caib.gestoli.front.spring.qualitat;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import es.caib.gestoli.logic.interfaces.OliQualitatEjb;
import es.caib.gestoli.logic.model.Establiment;

public class QualitatOrganigramaEmpresaController extends AbstractController {
	
	
	private static Logger logger = Logger.getLogger(QualitatOrganigramaEmpresaController.class);
	private OliQualitatEjb oliQualitatEjb;	
	private String listView;
	private HibernateTemplate hibernateTemplate;
    private String establimentSessionKey;
	
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
    	

//    	Long establimentId = null;
//    	if (request.getParameter("establimentId")!=null && !request.getParameter("establimentId").equals("")){ 
//    		establimentId = new Long(request.getParameter("establimentId"));
//    	}
    	
    	String tipus = request.getParameter("tipus");
    	String id = request.getParameter("id");
    	 	
    	try  {
    	
    		HttpSession session = request.getSession();
    		Establiment est = (Establiment)session.getAttribute(establimentSessionKey);
	   		Long idEstablecimiento =  est.getId();
	   		oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
	   		Collection llistat = oliQualitatEjb.puestoCercaTotsPerEstabliment(idEstablecimiento);
	   		oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
    		
	   		myModel.put("llistat", llistat);
	   		myModel.put("path", "qualitat_OrganigramaEmpresa");
	   		logger.info("Obtenint organigrama empresa: " + llistat.size() + " registres trobats");
	   		    		
    		
    	} catch (Exception ex) {
    		logger.error("Error obtenint organigrama d'emprsa", ex);
    		throw new ServletException(ex);
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
	 * Injecció de la dependència establimentSessionKey
	 *
	 * @param establimentSessionKey La classe a injectar.
	 */
	public void setEstablimentSessionKey(String establimentSessionKey) {
		this.establimentSessionKey = establimentSessionKey;
	}
    
    /**
     * Injecció de la dependència oliQualitatEjb
     *
     * @param oliQualitatEjb La classe a injectar.
     */
  
    public void setOliQualitatEjb(OliQualitatEjb oliQualitatEjb) {
        this.oliQualitatEjb = oliQualitatEjb;
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
