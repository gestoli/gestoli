/**
 * 
 */
package es.caib.gestoli.logic.ejb;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import javax.ejb.SessionContext;

import org.hibernate.Hibernate;
import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.dao.AnaliticaDao;
import es.caib.gestoli.logic.dao.AutocontrolOlivaDao;
import es.caib.gestoli.logic.dao.BotaDao;
import es.caib.gestoli.logic.dao.CampanyaDao;
import es.caib.gestoli.logic.dao.ConsultaBasicaGeneralDao;
import es.caib.gestoli.logic.dao.DescomposicioPartidaOlivaDao;
import es.caib.gestoli.logic.dao.DescomposicioPlantacioDao;
import es.caib.gestoli.logic.dao.DipositDao;
import es.caib.gestoli.logic.dao.ElaboracioDao;
import es.caib.gestoli.logic.dao.ElaboracioOlivaDao;
import es.caib.gestoli.logic.dao.EntradaDipositDao;
import es.caib.gestoli.logic.dao.EntradaLotDao;
import es.caib.gestoli.logic.dao.EstablimentDao;
import es.caib.gestoli.logic.dao.EtiquetesLotDao;
import es.caib.gestoli.logic.dao.FincasDao;
import es.caib.gestoli.logic.dao.LotDao;
import es.caib.gestoli.logic.dao.OlivicultorDao;
import es.caib.gestoli.logic.dao.PartidaFonollDao;
import es.caib.gestoli.logic.dao.PartidaOlivaDao;
import es.caib.gestoli.logic.dao.SortidaBotaDao;
import es.caib.gestoli.logic.dao.SortidaBotaGranelDao;
import es.caib.gestoli.logic.dao.SortidaDipositDao;
import es.caib.gestoli.logic.dao.SortidaLotDao;
import es.caib.gestoli.logic.dao.SortidaOrujoDao;
import es.caib.gestoli.logic.dao.SortidaPartidaDao;
import es.caib.gestoli.logic.dao.TrasllatDao;
import es.caib.gestoli.logic.model.Analitica;
import es.caib.gestoli.logic.model.Bota;
import es.caib.gestoli.logic.model.Campanya;
import es.caib.gestoli.logic.model.CategoriaOli;
import es.caib.gestoli.logic.model.ConsultaBasicaGeneral;
import es.caib.gestoli.logic.model.DescomposicioPartidaOliva;
import es.caib.gestoli.logic.model.Diposit;
import es.caib.gestoli.logic.model.Elaboracio;
import es.caib.gestoli.logic.model.ElaboracioOliva;
import es.caib.gestoli.logic.model.EntradaDiposit;
import es.caib.gestoli.logic.model.EntradaLot;
import es.caib.gestoli.logic.model.EtiquetesLot;
import es.caib.gestoli.logic.model.Lot;
import es.caib.gestoli.logic.model.Olivicultor;
import es.caib.gestoli.logic.model.PartidaElaboracio;
import es.caib.gestoli.logic.model.PartidaOli;
import es.caib.gestoli.logic.model.PartidaOliva;
import es.caib.gestoli.logic.model.SortidaBota;
import es.caib.gestoli.logic.model.SortidaDiposit;
import es.caib.gestoli.logic.model.SortidaLot;
import es.caib.gestoli.logic.model.Trasllat;
import es.caib.gestoli.logic.model.Traza;
import es.caib.gestoli.logic.model.VarietatOli;
import es.caib.gestoli.logic.util.Constants;
import es.caib.gestoli.logic.util.TrazabilitatResumida;



/**
 *
 * <!-- begin-user-doc -->
 * A generated session bean
 * <!-- end-user-doc -->
 *
 * <!-- begin-xdoclet-definition --> 
 * @ejb.bean name="OliConsultaEjb"	
 *           description="An EJB named OliConsultaEjb"
 *           display-name="OliConsultaEjb"
 *           jndi-name="OliConsultaEjb"
 *           local-jndi-name="OliConsultaEjbLocal"
 *           type="Stateless" 
 *           transaction-type="Container"
 * @ejb.transaction type="Required"
 * 
 * <!-- end-xdoclet-definition --> 
*/
public abstract class OliConsultaEjbBean implements javax.ejb.SessionBean {

	private static final long serialVersionUID = 1L;

	private static final String DESTI_EMBOTELLAT = "EMBOTELLAT";
	
	private DipositDao dipositDao;
	private BotaDao botaDao;
	private SessionContext sessionContext;
	private EstablimentDao establimentDao;
	private DescomposicioPlantacioDao descomposicioPlantacioDao;
	private DescomposicioPartidaOlivaDao descomposicioPartidaOlivaDao;
	private CampanyaDao campanyaDao;
	private PartidaOlivaDao partidaOlivaDao;
	private OlivicultorDao olivicultorDao;
	private AnaliticaDao analiticaDao;
	private ElaboracioDao elaboracioDao;
	private ElaboracioOlivaDao elaboracioOlivaDao;
	private EntradaDipositDao entradaDipositDao;
	private SortidaDipositDao sortidaDipositDao;
	private SortidaBotaDao sortidaBotaDao;
	private LotDao lotDao;
	private TrasllatDao trasllatDao;
	private SortidaLotDao sortidaLotDao;
	private EntradaLotDao entradaLotDao;
	private FincasDao fincasDao;
	private SortidaOrujoDao sortidaOrujoDao;
	private EtiquetesLotDao etiquetesLotDao;
	private PartidaFonollDao partidaFonollDao;
	private HibernateTemplate hibernateTemplate;
	private AutocontrolOlivaDao autocontrolOlivaDao;
	private SortidaPartidaDao sortidaPartidaDao;
	private SortidaBotaGranelDao sortidaBotaGranelDao;
	private ConsultaBasicaGeneralDao consultaBasicaGeneralDao;
	
	private static Logger logger = Logger.getLogger("es.caib.gestoli.logic.ejb.oliConsultesEjb");
	
	/**
	 * Creacio del EJB <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.create-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public void ejbCreate() {
		establimentDao = new EstablimentDao();
		dipositDao = new DipositDao();
		botaDao = new BotaDao();
		descomposicioPlantacioDao = new DescomposicioPlantacioDao();
		descomposicioPartidaOlivaDao = new DescomposicioPartidaOlivaDao();
		campanyaDao = new CampanyaDao();
		partidaOlivaDao = new PartidaOlivaDao();
		olivicultorDao = new OlivicultorDao();
		analiticaDao = new AnaliticaDao();
		elaboracioDao = new ElaboracioDao();
		elaboracioOlivaDao = new ElaboracioOlivaDao();
		entradaDipositDao = new EntradaDipositDao();
		sortidaDipositDao = new SortidaDipositDao();
		sortidaBotaDao = new SortidaBotaDao();
		lotDao = new LotDao();
		trasllatDao = new TrasllatDao();
		sortidaLotDao = new SortidaLotDao();
		entradaLotDao = new EntradaLotDao();
		fincasDao = new FincasDao();
		sortidaOrujoDao = new SortidaOrujoDao();
		etiquetesLotDao = new EtiquetesLotDao();
		partidaFonollDao = new PartidaFonollDao();
		autocontrolOlivaDao = new AutocontrolOlivaDao();
		sortidaPartidaDao = new SortidaPartidaDao();
		sortidaBotaGranelDao = new SortidaBotaGranelDao();
		consultaBasicaGeneralDao = new ConsultaBasicaGeneralDao();
	}

	/**
	 * Només serveix per poder especificar els permisos amb xdoclet. <!--
	 * begin-xdoclet-definition -->
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
	public void ejbRemove() {}
	

	
	/**
	 * Només serveix per poder especificar els permisos amb xdoclet. <!--
	 * begin-xdoclet-definition -->
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
	public void setSessionContext(SessionContext context) {
      sessionContext = context;
  }

		
	/**
	 * Cerca la campanya actual
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
	public Long campanyaCercaActual() {
		campanyaDao.setHibernateTemplate(getHibernateTemplate());
		return campanyaDao.getCampanyaActual();
	}


	/**
	 * Busca todas las plantaciones y la su detalle de los olivicultores dados de alta en la DO de la campanya
	 * actual
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection findDescomposicioPlantacioPerOlivicultor(Long idOlivicultor) {
		descomposicioPlantacioDao.setHibernateTemplate(getHibernateTemplate());
		return descomposicioPlantacioDao.findDescomposicioPlantacioPerOlivicultor(idOlivicultor);
	}

	/**
	 * Busca todas las plantaciones y la su detalle de los olivicultores dados de alta en la DO de la campanya
	 * actual
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection findDescomposicioPlantacioPerOlivicultorResum(Long idOlivicultor) {
		descomposicioPlantacioDao.setHibernateTemplate(getHibernateTemplate());
		return descomposicioPlantacioDao.findDescomposicioPlantacioPerOlivicultorResum(idOlivicultor);
	}
	
	
	/**
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection findOlivicultorsByEstabliment(Long idEstabliemnt) {
		olivicultorDao.setHibernateTemplate(getHibernateTemplate());
		return olivicultorDao.findOlivicultorsByEstabliment(idEstabliemnt);
	}
	
	/**
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection trasabilitatOlivicultor(Date dataInici, Date dataFi, Long idOlivicultor, Long idEstabliment) {
		List resultat = new ArrayList();
		olivicultorDao.setHibernateTemplate(getHibernateTemplate());
		Olivicultor olivicultor = olivicultorDao.getById(idOlivicultor);
		
		partidaOlivaDao.setHibernateTemplate(getHibernateTemplate());
		Collection partides = partidaOlivaDao.findByOlivicultorEntreDatesAndEstabliment(dataInici, dataFi, olivicultor.getId(), idEstabliment, true);
		
		Set elaboracions = new HashSet();
		
		for(Iterator it = partides.iterator(); it.hasNext();){
			PartidaOliva p = (PartidaOliva)it.next();
			if (p.getElaboracio() != null)
				elaboracions.add(p.getElaboracio());
		}
		
		for(Iterator ite = elaboracions.iterator(); ite.hasNext();){
			Elaboracio e = (Elaboracio)ite.next();
			PartidaOliva p = null;
			
			for(Iterator itp = e.getPartidaOlivas().iterator(); itp.hasNext();){
				p = (PartidaOliva)itp.next();
				
				resultat.add(new TrasabilitatOlivicultor(p,null,p.getData(),Constants.ACCIO_PARTIDA,null,null,null,null,null,null, p.getTotalKilos(),p.getOlivicultor()));
			}
			
			Collection fills = e.getTraza().getTrazasForTtrCodtrafill();
				
			if(fills != null && fills.size() > 0){
				try {
					emplenarResultatTrasabilitatOlivicultor(p,e,resultat, fills, Constants.ACCIO_PARTIDA);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		
//		Collections.sort(resultat);
		return resultat;
	}
	
	/**
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection trasabilitatPartides(Date finicio, Date ffin, Long estId) {
		List resultat = new ArrayList();
		
		partidaOlivaDao.setHibernateTemplate(getHibernateTemplate());
		Collection partides = partidaOlivaDao.getPartidasEntreDiasEnEstablecimiento(finicio, ffin, estId, null);
		
		Set elaboracions = new HashSet();
		
		for(Iterator it = partides.iterator(); it.hasNext();){
			PartidaOliva p = (PartidaOliva)it.next();
			if (p.getElaboracio() != null)
				elaboracions.add(p.getElaboracio());
		}
		
//		for(Iterator it = partides.iterator(); it.hasNext();){
//			PartidaOliva p = (PartidaOliva)it.next();
//			
//			Elaboracio elaboracio = p.getElaboracio();
//			
//			resultat.add(new TrasabilitatOlivicultor(p,null,p.getData(),Constants.ACCIO_PARTIDA,null,null,null,null,null,null, p.getTotalKilos(),p.getOlivicultor()));
//			
//			if(elaboracio != null){
//				Collection fills = elaboracio.getTraza().getTrazasForTtrCodtrafill();
//				
//				if(fills != null && fills.size() > 0){
//					try {
//						emplenarResultatTrasabilitatOlivicultor(p,elaboracio,resultat, fills, Constants.ACCIO_PARTIDA);
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//				}
//			}
//		}
		
		for(Iterator ite = elaboracions.iterator(); ite.hasNext();){
			Elaboracio e = (Elaboracio)ite.next();
			PartidaOliva p = null;
			
			for(Iterator itp = e.getPartidaOlivas().iterator(); itp.hasNext();){
				p = (PartidaOliva)itp.next();
				
				resultat.add(new TrasabilitatOlivicultor(p,null,p.getData(),Constants.ACCIO_PARTIDA,null,null,null,null,null,null, p.getTotalKilos(),p.getOlivicultor()));
			}
			
			Collection fills = e.getTraza().getTrazasForTtrCodtrafill();
				
			if(fills != null && fills.size() > 0){
				try {
					emplenarResultatTrasabilitatOlivicultor(p,e,resultat, fills, Constants.ACCIO_PARTIDA);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		
//		Collections.sort(resultat);
		return resultat;
	}
	
	private void emplenarResultatTrasabilitatOlivicultor(PartidaOliva p, Elaboracio e, List resultat, Collection traces, Integer accioPare) throws Exception{
		if(traces != null && traces.size() > 0){
			for(Iterator it2 = traces.iterator(); it2.hasNext();){
				Traza traca = (Traza)it2.next();
				Integer accio = null;
				
				switch(traca.getTipus()){
				
					case Constants.CODI_TRAZA_TIPUS_TRASLLAT_OLI:
					case Constants.CODI_TRAZA_TIPUS_TRASLLAT_DIPOSIT:	// Un cop enviat a un altre establiment, ja no mostram res
					case Constants.CODI_TRAZA_TIPUS_ANALITICA:	// Per a seguir la traçabilitat podem obviar el node d'analítica
						break;
				
					// Sortides
					case Constants.CODI_TRAZA_TIPUS_SORTIDA_DIPOSIT: 
					case Constants.CODI_TRAZA_TIPUS_VENDA_OLI:
						sortidaDipositDao.setHibernateTemplate(getHibernateTemplate());
						SortidaDiposit sd = sortidaDipositDao.findByTraza(traca.getId(), true);
						
						boolean emplenar = false;
						
						if(sd.getLot() != null){
							accio = Constants.ACCIO_EMBOTELLAT;
							emplenar = true;
						} else if(sd.getDesti() != null && sd.getDesti().equalsIgnoreCase(Constants.DESTI_PERDUA)){
							accio = Constants.ACCIO_PERDUA;
							emplenar = true;
						} else if(sd.getDesti() != null && (sd.getDesti().startsWith(Constants.DESTI_TORNAR) || sd.getDesti().startsWith(Constants.DESTI_TRASLLAT))){
							accio = Constants.ACCIO_TRASLLAT_DIPOSIT;
							emplenar = false;
						} else if(sd.getDipositBySdiCoddde() == null){
							accio = Constants.ACCIO_SORTIDA_GRANEL;
							emplenar = true;
						} else if(sd.getDesti() != null && sd.getDesti().equals(Constants.DESTI_TANCAMENT)){
							accio = Constants.ACCIO_TANCAMENT;
							emplenar = true;
						} else if(sd.getDesti() != null && sd.getDesti().equals(Constants.DESTI_DESQUALIFICAT)){
							accio = Constants.ACCIO_DESQUALIFICAT;
							emplenar = true;
						} else if(sd.getDesti() != null && sd.getDesti().equalsIgnoreCase(Constants.DESTI_ANALITICA)){
							accio = Constants.ACCIO_ANALITICA;
							emplenar = true;
						} else { 
							accio = Constants.ACCIO_TRASBALS;
							emplenar = true;
						}
						
						
						resultat.add(new TrasabilitatOlivicultor(p,e,sd.getData(),accio,sd.getDipositBySdiCoddor(),sd.getDipositBySdiCoddde(),sd.getLot(),sd,null,null, sd.getLitres(),p.getOlivicultor()));
						
						if(emplenar)
							emplenarResultatTrasabilitatOlivicultor(p, e, resultat, sd.getTraza().getTrazasForTtrCodtrafill(), accio);
						
						break;
					
					case Constants.CODI_TRAZA_TIPUS_SORTIDA_LOT:	// Els embotellats els tractem aquí
						sortidaLotDao.setHibernateTemplate(getHibernateTemplate());
						SortidaLot sl = sortidaLotDao.findByTraza(traca.getId(), true);
						
						resultat.add(new TrasabilitatOlivicultor(p,e,sl.getVendaData(),Constants.ACCIO_SORTIDA_LOT,null, null, sl.getLot(),null, sl,null, sl.getVendaLitres(),p.getOlivicultor()));
						
						emplenarResultatTrasabilitatOlivicultor(p, e, resultat, sl.getTraza().getTrazasForTtrCodtrafill(), Constants.ACCIO_SORTIDA_LOT);
						
						break;
					case Constants.CODI_TRAZA_TIPUS_ENTRADA_LOT:	// Els embotellats ja estan tractats, però necessitem seguir la traçabilitat
						entradaLotDao.setHibernateTemplate(getHibernateTemplate());
						EntradaLot el = entradaLotDao.findByTraza(traca.getId(), true);
						
						emplenarResultatTrasabilitatOlivicultor(p, e, resultat, el.getTraza().getTrazasForTtrCodtrafill(), accio);
						break;
						
					case Constants.CODI_TRAZA_TIPUS_ENTRADA_DIPOSIT:
						entradaDipositDao.setHibernateTemplate(getHibernateTemplate());
						EntradaDiposit ed = entradaDipositDao.findByTraza(traca.getId(), true);
						boolean mostrar = false;
						
						if(ed.getElaboracio() != null) {
							if (ed.getElaboracio().getId().equals(e.getId())){
								accio = Constants.ACCIO_ELABORACIO;
								mostrar = true;
							}
						} else {
							accio = Constants.ACCIO_ENTRADA_DIPOSIT;
							if (!accioPare.equals(Constants.ACCIO_TRASBALS))
								mostrar = true;
						}
						if(mostrar)
							resultat.add(new TrasabilitatOlivicultor(p,e,ed.getData(),accio,null,ed.getDiposit(),null,null,null,ed, ed.getLitres(),p.getOlivicultor()));
						
						emplenarResultatTrasabilitatOlivicultor(p, e, resultat, ed.getTraza().getTrazasForTtrCodtrafill(), accio);
						
						break;
					
					case Constants.CODI_TRAZA_TIPUS_PARTIDA: 	// No hauria d'entrar cap traça d'aquests tipus, 
					case Constants.CODI_TRAZA_TIPUS_ELABORACIO:	// ja s'haurien d'haver tractat abans.
					default:
						throw new Exception("CAS NO CONTEMPLAT. Tipus traça: " + traca.getTipus());
				}
			}
		}
	}

//// Per a obtenir les coordenades per provincia - municipi - poligon - parcela:
//// ---------------------------------------------------------------------------
//	/**
//	 * Funció per consultar les coordenades geogràfiques d'un poligon i una parcela.
//	 * 
//	 * @param prov
//	 * @param muni
//	 * @param poligon
//	 * @param parcela
//	 */
//	public String[] consultaCoordenades(Long prov, Long muni, String poligon, String parcela) {
//		String param1 = "";
//		String param2 = "";
//		String coordenadaX = "";
//		String coordenadaY = "";
//		
//		try {
//			Provincia provincia = provinciaDao.getById(prov);
//	    	Municipi municipi = municipiDao.getById(muni);
//			
//	    	// Construim la ruta de la petició per obtenir el codi de catastre.
//	    	String ruta = "http://ovc.catastro.meh.es/ovcservweb/OVCSWLocalizacionRC/OVCCallejero.asmx/Consulta_DNPPP";
//	    	ruta += "?Provincia=" + URLEncoder.encode(normalitzarILlevarAccents(provincia.getNom()), "UTF-8");
//	    	ruta += "&Municipio=" + URLEncoder.encode(normalitzarILlevarAccents(municipi.getNom()), "UTF-8");
//	    	ruta += "&Poligono=" + poligon;
//	    	ruta += "&Parcela=" + parcela;
//	   		
//	    	URL url = new URL(ruta);
//	    	BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
//	    	String str = "";
//	    	List<String> xml = new ArrayList<String>();
//	    	// Obtenim la resposta i cercam l'informació que ens interessa.
//	    	while ((str = br.readLine()) != null) {
//	    		xml.add(str);
//	    		if ((str.indexOf("<pc1>") != -1) && (param1.equals(""))) {
//	    			param1 = getTagContent(str, "pc1");
//	    		} else if ((str.indexOf("<pc2>") != -1) && (param2.equals(""))) {
//	    			param2 = getTagContent(str, "pc2");
//	    		}
//	    	}
//	    	br.close();
//	    	
//	    	// Si hem obtingut l'informació que cercavem, construim una altra petició per obtenir les coordenades.
//	    	if ((!param1.equals("")) && (!param2.equals(""))) {
//	    		String catastre = param1 + "" + param2;
//	    		
//	    		String rutaLocalitzacio = "http://ovc.catastro.meh.es/ovcservweb/OVCSWLocalizacionRC/OVCCoordenadas.asmx/Consulta_CPMRC";
//	    		rutaLocalitzacio += "?Provincia=" + URLEncoder.encode(normalitzarILlevarAccents(provincia.getNom()), "UTF-8");
//	    		rutaLocalitzacio += "&Municipio=" + URLEncoder.encode(normalitzarILlevarAccents(municipi.getNom()), "UTF-8");
//	    		rutaLocalitzacio += "&SRS=" + URLEncoder.encode("EPSG:4326", "UTF-8");
//	    		rutaLocalitzacio += "&RC=" + catastre;
//
//	    		URL urlLocalitzacio = new URL(rutaLocalitzacio);
//		    	BufferedReader brLocalitzacio = new BufferedReader(new InputStreamReader(urlLocalitzacio.openStream()));
//		    	str = "";
//		    	List<String> xmlLocalitzacio = new ArrayList<String>();
//		    	// Obtenim la resposta i cercam l'informació que ens interessa.
//		    	while ((str = brLocalitzacio.readLine()) != null) {
//		    		xmlLocalitzacio.add(str);
//		    		if ((str.indexOf("<xcen>") != -1) && (coordenadaX.equals(""))) {
//		    			coordenadaX = getTagContent(str, "xcen");
//		    		} else if ((str.indexOf("<ycen>") != -1) && (coordenadaY.equals(""))) {
//		    			coordenadaY = getTagContent(str, "ycen");
//		    		}
//		    	}
//		    	brLocalitzacio.close();
//		    	
//		    	// Si hem obtingut les coordenades, les guardam a la base de dades.
//		    	if ((!coordenadaX.equals("")) && (!coordenadaY.equals(""))) {
//		    		String[] coords = {coordenadaX, coordenadaY};
//		    		return coords;
//		    	}
//	    	}
//		} catch (Exception e) { }
//		return null;
//	}
//	
//	/**
//	 * @param source
//	 * @param tagName
//	 * @return Contingut del tag xml
//	 */
//	public String getTagContent(String source, String tagName) {
//		String retorn = "";
//		try {
//			if (((source != null) && (!source.equals(""))) && ((tagName != null) && (!tagName.equals("")))) {
//				String sourceAux = "";
//				int inici = source.indexOf("<" + tagName + ">");
//				int fi = source.indexOf("</" + tagName + ">");
//				sourceAux = source.substring(inici, fi);
//				sourceAux = sourceAux.replaceAll("<" + tagName + ">", "");
//				retorn = sourceAux;
//			}
//		} catch (Exception e) { }
//		
//		return retorn;
//	}
//	
//	private String normalitzarILlevarAccents(String str) {
//		String resultat = str.toUpperCase();
//		  
//		return resultat.
//			replaceAll("[ÀÁ]","A").
//			replaceAll("[ÈÉ]","E").
//			replaceAll("Í","I").
//			replaceAll("[ÓÒ]","O").
//			replaceAll("Ú","U");
//	}
//	
//// ---------------------------------------------------------------------------
	
	public class  TrasabilitatOlivicultor{
		private PartidaOliva partidaOliva;
		private Elaboracio elaboracio;
		private Date data;
		private Integer accio;
		private Diposit dipositOrigen;
		private Diposit dipositDesti;
		private Lot lot;
		private SortidaDiposit sortidaDiposit;
		private SortidaLot sortidaLot;
		private EntradaDiposit entradaDiposit;
		private Double quantitat;
		private Olivicultor olivicultor;
		
		
		public TrasabilitatOlivicultor(PartidaOliva partidaOliva,
				Elaboracio elaboracio, Date data, Integer accio,
				Diposit dipositOrigen, Diposit dipositDesti, Lot lot,
				SortidaDiposit sortidaDiposit, SortidaLot sortidaLot,
				EntradaDiposit entradaDiposit, Double quantitat, Olivicultor olivicultor) {
			super();
			this.partidaOliva = partidaOliva;
			this.elaboracio = elaboracio;
			this.data = data;
			this.accio = accio;
			this.dipositOrigen = dipositOrigen;
			this.dipositDesti = dipositDesti;
			this.lot = lot;
			this.sortidaDiposit = sortidaDiposit;
			this.sortidaLot = sortidaLot;
			this.entradaDiposit = entradaDiposit;
			this.quantitat = quantitat;
			this.olivicultor = olivicultor;
		}


		public PartidaOliva getPartidaOliva() {
			return partidaOliva;
		}


		public Elaboracio getElaboracio() {
			return elaboracio;
		}


		public Date getData() {
			return data;
		}


		public Integer getAccio() {
			return accio;
		}


		public Diposit getDipositOrigen() {
			return dipositOrigen;
		}


		public Diposit getDipositDesti() {
			return dipositDesti;
		}


		public Lot getLot() {
			return lot;
		}


		public SortidaDiposit getSortidaDiposit() {
			return sortidaDiposit;
		}


		public SortidaLot getSortidaLot() {
			return sortidaLot;
		}


		public EntradaDiposit getEntradaDiposit() {
			return entradaDiposit;
		}

		public Double getQuantitat() {
			return quantitat;
		}
		
		public Olivicultor getOlivicultor() {
			return olivicultor;
		}


		public int compareTo(Object o){
			TrasabilitatOlivicultor trasabilitatOlivicultor = (TrasabilitatOlivicultor)o;
			
			if (this.partidaOliva.getId().compareTo(trasabilitatOlivicultor.partidaOliva.getId()) == 0){
				if (this.data.compareTo(trasabilitatOlivicultor.data) == 0){
					if (this.accio.compareTo(trasabilitatOlivicultor.accio) == 0){
						if (this.entradaDiposit != null) {
							return this.entradaDiposit.getId().compareTo(trasabilitatOlivicultor.entradaDiposit.getId());
						} else if (this.sortidaDiposit != null) {
							return this.sortidaDiposit.getId().compareTo(trasabilitatOlivicultor.sortidaDiposit.getId());
						} else if (this.sortidaLot != null) {
							return this.sortidaLot.getId().compareTo(trasabilitatOlivicultor.sortidaLot.getId());
						} else if (this.lot != null) {
							return this.lot.getId().compareTo(trasabilitatOlivicultor.lot.getId());
						} else return this.quantitat.compareTo(trasabilitatOlivicultor.quantitat);
					} else {
						return this.accio.compareTo(trasabilitatOlivicultor.accio);
					}
				} else {
					return this.data.compareTo(trasabilitatOlivicultor.data);
				}
			} else {
				return this.partidaOliva.getId().compareTo(trasabilitatOlivicultor.partidaOliva.getId());
			}
		}
	}

	/**
	 * Busca todas las partidas de oliva y la su detalle de los olivicultores dados de alta en la DO de la campanya
	 * actual
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection findDescomposicioPartidaOlivaPerOlivicultor(Long idOlivicultor) {
		descomposicioPartidaOlivaDao.setHibernateTemplate(getHibernateTemplate());
		return descomposicioPartidaOlivaDao.findDescomposicioPartidaOlivaPerOlivicultor(idOlivicultor);
	}


	/**
	 * Busca todas las entradas de oliva y el detalle de los olivicultores
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection entradesOlivaConsulta(Date dataInici, Date dataFi, Long idEstabliment) {
		partidaOlivaDao.setHibernateTemplate(getHibernateTemplate());
		return partidaOlivaDao.getPartidasEntreDiasEnEstablecimiento(dataInici, dataFi, idEstabliment, new Boolean(true));
	}
	
	/**
	 * Devuelve partidas de oliva por establecimiento y entre 2 fechas
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection findPartidasEntreDiasEnEstablecimiento(Date finicio, Date ffin, Long estId) {
		partidaOlivaDao.setHibernateTemplate(getHibernateTemplate());
		return partidaOlivaDao.getPartidasEntreDiasEnEstablecimiento(finicio, ffin, estId, null);
	}	
	
	
	/**
	 * Devuelve el codigo de olivicultor original a partir del UID de la tarjeta. Es nulo si no existe.
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Long findCodigoOlivicultorFromUidTarjeta(String uid, Integer camp) {
		olivicultorDao.setHibernateTemplate(getHibernateTemplate());
		return olivicultorDao.getCodigoOlivicultorFromUidTarjeta(uid,camp);
	}		
	

	/**
	 * Devuelve analiticas de oliva por deposito
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection findAnaliticasParaDeposito(Long dipositId) {
		analiticaDao.setHibernateTemplate(getHibernateTemplate());
		return analiticaDao.findByDiposit(dipositId);
	}
	
	/**
	 * Devuelve analiticas de oliva por deposito
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection findAutocontrolPerLot(Long lotId) {
		autocontrolOlivaDao.setHibernateTemplate(getHibernateTemplate());
		return autocontrolOlivaDao.findByLot(lotId);
	}
	
	
	
	
	/**
	 * Devuelve analiticas de oliva por bota
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection findAutocontrolPerBota(Long botaId) {
		autocontrolOlivaDao.setHibernateTemplate(getHibernateTemplate());
		return autocontrolOlivaDao.findByBota(botaId); 
	}
	
	
	/**
	 * Busca todas las elaboraciones 
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection oliElaboratConsulta(Date dataInici, Date dataFi, Long idEstabliment, boolean asc, Boolean valid) {
		elaboracioDao.setHibernateTemplate(getHibernateTemplate());
		return elaboracioDao.getElaboracionesEntreDiasEnEstablecimiento(dataInici, dataFi, idEstabliment, asc, valid);
	}
	
	
	/**
	 * Busca todas las elaboraciones 
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection olivaElaboradaConsulta(Date dataInici, Date dataFi, Long idEstabliment, boolean asc, Boolean valid) {
		elaboracioOlivaDao.setHibernateTemplate(getHibernateTemplate());
		return elaboracioOlivaDao.getElaboracionesEntreDiasEnEstablecimiento(dataInici, dataFi, idEstabliment, asc, valid);
	}
	
	/**
	 * Busca todas las elaboraciones desglosadas por partidas
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection registreElaboracioOlivaConsulta(Date dataInici, Date dataFi, Long idEstabliment, boolean asc, Boolean valid) {
		elaboracioOlivaDao.setHibernateTemplate(getHibernateTemplate());
		return elaboracioOlivaDao.getRegistroElaboracionesEntreDiasEnEstablecimiento(dataInici, dataFi, idEstabliment, asc, valid);
	}
	
	
	/**
	 * Busca todas las elaboraciones 
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection sortidaOrujoConsulta(Date dataInici, Date dataFi, Long idEstabliment, Boolean valid) {
		sortidaOrujoDao.setHibernateTemplate(getHibernateTemplate());
		if (idEstabliment != null && !"".equals(idEstabliment)){
			return sortidaOrujoDao.findAllAmbEstablimentEntreDates(dataInici, dataFi, idEstabliment, valid);
		} else {
			return sortidaOrujoDao.findAllEntreDates(dataInici, dataFi, valid);
		}
		
	}
	
	
	/**
	 * Busca todas las entradas de depositos de un establecimiento 
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection entradaDipositConsulta(Long idEstabliment) {
		entradaDipositDao.setHibernateTemplate(getHibernateTemplate());
		return entradaDipositDao.findByEstablecimiento(idEstabliment, new Boolean(true));
	}
	
	
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection entradaDiposiOliByDipositAndElaboracio(Long dipositId, Long idElaboracio, Boolean valid) {
		entradaDipositDao.setHibernateTemplate(getHibernateTemplate());
		return entradaDipositDao.findEntradaDiposiOliByDipositAndElaboracio(dipositId, idElaboracio, valid);
	}
	
	
	/**
	 * Devuelve una lista de Diposit por  establecimiento y 
	 * las guarda en un Hash donde la key es la elaboracion
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public HashMap entradaDipositConsultaByEstablimentHash(Long idEstabliment) {
		HashMap entradasDipositElaboracioHash = new HashMap();
		Collection entradas = entradaDipositConsulta(idEstabliment);
		List entradasDipositElalaboracio = new ArrayList();
		for (Iterator it = entradas.iterator(); it.hasNext();) {
			EntradaDiposit entradasDiposit = (EntradaDiposit)it.next();
			if( entradasDiposit.getElaboracio()!= null){
				Long codiElaboracion = entradasDiposit.getElaboracio().getId();
				if(entradasDipositElaboracioHash.get(codiElaboracion)!= null){
					entradasDipositElalaboracio = (List) entradasDipositElaboracioHash.get(codiElaboracion);
					entradasDipositElalaboracio.add(entradasDiposit.getDiposit());
					entradasDipositElaboracioHash.put(codiElaboracion, entradasDipositElalaboracio);
				}else{
					entradasDipositElalaboracio = new ArrayList();
					entradasDipositElalaboracio.add(entradasDiposit.getDiposit());
					entradasDipositElaboracioHash.put(codiElaboracion, entradasDipositElalaboracio);
				}
			}        			
		}
		return entradasDipositElaboracioHash;
	}
	
	/**
	 * Devuelve los litros que se queda el olivicultor de un establecimiento y 
	 * las guarda en un Hash donde la key es la elaboracion
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection oliRetiratPropietarioEntradaDipositConsulta(Long idEstabliment) {
		entradaDipositDao.setHibernateTemplate(getHibernateTemplate());
		return entradaDipositDao.findEntradaDiposiOliRetiratPropietarioByEstablecimiento(idEstabliment, new Boolean(true));
		
	}
	
	/**
	 * Devuelve los litros que se queda el olivicultor en una elaboración
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection oliRetiratPropietarioEnElaboracio(Long idEstabliment, Long idElaboracio) {
		entradaDipositDao.setHibernateTemplate(getHibernateTemplate());
		return entradaDipositDao.findEntradaDipositOliRetiratPropietarioByEstablecimientoAndElaboracio(idEstabliment, idElaboracio, new Boolean(true));
		
	}
	
	/**
	 * Busca todas los tralados de depositos en los que este establecimiento es el origen 
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection oliSalidaTrasladoConsulta(Date dataInici, Date dataFi, Long idEstabliment) {
		trasllatDao.setHibernateTemplate(getHibernateTemplate());
		return trasllatDao.getSalidaTrasladosEntreDiasEnEstablecimiento(dataInici, dataFi, idEstabliment);	
	}
	
	/**
	 * Busca todas los tralados de depositos en los que este establecimiento es el origen
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection oliEntradaTrasladoConsulta(Date dataInici, Date dataFi, Long idEstabliment) {
		trasllatDao.setHibernateTemplate(getHibernateTemplate());
		return trasllatDao.getEntradaTrasladosEntreDiasEnEstablecimiento(dataInici, dataFi, idEstabliment);	
	}
	
	/**
	 * Busca todas los tralados de depositos en los que este establecimiento es el origen
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection movimentsEntreEstablimentsConsulta(Date dataInici, Date dataFi, Long idEstabliment) {
		trasllatDao.setHibernateTemplate(getHibernateTemplate());
		return trasllatDao.getMovimentsEntreEstablimentsEntreDiasEnEstablecimiento(dataInici, dataFi, idEstabliment);	
	}
	
	/**
	 * Busca todas los tralados de depositos en los que este establecimiento es el origen
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection movimentsEntreEstablimentsSortidaConsulta(Date dataInici, Date dataFi, Long idEstabliment) {
		trasllatDao.setHibernateTemplate(getHibernateTemplate());
		return trasllatDao.getMovimentsEntreEstablimentsSortidaEntreDiasEnEstablecimiento(dataInici, dataFi, idEstabliment);	
	}
	
	/**
	 * Busca todas los tralados de depositos en los que este establecimiento es el origen
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection movimentsEntreEstablimentsEntradaConsulta(Date dataInici, Date dataFi, Long idEstabliment) {
		trasllatDao.setHibernateTemplate(getHibernateTemplate());
		return trasllatDao.getMovimentsEntreEstablimentsEntradaEntreDiasEnEstablecimiento(dataInici, dataFi, idEstabliment);	
	}
	
	/**
	 * Busca todas los tralados de depositos en los que este establecimiento es el origen
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection movimentsEntreEstablimentsSortidaPendentConsulta(Date dataInici, Date dataFi, Long idEstabliment) {
		trasllatDao.setHibernateTemplate(getHibernateTemplate());
		return trasllatDao.getMovimentsEntreEstablimentsSortidaPendentsEntreDiasEnEstablecimiento(dataInici, dataFi, idEstabliment);	
	}
	
	
	/**
	 * Busca todas las salidas de lotes que se han vendido de este establecimiento
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection getSortidaLotVendaEntreDiasEnEstablecimiento(Date dataInici, Date dataFi, Long idEstabliment) {
		sortidaLotDao.setHibernateTemplate(getHibernateTemplate());
		return sortidaLotDao.getSortidaLotVendaEntreDiasEnEstablecimiento(dataInici, dataFi, idEstabliment, new Boolean(true));	
	}
	
	/**
	 * Busca todas las salidas de lotes que se han vendido de este establecimiento
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection getSortidaLotVendaOlivaTaulaEntreDiasEnEstablecimiento(Date dataInici, Date dataFi, Long idEstabliment) {
		sortidaLotDao.setHibernateTemplate(getHibernateTemplate());
		return sortidaLotDao.getSortidaLotVendaOlivaTaulaEntreDiasEnEstablecimiento(dataInici, dataFi, idEstabliment, new Boolean(true));	
	}
	
	/**
	 * Busca todas las salidas de lotes que se han vendido de este establecimiento
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection getSortidaDipositVendaEntreDiasEnEstablecimiento(Date dataInici, Date dataFi, Long idEstabliment) {
		sortidaDipositDao.setHibernateTemplate(getHibernateTemplate());
		return sortidaDipositDao.getSortidaDipositVendaEntreDiasEnEstablecimiento(dataInici, dataFi, idEstabliment, new Boolean(true));	
	}
	
	/**
	 * Busca todas las salidas de lotes que se han vendido de este establecimiento
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection getSortidaDipositVendaEnvasadoraEntreDiasEnEstablecimiento(Date dataInici, Date dataFi, Long idEstabliment) {
		sortidaDipositDao.setHibernateTemplate(getHibernateTemplate());
		return sortidaDipositDao.getSortidaDipositVendaEnvasadoraEntreDiasEnEstablecimiento(dataInici, dataFi, idEstabliment, new Boolean(true));	
	}
	
	/**
	 * Busca todas las salidas de lotes que se han vendido de este establecimiento
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection getSortidaDipositVendaNoEnvasadoraEntreDiasEnEstablecimiento(Date dataInici, Date dataFi, Long idEstabliment) {
		sortidaDipositDao.setHibernateTemplate(getHibernateTemplate());
		return sortidaDipositDao.getSortidaDipositVendaNoEnvasadoraEntreDiasEnEstablecimiento(dataInici, dataFi, idEstabliment, new Boolean(true));	
	}
	
	/**
	 * Busca todas las salidas de lotes que se han vendido de este establecimiento
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection getMovimentsEntreEstablimentsSortidaEntreDiasEnEstablecimiento(Date dataInici, Date dataFi, Long idEstabliment) {
		trasllatDao.setHibernateTemplate(getHibernateTemplate());
		return trasllatDao.getMovimentsEntreEstablimentsSortidaEntreDiasEnEstablecimiento(dataInici, dataFi, idEstabliment);	
	}
	
	/**
	 * Busca todas las salidas de depósitos que se han realizado de este establecimiento
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Double[] getSortidesEntreDiasEnEstablecimiento(Date dataInici, Date dataFi, Long idEstabliment) {
	
		Double[] sortides = {0.0, 0.0, 0.0};
		sortidaDipositDao.setHibernateTemplate(getHibernateTemplate());
		sortides[Constants.CATEGORIA_DO - 1] = sortidaDipositDao.getSortidaDipositEntreDiasEnEstablecimientoAndCategoria(dataInici, dataFi, idEstabliment, new Long(Constants.CATEGORIA_DO));
		sortides[Constants.CATEGORIA_PENDENT - 1] = sortidaDipositDao.getSortidaDipositEntreDiasEnEstablecimientoAndCategoria(dataInici, dataFi, idEstabliment, new Long(Constants.CATEGORIA_PENDENT));
		sortides[Constants.CATEGORIA_NO_DO - 1] = sortidaDipositDao.getSortidaDipositEntreDiasEnEstablecimientoAndCategoria(dataInici, dataFi, idEstabliment, new Long(Constants.CATEGORIA_NO_DO));
		
		sortidaLotDao.setHibernateTemplate(getHibernateTemplate());
		Collection sortLot = sortidaLotDao.getSortidaLotVendaEntreDiasEnEstablecimiento(dataInici, dataFi, idEstabliment, true);

		for (Iterator it = sortLot.iterator(); it.hasNext();){
			SortidaLot slo = (SortidaLot)it.next();
			sortides[slo.getLot().getPartidaOli().getCategoriaOli().getId()-1] += slo.getVendaNumeroBotelles() * slo.getLot().getEtiquetatge().getTipusEnvas().getVolum();
		}		
		
		entradaLotDao.setHibernateTemplate(getHibernateTemplate());
		Collection devolucions = entradaLotDao.getDevolucionsLotsEstablimentFinsData(idEstabliment, dataFi, true);
		
		for (Iterator it2 = devolucions.iterator(); it2.hasNext();){
			EntradaLot elo = (EntradaLot)it2.next();
			sortides[elo.getLot().getPartidaOli().getCategoriaOli().getId()-1] -= elo.getBotelles() * elo.getLot().getEtiquetatge().getTipusEnvas().getVolum();
		}
		return sortides;	
	}
	
	/**
	 * Devuelve el total de aceite existente DO
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Double getTotalOliExistenteDO( //Long temporadaId,Date dataInici,
			Date dataFin, Object[] categorias) {
		dipositDao.setHibernateTemplate(getHibernateTemplate());
//		return dipositDao.getExistenciaOliByCategoriaAndFecha(temporadaId, dataInici, dataFin, categorias);	
		return dipositDao.getExistenciaOliByCategoriaAndData(dataFin, categorias);
	}
	
	
	/**
	 * Devuelve el total de aceite existente DO
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Double getTotalOliExistenteTempAnteriorDO(Long temporadaId, Object[] categorias) {
		
		Campanya anteriorTemporada = null;
		campanyaDao.setHibernateTemplate(getHibernateTemplate());
		Collection campanyas =  campanyaDao.findAll();
		for(Iterator it = campanyas.iterator(); it.hasNext();){
			Campanya campanya = (Campanya) it.next();
			if(campanya.getId().intValue() == temporadaId.intValue() ){
				if(it.hasNext()){
					anteriorTemporada = (Campanya)it.next();
					break;
				}
			}
		}
		if(anteriorTemporada != null){
			dipositDao.setHibernateTemplate(getHibernateTemplate());
//			return dipositDao.getExistenciaOliTempAnteriorByCategoriaAndFecha(anteriorTemporadaId, categorias);
			return dipositDao.getExistenciaOliByCategoriaAndData(anteriorTemporada.getDataFi(), categorias);
		}else{
			return null;
		}		
			
	}
	
	
	/**
	 * Devuelve el total de kg de entrada de oliva por fechas
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Double getTotalOlivasEntradas(//Long temporadaId,
				Date dataInici,
				Date dataFin//,Long temporadaActual
				) {
		descomposicioPartidaOlivaDao.setHibernateTemplate(getHibernateTemplate());
//		return descomposicioPartidaOlivaDao.getTotalOlivasEntradas(temporadaId, dataInici, dataFin, temporadaActual);
		return descomposicioPartidaOlivaDao.getTotalOlivasEntradasEntreDates(dataInici, dataFin);
	}
	
	/**
	 * Devuelve el total daceite elaborado por fechas
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Double getTotalOliElaborat(//Long temporadaId,
				Date dataInici,
				Date dataFin//, Long temporadaActual
				) {
		elaboracioDao.setHibernateTemplate(getHibernateTemplate());
//		return elaboracioDao.getTotalOliElaborat(temporadaId, dataInici, dataFin, temporadaActual, new Boolean(true));	
		return elaboracioDao.getTotalOliElaboratEntreDates(dataInici, dataFin);
	}
	
	/**
	 * Busca todas las elaboraciones de una determinada categoria por fechas o temporada
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Double getTotalOliByCategoriasConsulta(//Long temporadaId, 
				Date dataInici, 
				Date dataFin, 
				Object[] categorias) {
		entradaDipositDao.setHibernateTemplate(getHibernateTemplate());
//		return entradaDipositDao.getTotalOliByCategorias(temporadaId, dataInici, dataFin, categorias, new Boolean(true));
		return entradaDipositDao.getTotalOliByCategoriasEntreDates(dataInici, dataFin, categorias);
	
	}
	
	/**
	 * Busca todas las ventas de lotes y aceite a granel de una determinada categoria por fechas o temporada
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Double getTotalOliComercialitzatByCategoriasConsulta(
				Long temporadaId, 
				Date dataInici, 
				Date dataFin, 
				Object[] categorias,
				Long temporadaActual) {
		sortidaLotDao.setHibernateTemplate(getHibernateTemplate());
		Double oliComercializatLotes = sortidaLotDao.getTotalOliComercialitzatByCategorias(temporadaId, dataInici, dataFin, categorias,temporadaActual, new Boolean(true));
		sortidaDipositDao.setHibernateTemplate(getHibernateTemplate());
		Double oliComercializatGranel = sortidaDipositDao.getTotalOliGranelComercialitzatByCategorias(temporadaId, dataInici, dataFin, categorias,temporadaActual, new Boolean(true));
		double resultado = 0;
		if(oliComercializatLotes != null && oliComercializatGranel != null ){
			resultado = oliComercializatLotes.doubleValue() + oliComercializatGranel.doubleValue();
		}
		return Double.valueOf(String.valueOf(resultado));
	
	}
	
	/**
	 * Busca todas las ventas de lotes y aceite a granel de una determinada categoria por fechas o temporada
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Double getTotalOliComercialitzatByCategoriasConsulta(//Long temporadaId, 
				Date dataInici, 
				Date dataFin, 
				Object[] categorias//,Long temporadaActual
				) {
		sortidaLotDao.setHibernateTemplate(getHibernateTemplate());
//		Double oliComercializatLotes = sortidaLotDao.getTotalOliComercialitzatByCategorias(temporadaId, dataInici, dataFin, categorias,temporadaActual, new Boolean(true));
		Double oliComercializatLotes = sortidaLotDao.getTotalOliComercialitzatByCategoriasEntreDates(dataInici, dataFin, categorias);
		sortidaDipositDao.setHibernateTemplate(getHibernateTemplate());
//		Double oliComercializatGranel = sortidaDipositDao.getTotalOliGranelComercialitzatByCategorias(temporadaId, dataInici, dataFin, categorias,temporadaActual, new Boolean(true));
		Double oliComercializatGranel = sortidaDipositDao.getTotalOliGranelComercialitzatByCategoriasEntreDates(dataInici, dataFin, categorias);
		double resultado = 0;
		if(oliComercializatLotes != null && oliComercializatGranel != null ){
			resultado = oliComercializatLotes.doubleValue() + oliComercializatGranel.doubleValue();
		}
		return Double.valueOf(String.valueOf(resultado));
	
	}
	
	/**
	 * Busca todas las ventas de lotes  por fechas y devuelve dos mapo uno de establecimientos y otro con el total de litros vendidos por establecimiento
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public List getTotalOliComercialitzatByEstablecimiento(Date dataInici, Date dataFin) {
		sortidaLotDao.setHibernateTemplate(getHibernateTemplate());
		
		return sortidaLotDao.getTotalOliComercialitzatByEstablecimiento( dataInici, dataFin, new Boolean(true));
	
	}
	
	/**
	 * Busca todos los los traslados de depositos con aceite con categoria autorizada por fechas o temporada
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Double getTotalOliDOdeEnvasadoresConsulta(
				Long temporadaId, 
				Date dataInici, 
				Date dataFin, 
				Integer idAutorizada) {
		trasllatDao.setHibernateTemplate(getHibernateTemplate());
		return trasllatDao.getTotalOliDOdeEnvasadores(temporadaId, dataInici, dataFin, idAutorizada);
//		return trasllatDao.getTotalOliDOdeEnvasadoresEntreDates(dataInici, dataFin, idAutorizada);
	}
	
	/**
	 * Busca todas las ventas de lotes de una determinada categoria por fechas o temporada
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Double getTotalOliDOaEnvasadoresConsulta(//Long temporadaId, 
				Date dataInici, 
				Date dataFin, 
				Integer idAutorizada) {
		trasllatDao.setHibernateTemplate(getHibernateTemplate());
//		return trasllatDao.getTotalOliDOaEnvasadores(temporadaId, dataInici, dataFin, idAutorizada);
		return trasllatDao.getTotalOliDOaEnvasadoresEntreDates(dataInici, dataFin, idAutorizada);
	}
	
	
	/**
	 * Construeix l'arbre per trazar el procés del vi.
	 * 
	 * @param contenidorId el contenidor per trazar l'arbre
	 * @return una colecció amb la informacó per construir
	 * l'arbre.
	 * 
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 * <!-- end-xdoclet-definition --> 
	 * @generated
	 */
	public Collection trazabilitat(String tipus,String id) {
		Collection result = new ArrayList();
		if(tipus!= null && !tipus.equals("")){
			Integer tipusTraza = Integer.valueOf(tipus);
			if(tipusTraza.intValue() == Constants.TRAZA_TIPUS_OLI_ELABORAT){
				elaboracioDao.setHibernateTemplate(getHibernateTemplate());
				Elaboracio elaboracion = elaboracioDao.getById(Long.valueOf(id), new Boolean(true));				
				
				if (elaboracion != null) {
				
					//ELABORACION
					Object[] fulla = novaFulla("Elaboracion_"+elaboracion.getId(), elaboracion, null, "Elaboracio", new Boolean(true));
					result.add(fulla);
					
					if(elaboracion.getPartidaOlivas()!=null){
						//PARTIDAS
						for(Iterator itPartidas = elaboracion.getPartidaOlivas().iterator(); itPartidas.hasNext();){
							PartidaOliva partida = (PartidaOliva)itPartidas.next();
							partidaOlivaDao.setHibernateTemplate(getHibernateTemplate());
							partidaOlivaDao.getById(partida.getId());
							fulla = novaFulla("Partida_"+partida.getId(), partida, "Elaboracion_"+elaboracion.getId(), "Partida", new Boolean(true));
							result.add(fulla);
								//OLIVICULTOR							
							    fulla = novaFulla("Olivicultor_"+partida.getOlivicultor().getId(), partida.getOlivicultor(), "Partida_"+partida.getId(), "Olivicultor", new Boolean(true));
								result.add(fulla);
								//DESCOMPOSICION PARTIDAS								
								for(Iterator itDescompPartidas = partida.getDescomposicioPartidesOlives().iterator(); itDescompPartidas.hasNext();){
									DescomposicioPartidaOliva descompPartida = (DescomposicioPartidaOliva)itDescompPartidas.next();
									fulla = novaFulla("DescomPartida_"+descompPartida.getId(), descompPartida, "Partida_"+partida.getId(), "DescomposicioPartidaOliva", new Boolean(true));
									result.add(fulla);
								}
						}
						
						//EntradaDiposit
						entradaDipositDao.setHibernateTemplate(getHibernateTemplate());
						Collection entradas = entradaDipositDao.findEntradaDiposiOliByElaboracio(elaboracion.getId(), new Boolean(true));
						for(Iterator itEntradaDiposit = entradas.iterator(); itEntradaDiposit.hasNext();){
							EntradaDiposit entradaDiposit = (EntradaDiposit)itEntradaDiposit.next();
							fulla = novaFulla("EntradaDiposit_"+entradaDiposit.getId(), entradaDiposit, "Elaboracion_"+elaboracion.getId(), "EntradaDiposit", new Boolean(true));
							result.add(fulla);								
						}
						
						//OliRetiratPropietario
						entradaDipositDao.setHibernateTemplate(getHibernateTemplate());
						Collection entradasDipositOliRetirado = entradaDipositDao.findOliRetiratPropietarioByElaboracio(elaboracion.getId(), new Boolean(true));
						
						for(Iterator it=entradasDipositOliRetirado.iterator(); it.hasNext();){
							EntradaDiposit entradaDipositOliRetirado = (EntradaDiposit)it.next();
							if(entradaDipositOliRetirado!= null){
								fulla = novaFulla("EntradaDiposit_"+entradaDipositOliRetirado.getId(), entradaDipositOliRetirado, "Elaboracion_"+elaboracion.getId(), "OliRetiratPropietario", new Boolean(true));
								result.add(fulla);	
							}
						}
					}
					

				}
			}else if(tipusTraza.intValue() == Constants.TRAZA_TIPUS_OLI_DISPONIBLE_DIPOSIT){
				result = trazabilitatOliDisponibleDiposit(id);				
			}else if(tipusTraza.intValue() == Constants.TRAZA_TIPUS_OLI_DISPONIBLE_LOTE){
				result = trazabilitatOliDisponibleLote(id);
			}else if(tipusTraza.intValue() == Constants.TRAZA_TIPUS_SORTIDA_VOLANT || tipusTraza.intValue() == Constants.TRAZA_TIPUS_ENTRADA_VOLANT){
				result = trazabilitatVolante(id);
			}else if(tipusTraza.intValue() == Constants.TRAZA_TIPUS_OLI_COMERCIALIZAT){
				result = trazabilitatOliComercializat(id);
			}
			else if(tipusTraza.intValue() == Constants.TRAZA_TIPUS_PARTIDAOLI){
				result = trazabilitatPartidaOliSinDeposito(id);
			}
		}
		
		
		return result;
	}
	
	/**
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 * <!-- end-xdoclet-definition --> 
	 * @generated
	 */
	public Collection trazabilitatOliDisponibleDiposit(String id) {
		Collection result = new ArrayList();
		dipositDao.setHibernateTemplate(getHibernateTemplate());
		Diposit deposito = dipositDao.getById(Long.valueOf(id));
		Hibernate.initialize(deposito.getMaterialDiposit().getNom());
		Hibernate.initialize(deposito.getEstabliment().getNom());
		Hibernate.initialize(deposito.getZona().getNom());
		
		if (deposito != null) {				
			//DEPOSITO
			Object[] fulla = novaFulla("Deposito_"+deposito.getId(), deposito, null, "Deposito", new Boolean(true));
			result.add(fulla);
						
			//Ultima entrada
			entradaDipositDao.setHibernateTemplate(getHibernateTemplate());
			EntradaDiposit entradaDiposit = entradaDipositDao.findUltimaByDeposito(deposito.getId(), new Boolean(true));
			//Ultima salida
			sortidaDipositDao.setHibernateTemplate(getHibernateTemplate());
			SortidaDiposit sortidaDiposit = sortidaDipositDao.findUltimaByDeposito(deposito.getId(), new Boolean(true));
			
			
			if(entradaDiposit!= null && sortidaDiposit!= null ){
				Traza trazaEntrada = entradaDiposit.getTraza();
				Traza trazaSalida = sortidaDiposit.getTraza();
				if(trazaEntrada.getId().intValue()< trazaSalida.getId().intValue()){
					Collection trazasPadre = trazaSalida.getTrazasForTtrCodtrapare();
					fulla = novaFulla("traza_"+trazaSalida.getId(), sortidaDiposit, "Deposito_"+deposito.getId(), "SortidaDiposit", new Boolean(true));
					result.add(fulla);			
					result = generaFullasPadre(result,"traza_"+trazaSalida.getId(),trazasPadre);
										
				}else{
					Collection trazasPadre = trazaEntrada.getTrazasForTtrCodtrapare();
					fulla = novaFulla("traza_"+trazaEntrada.getId(), entradaDiposit, "Deposito_"+deposito.getId(), "EntradaDiposit", new Boolean(true));
					result.add(fulla);			
					result = generaFullasPadre(result,"traza_"+trazaEntrada.getId(),trazasPadre);
					//Incorporamos la analitica si existe
					Collection trazasHijos = trazaEntrada.getTrazasForTtrCodtrafill();
					for(Iterator itertrazasHijos=trazasHijos.iterator(); itertrazasHijos.hasNext();){
						Traza trazaHijo = (Traza)itertrazasHijos.next();	
						if(trazaHijo.getTipus().intValue() == Constants.CODI_TRAZA_TIPUS_ANALITICA){
							analiticaDao.setHibernateTemplate(getHibernateTemplate());
							Analitica analitica = analiticaDao.findByTraza(trazaHijo.getId());
							if(analitica != null){
								fulla = novaFulla("traza_"+trazaHijo.getId(), analitica, "traza_"+trazaEntrada.getId(), "Analitica", new Boolean(true));
								result.add(fulla);
							}	
						}
					}
					
					
				}
			}else if(entradaDiposit!= null){
				Traza trazaEntrada = entradaDiposit.getTraza();
				Collection trazasPadre = trazaEntrada.getTrazasForTtrCodtrapare();
				fulla = novaFulla("traza_"+trazaEntrada.getId(), entradaDiposit, "Deposito_"+deposito.getId(), "EntradaDiposit", new Boolean(true));
				result.add(fulla);			
				result = generaFullasPadre(result,"traza_"+trazaEntrada.getId(),trazasPadre);
				//Incorporamos la analitica si existe
				Collection trazasHijos = trazaEntrada.getTrazasForTtrCodtrafill();
				for(Iterator itertrazasHijos=trazasHijos.iterator(); itertrazasHijos.hasNext();){
					Traza trazaHijo = (Traza)itertrazasHijos.next();	
					if(trazaHijo.getTipus().intValue() == Constants.CODI_TRAZA_TIPUS_ANALITICA){
						analiticaDao.setHibernateTemplate(getHibernateTemplate());
						Analitica analitica = analiticaDao.findByTraza(trazaHijo.getId());
						if(analitica != null){
							fulla = novaFulla("traza_"+trazaHijo.getId(), analitica, "traza_"+trazaEntrada.getId(), "Analitica", new Boolean(true));
							result.add(fulla);
						}	
					}
				}
				
			}			
			
		}
		return result;
	}
	
	/**
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 * <!-- end-xdoclet-definition --> 
	 * @generated
	 */
	public Collection trazabilitatOliDisponibleLote(String id) {
		Collection result = new ArrayList();
		lotDao.setHibernateTemplate(getHibernateTemplate());
		Lot lote = lotDao.getById(Long.valueOf(id));				
		
		if (lote != null) {				
			//LOTE
			Object[] fulla = novaFulla("Lote_"+lote.getId(), lote, null, "Lote", new Boolean(true));
			result.add(fulla);
						
			entradaLotDao.setHibernateTemplate(getHibernateTemplate());
			EntradaLot entradaLot = entradaLotDao.findUltimaByLot(lote.getId(), new Boolean(true));
			
			EntradaLot entradaLotAux = new EntradaLot();
			entradaLotAux.setId(entradaLot.getId()) ;
			entradaLotAux.setLot(entradaLot.getLot());
			entradaLotAux.setAcidesa(entradaLot.getAcidesa());
			entradaLotAux.setData(entradaLot.getData());
			entradaLotAux.setNumerosContraetiquetes(entradaLot.getNumerosContraetiquetes());
			entradaLotAux.setTraza(entradaLot.getTraza());
			entradaLotAux.setValid(entradaLot.getValid());
			entradaLotAux.setZona(entradaLot.getZona());
			entradaLotAux.setBotelles(entradaLot.getBotelles());
			
			if(entradaLot != null && entradaLot.getDipositProcedencia()!= null){
				try {
					Long idDiposit = Long.valueOf(entradaLot.getDipositProcedencia());
					dipositDao.setHibernateTemplate(getHibernateTemplate());
					Diposit dip = dipositDao.getById(idDiposit);
					if(dip != null && dip.getCodiAssignat()!= null){
						entradaLotAux.setDipositProcedencia(dip.getCodiAssignat());
					}
				} catch (Exception e) {
					entradaLotAux.setDipositProcedencia(entradaLot.getDipositProcedencia());
				}
			}
			
			//VEAMOS CUAL ES EL PADRE DE ESTA ENTRADA
			Traza trazaEntrada = entradaLot.getTraza();
			Collection trazasPadre = trazaEntrada.getTrazasForTtrCodtrapare();
			fulla = novaFulla("traza_"+trazaEntrada.getId(), entradaLotAux, "Lote_"+lote.getId(), "EntradaLot", new Boolean(true));
			result.add(fulla);			
			result = generaFullasPadre(result,"traza_"+trazaEntrada.getId(),trazasPadre);
			
		}
		return result;
	}
	/**
	 * DevuelveEntradaLot by traza
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public EntradaLot getEntradaLotByTraza(Long idTraza) {
		entradaLotDao.setHibernateTemplate(getHibernateTemplate());
		return entradaLotDao.findByTraza(idTraza, new Boolean(true));	
	}
	/**
	 * Devuelve SortidaDiposit by traza
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public SortidaDiposit getSortidaDipositByTraza(Long idTraza) {
		sortidaDipositDao.setHibernateTemplate(getHibernateTemplate());
		return sortidaDipositDao.findByTraza(idTraza, new Boolean(true));	
	}
	
	/**
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * <!-- end-xdoclet-definition --> 
	 * @generated
	 */
	public Collection trazabilitatPartidaOli(String id) {
			
		Collection result = new ArrayList();
		dipositDao.setHibernateTemplate(getHibernateTemplate());
		Diposit deposito = dipositDao.getById(Long.valueOf(id));				
		
		if (deposito != null) {				
			//DEPOSITO
			Object[] fulla = novaFulla("Deposito_"+deposito.getId(), deposito, null, "Deposito", new Boolean(true));
			result.add(fulla);
			
			//Ultima entrada
			entradaDipositDao.setHibernateTemplate(getHibernateTemplate());
			EntradaDiposit entradaDiposit = entradaDipositDao.findUltimaByDeposito(deposito.getId(), new Boolean(true));
			//Ultima salida
			sortidaDipositDao.setHibernateTemplate(getHibernateTemplate());
			SortidaDiposit sortidaDiposit = sortidaDipositDao.findUltimaByDeposito(deposito.getId(), new Boolean(true));
						
			if(entradaDiposit!= null && sortidaDiposit!= null ){
				Traza trazaEntrada = entradaDiposit.getTraza();
				Traza trazaSalida = sortidaDiposit.getTraza();
				if(trazaEntrada.getId().intValue()< trazaSalida.getId().intValue()){
					Collection trazasPadre = trazaSalida.getTrazasForTtrCodtrapare();
					fulla = novaFulla("traza_"+trazaSalida.getId(), sortidaDiposit, "Deposito_"+deposito.getId(), "SortidaDiposit", new Boolean(true));
					result.add(fulla);			
					result = generaFullasPadre(result,"traza_"+trazaSalida.getId(),trazasPadre);
					if(sortidaDiposit.getDesti()!=null && sortidaDiposit.getDesti().equalsIgnoreCase(DESTI_EMBOTELLAT)){
						//LOTE
						Traza traza = sortidaDiposit.getTraza();
						Collection hijos = traza.getTrazasForTtrCodtrafill();
						Traza trazaEntradaLot = null;
						EntradaLot entradaLot = null;
						for(Iterator itHijos= hijos.iterator(); itHijos.hasNext();){
							trazaEntradaLot = (Traza)itHijos.next();
							entradaLotDao.setHibernateTemplate(getHibernateTemplate());
							entradaLot = entradaLotDao.findByTraza(trazaEntradaLot.getId(), new Boolean(true));
							break;
						}
						if(entradaLot!= null && entradaLot.getLot()!= null){
							Lot lote = entradaLot.getLot();
							fulla = novaFulla("Lote_"+lote.getId(), lote, "traza_"+trazaSalida.getId(), "Lote", new Boolean(true));
							result.add(fulla);
						}					
					}					
				}else{					
					Collection trazasPadre = trazaEntrada.getTrazasForTtrCodtrapare();
					fulla = novaFulla("traza_"+trazaEntrada.getId(), entradaDiposit, "Deposito_"+deposito.getId(), "EntradaDiposit", new Boolean(true));
					result.add(fulla);			
					result = generaFullasPadre(result,"traza_"+trazaEntrada.getId(),trazasPadre);
					//ANALITICA
					Analitica analitica = null;
					Collection hijos = trazaEntrada.getTrazasForTtrCodtrafill();
					for(Iterator itHijos= hijos.iterator(); itHijos.hasNext();){
						Traza trazaHijo = (Traza)itHijos.next();
						if(trazaHijo.getTipus().intValue() == Constants.CODI_TRAZA_TIPUS_ANALITICA){
							analiticaDao.setHibernateTemplate(getHibernateTemplate());
							analitica = analiticaDao.findByTraza(trazaHijo.getId());
							if(analitica!= null){
								fulla = novaFulla("traza_"+trazaHijo.getId(), analitica, "traza_"+trazaEntrada.getId(), "Analitica", new Boolean(true));
								result.add(fulla);
							}							
							break;
						}						
					}
				}
				
			}else if(entradaDiposit!= null){
				Traza trazaEntrada = entradaDiposit.getTraza();
				Collection trazasPadre = trazaEntrada.getTrazasForTtrCodtrapare();
				fulla = novaFulla("traza_"+trazaEntrada.getId(), entradaDiposit, "Deposito_"+deposito.getId(), "EntradaDiposit", new Boolean(true));
				result.add(fulla);			
				result = generaFullasPadre(result,"traza_"+trazaEntrada.getId(),trazasPadre);				
			}			
			
		}
		return result;
		
		
	}
	
	/**
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition --> 
	 * @generated
	 */
	public Collection trazabilitatPartidaOliSinDeposito(String idPartida) {
			
		Collection result = new ArrayList();
		partidaOlivaDao.setHibernateTemplate(getHibernateTemplate());
		PartidaOliva partida = partidaOlivaDao.getById(Long.valueOf(idPartida));
		if(partida!= null){
			Object[] fulla = novaFulla("Partida_"+partida.getId(), partida, null, "Partida", new Boolean(true));			
			result.add(fulla);
			//OLIVICULTOR							
		    fulla = novaFulla("Olivicultor_"+partida.getOlivicultor().getId(), partida.getOlivicultor(), "Partida_"+partida.getId(), "Olivicultor", new Boolean(true));
			result.add(fulla);
			//DESCOMPOSICION PARTIDAS								
			for(Iterator itDescompPartidas = partida.getDescomposicioPartidesOlives().iterator(); itDescompPartidas.hasNext();){
				DescomposicioPartidaOliva descompPartida = (DescomposicioPartidaOliva)itDescompPartidas.next();
				fulla = novaFulla("DescomPartida_"+descompPartida.getId(), descompPartida, "Partida_"+partida.getId(), "DescomposicioPartidaOliva", new Boolean(true));
				result.add(fulla);
			}
		}
		
		return result;
		
		
	}
	
	
	/**
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * <!-- end-xdoclet-definition --> 
	 * @generated
	 */
	public List lotesElaboracio(String id) {
		
		elaboracioDao.setHibernateTemplate(getHibernateTemplate());
		Elaboracio elaboracio = elaboracioDao.getById(Long.valueOf(id), new Boolean(true));				
		//Lotes
		List lotes = new ArrayList();
		List lotesId = new ArrayList();
		
		if (elaboracio != null) {			
			Traza trazaElaboracio = elaboracio.getTraza();
			Collection trazasHijosElaboracion = trazaElaboracio.getTrazasForTtrCodtrafill();
			  		   		
    		
    		//LOTES (Vemos que hijos de las entradas de oliva son salidas de oliva con destino EMBOTELLADO)
    		for(Iterator hijosElaboracionIt = trazasHijosElaboracion.iterator();hijosElaboracionIt.hasNext(); ){    			
    			Traza traza = (Traza) hijosElaboracionIt.next();
    			if(traza.getTipus().intValue()== Constants.CODI_TRAZA_TIPUS_ENTRADA_DIPOSIT){
    				Collection hijos = traza.getTrazasForTtrCodtrafill();
        			for(Iterator hijosIt = hijos.iterator();hijosIt.hasNext(); ){
        				Traza trazaHijo = (Traza) hijosIt.next();
        				if(trazaHijo.getTipus().intValue()== Constants.CODI_TRAZA_TIPUS_SORTIDA_DIPOSIT){
        					
        					SortidaDiposit sortidaDiposit =  getSortidaDipositByTraza(trazaHijo.getId());
        					if(sortidaDiposit!= null && sortidaDiposit.getDesti()!= null && sortidaDiposit.getDesti().equalsIgnoreCase(Constants.DESTI_EMBOTELLAT)){
        						//LOTE
        						Traza trazaSortida = sortidaDiposit.getTraza();
        						Collection hijosSalida = trazaSortida.getTrazasForTtrCodtrafill();
        						Traza trazaEntradaLot = null;
        						EntradaLot entradaLot = null;
        						for(Iterator itHijosSalida= hijosSalida.iterator(); itHijosSalida.hasNext();){
        							trazaEntradaLot = (Traza)itHijosSalida.next();        							
                					entradaLot = getEntradaLotByTraza(trazaEntradaLot.getId());    							
        							break;
        						}
        						if(entradaLot!= null && entradaLot.getLot()!= null){
        							Lot lote = entradaLot.getLot();
        							if( !lotesId.contains(lote.getId()) ){
        		    					lotes.add(lote);
        		    					lotesId.add(lote.getId());
        		    				} 
        						}					
        					}    					
        				}
        			}
    			}
    			
    		}      		
		}
		return lotes;		
	}
	
	/**
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * <!-- end-xdoclet-definition --> 
	 * @generated
	 */
	public List analiticasLote(String lotId) {
		Collection result = new ArrayList();
		entradaLotDao.setHibernateTemplate(getHibernateTemplate());
		Collection entradasLot = entradaLotDao.findByLot(Long.valueOf(lotId), new Boolean(true));
		Object[] fulla = novaFulla("EntradaLot_"+lotId, entradasLot, null, "EntradaLot", new Boolean(true));
		result.add(fulla);	
		//Analiticas
		List analiticas = new ArrayList();
		List analiticasId = new ArrayList();
		for(Iterator entradasLotIt = entradasLot.iterator();entradasLotIt.hasNext(); ){
			EntradaLot entradaLot = (EntradaLot)entradasLotIt.next();
			Collection trazasPadre = entradaLot.getTraza().getTrazasForTtrCodtrapare();
			result = generaFullasPadre(result,"EntradaLot_"+lotId,trazasPadre);			
		}
		for(Iterator trazabilidatIt = result.iterator(); trazabilidatIt.hasNext();){
			fulla = (Object[]) trazabilidatIt.next();
			String tipo = (String)fulla[3];
			if(tipo.equalsIgnoreCase("Analitica") ){
				Analitica analitica = (Analitica)fulla[1];
				if( !analiticasId.contains(analitica.getId()) ){
					analiticas.add(analitica);
					analiticasId.add(analitica.getId());
				}   				
			}			
		}
		return analiticas;		
	}
	
	
	/**
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR" <!-- end-xdoclet-definition -->
	 * <!-- end-xdoclet-definition --> 
	 * @generated
	 */
//	public Collection trazabilitatLote(String id) {
//			
//		Collection result = new ArrayList();
//		elaboracioDao.setHibernateTemplate(getHibernateTemplate());
//		Elaboracio elaboracio = elaboracioDao.getById(Long.valueOf(id));				
//		
//		if (elaboracio != null) {				
//			
//			Object[] fulla = novaFulla("Elaboracio_"+id, elaboracio, null, "Elaboracio", new Boolean(true));
//			result.add(fulla);
//			Traza trazaElaboracio = elaboracio.getTraza();
//			Collection trazasHijos = trazaElaboracio.getTrazasForTtrCodtrafill();
//			result = generaFullasPadre(result,"Elaboracio_"+id,trazasHijos);
//		}
//		return result;
//		
//		
//	}
	
	
	/**
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * <!-- end-xdoclet-definition --> 
	 * @generated
	 */
	public Collection trazabilitatVolante(String id) {
		Collection result = new ArrayList();
		trasllatDao.setHibernateTemplate(getHibernateTemplate());
		Trasllat trasllat = trasllatDao.getById(Long.valueOf(id));				
		if (trasllat != null) {				
			//trasllat
			Object[] fulla = novaFulla("Trasllat_"+trasllat.getId(), trasllat, null, "Trasllat", new Boolean(true));
			result.add(fulla);
					
			//VEAMOS CUAL ES EL PADRE DE ESTA ENTRADA
			Traza trazaEntrada = trasllat.getTraza();
			Collection trazasPadre = trazaEntrada.getTrazasForTtrCodtrapare();
			
			result = generaFullasPadre(result,"Trasllat_"+trasllat.getId(),trazasPadre);
			
		}
		return result;
	}
	
	/**
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * <!-- end-xdoclet-definition --> 
	 * @generated
	 */
	public Collection trazabilitatOliComercializat(String id) {
		Collection result = new ArrayList();
		sortidaLotDao.setHibernateTemplate(getHibernateTemplate());
		SortidaLot sortidaLot = sortidaLotDao.getById(Long.valueOf(id));				
		
		if (sortidaLot != null) {	
			//LOTE	
			Lot lote = sortidaLot.getLot();			
			Object[] fulla = novaFulla("Lote_"+lote.getId(), lote, null, "Lote", new Boolean(true));
			result.add(fulla);
			
			//sortidaLot
			fulla = novaFulla("SortidaLot_"+sortidaLot.getId(), sortidaLot, "Lote_"+lote.getId(), "SortidaLot", new Boolean(true));
			result.add(fulla);
					
			//VEAMOS CUAL ES EL PADRE DE ESTA ENTRADA
			Traza trazaEntrada = sortidaLot.getTraza();
			Collection trazasPadre = trazaEntrada.getTrazasForTtrCodtrapare();
			
			result = generaFullasPadre(result,"SortidaLot_"+sortidaLot.getId(),trazasPadre);
			
		}
		return result;
	}
	
	/**
	 * Construeix l'arbre per a la traçabilitat resumida de l'oli.
	 * 
	 * @param contenidorId el contenidor per trazar l'arbre
	 * @return una colecció amb la informacó per construir
	 * l'arbre.
	 * 
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" 
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition --> 
	 * @generated
	 */
	public TrazabilitatResumida trazabilitatResumida(String tipus,String id) {
		TrazabilitatResumida resum = null;
		if(tipus!= null && !tipus.equals("")){
			Integer tipusTraza = Integer.valueOf(tipus);
			if(tipusTraza.intValue() == Constants.TRAZA_TIPUS_OLI_ELABORAT){
				resum = new TrazabilitatResumida();
				resum.setTipus(Constants.TIPUS_TRAZABILITAT_RESUMIDA_ELABORACIO);
				elaboracioDao.setHibernateTemplate(getHibernateTemplate());
				Elaboracio elaboracio = elaboracioDao.getById(Long.valueOf(id), new Boolean(true));				
				
				if (elaboracio != null) {
				
					//ELABORACION
					List elaboracions = resum.getElaboracions();
					elaboracions.add(elaboracio);
					resum.setElaboracions(elaboracions);
					resum.setRendiment(elaboracio.getLitres() * 100 / elaboracio.getTotalKilos());
					resum.setPartidaOli(elaboracio.getPartidaOli());
					analiticaDao.setHibernateTemplate(getHibernateTemplate());
					resum.setAnalitica(analiticaDao.findUltimaByPartida(elaboracio.getPartidaOli().getId()));
					resum.setAcidesa(elaboracio.getAcidesa());
					
					if(elaboracio.getPartidaOlivas()!=null){

						//PARTIDAS
						for(Iterator itPartidas = elaboracio.getPartidaOlivas().iterator(); itPartidas.hasNext();){
							PartidaOliva partida = (PartidaOliva)itPartidas.next();
							partidaOlivaDao.setHibernateTemplate(getHibernateTemplate());
							partidaOlivaDao.getById(partida.getId());
							
							//DESCOMPOSICION PARTIDAS								
							for(Iterator itDescompPartidas = partida.getDescomposicioPartidesOlives().iterator(); itDescompPartidas.hasNext();){
								DescomposicioPartidaOliva descompPartida = (DescomposicioPartidaOliva)itDescompPartidas.next();
								resum.setResum(descompPartida);
							}
						}
						
					}
				}
			}else if(tipusTraza.intValue() == Constants.TRAZA_TIPUS_OLI_DISPONIBLE_DIPOSIT){
				resum = trazabilitatResumidaOliDisponibleDiposit(id);				
			}else if(tipusTraza.intValue() == Constants.TRAZA_TIPUS_OLI_DISPONIBLE_LOTE){
				resum = trazabilitatResumidaOliDisponibleLote(id);
			}else if(tipusTraza.intValue() == Constants.TRAZA_TIPUS_PARTIDAOLI){
				resum = trazabilitatResumidaPartidaOliSinDeposito(id);
			}else if(tipusTraza.intValue() == Constants.TRAZA_TIPUS_OLIVA_ELABORADA){
				resum = new TrazabilitatResumida();
				resum.setTipus(Constants.TIPUS_TRAZABILITAT_RESUMIDA_OLIVA_ELABORACIO);
				resum.setOlivaTaula(true);
				resum.setCategoria(Constants.CATEGORIA_PENDENT);
				elaboracioOlivaDao.setHibernateTemplate(getHibernateTemplate());
				ElaboracioOliva elaboracio = elaboracioOlivaDao.getById(Long.valueOf(id), new Boolean(true));
				
				if (elaboracio != null) {
				
					//ELABORACION
					List elaboracions = resum.getElaboracionsOliva();
					elaboracions.add(elaboracio);
					resum.setElaboracionsOliva(elaboracions);
					resum.setBota(elaboracio.getBota());
					resum.setTipusOliva(elaboracio.getBota().getTipusOlivaTaula());
					
					if(elaboracio.getPartidaElaboracions() != null){
						Double quantitatTotal = 0.0;
						Double quantitatTotalCribada = 0.0;
						
						//PARTIDAS
						for(Iterator itPartidas = elaboracio.getPartidaElaboracions().iterator(); itPartidas.hasNext();){
							PartidaElaboracio partida = (PartidaElaboracio)itPartidas.next();
							partidaOlivaDao.setHibernateTemplate(getHibernateTemplate());
							partidaOlivaDao.getById(partida.getPartida().getId());
							
							Double percent = (partida.getQuantitat() / partida.getPartida().getTotalKilos()) * 100.0;
							quantitatTotal += partida.getQuantitat();
							quantitatTotalCribada += partida.getQuantitatCriba();
							
							//DESCOMPOSICION PARTIDAS								
							for(Iterator itDescompPartidas = partida.getPartida().getDescomposicioPartidesOlives().iterator(); itDescompPartidas.hasNext();){
								DescomposicioPartidaOliva descompPartida = (DescomposicioPartidaOliva)itDescompPartidas.next();
								resum.setResum(descompPartida, percent);
							}
						}
						resum.setQuantitatTotal(quantitatTotal);
						resum.setQuantitatTotalCribada(quantitatTotalCribada);
					}
				}
			}else if(tipusTraza.intValue() == Constants.TRAZA_TIPUS_OLIVA_DISPONIBLE_BOTA){
				resum = trazabilitatResumidaOlivaDisponibleBota(id);
			}else if(tipusTraza.intValue() == Constants.TRAZA_TIPUS_OLIVA_DISPONIBLE_ENVAS || 
					tipusTraza.intValue() == Constants.TRAZA_TIPUS_OLIVA_COMERCIALITZADA){
				resum = trazabilitatResumidaOlivaDisponibleEnvas(id);
			}else if(tipusTraza.intValue() == Constants.TRAZA_TIPUS_SORTIDA_OLI){
				sortidaDipositDao.setHibernateTemplate(getHibernateTemplate());
				SortidaDiposit sortida = sortidaDipositDao.getById(Long.valueOf(id));
				resum.setTipus(Constants.TIPUS_TRAZABILITAT_RESUMIDA_DIPOSIT);
				resum.setDiposit(sortida.getDipositBySdiCoddor());
				resum.setEstabliment(sortida.getEstabliment());
				resum = generaResum(resum, sortida.getTraza());
			}
		}
		
		return resum;
	}
	
	/**
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" 
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition --> 
	 * @generated
	 */
	public TrazabilitatResumida trazabilitatResumidaOliDisponibleDiposit(String id) {
		TrazabilitatResumida resum = new TrazabilitatResumida();
		resum.setTipus(Constants.TIPUS_TRAZABILITAT_RESUMIDA_DIPOSIT);
		dipositDao.setHibernateTemplate(getHibernateTemplate());
		Diposit deposito = dipositDao.getById(Long.valueOf(id));
		Hibernate.initialize(deposito.getMaterialDiposit().getNom());
		Hibernate.initialize(deposito.getEstabliment().getNom());
		Hibernate.initialize(deposito.getZona().getNom());
		
		if (deposito != null) {
			// DIPOSIT
			resum.setDiposit(deposito);
			resum.setEstabliment(deposito.getEstabliment());
			
			//Ultima entrada
			entradaDipositDao.setHibernateTemplate(getHibernateTemplate());
			EntradaDiposit entradaDiposit = entradaDipositDao.findUltimaByDeposito(deposito.getId(), new Boolean(true));
			//Ultima salida
			sortidaDipositDao.setHibernateTemplate(getHibernateTemplate());
			SortidaDiposit sortidaDiposit = sortidaDipositDao.findUltimaByDeposito(deposito.getId(), new Boolean(true));
			
			
			if(entradaDiposit!= null && sortidaDiposit!= null ){
				Traza trazaEntrada = entradaDiposit.getTraza();
				Traza trazaSalida = sortidaDiposit.getTraza();
				if(trazaEntrada.getId().intValue()< trazaSalida.getId().intValue()){
					resum = generaResum(resum, trazaSalida);
					
					sortidaDipositDao.setHibernateTemplate(getHibernateTemplate());
					SortidaDiposit sortida = sortidaDipositDao.findByTraza(trazaSalida.getId(), new Boolean(true));
					resum.setPartidaOli(sortida.getPartidaOli());
										
					Collection trazasHijos = trazaSalida.getTrazasForTtrCodtrafill();
					for(Iterator itertrazasHijos=trazasHijos.iterator(); itertrazasHijos.hasNext();){
						Traza trazaHijo = (Traza)itertrazasHijos.next();	
						if(trazaHijo.getTipus().intValue() == Constants.CODI_TRAZA_TIPUS_ENTRADA_LOT){
							entradaLotDao.setHibernateTemplate(getHibernateTemplate());
							EntradaLot entradaLot = entradaLotDao.findByTraza(trazaHijo.getId(), new Boolean(true));
							if(entradaLot != null && (entradaLot.getEsDevolucio() == null || !entradaLot.getEsDevolucio())){		
								List entrades = resum.getLotsEmbotellat();
								entrades.add(entradaLot);
								resum.setLotsEmbotellat(entrades);
							}
						}
					}
					
				}else{
					resum = generaResum(resum, trazaEntrada);
					
					entradaDipositDao.setHibernateTemplate(getHibernateTemplate());
					EntradaDiposit entrada = entradaDipositDao.findByTraza(trazaEntrada.getId(), new Boolean(true));
					resum.setPartidaOli(entrada.getPartidaOli());
				}
				
			}else if(entradaDiposit!= null){
				Traza trazaEntrada = entradaDiposit.getTraza();
				resum = generaResum(resum, trazaEntrada);
				
				entradaDipositDao.setHibernateTemplate(getHibernateTemplate());
				EntradaDiposit entrada = entradaDipositDao.findByTraza(trazaEntrada.getId(), new Boolean(true));
				resum.setPartidaOli(entrada.getPartidaOli());
			}

			// Rendiment
			Double litres = 0.0;
			Double kilos = 0.0;
			for (Iterator it = resum.getElaboracions().iterator(); it.hasNext();){
				Elaboracio ela = (Elaboracio)it.next();
				litres += (ela.getLitres() == null ? 0.0 : ela.getLitres());
				kilos  += ela.getTotalKilos();
			}
			if (kilos > 0){
				resum.setRendiment(litres * 100 / kilos);
			} else {
				resum.setRendiment(0.0);
			}
			
//			// Partida d'oli
//			resum.setPartidaOli(deposito.getPartidaOli());
			
			// Analítica
			if (resum.getPartidaOli() != null){
				analiticaDao.setHibernateTemplate(getHibernateTemplate());
				resum.setAnalitica(analiticaDao.findUltimaByPartida(resum.getPartidaOli().getId()));
			}
			
			// Acidesa
			resum.setAcidesa(deposito.getAcidesa());
			
		}
		return resum;
	}
	
	/**
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" 
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition --> 
	 * @generated
	 */
	public TrazabilitatResumida trazabilitatResumidaOlivaDisponibleBota(String id) {
		TrazabilitatResumida resum = new TrazabilitatResumida();
		resum.setTipus(Constants.TIPUS_TRAZABILITAT_RESUMIDA_OLIVA_BOTA);
		resum.setOlivaTaula(true);
		botaDao.setHibernateTemplate(getHibernateTemplate());
		Bota bota = botaDao.getById(Long.valueOf(id));
		Hibernate.initialize(bota.getEstabliment().getNom());
		Hibernate.initialize(bota.getZona().getNom());
		
		if (bota != null) {		
			// BOTA
			Hibernate.initialize(bota.getElaboracio());
			resum.setBota(bota);
			resum.setCategoria(Constants.CATEGORIA_PENDENT);
			resum.setTipusOliva(bota.getTipusOlivaTaula());
			resum = generaResum(resum, bota.getTraza());
			
			List sortides = new ArrayList();
			Collection trazasHijos = bota.getTraza().getTrazasForTtrCodtrafill();
			for(Iterator itertrazasHijos=trazasHijos.iterator(); itertrazasHijos.hasNext();) {
				Traza trazaHijo = (Traza)itertrazasHijos.next();	
				if(trazaHijo.getTipus().intValue() == Constants.CODI_TRAZA_TIPUS_SORTIDA_BOTA){
					sortidaBotaDao.setHibernateTemplate(getHibernateTemplate());
					SortidaBota sortidaBota = sortidaBotaDao.findByTraza(trazaHijo.getId(), new Boolean(true));
					
					Collection trazasHijosSortida = trazaHijo.getTrazasForTtrCodtrafill();
					for(Iterator itertrazasHijosSortida = trazasHijosSortida.iterator(); itertrazasHijosSortida.hasNext();) {
						Traza trazaHijoSortida = (Traza)itertrazasHijosSortida.next();
						if(trazaHijoSortida.getTipus().intValue() == Constants.CODI_TRAZA_TIPUS_ENTRADA_LOT){
							entradaLotDao.setHibernateTemplate(getHibernateTemplate());
							EntradaLot entradaLot = entradaLotDao.findByTraza(trazaHijoSortida.getId(), new Boolean(true));
							if(entradaLot != null && (entradaLot.getEsDevolucio() == null || !entradaLot.getEsDevolucio())){		
								List entrades = resum.getLotsEmbotellat();
								entrades.add(entradaLot);
								resum.setLotsEmbotellat(entrades);
								sortides.add(sortidaBota);
							}
						}
					}
				}
			}
			resum.setSortidesBota(sortides);
		}
		return resum;
	}
	
	/**
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition --> 
	 * @generated
	 */
	public TrazabilitatResumida trazabilitatResumidaOliDisponibleLote(String id) {
		TrazabilitatResumida resum = new TrazabilitatResumida();
		resum.setTipus(Constants.TIPUS_TRAZABILITAT_RESUMIDA_LOT);
		lotDao.setHibernateTemplate(getHibernateTemplate());
		Lot lote = lotDao.getById(Long.valueOf(id));				
		
		if (lote != null) {				
			//LOT
			resum.setLot(lote);
			resum.setEstabliment(lote.getZona().getEstabliment());
						
			entradaLotDao.setHibernateTemplate(getHibernateTemplate());
			EntradaLot entradaLot = entradaLotDao.findUltimaByLot(lote.getId(), new Boolean(true));
			List entradesLot = new ArrayList();
			entradesLot.add(entradaLot);
			resum.setLotsEmbotellat(entradesLot);
			
			if(entradaLot != null && entradaLot.getDipositProcedencia()!= null){
				try {
					Long idDiposit = Long.valueOf(entradaLot.getDipositProcedencia());
					dipositDao.setHibernateTemplate(getHibernateTemplate());
					Diposit dip = dipositDao.getById(idDiposit);
					if(dip != null && dip.getCodiAssignat()!= null){
						resum.setDiposit(dip);
					}
				} catch (Exception e) {
//					entradaLotAux.setDipositProcedencia(entradaLot.getDipositProcedencia());
				}
			}
			
			Traza trazaEntrada = entradaLot.getTraza();
			resum = generaResum(resum, trazaEntrada);
			
			// Rendiment
			Double litres = 0.0;
			Double kilos = 0.0;
			for (Iterator it = resum.getElaboracions().iterator(); it.hasNext();){
				Elaboracio ela = (Elaboracio)it.next();
				litres += (ela.getLitres() == null ? 0.0 : ela.getLitres());
				kilos  += ela.getTotalKilos();
			}
			if (kilos > 0){
				resum.setRendiment(litres * 100 / kilos);
			} else {
				resum.setRendiment(0.0);
			}
			
			// Partida d'oli
			resum.setPartidaOli(lote.getPartidaOli());
			
			// Analítica
			if (resum.getPartidaOli() != null){
				analiticaDao.setHibernateTemplate(getHibernateTemplate());
				resum.setAnalitica(analiticaDao.findUltimaByPartida(lote.getPartidaOli().getId()));
			}
			
			// Acidesa
			resum.setAcidesa(lote.getAcidesa());
		}
		return resum;
	}
	
	/**
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition --> 
	 * @generated
	 */
	public TrazabilitatResumida trazabilitatResumidaOlivaDisponibleEnvas(String id) {
		TrazabilitatResumida resum = new TrazabilitatResumida();
		resum.setTipus(Constants.TIPUS_TRAZABILITAT_RESUMIDA_OLIVA_ENVAS);
		resum.setOlivaTaula(true);
		
		lotDao.setHibernateTemplate(getHibernateTemplate());
		Lot lote = lotDao.getById(Long.valueOf(id));	
		
		if (lote != null) {	
			//LOT
			resum.setLot(lote);
			if (lote.getOlivaDO() == null) {
				resum.setCategoria(Constants.CATEGORIA_PENDENT);
			} else if (lote.getOlivaDO()) {
				resum.setCategoria(Constants.CATEGORIA_DO);
			} else {
				resum.setCategoria(Constants.CATEGORIA_NO_DO);
			}
			resum.setTipusOliva(lote.getTipusOlivaTaula());
			if (lote.getDiposit() != null) {
				resum.setDiposit(lote.getDiposit());
				resum.setPartidaOli(lote.getPartidaOli());
				resum.setAcidesa(lote.getAcidesa());
			} 
			resum.setLotOliAfegit(lote.getLotOliAfegit()); 
						
			entradaLotDao.setHibernateTemplate(getHibernateTemplate());
			EntradaLot entradaLot = entradaLotDao.findUltimaByLot(lote.getId(), new Boolean(true));
			List entradesLot = new ArrayList();
			entradesLot.add(entradaLot);
			resum.setLotsEmbotellat(entradesLot);
			resum.setBota(lote.getBota());
			
			Collection tracesPare = entradaLot.getTraza().getTrazasForTtrCodtrapare();
			for(Iterator itertracesPare=tracesPare.iterator(); itertracesPare.hasNext();) {
				Traza tracaPare = (Traza)itertracesPare.next();	
				if(tracaPare.getTipus().intValue() == Constants.CODI_TRAZA_TIPUS_SORTIDA_BOTA){
					sortidaBotaDao.setHibernateTemplate(getHibernateTemplate());
					SortidaBota sortidaBota = sortidaBotaDao.findByTraza(tracaPare.getId(), new Boolean(true));
					List sortides = resum.getSortidesBota();
					sortides.add(sortidaBota);
					resum.setSortidesBota(sortides);
					break;
				}
			}
			resum = generaResum(resum, entradaLot.getTraza());
			
			// Autocontrols
			if (resum.getPartidaOli() != null){
				autocontrolOlivaDao.setHibernateTemplate(getHibernateTemplate());
				resum.setAutocontrolOliva(autocontrolOlivaDao.findUltimaByLot(lote.getId()));
			}
		}
		return resum;
	}
	
	/**
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition --> 
	 * @generated
	 */
	public SortidaBota findSortidaBotaByLot(Lot lot) {
		SortidaBota sortida = null;
		if (!lot.getEntradaLots().isEmpty()) {
			EntradaLot el = (EntradaLot)lot.getEntradaLots().iterator().next();
			Collection tracesPare = el.getTraza().getTrazasForTtrCodtrapare();
			for(Iterator itertracesPare=tracesPare.iterator(); itertracesPare.hasNext();) {
				Traza tracaPare = (Traza)itertracesPare.next();	
				if(tracaPare.getTipus().intValue() == Constants.CODI_TRAZA_TIPUS_SORTIDA_BOTA){
					sortidaBotaDao.setHibernateTemplate(getHibernateTemplate());
					sortida = sortidaBotaDao.findByTraza(tracaPare.getId(), new Boolean(true));
					break;
				}
			}
		}
		return sortida;
	}
	
	/**
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition --> 
	 * @generated
	 */
	public TrazabilitatResumida trazabilitatResumidaPartidaOliSinDeposito(String idPartida) {
		TrazabilitatResumida resum = new TrazabilitatResumida();
		resum.setTipus(Constants.TIPUS_TRAZABILITAT_RESUMIDA_PARTIDA);
		partidaOlivaDao.setHibernateTemplate(getHibernateTemplate());
		PartidaOliva partida = partidaOlivaDao.getById(Long.valueOf(idPartida));
		if(partida!= null){
			if (partida.getEsOlivaDeTaula()) {
				resum.setOlivaTaula(true);
				resum.setTipusOliva(partida.getTipusOlivaTaula());
			}
			//DESCOMPOSICION PARTIDAS								
			for(Iterator itDescompPartidas = partida.getDescomposicioPartidesOlives().iterator(); itDescompPartidas.hasNext();){
				DescomposicioPartidaOliva descompPartida = (DescomposicioPartidaOliva)itDescompPartidas.next();
//				resum.setResum(descompPartida.getDescomposicioPlantacio(), descompPartida.getKilos());
				resum.setResum(descompPartida);
			}
			resum.setRendiment(null);
			resum.setLitresTotal(null);
		}			
		
		return resum;
	}
	
	/*
	 * Crea un node de l'arbre de la trazabilitat resumida
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	private TrazabilitatResumida generaResum(TrazabilitatResumida resum, Traza traza) {
		Collection trazasPadre = traza.getTrazasForTtrCodtrapare();
		
		for(Iterator itertrazasPadre=trazasPadre.iterator(); itertrazasPadre.hasNext();){
			Traza trazaPadre = (Traza)itertrazasPadre.next();
		
			System.out.println("--> " + trazaPadre.getId() + " - " + traza.getId());
			
			switch (trazaPadre.getTipus().intValue()) {
			case Constants.CODI_TRAZA_TIPUS_ENTRADA_LOT:		// Embotellat o devolució
				if (resum.getLot() != null){
					entradaLotDao.setHibernateTemplate(getHibernateTemplate());
					EntradaLot entradaLot = entradaLotDao.findByTraza(trazaPadre.getId(), new Boolean(true));
					if(entradaLot != null && (entradaLot.getEsDevolucio() == null || !entradaLot.getEsDevolucio())){		
						List entrades = resum.getLotsEmbotellat();
						entrades.add(entradaLot);
						resum.setLotsEmbotellat(entrades);
					}
				}		
				generaResum(resum, trazaPadre);
				break;
			case Constants.CODI_TRAZA_TIPUS_SORTIDA_DIPOSIT:
				boolean continuar = true;
				// Si el fill és un trasllat, llavors hem de comprovar que sigui una entrada al nostre establiment
				Collection trazasHijos = trazaPadre.getTrazasForTtrCodtrafill();
				for(Iterator itertrazasHijos=trazasHijos.iterator(); itertrazasHijos.hasNext();){
					Traza trazaHijo = (Traza)itertrazasHijos.next();	
					if(trazaHijo.getTipus().intValue() == Constants.CODI_TRAZA_TIPUS_TRASLLAT_DIPOSIT){
						sortidaDipositDao.setHibernateTemplate(getHibernateTemplate());
						SortidaDiposit sortida = sortidaDipositDao.findByTraza(trazaPadre.getId(), new Boolean(true));
						if(sortida==null){
							continuar = false;
						}else{
						
							if (resum.getEstabliment() != null && sortida.getEstabliment().equals(resum.getEstabliment())) {
								continuar = false;
							}	
						}
						
//						trasllatDao.setHibernateTemplate(getHibernateTemplate());
//						Trasllat trasllat = trasllatDao.findByTraza(trazaHijo.getId());
					}
				}
				if (continuar) {
					if (resum.getLot() == null){ // No és la traçabilitat d'un lot
						// Si el fill és una entrada de lot, llavors ens trobam amb un embotellat
	//					Collection trazasHijos = trazaPadre.getTrazasForTtrCodtrafill();
						for(Iterator itertrazasHijos=trazasHijos.iterator(); itertrazasHijos.hasNext();){
							Traza trazaHijo = (Traza)itertrazasHijos.next();	
							if(trazaHijo.getTipus().intValue() == Constants.CODI_TRAZA_TIPUS_ENTRADA_LOT){
								entradaLotDao.setHibernateTemplate(getHibernateTemplate());
								EntradaLot entradaLot = entradaLotDao.findByTraza(trazaHijo.getId(), new Boolean(true));
								if(entradaLot != null && (entradaLot.getEsDevolucio() == null || !entradaLot.getEsDevolucio())){		
									List entrades = resum.getLotsEmbotellat();
									if (!entrades.contains(entradaLot)){
										entrades.add(entradaLot);
										resum.setLotsEmbotellat(entrades);
									}
								}
							}
						}
					}
					if (resum.getLot() != null && resum.getLot().getOlivaTaula() != null && resum.getLot().getOlivaTaula() == true){
						Map oliAfegit = resum.getOliAfegit();
						if (!oliAfegit.containsKey(trazaPadre)) {
							//Ultima entrada
							sortidaDipositDao.setHibernateTemplate(getHibernateTemplate());
							SortidaDiposit sortida = sortidaDipositDao.findByTraza(trazaPadre.getId(), new Boolean(true));
							Hibernate.initialize(sortida.getPartidaOli().getNom());
							oliAfegit.put(trazaPadre, sortida);
							resum.setOliAfegit(oliAfegit);
						}
					} else {
						generaResum(resum, trazaPadre);
					}
				}
				break;
			case Constants.CODI_TRAZA_TIPUS_ELABORACIO:
				elaboracioDao.setHibernateTemplate(getHibernateTemplate());
				Elaboracio elaboracio = elaboracioDao.findByTraza(trazaPadre.getId(), new Boolean(true));				
				
				if (elaboracio != null) {
				
					System.out.println("----> E: " + elaboracio.getId() + " - " + elaboracio.getEstablimentName());
					
					//ELABORACION
					List elaboracions = resum.getElaboracions();
					if (!elaboracions.contains(elaboracio)) {
						elaboracions.add(elaboracio);
						resum.setElaboracions(elaboracions);
						Double volumActual = new Double(0.00);
						if(resum.getDiposit().getActiu() == true &&  resum.getDiposit().getVolumActual() != null){
							volumActual = resum.getDiposit().getVolumActual();	
						}
						
						//comprovam si hi han més dipòsits amb la mateixa partida 
						Boolean existeix = dipositDao.hiHaAltresDipositsAssociatsPartidesOli(elaboracio.getPartidaOli().getId());	
						
						if(existeix){
							List diposits = dipositDao.findDipositsAmbPartidaId(resum.getDiposit().getPartidaOli().getId());
							resum.setDipositsAmbPartidaId(diposits);
						}
						
						resum.setVolumDiposit(volumActual);
					
						if(elaboracio.getPartidaOlivas()!=null){
							//PARTIDAS
							for(Iterator itPartidas = elaboracio.getPartidaOlivas().iterator(); itPartidas.hasNext();){
								PartidaOliva partida = (PartidaOliva)itPartidas.next();
								partidaOlivaDao.setHibernateTemplate(getHibernateTemplate());
								partidaOlivaDao.getById(partida.getId());
								
								//DESCOMPOSICION PARTIDAS								
								for(Iterator itDescompPartidas = partida.getDescomposicioPartidesOlives().iterator(); itDescompPartidas.hasNext();){
									DescomposicioPartidaOliva descompPartida = (DescomposicioPartidaOliva)itDescompPartidas.next();
									resum.setResum(descompPartida);
								}
							}
						}
					}
				}
				break;
			case Constants.CODI_TRAZA_TIPUS_ELABORACIO_OLIVA_TAULA:
				elaboracioOlivaDao.setHibernateTemplate(getHibernateTemplate());
				ElaboracioOliva elaboracioOliva = elaboracioOlivaDao.findByTraza(trazaPadre.getId(), new Boolean(true));
						
				if (elaboracioOliva != null) {
				
					//ELABORACION
					List elaboracions = resum.getElaboracionsOliva();
					elaboracions.add(elaboracioOliva);
					resum.setElaboracionsOliva(elaboracions);
					resum.setBota(elaboracioOliva.getBota());
					resum.setTipusOliva(elaboracioOliva.getBota().getTipusOlivaTaula());
					
					if(elaboracioOliva.getPartidaElaboracions() != null){
						Double quantitatTotal = 0.0;
						Double quantitatTotalCribada = 0.0;
						
						//PARTIDAS
						for(Iterator itPartidas = elaboracioOliva.getPartidaElaboracions().iterator(); itPartidas.hasNext();){
							PartidaElaboracio partida = (PartidaElaboracio)itPartidas.next();
							partidaOlivaDao.setHibernateTemplate(getHibernateTemplate());
							partidaOlivaDao.getById(partida.getPartida().getId());
							
							Double percent = (partida.getQuantitat() / partida.getPartida().getTotalKilos()) * 100.0;
							quantitatTotal += partida.getQuantitat();
							quantitatTotalCribada += partida.getQuantitatCriba();
							
							//DESCOMPOSICION PARTIDAS								
							for(Iterator itDescompPartidas = partida.getPartida().getDescomposicioPartidesOlives().iterator(); itDescompPartidas.hasNext();){
								DescomposicioPartidaOliva descompPartida = (DescomposicioPartidaOliva)itDescompPartidas.next();
								resum.setResum(descompPartida, percent);
							}
						}
						resum.setQuantitatTotal(quantitatTotal);
						resum.setQuantitatTotalCribada(quantitatTotalCribada);
					}
				}
				break;
			case Constants.CODI_TRAZA_TIPUS_ENTRADA_DIPOSIT:
				// En cas de qeu sigui entrada de dipòsit, es pot donar el cas que tinguem sortides 
				// que no es tenguin en compte, ja que hi ha processos que s'enllacen a la última 
				// entrada, enlloc de fer-ho a la última acció (entrada / sortida) del dipòsit
				for (Iterator it = trazaPadre.getTrazasForTtrCodtrafill().iterator(); it.hasNext();){
					Traza trazaFilla = (Traza)it.next();
					if (!trazaFilla.getId().equals(traza.getId()) && trazaFilla.getTipus().equals(Constants.CODI_TRAZA_TIPUS_SORTIDA_DIPOSIT)){
						if (resum.getLot() == null){ // No és la traçabilitat d'un lot
							// Si el fill és una entrada de lot, llavors ens trobam amb un embotellat
							Collection trazasFills = trazaPadre.getTrazasForTtrCodtrafill();
							for(Iterator itertrazasHijos=trazasFills.iterator(); itertrazasHijos.hasNext();){
								Traza trazaHijo = (Traza)itertrazasHijos.next();	
								if(trazaHijo.getTipus().intValue() == Constants.CODI_TRAZA_TIPUS_ENTRADA_LOT){
									entradaLotDao.setHibernateTemplate(getHibernateTemplate());
									EntradaLot entradaLot = entradaLotDao.findByTraza(trazaHijo.getId(), new Boolean(true));
									if(entradaLot != null && (entradaLot.getEsDevolucio() == null || !entradaLot.getEsDevolucio())){		
										List entrades = resum.getLotsEmbotellat();
										if (!entrades.contains(entradaLot)){
											entrades.add(entradaLot);
											resum.setLotsEmbotellat(entrades);
										}
									}
								}
							}
						}
						
					}
				}
				generaResum(resum, trazaPadre);
				break;
			case Constants.CODI_TRAZA_TIPUS_TRASLLAT_DIPOSIT:
			case Constants.CODI_TRAZA_TIPUS_SORTIDA_LOT:
			case Constants.CODI_TRAZA_TIPUS_VENDA_OLI:
			case Constants.CODI_TRAZA_TIPUS_PARTIDA:
			case Constants.CODI_TRAZA_TIPUS_ANALITICA:
			case Constants.CODI_TRAZA_TIPUS_TRASLLAT_OLI:
			default:
				generaResum(resum, trazaPadre);
				break;
			}
		}
		Double quantitat = new Double(0.00);
		Diposit dip = dipositDao.getById(resum.getDiposit().getId());
		if(resum.getDiposit()!= null && resum.getDiposit().getVolumActual()>0.0001){
			if(dip.getPartidaOli().getEsDO()){
				if(resum.getDipositsAmbPartidaId().size()==0){
					resum.setLitresTotal(resum.getVolumDiposit());
				}else{
					for(int i=0;i<=resum.getDipositsAmbPartidaId().size()-1;i++){
						quantitat += resum.getDipositsAmbPartidaId().get(i).getVolumActual();
					}
					resum.setLitresTotal(quantitat);
				}
			}
		}
		
		return resum;
	}
	
	/*
	 * Crea un node de l'arbre de la trazabilitat
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	private Collection  generaFullasPadre(Collection result,String idNodo,Collection trazasPadre) {
		for(Iterator itertrazasPadre=trazasPadre.iterator(); itertrazasPadre.hasNext();){
			Traza trazaPadre = (Traza)itertrazasPadre.next();		
			if(trazaPadre.getTipus().intValue() == Constants.CODI_TRAZA_TIPUS_SORTIDA_LOT){
				sortidaLotDao.setHibernateTemplate(getHibernateTemplate());
				SortidaLot sortidaLot = sortidaLotDao.findByTraza(trazaPadre.getId(), new Boolean(true));
				if(sortidaLot != null){
					Object[] fulla  = novaFulla("traza_"+trazaPadre.getId(), sortidaLot, idNodo, "SortidaLot", new Boolean(true));
					result.add(fulla);					
				}								
			}else if(trazaPadre.getTipus().intValue() == Constants.CODI_TRAZA_TIPUS_ENTRADA_LOT){
				entradaLotDao.setHibernateTemplate(getHibernateTemplate());
				EntradaLot entradaLot = entradaLotDao.findByTraza(trazaPadre.getId(), new Boolean(true));
				EntradaLot entradaLotAux = new EntradaLot();
				entradaLotAux.setId(entradaLot.getId()) ;
				entradaLotAux.setLot(entradaLot.getLot());
				entradaLotAux.setId(entradaLot.getId()) ;
				entradaLotAux.setLot(entradaLot.getLot());
				entradaLotAux.setAcidesa(entradaLot.getAcidesa());
				entradaLotAux.setData(entradaLot.getData());
				entradaLotAux.setNumerosContraetiquetes(entradaLot.getNumerosContraetiquetes());
				entradaLotAux.setTraza(entradaLot.getTraza());
				entradaLotAux.setValid(entradaLot.getValid());
				entradaLotAux.setZona(entradaLot.getZona());
				entradaLotAux.setBotelles(entradaLot.getBotelles());
				
				if(entradaLot != null){					
					
					if(entradaLot != null && entradaLot.getDipositProcedencia()!= null){
						try {
							Long idDiposit = Long.valueOf(entradaLot.getDipositProcedencia());
							dipositDao.setHibernateTemplate(getHibernateTemplate());
							Diposit dip = dipositDao.getById(idDiposit);
							if(dip != null && dip.getCodiAssignat()!= null){
								entradaLotAux.setDipositProcedencia(dip.getCodiAssignat());
							}
						} catch (Exception e) {
							entradaLotAux.setDipositProcedencia(entradaLot.getDipositProcedencia());
						}
					}					
					Object[] fulla  = novaFulla("traza_"+trazaPadre.getId(), entradaLotAux, idNodo, "EntradaLot", new Boolean(true));
					result.add(fulla);
					
				}				
				
				
			}else if(trazaPadre.getTipus().intValue() == Constants.CODI_TRAZA_TIPUS_TRASLLAT_DIPOSIT){
				trasllatDao.setHibernateTemplate(getHibernateTemplate());
				Trasllat trasllat = trasllatDao.findByTraza(trazaPadre.getId());
				if(trasllat != null){
					Object[] fulla = novaFulla("traza_"+trazaPadre.getId(), trasllat, idNodo, "Trasllat", new Boolean(true));
					result.add(fulla);
				}								
			}else if(trazaPadre.getTipus().intValue() == Constants.CODI_TRAZA_TIPUS_SORTIDA_DIPOSIT){
				sortidaDipositDao.setHibernateTemplate(getHibernateTemplate());
				SortidaDiposit sortidaDiposit = sortidaDipositDao.findByTraza(trazaPadre.getId(), new Boolean(true));
				if(sortidaDiposit != null){
					Object[] fulla = novaFulla("traza_"+trazaPadre.getId(), sortidaDiposit, idNodo, "SortidaDiposit", new Boolean(true));
					result.add(fulla);					
				}							
				
			}else if(trazaPadre.getTipus().intValue() == Constants.CODI_TRAZA_TIPUS_VENDA_OLI){
				sortidaDipositDao.setHibernateTemplate(getHibernateTemplate());
				SortidaDiposit sortidaDiposit = sortidaDipositDao.findByTraza(trazaPadre.getId(), new Boolean(true));
				if(sortidaDiposit != null){
					Object[] fulla = novaFulla("traza_"+trazaPadre.getId(), sortidaDiposit, idNodo, "SortidaDiposit", new Boolean(true));
					result.add(fulla);			
					
				}							
				
			}else if(trazaPadre.getTipus().intValue() == Constants.CODI_TRAZA_TIPUS_ENTRADA_DIPOSIT){
				entradaDipositDao.setHibernateTemplate(getHibernateTemplate());
				EntradaDiposit  entradaDiposit = entradaDipositDao.findByTraza(trazaPadre.getId(), new Boolean(true));
				if(entradaDiposit != null){
					Object[] fulla = novaFulla("traza_"+trazaPadre.getId(), entradaDiposit, idNodo, "EntradaDiposit", new Boolean(true));
					result.add(fulla);
					//Incorporamos la analitica si existe
					Collection trazasHijos = trazaPadre.getTrazasForTtrCodtrafill();
						for(Iterator itertrazasHijos=trazasHijos.iterator(); itertrazasHijos.hasNext();){
							Traza trazaHijo = (Traza)itertrazasHijos.next();	
							if(trazaHijo.getTipus().intValue() == Constants.CODI_TRAZA_TIPUS_ANALITICA){
								analiticaDao.setHibernateTemplate(getHibernateTemplate());
								Analitica analitica = analiticaDao.findByTraza(trazaHijo.getId());
								if(analitica != null){
									fulla = novaFulla("traza_"+trazaHijo.getId(), analitica, "traza_"+trazaPadre.getId(), "Analitica", new Boolean(true));
									result.add(fulla);
								}	
							}
						}
					}
					
			}else if(trazaPadre.getTipus().intValue() == Constants.CODI_TRAZA_TIPUS_ELABORACIO){
				elaboracioDao.setHibernateTemplate(getHibernateTemplate());
				Elaboracio  elaboracio = elaboracioDao.findByTraza(trazaPadre.getId(), new Boolean(true));
				if(elaboracio != null){
					Object[] fulla = novaFulla("traza_"+trazaPadre.getId(), elaboracio, idNodo, "Elaboracio", new Boolean(true));
					result.add(fulla);
					if(elaboracio.getPartidaOlivas()!=null){
						//PARTIDAS
						for(Iterator itPartidas = elaboracio.getPartidaOlivas().iterator(); itPartidas.hasNext();){
							PartidaOliva partida = (PartidaOliva)itPartidas.next();
							partidaOlivaDao.setHibernateTemplate(getHibernateTemplate());
							partidaOlivaDao.getById(partida.getId());
							fulla = novaFulla("Partida_"+partida.getId(), partida, "traza_"+trazaPadre.getId(), "Partida", new Boolean(true));
							result.add(fulla);
								//OLIVICULTOR							
							    fulla = novaFulla("Olivicultor_"+partida.getOlivicultor().getId(), partida.getOlivicultor(), "Partida_"+partida.getId(), "Olivicultor", new Boolean(true));
								result.add(fulla);
								//DESCOMPOSICION PARTIDAS								
								for(Iterator itDescompPartidas = partida.getDescomposicioPartidesOlives().iterator(); itDescompPartidas.hasNext();){
									DescomposicioPartidaOliva descompPartida = (DescomposicioPartidaOliva)itDescompPartidas.next();
									fulla = novaFulla("DescomPartida_"+descompPartida.getId(), descompPartida, "Partida_"+partida.getId(), "DescomposicioPartidaOliva", new Boolean(true));
									result.add(fulla);
								}
						}										
						//OliRetiratPropietario
						entradaDipositDao.setHibernateTemplate(getHibernateTemplate());
						Collection entradasDipositOliRetirado = entradaDipositDao.findOliRetiratPropietarioByElaboracio(elaboracio.getId(), new Boolean(true));
						
						for(Iterator it=entradasDipositOliRetirado.iterator(); it.hasNext();){
							EntradaDiposit entradaDipositOliRetirado = (EntradaDiposit)it.next();
							if(entradaDipositOliRetirado!= null){
								fulla = novaFulla("EntradaDiposit_"+entradaDipositOliRetirado.getId(), entradaDipositOliRetirado, "traza_"+trazaPadre.getId(), "OliRetiratPropietario", new Boolean(true));
								result.add(fulla);	
							}
						}
						
												
					}	
				}
			}
			
			trazasPadre = trazaPadre.getTrazasForTtrCodtrapare();
			result = generaFullasPadre(result,"traza_"+trazaPadre.getId(),trazasPadre);
			
			
		}
		return result;
	}
	
	
	/*
	 * Crea un node de l'arbre de la trazabilitat
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	private Object[] novaFulla(String id, Object o, String pare, String tipo, Boolean autoritzat) {
		Object[] fulla = new Object[5];
		fulla[0] = id;
		fulla[1] = o;
		fulla[2] = pare;
		fulla[3] = tipo;
		fulla[4] = autoritzat;
		return fulla;
	}
	
	/*
	 * Crea un node de l'arbre de la trazabilitat
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	private Object[] novaFullaResum(String id, Object o, PartidaOli partidaOli, VarietatOli varietat, Double quantitat, Double rendiment, String pare, String tipo, Boolean autoritzat) {
		Object[] fulla = new Object[10];
		fulla[0] = id;
		fulla[1] = (partidaOli != null? partidaOli.getNom(): null);
		fulla[2] = (partidaOli != null? partidaOli.getCategoriaOli().getNom(): null);
		fulla[3] = (varietat != null? varietat.getNom(): null);
		fulla[4] = o;
		fulla[5] = quantitat;
		fulla[6] = rendiment;
		fulla[7] = pare;
		fulla[8] = tipo;
		fulla[9] = autoritzat;
		return fulla;
	}
		
	/**
	 * Cerca tots las diposits de un establecimiento no vacios y que pertenecen a unas determinadas categorias <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection findDipositsActiusNoVaciosByEstablecimientoAndCategorias(Long establimentId,Object[] categorias) {
		dipositDao.setHibernateTemplate(getHibernateTemplate());
		return dipositDao.findNoVaciosByEstablecimientoAndCategorias(establimentId, categorias);
	}
	
	/**
	 * Cerca tots las diposits de un establecimiento no vacios y que pertenecen a unas determinadas categorias <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection findDipositsActiusNoVaciosByEstablecimientoCategoriasAndData(Long establimentId, Object[] categorias, Date data) {
		return findDipositsActiusNoVaciosByEstablecimientoCategoriasAndData(establimentId, categorias, data, null);
	}
	
	/**
	 * Cerca tots las diposits de un establecimiento no vacios y que pertenecen a unas determinadas categorias <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection findDipositsActiusNoVaciosByEstablecimientoCategoriasAndData(Long establimentId, Object[] categorias, Date data, Boolean dipositsEnvasadora) {
		
		if (dipositsEnvasadora == null) logger.info("Existències dipòsits - Init");
		else if (dipositsEnvasadora == true) logger.info("Existències dipòsits de envasadora - Init");
		else logger.info("Existències dipòsits de tafona - Init");
		
		Collection dipositsAmbExistencia = new ArrayList();
		dipositDao.setHibernateTemplate(getHibernateTemplate());
		Collection diposits = dipositDao.findByEstablimentEnData(establimentId, data);
		entradaDipositDao.setHibernateTemplate(getHibernateTemplate());
		sortidaDipositDao.setHibernateTemplate(getHibernateTemplate());
		for (Iterator it = diposits.iterator(); it.hasNext();){
			java.math.BigInteger dipositId = (java.math.BigInteger)it.next();
			Diposit diposit = dipositDao.getById(dipositId.longValue());
			//Valid en 3 casos:
			// 1- No es té en compte si són dipòsits d'envasadora o no (dipositsEnvasadora = null)
			// 2- Només es tenen en compte els dipòsits d'envasadora
			// 3- Només es tenen en compte els dipòsits que no son d'envasadora
			
			if( // Dipòsits de tafona-envasadora 
					dipositsEnvasadora == null || 
				// Dipòsits d'envasadora:
				(	(dipositsEnvasadora == true &&
					// Dipòsit marcat com a dipòsit d'envasadora
					diposit.getDeEnvasadora() != null && diposit.getDeEnvasadora() == true) &&
					// i no provinent d'un altre establiment (teus)
					(diposit.getZonaOrigenTrasllat() == null || (diposit.getZonaOrigenTrasllat() != null && diposit.getZonaOrigenTrasllat().getEstabliment().getId().equals(establimentId)))
				) ||
				// Dipòsits de tafona
				(	(dipositsEnvasadora == false &&
					// Dipòsits no marcats com a dipòsit d'envasadora
					(diposit.getDeEnvasadora() == null || diposit.getDeEnvasadora() == false || 
					// o dipòsits provinents d'altres establiments
					(diposit.getZonaOrigenTrasllat() != null && !diposit.getZonaOrigenTrasllat().getEstabliment().getId().equals(establimentId)))))
			){
				
				Double entrades = entradaDipositDao.getSumaEntradesDipositPerDipositFinsData(diposit.getId(), data, new Boolean(true));
				
				Double sortides = sortidaDipositDao.getSumaSortidesDipositPerDipositFinsData(diposit.getId(), data, new Boolean(true));
				Double existencies = entrades - sortides;
				if (existencies > 0.0001){ // Evitam problemes amb decimals
//					entradaDipositDao.setHibernateTemplate(getHibernateTemplate());
					CategoriaOli cat = entradaDipositDao.getCategoriaUltimaEntradesDipositPerDipositFinsData(diposit.getId(), data, new Boolean(true));
					for (int i = 0; i < categorias.length; i++){
						if (cat != null && cat.getId().equals((Integer)categorias[i])){
							String nomDiposit = diposit.getCodiAssignat();
							if (!diposit.getEstabliment().getId().equals(establimentId)){
								if (diposit.getZonaOrigenTrasllat() != null){
									nomDiposit = "(" + diposit.getZonaOrigenTrasllat().getEstabliment().getNom() + ") " + diposit.getCodiAssignat();
								} else {
									nomDiposit = "(" + diposit.getEstabliment().getNom() + ") " + diposit.getCodiAssignat();
								}
							}
							
							logger.info("Dipòsit: "+nomDiposit+" | Exist. l.: "+existencies+" | Exist. kg.: "+(existencies * 0.916));
							
							Object[] obj = {diposit.getId(),
//											(diposit.getCodiOriginal() == null ? diposit.getCodiAssignat() : diposit.getCodiOriginal()),
											nomDiposit,
											existencies, //litres
											(existencies * 0.916), //quilos
											cat,
											(diposit.getPartidaOli() != null)?diposit.getPartidaOli().getOlivicultorEsPropietari():null};
							dipositsAmbExistencia.add(obj);
							break;
						}
					}
				}
			}
		}
		logger.info("Existències dipòsits - Fi");
		return dipositsAmbExistencia;
	}
	
	
	/**
	 * Cerca tots las diposits de un establecimiento no vacios y que pertenecen a unas determinadas categorias <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection findBotasActivasByEstablecimientoAndData(Long establimentId, Date data) {
		botaDao.setHibernateTemplate(getHibernateTemplate());
		return botaDao.findByEstablimentEnData(establimentId, data);
	}
	
	/**
	 * Cerca tots las diposits de un establecimiento no vacios y que pertenecen a unas determinadas categorias <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Double findExistenciesDipositByData(Long idDiposit, Date data) {
		dipositDao.setHibernateTemplate(getHibernateTemplate());
		Diposit diposit = dipositDao.getById(idDiposit);
		
		entradaDipositDao.setHibernateTemplate(getHibernateTemplate());
		Double entrades = entradaDipositDao.getSumaEntradesDipositPerDipositFinsData(diposit.getId(), data, new Boolean(true));
		
		sortidaDipositDao.setHibernateTemplate(getHibernateTemplate());
		Double sortides = sortidaDipositDao.getSumaSortidesDipositPerDipositFinsData(diposit.getId(), data, new Boolean(true));
		Double existencies = entrades - sortides;
		
		return existencies;
	}
	

	/**
	 * Devuelve todos las botellas de un lote de un establecimiento no vendidos en una fecha y que pertenecen a un determinado etiquetage 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Integer findBotellesByEstablimentEtiquetatgeAndData(Long establimentId, Long etiquetatgeId, Date data) {
		lotDao.setHibernateTemplate(getHibernateTemplate());
		return lotDao.findBotellesByEstablimentEtiquetageAndData(establimentId, etiquetatgeId, data);
	}
	
	/**
	 * Devuelve todos los lotes de un establecimiento no vendidos y que pertenecen a unas determinadas categorias <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection findLotsNoVendidosByEstablecimientoAndCategorias(Long establimentId,Object[] categorias) {
		lotDao.setHibernateTemplate(getHibernateTemplate());
		return lotDao.findNoVendidosByEstablecimientoAndCategorias(establimentId, categorias, new Boolean(true));
	}
	
	/**
	 * Cerca tots els lots d'un establiment no buits i que pertanyen a unes determinades categories 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" 
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection findLotsNoVendidosByEstablecimientoCategoriasAndData(Long establimentId, Object[] categorias, Date data) {
		Collection lotsAmbExistencia = new ArrayList();
		lotDao.setHibernateTemplate(getHibernateTemplate());
		entradaLotDao.setHibernateTemplate(getHibernateTemplate());
		sortidaLotDao.setHibernateTemplate(getHibernateTemplate());
		Collection lots = lotDao.findByEstablimentIData(establimentId, data, new Boolean(true));
		logger.info("Existències lots - Inici");
		for (Iterator it = lots.iterator(); it.hasNext();){
			Lot lot = (Lot)it.next();
			Boolean olivaTaula = lot.getOlivaTaula()==null?false : lot.getOlivaTaula();
			if (!olivaTaula) {
				
				logger.info("Lot: "+lot.getCodiAssignat() + " (" + lot.getNumeroBotellesActuals() + " bot.)");
				
				Integer sortides = sortidaLotDao.getSumaSortidesLotFinsData(lot.getId(), data, new Boolean(true));
				Integer devolucions = entradaLotDao.getSumaDevolucionsLotFinsData(lot.getId(), data, new Boolean(true));
				Double existencies = (lot.getNumeroBotellesActuals() + sortides - devolucions) * lot.getEtiquetatge().getTipusEnvas().getVolum();
				if (existencies > 0.0001){ // Evitam problemes amb decimals
					CategoriaOli cat = lot.getPartidaOli().getCategoriaOli();
					for (int i = 0; i < categorias.length; i++){
						if (cat.getId().equals((Integer)categorias[i])){
							
							logger.info("Lot: "+lot.getCodiAssignat()+" | Exist. l.: "+existencies+" | Exist. kg.: "+(existencies * 0.916));
							
							Object[] obj = {lot.getId(),
											lot.getCodiAssignat(),
											existencies, //Existències litre
											(existencies * 0.916), //Existències kilos
											cat, //Categoria lot
											(lot.getEtiquetatge() != null)?lot.getEtiquetatge().getMarca().getNom():"", //Marca
											(lot.getEtiquetatge() != null)?lot.getEtiquetatge().getTipusEnvas().getNomSelect():"", //Etiquetatge
											(lot.getNumeroBotellesActuals() + sortides - devolucions),//Nº botelles
											(lot.getPartidaOli() != null)?lot.getPartidaOli().getOlivicultorEsPropietari():"",
											(lot.getProducte() != null)?lot.getProducte().getNom():""}; //Producte 
							lotsAmbExistencia.add(obj);
							
							break;
						}
					}
				}
			}
		}
		logger.info("Existències lots - Fi");
		return lotsAmbExistencia;
	}
	
	/**
	 * Cerca tots els lots d'un establiment no buits i que pertanyen a unes determinades categories 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" 
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition -->
	 */
	
	public Collection findLotsOlivaTaulaDisponiblesByEstablecimientoAndData(Long establimentId, Date data) {
		Collection lotsAmbExistencia = new ArrayList();
		lotDao.setHibernateTemplate(getHibernateTemplate());
		Collection lots = lotDao.findByEstablimentIData(establimentId, data, new Boolean(true));
		logger.info("Existències lots - Inici");
		for (Iterator it = lots.iterator(); it.hasNext();){
			Lot lot = (Lot)it.next();
			Boolean olivaTaula = lot.getOlivaTaula()==null?false : lot.getOlivaTaula();
			if (olivaTaula) {
				sortidaLotDao.setHibernateTemplate(getHibernateTemplate());
				Integer sortides = sortidaLotDao.getSumaSortidesLotFinsData(lot.getId(), data, new Boolean(true));
				entradaLotDao.setHibernateTemplate(getHibernateTemplate());
				Integer devolucions = entradaLotDao.getSumaDevolucionsLotFinsData(lot.getId(), data, new Boolean(true));
				Double existencies = (lot.getNumeroBotellesActuals() + sortides - devolucions) * lot.getEtiquetatge().getTipusEnvas().getVolum();
				if (existencies > 0.0001){ // Evitam problemes amb decimals
					logger.info("Lot: "+lot.getCodiAssignat()+" | Exist. l.: "+existencies+" | Exist. kg.: "+(existencies * 0.916));
					
					Object[] obj = {lot.getId(),
									lot.getCodiAssignat(),
									existencies, //Existències litre
									(existencies * 0.916), //Existències kilos
									lot.getTipusOlivaTaula(), //Categoria lot
									(lot.getEtiquetatge() != null)?lot.getEtiquetatge().getMarca().getNom():"", //Marca
									(lot.getEtiquetatge() != null)?lot.getEtiquetatge().getTipusEnvas().getNomSelect():"", //Etiquetatge
									(lot.getNumeroBotellesActuals() + sortides - devolucions),//Nº botelles
									(lot.getPartidaOli() != null)?lot.getPartidaOli().getOlivicultorEsPropietari():"",
									(lot.getProducte() != null)?lot.getProducte().getNom():""}; //Producte 
					lotsAmbExistencia.add(obj);
				}
			}
		}
		logger.info("Existències lots - Fi");
		return lotsAmbExistencia;
	}
	
	/**
	 * Cerca tots els lots d'un establiment no buits i que pertanyen a unes determinades categories 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" 
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition -->
	 */
	
	public Collection findLotsOlivaTaulaAmbExistenciaByEstablecimientoAndData(Long establimentId, Date data) {
		Collection lotsAmbExistencia = new ArrayList();
		lotDao.setHibernateTemplate(getHibernateTemplate());
		Collection lots = lotDao.findByEstablimentIData(establimentId, data, new Boolean(true));
		logger.info("Existències lots - Inici");
		for (Iterator it = lots.iterator(); it.hasNext();){
			Lot lot = (Lot)it.next();
			Boolean olivaTaula = lot.getOlivaTaula()==null?false : lot.getOlivaTaula();
			if (olivaTaula) {
				sortidaLotDao.setHibernateTemplate(getHibernateTemplate());
				Integer sortides = sortidaLotDao.getSumaSortidesLotFinsData(lot.getId(), data, new Boolean(true));
				entradaLotDao.setHibernateTemplate(getHibernateTemplate());
				Integer devolucions = entradaLotDao.getSumaDevolucionsLotFinsData(lot.getId(), data, new Boolean(true));
				Double existencies = (lot.getNumeroBotellesActuals() + sortides - devolucions) * lot.getEtiquetatge().getTipusEnvas().getVolum();
				if (existencies > 0.0001){ // Evitam problemes amb decimals
					lotsAmbExistencia.add(lot);
				}
			}
		}
		logger.info("Existències lots - Fi");
		return lotsAmbExistencia;
	}
	
	/**
	 * Devuelve todos los lotes de un establecimiento no vendidos <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection findLotsNoVendidosByEstablecimiento(Long establimentId) {
		lotDao.setHibernateTemplate(getHibernateTemplate());
		return lotDao.findNoVendidosByEstablecimiento(establimentId, new Boolean(true));
	}
	
	/**
	 * Devuelve todos los lotes de un establecimiento no vendidos <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public List findBotellesLotsNousByEstablimentAndDataGroupedByEtiquetatge(Long establimentId, Date data) {
		lotDao.setHibernateTemplate(getHibernateTemplate());
		return lotDao.findBotellesLotsNousByEstablimentAndDataGroupedByEtiquetatge(establimentId, data);
	}
	
	/**
	 * Devuelve todos las salidas de un lote de un establecimiento no vendidos en una fecha y que pertenecen a un determinado etiquetage 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public List findSoridesLotByEstablimentAndDataGroupedByEtiquetatge(Long establimentId, Date data) {
		lotDao.setHibernateTemplate(getHibernateTemplate());
		return lotDao.findSoridesLotByEstablimentAndDataGroupedByEtiquetatge(establimentId, data);
	}
	
	/**
	 * Devuelve todos los lotes de un establecimiento no vendidos <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection findExistenciesLotsByEstabliment(Long establimentId) {
		lotDao.setHibernateTemplate(getHibernateTemplate());
		Collection lots = lotDao.findExistenciesLotsByEstabliment(establimentId, new Boolean(true));
		
		for(Iterator it=lots.iterator(); it.hasNext();){
			Lot l = (Lot)it.next();
			
			Hibernate.initialize(l.getEtiquetatge());
			Hibernate.initialize(l.getEtiquetatge().getTipusEnvas());
			Hibernate.initialize(l.getEtiquetatge().getTipusEnvas().getVolum());
			
			if(l.getPartidaOli() != null){
				Hibernate.initialize(l.getPartidaOli());
				Hibernate.initialize(l.getPartidaOli().getNom());
			}
		}
		
		return lots;
	}
	
	/**
	 * Cerca tots las diposits de un establecimiento no vacios y que pertenecen a unas determinadas categorias <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Double findExistenciesLotByData(Long idLot, Date data) {
		lotDao.setHibernateTemplate(getHibernateTemplate());
		Lot lot = lotDao.getById(idLot);
		
		sortidaLotDao.setHibernateTemplate(getHibernateTemplate());
		Integer sortides = sortidaLotDao.getSumaSortidesLotFinsData(idLot, data, new Boolean(true));
		
		entradaLotDao.setHibernateTemplate(getHibernateTemplate());
		Integer devolucions = entradaLotDao.getSumaDevolucionsLotFinsData(idLot, data, new Boolean(true));

		Double existencies = (lot.getNumeroBotellesActuals() + sortides - devolucions) * lot.getEtiquetatge().getTipusEnvas().getVolum();
		return existencies;
	}
	
	/**
	 * Devuelve todos los lotes de un establecimiento no vendidos <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection findLotsByEstablimentEntreDates(Long idEstabliment, Date dataInici, Date dataFi) {
		lotDao.setHibernateTemplate(getHibernateTemplate());
		return lotDao.findByEstablimententreDates(idEstabliment, dataInici, dataFi, false);
	}
	
	/**
	 * Devuelve todos los lotes de un establecimiento no vendidos <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection findLotsOlivaTaulaByEstablimentEntreDates(Long idEstabliment, Date dataInici, Date dataFi) {
		lotDao.setHibernateTemplate(getHibernateTemplate());
		return lotDao.findByEstablimententreDates(idEstabliment, dataInici, dataFi, true);
	}
	
	/**
	 * Devuelve todos los lotes de un establecimiento no vendidos <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection findLotsSortidesByEstablimentIDataInici(Long establimentId, Date data) {
		lotDao.setHibernateTemplate(getHibernateTemplate());
		Collection lots = lotDao.findLotsSortidesByEstablimentIDataInici(establimentId, data, new Boolean(true));
		
		for(Iterator it=lots.iterator(); it.hasNext();){
			Lot l = (Lot)it.next();
			
			Hibernate.initialize(l.getEtiquetatge());
			Hibernate.initialize(l.getEtiquetatge().getTipusEnvas());
			Hibernate.initialize(l.getEtiquetatge().getTipusEnvas().getVolum());
			
			if(l.getPartidaOli() != null){
				Hibernate.initialize(l.getPartidaOli());
				Hibernate.initialize(l.getPartidaOli().getNom());
			}
		}
		
		return lots;
	}

	/**
	 * Devuelve todos los lotes de un establecimiento no vendidos <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection findLotsSortidesByEstablimentEntreDates(Long establimentId, Date dataInici, Date dataFi) {
		lotDao.setHibernateTemplate(getHibernateTemplate());
		Collection lots = lotDao.findLotsSortidesByEstablimentEntreDates(establimentId, dataInici, dataFi, new Boolean(true));
		
		for(Iterator it=lots.iterator(); it.hasNext();){
			Lot l = (Lot)it.next();
			
			Hibernate.initialize(l.getEtiquetatge());
			Hibernate.initialize(l.getEtiquetatge().getTipusEnvas());
			Hibernate.initialize(l.getEtiquetatge().getTipusEnvas().getVolum());
			Hibernate.initialize(l.getValid());
			
			if(l.getPartidaOli() != null){
				Hibernate.initialize(l.getPartidaOli());
				Hibernate.initialize(l.getPartidaOli().getNom());
			}
		}
		
		return lots;
	}
	
	
	/**
	 * Busca las entradas de depositos entre 2 fechas para un establecimiento
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection getEntradasDipositEntreFechasAndEstablecimiento(Date finicio, Date ffin, Long idEst){
		entradaDipositDao.setHibernateTemplate(getHibernateTemplate());
		return entradaDipositDao.getEntradasDipositEntreFechasAndEstablecimiento(finicio, ffin, idEst, new Boolean(true));
	
	}
	
	
	
	/**
	 * Busca las salidas de depositos entre 2 fechas para un establecimiento
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection getSortidesDipositEntreFechasAndEstablecimiento(Date finicio, Date ffin, Long idEst){
		sortidaDipositDao.setHibernateTemplate(getHibernateTemplate());
		return sortidaDipositDao.getSortidesDipositEntreFechasAndEstablecimiento(finicio, ffin, idEst, new Boolean(true));
	
	}
	
//	/**
//	 * Busca las salidas de depositos entre 2 fechas para un establecimiento
//	 * 
//	 * <!-- begin-xdoclet-definition -->
//	 * @ejb.interface-method view-type="both"
//	 * @ejb.permission role-name="OLI_PRODUCTOR"
//	 * @ejb.permission role-name="OLI_ENVASADOR"
//	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
//	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
//	 * @ejb.permission role-name="OLI_DOGESTOR"
//	 * <!-- end-xdoclet-definition -->
//	 */
//	public Collection getSortidesDipositEnvasadoraEntreFechasAndEstablecimiento(Date finicio, Date ffin, Long idEst, Boolean valid){
//		sortidaDipositDao.setHibernateTemplate(getHibernateTemplate());
//		return sortidaDipositDao.getSortidesDipositEnvasadoraEntreFechasAndEstablecimiento(finicio, ffin, idEst, valid);
//	}
	
	/**
	 * Busca las salidas de depositos entre 2 fechas para un establecimiento
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection getEntradesDipositEnvasadoraEntreFechasAndEstablecimiento(Date finicio, Date ffin, Long idEst){
		entradaDipositDao.setHibernateTemplate(getHibernateTemplate());
		return entradaDipositDao.getEntradesDipositEnvasadoraEntreFechasAndEstablecimiento(finicio, ffin, idEst);
	}
	
	/**
	 * Busca las salidas de depositos entre 2 fechas para un establecimiento
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection getSortidesDipositEntreFechasAndEstablecimiento(Date finicio, Date ffin, Long idEst, Boolean valid){
		sortidaDipositDao.setHibernateTemplate(getHibernateTemplate());
		return sortidaDipositDao.getSortidesDipositEntreFechasAndEstablecimiento(finicio, ffin, idEst, valid);
	
	}
	
	/**
	 * Busca las salidas de lotes entre 2 fechas para un establecimiento
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection getSortidesLotsEntreDadesAndEstabliment(Date finicio, Date ffin, Long idEst, Boolean valid){
		sortidaLotDao.setHibernateTemplate(getHibernateTemplate());
		return sortidaLotDao.findByEstablecimientoEntreDates(idEst, valid, finicio, ffin);
	
	}
	
	/**
	 * Busca las salidas de lotes entre 2 fechas para un establecimiento
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection getSortidesLotsOlivaTaulaEntreDadesAndEstabliment(Date finicio, Date ffin, Long idEst, Boolean valid){
		sortidaLotDao.setHibernateTemplate(getHibernateTemplate());
		return sortidaLotDao.findOlivaTaulaByEstablecimientoEntreDates(idEst, valid, finicio, ffin);
	
	}
	
	/**
	 * Busca las salidas de lotes entre 2 fechas para un establecimiento
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection getEntradesLotsEntreDadesAndEstabliment(Date finicio, Date ffin, Long idEst, Boolean valid){
		entradaLotDao.setHibernateTemplate(getHibernateTemplate());
		return entradaLotDao.findByEstablecimientoEntreDates(idEst, valid, finicio, ffin);
	
	}
	
	/**
	 * Busca las salidas de lotes entre 2 fechas para un establecimiento
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection getEntradesLotsDevolucioEntreDadesAndEstabliment(Date finicio, Date ffin, Long idEst, Boolean valid){
		entradaLotDao.setHibernateTemplate(getHibernateTemplate());
		return entradaLotDao.findDevolucioByEstablecimientoEntreDates(idEst, valid, finicio, ffin);
	
	}
	
	/**
	 * Busca las entradas de lotes entre 2 fechas para un establecimiento
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection getEntradesLotesEntreFechasAndEstablecimiento(Date finicio, Date ffin, Long idEst, String entrada){
		entradaLotDao.setHibernateTemplate(getHibernateTemplate());
		return entradaLotDao.getEntradasLotesEntreFechasAndEstablecimiento(finicio, ffin, idEst, null, entrada);
	
	}
	

	/**
	 * Busca las salidas de lotes entre 2 fechas para un establecimiento
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection getSortidesLotesEntreFechasAndEstablecimiento(Date finicio, Date ffin, Long idEst){
		sortidaLotDao.setHibernateTemplate(getHibernateTemplate());
		return sortidaLotDao.getSortidesLotesEntreFechasAndEstablecimiento(finicio, ffin, idEst, null);
	
	}
	

	/**
	 * Devuelve la cantidad total de litros de aceite existente para un establecimiento y categoria
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Double getQuantitatOliExistenciesDiscriminantQualificacioAndEstabliment(/*Long temporadaId,*/Long establimentId, Date dataInici,Object[] categorias){
		dipositDao.setHibernateTemplate(getHibernateTemplate());
//		return dipositDao.getQuantitatOliExistenciesDiscriminantQualificacioAndEstabliment(temporadaId,establimentId, dataInici, categorias);
		return dipositDao.getQuantitatOliExistenciesDiscriminantQualificacioAndEstabliment(establimentId, dataInici, categorias);
	}
	
	/**
	 * Devuelve la cantidad total de litros de aceite existente para un establecimiento y categoria
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Double[] getQuantitatOliExistenciesQualificacioAndEstabliment(Long establimentId, Date data){
		Collection dipositsAmbExistencia = new ArrayList();
		dipositDao.setHibernateTemplate(getHibernateTemplate());
		Double existencies[] = {0.0, 0.0, 0.0};
		// DO
		existencies[Constants.CATEGORIA_DO-1] = existenciesCategoria(establimentId, new Long(Constants.CATEGORIA_DO), data);
		// Pendent
		existencies[Constants.CATEGORIA_PENDENT-1] = existenciesCategoria(establimentId, new Long(Constants.CATEGORIA_PENDENT), data);
		// No DO
		existencies[Constants.CATEGORIA_NO_DO-1] = existenciesCategoria(establimentId, new Long(Constants.CATEGORIA_NO_DO), data);
		return existencies;
	}
	
	Double existenciesCategoria(Long establimentId, Long categoriaId, Date data){
		Double exist = 0.0;
		dipositDao.setHibernateTemplate(getHibernateTemplate());
		Collection diposits = dipositDao.findByEstablimentCategoriaEnData(establimentId, categoriaId, data);
		Long[] dipositsId = new Long[diposits.size()];
		int i = 0;
		String sDipositsId = "";
		for (Iterator it = diposits.iterator(); it.hasNext(); i++){
			java.math.BigInteger dipositId = (java.math.BigInteger)it.next();
			dipositsId[i] = dipositId.longValue();
			sDipositsId += dipositId + ", ";
		}
//		logger.info("CATEGORIA: " + categoriaId + " -----------------------------------------------------------------------------");
		
		if (dipositsId.length > 0) {
			entradaDipositDao.setHibernateTemplate(getHibernateTemplate());
			Double entrades = entradaDipositDao.getSumaEntradesDipositsFinsData(dipositsId, data, new Boolean(true));
			sortidaDipositDao.setHibernateTemplate(getHibernateTemplate());
			Double sortides = sortidaDipositDao.getSumaSortidesDipositsFinsData(dipositsId, data, new Boolean(true));
			exist = entrades - sortides;
//			logger.info("Dips: " + sDipositsId + " - E: " + entrades + " - S: " + sortides + " = " + (entrades - sortides));
		}
		
		
//		logger.info("EXISTENCIES DIPOSITS GRUP (Categoria " + categoriaId + ") = " + exist);
//		
//		exist = 0.0;
//		
		Double ents = 0.0;
//		Double sorts = 0.0;
//		for (Iterator it = diposits.iterator(); it.hasNext();){
//			Diposit diposit = (Diposit)it.next();
//			entradaDipositDao.setHibernateTemplate(getHibernateTemplate());
//			//Double 
//			entrades = entradaDipositDao.getSumaEntradesDipositPerDipositFinsData(diposit.getId(), data, new Boolean(true));
//			ents += entrades;
//			sortidaDipositDao.setHibernateTemplate(getHibernateTemplate());
//			//Double 
//			sortides = sortidaDipositDao.getSumaSortidesDipositPerDipositFinsData(diposit.getId(), data, new Boolean(true));
//			sorts += sortides;
//			Double existencia = entrades - sortides;
//			logger.info("Dip: " + diposit.getCodiAssignat() + " - E: " + entrades + " - S: " + sortides + " = " + (entrades - sortides) + ". Acumulat: Et: " + ents + " - St: " + sorts + " = " + (ents - sorts));
//			if (existencia > 0.0001){ // Evitam problemes amb decimals
//				exist += existencia;
//			}
//		}
//		
//		logger.info("EXISTENCIES DIPOSITS INDIVIDUAL (Categoria " + categoriaId + ") = " + exist);
		
		lotDao.setHibernateTemplate(getHibernateTemplate());
		Collection lots = lotDao.findByEstablimentCategoriaIData(establimentId, categoriaId, data);
		
		Long[] lotsId = new Long[lots.size()];
		i = 0;
		String sLotsId = "";
		for (Iterator it = lots.iterator(); it.hasNext(); i++){
			Lot lot = (Lot)it.next();
			lotsId[i] = lot.getId();
			sLotsId += lot.getId() + ", ";
		}
		
		Double existLots = 0.0;
		if (lotsId.length > 0) {
			lotDao.setHibernateTemplate(getHibernateTemplate());
			Double actual = lotDao.getSumaContingutActual(lotsId, data);
			sortidaLotDao.setHibernateTemplate(getHibernateTemplate());
			Long milis = new Date().getTime();
			Double sortides = sortidaLotDao.getSumaSortidesLotsFinsData(lotsId, data, new Boolean(true)); 
			logger.info("       Sortides: " + (milis - new Date().getTime()));
			entradaLotDao.setHibernateTemplate(getHibernateTemplate());
			milis = new Date().getTime();
			Double devolucions = entradaLotDao.getSumaDevolucionsLotsFinsData(lotsId, data, new Boolean(true));
			logger.info("       Devolucions: " + (milis - new Date().getTime()));
			existLots = actual + sortides - devolucions;
//			logger.info("Lots: " + sLotsId + " - A: " + actual + " - S: " + sortides + " D: " + devolucions + " = " + existLots);
		}
		
//		logger.info("EXISTENCIES LOTS GRUP (Categoria " + categoriaId + ") = " + existLots);
		
//		for (Iterator it = lots.iterator(); it.hasNext();){
//			Lot lot = (Lot)it.next();
//			sortidaLotDao.setHibernateTemplate(getHibernateTemplate());
//			Integer sortida = sortidaLotDao.getSumaSortidesLotFinsData(lot.getId(), data, new Boolean(true));
//			entradaLotDao.setHibernateTemplate(getHibernateTemplate());
//			Integer	devolucio = entradaLotDao.getSumaDevolucionsLotFinsData(lot.getId(), data, new Boolean(true));
//			Double existencia = (lot.getNumeroBotellesActuals() + sortida - devolucio) * lot.getEtiquetatge().getTipusEnvas().getVolum();
//			ents += existencia;
//			logger.info("Lot: " + lot.getCodiAssignat() + " - A: " + lot.getNumeroBotellesActuals() + " - S: " + sortida + " - D: " + devolucio + " = " + (lot.getNumeroBotellesActuals() + sortida - devolucio) + " x " + lot.getEtiquetatge().getTipusEnvas().getVolum() + " (" + existencia + "). Acumulat: " + ents);
//			if (existencia > 0.0001){ // Evitam problemes amb decimals
//				exist += existencia;
//			}
//		}
//		
//		logger.info("EXISTENCIES LOTS INDIVIDUAL (Categoria " + categoriaId + ") = " + (ents));
//		
//		logger.info("EXISTENCIES TOTAL (Categoria " + categoriaId + ") = " + exist);
		
		return exist;
	}
	
	/**
	 * Devuelve la cantidad total de litros de aceite vendido para un establecimiento y categoria
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Double getQuantitatOliVenutDiscriminantQualificacioAndEstabliment(/*Long temporadaId,*/ Long establimentId, Date dataInici,Object[] categorias){
		dipositDao.setHibernateTemplate(getHibernateTemplate());
//		return dipositDao.getQuantitatOliVenutDiscriminantQualificacioAndEstabliment(temporadaId, establimentId, dataInici, categorias);
		return dipositDao.getQuantitatOliVenutDiscriminantQualificacioAndEstabliment(establimentId, dataInici, null, categorias);
	}
	
	/**
	 * Devuelve la cantidad total de litros de aceite vendido para un establecimiento y categoria
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Double getQuantitatOliVenutDiscriminantQualificacioAndEstabliment(Long establimentId, Date dataInici, Date dataFi, Object[] categorias){
		dipositDao.setHibernateTemplate(getHibernateTemplate());
		return dipositDao.getQuantitatOliVenutDiscriminantQualificacioAndEstabliment(establimentId, dataInici, dataFi, categorias);
	}
	
	
	/**
	 * Devuelve la cantidad total de litros de aceite elaborado para un establecimiento y categoria
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Double getQuantitatOliElaboratDiscriminantQualificacioAndEstabliment(/*Long temporadaId,*/Long establimentId, Date dataInici,Object[] categorias){
		dipositDao.setHibernateTemplate(getHibernateTemplate());
//		return dipositDao.getQuantitatOliElaboratDiscriminantQualificacioAndEstabliment(temporadaId, establimentId, dataInici, categorias);
		return dipositDao.getQuantitatOliElaboratDiscriminantQualificacioAndEstabliment(establimentId, dataInici, null, categorias);
	}
	
	/**
	 * Devuelve la cantidad total de litros de aceite elaborado para un establecimiento y categoria
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Double getQuantitatOliElaboratDiscriminantQualificacioAndEstabliment(Long establimentId, Date dataInici, Date dataFi, Object[] categorias){
		dipositDao.setHibernateTemplate(getHibernateTemplate());
		return dipositDao.getQuantitatOliElaboratDiscriminantQualificacioAndEstabliment(establimentId, dataInici, dataFi, categorias);
	}
	
	
	/**
	 * Devuelve la cantidad total de litros de aceite elaborado para un establecimiento y categoria
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Double getTotalOlivaMolturada(/*Long temporadaId,*/ Date dataInici, Boolean ambDo, Long establimentId){
		descomposicioPartidaOlivaDao.setHibernateTemplate(getHibernateTemplate());
//		return descomposicioPartidaOlivaDao.getTotalOlivaMolturada(temporadaId,dataInici, ambDo, establimentId);
		return descomposicioPartidaOlivaDao.getTotalOlivaMolturada(dataInici, null, ambDo, establimentId);
	}
	
	/**
	 * Devuelve la cantidad total de litros de aceite elaborado para un establecimiento y categoria
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Double getTotalOlivaMolturada(Date dataInici, Date dataFi, Boolean ambDo, Long establimentId){
		descomposicioPartidaOlivaDao.setHibernateTemplate(getHibernateTemplate());
		return descomposicioPartidaOlivaDao.getTotalOlivaMolturada(dataInici, dataFi, ambDo, establimentId);
	}
	
	/**
	 * Busca las salidas de lotes entre 2 fechas para un establecimiento
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection getEntradasDipositEntreFechasDipositAndEstablecimiento(Date finicio, Date ffin, Long idEst, Long idDip, Boolean valid){
		entradaDipositDao.setHibernateTemplate(getHibernateTemplate());
		return entradaDipositDao.getEntradasDipositEntreFechasDipositAndEstablecimiento(finicio, ffin, idEst, idDip, valid);
	
	}
	
	/**
	 * Busca las entradas de depósitp entre 2 fechas para un establecimiento
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection getEntradasDipositEntreFechasAndDiposit(Date finicio, Date ffin, Long idDip, Boolean valid){
		entradaDipositDao.setHibernateTemplate(getHibernateTemplate());
		return entradaDipositDao.getEntradasDipositEntreFechasAndDiposit(finicio, ffin, idDip, valid);
	}
	
	/**
	 * Busca las salidas de depósitos entre 2 fechas para un establecimiento
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection getSortidesDipositEntreFechasDipositAndEstablecimiento(Date finicio, Date ffin, Long idEst, Long idDip, Boolean valid){
		sortidaDipositDao.setHibernateTemplate(getHibernateTemplate());
		return sortidaDipositDao.getSortidesDipositEntreFechasDipositAndEstablecimiento(finicio, ffin, idEst, idDip, valid);
	
	}
	
	/**
	 * Busca las salidas de lotes entre 2 fechas para un establecimiento
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection getSortidesDipositEntreFechasAndDiposit(Date finicio, Date ffin, Long idDip, Boolean valid){
		sortidaDipositDao.setHibernateTemplate(getHibernateTemplate());
		return sortidaDipositDao.getSortidesDipositEntreFechasAndDiposit(finicio, ffin, idDip, valid);
	
	}
	
	/**
	 * Busca las salidas de lotes entre 2 fechas para un establecimiento
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection findSortidesOrujoEntreDatesIEstabliment(Date dataInici, Date dataFi, Long establimentId, Boolean valid){
		sortidaOrujoDao.setHibernateTemplate(getHibernateTemplate());
		return sortidaOrujoDao.findAllAmbEstablimentEntreDates(dataInici, dataFi, establimentId, valid);
	
	}
	
	
	/**
	 * Devuelve la cantidad total de aceite que cambia de categoria
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Double getQuantitatOliCanviatDO(Long temporadaId, Long establimentId, Date dataInici, Date dataFi, Object[] categoria, Boolean aOliDesqualificat){
		analiticaDao.setHibernateTemplate(getHibernateTemplate());
		Collection analiticas = analiticaDao.getAnaliticasByDesqualificatEstablimentData(establimentId, dataInici, dataFi, aOliDesqualificat);
		
		List trazasPadre = new ArrayList();
		Double suma;
		
		// Si el aceite pasa de DO a NO DO hay que mirar si la entrada con "pendiente de calificar" tenía una analítica previa
		// que lo convertía en DO
//		if (!aOliDesqualificat.booleanValue()){
			
			Iterator ite = analiticas.iterator();
			while (ite.hasNext()){
				Analitica ana = (Analitica)ite.next();
				Iterator itTrazasPadre = ana.getTraza().getTrazasForTtrCodtrapare().iterator();
				while (itTrazasPadre.hasNext()) {
					Traza trazaPadre = (Traza) itTrazasPadre.next();
					trazasPadre.add(trazaPadre.getId());
				}
			}
			
			entradaDipositDao.setHibernateTemplate(getHibernateTemplate());
			if (aOliDesqualificat.booleanValue()) {
				suma = entradaDipositDao.getSumaDoToNoDoByCampanyaEstabliment(temporadaId, trazasPadre.toArray(), new Boolean(true));
			} else {
				suma = entradaDipositDao.getSumaNoDoToDoByCampanyaEstabliment(temporadaId, trazasPadre.toArray(), new Boolean(true));
			}
//		}else{
//			
//			Iterator ite = analiticas.iterator();
//			while (ite.hasNext()){
//				Analitica ana = (Analitica)ite.next();
//				trazasPadre.addAll(ana.getTraza().getTrazasForTtrCodtrapare());
//			}
//			
//			entradaDipositDao.setHibernateTemplate(getHibernateTemplate());
//			suma = entradaDipositDao.getSumaNoDoToDoByCampanyaEstabliment(temporadaId, trazasPadre.toArray(), categoria);
//			
//		}

		/*
		if (col.size()>0){
			totalOli = (Double)col.iterator().next();
		}
		if (totalOli==null){
			totalOli=new Double(0);
		}
		*/
		
		
		return suma;
	}
	
	/**
	 * Devuelve la cantidad total de aceite que cambia de DO a NO_DO
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Double getQuantitatOliCanviatCategoriaDO_NoDO(Long establimentId, Date dataInici, Date dataFi){
		entradaDipositDao.setHibernateTemplate(getHibernateTemplate());
		
		// Oli Desqualificat amb una analítica que dóna un resultat de Verge o Llampant
		analiticaDao.setHibernateTemplate(getHibernateTemplate());
		Collection analiticasDO = analiticaDao.getAnaliticasByDesqualificatEstablimentData(establimentId, dataInici, dataFi, new Boolean(false));
		Collection analiticasNoDO = analiticaDao.getAnaliticasByDesqualificatEstablimentData(establimentId, dataInici, dataFi, new Boolean(true));
		
		List trazasPadreDO = new ArrayList();
		List trazasPadreNoDO = new ArrayList();
		
		// 0: DO -> NoDO, 1: Pendent -> DO, 2: Pendent -> NoDO
		//Double[] suma = {0.0, 0.0, 0.0};
		Double suma = 0.0;
		
//		Iterator ite = analiticasDO.iterator();
//		while (ite.hasNext()){
//			Analitica ana = (Analitica)ite.next();
//			// La traça pare de l'analítica és la 
//			Iterator itTrazasPadre = ana.getTraza().getTrazasForTtrCodtrapare().iterator();
//			while (itTrazasPadre.hasNext()) {
//				Traza trazaPadre = (Traza) itTrazasPadre.next();
//				if (trazaPadre.getTipus().equals(Constants.CODI_TRAZA_TIPUS_ENTRADA_DIPOSIT)){
//					EntradaDiposit edi = entradaDipositDao.findByTraza(trazaPadre.getId(), true);
//					// Pendent --> DO
//					if (edi != null && edi.getCategoriaOli().getId().equals(Constants.CATEGORIA_PENDENT)) {
//						suma[1] += edi.getLitres();
//						break;
//					}
//				}
//			}
//		}
		
		Iterator ite = analiticasNoDO.iterator();
		while (ite.hasNext()){
			Analitica ana = (Analitica)ite.next();
			// La traça pare de l'analítica és la 
			Iterator itTrazasPadre = ana.getTraza().getTrazasForTtrCodtrapare().iterator();
			while (itTrazasPadre.hasNext()) {
				Traza trazaPadre = (Traza) itTrazasPadre.next();
				if (trazaPadre.getTipus().equals(Constants.CODI_TRAZA_TIPUS_ENTRADA_DIPOSIT)){
					EntradaDiposit edi = entradaDipositDao.findByTraza(trazaPadre.getId(), true);
					// Pendent --> NoDO
//					if (edi != null && edi.getCategoriaOli().getId().equals(Constants.CATEGORIA_PENDENT)) {
//						suma[1] += edi.getLitres();
//						break;
					// DO -> NoDO
//					} else 
					if (edi != null && edi.getCategoriaOli().getId().equals(Constants.CATEGORIA_DO)) {
						suma += edi.getLitres();
					}
				}
			}
		}
		
		// No tindrem en compte les mescles que es produeixen durant el procés.
		// Només tindrem en compte les sortides desqualificades
		suma += entradaDipositDao.getSumaDoToNoDoByEstabliment(dataInici, dataFi, establimentId);
//		suma = entradaDipositDao.getSumaDoToNoDoByCampanyaEstabliment(temporadaId, trazasPadre.toArray(), new Boolean(true));
		
		return suma;
	}
	
	
	
	
	/**
	 * Devuelve la superficie de oliveras de una categoria inscritas en la DO
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection getSuperficiesVarietatsDO(Long idTemporada){
		descomposicioPlantacioDao.setHibernateTemplate(getHibernateTemplate());
		return descomposicioPlantacioDao.getSuperficiesVarietatsDO(idTemporada);
	}
	
		
	
	/**
	 * Devuelve la superficie de oliveras de una categoria inscritas en la DO
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection getNumeroArbresVarietatsDO(Long idTemporada) {
		descomposicioPlantacioDao.setHibernateTemplate(getHibernateTemplate());
		return descomposicioPlantacioDao.getNumeroArbresVarietatsDO(idTemporada);
	}
	
	/**
	 * Devuelve las superficies de cada variedad de oliva, por cada tipo, y su municipio 
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection getSuperficiesVarietats(Long idTemporada) {
		descomposicioPlantacioDao.setHibernateTemplate(getHibernateTemplate());
		return descomposicioPlantacioDao.getSuperficiesVarietats(idTemporada);
	}
	
	/**
	 * Devuelve la superficie de oliveras de una categoria inscritas en la DO
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Double getSuperfFincasDO(Long idTemporada, Double valorInicial, Double valorFinal){
		fincasDao.setHibernateTemplate(getHibernateTemplate());
		return fincasDao.getSuperfFincasDO(idTemporada, valorInicial, valorFinal);
	}
	
	
	
	/**
	 * Devuelve la produccion por meses para un establecimiento
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection getTotalOliElaboratTafonaPerMes(Long temporadaId, Long establimentId){
		elaboracioDao.setHibernateTemplate(getHibernateTemplate());
		return elaboracioDao.getTotalOliElaboratTafonaPerMes(temporadaId, establimentId, new Boolean(true));
	}

	
	/**
	 * Devuelve un listado de los productores de aceite
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection usuariosConTafonasByCampanya(Long idTemporada){
		establimentDao.setHibernateTemplate(getHibernateTemplate());
		return establimentDao.usuariosConTafonasByCampanya(idTemporada);
	}
	
	/**
	 * Devuelve un listado de los establecimientos  de aceite
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection establecimientosConTafonasByCampanya(Long idTemporada){
		establimentDao.setHibernateTemplate(getHibernateTemplate());
		return establimentDao.establecimientosConTafonasByCampanya(idTemporada);
	}
	
	/**
	 * Devuelve un listado de elaboraciones por productor 
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection getElaboracionesByUsuario(Long idTemporada,Long usuId){
		elaboracioDao.setHibernateTemplate(getHibernateTemplate());
		return elaboracioDao.getElaboracionesByUsuario(idTemporada, usuId, new Boolean(true));
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
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection getElaboracionesByEstablecimiento(Long idTemporada,Long establimentId){
		elaboracioDao.setHibernateTemplate(getHibernateTemplate());
		return elaboracioDao.getElaboracionesByEstablecimiento(idTemporada, establimentId, new Boolean(true));
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
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection getElaboracionesByEstablecimiento(Long establimentId){
		elaboracioDao.setHibernateTemplate(getHibernateTemplate());
		return elaboracioDao.getElaboracionesByEstablecimiento(establimentId, new Boolean(true));
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
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection findAnaliticasByEstabliment(Long establimentId){
		analiticaDao.setHibernateTemplate(getHibernateTemplate());
		Collection lista = analiticaDao.findByEstabliment(establimentId, new Boolean(true));
		
		Iterator itAn = lista.iterator();
		while (itAn.hasNext()){
			Analitica a = (Analitica) itAn.next();
			if (a.getLitresAnalitzats() == null){
				Iterator it = a.getTraza().getTrazasForTtrCodtrafill().iterator();
				while (it.hasNext()) {
					Traza tra = (Traza) it.next();
					if (tra.getTipus().equals(new Integer(4))) {
						sortidaDipositDao.setHibernateTemplate(getHibernateTemplate());
						SortidaDiposit sdi = sortidaDipositDao.findByTraza(tra.getId(), new Boolean(true));
						a.setLitresAnalitzats(sdi.getLitres());
						System.out.println("ANALITICA: id = " + a.getId() + " --> \t" + sdi.getLitres() + " litres.");
						break;
					}
				}
			}
		}
		
		return lista;
	}
	
	
	/**
	 * <!-- begin-xdoclet-definition --> 
	 * @throws RemoteException 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" 
	 * <!-- end-xdoclet-definition --> 
	 * @generated
	 */
	public Collection findEntradesDipositTrasllatByEstablimentAndDates(Date dataInici, Date dataFi, Long establimentId){
		Collection entradesDiposit = new ArrayList();

		trasllatDao.setHibernateTemplate(getHibernateTemplate());
		Collection trasllats = trasllatDao.findTrasllatsByEstablimentOrigenIDestiEntreDates(dataInici, dataFi, establimentId);
		Collection trasllatsFiltrats = new ArrayList();
		
		for(Iterator it=trasllats.iterator(); it.hasNext();){
			Trasllat trasllat = (Trasllat)it.next();
			
			if(trasllat.getEstablimentByTdiCodeor().getId().equals(establimentId) && trasllat.getQuantitatRetorn() != null && trasllat.getQuantitatRetorn() > 0){
				trasllatsFiltrats.add(trasllat);
			}
			
			if(trasllat.getEstablimentByTdiCodede().getId().equals(establimentId) && trasllat.getQuantitatEnviament() != null && trasllat.getQuantitatEnviament() > 0){
				trasllatsFiltrats.add(trasllat);
			}
		}
		
		for(Iterator it3=trasllatsFiltrats.iterator(); it3.hasNext();){
			Trasllat trasllat = (Trasllat)it3.next();
			//TODO Cercar la traça fill de la traça que ens arriba per cercar l'entrada de dipòsit 
			for(Iterator it2 = trasllat.getTraza().getTrazasForTtrCodtrafill().iterator(); it2.hasNext();){
				Traza t2 = (Traza)it2.next();
				if (t2.getTipus().equals(Constants.CODI_TRAZA_TIPUS_ENTRADA_DIPOSIT)){
					entradaDipositDao.setHibernateTemplate(getHibernateTemplate());
					EntradaDiposit ed = findEntradaDipositByTraza(t2.getId());
					entradesDiposit.add(ed);
				}
			}	
		}
		
		return entradesDiposit;
	}
	
	/**
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" 
	 * <!-- end-xdoclet-definition --> 
	 * @generated
	 */
	public Collection findEntradesDipositDeEnvasadoraByEstablimentAndDates(Date dataInici, Date dataFi, Long establimentId){
		sortidaDipositDao.setHibernateTemplate(getHibernateTemplate());
		return sortidaDipositDao.findEntradesDipositDeEnvasadoraByEstablimentAndDates(dataInici, dataFi, establimentId);
	}

	/**
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" 
	 * <!-- end-xdoclet-definition --> 
	 * @generated
	 */
	public EntradaDiposit findEntradaDipositByTraza(Long idTraza){
		entradaDipositDao.setHibernateTemplate(getHibernateTemplate());
		return entradaDipositDao.findByTraza(idTraza, true);
	}
	
	/**
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" 
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition --> 
	 * @generated
	 */
	public Collection trazabilitatMarca(Long idMarca){
		Iterator it; //  lots
		Iterator it2;//  partides olives taula
		Iterator it3;//  descomposicions partides olives taula
		Iterator it4;//  elaboracions partida oli
		Iterator it5;//  entrada partides olives
		Iterator it6;//  descomposicions partides oli
		
		PartidaOliva po;
		lotDao.setHibernateTemplate(getHibernateTemplate());
		Collection lots = lotDao.findByMarca(idMarca, new Boolean(true));
		Collection lotsAux = new ArrayList(); 
		lotsAux.addAll(lots);
		for (it = lotsAux.iterator(); it.hasNext();){
			Lot lot = new Lot(); 
			lot = (Lot)it.next();
			if(null != lot.getOlivaTaula()){
				ElaboracioOliva elaboracio = lot.getBota().getElaboracio();
				Collection partidesOlives = elaboracio.getPartidaElaboracions();
				for (it2 = partidesOlives.iterator(); it2.hasNext();){
					PartidaElaboracio pe;
					pe = (PartidaElaboracio)it2.next();					
					Collection descomposicions = pe.getPartida().getDescomposicioPartidesOlives();
					for (it3 = descomposicions.iterator(); it3.hasNext();){
						DescomposicioPartidaOliva dpo = (DescomposicioPartidaOliva)it3.next();
						if(!lots.contains(dpo)){
							lots.add(dpo);
						}
					}
				}
			}else{
				Collection elaboracions = lot.getPartidaOli().getElaboracions();
				for (it4 = elaboracions.iterator(); it4.hasNext();){
					Elaboracio ela = (Elaboracio)it4.next();
					Collection partidesOlives = ela.getPartidaOlivas();
					for (it5 = partidesOlives.iterator(); it5.hasNext();){
						po = (PartidaOliva)it5.next();					
						Collection descomposicions = po.getDescomposicioPartidesOlives();
						for (it6 = descomposicions.iterator(); it6.hasNext();){
							DescomposicioPartidaOliva dpo = (DescomposicioPartidaOliva)it6.next();
							if(!lots.contains(dpo)){
								lots.add(dpo);
							}
						}
					}
				}
			}
		}
		entradaLotDao.setHibernateTemplate(getHibernateTemplate());
		Collection entrades = entradaLotDao.findByMarca(idMarca, new Boolean(true));
		lots.addAll(entrades);
		sortidaLotDao.setHibernateTemplate(getHibernateTemplate());
		Collection sortides = sortidaLotDao.findByMarca(idMarca, new Boolean(true));
		lots.addAll(sortides);
		return lots;
	}
	
	/**
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" 
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition --> 
	 * @generated
	 */
	public Collection trazabilitatLotBotelles(String lBot){
		Iterator it; //  lots
		Iterator it2;//  partides olives taula
		Iterator it3;//  descomposicions partides olives taula
		Iterator it4;//  elaboracions partida oli
		Iterator it5;//  entrada partides olives
		Iterator it6;//  descomposicions partides oli
		
		PartidaOliva po;
		lotDao.setHibernateTemplate(getHibernateTemplate());
		Collection lots = lotDao.findByLotBotelles(lBot, new Boolean(true));
		Collection lotsAux = new ArrayList(); 
		lotsAux.addAll(lots);
		for (it = lotsAux.iterator(); it.hasNext();){
			Lot lot = new Lot(); 
			lot = (Lot)it.next();
			if(null != lot.getOlivaTaula()){
				ElaboracioOliva elaboracio = lot.getBota().getElaboracio();
				Collection partidesOlives = elaboracio.getPartidaElaboracions();
				for (it2 = partidesOlives.iterator(); it2.hasNext();){
					PartidaElaboracio pe;
					pe = (PartidaElaboracio)it2.next();					
					Collection descomposicions = pe.getPartida().getDescomposicioPartidesOlives();
					for (it3 = descomposicions.iterator(); it3.hasNext();){
						DescomposicioPartidaOliva dpo = (DescomposicioPartidaOliva)it3.next();
						if(!lots.contains(dpo)){
							lots.add(dpo);
						}
					}
				}
			}else{
				Collection elaboracions = lot.getPartidaOli().getElaboracions();
				for (it4 = elaboracions.iterator(); it4.hasNext();){
					Elaboracio ela = (Elaboracio)it4.next();
					Collection partidesOlives = ela.getPartidaOlivas();
					for (it5 = partidesOlives.iterator(); it5.hasNext();){
						po = (PartidaOliva)it5.next();					
						Collection descomposicions = po.getDescomposicioPartidesOlives();
						for (it6 = descomposicions.iterator(); it6.hasNext();){
							DescomposicioPartidaOliva dpo = (DescomposicioPartidaOliva)it6.next();
							if(!lots.contains(dpo)){
								lots.add(dpo);
							}
						}
					}
				}
			}
		}
		
		entradaLotDao.setHibernateTemplate(getHibernateTemplate());
		Collection entrades = entradaLotDao.findByLotbotellaBuida(lBot, new Boolean(true));
		lots.addAll(entrades);
		sortidaLotDao.setHibernateTemplate(getHibernateTemplate());
		Collection sortides = sortidaLotDao.findByLotbotellaBuida(lBot, new Boolean(true));
		lots.addAll(sortides);
		return lots;
	}
	
	/**
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" 
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition --> 
	 * @generated
	 */
	public Collection trazabilitatLot(Long idLot){
		Iterator it; //  lots
		Iterator it2;//  partides olives taula
		Iterator it3;//  descomposicions partides olives taula
		Iterator it4;//  elaboracions partida oli
		Iterator it5;//  entrada partides olives
		Iterator it6;//  descomposicions partides oli
		PartidaOliva po;
		
		lotDao.setHibernateTemplate(getHibernateTemplate());
		Collection lots = new ArrayList();
		Lot lot = lotDao.getById(idLot);
		lots.add(lot);
		Collection lotsAux = new ArrayList(); 
		lotsAux.addAll(lots);
		for (it = lotsAux.iterator(); it.hasNext();){
			Lot lt = new Lot(); 
			lt = (Lot)it.next();
			if(null != lt.getOlivaTaula()){
				ElaboracioOliva elaboracio = lt.getBota().getElaboracio();
				Collection partidesOlives = elaboracio.getPartidaElaboracions();
				for (it2 = partidesOlives.iterator(); it2.hasNext();){
					PartidaElaboracio pe;
					pe = (PartidaElaboracio)it2.next();					
					Collection descomposicions = pe.getPartida().getDescomposicioPartidesOlives();
					for (it3 = descomposicions.iterator(); it3.hasNext();){
						DescomposicioPartidaOliva dpo = (DescomposicioPartidaOliva)it3.next();
						if(!lots.contains(dpo)){
							lots.add(dpo);
						}
					}
				}
			}else{
				Collection elaboracions = lot.getPartidaOli().getElaboracions();
				for (it4 = elaboracions.iterator(); it4.hasNext();){
					Elaboracio ela = (Elaboracio)it4.next();
					Collection partidesOlives = ela.getPartidaOlivas();
					for (it5 = partidesOlives.iterator(); it5.hasNext();){
						po = (PartidaOliva)it5.next();					
						Collection descomposicions = po.getDescomposicioPartidesOlives();
						for (it6 = descomposicions.iterator(); it6.hasNext();){
							DescomposicioPartidaOliva dpo = (DescomposicioPartidaOliva)it6.next();
							if(!lots.contains(dpo)){
								lots.add(dpo);
							}
						}
					}
				}
			}
		}
		
		entradaLotDao.setHibernateTemplate(getHibernateTemplate());
		Collection entrades = entradaLotDao.findByLot(idLot, new Boolean(true));
		lots.addAll(entrades);
		sortidaLotDao.setHibernateTemplate(getHibernateTemplate());
		Collection sortides = sortidaLotDao.findByLot(idLot, new Boolean(true));
		lots.addAll(sortides);
		return lots;
	}
	
	/**
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" 
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition --> 
	 * @generated
	 */
	public Collection trazabilitatEtiquetesLot(EtiquetesLot et){
		Iterator it7; //  lots
		Iterator it2;//  partides olives taula
		Iterator it3;//  descomposicions partides olives taula
		Iterator it4;//  elaboracions partida oli
		Iterator it5;//  entrada partides olives
		Iterator it6;//  descomposicions partides oli
		PartidaOliva po;
		
		etiquetesLotDao.setHibernateTemplate(getHibernateTemplate());
		Collection lots = etiquetesLotDao.getLotsDescendents(et);
		Long[] lotsId = new Long[lots.size()];
		int i = 0;
		for (Iterator it = lots.iterator(); it.hasNext(); i++){
			lotsId[i] = ((Lot)it.next()).getId();
		}
		
		Collection lotsAux = new ArrayList(); 
		lotsAux.addAll(lots);
		for (it7 = lotsAux.iterator(); it7.hasNext();){
			Lot lt = new Lot(); 
			lt = (Lot)it7.next();
			if(null!= lt.getOlivaTaula()){
				ElaboracioOliva elaboracio = lt.getBota().getElaboracio();
				Collection partidesOlives = elaboracio.getPartidaElaboracions();
				for (it2 = partidesOlives.iterator(); it2.hasNext();){
					PartidaElaboracio pe;
					pe = (PartidaElaboracio)it2.next();					
					Collection descomposicions = pe.getPartida().getDescomposicioPartidesOlives();
					for (it3 = descomposicions.iterator(); it3.hasNext();){
						DescomposicioPartidaOliva dpo = (DescomposicioPartidaOliva)it3.next();
						if(!lots.contains(dpo)){
							lots.add(dpo);
						}
					}
				}
			}else{
				Collection elaboracions = lt.getPartidaOli().getElaboracions();
				for (it4 = elaboracions.iterator(); it4.hasNext();){
					Elaboracio ela = (Elaboracio)it4.next();
					Collection partidesOlives = ela.getPartidaOlivas();
					for (it5 = partidesOlives.iterator(); it5.hasNext();){
						po = (PartidaOliva)it5.next();					
						Collection descomposicions = po.getDescomposicioPartidesOlives();
						for (it6 = descomposicions.iterator(); it6.hasNext();){
							DescomposicioPartidaOliva dpo = (DescomposicioPartidaOliva)it6.next();
							if(!lots.contains(dpo)){
								lots.add(dpo);
							}
						}
					}
				}
			}
		}
		
		entradaLotDao.setHibernateTemplate(getHibernateTemplate());
		Collection entrades = entradaLotDao.findByLots(lotsId, new Boolean(true));
		lots.addAll(entrades);
		sortidaLotDao.setHibernateTemplate(getHibernateTemplate());
		Collection sortides = sortidaLotDao.findByLots(lotsId, new Boolean(true));
		lots.addAll(sortides);
		return lots;
	}
	
	/**
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" 
	 * <!-- end-xdoclet-definition --> 
	 * @generated
	 */
	public Collection trazabilitatLotTalc(String lTalc, Long idEstabliment){
		elaboracioDao.setHibernateTemplate(getHibernateTemplate());
		Collection elaboracions = elaboracioDao.getElaboracionesByLotTalc(lTalc, idEstabliment, new Boolean(true));
		Collection llistat = new ArrayList();
		Collection col = new ArrayList();
		for (Iterator it = elaboracions.iterator(); it.hasNext();){
			Elaboracio elaboracio = (Elaboracio)it.next();
			col.addAll(trazabilitatInvertida(elaboracio.getTraza()));
		}
		for (Iterator it = col.iterator(); it.hasNext();){
			Object obj = it.next();
			if (!llistat.contains(obj)) llistat.add(obj);
		}
		return llistat;
	}
	
	/**
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" 
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition --> 
	 * @generated
	 */
	public Collection trazabilitatPartidaOliva(Long idPartida){
		elaboracioDao.setHibernateTemplate(getHibernateTemplate());
		Elaboracio elaboracio = elaboracioDao.getElaboracioByPartidaOliva(idPartida, new Boolean(true));
		
//		Collection llistat = trazabilitatInvertida(elaboracio.getTraza());
		Collection col = trazabilitatInvertida(elaboracio.getTraza());
		Collection llistat = new ArrayList();
		for (Iterator it = col.iterator(); it.hasNext();){
			Object obj = it.next();
			if (!llistat.contains(obj)){ 
				llistat.add(obj);
			}
		}
		return llistat;
	}
	
	/**
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" 
	 * <!-- end-xdoclet-definition --> 
	 * @generated
	 */
	public Collection trazabilitatDiposit(Long idDiposit, Date data){
		Iterator it2;
		dipositDao.setHibernateTemplate(getHibernateTemplate());
		Diposit diposit = dipositDao.getById(idDiposit);
		Collection llistat = new ArrayList();
		Double volum = findExistenciesDipositByData(idDiposit, data);
		if (volum <= 0) {
			// Dipòsit buit... 
		} else {
			entradaDipositDao.setHibernateTemplate(getHibernateTemplate());
			EntradaDiposit ultima = entradaDipositDao.findAnteriorByData(idDiposit, data);
//			llistat = trazabilitatInvertida(ultima.getTraza());
			Collection col = trazabilitatInvertida(ultima.getTraza());
			for (Iterator it = col.iterator(); it.hasNext();){
				Object obj = it.next();
				if (!llistat.contains(obj)) llistat.add(obj);
			}
			
			Collection elaboracions = ultima.getPartidaOli().getElaboracions();
			for (it2 = elaboracions.iterator(); it2.hasNext();){
				Elaboracio elaboracio = (Elaboracio)it2.next();
				llistat.addAll(trazabilitatInvertida(elaboracio.getTraza()));
			}
			
		}
		return llistat;		
	}
	
	/**
	 * Construeix l'arbre per trazar el procés del vi.
	 * 
	 * @param contenidorId el contenidor per trazar l'arbre
	 * @return una colecció amb la informacó per construir
	 * l'arbre.
	 * 
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 * <!-- end-xdoclet-definition --> 
	 * @generated
	 */
	public Collection trazabilitatInvertida(Traza trazaPare) {
		Collection result = new ArrayList();
		trazabilitatInvertida(trazaPare, result);
		return result;
	}
	
	/**
	 * Construeix l'arbre per trazar el procés del vi.
	 * 
	 * @param contenidorId el contenidor per trazar l'arbre
	 * @return una colecció amb la informacó per construir
	 * l'arbre.
	 * 
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 * <!-- end-xdoclet-definition --> 
	 * @generated
	 */
	public void trazabilitatInvertida(Traza trazaPare, Collection result) {
		Iterator it;
		Iterator it2;
		Iterator it3;
		Iterator it4;
		EntradaDiposit edi;
		Diposit dip;
		SortidaDiposit sdi;
		EntradaLot el;
		Lot lot;
		SortidaLot sl;
		PartidaOliva po;
		
		for (it = trazaPare.getTrazasForTtrCodtrafill().iterator(); it.hasNext();){
			Traza traza = (Traza)it.next();
			
			//informació comú a tots els tipus de traça
			Collection elaboracions = trazaPare.getElaboracions();
			for (it2 = elaboracions.iterator(); it2.hasNext();){
				Elaboracio ela = (Elaboracio)it2.next();
				Collection partidesOlives = ela.getPartidaOlivas();
				for (it3 = partidesOlives.iterator(); it3.hasNext();){
					po = (PartidaOliva)it3.next();					
					Collection descomposicions = po.getDescomposicioPartidesOlives();
					for (it4 = descomposicions.iterator(); it4.hasNext();){
						DescomposicioPartidaOliva dpo = (DescomposicioPartidaOliva)it4.next();
						if(!result.contains(dpo)){
							result.add(dpo);
						}
					}
					if(!result.contains(po)){
						result.add(po);
					}
				}
			}
			
			switch (traza.getTipus()){
			case Constants.CODI_TRAZA_TIPUS_PARTIDA:
			case Constants.CODI_TRAZA_TIPUS_ELABORACIO:
			case Constants.CODI_TRAZA_TIPUS_ANALITICA:
			case Constants.CODI_TRAZA_TIPUS_TRASLLAT_DIPOSIT:
			case Constants.CODI_TRAZA_TIPUS_TRASLLAT_OLI:
			case Constants.CODI_TRAZA_TIPUS_VENDA_OLI:
				break;
			case Constants.CODI_TRAZA_TIPUS_ENTRADA_DIPOSIT:
				entradaDipositDao.setHibernateTemplate(getHibernateTemplate());
				edi = entradaDipositDao.findByTraza(traza.getId(), new Boolean(true));
				if (edi != null && !edi.getDiposit().getFictici()){
					// He d'afegir el dipòsit només en el cas que encara tengui oli de la traça!!!!!!!!!!!!!!!!!!
					// Puc obtenir les següents entrades de dipòsit, i comprovar que no te cap pare que també sigui una entrada d'oli
					dip = edi.getDiposit();
					dipositDao.setHibernateTemplate(getHibernateTemplate());
					if (!dipositDao.haEstatBuidatPosteriormentATraza(dip.getId(), traza.getId())) {
						if (!result.contains(dip)) result.add(dip);
					}
				}
				break;
				
			case Constants.CODI_TRAZA_TIPUS_SORTIDA_DIPOSIT:
				sortidaDipositDao.setHibernateTemplate(getHibernateTemplate());
				sdi = sortidaDipositDao.findByTraza(traza.getId(), new Boolean(true));
				if (sdi != null && sdi.getDipositBySdiCoddde() == null){
					if (!result.contains(sdi)) result.add(sdi);
				}
				break;
			case Constants.CODI_TRAZA_TIPUS_ENTRADA_LOT:
				entradaLotDao.setHibernateTemplate(getHibernateTemplate());
				el = entradaLotDao.findByTraza(traza.getId(), new Boolean(true));
				if (el != null){
					lot = el.getLot();
					if (!result.contains(lot)) result.add(lot);
					if (!result.contains(el)) result.add(el);
				}
				break;
			case Constants.CODI_TRAZA_TIPUS_SORTIDA_LOT:
				sortidaLotDao.setHibernateTemplate(getHibernateTemplate());
				sl = sortidaLotDao.findByTraza(traza.getId(), new Boolean(true));
				if (sl != null && !result.contains(sl)) result.add(sl);
				break;
			}
//			result.add(trazabilitatInvertida(traza));
			trazabilitatInvertida(traza, result);
		}
		
//		return result;
	}
	
	/**
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" 
	 * <!-- end-xdoclet-definition --> 
	 * @generated
	 */
	public String getContraetiquetesBySortidaDiposit(SortidaDiposit sd){
		String etiquetes = "";
		Iterator i = sd.getTraza().getTrazasForTtrCodtrafill().iterator();
		while (i.hasNext()){
			Traza tra = (Traza)i.next();
			if (tra.getTipus().equals(Constants.CODI_TRAZA_TIPUS_ENTRADA_LOT)) {
				entradaLotDao.setHibernateTemplate(getHibernateTemplate());
				EntradaLot elo = entradaLotDao.findByTraza(tra.getId(), new Boolean(true));
				etiquetes = elo.getNumerosContraetiquetes();
				break;
			}
		}
		return etiquetes;
	}
	
	/**
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" 
	 * <!-- end-xdoclet-definition --> 
	 * @generated
	 */
	public Collection getSuperficiesVarietatsByCampanya(Long[] olivicultors, Long campanyaId, Boolean taula, Boolean experimental){
		descomposicioPlantacioDao.setHibernateTemplate(getHibernateTemplate());
		return descomposicioPlantacioDao.getSuperficiesVarietatsByCampanya(olivicultors, campanyaId, taula, experimental);
	} 
	
//	/**
//	 * <!-- begin-xdoclet-definition --> 
//	 * @ejb.interface-method view-type="both"
//	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
//	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
//	 * @ejb.permission role-name="OLI_DOGESTOR"
//	 * @ejb.permission role-name="OLI_PRODUCTOR"
//	 * @ejb.permission role-name="OLI_ENVASADOR" 
//	 * <!-- end-xdoclet-definition --> 
//	 * @generated
//	 */
//	public Object[][][] getSuperficiesVarietatsByMida(Long[] olivicultors){
//		descomposicioPlantacioDao.setHibernateTemplate(getHibernateTemplate());
//		return descomposicioPlantacioDao.getSuperficiesVarietatsByMida(olivicultors);
//	}
	
	
	/**
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" 
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition --> 
	 * @generated
	 */
	public Collection findLotsByEstablimentEntreDates(Long idEstabliment, Date dataInici, Date dataFi, Boolean valid){
		lotDao.setHibernateTemplate(getHibernateTemplate());
		return lotDao.findAllByEstablimentEntreDates(idEstabliment, dataInici, dataFi, valid);
	}
	
	/**
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" 
	 * <!-- end-xdoclet-definition --> 
	 * @generated
	 */
	public Collection getDevolucionsEntreDatesAndEstabliment(Date di, Date df, Long idEst){
		entradaLotDao.setHibernateTemplate(getHibernateTemplate());
		return entradaLotDao.getDevolucionsLotsEstablimentEntreDates(idEst, di, df, new Boolean(true));
	}
	
	/**
	 * Busca todas las entradas de hinojo
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection entradesFonoll(Date dataInici, Date dataFi, Long idEstabliment) {
		partidaFonollDao.setHibernateTemplate(getHibernateTemplate());
		return partidaFonollDao.getPartidasEntreDiasEnEstablecimiento(dataInici, dataFi, idEstabliment, new Boolean(true));
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
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition --> 
	 */
	public HibernateTemplate getHibernateTemplate(){
		return this.hibernateTemplate;
	}
	
	
	
	/**
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" 
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition --> 
	 * @generated
	 */
	public Collection findSortidesOlivaCruaGranelByEstablimentEntreDates(Long idEstabliment, Date dataInici, Date dataFi, Boolean valid){
		sortidaPartidaDao.setHibernateTemplate(getHibernateTemplate());
		return sortidaPartidaDao.getSortidaVendaOlivaTaulaCruaGranelEntreDiasEnEstablecimiento(dataInici, dataFi, idEstabliment, valid);
	}
	

	/**
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" 
	 * @ejb.permission role-name="OLI_OLIVATAULA"
	 * <!-- end-xdoclet-definition --> 
	 * @generated
	 */
	public Collection findSortidesOlivaBotaGranelByEstablimentEntreDates(Long idEstabliment, Date dataInici, Date dataFi, Boolean valid){
		sortidaBotaGranelDao.setHibernateTemplate(getHibernateTemplate());
		return sortidaBotaGranelDao.getSortidaVendaOlivaTaulaBotaGranelEntreDiasEnEstablecimiento(dataInici, dataFi, idEstabliment, valid);
	}
	
	
	/**
	 * Busca todas las salidas de lotes que se han vendido de este establecimiento
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection getSortidaLotVendaFiltre(Date dataInici, Date dataFi, Long idEstabliment, String document, String motiuVenda) {
		sortidaLotDao.setHibernateTemplate(getHibernateTemplate());
		return sortidaLotDao.getSortidaLotVendaFiltre(dataInici, dataFi, idEstabliment, document, motiuVenda);	
	}
	
	/**
	 * Busca todas las salidas de lotes que se han vendido de este establecimiento
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection getSortidaLotById(Long sortidaLotId) {
		sortidaLotDao.setHibernateTemplate(getHibernateTemplate());
		return sortidaLotDao.getSortidaLotById(sortidaLotId);	
	}
	
	
	
	
	
	
	/**
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection getConsultaBasicaGeneral(ConsultaBasicaGeneral command, Long establimentId){
		consultaBasicaGeneralDao.setHibernateTemplate(getHibernateTemplate());
		return consultaBasicaGeneralDao.getConsultaGeneral(command, establimentId);
	}
	
}