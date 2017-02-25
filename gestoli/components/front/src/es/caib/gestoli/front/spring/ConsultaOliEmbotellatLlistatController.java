package es.caib.gestoli.front.spring;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
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
import es.caib.gestoli.logic.model.Lot;
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
public class ConsultaOliEmbotellatLlistatController extends SimpleFormController {

    /*
     * Objeto para escribir mensajes de log.
     */
	private static Logger logger = Logger.getLogger(ConsultaOliEmbotellatLlistatController.class);

    /*
     * Servicio para acceder a las funcionalidades de la aplicación.
     */
	private OliConsultaEjb oliConsultaEjb;
	private OliInfraestructuraEjb oliInfraestructuraEjb;
    private String rolProductor;
    private String rolDoControl;
    private String establimentSessionKey;
    private String trazaTipusOliElaborat;
	private String esProductorRequestKey;
	private String esEnvasadorRequestKey;
	private String esAdministracioRequestKey;
	private String esDoGestorRequestKey;

	private HibernateTemplate hibernateTemplate;

    public ModelAndView onSubmit(
            HttpServletRequest request,
            HttpServletResponse response,
            Object command,
            BindException errors)
    throws ServletException {
    	ConsultaOliEmbotellatLlistatCommand consulta = (ConsultaOliEmbotellatLlistatCommand) command;
    	HttpSession session = request.getSession();
    	Map myModel = new HashMap();
    	try {
    		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    		if (consulta.getDataInici() != null) myModel.put("dataInici", sdf.format(consulta.getDataInici()));
    		if (consulta.getDataFi() != null) myModel.put("dataFi", sdf.format(consulta.getDataFi()));
    		if (consulta.getIdEstabliment() != null) myModel.put("idEstabliment", consulta.getIdEstabliment());
    		if (consulta.getMesura() != null) myModel.put("mesura", consulta.getMesura());
    		
    		myModel.put("path", "consultar_oliEmbotellat");
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
	    				myModel.put("path", "consulta_oliEmbotellatEstabliment");
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
        	ConsultaOliEmbotellatLlistatCommand consulta = (ConsultaOliEmbotellatLlistatCommand) command;
        	//Long idEstabliment = Long.valueOf(request.getParameter("idEstabliment"));
        	
        	Long idEstabliment = null;
        	if (request.getAttribute(esEnvasadorRequestKey) != null || request.getAttribute(esProductorRequestKey) != null) {
    			Establiment establiment = (Establiment)request.getSession().getAttribute(establimentSessionKey);
    			if (establiment != null) {
    				idEstabliment = establiment.getId();
    			}
    		}else if (request.getAttribute(esDoGestorRequestKey) != null || request.getAttribute(esAdministracioRequestKey ) != null || request.isUserInRole(rolDoControl)) {
    			if(request.getParameter("idEstabliment")!= null && !request.getParameter("idEstabliment").equals("") ){
        			idEstabliment = Long.valueOf(request.getParameter("idEstabliment"));
        		}
    		}
        	
        	if (Util.isDataCorrecta(request.getParameter("dataInici"), "dd/MM/yyyy")) {
        		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        		        		
        		Date dataInici = sdf.parse(request.getParameter("dataInici"));
        		String mesura = request.getParameter("mesura");
        		String fromEstabliment = request.getParameter("fromEstabliment");

        		Date dataFi = new Date();
        		if (Util.isDataCorrecta(request.getParameter("dataFi"), "dd/MM/yyyy")) {
        			dataFi = sdf.parse(request.getParameter("dataFi"));
        		}
        		consulta.setDataFi(dataFi);
        		oliConsultaEjb.setHibernateTemplate(getHibernateTemplate());
        		Collection llista = oliConsultaEjb.findLotsByEstablimentEntreDates(idEstabliment, dataInici, dataFi);
        		
        		Collection lista = new ArrayList();
        		for(Iterator itLots= llista.iterator(); itLots.hasNext();){
        			ProcesLotCommand procesLotCommand = new ProcesLotCommand();
        			Lot lot = (Lot)itLots.next();
        			
        			if (lot.getOlivaTaula()==null || lot.getOlivaTaula()==false) {
	        			procesLotCommand.setPartidaOli(lot.getPartidaOli());
	        			procesLotCommand.setCodiAssignat(lot.getCodiAssignat());
	        			procesLotCommand.setData(lot.getData());
	        			procesLotCommand.setDataConsum(lot.getDataConsum());
	        			procesLotCommand.setLitresEnvassats(lot.getLitresEnvassats());
	        			procesLotCommand.setLitresPerduts(lot.getLitresPerduts());
	        			procesLotCommand.setKilosEnvassats(lot.getDisponibleOliQuilos());
	        			procesLotCommand.setNumeroBotellesActuals(lot.getNumeroBotellesActuals());
	        			procesLotCommand.setNumeroBotellesInicials(lot.getNumeroBotellesInicials());
	        			procesLotCommand.setEtiquetatge(lot.getEtiquetatge());
	        			procesLotCommand.setSortidaLots(lot.getSortidaLots());
	        			if (lot.getProducte() != null)
	        				procesLotCommand.setNomProducte(lot.getProducte().getNom());
	        			else
	        				procesLotCommand.setNomProducte("");
	        			Double envassat = 0.0;
	        			if (lot.getCreatTancament()){
	        				Calendar cal = new GregorianCalendar();
	        				cal.setTime(dataInici);
	        				cal.add(Calendar.DATE, -1);
	        				envassat = oliConsultaEjb.findExistenciesLotByData(lot.getId(), cal.getTime());
	        			} else {
	        				envassat = lot.getLitresEnvassats();
	        			}
	        			if (request.getParameter("mesura") == null ||  request.getParameter("mesura").equals("l"))
	        				procesLotCommand.setVolum(envassat);
	        			else
	        				procesLotCommand.setVolum(envassat*0.916);
	        			
	        			lista.add(procesLotCommand);
        			}
        		}
        		
        		if(!errors.hasErrors()){
        			myModel.put("llista", lista);
        		}
        		
//        		Collection lista = new ArrayList();
//        		
//        		oliConsultaEjb.setHibernateTemplate(getHibernateTemplate());
//        		Collection llistaLots = oliConsultaEjb.findExistenciesLotsByEstabliment(idEstabliment);
//        		
//        		for(Iterator it= llistaLots.iterator(); it.hasNext();){
//        			ProcesLotCommand procesLotCommand = new ProcesLotCommand();
//        			Lot lot = (Lot)it.next();
//        			
//        			procesLotCommand.setPartidaOli(lot.getPartidaOli());
//        			procesLotCommand.setCodiAssignat(lot.getCodiAssignat());
//        			procesLotCommand.setData(lot.getData());
//        			procesLotCommand.setDataConsum(lot.getDataConsum());
//        			procesLotCommand.setLitresEnvassats(lot.getLitresEnvassats());
//        			procesLotCommand.setLitresPerduts(lot.getLitresPerduts());
//        			procesLotCommand.setKilosEnvassats(lot.getDisponibleOliQuilos());
//        			procesLotCommand.setNumeroBotellesActuals(lot.getNumeroBotellesActuals());
//        			procesLotCommand.setNumeroBotellesInicials(lot.getNumeroBotellesInicials());
//        			procesLotCommand.setEtiquetatge(lot.getEtiquetatge());
//        			
//        			
//        			lista.add(procesLotCommand);
//        		}        		
//        		if(!errors.hasErrors()){
//        			myModel.put("llistat", lista);
//        		}
        		
        		Collection lista2 = new ArrayList();
        		
        		oliConsultaEjb.setHibernateTemplate(getHibernateTemplate());
        		Collection llistaLots2 = oliConsultaEjb.findLotsSortidesByEstablimentEntreDates(idEstabliment,dataInici, dataFi);
        		
        		for(Iterator it= llistaLots2.iterator(); it.hasNext();){
        			ProcesLotCommand procesLotCommand = new ProcesLotCommand();
        			Lot lot = (Lot)it.next();
        			
        			if (lot.getOlivaTaula()==null || lot.getOlivaTaula()==false) {
	        			procesLotCommand.setPartidaOli(lot.getPartidaOli());
	        			procesLotCommand.setCodiAssignat(lot.getCodiAssignat());
	        			procesLotCommand.setData(lot.getData());
	        			procesLotCommand.setDataConsum(lot.getDataConsum());
	        			procesLotCommand.setLitresEnvassats(lot.getLitresEnvassats());
	        			procesLotCommand.setLitresPerduts(lot.getLitresPerduts());
	        			procesLotCommand.setKilosEnvassats(lot.getDisponibleOliQuilos());
	        			procesLotCommand.setNumeroBotellesActuals(lot.getNumeroBotellesActuals());
	        			procesLotCommand.setNumeroBotellesInicials(lot.getNumeroBotellesInicials());
	        			procesLotCommand.setEtiquetatge(lot.getEtiquetatge());
	        			procesLotCommand.setSortidaLots(lot.getSortidaLots());
	        			if (lot.getProducte() != null)
	        				procesLotCommand.setNomProducte(lot.getProducte().getNom());
	        			else
	        				procesLotCommand.setNomProducte("");
	        			
	        			Double volum = 0.0;
	        			for (Iterator its = lot.getSortidaLots().iterator(); its.hasNext();){
	        				SortidaLot sl = (SortidaLot)its.next();
	        				if (sl.getVendaData().compareTo(dataInici) >= 0 && sl.getVendaData().compareTo(dataFi) <= 0 && sl.getValid().booleanValue()){
	        					volum += sl.getVendaLitres();
	        				}
	        			}
	        			if (request.getParameter("mesura") == null ||  request.getParameter("mesura").equals("l"))
	        				procesLotCommand.setVolum(volum);//lot.getTotalSortides());
	        			else
	        				procesLotCommand.setVolum(/*lot.getTotalSortides()*/volum*0.916);
	        			
	        			lista2.add(procesLotCommand);
        			}
        		}        		
        		if(!errors.hasErrors()){
        			myModel.put("llistat2", lista2);
        		}
        		
        		consulta.setDataInici(dataInici);
        		consulta.setIdEstabliment(idEstabliment);
        		consulta.setMesura(mesura);
        		consulta.setFromEstabliment(fromEstabliment);
        		
        	}
        	
    	    Collection tipusMesura = new ArrayList();
    	    tipusMesura.add(new TipusMesura("l",missatge("establiment.medida.l",request)));
            tipusMesura.add(new TipusMesura("kg",missatge("establiment.medida.k",request)));
            if (request.getParameter("mesura") == null) consulta.setMesura("l");
            myModel.put("tipusMesura", tipusMesura);
        	myModel.put("path", "consultar_oliEmbotellat");
        	
        	if(consulta.getFromEstabliment()!= null && consulta.getFromEstabliment().equalsIgnoreCase("true")){
    			Establiment establiment =(Establiment)session.getAttribute(establimentSessionKey);
    			if (establiment==null){
    				if (idEstabliment != null){
    					oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
        				establiment =(Establiment)oliInfraestructuraEjb.establimentAmbId(idEstabliment);        				
        			}
        		}
    			if(establiment.getNom()!= null && !establiment.getNom().equalsIgnoreCase("")){
    				myModel.put("path", "consulta_oliEmbotellatEstabliment");
    				myModel.put("path_extension1", establiment.getNom());
    			}
    		}
        	
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
    	ConsultaOliEmbotellatLlistatCommand consulta = (ConsultaOliEmbotellatLlistatCommand)request.getSession().getAttribute("consulta");
    	if (consulta == null) 
    		consulta = new ConsultaOliEmbotellatLlistatCommand();
    	else 
    		request.getSession().removeAttribute("consulta");
    	
        
    	try{
    		Long idEstabliment = null;
    		Date dataInici = null;
    		Date dataFi = null;
//    		if(request.getAttribute("idEstabliment") != null && !((String)request.getAttribute("idEstabliment")).equals("")){
//    			idEstabliment = Long.valueOf((String)request.getAttribute("idEstabliment"));
//    		}else if(request.getParameter("idEstabliment")!= null && !request.getParameter("idEstabliment").equals("") ){
//    			idEstabliment = Long.valueOf(request.getParameter("idEstabliment"));
//    		}    		
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
    		Calendar cal = Calendar.getInstance();
    		cal.set(Calendar.DAY_OF_MONTH, 1);
    		cal.set(Calendar.MONTH, Calendar.JANUARY);
    		Date data = cal.getTime();
    		
    		if(request.getParameter("dataInici") != null && !((String)request.getParameter("dataInici")).equals("")) {
    			dataInici = sdf.parse(request.getParameter("dataInici"));
    		} else {
    			dataInici = data;
    		}
    		if(request.getParameter("dataFi") != null && !((String)request.getParameter("dataFi")).equals("")) {
    			dataFi = sdf.parse(request.getParameter("dataFi"));
    		} else {
    			dataFi = new Date();
    		}
    		
    		if(request.getParameter("mesura") != null && !((String)request.getParameter("mesura")).equals("")) {
    			consulta.setMesura(request.getParameter("mesura"));
    		}
    		if(request.getParameter("fromEstabliment") != null ) {
    			consulta.setFromEstabliment(request.getParameter("fromEstabliment"));
    		}
    		
    		if(dataInici != null) consulta.setDataInici(dataInici);
    		if(dataFi != null) consulta.setDataFi(dataFi);
    		if(idEstabliment != null) consulta.setIdEstabliment(idEstabliment);
    		
    		Collection tipusMesura = new ArrayList();
    		tipusMesura.add(new TipusMesura("l",missatge("establiment.medida.l",request)));
            tipusMesura.add(new TipusMesura("kg",missatge("establiment.medida.k",request)));            
            request.setAttribute("tipusMesura", tipusMesura);
    		
    	}catch (Exception e) {
			logger.error("EXCEPTION", e);
		}   	
    	
    	return consulta;
    }
    
    public class TipusMesura implements java.io.Serializable {
		
		private String val;
		public String nom;
		
		TipusMesura(String val, String nom){
			this.val=val;
			this.nom=nom;
		}

		public String getVal() {
			return val;
		}
		public void setVal(String val) {
			this.val = val;
		}
		public String getNom() {
			return nom;
		}
		public void setNom(String nom) {
			this.nom = nom;
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
	 * Injecció de la dependència setTrazaTipusOliElaborat
	 *
	 * @param setTrazaTipusOliElaborat La classe a injectar.
	 */
	public void setTrazaTipusOliElaborat(String trazaTipusOliElaborat) {
		this.trazaTipusOliElaborat = trazaTipusOliElaborat;
	}
    
	private String missatge(String clave, HttpServletRequest request) {
		String valor = getApplicationContext().getMessage(clave, null, Idioma.getLocale(request));
		return valor;
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
	 * Injecció de la dependència esAdministracioRequestKey
	 * @param esAdministracioRequestKey La classe a injectar.
	 */
	public void setEsAdministracioRequestKey(String esAdministracioRequestKey) {
		this.esAdministracioRequestKey = esAdministracioRequestKey;
	}

	/**
	 * @param rolDoControl the rolDoControl to set
	 */
	public void setRolDoControl(String rolDoControl) {
		this.rolDoControl = rolDoControl;
	}

	/**
	 * Injecció de la dependència esDoGestorRequestKey
	 * @param esDoGestorRequestKey La classe a injectar.
	 */
	public void setEsDoGestorRequestKey(String esDoGestorRequestKey) {
		this.esDoGestorRequestKey = esDoGestorRequestKey;
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
