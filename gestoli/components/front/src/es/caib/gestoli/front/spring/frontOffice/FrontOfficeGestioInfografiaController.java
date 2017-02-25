package es.caib.gestoli.front.spring.frontOffice;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import es.caib.gestoli.front.spring.GestioInfografiaCommand;
import es.caib.gestoli.front.spring.InformacioUtilCommand;
import es.caib.gestoli.logic.interfaces.OliFrontOfficeEjb;
import es.caib.gestoli.logic.model.Arxiu;
import es.caib.gestoli.logic.model.GestioInfografia;
import es.caib.gestoli.logic.model.InformacioUtil;

public class FrontOfficeGestioInfografiaController extends AbstractController {
	
	
	private static Logger logger = Logger.getLogger(FrontOfficeInformacioUtilController.class);
	private OliFrontOfficeEjb oliFrontOfficeEjb;
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
    	String idioma = request.getParameter("idioma");
    	
    	try  {
    		
    		oliFrontOfficeEjb.setHibernateTemplate(getHibernateTemplate());
    		Collection gestioInfografies = oliFrontOfficeEjb.gestioInfografiaCercaTots();
    		
    		Set<GestioInfografiaCommand> commands = new HashSet<GestioInfografiaCommand>(0);
 
    		for (Object infografia : gestioInfografies){
    			GestioInfografia gestioInfografia = (GestioInfografia)infografia;
    			GestioInfografiaCommand command = new GestioInfografiaCommand();
    			command.fromGestioInfografia(gestioInfografia);
    			
    			/*
    			command.setIdImatge(gestioInfografia.getArxiu());
				if (gestioInfografia.getArxiu() != null){
					Arxiu arxiu = oliFrontOfficeEjb.arxiuAmbId(gestioInfografia.getArxiu());
					command.setArxiuImatge(arxiu);
				}*/
				
				commands.add(command);
    		}
    		myModel.put("infografies", commands);
    		
    		/*if (request.getParameter("id") != null){
    			Long idInformacio = Long.valueOf(request.getParameter("id"));

	    		oliFrontOfficeEjb.setHibernateTemplate(getHibernateTemplate());
	    		InformacioUtil informacioUtil = oliFrontOfficeEjb.informacioUtilAmbId(idInformacio);
	    		
	    		InformacioUtilCommand command = new InformacioUtilCommand();
	 
	    		command.fromInformacioUtil(informacioUtil);
	    			
	    		command.setImatgePrincipal(informacioUtil.getImatgePrincipal());
				if (informacioUtil.getImatgePrincipal() != null){
					Arxiu arxiuPrincipal = oliFrontOfficeEjb.arxiuAmbId(informacioUtil.getImatgePrincipal());
					command.setArxiuImatgePrincipal(arxiuPrincipal);
				}
					
				command.setImatgeSecundaria(informacioUtil.getImatgeSecundaria());
				if (informacioUtil.getImatgeSecundaria() != null){
					command.setArxiuImatgeSecundaria(oliFrontOfficeEjb.arxiuAmbId(informacioUtil.getImatgeSecundaria()));
				}
				
	    		myModel.put("noticia", command);
    		}*/
    		
    		
    	} catch (Exception ex) {
    		throw new ServletException(ex);
    	}
    	
    	return new ModelAndView("web/gestioInfografies."+idioma+".jsp?idioma="+idioma, myModel);

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

}
