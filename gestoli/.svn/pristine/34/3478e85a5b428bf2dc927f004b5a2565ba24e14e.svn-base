package es.caib.gestoli.front.spring;

import java.text.SimpleDateFormat;
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
import es.caib.gestoli.logic.model.Elaboracio;
import es.caib.gestoli.logic.model.Establiment;
import es.caib.gestoli.logic.model.PartidaOliva;
import es.caib.gestoli.logic.model.SortidaDiposit;
import es.caib.gestoli.logic.model.SortidaLot;
import es.caib.gestoli.logic.model.SortidaOrujo;

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
 * @author Raul Morales
 */
public class AgenciaOliResumController extends SimpleFormController {

    /*
     * Objeto para escribir mensajes de log.
     */
	private static Logger logger = Logger.getLogger(AgenciaOliResumController.class);

    /*
     * Servicio para acceder a las funcionalidades de la aplicación.
     */
	private OliConsultaEjb oliConsultaEjb;
	private OliInfraestructuraEjb oliInfraestructuraEjb;
    private String rolProductor;
    private String establimentSessionKey;
    private String esProductorRequestKey;
	private String esEnvasadorRequestKey;
	private String esAdministracioRequestKey;
	private String esDoGestorRequestKey;
	private String rolDoControl;
	private HibernateTemplate hibernateTemplate;

    public ModelAndView onSubmit(
            HttpServletRequest request,
            HttpServletResponse response,
            Object command,
            BindException errors)
    throws ServletException {
    	AgenciaOliResumCommand consulta = (AgenciaOliResumCommand) command;
    	Map myModel = new HashMap();
    	HttpSession session = request.getSession();
    	try {
    		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//    		myModel.put("bodegaId", consulta.getBodega());
    		if (consulta.getDataInici() != null) myModel.put("dataInici", sdf.format(consulta.getDataInici()));
    		if (consulta.getDataFi() != null) myModel.put("dataFi", sdf.format(consulta.getDataFi()));
    		if (consulta.getIdEstabliment() != null) myModel.put("idEstabliment", consulta.getIdEstabliment());

    		myModel.put("path", "agenciaOli_resum");
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
	    				myModel.put("path", "agenciaOli_resumEstabliment");
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
        	AgenciaOliResumCommand consulta = (AgenciaOliResumCommand) command;
        	if (Util.isDataCorrecta(request.getParameter("dataInici"), "dd/MM/yyyy")
        			&& Util.isDataCorrecta(request.getParameter("dataFi"), "dd/MM/yyyy")) {
        		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        		Date dataInici = sdf.parse(request.getParameter("dataInici"));
        		Date dataFi = sdf.parse(request.getParameter("dataFi"));
        		String fromEstabliment = request.getParameter("fromEstabliment");
        		
        		//Long idEstabliment = Long.valueOf(request.getParameter("idEstabliment"));
        		Long idEstabliment = null;
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

            	oliConsultaEjb.setHibernateTemplate(getHibernateTemplate());
            	Collection entradesOliva = oliConsultaEjb.entradesOlivaConsulta(dataInici, dataFi, idEstabliment);
            	
            	oliConsultaEjb.setHibernateTemplate(getHibernateTemplate());
            	Collection elaboracions  = oliConsultaEjb.oliElaboratConsulta(dataInici, dataFi, idEstabliment, true, true);
            	
        		oliConsultaEjb.setHibernateTemplate(getHibernateTemplate());
        		Collection sortidesLots = oliConsultaEjb.getSortidaLotVendaEntreDiasEnEstablecimiento(dataInici, dataFi, idEstabliment);
        		
        		oliConsultaEjb.setHibernateTemplate(getHibernateTemplate());
        		Collection sortidesGranel = oliConsultaEjb.getSortidaDipositVendaEntreDiasEnEstablecimiento(dataInici, dataFi, idEstabliment);
        		
        		oliConsultaEjb.setHibernateTemplate(getHibernateTemplate());
        		Collection sortidesOrujo = oliConsultaEjb.findSortidesOrujoEntreDatesIEstabliment(dataInici, dataFi, idEstabliment, true);
        		
        		
        		//Emplenam el llistat amb els dies
        		Map<Date,Object[]> llistat = new HashMap<Date,Object[]>();
        		
        		Calendar calIni = Calendar.getInstance();
        		calIni.setTime(dataInici);
        		calIni.set(Calendar.HOUR, 0);
        		calIni.set(Calendar.MINUTE, 0);
        		calIni.set(Calendar.SECOND, 0);
        		calIni.set(Calendar.MILLISECOND, 0);
        		
        		Calendar calFi = Calendar.getInstance();
        		calFi.setTime(dataFi);
        		calFi.set(Calendar.HOUR, 0);
        		calFi.set(Calendar.MINUTE, 0);
        		calFi.set(Calendar.SECOND, 0);
        		calFi.set(Calendar.MILLISECOND, 0);
        		
        		Calendar calAux = calIni;
        		
        		while(!calAux.after(calFi)){
        			//0: data
        			//1: entrada oliva (kg)
        			//2: kg oliva molturada
        			//3: kg oli produits
        			//4: kg sortida oli
        			//5: kg sortida orujo
        			//6: litres oli produits
        			Object[] o = {calAux.getTime(),0.0,0.0,0.0,0.0,0.0,0.0};
        			llistat.put(calAux.getTime(), o);
        			calAux.add(Calendar.DAY_OF_MONTH, 1);
        		}
        		
        		//Emplenam totes les dades
        		for(Iterator it=entradesOliva.iterator(); it.hasNext();){
        			PartidaOliva po = (PartidaOliva)it.next();
        			
        			Object[] obj = llistat.get(po.getData());
        			obj[1] = (Double)obj[1] + po.getTotalKilos();
        		}
        		
        		for(Iterator it=elaboracions.iterator(); it.hasNext();){
        			Elaboracio e = (Elaboracio)it.next();
        			
        			Object[] obj = llistat.get(e.getData());
        			obj[2] = (Double)obj[2] + e.getTotalKilos();
        			obj[3] = (Double)obj[3] + (e.getLitres() * 0.916);
        			obj[6] = (Double)obj[6] + e.getLitres();
        		}
        		
        		for(Iterator it=sortidesLots.iterator(); it.hasNext();){
        			SortidaLot sl = (SortidaLot)it.next();
        			
        			Object[] obj = llistat.get(sl.getVendaData());
        			obj[4] = (Double)obj[4] + (sl.getVendaLitres() * 0.916);
        		}
        		
        		
        		Calendar calAux2 = calAux;
        		for(Iterator it=sortidesGranel.iterator(); it.hasNext();){
        			SortidaDiposit sd = (SortidaDiposit)it.next();
        			
        			calAux2.setTime(sd.getData());
        			
        			Object[] obj = llistat.get(calAux2.getTime());
        			obj[4] = (Double)obj[4] + (sd.getLitres() * 0.916);
        		}
        		
        		for(Iterator it=sortidesOrujo.iterator(); it.hasNext();){
        			SortidaOrujo so = (SortidaOrujo)it.next();
        			
        			Object[] obj = llistat.get(so.getData());
        			obj[5] = (Double)obj[5] + so.getKilos();
        		}
        		
        		myModel.put("llistat", llistat);
        		
        		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        		
        		myModel.put("dataInici", sdf.format(dataInici));
        		myModel.put("dataFi", sdf.format(dataFi));

        		consulta.setDataInici(dataInici);
        		consulta.setDataFi(dataFi);
        		consulta.setIdEstabliment(idEstabliment);
        		consulta.setFromEstabliment(fromEstabliment);
        		
        	}
        	myModel.put("path", "agenciaOli_resum");
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
	    				myModel.put("path", "agenciaOli_resumEstabliment");
	    				myModel.put("path_extension1", establiment.getNom());
	    			}
	    		}
    		}
        } catch (Exception ex) {
        	logger.error("Error obtenint llistat de diposits", ex);
//        	ControllerUtils.saveMessageError(request, "No s'ha obtingut cap registre");
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
    	AgenciaOliResumCommand consulta = (AgenciaOliResumCommand)request.getSession().getAttribute("consulta");
    	if (consulta == null) 
    		consulta = new AgenciaOliResumCommand();
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
	 * Metodo 'setEstablimentSessionKey'
	 * @param establimentSessionKey
	 */
	public void setEstablimentSessionKey(String establimentSessionKey) {
		this.establimentSessionKey = establimentSessionKey;
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
