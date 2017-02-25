/**
 * ProcesLotFormController.java
 */
package es.caib.gestoli.front.spring; 

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
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
import es.caib.gestoli.logic.model.Lot;



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
public class ProcesEtiquetarOlivaTaulaFormController extends SimpleFormController {

    private static Logger logger = Logger.getLogger(ProcesEtiquetarOlivaTaulaFormController.class);
    private OliInfraestructuraEjb oliInfraestructuraEjb;
    private OliProcessosEjb oliProcessosEjb;
    private String seleccioSessionKeyOrigen;
    
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
    	
    	ProcesEtiquetarOlivaTaulaCommand pLot = (ProcesEtiquetarOlivaTaulaCommand)command;
    	
    	Lot lot = null;
		
    	try{
    		oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
    		lot = oliInfraestructuraEjb.lotAmbId(pLot.getId());


    		oliProcessosEjb.setHibernateTemplate(getHibernateTemplate());
    		oliProcessosEjb.etiquetar(lot, pLot.getIdEtiquetes(), pLot.getEtiquetesInicials(), pLot.getEtiquetesFinals());

    		ControllerUtils.saveMessageInfo(request, missatge("etiquetar.missatge.ok"));

    	} catch (Exception e) {
    		logger.error("Error processant l'etiquetatge", e);
    		ControllerUtils.saveMessageError(request, missatge("etiquetar.missatge.no"));
    	}
        
		//String forward = getSuccessView()+ "?tipusProces=2&pas=2&id=" + idElaboracio;
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
        
    	ProcesEtiquetarOlivaTaulaCommand pLot = (ProcesEtiquetarOlivaTaulaCommand) command;
    	
        Map myModel = new HashMap();
        
        //LOT
        Collection origenList = (Collection)request.getSession().getAttribute(seleccioSessionKeyOrigen);
        Collection origen = null;
        
        Lot lot = new Lot();
        if (origenList.size() != 0) {
        	oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
        	origen = oliInfraestructuraEjb.lotesOlivaTaulaInfo(origenList.toArray());
        	for(Iterator it= origen.iterator();it.hasNext();){
        		lot = (Lot)it.next();
        	}
        }
        
        myModel.put("lot", lot);
        
        //ETIQUETES EMPRADES
        if(lot != null){
	        oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
	        Collection etiquetesEmprades = oliInfraestructuraEjb.findEtiquetesLotEmpradesByLot(lot.getId(), lot.getBota().getElaboracio().getEsEcologic());
	        
	        if(!etiquetesEmprades.isEmpty()){
	        	myModel.put("etiquetesEmprades", etiquetesEmprades);
	        }
        }
        
        //ETIQUETES DISPONIBLES
        if(lot != null){
	        oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate()); 
	        Collection etiquetesDisponibles = oliInfraestructuraEjb.findEtiquetesLotDisponiblesByEstablimentILot(lot.getZona().getEstabliment().getId(), lot, true, lot.getBota().getElaboracio().getEsEcologic());
	        
	        if(!etiquetesDisponibles.isEmpty()){
	        	myModel.put("etiquetesDisponibles", etiquetesDisponibles);
	        }
        }
        
        String tipusProces = request.getParameter("tipusProces");
    	myModel.put("tipusProces", tipusProces);
    	
        String pas = request.getParameter("pas");
    	myModel.put("pas", pas);
    	
    	myModel.put("path", "etiquetar");
    	
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
    	
    	ProcesEtiquetarOlivaTaulaCommand pLot = new ProcesEtiquetarOlivaTaulaCommand();
    	
	    	try {
	            Collection origenList = (Collection)request.getSession().getAttribute(seleccioSessionKeyOrigen);
	            Collection origen = null;
	            
	            Lot lot = new Lot();
	            if (origenList.size() != 0) {
	            	oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
	            	origen = oliInfraestructuraEjb.lotesOlivaTaulaInfo(origenList.toArray());
	            	for(Iterator it= origen.iterator();it.hasNext();){
	            		lot = (Lot)it.next();
	            	}
	            }
	            
				pLot = new ProcesEtiquetarOlivaTaulaCommand(lot);
				
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
		        Collection etiquetesDisponibles = oliInfraestructuraEjb.findEtiquetesLotDisponiblesByEstablimentILot(lot.getZona().getEstabliment().getId(), lot, true, lot.getBota().getElaboracio().getEsEcologic());
		        
		        Long[] etiid = new Long[etiquetesDisponibles.size()]; 
		        Integer[] etiini = new Integer[etiquetesDisponibles.size()];
		        Integer[] etifi = new Integer[etiquetesDisponibles.size()];
		        
		        pLot.setIdEtiquetes(etiid);
		        pLot.setEtiquetesInicials(etiini);
		        pLot.setEtiquetesFinals(etifi);
	        	
			} catch (Exception e) {
				logger.error("EXCEPTION", e);
			}
    	    	
        return pLot;
        
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
    }
 
    /**
	 * @param infraestructuraEjb the infraestructuraEjb to set
	 */
	public void setOliInfraestructuraEjb(OliInfraestructuraEjb oliInfraestructuraEjb) {
		this.oliInfraestructuraEjb = oliInfraestructuraEjb;
	}

    /**
	 * @param oliProcessosEjb the oliProcessosEjb to set
	 */
	public void setOliProcessosEjb(OliProcessosEjb oliProcessosEjb) {
		this.oliProcessosEjb = oliProcessosEjb;
	}

	/**
     * Injecció de la dependència seleccioSessionKeyOrigen
     *
     * @param seleccioSessionKey La classe a injectar.
     */
    public void setSeleccioSessionKeyOrigen(String seleccioSessionKeyOrigen) {
        this.seleccioSessionKeyOrigen = seleccioSessionKeyOrigen;
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