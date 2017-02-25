/**
 * 
 */
package es.caib.gestoli.logic.ejb;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import javax.ejb.SessionBean;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.dao.AnaliticaDao;
import es.caib.gestoli.logic.dao.AutocontrolOlivaDao;
import es.caib.gestoli.logic.dao.BotaDao;
import es.caib.gestoli.logic.dao.CampanyaDao;
import es.caib.gestoli.logic.dao.CategoriaOliDao;
import es.caib.gestoli.logic.dao.DescomposicioPartidaOlivaDao;
import es.caib.gestoli.logic.dao.DescomposicioPlantacioDao;
import es.caib.gestoli.logic.dao.DipositDao;
import es.caib.gestoli.logic.dao.ElaboracioDao;
import es.caib.gestoli.logic.dao.ElaboracioOlivaDao;
import es.caib.gestoli.logic.dao.EntradaDipositDao;
import es.caib.gestoli.logic.dao.EntradaLotDao;
import es.caib.gestoli.logic.dao.EstablimentDao;
import es.caib.gestoli.logic.dao.EtiquetesLotDao;
import es.caib.gestoli.logic.dao.LotDao;
import es.caib.gestoli.logic.dao.MunicipiDao;
import es.caib.gestoli.logic.dao.OlivicultorDao;
import es.caib.gestoli.logic.dao.PartidaElaboracioDao;
import es.caib.gestoli.logic.dao.PartidaFonollDao;
import es.caib.gestoli.logic.dao.PartidaOliDao;
import es.caib.gestoli.logic.dao.PartidaOlivaDao;
import es.caib.gestoli.logic.dao.RendimentVarietatDao;
import es.caib.gestoli.logic.dao.SolicitanteDao;
import es.caib.gestoli.logic.dao.SortidaBotaDao;
import es.caib.gestoli.logic.dao.SortidaBotaGranelDao;
import es.caib.gestoli.logic.dao.SortidaDipositDao;
import es.caib.gestoli.logic.dao.SortidaLotDao;
import es.caib.gestoli.logic.dao.SortidaOrujoDao;
import es.caib.gestoli.logic.dao.SortidaPartidaDao;
import es.caib.gestoli.logic.dao.TrasllatDao;
import es.caib.gestoli.logic.dao.TrazaDao;
import es.caib.gestoli.logic.dao.UsuariDao;
import es.caib.gestoli.logic.dao.VarietatOliDao;
import es.caib.gestoli.logic.dao.ZonaDao;
import es.caib.gestoli.logic.model.Analitica;
import es.caib.gestoli.logic.model.AutocontrolOliva;
import es.caib.gestoli.logic.model.Bota;
import es.caib.gestoli.logic.model.CategoriaOli;
import es.caib.gestoli.logic.model.DescomposicioPartidaOliva;
import es.caib.gestoli.logic.model.DescomposicioPlantacio;
import es.caib.gestoli.logic.model.Diposit;
import es.caib.gestoli.logic.model.Elaboracio;
import es.caib.gestoli.logic.model.ElaboracioOliva;
import es.caib.gestoli.logic.model.EntradaDiposit;
import es.caib.gestoli.logic.model.EntradaLot;
import es.caib.gestoli.logic.model.Establiment;
import es.caib.gestoli.logic.model.Etiquetatge;
import es.caib.gestoli.logic.model.EtiquetesLot;
import es.caib.gestoli.logic.model.Lot;
import es.caib.gestoli.logic.model.Municipi;
import es.caib.gestoli.logic.model.Olivicultor;
import es.caib.gestoli.logic.model.Pais;
import es.caib.gestoli.logic.model.PartidaElaboracio;
import es.caib.gestoli.logic.model.PartidaFonoll;
import es.caib.gestoli.logic.model.PartidaOli;
import es.caib.gestoli.logic.model.PartidaOliva;
import es.caib.gestoli.logic.model.Provincia;
import es.caib.gestoli.logic.model.SortidaBota;
import es.caib.gestoli.logic.model.SortidaBotaGranel;
import es.caib.gestoli.logic.model.SortidaDiposit;
import es.caib.gestoli.logic.model.SortidaLot;
import es.caib.gestoli.logic.model.SortidaOrujo;
import es.caib.gestoli.logic.model.SortidaPartida;
import es.caib.gestoli.logic.model.Trasllat;
import es.caib.gestoli.logic.model.Traza;
import es.caib.gestoli.logic.model.VarietatOli;
import es.caib.gestoli.logic.model.VarietatOliva;
import es.caib.gestoli.logic.model.Zona;
import es.caib.gestoli.logic.util.Constants;
import es.caib.gestoli.logic.util.EdicioProcessosAux;
import es.caib.gestoli.logic.util.ElaboracioAux;
import es.caib.gestoli.logic.util.GeneraPdfVolante;
import es.caib.gestoli.logic.util.ServeiCorreu;


/**
 *
 * <!-- begin-user-doc -->
 * A generated session bean
 * <!-- end-user-doc -->
 * *
 * <!-- begin-xdoclet-definition --> 
 * @ejb.bean name="OliProcessosEjb"	
 *           description="An EJB named OliProcessosEjb"
 *           display-name="OliProcessosEjb"
 *           jndi-name="OliProcessosEjb"
 *           local-jndi-name="OliProcessosEjbLocal"
 *           type="Stateless" 
 *           transaction-type="Container"
 * @ejb.transaction type="Required"
 * 
 * <!-- end-xdoclet-definition --> 
 * @generated
 */

public abstract class OliProcessosEjbBean implements SessionBean {

	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(OliProcessosEjbBean.class);
	
	
	private static final int ENTRADA_OLIVA = 1;
	private static final int ELABORACIO_OLI = 2;
	private static final int TRANSVASSAMENT_OLI = 3;
	private static final int PERDUA_OLI = 4;
	private static final int DESQUALIFICAR_OLI = 5;
	private static final int MOURE_DIPOSIT = 6;
	private static final int SORTIDA_OLI = 7;
	private static final int EMBOTELLAR = 8;
	private static final int ETIQUETAR = 9;
	private static final int ANALITIQUES = 10;
	private static final int VENDA_LOT = 11;
	public static final int SORTIDA_ORUJO = 12;
	public static final int DEVOLUCIO = 13;
//	public static final int VENDA_OLIVA_CRUA_GRANEL = 14;
//	public static final int VENDA_OLIVA_CRUA_BOTA_GRANEL = 15;

	private CategoriaOliDao categoriaOliDao;
	private DescomposicioPartidaOlivaDao descomposicioPartidaOlivaDao;
	private DipositDao dipositDao;
	private ElaboracioDao elaboracioDao;
	private ElaboracioOlivaDao elaboracioOlivaDao;
	private EntradaDipositDao entradaDipositDao;
	private EntradaLotDao entradaLotDao;
	private PartidaOlivaDao partidaOlivaDao;
	private PartidaFonollDao partidaFonollDao;
	private SortidaDipositDao sortidaDipositDao;
	private SortidaBotaDao sortidaBotaDao;
	private SortidaLotDao sortidaLotDao;
	private TrazaDao trazaDao;
	private VarietatOliDao varietatOliDao;
	private AnaliticaDao analiticaDao;
	private AutocontrolOlivaDao autocontrolDao;
	private EstablimentDao establimentDao;
	private TrasllatDao trasllatDao;
	private LotDao lotDao;
	private UsuariDao usuariDao;
	private ZonaDao zonaDao;
	private SolicitanteDao solicitanteDao;
	private PartidaOliDao partidaOliDao;
	private DescomposicioPlantacioDao descomposicioPlantacioDao;
	private RendimentVarietatDao rendimentVarietatDao;
	private OlivicultorDao olivicultorDao;
	private EtiquetesLotDao etiquetesLotDao;
	private SortidaOrujoDao sortidaOrujoDao;
	private BotaDao botaDao;
	private MunicipiDao municipiDao;
	private CampanyaDao campanyaDao;
	private AutocontrolOlivaDao autocontrolOlivaDao;
	private PartidaElaboracioDao partidaElaboracioDao;
	private SortidaPartidaDao sortidaPartidaDao;
	private SortidaBotaGranelDao sortidaBotaGranelDao;
	
	private HibernateTemplate hibernateTemplate;
	private MessageSourceAccessor messageSourceAccessor;
	private Integer tipusEstablimentTafona;
	private Integer tipusEstablimentEnvasadora;
	private Integer tipusEstablimentTafonaEnvasadora;
	
	/** 
	 * Crea una instància de tots els DAOs necessàris per
	 * als mètodes de negoci.
	 * 
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.create-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition --> 
	 * @generated
	 */
	public void ejbCreate() {
		
		categoriaOliDao = new CategoriaOliDao();
		descomposicioPartidaOlivaDao = new DescomposicioPartidaOlivaDao();
		dipositDao = new DipositDao();
		elaboracioDao = new ElaboracioDao();
		elaboracioOlivaDao = new ElaboracioOlivaDao();
		botaDao = new BotaDao();
		entradaDipositDao = new EntradaDipositDao();
		entradaLotDao = new EntradaLotDao();
		partidaOlivaDao = new PartidaOlivaDao();
		partidaElaboracioDao = new PartidaElaboracioDao();
		partidaFonollDao = new PartidaFonollDao();
		sortidaDipositDao = new SortidaDipositDao();
		sortidaBotaDao = new SortidaBotaDao();
		sortidaLotDao = new SortidaLotDao();
		trazaDao = new TrazaDao();
		varietatOliDao = new VarietatOliDao();
		analiticaDao = new AnaliticaDao();
		autocontrolDao = new AutocontrolOlivaDao();
		establimentDao = new EstablimentDao();
		trasllatDao = new TrasllatDao();
		lotDao = new LotDao();
		usuariDao = new UsuariDao();
		zonaDao = new ZonaDao();
		solicitanteDao = new SolicitanteDao();
		partidaOliDao = new PartidaOliDao();
		descomposicioPlantacioDao = new DescomposicioPlantacioDao();
		rendimentVarietatDao = new RendimentVarietatDao();
		olivicultorDao = new OlivicultorDao();
		etiquetesLotDao = new EtiquetesLotDao();
		sortidaOrujoDao = new SortidaOrujoDao();
		municipiDao = new MunicipiDao();
		campanyaDao = new CampanyaDao();
		autocontrolOlivaDao = new AutocontrolOlivaDao();
		sortidaPartidaDao = new SortidaPartidaDao();
		sortidaBotaGranelDao = new SortidaBotaGranelDao();
	}

	/**
	 * NomÃƒÂ©s serveix per poder especificar els permisos amb
	 * xdoclet.
	 * 
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR" 
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition --> 
	 * @generated
	 */
	public void ejbRemove() {}


	

	/** 
	 * Executa un procÃƒÂ©s d'entrada de oliva al sistema
	 *
	 * @param dataExecucio 
	 * @param hora
	 * @param codi 
	 * @param fincaId
	 * @param plantacioId
	 * @param descomposicioPartidaOliva
	 * @param zonaId
	 * @param olivicultorId
	 * @param estatOliva
	 * @param mezcla
	 * @param quantitat
	 * 
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition --> 
	 * @generated
	 */
	public Long entradaOliva(
			Long establimentId,
			Date dataExecucio,
			String hora,
			String codi,
			Long fincaId,
			Long plantacioId,
			DescomposicioPartidaOliva[] descomposicioPartidaOliva,
			Long zonaId,
			Long olivicultorId,
			Boolean sana,
			Integer estat,
			Boolean mezcla,
			Float quantitat,
			String tipusTraza,
			Boolean esEcologic) {

		
		DecimalFormat df = new DecimalFormat("#.00"); 
		// Cream la partida d'oliva.
		PartidaOliva partidaOliva = new PartidaOliva();
		partidaOliva.setData(dataExecucio);
		partidaOliva.setHora(hora);
		
		// Obtenim el nombre de l'entrada.
		partidaOlivaDao.setHibernateTemplate(getHibernateTemplate());
		Integer numeroEntrada =  partidaOlivaDao.getNumeroEntrada(dataExecucio, establimentId, null);
		partidaOliva.setNumeroEntrada(numeroEntrada);
		
		// Obtenim l'olivicultor.
		olivicultorDao.setHibernateTemplate(getHibernateTemplate());
		Olivicultor olivicultor = olivicultorDao.getById(olivicultorId);
		partidaOliva.setOlivicultor(olivicultor);
		
		partidaOliva.setPosicioX(Integer.valueOf("0"));
		partidaOliva.setPosicioY(Integer.valueOf("0"));
		partidaOliva.setSana(sana);
		partidaOliva.setEstat(estat);
		partidaOliva.setQuantitat(quantitat.doubleValue());
		
		// Obtenim la zona.
		zonaDao.setHibernateTemplate(getHibernateTemplate());
		Zona zona = zonaDao.getById(zonaId);
		partidaOliva.setZona(zona);

		// Insertam la traça.
		Traza traza = new Traza();
		traza.setTipus(Integer.valueOf(tipusTraza));
		trazaDao.setHibernateTemplate(getHibernateTemplate());
		trazaDao.makePersistent(traza);
		partidaOliva.setTraza(traza);
		
		partidaOliva.setEsEcologic(esEcologic);

		// Insertam la partida d'oliva.
		partidaOlivaDao.setHibernateTemplate(getHibernateTemplate());
		partidaOlivaDao.makePersistent(partidaOliva);

		// Insertam les descomposicions de les partides.
		for (int i = 0; i < descomposicioPartidaOliva.length; i++) {
			DescomposicioPartidaOliva descomposicioPartidaOlivaItem = descomposicioPartidaOliva[i];
			if (descomposicioPartidaOlivaItem != null) {
				descomposicioPartidaOlivaItem.setPartidaOliva(partidaOliva);
				if (descomposicioPartidaOlivaItem.getKilos().doubleValue() > 0) {
					// Actualitzam la quantitat de producció restant.
					DescomposicioPlantacio dp = descomposicioPartidaOlivaItem.getDescomposicioPlantacio();
					if (dp != null) {
						dp.setProduccioRestant(dp.getProduccioRestant() - descomposicioPartidaOlivaItem.getKilos());
						descomposicioPlantacioDao.setHibernateTemplate(getHibernateTemplate());
						descomposicioPlantacioDao.makePersistent(dp);
						
						if (olivicultor.getCodiDOPOliva()!=null && descomposicioPartidaOlivaItem.getDescomposicioPlantacio().getVarietatOliva().getId()==Constants.VARIETAT_OLIVA_MALLORQUINA) {
							descomposicioPlantacioDao.setHibernateTemplate(getHibernateTemplate());
							 DescomposicioPlantacio dp2 = null;
							List llista = descomposicioPlantacioDao.getByIdPlantacioIdVariedad(dp.getPlantacio().getId(), Constants.VARIETAT_OLIVA_MALLORQUINA_TAULA);
							if (llista.size()>0) dp2 = (DescomposicioPlantacio) llista.get(0);
							if (dp2!=null) {
								rendimentVarietatDao.setHibernateTemplate(getHibernateTemplate());
								Double rvTaula = rendimentVarietatDao.rendimentCampanyaVarietat(campanyaCercaActual(), Constants.VARIETAT_OLIVA_MALLORQUINA_TAULA);
								Double rvMall = rendimentVarietatDao.rendimentCampanyaVarietat(campanyaCercaActual(), Constants.VARIETAT_OLIVA_MALLORQUINA);
								dp2.setProduccioRestant(dp2.getProduccioRestant() - (descomposicioPartidaOlivaItem.getKilos().doubleValue()*rvTaula/rvMall));
								descomposicioPlantacioDao.setHibernateTemplate(getHibernateTemplate());
								descomposicioPlantacioDao.makePersistent(dp2);
							}
						}
					}

					descomposicioPartidaOlivaDao.setHibernateTemplate(getHibernateTemplate());
					descomposicioPartidaOlivaDao.makePersistent(descomposicioPartidaOlivaItem);
				}
			}
		}
		
		return partidaOliva.getId();
	}
	
	/** 
	 * Executa un procÃƒÂ©s d'entrada de oliva al sistema
	 *
	 * @param dataExecucio 
	 * @param hora
	 * @param codi 
	 * @param fincaId
	 * @param plantacioId
	 * @param descomposicioPartidaOliva
	 * @param zonaId
	 * @param olivicultorId
	 * @param envasPetit
	 * @param envasRigid
	 * @param envasVentilat
	 * @param tipusOlivaTaula
	 * @param quantitat
	 * 
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition --> 
	 * @generated
	 */
	public Long entradaOliva(
			Long establimentId,
			Date dataExecucio,
			String hora,
			String codi,
			Long fincaId,
			Long plantacioId,
			DescomposicioPartidaOliva[] descomposicioPartidaOliva,
			Long zonaId,
			Long olivicultorId,
			Boolean envasPetit,
			Boolean envasRigid,
			Boolean envasVentilat,
			Integer tipusOlivaTaula,
			Float quantitat,
			String tipusTraza,
			Boolean esEcologic) {

		DecimalFormat df = new DecimalFormat("#.00"); 
		// Cream la partida d'oliva.
		PartidaOliva partidaOliva = new PartidaOliva();
		partidaOliva.setData(dataExecucio);
		partidaOliva.setHora(hora);
		BigDecimal quant = new BigDecimal(String.valueOf(quantitat)).setScale(2, BigDecimal.ROUND_HALF_UP);
		partidaOliva.setQuantitat(quant.doubleValue());
		
		// Obtenim el nombre de l'entrada.
		partidaOlivaDao.setHibernateTemplate(getHibernateTemplate());
		Integer numeroEntrada =  partidaOlivaDao.getNumeroEntrada(dataExecucio, establimentId, null);
		partidaOliva.setNumeroEntrada(numeroEntrada);
		
		// Obtenim l'olivicultor.
		olivicultorDao.setHibernateTemplate(getHibernateTemplate());
		Olivicultor olivicultor = olivicultorDao.getById(olivicultorId);
		partidaOliva.setOlivicultor(olivicultor);
		
		partidaOliva.setPosicioX(Integer.valueOf("0"));
		partidaOliva.setPosicioY(Integer.valueOf("0"));
		partidaOliva.setSana(true);
		partidaOliva.setEstat(0);
		partidaOliva.setTipusOlivaTaula(tipusOlivaTaula);
		partidaOliva.setEnvasPetit(envasPetit);
		partidaOliva.setEnvasRigid(envasRigid);
		partidaOliva.setEnvasVentilat(envasVentilat);
		partidaOliva.setOlivaTaula(true);
		
		// Obtenim la zona.
		zonaDao.setHibernateTemplate(getHibernateTemplate());
		Zona zona = zonaDao.getById(zonaId);
		partidaOliva.setZona(zona);

		// Insertam la traça.
		Traza traza = new Traza();
		traza.setTipus(Integer.valueOf(tipusTraza));
		trazaDao.setHibernateTemplate(getHibernateTemplate());
		trazaDao.makePersistent(traza);
		partidaOliva.setTraza(traza);
		partidaOliva.setEsEcologic(esEcologic);

		// Insertam la partida d'oliva.
		partidaOlivaDao.setHibernateTemplate(getHibernateTemplate());
		partidaOlivaDao.makePersistent(partidaOliva);

		// Insertam les descomposicions de les partides.
		for (int i = 0; i < descomposicioPartidaOliva.length; i++) {
			DescomposicioPartidaOliva descomposicioPartidaOlivaItem = descomposicioPartidaOliva[i];
			if (descomposicioPartidaOlivaItem != null) {
				descomposicioPartidaOlivaItem.setPartidaOliva(partidaOliva);
				if (descomposicioPartidaOlivaItem.getKilos().doubleValue() > 0) {
					// Actualitzam la quantitat de producció restant.
					DescomposicioPlantacio dp = descomposicioPartidaOlivaItem.getDescomposicioPlantacio();
					if (dp != null) {
						dp.setProduccioRestant(dp.getProduccioRestant() - descomposicioPartidaOlivaItem.getKilos());
						descomposicioPlantacioDao.setHibernateTemplate(getHibernateTemplate());
						descomposicioPlantacioDao.makePersistent(dp);
						
						if (descomposicioPartidaOlivaItem.getDescomposicioPlantacio().getVarietatOliva().getId()==Constants.VARIETAT_OLIVA_MALLORQUINA_TAULA) {
							descomposicioPlantacioDao.setHibernateTemplate(getHibernateTemplate());
							DescomposicioPlantacio dp2 = (DescomposicioPlantacio) descomposicioPlantacioDao.getByIdPlantacioIdVariedad(dp.getPlantacio().getId(), Constants.VARIETAT_OLIVA_MALLORQUINA).get(0);
							rendimentVarietatDao.setHibernateTemplate(getHibernateTemplate());
							Double rvTaula = rendimentVarietatDao.rendimentCampanyaVarietat(campanyaCercaActual(), Constants.VARIETAT_OLIVA_MALLORQUINA_TAULA);
							Double rvMall = rendimentVarietatDao.rendimentCampanyaVarietat(campanyaCercaActual(), Constants.VARIETAT_OLIVA_MALLORQUINA);
							dp2.setProduccioRestant(dp2.getProduccioRestant() - (descomposicioPartidaOlivaItem.getKilos().doubleValue()*rvMall/rvTaula));
							descomposicioPlantacioDao.setHibernateTemplate(getHibernateTemplate());
							descomposicioPlantacioDao.makePersistent(dp2);
						}
					}

					descomposicioPartidaOlivaDao.setHibernateTemplate(getHibernateTemplate());
					descomposicioPartidaOlivaDao.makePersistent(descomposicioPartidaOlivaItem);
				}
			}
		}
		
		return partidaOliva.getId();
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
	 * Executa un procÃƒÂ©s d'entrada de oliva al sistema
	 *
	 * @param dataExecucio 
	 * @param hora
	 * @param codi 
	 * @param fincaId
	 * @param plantacioId
	 * @param descomposicioPartidaOliva
	 * @param zonaId
	 * @param olivicultorId
	 * @param envasPetit
	 * @param envasRigid
	 * @param envasVentilat
	 * @param tipusOlivaTaula
	 * @param quantitat
	 * 
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition --> 
	 * @generated
	 */
	public Long entradaFonoll(
			Long establimentId,
			Date dataExecucio,
			String hora,
			String codi,
			String titular,
			Double kgInici,
			Long municipiId,
			String poligon,
			String parcela,
			String tipusTraza) {

		// Cream la partida de fonoll.
		PartidaFonoll partidaFonoll = new PartidaFonoll();
		partidaFonoll.setData(dataExecucio);
		partidaFonoll.setHora(hora);
		partidaFonoll.setTitular(titular);
		
		// Obtenim l'establiment.
		establimentDao.setHibernateTemplate(getHibernateTemplate());
		Establiment establiment = establimentDao.getById(establimentId);
		partidaFonoll.setEstabliment(establiment);
		
		// Obtenim el nombre de l'entrada.
		partidaFonollDao.setHibernateTemplate(getHibernateTemplate());
		Integer numeroEntrada =  partidaFonollDao.getNumeroEntrada(dataExecucio, establimentId, null);
		partidaFonoll.setNumeroEntrada(numeroEntrada);
		
		// Obtenim el municipi.
		municipiDao.setHibernateTemplate(getHibernateTemplate());
		Municipi municipi = municipiDao.getById(municipiId);
		partidaFonoll.setMunicipi(municipi);
		
		
		partidaFonoll.setPoligon(poligon);
		partidaFonoll.setParcela(parcela);
		
		partidaFonoll.setCodi(codi);
		partidaFonoll.setKgInici(kgInici);
		partidaFonoll.setKgRestants(kgInici);
		partidaFonoll.setValid(true);
		// Insertam la traça.
		Traza traza = new Traza();
		traza.setTipus(Integer.valueOf(tipusTraza));
		trazaDao.setHibernateTemplate(getHibernateTemplate());
		trazaDao.makePersistent(traza);
		partidaFonoll.setTraza(traza);

		// Insertam la partida d'oliva.
		partidaFonollDao.setHibernateTemplate(getHibernateTemplate());
		partidaFonollDao.makePersistent(partidaFonoll);

		
		return partidaFonoll.getId();
	}

	
	/** 
	 * Executa un procÃƒÂ©s d'elaboraciÃƒÂ³ d'oli al sistema
	 * @param elaboracio 
	 * @param session
	 */
	private Elaboracio elaboracioOli(Elaboracio elaboracio, Session session) {
		elaboracioDao.makePersistent(elaboracio, session);
		return elaboracio;
	}
	
	/** 
	 * Executa un procÃƒÂ©s d'elaboraciÃƒÂ³ d'oliva al sistema
	 * @param elaboracio 
	 * @param session
	 */
	private ElaboracioOliva elaboracioOliva(ElaboracioOliva elaboracio, Session session) {
		elaboracioOlivaDao.makePersistent(elaboracio, session);
		return elaboracio;
	}

	
	/** 
	 * Executa un procÃƒÂ©s d'elaboraciÃƒÂ³ d'oli al sistema
	 *
	 * @param elaboracio 
	 * 
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * <!-- end-xdoclet-definition --> 
	 * @generated
	 */
	public Integer findNumElaboracioByData(Date data, Long establimentId) {
		elaboracioDao.setHibernateTemplate(getHibernateTemplate());
		return elaboracioDao.findNumElaboracioByData(data, establimentId, null);
		
	}
	
	/** 
	 * Executa un procÃƒÂ©s d'elaboraciÃƒÂ³ d'oli al sistema
	 *
	 * @param elaboracio 
	 * 
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition --> 
	 * @generated
	 */
	public Integer findNumElaboracioOlivaByData(Date data, Long establimentId) {
		elaboracioOlivaDao.setHibernateTemplate(getHibernateTemplate());
		Integer num = elaboracioOlivaDao.findNumElaboracioByData(data, establimentId, null);
		if (num!=null) return num;
		else return 1;
		
	}
	
	
	/** 
	 * Executa un procés d'inserció de traça al sistema
	 * @param tipo
	 * @param session 
	 */
	private Traza insertarTraza(int tipo, Session session) {
		Traza traza = new Traza(new Integer(tipo));
		trazaDao.setHibernateTemplate(getHibernateTemplate());
		trazaDao.makePersistent(traza, session);
		return traza;
	}
	
	
	/** 
	 * Executa un procÃƒÂ©s d'entrada d'oli en el diposit al sistema
	 * @param elaboracio 
	 * @param diposit
	 * @param establiment
	 * @param litros
	 * @param observacions
	 * @param session
	 */
	private EntradaDiposit entradaDiposit(Elaboracio elaboracio, Diposit diposit, Establiment establiment, Double litros, boolean observacions, Session session) throws Exception {
		CategoriaOli categoria = null;
		if (elaboracio.getPartidaOli() != null) categoria = elaboracio.getPartidaOli().getCategoriaOli();
		return entradaDiposit(elaboracio, diposit, establiment, litros, null, observacions, session, categoria);
	}
	
	
	private EntradaDiposit entradaDiposit(Elaboracio elaboracio, Diposit diposit, Establiment establiment, Double litros, Olivicultor olivicultor, boolean observacions, Session session, CategoriaOli categoria) throws Exception {
		DecimalFormat numberDecimalFormat = new DecimalFormat("###,###,##0.000");
		EntradaDiposit entrada = null;
		
		double capacitatActual = (diposit.getCapacitat() != null?diposit.getCapacitat().doubleValue():new Double(0).doubleValue()) - (diposit.getVolumActual() != null?diposit.getVolumActual().doubleValue():new Double(0).doubleValue());
		if (capacitatActual >= litros.doubleValue() || diposit.getFictici().booleanValue()) {
			entrada = new EntradaDiposit();
			entrada.setValid(new Boolean(true));
			entrada.setAcidesa(elaboracio.getAcidesa());
			entrada.setCategoriaOli(categoria);
			entrada.setPartidaOli(elaboracio.getPartidaOli());
			entrada.setData(elaboracio.getData());
			entrada.setDiposit(diposit);
			entrada.setElaboracio(elaboracio);
			entrada.setEstabliment(establiment);
			entrada.setLitres(litros);
			entrada.setOlivicultor(olivicultor);
			if (observacions) entrada.setObservacions(elaboracio.getObservacions());
			entrada.setTraza(this.insertarTraza(Constants.CODI_TRAZA_TIPUS_ENTRADA_DIPOSIT, session));
			entradaDipositDao.setHibernateTemplate(getHibernateTemplate());
			entradaDipositDao.makePersistent(entrada, session);
		}
		return entrada;
	}
	
	
	/**
	 * Executa un procés de sortida d'oli en el diposit al sistema
	 * @param elaboracio
	 * @param diposit
	 * @param establiment
	 * @param litros
	 * @param olivicultor
	 * @param observacions
	 * @param session
	 * @return
	 * @throws Exception
	 */
	private SortidaDiposit sortidaDiposit(Elaboracio elaboracio, Diposit diposit, Establiment establiment, Double litros, Olivicultor olivicultor, boolean observacions, Session session) throws Exception {
		return sortidaDiposit(elaboracio, diposit, establiment, litros, olivicultor, observacions, session, diposit.getPartidaOli().getCategoriaOli());
	}
	
	private SortidaDiposit sortidaDiposit(Elaboracio elaboracio, Diposit diposit, Establiment establiment, Double litros, Olivicultor olivicultor, boolean observacions, Session session, CategoriaOli categoria) throws Exception {
		DecimalFormat numberDecimalFormat = new DecimalFormat("###,###,##0.000");
		SortidaDiposit sortida = null;

		sortida = new SortidaDiposit();
		sortida.setAcidesa(elaboracio.getAcidesa());
		sortida.setPartidaOli(diposit.getPartidaOli());
		sortida.setCategoriaOli(categoria);
		sortida.setData(elaboracio.getData());
//		sortida.setDesti(desti);
//		sortida.setDipositBySdiCoddde(dipositBySdiCoddde);
		sortida.setDipositBySdiCoddor(diposit);
		sortida.setEstabliment(establiment);
		sortida.setLitres(litros);
		if (establiment.getUnitatMesura().equals("k")) sortida.setLitres(new Double(numberDecimalFormat.parse(numberDecimalFormat.format(litros.doubleValue() / 0.916)).doubleValue()));
//		sortida.setLot(lot);
		sortida.setOlivicultor(olivicultor);
		if (observacions) sortida.setObservacions(elaboracio.getObservacions());
		sortida.setTraza(this.insertarTraza(Constants.CODI_TRAZA_TIPUS_SORTIDA_DIPOSIT, session));
		sortidaDipositDao.makePersistent(sortida, session);

		return sortida;
	}


	/**
	 * Busca todas las partidas de oliva y la su detalle de los olivicultores dados de alta en la DO de la campanya
	 * actual
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition -->
	 */
	public HashMap findkgsAcumulatsDescomposicioPartidaOlivaPerOlivicultor(Long idOlivicultor) {
		
		descomposicioPartidaOlivaDao.setHibernateTemplate(getHibernateTemplate());
		Iterator itPartida =  descomposicioPartidaOlivaDao.findDescomposicioPartidaOlivaPerOlivicultor(idOlivicultor).iterator();
		
		HashMap kgsAcumulats = new HashMap();
		
		while (itPartida.hasNext()) {
			DescomposicioPartidaOliva descPlantOliv = (DescomposicioPartidaOliva) itPartida.next();
			if(descPlantOliv.getPartidaOliva().getValid()!= null && descPlantOliv.getPartidaOliva().getValid().booleanValue()){
				//	Sumamos kilos totales
				Long descompPlantacioId = descPlantOliv.getDescomposicioPlantacio().getId();
				if (kgsAcumulats.containsKey(descompPlantacioId)){
					double acumulat = descPlantOliv.getKilos().doubleValue() + ((Double) kgsAcumulats.get(descompPlantacioId)).doubleValue();
					kgsAcumulats.put(descompPlantacioId, new Double(acumulat));
				} else {
					kgsAcumulats.put(descompPlantacioId, descPlantOliv.getKilos());
				}
			}
						
			
		}
		return kgsAcumulats;
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
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public PartidaOli getPartidaOliById(Long id) {
		partidaOliDao.setHibernateTemplate(getHibernateTemplate());
		return partidaOliDao.getById(id);

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
	 * partidaModificar
	 * @param partida
	 * @param session
	 */
	private void partidaModificar(PartidaOliva partida, Session session) {
		partidaOlivaDao.makePersistent(partida, session);
	}


	/* *
	 * dipositAmbId
	 * @param id
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
//	public Diposit dipositAmbId(Long id) {
//		dipositDao.setHibernateTemplate(getHibernateTemplate());
//		return dipositDao.getById(id);
//	}

	
	/**
	 * dipositModificar
	 * @param diposit
	 * @param session
	 */
	private void dipositModificar(Diposit diposit, Session session) {
		dipositDao.setHibernateTemplate(getHibernateTemplate());
		dipositDao.makePersistent(diposit, session);
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
	public void dipositModificar(Diposit diposit) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		dipositDao.setHibernateTemplate(getHibernateTemplate());
		dipositDao.makePersistent(diposit, session);
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
	public Elaboracio findElaboracioOliById(Long idElaboracio) {
		elaboracioDao.setHibernateTemplate(getHibernateTemplate());
		return elaboracioDao.getById(idElaboracio, null);
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
	public ElaboracioOliva findElaboracioOlivaById(Long idElaboracioOliva) {
		elaboracioOlivaDao.setHibernateTemplate(getHibernateTemplate());
		return elaboracioOlivaDao.getById(idElaboracioOliva, null);
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
	public Collection findOliRetiratPropietarioByElaboracio(Long idElaboracio) {
		entradaDipositDao.setHibernateTemplate(getHibernateTemplate());
		return entradaDipositDao.findOliRetiratPropietarioByElaboracio(idElaboracio, new Boolean(true));
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
	public Iterator findEntradaDiposiOliByElaboracio(Long idElaboracio, Boolean valid) {
		entradaDipositDao.setHibernateTemplate(getHibernateTemplate());
		return entradaDipositDao.findEntradaDiposiOliByElaboracio(idElaboracio, valid).iterator();
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
	public int findEntradaDiposiOliByElaboracioSize(Long idElaboracio) {
		entradaDipositDao.setHibernateTemplate(getHibernateTemplate());
		return entradaDipositDao.findEntradaDiposiOliByElaboracio(idElaboracio, new Boolean(true)).size();
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
	public CategoriaOli findCategoriaOliById(Integer idCategotia) {
		categoriaOliDao.setHibernateTemplate(getHibernateTemplate());
		return categoriaOliDao.getById(idCategotia);
	}
	
	
	
	/**
	 * Añade todos los registros necesarios para la elaboración del aceite
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Long generarElaboracioOli(ElaboracioAux aux, Long idEstabliment, String desqualificat, String pendentQualificar) throws Exception{
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Transaction tx = null;
		Elaboracio elaboracio = null;
		Establiment establiment = null;
		try {	

			establimentDao.setHibernateTemplate(getHibernateTemplate());
			establiment = establimentDao.getById(idEstabliment);
			
			categoriaOliDao.setHibernateTemplate(getHibernateTemplate());
			CategoriaOli catNoDO = categoriaOliDao.getById(Constants.CATEGORIA_NO_DO);
			CategoriaOli catOliDesqualificada = categoriaOliDao.getById(new Integer(desqualificat));
			CategoriaOli catOliPendentQualificar = categoriaOliDao.getById(new Integer(pendentQualificar));

			tx = session.beginTransaction();

			PartidaOli partidaOli = null;

			// Si tot l'oli va a l'olivicultor llavors no s'haurà assignat partida i en crearem una de nova
			if (aux.getIdPartidaOli() == null){
				partidaOliDao.setHibernateTemplate(getHibernateTemplate());
				partidaOli = partidaOliDao.getByNomAndEstabliment("Olivicultor - No DO", establiment.getId());
				
				if (partidaOli == null) {
					//Nova Partida d'oli
					partidaOli = new PartidaOli();
					partidaOli.setDataCreacio(aux.getData());
					partidaOli.setEsActiu(true);
					partidaOli.setEstabliment(establiment);
					partidaOli.setNom("Olivicultor - No DO");
					partidaOli.setOlivicultorEsPropietari(true);
					partidaOli.setCategoriaOli(catNoDO);
					partidaOli.setCategoriaOliOriginal(catNoDO);
					partidaOli.setEsVisualitza(new Boolean(false));
					partidaOliDao.makePersistent(partidaOli);
				}
				
			} else {
				partidaOli = this.getPartidaOliById(aux.getIdPartidaOli());
			}
			
			// Insertamos elaboracion
			elaboracio = new Elaboracio();
			elaboracio.setAcidesa(aux.getAcidesa());
			elaboracio.setData(aux.getData());
			elaboracio.setLitres(aux.getLitres());
			elaboracio.setNumeroElaboracio(this.findNumElaboracioByData(aux.getData(), establiment.getId()));
			elaboracio.setObservacions(aux.getObservacions());
			elaboracio.setResponsable(aux.getResponsable());
			elaboracio.setTalcLot(aux.getTalcLot());
			elaboracio.setTalcMarcaComercial(aux.getTalcMarcaComercial());
			elaboracio.setTalcQuantitat(aux.getTalcQuantitat());
			elaboracio.setTemperatura(aux.getTemperatura());
			elaboracio.setValid(new Boolean(true));
			Traza traza = this.insertarTraza(Constants.CODI_TRAZA_TIPUS_ELABORACIO, session);
			elaboracio.setTraza(traza);
			elaboracio.setCategoriaOli(categoriaOliDao.getById(aux.getIdCategoriaOli() == null ? Constants.CATEGORIA_NO_DO : aux.getIdCategoriaOli()));
			elaboracio.setPartidaOli(partidaOli);

			// Si s'ha seleccionat la cetegoria de NoDO, passarem la partida a NoDO.
			if (elaboracio.getCategoriaOli().getId().equals(Constants.CATEGORIA_NO_DO)){
				partidaOli.setCategoriaOli(elaboracio.getCategoriaOli());
				partidaOliDao.makePersistent(partidaOli);
			}
			//Configuram numero i num impresions
			elaboracio.setNumPrintsDocRendiment(0);
			elaboracio.setAutoNumDocRendiment(elaboracioDao.seguentNumeroByEstabliment(establiment.getId(), true));
			
			this.elaboracioOli(elaboracio, session);

			// Insertamos con que partidas hemos hecho la elaboración
			PartidaOliva partida = null;
			boolean olivaQualificada = true;
			
			for (int i = 0; i < aux.getPartides().length; i++) {
				partida = this.partidaAmbId(aux.getPartides()[i].getId());
				partida.getTraza().getTrazasForTtrCodtrafill().add(traza);
				if (!partida.getQualificada()) {
					olivaQualificada = false;
					elaboracio.setObservacions("Partida desqualificada. " + elaboracio.getObservacions());
				}
				partida.setElaboracio(elaboracio);
				this.partidaModificar(partida, session);

			}
			
			// Generamos las entradas y salidas de depósito
			if (aux.getDiposits().length > 0) { // S'ha ficat oli dins els diposits
				for (int i = 0; i < aux.getDiposits().length; i++) {
					Double litros = aux.getLitros()[i];	
					if (litros > 0){
						dipositDao.setHibernateTemplate(getHibernateTemplate());
						Diposit diposit = dipositDao.getById(aux.getDiposits()[i].getId());
						
						//Si el deposito no esta vacio incorporamos su ultima entrada como padre de la entrada consecuencia de la elaboracion
						entradaDipositDao.setHibernateTemplate(getHibernateTemplate());
						EntradaDiposit ultimaEntrada = entradaDipositDao.findUltimaByDeposito(diposit.getId(), new Boolean(true));	
						
						EntradaDiposit entrada = this.entradaDiposit(elaboracio, diposit, establiment, litros, true, session);
						elaboracio.getTraza().getTrazasForTtrCodtrafill().add(entrada.getTraza());
						if(diposit.getVolumActual()!= null && diposit.getVolumActual().doubleValue() > 0){
							ultimaEntrada.getTraza().getTrazasForTtrCodtrafill().add(entrada.getTraza());
							entradaDipositDao.makePersistent(ultimaEntrada);
						}
						
						if (diposit.getPartidaOli() != null){
							entrada.setCategoriaOriginalPartida(diposit.getPartidaOli().getCategoriaOli());
							entradaDipositDao.makePersistent(entrada);
						}
						
						diposit.setPartidaOli(elaboracio.getPartidaOli());
						//Comprovar a la validació que la partida a la que s'assigna l'entrada sigui desqualificada, o pendent, segons correspongui...
						if (!olivaQualificada || (diposit.getPartidaOli()!= null && diposit.getPartidaOli().getCategoriaOli() != null && diposit.getPartidaOli().getCategoriaOli().getId().equals(new Integer(desqualificat)))) {
							diposit.getPartidaOli().setCategoriaOli(catOliDesqualificada);
						} else {
							diposit.getPartidaOli().setCategoriaOli(catOliPendentQualificar);
						}
						
						diposit.setVolumActual(new Double((diposit.getVolumActual() != null ? diposit.getVolumActual().doubleValue() : new Double(0).doubleValue()) + litros.doubleValue()));
						diposit.setAcidesa(elaboracio.getAcidesa());
						this.dipositModificar(diposit, session);
					}
				}
			}
			// Si l'olivicultor retira oli...
			if (aux.getLitrosRetirats() != null && aux.getLitrosRetirats().length > 0) { // L'olivicultor es queda oli
				Iterator itDip = establiment.getDiposits().iterator();
				Diposit dipFictici = null;
				while (itDip.hasNext()) {
					Diposit d = (Diposit)itDip.next(); 
					if (d.getFictici().booleanValue()){
						dipFictici = d;
						break;
					}
				}

				if (dipFictici != null && dipFictici.getFictici().booleanValue()) {
					//Cream una entrada i sortida per a cada retirada d'oli del propietari
					
					for(int i=0; i<aux.getIdOlivicultors().length; i++){
						if(aux.getLitrosRetirats()[i] != null && aux.getLitrosRetirats()[i] > 0){
							
							olivicultorDao.setHibernateTemplate(getHibernateTemplate());
							Olivicultor olivicultor  = olivicultorDao.getById(aux.getIdOlivicultors()[i]);
							
							// Utilitzam una partida per a cada olivicultor, amb el seu nom
							partidaOliDao.setHibernateTemplate(getHibernateTemplate());
							PartidaOli partidaOliNoDo = partidaOliDao.getByNomAndEstabliment(aux.getDataFormatCurt() + " " + olivicultor.getNom(), establiment.getId());
							
							if (partidaOliNoDo == null) {
								//Nova Partida d'oli
								partidaOliNoDo = new PartidaOli();
								partidaOliNoDo.setDataCreacio(aux.getData());
								partidaOliNoDo.setEsActiu(true);
								partidaOliNoDo.setEstabliment(establiment);
								partidaOliNoDo.setNom(aux.getDataFormatCurt() + " " + olivicultor.getNom());
								partidaOliNoDo.setOlivicultorEsPropietari(true);
								partidaOliNoDo.setCategoriaOli(catNoDO);
								partidaOliNoDo.setCategoriaOliOriginal(catNoDO);
								partidaOliNoDo.setEsVisualitza(new Boolean(false));
								partidaOliDao.makePersistent(partidaOliNoDo);
							}
							
							dipFictici.setPartidaOli(partidaOliNoDo);
							
							// Crear entrada al dipòsit de la quantitat retirada per l'olivicultor
							EntradaDiposit entrada = this.entradaDiposit(elaboracio, dipFictici, establiment, aux.getLitrosRetirats()[i], olivicultor, true, session, catNoDO);
							elaboracio.getTraza().getTrazasForTtrCodtrafill().add(entrada.getTraza());

							// Crear una sortida al dipòsit de la quantitat retirada per l'olivicultor
							SortidaDiposit sortida = this.sortidaDiposit(elaboracio, dipFictici, establiment, aux.getLitrosRetirats()[i], olivicultor, true, session, catNoDO);
							entrada.getTraza().getTrazasForTtrCodtrafill().add(sortida.getTraza());
						}
					}
					
					
				} else {
					throw new Exception();
				}
			}

			tx.commit();
			
		} catch (Exception ex) {
			try {
				tx.rollback();
				throw new Exception(ex);
			} catch (HibernateException e) {
			}
		}
		
		return elaboracio.getId();
	}
	
	/**
	 * Añade todos los registros necesarios para la elaboración del aceite
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Long generarElaboracioOliva(ElaboracioOliva eo, Bota bot, Long idEstabliment) throws Exception{
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Transaction tx = null;
		Establiment establiment = null;
		try {	

			establimentDao.setHibernateTemplate(getHibernateTemplate());
			establiment = establimentDao.getById(idEstabliment);

			tx = session.beginTransaction();
			
			// Insertamos elaboracion
			ElaboracioOliva elaboracio;
			elaboracio = new ElaboracioOliva();
			elaboracio.setData(eo.getData());
			elaboracio.setNumeroElaboracio(this.findNumElaboracioOlivaByData(eo.getData(), establiment.getId()));
			elaboracio.setEstabliment(establiment);
			elaboracio.setObservacions(eo.getObservacions());
//			elaboracio.setPartidaOlivas(eo.getPartidaOlivas());
			elaboracio.setPartidaElaboracions(eo.getPartidaElaboracions());
			elaboracio.setValid(new Boolean(true));
			elaboracio.setEsEcologic(eo.getEsEcologic());
			
			Traza trazaElaboracio = this.insertarTraza(Constants.CODI_TRAZA_TIPUS_ELABORACIO_OLIVA_TAULA, session);
			trazaDao.makePersistent(trazaElaboracio);
			elaboracio.setTraza(trazaElaboracio);
			elaboracioOlivaDao.makePersistent(elaboracio);

			partidaOlivaDao.setHibernateTemplate(hibernateTemplate);
			PartidaOliva po = null;
			
//			Double totalKilos = 0.0;
//			for (Iterator it = eo.getPartidaElaboracions().iterator(); it.hasNext();) {
//				PartidaElaboracio partida = (PartidaElaboracio)it.next();
//				totalKilos += partida.getQuantitat();
//			}
			
			partidaElaboracioDao.setHibernateTemplate(getHibernateTemplate());
			for (Iterator it = elaboracio.getPartidaElaboracions().iterator(); it.hasNext();) {
				PartidaElaboracio pel = (PartidaElaboracio)it.next();
				po = partidaOlivaDao.getById(pel.getPartida().getId());
				po.getTraza().getTrazasForTtrCodtrafill().add(trazaElaboracio);
				Set partidaElaboracions = po.getPartidaElaboracions();
				if (partidaElaboracions == null)
					partidaElaboracions = new HashSet(0);
				
				partidaElaboracions.add(pel);
				po.setPartidaElaboracions(partidaElaboracions);
				po.setQuantitat(po.getQuantitat() - pel.getQuantitat());
				partidaOlivaDao.makePersistent(po);
				pel.setPartida(po);
				pel.setElaboracio(elaboracio);
				partidaElaboracioDao.makePersistent(pel);
			}
			
			Traza trazaBota = this.insertarTraza(Constants.CODI_TRAZA_TIPUS_ENTRADA_BOTA, session);
			trazaElaboracio.getTrazasForTtrCodtrafill().add(trazaBota);
			trazaDao.makePersistent(trazaBota);
			bot.setTraza(trazaBota);
			
			
			//Insertam bota
			bot.setElaboracio(elaboracio);
			botaDao.setHibernateTemplate(getHibernateTemplate());
			botaDao.makePersistent(bot, session);
			
			//Actualitzam partida fonoll si s'ha utilitzat
			PartidaFonoll pf = bot.getPartidaFonoll();
			if (pf!=null) {
				Double kgRestants = pf.getKgRestants() - (bot.getgFonoll()/1000);
				pf.setKgRestants(kgRestants);
				if (kgRestants<=0) pf.setValid(false);
				pf.getTraza().getTrazasForTtrCodtrafill().add(trazaElaboracio);
				partidaFonollDao.setHibernateTemplate(getHibernateTemplate());
				partidaFonollDao.makePersistent(pf);
			}
			tx.commit();
			
		} catch (Exception ex) {
			try {
				tx.rollback();
				throw new Exception(ex);
			} catch (HibernateException e) {
			}
		}
		
		return eo.getId();
	}
	
	
	/**
	 * Añade todos los registros necesarios para el trasvase del aceite
	 * @param data
	 * @param dipositsOrigen
	 * @param dipositDesti
	 * @param litresExtrets
	 * @param litrosAfegits
	 * @param acidesaDesti
	 * @param establiment
	 * @param desqualificat
	 * @param pendentQualificar
	 * @throws Exception
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public void generarTrasbals(Date data, Diposit[] dipositsOrigen, Diposit dipositDesti, Double[] litresExtrets, Double litrosAfegits, Double acidesaDesti, 
			Establiment establiment, String desqualificat, String pendentQualificar, String mescla, Long partidaId) throws Exception{
		
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Transaction tx = null;

		try {	

			Boolean partidaDesqualificada = false;
			Boolean conservarPartida = true;
			Boolean origenDO = false;
			Long idPartidaOrigen = 0L;
			
			tx = session.beginTransaction();
			dipositDao.setHibernateTemplate(getHibernateTemplate());
			dipositDesti = dipositDao.getById(dipositDesti.getId());

			CategoriaOli catOli = null;
			CategoriaOli catOliDesqualificada = null;
			CategoriaOli catOliPendentQualificar = null;
			
			categoriaOliDao.setHibernateTemplate(getHibernateTemplate());
			Iterator itCategoria = categoriaOliDao.findAll().iterator();
			while (itCategoria.hasNext()) {
				catOli = (CategoriaOli) itCategoria.next();
				if (catOli.getId().equals(new Integer(desqualificat))) catOliDesqualificada = catOli;
				if (catOli.getId().equals(new Integer(pendentQualificar))) catOliPendentQualificar = catOli;
			}
			partidaOliDao.setHibernateTemplate(getHibernateTemplate());
			PartidaOli partidaOli = partidaOliDao.getById(partidaId);
			ArrayList trazasPadre = new ArrayList();
			// Generamos las salidas de los depositos
			if (dipositsOrigen.length > 0) { 
				for (int i = 0; i < dipositsOrigen.length; i++) {
					dipositDao.setHibernateTemplate(getHibernateTemplate());
					Diposit diposit = dipositDao.getById(dipositsOrigen[i].getId());
					
					// --Dipòsit d'origen-------
					if (i == 0) {
						origenDO = diposit.getPartidaOli().getEsDO();
						idPartidaOrigen = diposit.getPartidaOli().getId();
						
						//Si el contenidor es ple ha de comprovar la partida
						if(dipositDesti.getVolumActual() != null && dipositDesti.getVolumActual() > 0 && dipositDesti.getPartidaOli() != null){
							if(!dipositDesti.getPartidaOli().getId().equals(idPartidaOrigen)){
								conservarPartida = false;
							}
							if (dipositDesti.getPartidaOli().getCategoriaOli().getId() == Constants.CATEGORIA_NO_DO) partidaDesqualificada = true;
						}
					}
					//---------------------------
					
					Double litros = litresExtrets[i];
					entradaDipositDao.setHibernateTemplate(getHibernateTemplate());
					EntradaDiposit ultimaEntrada = entradaDipositDao.findUltimaByDeposito(diposit.getId(), new Boolean(true));
						
					SortidaDiposit sortida = null;
					sortida = new SortidaDiposit();
					sortida.setAcidesa(ultimaEntrada.getAcidesa());
					sortida.setPartidaOli(diposit.getPartidaOli());
					sortida.setCategoriaOli(diposit.getPartidaOli().getCategoriaOli());
					sortida.setData(data);
					sortida.setDesti(dipositDesti.getCodiAssignat());
					if (dipositDesti.getVolumActual() != null && dipositDesti.getVolumActual().doubleValue() != 0){
						sortida.setObservacions(mescla);
					}
					sortida.setDipositBySdiCoddde(dipositDesti);
					sortida.setDipositBySdiCoddor(ultimaEntrada.getDiposit());
					sortida.setEstabliment(ultimaEntrada.getEstabliment());
					sortida.setLitres(litros);
					sortida.setTraza(this.insertarTraza(Constants.CODI_TRAZA_TIPUS_SORTIDA_DIPOSIT, session));
					
					//18-12-2009 La traza padre de esta salida puede ser tambien una salida (Perdida)no solo una entrada
					// sortida.getTraza().getTrazasForTtrCodtrapare().add(ultimaEntrada.getTraza());
					sortidaDipositDao.setHibernateTemplate(getHibernateTemplate());
					SortidaDiposit ultimaSortida = sortidaDipositDao.findUltimaByDeposito(diposit.getId(), new Boolean(true));
					if( ultimaSortida!= null && ultimaSortida.getTraza().getId().longValue() > ultimaEntrada.getTraza().getId().longValue()){
						sortida.getTraza().getTrazasForTtrCodtrapare().add(ultimaSortida.getTraza());
					}else{
						sortida.getTraza().getTrazasForTtrCodtrapare().add(ultimaEntrada.getTraza());
					}
					
					
					trazasPadre.add(sortida.getTraza());
					sortidaDipositDao.makePersistent(sortida, session);

					diposit.setVolumActual(new Double((diposit.getVolumActual() != null?diposit.getVolumActual().doubleValue():new Double(0).doubleValue()) - litros.doubleValue()));
					
					//Partida
					if (diposit.getPartidaOli() != null) {
						if (diposit.getPartidaOli().getCategoriaOli().getId() == Constants.CATEGORIA_NO_DO) partidaDesqualificada = true;
						if(!idPartidaOrigen.equals(diposit.getPartidaOli().getId())){
							conservarPartida = false;
						}
					}
					if (diposit.getVolumActual() == null || diposit.getVolumActual().doubleValue() == 0) {
						diposit.setAcidesa(new Double(0));
						diposit.setPartidaOli(null);
					}
					this.dipositModificar(diposit, session);
				}
			}

			if (dipositDesti.getVolumActual() != null && dipositDesti.getVolumActual().doubleValue() > 0) {
				entradaDipositDao.setHibernateTemplate(getHibernateTemplate());
				EntradaDiposit ultimaEntrada = entradaDipositDao.findUltimaByDeposito(dipositDesti.getId(), new Boolean(true));
				SortidaDiposit ultimaSortida = sortidaDipositDao.findUltimaByDeposito(dipositDesti.getId(), new Boolean(true));
				if( ultimaSortida!= null && ultimaSortida.getTraza().getId().longValue() > ultimaEntrada.getTraza().getId().longValue()){
					trazasPadre.add(ultimaSortida.getTraza());
				}else{
					trazasPadre.add(ultimaEntrada.getTraza());
				}
			}	
				
//			// Generamos la entrada del deposito con la suma de todos
			Double litrosTotals = new Double(litrosAfegits.doubleValue() + (dipositDesti.getVolumActual()!=null?dipositDesti.getVolumActual().doubleValue():new Double(0).doubleValue()));
			EntradaDiposit entrada = null; //this.entradaDiposit(null, dipositDesti, establiment, litrosTotals, false, session);
			entrada = new EntradaDiposit();
			entrada.setAcidesa(acidesaDesti);

			// Partida
			//Si les partides son igual (origen i destí o destí buit) i és oli de DO la partida ha de ser la d'origen
//			if(conservarPartida && origenDO && idPartidaOrigen.equals(partidaOli.getId())){
//				categoriaOliDao.setHibernateTemplate(getHibernateTemplate());
//				CategoriaOli catDO =  categoriaOliDao.getById(Constants.CATEGORIA_DO);
//				partidaOli.setCategoriaOli(catDO);
//				
//				partidaOliDao.setHibernateTemplate(getHibernateTemplate());
//				partidaOliDao.makePersistent(partidaOli, session);
//			} else {
//				if (partidaDesqualificada) {
//					partidaOli.setCategoriaOli(catOliDesqualificada);
//					partidaOliDao.setHibernateTemplate(getHibernateTemplate());
//					partidaOliDao.makePersistent(partidaOli, session);
//				} else {//if (partidaOli.getCategoriaOli().getId().equals(Constants.CATEGORIA_DO)){ // Si es poden seleccionar partides de DO...
//					partidaOli.setCategoriaOli(catOliPendentQualificar);
//					partidaOliDao.setHibernateTemplate(getHibernateTemplate());
//					partidaOliDao.makePersistent(partidaOli, session);
//				}
//			}
			
			entrada.setCategoriaOriginalPartida(partidaOli.getCategoriaOli());
			
			if (partidaDesqualificada) {
				partidaOli.setCategoriaOli(catOliDesqualificada);
				partidaOliDao.setHibernateTemplate(getHibernateTemplate());
				partidaOliDao.makePersistent(partidaOli, session);
			} else if(!conservarPartida){
				partidaOli.setCategoriaOli(catOliPendentQualificar);
				partidaOliDao.setHibernateTemplate(getHibernateTemplate());
				partidaOliDao.makePersistent(partidaOli, session);
			}
			
			// ---------------
			dipositDesti.setPartidaOli(partidaOli);
			entrada.setPartidaOli(partidaOli);
			entrada.setCategoriaOli(partidaOli.getCategoriaOli());
			entrada.setData(data);
			entrada.setDiposit(dipositDesti);
			entrada.setEstabliment(establiment);
//			entrada.setLitres(litrosTotals);
			entrada.setLitres(litrosAfegits);
			entrada.setTraza(this.insertarTraza(Constants.CODI_TRAZA_TIPUS_ENTRADA_DIPOSIT, session));
			for (int i = 0; i < trazasPadre.size(); i++) {
				entrada.getTraza().getTrazasForTtrCodtrapare().add((Traza)trazasPadre.get(i));
			}
			entradaDipositDao.makePersistent(entrada, session);

			dipositDesti.setVolumActual(litrosTotals);
			dipositDesti.setPartidaOli(entrada.getPartidaOli());
			dipositDesti.setAcidesa(acidesaDesti);

			this.dipositModificar(dipositDesti, session);

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
	 * Añade todos los registros necesarios para la perdida de  aceite
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public void generarPerdida(Date data, Diposit diposit, Double litros, Establiment establiment,String desti,String descripcion) throws Exception{
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Transaction tx = null;

		try {	

			tx = session.beginTransaction();

			// Insertamos en el libro de salidas
			dipositDao.setHibernateTemplate(getHibernateTemplate());
			diposit = dipositDao.getById(diposit.getId());					

			SortidaDiposit sortida = null;
			sortida = new SortidaDiposit();
			sortida.setAcidesa(diposit.getAcidesa());
			sortida.setData(data);
			sortida.setDipositBySdiCoddor(diposit);
			sortida.setEstabliment(establiment);
			sortida.setLitres(litros);
			sortida.setPartidaOli(diposit.getPartidaOli());
			sortida.setCategoriaOli(diposit.getPartidaOli().getCategoriaOli());
			sortida.setTraza(this.insertarTraza(Constants.CODI_TRAZA_TIPUS_SORTIDA_DIPOSIT, session));
			
			
			// Veamos cual es el padre de la perdida si una entrada o una salida
			entradaDipositDao.setHibernateTemplate(getHibernateTemplate());
			EntradaDiposit ultimaEntrada = entradaDipositDao.findUltimaByDeposito(diposit.getId(), new Boolean(true));
			
			sortidaDipositDao.setHibernateTemplate(getHibernateTemplate());
			SortidaDiposit ultimaSortida = sortidaDipositDao.findUltimaByDeposito(diposit.getId(), new Boolean(true));
			
			if( ultimaSortida!= null && ultimaSortida.getTraza().getId().longValue() > ultimaEntrada.getTraza().getId().longValue()){
				sortida.getTraza().getTrazasForTtrCodtrapare().add(ultimaSortida.getTraza());
			}else{
				sortida.getTraza().getTrazasForTtrCodtrapare().add(ultimaEntrada.getTraza());
			}
			
			
			sortida.setDesti(desti);
			sortida.setObservacions(descripcion);
			sortidaDipositDao.makePersistent(sortida, session);

			// Actualizamos el contenido del deposito
			if(diposit.getVolumActual() != null && diposit.getVolumActual().doubleValue() >= litros.doubleValue()){
				diposit.setVolumActual(new Double((diposit.getVolumActual().doubleValue()) - litros.doubleValue()));
				if (diposit.getVolumActual() <= 0.0001){
					diposit.setVolumActual(0.0);
					diposit.setAcidesa(null);
					diposit.setPartidaOli(null);
				}
			}
			this.dipositModificar(diposit, session);

			tx.commit();

		} catch (Exception ex) {
			try {
				tx.rollback();
				throw new Exception(ex);
			} catch (HibernateException e) {
				throw new Exception(e);
			}
		}

	}
	
	/**
	 * Añade todos los registros necesarios para la salida de aceite
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public void generarSortidaOli(
			Date data, 
			Diposit diposit, 
			Double litros, 
			String destinatari, 
			String numeroDocument,
			String tipusDocument,
			String observacions,
			Pais pais,
			Provincia provincia,
			Municipi municipi,
			Boolean desqualificar,
			String desqualificat) throws Exception{
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Transaction tx = null;

		try {	

			tx = session.beginTransaction();
			
			sortidaDipositDao.setHibernateTemplate(getHibernateTemplate());
			entradaDipositDao.setHibernateTemplate(getHibernateTemplate());
			
			PartidaOli partida = diposit.getPartidaOli();
			EntradaDiposit entradaDiposit = null; 
			SortidaDiposit sortidaDipositPare = null;
			Diposit dipositSortida = diposit;
				
			// Si hem de desqualificar la sortida, llavors hem de fer una sortida i una entrada amb partida NoDo al dipòsit fictici
			if (desqualificar != null && desqualificar.booleanValue()){
				
				dipositDao.setHibernateTemplate(getHibernateTemplate());
				dipositSortida = dipositDao.findDipositFicticiByEstabliment(diposit.getEstabliment().getId());
				
				SortidaDiposit sortidaDipositAux = new SortidaDiposit();
				sortidaDipositAux.setAcidesa(diposit.getAcidesa());
				sortidaDipositAux.setCategoriaOli(partida.getCategoriaOli());
				sortidaDipositAux.setPartidaOli(diposit.getPartidaOli());
				sortidaDipositAux.setDipositBySdiCoddor(diposit);
				sortidaDipositAux.setDipositBySdiCoddde(dipositSortida);
				sortidaDipositAux.setEstabliment(diposit.getEstabliment());
				sortidaDipositAux.setData(data);
				sortidaDipositAux.setDesti(destinatari);
				sortidaDipositAux.setVendaNumeroDocument(numeroDocument);
				sortidaDipositAux.setVendaTipusDocument(tipusDocument);
				sortidaDipositAux.setLitres(litros);
				if (observacions != null) {
					sortidaDipositAux.setObservacions(desqualificat + ". " + observacions);
				} else {
					sortidaDipositAux.setObservacions(desqualificat);
				}
				sortidaDipositAux.setPais(pais);
				sortidaDipositAux.setProvincia(provincia);
				sortidaDipositAux.setMunicipi(municipi);
				
				// Traça
				sortidaDipositAux.setTraza(this.insertarTraza(Constants.CODI_TRAZA_TIPUS_SORTIDA_DIPOSIT, session));
				
				EntradaDiposit entradaPare = entradaDipositDao.findUltimaByDeposito(diposit.getId(), new Boolean(true));
				SortidaDiposit sortidaPare = sortidaDipositDao.findUltimaByDeposito(diposit.getId(), new Boolean(true));
				if(entradaPare!= null && sortidaPare!= null && (entradaPare.getTraza().getId().intValue() < sortidaPare.getTraza().getId().intValue())){
					sortidaDipositAux.getTraza().getTrazasForTtrCodtrapare().add(sortidaPare.getTraza());
				}else if(entradaPare!= null && sortidaPare!= null && (entradaPare.getTraza().getId().intValue() > sortidaPare.getTraza().getId().intValue())){
					sortidaDipositAux.getTraza().getTrazasForTtrCodtrapare().add(entradaPare.getTraza());
				}else if(sortidaPare == null && entradaPare != null){
					sortidaDipositAux.getTraza().getTrazasForTtrCodtrapare().add(entradaPare.getTraza());
				}
				sortidaDipositDao.makePersistent(sortidaDipositAux);
				
				// Categoria NoDO
				categoriaOliDao.setHibernateTemplate(getHibernateTemplate());
				CategoriaOli categoriaNoDO = categoriaOliDao.getById(Constants.CATEGORIA_NO_DO);
				
				partidaOliDao.setHibernateTemplate(getHibernateTemplate());
				PartidaOli partidaOli = partidaOliDao.getByNomAndEstabliment(partida.getNom() + " - No DO", diposit.getEstabliment().getId());
				
				if (partidaOli == null) {
					//Nova Partida d'oli
					partidaOli = new PartidaOli();
					partidaOli.setDataCreacio(data);
					partidaOli.setEsActiu(true);
					partidaOli.setEstabliment(diposit.getEstabliment());
					partidaOli.setNom(partida.getNom() + " - No DO");
					partidaOli.setOlivicultorEsPropietari(true);
					partidaOli.setCategoriaOli(categoriaNoDO);
					partidaOli.setCategoriaOliOriginal(categoriaNoDO);
					partidaOli.setEsVisualitza(new Boolean(false));
					
					partidaOliDao.setHibernateTemplate(getHibernateTemplate());
					partidaOliDao.makePersistent(partidaOli);
				}
				
				partida = partidaOli;
				
				entradaDiposit = new EntradaDiposit();
				entradaDiposit.setAcidesa(diposit.getAcidesa());
				entradaDiposit.setPartidaOli(partidaOli);
				entradaDiposit.setCategoriaOli(categoriaNoDO);
				entradaDiposit.setData(data);
				entradaDiposit.setDiposit(dipositSortida);
				entradaDiposit.setElaboracio(null);
				entradaDiposit.setEstabliment(diposit.getEstabliment());
				entradaDiposit.setLitres(litros);
				if (observacions != null) {
					entradaDiposit.setObservacions(desqualificat + ". " + observacions);
				} else {
					entradaDiposit.setObservacions(desqualificat);
				}
				entradaDiposit.setTraza(this.insertarTraza(Constants.CODI_TRAZA_TIPUS_ENTRADA_DIPOSIT, session));
				entradaDiposit.getTraza().getTrazasForTtrCodtrapare().add(sortidaDipositAux.getTraza());
				entradaDipositDao.setHibernateTemplate(getHibernateTemplate());
				entradaDipositDao.makePersistent(entradaDiposit);
				
			} else {
				entradaDiposit = entradaDipositDao.findUltimaByDeposito(diposit.getId(), new Boolean(true));
				sortidaDipositPare = sortidaDipositDao.findUltimaByDeposito(diposit.getId(), new Boolean(true));
			}
			
			SortidaDiposit sortidaDiposit = new SortidaDiposit();
			sortidaDiposit.setAcidesa(diposit.getAcidesa());
			sortidaDiposit.setCategoriaOli(partida.getCategoriaOli());
			sortidaDiposit.setPartidaOli(partida);
			sortidaDiposit.setDipositBySdiCoddor(dipositSortida);
			sortidaDiposit.setEstabliment(dipositSortida.getEstabliment());
			sortidaDiposit.setData(data);
			sortidaDiposit.setDesti(destinatari);
			sortidaDiposit.setVendaNumeroDocument(numeroDocument);
			sortidaDiposit.setVendaTipusDocument(tipusDocument);
			sortidaDiposit.setLitres(litros);
			if (observacions != null) {
				sortidaDiposit.setObservacions(desqualificat + ". " + observacions);
			} else {
				sortidaDiposit.setObservacions(desqualificat);
			}
			sortidaDiposit.setPais(pais);
			sortidaDiposit.setProvincia(provincia);
			sortidaDiposit.setMunicipi(municipi);
			sortidaDiposit.setTraza(this.insertarTraza(Constants.CODI_TRAZA_TIPUS_VENDA_OLI, session));
			
			if(entradaDiposit!= null && sortidaDipositPare!= null && (entradaDiposit.getTraza().getId().intValue() < sortidaDipositPare.getTraza().getId().intValue())){
				sortidaDiposit.getTraza().getTrazasForTtrCodtrapare().add(sortidaDipositPare.getTraza());
			}else if(entradaDiposit!= null && sortidaDipositPare!= null && (entradaDiposit.getTraza().getId().intValue() > sortidaDipositPare.getTraza().getId().intValue())){
				sortidaDiposit.getTraza().getTrazasForTtrCodtrapare().add(entradaDiposit.getTraza());
			}else if(sortidaDipositPare == null && entradaDiposit != null){
				sortidaDiposit.getTraza().getTrazasForTtrCodtrapare().add(entradaDiposit.getTraza());
			}
			sortidaDipositDao.makePersistent(sortidaDiposit);
			
			diposit.setVolumActual(new Double(diposit.getVolumActual().doubleValue() -litros));
			if (diposit.getVolumActual() <= 0.0){
				diposit.setAcidesa(null);
				diposit.setPartidaOli(null);
			}
			dipositDao.setHibernateTemplate(getHibernateTemplate());
			dipositDao.makePersistent(diposit);
			
			tx.commit();

		} catch (Exception ex) {
			try {
				tx.rollback();
				throw new Exception(ex);
			} catch (HibernateException e) {
				throw new Exception(e);
			}
		}

	}
	
	
	/**
	 * Añade todos los registros necesarios para la perdida de  aceite
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * <!-- end-xdoclet-definition -->
	 */
	public void analiticaCrear(Analitica a, String stringAnalitica) throws Exception{
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Transaction tx = null;
		
		try {
			
			tx = session.beginTransaction();
		
			Analitica analitica = a;
			dipositDao.setHibernateTemplate(getHibernateTemplate());
			Diposit diposit = dipositDao.getById(a.getDiposit().getId());	
			
			analitica.setTraza(this.insertarTraza(Constants.CODI_TRAZA_TIPUS_ANALITICA, session));
			entradaDipositDao.setHibernateTemplate(getHibernateTemplate());
			analitica.getTraza().getTrazasForTtrCodtrapare().add(entradaDipositDao.findUltimaByDeposito(diposit.getId(), new Boolean(true)).getTraza());
			
			analiticaDao.setHibernateTemplate(getHibernateTemplate());
			analiticaDao.makePersistent(analitica, session);
			
			
			//Sortida al llibre d'existències de Pendent i Entrada amb el Tipus d'Oli assolit
			ArrayList trazasPadre = new ArrayList();

			 // Generam la sortida del diposit
			 entradaDipositDao.setHibernateTemplate(getHibernateTemplate());
			 EntradaDiposit ultimaEntrada = entradaDipositDao.findUltimaByDeposito(diposit.getId(), new Boolean(true));
			
			 SortidaDiposit sortida = new SortidaDiposit();
			 sortida.setAcidesa(ultimaEntrada.getAcidesa());
			 sortida.setPartidaOli(diposit.getPartidaOli());
			 sortida.setCategoriaOli(diposit.getPartidaOli().getCategoriaOli());
			 sortida.setData(analitica.getData());
			 sortida.setDesti("Analitica");
			 sortida.setObservacions(stringAnalitica + ". " + analitica.getPartidaOli().getCategoriaOli().getNom());
			 sortida.setDipositBySdiCoddde(diposit);
			 sortida.setDipositBySdiCoddor(ultimaEntrada.getDiposit());
			 sortida.setEstabliment(ultimaEntrada.getEstabliment());
			 sortida.setLitres(diposit.getVolumActual());
			 sortida.setTraza(this.insertarTraza(Constants.CODI_TRAZA_TIPUS_SORTIDA_DIPOSIT, session));
			 sortida.getTraza().getTrazasForTtrCodtrapare().add(analitica.getTraza());
			
			 sortidaDipositDao.setHibernateTemplate(getHibernateTemplate());
			 SortidaDiposit ultimaSortida = sortidaDipositDao.findUltimaByDeposito(diposit.getId(), new Boolean(true));
			 if( ultimaSortida!= null && ultimaSortida.getTraza().getId().longValue() > ultimaEntrada.getTraza().getId().longValue()){
			  sortida.getTraza().getTrazasForTtrCodtrapare().add(ultimaSortida.getTraza());
			 }else{
			  sortida.getTraza().getTrazasForTtrCodtrapare().add(ultimaEntrada.getTraza());
			 }
			 trazasPadre.add(sortida.getTraza());
			 sortidaDipositDao.makePersistent(sortida, session);
		
			 
			//Canviem la categoria
			//Si la varietat que ha sortit és Verge Extra i la
			//categoria de la partida és pendent o DO, aleshores la varietat serà DO, 
			//En cas contrari serà No DO.
			 
			 //MODIFIQUEM EL DIPOSIT
			//Canviem l'acidesa del dipòsit i la partida
			//LLevam el precinte del diposit
			diposit.setPrecintat(false);
			diposit.setAcidesa(a.getAnalisiFisicoQuimicAcidesa());
			diposit.setPartidaOli(a.getPartidaOli());
			this.dipositModificar(diposit, session);
			 
			 //Verge Extra = 1
			 PartidaOli p = analitica.getPartidaOli();
			 CategoriaOli c = null;
			 if(analitica.getVarietatOli().getId().intValue() == Constants.VARIETAT_VERGE_EXTRA && 
				analitica.getPartidaOli() != null && !analitica.getPartidaOli().getCategoriaOli().getId().equals(Constants.CATEGORIA_NO_DO)){
				
				categoriaOliDao.setHibernateTemplate(getHibernateTemplate());
				c = categoriaOliDao.getById(Constants.CATEGORIA_DO);
				
			 } else {
				 
				categoriaOliDao.setHibernateTemplate(getHibernateTemplate());
				c = categoriaOliDao.getById(Constants.CATEGORIA_NO_DO);
				
			 }
			 
			 p.setCategoriaOli(c);
			 
			 partidaOliDao.setHibernateTemplate(getHibernateTemplate());
			 partidaOliDao.makePersistent(p);

			 
			 // Generam la entrada al diposit
			  EntradaDiposit entrada = new EntradaDiposit();
			  entrada.setAcidesa(diposit.getAcidesa());
			  entrada.setPartidaOli(analitica.getPartidaOli());
			  entrada.setCategoriaOli(analitica.getPartidaOli().getCategoriaOli());
			  entrada.setData(analitica.getData());
			  entrada.setDiposit(diposit);
			  entrada.setEstabliment(diposit.getEstabliment());
			  entrada.setLitres(diposit.getVolumActual());
			  entrada.setObservacions(stringAnalitica + ". " + c.getNom());
			  entrada.setTraza(this.insertarTraza(Constants.CODI_TRAZA_TIPUS_ENTRADA_DIPOSIT, session));
			  entrada.getTraza().getTrazasForTtrCodtrapare().add(analitica.getTraza());
			  for (int j = 0; j < trazasPadre.size(); j++) {
			   entrada.getTraza().getTrazasForTtrCodtrapare().add((Traza)trazasPadre.get(j));
			  }
			  entradaDipositDao.makePersistent(entrada, session);
			
			
			dipositDao.setHibernateTemplate(getHibernateTemplate());
			dipositDao.makePersistent(diposit, session);
			
			
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
	 * Añade todos los registros necesarios para el autocontrol
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition -->
	 */
	public void autocontrolCrear(AutocontrolOliva a, String stringAutocontrol) throws Exception{
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Transaction tx = null;
		
		try {
			
			tx = session.beginTransaction();
		
			AutocontrolOliva autocontrol = a;
//			lotDao.setHibernateTemplate(getHibernateTemplate());
//			Bota bota = botaDao.getById(a.getIdBota());	
//			if (autocontrol.getApta()) {
//				lot.setOlivaDO(true);
//			} else {
//				lot.setOlivaDO(false);
//			}
//			lotDao.makePersistent(lot);
			
			autocontrol.setTraza(this.insertarTraza(Constants.CODI_TRAZA_TIPUS_AUTOCONTROL, session));
			botaDao.setHibernateTemplate(getHibernateTemplate());
			autocontrol.getTraza().getTrazasForTtrCodtrapare().add((botaDao.getById(autocontrol.getBota())).getTraza());
			
			autocontrolDao.setHibernateTemplate(getHibernateTemplate());
			autocontrolDao.makePersistent(autocontrol, session);
			
			
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
	 * Anade todos los registros necesarios para la perdida de  aceite
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public void generarDescalificacion(Date data, Diposit diposit, Establiment establiment, String idCategoria, String pendentQualificacioObservacio, String observaciones) throws Exception{
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Transaction tx = null;

		try {	

			tx = session.beginTransaction();

			// Insertamos en el libro de salidas la salida de todo el aceite del deposito
			dipositDao.setHibernateTemplate(getHibernateTemplate());
			diposit = dipositDao.getById(diposit.getId());					

			SortidaDiposit sortida = new SortidaDiposit();
			sortida.setAcidesa(diposit.getAcidesa());
			sortida.setData(data);
			sortida.setDipositBySdiCoddor(diposit);
			sortida.setDipositBySdiCoddde(diposit);
			sortida.setEstabliment(establiment);
			sortida.setLitres(diposit.getVolumActual());
			sortida.setPartidaOli(diposit.getPartidaOli());
			sortida.setCategoriaOli(diposit.getPartidaOli().getCategoriaOli());
			sortida.setTraza(this.insertarTraza(Constants.CODI_TRAZA_TIPUS_SORTIDA_DIPOSIT, session));
			entradaDipositDao.setHibernateTemplate(getHibernateTemplate());
			EntradaDiposit ultimaEntrada = entradaDipositDao.findUltimaByDeposito(diposit.getId(), new Boolean(true));
			sortidaDipositDao.setHibernateTemplate(getHibernateTemplate());
			SortidaDiposit ultimaSortida = sortidaDipositDao.findUltimaByDeposito(diposit.getId(), new Boolean(true));
			if( ultimaSortida!= null && ultimaSortida.getTraza().getId().longValue() > ultimaEntrada.getTraza().getId().longValue()){
				sortida.getTraza().getTrazasForTtrCodtrapare().add(ultimaSortida.getTraza());
			}else if(ultimaEntrada!= null){
				sortida.getTraza().getTrazasForTtrCodtrapare().add(ultimaEntrada.getTraza());
			}
			
			String categoriaAnterior = diposit.getPartidaOli().getCategoriaOli().getNom();
			
			sortida.setObservacions(categoriaAnterior + ". " + observaciones);
			sortidaDipositDao.makePersistent(sortida, session);

			
			// Actualitzam la categoria de la partida
			categoriaOliDao.setHibernateTemplate(getHibernateTemplate());
			CategoriaOli  categoriaOli = categoriaOliDao.getById(Integer.valueOf(idCategoria));
//			
			partidaOliDao.setHibernateTemplate(getHibernateTemplate());
			PartidaOli partidaOli = partidaOliDao.getById(diposit.getPartidaOli().getId());
			PartidaOli partidaNova = new PartidaOli();
			partidaNova.setEsActiu(new Boolean(true));
			partidaNova.setCategoriaOli(categoriaOliDao.getById(new Integer(1)));
			partidaNova.setCategoriaOliOriginal(categoriaOliDao.getById(new Integer(3)));
			partidaNova.setDataCreacio(new Date());
			partidaNova.setEstabliment(diposit.getEstabliment());
			partidaNova.setEsVisualitza(new Boolean(true));
			partidaNova.setNom("(desqualificada)"+partidaOli.getNom());
			partidaNova.setOlivicultorEsPropietari(partidaOli.getOlivicultorEsPropietari());
			partidaOliDao.setHibernateTemplate(getHibernateTemplate());
			partidaOliDao.makePersistent(partidaNova);
			diposit.setPartidaOli(partidaNova);
			
//			partidaOli.setCategoriaOli(categoriaOli);
//			partidaOliDao.setHibernateTemplate(getHibernateTemplate());
//			partidaOliDao.makePersistent(partidaOli);
			
			
			// Insertamos en el libro de entradas la entrada de todo el aceite del deposito pero ahora esta en la categoria de 
			EntradaDiposit entrada = new EntradaDiposit();
			entrada.setAcidesa(diposit.getAcidesa());
			entrada.setData(data);
			entrada.setDiposit(diposit);
			entrada.setEstabliment(establiment);
			entrada.setLitres(diposit.getVolumActual());
			entrada.setPartidaOli(partidaOli);
			entrada.setCategoriaOli(diposit.getPartidaOli().getCategoriaOli());
			//===========================
			entrada.setObservacions(categoriaOli.getNom() + ". " + pendentQualificacioObservacio);
			entrada.setTraza(this.insertarTraza(Constants.CODI_TRAZA_TIPUS_ENTRADA_DIPOSIT, session));
			entrada.getTraza().getTrazasForTtrCodtrapare().add(sortida.getTraza());
			entradaDipositDao.setHibernateTemplate(getHibernateTemplate());
			entradaDipositDao.makePersistent(entrada, session);

			
			//===========================
			this.dipositModificar(diposit, session);

			tx.commit();
			session.flush();
		} catch (Exception ex) {

			try {
				tx.rollback();
				throw new Exception(ex);
			} catch (HibernateException e) {

			}
		}
	}


	/**
	 * Añade todos los registros necesarios para la creacion de aceite
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition -->
	 */
	public void crearLot(Lot lot, Establiment establiment, Boolean perduaOli, Boolean etiquetar, String perdua, String embotellat) throws Exception {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			
			DecimalFormat numberDecimalFormat = new DecimalFormat("###,###,##0.000");
			
			Diposit diposit = lot.getDiposit();
			double litresTotals = 0;
			
			
			// Creamos el lote y la salida del depósito.
			lot.setPosicioX(Integer.valueOf("0"));
			lot.setPosicioY(Integer.valueOf("0"));
			lotDao.setHibernateTemplate(getHibernateTemplate());
			lotDao.makePersistent(lot, session);
			
			SortidaDiposit sortida = new SortidaDiposit();
			
			sortida.setAcidesa(diposit.getAcidesa());
			sortida.setPartidaOli(diposit.getPartidaOli());
			sortida.setCategoriaOli(diposit.getPartidaOli().getCategoriaOli());
			sortida.setData(lot.getData());
			sortida.setDesti(embotellat);
			sortida.setDipositBySdiCoddor(diposit);
			sortida.setEstabliment(establiment);
			sortida.setLitres(lot.getLitresEnvassats());
			litresTotals += lot.getLitresEnvassats().doubleValue();
			sortida.setLot(lot);
			sortida.setObservacions(lot.getObservacions());
			sortida.setTraza(this.insertarTraza(Constants.CODI_TRAZA_TIPUS_SORTIDA_DIPOSIT, session));
			entradaDipositDao.setHibernateTemplate(getHibernateTemplate());
			sortida.getTraza().getTrazasForTtrCodtrapare().add(entradaDipositDao.findUltimaByDeposito(diposit.getId(), new Boolean(true)).getTraza());
			
			sortidaDipositDao.setHibernateTemplate(getHibernateTemplate());
			sortidaDipositDao.makePersistent(sortida, session);
			
			EntradaLot entradaLot = new EntradaLot();
			entradaLot.setAcidesa(lot.getAcidesa());
			entradaLot.setData(lot.getData());
			entradaLot.setDipositProcedencia(diposit.getId().toString());
			entradaLot.setLot(lot);
			entradaLot.setNumerosContraetiquetes("");
			entradaLot.setTraza(this.insertarTraza(Constants.CODI_TRAZA_TIPUS_ENTRADA_LOT, session));
			entradaLot.getTraza().getTrazasForTtrCodtrapare().add(sortida.getTraza());
			entradaLot.setZona(lot.getZona());
			entradaLot.setBotelles(lot.getNumeroBotellesActuals());
			
			entradaLotDao.setHibernateTemplate(getHibernateTemplate());
			entradaLotDao.makePersistent(entradaLot, session);
			
			
			if (perduaOli != null && perduaOli.booleanValue()) { // Insertamos una salida de aceite
				sortida = new SortidaDiposit();
				sortida.setAcidesa(diposit.getAcidesa());
				sortida.setPartidaOli(diposit.getPartidaOli());
				sortida.setCategoriaOli(diposit.getPartidaOli().getCategoriaOli());
				sortida.setData(lot.getData());
				sortida.setDesti(perdua);
				sortida.setDipositBySdiCoddor(diposit);
				sortida.setEstabliment(establiment);
				sortida.setLitres(lot.getLitresPerduts());
				litresTotals += lot.getLitresPerduts().doubleValue();
				sortida.setObservacions(lot.getMotiuPerdua());
				sortida.setTraza(this.insertarTraza(Constants.CODI_TRAZA_TIPUS_SORTIDA_DIPOSIT, session));
				
				sortida.getTraza().getTrazasForTtrCodtrapare().add(entradaLot.getTraza());
				
				sortidaDipositDao.setHibernateTemplate(getHibernateTemplate());
				sortidaDipositDao.makePersistent(sortida, session);
				
				
				
			}
			
			//Actualizamos los litros del deposito
			diposit.setVolumActual(new Double(numberDecimalFormat.parse(numberDecimalFormat.format(diposit.getVolumActual().doubleValue() - litresTotals)).doubleValue()));
			dipositDao.setHibernateTemplate(getHibernateTemplate());
			dipositDao.makePersistent(diposit, session);
			
			tx.commit();
		} catch (Exception ex) {
			logger.error("EXCEPTION", ex);
			try {
				tx.rollback();
				throw new Exception(ex);
			} catch (HibernateException e) {
			}
		}
	}
	
	/**
	 * AÃƒÂ±ade todos los registros necesarios para la creacion de aceite
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition -->
	 */
	public void crearLot(Lot lot, boolean introExistencies) throws Exception {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			
			// Creamos el lote
			if (lot.getId() == null) {
				lot.setPosicioX(Integer.valueOf("0"));
				lot.setPosicioY(Integer.valueOf("0"));
			}
			lotDao.setHibernateTemplate(getHibernateTemplate());
			lotDao.makePersistent(lot);
			
			// Introducimos existencias de aceite en los lotes
			if (introExistencies) {
				EntradaLot entradaLot = new EntradaLot();
				entradaLot.setAcidesa(lot.getAcidesa());
				entradaLot.setData(lot.getData());
				entradaLot.setDipositProcedencia(null);
				entradaLot.setLot(lot);
				entradaLot.setNumerosContraetiquetes("");
				entradaLot.setTraza(this.insertarTraza(Constants.CODI_TRAZA_TIPUS_ENTRADA_LOT, session));
				entradaLot.setZona(lot.getZona());
				entradaLot.setBotelles(lot.getNumeroBotellesActuals());
				
				entradaLotDao.setHibernateTemplate(getHibernateTemplate());
				entradaLotDao.makePersistent(entradaLot);
			}
			
			tx.commit();
		} catch (Exception ex) {
			logger.error("EXCEPTION", ex);
			try {
				tx.rollback();
				throw new Exception(ex);
			} catch (HibernateException e) {
			}
		}
	}
	
	
	/**
	 * Añade todos los registros necesarios para la creacion de aceituna envasada
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition -->
	 */
	public void crearLot(Lot lot, Establiment establiment, Double pH1, Double pH2, String lotAcid, Double quantitatAcid) throws Exception {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			
			DecimalFormat numberDecimalFormat = new DecimalFormat("###,###,##0.000");
			
			//LOT OLIVA ENVASADA
			lot.setPosicioX(Integer.valueOf("0"));
			lot.setPosicioY(Integer.valueOf("0"));
			lotDao.setHibernateTemplate(getHibernateTemplate());
			lotDao.makePersistent(lot, session);
			
			//SORTIDA D'OLI PER AFEGIR A OLIVA NEGRA
			Diposit diposit = lot.getDiposit();
			SortidaDiposit sortidaDiposit = new SortidaDiposit();
			if (diposit!=null) {
				
				entradaDipositDao.setHibernateTemplate(getHibernateTemplate());
				sortidaDipositDao.setHibernateTemplate(getHibernateTemplate());
				dipositDao.setHibernateTemplate(getHibernateTemplate());
				
				sortidaDiposit.setAcidesa(diposit.getAcidesa());
				sortidaDiposit.setPartidaOli(diposit.getPartidaOli());
				sortidaDiposit.setCategoriaOli(diposit.getPartidaOli().getCategoriaOli());
				sortidaDiposit.setData(lot.getData());
				sortidaDiposit.setDesti(Constants.DESTI_OLIVA_NEGRA);
				sortidaDiposit.setDipositBySdiCoddor(diposit);
				sortidaDiposit.setEstabliment(establiment);
				sortidaDiposit.setLitres(lot.getTotalOliAfegit());
				sortidaDiposit.setLot(lot);
				sortidaDiposit.setObservacions(lot.getObservacions());
				sortidaDiposit.setTraza(this.insertarTraza(Constants.CODI_TRAZA_TIPUS_SORTIDA_DIPOSIT, session));
				sortidaDiposit.getTraza().getTrazasForTtrCodtrapare().add(entradaDipositDao.findUltimaByDeposito(diposit.getId(), new Boolean(true)).getTraza());
				sortidaDipositDao.makePersistent(sortidaDiposit, session);
				
				//Actualizamos los litros del deposito
				diposit.setVolumActual(new Double(numberDecimalFormat.parse(numberDecimalFormat.format(diposit.getVolumActual().doubleValue() - lot.getTotalOliAfegit().doubleValue())).doubleValue()));
				dipositDao.makePersistent(diposit, session);
			}
			
			//SORTIDA DE BOTA
			Bota bota = lot.getBota();
			
			botaDao.setHibernateTemplate(getHibernateTemplate());
			sortidaBotaDao.setHibernateTemplate(getHibernateTemplate());
			
			SortidaBota sortidaBota = new SortidaBota();
			sortidaBota.setData(lot.getData());
			sortidaBota.setDesti(Constants.DESTI_ENVASAT);
			sortidaBota.setBotaBySboCodbor(bota);
			sortidaBota.setEstabliment(establiment);
			sortidaBota.setKgOliva(lot.getKgOlivaTaula());
			sortidaBota.setLot(lot);
			sortidaBota.setpH1(pH1);
			sortidaBota.setpH2(pH2);
			sortidaBota.setLotAcidCitric(lotAcid);
			sortidaBota.setQuantitatAcidCitric(quantitatAcid);
			sortidaBota.setObservacions(lot.getObservacions());
			sortidaBota.setTraza(this.insertarTraza(Constants.CODI_TRAZA_TIPUS_SORTIDA_BOTA, session));
			sortidaBota.getTraza().getTrazasForTtrCodtrapare().add(bota.getTraza());
			sortidaBota.setTipusOlivaTaula(lot.getTipusOlivaTaula());
			sortidaBota.setValid(new Boolean(true));
			sortidaBotaDao.makePersistent(sortidaBota, session);
			
			//Actualizamos los kilos de la bota
			bota.setKgOlivaRestant(new Double(numberDecimalFormat.parse(numberDecimalFormat.format(bota.getKgOlivaRestant().doubleValue() - lot.getKgOlivaTaula().doubleValue())).doubleValue()));
			if (bota.getKgOlivaRestant()<=0) bota.setActiu(false);
			botaDao.makePersistent(bota, session);
			
			EntradaLot entradaLot = new EntradaLot();
			entradaLot.setAcidesa(lot.getAcidesa());
			entradaLot.setData(lot.getData());
			entradaLot.setDipositProcedencia(bota.getId().toString());
			entradaLot.setLot(lot);
			entradaLot.setNumerosContraetiquetes("");
			entradaLot.setTraza(this.insertarTraza(Constants.CODI_TRAZA_TIPUS_ENTRADA_LOT, session));
			entradaLot.getTraza().getTrazasForTtrCodtrapare().add(sortidaBota.getTraza());
			if (sortidaDiposit.getTraza()!=null) 
				entradaLot.getTraza().getTrazasForTtrCodtrapare().add(sortidaDiposit.getTraza());
			entradaLot.setZona(lot.getZona());
			entradaLot.setBotelles(lot.getNumeroBotellesActuals());
			
			entradaLotDao.setHibernateTemplate(getHibernateTemplate());
			entradaLotDao.makePersistent(entradaLot, session);
			
//			if (perduaOli != null && perduaOli.booleanValue()) { // Insertamos una salida de aceite
//				sortida = new SortidaDiposit();
//				sortida.setAcidesa(diposit.getAcidesa());
//				sortida.setPartidaOli(diposit.getPartidaOli());
//				sortida.setCategoriaOli(diposit.getPartidaOli().getCategoriaOli());
//				sortida.setData(lot.getData());
//				sortida.setDesti(perdua);
//				sortida.setDipositBySdiCoddor(diposit);
//				sortida.setEstabliment(establiment);
//				sortida.setLitres(lot.getLitresPerduts());
//				litresTotals += lot.getLitresPerduts().doubleValue();
//				sortida.setObservacions(lot.getMotiuPerdua());
//				sortida.setTraza(this.insertarTraza(Constants.CODI_TRAZA_TIPUS_SORTIDA_DIPOSIT, session));
//				
//				sortida.getTraza().getTrazasForTtrCodtrapare().add(entradaLot.getTraza());
//				
//				sortidaDipositDao.setHibernateTemplate(getHibernateTemplate());
//				sortidaDipositDao.makePersistent(sortida, session);
//				
//				
//				
//			}
			tx.commit();
		} catch (Exception ex) {
			logger.error("EXCEPTION", ex);
			try {
				tx.rollback();
				throw new Exception(ex);
			} catch (HibernateException e) {
			}
		}
	}
	
	/**
	 * Añade todos los registros necesarios para el cierre de libros del aceite
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public void generarTancamentLlibres(
			Establiment establiment,
			Date data,
			Long[] dipositId,
			Double[] acidesa, 
			Long[] partidaOliId,
			Double[] litres,
			String[] observacions,
			Long[] lotId,
			Long[] marcaId,
			Etiquetatge[] etiquetatge,
			String[] numeroLot,
			VarietatOliva[][] varietats,
			Long[] partidaOliIdLot,
			Zona[] zona,
			Double[] acidesaLot,
			Integer[] numeroBotellesInicials,
			Integer[] numeroBotellesActuals,
			Boolean[] etiquetar,
			String[] observacionsLot,
//			EtiquetesLot[] etiquetesLots,
			Date[] dataConsum,
			String tancamentLlibres) throws Exception {
		
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			
			// DIPÒSITS
			int i = 0;
			while (i < dipositId.length){
				
				dipositDao.setHibernateTemplate(getHibernateTemplate());
				Diposit diposit = dipositDao.getById(dipositId[i]);
				
				partidaOliDao.setHibernateTemplate(getHibernateTemplate());
				PartidaOli partida = partidaOliDao.getById(partidaOliId[i]);
				
//				boolean esEntrada = false;
				
				// Cream l'entrada / sortida corresponent
				// --------------------------------------
				
				// Si el dipòsit està buit
				if (diposit.getVolumActual() == null || diposit.getVolumActual() == 0) {
					
					// Assignam les noves dades al dipòsit
					diposit.setAcidesa(acidesa[i]);
					diposit.setPartidaOli(partida);
					diposit.setVolumActual(litres[i]);
					
					EntradaDiposit entrada = new EntradaDiposit();
					entrada = new EntradaDiposit();
					entrada.setAcidesa(diposit.getAcidesa());
					entrada.setPartidaOli(diposit.getPartidaOli());
					entrada.setCategoriaOli(diposit.getPartidaOli().getCategoriaOli());
					entrada.setData(data);
					entrada.setDiposit(diposit);
					entrada.setElaboracio(null);
					entrada.setEstabliment(establiment);
					entrada.setLitres(diposit.getVolumActual());
					if (observacions[i] != null) {
						entrada.setObservacions(tancamentLlibres + ". " + observacions[i]);
					} else {
						entrada.setObservacions(tancamentLlibres);
					}
					
//					Traza traza = new Traza(new Integer(Constants.CODI_TRAZA_TIPUS_ENTRADA_DIPOSIT));
//					trazaDao.setHibernateTemplate(getHibernateTemplate());
//					trazaDao.makePersistent(traza);
					
					entrada.setTraza(this.insertarTraza(Constants.CODI_TRAZA_TIPUS_ENTRADA_DIPOSIT, session));
					entradaDipositDao.setHibernateTemplate(getHibernateTemplate());
					entradaDipositDao.makePersistent(entrada);
					
				} else {
					
					ArrayList trazasPadre = new ArrayList();
					
					// Generam la sortida del diposit
					entradaDipositDao.setHibernateTemplate(getHibernateTemplate());
					EntradaDiposit ultimaEntrada = entradaDipositDao.findUltimaByDeposito(diposit.getId(), new Boolean(true));
						
					SortidaDiposit sortida = new SortidaDiposit();
					sortida.setAcidesa(ultimaEntrada.getAcidesa());
					sortida.setPartidaOli(diposit.getPartidaOli());
					sortida.setCategoriaOli(diposit.getPartidaOli().getCategoriaOli());
					sortida.setData(data);
					sortida.setDesti(tancamentLlibres);
					if (observacions[i] != null) {
						sortida.setObservacions(tancamentLlibres + ". " + observacions[i]);
					} else {
						sortida.setObservacions(tancamentLlibres);
					}
					sortida.setDipositBySdiCoddde(diposit);
					sortida.setDipositBySdiCoddor(ultimaEntrada.getDiposit());
					sortida.setEstabliment(ultimaEntrada.getEstabliment());
					sortida.setLitres(diposit.getVolumActual());
					sortida.setTraza(this.insertarTraza(Constants.CODI_TRAZA_TIPUS_SORTIDA_DIPOSIT, session));
					
					sortidaDipositDao.setHibernateTemplate(getHibernateTemplate());
					SortidaDiposit ultimaSortida = sortidaDipositDao.findUltimaByDeposito(diposit.getId(), new Boolean(true));
					if( ultimaSortida!= null && ultimaSortida.getTraza().getId().longValue() > ultimaEntrada.getTraza().getId().longValue()){
						sortida.getTraza().getTrazasForTtrCodtrapare().add(ultimaSortida.getTraza());
					}else{
						sortida.getTraza().getTrazasForTtrCodtrapare().add(ultimaEntrada.getTraza());
					}
					trazasPadre.add(sortida.getTraza());
					sortidaDipositDao.makePersistent(sortida, session);
					
					// Generam la entrada al diposit
					if (litres[i] > 0){
						EntradaDiposit entrada = new EntradaDiposit();
						entrada.setAcidesa(acidesa[i]);
						entrada.setPartidaOli(partida);
						entrada.setCategoriaOli(partida.getCategoriaOli());
						entrada.setData(data);
						entrada.setDiposit(diposit);
						entrada.setEstabliment(establiment);
						entrada.setLitres(litres[i]);
						entrada.setTraza(this.insertarTraza(Constants.CODI_TRAZA_TIPUS_ENTRADA_DIPOSIT, session));
						for (int j = 0; j < trazasPadre.size(); j++) {
							entrada.getTraza().getTrazasForTtrCodtrapare().add((Traza)trazasPadre.get(j));
						}
						entradaDipositDao.makePersistent(entrada, session);

						diposit.setAcidesa(acidesa[i]);
						diposit.setPartidaOli(partida);
					} else {
						diposit.setAcidesa(new Double(0));
						diposit.setPartidaOli(null);
					}
					diposit.setVolumActual(litres[i]);
				}
				
				dipositDao.setHibernateTemplate(getHibernateTemplate());
				dipositDao.makePersistent(diposit, session);
				i++;
			}
			
			// TODO: LOTS
			
			i = 0;
			while (i < lotId.length){
				
				lotDao.setHibernateTemplate(getHibernateTemplate());
				Lot lot = lotDao.getById(lotId[i]);
				
				Integer botellesOriginals = lot.getNumeroBotellesInicials();
				
				partidaOliDao.setHibernateTemplate(getHibernateTemplate());
				PartidaOli partidaOli = partidaOliDao.getById(partidaOliIdLot[i]);
				
				lot.setPartidaOli(partidaOli);
				lot.setEtiquetatge(etiquetatge[i]);
				lot.setNumeroLot(numeroLot[i]);
				lot.setAcidesa(acidesaLot[i]);
				lot.setLitresEnvassats(numeroBotellesInicials[i]*etiquetatge[i].getTipusEnvas().getVolum());
				lot.setNumeroBotellesInicials(numeroBotellesInicials[i]);
				lot.setNumeroBotellesActuals(numeroBotellesActuals[i]);
				lot.setZona(zona[i]);
				lot.setCreatTancament(new Boolean(true));
				lot.setDataConsum(dataConsum[i]);
				
				// Introducimos existencias de aceite en los lotes
				if (botellesOriginals == null || botellesOriginals == 0) { // Lot nou
					EntradaLot entradaLot = new EntradaLot();
					entradaLot.setAcidesa(lot.getAcidesa());
					entradaLot.setData(data);
					entradaLot.setDipositProcedencia(null);
					entradaLot.setLot(lot);
					entradaLot.setNumerosContraetiquetes("");
					entradaLot.setTraza(this.insertarTraza(Constants.CODI_TRAZA_TIPUS_ENTRADA_LOT, session));
					entradaLot.setZona(lot.getZona());
					entradaLot.setDipositProcedencia(tancamentLlibres);
					if (varietats[i] != null) {
						for (int j = 0; j < varietats[i].length; j++) {
							lot.getVarietatOlivas().add(varietats[i][j]);
						}
					}
					if(observacionsLot[i] != null) {
						entradaLot.setObservacions(tancamentLlibres + ". " + observacionsLot[i]);
					} else {
						entradaLot.setObservacions(tancamentLlibres);
					}
					entradaLot.setBotelles(numeroBotellesActuals[i]);
					entradaLotDao.setHibernateTemplate(getHibernateTemplate());
					entradaLotDao.makePersistent(entradaLot);
				} 
				
				lotDao.setHibernateTemplate(getHibernateTemplate());
				lotDao.makePersistent(lot);
/* TODO: ETIQUETES DELS LOTS */				
//				if (lot != null) {
//					if (etiquetesLots.length > 0) {
//						for (int n = 0; n < etiquetesLots.length; n++) {
//							etiquetesLotDao.setHibernateTemplate(getHibernateTemplate());
//							etiquetesLots[n].setLot(lot);
//							etiquetesLotDao.makePersistent(etiquetesLots[n]);
//						}
//					}
//				}
				i++;
			}
			tx.commit();
		} catch (Exception ex) {
			logger.error("EXCEPTION", ex);
			try {
				tx.rollback();
				throw new Exception(ex);
			} catch (HibernateException e) {
			}
		}
	}
	
	/**
	 * Añade todos los registros necesarios para el cierre de libros del aceite
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public void insertarDevolucioBotelles(Lot lot, Long idSortida, Integer botelles, Date data, String observacio) {
		if (lot != null) {
			Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			
			sortidaLotDao.setHibernateTemplate(getHibernateTemplate());
			SortidaLot sortidaLot = sortidaLotDao.getById(idSortida);
			Integer botellesTornades = 0;
			if (sortidaLot.getBotellesDevolucio() != null) botellesTornades = sortidaLot.getBotellesDevolucio();
			sortidaLot.setBotellesDevolucio(botellesTornades + botelles);
			sortidaLotDao.makePersistent(sortidaLot);
			
			Integer botellesOriginals = lot.getNumeroBotellesInicials();
			lot.setNumeroBotellesActuals(lot.getNumeroBotellesActuals() + botelles);
			
			
			// Introduïm la devolució al lot.
			EntradaLot entradaLot = new EntradaLot();
			entradaLot.setAcidesa(lot.getAcidesa());
//			entradaLot.setData(Calendar.getInstance().getTime());
			entradaLot.setData(data);
			entradaLot.setDipositProcedencia(null);
			entradaLot.setLot(lot);
					
			entradaLot.setTraza(this.insertarTraza(Constants.CODI_TRAZA_TIPUS_ENTRADA_LOT, session));
			entradaLot.getTraza().getTrazasForTtrCodtrapare().add(sortidaLot.getTraza());
			entradaLot.setZona(lot.getZona());
			entradaLot.setDipositProcedencia(observacio);
			entradaLot.setObservacions(observacio);
			entradaLot.setEsDevolucio(new Boolean(true));
			entradaLot.setBotelles(botelles);

			entradaLotDao.setHibernateTemplate(getHibernateTemplate());
			entradaLotDao.makePersistent(entradaLot);
			if(lot.getDatafi() !=null){
				lot.setDatafi(null);
				
			}
			lotDao.setHibernateTemplate(getHibernateTemplate());
			lotDao.makePersistent(lot);
		} 
	}
			
	/**
	 * Añade todos los registros necesarios para la creacion de aceite
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public void crearLotCierreLibros(Lot lot, boolean introExistencies, String tancamentLlibres) throws Exception {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			
			// Creamos el lote
			if (lot.getId() == null) {
				lot.setPosicioX(Integer.valueOf("0"));
				lot.setPosicioY(Integer.valueOf("0"));
			}
			lotDao.setHibernateTemplate(getHibernateTemplate());
			lotDao.makePersistent(lot);
			
			// Introducimos existencias de aceite en los lotes
			if (introExistencies) {
				EntradaLot entradaLot = new EntradaLot();
				entradaLot.setAcidesa(lot.getAcidesa());
				entradaLot.setData(lot.getData());
				entradaLot.setDipositProcedencia(null);
				entradaLot.setLot(lot);
				entradaLot.setNumerosContraetiquetes("");
				entradaLot.setTraza(this.insertarTraza(Constants.CODI_TRAZA_TIPUS_ENTRADA_LOT, session));
				entradaLot.setZona(lot.getZona());
				entradaLot.setDipositProcedencia(tancamentLlibres);
				entradaLot.setBotelles(lot.getNumeroBotellesActuals());
				entradaLotDao.setHibernateTemplate(getHibernateTemplate());
				entradaLotDao.makePersistent(entradaLot);
			}
			
			tx.commit();
		} catch (Exception ex) {
			logger.error("EXCEPTION", ex);
			try {
				tx.rollback();
				throw new Exception(ex);
			} catch (HibernateException e) {
			}
		}
	}
	
	/**
	 * AÃƒÂ±ade todos los registros necesarios para el trasvase del aceite
	 * @param data
	 * @param dipositsOrigen
	 * @param establimentDesti
	 * @param establiment
	 * @throws Exception
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Long[] generarMoureDiposits(Date data, Diposit[] dipositsOrigen, Long establimentDesti, Establiment establiment, String rolDoControlador, String rolDoGestor, String emailFrom) throws Exception{ 
		
		
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			
			//ENVIO DE MAIL: Obtenim les dades comuns
			String from = establiment.getEmail();
			establimentDao.setHibernateTemplate(getHibernateTemplate());
			String to = establimentDao.getEmail(establimentDesti);			
			usuariDao.setHibernateTemplate(getHibernateTemplate());
			if(!from.contains("@limit.es")){
				List mailControladores = usuariDao.getEmailsByGrup(rolDoControlador);
				if(mailControladores!= null){
					for(int i1=0;i1<mailControladores.size();i1++){
						if(mailControladores.get(i1)!= null){
							to += ", " + mailControladores.get(i1);
						}				
					}				
				}
				List mailGestors = usuariDao.getEmailsByGrup(rolDoGestor);
				if(mailGestors!= null){
					for(int i1=0;i1<mailGestors.size();i1++){
						if(mailGestors.get(i1)!= null){
							to += ", " + mailGestors.get(i1);
						}				
					}				
				}
			}
			// Anadimos el from también para recibir el mail
			if (from != null && to != null) {
				to += ", " + from; 
			}

			ResourceBundle bundle = ResourceBundle.getBundle("messages",new java.util.Locale("ca"));
			String preSubject = bundle.getString("proces.moureDiposit.subjectMail");
			String content = bundle.getString("proces.moureDiposit.textoMail");
			String preFileName =  bundle.getString("proces.moureDiposit.volantePdf");
			
			GeneraPdfVolante generaPdfVolante = new GeneraPdfVolante(getMessageSourceAccessor(),
					tipusEstablimentTafona,
					tipusEstablimentEnvasadora,
					tipusEstablimentTafonaEnvasadora);

			solicitanteDao.setHibernateTemplate(getHibernateTemplate());
			String solicitanteEmisor = solicitanteDao.getNameByEstablimentId(establiment.getId());
			solicitanteDao.setHibernateTemplate(getHibernateTemplate());
			String solicitanteDest = solicitanteDao.getNameByEstablimentId(establimentDesti);
			solicitanteDao.setHibernateTemplate(getHibernateTemplate());
			String solicitanteEmiNif = solicitanteDao.getNifByEstablimentId(establiment.getId());
			solicitanteDao.setHibernateTemplate(getHibernateTemplate());
			String solicitanteDesNif = solicitanteDao.getNifByEstablimentId(establimentDesti);
			
			List trasllats = new ArrayList();
			
			for(int i=0;i<dipositsOrigen.length;i++){
				Long dipID = dipositsOrigen[i].getId();
				dipositDao.setHibernateTemplate(getHibernateTemplate());
				Diposit dip = dipositDao.getById(dipID); 
				if(dip != null){					

					// INSERTAMOS UN REGISTRO EN TRASLLAT DIPOSIT
					
					Set diposit = new HashSet();
					diposit.add(dip);
					
					Long idPartidaTrasllat = null;
					if(dip.getPartidaOli() != null){
						idPartidaTrasllat = dip.getPartidaOli().getId();
					}
					
					Establiment establimentDestino = new Establiment();
					establimentDestino.setId(establimentDesti);
					
					final boolean ACCEPTATTRASLLAT = false;
					final boolean RETORNATESTABLIMENTORIGEN = false;
					final boolean TRASLLADANT = true;
					final boolean VALID = true;
					final boolean ESDIPOSIT = true;
					
					Trasllat trasllat = new Trasllat(establimentDestino, 
							this.insertarTraza(Constants.CODI_TRAZA_TIPUS_TRASLLAT_DIPOSIT, session),
							establiment, 
							data,
							//ACCEPTATTRASLLAT,
							null,
							RETORNATESTABLIMENTORIGEN,
							TRASLLADANT, 
							VALID, 
							ESDIPOSIT, 
							//new Date(),
							data, // Posam com a data d'alta la mateixa que la data esperada -> la data d'enviament
							diposit); //, idPartidaTrasllat);		
					
					trasllat.setQuantitatEnviament(dip.getVolumActual());
					
					trasllatDao.setHibernateTemplate(getHibernateTemplate());
					trasllatDao.makePersistent(trasllat);
				
					if (dip.getVolumActual() != null && dip.getVolumActual().doubleValue() > 0.0) {

						//ENVIO DE MAIL: Obtenim les dades particulars
						String subject = preSubject + " - " + trasllat.getId();
						String fileName =  preFileName + trasllat.getId() + ".pdf";
						
						//Creamos el pdf de solicitud
						ByteArrayOutputStream pdf = new ByteArrayOutputStream();
						
						establimentDao.setHibernateTemplate(getHibernateTemplate());
						Establiment estDes = establimentDao.getById(establimentDesti); 
						
						pdf.write(generaPdfVolante.generarPdfVolantCirculacio(trasllat, solicitanteEmisor, solicitanteDest, solicitanteEmiNif, solicitanteDesNif, establiment, estDes, dip.getVolumActual().doubleValue(),dip.getPartidaOli().getNom()));
						
						//enviar el email			
						ServeiCorreu correo =  new ServeiCorreu();
						correo.enviaCorreu(from, to, subject, content, pdf.toByteArray(), fileName, emailFrom);
						trasllats.add(trasllat.getId());
					}
				}				
			}
			
            tx.commit();
            
            if (trasllats.size() > 0) {
				Long[] volants = new Long[trasllats.size()];
				int i = 0;
				for (Iterator it = trasllats.iterator(); it.hasNext();){
					Long volant = (Long)it.next();
					volants[i] = volant;
					i++;
				}
				return volants;
			}
            
		} catch (Exception ex) {
			logger.error("EXCEPTION", ex);
			try {
				tx.rollback();
				throw new Exception(ex);
			} catch (HibernateException e) {
			}
		}
		return null;
	
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
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public void generarMoureOli(Date data, Diposit[] dipositsOrigen, Long establimentDesti, Establiment establiment, String rolDoControlador, String rolDoGestor, String emailFrom) throws Exception{ 
		
		
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			
			//Desactivamos todos los depositos
			List depositosList = new ArrayList();
			Long idPartidaTrasllat = null;

			for(int i=0;i<dipositsOrigen.length;i++){
				Long dipID = dipositsOrigen[i].getId();
				dipositDao.setHibernateTemplate(getHibernateTemplate());
				Diposit dip = dipositDao.getById(dipID); 
				if(dip != null){					
					depositosList.add(dip);				
				}
				
			}
			
			// INSERTAMOS UN REGISTRO EN TRASLLAT DIPOSIT
			
			
			Set depositos = new HashSet(depositosList);
			
			Establiment establimentDestino = new Establiment();
			establimentDestino.setId(establimentDesti);
			
			final boolean ACCEPTATTRASLLAT = false;
			final boolean RETORNATESTABLIMENTORIGEN = false;
			final boolean TRASLLADANT = true;
			final boolean VALID = true;
			final boolean ESDIPOSIT = false;
			
			//Posem "null" a la partida perquè com que traslladam oli després no haurem de necessitar la partida per retornar
			// el dipòsit a l'estat original.
			Trasllat trasllat = new Trasllat(establimentDestino, 
					this.insertarTraza(Constants.CODI_TRAZA_TIPUS_TRASLLAT_OLI, session),
					establiment, 
					data,
					ACCEPTATTRASLLAT, 
					RETORNATESTABLIMENTORIGEN,
					TRASLLADANT,
					VALID, 
					ESDIPOSIT, 
					//new Date(), 
					data, // Posam com a data d'alta la mateixa que la data esperada -> la data d'enviament
					depositos); //, null);
			trasllat.setQuantitatEnviament(new Double(0.00));
			trasllat.setQuantitatRetorn(new Double(0.00));
			trasllat.setDataAcceptarEnviament(data);
			trasllatDao.setHibernateTemplate(getHibernateTemplate());
			trasllatDao.makePersistent(trasllat);
			
			
			//Insertem la quantitat a traslladar al diposit
			for(int i=0;i<dipositsOrigen.length;i++){
				Long dipID = dipositsOrigen[i].getId();
				dipositDao.setHibernateTemplate(getHibernateTemplate());
				Diposit dip = dipositDao.getById(dipID); 
				if(dip != null){					
					dip.setVolumTrasllat(dipositsOrigen[i].getVolumTrasllat());		
					dipositDao.setHibernateTemplate(getHibernateTemplate());
					dipositDao.makePersistent(dip);
				}				
			}
			
			
			//ENVIO DE MAIL 
			String from = establiment.getEmail();
			establimentDao.setHibernateTemplate(getHibernateTemplate());
			String to = establimentDao.getEmail(establimentDesti);			
			usuariDao.setHibernateTemplate(getHibernateTemplate());
			if(!from.contains("@limit.es")){
				List mailControladores = usuariDao.getEmailsByGrup(rolDoControlador);
				if(mailControladores!= null){
					for(int i=0;i<mailControladores.size();i++){
						if(mailControladores.get(i)!= null){
							to += ", " + mailControladores.get(i);
						}				
					}				
				}
				List mailGestors = usuariDao.getEmailsByGrup(rolDoGestor);
				if(mailGestors!= null){
					for(int i=0;i<mailGestors.size();i++){
						if(mailGestors.get(i)!= null){
							to += ", " + mailGestors.get(i);
						}				
					}				
				}
			}	
			// Anadimos el from también para recibir el mail
			if (from != null && to != null) {
				to += ", " + from; 
			}
			
			ResourceBundle bundle = ResourceBundle.getBundle("messages",new java.util.Locale("ca"));
			String subject = bundle.getString("proces.moureDiposit.subjectMail")+" - "+trasllat.getId();
			String content = bundle.getString("proces.moureDiposit.textoMail");
			
			bundle = ResourceBundle.getBundle("messages",new java.util.Locale("ca"));
			String fileName =  bundle.getString("proces.moureDiposit.volantePdf")+trasllat.getId()+".pdf";
			
			//Creamos el pdf de solicitud
			ByteArrayOutputStream pdf = new ByteArrayOutputStream();
			GeneraPdfVolante generaPdfVolante = new GeneraPdfVolante(getMessageSourceAccessor(),
					tipusEstablimentTafona,
					tipusEstablimentEnvasadora,
					tipusEstablimentTafonaEnvasadora);

			solicitanteDao.setHibernateTemplate(getHibernateTemplate());
			String solicitanteEmisor = solicitanteDao.getNameByEstablimentId(establiment.getId());
			solicitanteDao.setHibernateTemplate(getHibernateTemplate());
			String solicitanteDest = solicitanteDao.getNameByEstablimentId(establimentDesti);
			solicitanteDao.setHibernateTemplate(getHibernateTemplate());
			String solicitanteEmiNif = solicitanteDao.getNifByEstablimentId(establiment.getId());
			solicitanteDao.setHibernateTemplate(getHibernateTemplate());
			String solicitanteDesNif = solicitanteDao.getNifByEstablimentId(establimentDesti);
			
			establimentDao.setHibernateTemplate(getHibernateTemplate());
			Establiment estDes = establimentDao.getById(establimentDesti); 
			
			Iterator ite = trasllat.getDiposits().iterator();
			double quantitatTotalDesti=0;
			String nomPartides = "";
			while (ite.hasNext()){
				if(!"".equals(nomPartides)){
					nomPartides += ", ";
				}
				Diposit di = (Diposit)ite.next();
				dipositDao.setHibernateTemplate(getHibernateTemplate());
				di=dipositDao.getById(di.getId());
				quantitatTotalDesti+=di.getVolumActual().doubleValue();
				nomPartides += di.getPartidaOli().getNom();
			}
			
			pdf.write(generaPdfVolante.generarPdfVolantCirculacio(trasllat,solicitanteEmisor,solicitanteDest, solicitanteEmiNif, solicitanteDesNif, establiment, estDes, quantitatTotalDesti,nomPartides));
			
						
			//enviar el email			
			ServeiCorreu correo =  new ServeiCorreu();
			correo.enviaCorreu(from, to, subject, content, pdf.toByteArray(),fileName, emailFrom);			
            tx.commit();
            
            
		} catch (Exception ex) {
			logger.error("EXCEPTION", ex);
			try {
				tx.rollback();
				throw new Exception(ex);
			} catch (HibernateException e) {
			}
		}
		
	
	}
	
	/**
	 * Añade todos los registros necesarios para el trasvase del aceite
	 * @param data
	 * @param dipositsOrigen
	 * @param dipositDesti
	 * @param litresExtrets
	 * @param litrosAfegits
	 * @param acidesaDesti
	 * @param establiment
	 * @param desqualificat
	 * @param pendentQualificar
	 * @throws Exception
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public void acceptarTrasllatOli(Date data, Diposit[] dipositsOrigen, Diposit dipositDesti, Double[] litresExtrets, Double litrosAfegits, Double acidesaDesti, 
			Establiment establimentOrigen, Establiment establimentDesti, String desqualificat, String pendentQualificar, String mescla, Long idTrasllat) throws Exception{
		
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Transaction tx = null;
		
		trasllatDao.setHibernateTemplate(getHibernateTemplate());
		Trasllat t = trasllatDao.getById(idTrasllat);

		try {	

			Boolean partidaDesqualificada = false;
			tx = session.beginTransaction();
			dipositDao.setHibernateTemplate(getHibernateTemplate());
			dipositDesti = dipositDao.getById(dipositDesti.getId());

			CategoriaOli catOli = null;
			CategoriaOli catOliDesqualificada = null;
			CategoriaOli catOliPendentQualificar = null;
//			CategoriaOli catOliOrigen = new CategoriaOli();
			
			categoriaOliDao.setHibernateTemplate(getHibernateTemplate());
			Iterator itCategoria = categoriaOliDao.findAll().iterator();
			while (itCategoria.hasNext()) {
				catOli = (CategoriaOli) itCategoria.next();
				if (catOli.getId().equals(new Integer(desqualificat))) catOliDesqualificada = catOli;
				if (catOli.getId().equals(new Integer(pendentQualificar))) catOliPendentQualificar = catOli;
			}
			
			PartidaOli partidaOli = null;
			
			categoriaOliDao.setHibernateTemplate(getHibernateTemplate());
			CategoriaOli categoriaPENDENT = categoriaOliDao.getById(Constants.CATEGORIA_PENDENT);
			
			//Conservem la partida del diposit i la posem a pendent (o no DO)
			if(dipositDesti.getPartidaOli() != null){
				partidaOliDao.setHibernateTemplate(getHibernateTemplate());
				partidaOli = partidaOliDao.getById(dipositDesti.getPartidaOli().getId());
				
				if(!partidaOli.getCategoriaOli().getId().equals(Constants.CATEGORIA_NO_DO)){
					partidaOli.setCategoriaOli(categoriaPENDENT);
				}
				
				partidaOliDao.setHibernateTemplate(getHibernateTemplate());
				partidaOliDao.makePersistent(partidaOli);
				
			} else { //Si no te partida la creem
				//Calculam la partida resultant
				CategoriaOli catResultant = null;
				CategoriaOli catAux = null;
				
				//Comprovam si es de NO DO
				for(int i=0; i<dipositsOrigen.length; i++){
					Diposit d = dipositDao.getById(dipositsOrigen[i].getId());
					
					if(d.getPartidaOli().getCategoriaOli().getId().equals(Constants.CATEGORIA_NO_DO)){
						catResultant = categoriaOliDao.getById(Constants.CATEGORIA_NO_DO);
						break;
					}
				}
				
				//Si no hi ha cap partida de no do miram si conservam la partida o la posem pendent
				if(catResultant == null){
					for(int i=0; i<dipositsOrigen.length; i++){
						Diposit d = dipositDao.getById(dipositsOrigen[i].getId());
						
						if(catAux == null){
							catAux = categoriaOliDao.getById(d.getPartidaOli().getCategoriaOli().getId());
							catResultant = catAux;
						} else {
							if(catAux.getId().equals(d.getPartidaOli().getCategoriaOli().getId())){
								catResultant = catAux;
							} else {
								catResultant = categoriaOliDao.getById(Constants.CATEGORIA_PENDENT);
								break;
							}
						}
					}
				}
				
				//Nova Partida d'oli
				partidaOli = new PartidaOli();
				partidaOli.setDataCreacio(new Date());
				partidaOli.setEsActiu(true);
				partidaOli.setEstabliment(establimentDesti);
				partidaOli.setNom("(" + establimentOrigen.getNom() + ") " + dipositDesti.getCodiAssignat());
				
				/* TODO: RESOLDRE PROPIETARI */
				partidaOli.setOlivicultorEsPropietari(true);
				
				partidaOli.setCategoriaOli(catResultant);
				partidaOli.setCategoriaOliOriginal(catResultant);
				
				partidaOliDao.setHibernateTemplate(getHibernateTemplate());
				partidaOliDao.makePersistent(partidaOli);
			}
			
			
			
			ArrayList trazasPadre = new ArrayList();
			// Generamos las salidas de los depositos
			if (dipositsOrigen.length > 0) { 
				for (int i = 0; i < dipositsOrigen.length; i++) {
					dipositDao.setHibernateTemplate(getHibernateTemplate());
					Diposit diposit = dipositDao.getById(dipositsOrigen[i].getId());
					Double litros = litresExtrets[i];
					entradaDipositDao.setHibernateTemplate(getHibernateTemplate());
					EntradaDiposit ultimaEntrada = entradaDipositDao.findUltimaByDeposito(diposit.getId(), new Boolean(true));
						
					SortidaDiposit sortida = null;
					sortida = new SortidaDiposit();
					sortida.setAcidesa(ultimaEntrada.getAcidesa());
					sortida.setPartidaOli(diposit.getPartidaOli());
					sortida.setCategoriaOli(diposit.getPartidaOli().getCategoriaOli());
					sortida.setData(data);
					if (dipositDesti.getVolumActual() != null && dipositDesti.getVolumActual().doubleValue() != 0){
						sortida.setDesti(mescla);
						sortida.setObservacions(dipositDesti.getCodiAssignat());
					}else{
						sortida.setDesti(dipositDesti.getCodiAssignat());
					}
					
					sortida.setDipositBySdiCoddde(dipositDesti);
					sortida.setDipositBySdiCoddor(ultimaEntrada.getDiposit());
//					sortida.setEstabliment(ultimaEntrada.getEstabliment());
					sortida.setEstabliment(establimentOrigen);
					sortida.setLitres(litros);
					sortida.setTraza(this.insertarTraza(Constants.CODI_TRAZA_TIPUS_SORTIDA_DIPOSIT, session));
					
					//18-12-2009 La traza padre de esta salida puede ser tambien una salida (Perdida)no solo una entrada
					// sortida.getTraza().getTrazasForTtrCodtrapare().add(ultimaEntrada.getTraza());
					sortidaDipositDao.setHibernateTemplate(getHibernateTemplate());
					SortidaDiposit ultimaSortida = sortidaDipositDao.findUltimaByDeposito(diposit.getId(), new Boolean(true));
					if( ultimaSortida!= null && ultimaSortida.getTraza().getId().longValue() > ultimaEntrada.getTraza().getId().longValue()){
						sortida.getTraza().getTrazasForTtrCodtrapare().add(ultimaSortida.getTraza());
					}else{
						sortida.getTraza().getTrazasForTtrCodtrapare().add(ultimaEntrada.getTraza());
					}
					
					//Enllaçam la sortida amb el trasllat.
					sortida.getTraza().getTrazasForTtrCodtrapare().add(t.getTraza());
					
					trazasPadre.add(sortida.getTraza());
					sortidaDipositDao.makePersistent(sortida, session);

					
					diposit.setVolumActual(new Double((diposit.getVolumActual() != null?diposit.getVolumActual().doubleValue():new Double(0).doubleValue()) - litros.doubleValue()));
					
					//Partida
					//---------------------------
					if (diposit.getVolumActual() == null || diposit.getVolumActual().doubleValue() == 0) {
						diposit.setAcidesa(new Double(0));
//						diposit.setCategoriaOli(null);
						diposit.setPartidaOli(null);
						
//						//Deixem el volum de trasllat a null
						diposit.setVolumTrasllat(null);
					}
					//===========================
					this.dipositModificar(diposit, session);
				}
			}
			
			// Generamos la entrada del deposito con la suma de todos
			Double litrosTotals = new Double(litrosAfegits.doubleValue() + (dipositDesti.getVolumActual()!=null?dipositDesti.getVolumActual().doubleValue():new Double(0).doubleValue()));
			EntradaDiposit entrada = null; //this.entradaDiposit(null, dipositDesti, establiment, litrosTotals, false, session);
			entrada = new EntradaDiposit();
			entrada.setAcidesa(acidesaDesti);

			dipositDesti.setPartidaOli(partidaOli);
			entrada.setPartidaOli(partidaOli);
			entrada.setCategoriaOli(partidaOli.getCategoriaOli());
			//======================
			entrada.setData(data);
			entrada.setDiposit(dipositDesti);
			entrada.setEstabliment(establimentDesti);
			entrada.setLitres(litrosTotals);
			entrada.setTraza(this.insertarTraza(Constants.CODI_TRAZA_TIPUS_ENTRADA_DIPOSIT, session));
			for (int i = 0; i < trazasPadre.size(); i++) {
				entrada.getTraza().getTrazasForTtrCodtrapare().add((Traza)trazasPadre.get(i));
			}
			
			//Enllaçam la sortida amb el trasllat.
			entrada.getTraza().getTrazasForTtrCodtrapare().add(t.getTraza());
			
			entradaDipositDao.makePersistent(entrada, session);

			dipositDesti.setVolumActual(litrosTotals);
//			dipositDesti.setCategoriaOli(entrada.getCategoriaOli());
			dipositDesti.setPartidaOli(entrada.getPartidaOli());
			dipositDesti.setAcidesa(acidesaDesti);

			this.dipositModificar(dipositDesti, session);

			//Actualitzem dades del trasllat
			t.setDataAcceptarEnviament(data);
			t.setQuantitatEnviament(litrosAfegits);
			
			//Finalment posem el trasllat com a acceptat
			t.setAcceptatTrasllat(true);
			t.setRetornatEstablimentOrigen(true);
			trasllatDao.setHibernateTemplate(getHibernateTemplate());
			trasllatDao.makePersistent(t);
			
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
	 * Añade todos los registros necesarios para la creacion de aceite
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition -->
	 */
	public void etiquetar(Lot lot, Long[] idEtiquetes, Integer[] etiquetesInicials, Integer[] etiquetesFinals) throws Exception {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			
			for(int i=0; i<idEtiquetes.length; i++){
				if(etiquetesInicials[i] != null && !etiquetesInicials[i].equals("") && 
				   etiquetesFinals[i] != null && !etiquetesFinals[i].equals("")){
					etiquetesLotDao.setHibernateTemplate(getHibernateTemplate());
					EtiquetesLot et = etiquetesLotDao.getById((Long)idEtiquetes[i]);
					dividirEtiquetes(lot, et, etiquetesInicials[i], etiquetesFinals[i]);
					
				
					entradaLotDao.setHibernateTemplate(getHibernateTemplate());
					Iterator itEntradaLot = entradaLotDao.findByLotZona(lot.getId(), lot.getZona().getId(), new Boolean(true)).iterator();
					if (itEntradaLot.hasNext()) {
						EntradaLot entradaLot = (EntradaLot) itEntradaLot.next();
						
						String contraetiquetes = "";
						if(entradaLot.getNumerosContraetiquetes() != null && !entradaLot.getNumerosContraetiquetes().equals("")){
							contraetiquetes += entradaLot.getNumerosContraetiquetes() + ", " + String.valueOf(etiquetesInicials[i]) + " - " + String.valueOf(etiquetesFinals[i]);
						} else {
							contraetiquetes += String.valueOf(etiquetesInicials[i]) + " - " + String.valueOf(etiquetesFinals[i]);
						}
						
						entradaLot.setNumerosContraetiquetes(contraetiquetes);
						
						entradaLotDao.setHibernateTemplate(getHibernateTemplate());
						entradaLotDao.makePersistent(entradaLot, session);
					}
				}
			}
			
			
			tx.commit();
			
		} catch (Exception ex) {
			logger.error("EXCEPTION", ex);
			try {
				tx.rollback();
				throw new Exception(ex);
			} catch (HibernateException e) {}
		}
	}
	
	private void dividirEtiquetes(Lot lot, EtiquetesLot etiquetesLot, Integer etiquetaInicial, Integer etiquetaFinal){
		//CAS: etiqueta inici i fi igual
		if(etiquetesLot.getEtiquetaInici().equals(etiquetaInicial) && 
		   etiquetesLot.getEtiquetaFi().equals(etiquetaFinal)){
			//Actualitzam les etiquetes
			etiquetesLot.setLot(lot);
			etiquetesLot.setUtilitzat(true);
			etiquetesLotDao.makePersistent(etiquetesLot);
		}
		
		//CAS: etiLotInici < etiquetaInici i etiqueta fi igual
		if(etiquetesLot.getEtiquetaInici() < etiquetaInicial && 
			etiquetesLot.getEtiquetaFi().equals(etiquetaFinal)){
			
			//Subrang 1
			Integer etiquetaFiel1 = etiquetaInicial - 1;
			
			EtiquetesLot el1 = new EtiquetesLot(
					etiquetesLot.getEtiquetaLletra(),
					etiquetesLot.getEtiquetaInici(),
					etiquetaFiel1,
					etiquetesLot.getCapacitat(),
					false,
					false,
					etiquetesLot.getOlivaTaula(),
					etiquetesLot.getEstabliment(),
					etiquetesLot.getEsEcologic());
			
			el1.setRangOriginal(etiquetesLot);
			
			etiquetesLotDao.makePersistent(el1);
			
			//Subrang2
			EtiquetesLot el2 = new EtiquetesLot(
					etiquetesLot.getEtiquetaLletra(),
					etiquetaInicial,
					etiquetesLot.getEtiquetaFi(),
					etiquetesLot.getCapacitat(),
					true,
					false,
					etiquetesLot.getOlivaTaula(),
					etiquetesLot.getEstabliment(),
					etiquetesLot.getEsEcologic());
			
			el2.setRangOriginal(etiquetesLot);
			el2.setLot(lot);
			
			etiquetesLotDao.makePersistent(el2);
		}
		
		//CAS: etiqueta inicial igual i etiLotFinal > etiquetaFi
		if(etiquetesLot.getEtiquetaFi() > etiquetaFinal && 
		   etiquetesLot.getEtiquetaInici().equals(etiquetaInicial)){
			
			//Subrang 1
			EtiquetesLot el1 = new EtiquetesLot(
					etiquetesLot.getEtiquetaLletra(),
					etiquetesLot.getEtiquetaInici(),
					etiquetaFinal,
					etiquetesLot.getCapacitat(),
					true,
					false,
					etiquetesLot.getOlivaTaula(),
					etiquetesLot.getEstabliment(),
					etiquetesLot.getEsEcologic());
			
			el1.setRangOriginal(etiquetesLot);
			el1.setLot(lot);
			
			etiquetesLotDao.makePersistent(el1);
			
			//Subrang 2
			Integer etiquetaIniciel2 = etiquetaFinal + 1;
			
			EtiquetesLot el2 = new EtiquetesLot(
					etiquetesLot.getEtiquetaLletra(),
					etiquetaIniciel2,
					etiquetesLot.getEtiquetaFi(),
					etiquetesLot.getCapacitat(),
					false,
					false,
					etiquetesLot.getOlivaTaula(),
					etiquetesLot.getEstabliment(),
					etiquetesLot.getEsEcologic());
			
			el2.setRangOriginal(etiquetesLot);
			
			etiquetesLotDao.makePersistent(el2);
		}
		
		//CAS: etiLotInici < etiquetaInici i etiLotFinal > etiquetaFi
		if(etiquetesLot.getEtiquetaInici() < etiquetaInicial && 
		   etiquetesLot.getEtiquetaFi() > etiquetaFinal){
			//Subrang 1
			Integer etiquetaFiel1 = etiquetaInicial - 1;
			Integer etiquetaIniciel2 = etiquetaFinal + 1;
			
			EtiquetesLot el1 = new EtiquetesLot(
					etiquetesLot.getEtiquetaLletra(),
					etiquetesLot.getEtiquetaInici(),
					etiquetaFiel1,
					etiquetesLot.getCapacitat(),
					false,
					false,
					etiquetesLot.getOlivaTaula(),
					etiquetesLot.getEstabliment(),
					etiquetesLot.getEsEcologic());
			
			el1.setRangOriginal(etiquetesLot);
			
			etiquetesLotDao.makePersistent(el1);
			
			//Subrang 2
			EtiquetesLot el2 = new EtiquetesLot(
					etiquetesLot.getEtiquetaLletra(),
					etiquetaInicial,
					etiquetaFinal,
					etiquetesLot.getCapacitat(),
					true,
					false,
					etiquetesLot.getOlivaTaula(),
					etiquetesLot.getEstabliment(),
					etiquetesLot.getEsEcologic());
			
			el2.setRangOriginal(etiquetesLot);
			el2.setLot(lot);
			
			etiquetesLotDao.makePersistent(el2);
			
			//Subrang 3
			EtiquetesLot el3 = new EtiquetesLot(
					etiquetesLot.getEtiquetaLletra(),
					etiquetaIniciel2,
					etiquetesLot.getEtiquetaFi(),
					etiquetesLot.getCapacitat(),
					false,
					false,
					etiquetesLot.getOlivaTaula(),
					etiquetesLot.getEstabliment(),
					etiquetesLot.getEsEcologic());
			
			el3.setRangOriginal(etiquetesLot);
			
			etiquetesLotDao.makePersistent(el3);
		}
	}
	
	/**
	 * Cerca tots els establiments activos <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection findRestoEstablimentsActivosZonaFicticia(Long campanyaId, Long establimentId) {
		establimentDao.setHibernateTemplate(getHibernateTemplate());
		return establimentDao.findRestoEstablimentsActivosZonaFicticia(campanyaId,establimentId);
	}
	
	
	/**
	 * Marca como aceptado un traslado de deposito <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public void aceptarTraslado(Long trasllatDipositId, Date data) {
		trasllatDao.setHibernateTemplate(getHibernateTemplate());
		trasllatDao.aceptarTraslado(trasllatDipositId);
		
//		// -----------------------------------------------------------------------------------------
//		// Per a actualitzar tots els trasllats....
//		// -----------------------------------------------------------------------------------------
//		for (Iterator it = trasllatDao.findAll().iterator(); it.hasNext();){
//			Trasllat t = (Trasllat)it.next();
//			if(t!= null){
//				Traza trazaTrasllat = t.getTraza();
//				if (t.getEsDiposit()){ // Trasllat de dipòsit
//					sortidaDipositDao.setHibernateTemplate(getHibernateTemplate());
//					Iterator itt = trazaTrasllat.getTrazasForTtrCodtrapare().iterator();
//					while ( itt.hasNext()){
//						Traza traza = (Traza)itt.next();
//						if (traza.getTipus().intValue() == Constants.CODI_TRAZA_TIPUS_SORTIDA_DIPOSIT){
//							SortidaDiposit sdi = sortidaDipositDao.findByTraza(traza.getId(), true);
//							if (sdi.getEstabliment().equals(t.getEstablimentByTdiCodeor())){
//								t.setDataAcceptarEnviament(sdi.getData());
//								t.setQuantitatEnviament(sdi.getLitres());
//							} else {
//								t.setDataAcceptarRetorn(sdi.getData());
//								t.setQuantitatRetorn(sdi.getLitres());
//							}
//							break;
//						}
//					}
//				} else { // Trasllat d'oli
//					entradaDipositDao.setHibernateTemplate(getHibernateTemplate());					
//					Iterator itt = trazaTrasllat.getTrazasForTtrCodtrafill().iterator();
//					while ( itt.hasNext()){
//						Traza traza = (Traza)itt.next();
//						if (traza.getTipus().intValue() == Constants.CODI_TRAZA_TIPUS_ENTRADA_DIPOSIT){
//							EntradaDiposit edi = entradaDipositDao.findByTraza(traza.getId(), true);
//							t.setDataAcceptarEnviament(edi.getData());
//							t.setQuantitatEnviament(edi.getLitres());
//						}
//					}
//				}
//			}
//		}
//		// -----------------------------------------------------------------------------------------
		
		
		Trasllat tdi = trasllatDao.getById(trasllatDipositId);
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		
		Double quantitatEnviament = 0.0;
		Date dataEnviament = data;
		if(tdi!= null){
			//ZONA FICTICIA DEL ESTABLECIMIENTO DESTINO
			zonaDao.setHibernateTemplate(getHibernateTemplate());
			List zonesFicticies = zonaDao.findFicticiaByEstabliment(tdi.getEstablimentByTdiCodede().getId());
			Zona zonaDefecteRetorn = zonaDao.findZonaRetornByEstabliment(tdi.getEstablimentByTdiCodede().getId());
			
			Zona zonaDefecto = null;
			if(zonaDefecteRetorn!= null){
				zonaDefecto = zonaDefecteRetorn;
			} else if(zonesFicticies != null){
				zonaDefecto = (Zona)zonesFicticies.get(0);
			}
			for(Iterator it = tdi.getDiposits().iterator(); it.hasNext();){
				//PARA CADA DEPOSITO
				Diposit diposit = (Diposit)it.next();																					
				//1.ACTUALIZAMOS TABLA SALIDA DEPOSITO							
				entradaDipositDao.setHibernateTemplate(getHibernateTemplate());
				EntradaDiposit ultimaEntrada = entradaDipositDao.findUltimaByDeposito(diposit.getId(), new Boolean(true));
				
				if(diposit.getVolumActual() != null && diposit.getVolumActual() > 0){
					SortidaDiposit sortida = null;
					sortida = new SortidaDiposit();
					sortida.setAcidesa(diposit.getAcidesa());
					sortida.setPartidaOli(diposit.getPartidaOli());
					sortida.setCategoriaOli(diposit.getPartidaOli().getCategoriaOli());
					sortida.setData(data); //sortida.setData(tdi.getData());
					sortida.setDipositBySdiCoddor(diposit);
					sortida.setEstabliment(tdi.getEstablimentByTdiCodeor());
					sortida.setLitres(diposit.getVolumActual());
					sortida.setDesti("TRASLLAT "+tdi.getEstablimentByTdiCodede().getNom());
					sortida.setTraza(this.insertarTraza(Constants.CODI_TRAZA_TIPUS_SORTIDA_DIPOSIT, session));
					sortida.getTraza().getTrazasForTtrCodtrapare().add(ultimaEntrada.getTraza());
					sortidaDipositDao.makePersistent(sortida, session);
					tdi.getTraza().getTrazasForTtrCodtrapare().add(sortida.getTraza()); 
				}
				//2.ELIMINAMOS EL DEPOSITO DEL ESTABLECIMIENTO ORIGEN y ASIGNAMOS EL DEPOSITO AL ESTABLECIMIENTO DESTINO
				
				/* Joan - Si el dipòsit té contingut, cream la partida i l'assignam al dipòsit */
				//Partida
						
				if(diposit.getVolumActual() != null && diposit.getVolumActual() > 0 && diposit.getPartidaOli() != null){
					/* Joan - Cream partida nova */
					partidaOliDao.setHibernateTemplate(getHibernateTemplate());
					PartidaOli partidaOrigen = partidaOliDao.getById(diposit.getPartidaOli().getId());
					
					PartidaOli partida = partidaOliDao.getByNomAndEstabliment(
							"(" + tdi.getEstablimentByTdiCodeor().getNom() + ") " + partidaOrigen.getNom(), 
							tdi.getEstablimentByTdiCodede().getId());
					if (partida == null) {
						partida = new PartidaOli();
						partida.setDataCreacio(new Date());
						partida.setEsActiu(true);
						partida.setEstabliment(tdi.getEstablimentByTdiCodede());
						partida.setNom("(" + tdi.getEstablimentByTdiCodeor().getNom() + ") " + partidaOrigen.getNom());
					
						partida.setCategoriaOli(partidaOrigen.getCategoriaOli());
						partida.setCategoriaOliOriginal(partidaOrigen.getCategoriaOli());
						partida.setEsVisualitza(new Boolean(true));
						partida.setOlivicultorEsPropietari(new Boolean(true));
						
						partidaOliDao.setHibernateTemplate(getHibernateTemplate());
						partidaOliDao.makePersistent(partida);
					
						//Assignació de la partida creada
						diposit.setPartidaOli(partida);
					} else if (partidaOliDao.teAnalitica(partida)){
						partida = new PartidaOli();
						partida.setDataCreacio(new Date());
						partida.setEsActiu(true);
						partida.setEstabliment(tdi.getEstablimentByTdiCodede());
						partida.setNom("(" + tdi.getEstablimentByTdiCodeor().getNom() + ") " + partidaOrigen.getNom() + "_" + new Date().getTime());
					
						partida.setCategoriaOli(partidaOrigen.getCategoriaOli());
						partida.setCategoriaOliOriginal(partidaOrigen.getCategoriaOli());
						partida.setEsVisualitza(new Boolean(true));
						partida.setOlivicultorEsPropietari(new Boolean(true));
						
						partidaOliDao.setHibernateTemplate(getHibernateTemplate());
						partidaOliDao.makePersistent(partida);
					
						//Assignació de la partida creada
						diposit.setPartidaOli(partida);
					}
				} else {
					//El diòsit es buit, no té partida d'oli
					diposit.setPartidaOli(null);
				}
					
				/* Joan - Canviam nom deposit */
				diposit.setCodiOriginal(diposit.getCodiAssignat());
				diposit.setCodiAssignat("(" + diposit.getEstabliment().getNom() + ") " + diposit.getCodiAssignat());
				
				dipositDao.setHibernateTemplate(getHibernateTemplate());
				dipositDao.makePersistent(diposit, session);
				
				diposit.setEstabliment(tdi.getEstablimentByTdiCodede());
				diposit.setZonaOrigenTrasllat(diposit.getZona());
				diposit.setZona(zonaDefecto);
				diposit.setActiu(Boolean.valueOf(true));	
				diposit.setPosicioX(0);
				diposit.setPosicioY(0);
				
				dipositDao.setHibernateTemplate(getHibernateTemplate());
				dipositDao.makePersistent(diposit, session);
				
				
				//3.ACTUALIZAMOS TABLA ENTRADA DEPOSITO
				if(diposit.getVolumActual() != null && diposit.getVolumActual() > 0){
					EntradaDiposit entrada = new EntradaDiposit();
					entrada.setAcidesa(diposit.getAcidesa());
					entrada.setPartidaOli(diposit.getPartidaOli());
					entrada.setCategoriaOli(diposit.getPartidaOli().getCategoriaOli());
					entrada.setData(data); //entrada.setData(tdi.getData());
					entrada.setDiposit(diposit);
					entrada.setEstabliment(tdi.getEstablimentByTdiCodede());
					entrada.setLitres(diposit.getVolumActual());
					entrada.setTraza(this.insertarTraza(Constants.CODI_TRAZA_TIPUS_ENTRADA_DIPOSIT, session));
					entrada.getTraza().getTrazasForTtrCodtrapare().add(tdi.getTraza());								
					entradaDipositDao.makePersistent(entrada, session);		
				}
				
				//4. ACTUALITZEM QUANTITAT ENVIAMENT
				if(diposit.getVolumActual() != null && diposit.getVolumActual() > 0){
					quantitatEnviament += diposit.getVolumActual();
				}
			}

			//ACTUALITZEM QUANTITAT ENVIAMENT I DATA ENVIAMENT AL TRASLLAT
			tdi.setQuantitatEnviament(quantitatEnviament);
			tdi.setDataAcceptarEnviament(dataEnviament);
			
			trasllatDao.setHibernateTemplate(getHibernateTemplate());
			trasllatDao.makePersistent(tdi);
			
		}
	}
	

	/**
	 * Marca como rechazado un traslado de deposito <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public void rechazarTraslado(Long trasllatDippositId)throws Exception {
		
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			trasllatDao.setHibernateTemplate(getHibernateTemplate());
			Trasllat tdi = trasllatDao.getById(trasllatDippositId);
			if(tdi!= null){
				// Activamos los depositos
				for(Iterator it = tdi.getDiposits().iterator(); it.hasNext();){
					Diposit diposit = (Diposit)it.next();
					diposit.setActiu(Boolean.valueOf(true));
					dipositDao.setHibernateTemplate(getHibernateTemplate());
					dipositDao.makePersistent(diposit);	
				}
				trasllatDao.setHibernateTemplate(getHibernateTemplate());
				trasllatDao.rechazarTraslado(trasllatDippositId);
			}
		} catch (Exception ex) {
			logger.error("EXCEPTION", ex);
			try {
				tx.rollback();
				throw new Exception(ex);
			} catch (HibernateException e) {}
		}
	}
	
	/**
	 * Devuelve los depositos del traslado a su establecimiento de origen <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public void devolverTraslado(Long trasllatDippositId, String rolDoControlador, String rolDoGestor, String emailFrom)throws Exception {
		
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			trasllatDao.setHibernateTemplate(getHibernateTemplate());
			Trasllat tdi = trasllatDao.getById(trasllatDippositId);
			
			if(tdi!= null){
				//ZONA FICTICIA DEL ESTABLECIMIENTO DESTINO
				zonaDao.setHibernateTemplate(getHibernateTemplate());
				List zonas = zonaDao.findFicticiaByEstabliment(tdi.getEstablimentByTdiCodeor().getId());
				Zona zonaDefecto = null;
				if(zonas!= null){
					zonaDefecto = (Zona)zonas.get(0);
				}
				
				//ENVIO DE MAIL: Obtenim les dades comuns
				Establiment establiment = tdi.getEstablimentByTdiCodede();
				String from = establiment.getEmail();
				
				establimentDao.setHibernateTemplate(getHibernateTemplate());
				String to = establimentDao.getEmail(tdi.getEstablimentByTdiCodeor().getId());			
				usuariDao.setHibernateTemplate(getHibernateTemplate());
				if(!from.contains("@limit.es")){
					List mailControladores = usuariDao.getEmailsByGrup(rolDoControlador);
					if(mailControladores!= null){
						for(int i1=0;i1<mailControladores.size();i1++){
							if(mailControladores.get(i1)!= null){
								to += ", " + mailControladores.get(i1);
							}				
						}				
					}
					List mailGestors = usuariDao.getEmailsByGrup(rolDoGestor);
					if(mailGestors!= null){
						for(int i1=0;i1<mailGestors.size();i1++){
							if(mailGestors.get(i1)!= null){
								to += ", " + mailGestors.get(i1);
							}				
						}				
					}
				}	 
				// Anadimos el from también para recibir el mail
				if (from != null && to != null) {
					to += ", " + from; 
				}

				ResourceBundle bundle = ResourceBundle.getBundle("messages",new java.util.Locale("ca"));
				String preSubject = bundle.getString("proces.moureDiposit.subjectMail");
				String content = bundle.getString("proces.moureDiposit.textoMail");
				String preFileName =  bundle.getString("proces.moureDiposit.volantePdf");
				
				GeneraPdfVolante generaPdfVolante = new GeneraPdfVolante(getMessageSourceAccessor(),
						tipusEstablimentTafona,
						tipusEstablimentEnvasadora,
						tipusEstablimentTafonaEnvasadora);

				solicitanteDao.setHibernateTemplate(getHibernateTemplate());
				String solicitanteEmisor = solicitanteDao.getNameByEstablimentId(establiment.getId());
				solicitanteDao.setHibernateTemplate(getHibernateTemplate());
				String solicitanteDest = solicitanteDao.getNameByEstablimentId(tdi.getEstablimentByTdiCodeor().getId());
				solicitanteDao.setHibernateTemplate(getHibernateTemplate());
				String solicitanteEmiNif = solicitanteDao.getNifByEstablimentId(establiment.getId());
				solicitanteDao.setHibernateTemplate(getHibernateTemplate());
				String solicitanteDesNif = solicitanteDao.getNifByEstablimentId(tdi.getEstablimentByTdiCodeor().getId());
				
				tdi.setQuantitatRetorn(0.0);
				for(Iterator it = tdi.getDiposits().iterator(); it.hasNext();){
					Diposit dip = (Diposit)it.next();
					
					if (dip.getVolumActual() != null && dip.getVolumActual().doubleValue() > 0.0) {

						tdi.setQuantitatRetorn(tdi.getQuantitatRetorn() + dip.getVolumActual());
						
						//ENVIO DE MAIL: Obtenim les dades particulars
						String subject = preSubject + " - " + tdi.getId();
						String fileName =  preFileName + tdi.getId() + ".pdf";
						
						//Creamos el pdf de solicitud
						ByteArrayOutputStream pdf = new ByteArrayOutputStream();
						
						pdf.write(generaPdfVolante.generarPdfVolantCirculacio(tdi, solicitanteEmisor, solicitanteDest, solicitanteEmiNif, solicitanteDesNif, establiment, tdi.getEstablimentByTdiCodeor(), dip.getVolumActual().doubleValue(), dip.getPartidaOli().getNom()));
						
						//enviar el email			
						ServeiCorreu correo =  new ServeiCorreu();
						correo.enviaCorreu(from, to, subject, content, pdf.toByteArray(), fileName, emailFrom);
						
					}
				}
				
				
				//Actualizamos el campo RetornatEstablimentOrigen a true
				tdi.setRetornatEstablimentOrigen(Boolean.valueOf(true));
				tdi.setAcceptatTrasllat(Boolean.valueOf(false));
				tdi.setTraslladant(Boolean.valueOf(true));
				trasllatDao.setHibernateTemplate(getHibernateTemplate());
				trasllatDao.makePersistent(tdi);
			}			
			tx.commit();
			
		} catch (Exception ex) {
			logger.error("EXCEPTION", ex);
			try {
				tx.rollback();
				throw new Exception(ex);
			} catch (HibernateException e) {}
		}
	}
	
	/**
	 * Acceptació dels depòsits retornats <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public void acceptarDevolverTraslado(Long trasllatDippositId, String retorno, Date data)throws Exception {
		
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			trasllatDao.setHibernateTemplate(getHibernateTemplate());
			Trasllat tdi = trasllatDao.getById(trasllatDippositId);
			
			Double quantitatRetorn = 0.0;
			Date dataRetorn = data;
			
			if(tdi!= null){
				//ZONA FICTICIA DEL ESTABLECIMIENTO DESTINO
				zonaDao.setHibernateTemplate(getHibernateTemplate());
				List zonesFicticies = zonaDao.findFicticiaByEstabliment(tdi.getEstablimentByTdiCodeor().getId());
				Zona zonaDefecteRetorn = zonaDao.findZonaRetornByEstabliment(tdi.getEstablimentByTdiCodeor().getId());
				
				for(Iterator it = tdi.getDiposits().iterator(); it.hasNext();){
					//PARA CADA DEPOSITO
					Diposit diposit = (Diposit)it.next();		
					
					
					Zona zonaDefecto = null;
					if(zonaDefecteRetorn!= null){
						zonaDefecto = zonaDefecteRetorn;
					} else if(diposit.getZonaOrigenTrasllat() != null){
						zonaDefecto = diposit.getZonaOrigenTrasllat();
					} else if(zonesFicticies != null){
						zonaDefecto = (Zona)zonesFicticies.get(0);
					}
					
					//1.ACTUALIZAMOS TABLA SALIDA DEPOSITO		
					entradaDipositDao.setHibernateTemplate(getHibernateTemplate());
					EntradaDiposit entrada = entradaDipositDao.findUltimaByDeposito(diposit.getId(), new Boolean(true));
					SortidaDiposit sortida = null;
					if(entrada != null && diposit.getVolumActual() != null && diposit.getVolumActual() > 0){
//						if(entrada != null){
						sortida = new SortidaDiposit();
						sortida.setAcidesa(diposit.getAcidesa());
						sortida.setPartidaOli(diposit.getPartidaOli());
						sortida.setCategoriaOli(diposit.getPartidaOli().getCategoriaOli());
						sortida.setData(data); //sortida.setData(tdi.getData());
						sortida.setDipositBySdiCoddor(diposit);
						sortida.setEstabliment(tdi.getEstablimentByTdiCodede());
						sortida.setLitres(diposit.getVolumActual());
						sortida.setDesti(retorno+" "+tdi.getEstablimentByTdiCodeor().getNom());
						sortida.setTraza(this.insertarTraza(Constants.CODI_TRAZA_TIPUS_SORTIDA_DIPOSIT, session));
						sortida.getTraza().getTrazasForTtrCodtrapare().add(entrada.getTraza());
						sortidaDipositDao.makePersistent(sortida, session);
						tdi.getTraza().getTrazasForTtrCodtrapare().add(sortida.getTraza());
					}
					 
					//2.ELIMINAMOS EL DEPOSITO DEL ESTABLECIMIENTO Destino y ASIGNAMOS EL DEPOSITO AL ESTABLECIMIENTO origen
					/* Joan - Retornam el codi al seu estat original */
					//Codi
					diposit.setCodiAssignat(diposit.getCodiOriginal());
					
					//Partida
					if(diposit.getVolumActual() != null && diposit.getVolumActual() > 0 && diposit.getPartidaOli() != null){
						/* Cream partida nova */
						partidaOliDao.setHibernateTemplate(getHibernateTemplate());
						PartidaOli partidaOrigen = partidaOliDao.getById(diposit.getPartidaOli().getId());
							
						PartidaOli partida = partidaOliDao.getByNomAndEstabliment(
								"(" + tdi.getEstablimentByTdiCodede().getNom() + ") " + partidaOrigen.getNom(), 
								tdi.getEstablimentByTdiCodeor().getId());
						if (partida == null)	{
							partida = new PartidaOli();
							partida.setDataCreacio(new Date());
							partida.setEsActiu(true);
							partida.setEstabliment(tdi.getEstablimentByTdiCodeor());
							partida.setNom("(" + tdi.getEstablimentByTdiCodede().getNom() + ") " + partidaOrigen.getNom());
								
							partida.setCategoriaOli(partidaOrigen.getCategoriaOli());
							partida.setCategoriaOliOriginal(partidaOrigen.getCategoriaOli());
							partida.setEsVisualitza(new Boolean(true));
							partida.setOlivicultorEsPropietari(new Boolean(true));
								
							partidaOliDao.setHibernateTemplate(getHibernateTemplate());
							partidaOliDao.makePersistent(partida);
						} else if (partidaOliDao.teAnalitica(partida)){
							partida = new PartidaOli();
							partida.setDataCreacio(new Date());
							partida.setEsActiu(true);
							partida.setEstabliment(tdi.getEstablimentByTdiCodede());
							partida.setNom("(" + tdi.getEstablimentByTdiCodeor().getNom() + ") " + partidaOrigen.getNom() + "_" + new Date().getTime());
						
							partida.setCategoriaOli(partidaOrigen.getCategoriaOli());
							partida.setCategoriaOliOriginal(partidaOrigen.getCategoriaOli());
							partida.setEsVisualitza(new Boolean(true));
							partida.setOlivicultorEsPropietari(new Boolean(true));
							
							partidaOliDao.setHibernateTemplate(getHibernateTemplate());
							partidaOliDao.makePersistent(partida);
						
							//Assignació de la partida creada
							diposit.setPartidaOli(partida);
						}
							
						//Assignació de la partida creada
						diposit.setPartidaOli(partida);
					} else {
						//El diòsit es buit, no té partida d'oli
						diposit.setPartidaOli(null);
					}
//						PartidaOli partidaOriginal = null;
//						if(tdi.getIdPartida() != null){
//							partidaOliDao.setHibernateTemplate(getHibernateTemplate());
//							partidaOriginal = partidaOliDao.getById(tdi.getIdPartida());
//						}
//						diposit.setPartidaOli(partidaOriginal);
					diposit.setEstabliment(tdi.getEstablimentByTdiCodeor());
					diposit.setZona(zonaDefecto);
					diposit.setPosicioX(0);
					diposit.setPosicioY(0);
					dipositDao.setHibernateTemplate(getHibernateTemplate());
					dipositDao.makePersistent(diposit, session);

					
					//3.ACTUALIZAMOS TABLA ENTRADA DEPOSITO
					if(diposit.getVolumActual() != null && diposit.getVolumActual() > 0){
						entrada = new EntradaDiposit();
						entrada.setAcidesa(diposit.getAcidesa());
						entrada.setPartidaOli(diposit.getPartidaOli());
						entrada.setCategoriaOli(diposit.getPartidaOli().getCategoriaOli());
						entrada.setData(data); //entrada.setData(tdi.getData());
						entrada.setDiposit(diposit);
						entrada.setEstabliment(tdi.getEstablimentByTdiCodeor());
						entrada.setLitres(diposit.getVolumActual());
						entrada.setTraza(this.insertarTraza(Constants.CODI_TRAZA_TIPUS_ENTRADA_DIPOSIT, session));
						entrada.getTraza().getTrazasForTtrCodtrapare().add(tdi.getTraza());								
						entradaDipositDao.makePersistent(entrada, session);	
					}
					
					//4. ACTUALITZEM LA QUANTITAT  DE RETORN
					if(diposit.getVolumActual() != null && diposit.getVolumActual() > 0){
						quantitatRetorn += diposit.getVolumActual();
					}
				}
				
				//Actualitzam data retorn i quantitat retorn
				tdi.setQuantitatRetorn(quantitatRetorn);
				tdi.setDataAcceptarRetorn(dataRetorn);
				
				//Actualizamos el campo RetornatEstablimentOrigen a true
				tdi.setRetornatEstablimentOrigen(Boolean.valueOf(true));
				tdi.setAcceptatTrasllat(Boolean.valueOf(Boolean.valueOf(true)));
				tdi.setTraslladant(Boolean.valueOf(false));
				trasllatDao.setHibernateTemplate(getHibernateTemplate());
				trasllatDao.makePersistent(tdi);
			}			
			tx.commit();
			
		} catch (Exception ex) {
			logger.error("EXCEPTION", ex);
			try {
				tx.rollback();
				throw new Exception(ex);
			} catch (HibernateException e) {}
		}
	}
	
	
	
	/**
	 * Añade todos los registros necesarios para la creacion de aceite
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition -->
	 */
	public void sortidaEmbotelladora(SortidaLot sortidaLot, Zona zonaCanvi, String entrada, String venda, String canviZona) throws Exception {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			
			lotDao.setHibernateTemplate(getHibernateTemplate());
			Lot lot = lotDao.getById(sortidaLot.getLot().getId());
			
			Integer numBotRestants = null;
			if (sortidaLot.getAccioSortida().equals(venda)) {
				numBotRestants = new Integer(lot.getNumeroBotellesActuals().intValue() - sortidaLot.getVendaNumeroBotelles().intValue());
				lot.setNumeroBotellesActuals(numBotRestants);
				
				lotDao.setHibernateTemplate(getHibernateTemplate());
				lotDao.makePersistent(lot);
			}
			
			EntradaLot entradaPare = null;
			entradaLotDao.setHibernateTemplate(getHibernateTemplate());
			Iterator itEntradaLot = entradaLotDao.findByLotZona(lot.getId(), lot.getZona().getId(), new Boolean(true)).iterator();
			if (itEntradaLot.hasNext()) {
				entradaPare = (EntradaLot) itEntradaLot.next();
			}
			if(entradaPare != null){
				sortidaLot.setTraza(this.insertarTraza(Constants.CODI_TRAZA_TIPUS_SORTIDA_LOT, session));
				sortidaLot.getTraza().getTrazasForTtrCodtrapare().add(entradaPare.getTraza());
			}
			
			sortidaLotDao.setHibernateTemplate(getHibernateTemplate());
			sortidaLotDao.makePersistent(sortidaLot);
			
			if (sortidaLot.getAccioSortida().equals(canviZona)) {
				lot.setZona(zonaCanvi);
				lotDao.setHibernateTemplate(getHibernateTemplate());
				lotDao.makePersistent(lot);
				
				EntradaLot entradaLot = new EntradaLot();
				
				entradaLot.setAcidesa(lot.getAcidesa());
				entradaLot.setData(sortidaLot.getCanviZonaData());
				entradaLot.setDipositProcedencia(entrada);
				entradaLot.setLot(lot);
				entradaLot.setTraza(this.insertarTraza(Constants.CODI_TRAZA_TIPUS_ENTRADA_LOT, session));
				entradaLot.getTraza().getTrazasForTtrCodtrapare().add(sortidaLot.getTraza());
				entradaLot.setZona(zonaCanvi);
				entradaLot.setBotelles(lot.getNumeroBotellesActuals());
				
				entradaLotDao.setHibernateTemplate(getHibernateTemplate());
				entradaLotDao.makePersistent(entradaLot, session);
			}
			
			tx.commit();
			
		} catch (Exception ex) {
			logger.error("EXCEPTION", ex);
			try {
				tx.rollback();
				throw new Exception(ex);
			} catch (HibernateException e) {}
		}
	}
	
	
	
	/**
	 * Cerca tots els processos d'un establiment <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection processosFindAllByEstablimentAndData(Long establimentId, String perdua, String pendentQualificacio, int tipoUsu, String devolver, String tancamentLlibres,String entrada, String stringAnalitica, String desqualificat, Date dataInici, Date dataFi, boolean oliva) {
		
		List list = new ArrayList();
		Iterator it = null;
		Iterator itPartidaOliva = null;
		Iterator itPartidaFonoll = null;
		Iterator itEntrada = null;
		Iterator itSortida = null;
		Iterator itTrasllat = null;
		Iterator itEntradaLot = null;
		Iterator itAnaliticas = null;
		Iterator itAutocontrol = null;
		Iterator itSortidaPartida = null;
		Iterator itSortidaBotaGranel = null;
		
		
		/* tipoUsu:
		 * 1 = Evasador
		 * 2 = Tafona
		 * 3 = Tafona/Envasador
		 * 4 = Controlador
		 * 5 = Oliva taula
		 */
		
		if (oliva) { // Oliva taula
			System.out.println(">>> Entrada oliva");
			// 14 - Entrada oliva
			partidaOlivaDao.setHibernateTemplate(getHibernateTemplate());
			itPartidaOliva = partidaOlivaDao.getPartidasTaulaEntreDiasEnEstablecimiento(dataInici, dataFi, establimentId, new Boolean(true)).iterator(); 
			
			while (itPartidaOliva.hasNext()) {
				PartidaOliva pao = (PartidaOliva) itPartidaOliva.next();
				System.out.println("> Entrada oliva: " + pao.getId());
					EdicioProcessosAux eProcessos = new EdicioProcessosAux();
					eProcessos.setAccio("14");
					eProcessos.setData(pao.getData());
					eProcessos.setTraza(pao.getTraza());
					eProcessos.setValid(this.getValid(pao.getTraza()));
					eProcessos.setIdDipositLot(pao.getId());
					eProcessos.setTipusDipositLot(7);
					eProcessos.setObservacions("Olivicultor: " + pao.getOlivicultor().getNom() + " | Quantitat: " + pao.getTotalQuilosDesglosats());
					eProcessos.setOlivaTaula(true);
					list.add(eProcessos);
			}
			
			System.out.println(">>> Entrada fenoll");
			// 15 - Entrada fenoll
			partidaFonollDao.setHibernateTemplate(getHibernateTemplate());
			itPartidaFonoll = partidaFonollDao.getPartidasEntreDiasEnEstablecimiento(dataInici, dataFi, establimentId, new Boolean(true)).iterator(); 
			
			while (itPartidaFonoll.hasNext()) {
				PartidaFonoll paf = (PartidaFonoll) itPartidaFonoll.next();
				System.out.println("> Entrada fonoll: " + paf.getId());
				
					EdicioProcessosAux eProcessos = new EdicioProcessosAux();
					eProcessos.setAccio("15");
					eProcessos.setData(paf.getData());
					eProcessos.setTraza(paf.getTraza());
					eProcessos.setValid(this.getValid(paf.getTraza()));
//					eProcessos.setIdDipositLot(paf.getId());
//					eProcessos.setTipusDipositLot(8);
					eProcessos.setDipositLot(paf.getCodi());
					eProcessos.setObservacions("Titular: " + paf.getTitular() + " | Quantitat: " + paf.getKgInici());
					eProcessos.setOlivaTaula(true);
					list.add(eProcessos);
			}
			
			System.out.println(">>> Elaboracio oliva taula");
			// 16 - Elaboració oliva
			elaboracioOlivaDao.setHibernateTemplate(getHibernateTemplate());
			Iterator itElaboracio = elaboracioOlivaDao.getElaboracionesEntreDiasEnEstablecimiento(dataInici, dataFi, establimentId, true, new Boolean(true)).iterator();
			while (itElaboracio.hasNext()) {
				ElaboracioOliva elao = (ElaboracioOliva)itElaboracio.next();
				System.out.println("> Elaboracio oliva: " + elao.getId());
				String botes ="";
				
				if (elao != null) {					
					Bota bota = elao.getBota();
					String partides = "";
					Iterator itp = elao.getPartidaElaboracions().iterator();
					while (itp.hasNext()) {
						PartidaElaboracio par = (PartidaElaboracio) itp.next();
						partides += par.getPartida().getCodiAssignat() + ", "; 
					}
					if (partides.length() > 0)
						partides = partides.substring(0, partides.length() - 2);
										
					EdicioProcessosAux eProcessos = new EdicioProcessosAux();
					eProcessos.setAccio("16");
					eProcessos.setData(elao.getData());
					eProcessos.setTraza(elao.getTraza());
					eProcessos.setValid(this.getValid(elao.getTraza()));						
					eProcessos.setIdDipositLot(elao.getId());
					eProcessos.setTipusDipositLot(8); // 1--> traza per la Elaboracion d'oliva 
					eProcessos.setDipositLot(bota.getIdentificador()); 
					eProcessos.setObservacions("Partida: " + partides + " | Quantitat: " + bota.getKgOliva() + "kg." + " | Núm. elaboració: " + elao.getNumeroElaboracio() + " | " + elao.getObservacions());
					eProcessos.setOlivaTaula(true);
					list.add(eProcessos);
				}
			}
			
			System.out.println(">>> Envasat oliva taula");
			// 17 - Envasat oliva
			entradaLotDao.setHibernateTemplate(getHibernateTemplate());
			itEntradaLot = entradaLotDao.findByEstablecimientoEntreDates(establimentId, new Boolean(true), dataInici, dataFi).iterator();
						
			while (itEntradaLot.hasNext()) {
				EntradaLot elot = (EntradaLot) itEntradaLot.next();
				if (elot.getLot() != null && elot.getLot().getOlivaTaula() != null && elot.getLot().getOlivaTaula() == true){
					System.out.println("> Envasat. Entrada lot: " + elot.getId());
					if((elot.getEsDevolucio() == null || elot.getEsDevolucio().equals(false)) && 
						(elot.getDipositProcedencia()== null || (elot.getDipositProcedencia()!= null && !elot.getDipositProcedencia().trim().equals(tancamentLlibres) && !elot.getDipositProcedencia().trim().equals(entrada)))){
										
							EdicioProcessosAux eProcessos = new EdicioProcessosAux();
							eProcessos.setAccio("17");
							eProcessos.setData(elot.getData());
							eProcessos.setTraza(elot.getTraza());
							eProcessos.setValid(this.getValid(elot.getTraza()));
							eProcessos.setIdDipositLot(elot.getLot().getId());
							eProcessos.setTipusDipositLot(3);
							eProcessos.setDipositLot(elot.getLot().getCodiAssignat());
							eProcessos.setOlivaTaula(true);
							eProcessos.setObservacions("");
							list.add(eProcessos);						
					}
				}
			}
			
			System.out.println(">>> Etiqueta oliva taula");
			// 18 - Etiquetat oliva
			entradaLotDao.setHibernateTemplate(getHibernateTemplate());
			itEntradaLot = entradaLotDao.findByEstablecimientoNoDevolucioEntreDates(dataInici, dataFi, establimentId, new Boolean(true)).iterator();
			
			while (itEntradaLot.hasNext()) {
				EntradaLot elot = (EntradaLot) itEntradaLot.next();
				if (elot.getLot() != null && elot.getLot().getOlivaTaula() != null && elot.getLot().getOlivaTaula() == true){
					etiquetesLotDao.setHibernateTemplate(getHibernateTemplate());
					Iterator itEtiquetes = etiquetesLotDao.findEtiquetesLotByIdLotEntreDates(elot.getLot().getId(), dataInici, dataFi).iterator();
					while (itEtiquetes.hasNext()){		
						EtiquetesLot eti = (EtiquetesLot)itEtiquetes.next();
						System.out.println("> Etiquetat oliva. Etiqueta: " + eti.getId());
						EdicioProcessosAux eProcessos = new EdicioProcessosAux();
						eProcessos.setAccio("18");
						eProcessos.setAux(eti.toString());
						eProcessos.setData(elot.getData());
						eProcessos.setTraza(elot.getTraza());
						eProcessos.setValid(this.getValid(elot.getTraza()));
						eProcessos.setIdDipositLot(eti.getId()); // Utilitzam aquest camp per a identificat l'etiqueta
						eProcessos.setTipusDipositLot(3);
						eProcessos.setDipositLot(elot.getLot().getCodiAssignat());
						eProcessos.setObservacions("Etiquetes: " + eti.toString());
						eProcessos.setOlivaTaula(true);
						list.add(eProcessos);
					}
				}
			}
			
			System.out.println(">>> Autocontrol oliva taula");
			// 19 - Autocontrol oliva
			autocontrolOlivaDao.setHibernateTemplate(getHibernateTemplate());
			itAutocontrol = autocontrolOlivaDao.findByEstablimentEntreDates(dataInici, dataFi, establimentId, new Boolean(true)).iterator();

			while (itAutocontrol.hasNext()) {
				AutocontrolOliva aut = (AutocontrolOliva) itAutocontrol.next();	
				System.out.println("> Autocontrol: " + aut.getId());
				EdicioProcessosAux eProcessos = new EdicioProcessosAux();
				eProcessos.setAccio("19");
				eProcessos.setData(aut.getData());
				eProcessos.setTraza(aut.getTraza());
				Bota bota = botaDao.getById(aut.getBota());
				autocontrolOlivaDao.setHibernateTemplate(getHibernateTemplate());
				AutocontrolOliva autocontrol = autocontrolOlivaDao.findUltimaByLot(bota.getId());
				if(autocontrol != null && autocontrol.getTraza().getId().longValue() > aut.getTraza().getId().longValue() && !autocontrol.getId().equals(aut.getId())){
					eProcessos.setValid(false);
				}else{
					eProcessos.setValid(this.getValid(aut.getTraza()));
				}
				
				eProcessos.setIdDipositLot(bota.getId());
				eProcessos.setTipusDipositLot(3);
				
				eProcessos.setDipositLot(bota.getIdentificador());
				eProcessos.setEstabliment(aut.getEstabliment());
				eProcessos.setOlivaTaula(true);
				list.add(eProcessos);
				
			}
			
			System.out.println(">>> Sortida oliva taula");
			// 20 - Sortida oliva
			sortidaLotDao.setHibernateTemplate(getHibernateTemplate());
			Iterator itSortidaLot = sortidaLotDao.findOlivaTaulaByEstablecimientoEntreDates(establimentId, new Boolean(true), dataInici, dataFi).iterator();
			
			while (itSortidaLot.hasNext()) {
				SortidaLot slot = (SortidaLot) itSortidaLot.next();
				System.out.println("> Venta de lot: " + slot.getId());
				if(slot.getVendaData()!= null ){
					EdicioProcessosAux eProcessos = new EdicioProcessosAux();
					eProcessos.setAccio("20");
					eProcessos.setData(slot.getVendaData());
					eProcessos.setTraza(slot.getTraza());
					eProcessos.setValid(this.getValid(slot.getTraza()));
					eProcessos.setIdDipositLot(slot.getId());
					eProcessos.setTipusDipositLot(6);
					eProcessos.setDipositLot(slot.getLot().getCodiAssignat());
					
					eProcessos.setObservacions("Pots: " + slot.getVendaBotelles() +" | Quantitat: " + slot.getVendaLitres() + " kg" + " | Destinatari: " + slot.getVendaDestinatari()+ " | Doc: " + slot.getVendaNumeroDocument());
					eProcessos.setOlivaTaula(true);
					list.add(eProcessos);
				}				
			}
		}
		
		if (tipoUsu == 1 || tipoUsu == 3) { // Envasador
			
			System.out.println(">>> Etiqueta");
			// 9 - Etiqueta
			entradaLotDao.setHibernateTemplate(getHibernateTemplate());
			itEntradaLot = entradaLotDao.findByEstablecimientoNoDevolucioEntreDates(dataInici, dataFi, establimentId, new Boolean(true)).iterator();
			
			while (itEntradaLot.hasNext()) {
				EntradaLot elot = (EntradaLot) itEntradaLot.next();
				if (elot.getLot() != null && elot.getLot().getOlivaTaula() == null){
					etiquetesLotDao.setHibernateTemplate(getHibernateTemplate());
					Iterator itEtiquetes = etiquetesLotDao.findEtiquetesLotByIdLotEntreDates(elot.getLot().getId(), dataInici, dataFi).iterator();
					while (itEtiquetes.hasNext()){		
						EtiquetesLot eti = (EtiquetesLot)itEtiquetes.next();
						System.out.println("> Etiquetat. Etiqueta: " + eti.getId());
						EdicioProcessosAux eProcessos = new EdicioProcessosAux();
						eProcessos.setAccio("9");
						eProcessos.setAux(eti.toString());
						eProcessos.setData(elot.getData());
						eProcessos.setTraza(elot.getTraza());
						if(!this.getValid(elot.getTraza())){
							eProcessos.setValid(false);
						}else{
						//Comprobamos que no hay ninguna analitica del deposito de origen del embotellado posterior para poder borrarlo
							analiticaDao.setHibernateTemplate(getHibernateTemplate());
							Analitica analitica = analiticaDao.findUltimaByDeposito(elot.getLot().getDiposit().getId());
							if(analitica != null && analitica.getTraza().getId().longValue() > elot.getTraza().getId().longValue()){
								eProcessos.setValid(false);
							}else{
								eProcessos.setValid(true);
							}
						}
						// Falta identificar l'etiquetatge
						eProcessos.setIdDipositLot(eti.getId()); // Utilitzam aquest camp per a identificat l'etiqueta
						eProcessos.setTipusDipositLot(3);
						eProcessos.setDipositLot(elot.getLot().getCodiAssignat());
						eProcessos.setObservacions("Etiquetes: " + eti.toString());
						eProcessos.setOlivaTaula(false);
						list.add(eProcessos);
					}
				}
			}
			
			System.out.println(">>> Embotellar");
			// 8 - Embotellar
			entradaLotDao.setHibernateTemplate(getHibernateTemplate());
			itEntradaLot = entradaLotDao.findByEstablecimientoEntreDates(establimentId, new Boolean(true), dataInici, dataFi).iterator();
						
			while (itEntradaLot.hasNext()) {
				EntradaLot elot = (EntradaLot) itEntradaLot.next();
				if (elot.getLot() != null && elot.getLot().getOlivaTaula() == null){
					System.out.println("> Embotellat. Entrada lot: " + elot.getId());
					if((elot.getEsDevolucio() == null || elot.getEsDevolucio().equals(false)) && 
						(elot.getDipositProcedencia()== null || (elot.getDipositProcedencia()!= null && !elot.getDipositProcedencia().trim().equals(tancamentLlibres) && !elot.getDipositProcedencia().trim().equals(entrada)))){
										
							EdicioProcessosAux eProcessos = new EdicioProcessosAux();
							eProcessos.setAccio("8");
							eProcessos.setData(elot.getData());
							eProcessos.setTraza(elot.getTraza());
							//eProcessos.setValid(this.getValid(elot.getTraza()));
							if(!this.getValid(elot.getTraza())){
								eProcessos.setValid(false);
							}//Comprobamos que no hay ninguna analitica del deposito de origen del embotellado posterior para poder borrarlo
							else {
								analiticaDao.setHibernateTemplate(getHibernateTemplate());
								if (elot.getLot().getDiposit() != null) {
									Analitica analitica = analiticaDao.findUltimaByDeposito(elot.getLot().getDiposit().getId());
									if(analitica != null && analitica.getTraza().getId().longValue() > elot.getTraza().getId().longValue()){
										eProcessos.setValid(false);
									}else{
										eProcessos.setValid(true);
									}
								} else {
									eProcessos.setValid(true);
								}
							}
							eProcessos.setIdDipositLot(elot.getLot().getId());
							eProcessos.setTipusDipositLot(3);
							eProcessos.setDipositLot(elot.getLot().getCodiAssignat());
							eProcessos.setOlivaTaula(false);
							eProcessos.setObservacions("Embotellat de " + elot.getLitres() + " l. ("+elot.getBotelles()+" Bot. de "+(elot.getLitres()/elot.getBotelles())+" l.) | Dip. procèdencia: " + elot.getLot().getDiposit().getCodiAssignat());
							list.add(eProcessos);						
					}
				}
			}
			
			System.out.println(">>> Venta de lote");
			//11 - Venta de lote
			sortidaLotDao.setHibernateTemplate(getHibernateTemplate());
			Iterator itSortidaLot = sortidaLotDao.findByEstablecimientoEntreDates(establimentId, new Boolean(true), dataInici, dataFi).iterator();
			
			while (itSortidaLot.hasNext()) {
				SortidaLot slot = (SortidaLot) itSortidaLot.next();
				System.out.println("> Venta de lot: " + slot.getId());
				if(slot.getVendaData()!= null ){
					EdicioProcessosAux eProcessos = new EdicioProcessosAux();
					eProcessos.setAccio("11");
					eProcessos.setData(slot.getVendaData());
					eProcessos.setTraza(slot.getTraza());
					eProcessos.setValid(this.getValid(slot.getTraza()));
					eProcessos.setIdDipositLot(slot.getId());
					eProcessos.setTipusDipositLot(6);
					eProcessos.setDipositLot(slot.getLot().getCodiAssignat());
					eProcessos.setObservacions("Botelles: " + slot.getVendaBotelles() + " | Litres: " + slot.getVendaLitres() + " l." + " | Destinatari: "+slot.getVendaDestinatari()+" | Doc: "+slot.getVendaNumeroDocument());
					eProcessos.setOlivaTaula(false);
					list.add(eProcessos);
				}				
			}
			
//			System.out.println(">>> Venta d'oliva crua a granel");
//			//14 - Venda d'oliva crua a granel
//			sortidaPartidaDao.setHibernateTemplate(getHibernateTemplate());
//			Iterator itSortidaPartida2 = sortidaPartidaDao.findByEstablecimientoEntreDates(establimentId, new Boolean(true), dataInici, dataFi).iterator();
//			
//			while (itSortidaPartida2.hasNext()) {
//				SortidaPartida spartida = (SortidaPartida) itSortidaPartida2.next();
//				System.out.println("> Venta d'oliva crua: " + spartida.getId());
//				if(spartida.getVendaData()!= null ){
//					EdicioProcessosAux eProcessos = new EdicioProcessosAux();
//					eProcessos.setAccio("21");
//					eProcessos.setData(spartida.getVendaData());
//					eProcessos.setTraza(spartida.getTraza());
//					eProcessos.setValid(this.getValid(spartida.getTraza()));
//					eProcessos.setDipositLot("");
//					eProcessos.setObservacions("Kilos: " + spartida.getVendaKilos()+" kg.");
//					eProcessos.setOlivaTaula(true);
//					list.add(eProcessos);
//				}				
//			}
			
//			System.out.println(">>> Venta d'oliva en bota a granel");
//			//15 - Venda d'oliva en bota a granel
//			sortidaBotaGranelDao.setHibernateTemplate(getHibernateTemplate());
//			Iterator itSortidaBotaGranel2 = sortidaBotaGranelDao.findByEstablecimientoEntreDates(establimentId, new Boolean(true), dataInici, dataFi).iterator();
//			
//			while (itSortidaBotaGranel2.hasNext()) {
//				SortidaBotaGranel sbotag = (SortidaBotaGranel) itSortidaBotaGranel2.next();
//				System.out.println("> Venta d'oliva crua: " + sbotag.getId());
//				if(sbotag.getVendaData()!= null ){
//					EdicioProcessosAux eProcessos = new EdicioProcessosAux();
//					eProcessos.setAccio("22");
//					eProcessos.setData(sbotag.getVendaData());
//					eProcessos.setTraza(sbotag.getTraza());
//					eProcessos.setValid(this.getValid(sbotag.getTraza()));
//					eProcessos.setDipositLot("");
//					eProcessos.setObservacions("Kilos: " + sbotag.getVendaKilos()+" kg.");
//					eProcessos.setOlivaTaula(true);
//					list.add(eProcessos);
//				}				
//			}
		}
		
		if (tipoUsu == 2 || tipoUsu == 3) { // Tafona
			System.out.println(">>> Entrada oliva");
			// 1 - Entrada oliva
			partidaOlivaDao.setHibernateTemplate(getHibernateTemplate());
			itPartidaOliva = partidaOlivaDao.getPartidasOliEntreDiasEnEstablecimiento(dataInici, dataFi, establimentId, new Boolean(true)).iterator(); 
			
			while (itPartidaOliva.hasNext()) {
				PartidaOliva pao = (PartidaOliva) itPartidaOliva.next();
				System.out.println("> Entrada oliva: " + pao.getId());
					EdicioProcessosAux eProcessos = new EdicioProcessosAux();
					eProcessos.setAccio("1");
					eProcessos.setData(pao.getData());
					eProcessos.setTraza(pao.getTraza());
					eProcessos.setValid(this.getValid(pao.getTraza()));
					eProcessos.setIdDipositLot(pao.getId());
					eProcessos.setTipusDipositLot(7);
					eProcessos.setObservacions("Olivicultor: " + pao.getOlivicultor().getNom() + " | Quantitat: " + pao.getTotalQuilosDesglosats() + " | Partida: " + pao.getPartidaElaboracions());
					eProcessos.setOlivaTaula(false);
					list.add(eProcessos);
			}
			
			System.out.println(">>> Elaboració oli");
			// 2 - Elaboració oli
			elaboracioDao.setHibernateTemplate(getHibernateTemplate());
			Iterator itElaboracio = elaboracioDao.getElaboracionesEntreDiasEnEstablecimiento(dataInici, dataFi, establimentId, true, new Boolean(true)).iterator();
			while (itElaboracio.hasNext()) {
				Elaboracio ela = (Elaboracio)itElaboracio.next();
				System.out.println("> Elaboracio oli: " + ela.getId());
				String depositos ="";
				boolean valido = true;
				
				if (ela != null) {					
					it = ela.getTraza().getTrazasForTtrCodtrafill().iterator();
					while (it.hasNext()) {
						Traza tra = (Traza) it.next();
						
						if (tra.getTipus().equals(new Integer(3))) {
							entradaDipositDao.setHibernateTemplate(getHibernateTemplate());
							EntradaDiposit edi = entradaDipositDao.findByTraza(tra.getId(), new Boolean(true));
							//Para no mostrar elaboración cuando se lleva el aceite el olivicultor
							if (edi != null && edi.getDiposit()!= null && edi.getDiposit().getCodiAssignat()!= null && !edi.getDiposit().getFictici().booleanValue()) {
								if (!"".equals(depositos)) depositos += ", ";
								depositos += edi.getDiposit().getCodiAssignat();
								
								
								//Comprobamos si se puede borrar es decir si para cada uno de los depositos donde ha ido el aceite se ha hecho algo con ellos
								if(!this.getValid(edi.getTraza())){
									valido = false;
								}
							}else if(edi != null && edi.getDiposit()!= null && edi.getDiposit().getFictici().booleanValue()){
								if (!"".equals(depositos)) depositos += ", ";
								depositos += "Olivicultor";
								
							}
						}
					}
					
										
					EdicioProcessosAux eProcessos = new EdicioProcessosAux();
					eProcessos.setAccio("2");
					eProcessos.setData(ela.getData());
					eProcessos.setTraza(ela.getTraza());
					eProcessos.setValid(valido);						
					eProcessos.setIdDipositLot(ela.getId());
					eProcessos.setTipusDipositLot(1); // 1--> traza para la Elaboracion
					eProcessos.setDipositLot(depositos); 
					eProcessos.setObservacions("Partida: " + ela.getPartidaOli().getNom() + " | Quantitat: " + ela.getLitres() + "l." + " | Acidesa: " + ela.getAcidesa()+ " | Núm. elaboració: " + ela.getNumeroElaboracio() + " | " + ela.getObservacions());
					eProcessos.setOlivaTaula(false);
					list.add(eProcessos);
					
				}
			}
			
			System.out.println(">>> Sortida d'orujo");
			// 12 - Sortida d'orujo
			sortidaOrujoDao.setHibernateTemplate(getHibernateTemplate());
			Iterator itSortidaOrujo = sortidaOrujoDao.findAllAmbEstablimentEntreDates(dataInici, dataFi, establimentId, new Boolean(true)).iterator();
			while (itSortidaOrujo.hasNext()) {
				SortidaOrujo sortidaOrujo = (SortidaOrujo)itSortidaOrujo.next();
				System.out.println("> Sortida orujo: " + sortidaOrujo.getId());
				boolean valido = true;
				
				if (sortidaOrujo != null) {					
					EdicioProcessosAux eProcessos = new EdicioProcessosAux();
					
					eProcessos.setAccio(""+SORTIDA_ORUJO);
					eProcessos.setData(sortidaOrujo.getData());
					eProcessos.setTraza(null);
					eProcessos.setValid(valido);						
					eProcessos.setIdDipositLot(sortidaOrujo.getId()); // -> Utilitzam aquest camp a l'hora d'esborrar el proces
					eProcessos.setTipusDipositLot(0);
					eProcessos.setDipositLot(null);
					eProcessos.setOlivaTaula(false);
					list.add(eProcessos);
					
				}
			}
			
			System.out.println(">>> Devolució");
			// 13 - Devolució
			entradaLotDao.setHibernateTemplate(getHibernateTemplate());
			itEntradaLot = entradaLotDao.findByEstablecimientoEntreDates(establimentId, new Boolean(true), dataInici, dataFi).iterator();
						
			while (itEntradaLot.hasNext()) {
				EntradaLot elot = (EntradaLot) itEntradaLot.next();
				System.out.println("> Devolucio. Entrada lot: " + elot.getId());
					if(elot.getEsDevolucio() != null && elot.getEsDevolucio().booleanValue() == true){
										
							EdicioProcessosAux eProcessos = new EdicioProcessosAux();
							eProcessos.setAccio("" + DEVOLUCIO);
							eProcessos.setData(elot.getData());
							eProcessos.setTraza(elot.getTraza());
							if(!this.getValid(elot.getTraza()) || elot.getBotelles() == null){
								eProcessos.setValid(false);
							}
							else {
								eProcessos.setValid(true);
							}
							eProcessos.setIdDipositLot(elot.getLot().getId());
							eProcessos.setTipusDipositLot(3);
							eProcessos.setDipositLot(elot.getLot().getCodiAssignat());
							eProcessos.setOlivaTaula(false);
							eProcessos.setObservacions("Devolució de "+elot.getBotelles()+" bot. " + " | Contra-etiq: "+elot.getNumerosContraetiquetes() + " | Observacions: " + elot.getObservacions());
							list.add(eProcessos);						
					}
			}
		}
		
		if (tipoUsu == 1 || tipoUsu == 2 || tipoUsu == 3) { // Tafona - Envasador
			System.out.println(">>> Transvassament oli");
			// 3 - Transvassament oli
			entradaDipositDao.setHibernateTemplate(getHibernateTemplate());
			itEntrada = entradaDipositDao.getEntradasDipositEntreFechasAndEstablecimiento(dataInici, dataFi, establimentId, new Boolean(true)).iterator();
			boolean flag = true;
			boolean val = true;
			boolean analiticaPosterior = false;
			while (itEntrada.hasNext()) {
				EntradaDiposit edi = (EntradaDiposit) itEntrada.next();
				System.out.println("> Trasvassament. Entrada: " + edi.getId());
				flag = true;
				analiticaPosterior = false;
				
				if (edi.getObservacions() != null && edi.getObservacions().trim().indexOf(tancamentLlibres)== 0) {	//Comprobamos que no se trata de un cierre de libros				
					flag = false;
				} else if (edi.getObservacions() != null && edi.getObservacions().trim().indexOf(stringAnalitica)== 0) { //Comprovam que no es una analitica
					flag = false;
				} else if (edi.getObservacions() != null && edi.getObservacions().trim().indexOf(desqualificat)== 0) { //Comprovam que no es una sortida d'oli desqualificant
					flag = false;
				} else if (((edi.getObservacions() != null && !edi.getObservacions().endsWith(pendentQualificacio)) || edi.getObservacions() == null) && edi.getElaboracio() == null) {
					// les observacions siguin distintes a "Pendent de qualificació" o null, que s'identifiquen com a tipus transvassament
					String dipDesti = "";
					it = edi.getTraza().getTrazasForTtrCodtrapare().iterator();
					while (it.hasNext()) {
						Traza tra = (Traza) it.next();
						// Comprovam si els pares de l'entrada són de tipus sortida.
						if (tra.getTipus().equals(new Integer(4))) {
							sortidaDipositDao.setHibernateTemplate(getHibernateTemplate());
							SortidaDiposit sdiAux = sortidaDipositDao.findByTraza(tra.getId(), new Boolean(true));
							// Si la salida tiene como destino "TORNAR ......" entonces es un traslado no un transvase
							if(sdiAux.getDesti()!= null && sdiAux.getDesti().indexOf(devolver)>=0){
								flag = false;
							}
							//Si el dipòsit origen es diferent al destí­ introduim el registre a la llista.
							// La sortida que s'identifica amb els mateixos dipòsits a origen i destí­ es la sortida del mateix diposit per al trasbals.
							// Si realitzam un trasbals de 2 dipòsits a 1 se descomposarà aquest trasbals.
							if (sdiAux.getDipositBySdiCoddor() != null && sdiAux.getDipositBySdiCoddde() != null && !sdiAux.getDipositBySdiCoddor().equals(sdiAux.getDipositBySdiCoddde())) {
								if (!"".equals(dipDesti)) dipDesti += ", ";
								dipDesti += sdiAux.getDipositBySdiCoddor().getCodiAssignat();								
							}
							
							//Si el deposito origen o destino ya no pertenecen al establecimiento no se muestra (Depositos trasladados ya devueltos)
							if(sdiAux.getDipositBySdiCoddor().getEstabliment().getId()!= establimentId || edi.getDiposit().getEstabliment().getId()!= establimentId ){
								flag = false;
							}
							
							//Si los depositos origen o deposito destino, alguno tiene alguna analitica posterior al trasvase no se puede borrar el trasvase
							analiticaDao.setHibernateTemplate(getHibernateTemplate());
							Analitica analitica = analiticaDao.findUltimaByDeposito(sdiAux.getDipositBySdiCoddor().getId());
							if(analitica != null && analitica.getTraza().getId().longValue() > sdiAux.getTraza().getId().longValue()){
								analiticaPosterior = true;		
							}							
							
							//Si el trasbals ha canviat la categoria de la partida, i s'han realitzat operacions posteriors a la partida, no es podrà editar
							if (edi.getPartidaOli().equals(sdiAux.getPartidaOli()) && !edi.getCategoriaOli().equals(sdiAux.getCategoriaOli())){
								for(Iterator it2 = sortidaDipositDao.getSortidesDipositEntreFechasPartidaAndEstabliment(edi.getData(), edi.getPartidaOli().getId(), establimentId, new Boolean(true)).iterator(); it2.hasNext();) {
									SortidaDiposit sdi2 = (SortidaDiposit)it2.next();
									if (sdi2.getData().after(edi.getData())){ 
										val = false;
										break;
									} else { // Mateixa data
										SortidaDiposit sd3 = sortidaDipositDao.findByTraza(tra.getId(), new Boolean(true));
										if (sd3.getId() < sdi2.getId()){
											val = false;
											break;
										}
									}
								}
							}
						}else if(tra.getTipus().equals(new Integer(8))){// Comprovam si els pares de l'entrada són de tipus trasllat.En tal caso no debe mostrarse como trasvase
							flag = false;
						}
					}
					
					if(flag){
						
						EdicioProcessosAux eProcessos = new EdicioProcessosAux();
						eProcessos.setAccio("3");
						eProcessos.setData(edi.getData());
						eProcessos.setTraza(edi.getTraza());
						if(analiticaPosterior){
							eProcessos.setValid(false);
						}else{
							eProcessos.setValid(this.getValid(edi.getTraza()) && val);
						}
						
						eProcessos.setIdDipositLot(edi.getDiposit().getId());
						eProcessos.setTipusDipositLot(2);
						eProcessos.setDipositLot("de " + dipDesti + " a " + edi.getDiposit().getCodiAssignat());
						eProcessos.setOlivaTaula(false);
						list.add(eProcessos);						
					}
				}
				
			}
			
			System.out.println(">>> Perdua oli");
			// 4 - Perdua oli
			sortidaDipositDao.setHibernateTemplate(getHibernateTemplate());
			itSortida = sortidaDipositDao.getSortidesDipositEntreFechasAndEstablecimiento(dataInici, dataFi, establimentId, new Boolean(true)).iterator();
			
			while (itSortida.hasNext()) {
				SortidaDiposit sdi = (SortidaDiposit) itSortida.next();
				System.out.println("> Perdua. Sortida: " + sdi.getId());
					// Comprobamos que el destí­ de la sortida és "PÈRDUA" y que el deposito no proviene de un traslado ya devuelto 
					if (sdi.getDesti() != null && sdi.getDesti().equals(perdua) && sdi.getDipositBySdiCoddor().getEstabliment().getId() == establimentId ) {
						EdicioProcessosAux eProcessos = new EdicioProcessosAux();
						eProcessos.setAccio("4");
						eProcessos.setData(sdi.getData());
						eProcessos.setTraza(sdi.getTraza());
						//Comprobamos que no hay ninguna analitica posterior para poder borrarlo
						//eProcessos.setValid(this.getValid(sdi.getTraza()));
						if(!this.getValid(sdi.getTraza())){
							eProcessos.setValid(false);
						}else{ 
							analiticaDao.setHibernateTemplate(getHibernateTemplate());
							Analitica analitica = analiticaDao.findUltimaByDeposito(sdi.getDipositBySdiCoddor().getId());
							if(analitica != null && analitica.getTraza().getId().longValue() > sdi.getTraza().getId().longValue()){
								eProcessos.setValid(false);
							}else{
								eProcessos.setValid(true);
							}
						}						
						
						eProcessos.setIdDipositLot(sdi.getDipositBySdiCoddor().getId());
						eProcessos.setTipusDipositLot(2);
						eProcessos.setDipositLot(sdi.getDipositBySdiCoddor().getCodiAssignat());
						eProcessos.setOlivaTaula(false);
						list.add(eProcessos);
					}
			}
			
			System.out.println(">>> Desqualificat oli");
			// 5 - Desqualificar oli
			entradaDipositDao.setHibernateTemplate(getHibernateTemplate());
			itEntrada = entradaDipositDao.getEntradasDipositEntreFechasAndEstablecimiento(dataInici, dataFi, establimentId, new Boolean(true)).iterator();
			
			while (itEntrada.hasNext()) {
				EntradaDiposit edi = (EntradaDiposit) itEntrada.next();
				System.out.println("> Desqualificar. Entrada: " + edi.getId());
				// Comprovam que les observacions siguin "Pendent de qualificació" o null
				if ((edi.getObservacions() != null && edi.getObservacions().endsWith(pendentQualificacio)) && edi.getElaboracio() == null && edi.getDiposit().getEstabliment().getId()== establimentId) {
					it = edi.getTraza().getTrazasForTtrCodtrapare().iterator();
					while (it.hasNext()) {
						Traza tra = (Traza) it.next();
						// Comprovam si els pares de l'entrada són de tipus sortida.
						if (tra.getTipus().equals(new Integer(4))) {
							EdicioProcessosAux eProcessos = new EdicioProcessosAux();
							eProcessos.setAccio("5");
							eProcessos.setData(edi.getData());
							eProcessos.setTraza(edi.getTraza());
							boolean valid = this.getValid(edi.getTraza());
							if (valid){
								sortidaDipositDao.setHibernateTemplate(getHibernateTemplate());
								for(Iterator it2 = sortidaDipositDao.getSortidesDipositEntreFechasPartidaAndEstabliment(edi.getData(), edi.getPartidaOli().getId(), establimentId, new Boolean(true)).iterator(); it2.hasNext();) {
									SortidaDiposit sdi2 = (SortidaDiposit)it2.next();
									if (sdi2.getData().after(edi.getData())){ 
										valid = false;
										break;
									} else { // Mateixa data
										SortidaDiposit sd3 = sortidaDipositDao.findByTraza(tra.getId(), new Boolean(true));
										if (sd3.getId() < sdi2.getId()){
											valid = false;
											break;
										}
									}
								}
							}
							eProcessos.setValid(valid);
							eProcessos.setIdDipositLot(edi.getDiposit().getId());
							eProcessos.setTipusDipositLot(2);
							eProcessos.setDipositLot(edi.getDiposit().getCodiAssignat());
							eProcessos.setOlivaTaula(false);
							list.add(eProcessos);
						}
					}
				}
				
			}
			
			System.out.println(">>> Moure dipòsit");
			// 6 - Moure dipòsit
			trasllatDao.setHibernateTemplate(getHibernateTemplate());
			itTrasllat = trasllatDao.findTrasllatsByEstablimentOrigenEntreDates(dataInici, dataFi, establimentId).iterator();
			
			while (itTrasllat.hasNext()) {
				Trasllat tdi = (Trasllat) itTrasllat.next();
				System.out.println("> Trasllat: " + tdi.getId());
				// Comprovam que encara no s'ha acceptat el trasllat
				String dipNom = "";
				
				if(tdi.getAcceptatTrasllat() == null){ //Si el traslado no ha sido rechazado
					
					EdicioProcessosAux eProcessos = new EdicioProcessosAux();
					
					eProcessos.setAccio("6");
					eProcessos.setData(tdi.getDataAlta());
					eProcessos.setTraza(tdi.getTraza());
					eProcessos.setValid(this.getValid(tdi.getTraza()));
					it = tdi.getDiposits().iterator();
					while (it.hasNext()) {
						Diposit dip = (Diposit) it.next();
						if (dipNom != null && !"".equals(dipNom)) {
							dipNom += ", ";
						}
						dipNom += dip.getCodiAssignat();
						if(tdi.getDiposits().size()== 1){
							eProcessos.setIdDipositLot(dip.getId());
						}						
						eProcessos.setTipusDipositLot(2);
					}
					eProcessos.setDipositLot(dipNom);
					list.add(eProcessos);
					//Si el traslado ya ha sido devuelto se mostrara la accion pero no se podra borrar
				} else {
					EdicioProcessosAux eProcessos = new EdicioProcessosAux();
					eProcessos.setAccio("6");
					eProcessos.setData(tdi.getDataAlta());
					eProcessos.setTraza(tdi.getTraza());
					eProcessos.setValid(false);
					it = tdi.getDiposits().iterator();
					while (it.hasNext()) {
						Diposit dip = (Diposit) it.next();
						if (dipNom != null && !"".equals(dipNom)) {
							dipNom += ", ";
						}
						dipNom += dip.getCodiAssignat();
						if(tdi.getDiposits().size()== 1){
							eProcessos.setIdDipositLot(dip.getId());
						}						
						eProcessos.setTipusDipositLot(2);
					}
					eProcessos.setDipositLot(dipNom);
					eProcessos.setOlivaTaula(false);
					list.add(eProcessos);					
				}
			}
			
			System.out.println(">>> Sortida oli (Venta de aceite a granel)");
			// 7 - Sortida oli (Venta de aceite a granel)
			sortidaDipositDao.setHibernateTemplate(getHibernateTemplate());
			itSortida = sortidaDipositDao.getSortidesDipositEntreFechasAndEstablecimiento(dataInici, dataFi, establimentId, new Boolean(true)).iterator();
			
			while (itSortida.hasNext()) {
				flag = true;
				SortidaDiposit sdi = (SortidaDiposit) itSortida.next();
				System.out.println("> Sortida: " + sdi.getId());
				if (sdi.getTraza() != null && sdi.getTraza().getTipus().intValue() == Constants.CODI_TRAZA_TIPUS_VENDA_OLI && sdi.getDipositBySdiCoddor().getEstabliment().getId() == establimentId ) {
					EdicioProcessosAux eProcessos = new EdicioProcessosAux();
					eProcessos.setAccio("7");
					eProcessos.setData(sdi.getData());
					eProcessos.setTraza(sdi.getTraza());
					
					//Comprobamos que no hay ninguna analitica posterior para poder borrarlo
					//eProcessos.setValid(this.getValid(sdi.getTraza()));
					if(!this.getValid(sdi.getTraza())){
						eProcessos.setValid(false);
					}else{ 
						analiticaDao.setHibernateTemplate(getHibernateTemplate());
						Analitica analitica = analiticaDao.findUltimaByDeposito(sdi.getDipositBySdiCoddor().getId());
						if(analitica != null && analitica.getTraza().getId().longValue() > sdi.getTraza().getId().longValue()){
							eProcessos.setValid(false);
						}else{
							eProcessos.setValid(true);
						}
					}
					eProcessos.setIdDipositLot(sdi.getDipositBySdiCoddor().getId());
					eProcessos.setTipusDipositLot(2);
					if (sdi.getObservacions() != null && sdi.getObservacions().trim().indexOf(desqualificat)== 0) { //Comprovam si es una sortida d'oli desqualificant
						entradaDipositDao.setHibernateTemplate(getHibernateTemplate());
						// Obtenim l'entrada d'origen
						for (it = sdi.getTraza().getTrazasForTtrCodtrapare().iterator(); it.hasNext();){
							Traza traza = (Traza)it.next();
							if (traza.getTipus().intValue() == Constants.CODI_TRAZA_TIPUS_ENTRADA_DIPOSIT){
								EntradaDiposit edi = entradaDipositDao.findByTraza(traza.getId(), new Boolean(true));
								if (edi.getObservacions() != null && edi.getObservacions().equals(sdi.getObservacions())){
									// Obtenim la sortida del dipòsit d'origen
									for (Iterator it2 = edi.getTraza().getTrazasForTtrCodtrapare().iterator(); it2.hasNext();){
										Traza traza2 = (Traza)it2.next();
										if (traza2.getTipus().intValue() == Constants.CODI_TRAZA_TIPUS_SORTIDA_DIPOSIT){
											SortidaDiposit sor = sortidaDipositDao.findByTraza(traza2.getId(), new Boolean(true));
											if (sdi.getObservacions().equals(sor.getObservacions())){
												EntradaDiposit ued = entradaDipositDao.findUltimaByDeposito(sor.getDipositBySdiCoddor().getId(), new Boolean(true));
												if (ued.getTraza().getId() > sor.getTraza().getId()) flag = false;
												eProcessos.setDipositLot(sor.getDipositBySdiCoddor().getCodiAssignat());
												eProcessos.setObservacions(sor.getLitres() + "l. (" + desqualificat + ") a " + sor.getDesti());
												break;
											}
										}
									}
									break;
								}
							}
						}
						
					} else {
						eProcessos.setDipositLot(sdi.getDipositBySdiCoddor().getCodiAssignat());
						eProcessos.setObservacions(sdi.getLitres() + "l. a " + sdi.getDesti());
					}
					eProcessos.setOlivaTaula(false);
					if (flag) list.add(eProcessos);
				}
			}
			
			System.out.println(">>> Introducció analítiques");
			// 10 - Introducció analítiques
			analiticaDao.setHibernateTemplate(getHibernateTemplate());
			itAnaliticas = analiticaDao.findByEstablimentEntreDates(dataInici, dataFi, establimentId, new Boolean(true)).iterator();

			while (itAnaliticas.hasNext()) {
				Analitica ana = (Analitica) itAnaliticas.next();	
				System.out.println("> Analitica: " + ana.getId());
				EdicioProcessosAux eProcessos = new EdicioProcessosAux();
				eProcessos.setAccio("10");
				eProcessos.setData(ana.getData());
				eProcessos.setTraza(ana.getTraza());
				
				analiticaDao.setHibernateTemplate(getHibernateTemplate());
				Analitica analitica = analiticaDao.findUltimaByPartida(ana.getPartidaOli().getId());
				if(analitica != null && analitica.getTraza().getId().longValue() > ana.getTraza().getId().longValue() && !analitica.getId().equals(ana.getId())){
					eProcessos.setValid(false);
				}else{
					eProcessos.setValid(true);
					for (Iterator itSort = ana.getPartidaOli().getDiposits().iterator(); itSort.hasNext();){
						Diposit dip = (Diposit)itSort.next();
						sortidaDipositDao.setHibernateTemplate(getHibernateTemplate());
						SortidaDiposit sor = sortidaDipositDao.findUltimaByDeposito(dip.getId(), new Boolean(true));
						if (sor != null){
							// L'analítica genera una traça, per tant si hi ha una sortida
							Traza traza = null;
							for (Iterator it2 = ana.getTraza().getTrazasForTtrCodtrafill().iterator(); it2.hasNext();){
								Traza trazaAux = (Traza)it2.next();
								if (trazaAux.getTipus().equals(Constants.CODI_TRAZA_TIPUS_SORTIDA_DIPOSIT)){
									traza = trazaAux;
								}
							}
							if (
									(traza != null && traza.getId() > sor.getTraza().getId()) ||
									(traza == null && sor.getData().after(ana.getData()))
								){
								eProcessos.setValid(false);
								break;
							}
						}
					}
				}
				
				eProcessos.setIdDipositLot(ana.getDiposit().getId());
				eProcessos.setTipusDipositLot(2);
				eProcessos.setDipositLot(ana.getPartidaOli().getNom() + " - " + ana.getDiposit().getCodiAssignat());
				eProcessos.setEstabliment(ana.getPartidaOli().getEstabliment());
				eProcessos.setOlivaTaula(false);
				list.add(eProcessos);
				
			}

		}
		
		ProcesosComparatorDecreciente procesosComparatorDecreciente = new ProcesosComparatorDecreciente();
		Collections.sort(list, procesosComparatorDecreciente);
		return list;
	}
	
	/**
	 * Cerca si es pot borrar el procés <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public boolean getValid(Traza traza) {
		boolean resultado = true; //traza.getTrazasForTtrCodtrafill().isEmpty();
		
		// Recorrem els fills per a sabre si es pot borrar o no el procés
		Iterator it = traza.getTrazasForTtrCodtrafill().iterator();
		while (it.hasNext()) {
			PartidaOliva pao = null;
			PartidaFonoll paf = null;
			Elaboracio ela = null;
			EntradaDiposit edi = null;
			SortidaDiposit sdi = null;
			Analitica ana = null;
			EntradaLot elot = null;
			SortidaLot slot = null;
			Trasllat tdi = null;
			ElaboracioOliva elao = null;
			Bota bot = null;
			SortidaBota sob = null;
			AutocontrolOliva aut = null;
			SortidaBotaGranel sbog = null;
			SortidaPartida spar = null;
			Traza tra = (Traza) it.next();
			
			switch (tra.getTipus().intValue()) {
			/* Segons el tipus de traça cercarem un objecte o un altre:
			 - partida de oliva: 1
			 - elaboracio: 2
			 - entrada diposit: 3
			 - sortida diposit: 4
			 - analitica: 5
			 - entrada lot: 6
			 - sortida lot: 7
			 - trasllat diposit: 8
			 - venda oli: 9 ; 
			 - trasllat d'oli: 10
			 - elaboracio oliva: 11
			 - entrada bota: 12
			 - entrada fonoll: 13
			 - sortida bota: 14
			 - autocontrol oliva: 15
			 - sortida oliva taula a granel: 16
			 - sortida oliva taula bota a granel: 17
			 */
			
			case 1:
				partidaOlivaDao.setHibernateTemplate(getHibernateTemplate());
				pao = partidaOlivaDao.findByTraza(tra.getId(), null);
				// Comprovam si es valid o no.
				if (pao.getValid().booleanValue()) resultado = false;				
				break;
			case 2:
				elaboracioDao.setHibernateTemplate(getHibernateTemplate());
				ela = elaboracioDao.findByTraza(tra.getId(), null);
				//Comprovam si es vÃƒÂ lid o no.
				if (ela.getValid().booleanValue()) resultado = false;
				break;
			case 3:
				entradaDipositDao.setHibernateTemplate(getHibernateTemplate());
				edi = entradaDipositDao.findByTraza(tra.getId(), null);
				// Comprovam si es vàlid o no.
				if (edi.getValid().booleanValue()) resultado = false;				
				break;
			case 4:
				sortidaDipositDao.setHibernateTemplate(getHibernateTemplate());
				sdi = sortidaDipositDao.findByTraza(tra.getId(), null);
				// Comprovam si es vàlid o no.
				if (sdi.getValid().booleanValue()) resultado = false;
				break;
			case 5:
				analiticaDao.setHibernateTemplate(getHibernateTemplate());
				ana = analiticaDao.findByTraza(tra.getId());
				// Comprovam si es vÃƒÂ lid o no.
				if (ana.getAnalisiFisicoQuimicValid().booleanValue()) resultado = false;
				break;
			case 6:
				entradaLotDao.setHibernateTemplate(getHibernateTemplate());
				elot = entradaLotDao.findByTraza(tra.getId(), null);
				// Comprovam si es vàlid o no.
				if (elot.getValid().booleanValue()) resultado = false;
				break;
			case 7:
				sortidaLotDao.setHibernateTemplate(getHibernateTemplate());
				slot = sortidaLotDao.findByTraza(tra.getId(), null);
				// Comprovam si es vÃƒÂ lid o no.
				if (slot.getValid().booleanValue()) resultado = false;
				break;
			case 8:
				trasllatDao.setHibernateTemplate(getHibernateTemplate());
				tdi = trasllatDao.findByTraza(tra.getId());
				// Comprovam si es vÃƒÂ lid o no.
				if (tdi.getValid().booleanValue()) resultado = false;
				break;
			case 9:
				sortidaDipositDao.setHibernateTemplate(getHibernateTemplate());
				sdi = sortidaDipositDao.findByTraza(tra.getId(), null);
				// Comprovam si es vÃƒÂ lid o no.
				if (sdi.getValid().booleanValue()) resultado = false;
				break;
				
			case 11:
				elaboracioOlivaDao.setHibernateTemplate(getHibernateTemplate());
				elao = elaboracioOlivaDao.findByTraza(tra.getId(), null);
				// Comprovam si es valid o no.
				if (elao.getValid().booleanValue()) resultado = false;				
				break;
			case 12:
				botaDao.setHibernateTemplate(getHibernateTemplate());
				bot = botaDao.findByTraza(tra.getId(), null);
				// Comprovam si es valid o no.
//				if (bot.getValid().booleanValue()) 
					resultado = getValid(tra);				
				break;
			case 13:
				partidaFonollDao.setHibernateTemplate(getHibernateTemplate());
				paf = partidaFonollDao.findByTraza(tra.getId(), null);
				// Comprovam si es valid o no.
				if (paf.getValid().booleanValue()) resultado = false;				
				break;
			case 14:
				sortidaBotaDao.setHibernateTemplate(getHibernateTemplate());
				sob = sortidaBotaDao.findByTraza(tra.getId(), null);
				// Comprovam si es valid o no.
				if (sob.getValid().booleanValue()) resultado = false;				
				break;
			case 15:
				autocontrolOlivaDao.setHibernateTemplate(getHibernateTemplate());
				aut = autocontrolOlivaDao.findByTraza(tra.getId(), null);
				// Comprovam si es valid o no.
				if (aut.getValid().booleanValue()) resultado = false;				
				break;
			case 16:
				sortidaPartidaDao.setHibernateTemplate(getHibernateTemplate());
				spar = sortidaPartidaDao.findByTraza(tra.getId(), null);
				// Comprovam si es valid o no.
				if (spar.getValid().booleanValue()) resultado = false;				
				break;	
			case 17:
				sortidaBotaGranelDao.setHibernateTemplate(getHibernateTemplate());
				sbog = sortidaBotaGranelDao.findByTraza(tra.getId(), null);
				// Comprovam si es valid o no.
				if (sbog.getValid().booleanValue()) resultado = false;				
				break;	
			}
			
			// Si algun dels fills són vàlids no es pot borrar el procés pare.
			if (!resultado) break;
		}
		
		//Si no tiene ningun hijo implicado en un proceso activo, comprobamos que no esta implicado en ningÃƒÂºn tralado
		if(resultado){
			if (this.getEnTrasllat(traza))	 resultado = false;
		}
		
		return resultado;
	}
	

/**
 * Cerca si es pot borrar el procés <!-- begin-xdoclet-definition -->
 * 
 * @ejb.interface-method view-type="both"
 * @ejb.permission role-name="OLI_DOCONTROLADOR"
 * @ejb.permission role-name="OLI_PRODUCTOR"
 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
 */
public boolean getEnTrasllat(Traza tra) {
	boolean resultado = false; //traza.getTrazasForTtrCodtrafill().isEmpty();
	EntradaDiposit edi = null;
	SortidaDiposit sdi = null;
	Analitica ana = null;
	EntradaLot elot = null;
	SortidaLot slot = null;
	Diposit dip = null;	
		
	switch (tra.getTipus().intValue()) {
		/* Segons el tipus de traça cercarem un objecte o un altre:
		 - partida de oliva: 1
		 - elaboracio: 2
		 - entrada diposit: 3
		 - sortida diposit: 4
		 - analitica: 5
		 - entrada lot: 6
		 - sortida lot: 7
		 - trasllat diposit: 8
		 - venda oli: 9 ;  
		 */
		case 3:
			entradaDipositDao.setHibernateTemplate(getHibernateTemplate());
			edi = entradaDipositDao.findByTraza(tra.getId(), null);
			if(edi!= null){
				dip = edi.getDiposit();
				trasllatDao.setHibernateTemplate(getHibernateTemplate());
				//resultado = trasllatDao.existenTrasladosAsociadosDepositoByFecha(dip.getId(), Boolean.valueOf(true), edi.getData(),dip.getEstabliment().getId());
				resultado = trasllatDao.existenTrasladosPosterioresAsociadosDepositoByTraza(dip.getId(), Boolean.valueOf(true), edi.getTraza().getId(),dip.getEstabliment().getId());
			}
			dip = edi.getDiposit();
			trasllatDao.setHibernateTemplate(getHibernateTemplate());
			//resultado = trasllatDao.existenTrasladosAsociadosDepositoByFecha(dip.getId(), Boolean.valueOf(true), edi.getData(),dip.getEstabliment().getId());
			resultado = trasllatDao.existenTrasladosPosterioresAsociadosDepositoByTraza(dip.getId(), Boolean.valueOf(true), edi.getTraza().getId(),dip.getEstabliment().getId());
			break;
		
		case 4:
			sortidaDipositDao.setHibernateTemplate(getHibernateTemplate());
			sdi = sortidaDipositDao.findByTraza(tra.getId(), null);
			if(sdi!= null){
				dip = sdi.getDipositBySdiCoddor();
				if(dip != null){
					trasllatDao.setHibernateTemplate(getHibernateTemplate());
					resultado = trasllatDao.existenTrasladosPosterioresAsociadosDepositoByTraza(dip.getId(), Boolean.valueOf(true), sdi.getTraza().getId(),sdi.getEstabliment().getId());
				}
				if(!resultado){
					dip = sdi.getDipositBySdiCoddde();
					if(dip!= null){
						trasllatDao.setHibernateTemplate(getHibernateTemplate());
						resultado = trasllatDao.existenTrasladosPosterioresAsociadosDepositoByTraza(dip.getId(), Boolean.valueOf(true), sdi.getTraza().getId(),sdi.getEstabliment().getId());
					}							
				}
			}			
			break;
		case 5:
			analiticaDao.setHibernateTemplate(getHibernateTemplate());
			ana = analiticaDao.findByTraza(tra.getId());
			if(ana != null){
				dip = ana.getDiposit();
				if(dip != null){
					trasllatDao.setHibernateTemplate(getHibernateTemplate());
					//resultado = trasllatDao.existenTrasladosAsociadosDepositoByFecha(dip.getId(), Boolean.valueOf(true), ana.getData(),dip.getEstabliment().getId());
					resultado = trasllatDao.existenTrasladosPosterioresAsociadosDepositoByTraza(dip.getId(), Boolean.valueOf(true), ana.getTraza().getId(),dip.getEstabliment().getId());
				}
			}
			break;
		case 6:
			entradaLotDao.setHibernateTemplate(getHibernateTemplate());
			elot = entradaLotDao.findByTraza(tra.getId(), null);
			if(elot!= null && elot.getLot()!= null && elot.getLot().getDiposit()!= null){
				dip = elot.getLot().getDiposit();
				trasllatDao.setHibernateTemplate(getHibernateTemplate());
				// resultado = trasllatDao.existenTrasladosAsociadosDepositoByFecha(dip.getId(), Boolean.valueOf(true), elot.getData(),dip.getEstabliment().getId());
				resultado = trasllatDao.existenTrasladosPosterioresAsociadosDepositoByTraza(dip.getId(), Boolean.valueOf(true), elot.getTraza().getId(),dip.getEstabliment().getId());
			}
			break;
		case 7:
			sortidaLotDao.setHibernateTemplate(getHibernateTemplate());
			slot = sortidaLotDao.findByTraza(tra.getId(), null);
			if(slot!= null && slot.getLot()!= null && slot.getLot().getDiposit()!= null){
				dip = slot.getLot().getDiposit();
				trasllatDao.setHibernateTemplate(getHibernateTemplate());
				if(slot.getCanviZonaData() != null){
					// resultado = trasllatDao.existenTrasladosAsociadosDepositoByFecha(dip.getId(), Boolean.valueOf(true), slot.getCanviZonaData(),dip.getEstabliment().getId());
					resultado = trasllatDao.existenTrasladosPosterioresAsociadosDepositoByTraza(dip.getId(), Boolean.valueOf(true), slot.getTraza().getId(),dip.getEstabliment().getId());
				}else if(slot.getVendaData() != null){
					// resultado = trasllatDao.existenTrasladosAsociadosDepositoByFecha(dip.getId(), Boolean.valueOf(true), slot.getVendaData(),dip.getEstabliment().getId());
					resultado = trasllatDao.existenTrasladosPosterioresAsociadosDepositoByTraza(dip.getId(), Boolean.valueOf(true), slot.getTraza().getId(),dip.getEstabliment().getId());
				}
			}
			break;
			
		/*		
		case 8:
			trasllatDao.setHibernateTemplate(getHibernateTemplate());
			tdi = trasllatDao.findByTraza(tra.getId());
			// Comprovam si es vÃƒÂ lid o no.
			if (tdi.getValid().booleanValue()) resultado = false;
		*/
		
		case 9:
			sortidaDipositDao.setHibernateTemplate(getHibernateTemplate());
			sdi = sortidaDipositDao.findByTraza(tra.getId(), null);
			if(sdi!= null){
				dip = sdi.getDipositBySdiCoddor();
				if(dip != null){
					trasllatDao.setHibernateTemplate(getHibernateTemplate());
					// resultado = trasllatDao.existenTrasladosAsociadosDepositoByFecha(dip.getId(), Boolean.valueOf(true), sdi.getData(),dip.getEstabliment().getId());
					resultado = trasllatDao.existenTrasladosPosterioresAsociadosDepositoByTraza(dip.getId(), Boolean.valueOf(true), sdi.getTraza().getId(),dip.getEstabliment().getId());
				}
				if(!resultado){
					dip = sdi.getDipositBySdiCoddde();
					if(dip!= null){
						trasllatDao.setHibernateTemplate(getHibernateTemplate());
						// resultado = trasllatDao.existenTrasladosAsociadosDepositoByFecha(dip.getId(), Boolean.valueOf(true), sdi.getData(),dip.getEstabliment().getId());	
						resultado = trasllatDao.existenTrasladosPosterioresAsociadosDepositoByTraza(dip.getId(), Boolean.valueOf(true), sdi.getTraza().getId(),dip.getEstabliment().getId());
					}							
				}
			}			
			break;		
			
		}

		return resultado;
}
	
	/**
	 * Cerca tots els processos d'un establiment <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public void eliminarProces(Long trazaId, int tipoPrc, String rolDoControlador, String rolDoGestor, String emailFrom, String pendentQualificacio, String tancamentLlibres, String stringAnalitica, String desqualificat) throws Exception {
		EntradaDiposit edi = null;
		SortidaDiposit sdi = null;
		SortidaBota sbo = null;
		Elaboracio ela = null;
		ElaboracioOliva elo = null;
		Diposit dip = null;
		Trasllat tdi = null;
		EntradaLot elot = null;
		Lot lot = null;
		SortidaLot slot = null;
		Traza tra = null;
		Analitica ana = null;
		AutocontrolOliva aut = null;
		Bota bot = null;
		SortidaPartida spar = null;
		SortidaBotaGranel sbog = null;
		Iterator it = null;
		
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			switch (tipoPrc) {
			case 1: // Entrada oliva
			case 14: // Entrada oliva de taula
				partidaOlivaDao.setHibernateTemplate(getHibernateTemplate());
				descomposicioPlantacioDao.setHibernateTemplate(getHibernateTemplate());
				PartidaOliva pao = partidaOlivaDao.findByTraza(trazaId, null);

				// Canviam l'estat a NO vàlid.
				pao.setValid(new Boolean(false));
				partidaOlivaDao.makePersistent(pao);
				
				// Per a cada descomposició hem de restaurar la producció màxima
				for (it = pao.getDescomposicioPartidesOlives().iterator(); it.hasNext();){
					DescomposicioPartidaOliva dpo = (DescomposicioPartidaOliva)it.next();
					Double kilosOliva = dpo.getKilos();
					
					DescomposicioPlantacio dp = dpo.getDescomposicioPlantacio();
					Double produccioRestant = 0.0;
					
					if (dp.getVarietatOliva().getId()==Constants.VARIETAT_OLIVA_MALLORQUINA_TAULA) {
						DescomposicioPlantacio dp2 = (DescomposicioPlantacio) descomposicioPlantacioDao.getByIdPlantacioIdVariedad(dp.getPlantacio().getId(), Constants.VARIETAT_OLIVA_MALLORQUINA).get(0);
						rendimentVarietatDao.setHibernateTemplate(getHibernateTemplate());
						Double rvTaula = rendimentVarietatDao.rendimentCampanyaVarietat(campanyaCercaActual(), Constants.VARIETAT_OLIVA_MALLORQUINA_TAULA);
						Double rvMall = rendimentVarietatDao.rendimentCampanyaVarietat(campanyaCercaActual(), Constants.VARIETAT_OLIVA_MALLORQUINA);
						if (dp2.getProduccioRestant() != null)
							dp2.setProduccioRestant(dp2.getProduccioRestant() + (kilosOliva*rvMall/rvTaula));
						descomposicioPlantacioDao.makePersistent(dp2);
					} else if (dp.getVarietatOliva().getId()==Constants.VARIETAT_OLIVA_MALLORQUINA) {
						DescomposicioPlantacio dp2 = (DescomposicioPlantacio) descomposicioPlantacioDao.getByIdPlantacioIdVariedad(dp.getPlantacio().getId(), Constants.VARIETAT_OLIVA_MALLORQUINA).get(0);
						rendimentVarietatDao.setHibernateTemplate(getHibernateTemplate());
						Double rvTaula = rendimentVarietatDao.rendimentCampanyaVarietat(campanyaCercaActual(), Constants.VARIETAT_OLIVA_MALLORQUINA_TAULA);
						Double rvMall = rendimentVarietatDao.rendimentCampanyaVarietat(campanyaCercaActual(), Constants.VARIETAT_OLIVA_MALLORQUINA);
						if (dp2.getProduccioRestant() != null)
							dp2.setProduccioRestant(dp2.getProduccioRestant() + (kilosOliva*rvTaula/rvMall));
						descomposicioPlantacioDao.makePersistent(dp2);
					}
					
					if (dp.getProduccioRestant() != null) produccioRestant = dp.getProduccioRestant();
					dp.setProduccioRestant(produccioRestant + kilosOliva);
					descomposicioPlantacioDao.makePersistent(dp);
				}
				break;
			case 2: // Elaboració oli
				EntradaDiposit ediAux = null;
				elaboracioDao.setHibernateTemplate(getHibernateTemplate());
				ela = elaboracioDao.findByTraza(trazaId, null);
				// Canviam els estats a NO vàlid de l'elaboració i l'entrada.
				ela.setValid(new Boolean(false));
				
				//Invalidam las entradas
				it = ela.getTraza().getTrazasForTtrCodtrafill().iterator();
				while (it.hasNext()) {
					tra = (Traza) it.next();
					if (tra.getTipus().equals(new Integer(3))) {
						entradaDipositDao.setHibernateTemplate(getHibernateTemplate());
						edi = entradaDipositDao.findByTraza(tra.getId(), new Boolean(true));
						if (edi != null) {
							edi.setValid(new Boolean(false));							
							// Vemos si alguna de las entrada tiene algun hijo salida de deposito con olivicultor para poner esta salida a invalida 
							Iterator it2 = edi.getTraza().getTrazasForTtrCodtrafill().iterator();
							while (it2.hasNext()) {
								tra = (Traza) it2.next();
								if (tra.getTipus().equals(new Integer(4))) {
									sortidaDipositDao.setHibernateTemplate(getHibernateTemplate());
									sdi = sortidaDipositDao.findByTraza(tra.getId(), new Boolean(true));
									if (sdi != null && sdi.getOlivicultor() != null) {
										// Invalidam l'oli que s'ha enduit l'olivicultor
										sdi.setValid(new Boolean(false));																			
									}
								}
							}
							entradaDipositDao.setHibernateTemplate(getHibernateTemplate());
							entradaDipositDao.makePersistent(edi);
							
							dip = edi.getDiposit();
							if(dip!= null && !dip.getFictici().booleanValue()){
								//Cercam l'estat anterior del dipòsit per a tornar-lo a aquest
//								EntradaDiposit edi2 = entradaDipositDao.findUltimaByDeposito(dip.getId(), new Boolean(true));
								Iterator it3 = edi.getTraza().getTrazasForTtrCodtrapare().iterator();
								while (it3.hasNext()) {
									tra = (Traza) it3.next();
									if (tra.getTipus().equals(new Integer(3))) {
										entradaDipositDao.setHibernateTemplate(getHibernateTemplate());
										ediAux = entradaDipositDao.findByTraza(tra.getId(), null);
										break;
									}
								}
								// Afegim els litres que s'havien elaborat, l'acidesa que tenia i la categoria d'oli anterior
								
								dip.setVolumActual(new Double((dip.getVolumActual() != null ? dip.getVolumActual().doubleValue() : new Double(0).doubleValue()) - edi.getLitres().doubleValue()));
								if (dip.getVolumActual() <= 0.0 || ediAux == null){
									dip.setVolumActual(0.0);
									dip.setAcidesa(null);
									dip.setPartidaOli(null);
								} else if (ediAux != null) {
									dip.setAcidesa(ediAux.getAcidesa());
									dip.setPartidaOli(ediAux.getPartidaOli());
									if (ediAux.getCategoriaOriginalPartida() != null && ediAux.getPartidaOli() != null && !ediAux.getPartidaOli().getCategoriaOli().equals(ediAux.getCategoriaOriginalPartida())){
										ediAux.getPartidaOli().setCategoriaOli(ediAux.getCategoriaOriginalPartida());
										entradaDipositDao.makePersistent(ediAux);
									}
								}
								dipositDao.setHibernateTemplate(getHibernateTemplate());
								dipositDao.makePersistent(dip);								
							}
						}						
					}
				}
				elaboracioDao.setHibernateTemplate(getHibernateTemplate());
				elaboracioDao.makePersistent(ela);	
				break;
			case 3: // Transvassament oli
				entradaDipositDao.setHibernateTemplate(getHibernateTemplate());
				edi = entradaDipositDao.findByTraza(trazaId, null);

				// flag per a sabre si, el diposit destí­, a l'estat anterior estava buit.
//				boolean flag = false;

				it = edi.getTraza().getTrazasForTtrCodtrapare().iterator();
				while (it.hasNext()) {
					tra = (Traza) it.next();
					// Comprovam si el seu pare es de tipus sortida.
					if (tra.getTipus().equals(new Integer(4))) {
						sortidaDipositDao.setHibernateTemplate(getHibernateTemplate());
						sdi = sortidaDipositDao.findByTraza(tra.getId(), null);

						// Invalidam el procés
						sdi.setValid(new Boolean(false));

						// Cercam els dipòsits origen i destí­.
						Diposit dipOri = sdi.getDipositBySdiCoddor();
						Diposit dipDes = sdi.getDipositBySdiCoddde();

						if (dipOri.getVolumActual() == null || dipOri.getVolumActual() == 0.0){
							dipOri.setAcidesa(sdi.getAcidesa());
							dipOri.setPartidaOli(sdi.getPartidaOli());
						}
						
						// Si origen i destí­ son diferents se li retornen els litres, a l'origen, que s'havien traspassat i se li resta el volum traspassat al diposit destí. 
						if (!dipOri.equals(dipDes)) {
							dipOri.setVolumActual(new Double((dipOri.getVolumActual() != null? dipOri.getVolumActual().doubleValue() : new Double(0).doubleValue()) + sdi.getLitres().doubleValue()));
							dipositDao.setHibernateTemplate(getHibernateTemplate());
							dipositDao.makePersistent(dipOri);
							if (dipDes != null) {
								dipDes.setVolumActual(new Double((dipDes.getVolumActual() != null? dipDes.getVolumActual().doubleValue() : new Double(0).doubleValue()) - sdi.getLitres().doubleValue()));
								dipositDao.setHibernateTemplate(getHibernateTemplate());
								dipositDao.makePersistent(dipDes);
							}
						} else {
							// Si origen i destí­ són iguals se retorna l'acidesa i la partida anterior
							// Posam el flag a true per a indicar que anteriorment, el dipòsit, no estava buit
//							flag = true;
							
							dipDes.setAcidesa(sdi.getAcidesa());
							
							// Tornam la partida de destí al seu estat original
							if (edi.getCategoriaOriginalPartida() != null){
								PartidaOli part = dipDes.getPartidaOli();
								part.setCategoriaOli(edi.getCategoriaOriginalPartida());
							}
							// Tornam a posar la partida original al dipòsit de destí
							dipDes.setPartidaOli(sdi.getPartidaOli());
							
							dipositDao.setHibernateTemplate(getHibernateTemplate());
							dipositDao.makePersistent(dipDes);
						}
						sortidaDipositDao.setHibernateTemplate(getHibernateTemplate());
						sortidaDipositDao.makePersistent(sdi);
					}
				}
				// Si no hem trobat sortida del dipòsit on origen i destí­ siguin iguals, es que anteriorment no hi havia oli.
//				if (!flag) {
				edi.setValid(new Boolean(false));
				dip = edi.getDiposit();
				if (dip.getVolumActual() > 0.0){
					EntradaDiposit edi2 = entradaDipositDao.findUltimaByDeposito(dip.getId(), new Boolean(true));
					dip.setAcidesa(edi2.getAcidesa());
					// Tornam la partida de destí al seu estat original
					if (edi.getCategoriaOriginalPartida() != null){
						PartidaOli part = dip.getPartidaOli();
						part.setCategoriaOli(edi.getCategoriaOriginalPartida());
					}
					// Tornam a posar la partida original al dipòsit de destí
					dip.setPartidaOli(edi2.getPartidaOli());
				} else {
					dip.setAcidesa(null);
					dip.setPartidaOli(null);
					dip.setVolumActual(null);
				}
				dipositDao.setHibernateTemplate(getHibernateTemplate());
				dipositDao.makePersistent(dip);

				entradaDipositDao.setHibernateTemplate(getHibernateTemplate());
				entradaDipositDao.makePersistent(edi);
				break;
			case 4: // Pèrdua oli
				sortidaDipositDao.setHibernateTemplate(getHibernateTemplate());
				sdi = sortidaDipositDao.findByTraza(trazaId, null);
				// Invalidam la sortida de dipòsit i retornam l'oli al dipòsit
				sdi.setValid(new Boolean(false));
				
				dip = sdi.getDipositBySdiCoddor();
				if (dip.getVolumActual() == null || dip.getVolumActual() == 0.0){
					dip.setPartidaOli(sdi.getPartidaOli());
					dip.setAcidesa(sdi.getAcidesa());
				}
				
				dip.setVolumActual(new Double((dip.getVolumActual() != null?dip.getVolumActual().doubleValue():new Double(0).doubleValue()) + sdi.getLitres().doubleValue()));
				
				// Comprovam si la pèrdua es va produir a l'hora d'embotellar. En aquest cas, es borren els valors de la pèrdua, informats al lot.
				lotDao.setHibernateTemplate(getHibernateTemplate());
				it = lotDao.findByDiposit(dip.getId(), new Boolean(true)).iterator();
				while (it.hasNext()) {
					lot = (Lot) it.next();
					// Si motiu, litres i data coincideixen es tracta del lot associat amb la sortida.
					if (lot.getData() != null && lot.getData().equals(sdi.getData()) && lot.getMotiuPerdua() != null && lot.getMotiuPerdua().equals(sdi.getObservacions()) && lot.getLitresPerduts() != null && lot.getLitresPerduts().equals(sdi.getLitres())) {
						lot.setLitresPerduts(null);
						lot.setMotiuPerdua(null);
						lotDao.setHibernateTemplate(getHibernateTemplate());
						lotDao.makePersistent(lot);
						
						break;
					}
				}
				
				dipositDao.setHibernateTemplate(getHibernateTemplate());
				dipositDao.makePersistent(dip);
				sortidaDipositDao.setHibernateTemplate(getHibernateTemplate());
				sortidaDipositDao.makePersistent(sdi);
				break;
			case 5: // Desqualificar oli
				entradaDipositDao.setHibernateTemplate(getHibernateTemplate());
				edi = entradaDipositDao.findByTraza(trazaId, null);

				it = edi.getTraza().getTrazasForTtrCodtrapare().iterator();
				while (it.hasNext()) {
					tra = (Traza) it.next();
					// Comprovam si el pare es de tipus sortida.
					if (tra.getTipus().equals(new Integer(4))) {
						sortidaDipositDao.setHibernateTemplate(getHibernateTemplate());
						sdi = sortidaDipositDao.findByTraza(tra.getId(), null);

						sdi.setValid(new Boolean(false));

						dip = sdi.getDipositBySdiCoddor();
						dip.setPartidaOli(sdi.getPartidaOli());

						// No s'ha fer cap acció a la partida posterior a la desqualificació. Per tant podem posar la categoria anterior
						dip.getPartidaOli().setCategoriaOli(sdi.getCategoriaOli());
						
						dipositDao.setHibernateTemplate(getHibernateTemplate());
						dipositDao.makePersistent(dip);
						sortidaDipositDao.setHibernateTemplate(getHibernateTemplate());
						sortidaDipositDao.makePersistent(sdi);
					}
				}

				edi.setValid(new Boolean(false));
				entradaDipositDao.setHibernateTemplate(getHibernateTemplate());
				entradaDipositDao.makePersistent(edi);
				break;
			case 6: // Moure dipòsit i oli
				trasllatDao.setHibernateTemplate(getHibernateTemplate());
				tdi = trasllatDao.findByTraza(trazaId);
				// Invalidam trasllat. No es modifica res del dipòsit perquè encara no s'ha acceptat el trasllat
				tdi.setValid(new Boolean(false));
				
				trasllatDao.setHibernateTemplate(getHibernateTemplate());
				trasllatDao.makePersistent(tdi);

				// Preparam email de cancel·lació (al igual que s'ha fet per a realitzar el trasllat)			
				String from = tdi.getEstablimentByTdiCodeor().getEmail();
				String to = tdi.getEstablimentByTdiCodede().getEmail();			
				usuariDao.setHibernateTemplate(getHibernateTemplate());
				if(!from.contains("@limit.es")){
					List mailControladores = usuariDao.getEmailsByGrup(rolDoControlador);
					if(mailControladores!= null){
						for(int i=0;i<mailControladores.size();i++){
							if(mailControladores.get(i)!= null){
								to += ", " + mailControladores.get(i);
							}				
						}				
					}
					List mailGestors = usuariDao.getEmailsByGrup(rolDoGestor);
					if(mailGestors!= null){
						for(int i1=0;i1<mailGestors.size();i1++){
							if(mailGestors.get(i1)!= null){
								to += ", " + mailGestors.get(i1);
							}				
						}				
					}
				}	
				// Anadimos el from también para recibir el mail
				if (from != null && to != null) {
					to += ", " + from; 
				}

				ResourceBundle bundle = ResourceBundle.getBundle("messages",new java.util.Locale("ca"));
				String subject = bundle.getString("proces.moureDiposit.cancelar.subjectMail")+" - "+tdi.getId();
				String content = bundle.getString("proces.moureDiposit.cancelar.textoMail");

				// Enviam email de cancel·lació
				ServeiCorreu correo =  new ServeiCorreu();
				correo.enviaCorreu(from, to, subject, content, null, null, emailFrom);

				// Si movem oli, hem de treure la quantitat d'oli a moure del dipòsit
				if (!tdi.getEsDiposit().booleanValue()){
					dipositDao.setHibernateTemplate(getHibernateTemplate());
					for(it = tdi.getDiposits().iterator(); it.hasNext();){
						dip = (Diposit)it.next();
						if(dip != null){					
							dip.setVolumTrasllat(null);		
							dipositDao.makePersistent(dip);
						}				
					}
				}
				break;
			case 7: // Sortida oli  ****Sortida oli DIPOSIT Venta ****
				dipositDao.setHibernateTemplate(getHibernateTemplate());
				sortidaDipositDao.setHibernateTemplate(getHibernateTemplate());
				sdi = sortidaDipositDao.findByTraza(trazaId, null);
				sdi.setValid(new Boolean(false));
				
				dip = sdi.getDipositBySdiCoddor();
				if (sdi.getObservacions() != null && sdi.getObservacions().trim().indexOf(desqualificat)== 0) { //Comprovam si es una sortida d'oli desqualificant
					entradaDipositDao.setHibernateTemplate(getHibernateTemplate());
					// Obtenim l'entrada d'origen
					for (it = sdi.getTraza().getTrazasForTtrCodtrapare().iterator(); it.hasNext();){
						Traza traza = (Traza)it.next();
						if (traza.getTipus().intValue() == Constants.CODI_TRAZA_TIPUS_ENTRADA_DIPOSIT){
							EntradaDiposit ed = entradaDipositDao.findByTraza(traza.getId(), new Boolean(true));
							if (ed.getObservacions().equals(sdi.getObservacions())){
								ed.setValid(new Boolean(false));
								// Obtenim la sortida del dipòsit d'origen
								for (Iterator it2 = ed.getTraza().getTrazasForTtrCodtrapare().iterator(); it2.hasNext();){
									Traza traza2 = (Traza)it2.next();
									if (traza2.getTipus().intValue() == Constants.CODI_TRAZA_TIPUS_SORTIDA_DIPOSIT){
										SortidaDiposit sor = sortidaDipositDao.findByTraza(traza2.getId(), new Boolean(true));
										if (sdi.getObservacions().equals(sor.getObservacions())){
											sor.setValid(new Boolean(false));
											dip = sor.getDipositBySdiCoddor();
											if (dip != null && dip.getVolumActual() <= 0.0){
												dip.setPartidaOli(sor.getPartidaOli());
												dip.setAcidesa(sor.getAcidesa());
											}
											break;
										}
									}
								}
								break;
							}
						}
					}
				}
				if(dip != null){
					dip.setVolumActual(new Double((dip.getVolumActual() != null?dip.getVolumActual().doubleValue():new Double(0).doubleValue()) + sdi.getLitres().doubleValue()));
					if (dip.getPartidaOli() == null){
						dip.setPartidaOli(sdi.getPartidaOli());
						dip.setAcidesa(sdi.getAcidesa());
					}
					dipositDao.makePersistent(dip);
				}
				sortidaDipositDao.setHibernateTemplate(getHibernateTemplate());
				sortidaDipositDao.makePersistent(sdi);
				break;
			case 8: // Embotellar
			case 17: // Envasat oliva
				entradaLotDao.setHibernateTemplate(getHibernateTemplate());
				elot = entradaLotDao.findByTraza(trazaId, null);
				elot.setValid(new Boolean(false));

				it = elot.getTraza().getTrazasForTtrCodtrapare().iterator();
				while (it.hasNext()) {
					tra = (Traza) it.next();
					// Comprovam que el seu pare es de tipus sortida dipòsit.
					if (tra.getTipus().equals(new Integer(4))) {
						sortidaDipositDao.setHibernateTemplate(getHibernateTemplate());
						sdi = sortidaDipositDao.findByTraza(tra.getId(), null);
						sdi.setValid(new Boolean(false));
					} else if (tra.getTipus().equals(Constants.CODI_TRAZA_TIPUS_SORTIDA_BOTA)) {
						sortidaBotaDao.setHibernateTemplate(getHibernateTemplate());
						sbo = sortidaBotaDao.findByTraza(tra.getId(), null);
						sbo.setValid(new Boolean(false));
					}
				}
				
				lot = elot.getLot();
				dip = lot.getDiposit();
				bot = lot.getBota();
				
				if (dip != null || bot != null){
					// Si l'embotellat no prové d'un tancament
					lot.setValid(new Boolean(false));
					if (bot == null) {
						// Si s'havia buidat el dipòsit, retornam la partida i l'acidesa
						if (dip.getVolumActual() <= 0.0){
							dip.setAcidesa(sdi.getAcidesa());
							dip.setPartidaOli(sdi.getPartidaOli());
						}
						// Retornam al dipòsit, els litres que haviem embotellat
						dip.setVolumActual(new Double((dip.getVolumActual() != null ? dip.getVolumActual().doubleValue() : new Double(0).doubleValue()) + lot.getLitresEnvassats().doubleValue()));
						dipositDao.setHibernateTemplate(getHibernateTemplate());
						dipositDao.makePersistent(dip);
					} else {
						bot.setKgOlivaRestant(new Double((bot.getKgOlivaRestant() != null ? bot.getKgOlivaRestant() : new Double(0).doubleValue()) + lot.getKgOlivaTaula().doubleValue()));
						bot.setActiu(true);
						botaDao.setHibernateTemplate(getHibernateTemplate());
						botaDao.makePersistent(bot);
						
						// Si al lot se li ha afegit oli el retornam al dipòsit
						it = elot.getTraza().getTrazasForTtrCodtrapare().iterator();
						while (it.hasNext()) {
							tra = (Traza) it.next();
							if (tra.getTipus().equals(Constants.CODI_TRAZA_TIPUS_SORTIDA_DIPOSIT)) {
								sortidaDipositDao.setHibernateTemplate(getHibernateTemplate());
								sdi = sortidaDipositDao.findByTraza(tra.getId(), null);
								dip = sdi.getDipositBySdiCoddor();
								dip.setVolumActual(new Double((dip.getVolumActual() != null ? dip.getVolumActual().doubleValue() : new Double(0).doubleValue()) + sdi.getLitres().doubleValue()));
								dipositDao.setHibernateTemplate(getHibernateTemplate());
								dipositDao.makePersistent(dip);
							}
						}
					}
					// Si el lot té etiquetes, les alliberam
					etiquetesLotDao.setHibernateTemplate(getHibernateTemplate());
					Collection etiquetes = etiquetesLotDao.findEtiquetesLotByLot(lot.getId());
					if (etiquetes != null && etiquetes.size() > 0){
						for (Iterator ite = etiquetes.iterator(); ite.hasNext();){
							EtiquetesLot eti = (EtiquetesLot)ite.next();
							eti.setUtilitzat( new Boolean(false));
							eti.setLot(null);
							etiquetesLotDao.makePersistent(eti);
						}
					}
					lotDao.setHibernateTemplate(getHibernateTemplate());
					lotDao.makePersistent(lot);
				}
				entradaLotDao.setHibernateTemplate(getHibernateTemplate());
				entradaLotDao.makePersistent(elot);
				break;
			case 9: // Etiquetar
			case 18: // Etiquetat oliva
				etiquetesLotDao.setHibernateTemplate(getHibernateTemplate());
				EtiquetesLot eti =  etiquetesLotDao.getById(trazaId);
				eti.setUtilitzat( new Boolean(false));
				eti.setLot(null);
				etiquetesLotDao.makePersistent(eti);
				break;
			case 10: // Introducció analitiques
				analiticaDao.setHibernateTemplate(getHibernateTemplate());
				ana = analiticaDao.findByTraza(trazaId);
				// Invalidam analítica.
				ana.setAnalisiFisicoQuimicValid(new Boolean(false));

				// Retornam al dipòsit la seva categoria anterior a l'analítica.
				dip = ana.getDiposit();
				for (it = ana.getTraza().getTrazasForTtrCodtrafill().iterator(); it.hasNext();){
					tra = (Traza)it.next();
					// Comprovam que el seu fill es de tipus sortida dipòsit.
					if (tra.getTipus().equals(new Integer(4))) {
						sortidaDipositDao.setHibernateTemplate(getHibernateTemplate());
						sdi = sortidaDipositDao.findByTraza(tra.getId(), null);
						
						//Restauram la partida del diòsit
						dip.setPartidaOli(sdi.getPartidaOli());
						
						// Invalidam la sortida de dipòsit
						sdi.setValid(new Boolean(false));
						// Restauram la categoria d'oli de la partida
						sdi.getPartidaOli().setCategoriaOli(sdi.getCategoriaOli());
						
					} else if (tra.getTipus().equals(new Integer(3))) {
						entradaDipositDao.setHibernateTemplate(getHibernateTemplate());
						edi = entradaDipositDao.findByTraza(tra.getId(), null);
						// Invalidam la entrada de dipòsit
						edi.setValid(new Boolean(false));
					}
				}
				dipositDao.setHibernateTemplate(getHibernateTemplate());
				dipositDao.makePersistent(dip);
				analiticaDao.setHibernateTemplate(getHibernateTemplate());
				analiticaDao.makePersistent(ana);
				break;
			case 11: // Sortida oli Lote 
			case 20: // Sortida oliva
				sortidaLotDao.setHibernateTemplate(getHibernateTemplate());
				slot = sortidaLotDao.findByTraza(trazaId, null);
				slot.setValid(new Boolean(false));
				
				lot = slot.getLot();
				Integer numBotellasActual = lot.getNumeroBotellesActuals();
				lot.setNumeroBotellesActuals(Integer.valueOf(String.valueOf(numBotellasActual.intValue() + slot.getVendaNumeroBotelles().intValue())) );
				if(lot.getNumeroBotellesActuals() > 0){
					lot.setDatafi(null);
				}
				lotDao.setHibernateTemplate(getHibernateTemplate());
				lotDao.makePersistent(lot);	
					
				sortidaLotDao.setHibernateTemplate(getHibernateTemplate());
				sortidaLotDao.makePersistent(slot);
				break;
			case 12: // Sortida d'orujo
				sortidaOrujoDao.setHibernateTemplate(getHibernateTemplate());
    			SortidaOrujo sortidaOrujo = sortidaOrujoDao.getById(trazaId);
    			sortidaOrujo.setValid(new Boolean(false));
    			sortidaOrujoDao.makePersistent(sortidaOrujo);
				break;
			case 13: // Devolució
				entradaLotDao.setHibernateTemplate(getHibernateTemplate());
				elot = entradaLotDao.findByTraza(trazaId, null);
				elot.setValid(new Boolean(false));
				
				lot = elot.getLot();
				Integer numBotellesActual = lot.getNumeroBotellesActuals();
				lot.setNumeroBotellesActuals(numBotellesActual - elot.getBotelles());
				if(lot.getNumeroBotellesActuals() > 0){
					lot.setDatafi(null);
				}
				lotDao.setHibernateTemplate(getHibernateTemplate());
				lotDao.makePersistent(lot);	
				
				sortidaLotDao.setHibernateTemplate(getHibernateTemplate());
				it = elot.getTraza().getTrazasForTtrCodtrapare().iterator();
				while (it.hasNext()) {
					tra = (Traza) it.next();
					// Comprovam que el seu pare es de tipus sortida lot.
					if (tra.getTipus().equals(new Integer(7))) {
						slot = sortidaLotDao.findByTraza(tra.getId(), null);
						Integer numBotellesDev = 0;
						if (slot.getBotellesDevolucio() != null) numBotellesDev = slot.getBotellesDevolucio();
						slot.setBotellesDevolucio(numBotellesDev - elot.getBotelles());
						sortidaLotDao.makePersistent(slot);
					}
				}
				
				entradaLotDao.setHibernateTemplate(getHibernateTemplate());
				entradaLotDao.makePersistent(elot);
				break;
			case 15: // Entrada fenoll
				partidaFonollDao.setHibernateTemplate(getHibernateTemplate());
				PartidaFonoll partidaFonoll = partidaFonollDao.findByTraza(trazaId, null);
				partidaFonoll.setValid(new Boolean(false));
				partidaFonollDao.makePersistent(partidaFonoll);
				break;
			case 16: // Elaboració oliva
				elaboracioOlivaDao.setHibernateTemplate(getHibernateTemplate());
				elo = elaboracioOlivaDao.findByTraza(trazaId, null);
				elo.setValid(new Boolean(false));
				elaboracioOlivaDao.makePersistent(elo);
				
				bot = elo.getBota();
				bot.setActiu(false);
				botaDao.setHibernateTemplate(getHibernateTemplate());
				botaDao.makePersistent(bot);
				
				partidaOlivaDao.setHibernateTemplate(getHibernateTemplate());
				partidaElaboracioDao.setHibernateTemplate(getHibernateTemplate());
				Iterator itp = elo.getPartidaElaboracions().iterator();
				while (itp.hasNext()) {
					PartidaElaboracio pel = (PartidaElaboracio) itp.next();
					PartidaOliva par = pel.getPartida();
					par.setElaboracio(null);
					par.setElaboracioOlivaTaula(null);
					par.setQuantitat(par.getQuantitat() + pel.getQuantitat());
					partidaOlivaDao.makePersistent(par);
					partidaElaboracioDao.makeTransient(pel);
				}
				
				partidaFonollDao.setHibernateTemplate(getHibernateTemplate());
				PartidaFonoll pf = bot.getPartidaFonoll();
				if (pf != null) {
					pf.setKgRestants(pf.getKgRestants() + bot.getgFonoll()/1000);
					pf.setValid(true);
					partidaFonollDao.setHibernateTemplate(getHibernateTemplate());
					partidaFonollDao.makePersistent(pf);
				}
				break;
			case 19: // Autocontrol oliva
				autocontrolDao.setHibernateTemplate(getHibernateTemplate());
				aut = autocontrolDao.findByTraza(trazaId, null);
				aut.setValid(new Boolean(false));
				autocontrolDao.makePersistent(aut);
				break;
//			case 21: // Sortida oliva de taula crua a granel
//				sortidaPartidaDao.setHibernateTemplate(getHibernateTemplate());
//				spar = sortidaPartidaDao.findByTraza(trazaId, null);
//				spar.setValid(new Boolean(false));
//				sortidaPartidaDao.makePersistent(spar);
//				break;
//			case 22: // Sortida oliva de taula en bota a granel
//				sortidaBotaGranelDao.setHibernateTemplate(getHibernateTemplate());
//				sbog = sortidaBotaGranelDao.findByTraza(trazaId, null);
//				sbog.setValid(new Boolean(false));
//				sortidaBotaGranelDao.makePersistent(sbog);
//				break;
			}
			
			tx.commit();
		} catch (Exception ex) {
			logger.error("EXCEPTION", ex);
			try {
				tx.rollback();
				throw new Exception(ex);
			} catch (HibernateException e) {
			}
		}
	}
	
	/**
	 * Edita el procés amb la traça indicada 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public void editarProces(Long trazaId, int tipoPrc, Date data, String desqualificat) throws Exception {
			
		EntradaDiposit edi = null;
		SortidaDiposit sdi = null;
		SortidaBota sbo = null;
		Elaboracio ela = null;
		ElaboracioOliva elo = null;
		Trasllat tdi = null;
		EntradaLot elot = null;
		Lot lot = null;
		SortidaLot slot = null;
		Analitica ana = null;
		AutocontrolOliva aut = null;

		Traza tra = null;
		Iterator it = null;
		
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();


			switch (tipoPrc) {
			case 1: // Entrada oliva
			case 14: // Entrada oliva taula
				partidaOlivaDao.setHibernateTemplate(getHibernateTemplate());
				PartidaOliva pao = partidaOlivaDao.findByTraza(trazaId, null);
				// Canviam la data.
				pao.setData(data);
				partidaOlivaDao.makePersistent(pao);
				break;

			case 2: // Elaboració oli
				elaboracioDao.setHibernateTemplate(getHibernateTemplate());
				ela = elaboracioDao.findByTraza(trazaId, null);
				
				// Canviam la data de l'elaboració
				ela.setData(data);
				
				//canviam la data de les entrades
				it = ela.getTraza().getTrazasForTtrCodtrafill().iterator();
				while (it.hasNext()) {
					tra = (Traza) it.next();
					if (tra.getTipus().equals(Constants.CODI_TRAZA_TIPUS_ENTRADA_DIPOSIT)) {
						entradaDipositDao.setHibernateTemplate(getHibernateTemplate());
						edi = entradaDipositDao.findByTraza(tra.getId(), new Boolean(true));
						if (edi != null) {
							edi.setData(data);							
							// Vemos si alguna de las entrada tiene algun hijo salida de deposito con olivicultor 
							Iterator it2 = edi.getTraza().getTrazasForTtrCodtrafill().iterator();
							while (it2.hasNext()) {
								tra = (Traza) it2.next();
								if (tra.getTipus().equals(Constants.CODI_TRAZA_TIPUS_SORTIDA_DIPOSIT)) {
									sortidaDipositDao.setHibernateTemplate(getHibernateTemplate());
									sdi = sortidaDipositDao.findByTraza(tra.getId(), new Boolean(true));
									if (sdi != null && sdi.getOlivicultor() != null) {
										// Canviam la data de l'oli que s'ha enduit l'olivicultor
										sdi.setData(data);
										sortidaDipositDao.makePersistent(sdi);
									}
								}
							}
							entradaDipositDao.makePersistent(edi);
						}						
					}
				}
				elaboracioDao.setHibernateTemplate(getHibernateTemplate());
				elaboracioDao.makePersistent(ela);	
				break;

			case 3: // Transvassament oli
				entradaDipositDao.setHibernateTemplate(getHibernateTemplate());
				edi = entradaDipositDao.findByTraza(trazaId, null);

				it = edi.getTraza().getTrazasForTtrCodtrapare().iterator();
				while (it.hasNext()) {
					tra = (Traza) it.next();
					// Comprovam si el seu pare es de tipus sortida.
					if (tra.getTipus().equals(Constants.CODI_TRAZA_TIPUS_SORTIDA_DIPOSIT)) {
						sortidaDipositDao.setHibernateTemplate(getHibernateTemplate());
						sdi = sortidaDipositDao.findByTraza(tra.getId(), null);

						// Canviam la data del procés
						sdi.setData(data);
						sortidaDipositDao.makePersistent(sdi);
					}
				}
				edi.setData(data);
				entradaDipositDao.makePersistent(edi);
				break;

			case 4: // Pèrdua oli
				sortidaDipositDao.setHibernateTemplate(getHibernateTemplate());
				sdi = sortidaDipositDao.findByTraza(trazaId, null);

				// Canviam la data
				sdi.setData(data);
				sortidaDipositDao.makePersistent(sdi);

				break;

			case 5: // Desqualificar oli
				entradaDipositDao.setHibernateTemplate(getHibernateTemplate());
				edi = entradaDipositDao.findByTraza(trazaId, null);

				it = edi.getTraza().getTrazasForTtrCodtrapare().iterator();
				while (it.hasNext()) {
					tra = (Traza) it.next();
					// Comprovam si el pare es de tipus sortida.
					if (tra.getTipus().equals(Constants.CODI_TRAZA_TIPUS_SORTIDA_DIPOSIT)) {
						sortidaDipositDao.setHibernateTemplate(getHibernateTemplate());
						sdi = sortidaDipositDao.findByTraza(tra.getId(), null);

						sdi.setData(data);
						sortidaDipositDao.makePersistent(sdi);
					}
				}
				edi.setData(data);
				entradaDipositDao.makePersistent(edi);
				break;

			case 6: // Moure dipòsit i oli
				// Només es modifica abans d'acceptar el trasllat
				trasllatDao.setHibernateTemplate(getHibernateTemplate());
				tdi = trasllatDao.findByTraza(trazaId);
				tdi.setData(data);
				trasllatDao.makePersistent(tdi);
				break;

			case 7: // Sortida oli  ****Sortida oli DIPOSIT Venta ****
				sortidaDipositDao.setHibernateTemplate(getHibernateTemplate());
				sdi = sortidaDipositDao.findByTraza(trazaId, null);
				sdi.setData(data);
				
				if (sdi.getObservacions() != null && sdi.getObservacions().trim().indexOf(desqualificat)== 0) { //Comprovam si es una sortida d'oli desqualificant
					entradaDipositDao.setHibernateTemplate(getHibernateTemplate());
					// Obtenim l'entrada d'origen
					for (it = sdi.getTraza().getTrazasForTtrCodtrapare().iterator(); it.hasNext();){
						Traza traza = (Traza)it.next();
						if (traza.getTipus().intValue() == Constants.CODI_TRAZA_TIPUS_ENTRADA_DIPOSIT){
							EntradaDiposit ed = entradaDipositDao.findByTraza(traza.getId(), new Boolean(true));
							if (ed.getObservacions().equals(sdi.getObservacions())){
								ed.setData(data);
								// Obtenim la sortida del dipòsit d'origen
								for (Iterator it2 = ed.getTraza().getTrazasForTtrCodtrapare().iterator(); it2.hasNext();){
									Traza traza2 = (Traza)it2.next();
									if (traza2.getTipus().intValue() == Constants.CODI_TRAZA_TIPUS_SORTIDA_DIPOSIT){
										SortidaDiposit sor = sortidaDipositDao.findByTraza(traza2.getId(), new Boolean(true));
										if (sdi.getObservacions().equals(sor.getObservacions())){
											sor.setData(data);
											break;
										}
									}
								}
								break;
							}
						}
					}
				}
				sortidaDipositDao.makePersistent(sdi);
				break;

			case 8: // Embotellar
			case 17: // Envasat oliva
				entradaLotDao.setHibernateTemplate(getHibernateTemplate());
				elot = entradaLotDao.findByTraza(trazaId, null);
				elot.setData(data);

				it = elot.getTraza().getTrazasForTtrCodtrapare().iterator();
				while (it.hasNext()) {
					tra = (Traza) it.next();
					if (tra.getTipus().equals(Constants.CODI_TRAZA_TIPUS_SORTIDA_DIPOSIT)) {
						sortidaDipositDao.setHibernateTemplate(getHibernateTemplate());
						sdi = sortidaDipositDao.findByTraza(tra.getId(), null);
						sdi.setData(data);
					} else if (tra.getTipus().equals(Constants.CODI_TRAZA_TIPUS_SORTIDA_BOTA)) {
						sortidaBotaDao.setHibernateTemplate(getHibernateTemplate());
						sbo = sortidaBotaDao.findByTraza(tra.getId(), null);
						sbo.setData(data);
					}
				}
				
				// Si a l'embotellar s'ha produït una pèrdua...-------------------------------------
				it = elot.getTraza().getTrazasForTtrCodtrafill().iterator();
				while (it.hasNext()) {
					tra = (Traza) it.next();
					if (tra.getTipus().equals(Constants.CODI_TRAZA_TIPUS_SORTIDA_DIPOSIT)) {
						sortidaDipositDao.setHibernateTemplate(getHibernateTemplate());
						sdi = sortidaDipositDao.findByTraza(tra.getId(), null);
						sdi.setData(data);
					} else if (tra.getTipus().equals(Constants.CODI_TRAZA_TIPUS_SORTIDA_BOTA)) {
						sortidaBotaDao.setHibernateTemplate(getHibernateTemplate());
						sbo = sortidaBotaDao.findByTraza(tra.getId(), null);
						sbo.setData(data);
					}
				}
				// Fi pèrdua -----------------------------------------------------------------------
				
				lot = elot.getLot();
				if (lot.getDiposit() != null || lot.getBota() != null){
					// Si l'embotellat no prové d'un tancament
					lot.setData(data);
					lotDao.setHibernateTemplate(getHibernateTemplate());
					lotDao.makePersistent(lot);
				}
				entradaLotDao.makePersistent(elot);
				break;

			case 10: // Introducció analitiques
				analiticaDao.setHibernateTemplate(getHibernateTemplate());
				ana = analiticaDao.findByTraza(trazaId);
				ana.setData(data);

				for (it = ana.getTraza().getTrazasForTtrCodtrafill().iterator(); it.hasNext();){
					tra = (Traza)it.next();
					if (tra.getTipus().equals(Constants.CODI_TRAZA_TIPUS_SORTIDA_DIPOSIT)) {
						sortidaDipositDao.setHibernateTemplate(getHibernateTemplate());
						sdi = sortidaDipositDao.findByTraza(tra.getId(), null);
						sdi.setData(data);
					} else if (tra.getTipus().equals(Constants.CODI_TRAZA_TIPUS_ENTRADA_DIPOSIT)) {
						entradaDipositDao.setHibernateTemplate(getHibernateTemplate());
						edi = entradaDipositDao.findByTraza(tra.getId(), null);
						edi.setData(data);
					}
				}
				analiticaDao.setHibernateTemplate(getHibernateTemplate());
				analiticaDao.makePersistent(ana);
				break;
				
			case 11: // Sortida oli Lote 
			case 20: // Sortida oliva
				sortidaLotDao.setHibernateTemplate(getHibernateTemplate());
				slot = sortidaLotDao.findByTraza(trazaId, null);
				slot.setVendaData(data);
				sortidaLotDao.makePersistent(slot);
				break;

			case 12: // Sortida d'orujo
				sortidaOrujoDao.setHibernateTemplate(getHibernateTemplate());
    			SortidaOrujo sortidaOrujo = sortidaOrujoDao.getById(trazaId);
    			sortidaOrujo.setData(data);
    			sortidaOrujoDao.makePersistent(sortidaOrujo);
				break;
				
			case 13: // Devolució
				entradaLotDao.setHibernateTemplate(getHibernateTemplate());
				elot = entradaLotDao.findByTraza(trazaId, null);
				elot.setData(data);
				entradaLotDao.makePersistent(elot);
				break;
				
			case 15: // Entrada fenoll
				partidaFonollDao.setHibernateTemplate(getHibernateTemplate());
				PartidaFonoll partidaFonoll = partidaFonollDao.findByTraza(trazaId, null);
				partidaFonoll.setData(data);
				partidaFonollDao.makePersistent(partidaFonoll);
				break;
				
			case 16: // Elaboració oliva
				elaboracioOlivaDao.setHibernateTemplate(getHibernateTemplate());
				elo = elaboracioOlivaDao.findByTraza(trazaId, null);
				elo.setData(data);
				elaboracioOlivaDao.makePersistent(elo);
				break;
				
			case 19: // Autocontrol oliva
				autocontrolDao.setHibernateTemplate(getHibernateTemplate());
				aut = autocontrolDao.findByTraza(trazaId, null);
				aut.setData(data);
				autocontrolDao.makePersistent(aut);
				break;
			}
			tx.commit();
		} catch (Exception ex) {
			logger.error("EXCEPTION", ex);
			try {
				tx.rollback();
				throw new Exception(ex);
			} catch (HibernateException e) {
			}
		}
	}
	
	/**
	 * Obté les dates d'inici i de fi que pot tenir el procés amb la traça indicada 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA" 
	 * <!-- end-xdoclet-definition -->
	 */
	public Date[] datesProces(Long trazaId, int tipoPrc, String desqualificat) throws Exception {
			
		EntradaDiposit edi = null;
		SortidaDiposit sdi = null;
		Elaboracio ela = null;
		PartidaOliva pao = null;
		Trasllat tdi = null;
		EntradaLot elot = null;
		SortidaLot slot = null;
		Analitica ana = null;
		Diposit dip = null;
		SortidaPartida sPartida = null;
		SortidaBotaGranel sBota = null;
		
		Traza tra = null;
		Iterator it = null;
		
		Date dataInici= null;
		Date dataFi = null;
		Date dataExecucio = null;
		
		try {
			entradaDipositDao.setHibernateTemplate(getHibernateTemplate());
			sortidaDipositDao.setHibernateTemplate(getHibernateTemplate());
			
			switch (tipoPrc) {
			case 1: // Entrada oliva
				partidaOlivaDao.setHibernateTemplate(getHibernateTemplate());
				pao = partidaOlivaDao.findByTraza(trazaId, null);
				dataExecucio = pao.getData();
				if (pao.getElaboracio() != null){
					dataFi = pao.getElaboracio().getData();
				}
				break;

			case 2: // Elaboració oli
				elaboracioDao.setHibernateTemplate(getHibernateTemplate());
				ela = elaboracioDao.findByTraza(trazaId, null);
				dataExecucio = ela.getData();

				// Data inicial
				for (it = ela.getPartidaOlivas().iterator(); it.hasNext();){
					pao = (PartidaOliva)it.next();
					if (dataInici == null || dataInici.before(pao.getData())){
						dataInici = pao.getData();
					}
				}
				
				//Data fi
				it = ela.getTraza().getTrazasForTtrCodtrafill().iterator();
				while (it.hasNext()) {
					tra = (Traza) it.next();
					if (tra.getTipus().equals(Constants.CODI_TRAZA_TIPUS_ENTRADA_DIPOSIT)) {
						edi = entradaDipositDao.findByTraza(tra.getId(), new Boolean(true));
						dip = edi.getDiposit();
						
						if (edi != null && dip.getFictici().equals(false)) {
							
							// Data Fi
							EntradaDiposit ed = entradaDipositDao.findSeguentByTrazaDeposito(dip.getId(), tra.getId());
							if ((ed != null) && (dataFi == null || ed.getData().before(dataFi))) dataFi = ed.getData();
							// Data Inici
							ed = entradaDipositDao.findAnteriorByTrazaDeposito(dip.getId(), tra.getId());
							if ((ed != null) && (dataInici == null || dataInici.before(ed.getData()))) dataInici = ed.getData();
							
						
							// Vemos si alguna de las entrada tiene algun hijo salida de deposito con olivicultor 
							Iterator it2 = edi.getTraza().getTrazasForTtrCodtrafill().iterator();
							boolean teSortides = false;
							while (it2.hasNext()) {
								Traza tra2 = (Traza) it2.next();
								if (tra2.getTipus().equals(Constants.CODI_TRAZA_TIPUS_SORTIDA_DIPOSIT)) {
									// Data Fi
									sdi = sortidaDipositDao.findSeguentByTrazaDeposito(dip.getId(), tra2.getId());
									if ((sdi != null) && (dataFi == null || sdi.getData().before(dataFi))) dataFi = sdi.getData();
									// Data Inici
									sdi = sortidaDipositDao.findAnteriorByTrazaDeposito(dip.getId(), tra2.getId());
									if ((sdi != null) && (dataInici == null || dataInici.before(sdi.getData()))) dataInici = sdi.getData();
									
									teSortides = true;
								}
							}
							
							if (!teSortides){
								// Data Fi
								sdi = sortidaDipositDao.findSeguentByTrazaDeposito(dip.getId(), tra.getId());
								if ((sdi != null) && (dataFi == null || sdi.getData().before(dataFi))) dataFi = sdi.getData();
								// Data Inici
								sdi = sortidaDipositDao.findAnteriorByTrazaDeposito(dip.getId(), tra.getId());
								if ((sdi != null) && (dataInici == null || dataInici.before(sdi.getData()))) dataInici = sdi.getData();
								
							}
						}						
					}
				}
				break;

			case 3: // Transvassament oli
				edi = entradaDipositDao.findByTraza(trazaId, null);
				dataExecucio = edi.getData();
				it = edi.getTraza().getTrazasForTtrCodtrapare().iterator();
				dip = edi.getDiposit();
				
				// Data Fi
				sdi = sortidaDipositDao.findSeguentByTrazaDeposito(dip.getId(), trazaId);
				if ((sdi != null) && (dataFi == null || sdi.getData().before(dataFi))) dataFi = sdi.getData();
				edi = entradaDipositDao.findSeguentByTrazaDeposito(dip.getId(), trazaId);
				if ((edi != null) && (dataFi == null || edi.getData().before(dataFi))) dataFi = edi.getData();
				// Data Inici
				sdi = sortidaDipositDao.findAnteriorByTrazaDeposito(dip.getId(), trazaId);
				if ((sdi != null) && (dataInici == null || dataInici.before(sdi.getData()))) dataInici = sdi.getData();
				edi = entradaDipositDao.findAnteriorByTrazaDeposito(dip.getId(), trazaId);
				if ((edi != null) && (dataInici == null || dataInici.before(edi.getData()))) dataInici = edi.getData();
				
				
				while (it.hasNext()) {
					tra = (Traza) it.next();
					// Comprovam si el seu pare es de tipus sortida.
					if (tra.getTipus().equals(Constants.CODI_TRAZA_TIPUS_SORTIDA_DIPOSIT)) {
						sdi = sortidaDipositDao.findByTraza(tra.getId(), null);
						dip = sdi.getDipositBySdiCoddor();
						// Data Fi
						sdi = sortidaDipositDao.findSeguentByTrazaDeposito(dip.getId(), tra.getId());
						if ((sdi != null) && (dataFi == null || sdi.getData().before(dataFi))) dataFi = sdi.getData();
						edi = entradaDipositDao.findSeguentByTrazaDeposito(dip.getId(), tra.getId());
						if ((edi != null) && (dataFi == null || edi.getData().before(dataFi))) dataFi = edi.getData();
						// Data Inici
						sdi = sortidaDipositDao.findAnteriorByTrazaDeposito(dip.getId(), tra.getId());
						if ((sdi != null) && (dataInici == null || dataInici.before(sdi.getData()))) dataInici = sdi.getData();
						edi = entradaDipositDao.findAnteriorByTrazaDeposito(dip.getId(), tra.getId());
						if ((edi != null) && (dataInici == null || dataInici.before(edi.getData()))) dataInici = edi.getData();
					}
				}
				break;

			case 4: // Pèrdua oli
				sdi = sortidaDipositDao.findByTraza(trazaId, null);
				dataExecucio = sdi.getData();
				dip = sdi.getDipositBySdiCoddor();
				// Data Inici
				for (it = sdi.getTraza().getTrazasForTtrCodtrapare().iterator(); it.hasNext();){
					tra = (Traza) it.next();
					if (tra.getTipus().equals(Constants.CODI_TRAZA_TIPUS_SORTIDA_DIPOSIT)) {
						SortidaDiposit sd = sortidaDipositDao.findByTraza(tra.getId(), null);
						if (dataInici == null || dataInici.before(sd.getData())){
							dataInici = sd.getData();
						}
					} else if (tra.getTipus().intValue() == Constants.CODI_TRAZA_TIPUS_ENTRADA_DIPOSIT){
						EntradaDiposit ed = entradaDipositDao.findByTraza(tra.getId(), new Boolean(true));
						if (dataInici == null || dataInici.before(ed.getData())){
							dataInici = ed.getData();
						}
					}
				}
				// El dipòsit es pot haver buidat, i llavors tornat a omplir...
				// hem d'obtenir la traça posterior!!!
				// Data fi
				sdi = sortidaDipositDao.findSeguentByTrazaDeposito(dip.getId(), trazaId);
				if ((sdi != null) && (dataFi == null || sdi.getData().before(dataFi))){
					dataFi = sdi.getData();
				} 
				edi = entradaDipositDao.findSeguentByTrazaDeposito(dip.getId(), trazaId);
				if ((edi != null) && (dataFi == null || edi.getData().before(dataFi))){
					dataFi = edi.getData();
				}
				break;

			case 5: // Desqualificar oli
				edi = entradaDipositDao.findByTraza(trazaId, null);
				dataExecucio = edi.getData();
				
				// Data Inici
				it = edi.getTraza().getTrazasForTtrCodtrapare().iterator();
				while (it.hasNext()) {
					tra = (Traza) it.next();
					// Comprovam si el pare es de tipus sortida.
					if (tra.getTipus().equals(Constants.CODI_TRAZA_TIPUS_SORTIDA_DIPOSIT)) {
						sdi = sortidaDipositDao.findByTraza(tra.getId(), null);
						for (it = ana.getTraza().getTrazasForTtrCodtrapare().iterator(); it.hasNext();){
							if (tra.getTipus().equals(Constants.CODI_TRAZA_TIPUS_SORTIDA_DIPOSIT)) {
								SortidaDiposit sd = sortidaDipositDao.findByTraza(tra.getId(), null);
								if (dataInici == null || dataInici.before(sd.getData())){
									dataInici = sd.getData();
								}
							} else if (tra.getTipus().intValue() == Constants.CODI_TRAZA_TIPUS_ENTRADA_DIPOSIT){
								EntradaDiposit ed = entradaDipositDao.findByTraza(tra.getId(), new Boolean(true));
								if (dataInici == null || dataInici.before(ed.getData())){
									dataInici = ed.getData();
								}
							}
						}
					}
				}
				
				// Data fi
				for (it = edi.getTraza().getTrazasForTtrCodtrafill().iterator(); it.hasNext();){
					tra = (Traza)it.next();
					if (tra.getTipus().equals(Constants.CODI_TRAZA_TIPUS_SORTIDA_DIPOSIT)) {
						SortidaDiposit sd = sortidaDipositDao.findByTraza(tra.getId(), null);
						if (dataFi == null || sd.getData().before(dataFi)){
							dataFi = sd.getData();
						}
					} else if (tra.getTipus().intValue() == Constants.CODI_TRAZA_TIPUS_ENTRADA_DIPOSIT){
						EntradaDiposit ed = entradaDipositDao.findByTraza(tra.getId(), new Boolean(true));
						if (dataFi == null || ed.getData().before(dataFi)){
							dataFi = ed.getData();
						}
					}
				}
				break;

			case 6: // Moure dipòsit i oli
				// Només es modifica abans d'acceptar el trasllat
				trasllatDao.setHibernateTemplate(getHibernateTemplate());
				tdi = trasllatDao.findByTraza(trazaId);
				dataExecucio = tdi.getDataAlta();
				for (it = tdi.getDiposits().iterator(); it.hasNext();){
					dip = (Diposit)it.next();
					// Data Fi
					sdi = sortidaDipositDao.findSeguentByTrazaDeposito(dip.getId(), trazaId);
					if ((sdi != null) && (dataFi == null || sdi.getData().before(dataFi))) dataFi = sdi.getData();
					edi = entradaDipositDao.findSeguentByTrazaDeposito(dip.getId(), trazaId);
					if ((edi != null) && (dataFi == null || edi.getData().before(dataFi))) dataFi = edi.getData();
					// Data Inici
					sdi = sortidaDipositDao.findAnteriorByTrazaDeposito(dip.getId(), trazaId);
					if ((sdi != null) && (dataInici == null || dataInici.before(sdi.getData()))) dataInici = sdi.getData();
					edi = entradaDipositDao.findAnteriorByTrazaDeposito(dip.getId(), trazaId);
					if ((edi != null) && (dataInici == null || dataInici.before(edi.getData()))) dataInici = edi.getData();
				}
				break;

			case 7: // Sortida oli  ****Sortida oli DIPOSIT Venta ****
				sdi = sortidaDipositDao.findByTraza(trazaId, null);
				dataExecucio = sdi.getData();
				dip = sdi.getDipositBySdiCoddor();
				// Data Inici
				sdi = sortidaDipositDao.findAnteriorByTrazaDeposito(dip.getId(), trazaId);
				if ((sdi != null) && (dataInici == null || dataInici.before(sdi.getData()))) dataInici = sdi.getData();
				edi = entradaDipositDao.findAnteriorByTrazaDeposito(dip.getId(), trazaId);
				if ((edi != null) && (dataInici == null || dataInici.before(edi.getData()))) dataInici = edi.getData();
				// Data Fi
				sdi = sortidaDipositDao.findSeguentByTrazaDeposito(dip.getId(), trazaId);
				if ((sdi != null) && (dataFi == null || sdi.getData().before(dataFi))) dataFi = sdi.getData();
				edi = entradaDipositDao.findSeguentByTrazaDeposito(dip.getId(), trazaId);
				if ((edi != null) && (dataFi == null || edi.getData().before(dataFi))) dataFi = edi.getData();				
				break;

			case 8: // Embotellar
				entradaLotDao.setHibernateTemplate(getHibernateTemplate());
				elot = entradaLotDao.findByTraza(trazaId, null);
				dataExecucio = elot.getData();
				Long traPerduaId = null;
				// Si té pèrdua, agafarem la entrada / sortida posterior a la de la pèrdua...-------
				it = elot.getTraza().getTrazasForTtrCodtrafill().iterator();
				while (it.hasNext()) {
					tra = (Traza) it.next();
					if (tra.getTipus().equals(Constants.CODI_TRAZA_TIPUS_SORTIDA_DIPOSIT)) {
						traPerduaId = tra.getId();
					}
				}
				// Fi pèrdua -----------------------------------------------------------------------
				
				it = elot.getTraza().getTrazasForTtrCodtrapare().iterator();
				while (it.hasNext()) {
					tra = (Traza) it.next();
					if (tra.getTipus().equals(Constants.CODI_TRAZA_TIPUS_SORTIDA_DIPOSIT)) {
						sortidaDipositDao.setHibernateTemplate(getHibernateTemplate());
						sdi = sortidaDipositDao.findByTraza(tra.getId(), null);
						
						// Data Fi
						SortidaDiposit sd = sortidaDipositDao.findSeguentByTrazaDeposito(sdi.getDipositBySdiCoddor().getId(), (traPerduaId == null ? tra.getId() : traPerduaId));
						if ((sd != null) && (dataFi == null || sd.getData().before(dataFi))){
							dataFi = sd.getData();
						} 
						edi = entradaDipositDao.findSeguentByTrazaDeposito(sdi.getDipositBySdiCoddor().getId(), tra.getId());
						if ((edi != null) && (dataFi == null || edi.getData().before(dataFi))){
							dataFi = edi.getData();
						}
						// Data Inici
						sd = sortidaDipositDao.findAnteriorByTrazaDeposito(sdi.getDipositBySdiCoddor().getId(), tra.getId());
						if ((sd != null) && (dataInici == null || dataInici.before(sd.getData()))){
							dataInici = sd.getData();
						} 
						edi = entradaDipositDao.findAnteriorByTrazaDeposito(sdi.getDipositBySdiCoddor().getId(), tra.getId());
						if ((edi != null) && (dataInici == null || dataInici.before(edi.getData()))){
							dataInici = edi.getData();
						}
						break;
					}
				}
				
				// Data fi
				for(it = elot.getLot().getSortidaLots().iterator(); it.hasNext();){
					slot = (SortidaLot)it.next();
					if (dataFi == null || slot.getVendaData().before(dataFi)) dataFi = slot.getVendaData();
				}
				break;

			case 10: // Introducció analitiques
				analiticaDao.setHibernateTemplate(getHibernateTemplate());
				ana = analiticaDao.findByTraza(trazaId);
				dataExecucio = ana.getData();
				
				// Data Inici
				for (it = ana.getTraza().getTrazasForTtrCodtrapare().iterator(); it.hasNext();){
					tra = (Traza) it.next();
					if (tra.getTipus().intValue() == Constants.CODI_TRAZA_TIPUS_ENTRADA_DIPOSIT){
						EntradaDiposit ed = entradaDipositDao.findByTraza(tra.getId(), new Boolean(true));
						if (dataInici == null || dataInici.before(ed.getData())){
							dataInici = ed.getData();
						}
					}
				}
				// Data fi
				for (it = ana.getTraza().getTrazasForTtrCodtrafill().iterator(); it.hasNext();){
					tra = (Traza)it.next();
					if (tra.getTipus().equals(Constants.CODI_TRAZA_TIPUS_ENTRADA_DIPOSIT)) {
						edi = entradaDipositDao.findByTraza(tra.getId(), null);
								
						// Data Fi
						SortidaDiposit sd = sortidaDipositDao.findSeguentByTrazaDeposito(edi.getDiposit().getId(), tra.getId());
						if ((sd != null) && (dataFi == null || sd.getData().before(dataFi))){
							dataFi = sd.getData();
						} 
						EntradaDiposit ed = entradaDipositDao.findSeguentByTrazaDeposito(edi.getDiposit().getId(), tra.getId());
						if ((ed != null) && (dataFi == null || ed.getData().before(dataFi))){
							dataFi = ed.getData();
						}
						break;
					}
				}
				break;
				
			case 11: // Sortida oli Lote 
				sortidaLotDao.setHibernateTemplate(getHibernateTemplate());
				slot = sortidaLotDao.findByTraza(trazaId, null);
				dataExecucio = slot.getVendaData();
				// Data Inici
				dataInici = slot.getLot().getData();
				break;

			case 12: // Sortida d'orujo
				sortidaOrujoDao.setHibernateTemplate(getHibernateTemplate());
    			SortidaOrujo sortidaOrujo = sortidaOrujoDao.getById(trazaId);
    			dataExecucio = sortidaOrujo.getData();
				break;
				
			case 13: // Devolució
				entradaLotDao.setHibernateTemplate(getHibernateTemplate());
				elot = entradaLotDao.findByTraza(trazaId, null);
				dataExecucio = elot.getData();
				for(it = elot.getTraza().getTrazasForTtrCodtrapare().iterator(); it.hasNext();){
					tra = (Traza)it.next();
					if (tra.getTipus().equals(Constants.CODI_TRAZA_TIPUS_SORTIDA_LOT)) {
						sortidaLotDao.setHibernateTemplate(getHibernateTemplate());
						slot = sortidaLotDao.findByTraza(tra.getId(), null);
						dataInici = slot.getVendaData();
						break;
					}
				}
				break;
//			case 21://sortida oliva crua a granel
//				sortidaPartidaDao.setHibernateTemplate(getHibernateTemplate());
//				sPartida = sortidaPartidaDao.findByTraza(trazaId, null);
//				dataExecucio = sPartida.getVendaData();
//				dataInici = sPartida.getPartidaOliva().getData();
//				break;
//			case 22: //sortida oliva en bota a granel
//				break;
			}
		} catch (Exception ex) {
			logger.error("EXCEPTION", ex);
		}
		
		Date[] dates = {dataInici, dataFi, dataExecucio};
		return dates;
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


	/**
	 * get the messageSourceAccessor
	 * @return the messageSourceAccessor
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
	public MessageSourceAccessor getMessageSourceAccessor() {
		return messageSourceAccessor;
	}


	/**
	 * set the messageSourceAccessor.
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
	public void setMessageSourceAccessor(MessageSourceAccessor messageSourceAccessor) {
		this.messageSourceAccessor = messageSourceAccessor;
	}

	/**
	 * set the messageSourceAccessor.
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
	public void setTipusEstablimentTafona(Integer tipusEstablimentTafona) {
		this.tipusEstablimentTafona = tipusEstablimentTafona;
	}

	/**
	 * set the messageSourceAccessor.
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
	public void setTipusEstablimentEnvasadora(Integer tipusEstablimentEnvasadora) {
		this.tipusEstablimentEnvasadora = tipusEstablimentEnvasadora;
	}

	/**
	 * set the messageSourceAccessor.
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
	public void setTipusEstablimentTafonaEnvasadora(
			Integer tipusEstablimentTafonaEnvasadora) {
		this.tipusEstablimentTafonaEnvasadora = tipusEstablimentTafonaEnvasadora;
	}
	
	class ProcesosComparatorDecreciente implements Comparator {

		public int compare(Object o1, Object o2) {
			int resultado;
			EdicioProcessosAux u1 = (EdicioProcessosAux) o1;
			EdicioProcessosAux u2 = (EdicioProcessosAux) o2;

			if (u1.getData().compareTo(u2.getData()) != 0) {
				resultado = - u1.getData().compareTo(u2.getData());
			} else {
				if ((u1.getTraza() != null) && (u2.getTraza() != null)) {
					resultado = - u1.getTraza().getId().compareTo(u2.getTraza().getId());
				} else {
					resultado = - u1.getData().compareTo(u2.getData());
				}
			}
			return resultado;
		}

		public boolean equals(Object o) {
			return this == o;
		}

	}
	
	/**
	 * Añade todos los registros necesarios para la salida d'oliva crua a granel
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public void generarSortidaOlivaCruaGranel(
			Date data, 
			Double kilos, 
			Diposit diposit, 
			String destinatari, 
			String numeroDocument,
			String tipusDocument,
			String observacions,
			Pais pais,
			Provincia provincia,
			Municipi municipi,
			Boolean desqualificar,
			String desqualificat,
			Long idPartidaOliva,
			String accio,
			String vendaMotiu,
			Long idZona,
			Establiment establiment) throws Exception{
				Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
				Transaction tx = null;
		
				try {	
		
					tx = session.beginTransaction();
					sortidaPartidaDao.setHibernateTemplate(getHibernateTemplate());
					
					PartidaOliva partidaOliva = this.partidaAmbId(idPartidaOliva);
					
					SortidaPartida sortidaPartida = new SortidaPartida();
					sortidaPartida.setVendaData(data);
					sortidaPartida.setVendaKilos(kilos);
					sortidaPartida.setVendaDestinatari(destinatari);
					sortidaPartida.setVendaNumeroDocument(numeroDocument);
					sortidaPartida.setVendaTipusDocument(tipusDocument);
					sortidaPartida.setPais(pais);
					sortidaPartida.setProvincia(provincia);
					sortidaPartida.setAccion("v");
					sortidaPartida.setMunicipi(municipi);
					sortidaPartida.setVendaMotiu(vendaMotiu);
					sortidaPartida.setValid(new Boolean(true));
					sortidaPartida.setEstabliment(establiment);
					if (observacions != null) {
						sortidaPartida.setVendaObservacions(observacions);
					} else {
						sortidaPartida.setVendaObservacions(desqualificat);
					}
					sortidaPartida.setTraza(this.insertarTraza(Constants.CODI_TRAZA_TIPUS_SORTIDA_OLIVA_CRUA_GRANEL, session));
					sortidaPartidaDao.makePersistent(sortidaPartida);
					partidaOliva.setQuantitat(new Double(partidaOliva.getQuantitat()).doubleValue() - kilos);
					partidaOlivaDao.setHibernateTemplate(getHibernateTemplate());
					partidaOlivaDao.makePersistent(partidaOliva);
					
					tx.commit();
		
				} catch (Exception ex) {
					try {
						tx.rollback();
						throw new Exception(ex);
					} catch (HibernateException e) {
						throw new Exception(e);
					}
				}
	
		}
	
	/**
	 * Añade todos los registros necesarios para la salida d'oliva en bota a granel
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public void generarSortidaBotaGranel(
			Date data, 
			Double kilos, 
			Bota bota, 
			String destinatari, 
			String numeroDocument,
			String tipusDocument,
			String observacions,
			Pais pais,
			Provincia provincia,
			Municipi municipi,
			Boolean desqualificar,
			String desqualificat,
			Long idPartidaOliva,
			String accio,
			String vendaMotiu,
			Long idZona,
			Establiment establiment) throws Exception{
				Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
				Transaction tx = null;
		
				try {	
					Bota dipositSortida = bota;
					botaDao.setHibernateTemplate(getHibernateTemplate());
					dipositSortida = botaDao.getById(bota.getId());
					tx = session.beginTransaction();
					sortidaBotaGranelDao.setHibernateTemplate(getHibernateTemplate());
					SortidaBotaGranel sortidaBota = new SortidaBotaGranel();
					sortidaBota.setVendaData(data);
					sortidaBota.setVendaKilos(kilos);
					sortidaBota.setVendaDestinatari(destinatari);
					sortidaBota.setVendaNumeroDocument(numeroDocument);
					sortidaBota.setVendaTipusDocument(tipusDocument);
					sortidaBota.setPais(pais);
					sortidaBota.setProvincia(provincia);
					sortidaBota.setAccion("v");
					sortidaBota.setMunicipi(municipi);
					sortidaBota.setVendaMotiu(vendaMotiu);
					sortidaBota.setValid(new Boolean(true));
					sortidaBota.setBota(dipositSortida);
					sortidaBota.setEstabliment(establiment);
					if (observacions != null) {
						sortidaBota.setVendaObservacions(observacions);
					} else {
						sortidaBota.setVendaObservacions(desqualificat);
					}
					sortidaBota.setTraza(this.insertarTraza(Constants.CODI_TRAZA_TIPUS_SORTIDA_OLIVA_BOTA_GRANEL, session));
					sortidaBotaGranelDao.makePersistent(sortidaBota);
					dipositSortida.setKgOlivaRestant((new Double(bota.getKgOlivaRestant()).doubleValue() - kilos));
					botaDao.setHibernateTemplate(getHibernateTemplate());
					botaDao.makePersistent(dipositSortida);
					
					tx.commit();
		
				} catch (Exception ex) {
					try {
						tx.rollback();
						throw new Exception(ex);
					} catch (HibernateException e) {
						throw new Exception(e);
					}
				}
	
		}
	
	/**
	 * Modifica la venda del lot
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition -->
	 */
	public void modificarSortidaLot(SortidaLot sortidaLot, String venda, Integer venudesInicialment) throws Exception {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			
			lotDao.setHibernateTemplate(getHibernateTemplate());
			Lot lot = lotDao.getById(sortidaLot.getLot().getId());
			
			Integer numBotRestants = null;
			if (sortidaLot.getAccioSortida().equals(venda)) {
				numBotRestants = new Integer((lot.getNumeroBotellesActuals().intValue()+venudesInicialment) - sortidaLot.getVendaNumeroBotelles().intValue());
				lot.setNumeroBotellesActuals(numBotRestants);
				
				lotDao.setHibernateTemplate(getHibernateTemplate());
				lotDao.makePersistent(lot);
			}
			
			EntradaLot entradaPare = null;
			entradaLotDao.setHibernateTemplate(getHibernateTemplate());
			Iterator itEntradaLot = entradaLotDao.findByLotZona(lot.getId(), lot.getZona().getId(), new Boolean(true)).iterator();
			if (itEntradaLot.hasNext()) {
				entradaPare = (EntradaLot) itEntradaLot.next();
			}
			if(entradaPare != null){
				sortidaLot.setTraza(this.insertarTraza(Constants.CODI_TRAZA_TIPUS_SORTIDA_LOT, session));
				sortidaLot.getTraza().getTrazasForTtrCodtrapare().add(entradaPare.getTraza());
			}
			
			sortidaLotDao.setHibernateTemplate(getHibernateTemplate());
			sortidaLotDao.makePersistent(sortidaLot);
			
			tx.commit();
			
		} catch (Exception ex) {
			logger.error("EXCEPTION", ex);
			try {
				tx.rollback();
				throw new Exception(ex);
			} catch (HibernateException e) {}
		}
	}
	
}