package es.caib.gestoli.front.spring;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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

import es.caib.gestoli.front.util.BasicData;
import es.caib.gestoli.front.util.Idioma;
import es.caib.gestoli.front.util.Util;
import es.caib.gestoli.logic.interfaces.OliConsultaEjb;
import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;
import es.caib.gestoli.logic.model.Diposit;
import es.caib.gestoli.logic.model.Establiment;
import es.caib.gestoli.logic.model.Trasllat;
import es.caib.gestoli.logic.model.VarietatOli;

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
public class AltresConsultesTrazabilidadPartidaController extends SimpleFormController {

    /*
     * Objeto para escribir mensajes de log.
     */
	private static Logger logger = Logger.getLogger(AltresConsultesTrazabilidadPartidaController.class);

    /*
     * Servicio para acceder a las funcionalidades de la aplicación.
     */
	private OliConsultaEjb oliConsultaEjb;
	private OliInfraestructuraEjb oliInfraestructuraEjb;
	private String campanyaSessionKey;
	private String trazaTipusPartidaOli;
	private HibernateTemplate hibernateTemplate;
	

    public ModelAndView onSubmit(
            HttpServletRequest request,
            HttpServletResponse response,
            Object command,
            BindException errors)
    throws ServletException {
    	AltresConsultesTrazabilidadPartidaCommand consulta = (AltresConsultesTrazabilidadPartidaCommand) command;
    	Map myModel = new HashMap();
    	HttpSession session = request.getSession();
    	try {
    		
    		if (consulta.getIdEstabliment() != null) myModel.put("idEstabliment", consulta.getIdEstabliment());    		
    		myModel.put("path", "consulta_trazabilidadPartida");		
    		

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
        	AltresConsultesTrazabilidadPartidaCommand consulta = (AltresConsultesTrazabilidadPartidaCommand) command;
        	Long idEstabliment = null;
        	if(request.getParameter("idEstabliment")!= null && !request.getParameter("idEstabliment").equals("") ){
        		idEstabliment = Long.valueOf(request.getParameter("idEstabliment"));
        	}
        	
        	
        	
    		oliConsultaEjb.setHibernateTemplate(getHibernateTemplate());
    		Collection lista = oliConsultaEjb.findDipositsActiusNoVaciosByEstablecimientoAndCategorias(idEstabliment, null);
    		    		        		
    		if(!errors.hasErrors() && lista!= null && lista.size()>0){
    			myModel.put("llistat", lista);
    			myModel.put("trazaTipusPartidaOli", trazaTipusPartidaOli);   
    		}
    	
    		oliConsultaEjb.setHibernateTemplate(getHibernateTemplate());
    		Collection listaLot = oliConsultaEjb.findLotsNoVendidosByEstablecimientoAndCategorias(idEstabliment, null);
    		if(!errors.hasErrors() && listaLot!= null && listaLot.size()>0){
    			myModel.put("llistatLot", listaLot);
    		}
    		
    		consulta.setIdEstabliment(idEstabliment);
        	
        		
        	
        	//Establecimierntos
            Collection establecimientos = new ArrayList();
            Long campanyaId = (Long)request.getSession().getAttribute(campanyaSessionKey);            
            oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
            establecimientos = oliInfraestructuraEjb.establimentCercaTotsByCampanya(campanyaId);
            myModel.put("establecimientos", establecimientos);
        	
        	
        	myModel.put("path", "consulta_trazabilidadPartida");
        	
        	
        } catch (Exception ex) {
        	logger.error("Error obtenint llistat de oli elaborat", ex);
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
    	AltresConsultesTrazabilidadPartidaCommand consulta = (AltresConsultesTrazabilidadPartidaCommand)request.getSession().getAttribute("consulta");
    	if (consulta == null) 
    		consulta = new AltresConsultesTrazabilidadPartidaCommand();
    	else 
    		request.getSession().removeAttribute("consulta");
    	
        
    	try{
    		Long idEstabliment = null;    		
    		if(request.getParameter("idEstabliment")!= null && !request.getParameter("idEstabliment").equals("") ){
    			idEstabliment = Long.valueOf(request.getParameter("idEstabliment"));
    		}    				
    		if(idEstabliment != null) consulta.setIdEstabliment(idEstabliment);    		
    		
    		
    	} catch (Exception e) {
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
	 * Injecció de la dependència campanyaSessionKey
	 *
	 * @param campanyaSessionKey La classe a injectar.
	 */
	public void setCampanyaSessionKey(String campanyaSessionKey) {
		this.campanyaSessionKey = campanyaSessionKey;
	}
	
	/**
	 * Metodo 'setTrazaTipusPartidaOli'
	 * @param trazaTipusPartidaOli
	 */
	public void setTrazaTipusPartidaOli(String trazaTipusPartidaOli) {
		this.trazaTipusPartidaOli = trazaTipusPartidaOli;
	}
}
