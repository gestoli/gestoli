package es.caib.gestoli.logic.dao;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.exception.InfrastructureException;
import es.caib.gestoli.logic.model.SortidaBota;
import es.caib.gestoli.logic.model.SortidaLot;
import es.caib.gestoli.logic.util.Constants;

/**
 * Home object for domain model class SortidaBota
 * @see es.caib.gestoli.logic.model.SortidaBota
 * @author Juan Carlos García
 */
public class SortidaBotaDao {
	private static Logger logger = Logger.getLogger(SortidaBotaDao.class);
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
			String q = "from SortidaBota as sbo where 1 = 1 ";
			if (valid != null && valid.booleanValue()) q += " and sbo.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and sbo.valid = false ";
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
	public SortidaBota getById(Long id) throws InfrastructureException {
		SortidaBota sortida;
		try {
			logger.debug("getById ini");
			sortida = (SortidaBota)getHibernateTemplate().load(SortidaBota.class, id);	
		} catch (HibernateException ex) {
			logger.error("getById failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getById fin");
		return sortida;
	}

	
	/**
	 * Recupera el SortidaBota por la traza 
	 * @return
	 * @throws InfrastructureException
	 */
	public SortidaBota findByTraza(Long idTraza, Boolean valid) throws InfrastructureException {
		List col;
		SortidaBota sortidaBota = null;
		try {
			logger.debug("findByTraza ini");
			String q = "from SortidaBota as sb where sb.traza.id = " + idTraza;
			if (valid != null && valid.booleanValue()) q += " and sb.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and sb.valid = false ";
			col = getHibernateTemplate().find(q);
			if(col!= null && col.size()>0){
				sortidaBota = (SortidaBota)col.get(0);
			}
		} catch (HibernateException ex) {
			logger.error("findByTraza failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findByTraza fin");
		return sortidaBota;
	}

	/**
	 * Devuelve la ultima SortidaBota de un deposito
	 * @param 
	 * @return
	 * @throws InfrastructureException
	 */
	public SortidaBota findUltimaByBota(Long dipId, Boolean valid) throws InfrastructureException {
		List entradas;
		try {
			logger.debug("findUltimaByBota ini");
			String q = "from SortidaBota sbo where " +
			"sbo.botaBySboCodbor.id = " + dipId + " ";
			if (valid != null && valid.booleanValue()) q += " and sbo.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and sbo.valid = false ";
			q += "order by sbo.data desc, sbo.id desc";
			entradas = getHibernateTemplate().find(q);
			if (entradas.size() > 0) {
				logger.debug("findUltimaByBota fin");
				return (SortidaBota) entradas.get(0);
			}
		} catch (HibernateException ex) {
			logger.error("findUltimaByBota failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findUltimaByBota fin");
		return null;
	}
	
	/**
	 * Devuelve la següent SortidaBota de un deposito
	 * @param 
	 * @return
	 * @throws InfrastructureException
	 */
	public SortidaBota findSeguentByTrazaBota(Long dipId, Long trazaId) throws InfrastructureException {
		List entradas;
		try {
			logger.debug("findSeguentByTrazaBota ini");
			String q = "from SortidaBota sbo where " +
			" sbo.botaBySboCodbor.id = " + dipId +
			" and sbo.valid = true" +
			" and sbo.id = (select min(id) from SortidaBota where traza.id > " + trazaId + " and botaBySboCodbor.id = " + dipId + ")" +
			" order by sbo.data desc, sbo.id desc";
			entradas = getHibernateTemplate().find(q);
			if (entradas.size() > 0) {
				logger.debug("findSeguentByTrazaBota fin");
				return (SortidaBota) entradas.get(0);
			}
		} catch (HibernateException ex) {
			logger.error("findSeguentByTrazaBota failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findSeguentByTrazaBota fin");
		return null;
	}
	
	/**
	 * Devuelve la anterior SortidaBota de un deposito
	 * @param 
	 * @return
	 * @throws InfrastructureException
	 */
	public SortidaBota findAnteriorByTrazaBota(Long dipId, Long trazaId) throws InfrastructureException {
		List entradas;
		try {
			logger.debug("findAnteriorByTrazaBota ini");
			String q = "from SortidaBota sbo where " +
			" sbo.botaBySboCodbor.id = " + dipId +
			" and sbo.valid = true" +
			" and sbo.id = (select max(id) from SortidaBota where traza.id < " + trazaId + " and botaBySboCodbor.id = " + dipId + ")" +
			" order by sbo.data asc, sbo.id asc";
			entradas = getHibernateTemplate().find(q);
			if (entradas.size() > 0) {
				logger.debug("findAnteriorByTrazaBota fin");
				return (SortidaBota) entradas.get(0);
			}
		} catch (HibernateException ex) {
			logger.error("findAnteriorByTrazaBota failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findAnteriorByTrazaBota fin");
		return null;
	}
	
	/**
	 * Actualiza un registro
	 * @param sortida
	 * @throws InfrastructureException
	 */
	public void makePersistent(SortidaBota sortida) throws InfrastructureException {
		try {
			logger.debug("makePersistent ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().saveOrUpdate(sortida);
			getHibernateTemplate().flush();
			logger.debug("makePersistent fin");
		} catch (HibernateException ex) {
			logger.error("makePersistent failed", ex);
			throw new InfrastructureException(ex);
		}
	}


	/**
	 * Actualiza un registro
	 * @param sortida
	 * @throws InfrastructureException
	 */
	public void makePersistent(SortidaBota sortida, Session session) throws InfrastructureException {
		try {
			logger.debug("makePersistent ini");
			session.setFlushMode(FlushMode.ALWAYS);
			session.saveOrUpdate(sortida);
			session.flush();
			logger.debug("makePersistent fin");
		} catch (HibernateException ex) {
			logger.error("makePersistent failed", ex);
			throw new InfrastructureException(ex);
		}
	}


	/**
	 * Borra un registro
	 * @param sortida
	 * @throws InfrastructureException
	 */
	public void makeTransient(SortidaBota sortida)
	throws InfrastructureException {
		try {
			logger.debug("makeTransient ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().delete(sortida);
			getHibernateTemplate().flush();
		} catch (HibernateException ex) {
			logger.error("makeTransient failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("makeTransient fin");
	}


	/**
	 * Devuelve los depositos que han salido de un establecimiento entre 2 fechas dadas
	 * @param fi
	 * @param ff
	 * @param idEst
	 * @return
	 */
	public Collection getSortidesBotaEntreFechasAndEstablecimiento(Date finicio, Date ffin, Long idEst, Boolean valid){
		
		Collection col;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String fi = df.format(finicio);
		String ff = df.format(ffin);

		try {
			logger.debug("getSortidesBotaEntreFechasAndEstablecimiento inicio");
			String q = "from SortidaBota sorbot where " +
			"sorbot.data >= '"+fi+"' and sorbot.data <= '"+ff+"' and sorbot.establiment.id = "+ idEst + " " +
			"and sorbot.kgOliva > 0";
			if (valid != null && valid.booleanValue()) q += " and sorbot.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and sorbot.valid = false ";
			q += " order by sorbot.data, sorbot.id";
			col = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("getSortidesBotaEntreFechasAndEstablecimiento failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getSortidesBotaEntreFechasAndEstablecimiento fin");
		return col;
		
	}
	
	
	/**
	 * Devuelve los depositos que han salido de un establecimiento entre 2 fechas dadas
	 * @param fi
	 * @param ff
	 * @param idEst
	 * @return
	 */
	public Collection getSortidesBotaEntreFechasAndBota(Date finicio, Date ffin, Long idDip, Boolean valid){
		Collection col;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String fi = df.format(finicio);
		String ff = df.format(ffin);
	
		try {
			logger.debug("getSortidesBotaEntreFechasAndBota inicio");
			String q = "from SortidaBota sorbot where " +
			"sorbot.data >= '"+fi+"' and sorbot.data <= '"+ff+"' " +
			" and sorbot.botaBySboCodbor.id = "+ idDip +
			" and sorbot.kgOliva > 0";
			if (valid != null && valid.booleanValue()) q += " and sorbot.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and sorbot.valid = false ";
			q += " order by sorbot.data, sorbot.id";
			col = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("getSortidesBotaEntreFechasAndBota failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getSortidesBotaEntreFechasAndBota fin");
		return col;
	}
	
	
	/**
	 * Devuelve los depositos que han salido de un establecimiento entre 2 fechas dadas
	 * @param fi
	 * @param ff
	 * @param idEst
	 * @return
	 */
	public Collection findByEstablecimiento(Long idEst, Boolean valid){
		
		Collection col;
		
		try {
			logger.debug("findByEstablecimiento inicio");
			String q = "from SortidaBota sorbot where " +
			"sorbot.establiment.id = "+ idEst;
			if (valid != null && valid.booleanValue()) q += " and sorbot.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and sorbot.valid = false ";
			q += "order by sorbot.id";
			col = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("findByEstablecimiento failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findByEstablecimiento fin");
		return col;
		
	}
	
	/**
	 * Devuelve las salidas de un deposito
	 * @param fi
	 * @param ff
	 * @param idEst
	 * @return
	 */
	public Collection findSortidaDiposiOliByBota(Long dipositId, Boolean valid){
		
		Collection col;
		
		try {
			logger.debug("findSortidaDiposiOliByBota inicio");
			String q = "from SortidaBota sorbot where " +
			"sorbot.botaBySboCodbor.id = "+ dipositId;
			if (valid != null && valid.booleanValue()) q += " and sorbot.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and sorbot.valid = false ";
			q += " order by sorbot.data asc";
			col = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("findSortidaDiposiOliByBota failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findSortidaDiposiOliByBota fin");
		return col;
		
	}
	

	/**
	 * Retorna les sortides d'oli efectuades en un dipòsit dins a una data donada
	 * @param data
	 * @param idBota
	 * @param valid
	 * @return
	 */
	public Double getSumaSortidesBotaPerBotaFinsData(Long idBota, Date data, Boolean valid){
		
		Collection col;
		Double suma = 0.0;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String fi = df.format(data);

		try {
			logger.debug("getSumaSortidesBotaPerBotaFinsData inicio");
			String q = "select sum(sorbot.kgOliva) from SortidaBota sorbot where " +
			"sorbot.data <= '"+fi+"' and sorbot.botaBySboCodbor.id = "+ idBota;
			if (valid != null && valid.booleanValue()) q += " and sorbot.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and sorbot.valid = false ";
			col = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("getSumaSortidesBotaPerBotaFinsData failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getSumaSortidesBotaPerBotaFinsData fin");
		if (col != null && col.size() > 0){
			suma = (Double)col.iterator().next();
			if (suma == null) suma = 0.0;
		}
		return suma;
		
	}
	
	
	/**
	 * Retorna les sortides d'oli efectuades en un dipòsit dins a una data donada
	 * @param data
	 * @param idBota
	 * @param valid
	 * @return
	 */
	public Double getSumaSortidesBotasFinsData(Long[] idBotas, Date data, Boolean valid){
		
		Collection col;
		Double suma = 0.0;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String fi = df.format(data);

		try {
			logger.debug("getSumaSortidesBotasFinsData inicio");
			String q = "select sum(sorbot.kgOliva) from SortidaBota sorbot where " +
			"sorbot.data <= '"+fi+"' and sorbot.botaBySboCodbor.id in (:idBotas)";
			if (valid != null && valid.booleanValue()) q += " and sorbot.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and sorbot.valid = false ";
			col = getHibernateTemplate().findByNamedParam(q, "idBotas", idBotas);
		} catch (HibernateException ex) {
			logger.error("getSumaSortidesBotasFinsData failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getSumaSortidesBotasFinsData fin");
		if (col != null && col.size() > 0){
			suma = (Double)col.iterator().next();
			if (suma == null) suma = 0.0;
		}
		return suma;
		
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

}