package es.caib.gestoli.front.spring;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import es.caib.gestoli.front.util.Util;
import es.caib.gestoli.logic.interfaces.OliConsultaEjb;

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
public class ConsultaGeneralOliComercialitzatController extends SimpleFormController {

    /*
     * Objeto para escribir mensajes de log.
     */
	private static Logger logger = Logger.getLogger(ConsultaGeneralOliComercialitzatController.class);

    /*
     * Servicio para acceder a las funcionalidades de la aplicación.
     */
	private OliConsultaEjb oliConsultaEjb;
	private String rolDoGestor;
	private String rolAdministracio;
	private String rolDoControlador;
	private HibernateTemplate hibernateTemplate;

    public ModelAndView onSubmit(
            HttpServletRequest request,
            HttpServletResponse response,
            Object command,
            BindException errors)
    throws ServletException {
    	ConsultaGeneralOliComercialitzatCommand consulta = (ConsultaGeneralOliComercialitzatCommand) command;
    	Map myModel = new HashMap();
    	try {
    		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    		if (consulta.getDataInici() != null) myModel.put("dataInici", sdf.format(consulta.getDataInici()));
    		if (consulta.getDataFi() != null) myModel.put("dataFi", sdf.format(consulta.getDataFi()));
    		if (consulta.getBuscar() != null) myModel.put("buscar", consulta.getBuscar());
    		myModel.put("path", "consultar_GeneralOliComercialitzat");
    		

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
        	ConsultaGeneralOliComercialitzatCommand consulta = (ConsultaGeneralOliComercialitzatCommand) command;
        	consulta.setBuscar("true");
    		
    		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    		Date dataInici = null;
    		Date dataFi = null;
    		HashMap establecimientos = null;
			HashMap oliComercialitzatMap = null;
			double total = 0;
    		if(request.getParameter("buscar")!= null && request.getParameter("buscar").equals("true")){
    			
    			if (request.isUserInRole(rolDoGestor) || request.isUserInRole(rolAdministracio) || request.isUserInRole(rolDoControlador)) {
        			if (Util.isDataCorrecta(request.getParameter("dataInici"), "dd/MM/yyyy") || Util.isDataCorrecta(request.getParameter("dataFi"), "dd/MM/yyyy")) {
    	        		if(request.getParameter("dataInici")!= null && !request.getParameter("dataInici").equals("")){
    	        			dataInici = sdf.parse(request.getParameter("dataInici"));
    	        			consulta.setDataInici(dataInici);
    	        		}
    	        		if(request.getParameter("dataFi")!= null && !request.getParameter("dataFi").equals("")){
    	        			dataFi = sdf.parse(request.getParameter("dataFi"));
    	        			consulta.setDataFi(dataFi);
    	        		}
    	        	}  
        		
        		   List maps = new ArrayList();
    			   oliConsultaEjb.setHibernateTemplate(getHibernateTemplate());
    			   maps =  oliConsultaEjb.getTotalOliComercialitzatByEstablecimiento(dataInici, dataFi);
    			   establecimientos = (HashMap)maps.get(0);
    			   oliComercialitzatMap = (HashMap)maps.get(1);
    			   
    			   for(Iterator itoliComercialitzat=oliComercialitzatMap.values().iterator();itoliComercialitzat.hasNext();){
    				   total+=((Double)itoliComercialitzat.next()).doubleValue();
    			   }
        		}
        			
	        	if(!errors.hasErrors()){ 
	        		//Ordenamos los establecimientos
	        		List establecimientosList = new ArrayList();
	        		for(Iterator itEstablecimientos=establecimientos.values().iterator();itEstablecimientos.hasNext();){
	        			establecimientosList.add(itEstablecimientos.next());
	        		}
	        		Collections.sort(establecimientosList);
	        		myModel.put("establecimientos", establecimientosList);
	    			myModel.put("oliComercialitzatMap", oliComercialitzatMap); 
	    			myModel.put("total", Double.valueOf(String.valueOf(total))); 
        		}        		
    		}
    		myModel.put("path", "consultar_GeneralOliComercialitzat");
        	
        	
        } catch (Exception ex) {
        	logger.error("Error obtenint consulta general", ex);
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
    	ConsultaGeneralOliComercialitzatCommand consulta = (ConsultaGeneralOliComercialitzatCommand)request.getSession().getAttribute("consulta");
    	if (consulta == null) 
    		consulta = new ConsultaGeneralOliComercialitzatCommand();
    	else 
    		request.getSession().removeAttribute("consulta");
    	
        
    	try{    		
    		Date dataInici = null;
    		Date dataFi = null;
    		    		
    		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    		Date data = Calendar.getInstance().getTime();
    		
    		if(request.getParameter("dataInici") != null) {
    			dataInici = sdf.parse(request.getParameter("dataInici"));
    		} 
    		else {
    			//dataInici = data;
    		}
    		
    		if(request.getParameter("dataFi") != null && !((String)request.getParameter("dataFi")).equals("")) {
    			dataFi = sdf.parse(request.getParameter("dataFi"));
    		} else {
    			dataFi = data;
    		}
    		
    					    		
    		if(dataInici != null) consulta.setDataInici(dataInici);
    		if(dataFi != null)consulta.setDataFi(dataFi);			
    		if(request.getParameter("buscar")!= null && request.getParameter("buscar").equals("true")){
    			consulta.setBuscar("true");
    		}
    		
    		
    	}catch (Exception e) {
    		 logger.error("Error formBackingObject-ConsultaGeneralOliComercialitzatController", e);
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
	 * Metodo 'setRolDoGestor'
	 * @param rolDoGestor 
	 */
	public void setRolDoGestor(String rolDoGestor) {
		this.rolDoGestor = rolDoGestor;
	}
	

	/**
	 * Metodo 'setRolAdministracio'
	 * @param rolAdministracio 
	 */
	public void setRolAdministracio(String rolAdministracio) {
		this.rolAdministracio = rolAdministracio;
	}
	
	
	/**
	 * @param rolDoControlador the rolDoControlador to set
	 */
	public void setRolDoControlador(String rolDoControlador) {
		this.rolDoControlador = rolDoControlador;
	}

	private String missatge(String clave, HttpServletRequest request) {
		String valor = getApplicationContext().getMessage(clave, null, Idioma.getLocale(request));
		return valor;
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
