/**
 * ProcesMoureDipositFormController.java
 */
package es.caib.gestoli.front.spring; 

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.propertyeditors.CustomBooleanEditor;
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

import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;
import es.caib.gestoli.logic.interfaces.OliProcessosEjb;
import es.caib.gestoli.logic.model.Diposit;
import es.caib.gestoli.logic.model.Establiment;



/**
 * <p>Controlador per a l'acció d'executar un proces d'entrada
 * de oliva.</p>
 * <p>Aquest controlador distingueix entre les peticions del
 * tipus GET y les de tipus POST. Si la petición es de tipus POST
 * s'executa l'acció de creació o d'edició. Si la petició es de
 * tipo GET només mostra la página.
 *
 * @author cperez <cperez@at4.net>
 */
public class ProcesMoureDipositFormController extends SimpleFormController {

    private static Logger logger = Logger.getLogger(ProcesMoureDipositFormController.class);
    private OliProcessosEjb oliProcessosEjb;
    private OliInfraestructuraEjb oliInfraestructuraEjb;
    private String establimentSessionKey;
    private String seleccioSessionKeyOrigen;
    private String campanyaSessionKey;
    private String rolDoControlador;
    private String rolDoGestor;

	private Integer tipusEstablimentTafona;
	private Integer tipusEstablimentEnvasadora;
	private Integer tipusEstablimentTafonaEnvasadora;
	private String emailFrom;

	private HibernateTemplate hibernateTemplate;
    
    /**
     * Es crida quan s'accepten les modificacions a l'objecte. Només
     * s'executa aquesta funció en el cas de que s'hagi executat la
     * validació sense produïr cap error.
     * 
     * @see SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.validation.BindException)
     */
    public ModelAndView onSubmit(
            HttpServletRequest request,
            HttpServletResponse response,
            Object command,
            BindException errors)
    throws ServletException {
    	
    	ProcesMoureDipositCommand pMoureDiposit = (ProcesMoureDipositCommand)command;    	
    	
    	Map myModel = new HashMap();
    	Establiment establiment = (Establiment) request.getSession().getAttribute(establimentSessionKey);
    	Long idMoureDiposit = null;
    	
		if (isCreate(request)) {
			try{ 
				oliProcessosEjb.setHibernateTemplate(getHibernateTemplate());
				//Insertamos registro en la trasllat diposit
				oliProcessosEjb.setMessageSourceAccessor(getMessageSourceAccessor());
				oliProcessosEjb.setTipusEstablimentTafona(tipusEstablimentTafona);
				oliProcessosEjb.setTipusEstablimentEnvasadora(tipusEstablimentEnvasadora);
				oliProcessosEjb.setTipusEstablimentTafonaEnvasadora(tipusEstablimentTafonaEnvasadora);
				Long[] volants = oliProcessosEjb.generarMoureDiposits(pMoureDiposit.getData(), pMoureDiposit.getDipositsOrigen(), pMoureDiposit.getEstablimentDesti(), establiment, rolDoControlador, rolDoGestor, emailFrom);
						
				ControllerUtils.saveMessageInfo(request, missatge("proces.moureDiposit.missatge.ok"));
				Integer sentit = null;
//				if (request.getParameter("sentit") != null) {
//					try {
//						sentit = Integer.parseInt(request.getParameter("sentit"));
//					} catch(Exception e) {}
//				}
				ControllerUtils.showVolantCirculacio(request, volants, 0);//sentit);
				
	    	} catch (Exception e) {
	    		 logger.error("Error processant el MoureDiposit de l'oli", e);
	             ControllerUtils.saveMessageError(request, missatge("proces.moureDiposit.missatge.no"));
			}
		}
        
		String desti = "EstablimentPrincipal.html";
		
		return new ModelAndView("redirect:" + desti);
    }


    

    /**
     * Retorna totes les dades del model necessàries per a mostrar
     * el formulari d'inserció (LOVs y coses per l'estil) si no
     * n'hi ha.
     * 
     * @see SimpleFormController#referenceData(javax.servlet.http.HttpServletRequest, java.lang.Object, org.springframework.validation.Errors)
     */
    protected Map referenceData(
            HttpServletRequest request,
            Object command,
            Errors errors) throws Exception {
        
        Map myModel = new HashMap();
        
        Establiment establiment = (Establiment) request.getSession().getAttribute(establimentSessionKey);
        
        Collection origenList = (Collection)request.getSession().getAttribute(seleccioSessionKeyOrigen);
        Collection origen = null;        
        
        oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
        if (origenList.size() != 0) origen = oliInfraestructuraEjb.dipositsInfo(origenList.toArray());
        
        Collection establiments = new ArrayList();
        Long campanyaId = (Long)request.getSession().getAttribute(campanyaSessionKey);
        oliProcessosEjb.setHibernateTemplate(getHibernateTemplate()); 
        establiments = oliProcessosEjb.findRestoEstablimentsActivosZonaFicticia(campanyaId,establiment.getId());
        myModel.put("establiments", establiments);
        
        myModel.put("origen", origen);
        String tipusProces = request.getParameter("tipusProces");
    	myModel.put("tipusProces", tipusProces);
        String pas = request.getParameter("pas");
    	myModel.put("pas", pas);
    	myModel.put("path", "moure_diposit");
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
    	
    	ProcesMoureDipositCommand pMoureDiposit = new ProcesMoureDipositCommand();
    	
    	Collection origenList = (Collection)request.getSession().getAttribute(seleccioSessionKeyOrigen);
    	

    	try {    		    		
			
			//			if (!isFormSubmission(request)) {
			//					pMoureDiposit.setData(new Date());			
			//			} 
			
			Diposit[] dipositsOrigen = new Diposit[origenList.size()];
			
			for (int i = 0; i < dipositsOrigen.length; i++) {
				dipositsOrigen[i] = new Diposit();
			}
			
			pMoureDiposit.setDipositsOrigen(dipositsOrigen);
			
		} catch (Exception e) {
			logger.error("EXCEPTION", e);
		}
    	    	
        return pMoureDiposit;
        
    }

    /**
     * Configuració del <i>binder</i>. Si hi ha camps en el
     * <i>command</i> amb tipus que no siguin <i>String</i>
     * s'ha de configurar el seu corresponent binder aquí.
     * 
     * @see BaseCommandController#initBinder(javax.servlet.http.HttpServletRequest, org.springframework.web.bind.ServletRequestDataBinder)
     */
    protected void initBinder(
            HttpServletRequest request,
            ServletRequestDataBinder binder)
    throws Exception {
    	binder.registerCustomEditor(
    			Integer.class,
        		new CustomNumberEditor(Integer.class, true));
    	binder.registerCustomEditor(
        		Long.class,
        		new CustomNumberEditor(Long.class, true));
    	binder.registerCustomEditor(
        		Float.class,
        		new CustomNumberEditor(Float.class, true));
    	binder.registerCustomEditor(
        		Boolean.class,
        		new CustomBooleanEditor("S", "N", true));
        binder.registerCustomEditor(
        		Date.class,
        		new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), true));
    }


    /**
     * Injecció de la dependència oliProcessb
     *
     * @param oliProcessosEjb La classe a injectar.
     */
       
    public void setOliProcessosEjb(OliProcessosEjb oliProcessosEjb) {
		this.oliProcessosEjb = oliProcessosEjb;
	}
 
    /**
	 * @param infraestructuraEjb the infraestructuraEjb to set
	 */
	public void setOliInfraestructuraEjb(OliInfraestructuraEjb oliInfraestructuraEjb) {
		this.oliInfraestructuraEjb = oliInfraestructuraEjb;
	}

    /**
	 * @param establimentSessionKey the establimentSessionKey to set
	 */
	public void setEstablimentSessionKey(String establimentSessionKey) {
		this.establimentSessionKey = establimentSessionKey;
	}

	/**
     * Injecció de la dependència seleccioSessionKeyOrigen
     *
     * @param seleccioSessionKey La classe a injectar.
     */
    public void setSeleccioSessionKeyOrigen(String seleccioSessionKeyOrigen) {
        this.seleccioSessionKeyOrigen = seleccioSessionKeyOrigen;
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
	 * @param rolAdministracio the rolAdministracio to set
	 */
	public void setRolDoControlador(String rolDoControlador) {
		this.rolDoControlador = rolDoControlador;
	}
    
	public void setRolDoGestor(String rolDoGestor) {
		this.rolDoGestor = rolDoGestor;
	}
    
	private String missatge(String clave){
    	String valor= getMessageSourceAccessor().getMessage(clave);
    	return valor;
    }
    
    /**
	 * Indica si la petición es de creación o no.
	 * @param request
	 * @return true si es una petición de creación o false si es de edición.
	 */
	private boolean isCreate(HttpServletRequest request) {
		return (request.getParameter("id") == null);
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


	public Integer getTipusEstablimentTafona() {
		return tipusEstablimentTafona;
	}




	public void setTipusEstablimentTafona(Integer tipusEstablimentTafona) {
		this.tipusEstablimentTafona = tipusEstablimentTafona;
	}




	public Integer getTipusEstablimentEnvasadora() {
		return tipusEstablimentEnvasadora;
	}




	public void setTipusEstablimentEnvasadora(Integer tipusEstablimentEnvasadora) {
		this.tipusEstablimentEnvasadora = tipusEstablimentEnvasadora;
	}




	public Integer getTipusEstablimentTafonaEnvasadora() {
		return tipusEstablimentTafonaEnvasadora;
	}




	public void setTipusEstablimentTafonaEnvasadora(
			Integer tipusEstablimentTafonaEnvasadora) {
		this.tipusEstablimentTafonaEnvasadora = tipusEstablimentTafonaEnvasadora;
	}




	public String getEmailFrom() {
		return emailFrom;
	}




	public void setEmailFrom(String emailFrom) {
		this.emailFrom = emailFrom;
	}





	
}