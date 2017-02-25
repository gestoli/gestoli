package es.caib.gestoli.front.spring;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
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

import es.caib.gestoli.front.util.Idioma;
import es.caib.gestoli.front.util.Util;
import es.caib.gestoli.logic.interfaces.OliConsultaEjb;
import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;
import es.caib.gestoli.logic.model.Establiment;
import es.caib.gestoli.logic.model.SortidaDiposit;
import es.caib.gestoli.logic.model.SortidaLot;

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
public class ConsultaOliComercialitzatEstablimentLlistatController extends SimpleFormController {

    /*
     * Objeto para escribir mensajes de log.
     */
	private static Logger logger = Logger.getLogger(ConsultaOliComercialitzatEstablimentLlistatController.class);

    /*
     * Servicio para acceder a las funcionalidades de la aplicación.
     */
	private OliConsultaEjb oliConsultaEjb;
	private OliInfraestructuraEjb oliInfraestructuraEjb;
    private String rolProductor;
    private String rolDoControl;
    private String establimentSessionKey;
    private String trazaTipusOliComercialitzat;
    private String trazaTipusOliDisponibleDiposit;
    private String esProductorRequestKey;
	private String esEnvasadorRequestKey;
	private String esAdministracioRequestKey;
	private String esDoGestorRequestKey;
	
    private String rolDoGestor;
	private String rolAdministracio;
	private String campanyaSessionKey;
	private Integer tipusEstablimentEnvasadora;
	private Integer tipusEstablimentTafonaEnvasadora;
	
    private String desqualificat;
	private String pendentQualificar;
	private String qualificat;

	private HibernateTemplate hibernateTemplate;

    public ModelAndView onSubmit(
            HttpServletRequest request,
            HttpServletResponse response,
            Object command,
            BindException errors)
    throws ServletException {
    	ConsultaOliComercialitzatEstablimentLlistatCommand consulta = (ConsultaOliComercialitzatEstablimentLlistatCommand) command;
    	Map myModel = new HashMap();
    	HttpSession session = request.getSession();
    	try {
    		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    		if (consulta.getDataInici() != null) myModel.put("dataInici", sdf.format(consulta.getDataInici()));
    		if (consulta.getDataFi() != null) myModel.put("dataFi", sdf.format(consulta.getDataFi()));
    		if (consulta.getIdEstabliment() != null) myModel.put("idEstabliment", consulta.getIdEstabliment());    		
    		myModel.put("path", "consultar_oliComercialitzat");
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
	    				myModel.put("path", "consulta_oliComercialitzatEstabliment");
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
        	ConsultaOliComercialitzatEstablimentLlistatCommand consulta = (ConsultaOliComercialitzatEstablimentLlistatCommand) command;
        	
        	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        	Date dataInici = null;
        	Date dataFi = null;
        	
        	if (Util.isDataCorrecta(request.getParameter("dataInici"), "dd/MM/yyyy")
        			&& Util.isDataCorrecta(request.getParameter("dataFi"), "dd/MM/yyyy")) {
        		dataInici = sdf.parse(request.getParameter("dataInici"));
        		dataFi = sdf.parse(request.getParameter("dataFi"));
        	} else {
        		Calendar cal = Calendar.getInstance();
        		dataFi = new Date();
        		cal.setTime(dataFi);
        		cal.add(Calendar.MONTH, -1);
        		dataInici = cal.getTime(); 
        	}
        		
    		Collection llistatEstabliments = new ArrayList();        
        	Long campanyaId = (Long)request.getSession().getAttribute(campanyaSessionKey);
        	List tipos = new ArrayList();
        	tipos.add(tipusEstablimentEnvasadora);
        	tipos.add(tipusEstablimentTafonaEnvasadora);
        	if (request.isUserInRole(rolDoGestor) || request.isUserInRole(rolAdministracio)|| request.isUserInRole(rolDoControl)) {   
        		oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
        		llistatEstabliments = oliInfraestructuraEjb.establimentCercaTotsActivosByTipos(campanyaId, tipos.toArray());
        	}
        	
        	Collection llistat = new ArrayList();
        	for(Object o: llistatEstabliments){
        		Establiment e = (Establiment)o;
        		
    		   	Double dipositDO = 0.0;
    		   	Double dipositPendent = 0.0;
    		   	Double dipositNoDO = 0.0;
    		   	
    		   	Integer lotDO = 0;
    		   	Integer lotNoDO = 0;
        		
    		   		
        	 	// Cerca tots las diposits de un establecimiento no vacios y que pertenecen a unas determinadas categorias
    		   	oliConsultaEjb.setHibernateTemplate(getHibernateTemplate());
    		   	Collection listaDepositos = oliConsultaEjb.getSortidaDipositVendaEntreDiasEnEstablecimiento(dataInici, dataFi, e.getId());
    		   
    		   	
        		for(Object obj: listaDepositos){
        			SortidaDiposit deposit = (SortidaDiposit)obj;
    		   		
    		   		if(deposit.getCategoriaOli().getId().toString().equals(qualificat)){ //Pos 2: existencies litres. Pos 4: categoria
    		   			dipositDO += deposit.getLitres(); 
    		   		} else if(deposit.getCategoriaOli().getId().toString().equals(pendentQualificar)){ //Pos 2: existencies litres. Pos 4: categoria
    		   			dipositPendent += deposit.getLitres(); 
    		   		} else if(deposit.getCategoriaOli().getId().toString().equals(desqualificat)){ //Pos 2: existencies litres. Pos 4: categoria
    		   			dipositNoDO += deposit.getLitres(); 
    		   		}
    		   	}
    		   	
    		   	// Cerca tots los lotes de un establecimiento no vacios y que pertenecen a unas determinadas categorias
		        oliConsultaEjb.setHibernateTemplate(getHibernateTemplate());
		        Collection listaLotes = oliConsultaEjb.getSortidaLotVendaEntreDiasEnEstablecimiento(dataInici, dataFi, e.getId());
		        
		        for(Object obj: listaLotes){
		        	SortidaLot sortidalot = (SortidaLot)obj;
		        	Integer botelles_sortides = sortidalot.getVendaNumeroBotelles() - (sortidalot.getBotellesDevolucio() != null ? sortidalot.getBotellesDevolucio() : 0);
		        	
		        	if (botelles_sortides > 0) {
	    		   		if(sortidalot.getLot().getPartidaOli().getCategoriaOli().getId().toString().equals(qualificat)){ //Pos 2: existencies litres. Pos 4: categoria
	    		   			lotDO += botelles_sortides;//sortidalot.getVendaNumeroBotelles(); 
	    		   		} else if(sortidalot.getLot().getPartidaOli().getCategoriaOli().getId().toString().equals(desqualificat)){ //Pos 2: existencies litres. Pos 4: categoria
	    		   			lotNoDO += botelles_sortides;//sortidalot.getVendaNumeroBotelles(); 
	    		   		}
		        	}
    		   	}
			        
        		Object[] item = {e.getId(),
        						e.getNom(),
        						e.getCif(),
        						dipositDO,
        						dipositPendent,
        						dipositNoDO,
        						lotDO,
        						lotNoDO,
        						(dipositDO + dipositPendent + dipositNoDO + lotDO + lotNoDO)}; //Total oli
        		
        		llistat.add(item);
        	}
        	
    		if(!errors.hasErrors()){
    			myModel.put("llistat", llistat);
    			myModel.put("trazaTipusOliComercialitzat", trazaTipusOliComercialitzat);
    			myModel.put("trazaTipusOliDisponibleDiposit", trazaTipusOliDisponibleDiposit);
    		}
    		
    		consulta.setDataInici(dataInici);
    		consulta.setDataFi(dataFi);
        		
        	myModel.put("path", "consultar_oliComercialitzat");
        	
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
    	ConsultaOliComercialitzatEstablimentLlistatCommand consulta = (ConsultaOliComercialitzatEstablimentLlistatCommand)request.getSession().getAttribute("consulta");
    	if (consulta == null) 
    		consulta = new ConsultaOliComercialitzatEstablimentLlistatCommand();
    	else 
    		request.getSession().removeAttribute("consulta");
    	
        
    	try{
    		Long idEstabliment = null;
    		Date dataInici = null;
    		Date dataFi = null;
    		
        	if (request.getAttribute(esEnvasadorRequestKey) != null || request.getAttribute(esProductorRequestKey) != null) {
    			Establiment establiment = (Establiment)request.getSession().getAttribute(establimentSessionKey);
    			if (establiment != null) {
    				idEstabliment = establiment.getId();
    			}
    		}else if (request.getAttribute(esDoGestorRequestKey) != null || request.getAttribute(esAdministracioRequestKey) != null || request.isUserInRole(rolDoControl)) {
    			if(request.getParameter("idEstabliment")!= null && !request.getParameter("idEstabliment").equals("") ){
        			idEstabliment = Long.valueOf(request.getParameter("idEstabliment"));
        		}
    		}
    		
    		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    		Date data = Calendar.getInstance().getTime();
    		
    		if(request.getParameter("dataInici") != null && !((String)request.getParameter("dataInici")).equals("")) {
    			dataInici = sdf.parse(request.getParameter("dataInici"));
    		} else {
    			dataInici = data;
    		}
    		
    		if(request.getParameter("dataFi") != null && !((String)request.getParameter("dataFi")).equals("")) {
    			dataFi = sdf.parse(request.getParameter("dataFi"));
    		} else {
    			dataFi = data;
    		}
    		if(request.getParameter("fromEstabliment") != null ) {
    			consulta.setFromEstabliment(request.getParameter("fromEstabliment"));
    		}
    		
    		if(dataInici != null) consulta.setDataInici(dataInici);
    		if(dataInici != null)consulta.setDataFi(dataFi);			
    		if(idEstabliment != null) consulta.setIdEstabliment(idEstabliment);    		
    		
    		
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
	 * Metodo 'setEstablimentSessionKey'
	 * @param establimentSessionKey
	 */
	public void setEstablimentSessionKey(String establimentSessionKey) {
		this.establimentSessionKey = establimentSessionKey;
	}
	
	
	/**
	 * Metodo 'setTrazaTipusOliComercialitzat'
	 * @param trazaTipusOliComercialitzat
	 */
	public void setTrazaTipusOliComercialitzat(String trazaTipusOliComercialitzat) {
		this.trazaTipusOliComercialitzat = trazaTipusOliComercialitzat;
	}
	
	/**
	 * Metodo 'setTrazaTipusOliDisponibleDiposit'
	 * @param trazaTipusOliDisponibleDiposit
	 */
	public void setTrazaTipusOliDisponibleDiposit(String trazaTipusOliDisponibleDiposit) {
		this.trazaTipusOliDisponibleDiposit = trazaTipusOliDisponibleDiposit;
	}
	
	private String missatge(String clave, HttpServletRequest request) {
		String valor = getApplicationContext().getMessage(clave, null, Idioma.getLocale(request));
		return valor;
	}
	
	/**
	 * Metodo 'getEsProductorRequestKey'
	 * @return el esProductorRequestKey
	 */
	public String getEsProductorRequestKey() {
		return esProductorRequestKey;
	}

	/**
	 * Metodo 'setEsProductorRequestKey'
	 * @param esProductorRequestKey el esProductorRequestKey a modificar
	 */
	public void setEsProductorRequestKey(String esProductorRequestKey) {
		this.esProductorRequestKey = esProductorRequestKey;
	}
	
	/**
	 * Metodo 'getEsEnvasadorRequestKey'
	 * @return el esEnvasadorRequestKey
	 */
	public String getEsEnvasadorRequestKey() {
		return esEnvasadorRequestKey;
	}

	/**
	 * Metodo 'setEsEnvasadorRequestKey'
	 * @param esEnvasadorRequestKey el esEnvasadorRequestKey a modificar
	 */
	public void setEsEnvasadorRequestKey(String esEnvasadorRequestKey) {
		this.esEnvasadorRequestKey = esEnvasadorRequestKey;
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

	public void setRolDoGestor(String rolDoGestor) {
		this.rolDoGestor = rolDoGestor;
	}

	public void setRolAdministracio(String rolAdministracio) {
		this.rolAdministracio = rolAdministracio;
	}

	public void setCampanyaSessionKey(String campanyaSessionKey) {
		this.campanyaSessionKey = campanyaSessionKey;
	}

	public void setTipusEstablimentEnvasadora(Integer tipusEstablimentEnvasadora) {
		this.tipusEstablimentEnvasadora = tipusEstablimentEnvasadora;
	}

	public void setTipusEstablimentTafonaEnvasadora(
			Integer tipusEstablimentTafonaEnvasadora) {
		this.tipusEstablimentTafonaEnvasadora = tipusEstablimentTafonaEnvasadora;
	}

	public void setDesqualificat(String desqualificat) {
		this.desqualificat = desqualificat;
	}

	public void setPendentQualificar(String pendentQualificar) {
		this.pendentQualificar = pendentQualificar;
	}

	public void setQualificat(String qualificat) {
		this.qualificat = qualificat;
	}
	
	

	
}
