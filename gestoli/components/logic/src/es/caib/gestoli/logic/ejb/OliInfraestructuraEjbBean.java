package es.caib.gestoli.logic.ejb;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.ejb.SessionBean;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.dao.AnaliticaDao;
import es.caib.gestoli.logic.dao.AnaliticaParametreDao;
import es.caib.gestoli.logic.dao.AnaliticaParametreTipusDao;
import es.caib.gestoli.logic.dao.AnaliticaValorDao;
import es.caib.gestoli.logic.dao.ArxiuDao;
import es.caib.gestoli.logic.dao.AutocontrolOlivaDao;
import es.caib.gestoli.logic.dao.AvisDao;
import es.caib.gestoli.logic.dao.BotaDao;
import es.caib.gestoli.logic.dao.CampanyaDao;
import es.caib.gestoli.logic.dao.CategoriaOliDao;
import es.caib.gestoli.logic.dao.ColorDao;
import es.caib.gestoli.logic.dao.DescomposicioPartidaOlivaDao;
import es.caib.gestoli.logic.dao.DescomposicioPlantacioDao;
import es.caib.gestoli.logic.dao.DipositDao;
import es.caib.gestoli.logic.dao.DocumentInspeccioDao;
import es.caib.gestoli.logic.dao.ElaboracioDao;
import es.caib.gestoli.logic.dao.EntradaDipositDao;
import es.caib.gestoli.logic.dao.EntradaLotDao;
import es.caib.gestoli.logic.dao.EstablimentDao;
import es.caib.gestoli.logic.dao.EtiquetatgeDao;
import es.caib.gestoli.logic.dao.EtiquetesLotDao;
import es.caib.gestoli.logic.dao.FacturaDao;
import es.caib.gestoli.logic.dao.FincasDao;
import es.caib.gestoli.logic.dao.GestioInfografiaDao;
import es.caib.gestoli.logic.dao.GrupDao;
import es.caib.gestoli.logic.dao.HistoricDescomposicioPlantacioDao;
import es.caib.gestoli.logic.dao.HistoricDipositDao;
import es.caib.gestoli.logic.dao.HistoricEstablimentDao;
import es.caib.gestoli.logic.dao.HistoricFincasDao;
import es.caib.gestoli.logic.dao.HistoricLlistatFacturaDao;
import es.caib.gestoli.logic.dao.HistoricOlivicultorDao;
import es.caib.gestoli.logic.dao.HistoricPlantacioDao;
import es.caib.gestoli.logic.dao.HistoricZonaDao;
import es.caib.gestoli.logic.dao.IdiomaDao;
import es.caib.gestoli.logic.dao.InformacioUtilDao;
import es.caib.gestoli.logic.dao.InformeCampanyaDao;
import es.caib.gestoli.logic.dao.LotDao;
import es.caib.gestoli.logic.dao.MarcaDao;
import es.caib.gestoli.logic.dao.MaterialDipositDao;
import es.caib.gestoli.logic.dao.MaterialTipusEnvasDao;
import es.caib.gestoli.logic.dao.MunicipiDao;
import es.caib.gestoli.logic.dao.OlivicultorDao;
import es.caib.gestoli.logic.dao.PaisDao;
import es.caib.gestoli.logic.dao.PartidaFonollDao;
import es.caib.gestoli.logic.dao.PartidaOliDao;
import es.caib.gestoli.logic.dao.PartidaOlivaDao;
import es.caib.gestoli.logic.dao.PlantacioDao;
import es.caib.gestoli.logic.dao.ProducteDao;
import es.caib.gestoli.logic.dao.ProvinciaDao;
import es.caib.gestoli.logic.dao.QuadernCampDao;
import es.caib.gestoli.logic.dao.RendimentVarietatDao;
import es.caib.gestoli.logic.dao.SolicitanteDao;
import es.caib.gestoli.logic.dao.SortidaDipositDao;
import es.caib.gestoli.logic.dao.SortidaLotDao;
import es.caib.gestoli.logic.dao.SortidaOliFacturacioDao;
import es.caib.gestoli.logic.dao.SortidaOrujoDao;
import es.caib.gestoli.logic.dao.TaxaDao;
import es.caib.gestoli.logic.dao.TipusEnvasDao;
import es.caib.gestoli.logic.dao.TipusEstablimentDao;
import es.caib.gestoli.logic.dao.TrasllatDao;
import es.caib.gestoli.logic.dao.TrazaDao;
import es.caib.gestoli.logic.dao.UsuariDao;
import es.caib.gestoli.logic.dao.VarietatOliDao;
import es.caib.gestoli.logic.dao.VarietatOlivaDao;
import es.caib.gestoli.logic.dao.ZonaDao;
import es.caib.gestoli.logic.model.Analitica;
import es.caib.gestoli.logic.model.AnaliticaParametre;
import es.caib.gestoli.logic.model.AnaliticaParametreTipus;
import es.caib.gestoli.logic.model.AnaliticaValor;
import es.caib.gestoli.logic.model.Arxiu;
import es.caib.gestoli.logic.model.AutocontrolOliva;
import es.caib.gestoli.logic.model.Avis;
import es.caib.gestoli.logic.model.Bota;
import es.caib.gestoli.logic.model.Campanya;
import es.caib.gestoli.logic.model.CategoriaOli;
import es.caib.gestoli.logic.model.Color;
import es.caib.gestoli.logic.model.DescomposicioPartidaOliva;
import es.caib.gestoli.logic.model.DescomposicioPlantacio;
import es.caib.gestoli.logic.model.Diposit;
import es.caib.gestoli.logic.model.DocumentInspeccio;
import es.caib.gestoli.logic.model.Elaboracio;
import es.caib.gestoli.logic.model.EntradaDiposit;
import es.caib.gestoli.logic.model.EntradaLot;
import es.caib.gestoli.logic.model.Establiment;
import es.caib.gestoli.logic.model.Etiquetatge;
import es.caib.gestoli.logic.model.EtiquetesLot;
import es.caib.gestoli.logic.model.Factura;
import es.caib.gestoli.logic.model.Finca;
import es.caib.gestoli.logic.model.GestioInfografia;
import es.caib.gestoli.logic.model.Grup;
import es.caib.gestoli.logic.model.HistoricDescomposicioPlantacio;
import es.caib.gestoli.logic.model.HistoricDiposit;
import es.caib.gestoli.logic.model.HistoricEstabliment;
import es.caib.gestoli.logic.model.HistoricFinca;
import es.caib.gestoli.logic.model.HistoricLlistatFactura;
import es.caib.gestoli.logic.model.HistoricOlivicultor;
import es.caib.gestoli.logic.model.HistoricPlantacio;
import es.caib.gestoli.logic.model.HistoricZona;
import es.caib.gestoli.logic.model.Idioma;
import es.caib.gestoli.logic.model.InformacioUtil;
import es.caib.gestoli.logic.model.InformeCampanya;
import es.caib.gestoli.logic.model.Lot;
import es.caib.gestoli.logic.model.Marca;
import es.caib.gestoli.logic.model.MaterialDiposit;
import es.caib.gestoli.logic.model.MaterialTipusEnvas;
import es.caib.gestoli.logic.model.Municipi;
import es.caib.gestoli.logic.model.Olivicultor;
import es.caib.gestoli.logic.model.Pais;
import es.caib.gestoli.logic.model.PartidaFonoll;
import es.caib.gestoli.logic.model.PartidaOli;
import es.caib.gestoli.logic.model.PartidaOliva;
import es.caib.gestoli.logic.model.Plantacio;
import es.caib.gestoli.logic.model.Producte;
import es.caib.gestoli.logic.model.Provincia;
import es.caib.gestoli.logic.model.QuadernCamp;
import es.caib.gestoli.logic.model.RendimentVarietat;
import es.caib.gestoli.logic.model.Solicitant;
import es.caib.gestoli.logic.model.SortidaDiposit;
import es.caib.gestoli.logic.model.SortidaLot;
import es.caib.gestoli.logic.model.SortidaOliFacturacio;
import es.caib.gestoli.logic.model.SortidaOrujo;
import es.caib.gestoli.logic.model.Taxa;
import es.caib.gestoli.logic.model.TipusEnvas;
import es.caib.gestoli.logic.model.TipusEstabliment;
import es.caib.gestoli.logic.model.Trasllat;
import es.caib.gestoli.logic.model.Traza;
import es.caib.gestoli.logic.model.Usuari;
import es.caib.gestoli.logic.model.VarietatOli;
import es.caib.gestoli.logic.model.VarietatOliva;
import es.caib.gestoli.logic.model.Zona;
import es.caib.gestoli.logic.util.Constants;
import es.caib.gestoli.logic.util.EtiquetatgeAux;

/**
 * 
 * <!-- begin-user-doc --> A generated session bean <!-- end-user-doc --> 
 * <!-- begin-xdoclet-definition -->
 * 
 * @ejb.bean name="OliInfraestructuraEjb"
 *           description="An EJB named OliInfraestructuraEjb"
 *           display-name="OliInfraestructuraEjb"
 *           jndi-name="OliInfraestructuraEjb"
 *           local-jndi-name="OliInfraestructuraEjbLocal"
 *           type="Stateless"
 *           transaction-type="Container"
 * @ejb.transaction type="Required"
 * 
 * <!-- end-xdoclet-definition -->
 */

public abstract class OliInfraestructuraEjbBean implements SessionBean {
	private static Logger logger = Logger.getLogger(OliInfraestructuraEjbBean.class);

	private GrupDao grupDao;
	private UsuariDao usuariDao;
	private IdiomaDao idiomaDao;
	private TaxaDao taxaDao;
	private ColorDao colorDao;
	private MaterialTipusEnvasDao materialTipusEnvasDao;
	private TipusEnvasDao tipusEnvasDao;
	private TipusEstablimentDao tipusEstablimentDao;
	private MarcaDao marcaDao;
	private EstablimentDao establimentDao;
	private EtiquetatgeDao etiquetatgeDao;
	private OlivicultorDao olivicultorDao;
	private FincasDao fincasDao;
	private PlantacioDao plantacioDao;
	private DescomposicioPlantacioDao descomposicioPlantacioDao;
	private DescomposicioPartidaOlivaDao descomposicioPartidaOlivaDao;
	private ZonaDao zonaDao;
	private DipositDao dipositDao;
	private CampanyaDao campanyaDao;
	private ArxiuDao arxiuDao;
	private MaterialDipositDao materialDipositDao;
	private VarietatOliDao varietatOliDao;
	private VarietatOlivaDao varietatOlivaDao;
	private FacturaDao facturaDao;
	private PartidaOlivaDao partidaOlivaDao;
	private SolicitanteDao solicitanteDao;
	private LotDao lotDao;
	private AnaliticaDao analiticaDao;
	private AutocontrolOlivaDao autocontrolDao;
	private AnaliticaParametreTipusDao analiticaParametreTipusDao;
	private AnaliticaParametreDao analiticaParametreDao;
	private AnaliticaValorDao analiticaValorDao;
	private CategoriaOliDao categoriaOliDao;
	private TrasllatDao trasllatDao;
	private SortidaLotDao sortidaLotDao;
	private EntradaLotDao entradaLotDao;
	private SortidaDipositDao sortidaDipositDao;
	private EntradaDipositDao entradaDipositDao;
	private TrazaDao trazaDao;
	private PartidaOliDao partidaOliDao;
	private PaisDao paisDao;
	private ProvinciaDao provinciaDao;
	private MunicipiDao municipiDao;
	private HistoricOlivicultorDao historicOlivicultorDao;
	private HistoricFincasDao historicFincaDao;
	private HistoricPlantacioDao historicPlantacioDao;
	private HistoricDescomposicioPlantacioDao historicDescomposicioPlantacioDao;
	private HistoricEstablimentDao historicEstablimentDao;
	private ElaboracioDao elaboracioDao;
	private HistoricLlistatFacturaDao historicLlistatFacturaDao;
	private SortidaOrujoDao sortidaOrujoDao;
	private RendimentVarietatDao rendimentVarietatDao;
	private EtiquetesLotDao etiquetesLotDao;
	private ProducteDao producteDao;
	private InformacioUtilDao informacioUtilDao;
	private GestioInfografiaDao gestioInfografiaDao;
	private AvisDao avisDao;
	private QuadernCampDao quadernCampDao;
	private DocumentInspeccioDao documentInspeccioDao;
	private SortidaOliFacturacioDao sortidaOliFacturacioDao;
	private InformeCampanyaDao informeCampanyaDao;
	private HistoricZonaDao historicZonaDao;
	private HistoricDipositDao historicDipositDao;
	private PartidaFonollDao partidaFonollDao;
	private BotaDao botaDao;
	
	private HibernateTemplate hibernateTemplate;
	

						   

	/**
	 * Creacio del EJB 
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.create-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" 
	 * <!-- end-xdoclet-definition -->
	 */
	public void ejbCreate() {
		grupDao = new GrupDao();
		usuariDao = new UsuariDao();
		idiomaDao = new IdiomaDao();
		taxaDao = new TaxaDao();
		colorDao = new ColorDao();
		materialTipusEnvasDao = new MaterialTipusEnvasDao();
		tipusEnvasDao = new TipusEnvasDao();
		tipusEstablimentDao = new TipusEstablimentDao();
		marcaDao = new MarcaDao();
		establimentDao = new EstablimentDao();
		etiquetatgeDao = new EtiquetatgeDao();
		olivicultorDao = new OlivicultorDao();
		fincasDao = new FincasDao();
		plantacioDao = new PlantacioDao();
		descomposicioPlantacioDao = new DescomposicioPlantacioDao();
		descomposicioPartidaOlivaDao = new DescomposicioPartidaOlivaDao();
		zonaDao = new ZonaDao();
		dipositDao = new DipositDao();
		campanyaDao = new CampanyaDao();
		arxiuDao = new ArxiuDao();
		materialDipositDao = new MaterialDipositDao();
		varietatOliDao = new VarietatOliDao();
		varietatOlivaDao = new VarietatOlivaDao();
		facturaDao = new FacturaDao();
		partidaOlivaDao = new PartidaOlivaDao();
		solicitanteDao = new SolicitanteDao();
		lotDao = new LotDao();
		analiticaDao = new AnaliticaDao();
		analiticaParametreDao = new AnaliticaParametreDao();
		analiticaParametreTipusDao = new AnaliticaParametreTipusDao();
		analiticaValorDao = new AnaliticaValorDao();
		autocontrolDao = new AutocontrolOlivaDao();
		categoriaOliDao = new CategoriaOliDao();
		trasllatDao = new TrasllatDao();
		sortidaLotDao = new SortidaLotDao();
		entradaLotDao = new EntradaLotDao();
		sortidaDipositDao = new SortidaDipositDao();
		entradaDipositDao = new EntradaDipositDao();
		trazaDao = new TrazaDao();
		partidaOliDao = new PartidaOliDao();
		paisDao = new PaisDao();
		provinciaDao = new ProvinciaDao();
		municipiDao = new MunicipiDao();
		historicOlivicultorDao = new HistoricOlivicultorDao();
		historicFincaDao = new HistoricFincasDao();
		historicPlantacioDao = new HistoricPlantacioDao();
		historicDescomposicioPlantacioDao = new HistoricDescomposicioPlantacioDao();
		historicEstablimentDao = new HistoricEstablimentDao();
		elaboracioDao = new ElaboracioDao();
		historicLlistatFacturaDao = new HistoricLlistatFacturaDao();
		sortidaOrujoDao = new SortidaOrujoDao();
		rendimentVarietatDao = new RendimentVarietatDao();
		etiquetesLotDao = new EtiquetesLotDao();
		producteDao = new ProducteDao();
		informacioUtilDao = new InformacioUtilDao();
		gestioInfografiaDao = new GestioInfografiaDao();
		avisDao = new AvisDao();
		quadernCampDao = new QuadernCampDao();
		documentInspeccioDao = new DocumentInspeccioDao();
		sortidaOliFacturacioDao = new SortidaOliFacturacioDao();
		informeCampanyaDao = new InformeCampanyaDao();
		historicZonaDao = new HistoricZonaDao();
		historicDipositDao = new HistoricDipositDao();
		partidaFonollDao = new PartidaFonollDao();
		botaDao = new BotaDao();
	}

	/**
	 * Només serveix per poder especificar els permisos amb xdoclet. 
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" 
	 * <!-- end-xdoclet-definition -->
	 */
	public void ejbRemove() {
	}

	/**
	 * Cerca tots els grups definits a GestOli
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" 
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection grupCercaTots() {
		grupDao.setHibernateTemplate(getHibernateTemplate());
		return grupDao.findAll();
	}

	/**
	 * Cerca tots els usuaris definits a GestOli
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection usuariCercaTots() {
		usuariDao.setHibernateTemplate(getHibernateTemplate());
		return usuariDao.findAll();
	}
	
	/**
	 * Cerca tots els usuaris no olivicultors
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" 
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection usuariCercaTotsNoOlivicultors() {
		usuariDao.setHibernateTemplate(getHibernateTemplate());
		return usuariDao.findAllNoOlivicultors();
	}
	
	/**
	 * Cerca tots els usuaris pertanyents a un establiment
	 * 
	 * @param idEstabliment Identificador de l'establiment
	 * @return Collection dels usuaris de l'establiment
	 *
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ESTADMINISTRADOR"
 	 * <!-- end-xdoclet-definition -->
	 */
	public Collection usuariCercaUsuarisEstabliment(Long idEstabliment) {
		usuariDao.setHibernateTemplate(getHibernateTemplate());
		return usuariDao.findUsuarisEstabliment(idEstabliment);
	}

	/**
	 * Cerca tots els usuaris actius 
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" 
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection usuariCercaTotsActivos() {
		usuariDao.setHibernateTemplate(getHibernateTemplate());
		return usuariDao.findAllActivos();
	}

	/**
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR" 
	 * @ejb.permission role-name="OLI_ESTADMINISTRADOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Long usuariCrear(Usuari usuari) {
		usuariDao.setHibernateTemplate(getHibernateTemplate());
		usuariDao.makePersistent(usuari);
		return usuari.getId();
	}

	/**
	 * Crea un usuari
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR" 
	 * @ejb.permission role-name="OLI_ESTADMINISTRADOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Long usuariCrear(Boolean actiu, String[] grupsArray,
			String idiomaId, String usuario, String password, 
			String email, String observaciones) {
		return usuariCrear(actiu, grupsArray, idiomaId, usuario, password, email, observaciones, null);
	}
	/**
	 * Crea un usuari, i l'assigna a l'establiment corresponent
	 * 
	 * @param actiu
	 * @param grupsArray
	 * @param idiomaId
	 * @param usuario
	 * @param password
	 * @param email
	 * @param observaciones
	 * @param establimentId
	 * @return identificador del nou usuari
	 *  
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR" 
	 * @ejb.permission role-name="OLI_ESTADMINISTRADOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Long usuariCrear(Boolean actiu, String[] grupsArray,
			String idiomaId, String usuario, String password, 
			String email, String observaciones, Long establimentId) {
		Idioma idioma = new Idioma();
		idioma.setId(idiomaId);
		Usuari usuari = new Usuari(idioma, actiu, usuario, password);
		usuari.setEmail(email);
		usuari.setObservacions(observaciones);

		if (grupsArray != null) {
			for (int i = 0; i < grupsArray.length; i++) {
				grupDao.setHibernateTemplate(getHibernateTemplate());
				Grup grupo = grupDao.getById(grupsArray[i]);
				grupo.getUsuaris().add(usuari);
			}
		}
		
		usuariDao.setHibernateTemplate(getHibernateTemplate());
		usuariDao.makePersistent(usuari);
		
		if (establimentId != null){
			Set establiments = new HashSet();
			establimentDao.setHibernateTemplate(getHibernateTemplate());
			Establiment establiment = establimentDao.getById(establimentId); 
			establiment.getUsuaris().add(usuari);
			establimentDao.setHibernateTemplate(getHibernateTemplate());
			establimentDao.makePersistent(establiment);
		}
		return usuari.getId();
	}

	/**
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_ESTADMINISTRADOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public void usuariModificar(Usuari usuari) {
		usuariDao.setHibernateTemplate(getHibernateTemplate());
		usuariDao.makePersistent(usuari);
	}


	/**
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_ESTADMINISTRADOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public void usuariModificar(Boolean actiu, String[] grupsArray,
			String idiomaId, String usuario, String password,
			String email, String observaciones, Long usuarioId) {
		usuariDao.setHibernateTemplate(getHibernateTemplate());
		Usuari usuari = usuariDao.getById(usuarioId);
		usuari.setActiu(actiu);
		usuari.setUsuari(usuario);
		if (password != null) {  // No se modifica
			usuari.setContrasenya(password);
		}
		Idioma idioma = new Idioma();
		idioma.setId(idiomaId);
		usuari.setIdioma(idioma);
		usuari.setEmail(email);
		usuari.setObservacions(observaciones);

		// 20090730: si grupsArray es Null es perque es modificacio feta per un no Gestor, per lo tant, per evitar
		// trapicheos, deixarem els grups que tenia feins ara
		if (grupsArray != null) {
			for (Iterator it = usuari.getGrups().iterator(); it.hasNext();)
				((Grup) it.next()).getUsuaris().remove(usuari);

			for (int i = 0; i < grupsArray.length; i++) {
				grupDao.setHibernateTemplate(getHibernateTemplate());
				Grup grup = grupDao.getById(grupsArray[i]);
				grup.getUsuaris().add(usuari);
			}
		}
		usuariDao.setHibernateTemplate(getHibernateTemplate());
		usuariDao.makePersistent(usuari);
	}

	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" 
	 * @ejb.permission role-name="OLI_ESTADMINISTRADOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Usuari usuariAmbId(Long id) {
		usuariDao.setHibernateTemplate(getHibernateTemplate());
		return usuariDao.getById(id);
	}
	
	/**
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public void trasllatModificar(Trasllat trasllat) {
		trasllatDao.setHibernateTemplate(getHibernateTemplate());
		trasllatDao.makePersistent(trasllat);
	}

	/**
	 * Cerca tots els idiomes <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection idiomaCercaTots() {
		idiomaDao.setHibernateTemplate(getHibernateTemplate());
		return idiomaDao.findAll();
	}

	/**
	 * Cerca totes les taxes <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection taxaCercaTots() {
		taxaDao.setHibernateTemplate(getHibernateTemplate());
		return taxaDao.findAll();
	}

	/**
	 * Cerca la taxa <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Taxa getTaxaActual() {
		taxaDao.setHibernateTemplate(getHibernateTemplate());
		return taxaDao.getTaxaActual();
	}

	/**
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR" <!-- end-xdoclet-definition -->
	 */
	public void taxaModificar(Taxa taxa) {
		taxaDao.setHibernateTemplate(getHibernateTemplate());
		taxaDao.makePersistent(taxa);
	}

	/**
	 * Cerca tots els colors <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection colorCercaTots() {
		colorDao.setHibernateTemplate(getHibernateTemplate());
		return colorDao.findAll();
	}

	/**
	 * Cerca tots els materials de tipus d'envas <!-- begin-xdoclet-definition
	 * -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR" <!-- end-xdoclet-definition -->
	 */
	public Collection materialTipusEnvasCercaTots() {
		materialTipusEnvasDao.setHibernateTemplate(getHibernateTemplate());
		return materialTipusEnvasDao.findAll();
	}

	/**
	 * Cerca tots els tipus d'envasos <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR" <!-- end-xdoclet-definition -->
	 */
	public Collection tipusEnvasCercaTots() {
		tipusEnvasDao.setHibernateTemplate(getHibernateTemplate());
		return tipusEnvasDao.findAll();
	}

	/**
	 * Cerca tots els tipus d'envasos <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR" <!-- end-xdoclet-definition -->
	 */
	public Collection tipusEnvasCercaTotsActius() {
		tipusEnvasDao.setHibernateTemplate(getHibernateTemplate());
		return tipusEnvasDao.findAllActius();
	}

	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR" <!-- end-xdoclet-definition -->
	 */
	public TipusEnvas tipusEnvasAmbId(Long id) {
		tipusEnvasDao.setHibernateTemplate(getHibernateTemplate());
		return tipusEnvasDao.getById(id);
	}

	/**
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR" <!-- end-xdoclet-definition -->
	 */
	public Long tipusEnvasCrear(TipusEnvas tipusEnvas) {
		tipusEnvasDao.setHibernateTemplate(getHibernateTemplate());
		tipusEnvasDao.makePersistent(tipusEnvas);
		return tipusEnvas.getId();
	}

	/**
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR" <!-- end-xdoclet-definition -->
	 */
	public Long tipusEnvasCrear(Integer materialTipusEnvasId, Integer colorId,
			Boolean actiu, Double volum, String observacions) {
		MaterialTipusEnvas materialTipusEnvas = new MaterialTipusEnvas();
		materialTipusEnvas.setId(materialTipusEnvasId);
		Color color = new Color();
		color.setId(colorId);
		TipusEnvas tipusEnvas = new TipusEnvas(materialTipusEnvas, color,
				actiu, volum);
		tipusEnvas.setObservacions(observacions);
		tipusEnvasDao.setHibernateTemplate(getHibernateTemplate());
		tipusEnvasDao.makePersistent(tipusEnvas);
		return tipusEnvas.getId();
	}

	/**
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR" <!-- end-xdoclet-definition -->
	 */
	public Long tipusEnvasCrear(Integer materialTipusEnvasId, Integer colorId,
			Double volum, String observacions) {
		Long id = tipusEnvasCrear(materialTipusEnvasId, colorId, new Boolean(
				true), volum, observacions);
		return id;
	}

	/**
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR" <!-- end-xdoclet-definition -->
	 */
	public void TipusEnvasModificar(TipusEnvas tipusEnvas) {
		tipusEnvasDao.setHibernateTemplate(getHibernateTemplate());
		tipusEnvasDao.makePersistent(tipusEnvas);
	}

	/**
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR" <!-- end-xdoclet-definition -->
	 */
	public void tipusEnvasModificar(Integer materialTipusEnvasId,
			Integer colorId, Boolean actiu, Double volum, String observaciones,
			Long tipusEnvasId) {
		MaterialTipusEnvas materialTipusEnvas = new MaterialTipusEnvas();
		materialTipusEnvas.setId(materialTipusEnvasId);
		Color color = new Color();
		color.setId(colorId);
		TipusEnvas tipusEnvas = new TipusEnvas(materialTipusEnvas, color,
				actiu, volum);
		tipusEnvas.setObservacions(observaciones);
		tipusEnvas.setId(tipusEnvasId);
		tipusEnvasDao.setHibernateTemplate(getHibernateTemplate());
		tipusEnvasDao.makePersistent(tipusEnvas);

		// Usuari usuari = usuariDao.getById(usuarioId);
		// usuari.setActiu(actiu);
		// usuari.setUsuari(usuario);
		// usuari.setContrasenya(password);
		// Idioma idioma = new Idioma();
		// idioma.setId(idiomaId);
		// usuari.setIdioma(idioma);
		// usuari.setObservacions(observaciones);
		//				
		// for (Iterator it = usuari.getGrups().iterator(); it.hasNext();)
		// ((Grup)it.next()).getUsuaris().remove(usuari);
		// if (grupsArray != null) {
		// for (int i = 0; i < grupsArray.length; i++) {
		// Grup grup = grupDao.getById(grupsArray[i]);
		// grup.getUsuaris().add(usuari);
		// }
		// }
		// usuariDao.makePersistent(usuari);
	}

	/**
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR" <!-- end-xdoclet-definition -->
	 */
	public void tipusEnvasModificar(Integer materialTipusEnvasId,
			Integer colorId, Boolean actiu, Double volum, Long tipusEnvaseId) {
		tipusEnvasModificar(materialTipusEnvasId, colorId, actiu, volum, null,
				tipusEnvaseId);
	}

	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR" <!-- end-xdoclet-definition -->
	 * @generated
	 */
	public void tipusEnvasEsborrar(Long id) {
		tipusEnvasDao.setHibernateTemplate(getHibernateTemplate());
		TipusEnvas tipusEnvas = tipusEnvasDao.getById(id);
		if (tipusEnvas != null) {
			tipusEnvasDao.setHibernateTemplate(getHibernateTemplate());
			tipusEnvasDao.makeTransient(tipusEnvas);
		}
	}

	/**
	 * Cerca tots els tipus d'establiments <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection tipusEstablimentCercaTots() {
		tipusEstablimentDao.setHibernateTemplate(getHibernateTemplate());
		return tipusEstablimentDao.findAll();
	}

	/**
	 * Comprueba si existe un usuario ya en BBDD <!-- begin-xdoclet-definition
	 * -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public boolean existeNombre(String nombre, Long userId) {
		usuariDao.setHibernateTemplate(getHibernateTemplate());
		Long user = usuariDao.getUsuari(nombre);
		if (user != null) {
			if (userId != null) {
				if (user.equals(userId)) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	
	/**
	 * Comprueba si existen olivicultores altaDO en la BBDD <!--
	 * begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public boolean existenOlivicultoresAltaDOConFincas() {
		olivicultorDao.setHibernateTemplate(getHibernateTemplate());
		return olivicultorDao.existenOlivicultoresAltaDOConFincas().booleanValue();
	}

	
	/**
	 * Comprueba si existen fincas asociadas a olivicultores en la BBDD, para
	 * validar el borrado. <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public boolean existenFincasAsociadasOlivicultores(Long idOlivicultor) {
		fincasDao.setHibernateTemplate(getHibernateTemplate());
		return fincasDao.existenFincasAsociadasOlivicultores(idOlivicultor);
	}

	/**
	 * Comprueba si existen plantaciones asociadas a fincas en la BBDD, para
	 * validar el borrado. <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public boolean existenPlantacionesAsociadasFincas(Long idFinca) {
		plantacioDao.setHibernateTemplate(getHibernateTemplate());
		return plantacioDao.existenPlantacionesAsociadasFincas(idFinca);
	}

	
	/**
	 * Devuelve el olivicultor de una finca
	 * validar el borrado. <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Long findOlivicultorDeFinca(Long idFinca) {
		fincasDao.setHibernateTemplate(getHibernateTemplate());
		return fincasDao.findOlivicultorDeFinca(idFinca);
	}
	
	
	
	
	
	/**
	 * Comprueba si existen olivicultores asociados a usuarios en la BBDD, para
	 * validar el borrado. <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public boolean existenOlivicultoresAsociadosUsuarios(Long idUsu) {
		olivicultorDao.setHibernateTemplate(getHibernateTemplate());
		return olivicultorDao.existenOlivicultoresAsociadosUsuarios(idUsu);
	}

	/**
	 * Comprueba si existen establecimientos asociados a usuarios en la BBDD,
	 * para validar el borrado. <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public boolean existenEstablecimientosAsociadosUsuarios(Long idUsu) {
		establimentDao.setHibernateTemplate(getHibernateTemplate());
		return establimentDao.existenEstablecimientosAsociadosUsuarios(idUsu);
	}

	/**
	 * Comprueba si existen establecimientos asociados a usuarios en la BBDD,
	 * para validar el borrado. <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public boolean existenZonasAsociadasEstablecimientos(Long idEstabliment) {
		zonaDao.setHibernateTemplate(getHibernateTemplate());
		return zonaDao.existenZonasAsociadasEstablecimientos(idEstabliment);
	}

	/**
	 * Comprueba si existen depósitos asociados a zonas en la BBDD, para validar
	 * el borrado. <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public boolean existenDepositosAsociadosZonas(Long idZona) {
		dipositDao.setHibernateTemplate(getHibernateTemplate());
		return dipositDao.existenDepositosAsociadosZonas(idZona);
	}

	/**
	 * Comprueba si existen partidas de oliva asociadas a zonas en la BBDD, para validar
	 * el borrado. <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public boolean existenPartidasAsociadasZonas(Long idZona) {
		partidaOlivaDao.setHibernateTemplate(getHibernateTemplate());
		return partidaOlivaDao.existenPartidasAsociadasZonas(idZona, new Boolean(true));
	}
	
	/**
	 * Totes les partides d'oli
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection findPartidesOlivaByEstablimentEntreDates(Long idEstabliment, Date dataInici, Date dataFi, Boolean valid) {
		partidaOlivaDao.setHibernateTemplate(getHibernateTemplate());
		return partidaOlivaDao.findByEstablimentEntreDates(idEstabliment, dataInici, dataFi, valid);
	}
	
	/**
	 * Totes les partides d'oli
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection findPartidesOlivaTaulaByEstablimentEntreDates(Long idEstabliment, Date dataInici, Date dataFi, Boolean valid) {
		partidaOlivaDao.setHibernateTemplate(getHibernateTemplate());
		return partidaOlivaDao.findEntradaOlivaTaulaByEstablimentEntreDates(idEstabliment, dataInici, dataFi, valid);
	}
	
	/**
	 * Totes les partides d'oli
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection findPartidesOlivaByEstabliment(Long idEstabliment, Boolean valid) {
		partidaOlivaDao.setHibernateTemplate(getHibernateTemplate());
		return partidaOlivaDao.findByEstabliment(idEstabliment, valid);
	}
	
	/**
	 * Comprueba si existen lotes asociados a zonas en la BBDD, para validar
	 * el borrado. <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public boolean existenLotesAsociadosZonas(Long idZona) {
		lotDao.setHibernateTemplate(getHibernateTemplate());
		return lotDao.existenLotesAsociadosZonas(idZona, new Boolean(true));
	}
	
	/**
	 * Comprueba si existen entradas de lote asociadas a zonas en la BBDD, para validar
	 * el borrado. <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public boolean existenEntradasLoteAsociadasZonas(Long idZona) {
		entradaLotDao.setHibernateTemplate(getHibernateTemplate());
		return entradaLotDao.existenEntradasLoteAsociadasZonas(idZona, new Boolean(true));
	}
	
	/**
	 * Comprueba si existen salidas de lote asociadas a zonas en la BBDD, para validar
	 * el borrado. <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public boolean existenSalidasLoteAsociadasZonas(Long idZona) {
		sortidaLotDao.setHibernateTemplate(getHibernateTemplate());
		return sortidaLotDao.existenSalidasLoteAsociadasZonas(idZona, new Boolean(true));
	}
	
	/**
	 * Comprueba si existen entradas de lote asociadas al lote en la BBDD, para validar
	 * el borrado. <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public boolean existeixenEntradesLotAssociadesLot(Long idLot) {
		entradaLotDao.setHibernateTemplate(getHibernateTemplate());
		return entradaLotDao.existeixenEntradesLotAssociadesLot(idLot, new Boolean(true));
	}
	
	/**
	 * Comprueba si existen salidas de lote asociadas a zonas en la BBDD, para validar
	 * el borrado. <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public boolean existeixenSortidesLotAssociadesLot(Long idLot) {
		sortidaLotDao.setHibernateTemplate(getHibernateTemplate());
		return sortidaLotDao.existeixenSortidesLotAssociadesLot(idLot, new Boolean(true));
	}
	
	/**
	 * Comprueba si existen salidas de lote asociadas a zonas en la BBDD, para validar
	 * el borrado. <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public boolean existenEntradasDepositoAsociadasDeposito(Long idDiposit) {
		entradaDipositDao.setHibernateTemplate(getHibernateTemplate());
		return entradaDipositDao.existenEntradasDepositoAsociadasDeposito(idDiposit, new Boolean(true));
	}
	
	/**
	 * Comprueba si existen salidas de lote asociadas a zonas en la BBDD, para validar
	 * el borrado. <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public boolean existenAnaliticasAsociadasDeposito(Long idDiposit) {
		analiticaDao.setHibernateTemplate(getHibernateTemplate());
		return analiticaDao.existenAnaliticasAsociadasDeposito(idDiposit);
	}
	
	/**
	 * Comprueba si existen salidas de lote asociadas a zonas en la BBDD, para validar
	 * el borrado. <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public boolean existenSalidasDepositoAsociadasDeposito(Long idDiposit) {
		sortidaDipositDao.setHibernateTemplate(getHibernateTemplate());
		return sortidaDipositDao.existenSalidasDepositoAsociadasDeposito(idDiposit, new Boolean(true));
	}
	
	/**
	 * Comprueba si existen salidas de lote asociadas a zonas en la BBDD, para validar
	 * el borrado. <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
    public boolean existenTrasladosAsociadosDeposito(Long idDiposit) {
    	trasllatDao.setHibernateTemplate(getHibernateTemplate());
		return trasllatDao.existenTrasladosAsociadosDeposito(idDiposit, new Boolean(true));
    }
    
    /**
	 * Comprueba si existen salidas de lote asociadas a zonas en la BBDD, para validar
	 * el borrado. <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
    public boolean existenLotesAsociadosDeposito(Long idDiposit) {
    	lotDao.setHibernateTemplate(getHibernateTemplate());
		return lotDao.existenLotesAsociadosDeposito(idDiposit, new Boolean(true));
    }
	
	
	/**
	 * Comprueba si existen etiquetajes asociados a tipos de envase en la BBDD,
	 * para validar el borrado. <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public boolean existenEtiquetajesAsociadosTiposEnvase(Long idTipEnv) {
		etiquetatgeDao.setHibernateTemplate(getHibernateTemplate());
		return etiquetatgeDao.existenEtiquetajesAsociadosTiposEnvase(idTipEnv);
	}

	/**
	 * Comprueba si existe un establecimiento ya en BBDD <!--
	 * begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public boolean existeEstablecimiento(String nombre, Long estId) {
		establimentDao.setHibernateTemplate(getHibernateTemplate());
		Long est = establimentDao.getEstablecimiento(nombre);
		if (est != null) {
			if (estId != null) {
				if (est.equals(estId)) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	/**
	 * Comprueba si existe un cif ya en BBDD <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public boolean existeCif(String nombre, Long estId) {
		establimentDao.setHibernateTemplate(getHibernateTemplate());
		Long cif = establimentDao.getCif(nombre);
		if (cif != null) {
			if (estId != null) {
				if (cif.equals(estId)) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	/**
	 * Comprueba si existe un cif y nombre ya en BBDD <!--
	 * begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public boolean establimentExisteCifName(Long campanyaId, String cif,
			String name, Long estId) {
		establimentDao.setHibernateTemplate(getHibernateTemplate());
		Long nombrecif = establimentDao.getCifName(campanyaId, cif, name);

		if (nombrecif != null) {
			if (estId != null) {
				if (nombrecif.equals(estId)) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	/**
	 * Comprueba si existe un nif y nombre ya en BBDD <!--
	 * begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public boolean existeNifName(Long campanyaId, String nif, String name,
			Long estId) {
		olivicultorDao.setHibernateTemplate(getHibernateTemplate());
		Long nombrenif = olivicultorDao.getNifName(campanyaId, nif, name);

		if (nombrenif != null) {
			if (estId != null) {
				if (nombrenif.equals(estId)) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	//
	// /**
	// * Comprueba si existe ya el nombre de una finca para un mismo olivicultor
	// * <!-- begin-xdoclet-definition -->
	// * @ejb.interface-method view-type="both"
	// * @ejb.permission role-name="OLI_ADMINISTRACIO"
	// * @ejb.permission role-name="OLI_DOGESTOR"
	// * @ejb.permission role-name="OLI_DOCONTROLADOR"
	// * @ejb.permission role-name="OLI_OLIVICULTOR"
	// * @ejb.permission role-name="OLI_PRODUCTOR"
	// * @ejb.permission role-name="OLI_ENVASADOR"
	// * <!-- end-xdoclet-definition -->
	// */
	// public Boolean existeNombreFinca(String nombreFinca, String
	// idOlivicultor, Long fincaId){
	// if (fincaId != null)
	// return fincasDao.getNombreFinca(nombreFinca,idOlivicultor,
	// fincaId.toString());
	// else
	// return fincasDao.getNombreFinca(nombreFinca,idOlivicultor, null);
	// }

	/**
	 * Comprueba si existe un nif y nombre ya en BBDD <!--
	 * begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public boolean existeNombreFinca(String nombreFinca, String idOlivicultor,
			Long fincaId) {
		fincasDao.setHibernateTemplate(getHibernateTemplate());
		Long nombrefinca = fincasDao.getFincaName(nombreFinca, idOlivicultor);

		if (nombrefinca != null) {
			if (fincaId != null) {
				if (nombrefinca.equals(fincaId)) {
					return false;
				}
			}
			return true;
		}
		return false;
	}


	/**
	 * Comprueba si existe un CodiDO ya en BBDD
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public boolean existeCodiDO(Long campanyaId, String codiDO, Long estId) {
		olivicultorDao.setHibernateTemplate(getHibernateTemplate());
		Long codigo = olivicultorDao.existeCodiDO(campanyaId, codiDO);
		if (codigo != null) {
			if (estId != null) {
				if (codigo.equals(estId)) {
					return false;
				}
			}
			return true;
		}
		return false;
	}


	/**
	 * Comprueba si existe un CodiDOExperimental ya en BBDD
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public boolean existeCodiDOExperimental(Long campanyaId, String codiDOExperimental, Long estId) {
		olivicultorDao.setHibernateTemplate(getHibernateTemplate());
		Long codigo = olivicultorDao.existeCodiDOExperimental(campanyaId, codiDOExperimental);
		if (codigo != null) {
			if (estId != null) {
				if (codigo.equals(estId)) {
					return false;
				}
			}
			return true;
		}
		return false;
	}


	/**
	 * Comprueba si existe un CodiRB ya en BBDD
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public boolean existeCodiRB(Long campanyaId, String codiRB, Long estId) {
		establimentDao.setHibernateTemplate(getHibernateTemplate());
		Long codigo = establimentDao.existeCodiRB(campanyaId, codiRB);
		if (codigo != null) {
			if (estId != null) {
				if (codigo.equals(estId)) {
					return false;
				}
			}
			return true;
		}
		return false;
	}


	/**
	 * Comprueba si existe un CodiRC ya en BBDD
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public boolean existeCodiRC(Long campanyaId, String codiRC, Long estId) {
		establimentDao.setHibernateTemplate(getHibernateTemplate());
		Long codigo = establimentDao.existeCodiRC(campanyaId, codiRC);
		if (codigo != null) {
			if (estId != null) {
				if (codigo.equals(estId)) {
					return false;
				}
			}
			return true;
		}
		return false;
	}


	/**
	 * Comprueba si el usuario ya esta asignado a algun olivicultor <!--
	 * begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR" <!-- end-xdoclet-definition -->
	 */
	public boolean usuarioAsignadoOlivicultor(Long campanyaId,
			Long usuarioId, Long estId) {
		olivicultorDao.setHibernateTemplate(getHibernateTemplate());
		Long codigo = olivicultorDao.usuarioAsignado(campanyaId, usuarioId);

		if (codigo != null) {
			if (estId != null) {
				if (codigo.equals(estId)) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	/**
	 * Comprueba si el usuario ya esta asignado a algun establecimiento <!--
	 * begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public boolean usuarioAsignadoEstablecimiento(Long campanyaId,
			Long usuarioId, Long estId) {
		establimentDao.setHibernateTemplate(getHibernateTemplate());
		Long codigo = establimentDao.usuarioAsignado(campanyaId, usuarioId);

		if (codigo != null) {
			if (estId != null) {
				if (codigo.equals(estId)) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	/**
	 * Comprueba si existe un tipo de envase igual ya en BBDD <!--
	 * begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR" <!-- end-xdoclet-definition -->
	 */
	public boolean existeTipusEnvas(Integer materialTipusEnvas, Integer color,
			Double volum, Long tipusEnvasId) {
		Long tipoEnvase = null;

		if (materialTipusEnvas != null && color != null && volum != null) {
			tipusEnvasDao.setHibernateTemplate(getHibernateTemplate());
			tipoEnvase = tipusEnvasDao.getTipusEnvas(materialTipusEnvas, color,
					volum);
		}
		if (tipoEnvase != null) {
			if (tipusEnvasId != null) {
				if (tipoEnvase.equals(tipusEnvasId)) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	/**
	 * Comprueba si se modifica de activo a no activo, no debe tener ningún
	 * etiquetaje asociado <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR" <!-- end-xdoclet-definition -->
	 */
	public boolean existenEtiquetatgesActiusEnTipusEnvas(Long tipusEnvasId) {
		tipusEnvasDao.setHibernateTemplate(getHibernateTemplate());
		return tipusEnvasDao.existenEtiquetatgesActiusEnTipusEnvas(tipusEnvasId);
	}

	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR" 
	 * <!-- end-xdoclet-definition -->
	 */
	public void usuariEsborrar(Long id) {
		usuariDao.setHibernateTemplate(getHibernateTemplate());
		Usuari usuari = usuariDao.getById(id);
		if (usuari != null) {
			
			// Eliminam les referències a establiments
			Set establiments = usuari.getEstabliments();
			for (Iterator it = establiments.iterator(); it.hasNext();){
				Establiment establiment = (Establiment)it.next();
				establiment.getUsuaris().remove(usuari);
			}
			//-------------------------------------------------------
			
			usuariDao.setHibernateTemplate(getHibernateTemplate());
			usuariDao.makeTransient(usuari);
		}
	}

	/**
	 * Cerca tots les marques <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection marcaCercaTots() {
		marcaDao.setHibernateTemplate(getHibernateTemplate());
		return marcaDao.findAll();
	}

	/**
	 * Cerca tots els marques activas <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection marcaCercaTotsActivos() {
		marcaDao.setHibernateTemplate(getHibernateTemplate());
		return marcaDao.findAllActivos();
	}

	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR" <!-- end-xdoclet-definition -->
	 */
	public void marcaEsborrar(Long id) {
		marcaDao.setHibernateTemplate(getHibernateTemplate());
		Marca marca = marcaDao.getById(id);
		if (marca != null) {
			//1º eliminamos los etiquetages asociados a la marca asi como las imagenes asociadas a dichos etiquetatges
			Set etiquetatges = marca.getEtiquetatges();
			for (Iterator it = etiquetatges.iterator(); it.hasNext();){
				Etiquetatge etiquetatge = (Etiquetatge)it.next();
				//Borramos las imagenes asociadas al etiquetatge
				if(etiquetatge.getImatgeFrontal()!= null){
					arxiuDao.setHibernateTemplate(getHibernateTemplate());
					Arxiu imagenFrontalArxiu = arxiuDao.getById(etiquetatge.getImatgeFrontal());
					if (imagenFrontalArxiu != null) {
						arxiuDao.setHibernateTemplate(getHibernateTemplate());
						arxiuDao.makeTransient(imagenFrontalArxiu);
					}
				}
				if(etiquetatge.getImatgePosterior()!= null){
					arxiuDao.setHibernateTemplate(getHibernateTemplate());
					Arxiu imagenPosteriorArxiu = arxiuDao.getById(etiquetatge.getImatgePosterior());
					if (imagenPosteriorArxiu != null) {
						arxiuDao.setHibernateTemplate(getHibernateTemplate());
						arxiuDao.makeTransient(imagenPosteriorArxiu);
					}
				}
				//Borramos el etiquetatge
				etiquetatgeDao.setHibernateTemplate(getHibernateTemplate());
				etiquetatgeDao.makeTransient(etiquetatge);
			}
			marcaDao.setHibernateTemplate(getHibernateTemplate());
			marcaDao.makeTransient(marca);
		}
	}

	/**
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR" <!-- end-xdoclet-definition -->
	 */
	public void marcaCrear(Marca marca) {
		marcaDao.setHibernateTemplate(getHibernateTemplate());
		marcaDao.makePersistent(marca);
	}

	/**
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR" <!-- end-xdoclet-definition -->
	 */
	public Long marcaCrear(String nombre, Boolean actiu,
			Boolean denominacioOrigen, Boolean olivaTaula, List etiquetatgesList,
			String observacions) {
		
		Long marcaId = null;
		List etiquetatges = new ArrayList();
		
		//1º- RECORREMOS LOS ETIQUETAGES Y DAMOS DE ALTA SUS IMAGENES FRONTAL Y POSTERIOR EN BBDD
		//ASOCIANDO POSTERIORMENTE A CADA ETIQUETATGE SUS ID CORRESPONDIENTES DE IMAGEN FRONTAL Y POSTERIOR
		for (int i = 0; i < etiquetatgesList.size(); i++) {
			
			EtiquetatgeAux etiquetatgeAux = new EtiquetatgeAux();
			Etiquetatge etiquetatge = new Etiquetatge();
			
			etiquetatgeAux = (EtiquetatgeAux)etiquetatgesList.get(i);
			etiquetatge.setActiu(etiquetatgeAux.getActiu());
			etiquetatge.setTipusEnvas(etiquetatgeAux.getTipusEnvas());
			etiquetatge.setObservacions(etiquetatgeAux.getObservacions());			
			//ImagenFrontal
			Arxiu imagenFrontalArxiu = null;
			if (etiquetatgeAux.getArxiucImatgeFrontal()!= null) {
				imagenFrontalArxiu = new Arxiu();
				imagenFrontalArxiu = etiquetatgeAux.getArxiucImatgeFrontal();
				imagenFrontalArxiu.setTamany(new Integer(etiquetatgeAux.getArxiucImatgeFrontal().getBinari().length));
				arxiuDao.setHibernateTemplate(getHibernateTemplate());
				arxiuDao.makePersistent(imagenFrontalArxiu);
			}
			etiquetatge.setImatgeFrontal(imagenFrontalArxiu.getId());
			//ImagenPosterior
			Arxiu imagenPosteriorArxiu = null;
			if (etiquetatgeAux.getArxiucImatgePosterior()!= null) {
				imagenPosteriorArxiu = new Arxiu();
				imagenPosteriorArxiu = etiquetatgeAux.getArxiucImatgePosterior();
				imagenPosteriorArxiu.setTamany(new Integer(etiquetatgeAux.getArxiucImatgePosterior().getBinari().length));
				arxiuDao.setHibernateTemplate(getHibernateTemplate());
				arxiuDao.makePersistent(imagenPosteriorArxiu);
			}
			if (imagenPosteriorArxiu != null) {
				etiquetatge.setImatgePosterior(imagenPosteriorArxiu.getId());
			}
			
			etiquetatges.add(etiquetatge);
		}
		//2º-DAMOS DE ALTA LA MARCA EN BBDD
		
		if (denominacioOrigen == null) {
			denominacioOrigen = new Boolean(false);
		}
		if (olivaTaula == null) {
			olivaTaula = new Boolean(false);
		}
		Marca marca = new Marca(actiu, nombre, denominacioOrigen);
		marca.setOlivaTaula(olivaTaula);
		marca.setObservacions(observacions);
		marca.setDataAlta(Calendar.getInstance().getTime());
		marcaDao.setHibernateTemplate(getHibernateTemplate());
		marcaDao.makePersistent(marca);
		marcaId = marca.getId();
		//3º-RECORREMOS LOS ETIQUETAGES Y LOS DAMOS DE ALTA EN BBDD
		for (int i = 0; i < etiquetatges.size(); i++) {
			Etiquetatge etiquetatge = (Etiquetatge)etiquetatges.get(i);
			etiquetatge.setMarca(marca);
			etiquetatgeDao.setHibernateTemplate(getHibernateTemplate());
			etiquetatgeDao.makePersistent(etiquetatge);
		}
				
		return marcaId;
	}
	
	/**
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR" <!-- end-xdoclet-definition -->
	 */
	public void marcaModificar(Marca marca) {
		marcaDao.setHibernateTemplate(getHibernateTemplate());
		marcaDao.makePersistent(marca);
	}

	/**
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR" <!-- end-xdoclet-definition -->
	 */
	public Collection getEstablecimientosByMarca(Long marcaId) {
		Collection establecimientos;			
		establimentDao.setHibernateTemplate(getHibernateTemplate());
		establecimientos = establimentDao.getEstablecimientosByMarca(marcaId);		
		return establecimientos;	
	}
	
	
	/**
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR" <!-- end-xdoclet-definition -->
	 */
	public void marcaModificar(Long marcaId, String nombre, Boolean actiu,
			Boolean denominacioOrigen, Boolean olivaTaula, List etiquetatgesList, Collection establiments,
			String observacions) {
				
		//1º-ACTUALIZAMOS LOS DATOS BASICOS MARCA EN BBDD
				
		if (denominacioOrigen == null) {
			denominacioOrigen = new Boolean(false);
		}
		if (olivaTaula == null) {
			olivaTaula = new Boolean(false);
		}
		Marca marca = null;
		if (marcaId != null) {
			marca = marcaDao.getById(marcaId);
			marca.setActiu(actiu);
			marca.setNom(nombre);
			marca.setDenominacioOrigen(denominacioOrigen);
		} else {
			marca = new Marca(actiu, nombre, denominacioOrigen);
		}
		marca.setOlivaTaula(olivaTaula);
		marca.setObservacions(observacions);
		
		if(establiments!= null){
			Set est = new HashSet();
			est.addAll(establiments);
			marca.setEstabliments(est);
		}
		
		if (!marca.getActiu()) {
			marca.setDataBaixa(Calendar.getInstance().getTime());
		}
		
		marcaDao.setHibernateTemplate(getHibernateTemplate());
		marcaDao.makePersistent(marca);
		
		//2º- RECORREMOS LOS ETIQUETAGES Y BORRAMOS LAS IMAGENES OBSOLETAS EN BBDD
		
		List etiquetatges = new ArrayList();
		List imagenesTratadas = new ArrayList();//Utilizamos una lista para guardar todos los ids de las imagenes que vamos creando y borrando
		if(etiquetatgesList.size()>0){
			for(int i=0;i<etiquetatgesList.size();i++){
				EtiquetatgeAux etiquetatgeAux = new EtiquetatgeAux();
				etiquetatgeAux = (EtiquetatgeAux)etiquetatgesList.get(i);
				
				Etiquetatge etiquetatge = new Etiquetatge();
				etiquetatge.setActiu(etiquetatgeAux.getActiu());
				etiquetatge.setTipusEnvas(etiquetatgeAux.getTipusEnvas());
				etiquetatge.setObservacions(etiquetatgeAux.getObservacions());
				etiquetatge.setId(etiquetatgeAux.getId());
				etiquetatge.setMarca(marca);
				//IMAGEN FRONTAL				
				if(etiquetatgeAux.getImatgeFrontal()!= null){
					if(etiquetatgeAux.getArxiucImatgeFrontal()!= null){
						//Borramos la imagen antigua
						arxiuDao.setHibernateTemplate(getHibernateTemplate());
						Arxiu imagenFrontalArxiu = arxiuDao.getById(etiquetatgeAux.getImatgeFrontal());
						if (imagenFrontalArxiu != null) {
							arxiuDao.setHibernateTemplate(getHibernateTemplate());
							arxiuDao.makeTransient(imagenFrontalArxiu);
						}
						imagenesTratadas.add(etiquetatgeAux.getImatgeFrontal());
						//Insertamos la nueva
						imagenFrontalArxiu = new Arxiu();
						imagenFrontalArxiu = etiquetatgeAux.getArxiucImatgeFrontal();
						imagenFrontalArxiu.setTamany(new Integer(etiquetatgeAux.getArxiucImatgeFrontal().getBinari().length));
						arxiuDao.setHibernateTemplate(getHibernateTemplate());
						arxiuDao.makePersistent(imagenFrontalArxiu);
						etiquetatge.setImatgeFrontal(imagenFrontalArxiu.getId());
						imagenesTratadas.add(imagenFrontalArxiu.getId());
					}else{
						etiquetatge.setImatgeFrontal(etiquetatgeAux.getImatgeFrontal());
						imagenesTratadas.add(etiquetatgeAux.getImatgeFrontal());
					}					
				}else if(etiquetatgeAux.getArxiucImatgeFrontal()!= null){
					//Insertamos la nueva
					Arxiu imagenFrontalArxiu = new Arxiu();
					imagenFrontalArxiu = etiquetatgeAux.getArxiucImatgeFrontal();
					imagenFrontalArxiu.setTamany(new Integer(etiquetatgeAux.getArxiucImatgeFrontal().getBinari().length));
					arxiuDao.setHibernateTemplate(getHibernateTemplate());
					arxiuDao.makePersistent(imagenFrontalArxiu);
					etiquetatge.setImatgeFrontal(imagenFrontalArxiu.getId());
					imagenesTratadas.add(imagenFrontalArxiu.getId());
				}
				//	IMAGEN POSTERIOR
				if(etiquetatgeAux.getImatgePosterior()!= null){
					if(etiquetatgeAux.getArxiucImatgePosterior()!= null){
						//Borramos la imagen antigua
						arxiuDao.setHibernateTemplate(getHibernateTemplate());
						Arxiu imagenPosteriorArxiu = arxiuDao.getById(etiquetatgeAux.getImatgePosterior());
						if (imagenPosteriorArxiu != null) {
							arxiuDao.setHibernateTemplate(getHibernateTemplate());
							arxiuDao.makeTransient(imagenPosteriorArxiu);
						}
						imagenesTratadas.add(etiquetatgeAux.getImatgePosterior());
						//Insertamos la nueva
						imagenPosteriorArxiu = new Arxiu();
						imagenPosteriorArxiu = etiquetatgeAux.getArxiucImatgePosterior();
						imagenPosteriorArxiu.setTamany(new Integer(etiquetatgeAux.getArxiucImatgePosterior().getBinari().length));
						arxiuDao.setHibernateTemplate(getHibernateTemplate());
						arxiuDao.makePersistent(imagenPosteriorArxiu);
						etiquetatge.setImatgePosterior(imagenPosteriorArxiu.getId());
						imagenesTratadas.add(imagenPosteriorArxiu.getId());
					}else{
						etiquetatge.setImatgePosterior(etiquetatgeAux.getImatgePosterior());
						imagenesTratadas.add(etiquetatgeAux.getImatgePosterior());
					}
				}else if(etiquetatgeAux.getArxiucImatgePosterior()!= null){
					//Insertamos la nueva
					Arxiu imagenPosteriorlArxiu = new Arxiu();
					imagenPosteriorlArxiu = etiquetatgeAux.getArxiucImatgePosterior();
					imagenPosteriorlArxiu.setTamany(new Integer(etiquetatgeAux.getArxiucImatgePosterior().getBinari().length));
					arxiuDao.setHibernateTemplate(getHibernateTemplate());
					arxiuDao.makePersistent(imagenPosteriorlArxiu);
					etiquetatge.setImatgePosterior(imagenPosteriorlArxiu.getId());
					imagenesTratadas.add(imagenPosteriorlArxiu.getId());
				}
								
				
				etiquetatges.add(etiquetatge);
			}						
		}
				
		
		
		// Comprobamos si se ha borrado algún etiquetaje que se pueda borrar y modificamos y añadimos los nuevos
		// Terminar de borrar las imagenes obsoletas, seran aquellas que no estan en la lista "imagenesTratadas"
		
		List etiquetatgesListAntiguos = new ArrayList();
		etiquetatgesListAntiguos = etiquetatgeByMarca(marcaId);
		List imagenesEtiquetatgesListAntiguos = new ArrayList();		
		if(etiquetatgesListAntiguos.size()>0){
			for(int i=0;i<etiquetatgesListAntiguos.size();i++){
				Etiquetatge etiquetatge = (Etiquetatge)etiquetatgesListAntiguos.get(i);
				if(etiquetatge.getImatgeFrontal()!= null){
					imagenesEtiquetatgesListAntiguos.add(etiquetatge.getImatgeFrontal());
				}
				if(etiquetatge.getImatgePosterior()!= null){
					imagenesEtiquetatgesListAntiguos.add(etiquetatge.getImatgePosterior());
				}
				
				boolean borrar = true;
				Etiquetatge eti = null;
				for (int x = 0; x < etiquetatges.size(); x++) {
					eti = (Etiquetatge)etiquetatges.get(x);
					if (eti.getId() != null && eti.getId().equals(etiquetatge.getId())) {
						borrar = false;
						break;
					}
				}
				
				if (borrar) { // Si l'etiquetatge antic no es a la llista, el borra (Es pot borrar perquè ja hem comprovat abans)
					etiquetatgeDao.setHibernateTemplate(getHibernateTemplate());
					etiquetatgeDao.makeTransient(etiquetatge);
				} else { // Modificamos
					Etiquetatge etiquetage = new Etiquetatge();
					etiquetatgeDao.setHibernateTemplate(getHibernateTemplate());
					etiquetage = etiquetatgeDao.getById(eti.getId());
					etiquetage.setActiu(eti.getActiu());
					etiquetage.setId(eti.getId());
					etiquetage.setImatgeFrontal(eti.getImatgeFrontal());
					etiquetage.setImatgePosterior(eti.getImatgePosterior());
					etiquetage.setMarca(eti.getMarca());
					etiquetage.setObservacions(eti.getObservacions());
					etiquetage.setTipusEnvas(eti.getTipusEnvas());
					
					etiquetatgeDao.setHibernateTemplate(getHibernateTemplate());
					etiquetatgeDao.makePersistent(etiquetage);
				}
			}
		}
		
		for(int i=0; i<imagenesEtiquetatgesListAntiguos.size();i++){
			if(!imagenesTratadas.contains(imagenesEtiquetatgesListAntiguos.get(i))){
				//Borramos la imagen antigua
				arxiuDao.setHibernateTemplate(getHibernateTemplate());
				Arxiu imagen = arxiuDao.getById((Long)imagenesEtiquetatgesListAntiguos.get(i));
				if (imagen != null) {
					arxiuDao.setHibernateTemplate(getHibernateTemplate());
					arxiuDao.makeTransient(imagen);
				}
			}
		}
		
		//3º-RECORREMOS LOS ETIQUETAGES Y LOS DAMOS DE ALTA EN BBDD (en caso de no tener id)
		
		for (int i = 0; i < etiquetatges.size(); i++) {
			Etiquetatge etiquetatge = (Etiquetatge)etiquetatges.get(i);
			if (etiquetatge.getId() == null) {
				etiquetatgeDao.setHibernateTemplate(getHibernateTemplate());
				etiquetatgeDao.makePersistent(etiquetatge);
			}
		}
		
	}

	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" 
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Marca marcaAmbId(Long id) {
		marcaDao.setHibernateTemplate(getHibernateTemplate());
		return marcaDao.getById(id);
	}

	/**
	 * Comprueba si existe una marca con ese nombre ya en BBDD <!--
	 * begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR" <!-- end-xdoclet-definition -->
	 */
	public boolean existeMarca(String nombre, Long id) {
		marcaDao.setHibernateTemplate(getHibernateTemplate());
		Long marcaId = marcaDao.getMarca(nombre);
		if (marcaId != null) {
			if (id != null) {
				if (marcaId.equals(id)) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	/**
	 * Cerca tots les etiquetatges <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection etiquetatgeCercaTots() {
		etiquetatgeDao.setHibernateTemplate(getHibernateTemplate());
		return etiquetatgeDao.findAll();
	}

	/**
	 * Cerca tots les etiquetatges <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_DOGESTOR" <!-- end-xdoclet-definition -->
	 */
	public List etiquetatgeByMarca(Long idMarca) {
		etiquetatgeDao.setHibernateTemplate(getHibernateTemplate());
		return etiquetatgeDao.getEtiquetatgeByMarca(idMarca);
	}
	
	/**
	 * Cerca tots les etiquetatges <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public List etiquetatgeByEstabliment(Long establimentId) {
		etiquetatgeDao.setHibernateTemplate(getHibernateTemplate());
		return etiquetatgeDao.findByEstabliment(establimentId);
	}

	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 * @generated
	 */
	public void etiquetatgeEsborrar(Long id) {
		etiquetatgeDao.setHibernateTemplate(getHibernateTemplate());
		Etiquetatge etiquetatge = etiquetatgeDao.getById(id);
		if (etiquetatge != null) {
			etiquetatgeDao.setHibernateTemplate(getHibernateTemplate());
			etiquetatgeDao.makeTransient(etiquetatge);
		}
	}

	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Etiquetatge etiquetatgeAmbId(Long id) {
		etiquetatgeDao.setHibernateTemplate(getHibernateTemplate());
		return etiquetatgeDao.getById(id);
	}

	/**
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public void etiquetatgeCrear(Etiquetatge etiquetatge) {
		etiquetatgeDao.setHibernateTemplate(getHibernateTemplate());
		etiquetatgeDao.makePersistent(etiquetatge);
	}

	/**
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public void etiquetatgeCrear(Long tipusEnvasId, Long marcaId,
			Boolean actiu, Long imatgeFrontal, Long imatgePosterior,
			String observacions) {

		TipusEnvas tipusEnvas = new TipusEnvas();
		tipusEnvas.setId(tipusEnvasId);
		Marca marca = new Marca();
		marca.setId(marcaId);
		Etiquetatge etiquetatge = new Etiquetatge(tipusEnvas, marca, actiu,
				imatgeFrontal, imatgePosterior, observacions);
		etiquetatgeDao.setHibernateTemplate(getHibernateTemplate());
		etiquetatgeDao.makePersistent(etiquetatge);
	}

	/**
	 * Cerca tots les establiments <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection establimentCercaTots() {
		establimentDao.setHibernateTemplate(getHibernateTemplate());
		return establimentDao.findAll();
	}
	
	/**
	 * Cerca tots les establiments <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection establimentCercaTotsEliminats() {
		establimentDao.setHibernateTemplate(getHibernateTemplate());
		return establimentDao.findAllEliminats();
	}

	/**
	 * Cerca tots els establecimientos per campanya <!--
	 * begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR" <!-- end-xdoclet-definition -->
	 */
	public Collection establimentCercaTotsByCampanya(Long campanyaId) {
		establimentDao.setHibernateTemplate(getHibernateTemplate());
		return establimentDao.findAllByCampanya(campanyaId);
	}
	
	/**
	 * Cerca tots els establecimientos tafona activos per campanya <!--
	 * begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR" <!-- end-xdoclet-definition -->
	 */
	public Long numTafonasByCampanya(Long campanyaId) {
		establimentDao.setHibernateTemplate(getHibernateTemplate());
		return establimentDao.numTafonasByCampanya(campanyaId);
	}
	
	/**
	 * Cerca tots els establecimientos tafona activos productors d'oliva de taula per campanya <!--
	 * begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR" <!-- end-xdoclet-definition -->
	 */
	public Long numTafonasRTByCampanya(Long campanyaId) {
		establimentDao.setHibernateTemplate(getHibernateTemplate());
		return establimentDao.numTafonasRTByCampanya(campanyaId);
	}
	
	
	

	/**
	 * Cerca tots els establecimientos envasadora activos per campanya <!--
	 * begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR" <!-- end-xdoclet-definition -->
	 */
	public Long numEnvasadoraByCampanya(Long campanyaId) {
		establimentDao.setHibernateTemplate(getHibernateTemplate());
		return establimentDao.numEnvasadoraByCampanya(campanyaId);
	}
	
	/**
	 * Cerca tots els establecimientos envasadora activos per campanya <!--
	 * begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR" <!-- end-xdoclet-definition -->
	 */
	public Long numEnvasadoraRTByCampanya(Long campanyaId) {
		establimentDao.setHibernateTemplate(getHibernateTemplate());
		return establimentDao.numEnvasadoraRTByCampanya(campanyaId);
	}
	

	/**
	 * Cerca tots els establecimientos per campanya ordenat per RB i RC
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection establimentCercaTotsByCampanyaOrderedByRbRc(Long campanyaId) {
		establimentDao.setHibernateTemplate(getHibernateTemplate());
		return establimentDao.establimentCercaTotsByCampanyaOrderedByRbRc(campanyaId);
	}


	/**
	 * Cerca tots els establiments activos <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection establimentCercaTotsActivos(Long campanyaId) {
		Campanya campanya = campanyaAmbId(campanyaId.intValue());
		if (campanya != null) {
			if (campanya.getDataFi() != null) {
				historicEstablimentDao.setHibernateTemplate(getHibernateTemplate());
				return historicEstablimentDao.findAllActivos(campanyaId);
			} else {
				establimentDao.setHibernateTemplate(getHibernateTemplate());
				return establimentDao.findAllActivos(campanyaId);
			}
		} else return null;
	}
	
	/**
	 * Cerca tots els establiments activos <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection establimentProductorOlivaCercaTotsActivos(Long campanyaId) {
		Campanya campanya = campanyaAmbId(campanyaId.intValue());
		if (campanya != null) {
			if (campanya.getDataFi() != null) {
				historicEstablimentDao.setHibernateTemplate(getHibernateTemplate());
				return historicEstablimentDao.findAllActivosProductorsOliva(campanyaId);
			} else {
				establimentDao.setHibernateTemplate(getHibernateTemplate());
				return establimentDao.findAllActivosProductorsOliva(campanyaId);
			}
		} else return null;
	}

	/**
	 * Cerca tots els establiments activos <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection establimentCercaTotsEliminats(Long campanyaId) {
		establimentDao.setHibernateTemplate(getHibernateTemplate());
		return establimentDao.findAllEliminats(campanyaId);
	}
	
	/**
	 * Cerca tots els establiments activos de unos tipos determinados <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection establimentCercaTotsActivosByTipos(Long campanyaId,Object[] tipos) {
		establimentDao.setHibernateTemplate(getHibernateTemplate());
		return establimentDao.findEstablimentsByTipos(campanyaId, tipos);
	}
	
	/**
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public void establimentCrear(Establiment est) {
		establimentDao.setHibernateTemplate(getHibernateTemplate());
		establimentDao.makePersistent(est);
		
		guardaHistoricEstabliment(est, "alta");
	}

	/**
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR" 
	 * <!-- end-xdoclet-definition -->
	 */
	public Long establimentCrear(//Long usuariId, 
			Campanya campanya,
			Long idOriginal, String establecimiento, String cif,
			String direccion, Long localidad, String cpostal, String email,
			String telefono, String fax, Integer trabajadores,
			Double superficie, Long tipoEstablecimientoId,
			String nombresolicitante, String dnisolicitante, 
			String nombreresponsable, String dniresponsable,
			String perfil,
			String telefonosolicitante, String unidadmedida,
			String observaciones, Integer tempMaximaPasta,
			Double capacidadProduccion, Boolean envasadoManual,
			Boolean etiquetadoManual, Long[] marcasArray,
			String codiRB, String codiRC, String codiRT,
			Boolean olivaTaula) {
		Long establecimientoId = null;
//		Usuari usuario = new Usuari();
//		usuario.setId(usuariId);
		TipusEstabliment tipoEstablecimiento = new TipusEstabliment();
		tipoEstablecimiento.setId(new Integer(tipoEstablecimientoId.intValue()));

		Solicitant solicitante = new Solicitant();
		solicitante.setNom(nombresolicitante);
		solicitante.setNif(dnisolicitante);
		solicitante.setPerfil(perfil);
		solicitante.setTelefon(telefonosolicitante);
		solicitante.setData(GregorianCalendar.getInstance().getTime());
		solicitanteDao.setHibernateTemplate(getHibernateTemplate());
		Long idSolicitant = solicitanteDao.makePersistent(solicitante);

		Establiment est = new Establiment();
		est.setCampanya(campanya);
		est.setIdOriginal(idOriginal);
//		est.setUsuari(usuario);
		est.setTipusEstabliment(tipoEstablecimiento);
		est.setSolicitant(solicitante);
		est.setNom(establecimiento);
		est.setCif(cif);
		est.setDireccio(direccion);
		municipiDao.setHibernateTemplate(getHibernateTemplate());
		est.setPoblacio(municipiDao.getById(localidad));
		est.setCodiPostal(cpostal);
		est.setEmail(email);
		est.setTelefon(telefono);
		est.setFax(fax);
		est.setNumeroTreballadors(trabajadores);
		est.setSuperficie(superficie);
		est.setUnitatMesura(unidadmedida);
		est.setObservacions(observaciones);
		est.setActiu(new Boolean(true));
		est.setTemperaturaMaximaPasta(tempMaximaPasta);
		est.setCapacitatProduccio(capacidadProduccion);
		est.setEnvassamentManual(envasadoManual);
		est.setEtiquetatManual(etiquetadoManual);
		est.setCodiRB(codiRB);
		est.setCodiRC(codiRC);
		est.setCodiRT(codiRT);
		est.setNomResponsable(nombreresponsable);
		est.setCifResponsable(dniresponsable);
		est.setOlivaTaula(olivaTaula);

		if (marcasArray != null) {
			for (int i = 0; i < marcasArray.length; i++) {
				marcaDao.setHibernateTemplate(getHibernateTemplate());
				Marca marca = marcaDao.getById(marcasArray[i]);
				marca.getEstabliments().add(est);
			}
		}
		establimentDao.setHibernateTemplate(getHibernateTemplate());
		establimentDao.makePersistent(est);
		establecimientoId = est.getId();
		
		guardaHistoricEstabliment(est, "alta");
		
		return establecimientoId;

	}

	/**
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public void establimentModificar(//Long usuariId, 
			Campanya campanya,
			Long idOriginal, String establecimiento, String cif,
			String direccion, Long localidad, String cpostal, String email,
			String telefono, String fax, Integer trabajadores,
			Double superficie, Long tipoEstablecimientoId,
			String nombresolicitante, String dnisolicitante, 
			String nombreresponsable, String dniresponsable,
			String perfil,
			String telefonosolicitante, String unidadmedida,
			String observaciones, Integer tempMaximaPasta,
			Double capacidadProduccion, Boolean envasadoManual,
			Boolean etiquetadoManual, Boolean activo, Long establecimientoId,
			Long[] marcasArray, String cambioEstado,
			String codiRB, String codiRC, String codiRT,
			Boolean olivaTaula) {

		// Recuperamos el establecimiento de la BBDD ya que vamos a necesitar
		// recoger las antiguas marcas asociadas y el solicitantAlta
		establimentDao.setHibernateTemplate(getHibernateTemplate());
		Establiment est = establimentDao.getById(establecimientoId);

//		Usuari usuario = new Usuari();
//		usuario.setId(usuariId);
		
		if ( tipoEstablecimientoId != null){
			TipusEstabliment tipoEstablecimiento = new TipusEstabliment();
			tipoEstablecimiento.setId(new Integer(tipoEstablecimientoId.intValue()));
			est.setTipusEstabliment(tipoEstablecimiento);
		}

		Solicitant solicitante = est.getSolicitant();
		solicitante.setNom(nombresolicitante);
		solicitante.setNif(dnisolicitante);
		solicitante.setPerfil(perfil);
		solicitante.setTelefon(telefonosolicitante);
		est.setCampanya(campanya);
		est.setIdOriginal(idOriginal);
//		est.setUsuari(usuario);
		est.setSolicitant(solicitante);
		if ( establecimiento != null){
			est.setNom(establecimiento);
		}
		if ( cif != null){
			est.setCif(cif);
		}
		est.setDireccio(direccion);
		municipiDao.setHibernateTemplate(getHibernateTemplate());
		est.setPoblacio(municipiDao.getById(localidad));
		est.setCodiPostal(cpostal);
		est.setEmail(email);
		est.setTelefon(telefono);
		est.setFax(fax);
		est.setNumeroTreballadors(trabajadores);
		est.setSuperficie(superficie);
		est.setUnitatMesura(unidadmedida);
		est.setObservacions(observaciones);
		est.setTemperaturaMaximaPasta(tempMaximaPasta);
		est.setCapacitatProduccio(capacidadProduccion);
		est.setEnvassamentManual(envasadoManual);
		est.setEtiquetatManual(etiquetadoManual);
		est.setOlivaTaula(olivaTaula);
		if ( codiRB != null){
			est.setCodiRB(codiRB);
		}
		if ( codiRC != null){
			est.setCodiRC(codiRC);
		}
		if ( codiRT != null){
			est.setCodiRT(codiRT);
		}

		for (Iterator it = est.getMarcas().iterator(); it.hasNext();)
			((Marca) it.next()).getEstabliments().remove(est);
		if (marcasArray != null) {
			for (int i = 0; i < marcasArray.length; i++) {
				marcaDao.setHibernateTemplate(getHibernateTemplate());
				Marca marca = marcaDao.getById(marcasArray[i]);
				marca.getEstabliments().add(est);
			}
		}

		est.setActiu(activo);
		
		est.setNomResponsable(nombreresponsable);
		est.setCifResponsable(dniresponsable);

		// Si pasamos de activo a inactivo cambiamos todas sus zonas asociadas a
		// inactivas
		if (cambioEstado != null && cambioEstado.equalsIgnoreCase("true")
				&& !activo.booleanValue()) {
			for (Iterator it = est.getZonas().iterator(); it.hasNext();)
				((Zona) it.next()).setActiu(Boolean.valueOf(false));
		}

		// No necesitamos hacer un makePersistent porque la session todavia esta
		// abierta
		// establimentDao.makePersistent(est);

	}

	/**
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */

	public void establimentModificar(Establiment establiment) {
		establimentDao.setHibernateTemplate(getHibernateTemplate());
		establimentDao.makePersistent(establiment);
	}

//	/**
//	 * <!-- begin-xdoclet-definition -->
//	 * 
//	 * @ejb.interface-method view-type="both"
//	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
//	 * @ejb.permission role-name="OLI_DOGESTOR"
//	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
//	 * @ejb.permission role-name="OLI_OLIVICULTOR"
//	 * @ejb.permission role-name="OLI_PRODUCTOR"
//	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
//	 */
//
//	public void addUsuariEstabliment(Long usuariId, Establiment establiment) {
//		Set usuaris = establiment.getUsuaris();
//		usuariDao.setHibernateTemplate(getHibernateTemplate());
//		Usuari usuari = usuariDao.getById(usuariId);
//		usuaris.add(usuari);
//		establiment.setUsuaris(usuaris);
//		establimentDao.setHibernateTemplate(getHibernateTemplate());
//		establimentDao.makePersistent(establiment);
//	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 * @generated
	 */
	public void establimentEsborrar(Long id) {
		establimentDao.setHibernateTemplate(getHibernateTemplate());
		Establiment establiment = establimentDao.getById(id);
		if (establiment != null) {
			establiment.setDataBaixa(Calendar.getInstance().getTime());
			establimentDao.setHibernateTemplate(getHibernateTemplate());
			establimentDao.makePersistent(establiment);
			
			guardaHistoricEstabliment(establiment, "baixa");
			
//			establimentDao.setHibernateTemplate(getHibernateTemplate());
//			establimentDao.makeTransient(establiment);
		}
	}

	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Establiment establimentAmbId(Long id) {
		establimentDao.setHibernateTemplate(getHibernateTemplate());
		return establimentDao.getById(id);
	}

	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Establiment establimentAmbIdOriginal(Long id) {
		establimentDao.setHibernateTemplate(getHibernateTemplate());
		return establimentDao.getByIdOriginal(id);
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Establiment getEstablecimientoByUsuari(Long idUsuario) {
		establimentDao.setHibernateTemplate(getHibernateTemplate());
		return establimentDao.getEstablecimientoByUsuari(idUsuario);
	}

	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Establiment getEstablecimientoActivoByUsuari(Long idUsuario, Long idCampanya) {
		establimentDao.setHibernateTemplate(getHibernateTemplate());
		return establimentDao.getEstablecimientoActivoByUsuari(idUsuario, idCampanya);
	}
	
	
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR" <!-- end-xdoclet-definition -->
	 */
	public Boolean getExistenEnvasadoresActivoByMarca(Long idMarca, Long idCampanya,Integer tipusEstablimentTafonaEnvasadora, Integer tipusEstablimentEnvasadora) {
		establimentDao.setHibernateTemplate(getHibernateTemplate());
		return establimentDao.getExistenEnvasadoresActivoByMarca(idMarca,idCampanya,tipusEstablimentTafonaEnvasadora,tipusEstablimentEnvasadora);
	}
	
		

	/*
	 * public Olivicultor olivicultorAmbId(Long id){ Olivicultor olivicultor =
	 * olivicultorDao.getById(id); return olivicultor; }
	 */
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Olivicultor olivicultorAmbId(Long olivicultorId) {
		olivicultorDao.setHibernateTemplate(getHibernateTemplate());
		Olivicultor olivicultor = olivicultorDao.getById(olivicultorId);
		return olivicultor;
	}

	/**
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Olivicultor olivicultorUsuari(Long id) {
		olivicultorDao.setHibernateTemplate(getHibernateTemplate());
		Olivicultor valor = olivicultorDao.olivicultorUsuari(id);
		return valor;
	}

	/**
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Olivicultor olivicultorUsuari(Long id, Long idCampanya) {
		olivicultorDao.setHibernateTemplate(getHibernateTemplate());
		Olivicultor valor = olivicultorDao.olivicultorUsuari(id, idCampanya);
		return valor;
	}

	/**
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public boolean olivicultorUsuariTieneFincas(Long id, Long idCampanya) {
		olivicultorDao.setHibernateTemplate(getHibernateTemplate());
		Boolean valor = olivicultorDao.olivicultorUsuariTieneFincas(id, idCampanya);
		if (valor != null) {
			return valor.booleanValue();
		} else {
			return false;
		}
	}

	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 * @generated
	 */

	public void olivicultorEsborrar(Long olivicultorId) {
		olivicultorDao.setHibernateTemplate(getHibernateTemplate());
		Olivicultor olivicultor = olivicultorDao.getById(olivicultorId);
		// No s'esborra l'olivicultor, se li posa una data de baixa.
		olivicultor.setDataBaixa(Calendar.getInstance().getTime());
		olivicultorDao.setHibernateTemplate(getHibernateTemplate());
		olivicultorDao.makePersistent(olivicultor);
		
		if (olivicultor != null) {
			// Marcam de baixa totes les finques de l'olivicultor.
			Set finques = olivicultor.getFincas();
			for (Iterator itf = finques.iterator(); itf.hasNext();) {
				Finca finca = (Finca)itf.next();
				finca.setDeBaixa(true);
				
				// Marcam de baixa totes les plantacions de l'olivicultor i la finca.
				Set plantacions = finca.getPlantacios();
				for (Iterator itp = plantacions.iterator(); itp.hasNext();) {
					Plantacio plantacio = (Plantacio)itp.next();
					plantacio.setDeBaixa(true);
					plantacioDao.setHibernateTemplate(getHibernateTemplate());
					plantacioDao.makePersistent(plantacio);
				}
				
				fincasDao.setHibernateTemplate(getHibernateTemplate());
				fincasDao.makePersistent(finca);
			}
		}
		
		guardaHistoricOlivicultor(olivicultor, "baixa");
		
		/*
		if (olivicultor != null) {
			Long usuariId = olivicultor.getUsuari().getId();
			usuariDao.setHibernateTemplate(getHibernateTemplate());
			Usuari usuari = usuariDao.getById(usuariId);
			Set sOlis = usuari.getOlivicultors();
			sOlis.remove(olivicultor);
			usuari.setOlivicultors(sOlis);

			olivicultorDao.setHibernateTemplate(getHibernateTemplate());
			olivicultorDao.makeTransient(olivicultor);
			if (sOlis.size() == 0){
				usuariDao.setHibernateTemplate(getHibernateTemplate());
				usuariDao.makeTransient(usuari);
			}
		}
		*/
	}

	/**
	 * Cerca tots les olivicultores <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection olivicultorCercaTots() {
		olivicultorDao.setHibernateTemplate(getHibernateTemplate());
		return olivicultorDao.findAll();
	}
	
	/**
	 * Cerca tots les olivicultores <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection olivicultorCercaTotsEliminats() {
		olivicultorDao.setHibernateTemplate(getHibernateTemplate());
		return olivicultorDao.findAllEliminats();
	}

	/**
	 * Cerca tots les olivicultores <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection findAllOlivicultorsByCampanyaOrderedByCodiDODesc(Long campanyaId) {
		olivicultorDao.setHibernateTemplate(getHibernateTemplate());
		return olivicultorDao.findAllOlivicultorsByCampanyaOrderedByCodiDODesc(campanyaId);
	}
	
	/**
	 * Cerca tots les olivicultores <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection findAllOlivicultorsByCampanyaOrderedByCodiDODescAmbFiltre(Long campanyaId, String cerca) {
		olivicultorDao.setHibernateTemplate(getHibernateTemplate());
		return olivicultorDao.findAllOlivicultorsByCampanyaOrderedByCodiDODescAmbFiltre(campanyaId, cerca);
	}
	

	/**
	 * Cerca tots les olivicultores <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection findAllOlivicultorsEnCampanyaActualOrderedByNom(){
		olivicultorDao.setHibernateTemplate(getHibernateTemplate());
		return olivicultorDao.findAllOlivicultorsEnCampanyaActualOrderedByNom();
	}
	
	/**
	 * Cerca tots les olivicultores <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_DOGESTOR" <!-- end-xdoclet-definition -->
	 */
	public Collection findOlivicultorsAltaDOCartillaByCampanya(Long campanyaId) {
		olivicultorDao.setHibernateTemplate(getHibernateTemplate());
		return olivicultorDao.findOlivicultorsAltaDOCartillaByCampanya(campanyaId);
	}
	
	
	/**
	 * Cerca tots les olivicultores con DO y cartilla, ordenados por campo nombre <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR" 
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection findOlivicultorsAltaDOCartillaByCampanyaOrderNom(Long campanyaId) {
		olivicultorDao.setHibernateTemplate(getHibernateTemplate());
		return olivicultorDao.findOlivicultorsAltaDOCartillaByCampanyaOrderNom(campanyaId);
	}
	
	
	
	/**
	 * Cerca todos los olivicultores dados de alta en la DO de la campanya
	 * actual <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR" <!-- end-xdoclet-definition -->
	 */
	public Collection findOlivicultorsAltaDOenCampanyaActual() {
		olivicultorDao.setHibernateTemplate(getHibernateTemplate());
		return olivicultorDao.findOlivicultorsAltaDOenCampanyaActual();
	}


	/**
	 * Recupera todos los olivicultores dados de alta en la DO de la campanya actual SIN CARTILLA
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection findOlivicultorsAltaDOSinCartillaEnCampanyaActual() {
		olivicultorDao.setHibernateTemplate(getHibernateTemplate());
		return olivicultorDao.findOlivicultorsAltaDOSinCartillaEnCampanyaActual();
	}	


	/**
	 * Recupera todos los olivicultores dados de alta en la DO de la campanya actual SIN CARTILLA y que no estén subvencionados
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection findOlivicultorsAltaDOSinCartillaNoSubvencionadosEnCampanyaActual() {
		olivicultorDao.setHibernateTemplate(getHibernateTemplate());
		return olivicultorDao.findOlivicultorsAltaDOSinCartillaNoSubvencionadosEnCampanyaActual();
	}	
	
	
	/**
	 * Cerca todos los olivicultores dados de alta en la DO de la campanya dada tots
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR" <!-- end-xdoclet-definition -->
	 */
	public Collection findAllAltaDOByCampanya(Long campanyaId) {
		olivicultorDao.setHibernateTemplate(getHibernateTemplate());
		return olivicultorDao.findAllAltaDOByCampanya(campanyaId);
	}
	
	
	/**
	 * Cerca todos los olivicultores dados de alta en la DO de la campanya dada productors RA
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR" <!-- end-xdoclet-definition -->
	 */
	public Collection findAllAltaDOByCampanyaRA(Long campanyaId) {
		olivicultorDao.setHibernateTemplate(getHibernateTemplate());
		return olivicultorDao.findAllAltaDOByCampanyaRA(campanyaId);
	}
	
	/**
	 * Cerca todos los olivicultores dados de alta en la DO de la campanya dada productors RE
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR" <!-- end-xdoclet-definition -->
	 */
	public Collection findAllAltaDOByCampanyaRE(Long campanyaId) {
		olivicultorDao.setHibernateTemplate(getHibernateTemplate());
		return olivicultorDao.findAllAltaDOByCampanyaRE(campanyaId);
	}
	
	/**
	 * Cerca todos los olivicultores dados de alta en la DO de la campanya dada productors RT
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR" <!-- end-xdoclet-definition -->
	 */
	public Collection findAllAltaDOByCampanyaRT(Long campanyaId) {
		olivicultorDao.setHibernateTemplate(getHibernateTemplate());
		return olivicultorDao.findAllAltaDOByCampanyaRT(campanyaId);
	}
	
	/**
	 * Cerca todos los olivicultores dados de alta en la DO de la campanya donada tots
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR" <!-- end-xdoclet-definition -->
	 */
	public Collection findAllIdAltaDOByCampanya(Long campanyaId) {
		olivicultorDao.setHibernateTemplate(getHibernateTemplate());
		return olivicultorDao.findAllIdAltaDOByCampanya(campanyaId);
	}
	
	
	
	/**
	 * Cerca todos los olivicultores dados de alta en la DO de la campanya donada productors RA
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR" <!-- end-xdoclet-definition -->
	 */
	public Collection findAllIdAltaDOByCampanyaRA(Long campanyaId) {
		olivicultorDao.setHibernateTemplate(getHibernateTemplate());
		return olivicultorDao.findAllIdAltaDOByCampanyaRA(campanyaId);
	}
	
	/**
	 * Cerca todos los olivicultores dados de alta en la DO de la campanya donada productors RE
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR" <!-- end-xdoclet-definition -->
	 */
	public Collection findAllIdAltaDOByCampanyaRE(Long campanyaId) {
		olivicultorDao.setHibernateTemplate(getHibernateTemplate());
		return olivicultorDao.findAllIdAltaDOByCampanyaRE(campanyaId);
	}
	
	/**
	 * Cerca todos los olivicultores dados de alta en la DO de la campanya donada productors RT
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR" <!-- end-xdoclet-definition -->
	 */
	public Collection findAllIdAltaDOByCampanyaRT(Long campanyaId) {
		olivicultorDao.setHibernateTemplate(getHibernateTemplate());
		return olivicultorDao.findAllIdAltaDOByCampanyaRT(campanyaId);
	}
	
	/**
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Long olivicultorCrear(Long usuariId, Campanya campanya,
			Long idOriginal, Boolean teCodiDO, Boolean teCodiDOExperimental, Boolean teCodiDOPOliva,
			String nom, String telefon, String email, String fax,
			String direccio, Long poblacio, String codiPostal, String compteBancari,
			Boolean altaDO, Boolean cartilla, String nif, String observacions, 
			String uidRfid, Boolean subvencionat) {

		Usuari usuari = null;
		if (usuariId != null) {
			usuariDao.setHibernateTemplate(getHibernateTemplate());
			usuari = usuariDao.getById(usuariId);
			if (usuari == null) {
				usuari= new Usuari();
				usuari.setId(usuariId);
			}
		}

		// Donam d'alta l'olivicultor.
		Olivicultor olivicultor = new Olivicultor();
		olivicultor.setUsuari(usuari);
		olivicultor.setCampanya(campanya);
		olivicultor.setIdOriginal(idOriginal);
		if ((teCodiDO != null) && teCodiDO) {
			olivicultorDao.setHibernateTemplate(getHibernateTemplate());
			String codiDo = olivicultorDao.getNextCodiDo();
			olivicultor.setCodiDO(codiDo);
		}
		if ((teCodiDOExperimental != null) && teCodiDOExperimental) {
			olivicultorDao.setHibernateTemplate(getHibernateTemplate());
			String codiDoExperimental = olivicultorDao.getNextCodiDoExperimental();
			olivicultor.setCodiDOExperimental(codiDoExperimental);
		}
		if (teCodiDOPOliva != null && teCodiDOPOliva.booleanValue() && (olivicultor.getCodiDOPOliva() == null)) {
			olivicultorDao.setHibernateTemplate(getHibernateTemplate());
			String codiDopOliva = olivicultorDao.getNextCodiDopOliva();
			olivicultor.setCodiDOPOliva(codiDopOliva);
		}
		olivicultor.setNom(nom);
		olivicultor.setTelefon(telefon);
		olivicultor.setEmail(email);
		olivicultor.setFax(fax);
		olivicultor.setDireccio(direccio);
		municipiDao.setHibernateTemplate(getHibernateTemplate());
		olivicultor.setPoblacio(municipiDao.getById(poblacio));
		olivicultor.setCodiPostal(codiPostal);
		olivicultor.setCompteBancari(compteBancari);
		olivicultor.setAltaDO(altaDO);
		olivicultor.setCartilla(cartilla);
		olivicultor.setNif(nif);
		olivicultor.setObservacions(observacions);
		olivicultor.setUidRfid(uidRfid);
		olivicultor.setSubvencionat(subvencionat);
		olivicultorDao.setHibernateTemplate(getHibernateTemplate());
		olivicultorDao.makePersistent(olivicultor);
		
		guardaHistoricOlivicultor(olivicultor, "alta");
		
		return olivicultor.getId();
	}

	/**
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR" <!-- end-xdoclet-definition -->
	 */
	public void olivicultorModificar(Long usuariId, Campanya campanya,
			Long idOriginal, Boolean teCodiDO, Boolean teCodiDOExperimental, Boolean teCodiDOPOliva,
			String nom, String telefon, String email, String fax,
			String direccio, Long poblacio, String codiPostal, String compteBancari,
			Boolean altaDO, Boolean cartilla, String nif, String observacions,
			Long olivicultorId, String uidRfid, Boolean subvencionat) {

		Usuari usuari = new Usuari();
		usuari.setId(usuariId);

		Olivicultor olivicultor = olivicultorDao.getById(olivicultorId);
		olivicultor.setId(olivicultorId);
		olivicultor.setUsuari(usuari);
		olivicultor.setCampanya(campanya);
		olivicultor.setIdOriginal(idOriginal);
		if (teCodiDO != null) {
			if(teCodiDO && (olivicultor.getCodiDO() == null)) {
				olivicultorDao.setHibernateTemplate(getHibernateTemplate());
				String codiDo = olivicultorDao.getNextCodiDo();
				olivicultor.setCodiDO(codiDo);
			} else if (!teCodiDO) {
				olivicultor.setCodiDO(null);
			}
		}
		if (teCodiDOExperimental != null) {
			if(teCodiDOExperimental.booleanValue() && (olivicultor.getCodiDOExperimental() == null)) {
				olivicultorDao.setHibernateTemplate(getHibernateTemplate());
				String codiDoExperimental = olivicultorDao.getNextCodiDoExperimental();
				olivicultor.setCodiDOExperimental(codiDoExperimental);
			} else if (!teCodiDOExperimental.booleanValue()) {
				olivicultor.setCodiDOExperimental(null);
			}
		}
		if (teCodiDOPOliva != null) {
			if ( teCodiDOPOliva.booleanValue() && (olivicultor.getCodiDOPOliva() == null)) {
				olivicultorDao.setHibernateTemplate(getHibernateTemplate());
				String codiDopOliva = olivicultorDao.getNextCodiDopOliva();
				olivicultor.setCodiDOPOliva(codiDopOliva);
			} else if (!teCodiDOPOliva.booleanValue()) {
				olivicultor.setCodiDOPOliva(null);
			}
		}
		if ( nom != null){
			olivicultor.setNom(nom);
		}
		olivicultor.setTelefon(telefon);
		olivicultor.setEmail(email);
		olivicultor.setFax(fax);
		olivicultor.setDireccio(direccio);
		municipiDao.setHibernateTemplate(getHibernateTemplate());
		olivicultor.setPoblacio(municipiDao.getById(poblacio));
		olivicultor.setCodiPostal(codiPostal);
		olivicultor.setCompteBancari(compteBancari);
		olivicultor.setAltaDO(altaDO);
		
		//Si no és alta de DO desactivam la cartilla
		if(!altaDO){
			olivicultor.setCartilla(false);
		} else {
			olivicultor.setCartilla(cartilla);
		}
		
		if ( nif != null){
			olivicultor.setNif(nif);
		}
		olivicultor.setObservacions(observacions);
		olivicultor.setUidRfid(uidRfid);
		olivicultor.setSubvencionat(subvencionat);

		olivicultorDao.setHibernateTemplate(getHibernateTemplate());
		olivicultorDao.makePersistent(olivicultor);
		
		//Si no és alta DO desactivam finques i plantacions
		if(!altaDO){
			Set finques = olivicultor.getFincas();
			for(Iterator it=finques.iterator(); it.hasNext();){
				Finca finca = (Finca)it.next();
				finca.setActiu(false);
				fincasDao.setHibernateTemplate(getHibernateTemplate());
				fincasDao.makePersistent(finca);

				Set plantacions = finca.getPlantacios();
				
				for(Iterator it2=plantacions.iterator(); it2.hasNext();){
					Plantacio plantacio = (Plantacio)it2.next();
					plantacio.setActiu(false);
					plantacioDao.setHibernateTemplate(getHibernateTemplate());
					plantacioDao.makePersistent(plantacio);
				}
			}
		}
	}

	/**
	 * Cerca tots las fincas <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection fincasCercaTots() {
		fincasDao.setHibernateTemplate(getHibernateTemplate());
		return fincasDao.findAll();
	}

	/**
	 * Cerca tots las fincas <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection fincasOlivicultorCercaTots(Long idOlivicultor) {
		fincasDao.setHibernateTemplate(getHibernateTemplate());
		return fincasDao.findAllOlivicultor(idOlivicultor);
	}
	/**
	 * Cerca tots las fincas <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR" 
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection fincasOlivicultorCercaTotsActius(Long idOlivicultor) {
		fincasDao.setHibernateTemplate(getHibernateTemplate());
		return fincasDao.findActiusByOlivicultor(idOlivicultor);
	}
	/**
	 * Cerca tots las fincas <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection fincasOlivicultorCercaTotsActiusConPlantacionesConDescomposicion(Long idOlivicultor) {
		fincasDao.setHibernateTemplate(getHibernateTemplate());
		return fincasDao.fincasOlivicultorCercaTotsActiusConPlantacionesConDescomposicion(idOlivicultor);
	}

	/**
	 * Cerca tots las fincas <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_OLIVICULTOR" <!-- end-xdoclet-definition -->
	 */
	public Collection fincasIdOlivicultorCercaTots(Long idOlivicultor) {
		fincasDao.setHibernateTemplate(getHibernateTemplate());
		return fincasDao.findAllIdOlivicultor(idOlivicultor);
	}

	/**
	 * Cerca tots las id de las plantaciones activas de idFincas <!--
	 * begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_OLIVICULTOR" <!-- end-xdoclet-definition -->
	 */
	public Collection idPlantacionesActivas(Long idFinca) {
		plantacioDao.setHibernateTemplate(getHibernateTemplate());
		return plantacioDao.findAllIdPlantacioActivas(idFinca);
	}
	
	/**
	 * Cerca totes les plantacions 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR" 
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection findPlantacions() {
		plantacioDao.setHibernateTemplate(getHibernateTemplate());
		return plantacioDao.findAll();
	}

	/**
	 * Hacer inactiva plantacion idPlantacio <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR" <!-- end-xdoclet-definition -->
	 */
	public void plantacioSetActiuFalse(Long idPlantacio) {
		plantacioDao.setHibernateTemplate(getHibernateTemplate());
		Plantacio plantacio = plantacioDao.getById(idPlantacio);
		plantacio.setActiu(Boolean.valueOf(false));
		plantacioDao.setHibernateTemplate(getHibernateTemplate());
		plantacioDao.makePersistent(plantacio);
	}

	/**
	 * Cerca tots las plantaciones de una finca <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection getPlantacionesDeFinca(Long fincaId) {
		plantacioDao.setHibernateTemplate(getHibernateTemplate());
		return plantacioDao.getByFincaId(fincaId);
	}

	/**
	 * Cerca tots las plantaciones de una finca <!-- begin-xdoclet-definition
	 * -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection getPlantacionesActivasDeFinca(Long fincaId) {
		plantacioDao.setHibernateTemplate(getHibernateTemplate());
		return plantacioDao.getActivasByFincaId(fincaId);
	}
	
	/**
	 * Cerca tots las plantaciones de una finca <!-- begin-xdoclet-definition
	 * -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public List plantacionesActivasConDescomposicionesByFinca(Long fincaId) {
		plantacioDao.setHibernateTemplate(getHibernateTemplate());
		return plantacioDao.getActivasConDescomposicionByFincaId(fincaId);
	}

	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Finca fincaAmbId(Long id) {
		fincasDao.setHibernateTemplate(getHibernateTemplate());
		return fincasDao.getById(id);
	}

	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 * @generated
	 */

	public void fincaEsborrar(Long fincaId) {
		fincasDao.setHibernateTemplate(getHibernateTemplate());
		Finca finca = fincasDao.getById(fincaId);
		if (finca != null) {
			fincasDao.setHibernateTemplate(getHibernateTemplate());
			fincasDao.makeTransient(finca);
		}
	}

	/**
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */

	public Long fincaCrear(String nombre, Long olivicultorId,
			String observaciones, Boolean activo) {
		olivicultorDao.setHibernateTemplate(getHibernateTemplate());
		Olivicultor olivicultor = olivicultorDao.getById(olivicultorId);
		Finca finca = new Finca();
		finca.setNom(nombre);
		finca.setObservacions(observaciones);
		finca.setActiu(activo);
		finca.setOlivicultor(olivicultor);
		fincasDao.setHibernateTemplate(getHibernateTemplate());
		fincasDao.makePersistent(finca);
		return finca.getId();
	}

	/**
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public void fincaModificar(Long fincaId, String nombre,
			String observaciones, Boolean activo) {

		fincasDao.setHibernateTemplate(getHibernateTemplate());
		Finca finca = fincasDao.getById(fincaId);
		finca.setNom(nombre);
		finca.setObservacions(observaciones);
		finca.setActiu(activo);
		fincasDao.setHibernateTemplate(getHibernateTemplate());
		fincasDao.makePersistent(finca);
	}

	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Plantacio plantacioAmbId(Long id) {
		plantacioDao.setHibernateTemplate(getHibernateTemplate());
		return plantacioDao.getById(id);
	}

	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public DescomposicioPlantacio descomposicioPlantacioAmbId(Long id) {
		return descomposicioPlantacioDao.getById(id);
	}

	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection descomposicioPlantacioAmbIdPlantacio(Long id) {
		descomposicioPlantacioDao.setHibernateTemplate(getHibernateTemplate());
		return descomposicioPlantacioDao.getByIdPlantacio(id);		
	}

	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection descomposicioPlantaciones() {
		descomposicioPlantacioDao.setHibernateTemplate(getHibernateTemplate());
		return descomposicioPlantacioDao.findAllOrdered();

	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR" 
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public List descomposicioPlantacioActivasByOlivicultor(Long idOlivicultor) {
		descomposicioPlantacioDao.setHibernateTemplate(getHibernateTemplate());
		return descomposicioPlantacioDao.getDescPlantacioActivasByOlivicultor(idOlivicultor);

	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_PRODUCTOR" <!-- end-xdoclet-definition -->
	 */
	public DescomposicioPlantacio descomposicioPlantacioByPlantacioIdVariedadId(Long idPlantacio, Integer idVariedad) {
		DescomposicioPlantacio descomposicioPlantacio = null;
		descomposicioPlantacioDao.setHibernateTemplate(getHibernateTemplate());
		List descompsicionList = descomposicioPlantacioDao.getByIdPlantacioIdVariedad(idPlantacio,idVariedad);
		if(descompsicionList!= null && descompsicionList.size()>0){
			descomposicioPlantacio = (DescomposicioPlantacio)descompsicionList.get(0);
		}
		return descomposicioPlantacio;
	}

	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection descomposicioVariedades() {
		varietatOlivaDao.setHibernateTemplate(getHibernateTemplate());
		return varietatOlivaDao.findAllOrdered();
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection descomposicioVarietatsNoExperimentals() {
		varietatOlivaDao.setHibernateTemplate(getHibernateTemplate());
		return varietatOlivaDao.findAllNoExperimentalsOrdered();
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection descomposicioPartidaOlivaByDescomposicioPlantacio(Long descId) {
		descomposicioPartidaOlivaDao.setHibernateTemplate(getHibernateTemplate());
		return descomposicioPartidaOlivaDao.getByIdDescomposicioPlantacio(descId);

	}
	

	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public VarietatOliva variedadAmbId(Integer id) {
		varietatOlivaDao.setHibernateTemplate(getHibernateTemplate());
		return varietatOlivaDao.getById(id);
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public List varietatsOlivaByOlivicultor(Long idOlivicultor) {
		varietatOlivaDao.setHibernateTemplate(getHibernateTemplate());
		return varietatOlivaDao.getVarietatsOlivaByOlivicultor(idOlivicultor);
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public List totesVarietatsOlivaByOlivicultor(Long idOlivicultor) {
		varietatOlivaDao.setHibernateTemplate(getHibernateTemplate());
		return varietatOlivaDao.getTotesVarietatsOlivaByOlivicultor(idOlivicultor);
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public List varietatsOlivaByOlivicultorPlantacio(Long idOlivicultor, Long idPlantacio) {
		varietatOlivaDao.setHibernateTemplate(getHibernateTemplate());
		return varietatOlivaDao.getVarietatsOlivaByOlivicultorPlantacio(idOlivicultor, idPlantacio);
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection varietatsOliva() {
		varietatOlivaDao.setHibernateTemplate(getHibernateTemplate());
		return varietatOlivaDao.findAll();
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection varietatsOlivaActiu() {
		varietatOlivaDao.setHibernateTemplate(getHibernateTemplate());
		return varietatOlivaDao.findActius();
	}

	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public void descomposicioPlantacioModificar(
			Long[] idDescomposicioPlantacio, String[] idVariedadOliva,
			Integer[] numOlivos, Double[] superf, Double[] prodMaxima,
			Long idPlantacion, String observaciones, Plantacio plantacio,
			VarietatOliva variedadOliva) {
		for (int i = 0; i < idDescomposicioPlantacio.length; i++) {

		}

	}

	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection getDescPlantacioActivasAmbFinca(Long idFinca) {
		descomposicioPlantacioDao.setHibernateTemplate(getHibernateTemplate());
		return descomposicioPlantacioDao.getDescPlantacioActivasAmbFinca(idFinca);
	}

	/**
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Plantacio plantacioModificar(Plantacio plantacio, Collection col)throws Exception {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try{
			plantacio.setNomComplet(plantacio.getMunicipi().getNom() + " (" + plantacio.getPoligon() + " - " + plantacio.getParcela() + ")");
			tx = session.beginTransaction();
			plantacioDao.setHibernateTemplate(getHibernateTemplate());
			plantacioDao.makePersistent(plantacio, session);
			plantacio.setIdOriginal(plantacio.getId());
			for (Iterator it = col.iterator(); it.hasNext();) {
				DescomposicioPlantacio desc = (DescomposicioPlantacio) it.next();
				desc.setPlantacio(plantacio);
				if (desc.getProduccioRestant() == null) desc.setProduccioRestant(desc.getMaxProduccio());
				descomposicioPlantacioDao.setHibernateTemplate(getHibernateTemplate());
				descomposicioPlantacioDao.makePersistent(desc, session);
				desc.setIdOriginal(desc.getId());
			}
			tx.commit();
		}catch (Exception ex) {
			try {
				tx.rollback();
				throw new Exception(ex);
			} catch (HibernateException e) {
				throw new Exception(e);
			}
		}
		return plantacio;			
	}
	
	
//	/**
//	 * <!-- begin-xdoclet-definition -->
//	 * 
//	 * @ejb.interface-method view-type="both"
//	 * @ejb.permission role-name="OLI_DOGESTOR"
//	 * @ejb.permission role-name="OLI_OLIVICULTOR"
//	 * @ejb.permission role-name="OLI_PRODUCTOR"
//	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
//	 */
//	public Plantacio plantacioModificar(Plantacio plantacio, Collection col_nueva, Collection col_vieja)throws Exception  {
//		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
//		Transaction tx = null;
//		try{
//			tx = session.beginTransaction();
//			plantacioDao.setHibernateTemplate(getHibernateTemplate());
//			plantacioDao.makePersistent(plantacio, session);
//			
//			for (Iterator it = col_nueva.iterator(); it.hasNext();) {
//				DescomposicioPlantacio desc = (DescomposicioPlantacio) it.next();
//				desc.setPlantacio(plantacio);
//				//desc.setVarietatOliva(varietatOliva);
//				descomposicioPlantacioDao.makePersistent(desc, session);
//			}
//			
//			tx.commit();
//		}catch (Exception ex) {
//			try {
//				tx.rollback();
//				throw new Exception(ex);
//			} catch (HibernateException e) {
//				throw new Exception(e);
//			}
//		}
//		return plantacio;
//		
//	}


	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR" <!-- end-xdoclet-definition -->
	 */
	public void plantacioEsborrar(Long id) {
		plantacioDao.setHibernateTemplate(getHibernateTemplate());
		Plantacio plantacio = plantacioDao.getById(id);
		if (plantacio != null) {
			//PRIMERO BORRAMOS LAS DESCOMPOSICIONES ASOCIADAS
			 for (Iterator it = plantacio.getDescomposicioPlantacios().iterator(); it.hasNext(); ){
				DescomposicioPlantacio dp = (DescomposicioPlantacio) it.next();
				borrarDescomposicio(dp);
			}
			//BORRAMOS LA PLANTACION
			 plantacioDao.setHibernateTemplate(getHibernateTemplate());
			 plantacioDao.makeTransient(plantacio);
		}
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR" <!-- end-xdoclet-definition -->
	 */
	public void plantacioBaixa(Long id) {
		plantacioDao.setHibernateTemplate(getHibernateTemplate());
		Plantacio plantacio = plantacioDao.getById(id);
		if (plantacio != null) {
			plantacio.setDeBaixa(true);
			plantacio.setActiu(false);
			plantacioDao.setHibernateTemplate(getHibernateTemplate());
			plantacioDao.makePersistent(plantacio);
		}
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR" 
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public List PlantacioActivasByIdOlivicultor(Long idOlivicultor) {
		plantacioDao.setHibernateTemplate(getHibernateTemplate());
		return plantacioDao.getActivasByOlivicultorId(idOlivicultor);
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR" <!-- end-xdoclet-definition -->
	 */
	public List plantacioActivasConDescomposicionesByIdOlivicultor(Long idOlivicultor) {
		plantacioDao.setHibernateTemplate(getHibernateTemplate());
		return plantacioDao.getActivasConDescomposicionesByOlivicultorId(idOlivicultor);
	}

	/**
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public void descomposicionesModificar(Collection col) {
		for (Iterator it = col.iterator(); it.hasNext();) {
			DescomposicioPlantacio desc = (DescomposicioPlantacio) it.next();
			descomposicioPlantacioDao.setHibernateTemplate(getHibernateTemplate());
			descomposicioPlantacioDao.makePersistent(desc);
		}
	}

	/**
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public void borrarDescomposicio(DescomposicioPlantacio desc) {
		descomposicioPlantacioDao.setHibernateTemplate(getHibernateTemplate());
		descomposicioPlantacioDao.makeTransient(desc);
	}

	/**
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public void borrarDescomposiciones(Long idDesc) {
		descomposicioPlantacioDao.setHibernateTemplate(getHibernateTemplate());
		Collection actuales = descomposicioPlantacioDao
				.getByIdPlantacio(idDesc);
		for (Iterator ite = actuales.iterator(); ite.hasNext();) {
			DescomposicioPlantacio dp = (DescomposicioPlantacio) ite.next();
			descomposicioPlantacioDao.setHibernateTemplate(getHibernateTemplate());
			descomposicioPlantacioDao.makeTransient(dp);
		}

	}

	/**
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public List zonaActiusAmbEstabliment(Long establimentId) {
		zonaDao.setHibernateTemplate(getHibernateTemplate());
		return zonaDao.findActiusByEstabliment(establimentId);
	}

	/**
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public List zonaAmbEstabliment(Long establimentId) {
		zonaDao.setHibernateTemplate(getHibernateTemplate());
		return zonaDao.findByEstabliment(establimentId);
	}

	/**
	 * Cerca tots las zonas <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection zonaCercaTots() {
		zonaDao.setHibernateTemplate(getHibernateTemplate());
		return zonaDao.findAll();
	}

	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 * @generated
	 */
	public void zonaEsborrar(Long id) {
		zonaDao.setHibernateTemplate(getHibernateTemplate());
		Zona zona = zonaDao.getById(id);
		if (zona != null) {
			arxiuDao.setHibernateTemplate(getHibernateTemplate());
			if(zona.getImatgePlanol()!= null){
				Arxiu archivo = arxiuDao.getById(zona.getImatgePlanol());
				if (archivo != null) {
					arxiuDao.setHibernateTemplate(getHibernateTemplate());
					arxiuDao.makeTransient(archivo);
				}
			}
			
			partidaOlivaDao.setHibernateTemplate(getHibernateTemplate());
			Iterator itPao = partidaOlivaDao.findByZona(zona.getId(), new Boolean(false)).iterator();
			while (itPao.hasNext()) {
				PartidaOliva partidaOliva = (PartidaOliva) itPao.next();
				
				Iterator itDpo = partidaOliva.getDescomposicioPartidesOlives().iterator();
				while (itDpo.hasNext()) {
					DescomposicioPartidaOliva dpo = (DescomposicioPartidaOliva) itDpo.next();
					descomposicioPartidaOlivaDao.setHibernateTemplate(getHibernateTemplate());
					descomposicioPartidaOlivaDao.makeTransient(dpo);
				}
				partidaOlivaDao.setHibernateTemplate(getHibernateTemplate());
				partidaOlivaDao.makeTransient(partidaOliva);
			}
			zonaDao.setHibernateTemplate(getHibernateTemplate());
			zonaDao.makeTransient(zona);
		}
	}

	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Zona zonaAmbId(Long id) {
		zonaDao.setHibernateTemplate(getHibernateTemplate());
		return zonaDao.getById(id);
	}

	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Long zonaCrear(String nombre, Long idEstablecimiento,
			Long imagePlanol, String observaciones, Boolean activo,
			Boolean defecte, Boolean defecteTrasllat, Boolean fictici, String arxiuNom,
			String arxiuTipusMime, byte[] arxiuContingut, Long idOriginal) {

		Arxiu arxiu = null;
		if (arxiuNom != null && !arxiuNom.equals("")) {
			arxiu = new Arxiu();
			arxiu.setNom(arxiuNom);
			arxiu.setMime(arxiuTipusMime);
			arxiu.setBinari(arxiuContingut);
			arxiu.setTamany(new Integer(arxiuContingut.length));
			arxiuDao.setHibernateTemplate(getHibernateTemplate());
			arxiuDao.makePersistent(arxiu);
		}

		Establiment establiment = new Establiment();
		establiment.setId(idEstablecimiento);
		Zona zona = new Zona(establiment, activo, nombre, fictici, defecte, defecteTrasllat);
		if (arxiu != null) {
			zona.setImatgePlanol(new Long(arxiu.getId().intValue()));
		} else {
			zona.setImatgePlanol(null);
		}
		zona.setObservacions(observaciones);
		zona.setIdOriginal(idOriginal);
		zonaDao.setHibernateTemplate(getHibernateTemplate());
		zonaDao.makePersistent(zona);
		return zona.getId();
	}

	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Long zonaModificar(String nombre, Long idEstablecimiento,
			Long imagePlanol, String observaciones, Boolean activo,
			Boolean defecte, Boolean defecteTrasllat, Boolean fictici, Long zonaId, String arxiuNom,
			String arxiuTipusMime, byte[] arxiuContingut, Long idOriginal,
			String cambioEstado) {
		zonaDao.setHibernateTemplate(getHibernateTemplate());
		Zona zona = zonaDao.getById(zonaId);
		if (arxiuNom != null && !arxiuNom.equals("")) {
			Arxiu arxiu = new Arxiu();
			if (zona.getImatgePlanol() != null) {
				arxiuDao.setHibernateTemplate(getHibernateTemplate());
				arxiu = arxiuDao.getById(new Long(zona.getImatgePlanol()
						.intValue()));
			}
			if (arxiu != null) {
				arxiu.setNom(arxiuNom);
				arxiu.setMime(arxiuTipusMime);
				arxiu.setBinari(arxiuContingut);
				arxiu.setTamany(new Integer(arxiuContingut.length));
			} else {
				arxiu = new Arxiu();
				arxiu.setNom(arxiuNom);
				arxiu.setMime(arxiuTipusMime);
				arxiu.setBinari(arxiuContingut);
				arxiu.setTamany(new Integer(arxiuContingut.length));
			}
			arxiuDao.setHibernateTemplate(getHibernateTemplate());
			arxiuDao.makePersistent(arxiu);
			zona.setImatgePlanol(new Long(arxiu.getId().intValue()));
		}

		Establiment establiment = new Establiment();
		establiment.setId(idEstablecimiento);
		zona.setNom(nombre);
		zona.setEstabliment(establiment);

		zona.setObservacions(observaciones);
		zona.setActiu(activo);
		zona.setDefecte(defecte);
		zona.setDefecteTrasllat(defecteTrasllat);
		zona.setFictici(fictici);
		zona.setIdOriginal(idOriginal);
		zonaDao.setHibernateTemplate(getHibernateTemplate());
		zonaDao.makePersistent(zona);

		// Si pasamos de activo a inactivo cambiamos todas sus depositos
		// asociados a inactivos
		if (cambioEstado != null && cambioEstado.equalsIgnoreCase("true")
				&& !activo.booleanValue()) {
			for (Iterator it = zona.getDiposits().iterator(); it.hasNext();)
				((Diposit) it.next()).setActiu(Boolean.valueOf(false));
		}

		return zona.getId();
	}

	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Long zonaModificar(Zona zona) {
		zonaDao.setHibernateTemplate(getHibernateTemplate());
		zonaDao.makePersistent(zona);
		return zona.getId();
	}

	/**
	 * Cerca si existen zonas activass para un establecimiento <!--
	 * begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Boolean existenZonasActiusEnEstabliment(Long establimentId) {
		zonaDao.setHibernateTemplate(getHibernateTemplate());
		return zonaDao.existenZonasActiusEnEstabliment(establimentId);
	}

	
	
	/**
	 * Comprueba si la zona es ficticia <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public boolean zonaFicticia(Long id){
		zonaDao.setHibernateTemplate(getHibernateTemplate());
		return zonaDao.zonaFicticia(id);
	}			
	
	
	
	/**
	 * Cerca tots las diposits <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection dipositCercaTots() {
		dipositDao.setHibernateTemplate(getHibernateTemplate());
		return dipositDao.findAll();
	}
	
	/**
	 * Cerca tots las diposits de l'establiment<!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection dipositCercaTotsNoFicticisPerEstabliment(Long idEstabliment) {
		dipositDao.setHibernateTemplate(getHibernateTemplate());
		return dipositDao.findNoFicticiByEstabliment(idEstabliment);
	}
	
	/**
	 * Cerca tots els diposits buits <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection dipositCercaTotsBuits(Long idEstabliment) {
		dipositDao.setHibernateTemplate(getHibernateTemplate());
		return dipositDao.findVaciosByEstabliment(idEstabliment);
	}
	
	/**
	 * Cerca tots els lots d'un establiment <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" 
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection lotCercaTotsPerEstabliment(Long idEstabliment) {
		lotDao.setHibernateTemplate(getHibernateTemplate());
		return lotDao.findByEstabliment(idEstabliment, new Boolean(true));
	}
	
	/**
	 * Cerca tots els lots d'un establiment <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection findLotBotellesPerEstabliment(Long idEstabliment) {
		lotDao.setHibernateTemplate(getHibernateTemplate());
		return lotDao.findLotBotellesByEstabliment(idEstabliment, new Boolean(true));
	}
	
	/**
	 * Cerca tots els lots buits <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection lotCercaTotsBuits(Long idEstabliment) {
		lotDao.setHibernateTemplate(getHibernateTemplate());
		return lotDao.findVaciosByEstabliment(idEstabliment, new Boolean(true));
	}
	
	/**
	 * Cerca tots los materiales de diposits <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection materialesDipCercaTots() {
		materialDipositDao.setHibernateTemplate(getHibernateTemplate());
		return materialDipositDao.findAll();
	}

	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 * @generated
	 */
	public void dipositEsborrar(Long id) {
		dipositDao.setHibernateTemplate(getHibernateTemplate());
		Diposit diposit = dipositDao.getById(id);
		if (diposit != null) {
			// Borrar entradas no válidas.
//			entradaDipositDao.findEntradaDiposiOliByDiposit(diposit.getId(), new Boolean(false));
//			entradaDipositDao.setHibernateTemplate(getHibernateTemplate());
			
			dipositDao.setHibernateTemplate(getHibernateTemplate());
			dipositDao.makeTransient(diposit);
		}
	}

	/**
	 * Cerca tots las diposits de un establiment <!-- begin-xdoclet-definition
	 * -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public List findDipositsByEstabliment(Long establimentId) {
		dipositDao.setHibernateTemplate(getHibernateTemplate());
		return dipositDao.findByEstabliment(establimentId);
	}
	
	/**
	 * Cerca tots las diposits de un establiment <!-- begin-xdoclet-definition
	 * -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public List findDipositsActiusByEstabliment(Long establimentId) {
		dipositDao.setHibernateTemplate(getHibernateTemplate());
		return dipositDao.findActiusByEstabliment(establimentId);
	}
	
	/**
	 * Cerca tots las diposits de un establiment <!-- begin-xdoclet-definition
	 * -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public List findDipositsQueHanPassatPerEstabliment(Long establimentId) {
		dipositDao.setHibernateTemplate(getHibernateTemplate());
		return dipositDao.findDipositsQueHanPassatPerEstabliment(establimentId);
	}
	
	
	/**
	 * Cerca tots las diposits de un establiment <!-- begin-xdoclet-definition
	 * -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection findTrasllatsPerEstabliment(Long establimentId, Long dipositId, Date inici, Date fi) {
		trasllatDao.setHibernateTemplate(getHibernateTemplate());
		return trasllatDao.findTrasllatsPerEstabliment(establimentId, dipositId, inici, fi);
	}
	
	/**
	 * Cerca tots las diposits de un establiment <!-- begin-xdoclet-definition
	 * -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Diposit findDipositFicticiByEstabliment(Long establimentId) {
		dipositDao.setHibernateTemplate(getHibernateTemplate());
		return dipositDao.findDipositFicticiByEstabliment(establimentId);
	}
	
	/**
	 * Cerca tots las diposits no ficticis de un establiment <!-- begin-xdoclet-definition
	 * -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public List findNoFicticiByEstabliment(Long establimentId) {
		dipositDao.setHibernateTemplate(getHibernateTemplate());
		return dipositDao.findByEstabliment(establimentId);
	}
	
	
	/**
	 * Cerca tots las diposits de un establiment <!-- begin-xdoclet-definition
	 * -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public boolean existeixenDipositsTraslladatsByEstabliment(Long establimentId, String codiDiposit) {
		dipositDao.setHibernateTemplate(getHibernateTemplate());
		return dipositDao.existeixenTraslladatsByEstabliment(establimentId, codiDiposit);
	}

	/**
	 * Cerca tots las diposits de una zona <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection findDipositsActiusByZona(Long zonaId) {
		dipositDao.setHibernateTemplate(getHibernateTemplate());
		return dipositDao.findActiusByZona(zonaId);
	}
	
	/**
	 * Cerca tots las diposits de una zona <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection findDipositsActiusByZonaNoTraslladats(Long zonaId) {
		dipositDao.setHibernateTemplate(getHibernateTemplate());
		return dipositDao.findActiusByZonaNoTraslladats(zonaId);
	}
	
	/**
	 * Cerca tots las diposits de una zona <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection findDipositsActiusByZonaNoFicticio(Long zonaId) {
		dipositDao.setHibernateTemplate(getHibernateTemplate());
		return dipositDao.findActiusNoFicticioByZona(zonaId);
	}
	
	/**
	 * Cerca tots las diposits de una zona no vacios y que pertenecen a unas determinadas categorias <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection findDipositsActiusNoVaciosByZonaAndCategorias(Long zonaId,Object[] categorias) {
		dipositDao.setHibernateTemplate(getHibernateTemplate());
		return dipositDao.findNoVaciosByZonaAndCategorias(zonaId, categorias);
	}
	
	/**
	 * Cerca tots las diposits de una zona no vacios ni traslladats i que pertenecen a unas determinadas categorias <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection findDipositsActiusNoVaciosNiTraslladatsByZonaAndCategorias(Long zonaId,Object[] categorias) {
		dipositDao.setHibernateTemplate(getHibernateTemplate());
		return dipositDao.findNoVaciosNiTraslladatsByZonaAndCategorias(zonaId, categorias);
	}
	
	/**
	 * Retorna un llista de tots els diposits associats aa una zona de unas categorias determinadas y no pendientes de traslado <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection findNoVaciosByZonaAndCategoriasNoPendnientesDeTraslado(Long zonaId,Object[] categorias) {
		dipositDao.setHibernateTemplate(getHibernateTemplate());
		return dipositDao.findNoVaciosByZonaAndCategoriasNoPendnientesDeTraslado(zonaId, categorias);
	}
	
	/**
	 * Cerca tots las diposits de una zona ,vacios o no llenos que pertenecen a unas determinadas categorias <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection findDipositsActiusNoLlenosByZonaAndCategorias(Long zonaId,Object[] categorias) {
		dipositDao.setHibernateTemplate(getHibernateTemplate());
		return dipositDao.findNoLlenosByZonaAndCategorias(zonaId, categorias);
	}

	/**
	 * Cerca totes les partides d'oliva d'una zona <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public void partidaOlivaModificar(PartidaOliva partidaOliva) {
		partidaOlivaDao.setHibernateTemplate(getHibernateTemplate());
		partidaOlivaDao.makePersistent(partidaOliva);
	}
	/**
	 * Cerca totes les partides d'oliva d'una zona <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO" <!-- end-xdoclet-definition -->
	 */
	public Collection findPartidesNoUsadasByZona(Long zonaId) {
		partidaOlivaDao.setHibernateTemplate(getHibernateTemplate());
		return partidaOlivaDao.findNoUtlizadasByZona(zonaId, new Boolean(true));
	}
	
	/**
	 * Cerca totes les partides d'oliva d'una zona <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO" <!-- end-xdoclet-definition -->
	 */
	public Collection findPartidesPerOliNoUsadasByZona(Long zonaId) {
		partidaOlivaDao.setHibernateTemplate(getHibernateTemplate());
		return partidaOlivaDao.findPerOliNoUtlizadasByZona(zonaId, new Boolean(true));
	}
	
	/**
	 * Cerca totes les partides d'oliva de taula d'una zona <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO" <!-- end-xdoclet-definition -->
	 */
	public Collection findPartidesOlivaTaulaNoUsadasByZona(Long zonaId) {
		partidaOlivaDao.setHibernateTemplate(getHibernateTemplate());
		return partidaOlivaDao.findOlivaTaulaNoUtlitzadesByZona(zonaId, new Boolean(true));
	}
	
	/**
	 * Cerca totes les lotes d'oli d'una zona que pertenecen a una determinadas categorias y que no estan etiquetats <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" 
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * 
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection findLotesNoEtiquetadosByZonaAndCategorias(Long zonaId,Object[] categorias) {
		lotDao.setHibernateTemplate(getHibernateTemplate());
		return lotDao.findNoEtiquetadosByZonaAndCategorias(zonaId, categorias, new Boolean(true));
	}
	
	/**
	 * Cerca totes les lotes d'oli d'una zona que pertenecen a una determinadas categorias y que no estan etiquetats <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" 
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection findLotesByZonaAndCategorias(Long zonaId,Object[] categorias) {
		lotDao.setHibernateTemplate(getHibernateTemplate());
		return lotDao.findByZonaAndCategorias(zonaId, categorias, new Boolean(true), new Boolean(false));
	}
	
	/**
	 * Cerca totes les lotes d'oliva de taula d'una zona que pertenecen a una determinadas categorias y que no estan etiquetats <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" 
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection findLotesOlivaTaulaByZona(Long zonaId) {
		lotDao.setHibernateTemplate(getHibernateTemplate());
		return lotDao.findByZona(zonaId, new Boolean(true), new Boolean(true));
	}
	
	
	/**
	 * Cerca totes les lotes d'oli d'una zona  <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" 
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection findLotesByEtiquetaje(Long etiId) {
		lotDao.setHibernateTemplate(getHibernateTemplate());
		return lotDao.findByEtiquetaje(etiId, new Boolean(true));
	}
	
	/**
	 * Cerca totes les lotes d'oli no vendidos d'una zona  <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" 
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection findLotesNoVendidosByZona(Long zonaId, boolean showCreatTancament) {
		lotDao.setHibernateTemplate(getHibernateTemplate());
		return lotDao.findNoVendidosByZona(zonaId, new Boolean(true), showCreatTancament);
	}
	
	/**
	 * Cerca totes les lotes d'oliva no vendidos d'una zona  <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" 
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection findLotesNoVendidosByZona(Long zonaId, boolean showCreatTancament, boolean mostrarOlivaTaula) {
		lotDao.setHibernateTemplate(getHibernateTemplate());
		return lotDao.findNoVendidosByZona(zonaId, new Boolean(true), showCreatTancament, new Boolean(mostrarOlivaTaula));
	}
	
	/**
	 * Situa un contenidor a dins la imatge d'una zona
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public void loteSituar(Long id, Long establimentId, Long zonaId, Integer posx, Integer posy) {
		lotDao.setHibernateTemplate(getHibernateTemplate());
		Lot lote = lotDao.getById(id);
		if (zonaId == null) {
			lote.setZona(null);
		}
		lote.setPosicioX(posx);
		lote.setPosicioY(posy);
	}
	
	/**
	 * Situa un contenidor a dins la imatge d'una zona
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public void botaSituar(Long id, Long establimentId, Long zonaId, Integer posx, Integer posy) {
		botaDao.setHibernateTemplate(getHibernateTemplate());
		Bota bota = botaDao.getById(id);
		if (zonaId == null) {
			bota.setZona(null);
		}
		bota.setPosicioX(posx);
		bota.setPosicioY(posy);
	}
	
	/**
	 * Cerca una bota amb id
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" 
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Bota botaAmbId(Long id) {
		botaDao.setHibernateTemplate(getHibernateTemplate());
		return botaDao.getById(id);
	}
	
	/**
	 * Cerca un lot amb id
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" 
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Lot lotAmbId(Long id) {
		lotDao.setHibernateTemplate(getHibernateTemplate());
		return lotDao.getById(id);
	}
	
	/**
	 * Cerca un lot amb nom i producte
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public List lotAmbNomIProducte(String nom, Long idProducte) {
		lotDao.setHibernateTemplate(getHibernateTemplate());
		return lotDao.getByNomIProducte(nom, idProducte);
	}
	
	
	/**
	 * Esborra un lot amb id
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 * @generated
	 */
	public void lotEsborrar(Long id) {
		lotDao.setHibernateTemplate(getHibernateTemplate());
		Lot lot = lotDao.getById(id);
		if (lot != null) {
			lotDao.setHibernateTemplate(getHibernateTemplate());
			lotDao.makeTransient(lot);
		}
	}
	
	/**
	 * Cerca si existen depósitos activos en una zona zonaId <!--
	 * begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Boolean existenDipositsActiusEnZona(Long zonaId) {
		dipositDao.setHibernateTemplate(getHibernateTemplate());
		return dipositDao.existenDipositsActiusEnZona(zonaId);
	}

	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Diposit dipositAmbId(Long id) {
		dipositDao.setHibernateTemplate(getHibernateTemplate());
		return dipositDao.getById(id);
	}

	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public PartidaOliva partidaAmbId(Long id) {
		partidaOlivaDao.setHibernateTemplate(getHibernateTemplate());
		return partidaOlivaDao.getById(id);
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public PartidaFonoll partidaFonollAmbId(Long id) {
		partidaFonollDao.setHibernateTemplate(getHibernateTemplate());
		return partidaFonollDao.getById(id);
	}
	
	/**
	 * Totes les partides de fonoll
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection findPartidesFonollByEstabliment(Long idEstabliment, Boolean valid) {
		partidaFonollDao.setHibernateTemplate(getHibernateTemplate());
		return partidaFonollDao.findByEstabliment(idEstabliment, valid);
	}
	
	/**
	 * Situa un contenidor a dins la imatge d'una zona
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Elaboracio elaboracioAmbId(Long id, Boolean valid) {
		elaboracioDao.setHibernateTemplate(getHibernateTemplate());
		return elaboracioDao.getById(id,valid);
	}
	
	/**
	 * Situa un contenidor a dins la imatge d'una zona
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection findLotsTalcPerEstabliment(Long id, Boolean valid) {
		elaboracioDao.setHibernateTemplate(getHibernateTemplate());
		return elaboracioDao.findLotsTalcPerEstabliment(id,valid);
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Long elaboracioModificarImpresions(
			Long elaboracioId,
			Integer numPrintsDocRendiment) {
		elaboracioDao.setHibernateTemplate(getHibernateTemplate());
		Elaboracio elaboracio = elaboracioDao.getById(elaboracioId,true);
		elaboracio.setNumPrintsDocRendiment(numPrintsDocRendiment);
		elaboracioDao.setHibernateTemplate(getHibernateTemplate());
		elaboracioDao.makePersistent(elaboracio);
		return elaboracio.getId();

	}
	
	/**
	 * Situa un contenidor a dins la imatge d'una zona
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public void dipositSituar(Long id, Long establimentId, Long zonaId, Integer posx, Integer posy) {
		dipositDao.setHibernateTemplate(getHibernateTemplate());
		Diposit diposit = dipositDao.getById(id);

		if (zonaId == null) {
			diposit.setZona(null);
		}
		diposit.setPosicioX(posx);
		diposit.setPosicioY(posy);
	}
	
	/**
	 * Situa una partida d'oliva a dins la imatge d'una zona
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public void partidaSituar(Long id, Long establimentId, Long zonaId,
			Integer posx, Integer posy) {
		partidaOlivaDao.setHibernateTemplate(getHibernateTemplate());
		PartidaOliva partida = partidaOlivaDao.getById(id);
		
		if (zonaId == null) {
			partida.setZona(null);
		}
		partida.setPosicioX(posx);
		partida.setPosicioY(posy);
	}

	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR" 
	 * @ejb.permission role-name="OLI_DOGESTOR" <!-- end-xdoclet-definition -->
	 */
	public Long dipositCrear(	Double capacitat, 
								String codiAssignat, 
								Long idEstabliment,
								Long idZona, 
								Integer idMaterialDiposit, 
								String observaciones,
								Boolean activo, 
								Boolean fictici,
								Boolean deEnvasadora) {
		Establiment establiment = new Establiment();
		establiment.setId(idEstabliment);
		
		Zona zona = null;
		if(idZona != null){
			zona = new Zona();
			zona.setId(idZona);
		}
		
		MaterialDiposit materialDiposit = null;
		if (idMaterialDiposit != null) {
			materialDiposit = new MaterialDiposit();
			materialDiposit.setId(idMaterialDiposit);
		}

		Diposit diposit = new Diposit();
		diposit.setZona(zona);
		diposit.setEstabliment(establiment);
		diposit.setActiu(activo);
		diposit.setCodiAssignat(codiAssignat);
		diposit.setFictici(fictici);
		diposit.setMaterialDiposit(materialDiposit);
		diposit.setObservacions(observaciones);
		diposit.setCapacitat(capacitat);
		diposit.setPosicioX(Integer.valueOf("0"));
		diposit.setPosicioY(Integer.valueOf("0"));
		
		diposit.setPrecintat(false);
		diposit.setDeEnvasadora(deEnvasadora);
		
		dipositDao.setHibernateTemplate(getHibernateTemplate());
		dipositDao.makePersistent(diposit);
		return diposit.getId();

	}

	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR" 
	 * @ejb.permission role-name="OLI_DOGESTOR" <!-- end-xdoclet-definition -->
	 */
	public Long dipositModificar(	Double capacitat, 
									String codiAssignant, 
									Long idEstabliment,
									Long idZona, 
									Integer idMaterialDiposit, 
									String observaciones,
									Boolean activo, 
									Boolean fictici, 
									Long dipositId,
									Boolean deEnvasadora) {
		dipositDao.setHibernateTemplate(getHibernateTemplate());
		Diposit diposit = dipositDao.getById(dipositId);
		Establiment establiment = new Establiment();
		Zona zona = new Zona();
		MaterialDiposit materialDiposit = null;
		if (idMaterialDiposit != null) {
			materialDiposit = new MaterialDiposit();
			materialDiposit.setId(idMaterialDiposit);
		}
		establiment.setId(idEstabliment);
		zona.setId(idZona);
		
		//Reiniciam les coordenades x i y si cal
		if(diposit.getZona() != null && !diposit.getZona().getId().equals(idZona)){
			diposit.setPosicioX(0);
			diposit.setPosicioY(0);
		}
		
		diposit.setZona(zona);
		diposit.setMaterialDiposit(materialDiposit);			
		diposit.setCapacitat(capacitat);
		diposit.setCodiAssignat(codiAssignant);
		diposit.setObservacions(observaciones);
		diposit.setActiu(activo);
		diposit.setFictici(fictici);
		diposit.setDeEnvasadora(deEnvasadora);

		dipositDao.setHibernateTemplate(getHibernateTemplate());
		dipositDao.makePersistent(diposit);
		return diposit.getId();
	}

	
	/**
	 * Cerca la campanya actual <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="*" <!-- end-xdoclet-definition -->
	 */
	public Long campanyaCercaActual() {
		campanyaDao.setHibernateTemplate(getHibernateTemplate());
		return campanyaDao.getCampanyaActual();
	}
	
	/**
	 * Devuelve todas las campanyas <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="*" <!-- end-xdoclet-definition -->
	 */
	public Collection campanyaCercaTotes() {
		campanyaDao.setHibernateTemplate(getHibernateTemplate());
		return campanyaDao.findAll();
	}

	/**
	 * Cerca una campanya amb id <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 *  <!-- end-xdoclet-definition -->
	 */
	public Campanya campanyaAmbId(Integer id) {
		campanyaDao.setHibernateTemplate(getHibernateTemplate());
		return campanyaDao.campanyaAmbId(id);
	}

	/**
	 * Validacion de que no exista otra campaña con el mismo nombre <!--
	 * begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR" <!-- end-xdoclet-definition -->
	 */
	public boolean existeCampanyaMismoNombre(String nom) {
		campanyaDao.setHibernateTemplate(getHibernateTemplate());
		return (campanyaDao.existeCampanyaMismoNombre(nom));
	}

	/**
	 * Consigue el numero de descomposiciones activas para la campanya actual
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR" <!-- end-xdoclet-definition -->
	 */
	public Long getNumeroDescomposicionsActivesCampanyaActual() {
		campanyaDao.setHibernateTemplate(getHibernateTemplate());
		return (campanyaDao.getNumeroDescomposicionsActivesCampanyaActual());
	}

	/**
	 * Creacio d'una nova campanya
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Long campanyaCrear(Campanya campanya, String novaCampanya) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Transaction trans = null;
		try {
			trans = session.beginTransaction();
			// Pasos:
			campanyaDao.setHibernateTemplate(getHibernateTemplate());
			Long campanyaActual = campanyaDao.getCampanyaActual();
			
			// Posam la data fi a la campanya actual
			Campanya ca = campanyaDao.campanyaAmbId(campanyaActual.intValue());
			Calendar cal = new GregorianCalendar();
			cal.setTime(campanya.getData());
			cal.add(Calendar.DATE, -1);
			ca.setDataFi(cal.getTime());
			
			// 1.- Recuperar (en hibernate) tots els olivicultors en curs (encara no s'ha creat campanya nova)
			olivicultorDao.setHibernateTemplate(getHibernateTemplate());
			Iterator olivicultors = olivicultorDao.findAllOlivicultorsByCampanyaOrderedByCodiDOAsc(campanyaActual).iterator();
			// 2.- Recuperar (en hibernate) totes les taxes en curs (encara no s'ha creat campanya nova)
			taxaDao.setHibernateTemplate(getHibernateTemplate());
			Taxa taxaActual = taxaDao.getTaxaActual();
			// 3.- Recuperar (en hibernate) tots els establiments en curs (encara no s'ha creat campanya nova)
			establimentDao.setHibernateTemplate(getHibernateTemplate());
			Iterator establiments = establimentDao.findAllByCampanya(campanyaActual).iterator();
			// 4.- Crear nova campanya
			campanyaDao.makePersistent(campanya, session);

			// 5.- Duplicar olivicultors, fincas, plantacions i descomposicions de plantacio
			while (olivicultors.hasNext()) {
				Olivicultor olivicultor = (Olivicultor) olivicultors.next();
				guardaHistoricOlivicultorIFinques(olivicultor);
				olivicultor.setCampanya(campanya);
				
				// Si l'olivicultor no està subvencionat, posam la cartilla a fals (ha de pagar)
				if (olivicultor.getSubvencionat() == null || !olivicultor.getSubvencionat().booleanValue()) {
					olivicultor.setCartilla(new Boolean(false)); 
				}
				
				olivicultorDao.makePersistent(olivicultor, session);
			}

			// 6.- Duplicar taxes
			if (taxaActual != null) {
				Taxa taxa = copiaTaxa(taxaActual);
				taxa.setId(campanya.getId());
				taxaDao.makePersistent(taxa, session);
			}

			// 7.- Duplicar establiments, zones i diposits (i entrades si es necessari)
			while (establiments.hasNext()) {
				Establiment establiment = (Establiment) establiments.next();
				guardaHistoricEstablimentZonesIDiposits(establiment);
				establiment.setCampanya(campanya);
				
				establimentDao.setHibernateTemplate(getHibernateTemplate());
				establimentDao.makePersistent(establiment, session);
			}
			// IMPORTANT: els codis originals "codori"
			if (trans != null) {
				trans.commit();
			}
		} catch (Exception e) {
			logger.error("EXCEPTION", e);
			try {
				if (trans != null) {
					trans.rollback();
				}
			} catch (Exception ex) {
				logger.error("EXCEPTION", ex);
			}
		}
		return campanya.getId();
	}


	/**
	 * Constructor necesari per a la duplicacio de zones en la campanya
	 * @param zona
	 */
	public Zona copiaZona(Zona zona) {
		Zona novaZona = new Zona();
		novaZona.setActiu(zona.getActiu());
		novaZona.setDefecte(zona.getDefecte());
		novaZona.setDefecteTrasllat(zona.getDefecteTrasllat());
		novaZona.setFictici(zona.getFictici());
		novaZona.setImatgePlanol(zona.getImatgePlanol());
		novaZona.setNom(zona.getNom());
		novaZona.setObservacions(zona.getObservacions());
		novaZona.setIdOriginal(zona.getIdOriginal());
		return novaZona;
	}

	/**
	 * Constructor necesari per a la duplicacio de taxes en la campanya
	 * 
	 * @param taxa
	 */
	public Taxa copiaTaxa(Taxa taxa) {
		Taxa novaTaxa = new Taxa();
		novaTaxa.setTaxaContraEtiqueta(taxa.getTaxaContraEtiqueta());
		novaTaxa.setTaxaPlantacioMajorIgual75(taxa.getTaxaPlantacioMajorIgual75());
		novaTaxa.setTaxaPlantacioMenor75(taxa.getTaxaPlantacioMenor75());
		novaTaxa.setTaxaVolumEnvasar(taxa.getTaxaVolumEnvasar());
		return novaTaxa;
	}

	/**
	 * Constructor necesari per a la duplicacio d'establiments en la campanya
	 * 
	 * @param establiment
	 */
	public Establiment copiaEstabliment(Establiment es) {
		Establiment nouEstabliment = new Establiment();
		nouEstabliment.setActiu(es.getActiu());
		nouEstabliment.setCapacitatProduccio(es.getCapacitatProduccio());
		nouEstabliment.setCif(es.getCif());
		nouEstabliment.setCodiPostal(es.getCodiPostal());
		nouEstabliment.setCodiRB(es.getCodiRB());
		nouEstabliment.setCodiRC(es.getCodiRC());
		nouEstabliment.setDireccio(es.getDireccio());
		nouEstabliment.setEmail(es.getEmail());
		nouEstabliment.setEnvassamentManual(es.getEnvassamentManual());
		nouEstabliment.setEtiquetatManual(es.getEtiquetatManual());
		nouEstabliment.setFax(es.getFax());
		nouEstabliment.setIdOriginal(es.getIdOriginal());
		nouEstabliment.setNom(es.getNom());
		nouEstabliment.setNumeroTreballadors(es.getNumeroTreballadors());
		nouEstabliment.setObservacions(es.getObservacions());
		nouEstabliment.setPoblacio(es.getPoblacio());
		nouEstabliment.setSolicitant(es.getSolicitant());
		nouEstabliment.setSuperficie(es.getSuperficie());
		nouEstabliment.setTelefon(es.getTelefon());
		nouEstabliment.setTemperaturaMaximaPasta(es.getTemperaturaMaximaPasta());
		nouEstabliment.setTipusEstabliment(es.getTipusEstabliment());
		nouEstabliment.setUnitatMesura(es.getUnitatMesura());
		//nouEstabliment.setUsuari(es.getUsuari());
		Set usuaris = new HashSet();
		for (Iterator it = es.getUsuaris().iterator(); it.hasNext();) {
			Usuari u = (Usuari)it.next();
			Set establiments = u.getEstabliments();
			establiments.add(nouEstabliment);
			u.setEstabliments(establiments);
			usuaris.add(u);
		}
		nouEstabliment.setUsuaris(usuaris);
		
		nouEstabliment.setDataBaixa(es.getDataBaixa());
		nouEstabliment.setNomResponsable(es.getNomResponsable());
		nouEstabliment.setCifResponsable(es.getCifResponsable());
		
		return nouEstabliment;
	}

	/**
	 * Constructor necesari per a la duplicacio de diposits en la campanya
	 * 
	 * @param diposit
	 */
	public Diposit copiaDiposit(Diposit d) {
		Diposit nouDiposit = new Diposit();
		nouDiposit.setAcidesa(d.getAcidesa());
		nouDiposit.setActiu(d.getActiu());
		nouDiposit.setCapacitat(d.getCapacitat());
//		nouDiposit.setCategoriaOli(d.getCategoriaOli());
		nouDiposit.setPartidaOli(d.getPartidaOli());
		nouDiposit.setCodiAssignat(d.getCodiAssignat());
		nouDiposit.setFictici(d.getFictici());
		nouDiposit.setIdOriginal(d.getIdOriginal());
		nouDiposit.setMaterialDiposit(d.getMaterialDiposit());
		nouDiposit.setObservacions(d.getObservacions());
		nouDiposit.setPosicioX(d.getPosicioX());
		nouDiposit.setPosicioY(d.getPosicioY());
		nouDiposit.setVolumActual(d.getVolumActual());
		return nouDiposit;
	}
	
	/**
	 * Constructor necesari per a la duplicacio de diposits en la campanya
	 * 
	 * @param diposit
	 */
	public EntradaDiposit copiaEntradaDiposit(EntradaDiposit ed, String novaCampanya) {
		EntradaDiposit novaEntrada = new EntradaDiposit();
		novaEntrada.setAcidesa(ed.getAcidesa());
		novaEntrada.setCategoriaOli(ed.getCategoriaOli());
		novaEntrada.setPartidaOli(ed.getPartidaOli());
		novaEntrada.setData(ed.getData());
		novaEntrada.setElaboracio(ed.getElaboracio());
		novaEntrada.setLitres(ed.getLitres());
		novaEntrada.setObservacions(novaCampanya);
		// Creamos nueva traza y la asignamos a la entrada de la campaña anterior
		Traza traza = new Traza(new Integer(Constants.CODI_TRAZA_TIPUS_ENTRADA_DIPOSIT));
		traza.getTrazasForTtrCodtrapare().add(ed.getTraza());
		trazaDao.setHibernateTemplate(getHibernateTemplate());
		trazaDao.makePersistent(traza);
		novaEntrada.setTraza(traza);
		return novaEntrada;
	}
	
	/**
	 * Constructor necesari per a la duplicacio dels olivicultors en la campanya
	 * 
	 * @param olivicultor
	 */
	public Olivicultor copiaOlivicultor(Olivicultor ol) {
		Olivicultor nouOlivicultor = new Olivicultor();
		nouOlivicultor.setAltaDO(ol.getAltaDO());
		if (ol.getSubvencionat() != null && ol.getSubvencionat().booleanValue()) {
			nouOlivicultor.setCartilla(ol.getCartilla());
		} else {
			nouOlivicultor.setCartilla(new Boolean(false)); // Quan es copien els olivicultors cartilla = false
		}
		nouOlivicultor.setCodiDO(ol.getCodiDO());
		nouOlivicultor.setCodiDOExperimental(ol.getCodiDOExperimental());
		nouOlivicultor.setCodiPostal(ol.getCodiPostal());
		nouOlivicultor.setCompteBancari(ol.getCompteBancari());
		nouOlivicultor.setDireccio(ol.getDireccio());
		nouOlivicultor.setEmail(ol.getEmail());
		nouOlivicultor.setFax(ol.getFax());
		nouOlivicultor.setIdOriginal(ol.getIdOriginal());
		nouOlivicultor.setNif(ol.getNif());
		nouOlivicultor.setNom(ol.getNom());
		nouOlivicultor.setObservacions(ol.getObservacions());
		nouOlivicultor.setPoblacio(ol.getPoblacio());
		nouOlivicultor.setSubvencionat(ol.getSubvencionat());
		nouOlivicultor.setTelefon(ol.getTelefon());
		nouOlivicultor.setUidRfid(ol.getUidRfid());
		nouOlivicultor.setUsuari(ol.getUsuari());
		return nouOlivicultor;
	}

	/**
	 * Constructor necesari per a la duplicacio de les finques en la campanya
	 * 
	 * @param finca
	 */
	public Finca copiaFinca(Finca f) {
		Finca novaFinca = new Finca();
		novaFinca.setActiu(f.getActiu());
		novaFinca.setIdOriginal(f.getIdOriginal());
		novaFinca.setNom(f.getNom());
		novaFinca.setObservacions(f.getObservacions());
		return novaFinca;
	}

	/**
	 * Constructor necesari per a la duplicacio de les plantacions en la
	 * campanya
	 * 
	 * @param plantacio
	 */
	public Plantacio copiaPlantacio(Plantacio p) {
		Plantacio novaPlantacio = new Plantacio();
		novaPlantacio.setActiu(p.getActiu());
		novaPlantacio.setAnyPlantacio(p.getAnyPlantacio());
		novaPlantacio.setIdOriginal(p.getIdOriginal());
		novaPlantacio.setMunicipi(p.getMunicipi());
		novaPlantacio.setObservacions(p.getObservacions());
		novaPlantacio.setParcela(p.getParcela());
		novaPlantacio.setPoligon(p.getPoligon());
		novaPlantacio.setRegadiu(p.getRegadiu());
		novaPlantacio.setNomComplet(p.getNomComplet());
		novaPlantacio.setDeBaixa(p.getDeBaixa());
		return novaPlantacio;
	}

	/**
	 * Constructor necesari per a la duplicacio de les descomposicions de
	 * plantacions en la campanya
	 * 
	 * @param descomposició plantació
	 */
	public DescomposicioPlantacio copiaDescomposicio(DescomposicioPlantacio dp) {
		DescomposicioPlantacio novaDescomposicioPlantacio = new DescomposicioPlantacio();
		novaDescomposicioPlantacio.setIdOriginal(dp.getIdOriginal());
		novaDescomposicioPlantacio.setMaxProduccio(dp.getMaxProduccio());
		novaDescomposicioPlantacio.setNumeroOliveres(dp.getNumeroOliveres());
		novaDescomposicioPlantacio.setObservacions(dp.getObservacions());
		novaDescomposicioPlantacio.setSuperficie(dp.getSuperficie());
		novaDescomposicioPlantacio.setVarietatOliva(dp.getVarietatOliva());
		return novaDescomposicioPlantacio;
	}
	
	
	/**
	 * crea o modifica una factura amb id <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR" 
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public void facturaCreaModifica(Factura factura){
		facturaDao.setHibernateTemplate(getHibernateTemplate());
		facturaDao.makePersistent(factura);
	}	
	
	
	/**
	 * Cerca una factura amb id
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Factura facturaCercaOliId(Long id){
		facturaDao.setHibernateTemplate(getHibernateTemplate());
		return facturaDao.findByOlivicultorId(id);
	}	
	
	/**
	 * Cerca una factura amb id
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Factura facturaAnyCercaOliId(int any, Long id){
		facturaDao.setHibernateTemplate(getHibernateTemplate());
		return facturaDao.findByAnyOlivicultorId(any, id);
	}	
	

	/**
	 * Cerca maxim de numeracio per un any <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR" 
	 * <!-- end-xdoclet-definition -->
	 */
	public Integer facturaGetMaxNumeracionParaAnyo(Integer anyo){
		facturaDao.setHibernateTemplate(getHibernateTemplate());
		return facturaDao.getMaxNumeracionParaAnyo(anyo);
	}		
	
	
	/**
	 * Comprueba si existe la factura <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR" 
	 * <!-- end-xdoclet-definition -->
	 */
	public boolean facturaExisteByOliId(Long id){
		facturaDao.setHibernateTemplate(getHibernateTemplate());
		return facturaDao.facturaExisteByOliId(id);
	}	
	
	/**
	 * Comprueba si existe la factura <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Boolean existenAccionesPendientesByEstablecimiento(Long Establimentid){
		trasllatDao.setHibernateTemplate(getHibernateTemplate());
		return trasllatDao.existenAccionesPendientesByEstablecimiento(Establimentid);
	}
	

	/**
	 * Comprueba si existe la factura <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection findTrasllatsNoCompletados(){
		trasllatDao.setHibernateTemplate(getHibernateTemplate());
		return trasllatDao.findTrasllatsNoCompletados();
	}
	
	/**
	 * Recupera las facturas de la campaña actual <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection facturaFindByCampanyaAltaDOCartilla(Long campanyaId){
		facturaDao.setHibernateTemplate(getHibernateTemplate());
		return facturaDao.facturaFindByCampanyaAltaDOCartilla(campanyaId);
	}		
	
	/**
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition --> 
	 * @generated
	 */
	public Collection lotesInfo(Object[] seleccio) {
		Collection c = null;
		if (seleccio != null) {
			lotDao.setHibernateTemplate(getHibernateTemplate());
			return lotDao.findInfo(seleccio, new Boolean(true), null);			
		}		
		return c;
	}
	
	/**
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition --> 
	 * @generated
	 */
	public Collection lotesOlivaTaulaInfo(Object[] seleccio) {
		Collection c = null;
		if (seleccio != null) {
			lotDao.setHibernateTemplate(getHibernateTemplate());
			return lotDao.findInfo(seleccio, new Boolean(true), new Boolean(true));			
		}		
		return c;
	}
	
	/**
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition --> 
	 * @generated
	 */
	public Collection dipositsInfo(Object[] seleccio) {
		Collection c = null;
		if (seleccio != null) {
			dipositDao.setHibernateTemplate(getHibernateTemplate());
			return dipositDao.findInfo(seleccio);
		}		
		return c;
	}
	/**
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition --> 
	 * @generated
	 */
	public Collection partidasInfo(Object[] seleccio) {
		Collection c = null;
		if (seleccio != null) {
			partidaOlivaDao.setHibernateTemplate(getHibernateTemplate());
			return partidaOlivaDao.findInfo(seleccio, new Boolean(true));
			
		}
		
		return c;
	}
	
	/**
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition --> 
	 * @generated
	 */
	public Collection botesInfo(Object[] seleccio) {
		Collection c = null;
		if (seleccio != null) {
			botaDao.setHibernateTemplate(getHibernateTemplate());
			return botaDao.findInfo(seleccio);
		}		
		return c;
	}

	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection varietatsOli() {
		varietatOliDao.setHibernateTemplate(getHibernateTemplate());
		return varietatOliDao.findAll();

	}	
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public int calcularVarietatOli(Long[] idsSensorial, String[] valorsSensorial, Long[] idsFisicoQuimic, String[] valorsFisicoQuimic, Double acidesa, Double ACIDESA_VERGE_EXTRA, Double ACIDESA_VERGE){
		int OLI_VERGE_EXTRA = 1;
		int OLI_VERGE = 2;
		int OLI_LLAMPANT = 3;
		int SENSE_CATEGORIA = 0;
		
		System.out.println("AQUIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII555555555");
		System.out.println("VERGE EXTRA ES "+ ACIDESA_VERGE_EXTRA);
		System.out.println("VERGE ES "+ ACIDESA_VERGE);
		
		//Passam els paràmetres sensorials i fisicoquímics dins un sol array per així només haver de
		//fer una comprovació
		
		//NO FEIM COMPROVACIO DE SENSORIAL (NOU REQUISIT)
//		Long[] ids = new Long[idsSensorial.length + idsFisicoQuimic.length];
//		String[] valors = new String[valorsSensorial.length + valorsFisicoQuimic.length];
		
		Long[] ids = new Long[idsFisicoQuimic.length];
		String[] valors = new String[valorsFisicoQuimic.length];
		
		int index = 0;
		
		//NO FEIM COMPROVACIO DE SENSORIAL (NOU REQUISIT)
		/*for(int i=0; i<idsSensorial.length; i++){
			ids[index] = idsSensorial[i];
			valors[index] = valorsSensorial[i];
			index++;
		}*/
		
		for(int i=0; i<idsFisicoQuimic.length; i++){
			ids[index] = idsFisicoQuimic[i];
			valors[index] = valorsFisicoQuimic[i];
			index++;
		}
		
		//COMPROVACIONS DELS PARÀMETRES (SENSORIAL I FISICOQUIMIC)
		boolean esVergeExtra = true;
		boolean esVerge = true;
		boolean esLlampant = true;
		
		try{
			//COMPROVEM L'ACIDESA
			if(acidesa > ACIDESA_VERGE_EXTRA){
				esVergeExtra = false;
			}
			
			if(acidesa > ACIDESA_VERGE){
				esVerge = false;
			}
			
			
			
			//OLI VERGE EXTRA
			for(int i=0; i<ids.length; i++){
				analiticaParametreTipusDao.setHibernateTemplate(getHibernateTemplate());
				AnaliticaParametreTipus analiticaParametreTipus = analiticaParametreTipusDao.getById(ids[i]);
				
				if(esVergeExtra){
					esVergeExtra = esVarietatOliCorrecte(analiticaParametreTipus.getId(), new Long(OLI_VERGE_EXTRA),valors[i]);
				}
			}
			
			//OLI VERGE
			if(!esVergeExtra && esVerge){
				for(int i=0; i<ids.length; i++){
					analiticaParametreTipusDao.setHibernateTemplate(getHibernateTemplate());
					AnaliticaParametreTipus analiticaParametreTipus = analiticaParametreTipusDao.getById(ids[i]);
					
					if(!esVergeExtra && esVerge){
						esVerge = esVarietatOliCorrecte(analiticaParametreTipus.getId(), new Long(OLI_VERGE),valors[i]);
					}
				}
			}
			
			//OLI LLAMPANT
			if(!esVergeExtra && !esVerge && esLlampant){
				for(int i=0; i<ids.length; i++){
					analiticaParametreTipusDao.setHibernateTemplate(getHibernateTemplate());
					AnaliticaParametreTipus analiticaParametreTipus = analiticaParametreTipusDao.getById(ids[i]);
					
					if(!esVergeExtra && !esVerge && esLlampant){
						esLlampant = esVarietatOliCorrecte(analiticaParametreTipus.getId(), new Long(OLI_LLAMPANT),valors[i]);
					}
				}
			}
		} catch(Exception e){
			return SENSE_CATEGORIA;
		}
		
		if(esVergeExtra) return OLI_VERGE_EXTRA;
		if(esVerge) return OLI_VERGE;
		if(esLlampant) return OLI_LLAMPANT;
		
		return SENSE_CATEGORIA;
	}
	
	private boolean esVarietatOliCorrecte(Long idAnaliticaParametreTipus, Long idVarietat, String valAComprovar) throws Exception{
		try{
			boolean esCorrecte = true;
			
			analiticaParametreDao.setHibernateTemplate(getHibernateTemplate());
			List parametres = (List)analiticaParametreDao.findByTipusIVarietat(idAnaliticaParametreTipus, idVarietat);
			
			for(int i=0; i<parametres.size(); i++){
				
				AnaliticaParametre param = (AnaliticaParametre)parametres.get(i);
				
				String valorParametre = param.getValor();
				String valorAComprovar = valAComprovar;
				
				//Si es obligatori o si no es obligatori pero han introduit valor
				if(   param.getObligatori() || 
					(!param.getObligatori() && valorAComprovar != null && !valorAComprovar.equals("")  )){
				
					if(esCorrecte){
						switch(param.getAnaliticaParametreTipus().getTipus()){
							case Constants.PARAMETRE_TIPUS_STRING: 
								switch(param.getOperador()){
									case Constants.PARAMETRE_OPERADOR_EQ: if(!(valorAComprovar.equals(valorParametre)))esCorrecte = false; break;
									case Constants.PARAMETRE_OPERADOR_NE: if(!(!(valorAComprovar.equals(valorParametre)))) esCorrecte = false; break;
									case Constants.PARAMETRE_OPERADOR_LT: 
									case Constants.PARAMETRE_OPERADOR_LE:
									case Constants.PARAMETRE_OPERADOR_GT:
									case Constants.PARAMETRE_OPERADOR_GE: esCorrecte = false; break;
								}
							break;
							
							case Constants.PARAMETRE_TIPUS_SENCER:
								Integer valorParametreSencer = Integer.parseInt(valorParametre);
								Integer valorAComprovarSencer = Integer.parseInt(valorAComprovar);
								switch(param.getOperador()){
									case Constants.PARAMETRE_OPERADOR_EQ: if(!(valorAComprovarSencer.equals(valorParametreSencer)))esCorrecte = false; break;
									case Constants.PARAMETRE_OPERADOR_NE: if(!(!(valorAComprovarSencer.equals(valorParametreSencer)))) esCorrecte = false; break;
									case Constants.PARAMETRE_OPERADOR_LT: if(!(valorAComprovarSencer < valorParametreSencer)) esCorrecte = false; break;
									case Constants.PARAMETRE_OPERADOR_LE: if(!(valorAComprovarSencer <= valorParametreSencer)) esCorrecte = false; break;
									case Constants.PARAMETRE_OPERADOR_GT: if(!(valorAComprovarSencer > valorParametreSencer)) esCorrecte = false; break;
									case Constants.PARAMETRE_OPERADOR_GE: if(!(valorAComprovarSencer >= valorParametreSencer)) esCorrecte = false; break;
								}
							break;
							
							case Constants.PARAMETRE_TIPUS_DECIMAL:
								Double valorParametreDouble = Double.parseDouble(valorParametre);
								Double valorAComprovarDouble = Double.parseDouble(valorAComprovar);
								switch(param.getOperador()){
									case Constants.PARAMETRE_OPERADOR_EQ: if(!(valorAComprovarDouble.equals(valorParametreDouble)))esCorrecte = false; break;
									case Constants.PARAMETRE_OPERADOR_NE: if(!(!(valorAComprovarDouble.equals(valorParametreDouble)))) esCorrecte = false; break;
									case Constants.PARAMETRE_OPERADOR_LT: if(!(valorAComprovarDouble < valorParametreDouble)) esCorrecte = false; break;
									case Constants.PARAMETRE_OPERADOR_LE: if(!(valorAComprovarDouble <= valorParametreDouble)) esCorrecte = false; break;
									case Constants.PARAMETRE_OPERADOR_GT: if(!(valorAComprovarDouble > valorParametreDouble)) esCorrecte = false; break;
									case Constants.PARAMETRE_OPERADOR_GE: if(!(valorAComprovarDouble >= valorParametreDouble)) esCorrecte = false; break;
								}
							break;
						}
					}
				}
			}
			return esCorrecte;
		} catch(Exception e){
			return false;
		}
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public VarietatOli getVarietatOliById(Integer id) {
		varietatOliDao.setHibernateTemplate(getHibernateTemplate());
		return varietatOliDao.getById(id);

	}
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Analitica analiticaAmbId(Long id) {
		analiticaDao.setHibernateTemplate(getHibernateTemplate());
		return analiticaDao.getById(id);
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public AutocontrolOliva autocontrolAmbId(Long id) {
		autocontrolDao.setHibernateTemplate(getHibernateTemplate());
		return autocontrolDao.getById(id);
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public void analiticaEsborrar(Analitica analitica) {
		analiticaDao.setHibernateTemplate(getHibernateTemplate());
		analiticaDao.makeTransient(analitica);
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public void autocontrolEsborrar(AutocontrolOliva autocontrol) {
		autocontrolDao.setHibernateTemplate(getHibernateTemplate());
		autocontrolDao.makeTransient(autocontrol);
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection analiticaParametreLlistat() {
		analiticaParametreDao.setHibernateTemplate(getHibernateTemplate());
		return analiticaParametreDao.findAll();
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR" 
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection analiticaParametreByParametreTipusIVarietat(Long idAnaliticaParametreTipus, Integer idVarietat) {
		analiticaParametreDao.setHibernateTemplate(getHibernateTemplate());
		return analiticaParametreDao.findByTipusIVarietat(idAnaliticaParametreTipus, Long.valueOf(idVarietat));
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR" 
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection analiticaParametreByParametreTipus(Long idAnaliticaParametreTipus) {
		analiticaParametreDao.setHibernateTemplate(getHibernateTemplate());
		return analiticaParametreDao.findByTipus(idAnaliticaParametreTipus);
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR" 
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public AnaliticaParametre analiticaParametreAmbId(Long id) {
		analiticaParametreDao.setHibernateTemplate(getHibernateTemplate());
		return analiticaParametreDao.getById(id);
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR" 
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection analiticaParametreTipusLlistat() {
		analiticaParametreTipusDao.setHibernateTemplate(getHibernateTemplate());
		return analiticaParametreTipusDao.findAll();
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR" 
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection analiticaParametreTipusLlistatActius() {
		analiticaParametreTipusDao.setHibernateTemplate(getHibernateTemplate());
		return analiticaParametreTipusDao.findAllActius();
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR" 
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public List analiticaParametreTipusLlistatActius(int tipuscontrol) {
		analiticaParametreTipusDao.setHibernateTemplate(getHibernateTemplate());
		return analiticaParametreTipusDao.findAllActiusByTipusControl(tipuscontrol);
	}
	
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR" 
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public AnaliticaParametreTipus analiticaParametreTipusAmbId(Long id) {
		analiticaParametreTipusDao.setHibernateTemplate(getHibernateTemplate());
		return analiticaParametreTipusDao.getById(id);
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR" 
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * <!-- end-xdoclet-definition -->
	 */
	public void analiticaParametreCrear(AnaliticaParametre analiticaParametre) {
		analiticaParametreDao.setHibernateTemplate(getHibernateTemplate());
		analiticaParametreDao.makePersistent(analiticaParametre);
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR" 
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * <!-- end-xdoclet-definition -->
	 */
	public void analiticaParametreModificar(AnaliticaParametre analiticaParametre) {
		analiticaParametreDao.setHibernateTemplate(getHibernateTemplate());
		analiticaParametreDao.makePersistent(analiticaParametre);
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR" 
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * <!-- end-xdoclet-definition -->
	 */
	public void analiticaParametreEsborrar(Long id) {
		analiticaParametreDao.setHibernateTemplate(getHibernateTemplate());
		AnaliticaParametre ap = analiticaParametreDao.getById(id);
		if(ap != null){
			analiticaParametreDao.setHibernateTemplate(getHibernateTemplate());
			analiticaParametreDao.makeTransient(ap);
		}
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR" 
	 * @ejb.permission role-name="OLI_ADMINISTRACIO" <!-- end-xdoclet-definition -->
	 */
	public void analiticaParametreTipusCrear(AnaliticaParametreTipus analiticaParametreTipus) {
		analiticaParametreTipusDao.setHibernateTemplate(getHibernateTemplate());
		analiticaParametreTipusDao.makePersistent(analiticaParametreTipus);
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR" 
	 * @ejb.permission role-name="OLI_ADMINISTRACIO" <!-- end-xdoclet-definition -->
	 */
	public void analiticaParametreTipusModificar(AnaliticaParametreTipus analiticaParametreTipus) {
		analiticaParametreTipusDao.setHibernateTemplate(getHibernateTemplate());
		analiticaParametreTipusDao.makePersistent(analiticaParametreTipus);
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR" 
	 * @ejb.permission role-name="OLI_ADMINISTRACIO" <!-- end-xdoclet-definition -->
	 */
	public void analiticaParametreTipusEsborrar(Long id) {
		analiticaParametreTipusDao.setHibernateTemplate(getHibernateTemplate());
		AnaliticaParametreTipus apt = analiticaParametreTipusDao.getById(id);
		if(apt != null){
			analiticaParametreTipusDao.setHibernateTemplate(getHibernateTemplate());
			analiticaParametreTipusDao.makeTransient(apt);
		}
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public void analiticaValorCrear(AnaliticaValor analiticaValor) {
		analiticaValorDao.setHibernateTemplate(getHibernateTemplate());
		analiticaValorDao.makePersistent(analiticaValor);
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public void analiticaValorEsborrar(AnaliticaValor analiticaValor) {
		analiticaValorDao.setHibernateTemplate(getHibernateTemplate());
		analiticaValorDao.makeTransient(analiticaValor);
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection analiticaValorByIdAnalitica(Long idAnalitica) {
		analiticaValorDao.setHibernateTemplate(getHibernateTemplate());
		return analiticaValorDao.findByIdAnalitica(idAnalitica);
	}
	
	/**
	 * Cerca tots els categorias d'oli <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection categoriaOliCercaTots() {
		categoriaOliDao.setHibernateTemplate(getHibernateTemplate());
		return categoriaOliDao.findAll();
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public CategoriaOli findCategoriaOliById(Integer idCategotia) {
		categoriaOliDao.setHibernateTemplate(getHibernateTemplate());
		return categoriaOliDao.getById(idCategotia);
	}
	
	/**
	 * Guarda el uid de tarjeta para un olivicultor y una campanya
	 *  <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public void olivicultorGrabaUidTarjeta(String uid, Long oliCod) {
		Olivicultor o = olivicultorAmbId(oliCod);
		o.setUidRfid(uid);
		olivicultorDao.setHibernateTemplate(getHibernateTemplate());
		olivicultorDao.makePersistent(o);
	}

	
	/**
	 * Guarda el uid de tarjeta para un olivicultor y una campanya
	 *  <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection getMarcasEstabliment(Long idEstabliment) {
		establimentDao.setHibernateTemplate(getHibernateTemplate());
		Establiment establiment = establimentDao.getById(idEstabliment);
		
		List marques = new ArrayList();
		Object[] marquesEstabliment = establiment.getMarcas().toArray();
		
		for(int i=0; i<marquesEstabliment.length; i++){
			marques.add(marquesEstabliment[i]);
		}
		
		Collections.sort(marques, new Comparator() {
			public int compare(Object o1, Object o2) {
				if (o1 == null && o2 == null) return 0;
				if (o1 == null || ((Marca)o1).getId() == null) return -1;
				if (o2 == null || ((Marca)o2).getId() == null) return 1;
				String t1 = ((Marca)o1).getNom();
				String t2 = ((Marca)o2).getNom();
				return t1.compareTo(t2);
			}
		});
		return marques;
	}
	
	/**
	 * Llistat de marques d'oliva de taula per establiment
	 *  <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection getMarcasOlivaTaulaEstabliment(Long idEstabliment) {
		establimentDao.setHibernateTemplate(getHibernateTemplate());
		Establiment establiment = establimentDao.getById(idEstabliment);
		
		List marques = new ArrayList();
		Object[] marquesEstabliment = establiment.getMarcas().toArray();
		
		for(int i=0; i<marquesEstabliment.length; i++){
			Marca marca = (Marca) marquesEstabliment[i];
			Boolean olivaTaula = marca.getOlivaTaula()!=null? marca.getOlivaTaula():false;
			if (olivaTaula)
				marques.add(marquesEstabliment[i]);
		}
		
		Collections.sort(marques, new Comparator() {
			public int compare(Object o1, Object o2) {
				if (o1 == null && o2 == null) return 0;
				if (o1 == null || ((Marca)o1).getId() == null) return -1;
				if (o2 == null || ((Marca)o2).getId() == null) return 1;
				String t1 = ((Marca)o1).getNom();
				String t2 = ((Marca)o2).getNom();
				return t1.compareTo(t2);
			}
		});
		return marques;
	}
	
	/**
	 * Devuelve Traslados de depositos por establecimiento pendientes de ser aceptados <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection findTrasllatsByEstablimentPendientesAceptar(Long establimentId) {
		trasllatDao.setHibernateTemplate(getHibernateTemplate());
		return trasllatDao.findTrasllatsByEstablimentPendientesAceptar(establimentId);
	}
	
	
	/**
	 * Devuelve Traslados de depositos por establecimiento pendientes de ser devueltos <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection findTrasllatsByEstablimentPendientesDevolver(Long establimentId) {
		trasllatDao.setHibernateTemplate(getHibernateTemplate());
		return trasllatDao.findTrasllatsByEstablimentPendientesDevolver(establimentId);
	}
	
	/**
	 * Devuelve Traslados de depositos por establecimiento pendientes de ser devueltos <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection findTrasllatsByEstablimentPendientesAceptarDevolver(Long establimentId) {
		trasllatDao.setHibernateTemplate(getHibernateTemplate());
		return trasllatDao.findTrasllatsByEstablimentPendientesAceptarDevolver(establimentId);
	}

	
	
	/**
	 * Devuelve el Traslado con el id dado <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both" 
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Trasllat findTrasllatById(Long idT) {
		trasllatDao.setHibernateTemplate(getHibernateTemplate());
		return trasllatDao.getById(idT);
	}
	
	/**
	 * Añade todos los registros necesarios para el trasvase del aceite
	 * @param data
	 * @param dipositsOrigen
	 * @param establimentDesti
	 * @param establiment
	 * @throws Exception
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" 
	 * <!-- end-xdoclet-definition -->
	 */
	public boolean findContenidorEnCami(Long idDiposit){
		trasllatDao.setHibernateTemplate(getHibernateTemplate());
		return trasllatDao.contenidorEnCami(idDiposit);
	}
	
	/**
	 * Añade todos los registros necesarios para la elaboración del aceite
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public void generarExistenciesDiposit(Diposit diposit, EntradaDiposit entrada) throws Exception{
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			// Generamos las entradas de depósito y la traza de la entrada.
			Traza traza = new Traza(new Integer(Constants.CODI_TRAZA_TIPUS_ENTRADA_DIPOSIT));
			trazaDao.setHibernateTemplate(getHibernateTemplate());
			trazaDao.makePersistent(traza);
			
			entrada.setTraza(traza);
			entradaDipositDao.setHibernateTemplate(getHibernateTemplate());
			entradaDipositDao.makePersistent(entrada);
			
			dipositDao.setHibernateTemplate(getHibernateTemplate());
			dipositDao.makePersistent(diposit);
			
			tx.commit();
			
		} catch (Exception ex) {
			try {
				tx.rollback();
				throw new Exception(ex);
			} catch (HibernateException e) {
			}
		}
	}
	
	/**
	 * Añade todos los registros necesarios para la elaboración del aceite
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public void sortidaOliDiposit(SortidaDiposit sortidaDiposit, boolean desqualificar) throws Exception{
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Diposit diposit = sortidaDiposit.getDipositBySdiCoddor();
			
			Traza traza = new Traza(new Integer(Constants.CODI_TRAZA_TIPUS_VENDA_OLI));
			trazaDao.setHibernateTemplate(getHibernateTemplate());
			trazaDao.makePersistent(traza);
			
			sortidaDiposit.setTraza(traza);
			
			//Veamos si la ultima traza de deposito corresponde a una salida o a una entrada
			entradaDipositDao.setHibernateTemplate(getHibernateTemplate());
			EntradaDiposit ultimaEntradaDiposit =  entradaDipositDao.findUltimaByDeposito(diposit.getId(), new Boolean(true));
			
			sortidaDipositDao.setHibernateTemplate(getHibernateTemplate());
			SortidaDiposit ultimaSortidaDiposit =  sortidaDipositDao.findUltimaByDeposito(diposit.getId(), new Boolean(true));
			
			if(ultimaEntradaDiposit!= null && ultimaSortidaDiposit!= null && (ultimaEntradaDiposit.getTraza().getId().intValue() < ultimaSortidaDiposit.getTraza().getId().intValue())){
				sortidaDiposit.getTraza().getTrazasForTtrCodtrapare().add(ultimaSortidaDiposit.getTraza());
			}else if(ultimaEntradaDiposit!= null && ultimaSortidaDiposit!= null && (ultimaEntradaDiposit.getTraza().getId().intValue() > ultimaSortidaDiposit.getTraza().getId().intValue())){
				sortidaDiposit.getTraza().getTrazasForTtrCodtrapare().add(ultimaEntradaDiposit.getTraza());
			}else if(ultimaSortidaDiposit == null && ultimaEntradaDiposit!= null){
				sortidaDiposit.getTraza().getTrazasForTtrCodtrapare().add(ultimaEntradaDiposit.getTraza());
			}
			
			diposit.setVolumActual(new Double(diposit.getVolumActual().doubleValue() - sortidaDiposit.getLitres().doubleValue()));
			
			dipositDao.setHibernateTemplate(getHibernateTemplate());
			dipositDao.makePersistent(diposit);
			sortidaDipositDao.setHibernateTemplate(getHibernateTemplate());
			sortidaDipositDao.makePersistent(sortidaDiposit);
			
			tx.commit();
			
		} catch (Exception ex) {
			try {
				tx.rollback();
				throw new Exception(ex);
			} catch (HibernateException e) {
			}
		}
	}
	
	/**
	 * Compruova si existeix algun lot en la BBDD amb el mateix nom de partida  <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public boolean existeixNomPartida(String nomPartida, Long idEstabliment) {
		lotDao.setHibernateTemplate(getHibernateTemplate());
		//return lotDao.existeixNomPartida(nomPartida, idEstabliment, new Boolean(true));
		//TODO: fer comprovació d'existència de nom de partida
		return false;
	}


	/**
	 * Llistat de tots els municipis
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition -->
	 */
	public List obtenirMunicipis() {
		municipiDao.setHibernateTemplate(getHibernateTemplate());
		return municipiDao.getAllMunicipis();
	}
	
	/**
	 * Totes les partides d'oli
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection partidesOli() {
		partidaOliDao.setHibernateTemplate(getHibernateTemplate());
		return partidaOliDao.findAll(null);
	}
	
	/**
	 * Totes les partides d'oli visibles
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection partidesOliVisibles() {
		partidaOliDao.setHibernateTemplate(getHibernateTemplate());
		return partidaOliDao.findAllVisibles(null);
	}
	
	/**
	 * Totes les partides d'oli
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection findPartidesOliByEstabliment(Long idEstabliment) {
		partidaOliDao.setHibernateTemplate(getHibernateTemplate());
		return partidaOliDao.findAllByEstabliment(idEstabliment, null);
	}
	
	/**
	 * Totes les partides d'oli
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection findPartidesOliVisiblesByEstabliment(Long idEstabliment) {
		partidaOliDao.setHibernateTemplate(getHibernateTemplate());
		return partidaOliDao.findAllVisiblesByEstabliment(idEstabliment, null);
	}
	
	/**
	 * Totes les partides d'oli
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection findPartidesOliByEstablimentExceptDO(Long idEstabliment) {
		partidaOliDao.setHibernateTemplate(getHibernateTemplate());
		return partidaOliDao.findByEstablimentExceptDO(idEstabliment, null);
	}
	
	/**
	 * Totes les partides d'oli
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection findPartidesOliVisiblesByEstablimentExceptDO(Long idEstabliment) {
		partidaOliDao.setHibernateTemplate(getHibernateTemplate());
		return partidaOliDao.findVisiblesByEstablimentExceptDO(idEstabliment, true);
	}
	
	/**
	 * Totes les partides d'oli
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection partidesOliExcepteDO() {
		partidaOliDao.setHibernateTemplate(getHibernateTemplate());
		return partidaOliDao.findAllExceptDO(null);
	}
	
	/**
	 * Totes les partides d'oli
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection partidesOliVisiblesExcepteDO() {
		partidaOliDao.setHibernateTemplate(getHibernateTemplate());
		return partidaOliDao.findAllVisiblesExceptDO(null);
	}
	
	/**
	 * Partida d'oli amb d'identificador especificat
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public PartidaOli getPartidaOliById(Long id) {
		partidaOliDao.setHibernateTemplate(getHibernateTemplate());
		return partidaOliDao.getById(id);

	}
	
	/**
	 * Partida d'oli amb d'identificador especificat
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public void partidaOliEsborrar(Long id){
		partidaOliDao.setHibernateTemplate(getHibernateTemplate());
		PartidaOli partidaOli = partidaOliDao.getById(id);
		if (partidaOli != null){
			partidaOliDao.makeTransient(partidaOli);
		}
	}
	
	/**
	 * Modifica la partida d'oli amb d'identificador especificat
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public void modificaPartidaOli(
			Long id,
			String nom, 
			Integer categoriaId,
			Boolean actiu,
			Boolean esVisualitza,
			Boolean esEcologica){
		modificaPartidaOli(id, nom, categoriaId, actiu, null, esVisualitza, esEcologica);
	}
	
	/**
	 * Modifica la partida d'oli amb d'identificador especificat
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public void modificaPartidaOli(
			Long id,
			String nom, 
			Integer categoriaId,
			Boolean actiu,
			Boolean olivicultorEsPropietari,
			Boolean esVisualitza,
			Boolean esEcologica){
		categoriaOliDao.setHibernateTemplate(getHibernateTemplate());
		CategoriaOli categoriaOli = categoriaOliDao.getById(categoriaId);
		
		partidaOliDao.setHibernateTemplate(getHibernateTemplate());
		PartidaOli partidaOli = getPartidaOliById(id);
		partidaOli.setNom(nom);
		partidaOli.setCategoriaOli(categoriaOli);
		partidaOli.setEsActiu(actiu);
		if (olivicultorEsPropietari != null) partidaOli.setOlivicultorEsPropietari(olivicultorEsPropietari);
		partidaOli.setEsVisualitza(esVisualitza);
		partidaOli.setEsEcologic(esEcologica);
		partidaOliDao.makePersistent(partidaOli);
	}
	
	/**
	 * Desactiva les partides d'oli amb els identificadors especificats
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public void desactivaPartidesOli(List partidesOli){
		partidaOliDao.setHibernateTemplate(getHibernateTemplate());
		for (Iterator it = partidesOli.iterator(); it.hasNext();){
			PartidaOli partidaOli = getPartidaOliById((Long)it.next());
			partidaOli.setEsActiu(new Boolean(false));
			partidaOliDao.makePersistent(partidaOli);
		}
	}
	
	/**
	 * Oculta les partides d'oli amb els identificadors especificats
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public void ocultaPartidesOli(List partidesOli){
		partidaOliDao.setHibernateTemplate(getHibernateTemplate());
		for (Iterator it = partidesOli.iterator(); it.hasNext();){
			PartidaOli partidaOli = getPartidaOliById((Long)it.next());
			partidaOli.setEsVisualitza(new Boolean(false));
			partidaOliDao.makePersistent(partidaOli);
		}
	}
	
	/**
	 * Crea una nova partida d'oli
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Long creaPartidaOli(
			String nom, 
			Integer categoriaId,
			Boolean actiu, 
			Establiment establiment,
			Boolean esVisualitza,
			Boolean olivicultorEsPropietari,
			Boolean esEcologic){
		categoriaOliDao.setHibernateTemplate(getHibernateTemplate());
		CategoriaOli categoriaOli = categoriaOliDao.getById(categoriaId);
		
//		establimentDao.setHibernateTemplate(getHibernateTemplate());
//		Establiment establiment = establimentDao.getById(idEstabliment);
		
		partidaOliDao.setHibernateTemplate(getHibernateTemplate());
		PartidaOli partidaOli = new PartidaOli();
		partidaOli.setNom(nom);
		partidaOli.setDataCreacio(new Date());
		partidaOli.setCategoriaOli(categoriaOli);
		partidaOli.setCategoriaOliOriginal(categoriaOli);
		partidaOli.setEsActiu(actiu);
		partidaOli.setEstabliment(establiment);
		
		if(olivicultorEsPropietari != null){
			partidaOli.setOlivicultorEsPropietari(olivicultorEsPropietari);
		} else {
			partidaOli.setOlivicultorEsPropietari(new Boolean(true));
		}
		
		if (esVisualitza != null){
			partidaOli.setEsVisualitza(esVisualitza);
		} else {
			partidaOli.setEsVisualitza(new Boolean(true));
		}
		partidaOli.setEsEcologic(esEcologic);
		partidaOliDao.makePersistent(partidaOli);
		
		return partidaOli.getId();
	}
	
	/**
	 * Comprova si existeixen partides amb el nom indicat
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Boolean existeixPartidaEstabliment(Long partidaOliId, String partidaOliNom, Long establimentId){
		partidaOliDao.setHibernateTemplate(getHibernateTemplate());
		return partidaOliDao.existeixPartidaEstabliment(partidaOliId, partidaOliNom, establimentId);
	}
	
	/**
	 * Comprova si existeixen partides amb el nom indicat
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Boolean partidaCorrecteAnalitica(Long partidaOliId, Long dipositId){
		partidaOliDao.setHibernateTemplate(getHibernateTemplate());
		return partidaOliDao.partidaCorrecteAnalitica(partidaOliId, dipositId);
	}
	
	
	
	
	/**
	 * Comprova si existeixen elaboracions associats a la partida d'oli
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Boolean existeixenElaboracionsAssociadesPartidesOli(Long lid) {
		elaboracioDao.setHibernateTemplate(getHibernateTemplate());
		return elaboracioDao.existeixenElaboracionsAssociadesPartidesOli(lid, new Boolean(true));
	}
	
	/**
	 * Comprova si existeixen entrades de diposits associats a la partida d'oli
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Boolean existeixenEntradesDipositsAssociatsPartidesOli(Long lid) {
		entradaDipositDao.setHibernateTemplate(getHibernateTemplate());
		return entradaDipositDao.existeixenEntradesDipositsAssociatsPartidesOli(lid, new Boolean(true));
	}
	
	/**
	 * Comprova si existeixen sortides de diposit associats a la partida d'oli
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Boolean existeixenSortidesDipositsAssociatsPartidesOli(Long lid) {
		sortidaDipositDao.setHibernateTemplate(getHibernateTemplate());
		return sortidaDipositDao.existeixenSortidesDipositsAssociatsPartidesOli(lid, new Boolean(true));
	}
	
	/**
	 * Comprova si existeixen lots associats a la partida d'oli
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Boolean existeixenLotsAssociatsPartidesOli(Long lid) {
		lotDao.setHibernateTemplate(getHibernateTemplate());
		return lotDao.existeixenLotsAssociatsPartidesOli(lid, new Boolean(true));
	}
	
	/**
	 * Comprova si existeixen diposits associats a la partida d'oli
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Boolean existeixenDipositsAssociatsPartidesOli(Long lid) {
		dipositDao.setHibernateTemplate(getHibernateTemplate());
		return dipositDao.existeixenDipositsAssociatsPartidesOli(lid);
	}
	
	/**
	 * set the hibernate template.
	 * @param hibernateTemplate the hibernate spring template.
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition --> 
	 */
	public Collection municipis() {
		municipiDao.setHibernateTemplate(getHibernateTemplate());
		return municipiDao.getAllMunicipis();
	}
	
	/**
	 * Municipi que té la id indicada
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Municipi municipiAmbId(Long id) {
		municipiDao.setHibernateTemplate(getHibernateTemplate());
		return municipiDao.getById(id);
	}
	
	/**
	 * Municipi que té el nom indicat
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Municipi municipiAmbNom(String nom) {
		municipiDao.setHibernateTemplate(getHibernateTemplate());
		return municipiDao.getByNom(nom);
	}
	
	/**
	 * set the hibernate template.
	 * @param hibernateTemplate the hibernate spring template.
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition --> 
	 */
	public Collection paisos() {
		paisDao.setHibernateTemplate(getHibernateTemplate());
		return paisDao.getAllPaisos();
	}
	
	/**
	 * set the hibernate template.
	 * @param hibernateTemplate the hibernate spring template.
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition --> 
	 */
	public Pais paisAmbId(Long id) {
		paisDao.setHibernateTemplate(getHibernateTemplate());
		return paisDao.getById(id);
	}
	
	/**
	 * set the hibernate template.
	 * @param hibernateTemplate the hibernate spring template.
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition --> 
	 */
	public Pais paisAmbNom(String nom) {
		paisDao.setHibernateTemplate(getHibernateTemplate());
		return paisDao.getByNom(nom);
	}
	
	/**
	 * set the hibernate template.
	 * @param hibernateTemplate the hibernate spring template.
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * <!-- end-xdoclet-definition --> 
	 */
	public void actualitzarPais(Pais pais) {
		paisDao.setHibernateTemplate(getHibernateTemplate());
		paisDao.makePersistent(pais);
	}
	
	/**
	 * set the hibernate template.
	 * @param hibernateTemplate the hibernate spring template.
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition --> 
	 */
	public Collection provincies() {
		provinciaDao.setHibernateTemplate(getHibernateTemplate());
		return provinciaDao.getAllProvincies();
	}
	
	/**
	 * set the hibernate template.
	 * @param hibernateTemplate the hibernate spring template.
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition --> 
	 */
	public Provincia provinciaAmbId(Long id) {
		provinciaDao.setHibernateTemplate(getHibernateTemplate());
		return provinciaDao.getById(id);
	}
	
	/**
	 * set the hibernate template.
	 * @param hibernateTemplate the hibernate spring template.
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition --> 
	 */
	public Provincia provinciaAmbNom(String nom) {
		provinciaDao.setHibernateTemplate(getHibernateTemplate());
		return provinciaDao.getByNom(nom);
	}
	
	/**
	 * Finques que estan donades de baixa a l'aplicació
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public List finquesDeBaixa() {
		fincasDao.setHibernateTemplate(getHibernateTemplate());
		return fincasDao.findFinquesDeBaixa();
	}
	
	/**
	 * Actualitza una finca.
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public void finquesActualitzar(Finca finca) {
		fincasDao.setHibernateTemplate(getHibernateTemplate());
		fincasDao.makePersistent(finca);
	}
	
	/**
	 * Actualitza un olivicultor.
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public void olivicultorActualitzar(Olivicultor olivicultor) {
		olivicultorDao.setHibernateTemplate(getHibernateTemplate());
		olivicultorDao.makePersistent(olivicultor);
	}
	
	/**
	 * Plantacions que estan donades de baixa a l'aplicació
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public List plantacionsDeBaixa() {
		plantacioDao.setHibernateTemplate(getHibernateTemplate());
		return plantacioDao.findPlantacionsDeBaixa();
	}
	
	/**
	 * Actualitza una plantació.
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public void plantacioActualitzar(Plantacio plantacio) {
		plantacioDao.setHibernateTemplate(getHibernateTemplate());
		plantacioDao.makePersistent(plantacio);
	}
	
	/**
	 * Guarda un olivicultor a l'historic.
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public void guardaHistoricOlivicultor(Olivicultor olivicultor, String tipus) {
		// Donam d'alta l'olivicultor a l'històric.
		HistoricOlivicultor histOlivicultor = new HistoricOlivicultor();
		histOlivicultor.setOlivicultor(olivicultor);
		histOlivicultor.setUsuari(olivicultor.getUsuari());
		histOlivicultor.setCampanya(olivicultor.getCampanya());
		histOlivicultor.setIdOriginal(olivicultor.getId());
		histOlivicultor.setCodiDO(olivicultor.getCodiDO());
		histOlivicultor.setCodiDOExperimental(olivicultor.getCodiDOExperimental());
		histOlivicultor.setNom(olivicultor.getNom());
		histOlivicultor.setDireccio(olivicultor.getDireccio());
		histOlivicultor.setPoblacio(olivicultor.getPoblacio());
		histOlivicultor.setCodiPostal(olivicultor.getCodiPostal());
		histOlivicultor.setNif(olivicultor.getNif());
		histOlivicultor.setCompteBancari(olivicultor.getCompteBancari());
		histOlivicultor.setAltaDO(olivicultor.getAltaDO());
		histOlivicultor.setCartilla(olivicultor.getCartilla());
		histOlivicultor.setEmail(olivicultor.getEmail());
		histOlivicultor.setTelefon(olivicultor.getTelefon());
		histOlivicultor.setFax(olivicultor.getFax());
		histOlivicultor.setUidRfid(olivicultor.getUidRfid());
		histOlivicultor.setSubvencionat(olivicultor.getSubvencionat());
		histOlivicultor.setObservacions(olivicultor.getObservacions());
		if ("alta".equals(tipus)) {
			histOlivicultor.setDataAlta(Calendar.getInstance().getTime());
		} else if ("baixa".equals(tipus)) {
			histOlivicultor.setDataBaixa(Calendar.getInstance().getTime());
		}
		histOlivicultor.setEsAltaBaixa(new Boolean(true));
		historicOlivicultorDao.setHibernateTemplate(getHibernateTemplate());
		historicOlivicultorDao.makePersistent(histOlivicultor);
	}
	
	/**
	 * Guarda un olivicultor a l'historic.
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public void guardaHistoricOlivicultorIFinques(Olivicultor olivicultor) {
		// Donam d'alta l'olivicultor a l'històric.
		logger.info("Històric d'olivicultor: " + olivicultor.getNif() + " (" + (olivicultor.getCodiDO() != null? "RA-" + olivicultor.getCodiDO() : "") + (olivicultor.getCodiDOExperimental() != null? "RE-" + olivicultor.getCodiDOExperimental() : "") + ")" );
		HistoricOlivicultor histOlivicultor = new HistoricOlivicultor();
		histOlivicultor.setOlivicultor(olivicultor);
		histOlivicultor.setUsuari(olivicultor.getUsuari());
		histOlivicultor.setCampanya(olivicultor.getCampanya());
		histOlivicultor.setIdOriginal(olivicultor.getId());
		histOlivicultor.setCodiDO(olivicultor.getCodiDO());
		histOlivicultor.setCodiDOExperimental(olivicultor.getCodiDOExperimental());
		histOlivicultor.setNom(olivicultor.getNom());
		histOlivicultor.setDireccio(olivicultor.getDireccio());
		histOlivicultor.setPoblacio(olivicultor.getPoblacio());
		histOlivicultor.setCodiPostal(olivicultor.getCodiPostal());
		histOlivicultor.setNif(olivicultor.getNif());
		histOlivicultor.setCompteBancari(olivicultor.getCompteBancari());
		histOlivicultor.setAltaDO(olivicultor.getAltaDO());
		histOlivicultor.setCartilla(olivicultor.getCartilla());
		histOlivicultor.setEmail(olivicultor.getEmail());
		histOlivicultor.setTelefon(olivicultor.getTelefon());
		histOlivicultor.setFax(olivicultor.getFax());
		histOlivicultor.setUidRfid(olivicultor.getUidRfid());
		histOlivicultor.setSubvencionat(olivicultor.getSubvencionat());
		histOlivicultor.setObservacions(olivicultor.getObservacions());
		histOlivicultor.setDataBaixa(olivicultor.getDataBaixa());
		histOlivicultor.setEsAltaBaixa(new Boolean(false));
		histOlivicultor.setCodiDOPOliva(olivicultor.getCodiDOPOliva());
		// Donam d'alta les finques de l'olivicultor
		Set finques = olivicultor.getFincas();
		Set histFinq = new HashSet(0);
		for (Iterator itFin = finques.iterator(); itFin.hasNext();) {
			Finca f = (Finca)itFin.next();
			
			HistoricFinca histFinca = new HistoricFinca();
			histFinca.setOlivicultor(histOlivicultor);
			histFinca.setIdOriginal(f.getIdOriginal());
			histFinca.setActiu(f.getActiu());
			histFinca.setNom(f.getNom());
			histFinca.setObservacions(f.getObservacions());
			histFinca.setDeBaixa(f.getDeBaixa());
			//Doman d'alta les plantacions de la finca
			Set plantacions = f.getPlantacios();
			Set histPlant = new HashSet(0);
			for (Iterator itPlan = plantacions.iterator(); itPlan.hasNext();) {
				Plantacio p = (Plantacio)itPlan.next();
				
				HistoricPlantacio histPlantacio = new HistoricPlantacio();
				histPlantacio.setFinca(histFinca);
				histPlantacio.setIdOriginal(p.getIdOriginal());
				histPlantacio.setActiu(p.getActiu());
				histPlantacio.setMunicipi(p.getMunicipi());
				histPlantacio.setPoligon(p.getPoligon());
				histPlantacio.setParcela(p.getParcela());
				histPlantacio.setCoordX(p.getCoordX());
				histPlantacio.setCoordY(p.getCoordY());
				histPlantacio.setCatastre(p.getCatastre());
				histPlantacio.setRegadiu(p.getRegadiu());
				histPlantacio.setAnyPlantacio(p.getAnyPlantacio());
				histPlantacio.setObservacions(p.getObservacions());
				histPlantacio.setDeBaixa(p.getDeBaixa());
				histPlantacio.setNomComplet(p.getNomComplet());
				//Donam d'alta les descomposicions de plantació
				Set descPlantacions = p.getDescomposicioPlantacios();
				Set histDesc = new HashSet(0);
				for (Iterator itDesc = descPlantacions.iterator(); itDesc.hasNext();) {
					DescomposicioPlantacio d = (DescomposicioPlantacio)itDesc.next();
					
					HistoricDescomposicioPlantacio histDescPlant = new HistoricDescomposicioPlantacio();
					histDescPlant.setPlantacio(histPlantacio);
					histDescPlant.setVarietatOliva(d.getVarietatOliva());
					histDescPlant.setIdOriginal(d.getIdOriginal());
					histDescPlant.setNumeroOliveres(d.getNumeroOliveres());
					histDescPlant.setSuperficie(d.getSuperficie());
					histDescPlant.setMaxProduccio(d.getMaxProduccio());
					histDescPlant.setObservacions(d.getObservacions());
					histDescPlant.setProduccioRestant(d.getProduccioRestant());
					
					histDesc.add(histDescPlant);
//					historicDescomposicioPlantacioDao.setHibernateTemplate(getHibernateTemplate());
//					historicDescomposicioPlantacioDao.makePersistent(histDescPlant);
					
					// Inicialitzam la producció màxima
					rendimentVarietatDao.setHibernateTemplate(getHibernateTemplate());
					RendimentVarietat rv = rendimentVarietatDao.findByCampanyaVarietat(olivicultor.getCampanya(), d.getVarietatOliva());
					if (rv.getTipusRendiment().equals("hectarea")) {
						d.setMaxProduccio(rv.getRendiment() * d.getSuperficie());
					} else if (rv.getTipusRendiment().equals("arbre")) {
						d.setMaxProduccio(rv.getRendiment() * d.getNumeroOliveres());
					}
					d.setProduccioRestant(d.getMaxProduccio());
//					descomposicioPlantacioDao.setHibernateTemplate(getHibernateTemplate());
//					descomposicioPlantacioDao.makePersistent(d);
				}
				histPlantacio.setDescomposicioPlantacios(histDesc);
				histPlant.add(histPlantacio);
//				historicPlantacioDao.setHibernateTemplate(getHibernateTemplate());
//				historicPlantacioDao.makePersistent(histPlantacio);
			}
			histFinca.setPlantacios(histPlant);
			histFinq.add(histFinca);
//			historicFincaDao.setHibernateTemplate(getHibernateTemplate());
//			historicFincaDao.makePersistent(histFinca);
		}
		histOlivicultor.setFincas(histFinq);
		historicOlivicultorDao.setHibernateTemplate(getHibernateTemplate());
		historicOlivicultorDao.makePersistent(histOlivicultor);
		
		
		for (Iterator itFinca = histFinq.iterator(); itFinca.hasNext();) {
			HistoricFinca finca = (HistoricFinca)itFinca.next();
			historicFincaDao.setHibernateTemplate(getHibernateTemplate());
			historicFincaDao.makePersistent(finca);
			
			for (Iterator itPlantacio = finca.getPlantacios().iterator(); itPlantacio.hasNext();) {
				HistoricPlantacio plantacio = (HistoricPlantacio)itPlantacio.next();
				historicPlantacioDao.setHibernateTemplate(getHibernateTemplate());
				historicPlantacioDao.makePersistent(plantacio);
				
				for (Iterator itDescomposicio = plantacio.getDescomposicioPlantacios().iterator(); itDescomposicio.hasNext();) {
					HistoricDescomposicioPlantacio descomposicio = (HistoricDescomposicioPlantacio)itDescomposicio.next();
					historicDescomposicioPlantacioDao.setHibernateTemplate(getHibernateTemplate());
					historicDescomposicioPlantacioDao.makePersistent(descomposicio);
				}	

			}
			
		}
		
	}
	
	/**
	 * Torna el llistat d'històrics d'olivicultors.
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR" 
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection historicsOlivicultors() {
		historicOlivicultorDao.setHibernateTemplate(getHibernateTemplate());
		return historicOlivicultorDao.findAllAltaBaixa();
	}
	
	/**
	 * Guarda un establiment a l'històric.
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR" 
	 * <!-- end-xdoclet-definition -->
	 */
	public void guardaHistoricEstabliment(Establiment establiment, String tipus) {
		// Donam d'alta l'establiment a l'històric.
		HistoricEstabliment histEstabliment = new HistoricEstabliment();
		histEstabliment.setEstabliment(establiment);
		histEstabliment.setCampanya(establiment.getCampanya());
		histEstabliment.setTipusEstabliment(establiment.getTipusEstabliment());
		histEstabliment.setSolicitant(establiment.getSolicitant());
		histEstabliment.setIdOriginal(establiment.getIdOriginal());
		histEstabliment.setCodiRB(establiment.getCodiRB());
		histEstabliment.setCodiRC(establiment.getCodiRC());
		histEstabliment.setActiu(establiment.getActiu());
		histEstabliment.setNom(establiment.getNom());
		histEstabliment.setCif(establiment.getCif());
		histEstabliment.setCifResponsable(establiment.getCifResponsable());
		histEstabliment.setNomResponsable(establiment.getNomResponsable());
		histEstabliment.setDireccio(establiment.getDireccio());
		histEstabliment.setPoblacio(establiment.getPoblacio());
		histEstabliment.setCodiPostal(establiment.getCodiPostal());
		histEstabliment.setEmail(establiment.getEmail());
		histEstabliment.setTelefon(establiment.getTelefon());
		histEstabliment.setFax(establiment.getFax());
		histEstabliment.setNumeroTreballadors(establiment.getNumeroTreballadors());
		histEstabliment.setSuperficie(establiment.getSuperficie());
		histEstabliment.setUnitatMesura(establiment.getUnitatMesura());
		histEstabliment.setTemperaturaMaximaPasta(establiment.getTemperaturaMaximaPasta());
		histEstabliment.setCapacitatProduccio(establiment.getCapacitatProduccio());
		histEstabliment.setEnvassamentManual(establiment.getEnvassamentManual());
		histEstabliment.setEtiquetatManual(establiment.getEtiquetatManual());
		histEstabliment.setObservacions(establiment.getObservacions());
		if ("alta".equals(tipus)) {
			histEstabliment.setDataAlta(Calendar.getInstance().getTime());
		} else if ("baixa".equals(tipus)) {
			histEstabliment.setDataBaixa(Calendar.getInstance().getTime());
		}
		histEstabliment.setEsAltaBaixa(new Boolean(true));
		historicEstablimentDao.setHibernateTemplate(getHibernateTemplate());
		historicEstablimentDao.makePersistent(histEstabliment);
	}
	
	/**
	 * Guarda un establiment a l'històric.
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR" 
	 * <!-- end-xdoclet-definition -->
	 */
	public void guardaHistoricEstablimentZonesIDiposits(Establiment establiment) {
		// Donam d'alta l'establiment a l'històric.
		logger.info("Històric d'establiment: " + establiment.getNom() + " (" + (establiment.getCodiRB() != null? "RB-" + establiment.getCodiRB() : "") + (establiment.getCodiRC() != null? "RC-" + establiment.getCodiRC() : "") + ")" );
		HistoricEstabliment histEstabliment = new HistoricEstabliment();
		histEstabliment.setEstabliment(establiment);
		histEstabliment.setCampanya(establiment.getCampanya());
		histEstabliment.setTipusEstabliment(establiment.getTipusEstabliment());
		histEstabliment.setSolicitant(establiment.getSolicitant());
		histEstabliment.setIdOriginal(establiment.getIdOriginal());
		histEstabliment.setCodiRB(establiment.getCodiRB());
		histEstabliment.setCodiRC(establiment.getCodiRC());
		histEstabliment.setActiu(establiment.getActiu());
		histEstabliment.setNom(establiment.getNom());
		histEstabliment.setCif(establiment.getCif());
		histEstabliment.setCifResponsable(establiment.getCifResponsable());
		histEstabliment.setNomResponsable(establiment.getNomResponsable());
		histEstabliment.setDireccio(establiment.getDireccio());
		histEstabliment.setPoblacio(establiment.getPoblacio());
		histEstabliment.setCodiPostal(establiment.getCodiPostal());
		histEstabliment.setEmail(establiment.getEmail());
		histEstabliment.setTelefon(establiment.getTelefon());
		histEstabliment.setFax(establiment.getFax());
		histEstabliment.setNumeroTreballadors(establiment.getNumeroTreballadors());
		histEstabliment.setSuperficie(establiment.getSuperficie());
		histEstabliment.setUnitatMesura(establiment.getUnitatMesura());
		histEstabliment.setTemperaturaMaximaPasta(establiment.getTemperaturaMaximaPasta());
		histEstabliment.setCapacitatProduccio(establiment.getCapacitatProduccio());
		histEstabliment.setEnvassamentManual(establiment.getEnvassamentManual());
		histEstabliment.setEtiquetatManual(establiment.getEtiquetatManual());
		histEstabliment.setObservacions(establiment.getObservacions());
		histEstabliment.setDataBaixa(establiment.getDataBaixa());
		
		Set marques = establiment.getMarcas();
		Set marq = new HashSet(0);
		for (Iterator it = marques.iterator(); it.hasNext();) {
			Marca m = (Marca)it.next();
			marq.add(m);
		}
		histEstabliment.setMarcas(marq);
		
		Set zones = establiment.getZonas();
		Set zon = new HashSet(0);
		Set dipos = new HashSet(0);
		for (Iterator it = zones.iterator(); it.hasNext();) {
			Zona z = (Zona)it.next();
			HistoricZona histZon = historicZonaFromZona(z);
			
			Set diposits = z.getDiposits();
			Set dipszona = new HashSet(0);
			for (Iterator it2 = diposits.iterator(); it2.hasNext();) {
				Diposit d = (Diposit)it2.next();
				HistoricDiposit histDip = historicDipositFromDiposit(d);
				histDip.setZona(histZon);
				histDip.setEstabliment(histEstabliment);
				dipszona.add(histDip);
			}
			histZon.setDiposits(dipszona);
			dipos.addAll(dipszona);
			histZon.setEstabliment(histEstabliment);
			zon.add(histZon);
		}
		histEstabliment.setZonas(zon);
		
		// Ara ens falten els dipòsits sense zona...
		Set diposits = establiment.getDiposits();
		for (Iterator it = diposits.iterator(); it.hasNext();) {
			Diposit d = (Diposit)it.next();
			if (d.getZona() == null){
				HistoricDiposit histDip = historicDipositFromDiposit(d);
				histDip.setEstabliment(histEstabliment);
				dipos.add(histDip);
			}
		}
		histEstabliment.setDiposits(dipos);
		
		
		/*
		Set informacions = establiment.getInformacios();
		Set infos = new HashSet(0);
		for (Iterator it = informacions.iterator(); it.hasNext();) {
			Informacio i = (Informacio)it.next();
			infos.add(i);
		}
		histEstabliment.setInformacions(infos);
		
		Set usuaris = establiment.getUsuaris();
		Set users = new HashSet(0);
		for (Iterator it = usuaris.iterator(); it.hasNext();) {
			Usuari u = (Usuari)it.next();
			users.add(u);
		}
		histEstabliment.setUsuaris(users);
		 */
		histEstabliment.setEsAltaBaixa(new Boolean(false));
		historicEstablimentDao.setHibernateTemplate(getHibernateTemplate());
		historicEstablimentDao.makePersistent(histEstabliment);
		
		for (Iterator itZona = zon.iterator(); itZona.hasNext();) {
			historicZonaDao.setHibernateTemplate(getHibernateTemplate());
			historicZonaDao.makePersistent((HistoricZona)itZona.next());
		}

		for (Iterator itDiposit = dipos.iterator(); itDiposit.hasNext();) {
			historicDipositDao.setHibernateTemplate(getHibernateTemplate());
			historicDipositDao.makePersistent((HistoricDiposit)itDiposit.next());
		}
		
	}
	
	/**
	 * Crea un objecte HistoricZona amb les dades de la Zona.
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR" 
	 * <!-- end-xdoclet-definition -->
	 */
	public HistoricZona historicZonaFromZona(Zona z) {
		HistoricZona histZon = new HistoricZona();
		histZon.setZona(z);
		//private HistoricEstabliment establiment;
		histZon.setActiu(z.getActiu());
		histZon.setIdOriginal(z.getIdOriginal());
		histZon.setActiu(z.getActiu());
		histZon.setNom(z.getNom());
		histZon.setFictici(z.getFictici());
		histZon.setImatgePlanol(z.getImatgePlanol());
		histZon.setDefecte(z.getDefecte());
		histZon.setObservacions(z.getObservacions());
		histZon.setDefecteTrasllat(z.getDefecteTrasllat());
		
		return histZon;
	}
	
	/**
	 * Crea un objecte HistoricDiposit amb les dades del Diposit.
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR" 
	 * <!-- end-xdoclet-definition -->
	 */
	public HistoricDiposit historicDipositFromDiposit(Diposit d) {
		HistoricDiposit histDip = new HistoricDiposit();
		histDip.setDiposit(d);
		histDip.setPartidaOli(d.getPartidaOli());
		//private HistoricZona zona;
		//private HistoricZona zonaOrigenTrasllat;
		//private HistoricEstabliment establiment;
		histDip.setMaterialDiposit(d.getMaterialDiposit());
		histDip.setIdOriginal(d.getIdOriginal());
		histDip.setActiu(d.getActiu());
		histDip.setCodiAssignat(d.getCodiAssignat());
		histDip.setFictici(d.getFictici());
		histDip.setCapacitat(d.getCapacitat());
		histDip.setPosicioX(d.getPosicioX());
		histDip.setPosicioY(d.getPosicioY());
		histDip.setVolumActual(d.getVolumActual());
		histDip.setAcidesa(d.getAcidesa());
		histDip.setObservacions(d.getObservacions());
		histDip.setVolumTrasllat(d.getVolumTrasllat());
		histDip.setCodiOriginal(d.getCodiOriginal());
		histDip.setPrecintat(d.getPrecintat());

		return histDip;
	}
	
	
	/**
	 * Torna el llistat d'històrics d'establiments.
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR" 
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection historicsEstabliments() {
		historicEstablimentDao.setHibernateTemplate(getHibernateTemplate());
		return historicEstablimentDao.findAllAltaBaixa();
	}
	
	/**
	 * Torna el llistat de varietats experimentals.
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR" 
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection varietatOlivaExperimental() {
		varietatOlivaDao.setHibernateTemplate(getHibernateTemplate());
		return varietatOlivaDao.findAllExperimentals();
	}
	
	
	/**
	 * Torna el llistat de varietats per oliva de taula.
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR" 
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection varietatOlivaTaula() {
		varietatOlivaDao.setHibernateTemplate(getHibernateTemplate());
		return varietatOlivaDao.findAllOlivaTaula();
	}
	
	/**
	 * Esborra la varietat experimental que té la id indicada.
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR" 
	 * <!-- end-xdoclet-definition -->
	 */
	public void varietatOlivaExperimentalEsborrar(Long id) {
		varietatOlivaDao.setHibernateTemplate(getHibernateTemplate());
		VarietatOliva varietatOliva = varietatOlivaDao.getById(id.intValue());
		if (varietatOliva != null) {
			varietatOlivaDao.setHibernateTemplate(getHibernateTemplate());
			varietatOlivaDao.makeTransient(varietatOliva);
		}
	}
	
	/**
	 * Modifica una varitat experimental.
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR" 
	 * <!-- end-xdoclet-definition -->
	 */
	public void varietatOlivaExperimentalModificar(VarietatOliva varietatOliva) {
		varietatOlivaDao.setHibernateTemplate(getHibernateTemplate());
		varietatOlivaDao.makePersistent(varietatOliva);
	}
	
	/**
	 * Retorna una varitat experimental.
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR" 
	 * <!-- end-xdoclet-definition -->
	 */
	public VarietatOliva varietatOlivaExperimentalAmbId(Long id) {
		varietatOlivaDao.setHibernateTemplate(getHibernateTemplate());
		return varietatOlivaDao.getById(id.intValue());
	}
	
	/**
	 * Crea una varitat experimental.
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR" 
	 * <!-- end-xdoclet-definition -->
	 */
	public Integer varietatOlivaExperimentalCrear(VarietatOliva varietatOliva) {
		VarietatOliva vOliva = new VarietatOliva();
		vOliva.setNom(varietatOliva.getNom());
		vOliva.setAutoritzada(varietatOliva.getAutoritzada());
		vOliva.setIcona(varietatOliva.getIcona());
		vOliva.setColor(varietatOliva.getColor());
		vOliva.setObservacions(varietatOliva.getObservacions());
		vOliva.setDescomposicioPlantacios(varietatOliva.getDescomposicioPlantacios());
		vOliva.setLots(varietatOliva.getLots());
		vOliva.setNomVarietat(varietatOliva.getNomVarietat());
		vOliva.setExperimental(varietatOliva.getExperimental());
		vOliva.setActiu(varietatOliva.getActiu());
		varietatOlivaDao.setHibernateTemplate(getHibernateTemplate());
		varietatOlivaDao.makePersistent(vOliva);
		return vOliva.getId();
	}
	
	/**
	 * set the hibernate template.
	 * @param hibernateTemplate the hibernate spring template.
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * <!-- end-xdoclet-definition --> 
	 */
	public Object[] obtenirInfoOliva(Long dipositId) {
		dipositDao.setHibernateTemplate(getHibernateTemplate());
		return dipositDao.getInfoOliva(dipositId);
	}
	
	/**
	 * Retorna la categoria resultant de dues categories <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public CategoriaOli categoriaResultantMescla(CategoriaOli cat1, CategoriaOli cat2) {
		categoriaOliDao.setHibernateTemplate(getHibernateTemplate());
		return categoriaOliDao.categoriaResultantMescla(cat1, cat2);
	}
	
	/**
	 * set the hibernate template.
	 * @param hibernateTemplate the hibernate spring template.
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * <!-- end-xdoclet-definition --> 
	 */
	public Collection obtenirVarietatsDiposit(Long dipositId) {
		Collection varietats = new ArrayList();
		entradaDipositDao.setHibernateTemplate(getHibernateTemplate());
		Collection entrades = entradaDipositDao.findEntradaDiposiOliByDiposit(dipositId, true);
		if ((entrades != null) && (entrades.size() > 0)) {
			for (Iterator it = entrades.iterator(); it.hasNext();) {
				EntradaDiposit entrada = (EntradaDiposit)it.next();
				if (entrada.getElaboracio() != null){
					Set partides = entrada.getElaboracio().getPartidaOlivas();
					if ((partides != null) && (partides.size() > 0)) {
						for (Iterator itp = partides.iterator(); itp.hasNext();) {
							PartidaOliva p = (PartidaOliva)itp.next();
							String[] vars = p.getVarietatsQuilos().split("<br/>");
							for (int i = 0; i < vars.length; i++) {
								varietats.add(" · " + vars[i]);
							}
						}
					}
				}
			}
		}
		return varietats;
	}

	
	/**
	 * set the hibernate template.
	 * @param hibernateTemplate the hibernate spring template.
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * <!-- end-xdoclet-definition --> 
	 */
	public Collection obtenirLotDiposit(Long dipositId) {
		Collection lots = new ArrayList();
		lotDao.setHibernateTemplate(getHibernateTemplate());
		List lotes = lotDao.findByDiposit(dipositId, true);
		if (lotes.size() > 0) {
			for (int i = 0; i < lotes.size(); i++) {
				Lot lote = (Lot)lotes.get(i);
				lots.add(" · " + lote.getCodiAssignat() + " (" + lote.getNumeroBotellesActuals() + ")");
			}
		}
		return lots;
	}
	
	/**
	 * Cerca la taxa
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public void creaHistoricLlistatFactura(HistoricLlistatFactura historicLlistatFactura) {
		historicLlistatFacturaDao.setHibernateTemplate(getHibernateTemplate());
		historicLlistatFacturaDao.actualitzaLlistatFactura(historicLlistatFactura);
	}
	
	/**
	 * Cerca la taxa
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public HistoricLlistatFactura findHistoricLlistatFacturaPreformaByOlivicultorAny(String idOlivicultor, String anyActual) {
		historicLlistatFacturaDao.setHibernateTemplate(getHibernateTemplate());
		return historicLlistatFacturaDao.findHistoricLlistatFacturaPreformaByOlivicultorAny(idOlivicultor, anyActual);
	}
	
	/**
	 * Cerca la taxa
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public HistoricLlistatFactura findHistoricLlistatFacturaByOlivicultorAny(String idOlivicultor, String anyActual) {
		historicLlistatFacturaDao.setHibernateTemplate(getHibernateTemplate());
		return historicLlistatFacturaDao.findHistoricLlistatFacturaByOlivicultorAny(idOlivicultor, anyActual);
	}
	
	/**
	 * Torna el llistat d'històrics de llistats de factures.
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR" 
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection historicsLlistatsFactures() {
		historicLlistatFacturaDao.setHibernateTemplate(getHibernateTemplate());
		return historicLlistatFacturaDao.findHistoricFactures();
	}
	
	/**
	 * Torna les dades de la factura per l'identificador doant.
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR" 
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection findDadesPerIdntificador(Long identificador) {
		historicLlistatFacturaDao.setHibernateTemplate(getHibernateTemplate());
		return historicLlistatFacturaDao.findDadesPerIdntificador(identificador);
	}
	
	/**
	 * Torna l'identificador per la factura
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR" 
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Long identificadorFactura() {
		historicLlistatFacturaDao.setHibernateTemplate(getHibernateTemplate());
		return historicLlistatFacturaDao.identificadorFactura();
	}
	
	/**
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection sortidesOrujo() {
		sortidaOrujoDao.setHibernateTemplate(getHibernateTemplate());
		return sortidaOrujoDao.findAll();
	}
	
	/**
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection sortidesOrujoValides(Boolean valid) {
		sortidaOrujoDao.setHibernateTemplate(getHibernateTemplate());
		return sortidaOrujoDao.findAllValides(valid);
	}
	
	/**
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection sortidesOrujoAmbEstabliment(Long establimentId, Boolean valid) {
		sortidaOrujoDao.setHibernateTemplate(getHibernateTemplate());
		return sortidaOrujoDao.findAllAmbEstabliment(establimentId, valid);
	}
	
	/**
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public void actualitzaSortidaOrujo(SortidaOrujo sortidaOrujo) {
		sortidaOrujoDao.setHibernateTemplate(getHibernateTemplate());
		sortidaOrujoDao.makePersistent(sortidaOrujo);
	}
	
	/**
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public SortidaOrujo sortidaOrujoAmbId(Long id) {
		sortidaOrujoDao.setHibernateTemplate(getHibernateTemplate());
		return sortidaOrujoDao.getById(id);
	}
	
	/**
	 * Modifica una sortida d'orujo.
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public void sortidaOrujoModificar(SortidaOrujo sortidaOrujo) {
		sortidaOrujoDao.setHibernateTemplate(getHibernateTemplate());
		sortidaOrujoDao.makePersistent(sortidaOrujo);
	}
	
	/**
	 * Crea una sortida d'orujo.
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public void sortidaOrujoCrear(SortidaOrujo sortidaOrujo) {
		sortidaOrujoDao.setHibernateTemplate(getHibernateTemplate());
		sortidaOrujoDao.makePersistent(sortidaOrujo);
	}
	
	/**
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Long cercaOlivicultorPerRfid(String rfid) {
		olivicultorDao.setHibernateTemplate(getHibernateTemplate());
		return olivicultorDao.findByRfid(rfid);
	}
	
	/**
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Olivicultor cercaOlivicultorAmbValor(String valor) {
		olivicultorDao.setHibernateTemplate(getHibernateTemplate());
		return olivicultorDao.findByValue(valor);
	}
	
	/**
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Olivicultor cercaOlivicultorAmbNif(String nif) {
		olivicultorDao.setHibernateTemplate(getHibernateTemplate());
		return olivicultorDao.findByNif(nif);
	}
	
	/**
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection rendimentVarietatPerCampanya(Long idCampanya) {
		Long campanyaId = idCampanya;
		if (idCampanya == null) {
			campanyaId = campanyaCercaActual();
		}
		rendimentVarietatDao.setHibernateTemplate(getHibernateTemplate());
		return rendimentVarietatDao.rendimentVarietatPerCampanya(campanyaId);
	}
	
	/**
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public void creaRendimentsVarietatsCampanya(Campanya campanya, String tipusRendiment, RendimentVarietat[] rendiments) {
		for (int i = 0; i < rendiments.length; i++) {
			RendimentVarietat rv = rendiments[i];
			if (rv.getRendiment() != null) {
				rendimentVarietatDao.setHibernateTemplate(getHibernateTemplate());
				RendimentVarietat existeix = rendimentVarietatDao.findByCampanyaVarietat(campanya, rv.getVarietatOliva());
				if (existeix == null) {
					RendimentVarietat rendimentVarietat = new RendimentVarietat();
					rendimentVarietat.setCampanya(campanya);
					rendimentVarietat.setRendiment(rv.getRendiment());
					rendimentVarietat.setTipusRendiment(tipusRendiment);
					rendimentVarietat.setVarietatOliva(rv.getVarietatOliva());
					rendimentVarietat.setIdVarietatOliva(rv.getIdVarietatOliva());
					rendimentVarietatDao.setHibernateTemplate(getHibernateTemplate());
					rendimentVarietatDao.makePersistent(rendimentVarietat);
					
					// Modificam la producció màxima a les descomposicions de les plantacions.
					modificarProduccioMaxima(rv.getIdVarietatOliva().intValue(), rv.getRendiment(), tipusRendiment);
				} else {
					existeix.setCampanya(campanya);
					existeix.setRendiment(rv.getRendiment());
					existeix.setTipusRendiment(tipusRendiment);
					existeix.setVarietatOliva(rv.getVarietatOliva());
					existeix.setIdVarietatOliva(rv.getIdVarietatOliva());
					rendimentVarietatDao.setHibernateTemplate(getHibernateTemplate());
					rendimentVarietatDao.makePersistent(existeix);
					
					// Modificam la producció màxima a les descomposicions de les plantacions.
					modificarProduccioMaxima(rv.getIdVarietatOliva().intValue(), rv.getRendiment(), tipusRendiment);
				}
			}
		}
	}
	
	/**
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public void modificarRendimentVarietat(Long idCampanya, Integer idVarietat, Double rendiment, String tipusRendiment) {
		Long campanyaId = idCampanya;
		if (idCampanya == null) {
			campanyaId = campanyaCercaActual();
		}
		rendimentVarietatDao.setHibernateTemplate(getHibernateTemplate());
		RendimentVarietat rendimentVarietat = rendimentVarietatDao.findByCampanyaVarietat(campanyaId, idVarietat);
		if (rendimentVarietat == null){
			rendimentVarietat = new RendimentVarietat();
			rendimentVarietat.setIdVarietatOliva(idVarietat.longValue());
			rendimentVarietat.setCampanya(campanyaAmbId(campanyaId.intValue()));
			rendimentVarietat.setVarietatOliva(varietatOlivaDao.getById(idVarietat));
		}
		rendimentVarietat.setRendiment(rendiment);
		rendimentVarietat.setTipusRendiment(tipusRendiment);
		
		rendimentVarietatDao.setHibernateTemplate(getHibernateTemplate());
		rendimentVarietatDao.makePersistent(rendimentVarietat);
		
		// Modificam la producció màxima a les descomposicions de les plantacions.
		modificarProduccioMaxima(idVarietat, rendiment, tipusRendiment);
	}
	
	/**
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public String obtenirTipusRendimentCampanyaVarietat(Long idCampanya, Integer idVarietat) {
		Long campanyaId = idCampanya;
		if (idCampanya == null) {
			campanyaId = campanyaCercaActual();
		}
		rendimentVarietatDao.setHibernateTemplate(getHibernateTemplate());
		return rendimentVarietatDao.tipusRendimentCampanyaVarietat(campanyaId, idVarietat);
	}
	
	/**
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Double obtenirRendimentCampanyaVarietat(Long idCampanya, Integer idVarietat) {
		Long campanyaId = idCampanya;
		if (idCampanya == null) {
			campanyaId = campanyaCercaActual();
		}
		rendimentVarietatDao.setHibernateTemplate(getHibernateTemplate());
		return rendimentVarietatDao.rendimentCampanyaVarietat(campanyaId, idVarietat);
	}
	
	/**
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public void modificarProduccioMaxima(Integer idVarietat, Double rendiment, String tipusRendiment) {
		descomposicioPlantacioDao.setHibernateTemplate(getHibernateTemplate());
		Collection descomposicions = descomposicioPlantacioDao.findByVarietat(idVarietat);
		if ((descomposicions != null) && (descomposicions.size() > 0)) {
			for (Iterator it = descomposicions.iterator(); it.hasNext();) {
				DescomposicioPlantacio descomposicioPlantacio = (DescomposicioPlantacio)it.next();
				
				Double maxProduccioAux = descomposicioPlantacio.getMaxProduccio();
				Double maxProduccio = descomposicioPlantacio.getMaxProduccio();
				if (tipusRendiment.equals("hectarea")) {
					maxProduccio = rendiment * descomposicioPlantacio.getSuperficie();
				} else if (tipusRendiment.equals("arbre")) {
					maxProduccio = rendiment * descomposicioPlantacio.getNumeroOliveres();
				}
				descomposicioPlantacio.setMaxProduccio(maxProduccio);
				
				Double produccioRestant = descomposicioPlantacio.getProduccioRestant();
				if ((produccioRestant == null) || (produccioRestant == maxProduccioAux)) {
					descomposicioPlantacio.setProduccioRestant(maxProduccio);
				} else {
					Double newProduccioRestant = (maxProduccio - maxProduccioAux) + produccioRestant;
					descomposicioPlantacio.setProduccioRestant(newProduccioRestant);
				}
				
				descomposicioPlantacioDao.setHibernateTemplate(getHibernateTemplate());
				descomposicioPlantacioDao.makePersistent(descomposicioPlantacio);
			}
		}
	}
	
	/**
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection cercaVarietatsOlivaPerPlantacions(String ids) {
		descomposicioPlantacioDao.setHibernateTemplate(getHibernateTemplate());
		return descomposicioPlantacioDao.findByPlantacions(ids);
	}
	
	/**
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection cercaVarietatsOlivaTaulaPerPlantacions(String ids) {
		descomposicioPlantacioDao.setHibernateTemplate(getHibernateTemplate());
		return descomposicioPlantacioDao.findByPlantacionsOlivaDeTaula(ids);
	}
	
	/**
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Double obtenirTotalProduccioRestantOlivicultor(Long idOlivicultor) {
		descomposicioPlantacioDao.setHibernateTemplate(getHibernateTemplate());
		return descomposicioPlantacioDao.obtenirTotalProduccioRestantOlivicultor(idOlivicultor);
	}
	
	/**
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" 
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition -->
	 */
	public void guardarEtiquetesLot(EtiquetesLot etiquetesLot) {
		etiquetesLotDao.setHibernateTemplate(getHibernateTemplate());
		etiquetesLotDao.makePersistent(etiquetesLot);
	}
	
	/**
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" 
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition -->
	 */
	public void eliminarEtiquetesLot(EtiquetesLot etiquetesLot) {
		etiquetesLotDao.setHibernateTemplate(getHibernateTemplate());
		etiquetesLotDao.makeTransient(etiquetesLot);
	}
	
	/**
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" 
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition -->
	 */
	public void retirarEtiquetesLot(EtiquetesLot etiquetesLot) {
		
		etiquetesLot.setRetirat(true);
		
		etiquetesLotDao.setHibernateTemplate(getHibernateTemplate());
		etiquetesLotDao.makePersistent(etiquetesLot);
	}
	
	/**
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" 
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition -->
	 */
	public EtiquetesLot findEtiquetesLotById(Long idEtiquetesLot) {
		etiquetesLotDao.setHibernateTemplate(getHibernateTemplate());
		return etiquetesLotDao.getById(idEtiquetesLot);
	}
	
	/**
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" 
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection findEtiquetesLotByIdLot(Long idLot) {
		etiquetesLotDao.setHibernateTemplate(getHibernateTemplate());
		return etiquetesLotDao.findEtiquetesLotByIdLot(idLot);
	}
	
	/**
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" 
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection findEtiquetesLot() {
		etiquetesLotDao.setHibernateTemplate(getHibernateTemplate());
		return etiquetesLotDao.findEtiquetesLot();
	}
	
	/**
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" 
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection findEtiquetesLotByEstabliment(Long idEstabliment) {
		etiquetesLotDao.setHibernateTemplate(getHibernateTemplate());
		return etiquetesLotDao.findEtiquetesLotByEstabliment(idEstabliment);
	}
	
	/**
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" 
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection findEtiquetesLotOriginalsByEstabliment(Long idEstabliment) {
		etiquetesLotDao.setHibernateTemplate(getHibernateTemplate());
		return etiquetesLotDao.findEtiquetesLotOriginalsByEstabliment(idEstabliment);
	}
	
	/**
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" 
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection findEtiquetesLotByLot(Long idLot) {
		etiquetesLotDao.setHibernateTemplate(getHibernateTemplate());
		return etiquetesLotDao.findEtiquetesLotByLot(idLot);
	}
	
	/**
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	* @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" 
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection findEtiquetesLotEmpradesByLot(Long idLot, Boolean esEcologic) {
		etiquetesLotDao.setHibernateTemplate(getHibernateTemplate());
		return etiquetesLotDao.findEtiquetesLotEmpradesByLot(idLot,esEcologic);
	}
	
	/**
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" 
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection findEtiquetesLotDisponiblesByEstabliment(Long idEstabliment) {
		etiquetesLotDao.setHibernateTemplate(getHibernateTemplate());
		return etiquetesLotDao.findEtiquetesLotDisponiblesByEstabliment(idEstabliment);
	}
	
	/**
	 * Devuelve un listado de elaboraciones por establecimiento 
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection findEtiquetesLotEmpradesByEstabliment(Long idEstabliment){
		etiquetesLotDao.setHibernateTemplate(getHibernateTemplate());
		return etiquetesLotDao.findEtiquetesLotEmpradesByEstabliment(idEstabliment);
	}

	/**
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	* @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" 
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection findEtiquetesLotDisponiblesByEstablimentILot(Long idEstabliment, Lot lot, Boolean olivaTaula, Boolean esEcologic) {
		etiquetesLotDao.setHibernateTemplate(getHibernateTemplate());
		return etiquetesLotDao.findEtiquetesLotDisponiblesByEstablimentILot(idEstabliment, lot, olivaTaula, esEcologic);
	}
	
	/**
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	* @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" 
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection findEtiquetesLotRetiradesByEstabliment(Long idEstabliment) {
		etiquetesLotDao.setHibernateTemplate(getHibernateTemplate());
		return etiquetesLotDao.findEtiquetesLotRetiradesByEstabliment(idEstabliment);
	}
	
	/**
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" 
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition -->
	 */
	public boolean teEtiquetesLotSolapades(EtiquetesLot etiquetesLot) {
		etiquetesLotDao.setHibernateTemplate(getHibernateTemplate());
		return etiquetesLotDao.teEtiquetesLotSolapades(etiquetesLot);
	}
	
	/**
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" 
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition -->
	 */
	public boolean teEtiquetesLotFills(EtiquetesLot etiquetesLot) {
		etiquetesLotDao.setHibernateTemplate(getHibernateTemplate());
		return etiquetesLotDao.teFills(etiquetesLot);
	}
	
	/**
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * <!-- end-xdoclet-definition --> 
	 */
	public Collection sortidesDiposits() {
		sortidaDipositDao.setHibernateTemplate(getHibernateTemplate());
		return sortidaDipositDao.findAll(true);
	}
	
	/**
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * <!-- end-xdoclet-definition --> 
	 */
	public Collection sortidesDipositsPerEstabliment(Long establimentId) {
		sortidaDipositDao.setHibernateTemplate(getHibernateTemplate());
		return sortidaDipositDao.findByEstablecimiento(establimentId, true);
	}
	
	/**
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" 
	 * <!-- end-xdoclet-definition -->
	 */
	public SortidaDiposit getSortidaDipositByTraza(Long idTraza){
		sortidaDipositDao.setHibernateTemplate(getHibernateTemplate());
		return sortidaDipositDao.findByTraza(idTraza, new Boolean(true));
	}
	
	/**
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * <!-- end-xdoclet-definition --> 
	 */
	public Collection sortidesLots() {
		sortidaLotDao.setHibernateTemplate(getHibernateTemplate());
		return sortidaLotDao.findAll(true);
	}
	
	/**
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * <!-- end-xdoclet-definition --> 
	 */
	public Collection sortidesLotsPerEstabliment(Long establimentId) {
		sortidaLotDao.setHibernateTemplate(getHibernateTemplate());
		return sortidaLotDao.findByEstablecimiento(establimentId, true);
	}
	
	
	/**
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * <!-- end-xdoclet-definition --> 
	 */
	public SortidaDiposit sortidaDipositAmbId(Long idSortida) {
		sortidaDipositDao.setHibernateTemplate(getHibernateTemplate());
		return sortidaDipositDao.getById(idSortida);
	}
	
	/**
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * <!-- end-xdoclet-definition --> 
	 */
	public SortidaLot sortidaLotAmbId(Long idSortida) {
		sortidaLotDao.setHibernateTemplate(getHibernateTemplate());
		return sortidaLotDao.getById(idSortida);
	}
	
	/**
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * <!-- end-xdoclet-definition --> 
	 */
	public void actualitzaSortidaDiposit(SortidaDiposit sortidaDiposit) {
		sortidaDipositDao.setHibernateTemplate(getHibernateTemplate());
		sortidaDipositDao.makePersistent(sortidaDiposit);
	}
	
	/**
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * <!-- end-xdoclet-definition --> 
	 */
	public void actualitzaSortidaLot(SortidaLot sortidaLot) {
		sortidaLotDao.setHibernateTemplate(getHibernateTemplate());
		sortidaLotDao.makePersistent(sortidaLot);
	}
	
	/**
	 * set the hibernate template.
	 * @param hibernateTemplate the hibernate spring template.
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * <!-- end-xdoclet-definition --> 
	 */
	public void canviaPropietariOli(Long id, Integer tipus){
		PartidaOli partida = null;
		switch (tipus){
			case Constants.TRAZA_TIPUS_OLI_DISPONIBLE_DIPOSIT:
				dipositDao.setHibernateTemplate(getHibernateTemplate());
				Diposit diposit = dipositDao.getById(id);
				partida = diposit.getPartidaOli();
				break;
			case Constants.TRAZA_TIPUS_OLI_DISPONIBLE_LOTE:
				lotDao.setHibernateTemplate(getHibernateTemplate());
				Lot lot = lotDao.getById(id);
				partida = lot.getPartidaOli();
				break;
			default: 
				break;
		}
		if (partida != null){
			partida.setOlivicultorEsPropietari(new Boolean(false));
			partidaOliDao.setHibernateTemplate(getHibernateTemplate());
			partidaOliDao.makePersistent(partida);
		}
	}
	
//Per a obtenir les coordenades per provincia - municipi - poligon - parcela:
//---------------------------------------------------------------------------
	/**
	 * Funció per consultar les coordenades geogràfiques d'un poligon i una parcela.
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * <!-- end-xdoclet-definition --> 
	 */
	public String[] consultaCoordenades(Long prov, Long muni, String poligon, String parcela) {
		String param1 = "";
		String param2 = "";
		String coordenadaX = "";
		String coordenadaY = "";
		
		try {
			provinciaDao.setHibernateTemplate(getHibernateTemplate());
			Provincia provincia = provinciaDao.getById(prov);
			municipiDao.setHibernateTemplate(getHibernateTemplate());
	    	Municipi municipi = municipiDao.getById(muni);
			
	    	// Construim la ruta de la petició per obtenir el codi de catastre.
	    	String ruta = "http://ovc.catastro.meh.es/ovcservweb/OVCSWLocalizacionRC/OVCCallejero.asmx/Consulta_DNPPP";
	    	ruta += "?Provincia=" + URLEncoder.encode(normalitzarILlevarAccents(provincia.getNom()), "ISO-8859-1");
	    	ruta += "&Municipio=" + URLEncoder.encode(normalitzarILlevarAccents(municipi.getNom()), "ISO-8859-1");
	    	ruta += "&Poligono=" + poligon;
	    	ruta += "&Parcela=" + parcela;
	   		
	    	logger.info("Ruta per obtenir el codi del catastre: " + ruta);
	    	
	    	URL url = new URL(ruta);
	    	BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
	    	String str = "";
	    	List xml = new ArrayList();
	    	// Obtenim la resposta i cercam l'informació que ens interessa.
	    	while ((str = br.readLine()) != null) {
	    		xml.add(str);
	    		if ((str.indexOf("<pc1>") != -1) && (param1.equals(""))) {
	    			param1 = getTagContent(str, "pc1");
	    		} else if ((str.indexOf("<pc2>") != -1) && (param2.equals(""))) {
	    			param2 = getTagContent(str, "pc2");
	    		}
	    	}
	    	br.close();
	    	
	    	// Si hem obtingut l'informació que cercavem, construim una altra petició per obtenir les coordenades.
	    	if ((!param1.equals("")) && (!param2.equals(""))) {
	    		String catastre = param1 + "" + param2;
	    		
	    		String rutaLocalitzacio = "http://ovc.catastro.meh.es/ovcservweb/OVCSWLocalizacionRC/OVCCoordenadas.asmx/Consulta_CPMRC";
	    		rutaLocalitzacio += "?Provincia=" + URLEncoder.encode(normalitzarILlevarAccents(provincia.getNom()), "ISO-8859-1");
	    		rutaLocalitzacio += "&Municipio=" + URLEncoder.encode(normalitzarILlevarAccents(municipi.getNom()), "ISO-8859-1");
	    		rutaLocalitzacio += "&SRS=" + URLEncoder.encode("EPSG:4326", "UTF-8");
	    		rutaLocalitzacio += "&RC=" + catastre;

	    		logger.info("Ruta per obtenir les coordenades: " + rutaLocalitzacio);
	    		
	    		URL urlLocalitzacio = new URL(rutaLocalitzacio);
		    	BufferedReader brLocalitzacio = new BufferedReader(new InputStreamReader(urlLocalitzacio.openStream()));
		    	str = "";
		    	List xmlLocalitzacio = new ArrayList();
		    	// Obtenim la resposta i cercam l'informació que ens interessa.
		    	while ((str = brLocalitzacio.readLine()) != null) {
		    		xmlLocalitzacio.add(str);
		    		if ((str.indexOf("<xcen>") != -1) && (coordenadaX.equals(""))) {
		    			coordenadaX = getTagContent(str, "xcen");
		    		} else if ((str.indexOf("<ycen>") != -1) && (coordenadaY.equals(""))) {
		    			coordenadaY = getTagContent(str, "ycen");
		    		}
		    	}
		    	brLocalitzacio.close();
		    	
		    	// Si hem obtingut les coordenades, les guardam a la base de dades.
		    	if ((!coordenadaX.equals("")) && (!coordenadaY.equals(""))) {
		    		String[] coords = {coordenadaX, coordenadaY, catastre};
		    		return coords;
		    	}
	    	}
		} catch (Exception e) { }
		return null;
	}
	
	public String getTagContent(String source, String tagName) {
		String retorn = "";
		try {
			if (((source != null) && (!source.equals(""))) && ((tagName != null) && (!tagName.equals("")))) {
				String sourceAux = "";
				int inici = source.indexOf("<" + tagName + ">");
				int fi = source.indexOf("</" + tagName + ">");
				sourceAux = source.substring(inici, fi);
				sourceAux = sourceAux.replaceAll("<" + tagName + ">", "");
				retorn = sourceAux;
			}
		} catch (Exception e) { }
		
		return retorn;
	}
	
	private String normalitzarILlevarAccents(String str) {
		String resultat = str.toUpperCase();
		  
		if (resultat.equals("SANT LLORENÇ DES CARDASSAR")){
			resultat = "SANT LLORENC DES CARDASSAR";
		} else if(resultat.equals("VILAFRANCA DE BONANY")){
			resultat = "VILAFRANCA BONANY";
		}
		
		return resultat.
			replaceAll("[ÀÁ]","A").
			replaceAll("[ÈÉ]","E").
			replaceAll("[ÍÏ]","I").
			replaceAll("[ÓÒ]","O").
			replaceAll("Ú","U");
	}
	
//---------------------------------------------------------------------------

	/**
	 * set the hibernate template.
	 * @param hibernateTemplate the hibernate spring template.
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition --> 
	 */
	public EtiquetesLot findEtiquetesLotByEtiqueta(String lletres, Integer numeros){
		etiquetesLotDao.setHibernateTemplate(getHibernateTemplate());
		return etiquetesLotDao.findByEtiqueta(lletres, numeros);
	}
	
	/**
	 * set the hibernate template.
	 * @param hibernateTemplate the hibernate spring template.
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition --> 
	 */
	public boolean esDataDipositCorrecte(Date data, Long idDiposit){
		entradaDipositDao.setHibernateTemplate(getHibernateTemplate());
		EntradaDiposit ultimaEntrada = entradaDipositDao.findUltimaByDeposito(idDiposit, new Boolean(true));
		
		sortidaDipositDao.setHibernateTemplate(getHibernateTemplate());
		SortidaDiposit ultimaSortida = sortidaDipositDao.findUltimaByDeposito(idDiposit, new Boolean(true));
		
		if( (ultimaEntrada == null || !ultimaEntrada.getData().after(data)) && (ultimaSortida == null || !ultimaSortida.getData().after(data))){
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * set the hibernate template.
	 * @param hibernateTemplate the hibernate spring template.
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition --> 
	 */
	public boolean esDataLotCorrecte(Date data, Long idLot){
		entradaLotDao.setHibernateTemplate(getHibernateTemplate());
		EntradaLot ultimaEntrada = entradaLotDao.findUltimaByLot(idLot, new Boolean(true));
		
		if(ultimaEntrada == null || !ultimaEntrada.getData().after(data)){
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * set the hibernate template.
	 * @param hibernateTemplate the hibernate spring template.
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition --> 
	 */
	public boolean esDataPartidaOlivaCorrecte(Date data, Long idPartidaOliva){
		partidaOlivaDao.setHibernateTemplate(getHibernateTemplate());
		PartidaOliva po = partidaOlivaDao.getById(idPartidaOliva);
		
		if(!po.getData().after(data)){
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * set the hibernate template.
	 * @param hibernateTemplate the hibernate spring template.
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition --> 
	 */
	public boolean esDataCampanyaCorrecte(Date data){
		campanyaDao.setHibernateTemplate(getHibernateTemplate());
		Long idCampanya = campanyaDao.getCampanyaActual();
		
		campanyaDao.setHibernateTemplate(getHibernateTemplate());
		Campanya campanya = campanyaDao.campanyaAmbId(idCampanya.intValue());
		
		if(!campanya.getData().after(data)){
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * set the hibernate template.
	 * @param hibernateTemplate the hibernate spring template.
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * <!-- end-xdoclet-definition --> 
	 */
	public String findResumEntradaDiposiOliByElaboracio(Long elaId){
		entradaDipositDao.setHibernateTemplate(getHibernateTemplate());
		Collection entrades = entradaDipositDao.findEntradaDiposiOliByElaboracio(elaId, new Boolean(true));
		
		String resum = "";
		for (Iterator it = entrades.iterator(); it.hasNext();){
			EntradaDiposit edi = (EntradaDiposit)it.next();
			Diposit dip = edi.getDiposit();
			if (!resum.equals("")) resum += "<br/>";
			if (dip.getCodiOriginal() != null && !dip.getCodiOriginal().equals("")){
				resum += dip.getCodiOriginal();
			} else {
				resum += dip.getCodiAssignat();
			}
			resum += " (" + edi.getLitres() + " l.)";
		}
		
		return resum;
	}
	
	/**
	 * set the hibernate template.
	 * @param hibernateTemplate the hibernate spring template.
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * <!-- end-xdoclet-definition --> 
	 */
	public String findResumsortidaOlivicultorByElaboracio(Long elaId){
		entradaDipositDao.setHibernateTemplate(getHibernateTemplate());
		Collection entrades = entradaDipositDao.findOliRetiratPropietarioByElaboracio(elaId, new Boolean(true));
		
		String resum = "";
		for (Iterator it = entrades.iterator(); it.hasNext();){
			EntradaDiposit edi = (EntradaDiposit)it.next();
			if (!resum.equals("")) resum += "<br/>";
			resum += edi.getOlivicultor().getNom() + " (" + edi.getLitres() + " l.)";
		}
		
		return resum;
	}
	
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" 
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Producte producteAmbId(Long id) {
		if (id != null){
			producteDao.setHibernateTemplate(getHibernateTemplate());
			return producteDao.getById(id);
		} else {
			return null;
		}
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" 
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition -->
	 */
	public List producteAmbNom(String nom, Long idEstabliment) {
		if (nom != null){
			producteDao.setHibernateTemplate(getHibernateTemplate());
			return producteDao.getByNom(nom, idEstabliment);
		} else {
			return null;
		}
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" 
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection producteActiusAmbEstabliment(Long idEst) {
		producteDao.setHibernateTemplate(getHibernateTemplate());
		return producteDao.findActiusAmbEstabliment(idEst);
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" 
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection producteAmbEstabliment(Long idEst) {
		producteDao.setHibernateTemplate(getHibernateTemplate());
		return producteDao.findAllAmbEstabliment(idEst);
	}
	
	/**
	 * Comprova si existeixen productes amb el nom indicat
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Boolean existeixProducteEstabliment(Long partidaOliId, String partidaOliNom, Long establimentId){
		partidaOliDao.setHibernateTemplate(getHibernateTemplate());
		return producteDao.existeixProducteEstabliment(partidaOliId, partidaOliNom, establimentId);
	}
	
	/**
	 * Crea un nou producte
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Long creaProducte(String nom, Establiment establiment){
		producteDao.setHibernateTemplate(getHibernateTemplate());
		Producte producte = new Producte();
		producte.setNom(nom);
		producte.setActiu(new Boolean(true));
		producte.setEstabliment(establiment);
		producteDao.makePersistent(producte);
		
		return producte.getId();
	}
	
	/**
	 * Modifica un producte
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public void modificaProducte(Long id, String nom, Boolean actiu){
		producteDao.setHibernateTemplate(getHibernateTemplate());
		Producte producte = producteDao.getById(id);
		producte.setNom(nom);
		producte.setActiu(actiu);
		producteDao.makePersistent(producte);
	}
	
	/**
	 * Elimina el producte
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public void producteEsborrar(Long id){
		producteDao.setHibernateTemplate(getHibernateTemplate());
		Producte producte = producteDao.getById(id);
		if (producte != null){
			producteDao.makeTransient(producte);
		}
	}
	
	/**
	 * Comprova si existeixen lots associats al producte
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" 
	 * <!-- end-xdoclet-definition -->
	 */
	public Boolean existeixenLotsAssociatsProducte(Long lid) {
		lotDao.setHibernateTemplate(getHibernateTemplate());
		return lotDao.existeixenLotsAssociatsProducte(lid, new Boolean(true));
	}
	
	/**
	 * Només serveix per poder especificar els permisos amb xdoclet. 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Long informacioUtilCrear(InformacioUtil q){
		informacioUtilDao.setHibernateTemplate(getHibernateTemplate());
		informacioUtilDao.makePersistent(q);
		return q.getId();
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public InformacioUtil informacioUtilAmbId(Long id) {
		informacioUtilDao.setHibernateTemplate(getHibernateTemplate());
		return informacioUtilDao.getById(id);
	}
	
	/**
	 * Cerca totes les informacions utils<!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection informacioUtilCercaTots() {
		informacioUtilDao.setHibernateTemplate(getHibernateTemplate());
		return informacioUtilDao.findAll();
	}
	
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * <!-- end-xdoclet-definition -->
	 * @generated
	 */
	public void informacioUtilEsborrar(Long id) {
		informacioUtilDao.setHibernateTemplate(getHibernateTemplate());
		InformacioUtil informacioUtil = informacioUtilDao.getById(id);
		if (informacioUtil != null) {
			informacioUtilDao.setHibernateTemplate(getHibernateTemplate());
			informacioUtilDao.makeTransient(informacioUtil);
		}
	}
	
	
	
	/**
	 * Només serveix per poder especificar els permisos amb xdoclet. 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Long gestioInfografiaCrear(GestioInfografia q){
		gestioInfografiaDao.setHibernateTemplate(getHibernateTemplate());
		gestioInfografiaDao.makePersistent(q);
		return q.getId();
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public GestioInfografia gestioInfografiaAmbId(Long id) {
		gestioInfografiaDao.setHibernateTemplate(getHibernateTemplate());
		return gestioInfografiaDao.getById(id);
	}
	
	/**
	 * Cerca totes les informacions utils<!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection gestioInfografiaCercaTots() {
		gestioInfografiaDao.setHibernateTemplate(getHibernateTemplate());
		return gestioInfografiaDao.findAll();
	}
	
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * <!-- end-xdoclet-definition -->
	 * @generated
	 */
	public void gestioInfografiaEsborrar(Long id) {
		gestioInfografiaDao.setHibernateTemplate(getHibernateTemplate());
		GestioInfografia gestioInfografia = gestioInfografiaDao.getById(id);
		if (gestioInfografia != null) {
			gestioInfografiaDao.setHibernateTemplate(getHibernateTemplate());
			gestioInfografiaDao.makeTransient(gestioInfografia);
		}
	}
	
	/**
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 */
	public void arxiuCrear(Arxiu arxiu) {
		arxiuDao.setHibernateTemplate(getHibernateTemplate());
		arxiuDao.makePersistent(arxiu);
	}

	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Arxiu arxiuAmbId(Long id) {
		arxiuDao.setHibernateTemplate(getHibernateTemplate());
		return arxiuDao.getById(id);
	}
	
	/**
	 * Només serveix per poder especificar els permisos amb xdoclet. 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" 
	 * <!-- end-xdoclet-definition -->
	 */
	public Long crearAvis(Avis q){
		avisDao.setHibernateTemplate(getHibernateTemplate());
		avisDao.makePersistent(q);
		return q.getId();
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" 
	 * <!-- end-xdoclet-definition -->
	 */
	public Avis avisAmbId(Long id) {
		avisDao.setHibernateTemplate(getHibernateTemplate());
		return avisDao.getById(id);
	}
	
	/**
	 * Cerca tots els avisos de l'establiment
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" 
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection avisCercaTotsPerEstabliment(Long idEstabliment) {
		avisDao.setHibernateTemplate(getHibernateTemplate());
		return avisDao.findByEstabliment(idEstabliment);
	}
	
	/**
	 * Cerca tots els avisos pendents de l'establiment
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" 
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection avisCercaPendentsPerEstabliment(Long idEstabliment) {
		avisDao.setHibernateTemplate(getHibernateTemplate());
		return avisDao.cercaPendentsByEstabliment(idEstabliment);
	}
	
	/**
	 * Cerca si hi ha avisos pendents a l'establiment
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" 
	 * <!-- end-xdoclet-definition -->
	 */
	public Boolean avisExisteixenPendentsPerEstabliment(Long idEstabliment) {
		avisDao.setHibernateTemplate(getHibernateTemplate());
		return avisDao.existeixenPendentsByEstabliment(idEstabliment);
	}
	
	/**
	 * Cerca si hi ha avisos pendents a l'establiment
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" 
	 * <!-- end-xdoclet-definition -->
	 */
	public void avisActualitzaData(Long idAvis) {
		avisDao.setHibernateTemplate(getHibernateTemplate());
		Avis avis = avisDao.getById(idAvis);

		Date data = new Date();
		int frec = Integer.parseInt(avis.getFrecuencia());
		
		Calendar cal = Calendar.getInstance();
		cal.setTime( avis.getDataSeguent() );
		while (cal.getTime().getTime() < data.getTime()){
			if ((frec == 30) || (frec == 60) || (frec == 90) || (frec == 180)){
				cal.add( Calendar.MONTH, (frec/30) );
			} else if ((frec == 365) || (frec == 730) || (frec == 1095)){
				cal.add( Calendar.YEAR, (frec/365) );
			} else {
	//			avis.getDataSeguent().setTime(avis.getDataSeguent().getTime() + (frec * Long.valueOf(24 * 60 * 60 * 1000)));
				cal.add( Calendar.DATE, frec );
			}
		}
		avis.setDataSeguent(cal.getTime());
		crearAvis(avis);		
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" 
	 * <!-- end-xdoclet-definition -->
	 * @generated
	 */
	public void avisEsborrar(Long id) {
		avisDao.setHibernateTemplate(getHibernateTemplate());
		Avis avis = avisDao.getById(id);
		if (avis != null) {
			avisDao.setHibernateTemplate(getHibernateTemplate());
			avisDao.makeTransient(avis);
		}
	}
	
	
	/**
	 * Només serveix per poder especificar els permisos amb xdoclet. 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Long crearQuadernCamp(QuadernCamp q){
		quadernCampDao.setHibernateTemplate(getHibernateTemplate());
		quadernCampDao.makePersistent(q);
		return q.getId();
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public QuadernCamp quadernCampAmbId(Long id) {
		quadernCampDao.setHibernateTemplate(getHibernateTemplate());
		return quadernCampDao.getById(id);
	}
	
	/**
	 * Cerca tots els quadernCampos de l'establiment
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection quadernCampCercaTotsPerOlivicultor(Long idOlivicultor, Date dataInici, Date dataFi) {
		quadernCampDao.setHibernateTemplate(getHibernateTemplate());
		return quadernCampDao.findByOlivicultor(idOlivicultor, dataInici, dataFi);
	}
	
	/**
	 * Cerca tots els quadernCampos de l'establiment
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection quadernCampCercaOlivicultors() {
		quadernCampDao.setHibernateTemplate(getHibernateTemplate());
		return quadernCampDao.findOlivicultors();
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * <!-- end-xdoclet-definition -->
	 * @generated
	 */
	public void quadernCampEsborrar(Long id) {
		quadernCampDao.setHibernateTemplate(getHibernateTemplate());
		QuadernCamp quadernCamp = quadernCampDao.getById(id);
		if (quadernCamp != null) {
			quadernCampDao.setHibernateTemplate(getHibernateTemplate());
			quadernCampDao.makeTransient(quadernCamp);
		}
	}
	
	
	
	/**
	 * Només serveix per poder especificar els permisos amb xdoclet. 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Long documentInspeccioCrear(DocumentInspeccio q){
		documentInspeccioDao.setHibernateTemplate(getHibernateTemplate());
		documentInspeccioDao.makePersistent(q);
		return q.getId();
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public DocumentInspeccio documentInspeccioAmbId(Long id) {
		documentInspeccioDao.setHibernateTemplate(getHibernateTemplate());
		return documentInspeccioDao.getById(id);
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public List documentsInspeccioByEstabliment(Long idEstabliment) {
		documentInspeccioDao.setHibernateTemplate(getHibernateTemplate());
		return documentInspeccioDao.findByEstabliment(idEstabliment);
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public List documentsInspeccioByOlivicultor(Long idOlivicultor) {
		documentInspeccioDao.setHibernateTemplate(getHibernateTemplate());
		return documentInspeccioDao.findByOlivicultor(idOlivicultor);
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public PartidaOli getPartidaNoDOByNomAndEstabliment(String partidaOliNom, Establiment establiment) {
		partidaOliDao.setHibernateTemplate(getHibernateTemplate());
		PartidaOli partida = partidaOliDao.getByNomAndEstabliment(partidaOliNom + "_NoDO", establiment.getId());
		if (partida == null) {
			partida = new PartidaOli();
			partida.setDataCreacio(new Date());
			partida.setEsActiu(true);
			partida.setEstabliment(establiment);
			partida.setNom(partidaOliNom + "_NoDO");
			categoriaOliDao.setHibernateTemplate(getHibernateTemplate());
			partida.setCategoriaOli(categoriaOliDao.getById(Constants.CATEGORIA_NO_DO));
			partida.setCategoriaOliOriginal(partida.getCategoriaOli());
			partida.setEsVisualitza(new Boolean(false));
			partida.setOlivicultorEsPropietari(new Boolean(true));
			
			partidaOliDao.setHibernateTemplate(getHibernateTemplate());
			partidaOliDao.makePersistent(partida);
		}
		return partida;
	}
	
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * <!-- end-xdoclet-definition -->
	 * @generated
	 */
	public void documentInspeccioEsborrar(Long id) {
		documentInspeccioDao.setHibernateTemplate(getHibernateTemplate());
		DocumentInspeccio documentInspeccio = documentInspeccioDao.getById(id);
		if (documentInspeccio != null) {
			documentInspeccioDao.setHibernateTemplate(getHibernateTemplate());
			documentInspeccioDao.makeTransient(documentInspeccio);
		}
	}
	
	
	/**
	 * Només serveix per poder especificar els permisos amb xdoclet. 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Long sortidaOliFacturacioCrear(SortidaOliFacturacio q){
		sortidaOliFacturacioDao.setHibernateTemplate(getHibernateTemplate());
		sortidaOliFacturacioDao.makePersistent(q);
		return q.getId();
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public List sortidesOliFacturacioByImportacioEstat(Long idImportacio, Boolean estat) {
		sortidaOliFacturacioDao.setHibernateTemplate(getHibernateTemplate());
		return sortidaOliFacturacioDao.findByImportacioEstat(idImportacio, estat);
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public List sortidesOliFacturacioByImportacio(Long idImportacio) {
		sortidaOliFacturacioDao.setHibernateTemplate(getHibernateTemplate());
		return sortidaOliFacturacioDao.findByImportacio(idImportacio);
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Long sortidaOliFacturacioDarreraImportacio() {
		sortidaOliFacturacioDao.setHibernateTemplate(getHibernateTemplate());
		return sortidaOliFacturacioDao.findLastIdImportacio();
	}
	
	/**
	 * Només serveix per poder especificar els permisos amb xdoclet. 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Long informeCampanyaCrear(InformeCampanya q){
		informeCampanyaDao.setHibernateTemplate(getHibernateTemplate());
		informeCampanyaDao.makePersistent(q);
		return q.getId();
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public InformeCampanya informeCampanyaAmbId(Long id) {
		informeCampanyaDao.setHibernateTemplate(getHibernateTemplate());
		return informeCampanyaDao.getById(id);
	}
	
	/**
	 * Cerca tots els informes de les campanyes
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection informeCampanyaCercaTots() {
		informeCampanyaDao.setHibernateTemplate(getHibernateTemplate());
		return informeCampanyaDao.findAll();
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * <!-- end-xdoclet-definition -->
	 * @generated
	 */
	public void informeCampanyaEsborrar(Long id) {
		informeCampanyaDao.setHibernateTemplate(getHibernateTemplate());
		InformeCampanya informeCampanya = informeCampanyaDao.getById(id);
		if (informeCampanya != null) {
			informeCampanyaDao.setHibernateTemplate(getHibernateTemplate());
			informeCampanyaDao.makeTransient(informeCampanya);
		}
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" 
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition -->
	 */
	public EtiquetesLot etiquetesLotAmbId(Long id) {
		etiquetesLotDao.setHibernateTemplate(getHibernateTemplate());
		return etiquetesLotDao.getById(id);
	}
	
	/**
	 * Cerca totes les botes de una zona <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection findBotesActivesByZona(Long zonaId) {
		botaDao.setHibernateTemplate(getHibernateTemplate());
		return botaDao.findActiusByZona(zonaId);
	}
	
	/**
	 * set the hibernate template.
	 * @param hibernateTemplate the hibernate spring template.
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * <!-- end-xdoclet-definition --> 
	 */
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate){
		this.hibernateTemplate = hibernateTemplate;
	}


	/**
	 * get the hibernate template.
	 * @return the hibernate spring template.
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * <!-- end-xdoclet-definition --> 
	 */
	public HibernateTemplate getHibernateTemplate(){
		return this.hibernateTemplate;
	}

	
	
	//recupera el darrer codiRB
	/**
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" 
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition -->
	 */
	public String findLastCodiRB(){
		establimentDao.setHibernateTemplate(getHibernateTemplate());
		return establimentDao.getLastRB();
	}
	
	//recupera el darrer codiRC
	/**
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" 
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition -->
	 */
	public String findLastCodiRC(){
		establimentDao.setHibernateTemplate(getHibernateTemplate());
		return establimentDao.getLastRC();
	}
	
	//recupera el darrer codiRT
	/**
	 *  <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" 
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition -->
	 */
	public String findLastCodiRT(){
		establimentDao.setHibernateTemplate(getHibernateTemplate());
		return establimentDao.getLastRT();
	}
	
}