package es.caib.gestoli.front.spring;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractFormController;
import org.springframework.web.servlet.mvc.BaseCommandController;
import org.springframework.web.servlet.mvc.SimpleFormController;

import es.caib.gestoli.front.util.Idioma;
import es.caib.gestoli.logic.interfaces.OliConsultaEjb;
import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;
import es.caib.gestoli.logic.model.Establiment;
import es.caib.gestoli.logic.util.Constants;

/**
 * <p>Controlador para las acciones de dar de alta o editar un
 * registro de tipos de información.</p>
 * <p>Los parámetros de la petición HTTP relevantes para este
 * controlador son:
 * <ul>
 *   <li><code>id</code>
 *    - Identificador del registro. Este parámetro es el que
 *      determina si se ha de mostrar la página de creación o la
 *      página de edición.</li>
 * </ul></p>
 * <p>No hay información para la vista:
 * <p>Este controlador distingue entre las peticiones del tipo
 * GET y las de tipo POST. Si la petición es de tipo POST
 * se ejecuta la acción de creación o de edición. Si la petición es de
 * tipo GET solo se muestra la página.
 *
 */
public class ConsultaOlivaTaulaDisponibleLlistatController extends SimpleFormController {
	private static Logger logger = Logger.getLogger(ConsultaOlivaTaulaDisponibleLlistatController.class);
	private OliConsultaEjb oliConsultaEjb;
	private OliInfraestructuraEjb oliInfraestructuraEjb;
    private String rolProductor;
    private String rolDoControl;
    private String desqualificat;
	private String pendentQualificar;
	private String qualificat;
	private String establimentSessionKey;
	private String trazaTipusOlivaTaulaDisponibleBota;
	private String trazaTipusOlivaTaulaDisponibleLote;
	private HibernateTemplate hibernateTemplate;
	private String esOlivaTaulaRequestKey;
	private String esAdministracioRequestKey;
	private String esDoGestorRequestKey;

	
    public ModelAndView onSubmit(
            HttpServletRequest request,
            HttpServletResponse response,
            Object command,
            BindException errors)
    throws ServletException {
    	ConsultaOlivaTaulaDisponibleLlistatCommand consulta = (ConsultaOlivaTaulaDisponibleLlistatCommand) command;
    	HttpSession session = request.getSession();
    	Map myModel = new HashMap();
    	try {
    		
    		if (consulta.getIdEstabliment() != null) myModel.put("idEstabliment", consulta.getIdEstabliment());
    		if (consulta.getData() != null){
    			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    			myModel.put("data", df.format(consulta.getData()));
    		}
    		
    		myModel.put("path", "consultar_olivaTaulaDisponible");
    		if (consulta.getFromEstabliment() != null && consulta.getFromEstabliment().equalsIgnoreCase("true")){	
	    		myModel.put("fromEstabliment", consulta.getFromEstabliment());
	    		
	    		if(request.getParameter("fromEstabliment")!= null){
	    			Establiment establiment =(Establiment)session.getAttribute(establimentSessionKey);
	    			if (establiment==null){
	    				if (consulta.getIdEstabliment() != null){
	    					oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
	        				establiment =(Establiment)oliInfraestructuraEjb.establimentAmbId(consulta.getIdEstabliment());        				
	        			}
	        		}
	    			if(establiment.getNom()!= null && !establiment.getNom().equalsIgnoreCase("")){
	    				myModel.put("path", "consulta_olivaTaulaDisponibleEstabliment");
	    				myModel.put("path_extension1", establiment.getNom());
	    			}
	    		}
    		}
    		

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
        HttpSession session = request.getSession();

        try {
        	ConsultaOlivaTaulaDisponibleLlistatCommand consulta = (ConsultaOlivaTaulaDisponibleLlistatCommand) command;
        	
        	
        	Long idEstabliment = null;
    		if (request.getAttribute(esOlivaTaulaRequestKey) != null) {
    			Establiment establiment = (Establiment)request.getSession().getAttribute(establimentSessionKey);
    			if (establiment != null) {
    				idEstabliment = establiment.getId();
    			}
    		}else if (request.getAttribute(esDoGestorRequestKey) != null || request.getAttribute(esAdministracioRequestKey) != null || request.isUserInRole(rolDoControl)) {
    			if(request.getParameter("idEstabliment")!= null && !request.getParameter("idEstabliment").equals("") ){
        			idEstabliment = Long.valueOf(request.getParameter("idEstabliment"));
        		}
    		}

        	String fromEstabliment = request.getParameter("fromEstabliment");
        	String data = request.getParameter("data");
        		     
		   	
		   	Date datafi = new Date();
		   	if (data != null && !data.equals("")) {
		   		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		   		datafi = df.parse(data);
		   	}
		   	// Cerca tots las diposits de un establecimiento no vacios y que pertenecen a unas determinadas categorias
		   	oliConsultaEjb.setHibernateTemplate(getHibernateTemplate());
		   	Collection listaBotas = oliConsultaEjb.findBotasActivasByEstablecimientoAndData(idEstabliment, datafi);
		   	// Cerca tots los lotes de un establecimiento no vacios y que pertenecen a unas determinadas categorias
	        oliConsultaEjb.setHibernateTemplate(getHibernateTemplate());
	        Collection listaLotes = oliConsultaEjb.findLotsOlivaTaulaDisponiblesByEstablecimientoAndData(idEstabliment, datafi);

	        
    		if(!errors.hasErrors()){
    			myModel.put("llistatLotes", listaLotes);
    			myModel.put("llistatBotes", listaBotas);
    			myModel.put("trazaTipusOlivaTaulaDisponibleBota", trazaTipusOlivaTaulaDisponibleBota);
    			myModel.put("trazaTipusOlivaTaulaDisponibleLote", trazaTipusOlivaTaulaDisponibleLote);
    		}
	    			    		
     
        	consulta.setIdEstabliment(idEstabliment);
    		consulta.setFromEstabliment(fromEstabliment);
    		if (data != null && !data.equals("")){
    			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    			consulta.setData(df.parse(data));
    		} else {
    			consulta.setData(new Date());
    		}
    			
    			
        	myModel.put("path", "consultar_olivaTaulaDisponible");
        	
        	if(consulta.getFromEstabliment()!= null && consulta.getFromEstabliment().equalsIgnoreCase("true")){
    			Establiment establiment =(Establiment)session.getAttribute(establimentSessionKey);
    			if (establiment==null){
    				if (idEstabliment != null){
    					oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
        				establiment =(Establiment)oliInfraestructuraEjb.establimentAmbId(idEstabliment);        				
        			}
        		}
    			if(establiment.getNom()!= null && !establiment.getNom().equalsIgnoreCase("")){
    				myModel.put("path", "consulta_olivaTaulaDisponibleEstabliment");
    				myModel.put("path_extension1", establiment.getNom());
    			}
    		}
        	
        } catch (Exception ex) {
        	logger.error("Error obtenint llistat de oli disponible", ex);
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
    	ConsultaOlivaTaulaDisponibleLlistatCommand consulta = (ConsultaOlivaTaulaDisponibleLlistatCommand)request.getSession().getAttribute("consulta");
    	if (consulta == null) {
    		consulta = new ConsultaOlivaTaulaDisponibleLlistatCommand();
    	} else { 
    		request.getSession().removeAttribute("consulta");
    	}
    	
        
    	try{
    		Long idEstabliment = null;
    		if (request.getAttribute(esOlivaTaulaRequestKey) != null) {
    			Establiment establiment = (Establiment)request.getSession().getAttribute(establimentSessionKey);
    			if (establiment != null) {
    				idEstabliment = establiment.getId();
    			}
    		}else if (request.getAttribute(esDoGestorRequestKey) != null || request.getAttribute(esAdministracioRequestKey) != null || request.isUserInRole(rolDoControl)) {
    			if(request.getParameter("idEstabliment")!= null && !request.getParameter("idEstabliment").equals("") ){
        			idEstabliment = Long.valueOf(request.getParameter("idEstabliment"));
        		}
    		}
    		
    		
    		if(idEstabliment != null) consulta.setIdEstabliment(idEstabliment);
    		
    		if(request.getParameter("fromEstabliment") != null ) {
    			consulta.setFromEstabliment(request.getParameter("fromEstabliment"));
    		}
    		if(request.getParameter("data") != null ) {
    			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    			consulta.setData(df.parse(request.getParameter("data")));
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
     * Injecció de la dependència consultaEjb
     *
     * @param consultaEjb La classe a injectar.
     */
  
    public void setOliConsultaEjb(OliConsultaEjb oliConsultaEjb) {
        this.oliConsultaEjb = oliConsultaEjb;
    }
	
    /**
     * Inyección de la dependencia oliInfraestructuraEjb
     * @param oliInfraestructuraEjb La clase a inyectar.
     */
    public void setOliInfraestructuraEjb(OliInfraestructuraEjb oliInfraestructuraEjb) {
        this.oliInfraestructuraEjb = oliInfraestructuraEjb;
    }


	/**
	 * Metodo 'setRolProductor'
	 * @param rolProductor el rolProductor a modificar
	 */
	public void setRolProductor(String rolProductor) {
		this.rolProductor = rolProductor;
	}
	/**
	 * Injecció de la dependència desqualificat
	 * @param desqualificat La classe a injectar.
	 */
    
	private String missatge(String clave, HttpServletRequest request) {
		String valor = getApplicationContext().getMessage(clave, null, Idioma.getLocale(request));
		return valor;
	}
	/**
	 * Metodo 'setEstablimentSessionKey'
	 * @param establimentSessionKey
	 */
	public void setEstablimentSessionKey(String establimentSessionKey) {
		this.establimentSessionKey = establimentSessionKey;
	}

	/**
	 * Injecció de la dependència trazaTipusOlivaTaulaDisponibleBota
	 *
	 * @param trazaTipusOlivaTaulaDisponibleBota La classe a injectar.
	 */
	public void setTrazaTipusOlivaTaulaDisponibleBota(String trazaTipusOlivaTaulaDisponibleBota) {
		this.trazaTipusOlivaTaulaDisponibleBota = trazaTipusOlivaTaulaDisponibleBota;
	}
	
	/**
	 * Injecció de la dependència trazaTipusOlivaTaulaDisponiblePartida
	 * @param trazaTipusOlivaTaulaDisponiblePartida La classe a injectar.
	 */
	public void setTrazaTipusOlivaTaulaDisponibleLote(String trazaTipusOlivaTaulaDisponibleLote) {
		this.trazaTipusOlivaTaulaDisponibleLote = trazaTipusOlivaTaulaDisponibleLote;
	}

	public void setEsOlivaTaulaRequestKey(String esOlivaTaulaRequestKey) {
		this.esOlivaTaulaRequestKey = esOlivaTaulaRequestKey;
	}

	/**
	 * Injecció de la dependència esAdministracioRequestKey
	 * @param esAdministracioRequestKey La classe a injectar.
	 */
	public void setEsAdministracioRequestKey(String esAdministracioRequestKey) {
		this.esAdministracioRequestKey = esAdministracioRequestKey;
	}


	/**
	 * Injecció de la dependència esDoGestorRequestKey
	 * @param esDoGestorRequestKey La classe a injectar.
	 */
	public void setEsDoGestorRequestKey(String esDoGestorRequestKey) {
		this.esDoGestorRequestKey = esDoGestorRequestKey;
	}

	/**
	 * @param rolDoControl the rolDoControl to set
	 */
	public void setRolDoControl(String rolDoControl) {
		this.rolDoControl = rolDoControl;
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
