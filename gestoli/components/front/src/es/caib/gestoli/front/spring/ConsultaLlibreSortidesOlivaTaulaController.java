package es.caib.gestoli.front.spring;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
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

import es.caib.gestoli.front.util.Util;
import es.caib.gestoli.logic.interfaces.OliConsultaEjb;
import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;
import es.caib.gestoli.logic.model.Campanya;
import es.caib.gestoli.logic.model.Establiment;
import es.caib.gestoli.logic.model.Lot;
import es.caib.gestoli.logic.model.SortidaBota;

public class ConsultaLlibreSortidesOlivaTaulaController extends SimpleFormController {

	private static Logger logger = Logger.getLogger(ConsultaLlibreSortidesOlivaTaulaController.class);

    /*
     * Servicio para acceder a las funcionalidades de la aplicación.
     */
	private OliConsultaEjb oliConsultaEjb;
	private OliInfraestructuraEjb oliInfraestructuraEjb;
    private String establimentSessionKey;
    private String esOlivaTaulaRequestKey;
	private String esAdministracioRequestKey;
	private String esDoGestorRequestKey;
	private String rolDoControl;
	private HibernateTemplate hibernateTemplate;

    @SuppressWarnings({ "unchecked", "rawtypes" })
	public ModelAndView onSubmit(
            HttpServletRequest request,
            HttpServletResponse response,
            Object command,
            BindException errors)
    throws ServletException {
    	ConsultaLlibreOlivaTaulaCommand consulta = (ConsultaLlibreOlivaTaulaCommand) command;
    	Map myModel = new HashMap();
    	HttpSession session = request.getSession();
    	try {
    		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    		if (consulta.getDataInici() != null) myModel.put("dataInici", sdf.format(consulta.getDataInici()));
    		if (consulta.getDataFi() != null) myModel.put("dataFi", sdf.format(consulta.getDataFi()));
    		if (consulta.getIdCampanya() != null) myModel.put("idCampanya", consulta.getIdCampanya());
    		if (consulta.getIdEstabliment() != null) myModel.put("idEstabliment", consulta.getIdEstabliment());

    		myModel.put("path", "consultar_sortidesOliva");
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
	    				myModel.put("path", "consulta_sortidesOlivaEstabliment");
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
    @SuppressWarnings({ "rawtypes", "unchecked" })
	protected Map referenceData(
            HttpServletRequest request,
            Object command,
            Errors errors) throws Exception {
        Map myModel = new HashMap();
        HttpSession session = request.getSession();

        try {
        	ConsultaLlibreOlivaTaulaCommand consulta = (ConsultaLlibreOlivaTaulaCommand) command;
        	if (Util.isDataCorrecta(request.getParameter("dataInici"), "dd/MM/yyyy")
        			&& Util.isDataCorrecta(request.getParameter("dataFi"), "dd/MM/yyyy")) {
        		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        		Date dataInici;
        		Date dataFi;
        		String fromEstabliment = request.getParameter("fromEstabliment");
        		
        		oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
            	Collection campanyes = oliInfraestructuraEjb.campanyaCercaTotes();
    			myModel.put("campanyes", campanyes);
    			Long idCampanya = null;
        		if(request.getParameter("idCampanya")!= null && !request.getParameter("idCampanya").equals("")){
        				idCampanya = Long.valueOf(request.getParameter("idCampanya"));
        				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
        				Campanya campanya = oliInfraestructuraEjb.campanyaAmbId(idCampanya.intValue());
        				dataInici = campanya.getData();
        				Calendar cal = Calendar.getInstance();
        				cal.setTime( campanya.getData() );
        				cal.add( Calendar.YEAR, 1 );
        				cal.add( Calendar.DATE, -1 );
        				dataFi = cal.getTime();
        		}else {
        			dataInici = sdf.parse(request.getParameter("dataInici"));
        			dataFi = sdf.parse(request.getParameter("dataFi"));
        		}
        		
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

        		oliConsultaEjb.setHibernateTemplate(getHibernateTemplate());
        		Collection llista = oliConsultaEjb.findLotsOlivaTaulaByEstablimentEntreDates(idEstabliment, dataInici, dataFi);
        		
        		Collection listaEnvassat = new ArrayList();
        		for(Iterator itLots= llista.iterator(); itLots.hasNext();){
        			ProcesEnvasatOlivaCommand peoc = new ProcesEnvasatOlivaCommand();
        			Lot lot = (Lot)itLots.next();
        			
        			peoc.setPartidaOli(lot.getPartidaOli());
        			peoc.setCodiAssignat(lot.getCodiAssignat());
        			peoc.setData(lot.getData());
        			peoc.setDataConsum(lot.getDataConsum());
        			peoc.setLitresEnvassats(lot.getLitresEnvassats());
        			peoc.setLitresPerduts(lot.getLitresPerduts());
        			peoc.setKilosEnvassats(lot.getDisponibleOliQuilos());
        			peoc.setNumeroBotellesActuals(lot.getNumeroBotellesActuals());
        			peoc.setNumeroBotellesInicials(lot.getNumeroBotellesInicials());
        			peoc.setEtiquetatge(lot.getEtiquetatge());
        			peoc.setSortidaLots(lot.getSortidaLots());
        			peoc.setBota(lot.getBota());
        			peoc.setLotOliAfegit(lot.getLotOliAfegit());
        			peoc.setTotalOliAfegit(lot.getTotalOliAfegit());
        			peoc.setKgOlivaTaula(lot.getKgOlivaTaula());
        			peoc.setTipusOlivaTaula(lot.getTipusOlivaTaula());
        			peoc.setOlivaTaula(lot.getOlivaTaula());
  
        			if (lot.getProducte() != null)
        				peoc.setNomProducte(lot.getProducte().getNom());
        			else
        				peoc.setNomProducte("");
        			
        			SortidaBota sortida = oliConsultaEjb.findSortidaBotaByLot(lot);
        			peoc.setpH1(sortida.getpH1());
        			peoc.setpH2(sortida.getpH2());
        			peoc.setQuantitatAcidCitric(sortida.getQuantitatAcidCitric());
        			peoc.setLotAcidCitric(sortida.getLotAcidCitric());
        			
//        			Double envassat = 0.0;
//        			if (lot.getCreatTancament()){
//        				Calendar cal = new GregorianCalendar();
//        				cal.setTime(dataInici);
//        				cal.add(Calendar.DATE, -1);
//        				envassat = oliConsultaEjb.findExistenciesLotByData(lot.getId(), cal.getTime());
//        			} else {
//        				envassat = lot.getLitresEnvassats();
//        			}
        			
        			listaEnvassat.add(peoc);
        		}
        		myModel.put("listaEnvassat", listaEnvassat);

        		Collection listaSortides = oliConsultaEjb.getSortidesLotsOlivaTaulaEntreDadesAndEstabliment(dataInici, dataFi, idEstabliment, true);
        		myModel.put("listaSortides", listaSortides);
        		Collection sortidaOlivaCruaGranel = oliConsultaEjb.findSortidesOlivaCruaGranelByEstablimentEntreDates(idEstabliment,dataInici, dataFi, true);
        		myModel.put("sortidesOlivaCruaGranel", sortidaOlivaCruaGranel);
        		Collection sortidaOlivaBotaGranel = oliConsultaEjb.findSortidesOlivaBotaGranelByEstablimentEntreDates(idEstabliment,dataInici, dataFi, true);
        		myModel.put("sortidesOlivaBotaGranel", sortidaOlivaBotaGranel);
        		
        		consulta.setDataInici(dataInici);
        		consulta.setDataFi(dataFi);
        		consulta.setIdCampanya(idCampanya);
        		consulta.setIdEstabliment(idEstabliment);
        		consulta.setFromEstabliment(fromEstabliment);
        		
        		
        		
        	}
        	myModel.put("path", "consulta_sortidesOlivaEstabliment");
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
	    				myModel.put("path", "consulta_sortidesOlivaEstabliment");
	    				myModel.put("path_extension1", establiment.getNom());
	    			}
	    		}
    		}
        } catch (Exception ex) {
        	logger.error("Error obtenint llistat de diposits", ex);
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
    @SuppressWarnings("rawtypes")
	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
    	ConsultaLlibreOlivaTaulaCommand consulta = (ConsultaLlibreOlivaTaulaCommand)request.getSession().getAttribute("consulta");
    	if (consulta == null) 
    		consulta = new ConsultaLlibreOlivaTaulaCommand();
    	else 
    		request.getSession().removeAttribute("consulta");
    	
        
    	try{
    		Long idEstabliment = null;
    		Date dataInici = null;
    		Date dataFi = null;

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
    		
    		oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
        	Collection campanyes = oliInfraestructuraEjb.campanyaCercaTotes();
			request.setAttribute("campanyes", campanyes);
			Long idCampanya = null;
    		if(request.getParameter("idCampanya")!= null){
    			if(!request.getParameter("idCampanya").equals("")){
    				idCampanya = Long.valueOf(request.getParameter("idCampanya"));
    			}    			
    		}
    		if(request.getParameter("fromEstabliment") != null ) {
    			consulta.setFromEstabliment(request.getParameter("fromEstabliment"));
    		}
    		
    		if(dataInici != null) consulta.setDataInici(dataInici);
    		if(dataInici != null)consulta.setDataFi(dataFi);			
    		if(idCampanya != null) consulta.setIdCampanya(idCampanya);
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
	 * Metodo 'setEstablimentSessionKey'
	 * @param establimentSessionKey
	 */
	public void setEstablimentSessionKey(String establimentSessionKey) {
		this.establimentSessionKey = establimentSessionKey;
	}
	

	public void setEsOlivaTaulaRequestKey(String esOlivaTaulaRequestKey) {
		this.esOlivaTaulaRequestKey = esOlivaTaulaRequestKey;
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
