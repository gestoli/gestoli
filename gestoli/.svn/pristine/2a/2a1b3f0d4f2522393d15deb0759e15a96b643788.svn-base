/**
 * TancamentLlibresLotFormController.java
 */
package es.caib.gestoli.front.spring; 

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
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
import es.caib.gestoli.logic.model.Establiment;
import es.caib.gestoli.logic.model.Etiquetatge;
import es.caib.gestoli.logic.model.Lot;
import es.caib.gestoli.logic.model.Marca;
import es.caib.gestoli.logic.model.PartidaOli;
import es.caib.gestoli.logic.model.VarietatOliva;
import es.caib.gestoli.logic.model.Zona;



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
public class TancamentLlibresLotFormController extends SimpleFormController {

    private static Logger logger = Logger.getLogger(TancamentLlibresLotFormController.class);
    private OliInfraestructuraEjb oliInfraestructuraEjb;
    private OliProcessosEjb oliProcessosEjb;
    private String qualificat;
    private String establimentSessionKey;
    private String tancamentLlibres;
    
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
    	
    	ProcesLotCommand pLot = (ProcesLotCommand)command;
//    	Establiment establiment = (Establiment) request.getSession().getAttribute(establimentSessionKey);
    	
    	Integer[] varietats = pLot.getVarietatsArray();
    	
    	Lot lot = null;
		
			try{
				if (isCreate(request)) {
					lot = new Lot();
				} else {
					oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
					lot = oliInfraestructuraEjb.lotAmbId(pLot.getId());
				}
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				Etiquetatge etiquetatge = oliInfraestructuraEjb.etiquetatgeAmbId(pLot.getIdEtiquetatge());
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				Zona zona = oliInfraestructuraEjb.zonaAmbId(pLot.getIdZona());
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
//				CategoriaOli catOli = oliInfraestructuraEjb.findCategoriaOliById(pLot.getIdCategoriaOli());
//				lot.setCategoriaOli(catOli);
				PartidaOli partidaOli = oliInfraestructuraEjb.getPartidaOliById(pLot.getIdPartidaOli());
				lot.setPartidaOli(partidaOli);
				lot.setCodiAssignat(pLot.getCodiAssignat());
				lot.setData(pLot.getData());
//				lot.setDiposit(null);
				lot.setEtiquetatge(etiquetatge);
				lot.setNumeroLot(pLot.getNumeroLot());
				
				if (isCreate(request)) {
					lot.setAcidesa(new Double(0));
					lot.setLitresEnvassats(new Double(0));
					lot.setNumeroBotellesInicials(new Integer(0));
					lot.setNumeroBotellesActuals(new Integer(0));
				} else {
					lot.setId(pLot.getId());
					lot.setAcidesa(pLot.getAcidesa());
					lot.setLitresEnvassats(pLot.getLitresEnvassats());
					lot.setNumeroBotellesInicials(pLot.getNumeroBotellesInicials());
					lot.setNumeroBotellesActuals(pLot.getNumeroBotellesInicials());
				}
				
				if (varietats != null) {
					for (int i = 0; i < varietats.length; i++) {
						oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
						VarietatOliva vOliva = oliInfraestructuraEjb.variedadAmbId(varietats[i]);
						lot.getVarietatOlivas().add(vOliva);
					}
				}
				if(pLot.getObservacions()!= null){
					lot.setObservacions(tancamentLlibres + ". " + pLot.getObservacions());
				}else{
					lot.setObservacions(tancamentLlibres);
				}
				
				lot.setZona(zona);
				lot.setCreatTancament(new Boolean(true));
				
				oliProcessosEjb.setHibernateTemplate(getHibernateTemplate());
				if (isCreate(request)) {
					oliProcessosEjb.crearLotCierreLibros(lot, false, tancamentLlibres);
					ControllerUtils.saveMessageInfo(request, missatge("lot.missatge.ok"));
				} else {
					oliProcessosEjb.crearLotCierreLibros(lot, true, tancamentLlibres);
					ControllerUtils.saveMessageInfo(request, missatge("tancamentLlibres.lot.missatge.ok"));
				}
				
	    	} catch (Exception e) {
	    		 logger.error("Error processant el Lot", e);
	    		 if (isCreate(request)) {
	    			 ControllerUtils.saveMessageError(request, missatge("lot.missatge.no"));
	    		 } else {
	    			 ControllerUtils.saveMessageError(request, missatge("tancamentLlibres.lot.missatge.no"));
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
        
    	ProcesLotCommand pLot = (ProcesLotCommand) command;
    	
        Map myModel = new HashMap();
        
        Establiment establiment = (Establiment) request.getSession().getAttribute(establimentSessionKey);
        
        // MARCAS
        List<BasicData> marcas = new ArrayList<BasicData>();
        oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
        Collection<Marca> itMarcas = oliInfraestructuraEjb.getMarcasEstabliment(establiment.getId());
        ArrayList<Marca> marcasEstablecimiento = new ArrayList<Marca>();
        for (Marca marca : itMarcas) {
        	if (marca.getActiu().booleanValue()) {
        		if (!marcasEstablecimiento.contains(marca)) marcasEstablecimiento.add(marca);
        	}
        }
        
        Collections.sort(
        		marcasEstablecimiento,
				new Comparator() {
					public int compare(Object o1, Object o2) {
						if (o1 == null && o2 == null) return 0;
						if (o1 == null || ((Marca)o1).getNom() == null) return -1;
						if (o2 == null || ((Marca)o2).getNom() == null) return 1;
						String t1 = ((Marca)o1).getNom();
						String t2 = ((Marca)o2).getNom();
						return t1.compareTo(t2);
					}
				});
        
        for (Marca marca : marcasEstablecimiento) {
        	BasicData basic = new BasicData();
        	basic.setId(String.valueOf(marca.getId()));
        	basic.setNom(marca.getNom());
        	marcas.add(basic);
        }
        
        myModel.put("marcas", marcas);
        
        // ETIQUETAJES
        List etiquetatges = new ArrayList();
        if (pLot.getIdMarca() != null) {
        	oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
        	Iterator itEtiquetajes = oliInfraestructuraEjb.etiquetatgeByMarca(pLot.getIdMarca()).iterator();
        	while (itEtiquetajes.hasNext()) {
        		Etiquetatge etiquetaje = (Etiquetatge) itEtiquetajes.next();
        		BasicData basic = new BasicData();
        		basic.setId(String.valueOf(etiquetaje.getId()));
        		basic.setNom(etiquetaje.getTipusEnvas().getVolum() + " - " + etiquetaje.getTipusEnvas().getColor().getNom() + " - " + etiquetaje.getTipusEnvas().getMaterialTipusEnvas().getNom());
        		etiquetatges.add(basic);
        	}
        }
        
        Collections.sort(
        		etiquetatges,
				new Comparator() {
					public int compare(Object o1, Object o2) {
						if (o1 == null && o2 == null) return 0;
						if (o1 == null || ((BasicData)o1).getNom() == null) return -1;
						if (o2 == null || ((BasicData)o2).getNom() == null) return 1;
						String t1 = ((BasicData)o1).getNom();
						String t2 = ((BasicData)o2).getNom();
						return t1.compareTo(t2);
					}
				});
        
        myModel.put("etiquetatges", etiquetatges);
        
        // ZONAS
        List zonas = new ArrayList();
        oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
        Iterator itZonas = oliInfraestructuraEjb.zonaActiusAmbEstabliment(establiment.getId()).iterator();
        while (itZonas.hasNext()) {
        	Zona zona = (Zona) itZonas.next();
        	BasicData basic = new BasicData();
        	basic.setId(String.valueOf(zona.getId()));
        	basic.setNom(zona.getNom());
        	zonas.add(basic);
        }
        
        myModel.put("zonas", zonas);
        
        // VARIEDADES DE OLIVA
        oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
        Collection<VarietatOliva> varietats = oliInfraestructuraEjb.varietatsOliva();
        Collection vars = new ArrayList();
        for (VarietatOliva var : varietats){
        	if (var.getExperimental() != null && var.getExperimental().booleanValue() && var.getNomVarietat() != null){
        		vars.add(new BasicData(var.getId().toString(), var.getNomVarietat()));
        	} else {
        		vars.add(new BasicData(var.getId().toString(), var.getNom()));
        	}
        }
        myModel.put("varietatsArray", vars);
        
//        // CATEGORIA ACEITE
//        List catOli = new ArrayList();
//        oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
//        Iterator itCatOli = oliInfraestructuraEjb.categoriaOliCercaTots().iterator();
//        while (itCatOli.hasNext()) {
//        	CategoriaOli categoriaOli = (CategoriaOli) itCatOli.next();
//        	BasicData basic = new BasicData();
//        	basic.setId(String.valueOf(categoriaOli.getId()));
//        	basic.setNom(missatge("consulta.general.camp.categoriaOli." + categoriaOli.getId()));
//        	catOli.add(basic);
//        }
//        
//      myModel.put("categoriasOli", catOli);
        
      //PARTIDES D'OLI
        oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
        Collection partidesOli = null;
        if (establiment != null){
        	partidesOli = oliInfraestructuraEjb.findPartidesOliVisiblesByEstabliment(establiment.getId());
        }
        myModel.put("partidesOli", partidesOli);
        
        myModel.put("establiment", establiment);
        myModel.put("qualificat", qualificat);
        
        if ((!isFormSubmission(request)) && (!isCreate(request))) {
        	myModel.put("path", "crear_tancament_llibres_lot");
		} else {
			myModel.put("path", "crear_lot");
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
    	
    	ProcesLotCommand pLot = null;
    	
    	Establiment establiment = (Establiment) request.getSession().getAttribute(establimentSessionKey);
    	
    	if ((!isFormSubmission(request)) && (!isCreate(request))) {
			try {
				Long codi = new Long(Long.parseLong(request.getParameter("id")));
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				Lot l = oliInfraestructuraEjb.lotAmbId(codi);
				pLot = new ProcesLotCommand(l);

			} catch (RemoteException ex) {
				throw new ServletException("Error cridant l'EJB", ex);
			}
		} else {
			pLot = new ProcesLotCommand();
			pLot.setData(new Date());
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

	public void setQualificat(String qualificat) {
		this.qualificat = qualificat;
	}

	/**
	 * @param establimentSessionKey the establimentSessionKey to set
	 */
	public void setEstablimentSessionKey(String establimentSessionKey) {
		this.establimentSessionKey = establimentSessionKey;
	}

	public void setTancamentLlibres(String tancamentLlibres) {
		this.tancamentLlibres = tancamentLlibres;
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
		return (request.getParameter("id") == null );
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