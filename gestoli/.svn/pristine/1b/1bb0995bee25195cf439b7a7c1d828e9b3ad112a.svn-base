/**
 * ProcesSortidaOlivaTaulaFormController.java
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
import es.caib.gestoli.logic.model.Bota;
import es.caib.gestoli.logic.model.Diposit;
import es.caib.gestoli.logic.model.Establiment;
import es.caib.gestoli.logic.model.Municipi;
import es.caib.gestoli.logic.model.Pais;
import es.caib.gestoli.logic.model.Provincia;
import es.caib.gestoli.logic.model.SortidaDiposit;



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
public class ProcesSortidaOlivaTaulaBotaGranelFormController extends SimpleFormController {

    private static Logger logger = Logger.getLogger(ProcesSortidaOlivaTaulaBotaGranelFormController.class);
    private OliInfraestructuraEjb oliInfraestructuraEjb;
    private OliProcessosEjb oliProcessosEjb;
    private String seleccioSessionKeyOrigen;
    private String establimentSessionKey;
    private String motiuVenta;
    private String motiuPromocio;
    private String motiuConsumPropi;
    private String motiuPerdua;
    private String motiuConsumidorFinal;
    private String entrada;
    private String venda;
    private String canviZona;
    private String desqualificat;
//    private String pendentQualificar;
    
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
    	
    	ProcesSortidaOlivaTaulaBotaGranelCommand pSortida = (ProcesSortidaOlivaTaulaBotaGranelCommand)command;
		if (isCreate(request)) {
			try{
				Pais pais = null;
		    	if (pSortida.getPaisId() != null) {
		    		oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
		    		pais = oliInfraestructuraEjb.paisAmbId(pSortida.getPaisId());
		    	}
		    	
		    	Provincia provincia = null;
		    	if (pSortida.getProvinciaId() != null) {
		    		oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
		    		provincia = oliInfraestructuraEjb.provinciaAmbId(pSortida.getProvinciaId());
		    	}
		    	
		    	Municipi municipi = null;
		    	if (pSortida.getMunicipiId() != null) {
		    		oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
		    		municipi = oliInfraestructuraEjb.municipiAmbId(pSortida.getMunicipiId());
		    	}
				
				SortidaDiposit sortidaDiposit = new SortidaDiposit();
				oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
				Bota diposit = oliInfraestructuraEjb.botaAmbId(pSortida.getIdBota());
				Establiment establiment = oliInfraestructuraEjb.establimentAmbId(pSortida.getIdEstabliment());
				if (diposit != null){
					oliProcessosEjb.setHibernateTemplate(getHibernateTemplate());
					oliProcessosEjb.generarSortidaBotaGranel(
							pSortida.getVendaData(), 
							pSortida.getKilos(),
							diposit,
							pSortida.getVendaDestinatari(),
							pSortida.getVendaNumeroDocument(),
							pSortida.getVendaTipusDocument(),
							pSortida.getVendaObservacions(),
							pais,
							provincia,
							municipi,
							pSortida.getDesqualificar(),
							desqualificat,
							pSortida.getIdPartidaOliva(),
							pSortida.getAccion(),
							pSortida.getVendaMotiu(),
							pSortida.getIdZona(),
							establiment);
				}

				ControllerUtils.saveMessageInfo(request, missatge("sortidaLot.missatge.ok"));
				
	    	} catch (Exception e) {
	    		 logger.error("Error processant la sortida del lot", e);
	             ControllerUtils.saveMessageError(request, missatge("sortidaLot.missatge.no"));
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
        
    	ProcesSortidaOlivaTaulaBotaGranelCommand pSortida = (ProcesSortidaOlivaTaulaBotaGranelCommand) command;
    	Establiment establiment = (Establiment) request.getSession().getAttribute(establimentSessionKey);
    	
        Map myModel = new HashMap();
        
        Collection origenList = (Collection)request.getSession().getAttribute(seleccioSessionKeyOrigen);
        Collection origen = null;
        
        String tipusSortida = (String) request.getAttribute("tipusSortida");
        
        if(pSortida.getTipusSortida()!= null && !pSortida.getTipusSortida().equalsIgnoreCase("")){
        	tipusSortida = pSortida.getTipusSortida();
        }
        if (pSortida.getPaisId() == null){
			pSortida.setPaisId(73L);
			pSortida.setProvinciaId(7L);
			pSortida.setMunicipiId(0L);
		}   
        
        Bota bota = null;
        if (origenList.size() != 0) {
    		Long pBota = new Long(0);
    		for(Iterator it= origenList.iterator();it.hasNext();){
    			pBota = (Long)it.next();
    			break;
    		}
    		 bota = oliInfraestructuraEjb.botaAmbId(pBota);
        }
        
        
        // MOTIUS
        List motius = new ArrayList();
        // Venta
        BasicData basicVenta = new BasicData();
        basicVenta.setId(motiuVenta);
        basicVenta.setNom(missatge("sortidaLot.venta"));
        motius.add(basicVenta);
        // Promoció
        BasicData basicPromocio = new BasicData();
        basicPromocio.setId(motiuPromocio);
        basicPromocio.setNom(missatge("sortidaLot.promocio"));
        motius.add(basicPromocio);
        // Consum propi
        BasicData basicConsum = new BasicData();
        basicConsum.setId(motiuConsumPropi);
        basicConsum.setNom(missatge("sortidaLot.consum.propi"));
        motius.add(basicConsum);
        // Pèrdua
        BasicData basicPerdua = new BasicData();
        basicPerdua.setId(motiuPerdua);
        basicPerdua.setNom(missatge("sortidaLot.perdua"));
        motius.add(basicPerdua);
        // Consumidor final
        BasicData basicFinal = new BasicData();
        basicFinal.setId(motiuConsumidorFinal);
        basicFinal.setNom(missatge("sortidaLot.consumidor.final"));
        motius.add(basicFinal);
        
        myModel.put("motius", motius);
        
        // Paisos.
        oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
        Collection paisos = oliInfraestructuraEjb.paisos();
        myModel.put("paisos", paisos);
        
        // Provincies.
        oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
        Collection provincies = oliInfraestructuraEjb.provincies();
        myModel.put("provincies", provincies);
        
        // Municipis.
        oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
        Collection municipis = oliInfraestructuraEjb.municipis();
        myModel.put("municipis", municipis);
        
        myModel.put("motiuVenta", motiuVenta);
        myModel.put("motiuPromocio", motiuPromocio); 
        myModel.put("motiuConsumPropi", motiuConsumPropi);
        myModel.put("motiuPerdua", motiuPerdua);
        
        myModel.put("venda", venda);
        myModel.put("canviZona", canviZona);
        
        myModel.put("tipusSortida", tipusSortida);
        String tipusProces = request.getParameter("tipusProces");
    	myModel.put("tipusProces", tipusProces);
        String pas = request.getParameter("pas");
    	myModel.put("pas", pas);
    	myModel.put("path", "sortida_oliva");
    	myModel.put("bota", bota);
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
    	
    	ProcesSortidaOlivaTaulaBotaGranelCommand pSortida = new ProcesSortidaOlivaTaulaBotaGranelCommand();
    	
    	try {
    					
			if (!isFormSubmission(request)) {
				pSortida.setVendaData(new Date());
				pSortida.setCanviZonaData(new Date());
			}
			
			Collection origenList = (Collection)request.getSession().getAttribute(seleccioSessionKeyOrigen);
			
		} catch (Exception e) {
			logger.error("EXCEPTION", e);
		}
    	    	
        return pSortida;
        
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
     * Injecció de la dependència seleccioSessionKeyOrigen
     *
     * @param seleccioSessionKey La classe a injectar.
     */
    public void setSeleccioSessionKeyOrigen(String seleccioSessionKeyOrigen) {
        this.seleccioSessionKeyOrigen = seleccioSessionKeyOrigen;
    }
    
	public void setMotiuVenta(String motiuVenta) {
		this.motiuVenta = motiuVenta;
	}

	public void setMotiuPromocio(String motiuPromocio) {
		this.motiuPromocio = motiuPromocio;
	}

	public void setMotiuConsumPropi(String motiuConsumPropi) {
		this.motiuConsumPropi = motiuConsumPropi;
	}

	public void setMotiuPerdua(String motiuPerdua) {
		this.motiuPerdua = motiuPerdua;
	}

	public void setMotiuConsumidorFinal(String motiuConsumidorFinal) {
		this.motiuConsumidorFinal = motiuConsumidorFinal;
	}

	public void setEntrada(String entrada) {
		this.entrada = entrada;
	}

	public void setVenda(String venda) {
		this.venda = venda;
	}

	public void setDesqualificat(String desqualificat) {
		this.desqualificat = desqualificat;
	}

//	public void setPendentQualificar(String pendentQualificar) {
//		this.pendentQualificar = pendentQualificar;
//	}

	public void setCanviZona(String canviZona) {
		this.canviZona = canviZona;
	}

    public void setEstablimentSessionKey(String establimentSessionKey) {
		this.establimentSessionKey = establimentSessionKey;
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