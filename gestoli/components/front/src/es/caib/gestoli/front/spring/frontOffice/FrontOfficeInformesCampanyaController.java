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

import es.caib.gestoli.front.spring.InformeCampanyaCommand;
import es.caib.gestoli.logic.interfaces.OliFrontOfficeEjb;
import es.caib.gestoli.logic.model.Arxiu;
import es.caib.gestoli.logic.model.InformeCampanya;

public class FrontOfficeInformesCampanyaController extends AbstractController {
	
	
	private static Logger logger = Logger.getLogger(FrontOfficeInformesCampanyaController.class);
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
    		Collection informesProduccio = oliFrontOfficeEjb.informeCampanyaCercaPerTipus(false);
    		myModel.put("informesProduccio", informesProduccio);
    		
    		oliFrontOfficeEjb.setHibernateTemplate(getHibernateTemplate());
    		Collection informesComercialitzacio = oliFrontOfficeEjb.informeCampanyaCercaPerTipus(true);
    		myModel.put("informesComercialitzacio", informesComercialitzacio);
    		
    		/*Set<InformeCampanyaCommand> commands = new HashSet<InformeCampanyaCommand>(0);
 
    		for (Object informe : informesProduccio){
    			InformeCampanya informesCampanya = (InformeCampanya)informacio;
    			InformeCampanyaCommand command = new InformeCampanyaCommand();
    			command.fromInformesCampanya(informesCampanya);
    			
    			command.setImatgePrincipal(informesCampanya.getImatgePrincipal());
				if (informesCampanya.getImatgePrincipal() != null){
					Arxiu arxiuPrincipal = oliFrontOfficeEjb.arxiuAmbId(informesCampanya.getImatgePrincipal());
					command.setArxiuImatgePrincipal(arxiuPrincipal);
				}
				
				command.setImatgeSecundaria(informesCampanya.getImatgeSecundaria());
				if (informesCampanya.getImatgeSecundaria() != null){
					command.setArxiuImatgeSecundaria(oliFrontOfficeEjb.arxiuAmbId(informesCampanya.getImatgeSecundaria()));
				}
				
				commands.add(command);
    		}
    		myModel.put("informacionsUtils", informacionsUtils);
    		*/
    		
    		
    		/*
    		if (request.getParameter("id") != null){
    			Long idInformacio = Long.valueOf(request.getParameter("id"));
    		
	    		oliFrontOfficeEjb.setHibernateTemplate(getHibernateTemplate());
	    		InformesCampanya informesCampanya = oliFrontOfficeEjb.informesCampanyaAmbId(idInformacio);
	    		
	    		InformesCampanyaCommand command = new InformesCampanyaCommand();
	 
	    		command.fromInformesCampanya(informesCampanya);
	    			
	    		command.setImatgePrincipal(informesCampanya.getImatgePrincipal());
				if (informesCampanya.getImatgePrincipal() != null){
					Arxiu arxiuPrincipal = oliFrontOfficeEjb.arxiuAmbId(informesCampanya.getImatgePrincipal());
					command.setArxiuImatgePrincipal(arxiuPrincipal);
				}
					
				command.setImatgeSecundaria(informesCampanya.getImatgeSecundaria());
				if (informesCampanya.getImatgeSecundaria() != null){
					command.setArxiuImatgeSecundaria(oliFrontOfficeEjb.arxiuAmbId(informesCampanya.getImatgeSecundaria()));
				}
				
	    		myModel.put("noticia", command);
    		}
    		*/
    		
    	} catch (Exception ex) {
    		throw new ServletException(ex);
    	}
    	
    	return new ModelAndView("web/informescampanya."+idioma+".jsp?idioma="+idioma, myModel);

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
