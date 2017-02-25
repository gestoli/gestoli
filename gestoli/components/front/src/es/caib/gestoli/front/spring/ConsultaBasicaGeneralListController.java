/**
 * ConsultaTrazabilitatController.java
 * 
 * Creada el 28 de juny de 2006, 13:19:22
 * &copy; Limit Tecnologies 2006
 */
package es.caib.gestoli.front.spring;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.propertyeditors.CustomBooleanEditor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.mvc.BaseCommandController;
import org.springframework.web.servlet.mvc.SimpleFormController;

import es.caib.gestoli.logic.interfaces.OliConsultaEjb;
import es.caib.gestoli.logic.model.ConsultaBasicaGeneral;
import es.caib.gestoli.logic.model.Establiment;

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
public class ConsultaBasicaGeneralListController extends SimpleFormController {
	
	private static Logger logger = Logger.getLogger(ModificarSortidaLotController.class);
	private OliConsultaEjb oliConsultaEjb;
	private HibernateTemplate hibernateTemplate;
	


    /**
     * Se llama cuando se aceptan las modificaciones del objeto. Solo
     * se ejecuta esta función en el caso de que se haya ejecutado la
     * validación sin producir ningún error.
     * 
     * @see AbstractController#handleRequest(HttpServletRequest, HttpServletResponse)
     */
    
    public ModelAndView onSubmit(
            HttpServletRequest request,
            HttpServletResponse response,
            Object command,
            BindException errors)
    throws ServletException {
    	Map myModel = new HashMap();
    	Collection consultaGeneral = null;
    	ConsultaBasicaGeneralCommand cbg = (ConsultaBasicaGeneralCommand)command;
    	Establiment establiment = (Establiment) request.getSession().getAttribute("establiment");
    	if(command!=null && (
    			cbg.getLotNom() != null
    		|| 	cbg.getPartidaNom()!= null
    		)){	
			ConsultaBasicaGeneral nova = new ConsultaBasicaGeneral();
			nova = this.fromConsultaBasicaGeneral(cbg);
	    	try {
		    	oliConsultaEjb.setHibernateTemplate(getHibernateTemplate());
		    	consultaGeneral = oliConsultaEjb.getConsultaBasicaGeneral(nova,establiment.getId());
	    	}catch (Exception e){
	    		e.getStackTrace();
	    	}
	    	if(consultaGeneral.isEmpty()){
	    		myModel.put("sensedades", "sensedades");
	    	}
	    	myModel.put("consultaGeneral", consultaGeneral);
    	}
    	myModel.put("formData", cbg);
    	
    	
    	return new ModelAndView("ConsultaBasicaGeneralList",myModel);
    }
    
        
    protected Map referenceData(
    		HttpServletRequest request,
			Object command,
			Errors errors) throws ServletException {
    	Map model = new HashMap();
    	ConsultaBasicaGeneralCommand formData = (ConsultaBasicaGeneralCommand)command;
    	if((ConsultaBasicaGeneralCommand)command !=null && (
    			//(ConsultaGeneralCommand)obj).get
    			((ConsultaBasicaGeneralCommand)command).getLotNomC()!=null
    			||	((ConsultaBasicaGeneralCommand)command).getPartidaNomC()!= null )){
    		
    		ConsultaBasicaGeneral cmd = new ConsultaBasicaGeneral();
        	cmd = fromConsultaBasicaGeneral((ConsultaBasicaGeneralCommand)command);
        	try{
        		Establiment establiment = (Establiment) request.getSession().getAttribute("establiment");
        		List<ConsultaBasicaGeneral> consultaGral = (List<ConsultaBasicaGeneral>) oliConsultaEjb.getConsultaBasicaGeneral(cmd,establiment.getId());
        		model.put("consultaGeneral", consultaGral);
        	}catch (Exception e){
        		e.getStackTrace();
        	}
        	model.put("formData", cmd);
    	}else{
    		model.put("formData", formData);
    	}
    	
    	
    	return model;
    	
        
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
    	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    	CustomDateEditor dateEditor = new CustomDateEditor(sdf, true);
    	binder.registerCustomEditor(java.util.Date.class, dateEditor);
    	binder.registerCustomEditor(Boolean.class, new CustomBooleanEditor("S", "N", true));
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


	/**
	 * Indica si la petición es de creación o no.
	 * @param request
	 * @return true si es una petición de creación o false si es de edición.
	 */
	private boolean isCreate(HttpServletRequest request) {
		String path = request.getServletPath();
		return (path.equals("/ConsultaBasicaGeneralList.html"));
	}


	public OliConsultaEjb getOliConsultaEjb() {
		return oliConsultaEjb;
	}

	public void setOliConsultaEjb(OliConsultaEjb oliConsultaEjb) {
		this.oliConsultaEjb = oliConsultaEjb;
	}

	private String missatge(String clave){
    	String valor= getMessageSourceAccessor().getMessage(clave);
    	return valor;
    }
	
	public ConsultaBasicaGeneral fromConsultaBasicaGeneral(ConsultaBasicaGeneralCommand c){
		ConsultaBasicaGeneral nc = new ConsultaBasicaGeneral();
		if(c.getLotNom()!=""){
			nc.setLotNom(c.getLotNom());	
		}
		if(c.getLotNomC()!=""){
			nc.setLotNomC(c.getLotNomC());	
		}
		if(c.getPartidaNom()!=""){
			nc.setPartidaNom(c.getPartidaNom());	
		}
		if(c.getPartidaNomC()!=""){
			nc.setPartidaNomC(c.getPartidaNomC());	
		}
		if(c.getZona()!=""){
			nc.setZona(c.getZona());	
		}
		if(c.getZonaC()!=""){
			nc.setZonaC(c.getZonaC());	
		}
		
		return nc;
		
		
	}

}
