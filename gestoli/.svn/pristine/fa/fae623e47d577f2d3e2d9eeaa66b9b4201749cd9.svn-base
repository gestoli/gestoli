/**
 * ConsultaTrazabilitatController.java
 * 
 * Creada el 28 de juny de 2006, 13:19:22
 * &copy; Limit Tecnologies 2006
 */
package es.caib.gestoli.front.spring;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.mvc.BaseCommandController;
import org.springframework.web.servlet.mvc.SimpleFormController;

import es.caib.gestoli.front.util.BasicData;
import es.caib.gestoli.logic.interfaces.OliConsultaEjb;
import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;
import es.caib.gestoli.logic.interfaces.OliProcessosEjb;
import es.caib.gestoli.logic.model.Establiment;
import es.caib.gestoli.logic.model.Lot;
import es.caib.gestoli.logic.model.Municipi;
import es.caib.gestoli.logic.model.Pais;
import es.caib.gestoli.logic.model.Provincia;
import es.caib.gestoli.logic.model.SortidaLot;
import es.caib.gestoli.logic.model.Zona;

/**
 * <p>Controlador per a mostrar la trazabilitat d'un
 * contenidor.</p>
 * <p>Els paràmetres de la petició HTTP relevants per
 * a aquest controlador són:
 * <ul>
 *   <li>contenidorId: Identificador del contenidor.</li>
 * </ul></p>
 *
 */
public class ModificarSortidaLotController extends SimpleFormController {
	
	
	private static Logger logger = Logger.getLogger(ModificarSortidaLotController.class);
	private OliConsultaEjb oliConsultaEjb;
	private OliInfraestructuraEjb oliInfraestructuraEjb;
	private HibernateTemplate hibernateTemplate;
	private String trazaTipusOliElaborat;
	private String trazaTipusOliDisponibleDiposit;
	private String trazaTipusOliDisponibleLote;
	private String trazaTipusEntradaVolant;
	private String trazaTipusSortidaVolant;
	private String trazaTipusOliComercialitzat;
	private String trazaTipusPartidaOli;
	private String trazaTipusOlivaElaborada;
	private String trazaTipusOlivaComercialitzada;
	private String trazaTipusOlivaDisponibleBota;
	private String trazaTipusOlivaDisponibleEnvas;
	private String seleccioSessionKeyOrigen;
    private String establimentSessionKey;
    private String entrada;
    private String venda;
    private String canviZona;
    private String desqualificat;
    private String motiuVenta;
    private String motiuPromocio;
    private String motiuConsumPropi;
    private String motiuPerdua;
    private String motiuConsumidorFinal;
    private OliProcessosEjb oliProcessosEjb;
	


    /**
     * Se llama cuando se aceptan las modificaciones del objeto. Solo
     * se ejecuta esta función en el caso de que se haya ejecutado la
     * validación sin producir ningún error.
     * 
     * @see AbstractController#handleRequest(HttpServletRequest, HttpServletResponse)
     */
    
    public ModelAndView onSubmit(
            HttpServletRequest request,
            HttpServletResponse response,
            Object command,
            BindException errors)
    throws ServletException {
    	
    	ProcesSortidaCommand pSortida = (ProcesSortidaCommand)command;
    	Integer venudesInicialment = pSortida.getVendaNumeroBotelles()[1];
    	SortidaLot sortidaLot = null;
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
				
		    	if ("l".equals(pSortida.getTipusSortida())) {
					
					oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
					int numBotTot = new Integer(0);
					//for (int i = 0; i < pSortida.getLot().length; i++){
				sortidaLot = new SortidaLot();
				
				Lot lot = oliInfraestructuraEjb.lotAmbId(pSortida.getLot()[0].getId());
				Zona zonaCanvi = null;

				String accio = "v";
				sortidaLot.setId(Long.parseLong(request.getParameter("slId")));
				sortidaLot.setLot(lot);
				sortidaLot.setZona(lot.getZona());
				sortidaLot.setAccioSortida(accio);
				sortidaLot.setPais(pais);
				sortidaLot.setProvincia(provincia);
				sortidaLot.setMunicipi(municipi);
				sortidaLot.setValid(true);
				if (accio.equals(venda)) {
					sortidaLot.setVendaData(pSortida.getVendaData());
					sortidaLot.setVendaNumeroBotelles(pSortida.getVendaNumeroBotelles()[0]);
					sortidaLot.setVendaMotiu(pSortida.getVendaMotiu());
					sortidaLot.setVendaObservacions(pSortida.getVendaObservacions());
					if (sortidaLot.getVendaMotiu().equals(motiuPromocio)) {
						sortidaLot.setVendaDestinatari(motiuPromocio);
					} else if (sortidaLot.getVendaMotiu().equals(motiuConsumPropi)) {
						sortidaLot.setVendaDestinatari(motiuConsumPropi);
					} else if (sortidaLot.getVendaMotiu().equals(motiuPerdua)) {
						sortidaLot.setVendaDestinatari(motiuPerdua);
					} else if (sortidaLot.getVendaMotiu().equals(motiuConsumidorFinal)) {
						sortidaLot.setVendaDestinatari(motiuConsumidorFinal);
					} else {
						sortidaLot.setVendaDestinatari(pSortida.getVendaDestinatari());
						sortidaLot.setVendaNumeroDocument(pSortida.getVendaNumeroDocument());
						sortidaLot.setVendaTipusDocument(pSortida.getVendaTipusDocument());
					}
					// si el lot estava buit, llevar la data de la darrera sortida
					
					if((lot.getNumeroBotellesActuals().doubleValue() + pSortida.getVendaNumeroBotelles()[1]) - pSortida.getVendaNumeroBotelles()[0] > 0){
						lot.setDatafi(null);
					}
					//si el lot no e stava buit i es buida, posar data darrera venda
					if((lot.getNumeroBotellesActuals().doubleValue() + pSortida.getVendaNumeroBotelles()[1]) - pSortida.getVendaNumeroBotelles()[0] == 0){
						lot.setDatafi(pSortida.getVendaData());
					}
						
				} else if (accio.equals(canviZona)) {
					sortidaLot.setCanviZonaData(pSortida.getCanviZonaData());
					sortidaLot.setCanviZonaObservacions(pSortida.getCanviZonaObservacions());

					zonaCanvi = oliInfraestructuraEjb.zonaAmbId(pSortida.getIdZona());
					sortidaLot.setVendaDestinatari(zonaCanvi.getNom());
				}

				oliProcessosEjb.setHibernateTemplate(getHibernateTemplate());
				oliProcessosEjb.modificarSortidaLot(sortidaLot, venda, venudesInicialment);
					//}//fi for
				}
				
				ControllerUtils.saveMessageInfo(request, missatge("sortidaLot.missatge.ok.modificar"));
				
	    	} catch (Exception e) {
	    		 logger.error("Error processant la modificació de la sortida del lot", e);
	             ControllerUtils.saveMessageError(request, missatge("sortidaLot.missatge.no.modificar"));
			}
		}
        
		String desti = "EstablimentPrincipal.html";
		
		return new ModelAndView("redirect:" + desti);
    }
    
    
    
    
    
    protected Map referenceData(
			HttpServletRequest request,
			Object command,
			Errors errors) throws ServletException {
    	Map myModel = new HashMap();
    	
    	String tipusSortida = request.getParameter("tipusSortida"); 
    	//command
    	ProcesSortidaCommand procesSortidaCommand = (ProcesSortidaCommand)command; 	
    	Establiment establiment = (Establiment) request.getSession().getAttribute(establimentSessionKey);
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
        
        // ZONAS
        List zonas = new ArrayList();
        try {
			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Iterator itZonas = null;
		try {
			itZonas = oliInfraestructuraEjb.zonaActiusAmbEstabliment(establiment.getId()).iterator();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        while (itZonas.hasNext()) {
        	Zona zona = (Zona) itZonas.next();
        	if (procesSortidaCommand.getLot() != null && procesSortidaCommand.getLot()[0] != null && !procesSortidaCommand.getLot()[0].getZona().getId().equals(zona.getId())) {
        		BasicData basic = new BasicData();
        		basic.setId(String.valueOf(zona.getId()));
        		basic.setNom(zona.getNom());
        		zonas.add(basic);
        	}
        }
        
        myModel.put("zonas", zonas);
        
        // Paisos.
        try {
			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Collection paisos = null;
		try {
			paisos = oliInfraestructuraEjb.paisos();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        myModel.put("paisos", paisos);
        
        // Provincies.
        try {
			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Collection provincies = null;
		try {
			provincies = oliInfraestructuraEjb.provincies();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        myModel.put("provincies", provincies);
        
        // Municipis.
        try {
			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Collection municipis = null;
		try {
			municipis = oliInfraestructuraEjb.municipis();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        myModel.put("municipis", municipis);
        
		myModel.put("formData", procesSortidaCommand);
		myModel.put("tipusSortida", procesSortidaCommand.getTipusSortida());
		myModel.put("motius", motius);
		myModel.put("lots", procesSortidaCommand.getLot());
		myModel.put("seleccioSessionKeyOrigen", seleccioSessionKeyOrigen);
		String slId = "";
		slId = request.getParameter("sortidaLotId");
		if(slId == null){
			slId = request.getParameter("slId");
		}
		myModel.put("slId", slId);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Integer mes = Calendar.getInstance().get(Calendar.MONTH)+1;
		String mesActual = mes.toString();
		if(mesActual.length()==1){
			mesActual = "0"+mesActual;
		}
		boolean mateixMes = false;
		String dataV = "";
		try{
			dataV = request.getParameter("dataSortida");
			
			if(dataV!=null){
				String[] mesDoc = dataV.split("/");
				mateixMes = mesDoc[1].equals(mesActual);
			}
		}catch (Exception e){
			e.getStackTrace();
		}
		myModel.put("mateixMes", mateixMes);
    	return myModel;
    }

    
    
    
    protected Object formBackingObject(HttpServletRequest request) throws ServletException {
    	
    	String id = request.getParameter("sortidaLotId");
    	String tipusSortida = request.getParameter("tipusSortida"); 
    	
    	//command
    	ProcesSortidaCommand procesSortidaCommand = new ProcesSortidaCommand();
//    	if(id!=null){
    		try  {
        		
        		oliConsultaEjb.setHibernateTemplate(getHibernateTemplate());
        		Collection sortidaLot = oliConsultaEjb.getSortidaLotById(Long.parseLong(id));
        		SortidaLot sl = new SortidaLot();
        		for(Object o: sortidaLot){
            		 sl = (SortidaLot)o;
        		}
        		procesSortidaCommand.setAccion(sl.getAccioSortida());
        		procesSortidaCommand.setCanviZonaData(sl.getCanviZonaData());
        		procesSortidaCommand.setCanviZonaObservacions(sl.getObservacions());
        		procesSortidaCommand.setDesqualificar(false);
        		procesSortidaCommand.setDiposit(sl.getLot().getDiposit());
        		procesSortidaCommand.setIdZona(sl.getZona().getId());
        		procesSortidaCommand.setKilos(sl.getLot().getKilosEnvassats());
        		procesSortidaCommand.setLitros(sl.getLot().getLitresEnvassats());
        		Lot[] lots = new Lot[1];
        		lots[0] = sl.getLot();
        		procesSortidaCommand.setLot(lots);
        		procesSortidaCommand.setMunicipiId(sl.getMunicipi().getId());
        		procesSortidaCommand.setPaisId(sl.getPais().getId());
        		procesSortidaCommand.setProvinciaId(sl.getProvincia().getId());
        		procesSortidaCommand.setTipusSortida(tipusSortida);
        		procesSortidaCommand.setVendaData(sl.getVendaData());
        		procesSortidaCommand.setVendaDestinatari(sl.getVendaDestinatari());
        		procesSortidaCommand.setVendaMotiu(sl.getVendaMotiu());
        		Integer[] numBot = new Integer[2];
        		numBot[0] = sl.getVendaNumeroBotelles();
        		numBot[1] = sl.getVendaNumeroBotelles();//aquí guardam el valor inicial del número de botelles per validar les botelles venudes són superiors a les existents.
        		procesSortidaCommand.setVendaNumeroBotelles(numBot);
        		procesSortidaCommand.setVendaNumeroDocument(sl.getVendaNumeroDocument());
        		procesSortidaCommand.setVendaObservacions(sl.getVendaObservacions());
        		procesSortidaCommand.setVendaTipusDocument(sl.getVendaTipusDocument());
        		
    		} catch (Exception e) {
    			logger.error("EXCEPTION", e);
    		}
    		
//    	}
    	
        return procesSortidaCommand;
        
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

	/**
	 * Injecció de la dependència setTrazaTipusOliElaborat
	 *
	 * @param trazaTipusOliElaborat La classe a injectar.
	 */
	public void setTrazaTipusOliElaborat(String trazaTipusOliElaborat) {
		this.trazaTipusOliElaborat = trazaTipusOliElaborat;
	}
	/**
	 * Injecció de la dependència trazaTipusOliDisponibleDiposit
	 *
	 * @param trazaTipusOliDisponibleDiposit La classe a injectar.
	 */
	public void setTrazaTipusOliDisponibleDiposit(String trazaTipusOliDisponibleDiposit) {
		this.trazaTipusOliDisponibleDiposit = trazaTipusOliDisponibleDiposit;
	}
	
	/**
	 * Injecció de la dependència trazaTipusOliDisponiblePartida
	 *
	 * @param trazaTipusOliDisponiblePartida La classe a injectar.
	 */
	public void setTrazaTipusOliDisponibleLote(String trazaTipusOliDisponibleLote) {
		this.trazaTipusOliDisponibleLote = trazaTipusOliDisponibleLote;
	}
	
	/**
	 * Injecció de la dependència trazaTipusEntradaVolant
	 *
	 * @param trazaTipusEntradaVolant La classe a injectar.
	 */
	public void setTrazaTipusEntradaVolant(String trazaTipusEntradaVolant) {
		this.trazaTipusEntradaVolant = trazaTipusEntradaVolant;
	}
	/**
	 * Injecció de la dependència trazaTipusSortidaVolant
	 *
	 * @param trazaTipusSortidaVolant La classe a injectar.
	 */
	public void setTrazaTipusSortidaVolant(String trazaTipusSortidaVolant) {
		this.trazaTipusSortidaVolant = trazaTipusSortidaVolant;
	}
	/**
	 * Injecció de la dependència setTrazaTipusOlivaElaborada
	 *
	 * @param trazaTipusOlivaElaborada La classe a injectar.
	 */
	public void setTrazaTipusOlivaElaborada(String trazaTipusOlivaElaborada) {
		this.trazaTipusOlivaElaborada = trazaTipusOlivaElaborada;
	}
	/**
	 * Metodo 'seTtrazaTipusOliComercialitzat'
	 * @param trazaTipusOliComercialitzat
	 */
	public void setTrazaTipusOliComercialitzat(String trazaTipusOliComercialitzat) {
		this.trazaTipusOliComercialitzat = trazaTipusOliComercialitzat;
	}
	/**
	 * Metodo 'setTrazaTipusPartidaOli'
	 * @param trazaTipusPartidaOli
	 */
	public void setTrazaTipusPartidaOli(String trazaTipusPartidaOli) {
		this.trazaTipusPartidaOli = trazaTipusPartidaOli;
	}

	public void setTrazaTipusOlivaComercialitzada(
			String trazaTipusOlivaComercialitzada) {
		this.trazaTipusOlivaComercialitzada = trazaTipusOlivaComercialitzada;
	}

	public void setTrazaTipusOlivaDisponibleBota(
			String trazaTipusOlivaDisponibleBota) {
		this.trazaTipusOlivaDisponibleBota = trazaTipusOlivaDisponibleBota;
	}

	public void setTrazaTipusOlivaDisponibleEnvas(
			String trazaTipusOlivaDisponibleEnvas) {
		this.trazaTipusOlivaDisponibleEnvas = trazaTipusOlivaDisponibleEnvas;
	}

	public String getSeleccioSessionKeyOrigen() {
		return seleccioSessionKeyOrigen;
	}

	public void setSeleccioSessionKeyOrigen(String seleccioSessionKeyOrigen) {
		this.seleccioSessionKeyOrigen = seleccioSessionKeyOrigen;
	}

	public String getEstablimentSessionKey() {
		return establimentSessionKey;
	}

	public void setEstablimentSessionKey(String establimentSessionKey) {
		this.establimentSessionKey = establimentSessionKey;
	}

	public String getEntrada() {
		return entrada;
	}

	public void setEntrada(String entrada) {
		this.entrada = entrada;
	}

	public String getCanviZona() {
		return canviZona;
	}

	public void setCanviZona(String canviZona) {
		this.canviZona = canviZona;
	}

	public String getDesqualificat() {
		return desqualificat;
	}

	public void setDesqualificat(String desqualificat) {
		this.desqualificat = desqualificat;
	}

	public String getVenda() {
		return venda;
	}

	public void setVenda(String venda) {
		this.venda = venda;
	}
	/**
	 * Indica si la petición es de creación o no.
	 * @param request
	 * @return true si es una petición de creación o false si es de edición.
	 */
	private boolean isCreate(HttpServletRequest request) {
		return (request.getParameter("id") == null);
	}


	public OliProcessosEjb getOliProcessosEjb() {
		return oliProcessosEjb;
	}


	public void setOliProcessosEjb(OliProcessosEjb oliProcessosEjb) {
		this.oliProcessosEjb = oliProcessosEjb;
	}


	public OliConsultaEjb getOliConsultaEjb() {
		return oliConsultaEjb;
	}


	public OliInfraestructuraEjb getOliInfraestructuraEjb() {
		return oliInfraestructuraEjb;
	}


	public String getMotiuVenta() {
		return motiuVenta;
	}


	public void setMotiuVenta(String motiuVenta) {
		this.motiuVenta = motiuVenta;
	}

	public String getMotiuPromocio() {
		return motiuPromocio;
	}

	public void setMotiuPromocio(String motiuPromocio) {
		this.motiuPromocio = motiuPromocio;
	}

	public String getMotiuConsumPropi() {
		return motiuConsumPropi;
	}

	public void setMotiuConsumPropi(String motiuConsumPropi) {
		this.motiuConsumPropi = motiuConsumPropi;
	}


	public String getMotiuPerdua() {
		return motiuPerdua;
	}

	public void setMotiuPerdua(String motiuPerdua) {
		this.motiuPerdua = motiuPerdua;
	}

	public String getMotiuConsumidorFinal() {
		return motiuConsumidorFinal;
	}

	public void setMotiuConsumidorFinal(String motiuConsumidorFinal) {
		this.motiuConsumidorFinal = motiuConsumidorFinal;
	}
	
	private String missatge(String clave){
    	String valor= getMessageSourceAccessor().getMessage(clave);
    	return valor;
    }
}
