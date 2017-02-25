package es.caib.gestoli.logic.ejb;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.ejb.SessionBean;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.dao.AnaliticaDao;
import es.caib.gestoli.logic.dao.ArxiuDao;
import es.caib.gestoli.logic.dao.CampanyaDao;
import es.caib.gestoli.logic.dao.DescomposicioPartidaOlivaDao;
import es.caib.gestoli.logic.dao.DipositDao;
import es.caib.gestoli.logic.dao.ElaboracioDao;
import es.caib.gestoli.logic.dao.EntradaDipositDao;
import es.caib.gestoli.logic.dao.EntradaLotDao;
import es.caib.gestoli.logic.dao.EstablimentDao;
import es.caib.gestoli.logic.dao.EtiquetatgeDao;
import es.caib.gestoli.logic.dao.EtiquetesLotDao;
import es.caib.gestoli.logic.dao.GestioInfografiaDao;
import es.caib.gestoli.logic.dao.InformacioUtilDao;
import es.caib.gestoli.logic.dao.InformeCampanyaDao;
import es.caib.gestoli.logic.dao.LotDao;
import es.caib.gestoli.logic.dao.PartidaOliDao;
import es.caib.gestoli.logic.dao.PartidaOlivaDao;
import es.caib.gestoli.logic.dao.PlantacioDao;
import es.caib.gestoli.logic.dao.SortidaDipositDao;
import es.caib.gestoli.logic.dao.SortidaLotDao;
import es.caib.gestoli.logic.model.Analitica;
import es.caib.gestoli.logic.model.Arxiu;
import es.caib.gestoli.logic.model.Campanya;
import es.caib.gestoli.logic.model.DescomposicioPartidaOliva;
import es.caib.gestoli.logic.model.Diposit;
import es.caib.gestoli.logic.model.Elaboracio;
import es.caib.gestoli.logic.model.EntradaDiposit;
import es.caib.gestoli.logic.model.EntradaLot;
import es.caib.gestoli.logic.model.Establiment;
import es.caib.gestoli.logic.model.EtiquetesLot;
import es.caib.gestoli.logic.model.GestioInfografia;
import es.caib.gestoli.logic.model.InformacioUtil;
import es.caib.gestoli.logic.model.Lot;
import es.caib.gestoli.logic.model.PartidaOliva;
import es.caib.gestoli.logic.model.Plantacio;
import es.caib.gestoli.logic.model.SortidaLot;
import es.caib.gestoli.logic.model.Traza;
import es.caib.gestoli.logic.util.Constants;
import es.caib.gestoli.logic.util.TrazabilitatResumida;

/**
 * 
 * <!-- begin-user-doc --> A generated session bean <!-- end-user-doc --> * <!--
 * begin-xdoclet-definition -->
 * 
 * @ejb.bean name="OliFrontOfficeEjb"
 *           description="An EJB named OliFrontOfficeEjb"
 *           display-name="OliFrontOfficeEjb"
 *           jndi-name="OliFrontOfficeEjb"
 *           local-jndi-name="OliFrontOfficeEjbLocal"
 *           type="Stateless"
 *           transaction-type="Container"
 * @ejb.transaction type="Required"
 * 
 *           <!-- end-xdoclet-definition -->
 */
public abstract class OliFrontOfficeEjbBean implements SessionBean {
	private static Logger logger = Logger.getLogger(OliFrontOfficeEjbBean.class);

	private EtiquetesLotDao etiquetesLotDao;
	private ElaboracioDao elaboracioDao;
	private AnaliticaDao analiticaDao;
	private PartidaOlivaDao partidaOlivaDao;
	private LotDao lotDao;
	private EntradaLotDao entradaLotDao;
	private DipositDao dipositDao;
	private EstablimentDao establimentDao;
	private EntradaDipositDao entradaDipositDao;
	private InformacioUtilDao informacioUtilDao;
	private ArxiuDao arxiuDao;
	private GestioInfografiaDao gestioInfografiaDao;
	private CampanyaDao campanyaDao;
	private DescomposicioPartidaOlivaDao descomposicioPartidaOlivaDao;
	private SortidaLotDao sortidaLotDao;
	private SortidaDipositDao sortidaDipositDao;
	private InformeCampanyaDao informeCampanyaDao;
	private EtiquetatgeDao etiquetatgeDao;
	private PartidaOliDao partidaOliDao;
	private PlantacioDao plantacioDao;
	
	private HibernateTemplate hibernateTemplate;
	
	/**
	 * Creacio del EJB <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.create-method view-type="both"
	 * @ejb.permission unchecked="true"
	 * <!-- end-xdoclet-definition -->
	 */
	public void ejbCreate() {
		etiquetesLotDao = new EtiquetesLotDao();
		elaboracioDao = new ElaboracioDao();
		analiticaDao = new AnaliticaDao();
		partidaOlivaDao = new PartidaOlivaDao();
		entradaLotDao = new EntradaLotDao();
		lotDao = new LotDao();
		dipositDao = new DipositDao();
		establimentDao = new EstablimentDao();
		entradaDipositDao = new EntradaDipositDao();
		informacioUtilDao = new InformacioUtilDao();
		arxiuDao = new ArxiuDao();
		gestioInfografiaDao = new GestioInfografiaDao();
		campanyaDao = new CampanyaDao();
		descomposicioPartidaOlivaDao = new DescomposicioPartidaOlivaDao();
		sortidaLotDao = new SortidaLotDao();
		sortidaDipositDao = new SortidaDipositDao();
		informeCampanyaDao = new InformeCampanyaDao();
		etiquetatgeDao = new EtiquetatgeDao();
		partidaOliDao = new PartidaOliDao();
		plantacioDao = new PlantacioDao();
		
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission unchecked="true"
	 * <!-- end-xdoclet-definition -->
	 */
	public void ejbRemove() {
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission unchecked="true"
	 * <!-- end-xdoclet-definition -->
	 */
	public EtiquetesLot findEtiquetesLotByEtiqueta(String lletres, Integer numeros){
		etiquetesLotDao.setHibernateTemplate(getHibernateTemplate());
		return etiquetesLotDao.findByEtiqueta(lletres, numeros);
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission unchecked="true"
	 * <!-- end-xdoclet-definition -->
	 */
	public EtiquetesLot findEtiquetesLotByEtiquetaUsada(String lletres, Integer numeros){
		etiquetesLotDao.setHibernateTemplate(getHibernateTemplate());
		return etiquetesLotDao.findByEtiquetaUsada(lletres, numeros);
	}
	
	/**
	 * Construeix l'arbre per a la traçabilitat resumida de l'oli.
	 * 
	 * @param contenidorId el contenidor per trazar l'arbre
	 * @return una colecció amb la informacó per construir
	 * l'arbre.
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission unchecked="true"
	 * <!-- end-xdoclet-definition -->
	 */
	public TrazabilitatResumida trazabilitatResumidaOliDisponibleLote(String id) {
		TrazabilitatResumida resum = new TrazabilitatResumida();
		resum.setTipus(Constants.TIPUS_TRAZABILITAT_RESUMIDA_LOT);
		lotDao.setHibernateTemplate(getHibernateTemplate());
		Lot lote = lotDao.getById(Long.valueOf(id));				
		
		if (lote != null) {				
			//LOT
			resum.setLot(lote);
						
			entradaLotDao.setHibernateTemplate(getHibernateTemplate());
			EntradaLot entradaLot = entradaLotDao.findUltimaByLot(lote.getId(), new Boolean(true));
			Hibernate.initialize(entradaLot.getZona());
			if (entradaLot.getZona() != null){
				Hibernate.initialize(entradaLot.getZona().getEstabliment());
			}
			Hibernate.initialize(entradaLot.getLot());
			if (entradaLot.getLot() != null){
				Hibernate.initialize(entradaLot.getLot().getEtiquetatge());
				if (entradaLot.getLot().getEtiquetatge() != null){
					Hibernate.initialize(entradaLot.getLot().getEtiquetatge().getMarca());
				}
				Hibernate.initialize(entradaLot.getLot().getEtiquetesLots());
				Iterator itEtiq = entradaLot.getLot().getEtiquetesLots().iterator();
				if (itEtiq.hasNext()){
					resum.setEtiquetaLletra(((EtiquetesLot)itEtiq.next()).getEtiquetaLletra());
				}
			}
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
			if(lote.getPartidaOli() != null){
				resum.setPartidaOli(lote.getPartidaOli());	
			}
			
			// Analítica
			if (resum.getPartidaOli() != null){
				analiticaDao.setHibernateTemplate(getHibernateTemplate());
				Analitica analitica = analiticaDao.findUltimaByPartida(lote.getPartidaOli().getId());
				if (analitica != null){
					Hibernate.initialize(analitica.getDiposit());
				}
				resum.setAnalitica(analitica);
			}
			
			// Acidesa
			resum.setAcidesa(lote.getAcidesa());
		}
		return resum;
	}
	
	
	private TrazabilitatResumida generaResum(TrazabilitatResumida resum, Traza traza) {
		Collection trazasPadre = traza.getTrazasForTtrCodtrapare();
		
		for(Iterator itertrazasPadre=trazasPadre.iterator(); itertrazasPadre.hasNext();){
			Traza trazaPadre = (Traza)itertrazasPadre.next();
			
			switch (trazaPadre.getTipus().intValue()) {
			case Constants.CODI_TRAZA_TIPUS_ENTRADA_LOT:		// Embotellat o devolució
				if (resum.getLot() != null){
					entradaLotDao.setHibernateTemplate(getHibernateTemplate());
					EntradaLot entradaLot = entradaLotDao.findByTraza(trazaPadre.getId(), new Boolean(true));
					if(entradaLot != null && (entradaLot.getEsDevolucio() == null || !entradaLot.getEsDevolucio())){		
						List entrades = resum.getLotsEmbotellat();
						Hibernate.initialize(entradaLot.getZona());
						if (entradaLot.getZona() != null){
							Hibernate.initialize(entradaLot.getZona().getEstabliment());
						}
						Hibernate.initialize(entradaLot.getLot());
						if (entradaLot.getLot() != null){
							Hibernate.initialize(entradaLot.getLot().getEtiquetatge());
							if (entradaLot.getLot().getEtiquetatge() != null){
								Hibernate.initialize(entradaLot.getLot().getEtiquetatge().getMarca());
							}
							Hibernate.initialize(entradaLot.getLot().getEtiquetesLots());
							Iterator itEtiq = entradaLot.getLot().getEtiquetesLots().iterator();
							if (itEtiq.hasNext()){
								resum.setEtiquetaLletra(((EtiquetesLot)itEtiq.next()).getEtiquetaLletra());
							}
						}
						entrades.add(entradaLot);
						resum.setLotsEmbotellat(entrades);
					}
				}		
				generaResum(resum, trazaPadre);
				break;
			case Constants.CODI_TRAZA_TIPUS_SORTIDA_DIPOSIT:
				if (resum.getLot() == null){ // No és la traçabilitat d'un lot
					// Si el fill és una entrada de lot, llavors ens trobam amb un embotellat
					Collection trazasHijos = trazaPadre.getTrazasForTtrCodtrafill();
					for(Iterator itertrazasHijos=trazasHijos.iterator(); itertrazasHijos.hasNext();){
						Traza trazaHijo = (Traza)itertrazasHijos.next();	
						if(trazaHijo.getTipus().intValue() == Constants.CODI_TRAZA_TIPUS_ENTRADA_LOT){
							entradaLotDao.setHibernateTemplate(getHibernateTemplate());
							EntradaLot entradaLot = entradaLotDao.findByTraza(trazaHijo.getId(), new Boolean(true));
							if(entradaLot != null && (entradaLot.getEsDevolucio() == null || !entradaLot.getEsDevolucio())){		
								List entrades = resum.getLotsEmbotellat();
								Hibernate.initialize(entradaLot.getZona());
								if (entradaLot.getZona() != null){
									Hibernate.initialize(entradaLot.getZona().getEstabliment());
								}
								Hibernate.initialize(entradaLot.getLot());
								if (entradaLot.getLot() != null){
									Hibernate.initialize(entradaLot.getLot().getEtiquetatge());
									if (entradaLot.getLot().getEtiquetatge() != null){
										Hibernate.initialize(entradaLot.getLot().getEtiquetatge().getMarca());
									}
									Hibernate.initialize(entradaLot.getLot().getEtiquetesLots());
									Iterator itEtiq = entradaLot.getLot().getEtiquetesLots().iterator();
									if (itEtiq.hasNext()){
										resum.setEtiquetaLletra(((EtiquetesLot)itEtiq.next()).getEtiquetaLletra());
									}
								}
								entrades.add(entradaLot);
								resum.setLotsEmbotellat(entrades);
							}
						}
					}
				}
				generaResum(resum, trazaPadre);
				break;
			case Constants.CODI_TRAZA_TIPUS_ELABORACIO:
				elaboracioDao.setHibernateTemplate(getHibernateTemplate());
				Elaboracio elaboracio = elaboracioDao.findByTraza(trazaPadre.getId(), new Boolean(true));				
				
				if (elaboracio != null) {
				
					//ELABORACION
					List elaboracions = resum.getElaboracions();
					Hibernate.initialize(elaboracio.getCategoriaOli());
					Hibernate.initialize(elaboracio.getPartidaOli());
					
					if (!elaboracions.contains(elaboracio)) {
						elaboracions.add(elaboracio);
						resum.setElaboracions(elaboracions);
						
						if(elaboracio.getPartidaOlivas()!=null){
							//PARTIDAS
							for(Iterator itPartidas = elaboracio.getPartidaOlivas().iterator(); itPartidas.hasNext();){
								PartidaOliva partida = (PartidaOliva)itPartidas.next();
								partidaOlivaDao.setHibernateTemplate(getHibernateTemplate());
								partidaOlivaDao.getById(partida.getId());
								
								//DESCOMPOSICION PARTIDAS								
								for(Iterator itDescompPartidas = partida.getDescomposicioPartidesOlives().iterator(); itDescompPartidas.hasNext();){
									DescomposicioPartidaOliva descompPartida = (DescomposicioPartidaOliva)itDescompPartidas.next();
	//								DescomposicioPartidaOliva descompPartida = descomposicioPartidaOlivaAmbId(((DescomposicioPartidaOliva)itDescompPartidas.next()).getId());
	//								resum.setResum(descompPartida.getDescomposicioPlantacio(), descompPartida.getKilos());
									Hibernate.initialize(descompPartida);
									Hibernate.initialize(descompPartida.getDescomposicioPlantacio());
									Hibernate.initialize(descompPartida.getDescomposicioPlantacio().getPlantacio());
									Hibernate.initialize(descompPartida.getDescomposicioPlantacio().getPlantacio().getFinca());
									Hibernate.initialize(descompPartida.getDescomposicioPlantacio().getPlantacio().getFinca().getOlivicultor());
									Hibernate.initialize(descompPartida.getDescomposicioPlantacio().getPlantacio().getMunicipi());
									resum.setResum(descompPartida);
								}
							}
						}
					}
				}
				break;
			case Constants.CODI_TRAZA_TIPUS_SORTIDA_LOT:
			case Constants.CODI_TRAZA_TIPUS_TRASLLAT_DIPOSIT:
			case Constants.CODI_TRAZA_TIPUS_VENDA_OLI:
			case Constants.CODI_TRAZA_TIPUS_ENTRADA_DIPOSIT:
			default:
				generaResum(resum, trazaPadre);
				break;
			}
		}
		return resum;
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission unchecked="true"
	 * <!-- end-xdoclet-definition -->
	 */
	public DescomposicioPartidaOliva descomposicioPartidaOlivaAmbId(Long id) {
		descomposicioPartidaOlivaDao.setHibernateTemplate(getHibernateTemplate());
		return descomposicioPartidaOlivaDao.getById(id);
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission unchecked="true"
	 * <!-- end-xdoclet-definition -->
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
	 * @ejb.permission unchecked="true"
	 * <!-- end-xdoclet-definition -->
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
	 * @ejb.permission unchecked="true"
	 * <!-- end-xdoclet-definition -->
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
	 * @ejb.permission unchecked="true"
	 * <!-- end-xdoclet-definition -->
	 */
	public Elaboracio elaboracioAmbId(Long id, Boolean valid) {
		elaboracioDao.setHibernateTemplate(getHibernateTemplate());
		return elaboracioDao.getById(id,valid);
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission unchecked="true"
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
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission unchecked="true"
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
	 * @ejb.permission unchecked="true"
	 * <!-- end-xdoclet-definition -->
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
	 * 
	 * Cerca totes les informacions utils actives
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission unchecked="true"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection informacioUtilActius() {
		informacioUtilDao.setHibernateTemplate(getHibernateTemplate());
		return informacioUtilDao.findActius();
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission unchecked="true"
	 * <!-- end-xdoclet-definition -->
	 */
	public InformacioUtil informacioUtilAmbId(Long id) {
		informacioUtilDao.setHibernateTemplate(getHibernateTemplate());
		return informacioUtilDao.getById(id);
	}
	
	/**
	 * 
	 * Cerca totes les informacions utils actives
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission unchecked="true"
	 * <!-- end-xdoclet-definition -->
	 */
	public Arxiu arxiuAmbId(Long id) {
		arxiuDao.setHibernateTemplate(getHibernateTemplate());
		return arxiuDao.getById(id);
	}
	
	/**
	 * 
	 * Cerca totes les gestioInfografies
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission unchecked="true"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection gestioInfografiaCercaTots() {
		gestioInfografiaDao.setHibernateTemplate(getHibernateTemplate());
		return gestioInfografiaDao.findAll();
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission unchecked="true"
	 * <!-- end-xdoclet-definition -->
	 */
	public GestioInfografia gestioInfografiaDaoAmbId(Long id) {
		gestioInfografiaDao.setHibernateTemplate(getHibernateTemplate());
		return gestioInfografiaDao.getById(id);
	}
	
	/**
	 * Devuelve todas las campanyas
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission unchecked="true"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection campanyaCercaTotes() {
		campanyaDao.setHibernateTemplate(getHibernateTemplate());
		return campanyaDao.findAll();
	}
	
	/**
	 * Cerca una campanya amb id 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission unchecked="true"
	 * <!-- end-xdoclet-definition -->
	 */
	public Campanya campanyaAmbId(Integer id) {
		campanyaDao.setHibernateTemplate(getHibernateTemplate());
		return campanyaDao.campanyaAmbId(id);
	}
	
	/**
	 * Cerca la campanya actual 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission unchecked="true"
	 * <!-- end-xdoclet-definition -->
	 */
	public Long campanyaCercaActual() {
		campanyaDao.setHibernateTemplate(getHibernateTemplate());
		return campanyaDao.getCampanyaActual();
	}
	
	/**
	 * Devuelve el total de kg de entrada de oliva por fechas
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission unchecked="true"
	 * <!-- end-xdoclet-definition -->
	 */
	public Double getTotalOlivasEntradas(Long temporadaId,Date dataInici,Date dataFin,Long temporadaActual) {
	//public Double getTotalOlivasEntradas(Date dataInici,Date dataFin) {
		descomposicioPartidaOlivaDao.setHibernateTemplate(getHibernateTemplate());
		//return descomposicioPartidaOlivaDao.getTotalOlivasEntradas(temporadaId, dataInici, dataFin, temporadaActual);	
		return descomposicioPartidaOlivaDao.getTotalOlivasEntradasEntreDates(dataInici, dataFin);
	}
	
	/**
	 * Devuelve el total de aceite elaborado por fechas
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission unchecked="true"
	 * <!-- end-xdoclet-definition -->
	 */
	public Double getTotalOliElaborat(Long temporadaId,Date dataInici,Date dataFin, Long temporadaActual) {
	//public Double getTotalOliElaborat(Date dataInici,Date dataFin) {
		elaboracioDao.setHibernateTemplate(getHibernateTemplate());
		//return elaboracioDao.getTotalOliElaborat(temporadaId, dataInici, dataFin, temporadaActual, new Boolean(true));	
		return elaboracioDao.getTotalOliElaboratEntreDates(dataInici, dataFin);
	}
	
	/**
	 * Busca todas las ventas de lotes y aceite a granel de una determinada categoria por fechas o temporada
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission unchecked="true"
	 * <!-- end-xdoclet-definition -->
	 */
	public Double getTotalOliComercialitzatByCategoriasConsulta(Long temporadaId, Date dataInici, Date dataFin, Object[] categorias,Long temporadaActual) {
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
	 * Cerca tots els establiments activos de unos tipos determinados <!-- begin-xdoclet-definition -->
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission unchecked="true"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection establimentCercaTotsActivosByTipos(Long campanyaId,Object[] tipos) {
		establimentDao.setHibernateTemplate(getHibernateTemplate());
		return establimentDao.findEstablimentsByTipos(campanyaId, tipos);
	}
	
	
	/**
	 * Cerca tots els informes de les campanyes per tipus
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission unchecked="true"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection informeCampanyaCercaPerTipus(Boolean tipus) {
		informeCampanyaDao.setHibernateTemplate(getHibernateTemplate());
		return informeCampanyaDao.findByTipus(tipus);
	}
	
	/**
	 * Guarda el uid de tarjeta para un olivicultor y una campanya
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission unchecked="true"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection marquesCercaPerEstabliment(Long idEstabliment) {
		establimentDao.setHibernateTemplate(getHibernateTemplate());
		Establiment establiment = establimentDao.getById(idEstabliment);
		Collection marcas = establiment.getMarcas();
		return marcas;
	}
	
	/**
	 * Cerca tots els etiquetatges d'una marca 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission unchecked="true"
	 * <!-- end-xdoclet-definition -->
	 */
	public List etiquetatgeByMarca(Long idMarca) {
		etiquetatgeDao.setHibernateTemplate(getHibernateTemplate());
		return etiquetatgeDao.getEtiquetatgeByMarca(idMarca);
	}
	
	/**
	 * Cerca totes les partides d'un etiquetatge
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission unchecked="true"
	 * <!-- end-xdoclet-definition -->
	 */
	public List partidaByEtiquetatge(Long idEtiquetatge) {
		partidaOliDao.setHibernateTemplate(getHibernateTemplate());
		return partidaOliDao.findByEtiquetage(idEtiquetatge, true);
	}

	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission unchecked="true"
	 * <!-- end-xdoclet-definition -->
	 */
	public List lotByPartidaEtiquetatge(Long partidaId, Long etiquetatgeId){
		lotDao.setHibernateTemplate(getHibernateTemplate());
		return lotDao.findByPartidaEtiquetatge(partidaId, etiquetatgeId, true);
	}


	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission unchecked="true"
	 * <!-- end-xdoclet-definition -->
	 */
	public Plantacio plantacioAmbId(Long id) {
		plantacioDao.setHibernateTemplate(getHibernateTemplate());
		return plantacioDao.getById(id);
	}
	
	/**
	 * Devuelve la cantidad total de litros de aceite existente para un establecimiento y categoria
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission unchecked="true"
	 * <!-- end-xdoclet-definition -->
	 */
	public Double getQuantitatOliExistenciDO(Date data){
		Double exist = 0.0;
		dipositDao.setHibernateTemplate(getHibernateTemplate());
		Collection diposits = dipositDao.findByEstablimentCategoriaEnData(null, new Long(Constants.CATEGORIA_DO), data);
//		Double ent = 0.0;
//		Double sort = 0.0;
		for (Iterator it = diposits.iterator(); it.hasNext();){
			Diposit diposit = (Diposit)it.next();
			entradaDipositDao.setHibernateTemplate(getHibernateTemplate());
			Double entrades = entradaDipositDao.getSumaEntradesDipositPerDipositFinsData(diposit.getId(), data, new Boolean(true));
			sortidaDipositDao.setHibernateTemplate(getHibernateTemplate());
			Double sortides = sortidaDipositDao.getSumaSortidesDipositPerDipositFinsData(diposit.getId(), data, new Boolean(true));
			Double existencia = entrades - sortides;
//			ent += entrades;
//			sort += sortides;
			if (existencia > 0.0001){ // Evitam problemes amb decimals
				exist += existencia;
				//logger.info("Dip: " + diposit.getCodiAssignat() + " - E: " + entrades + " - S: " + sortides + " = " + (entrades - sortides));
			}
//			System.out.println("Dip: " + diposit.getCodiAssignat() + " - E: " + entrades + " - S: " + sortides + " = " + (entrades - sortides));
		}
//		System.out.println("Entrades dip = " + ent + ", Sortides dip = " + sort);
		
//		ent = 0.0;
//		sort = 0.0;
		Double cont = 0.0;
		lotDao.setHibernateTemplate(getHibernateTemplate());
		Collection lots = lotDao.findByEstablimentCategoriaIData(null, new Long(Constants.CATEGORIA_DO), data);
		
		for (Iterator it = lots.iterator(); it.hasNext();){
			Lot lot = (Lot)it.next();
			sortidaLotDao.setHibernateTemplate(getHibernateTemplate());
			Integer sortida = sortidaLotDao.getSumaSortidesLotFinsData(lot.getId(), data, new Boolean(true));
			entradaLotDao.setHibernateTemplate(getHibernateTemplate());
			Integer devolucions = entradaLotDao.getSumaDevolucionsLotFinsData(lot.getId(), data, new Boolean(true));
			Double existencia = (lot.getNumeroBotellesActuals() + sortida - devolucions) * lot.getEtiquetatge().getTipusEnvas().getVolum();
			if (existencia > 0.0001){ // Evitam problemes amb decimals
				exist += existencia;
				//logger.info("Lot: " + lot.getCodiAssignat() + " - A: " + lot.getNumeroBotellesActuals() + " - S: " + sortida + " - D: " + devolucions + " = " + (lot.getNumeroBotellesActuals() + sortida - devolucions) + " x " + lot.getEtiquetatge().getTipusEnvas().getVolum() + " (" + existencia + ")");
			}
//			ent += (devolucions * lot.getEtiquetatge().getTipusEnvas().getVolum());
//			sort += (sortida * lot.getEtiquetatge().getTipusEnvas().getVolum());
//			cont = lot.getNumeroBotellesActuals() * lot.getEtiquetatge().getTipusEnvas().getVolum();
		}
//		System.out.println("Contingut actual lots = " + (cont) + "Devolucions lots = " + ent + ", Sortides lots = " + sort);
		
		return exist;
	}
	
	/**
	 * Devuelve la cantidad total de litros de aceite existente para un establecimiento y categoria
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission unchecked="true"
	 * <!-- end-xdoclet-definition -->
	 */
	public Double getQuantitatOliExistenciDOSistema(Date data){
		Double existencia = 0.0;
		dipositDao.setHibernateTemplate(getHibernateTemplate());
		Collection diposits = dipositDao.findByEstablimentCategoriaEnData(null, new Long(Constants.CATEGORIA_DO), data);
		if (diposits != null && diposits.size() > 0) {
			Long idDiposits[] = new Long[diposits.size()];
			int i = 0;
//			System.out.println("Dipòsits:\n");
			for(Iterator it = diposits.iterator(); it.hasNext(); i++){
				Diposit diposit = (Diposit)it.next();
				idDiposits[i] = diposit.getId();
//				System.out.println("\t" + diposit.getId() + "\n");
			}
			
			entradaDipositDao.setHibernateTemplate(getHibernateTemplate());
			Double entrades = entradaDipositDao.getSumaEntradesDipositsFinsData(idDiposits, data, new Boolean(true));
			sortidaDipositDao.setHibernateTemplate(getHibernateTemplate());
			Double sortides = sortidaDipositDao.getSumaSortidesDipositsFinsData(idDiposits, data, new Boolean(true));
			existencia = entrades - sortides;
			
//			System.out.println("Entrades dip = " + entrades + ", Sortides dip = " + sortides);
		}
		
		lotDao.setHibernateTemplate(getHibernateTemplate());
		Collection lots = lotDao.findByEstablimentCategoriaIData(null, new Long(Constants.CATEGORIA_DO), data);
		if (lots != null && lots.size() > 0) {
			Long[] lotsId = new Long[lots.size()];
			int i = 0;
//			System.out.println("Lots:\n");
			for (Iterator it = lots.iterator(); it.hasNext(); i++){
				Lot lot = (Lot)it.next();
				lotsId[i] = lot.getId();
//				System.out.println("\t" + lot.getId() + "\n");
			}
		
			lotDao.setHibernateTemplate(getHibernateTemplate());
			Double contingut = lotDao.getSumaContingutActual(lotsId, data);
			
			sortidaLotDao.setHibernateTemplate(getHibernateTemplate());
			Double sortides = sortidaLotDao.getSumaSortidesLotsFinsData(lotsId, data, new Boolean(true)); 
		
			entradaLotDao.setHibernateTemplate(getHibernateTemplate());
			Double devolucions = entradaLotDao.getSumaDevolucionsLotsFinsData(lotsId, data, new Boolean(true));
		
//			System.out.println("Contingut actual lots = " + (contingut) + "Devolucions lots = " + devolucions + ", Sortides lots = " + sortides);
			
			existencia += (contingut + sortides - devolucions);
		}
		return existencia;
	}
	
	/**
	 * Devuelve la cantidad total de litros de aceite existente para un establecimiento y categoria
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission unchecked="true"
	 * <!-- end-xdoclet-definition -->
	 */
	public Double getQuantitatOliAnalisiDOEntreDates(Date dataInici, Date dataFi){
		entradaDipositDao.setHibernateTemplate(getHibernateTemplate());
		return entradaDipositDao.getQuantitatOliAnalisiDOEntreDates(dataInici, dataFi);
	}
	
	
	/**
	 * Busca todas las salidas de depósitos que se han realizado de este establecimiento
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission unchecked="true"
	 * <!-- end-xdoclet-definition -->
	 */
	public Double getSortidesDOEntreDias(Date dataInici, Date dataFi) {
	
		sortidaDipositDao.setHibernateTemplate(getHibernateTemplate());
		Double sortides = sortidaDipositDao.getSortidaDipositEntreDiasEnEstablecimientoAndCategoria(dataInici, dataFi, null, new Long(Constants.CATEGORIA_DO));
		
		sortidaLotDao.setHibernateTemplate(getHibernateTemplate());
		Collection sortLot = sortidaLotDao.getSortidaLotVendaCategoriaEntreDiasEnEstablecimiento(dataInici, dataFi, null, new Long(Constants.CATEGORIA_DO), true);

		for (Iterator it = sortLot.iterator(); it.hasNext();){
			Object[] obj = (Object[])it.next();
			if (obj[0] != null && obj[1] != null) {
				sortides += (Integer)obj[0] * (Double)obj[1];
			}
		}		
		
		entradaLotDao.setHibernateTemplate(getHibernateTemplate());
		Collection devolucions = entradaLotDao.getDevolucionsLotsCategoriaEstablimentFinsData(null, dataFi, new Long(Constants.CATEGORIA_DO), true);
		
		for (Iterator it2 = devolucions.iterator(); it2.hasNext();){
			Object[] obj = (Object[])it2.next();
			if (obj[0] != null && obj[1] != null) {
				sortides -= (Integer)obj[0] * (Double)obj[1];
			}
		}
		return sortides;	
	}
	
	/**
	 * set the hibernate template.
	 * @param hibernateTemplate the hibernate spring template.
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission unchecked="true"
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
	 * @ejb.permission unchecked="true"
	 * <!-- end-xdoclet-definition --> 
	 */
	public HibernateTemplate getHibernateTemplate(){
		return this.hibernateTemplate;
	}
}
