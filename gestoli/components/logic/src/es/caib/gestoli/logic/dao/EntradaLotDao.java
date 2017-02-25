package es.caib.gestoli.logic.dao;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.exception.InfrastructureException;
import es.caib.gestoli.logic.model.EntradaLot;
import es.caib.gestoli.logic.model.Lot;


/**
 * Home object for domain model class EntradaLot
 * @see es.caib.gestoli.logic.model.EntradaLot
 * @author Juan Carlos García
 */
public class EntradaLotDao {
	private static Logger logger = Logger.getLogger(EntradaLotDao.class);
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
			String q = "from EntradaLot as elot where 1 = 1 ";
			if (valid != null && valid.booleanValue()) q += " and elot.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and elot.valid = false ";
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
	public EntradaLot getById(Long id) throws InfrastructureException {
		EntradaLot entrada;
		try {
			logger.debug("getById ini");
			entrada = (EntradaLot)getHibernateTemplate().load(EntradaLot.class, id);
		} catch (HibernateException ex) {
			logger.error("getById failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getById fin");
		return entrada;
	}

	/**
	 * Devuelve la ultima entrada de lote por lote
	 * @param 
	 * @return
	 * @throws InfrastructureException
	 */
	public EntradaLot findUltimaByLot(Long lotId, Boolean valid) throws InfrastructureException {
		List entradas;
		try {
			logger.debug("findUltimaByLot ini");
			String q = "from EntradaLot elo where " +
			"elo.lot.id = "+ lotId;
			if (valid != null && valid.booleanValue()) q += " and elo.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and elo.valid = false ";
			q += " and (elo.esDevolucio is null or elo.esDevolucio = false)";
			q += "order by elo.data desc, elo.id desc";
			entradas = getHibernateTemplate().find(q);
			if (entradas.size() > 0) {
				logger.debug("findUltimaByLot fin");
				return (EntradaLot) entradas.get(0);
			}
		} catch (HibernateException ex) {
			logger.error("findUltimaByLot failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findUltimaByLot fin");
		return null;
	}
	
	public EntradaLot findUltimaByLotInclouDevolucio(Long lotId, Boolean valid) throws InfrastructureException {
		List entradas;
		try {
			logger.debug("findUltimaByLot ini");
			String q = "from EntradaLot elo where " +
			"elo.lot.id = "+ lotId;
			if (valid != null && valid.booleanValue()) q += " and elo.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and elo.valid = false ";
			q += "order by elo.data desc, elo.id desc";
			entradas = getHibernateTemplate().find(q);
			if (entradas.size() > 0) {
				logger.debug("findUltimaByLot fin");
				return (EntradaLot) entradas.get(0);
			}
		} catch (HibernateException ex) {
			logger.error("findUltimaByLot failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findUltimaByLot fin");
		return null;
	}
	
	/**
	 * Recupera el EntradaLot por la traza 
	 * @return
	 * @throws InfrastructureException
	 */
	public EntradaLot findByTraza(Long idTraza, Boolean valid) throws InfrastructureException {
		List col;
		EntradaLot entradaLot = null;
		try {
			logger.debug("findByTraza ini");
			String q = "from EntradaLot as elo where elo.traza.id="+idTraza;
			if (valid != null && valid.booleanValue()) q += " and elo.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and elo.valid = false ";
			col = getHibernateTemplate().find(q);
			if(col!= null && col.size()>0){
				entradaLot = (EntradaLot)col.get(0);
			}
		} catch (HibernateException ex) {
			logger.error("findByTraza failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findByTraza fin");
		return entradaLot;
	}

	


	/**
	 * Devuelve EntradaLot  por establecimiento
	 * @param 
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection findByEstablecimiento(Long estId, Boolean valid) throws InfrastructureException {
		Collection col;
		try {
			logger.debug("findByEstablecimiento ini");
			String q = "from EntradaLot el where " +
			"el.zona.establiment.id = "+ estId ;
			if (valid != null && valid.booleanValue()) q += " and el.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and el.valid = false ";
			q += "order by el.id";
			col = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("findByEstablecimiento failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findByEstablecimiento fin");
		return col;
	}
	
	/**
	 * Devuelve EntradaLot  por establecimiento entre dates
	 * @param 
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection findByEstablecimientoEntreDates(Long estId, Boolean valid, Date dataInici, Date dataFi){
		Collection col;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String di = sdf.format(dataInici);
			String df = sdf.format(dataFi);
			
			logger.debug("findByEstablecimientoEntreDates ini");
			String q = "from EntradaLot el where " +
			"el.data >= '"+ di +"' and el.data <= '"+ df +"' and " +
			"el.zona.establiment.id = "+ estId ;
			if (valid != null && valid.booleanValue()) q += " and el.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and el.valid = false ";
			q += "order by el.id";
			col = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("findByEstablecimientoEntreDates failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findByEstablecimientoEntreDates fin");
		return col;
	}

	/**
	 * Devuelve EntradaLot  por establecimiento entre dates
	 * @param 
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection findDevolucioByEstablecimientoEntreDates(Long estId, Boolean valid, Date dataInici, Date dataFi){
		Collection col;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String di = sdf.format(dataInici);
			String df = sdf.format(dataFi);
			
			logger.debug("findDevolucioByEstablecimientoEntreDates ini");
			String q = "from EntradaLot el where " +
			"el.data >= '"+ di +"' and el.data <= '"+ df +"' and " +
			"el.zona.establiment.id = "+ estId + " and " +
			"el.esDevolucio = true";
			if (valid != null && valid.booleanValue()) q += " and el.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and el.valid = false ";
			q += "order by el.id";
			col = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("findDevolucioByEstablecimientoEntreDates failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findDevolucioByEstablecimientoEntreDates fin");
		return col;
	}
	
	/**
	 * Devuelve EntradaLot  por establecimiento
	 * @param 
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection findByEstablecimientoNoDevolucio(Long estId, Boolean valid) throws InfrastructureException {
		Collection col;
		try {
			logger.debug("findByEstablecimientoNoDevolucio ini");
			String q = "from EntradaLot el where " +
			"el.zona.establiment.id = "+ estId + 
			" and (el.esDevolucio is null or el.esDevolucio = false)";
			if (valid != null && valid.booleanValue()) q += " and el.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and el.valid = false ";
			q += "order by el.id";
			col = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("findByEstablecimientoNoDevolucio failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findByEstablecimientoNoDevolucio fin");
		return col;
	}
	
	/**
	 * Devuelve EntradaLot  por establecimiento entre dates
	 * @param 
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection findByEstablecimientoNoDevolucioEntreDates(Date dataInici, Date dataFi, Long estId, Boolean valid) throws InfrastructureException {
		Collection col;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String di = sdf.format(dataInici);
			String df = sdf.format(dataFi);
			
			logger.debug("findByEstablecimientoNoDevolucioEntreDates ini");
			String q = "from EntradaLot el where " +
			"el.data >= '"+ di +"' and el.data <= '"+ df +"' and " +
			"el.zona.establiment.id = "+ estId + 
			" and (el.esDevolucio is null or el.esDevolucio = false)";
			if (valid != null && valid.booleanValue()) q += " and el.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and el.valid = false ";
			q += "order by el.id";
			col = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("findByEstablecimientoNoDevolucioEntreDates failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findByEstablecimientoNoDevolucioEntreDates fin");
		return col;
	}
	
	/**
	 * Devuelve EntradaLot  por lote
	 * @param 
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection findByLot(Long lotId, Boolean valid) throws InfrastructureException {
		Collection col;
		try {
			logger.debug("findByLot ini");
			String q = "from EntradaLot as el where el.lot.id = "+ lotId;
			if (valid != null && valid.booleanValue()) q += " and el.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and el.valid = false ";
			q += " order by el.id desc";
			col = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("findByLot failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findByLot fin");
		return col;
	}
	
	/**
	 * Devuelve EntradaLot  por lote
	 * @param 
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection findByLotZona(Long lotId, Long zonaId, Boolean valid) throws InfrastructureException {
		Collection col;
		try {
			logger.debug("findByLotZona ini");
			String q = 	"from EntradaLot as el where el.lot.id = "+ lotId +
						" and el.zona.id = "+ zonaId;
			if (valid != null && valid.booleanValue()) q += " and el.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and el.valid = false ";
			q += " order by el.id desc";
			col = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("findByLotZona failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findByLotZona fin");
		return col;
	}
	
	/**
	 * Retorna EntradaLot  per Marca
	 * @param 
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection findByMarca(Long idMarca, Boolean valid) {
		Collection col;
		try {
			logger.debug("findByMarca ini");
			String q = "from EntradaLot as el where el.lot.etiquetatge.marca.id = "+ idMarca;
			if (valid != null && valid.booleanValue()) q += " and el.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and el.valid = false ";
			q += " order by el.id desc";
			col = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("findByMarca failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findByMarca fin");
		return col;
	}
	
	/**
	 * Retorna EntradaLot  per lot de botella buida
	 * @param 
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection findByLotbotellaBuida(String lot, Boolean valid) {
		Collection col;
		try {
			logger.debug("findByLotbotellaBuida ini");
			String q = "from EntradaLot as el where el.lot.numeroLot = '" + lot + "'";
			if (valid != null && valid.booleanValue()) q += " and el.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and el.valid = false ";
			q += " order by el.id desc";
			col = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("findByLotbotellaBuida failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findByLotbotellaBuida fin");
		return col;
	}
	
	/**
	 * Devuelve EntradaLot  por lotes
	 * @param 
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection findByLots(Long[] lotsId, Boolean valid) throws InfrastructureException {
		Collection col;
		try {
			logger.debug("findByLots ini");
			String q = "from EntradaLot as el where el.lot.id in (:lotsId)";
			if (valid != null && valid.booleanValue()) q += " and el.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and el.valid = false ";
			q += " order by el.id desc";
			col = getHibernateTemplate().findByNamedParam(q, "lotsId", lotsId);
		} catch (HibernateException ex) {
			logger.error("findByLots failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findByLots fin");
		return col;
	}
	
	/**
	 * Devuelve los lotes que han entrado en un establecimiento entre 2 fechas dadas
	 * @param fi
	 * @param ff
	 * @param idEst
	 * @return
	 */
	public Collection getEntradasLotesEntreFechasAndEstablecimiento(Date finicio, Date ffin, Long idEst, Boolean valid, String entrada){
		
		Collection col;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String fi = df.format(finicio);
		String ff = df.format(ffin);

		try {
			logger.debug("getEntradasLotesEntreFechasAndEstablecimiento inicio");

			String q = "from EntradaLot entlot where " +
			"entlot.data >= '"+fi+"' and entlot.data <= '"+ff+"' and entlot.zona.establiment.idOriginal in (select est.idOriginal from Establiment est where est.id = " +idEst+ ") ";
			q += " and entlot.dipositProcedencia != '"+entrada +"' ";
			if (valid != null && valid.booleanValue()) q += " and el.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and el.valid = false ";
			q += "order by entlot.data";
			
			col = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("getEntradasLotesEntreFechasAndEstablecimiento failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getEntradasLotesEntreFechasAndEstablecimiento fin");
		return col;
		
	}
	
	/**
	 * Retorna true si existen entradas de lotes asociadas a zonas
	 * 
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public boolean existenEntradasLoteAsociadasZonas(Long idZona, Boolean valid) throws InfrastructureException {
		List entrada;
		try {
			logger.debug("existenEntradasLoteAsociadasZonas ini");
			String q = "select count(el.id) from EntradaLot el " +
			"where el.zona.id = " + idZona + " ";
			if (valid != null && valid.booleanValue()) q += " and el.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and el.valid = false ";
			entrada = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("existenEntradasLoteAsociadasZonas failed", ex);
			throw new InfrastructureException(ex);
		}
		if (entrada != null && entrada.size()> 0 && ((Long)entrada.get(0)).intValue() > 0) {
			logger.debug("existenEntradasLoteAsociadasZonas fin");
			return true;
		}
		logger.debug("existenEntradasLoteAsociadasZonas fin");
		return false;
	}
	
	
	/**
	 * Retorna true si existen entradas de lotes asociadas al lot
	 * 
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public boolean existeixenEntradesLotAssociadesLot(Long idLot, Boolean valid) {
		List entrada;
		try {
			logger.debug("existeixnEntradesLotAssociadesLot ini");
			String q = "select count(el.id) from EntradaLot el " +
			"where el.lot.id = " + idLot + " ";
			if (valid != null && valid.booleanValue()) q += " and el.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and el.valid = false ";
			entrada = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("existeixnEntradesLotAssociadesLot failed", ex);
			throw new InfrastructureException(ex);
		}
		if (entrada != null && entrada.size()> 0 && ((Long)entrada.get(0)).intValue() > 0) {
			logger.debug("existeixnEntradesLotAssociadesLot fin");
			return true;
		}
		logger.debug("existeixnEntradesLotAssociadesLot fin");
		return false;
	}
	
	/**
	 * Actualiza un registro
	 * @param entrada
	 * @throws InfrastructureException
	 */
	public void makePersistent(EntradaLot entrada) throws InfrastructureException {
		try {
			logger.debug("makePersistent ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().saveOrUpdate(entrada);
			getHibernateTemplate().flush();
			logger.debug("makePersistent fin");
		} catch (HibernateException ex) {
			logger.error("makePersistent failed", ex);
			throw new InfrastructureException(ex);
		}
	}

	
	/**
	 * Actualiza un registro
	 * @param entrada
	 * @param session
	 * @throws InfrastructureException
	 */
	public void makePersistent(EntradaLot entrada, Session session) throws InfrastructureException {
		try {
			logger.debug("makePersistent ini");
			session.setFlushMode(FlushMode.ALWAYS);
			session.saveOrUpdate(entrada);
			session.flush();
			logger.debug("makePersistent fin");
		} catch (HibernateException ex) {
			logger.error("makePersistent failed", ex);
			throw new InfrastructureException(ex);
		}
	}


	/**
	 * Borra un registro
	 * @param entrada
	 * @throws InfrastructureException
	 */
	public void makeTransient(EntradaLot entrada)
	throws InfrastructureException {
		try {
			logger.debug("makeTransient ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().delete(entrada);
			getHibernateTemplate().flush();
			logger.debug("makeTransient fin");
		} catch (HibernateException ex) {
			logger.error("makeTransient failed", ex);
			throw new InfrastructureException(ex);
		}
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


	/**
	 * Retorna suma de devolucions d'un lot donat, fins a una data concreta
	 * @param 
	 * @return
	 * @throws InfrastructureException
	 */
	public Integer getSumaDevolucionsLotFinsData(Long idLot, Date data, Boolean valid) {
		Long botelles = 0L;
		Collection col;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String fi = df.format(data);
		try {
			logger.debug("getSumaDevolucionsLotFinsData ini");
			String q = 	" select sum(elo.botelles) " +
						"from EntradaLot elo " +
						"where elo.lot.id = " + idLot + " " +
						"and elo.data > '" + fi + "' " +
						"and elo.esDevolucio = true";
			if (valid != null && valid.booleanValue()) q += " and elo.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and elo.valid = false ";
			
			col = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("getSumaDevolucionsLotFinsData failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getSumaDevolucionsLotFinsData fin");
		
		if (col != null && col.size() > 0){
			botelles = ((Long)col.iterator().next());
			if (botelles == null) botelles = 0L;
		}
		return botelles.intValue();
	}
	
	/**
	 * Retorna suma de devolucions d'un lot donat, fins a una data concreta
	 * @param 
	 * @return
	 * @throws InfrastructureException
	 */
	public Double getSumaDevolucionsLotsFinsData(Long[] idLots, Date data, Boolean valid) {
		Double suma = 0.0;
		Collection col;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String fi = df.format(data);
		try {
			logger.debug("getSumaDevolucionsLotsFinsData ini");
			String q = 	" select sum(elo.botelles * elo.lot.etiquetatge.tipusEnvas.volum) " +
						"from EntradaLot elo " +
						"where elo.lot.id in (:idLots) " +
						"and elo.data > '" + fi + "' " +
						"and elo.esDevolucio = true";
			if (valid != null && valid.booleanValue()) q += " and elo.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and elo.valid = false ";
			
			col = getHibernateTemplate().findByNamedParam(q, "idLots", idLots);
		} catch (HibernateException ex) {
			logger.error("getSumaDevolucionsLotsFinsData failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getSumaDevolucionsLotsFinsData fin");
		
		if (col != null && col.size() > 0){
			suma = ((Double)col.iterator().next());
			if (suma == null) suma = 0.0;
		}
		return suma;
	}
	/**
	 * Retorna devolucions d'un lot donat, fins a una data concreta
	 * @param 
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection getDevolucionsLotsEstablimentFinsData(Long estId, Date data, Boolean valid) {
		Collection col;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String fi = df.format(data);
		try {
			logger.debug("getDevolucionsLotsEstablimentFinsData ini");
			String q = 	"from EntradaLot elo " +
						"where elo.data > '" + fi + "' " +
						" and elo.esDevolucio = true ";
			if (estId != null) q += " and elo.zona.establiment.id = " +estId+ " ";
			if (valid != null && valid.booleanValue()) q += " and elo.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and elo.valid = false ";
			
			col = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("getDevolucionsLotsEstablimentFinsData failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getDevolucionsLotsEstablimentFinsData fin");
		
		return col;
	}
	
	/**
	 * Retorna devolucions d'un lot donat, fins a una data concreta
	 * @param 
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection getDevolucionsLotsEstablimentEntreDates(Long estId, Date dataInici, Date dataFi, Boolean valid) {
		Collection col;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String fi = df.format(dataInici);
		String ff = df.format(dataFi);
		try {
			logger.debug("getDevolucionsLotsEstablimentEntreDates ini");
			String q = 	"from EntradaLot elo " +
						"where elo.data >= '" + fi + "' " +
						" and elo.data <= '" + ff + "' " +
						" and elo.esDevolucio = true ";
			if (estId != null) q += " and elo.zona.establiment.id = " +estId+ " ";
			if (valid != null && valid.booleanValue()) q += " and elo.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and elo.valid = false ";
			
			col = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("getDevolucionsLotsEstablimentEntreDates failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getDevolucionsLotsEstablimentEntreDates fin");
		
		return col;
	}
	
	/**
	 * Retorna devolucions d'un lot donat, fins a una data concreta
	 * @param 
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection getDevolucionsLotsCategoriaEstablimentFinsData(Long estId, Date data, Long categoriaId, Boolean valid) {
		Collection col;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String fi = df.format(data);
		try {
			logger.debug("getDevolucionsLotsEstablimentFinsData ini");
			String q = 	"select elo.botelles, elo.lot.etiquetatge.tipusEnvas.volum " +
						"from EntradaLot elo " +
						"where elo.data > '" + fi + "' " +
						" and elo.esDevolucio = true " +
						" and elo.lot.partidaOli.categoriaOli.id = " + categoriaId;
			if (estId != null) q += " and elo.zona.establiment.id = " +estId+ " ";
			if (valid != null && valid.booleanValue()) q += " and elo.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and elo.valid = false ";
			
			col = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("getDevolucionsLotsEstablimentFinsData failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getDevolucionsLotsEstablimentFinsData fin");
		
		return col;
	}
	
//	/**
//	 * Retorna suma de devolucions d'un lot donat, fins a una data concreta
//	 * @param 
//	 * @return
//	 * @throws InfrastructureException
//	 */
//	public Double getSumaDevolucionsLotsFinsData(Long[] idLots, Date data, Boolean valid) {
//		Double botelles = 0.0;
//		Collection col;
//		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
//		String fi = df.format(data);
//		try {
//			logger.debug("getSumaDevolucionsLotsFinsData ini");
//			String q = 	" select sum(elo.botelles * elo.lot.etiquetatge.tipusEnvas.volum) " +
//						"from EntradaLot elo " +
//						"where elo.lot.id in (:idLots) " +
//						"and elo.data > '" + fi + "'";
//			if (valid != null && valid.booleanValue()) q += " and elo.valid = true ";
//			if (valid != null && !valid.booleanValue()) q += " and elo.valid = false ";
//			
//			col = getHibernateTemplate().findByNamedParam(q, "idLots", idLots);
//		} catch (HibernateException ex) {
//			logger.error("getSumaDevolucionsLotsFinsData failed", ex);
//			throw new InfrastructureException(ex);
//		}
//		logger.debug("getSumaDevolucionsLotsFinsData fin");
//		
//		if (col != null && col.size() > 0){
//			botelles = ((Double)col.iterator().next());
//			if (botelles == null) botelles = 0.0;
//		}
//		return botelles;
//	}

}
