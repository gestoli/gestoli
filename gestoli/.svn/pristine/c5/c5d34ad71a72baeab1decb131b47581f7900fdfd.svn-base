package es.caib.gestoli.front.spring;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractFormController;
import org.springframework.web.servlet.mvc.BaseCommandController;
import org.springframework.web.servlet.mvc.SimpleFormController;

import es.caib.gestoli.front.util.Idioma;
import es.caib.gestoli.front.util.Util;
import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;
import es.caib.gestoli.logic.model.Establiment;
import es.caib.gestoli.logic.model.Olivicultor;
import es.caib.gestoli.logic.model.Usuari;

public class QuadernCampLlistatController extends SimpleFormController { //implements ApplicationContextAware {
	
	private static Logger logger = Logger.getLogger(QuadernCampLlistatController.class);
	//private String listView;
    //private String deleteView;
	private OliInfraestructuraEjb oliInfraestructuraEjb;
    //private ApplicationContext applicationContext;
    private String rolOlivicultor;
    private String rolDoGestor;
	private String rolDoControl;
    private String usuariSessionKey;
	private HibernateTemplate hibernateTemplate;

	
	 public ModelAndView onSubmit(
	            HttpServletRequest request,
	            HttpServletResponse response,
	            Object command,
	            BindException errors)
	    throws ServletException {
	    	QuadernCampLlistatCommand consulta = (QuadernCampLlistatCommand) command;
	    	HttpSession session = request.getSession();
        	Usuari usuari = (Usuari)session.getAttribute(usuariSessionKey);
        	
	    	Map myModel = new HashMap();
	    	try {
	    		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	    		if (consulta.getDataInici() != null) myModel.put("dataInici", sdf.format(consulta.getDataInici()));
	    		if (consulta.getDataFi() != null) myModel.put("dataFi", sdf.format(consulta.getDataFi()));

	        	if (!request.isUserInRole(rolOlivicultor) && consulta.getIdOlivicultor() != null) myModel.put("idOlivicultor", consulta.getIdOlivicultor());
	    		
	    		myModel.put("path", "quadernCamp");
	    		
	    	} catch (Exception ex) {
				throw new ServletException(ex);
			}
	    	
			return new ModelAndView(getSuccessView(), myModel);
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

        try {
        	QuadernCampLlistatCommand consulta = (QuadernCampLlistatCommand) command;
       
        	HttpSession session = request.getSession();
        	Usuari usuari = (Usuari)session.getAttribute(usuariSessionKey);

        	Long idOlivicultor = null;
        	if (request.isUserInRole(rolOlivicultor)){

        		if (usuariSessionKey != null){
        			idOlivicultor = oliInfraestructuraEjb.olivicultorUsuari(usuari.getId()).getId();
        		}
        	} else if (request.isUserInRole(rolDoGestor) || request.isUserInRole(rolDoControl)){ 
        		if ( (request.getParameter("idOlivicultor") != null) && (!"".equals(request.getParameter("idOlivicultor"))) ){
        			idOlivicultor = new Long(Long.parseLong(request.getParameter("idOlivicultor")));
        		}
        	}
        	
        	oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
        	Collection llistatOlivicultors = new ArrayList();
        	if (request.isUserInRole(rolDoGestor) || request.isUserInRole(rolDoControl)){ 
        		llistatOlivicultors = oliInfraestructuraEjb.quadernCampCercaOlivicultors();
        	} else if (request.isUserInRole(rolOlivicultor) && idOlivicultor != null) {
        		llistatOlivicultors.add(oliInfraestructuraEjb.olivicultorAmbId(idOlivicultor));
        	}
        	myModel.put("llistatOlivicultors", llistatOlivicultors);

    		
        	if (Util.isDataCorrecta(request.getParameter("dataInici"), "dd/MM/yyyy")
        			&& Util.isDataCorrecta(request.getParameter("dataFi"), "dd/MM/yyyy")) {
        		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        		        		
        		Date dataInici = sdf.parse(request.getParameter("dataInici"));
        		Date dataFi = sdf.parse(request.getParameter("dataFi"));        		
        	
        		Collection llistat = new ArrayList();
        		if (idOlivicultor != null){
        			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
            		llistat = oliInfraestructuraEjb.quadernCampCercaTotsPerOlivicultor(idOlivicultor, dataInici, dataFi);
        		
	        		if(!errors.hasErrors()){
	        			myModel.put("llistat", llistat);
	        		}
        		}
        		
        		consulta.setDataInici(dataInici);
        		consulta.setDataFi(dataFi);
        		consulta.setIdOlivicultor(idOlivicultor);
        		
        	}
        		
        	myModel.put("path", "quadernCamp");
        	
        } catch (Exception ex) {
        	logger.error("Error obtenint llistat de quaderns de camp", ex);
        }
        return myModel;
    }
    
    /**
     * En el cas de que sigui una edició retorna l'objecte omplit amb
     * les dades actuals del registre. En caso de que sigui una creació
     * retorna l'objecte buit.
     * 
     * @see AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
     */
    protected Object formBackingObject(HttpServletRequest request) throws ServletException {
    	QuadernCampLlistatCommand consulta = (QuadernCampLlistatCommand)request.getSession().getAttribute("consulta");
    	if (consulta == null) 
    		consulta = new QuadernCampLlistatCommand();
    	else 
    		request.getSession().removeAttribute("consulta");
    	
    	try{
    		Long idOlivicultor = null;
    		Date dataInici = null;
    		Date dataFi = null;
    		
    		HttpSession session = request.getSession();
    		Usuari usuari = (Usuari)session.getAttribute(usuariSessionKey);
    		
        	if (request.isUserInRole(rolOlivicultor)){
        		if (usuariSessionKey != null){
        			idOlivicultor = oliInfraestructuraEjb.olivicultorUsuari(usuari.getId()).getId();
        		}
        	} else if (request.isUserInRole(rolDoGestor) || request.isUserInRole(rolDoControl)){ 
        		if ( (request.getParameter("idOlivicultor") != null) && (!"".equals(request.getParameter("idOlivicultor"))) ){
        			idOlivicultor = new Long(Long.parseLong(request.getParameter("idOlivicultor")));
        		}
        	}
        	
    		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    		Calendar cal = Calendar.getInstance();
    		
    		if(request.getParameter("dataFi") != null && !((String)request.getParameter("dataFi")).equals("")) {
    			dataFi = sdf.parse(request.getParameter("dataFi"));
    		} else {
    			dataFi = cal.getTime();
    		}
    		
    		if(request.getParameter("dataInici") != null && !((String)request.getParameter("dataInici")).equals("")) {
    			dataInici = sdf.parse(request.getParameter("dataInici"));
    		} else {
    			cal.add( Calendar.MONTH, -1 );
    			dataInici = cal.getTime();
    		}
    		
    		
    		if(dataInici != null) consulta.setDataInici(dataInici);
    		if(dataInici != null) consulta.setDataFi(dataFi);			
    		if(idOlivicultor != null) consulta.setIdOlivicultor(idOlivicultor);
    		
    		Collection llistatOlivicultors = new ArrayList();
    		if (request.isUserInRole(rolDoGestor) || request.isUserInRole(rolDoControl)){ 
        		llistatOlivicultors = oliInfraestructuraEjb.quadernCampCercaOlivicultors();
        	} else if (request.isUserInRole(rolOlivicultor) && idOlivicultor != null) {
        		llistatOlivicultors.add(oliInfraestructuraEjb.olivicultorAmbId(idOlivicultor));
        	}
    		request.setAttribute("llistatOlivicultors", llistatOlivicultors);
    		
    		
    		Collection llistat = new ArrayList();
    		if (idOlivicultor != null){
    			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
        		llistat = oliInfraestructuraEjb.quadernCampCercaTotsPerOlivicultor(idOlivicultor, dataInici, dataFi);
        		request.setAttribute("llistat", llistat);
        	}
    		
    		
    	}catch (Exception e) {
			logger.error("EXCEPTION", e);
		}   	
    	
    	return consulta;
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
    }	
	    
	

	/**
	 * @param rolOlivicultor the rolDoControl to set
	 */
	public void setRolOlivicultor(String rolOlivicultor) {
		this.rolOlivicultor = rolOlivicultor;
	}
    
    /**
	 * Injecció de la dependència rolDoGestor
	 * @param rolDoGestor La classe a injectar.
	 */
	public void setRolDoGestor(String rolDoGestor) {
		this.rolDoGestor = rolDoGestor;
	}
	
	
	/**
	 * @param rolDoControl the rolDoControl to set
	 */
	public void setRolDoControl(String rolDoControl) {
		this.rolDoControl = rolDoControl;
	}
    
    
    /**
     * Inyección de la dependencia oliInfraestructuraEjb
     * @param oliInfraestructuraEjb La clase a inyectar.
     */
    public void setOliInfraestructuraEjb(OliInfraestructuraEjb oliInfraestructuraEjb) {
        this.oliInfraestructuraEjb = oliInfraestructuraEjb;
    }

    /**
	 * Injecció de la dependència usuariSessionKey
	 *
	 * @param usuariSessionKey La classe a injectar.
	 */
	public void setUsuariSessionKey(String usuariSessionKey) {
		this.usuariSessionKey = usuariSessionKey;
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
