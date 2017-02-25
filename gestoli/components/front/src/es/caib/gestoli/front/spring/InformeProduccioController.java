package es.caib.gestoli.front.spring;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractFormController;
import org.springframework.web.servlet.mvc.BaseCommandController;
import org.springframework.web.servlet.mvc.SimpleFormController;

import es.caib.gestoli.logic.interfaces.OliConsultaEjb;
import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;
import es.caib.gestoli.logic.model.Campanya;
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
public class InformeProduccioController extends SimpleFormController {

    /*
     * Objeto para escribir mensajes de log.
     */
	private static Logger logger = Logger.getLogger(InformeProduccioController.class);

    /*
     * Servicio para acceder a las funcionalidades de la aplicación.
     */
	private OliConsultaEjb oliConsultaEjb;
	private OliInfraestructuraEjb oliInfraestructuraEjb;
	private String desqualificat;
	private String pendentQualificar;
	private String qualificat;
	private String campanyaSessionKey;
	private HibernateTemplate hibernateTemplate;
	
    
    
	
	/**
	 * Se llama cuando se aceptan las modificaciones del objeto. Solo
	 * se ejecuta esta función en el caso de que se haya ejecutado la
	 * validación sin producir ningún error.
	 * @see SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.validation.BindException)
	 */
	public ModelAndView onSubmit(
			HttpServletRequest request,
			HttpServletResponse response,
			Object command,
			BindException errors)
	throws ServletException {
		
		InformeProduccioCommand cmd = (InformeProduccioCommand)command;

		String goToUrl;
		if(cmd.getIdEstabliment()!=null){
			goToUrl=getSuccessView()+"?idEstabliment="+cmd.getIdEstabliment();
		}else{
			goToUrl=getSuccessView()+"?idEstabliment=";
		}
		
		try{
//			Collection establecimientos = new ArrayList();
			if (cmd.getIdCampanya() != null) {
				goToUrl+="&idCampanya="+cmd.getIdCampanya();
//				establecimientos = oliInfraestructuraEjb.establimentCercaTotsActivos(cmd.getIdCampanya());
			} else 
//				establecimientos = oliInfraestructuraEjb.establimentCercaTots();
//			}
//			request.setAttribute("establecimientos", establecimientos);
			if (cmd.getDataInici()!=null){
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				goToUrl+="&dataInici="+URLEncoder.encode(sdf.format(cmd.getDataInici()), "UTF-8");
			}
//			Collection campanyes = oliInfraestructuraEjb.campanyaCercaTotes();
//			request.setAttribute("campanyes", campanyes);
			
			
		}catch(Exception e){
		}
	
		return new ModelAndView(goToUrl);
			
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
        	InformeProduccioCommand consulta = (InformeProduccioCommand) command;
        	oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
        	// estId es l'idOriginal
        	Long estId=consulta.getIdEstabliment();
        	Long establimentId = null;
        	if (estId != null) {
        		Establiment est = oliInfraestructuraEjb.establimentAmbIdOriginal(estId);
        		establimentId = est.getId();
        	}
        	Date dataInici=consulta.getDataInici();
        	Date dataFi=null;
        	Long idCampanya = consulta.getIdCampanya();
        	if (idCampanya != null) {
				Campanya campanya = oliInfraestructuraEjb.campanyaAmbId(idCampanya.intValue());
				dataInici = campanya.getData();
				dataFi = campanya.getDataFi();
        	}
        	if (dataInici == null) {
        		idCampanya = (Long) session.getAttribute(campanyaSessionKey);
        		Campanya campanya = oliInfraestructuraEjb.campanyaAmbId(idCampanya.intValue());
        		dataInici = campanya.getData();
        		dataFi = campanya.getDataFi();
        	}
        	consulta.setIdCampanya(idCampanya);
        	consulta.setDataInici(dataInici);
        	
        	List lQualificats = new ArrayList();
        	lQualificats.add(Integer.valueOf(qualificat));

        	List lDesqualificats = new ArrayList();
        	lDesqualificats.add(Integer.valueOf(desqualificat));
        	
        	List lPendents = new ArrayList();
        	lPendents.add(Integer.valueOf(pendentQualificar));
        	
        	List lNoDO = new ArrayList();
        	lNoDO.add(Integer.valueOf(pendentQualificar));
        	lNoDO.add(Integer.valueOf(desqualificat));
        	
        	//Establecimierntos
            Collection establecimientos = new ArrayList();
            establecimientos = oliInfraestructuraEjb.establimentCercaTotsActivos(idCampanya);
            myModel.put("establecimientos", establecimientos);
            
            oliConsultaEjb.setHibernateTemplate(getHibernateTemplate());
            Double[] existencies = oliConsultaEjb.getQuantitatOliExistenciesQualificacioAndEstabliment(establimentId, dataInici);

            // EXISTENCIES INICIALS
            myModel.put("quantitatOliDoExistencies", existencies[Constants.CATEGORIA_DO-1]);
            myModel.put("quantitatOliNoDoExistencies", existencies[Constants.CATEGORIA_NO_DO-1]);
            myModel.put("quantitatOliPendentExistencies", existencies[Constants.CATEGORIA_PENDENT-1]);

            if (dataFi != null){
            	// OLIVA MOLTURADA
            	myModel.put("quantitatOlivaDo", oliConsultaEjb.getTotalOlivaMolturada(dataInici, dataFi, new Boolean(true), establimentId));
            	myModel.put("quantitatOlivaNoDo", oliConsultaEjb.getTotalOlivaMolturada(dataInici, dataFi, new Boolean(false), establimentId));
            	
            	// OLI ELABORAT
                myModel.put("quantitatOliNoDoElaborat", oliConsultaEjb.getQuantitatOliElaboratDiscriminantQualificacioAndEstabliment(establimentId, dataInici, dataFi, lDesqualificats.toArray()));
                myModel.put("quantitatOliPendentElaborat", oliConsultaEjb.getQuantitatOliElaboratDiscriminantQualificacioAndEstabliment(establimentId, dataInici, dataFi, lPendents.toArray()));
            	
            	// OLI VENUT 
                myModel.put("quantitatOliDoVenut", oliConsultaEjb.getQuantitatOliVenutDiscriminantQualificacioAndEstabliment(establimentId, dataInici, dataFi, lQualificats.toArray()));
                myModel.put("quantitatOliNoDoVenut", oliConsultaEjb.getQuantitatOliVenutDiscriminantQualificacioAndEstabliment(establimentId, dataInici, dataFi, lDesqualificats.toArray()));
                myModel.put("quantitatOliPendentVenut", oliConsultaEjb.getQuantitatOliVenutDiscriminantQualificacioAndEstabliment(establimentId, dataInici, dataFi, lPendents.toArray()));
                
                // OLI DESQUALIFICAT
                myModel.put("qualificatToDesqualificat", oliConsultaEjb.getQuantitatOliCanviatCategoriaDO_NoDO(establimentId, dataInici, dataFi));
            } else {
            	// OLIVA MOLTURADA
            	myModel.put("quantitatOlivaDo", oliConsultaEjb.getTotalOlivaMolturada(dataInici, new Boolean(true), establimentId));
            	myModel.put("quantitatOlivaNoDo", oliConsultaEjb.getTotalOlivaMolturada(dataInici, new Boolean(false), establimentId));
            	
            	// OLI ELABORAT
                myModel.put("quantitatOliNoDoElaborat", oliConsultaEjb.getQuantitatOliElaboratDiscriminantQualificacioAndEstabliment(establimentId, dataInici, lDesqualificats.toArray()));
                myModel.put("quantitatOliPendentElaborat", oliConsultaEjb.getQuantitatOliElaboratDiscriminantQualificacioAndEstabliment(establimentId, dataInici, lPendents.toArray()));
                
            	// OLI VENUT 
                myModel.put("quantitatOliDoVenut", oliConsultaEjb.getQuantitatOliVenutDiscriminantQualificacioAndEstabliment(establimentId, dataInici, lQualificats.toArray()));
                myModel.put("quantitatOliNoDoVenut", oliConsultaEjb.getQuantitatOliVenutDiscriminantQualificacioAndEstabliment(establimentId, dataInici, lDesqualificats.toArray()));
                myModel.put("quantitatOliPendentVenut", oliConsultaEjb.getQuantitatOliVenutDiscriminantQualificacioAndEstabliment(establimentId, dataInici, lPendents.toArray()));
                
                // OLI DESQUALIFICAT
                myModel.put("qualificatToDesqualificat", oliConsultaEjb.getQuantitatOliCanviatCategoriaDO_NoDO(establimentId, dataInici, null));
            }
            
            //myModel.put("qualificatToDesqualificat", oliConsultaEjb.getQuantitatOliCanviatDO(idCampanya, estId, dataInici, lPendents.toArray(), new Boolean(true)));
            //myModel.put("desqualificatToQualificat",  oliConsultaEjb.getQuantitatOliCanviatDO(idCampanya, estId, dataInici, lNoDO.toArray(), new Boolean(false)));
            myModel.put("desqualificatToQualificat",  new Double(0));

 
        	myModel.put("path", "consulta_informeProduccio");
        	
        	
        } catch (Exception ex) {
        	logger.error("Error obtenint llistat de informe de produccio", ex);
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
    	InformeProduccioCommand consulta = new InformeProduccioCommand();

    	try{
    		Long idEstabliment = null;    		
    		if(request.getParameter("idEstabliment")!= null && !request.getParameter("idEstabliment").equals("") ){
    			idEstabliment = Long.valueOf(request.getParameter("idEstabliment"));
    		}    				
    		 
    		Date dataInici=null;
    		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    		if(request.getParameter("dataInici")!= null && !request.getParameter("dataInici").equals("") ){
    			String urlFecha=request.getParameter("dataInici");
    			dataInici = sdf.parse(URLDecoder.decode(urlFecha, "UTF-8"));
    		}    				
    		   
    		oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
    		Collection campanyes = oliInfraestructuraEjb.campanyaCercaTotes();
			request.setAttribute("campanyes", campanyes);
			Long idCampanya = null;
			Collection establecimientos = new ArrayList();
    		if(request.getParameter("idCampanya")!= null){
    			if(!request.getParameter("idCampanya").equals("")){
    				idCampanya = Long.valueOf(request.getParameter("idCampanya"));
    				Campanya campanya = oliInfraestructuraEjb.campanyaAmbId(idCampanya.intValue());
    				dataInici = campanya.getData();
    				establecimientos = oliInfraestructuraEjb.establimentCercaTotsActivos(idCampanya);
    			}    			
    		}
    		if (establecimientos.size() == 0) establecimientos = oliInfraestructuraEjb.establimentCercaTots();
    		request.setAttribute("establecimientos", establecimientos);
    		
    		if(idEstabliment != null) consulta.setIdEstabliment(idEstabliment);
    		if(dataInici != null) consulta.setDataInici(dataInici);
    		if(idCampanya != null) consulta.setIdCampanya(idCampanya);
    		
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
		binder.registerCustomEditor(
				Long.class,
				new CustomNumberEditor(Long.class, true));
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




	public String getDesqualificat() {
		return desqualificat;
	}




	public void setDesqualificat(String desqualificat) {
		this.desqualificat = desqualificat;
	}




	public String getPendentQualificar() {
		return pendentQualificar;
	}




	public void setPendentQualificar(String pendentQualificar) {
		this.pendentQualificar = pendentQualificar;
	}




	public String getQualificat() {
		return qualificat;
	}




	public void setQualificat(String qualificat) {
		this.qualificat = qualificat;
	}




	public String getCampanyaSessionKey() {
		return campanyaSessionKey;
	}




	public void setCampanyaSessionKey(String campanyaSessionKey) {
		this.campanyaSessionKey = campanyaSessionKey;
	}


}
