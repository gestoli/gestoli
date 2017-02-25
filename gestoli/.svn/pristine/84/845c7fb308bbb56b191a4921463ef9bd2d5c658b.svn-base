/**
 * ProcesDesqualificaFormController.java
 */
package es.caib.gestoli.front.spring; 

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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
public class ProcesDesqualificarFormController extends SimpleFormController {

    private static Logger logger = Logger.getLogger(ProcesDesqualificarFormController.class);
    private OliProcessosEjb oliProcessosEjb;
    private OliInfraestructuraEjb oliInfraestructuraEjb;
    private String establimentSessionKey;
    private String seleccioSessionKeyOrigen;
    private String desqualificat;
    private String pendentQualificar;
    private String pendentQualificacioObservacio;
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
    	
    	ProcesDesqualificarCommand pDesqualifica = (ProcesDesqualificarCommand)command;
    	Establiment establiment = (Establiment) request.getSession().getAttribute(establimentSessionKey);
    	    	    	
		if (isCreate(request)) {
			try{ 
				oliProcessosEjb.setHibernateTemplate(getHibernateTemplate());
				oliProcessosEjb.generarDescalificacion(pDesqualifica.getData(), pDesqualifica.getDiposit(), establiment, desqualificat ,pendentQualificacioObservacio, pDesqualifica.getObservaciones());
				ControllerUtils.saveMessageInfo(request, missatge("proces.desqualificar.missatge.ok"));
				
	    	} catch (Exception e) {
	    		 logger.error("Error processant el Desqualifica de l'oli", e);
	             ControllerUtils.saveMessageError(request, missatge("proces.desqualificar.missatge.no"));
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
        
        
        Diposit deposito = new Diposit();
        if (origenList.size() != 0) {
        	oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
        	origen = oliInfraestructuraEjb.dipositsInfo(origenList.toArray());
        	for(Iterator it= origen.iterator();it.hasNext();){
        		deposito = (Diposit)it.next();
        	}
        	
        }
        
        myModel.put("establiment", establiment);
        myModel.put("deposito", deposito);       
        String tipusProces = request.getParameter("tipusProces");
    	myModel.put("tipusProces", tipusProces);
        String pas = request.getParameter("pas");
    	myModel.put("pas", pas);
    	myModel.put("path", "desqualificar_oli");
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
    	
    	ProcesDesqualificarCommand pDesqualificar = new ProcesDesqualificarCommand();
    	Establiment establiment = (Establiment) request.getSession().getAttribute(establimentSessionKey);    	
    	try {
    		if (!isFormSubmission(request)) {
					pDesqualificar.setData(new Date());
			
			} 	
			Diposit diposit = new Diposit();
			pDesqualificar.setDiposit(diposit);
			pDesqualificar.setEstabliment(establiment);
			
			
		} catch (Exception e) {
			logger.error("EXCEPTION", e);
		}
    	    	
        return pDesqualificar;
        
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
    
       
    
    public void setDesqualificat(String desqualificat) {
		this.desqualificat = desqualificat;
	}

	public void setPendentQualificar(String pendentQualificar) {
		this.pendentQualificar = pendentQualificar;
	}
	
	public void setPendentQualificacioObservacio(String pendentQualificacioObservacio) {
		this.pendentQualificacioObservacio = pendentQualificacioObservacio;
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

	
}