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

import es.caib.gestoli.front.util.Idioma;
import es.caib.gestoli.front.util.Util;
import es.caib.gestoli.logic.interfaces.OliConsultaEjb;
import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;
import es.caib.gestoli.logic.model.CategoriaOli;
import es.caib.gestoli.logic.model.Diposit;
import es.caib.gestoli.logic.model.EntradaDiposit;
import es.caib.gestoli.logic.model.Establiment;
import es.caib.gestoli.logic.model.Trasllat;
import es.caib.gestoli.logic.model.Traza;
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
public class ConsultaMovimentsEntreEstablimentsLlistatController extends SimpleFormController {

    /*
     * Objeto para escribir mensajes de log.
     */
	private static Logger logger = Logger.getLogger(ConsultaMovimentsEntreEstablimentsLlistatController.class);

    /*
     * Servicio para acceder a las funcionalidades de la aplicación.
     */
	private OliConsultaEjb oliConsultaEjb;
	private OliInfraestructuraEjb oliInfraestructuraEjb;
    private String rolProductor;
    private String rolDoControl;
    private String establimentSessionKey;
    private String trazaTipusEntradaVolant;
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
    	ConsultaMovimentsEntreEstablimentsLlistatCommand consulta = (ConsultaMovimentsEntreEstablimentsLlistatCommand) command;
    	Map myModel = new HashMap();
    	HttpSession session = request.getSession();
    	try {
    		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    		if (consulta.getDataInici() != null) myModel.put("dataInici", sdf.format(consulta.getDataInici()));
    		if (consulta.getDataFi() != null) myModel.put("dataFi", sdf.format(consulta.getDataFi()));
    		if (consulta.getIdEstabliment() != null) myModel.put("idEstabliment", consulta.getIdEstabliment());    		
    		myModel.put("path", "consultar_movimentsEntreEstabliments");
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
	    				myModel.put("path", "consulta_movimentsEntreEstablimentsEstabliment");
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
        	ConsultaMovimentsEntreEstablimentsLlistatCommand consulta = (ConsultaMovimentsEntreEstablimentsLlistatCommand) command;
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
        	if (Util.isDataCorrecta(request.getParameter("dataInici"), "dd/MM/yyyy")
        			&& Util.isDataCorrecta(request.getParameter("dataFi"), "dd/MM/yyyy")) {
        		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        		        		
        		Date dataInici = sdf.parse(request.getParameter("dataInici"));
        		Date dataFi = sdf.parse(request.getParameter("dataFi"));
        		
        		String fromEstabliment = request.getParameter("fromEstabliment");
        		
        		Collection listaSortidesPendents = new ArrayList();
        		Collection listaSortides = new ArrayList();
        		Collection listaEntrades = new ArrayList();
        		
        		oliConsultaEjb.setHibernateTemplate(getHibernateTemplate());
        		Collection listaTrasllatSortidaPendent = oliConsultaEjb.movimentsEntreEstablimentsSortidaPendentConsulta(dataInici, dataFi, idEstabliment);
        		Collection listaTrasllatSortides = oliConsultaEjb.movimentsEntreEstablimentsSortidaConsulta(dataInici, dataFi, idEstabliment);
        		Collection listaTrasllatEntrades = oliConsultaEjb.movimentsEntreEstablimentsEntradaConsulta(dataInici, dataFi, idEstabliment);
        		
        		//Para cada uno de los registros  Trasllat separamos los depositos y devolvemos un objeto trasllatCommand
        		TrasllatCommand trasllatCommand = null; 
        		for(Iterator it=listaTrasllatSortides.iterator();it.hasNext();){
        			Trasllat trasllat = (Trasllat)it.next();
        			String nomDiposits = "";
        			
        			if(trasllat.getDiposits()!= null){
        				for(Iterator itDip=trasllat.getDiposits().iterator();itDip.hasNext();){
        					Diposit diposit = (Diposit)itDip.next();
        					nomDiposits += diposit.getCodiAssignat() + " ";
        				}
        			}
        			
        			trasllatCommand = new TrasllatCommand(
							trasllat.getEstablimentByTdiCodede(), 
							trasllat.getTraza(),
							trasllat.getEstablimentByTdiCodeor(), 
							trasllat.getData(),
							trasllat.getAcceptatTrasllat(), 
							trasllat.getRetornatEstablimentOrigen(),
							trasllat.getTraslladant(), 
							trasllat.getValid(), 
							trasllat.getEsDiposit(), 
							trasllat.getDataAlta(), 
							trasllat.getDiposits());//, trasllat.getIdPartida());
					trasllatCommand.setId(trasllat.getId());
					
					trasllatCommand.setDataAcceptarEnviament(trasllat.getDataAcceptarEnviament());
					trasllatCommand.setDataAcceptarRetorn(trasllat.getDataAcceptarRetorn());
					trasllatCommand.setQuantitatEnviament(trasllat.getQuantitatEnviament());
					trasllatCommand.setQuantitatRetorn(trasllat.getQuantitatRetorn());
					
					trasllatCommand.setNomDiposits(nomDiposits);
					
					if(trasllat.getEstablimentByTdiCodeor().getId().equals(idEstabliment)){
						trasllatCommand.setQuantitatMostrar(trasllat.getQuantitatEnviament());
						trasllatCommand.setSentit(new Integer(0));
					} else {
						trasllatCommand.setQuantitatMostrar(trasllat.getQuantitatRetorn());
						trasllatCommand.setSentit(1);
						if(trasllat.getEsDiposit()){
							trasllatCommand.setEstablimentByTdiCodeor(trasllat.getEstablimentByTdiCodede());
							trasllatCommand.setEstablimentByTdiCodede(trasllat.getEstablimentByTdiCodeor());
						}
					}
					
					//Categorid d'oli
					CategoriaOli categoria = null;
					
					for(Iterator it2 = trasllat.getTraza().getTrazasForTtrCodtrafill().iterator(); it2.hasNext();){
						Traza t = (Traza)it2.next();

						if (t.getTipus().equals(Constants.CODI_TRAZA_TIPUS_ENTRADA_DIPOSIT)){
							
							oliConsultaEjb.setHibernateTemplate(getHibernateTemplate());
							EntradaDiposit ed = oliConsultaEjb.findEntradaDipositByTraza(t.getId());
							if(ed != null){
								categoria = ed.getPartidaOli().getCategoriaOli();
							}
						}
					}
					trasllatCommand.setCategoriaOli(categoria);
					
					listaSortides.add(trasllatCommand);
        		}
        		
        		TrasllatCommand trasllatCommand2 = null; 
        		for(Iterator it=listaTrasllatEntrades.iterator();it.hasNext();){
        			Trasllat trasllat = (Trasllat)it.next();
        			String nomDiposits = "";
        			
        			if(trasllat.getDiposits()!= null){
        				for(Iterator itDip=trasllat.getDiposits().iterator();itDip.hasNext();){
        					Diposit diposit = (Diposit)itDip.next();
        					nomDiposits += diposit.getCodiAssignat() + " ";
        				}
        			}
        			
        			trasllatCommand2 = new TrasllatCommand(
							trasllat.getEstablimentByTdiCodede(), 
							trasllat.getTraza(),
							trasllat.getEstablimentByTdiCodeor(), 
							trasllat.getData(),
							trasllat.getAcceptatTrasllat(), 
							trasllat.getRetornatEstablimentOrigen(),
							trasllat.getTraslladant(), 
							trasllat.getValid(), 
							trasllat.getEsDiposit(), 
							trasllat.getDataAlta(), 
							trasllat.getDiposits());//, trasllat.getIdPartida());
					trasllatCommand2.setId(trasllat.getId());
					trasllatCommand2.setNomDiposits(nomDiposits);
					
					trasllatCommand2.setDataAcceptarEnviament(trasllat.getDataAcceptarEnviament());
					trasllatCommand2.setDataAcceptarRetorn(trasllat.getDataAcceptarRetorn());
					trasllatCommand2.setQuantitatEnviament(trasllat.getQuantitatEnviament());
					trasllatCommand2.setQuantitatRetorn(trasllat.getQuantitatRetorn());
					
					if(trasllat.getEsDiposit()){
						if(trasllat.getEstablimentByTdiCodeor().getId().equals(idEstabliment)){
							trasllatCommand2.setQuantitatMostrar(trasllat.getQuantitatRetorn());
							trasllatCommand2.setSentit(1);
						} else {
							trasllatCommand2.setQuantitatMostrar(trasllat.getQuantitatEnviament());
							trasllatCommand2.setSentit(new Integer(0));
							trasllatCommand2.setEstablimentByTdiCodeor(trasllat.getEstablimentByTdiCodede());
							trasllatCommand2.setEstablimentByTdiCodede(trasllat.getEstablimentByTdiCodeor());
						}
					} else {
						trasllatCommand2.setQuantitatMostrar(trasllat.getQuantitatEnviament());
					}
					
					//Categorid d'oli
					CategoriaOli categoria = null;
					
					for(Iterator it2 = trasllat.getTraza().getTrazasForTtrCodtrafill().iterator(); it2.hasNext();){
						Traza t = (Traza)it2.next();

						if (t.getTipus().equals(Constants.CODI_TRAZA_TIPUS_ENTRADA_DIPOSIT)){
							
							oliConsultaEjb.setHibernateTemplate(getHibernateTemplate());
							EntradaDiposit ed = oliConsultaEjb.findEntradaDipositByTraza(t.getId());
							
							categoria = ed.getPartidaOli().getCategoriaOli();
						}
					}
					trasllatCommand2.setCategoriaOli(categoria);
					
					listaEntrades.add(trasllatCommand2);
        		}
        		
        		TrasllatCommand trasllatCommand3 = null; 
        		for(Iterator it=listaTrasllatSortidaPendent.iterator();it.hasNext();){
        			Trasllat trasllat = (Trasllat)it.next();
        			String nomDiposits = "";
        			
        			if (trasllat.getValid()){
	        			
        				if(trasllat.getDiposits()!= null){
	        				for(Iterator itDip=trasllat.getDiposits().iterator();itDip.hasNext();){
	        					Diposit diposit = (Diposit)itDip.next();
	        					nomDiposits += diposit.getCodiAssignat() + " ";
	        				}
	        			}
        			
	        			trasllatCommand3 = new TrasllatCommand(
								trasllat.getEstablimentByTdiCodede(), 
								trasllat.getTraza(),
								trasllat.getEstablimentByTdiCodeor(), 
								trasllat.getData(),
								trasllat.getAcceptatTrasllat(), 
								trasllat.getRetornatEstablimentOrigen(),
								trasllat.getTraslladant(), 
								trasllat.getValid(), 
								trasllat.getEsDiposit(), 
								trasllat.getDataAlta(), 
								trasllat.getDiposits());//, trasllat.getIdPartida());
						trasllatCommand3.setId(trasllat.getId());
						
						trasllatCommand3.setDataAcceptarEnviament(trasllat.getDataAcceptarEnviament());
						trasllatCommand3.setDataAcceptarRetorn(trasllat.getDataAcceptarRetorn());
						trasllatCommand3.setQuantitatEnviament(trasllat.getQuantitatEnviament());
						trasllatCommand3.setQuantitatRetorn(trasllat.getQuantitatRetorn());
						
						trasllatCommand3.setNomDiposits(nomDiposits);
						
						if(trasllat.getEstablimentByTdiCodeor().getId().equals(idEstabliment)){
							trasllatCommand3.setQuantitatMostrar(trasllat.getQuantitatEnviament());
							trasllatCommand3.setSentit(new Integer(0));
						} else {
							trasllatCommand3.setQuantitatMostrar(trasllat.getQuantitatRetorn());
							trasllatCommand3.setSentit(1);
							if(trasllat.getEsDiposit()){
								trasllatCommand3.setEstablimentByTdiCodeor(trasllat.getEstablimentByTdiCodede());
								trasllatCommand3.setEstablimentByTdiCodede(trasllat.getEstablimentByTdiCodeor());
							}
						}
						
						//Categorid d'oli
						CategoriaOli categoria = null;
						
						for(Iterator it2 = trasllat.getTraza().getTrazasForTtrCodtrafill().iterator(); it2.hasNext();){
							Traza t = (Traza)it2.next();
	
							if (t.getTipus().equals(Constants.CODI_TRAZA_TIPUS_ENTRADA_DIPOSIT)){
								
								oliConsultaEjb.setHibernateTemplate(getHibernateTemplate());
								EntradaDiposit ed = oliConsultaEjb.findEntradaDipositByTraza(t.getId());
								
								categoria = ed.getPartidaOli().getCategoriaOli();
							}
						}
						trasllatCommand3.setCategoriaOli(categoria);
						
						listaSortidesPendents.add(trasllatCommand3);
        			}
        		}
        		
        		if(!errors.hasErrors()){
        			myModel.put("llistatSortidesPendents", listaSortidesPendents);
        			myModel.put("llistatSortides", listaSortides);
        			myModel.put("llistatEntrades", listaEntrades);
        			myModel.put("trazaTipusEntradaVolant", trazaTipusEntradaVolant);
        		}
        		
        		consulta.setDataInici(dataInici);
        		consulta.setDataFi(dataFi);
        		consulta.setIdEstabliment(idEstabliment);
        		consulta.setFromEstabliment(fromEstabliment);
        		
        	}
        	myModel.put("path", "consultar_movimentsEntreEstabliments");
        	if(consulta.getFromEstabliment()!= null && consulta.getFromEstabliment().equalsIgnoreCase("true")){
    			Establiment establiment =(Establiment)session.getAttribute(establimentSessionKey);
    			if (establiment==null){
    				if (idEstabliment != null){
    					oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
        				establiment =(Establiment)oliInfraestructuraEjb.establimentAmbId(idEstabliment);        				
        			}
        		}
    			if(establiment.getNom()!= null && !establiment.getNom().equalsIgnoreCase("")){
    				myModel.put("path", "consulta_movimentsEntreEstablimentsEstabliment");
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
    	ConsultaMovimentsEntreEstablimentsLlistatCommand consulta = (ConsultaMovimentsEntreEstablimentsLlistatCommand)request.getSession().getAttribute("consulta");
    	if (consulta == null) 
    		consulta = new ConsultaMovimentsEntreEstablimentsLlistatCommand();
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
	 * Metodo 'setTrazaTipusSortidaVolant'
	 * @param trazaTipusSortidaVolant
	 */
	public void setTrazaTipusEntradaVolant(String trazaTipusEntradaVolant) {
		this.trazaTipusEntradaVolant = trazaTipusEntradaVolant;
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
