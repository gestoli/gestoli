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

import es.caib.gestoli.front.util.Util;
import es.caib.gestoli.logic.interfaces.OliConsultaEjb;
import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;
import es.caib.gestoli.logic.model.Campanya;
import es.caib.gestoli.logic.model.DescomposicioPartidaOliva;
import es.caib.gestoli.logic.model.Establiment;
import es.caib.gestoli.logic.model.PartidaOliva;
import es.caib.gestoli.logic.model.VarietatOliva;

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
public class ConsultaLlistatController extends SimpleFormController {

    /*
     * Objeto para escribir mensajes de log.
     */
	private static Logger logger = Logger.getLogger(ConsultaLlistatController.class);

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
    	ConsultaLlistatCommand consulta = (ConsultaLlistatCommand) command;
    	Map myModel = new HashMap();
    	HttpSession session = request.getSession();
    	try {
    		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//    		myModel.put("bodegaId", consulta.getBodega());
    		if (consulta.getDataInici() != null) myModel.put("dataInici", sdf.format(consulta.getDataInici()));
    		if (consulta.getDataFi() != null) myModel.put("dataFi", sdf.format(consulta.getDataFi()));
    		if (consulta.getIdCampanya() != null) myModel.put("idCampanya", consulta.getIdCampanya());
    		if (consulta.getIdEstabliment() != null) myModel.put("idEstabliment", consulta.getIdEstabliment());

    		myModel.put("path", "consultar_entradesOliva");
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
	    				myModel.put("path", "consulta_entradaOlivaEstabliment");
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
        	ConsultaLlistatCommand consulta = (ConsultaLlistatCommand) command;
        	if (Util.isDataCorrecta(request.getParameter("dataInici"), "dd/MM/yyyy")
        			&& Util.isDataCorrecta(request.getParameter("dataFi"), "dd/MM/yyyy")) {
        		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        		Date dataInici;// = sdf.parse(request.getParameter("dataInici"));
        		Date dataFi;// = sdf.parse(request.getParameter("dataFi"));
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
        				//TODO: Cambiar este bloque por getDataFi() cuando se añada la variable en la campaña.
        				//Dejar este bloque si dataFi = null (campaña actual).
        				Calendar cal = Calendar.getInstance();
        				cal.setTime( campanya.getData() );
        				cal.add( Calendar.YEAR, 1 );
        				cal.add( Calendar.DATE, -1 );
        				dataFi = cal.getTime();
        		}else {
        			dataInici = sdf.parse(request.getParameter("dataInici"));
        			dataFi = sdf.parse(request.getParameter("dataFi"));
        		}
        		
        		/*else if(campanyes.iterator().hasNext()){
        			idCampanya =((Campanya)campanyes.iterator().next()).getId();
        			consulta.setIdCampanya(idCampanya);
        		}*/
        		
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
        		Collection lista = oliConsultaEjb.entradesOlivaConsulta(dataInici, dataFi, idEstabliment);
        		//if (lista.size() == 0) throw new Exception();
        		
        		Collection llistat = oliConsultaEjb.entradesOlivaConsulta(dataInici, dataFi, idEstabliment);;
        		for(Object o: lista){
        			PartidaOliva po = (PartidaOliva)o;
        			
        			if (po.getEsOlivaDeTaula())	llistat.remove(o);
        		}
        		
        		myModel.put("llistat", llistat);
        		
        		//Llistat desglosat per varietats experimentals i de do
        		List<Object[]> listAux = new ArrayList<Object[]>();
    			List<Object[]> listAuxExperimental = new ArrayList<Object[]>();
    			
        		for(Object o: llistat){
        			PartidaOliva po = (PartidaOliva)o;
        			
        			Collection descomposicions = po.getDescomposicioPartidesOlives();
        			
        			for(Object o2: descomposicions){
        				DescomposicioPartidaOliva dpo = (DescomposicioPartidaOliva)o2;
        				
        				VarietatOliva var = dpo.getDescomposicioPlantacio().getVarietatOliva();
        				
        				Object[] obj = new Object[3];
        				obj[0] = dpo.getDescomposicioPlantacio().getVarietatOliva().getId();
        				
        				if (var.getExperimental()!= null && var.getExperimental().booleanValue()){
        					obj[1] = var.getNomVarietat(); // + " - " + dpo.getDescomposicioPlantacio().getPlantacio().getFinca().getNom();
        				} else {
        					obj[1] = var.getNom(); // + " - " + dpo.getDescomposicioPlantacio().getPlantacio().getFinca().getNom();
        				}
        				
        				obj[2] = dpo.getKilos();
        				
        				//Comprovam si la varietat hi es
        				//Si hi es, sumam els kilos, sinó afageim varietat nova
        				boolean trobat = false;
        				
        				if (!po.getTeVarietatsExperimental()){
	        				for(Object[] o3: listAux){
	        					if(o3[0].equals(obj[0])){
	        						trobat = true;
	        						Double val = (Double)o3[2] + (Double)obj[2];
	        						o3[2] = val;
	        					}
	        				}
        				}
        				
        				if (po.getTeVarietatsExperimental()){
	        				for(Object[] o3: listAuxExperimental){
	        					if(o3[0].equals(obj[0])){
	        						trobat = true;
	        						Double val = (Double)o3[2] + (Double)obj[2];
	        						o3[2] = val;
	        					}
	        				}
        				}
        				
        				if(!trobat) { 
        					if (po.getTeVarietatsExperimental()){
        						listAuxExperimental.add(obj);
        					} else {
        						listAux.add(obj);
        					}
        				}
        			}
        		}
        		
        		myModel.put("llistatVarietats", listAux);
        		myModel.put("llistatVarietatsExperimentals", listAuxExperimental);

        		consulta.setDataInici(dataInici);
        		consulta.setDataFi(dataFi);
        		consulta.setIdCampanya(idCampanya);
        		consulta.setIdEstabliment(idEstabliment);
        		consulta.setFromEstabliment(fromEstabliment);
        		
        	}
        	myModel.put("path", "consultar_entradesOliva");
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
	    				myModel.put("path", "consulta_entradaOlivaEstabliment");
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
    	ConsultaLlistatCommand consulta = (ConsultaLlistatCommand)request.getSession().getAttribute("consulta");
    	if (consulta == null) 
    		consulta = new ConsultaLlistatCommand();
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
    		
    		oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
        	Collection campanyes = oliInfraestructuraEjb.campanyaCercaTotes();
			request.setAttribute("campanyes", campanyes);
			Long idCampanya = null;
    		if(request.getParameter("idCampanya")!= null){
    			if(!request.getParameter("idCampanya").equals("")){
    				idCampanya = Long.valueOf(request.getParameter("idCampanya"));
    			}    			
    		}/*else if(campanyes.iterator().hasNext()){
    			idCampanya =((Campanya)campanyes.iterator().next()).getId();
    			consulta.setIdCampanya(idCampanya);
    		}*/
    		
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
