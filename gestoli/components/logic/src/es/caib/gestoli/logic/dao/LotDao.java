package es.caib.gestoli.logic.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.exception.InfrastructureException;
import es.caib.gestoli.logic.model.EtiquetesLot;
import es.caib.gestoli.logic.model.Lot;


/**
 * Home object for domain model class Lot
 * @see es.caib.gestoli.logic.model.Lot
 * 
 */
public class LotDao {
	private static Logger logger = Logger.getLogger(LotDao.class);
	private HibernateTemplate hibernateTemplate;


	/**
	 * Recupera todos los registros
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection findAll(Boolean valid) throws InfrastructureException {
		Collection col;
		try {
			logger.debug("findAll ini");
			// Ponemos where 1 = 1 para que no nos de error cuando se añada el and si se añade.
			String q = "from Lot as lot where 1 = 1 ";
			if (valid != null && valid.booleanValue()) q += " and lot.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and lot.valid = false ";
			q += "order by lot.id ";
			col = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("findAll failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findAll fin");
		return col; 
	}
	
	public Collection findAllByEstablimentEntreDates(Long idEstabliment, Date dataInici, Date dataFi, Boolean valid) throws InfrastructureException {
		Collection col;
		try {
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			String inici = df.format(dataInici);
			String fi = df.format(dataFi);
			
			logger.debug("findAll ini");
			String q = "from Lot as lot where " +
			"     lot.data >= '" + inici + "' " + 
			" and lot.data <= '" + fi + "' " +
			" and lot.zona.establiment.id = " + idEstabliment;
			if (valid != null && valid.booleanValue()) q += " and lot.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and lot.valid = false ";
			q += " order by lot.id ";
			col = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("findAll failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findAll fin");
		return col; 
	}


	/**
	 * Devuelve el objeto que tiene el id
	 * @param id
	 * @return
	 * @throws InfrastructureException
	 */
	public Lot getById(Long id) throws InfrastructureException {
		Lot lot;
		try {
			logger.debug("getById ini");
			lot = (Lot)getHibernateTemplate().load(Lot.class, id);
		} catch (HibernateException ex) {
			logger.error("getById failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getById fin");
		return lot;		
	}


	/**
	 * Lots amb el nom indicat.
	 * 
	 * @return
	 */
	public List getByNomIProducte(String nom, Long idProducte) throws InfrastructureException {
		List lots = null;
		try {
			String sql = "select lot from Lot lot " +
			"where (lot.producte.id = " + idProducte + " or " + idProducte + " is null) " +
			"and upper(lot.codiAssignat) = '" + nom.toUpperCase().replace("'", "''") + "' " + 
			"and lot.valid = true";
			
			lots = getHibernateTemplate().find(sql);
//					"select lot from Lot lot " +
//					"where (lot.producte.id = " + idProducte + " or " + idProducte + " is null) " +
//					"and upper(lot.codiAssignat) = '" + nom.toUpperCase().replace("'", "''") + "' " + 
//					"and lot.valid = true"
//			);
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		
		return lots;
	}

	/**
	 * Retorna un llista de totes les lots associades amb
	 * un establiment.
	 * @return
	 * @throws InfrastructureException
	 */
	public List findByEstabliment(Long establimentId, Boolean valid) throws InfrastructureException {
		List lots;
		try {
			logger.debug("findByEstabliment ini");
			String q = "select lot from Lot lot " +
			"where lot.zona.actiu = true " +
			"and lot.zona.establiment.id = " + establimentId + " ";
			if (valid != null && valid.booleanValue()) q += " and lot.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and lot.valid = false ";
			lots = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("findByEstabliment failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findByEstabliment fin");
		return lots;
	}
	
	/**
	 * Retorna un llista de totes les lots associades amb
	 * un establiment.
	 * @return
	 * @throws InfrastructureException
	 */
	public List findLotBotellesByEstabliment(Long establimentId, Boolean valid) throws InfrastructureException {
		List lots;
		try {
			logger.debug("findLotBotellesByEstabliment ini");
			String q = "select distinct trim(lot.numeroLot) from Lot lot " +
			"where lot.zona.establiment.id = " + establimentId + " ";
			if (valid != null && valid.booleanValue()) q += " and lot.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and lot.valid = false ";
			lots = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("findLotBotellesByEstabliment failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findLotBotellesByEstabliment fin");
		return lots;
	}
	
	/**
	 * Retorna un llista de totes les lots associades amb
	 * un establiment.
	 * @return
	 * @throws InfrastructureException
	 */
	public List findByEstablimentIData(Long establimentId, Date data, Boolean valid) throws InfrastructureException {
		List<Lot> lots;
//		List<Lot> lotsActius = new ArrayList<Lot>();
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String fi = df.format(data);
		try {
			logger.debug("findByEstablimentData ini");
			String q = "select l from Lot l " +
			"where l.zona.actiu = true " +
			"and l.zona.establiment.id = " + establimentId + " " +
			"and l.data <= '" + fi + "' " +
			"and (l.datafi is null or l.datafi > '" + fi + "') ";
			if (valid != null && valid.booleanValue()) q += " and l.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and l.valid = false ";
//			q += " order by l.id";
			lots = getHibernateTemplate().find(q);
			
//			q = "select sl.lot.id, " +
//				"		max(sl.vendaData) " +
//				"  from SortidaLot sl " +
//				" where sl.valid = true " +
//				" group by sl.lot.id " +
//				" order by sl.lot.id";
//			
//			List sorts = getHibernateTemplate().find(q);
//			
//			for (Lot l: lots) {
//				boolean afegir = true;
//				if (l.getNumeroBotellesActuals() <= 0) {
//					for (Object[] obj: (List<Object[]>)sorts) {
//						Long id = (Long)obj[0];
//						if (l.getId().equals(id)) {
//							if (((Date)obj[1]).before(data)) {
//								afegir = false;
//								break;
//							}
//						} else if (id > l.getId())
//							break;
//					}
//					if (afegir) lotsActius.add(l);
//				}
//			}
			
		} catch (HibernateException ex) {
			logger.error("findByEstablimentData failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findByEstablimentData fin");
		return lots;
//		return lotsActius;
	}
	
	/**
	 * Retorna un llista de totes les lots associades amb
	 * un establiment.
	 * @return
	 * @throws InfrastructureException
	 */
	public List findByEstablimentCategoriaIData(Long establimentId, Long categoriaId, Date data) throws InfrastructureException {
		List lots;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String fi = df.format(data);
		try {
			logger.debug("findByEstablimentCategoriaIData ini");
			String q = "select l from Lot l " +
			"where l.zona.actiu = true " +
			" and l.data <= '" + fi + "' " +
			" and l.valid = true " +
			" and l.partidaOli.categoriaOli.id = " + categoriaId;
			if (establimentId != null)
			q += " and l.zona.establiment.id = " + establimentId;
			
			lots = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("findByEstablimentCategoriaIData failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findByEstablimentCategoriaIData fin");
		return lots;
	}
	
	/**
	 * Retorna un llista de totes les lots associades amb
	 * un establiment.
	 * @return
	 * @throws InfrastructureException
	 */
	public List findByEstablimententreDates(Long establimentId, Date dataInici, Date dataFi) throws InfrastructureException {
		List lots;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String fi = df.format(dataInici);
		String ff = df.format(dataFi);
		try {
			logger.debug("findByEstablimententreDates ini");
			String q = "select l from Lot l " +
			"where l.zona.actiu = true " +
			" and l.data >= '" + fi + "' " +
			" and l.data <= '" + ff + "' " +
			" and l.valid = true ";
			if (establimentId != null)
			q += " and l.zona.establiment.id = " + establimentId;
			
			lots = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("findByEstablimententreDates failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findByEstablimententreDates fin");
		return lots;
	}
	
	/**
	 * Retorna un llista de totes les lots associades amb
	 * un establiment.
	 * @return
	 * @throws InfrastructureException
	 */
	public List findByEstablimententreDates(Long establimentId, Date dataInici, Date dataFi, Boolean olivaTaula) throws InfrastructureException {
		List lots;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String fi = df.format(dataInici);
		String ff = df.format(dataFi);
		try {
			logger.debug("findByEstablimententreDates ini");
			String q = "select l from Lot l " +
			"where l.zona.actiu = true " +
			" and l.data >= '" + fi + "' " +
			" and l.data <= '" + ff + "' " +
			" and l.valid = true ";
			if (olivaTaula != null && olivaTaula.booleanValue()) q += " and l.olivaTaula = true ";
			if (olivaTaula != null && !olivaTaula.booleanValue()) q += " and (l.olivaTaula is null or l.olivaTaula = false) ";
			if (establimentId != null)
			q += " and l.zona.establiment.id = " + establimentId;
			
			lots = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("findByEstablimententreDates failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findByEstablimententreDates fin");
		return lots;
	}
	/**
	 * Retorna un llista de totes les lots associades amb
	 * un establiment.
	 * @return
	 * @throws InfrastructureException
	 */
	public List findVaciosByEstabliment(Long establimentId, Boolean valid) throws InfrastructureException {
		List lots;
		try {
			logger.debug("findByEstabliment ini");
			String q = "select lot from Lot lot " +
			"where lot.zona.actiu = true " +
			"and lot.zona.establiment.id = " + establimentId + " " +
			"and (lot.numeroBotellesInicials is null or lot.numeroBotellesInicials = 0) ";
			if (valid != null && valid.booleanValue()) q += " and lot.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and lot.valid = false ";
			lots = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("findByEstabliment failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findByEstabliment fin");
		return lots;
	}
	
	/**
	 * Retorna un llista de totes les lots associades amb
	 * un dipòsit.
	 * @return
	 * @throws InfrastructureException
	 */
	public List findByDiposit(Long dipositId, Boolean valid) throws InfrastructureException {
		List lots;
		try {
			logger.debug("findByDiposit ini");
			String q = "select lot from Lot lot " +
			"where lot.zona.actiu = true " +
			"and lot.diposit.id = " + dipositId + " ";
			if (valid != null && valid.booleanValue()) q += " and lot.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and lot.valid = false ";
			lots = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("findByDiposit failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findByDiposit fin");
		return lots;
	}

	/**
	 * Retorna un llista de tots els lots associats amb les zones
	 * @return
	 * @throws InfrastructureException
	 */
	public List findByZona(Long zonaId, Boolean valid) throws InfrastructureException {
		List establiments;
		try {
			logger.debug("findByZona ini");
			String q = "select lot from Lot lot " +
			"where lot.zona.id = " +zonaId;
			if (valid != null && valid.booleanValue()) q += " and lot.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and lot.valid = false ";
			q += "order by lot.id";
			establiments = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("findByZona failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findByZona fin");
		return establiments;
	}

	/**
	 * Retorna un llista de tots els lots associats amb les zones
	 * @return
	 * @throws InfrastructureException
	 */
	public List findByEtiquetaje(Long etiId, Boolean valid) throws InfrastructureException {
		List etiquetatges;
		try {
			logger.debug("findByEtiquetaje ini");
			String q = "select lot from Lot lot " +
			"where lot.etiquetatge.id = " +etiId;
			if (valid != null && valid.booleanValue()) q += " and lot.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and lot.valid = false ";
			q += "order by lot.id";
			etiquetatges = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("findByEtiquetaje failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findByEtiquetaje fin");
		return etiquetatges;
	}
	
	/**
	 * Retorna un llista de tots els lots associats amb les zones
	 * @return
	 * @throws InfrastructureException
	 */
	public List findByPartidaEtiquetatge(Long partidaId, Long etiquetatgeId, Boolean valid) throws InfrastructureException {
		List lots;
		try {
			logger.debug("findByPartidaEtiquetatge ini");
			String q = "select lot from Lot lot " +
			"where lot.etiquetatge.id = " +etiquetatgeId + " " +
			"and   lot.partidaOli.id = " + partidaId;
			if (valid != null && valid.booleanValue()) q += " and lot.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and lot.valid = false ";
			q += "order by lot.id";
			lots = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("findByPartidaEtiquetatge failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findByPartidaEtiquetatge fin");

		return lots;
		/*if (lots != null && lots.size() > 0){
			return (Lot)lots.get(0);
		} else {
			return null;
		}*/
		
	}

	/**
	 * Retorna un llista de tots els lots no vendidos associats amb les zones
	 * @return
	 * @throws InfrastructureException
	 */
	public List findNoVendidosByZona(Long zonaId, Boolean valid, boolean showCreatTancament) throws InfrastructureException {
		List lots;
		try {
			logger.debug("findNoVendidosByZona ini");
			// JUAN CARLOS: Select anterior. Solo mostraba lotes por zona y campaña.
//			String q = "select lot from Lot lot " +
//			"where lot.zona.id = " +zonaId+ " " +
//			"and lot.id not in (select slo.lot.id  from SortidaLot slo where slo.accioSortida = 'v' ) "+
//			"order by lot.id";
			
			String q = "from Lot lot " +
			"where lot.zona.idOriginal in (select zon.idOriginal from Zona zon where zon.id = " +zonaId+ ") " +
			// JUAN CARLOS: En principio si tenemos botellasActuals > 0 cuenta como no vendido 
			// o que aun quedan botellas para vender y no hace falta comprobar si hay salida
//			"and (lot.id not in (select slo.lot.id  from SortidaLot slo where slo.accioSortida = 'v') or lot.numeroBotellesActuals > 0) ";
			"and (lot.numeroBotellesActuals > 0 " + (showCreatTancament == true?"or lot.numeroBotellesInicials = 0":"") + ") ";
			if (valid != null && valid.booleanValue()) q += " and lot.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and lot.valid = false ";
			q += "order by lot.id";
			
			lots = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("findNoVendidosByZona failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findNoVendidosByZona fin");
		return lots;
	}
	
	/**
	 * Retorna un llista de tots els lots no vendidos associats amb les zones
	 * @return
	 * @throws InfrastructureException
	 */
	public List findNoVendidosByZona(Long zonaId, Boolean valid, boolean showCreatTancament, Boolean olivaTaula) throws InfrastructureException {
		List lots;
		try {
			logger.debug("findNoVendidosByZona ini");
			// JUAN CARLOS: Select anterior. Solo mostraba lotes por zona y campaña.
//			String q = "select lot from Lot lot " +
//			"where lot.zona.id = " +zonaId+ " " +
//			"and lot.id not in (select slo.lot.id  from SortidaLot slo where slo.accioSortida = 'v' ) "+
//			"order by lot.id";
			
			String q = "from Lot lot " +
			"where lot.zona.idOriginal in (select zon.idOriginal from Zona zon where zon.id = " +zonaId+ ") " +
//			" CASE " +
//			"	WHEN lot.partidaOli.categoriaOli.id = 3 THEN lot.id in (select et.lot.id from EtiquetesLot et where et.lot.id = lot.id ) " +
//			" END " +
			// JUAN CARLOS: En principio si tenemos botellasActuals > 0 cuenta como no vendido 
			// o que aun quedan botellas para vender y no hace falta comprobar si hay salida
//			"and (lot.id not in (select slo.lot.id  from SortidaLot slo where slo.accioSortida = 'v') or lot.numeroBotellesActuals > 0) ";
			"and (lot.numeroBotellesActuals > 0 " + (showCreatTancament == true?"or lot.numeroBotellesInicials = 0":"") + ") ";
			if (valid != null && valid.booleanValue()) q += " and lot.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and lot.valid = false ";
			if (olivaTaula != null && olivaTaula.booleanValue()) q += " and lot.olivaTaula = true ";
			if (olivaTaula != null && !olivaTaula.booleanValue()) q += " and (lot.olivaTaula is null or lot.olivaTaula = false) ";
			q += "order by lot.id";
			
			lots = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("findNoVendidosByZona failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findNoVendidosByZona fin");
		return lots;
	}


	/**
	 * Retorna un llista de tots els lots no vendidos associats amb establiment
	 * @return
	 * @throws InfrastructureException
	 */
	public List findNoVendidosByEstablecimiento(Long establimentId, Boolean valid) throws InfrastructureException {
		List establiments;
		try {
			logger.debug("findNoVendidosByEstablecimiento ini");
//			String q = "select lot from Lot lot " +
//			"where lot.zona.establiment.id = " +establimentId+ " " +
//			"and lot.id not in (select slo.lot.id  from SortidaLot slo where slo.accioSortida = 'v' ) "+
//			"order by lot.id";
			String q = "select lot from Lot lot " +
			"where lot.zona.establiment.idOriginal in (select est.idOriginal from Establiment est where est.id = " +establimentId+ ") " +
			"and lot.id not in (select slo.lot.id  from SortidaLot slo where slo.accioSortida = 'v' ) ";
			if (valid != null && valid.booleanValue()) q += " and lot.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and lot.valid = false ";
			q += "order by lot.id";
			establiments = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("findNoVendidosByEstablecimiento failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findNoVendidosByEstablecimiento fin");
		return establiments;
	}
	
	
	public List findExistenciesLotsByEstabliment(Long establimentId, Boolean valid) throws InfrastructureException {
		List establiments;
		try {
			logger.debug("findNoVendidosByEstablecimiento ini");
//			String q = "select lot from Lot lot " +
//			"where lot.zona.establiment.id = " +establimentId+ " " +
//			"and lot.id not in (select slo.lot.id  from SortidaLot slo where slo.accioSortida = 'v' ) "+
//			"order by lot.id";
			String q = "select lot from Lot lot " +
			"where lot.zona.establiment.idOriginal in (select est.idOriginal from Establiment est where est.id = " +establimentId+ ") " +
			"and lot.numeroBotellesActuals > 0 ";
			if (valid != null && valid.booleanValue()) q += " and lot.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and lot.valid = false ";
			q += "order by lot.id";
			establiments = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("findNoVendidosByEstablecimiento failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findNoVendidosByEstablecimiento fin");
		return establiments;
	}
	
	/**
	 * Retorna un llista de tots els lots no vendidos associats amb establiment
	 * @return
	 * @throws InfrastructureException
	 */
	public List findLotsSortidesByEstablimentIDataInici(Long establimentId, Date dataInici, Boolean valid) throws InfrastructureException {
		List establiments;
		try {
			logger.debug("findNoVendidosByEstablecimiento ini");
//			String q = "select lot from Lot lot " +
//			"where lot.zona.establiment.id = " +establimentId+ " " +
//			"and lot.id not in (select slo.lot.id  from SortidaLot slo where slo.accioSortida = 'v' ) "+
//			"order by lot.id";
			String q = "select lot from Lot lot " +
			"where lot.zona.establiment.idOriginal in (select est.idOriginal from Establiment est where est.id = " +establimentId+ ") " +
			"and lot.id in (select slo.lot.id  from SortidaLot slo where slo.accioSortida = 'v' and slo.vendaData > '"+ dataInici +"') ";
			if (valid != null && valid.booleanValue()) q += " and lot.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and lot.valid = false ";
			q += "order by lot.id";
			establiments = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("findNoVendidosByEstablecimiento failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findNoVendidosByEstablecimiento fin");
		return establiments;
	}
	
	/**
	 * Retorna un llista de tots els lots no vendidos associats amb establiment
	 * @return
	 * @throws InfrastructureException
	 */
	public List findLotsSortidesByEstablimentEntreDates(Long establimentId, Date dataInici, Date dataFi, Boolean valid) throws InfrastructureException {
		List establiments;
		try {
			logger.debug("findNoVendidosByEstablecimiento ini");
			String q = 	"select lot from Lot lot " +
						"where lot.zona.establiment.id = " +establimentId+ " " +
						"and lot.id in (select slo.lot.id  " +
										"from SortidaLot slo where slo.accioSortida = 'v' " +
										"and slo.vendaData >= '"+ dataInici +"' " +
										"and slo.vendaData <= '"+ dataFi +"') ";
			if (valid != null && valid.booleanValue()) q += " and lot.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and lot.valid = false ";
			q += "order by lot.id";
			establiments = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("findLotsSortidesByEstablimentEntreDates failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findLotsSortidesByEstablimentEntreDates fin");
		return establiments;
	}
	
	/**
	 * Retorna un llista de tots NO vacios, sin etiquetas y  de unas determinadas categorias associats amb les zones
	 * @return
	 * @throws InfrastructureException
	 */
	
	public List findNoEtiquetadosByZonaAndCategorias(Long zonaId,Object[] categorias, Boolean valid) throws InfrastructureException {
		List lots = null;
//		try {
//			logger.debug("findNoEtiquetadosByZonaAndCategorias ini");
//			String q = "select lot from Lot lot " +
//			"where lot.zona.idOriginal in (select zon.idOriginal from Zona zon where zon.id = " +zonaId+ ") " +
//			"and lot.numeroEtiquetaInicial is null " +
//			"and lot.numeroEtiquetaFinal is null " +
//			"and lot.partidaOli.categoriaOli.id in(:categorias) "+
//			// "and lot.id not in (select slo.lot.id  from SortidaLot slo where slo.accioSortida = 'v' ) ";
//			"and lot.numeroBotellesActuals > 0 ";
//			if (valid != null && valid.booleanValue()) q += " and lot.valid = true ";
//			if (valid != null && !valid.booleanValue()) q += " and lot.valid = false ";
//			q += "order by lot.id";
//			lots = getHibernateTemplate().findByNamedParam(q, "categorias", categorias);
//		} catch (HibernateException ex) {
//			logger.error("findNoEtiquetadosByZonaAndCategorias failed", ex);
//			throw new InfrastructureException(ex);
//		}
//		logger.debug("findNoEtiquetadosByZonaAndCategorias fin");
		return lots;
	}
	
	public List findByZonaAndCategorias(Long zonaId,Object[] categorias, Boolean valid, Boolean olivaTaula) throws InfrastructureException {
		List lots;
		try {
			logger.debug("findNoEtiquetadosByZonaAndCategorias ini");
			String q = "select lot from Lot lot " +
			"where lot.zona.idOriginal in (select zon.idOriginal from Zona zon where zon.id = " +zonaId+ ") " +
			"and lot.partidaOli.categoriaOli.id in(:categorias) "+
			// "and lot.id not in (select slo.lot.id  from SortidaLot slo where slo.accioSortida = 'v' ) ";
			"and lot.numeroBotellesActuals > 0 ";
			if (valid != null && valid.booleanValue()) q += " and lot.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and lot.valid = false ";
			if (olivaTaula != null && olivaTaula.booleanValue()) q += " and lot.olivaTaula = true ";
			if (olivaTaula != null && !olivaTaula.booleanValue()) q += " and (lot.olivaTaula is null or lot.olivaTaula = false) ";
			q += "order by lot.id";
			lots = getHibernateTemplate().findByNamedParam(q, "categorias", categorias);
		} catch (HibernateException ex) {
			logger.error("findNoEtiquetadosByZonaAndCategorias failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findNoEtiquetadosByZonaAndCategorias fin");
		return lots;
	}
	
	/**
	 * Retorna un llista de tots els lots associats amb les zones
	 * @return
	 * @throws InfrastructureException
	 */
	public List findByZona(Long zonaId, Boolean valid, Boolean olivaTaula) throws InfrastructureException {
		List establiments;
		try {
			logger.debug("findByZona ini");
			String q = "select lot from Lot lot " +
			"where lot.zona.id = " +zonaId;
			if (valid != null && valid.booleanValue()) q += " and lot.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and lot.valid = false ";
			if (olivaTaula != null && olivaTaula.booleanValue()) q += " and lot.olivaTaula = true ";
			if (olivaTaula != null && !olivaTaula.booleanValue()) q += " and (lot.olivaTaula is null or lot.olivaTaula = false) ";
			q += "order by lot.id";
			establiments = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("findByZona failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findByZona fin");
		return establiments;
	}
	
	
	

	/**
	 * Retorna un llista de tots NO VENDIDOS, de unas determinadas categorias asociados con un establecimiento
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public List findNoVendidosByEstablecimientoAndCategorias(Long establimentId,Object[] categorias, Boolean valid) throws InfrastructureException {
		List lots;
		try {
			logger.debug("findNoVendidosByEstablecimientoAndCategorias ini");

			String q = "select lot from Lot lot where lot.zona.establiment.idOriginal in (select est.idOriginal from Establiment est where est.id = " +establimentId+ ") ";
			if(categorias!= null && categorias.length>0){
				q += "and lot.partidaOli.categoriaOli.id in(:categorias) ";
			}
			
			//aamengual 24/03/2010 - correción del bug [mantisID: 122] Lotes semivacíos no aparecen en la consulta de "Aceite disponible"
			//q += "and lot.id not in (select slo.lot.id  from SortidaLot slo where slo.accioSortida = 'v' ) ";
			q += " and lot.numeroBotellesActuals != 0 ";
			
			if (valid != null && valid.booleanValue()) q += " and lot.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and lot.valid = false ";
			q += "order by lot.id";
			if(categorias!= null && categorias.length>0){
				lots = getHibernateTemplate().findByNamedParam(q, "categorias", categorias);
				
			} else {
				lots = getHibernateTemplate().find(q);
			}
			
		} catch (HibernateException ex) {
			logger.error("findNoVendidosByEstablecimientoAndCategorias failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findNoVendidosByEstablecimientoAndCategorias fin");
		return lots;
	}
	
	/**
	 * Devuelve todos las botellas de un lote de un establecimiento no vendidos en una fecha y que pertenecen a un determinado etiquetage
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public Integer findBotellesByEstablimentEtiquetageAndData(Long establimentId, Long etiquetatgeId, Date data) {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String fi = df.format(data);
		List botelles = null;
		try {
			logger.debug("findBotellesByEstablimentEtiquetageAndData ini");
			String q = "select lot.numeroBotellesActuals, sum(sl.vendaNumeroBotelles - sl.botellesDevolucio) as botsort " +
						"from Lot lot " +
						"left outer join lot.sortidaLots sl with sl.vendaData > '" + fi + "' and sl.valid = true " +
						"where lot.valid = true " +
						"and lot.zona.establiment.id = " + establimentId + " " +
						"and lot.etiquetatge.id = " + etiquetatgeId + " " +
						"and lot.data <= '" + fi + "' " +
//						"and sl.valid = true " +
						"group by lot.numeroBotellesActuals"; 
			botelles = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("findBotellesByEstablimentEtiquetageAndData failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findBotellesByEstablimentEtiquetageAndData fin");
		if (botelles != null && botelles.size() > 0){
			Integer numBots = 0;
			for(Object obj : botelles){
				Object[] bots =  (Object[])obj;
				numBots += (Integer)bots[0];
				if (bots[1] != null){
					numBots += ((Long)bots[1]).intValue();
				}
			}
			return numBots;
		} else {
			return 0;
		}
	}

	
	/**
	 * Devuelve todos las botellas de un lote de un establecimiento no vendidos en una fecha y que pertenecen a un determinado etiquetage
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public List findSoridesLotByEstablimentAndDataGroupedByEtiquetatge(Long establimentId, Date data) {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String fi = df.format(data);
		List sortides = null;
		try {
			logger.debug("findSoridesLotByEstablimentAndDataGroupedByEtiquetatge ini");
			String q = 	"select e.id, sum(sl.vendaNumeroBotelles) " +
						"from SortidaLot sl, " +
						"Etiquetatge e " +
						"where sl.valid = true " +
						"and sl.zona.establiment.id = " + establimentId + " " +
						"and sl.vendaData = '" + fi + "' " +
						"and sl.lot.etiquetatge.id = e.id " +
						"group by e.id"; 
			sortides = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("findSoridesLotByEstablimentAndDataGroupedByEtiquetatge failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findSoridesLotByEstablimentAndDataGroupedByEtiquetatge fin");
		return sortides;
	}
	
	
	/**
	 * Retorna tots els lots que s'han creat un dia determinat, en un establiment
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public List findBotellesLotsNousByEstablimentAndDataGroupedByEtiquetatge(Long establimentId, Date data){
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			String fi = df.format(data);
			List lots = null;
		try {
			logger.debug("findLotsNousByEstablimentAndData ini");
			String q = 	"select e.id, sum(lot.numeroBotellesInicials) " +
						"from Lot lot, " +
						"Etiquetatge e " +
						"where lot.valid = true " +
						"and lot.zona.establiment.id = " + establimentId + " " +
						"and lot.data = '" + fi + "' " +
						"and lot.etiquetatge.id = e.id " +
						"group by e.id"; 
			lots = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("findLotsNousByEstablimentAndData failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findLotsNousByEstablimentAndData fin");
		return lots;
	}
	
	/**
	 * Retorna true si existen lotes asociados a zonas
	 * 
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public boolean existenLotesAsociadosZonas(Long idZona, Boolean valid) throws InfrastructureException {
		List lots;
		try {
			logger.debug("existenLotesAsociadosZonas ini");
//			String q = "select count(lot.id) from Lot lot " +
//			"where lot.zona.id = " + idZona + " ";
			String q = "select count(lot.id) from Lot lot " +
			"where lot.zona.idOriginal in (select zon.idOriginal from Zona zon where zon.id = " +idZona+ ") ";			
			if (valid != null && valid.booleanValue()) q += " and lot.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and lot.valid = false ";
			
			lots = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("existenLotesAsociadosZonas failed", ex);
			throw new InfrastructureException(ex);
		}
		if (lots != null && lots.size()> 0 && ((Long)lots.get(0)).intValue() > 0) {
			logger.debug("existenLotesAsociadosZonas fin");
			return true;
		}
		logger.debug("existenLotesAsociadosZonas fin");
		return false;
	}

	/**
	 * Retorna true si existen lotes asociados a depositos
	 * 
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public boolean existenLotesAsociadosDeposito(Long idDiposit, Boolean valid) throws InfrastructureException {
		List lot;
		try {
			logger.debug("existenLotesAsociadosDeposito ini");
			String q = "select count(lot.id) from Lot lot " +
			"where lot.diposit.id = " + idDiposit + " ";
			if (valid != null && valid.booleanValue()) q += " and lot.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and lot.valid = false ";
			lot = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("existenLotesAsociadosDeposito failed", ex);
			throw new InfrastructureException(ex);
		}
		if (lot != null && lot.size()> 0 && ((Long)lot.get(0)).intValue() > 0) {
			logger.debug("existenLotesAsociadosDeposito fin");
			return true;
		}
		logger.debug("existenLotesAsociadosDeposito fin");
		return false;
	}
	
	/**
	 * Actualiza un registro
	 * @param Lot
	 * @throws InfrastructureException
	 */
	public void makePersistent(Lot lot) throws InfrastructureException {
		try {
			logger.debug("makePersistent ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().saveOrUpdate(lot);
			getHibernateTemplate().flush();
			logger.debug("makePersistent fin");
		} catch (HibernateException ex) {
			logger.error("makePersistent failed", ex);
			throw new InfrastructureException(ex);
		}
	}


	/**
	 * Actualiza un registro
	 * @param Lot
	 * @throws InfrastructureException
	 */
	public void makePersistent(Lot lot, Session session) throws InfrastructureException {
		try {
			logger.debug("makePersistent ini");
			session.setFlushMode(FlushMode.ALWAYS);
			session.saveOrUpdate(lot);
			session.flush();
		} catch (HibernateException ex) {
			logger.error("makePersistent failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("makePersistent fin");
	}


	/**
	 * Borra un registro
	 * @param Lot
	 * @throws InfrastructureException
	 */
	public void makeTransient(Lot lot) throws InfrastructureException {
		try {
			logger.debug("makePersistent ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().delete(lot);
			getHibernateTemplate().flush();
			logger.debug("makePersistent fin");
		} catch (HibernateException ex) {
			logger.error("makePersistent failed", ex);
			throw new InfrastructureException(ex);
		}
	}


	/**
	 * Retorna un llista de tots els lots d'un establiment
	 * que tenen un id que està a dins la llista que se passa com a
	 * paràmetre i que siguin de tipus <i>partida de aceite</i>.
	 * @param seleccio
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection findInfo( Object[] seleccio, Boolean valid, Boolean olivaTaula) throws InfrastructureException {
		Collection c = null;
		try {
			logger.debug("findInfo ini");
			if (seleccio != null) {
				String q ="from Lot as lot where lot.id in (:seleccio)";						
				if (valid != null && valid.booleanValue()) q += " and lot.valid = true ";
				if (valid != null && !valid.booleanValue()) q += " and lot.valid = false ";
				if (olivaTaula != null && olivaTaula.booleanValue()) q += " and lot.olivaTaula = true ";
				if (olivaTaula != null && !olivaTaula.booleanValue()) q += " and (lot.olivaTaula is null or lot.olivaTaula = false) ";
				c = getHibernateTemplate().findByNamedParam(q, "seleccio", seleccio);
				// Ordena els resltats segons l'ordre de la selecció
				ArrayList infoOrdenat = new ArrayList();
				for (int nsel = 0; nsel < seleccio.length; nsel++) {
					Long cid = (Long)seleccio[nsel];
					for (Iterator it = c.iterator(); it.hasNext();) {
						Lot info = (Lot)it.next();
						if (((Long)info.getId()).longValue() == cid.longValue()) {
							infoOrdenat.add(info);
							break;
						}
					}
				}
				c = infoOrdenat;
			}
		} catch (HibernateException ex) {
			logger.error("findInfo failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findInfo ini");
		return c;
	}
	
	
	/**
	 * Retorna un llista de tots les entrades d'oliva d'un establiment
	 * que tenen un id que està a dins la llista que se passa com a
	 * paràmetre i que siguin de tipus <i>partida oliva</i>.
	 * @param seleccio
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection findInfoOlivaCruaGranel( Object[] seleccio, Boolean valid, Boolean olivaTaula) throws InfrastructureException {
		Collection c = null;
		try {
			logger.debug("findInfo ini");
			if (seleccio != null) {
				String q ="from PartidaOliva as poliva where poliva.id in (:seleccio)";						
//				if (valid != null && valid.booleanValue()) q += " and poliva.valid = true ";
//				if (valid != null && !valid.booleanValue()) q += " and poliva.valid = false ";
//				if (olivaTaula != null && olivaTaula.booleanValue()) q += " and lot.olivaTaula = true ";
//				if (olivaTaula != null && !olivaTaula.booleanValue()) q += " and (lot.olivaTaula is null or lot.olivaTaula = false) ";
				c = getHibernateTemplate().findByNamedParam(q, "seleccio", seleccio);
				// Ordena els resltats segons l'ordre de la selecció
				ArrayList infoOrdenat = new ArrayList();
				for (int nsel = 0; nsel < seleccio.length; nsel++) {
					Long cid = (Long)seleccio[nsel];
					for (Iterator it = c.iterator(); it.hasNext();) {
						Lot info = (Lot)it.next();
						if (((Long)info.getId()).longValue() == cid.longValue()) {
							infoOrdenat.add(info);
							break;
						}
					}
				}
				c = infoOrdenat;
			}
		} catch (HibernateException ex) {
			logger.error("findInfo failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findInfo ini");
		return c;
	}
	
	
	
	
	
	
	/**
	 * Retorna un llista de tots els lots d'un establiment
	 * que tenen un id que està a dins la llista que se passa com a
	 * paràmetre i que siguin de tipus <i>partida de aceite</i>.
	 * @param seleccio
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection findInfo( Object[] seleccio, Boolean valid) throws InfrastructureException {
		Collection c = null;
		try {
			logger.debug("findInfo ini");
			if (seleccio != null) {
				String q ="from Lot as lot where lot.id in (:seleccio)";						
				if (valid != null && valid.booleanValue()) q += " and lot.valid = true ";
				if (valid != null && !valid.booleanValue()) q += " and lot.valid = false ";
				c = getHibernateTemplate().findByNamedParam(q, "seleccio", seleccio);
				// Ordena els resltats segons l'ordre de la selecció
				ArrayList infoOrdenat = new ArrayList();
				for (int nsel = 0; nsel < seleccio.length; nsel++) {
					Long cid = (Long)seleccio[nsel];
					for (Iterator it = c.iterator(); it.hasNext();) {
						Lot info = (Lot)it.next();
						if (((Long)info.getId()).longValue() == cid.longValue()) {
							infoOrdenat.add(info);
							break;
						}
					}
				}
				c = infoOrdenat;
			}
		} catch (HibernateException ex) {
			logger.error("findInfo failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findInfo ini");
		return c;
	}
	

	/**
	 * Comprova si la partida d'oli té lots associats
	 * @param lid
	 * @return
	 */
	public Boolean existeixenLotsAssociatsPartidesOli(Long idPartidaOli, Boolean valid) {
		List sortides;
		try {
			logger.debug("existeixenLotsAssociatsPartidesOli ini");
			String q = "select count(lot.id) from Lot lot " +
			"where lot.partidaOli.id = " + idPartidaOli + " ";
			if (valid != null && valid.booleanValue()) q += "and lot.valid = true ";
			if (valid != null && !valid.booleanValue()) q += "and lot.valid = false ";
			sortides = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("existeixenLotsAssociatsPartidesOli failed", ex);
			throw new InfrastructureException(ex);
		}
		if (sortides != null && sortides.size()> 0 && ((Long)sortides.get(0)).intValue() > 0) {
			logger.debug("existeixenLotsAssociatsPartidesOli fin");
			return true;
		}
		logger.debug("existeixenLotsAssociatsPartidesOli fin");
		return false;
	}
	
	/**
	 * Comprova si el producte té lots associats
	 * @param lid
	 * @return
	 */
	public Boolean existeixenLotsAssociatsProducte(Long idProducte, Boolean valid) {
		List lots;
		try {
			logger.debug("existeixenLotsAssociatsProducte ini");
			String q = "select count(lot.id) from Lot lot " +
			"where lot.producte is not null " +
			"and lot.producte.id = " + idProducte + " ";
			if (valid != null && valid.booleanValue()) q += "and lot.valid = true ";
			if (valid != null && !valid.booleanValue()) q += "and lot.valid = false ";
			lots = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("existeixenLotsAssociatsProducte failed", ex);
			throw new InfrastructureException(ex);
		}
		if (lots != null && lots.size()> 0 && ((Long)lots.get(0)).intValue() > 0) {
			logger.debug("existeixenLotsAssociatsProducte fin");
			return true;
		}
		logger.debug("existeixenLotsAssociatsProducte fin");
		return false;
	}
	
	/**
	 * Retorna Lot  per Marca
	 * @param 
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection findByMarca(Long idMarca, Boolean valid) {
		Collection col;
		try {
			logger.debug("findByMarca ini");
			String q = "from Lot as l where l.etiquetatge.marca.id = "+ idMarca;
			if (valid != null && valid.booleanValue()) q += " and l.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and l.valid = false ";
			q += " order by l.id desc";
			col = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("findByMarca failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findByMarca fin");
		return col;
	}
	
	public Collection findByLotBotelles(String lBot, Boolean valid) {
		Collection col;
		try {
			logger.debug("findByLotBotelles ini");
			String q = "from Lot as l where l.numeroLot = '"+ lBot + "'";
			if (valid != null && valid.booleanValue()) q += " and l.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and l.valid = false ";
			q += " order by l.id desc";
			col = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("findByLotBotelles failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findByLotBotelles fin");
		return col;
	}

	/**
	 * set the hibernate template.
	 * @param hibernateTemplate the hibernate spring template.
	 * @spring.property ref="hibernateTemplate"
	 *
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

	public Double getSumaContingutActual(Long[] lotsId, Date data) {
		Double botelles = 0.0;
		Collection col;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String fi = df.format(data);
		try {
			logger.debug("getSumaContingutActual ini");
//			String q = 	"select sum(lo.numeroBotellesActuals * lo.etiquetatge.tipusEnvas.volum) " +
//						"from Lot lo " +
//						"where lo.id in (:idLots) " +
//						"and lo.valid = true ";
//			col = getHibernateTemplate().findByNamedParam(q, "idLots", lotsId);
			
			String q = 	"select sum(elo.elo_botell * ten.ten_volum) " +
						"from oli_entrada_lot elo, oli_tipus_envas ten " +
						"where elo.elo_codlot 	= ANY (ARRAY[:idLots]) " +
						"and elo.elo_valid = true ";
			
			Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(q);
			query.setParameterList("idLots", lotsId);
			col = query.list();
			
		} catch (HibernateException ex) {
			logger.error("getSumaContingutActual failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getSumaContingutActual fin");
		
		if (col != null && col.size() > 0){
			botelles = ((Double)col.iterator().next());
			if (botelles == null) botelles = 0.0;
		}
		return botelles;
	}

//	public Double getQuantitatInicialLotTancament(Long idLot) {
//		Double quantitat = 0.0;
//		
//		List botelles = null;
//		try {
//			logger.debug("getQuantitatInicialLotTancament ini");
//			String q = "select elo.botelles, elo.lot.etiquetatge.tipusEnvas.volum as quantitat " +
//						"from EntradaLot elo " +
//						"where elo.valid = true " +
//						"and elo.lot.id = " + idLot + " " +
//						"and elo.esDevolucio = false ";
//			botelles = getHibernateTemplate().find(q);
//		} catch (HibernateException ex) {
//			logger.error("getQuantitatInicialLotTancament failed", ex);
//			throw new InfrastructureException(ex);
//		}
//		logger.debug("getQuantitatInicialLotTancament fin");
//		if (botelles != null && botelles.size() > 0){
//			for(Object obj : botelles){
//				Object[] bots =  (Object[])obj;
//				if (bots[0] != null && bots[1] != null)
//					quantitat = (Integer)bots[0] * (Double)bots[1];
//				break;
//			}
//		}		
//		
//		return quantitat;
//	}

}