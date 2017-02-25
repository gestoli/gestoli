/**
 * ProcesEntradaOlivaFormController.java
 */
package es.caib.gestoli.front.spring; 

import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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

import es.caib.gestoli.front.util.BasicData;
import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;
import es.caib.gestoli.logic.interfaces.OliProcessosEjb;
import es.caib.gestoli.logic.model.Diposit;
import es.caib.gestoli.logic.model.Elaboracio;
import es.caib.gestoli.logic.model.EntradaDiposit;
import es.caib.gestoli.logic.model.Establiment;
import es.caib.gestoli.logic.model.Olivicultor;
import es.caib.gestoli.logic.model.PartidaOliva;
import es.caib.gestoli.logic.util.ElaboracioAux;



/**
 * <p>Controlador per a l'accio d'executar un proces d'entrada
 * de oliva.</p>
 * <p>Aquest controlador distingueix entre les peticions del
 * tipus GET y les de tipus POST. Si la petici�n es de tipus POST
 * s'executa l'acci� de creaci� o d'edici�. Si la petici� es de
 * tipo GET nom�s mostra la p�gina.
 *
 * @author cperez <cperez@at4.net>
 */
public class ProcesElaboracioOliFormController extends SimpleFormController {

    private static Logger logger = Logger.getLogger(ProcesElaboracioOliFormController.class);
    private OliProcessosEjb oliProcessosEjb;
    private OliInfraestructuraEjb oliInfraestructuraEjb;
    private String establimentSessionKey;
    private String seleccioSessionKeyOrigen;
    private String seleccioSessionKeyDesti;
    private String desqualificat;
    private String pendentQualificar;
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
    	
    	ProcesElaboracioOliCommand peo = (ProcesElaboracioOliCommand)command;
    	Establiment establiment = (Establiment) request.getSession().getAttribute(establimentSessionKey);
    	
    	Map myModel = new HashMap();
    	
    	Long idElaboracio = null;
    	
		if (isCreate(request)) {
			try{ 
				ElaboracioAux commandAux = new ElaboracioAux();
				commandAux.setId(peo.getId());
				commandAux.setCategoriaOli(peo.getCategoriaOli());
				commandAux.setTraza(peo.getTraza());
				commandAux.setData(peo.getData());
				commandAux.setNumeroElaboracio(peo.getNumeroElaboracio());
				commandAux.setResponsable(peo.getResponsable());
				commandAux.setAcidesa(peo.getAcidesa());
				commandAux.setTemperatura(peo.getTemperatura());
				commandAux.setTalcMarcaComercial(peo.getTalcMarcaComercial());
				commandAux.setTalcLot(peo.getTalcLot());
				commandAux.setTalcQuantitat(peo.getTalcQuantitat());
				commandAux.setObservacions(peo.getObservacions());
				commandAux.setPartidaOlivas(peo.getPartidaOlivas());
				commandAux.setIdCategoriaOli(peo.getIdCategoriaOli());
				commandAux.setPartides(peo.getPartides());
				commandAux.setDiposits(peo.getDiposits());
				commandAux.setLitros(peo.getLitros());
				commandAux.setKilos(peo.getKilos());
				commandAux.setLitrosRetirats(peo.getLitrosRetirats());
				commandAux.setIdOlivicultors(peo.getIdOlivicultors());
				commandAux.setIdPartidaOli(peo.getIdPartidaOli());
				
				//Litres totals = litres elaboració + litres retirats pels olivicultors
				double litros = 0;
				
				//Litres elaboració
				for (int i = 0; i < peo.getLitros().length; i++) {
					litros += peo.getLitros()[i].doubleValue();
				}
				
				//Sumem els litres retirats pels olivicultors
				if (peo.getLitrosRetirats() != null){ 
					for(Double l: peo.getLitrosRetirats()){
						if(l != null){
							litros += l.doubleValue(); 
						}
					}
				}
				commandAux.setLitres(new Double(litros));
				
				oliProcessosEjb.setHibernateTemplate(getHibernateTemplate());
				oliProcessosEjb.setMessageSourceAccessor(getMessageSourceAccessor());
				idElaboracio = oliProcessosEjb.generarElaboracioOli(commandAux, establiment.getId(), desqualificat, pendentQualificar);
				

				ControllerUtils.saveMessageInfo(request, missatge("proces.elaboracioOli.missatge.crear.ok"));
				ControllerUtils.showDocumentRendiment(request, idElaboracio, establiment.getId());
	    	} catch (Exception e) {
	    		 logger.error("Error processant l'elaboracio de l'oli", e);
	             ControllerUtils.saveMessageError(request, missatge("proces.elaboracioOli.missatge.no"));
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
    @SuppressWarnings("unchecked")
	protected Map referenceData(
            HttpServletRequest request,
            Object command,
            Errors errors) throws Exception {
        
        Map myModel = new HashMap();
        ProcesElaboracioOliCommand peo = (ProcesElaboracioOliCommand)command;
        
        if (peo.getTalcQuantitat() == null){
        	peo.setTalcQuantitat(0.0);
        	peo.setTalcLot(" ");
        	peo.setTalcMarcaComercial(" ");
        }
        
        Establiment establiment = (Establiment) request.getSession().getAttribute(establimentSessionKey);
        
        Collection origenList = (Collection)request.getSession().getAttribute(seleccioSessionKeyOrigen);
        Collection destiList = (Collection)request.getSession().getAttribute(seleccioSessionKeyDesti);
        
        Collection origen = null;
        Collection desti = null;
        
        Boolean esEco = true;
        if (origenList.size() != 0) {
        	oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
        	origen = oliInfraestructuraEjb.partidasInfo(origenList.toArray());
        	
        	Date data = null;
        	Iterator itOri = origen.iterator();
        	Long olivicultor = null;
        	
        	while (itOri.hasNext()) {
        		PartidaOliva partida = (PartidaOliva) itOri.next();
        		if(!partida.getEsEcologic()){
        			esEco =  false;
        		}
        		olivicultor = partida.getOlivicultor().getId();
        		if (data == null){ 
        			data = partida.getData();
        		} else if (partida.getData().after(data)){
        			data = partida.getData();  
        		}
        	}
        	if (peo.getData() == null) peo.setData(data);
        }
        
        
        oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
        if (destiList.size() != 0) desti = oliInfraestructuraEjb.dipositsInfo(destiList.toArray());
        
        // CATEGORIES D'OLI
        List categories = new ArrayList();
        categories.add(new BasicData("1", missatge("consulta.general.camp.varietat.no.do")));
        categories.add(new BasicData("2", missatge("consulta.general.camp.varietat.pendent")));
        
        
        //PARTIDES D'OLI
        Collection partidesOli = null;
        if (establiment != null){
        	oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
        	partidesOli = oliInfraestructuraEjb.findPartidesOliVisiblesByEstablimentExceptDO(establiment.getId());
        }
        
    	
        myModel.put("categories", categories);
        myModel.put("partidesOli", partidesOli);
        myModel.put("establiment", establiment);
        myModel.put("origen", origen);
        myModel.put("desti", desti);
        String tipusProces = request.getParameter("tipusProces");
    	myModel.put("tipusProces", tipusProces);
        String pas = request.getParameter("pas");
    	myModel.put("pas", pas);
    	myModel.put("path", "elaboracion");
    	myModel.put("esEco", esEco);
        return myModel;
    }

    
    /**
     * En el cas de que sigui una ediciÃ³ retorna l'objecte omplit amb
     * les dades actuals del registre. En caso de que sigui una creaciÃ³
     * retorna l'objecte buit.
     * 
     * @see AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
     */
    protected Object formBackingObject(HttpServletRequest request) throws ServletException {
    	
    	ProcesElaboracioOliCommand peo = new ProcesElaboracioOliCommand();
    	
    	Establiment establiment = (Establiment) request.getSession().getAttribute(establimentSessionKey);
    	
    	Collection destiList = (Collection)request.getSession().getAttribute(seleccioSessionKeyDesti);
    	Collection origenList = (Collection)request.getSession().getAttribute(seleccioSessionKeyOrigen);
    	
    	DecimalFormat numberDecimalFormat = new DecimalFormat("###,###,##0.000");
    	
    	Object[] idPartidesOliva = origenList.toArray();
    	List<Olivicultor> olivicultors = new ArrayList<Olivicultor>();
    	
    	for(int i=0; i<idPartidesOliva.length; i++){
    		try {
    			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
    			PartidaOliva p = oliInfraestructuraEjb.partidaAmbId((Long)idPartidesOliva[i]);
				Olivicultor o  = oliInfraestructuraEjb.olivicultorAmbId(p.getOlivicultor().getId());
				if(!olivicultors.contains(o)){
					olivicultors.add(o);
				}
			} catch (RemoteException e) {
				e.printStackTrace();
			}
    	}
    	

    	try {
    		Long idElaboracio = null;
    		if (request.getParameter("id") != null) idElaboracio = new Long(request.getParameter("id"));
			
			if (!isFormSubmission(request)) {
				if (idElaboracio != null) {
					oliProcessosEjb.setHibernateTemplate(getHibernateTemplate());
					Elaboracio elaboracio = oliProcessosEjb.findElaboracioOliById(idElaboracio);
					
					if (elaboracio != null) {
						peo.setAcidesa(elaboracio.getAcidesa());
						peo.setData(elaboracio.getData());
			    		peo.setId(elaboracio.getId());
			    		peo.setIdCategoriaOli(elaboracio.getCategoriaOli().getId());
			    		//peo.setLitres(elaboracio.getLitres());
			    		peo.setResponsable(elaboracio.getResponsable());
			    		peo.setObservacions(elaboracio.getObservacions());
			    		peo.setTalcLot(elaboracio.getTalcLot());
			    		peo.setTalcMarcaComercial(elaboracio.getTalcMarcaComercial());
			    		peo.setTalcQuantitat(elaboracio.getTalcQuantitat());
			    		peo.setTemperatura(elaboracio.getTemperatura());
			    		peo.setPartidaOli(elaboracio.getPartidaOli());
			    		if (elaboracio.getPartidaOli() != null){
			    			peo.setNomCategoriaOli(elaboracio.getPartidaOli().getCategoriaOli().getNom());
			    		}
			    		
			    		oliProcessosEjb.setHibernateTemplate(getHibernateTemplate());
			    		Collection entradesDiposit = oliProcessosEjb.findOliRetiratPropietarioByElaboracio(elaboracio.getId());
			    		
			    		Double[] litresRetirats = new Double[entradesDiposit.size()];
			    		Double[] kilosRetirats = new Double[entradesDiposit.size()];
			    		Long[] idOlivicultors = new Long[entradesDiposit.size()];
			    		int index = 0;
			    		for(Object o: entradesDiposit){
			    			EntradaDiposit ed = (EntradaDiposit)o;
			    			litresRetirats[index] = ed.getLitres();
			    			kilosRetirats[index] = ed.getLitres() * 0.916;
			    			idOlivicultors[index] = ed.getOlivicultor().getId();
			    			index++;
			    		}
			    		peo.setLitrosRetirats(litresRetirats);
			    		peo.setKilosRetirats(kilosRetirats);
			    		peo.setIdOlivicultors(idOlivicultors);
			    		oliProcessosEjb.setHibernateTemplate(getHibernateTemplate());
			    		Iterator itEntradaOli = oliProcessosEjb.findEntradaDiposiOliByElaboracio(elaboracio.getId(), new Boolean(true));
			    		oliProcessosEjb.setHibernateTemplate(getHibernateTemplate());
			    		Double[] litros = new Double[oliProcessosEjb.findEntradaDiposiOliByElaboracioSize(elaboracio.getId())];
			    		oliProcessosEjb.setHibernateTemplate(getHibernateTemplate());
			    		Double[] kilos = new Double[oliProcessosEjb.findEntradaDiposiOliByElaboracioSize(elaboracio.getId())];
			    		int i = 0;
			    		EntradaDiposit entrada = null;
			    		while (itEntradaOli.hasNext()) {
				    		entrada = (EntradaDiposit) itEntradaOli.next();
			    			
			    			litros[i] = entrada.getLitres();
				    		kilos[i] = (Double) numberDecimalFormat.parse(numberDecimalFormat.format(entrada.getLitres().doubleValue() * 0.916));
			    			i++;
			    		}
			    		peo.setLitros(litros);
			    		peo.setKilos(kilos);
					}
					
				} else {
					peo.setLitros(new Double[destiList.size()]);
			    	peo.setKilos(new Double[destiList.size()]);
//			    	peo.setData(new Date());
			    	peo.setTemperatura(establiment.getTemperaturaMaximaPasta().doubleValue());
			    	
			    	Double[] litresRetirats = new Double[olivicultors.size()];
			    	Double[] kilosRetirats = new Double[olivicultors.size()];
			    	Long[] idOlivicultors = new Long[olivicultors.size()];
			    	String[] nomOlivicultors = new String[olivicultors.size()];
			    	
			    	if (olivicultors != null && olivicultors.size() > 0){
				    	int index = 0;
				    	String obs = "Olivicultors: ";
				    	for(Olivicultor o: olivicultors){
				    		litresRetirats[index] = 0.0;
				    		kilosRetirats[index] = 0.0;
				    		idOlivicultors[index] = o.getId();
				    		nomOlivicultors[index] = o.getNom();
				    		obs += o.getNom() + ", ";
				    		index++;
				    	}
				    	peo.setObservacions(obs.substring(0, obs.length()-2));
			    	}
			    	peo.setLitrosRetirats(litresRetirats);
			    	peo.setKilosRetirats(kilosRetirats);
			    	peo.setIdOlivicultors(idOlivicultors);
			    	peo.setNomOlivicultors(nomOlivicultors);
				}
			} else {
				peo.setLitros(new Double[destiList.size()]);
				peo.setKilos(new Double[destiList.size()]);
				//peo.setData(new Date());
				
		    	Double[] litresRetirats = new Double[olivicultors.size()];
		    	Double[] kilosRetirats = new Double[olivicultors.size()];
		    	Long[] idOlivicultors = new Long[olivicultors.size()];
		    	String[] nomOlivicultors = new String[olivicultors.size()];
		    	
		    	if (olivicultors != null && olivicultors.size() > 0){
			    	int index = 0;
			    	for(Olivicultor o: olivicultors){
			    		litresRetirats[index] = 0.0;
			    		kilosRetirats[index] = 0.0;
			    		idOlivicultors[index] = o.getId();
			    		nomOlivicultors[index] = o.getNom();
			    		index++;
			    	}
		    	}
		    	peo.setLitrosRetirats(litresRetirats);
		    	peo.setKilosRetirats(kilosRetirats);
		    	peo.setIdOlivicultors(idOlivicultors);
		    	peo.setNomOlivicultors(nomOlivicultors);
			}
			
				
			PartidaOliva[] partides = new PartidaOliva[origenList.size()];
			for (int i = 0; i < partides.length; i++) {
				partides[i] = new PartidaOliva();
			}
			
			Diposit[] diposits = new Diposit[destiList.size()];
			
			for (int i = 0; i < diposits.length; i++) {
				diposits[i] = new Diposit();
			}
			
			peo.setEstabliment(establiment);
			peo.setPartides(partides);
			peo.setDiposits(diposits);
			
		} catch (Exception e) {
			logger.error("EXCEPTION", e);
		}
    	    	
        return peo;
        
    }

    /**
     * ConfiguraciÃ³ del <i>binder</i>. Si hi ha camps en el
     * <i>command</i> amb tipus que no siguin <i>String</i>
     * s'ha de configurar el seu corresponent binder aquÃ­.
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
     * InjecciÃ³ de la dependÃ¨ncia oliProcessb
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
     * InjecciÃ³ de la dependÃ¨ncia seleccioSessionKeyOrigen
     *
     * @param seleccioSessionKey La classe a injectar.
     */
    public void setSeleccioSessionKeyOrigen(String seleccioSessionKeyOrigen) {
        this.seleccioSessionKeyOrigen = seleccioSessionKeyOrigen;
    }
    
    /**
     * InjecciÃ³ de la dependÃ¨ncia seleccioSessionKeyDesti
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

	private String missatge(String clave){
    	String valor= getMessageSourceAccessor().getMessage(clave);
    	return valor;
    }
    
    /**
	 * Indica si la peticiÃ³n es de creaciÃ³n o no.
	 * @param request
	 * @return true si es una peticiÃ³n de creaciÃ³n o false si es de ediciÃ³n.
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