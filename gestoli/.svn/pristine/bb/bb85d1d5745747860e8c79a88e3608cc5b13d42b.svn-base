/**
 * ProcesTrasbalsFormController.java
 */
package es.caib.gestoli.front.spring; 

import java.text.DecimalFormat;
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
import es.caib.gestoli.logic.util.Constants;



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
public class ProcesTrasbalsFormController extends SimpleFormController {

    private static Logger logger = Logger.getLogger(ProcesTrasbalsFormController.class);
    private OliProcessosEjb oliProcessosEjb;
    private OliInfraestructuraEjb oliInfraestructuraEjb;
    private String establimentSessionKey;
    private String seleccioSessionKeyOrigen;
    private String seleccioSessionKeyDesti;
    private String desqualificat;
    private String pendentQualificar;
    private String mescla;
    
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
    	
    	ProcesTrasbalsCommand pTrasbals = (ProcesTrasbalsCommand)command;
    	Establiment establiment = (Establiment) request.getSession().getAttribute(establimentSessionKey);
    	
    	Map myModel = new HashMap();
    	
    	Long idTrasbals = null;
    	
		if (isCreate(request)) {
			try{ 
				oliProcessosEjb.setHibernateTemplate(getHibernateTemplate());
				oliProcessosEjb.generarTrasbals(pTrasbals.getData(), pTrasbals.getDipositsOrigen(), pTrasbals.getDipositDesti(), pTrasbals.getLitros(), 
						pTrasbals.getLitrosAfegits(), pTrasbals.getAcidesa(), establiment, desqualificat, pendentQualificar, mescla, pTrasbals.getIdPartidaOli());
				
				ControllerUtils.saveMessageInfo(request, missatge("proces.trasbals.missatge.crear.ok"));
				
	    	} catch (Exception e) {
	    		 logger.error("Error processant el trasbals de l'oli", e);
	             ControllerUtils.saveMessageError(request, missatge("proces.trasbals.missatge.no"));
			}
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
        
        Map myModel = new HashMap();
        
        Establiment establiment = (Establiment) request.getSession().getAttribute(establimentSessionKey);
        
        Collection origenList = (Collection)request.getSession().getAttribute(seleccioSessionKeyOrigen);
        Collection destiList = (Collection)request.getSession().getAttribute(seleccioSessionKeyDesti);
        
        Collection origen = null;
        Collection desti = null;
        
        Boolean noDo = false;
        Boolean DO = true;
        Long idPartida = null;
        oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
        if (origenList.size() != 0) origen = oliInfraestructuraEjb.dipositsInfo(origenList.toArray());
        Iterator ito = origen.iterator();
        while (ito.hasNext()) {
        	Diposit diposit = (Diposit) ito.next();
//        	if (dipositDesti.getVolumActual() == null) dipositDesti.setVolumActual(new Double(0));
        	if (diposit.getPartidaOli() != null){
        		if(diposit.getVolumActual() != null && diposit.getVolumActual() > 0.0){
	        		if(diposit.getPartidaOli().getCategoriaOli().getId() == Constants.CATEGORIA_NO_DO){
	        			noDo = true;
	        			break;
	        		} else if(diposit.getPartidaOli().getCategoriaOli().getId() == Constants.CATEGORIA_PENDENT){
	        			DO = false;
	        		} else {
	        			// Si es mesclen diferents partides, encara que siguin totes de DO, el resultat serà Pendent
	        			if (idPartida == null){
	        				idPartida = diposit.getPartidaOli().getId();
	        			} else if (!diposit.getPartidaOli().getId().equals(idPartida)){
	        				DO = false;
	        			}
	        		}
        		}
        	} 
        }
        oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
        if (destiList.size() != 0) desti = oliInfraestructuraEjb.dipositsInfo(destiList.toArray());
        Iterator itd = desti.iterator();
        Diposit dipositDesti = null;
        if (itd.hasNext()) {
        	dipositDesti = (Diposit) itd.next();
        	// El dipòsit de destí no es buit
        	if (dipositDesti.getPartidaOli() != null  && dipositDesti.getVolumActual() != null && dipositDesti.getVolumActual() > 0.0){
        		if (dipositDesti.getPartidaOli().getCategoriaOli().getId() == Constants.CATEGORIA_NO_DO){
        			noDo = true;
        		} else if (dipositDesti.getPartidaOli().getCategoriaOli().getId() == Constants.CATEGORIA_PENDENT){
        			DO = false;
        		} else if (!dipositDesti.getPartidaOli().getId().equals(idPartida)){
        			// Si es mesclen diferents partides, encara que siguin totes de DO, el resultat serà Pendent
    				DO = false;
        		}
        	}
        }
        
      //PARTIDES D'OLI
        Collection partidesOli = null;
        if (establiment != null){
//        	partidesOli = oliInfraestructuraEjb.findPartidesOliVisiblesByEstablimentExceptDO(establiment.getId());
        	// També es poden seleccionar partides de DO...
        	oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
        	partidesOli = oliInfraestructuraEjb.findPartidesOliVisiblesByEstabliment(establiment.getId());
        }
        
        //Comprovem la categoria
        boolean esDO = false;
        if(dipositDesti != null){
        	oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
    		Diposit dipositOrigen = oliInfraestructuraEjb.dipositAmbId((Long)origenList.iterator().next());
    		
    		if(dipositOrigen.getPartidaOli().getCategoriaOli().getId().equals(Constants.CATEGORIA_DO)){
    			esDO = true;
    		}
    		
    		//Comprovem que TOTS els dipòsits origen són de la mateixa partida
    		for(Object o: origenList){
    			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
    			Diposit d = oliInfraestructuraEjb.dipositAmbId((Long)o);
    			if(!dipositOrigen.getPartidaOli().getId().equals(d.getPartidaOli().getId())){
    				esDO = false;
    			}
    		}
    		
    		if(dipositDesti.getVolumActual() != null && dipositDesti.getVolumActual() > 0){
    			if(!dipositDesti.getPartidaOli().getId().equals(dipositOrigen.getPartidaOli().getId())){
    				esDO = false;
    			}
    		}
        }
        
//        myModel.put("catDesti", (noDo? "noDO" : (DO? "DO" : "pendent")));
        if(esDO){
        	myModel.put("catDesti", "DO");
        	myModel.put("partidaDO", (dipositDesti.getPartidaOli() != null)?dipositDesti.getPartidaOli().getId():"");
        } else {
        	myModel.put("catDesti", (noDo? "noDO" : "pendent"));
        }
        myModel.put("partidesOli", partidesOli);
        myModel.put("establiment", establiment);
        myModel.put("origen", origen);
        myModel.put("dipositDesti", dipositDesti);
        String tipusProces = request.getParameter("tipusProces");
    	myModel.put("tipusProces", tipusProces);
        String pas = request.getParameter("pas");
    	myModel.put("pas", pas);
    	myModel.put("path", "trasbalsOli");
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
    	
    	ProcesTrasbalsCommand pTrasbals = new ProcesTrasbalsCommand();
    	
    	Establiment establiment = (Establiment) request.getSession().getAttribute(establimentSessionKey);
    	
    	Collection destiList = (Collection)request.getSession().getAttribute(seleccioSessionKeyDesti);
    	Collection origenList = (Collection)request.getSession().getAttribute(seleccioSessionKeyOrigen);
    	
    	DecimalFormat numberDecimalFormat = new DecimalFormat("###,###,##0.000");

    	try {
    		Long idElaboracio = null;
    		if (request.getParameter("id") != null) idElaboracio = new Long(request.getParameter("id"));
			
			if (!isFormSubmission(request)) {
				if (idElaboracio != null) {
//					Elaboracio elaboracio = oliProcessosEjb.findTrasbalsById(idElaboracio);
//					
//					if (elaboracio != null) {
//						pTrasbals.setAcidesa(elaboracio.getAcidesa());
//						pTrasbals.setData(elaboracio.getData());
//			    		pTrasbals.setId(elaboracio.getId());
//			    		pTrasbals.setIdVarietatOli(elaboracio.getVarietatOli().getId());
//			    		//pTrasbals.setLitres(elaboracio.getLitres());
//			    		pTrasbals.setResponsable(elaboracio.getResponsable());
//			    		pTrasbals.setObservacions(elaboracio.getObservacions());
//			    		pTrasbals.setTalcLot(elaboracio.getTalcLot());
//			    		pTrasbals.setTalcMarcaComercial(elaboracio.getTalcMarcaComercial());
//			    		pTrasbals.setTalcQuantitat(elaboracio.getTalcQuantitat());
//			    		pTrasbals.setTemperatura(elaboracio.getTemperatura());
//			    		
//			    		pTrasbals.setLitrosAfegits(oliProcessosEjb.findOliRetiratPropietarioByElaboracio(elaboracio.getId()));
//			    		pTrasbals.setKilosAfegits((Double) numberDecimalFormat.parse(numberDecimalFormat.format(oliProcessosEjb.findOliRetiratPropietarioByElaboracio(elaboracio.getId()))));
//			    		Iterator itEntradaOli = oliProcessosEjb.findEntradaDiposiOliByElaboracio(elaboracio.getId());
//			    		Double[] litros = new Double[oliProcessosEjb.findEntradaDiposiOliByElaboracioSize(elaboracio.getId())];
//			    		Double[] kilos = new Double[oliProcessosEjb.findEntradaDiposiOliByElaboracioSize(elaboracio.getId())];
//			    		int i = 0;
//			    		EntradaDiposit entrada = null;
//			    		while (itEntradaOli.hasNext()) {
//				    		entrada = (EntradaDiposit) itEntradaOli.next();
//			    			
//			    			litros[i] = entrada.getLitres();
//				    		kilos[i] = (Double) numberDecimalFormat.parse(numberDecimalFormat.format(entrada.getLitres().doubleValue() * 0.916));
//			    			i++;
//			    		}
//			    		pTrasbals.setLitros(litros);
//			    		pTrasbals.setKilos(kilos);
//					}
					
				} else {
					pTrasbals.setLitros(new Double[origenList.size()]);
					pTrasbals.setKilos(new Double[origenList.size()]);
					pTrasbals.setData(new Date());
				}
			} else {
				pTrasbals.setLitros(new Double[origenList.size()]);
				pTrasbals.setKilos(new Double[origenList.size()]);
				//pTrasbals.setData(new Date());
			}
			
			Diposit[] dipositsOrigen = new Diposit[origenList.size()];
			
			for (int i = 0; i < dipositsOrigen.length; i++) {
				dipositsOrigen[i] = new Diposit();
			}
			
			pTrasbals.setEstabliment(establiment);
			pTrasbals.setDipositsOrigen(dipositsOrigen);
			pTrasbals.setDipositDesti(new Diposit());
		} catch (Exception e) {
			logger.error("EXCEPTION", e);
		}
    	    	
        return pTrasbals;
        
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
     * Injecció de la dependència seleccioSessionKeyDesti
     *
     * @param seleccioSessionKey La classe a injectar.
     */
    public void setSeleccioSessionKeyDesti(String seleccioSessionKeyDesti) {
        this.seleccioSessionKeyDesti = seleccioSessionKeyDesti;
    }
    
    
    
    public void setDesqualificat(String desqualificat) {
		this.desqualificat = desqualificat;
	}

	public void setPendentQualificar(String pendentQualificar) {
		this.pendentQualificar = pendentQualificar;
	}

	public void setMescla(String mescla) {
		this.mescla = mescla;
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