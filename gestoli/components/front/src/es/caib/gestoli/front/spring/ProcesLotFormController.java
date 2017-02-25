/**
 * ProcesLotFormController.java
 */
package es.caib.gestoli.front.spring; 

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
import es.caib.gestoli.logic.model.Establiment;
import es.caib.gestoli.logic.model.Etiquetatge;
import es.caib.gestoli.logic.model.Lot;
import es.caib.gestoli.logic.model.Marca;
import es.caib.gestoli.logic.model.PartidaOli;
import es.caib.gestoli.logic.model.Producte;
import es.caib.gestoli.logic.model.Zona;
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
public class ProcesLotFormController extends SimpleFormController {

    private static Logger logger = Logger.getLogger(ProcesLotFormController.class);
    private OliInfraestructuraEjb oliInfraestructuraEjb;
    private OliProcessosEjb oliProcessosEjb;
    private String establimentSessionKey;
    private String seleccioSessionKeyOrigen;
    private String qualificat;
    private String perdua;
    private String embotellat;
    
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
    	
    	Map myModel = new HashMap();
    	
    	ProcesLotCommand pLot = (ProcesLotCommand)command;
    	Establiment establiment = (Establiment) request.getSession().getAttribute(establimentSessionKey);
    	
    	Lot lot = null;
		if (isCreate(request)) {
			try{
				lot = new Lot();
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				Diposit diposit = oliInfraestructuraEjb.dipositAmbId(pLot.getDiposit().getId());
				Etiquetatge etiquetatge = oliInfraestructuraEjb.etiquetatgeAmbId(pLot.getIdEtiquetatge());
				Zona zona = oliInfraestructuraEjb.zonaAmbId(pLot.getIdZona());
				Producte producte = oliInfraestructuraEjb.producteAmbId(pLot.getIdProducte());
				PartidaOli partida = diposit.getPartidaOli();
				
				// Si la partida és de DO, i la marca és de No DO, llavors hem d'assignar el lot 
				// a una altra partida  No DO
				if (etiquetatge != null && etiquetatge.getMarca() != null){
					if (!etiquetatge.getMarca().getDenominacioOrigen()){
						if (diposit.getPartidaOli().getCategoriaOli().getId().equals(Constants.CATEGORIA_DO)){
							partida = oliInfraestructuraEjb.getPartidaNoDOByNomAndEstabliment(partida.getNom(), establiment);
						}
					}
				}
				
				lot.setAcidesa(diposit.getAcidesa());
//				lot.setCategoriaOli(diposit.getCategoriaOli());
				lot.setPartidaOli(partida);
				lot.setCodiAssignat(pLot.getCodiAssignat());
				lot.setData(pLot.getData());
				lot.setDiposit(diposit);
				lot.setEtiquetatge(etiquetatge);
				lot.setLitresEnvassats(pLot.getLitresEnvassats());
				if (pLot.getPerdua() != null && pLot.getPerdua().booleanValue()) { 
					lot.setLitresPerduts(pLot.getLitresPerduts());
					lot.setMotiuPerdua(pLot.getMotiuPerdua());
				}
				lot.setNumeroBotellesInicials(pLot.getNumeroBotellesInicials());
				lot.setNumeroBotellesActuals(pLot.getNumeroBotellesInicials());
				lot.setObservacions(pLot.getObservacions());
//				lot.setEntradaLots(entradaLots);
//				lot.setSortidaDiposits(pLot.getSortidaDiposits());
//				lot.setSortidaLots(pLot.getSortidaLots());
				lot.setZona(zona);
				lot.setCreatTancament(new Boolean(false));
				lot.setNumeroLot(pLot.getNumeroLot());
				lot.setDataConsum(pLot.getDataConsum());
				lot.setProducte(producte);
				lot.setLotTap((pLot.getLotTap()!=null)?pLot.getLotTap():"");
				
				oliProcessosEjb.setHibernateTemplate(getHibernateTemplate());
				oliProcessosEjb.crearLot(lot, establiment, pLot.getPerdua(), pLot.getEtiquetar(), perdua, embotellat);
				request.getSession().setAttribute("idLot", lot.getId());
				ControllerUtils.saveMessageInfo(request, missatge("lot.missatge.ok"));
				
	    	} catch (Exception e) {
	    		 logger.error("Error processant el Lot de l'oli", e);
	             ControllerUtils.saveMessageError(request, missatge("lot.missatge.no"));
			}
		}
        
		//String forward = getSuccessView()+ "?tipusProces=2&pas=2&id=" + idElaboracio;
		String desti = "EstablimentPrincipal.html";
		if (pLot.getFerEtiquetat() != null && pLot.getFerEtiquetat()) {
			desti = "ProcesEtiquetarForm.html" +
					"?pas=1" +
					"&zonaId=" + pLot.getIdZona() +
					"&establimentId=" + establiment.getId() +
					"&pas=1" +
					"&tipusProces=8" +
					"&idLot=" + request.getSession().getAttribute("idLot") +
					"&numBot =" + pLot.getNumeroBotellesInicials() +
					"&capacitat="+lot.getLitresEnvassats()/lot.getNumeroBotellesActuals(); 
		}
		
		return new ModelAndView("redirect:" + desti, myModel);
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
    	oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
    	ProcesLotCommand pLot = (ProcesLotCommand) command;
    	
        Map myModel = new HashMap();
        
        Establiment establiment = (Establiment) request.getSession().getAttribute(establimentSessionKey);
//        String qualificat = (String) request.getSession().getAttribute(qualificat);
        
        Collection origenList = (Collection)request.getSession().getAttribute(seleccioSessionKeyOrigen);
        Collection origen = null;
        
        
        Diposit deposito = new Diposit();
        if (origenList.size() != 0) {
        	origen = oliInfraestructuraEjb.dipositsInfo(origenList.toArray());
        	for(Iterator it= origen.iterator();it.hasNext();){
        		deposito = (Diposit)it.next();
        	}
        	
        }
        
        // MARCAS
        List marcas = new ArrayList();
        Iterator itMarcas = oliInfraestructuraEjb.getMarcasEstabliment(establiment.getId()).iterator();
        ArrayList marcasEstablecimiento = new ArrayList();
        while (itMarcas.hasNext()) {
        	Marca marca = (Marca) itMarcas.next();
        	if (marca.getActiu().booleanValue()) {
        		if(!deposito.getPartidaOli().getEsDO()){
        			if(!marca.getDenominacioOrigen()){
        				if (!marcasEstablecimiento.contains(marca)) marcasEstablecimiento.add(marca);
        			}
        		}else{
        			if (!marcasEstablecimiento.contains(marca)) marcasEstablecimiento.add(marca);
            	}
        		
        	}
        }
        
        for (int i = 0; i < marcasEstablecimiento.size(); i++) {
        	Marca marca = (Marca) marcasEstablecimiento.get(i);
        	BasicData basic = new BasicData();
        	basic.setId(String.valueOf(marca.getId()));
        	basic.setNom(marca.getNom());
        	marcas.add(basic);
        }
        
        myModel.put("marcas", marcas);
        
        // ETIQUETAJES
        List etiquetatges = new ArrayList();
        if (pLot.getIdMarca() != null) {
        	Iterator itEtiquetajes = oliInfraestructuraEjb.etiquetatgeByMarca(pLot.getIdMarca()).iterator();
        	while (itEtiquetajes.hasNext()) {
        		Etiquetatge etiquetaje = (Etiquetatge) itEtiquetajes.next();
        		BasicData basic = new BasicData();
        		basic.setId(String.valueOf(etiquetaje.getId()));
        		basic.setNom(etiquetaje.getTipusEnvas().getVolum() + " - " + etiquetaje.getTipusEnvas().getColor().getNom() + " - " + etiquetaje.getTipusEnvas().getMaterialTipusEnvas().getNom());
        		etiquetatges.add(basic);
        	}
        }
        
        myModel.put("etiquetatges", etiquetatges);
        
        // ZONAS
        List zonas = new ArrayList();
        Iterator itZonas = oliInfraestructuraEjb.zonaActiusAmbEstabliment(establiment.getId()).iterator();
        while (itZonas.hasNext()) {
        	Zona zona = (Zona) itZonas.next();
        	BasicData basic = new BasicData();
        	basic.setId(String.valueOf(zona.getId()));
        	basic.setNom(zona.getNom());
        	zonas.add(basic);
        }
        
        myModel.put("zonas", zonas);
        
        // PRODUCTES
        List productes = new ArrayList();
        Iterator itProductes = oliInfraestructuraEjb.producteActiusAmbEstabliment(establiment.getId()).iterator();
        while (itProductes.hasNext()) {
        	Producte producte = (Producte) itProductes.next();
        	BasicData basic = new BasicData();
        	basic.setId(String.valueOf(producte.getId()));
        	basic.setNom(producte.getNom());
        	productes.add(basic);
        }
        
        myModel.put("productes", productes);
        
        myModel.put("qualificat", qualificat);
        
        myModel.put("establiment", establiment);
        myModel.put("deposito", deposito);       
        String tipusProces = request.getParameter("tipusProces");
    	myModel.put("tipusProces", tipusProces);
        String pas = request.getParameter("pas");
    	myModel.put("pas", pas);
    	myModel.put("path", "embotellar");
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
    	
    	ProcesLotCommand pLot = new ProcesLotCommand();
    	
    	Establiment establiment = (Establiment) request.getSession().getAttribute(establimentSessionKey);
    	
    	try {
    					
			if (!isFormSubmission(request)) {
				pLot.setData(new Date());
			
			} 
					
			Diposit diposit = new Diposit();
			pLot.setDiposit(diposit);
			
			
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
    	binder.registerCustomEditor(
        		Boolean.class,
        		new CustomBooleanEditor("S", "N", true));
        binder.registerCustomEditor(
        		Date.class,
        		new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), true));
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
	 * @param qualificat the qualificat to set
	 */
	public void setQualificat(String qualificat) {
		this.qualificat = qualificat;
	}

	public void setPerdua(String perdua) {
		this.perdua = perdua;
	}

	public void setEmbotellat(String embotellat) {
		this.embotellat = embotellat;
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