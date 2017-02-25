package es.caib.gestoli.front.spring.frontOffice;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.propertyeditors.CustomBooleanEditor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractFormController;
import org.springframework.web.servlet.mvc.BaseCommandController;
import org.springframework.web.servlet.mvc.SimpleFormController;

import es.caib.gestoli.front.spring.ConsultaTrazabilitatResumidaController;
import es.caib.gestoli.logic.interfaces.OliFrontOfficeEjb;
import es.caib.gestoli.logic.model.Lot;
import es.caib.gestoli.logic.util.TrazabilitatResumida;

public class FrontOfficeInformacioOliFormController extends SimpleFormController {
	
	
	private static Logger logger = Logger.getLogger(ConsultaTrazabilitatResumidaController.class);
	
	private OliFrontOfficeEjb oliFrontOfficeEjb;
	private HibernateTemplate hibernateTemplate;

	
	/**
	 * Se llama cuando se aceptan las modificaciones del objeto. Solo
	 * se ejecuta esta función en el caso de que se haya ejecutado la
	 * validación sin producir ningún error.
	 * @see SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.validation.BindException)
	 */
	public ModelAndView onSubmit(
			HttpServletRequest request,
			HttpServletResponse response,
			Object command,
			BindException errors)
	throws ServletException {
		
		FrontOfficeInformacioOliCommand frontOfficeInformacioOliCommand = (FrontOfficeInformacioOliCommand)command;
		
    	String forward = getSuccessView();
    	Map myModel = new HashMap();
    	String idioma = request.getParameter("idioma");
    	

//    	Long establimentId = null;
//    	if (request.getParameter("establimentId")!=null && !request.getParameter("establimentId").equals("")){ 
//    		establimentId = new Long(request.getParameter("establimentId"));
//    	}
    	String lletres = frontOfficeInformacioOliCommand.getLletra();//request.getParameter("lletres");
    	String numeros = frontOfficeInformacioOliCommand.getNumero();//request.getParameter("numeros");
    	Lot lot = null;
    	String id = "";
    	try {
			oliFrontOfficeEjb.setHibernateTemplate(getHibernateTemplate());
			lot = oliFrontOfficeEjb.findEtiquetesLotByEtiqueta(lletres, Integer.valueOf(numeros)).getLot();
			id = String.valueOf(lot.getId());
		} catch (Exception e) {
			id = "";
		}
		
    	//String tipus = request.getParameter("tipus");
    	/*String id = request.getParameter("id");
    	try  {
    		if (id != null){
	    		oliFrontOfficeEjb.setHibernateTemplate(getHibernateTemplate());
	    		TrazabilitatResumida trazabilitat = oliFrontOfficeEjb.trazabilitatResumidaOliDisponibleLote(id);
	    		myModel.put("trazabilitat", trazabilitat.getArbre());
    		}else{
    			myModel.put("trazabilitat", null);
    		}
    		
    		//myModel.put("path", "consulta_DisponibleOliLoteTraza");
    		
    	} catch (Exception ex) {
    		myModel.put("trazabilitat", null);
    	}*/
    	    	
    	//String url = request.getRequestURI();
    	forward = "redirect:cercaid."+idioma+".html?id=" + id + "&lletres=" + lletres + "&numeros=" + numeros + "&idioma=" + idioma;
    	return new ModelAndView(forward, myModel);
    }
	
	/**
	 * Retorna todos los datos del modelo necesarios para mostrar
	 * el formulario de inserción (LOVs y cosas de esas) si no hay.
	 * @see SimpleFormController#referenceData(javax.servlet.http.HttpServletRequest, java.lang.Object, org.springframework.validation.Errors)
	 */
	protected Map referenceData(
			HttpServletRequest request,
			Object command,
			Errors errors) throws Exception {
		
		Map myModel = new HashMap();
		//myModel.put("path", "qualitat_APPCC.PlaVerificacio");
		
		myModel.put("formData", command);
		
    	String id = request.getParameter("id");
    	if (!"".equals(id)){ 
    		myModel.put("id", id);	
    	}
    	
    	String lletres = request.getParameter("lletres");
    	if (!"".equals(lletres)){ 
    		myModel.put("lletres", lletres); 
    	}
    	
    	String numeros = request.getParameter("numeros");
    	if (!"".equals(numeros)){ 
    		myModel.put("numeros", numeros); 
    	}
    	
    	try  {
    		if ((!"".equals(id)) && (id != null) ){
	    		oliFrontOfficeEjb.setHibernateTemplate(getHibernateTemplate());
	    		TrazabilitatResumida trazabilitat = oliFrontOfficeEjb.trazabilitatResumidaOliDisponibleLote(id);
	    		myModel.put("trazabilitat", trazabilitat.getArbre());
    		}else{
    			myModel.put("trazabilitat", null);
    		}
    	} catch (Exception ex) {
    		myModel.put("trazabilitat", null);
    	}
    	
    	return myModel;
		
	}
    
	/**
	 * En caso de que sea una edición retorna el objeto rellenado con
	 * los datos actuales del registro. En caso de que sea una creación
	 * retorna el objeto vacío.
	 * @see AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
	 */
	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		try {
			FrontOfficeInformacioOliCommand command = new FrontOfficeInformacioOliCommand();
			return command;
		} catch (Exception ex) {
			throw new ServletException("Error cridant l'EJB", ex);
		}
	}
	
	/**
	 * Configuración del <i>binder</i>. Si hay campos en el <i>command</i>
	 * con tipos que no sean <i>String</i> se ha de configurar su
	 * correspondiente binder aquí.
	 * @see BaseCommandController#initBinder(javax.servlet.http.HttpServletRequest, org.springframework.web.bind.ServletRequestDataBinder)
	 */
	protected void initBinder(
			HttpServletRequest request,
			ServletRequestDataBinder binder)
	throws Exception {
		binder.registerCustomEditor(
				Boolean.class,
				new CustomBooleanEditor("S", "N", true));
		binder.registerCustomEditor(
	    		Double.class,
	    		new CustomNumberEditor(Double.class, true));
		binder.registerCustomEditor(
	    		Long.class,
	    		new CustomNumberEditor(Long.class, true));
		binder.registerCustomEditor(
	    		Integer.class,
	    		new CustomNumberEditor(Integer.class, true));
        binder.registerCustomEditor(
        		Date.class,
        		new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), true));
	}
			
    /**
     * Injecció de la dependència frontOfficeEjb
     *
     * @param frontOfficeEjb La classe a injectar.
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
