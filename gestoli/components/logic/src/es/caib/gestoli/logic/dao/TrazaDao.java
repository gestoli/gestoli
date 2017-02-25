package es.caib.gestoli.logic.dao;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.exception.InfrastructureException;
import es.caib.gestoli.logic.model.Traza;


/**
 * Home object for domain model class TrazaDao.java
 * @see es.caib.gestoli.logic.model.Traza
 * 
 */
public class TrazaDao {
	private static Logger logger = Logger.getLogger(TrazaDao.class);
	private HibernateTemplate hibernateTemplate;


	/**
	 * Recupera todos los registros
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection findAll() throws InfrastructureException {
		Collection col;
		try {
			logger.debug("findAll ini");
			col = getHibernateTemplate().find("from Traza as tra order by tra.id");			
		} catch (HibernateException ex) {
			logger.error("findAll failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findAll fin");
		return col;
	}

	/**
	 * Retorna un llista de totes les trazas associades amb
	 * una tipus determinado.
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public List findByTipus(Long tipusId) throws InfrastructureException {
		List diposits;
		try {
			logger.debug("findByTipus ini");
			String q = "from Traza tra " +
			"where tra.tipus = " + tipusId + " " +
			"order by tra.id";
			diposits = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("findByTipus ini",ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findByTipus fin");
		return diposits;
	}
	
	/**
	 * Retorna un llista de totes les trazas associades amb
	 * una tipus determinado.
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public List procesEntradaOliva(Long establimentId) throws InfrastructureException {
		List processos;
		try {
			logger.debug("procesEntradaOliva ini");
			String q = "from Traza as tra " +
			"where tra.id in " + 
			"(select pao.traza.id from PartidaOliva as pao " +
			"where pao.zona.establiment.id = " + establimentId + ")";
			processos = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("procesEntradaOliva ini",ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("procesEntradaOliva fin");
		return processos;
	}
	
	/**
	 * Actualiza un registro
	 * @param Traza
	 * @throws InfrastructureException
	 */
	public void makePersistent(Traza traza) throws InfrastructureException {
		try {
			logger.debug("makePersistent ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			if(traza.getId()!= null){
				getHibernateTemplate().saveOrUpdate(traza);
			}else{
				getHibernateTemplate().save(traza);
			}			
			getHibernateTemplate().flush();
			logger.debug("makePersistent fin");
		} catch (HibernateException ex) {
			logger.error("makePersistent failed", ex);
			throw new InfrastructureException(ex);
		}
	}


	/**
	 * Actualiza un registro
	 * @param Traza
	 * @param session
	 * @throws InfrastructureException
	 */
	public void makePersistent(Traza traza, Session session) throws InfrastructureException {
		try {
			session.setFlushMode(FlushMode.ALWAYS);
			logger.debug("makePersistent ini");
			if(traza.getId()!= null){
				session.saveOrUpdate(traza);
			}else{
				session.save(traza);
			}
			session.flush();
			logger.debug("makePersistent fin");
		} catch (HibernateException ex) {
			logger.error("makePersistent failed", ex);
			throw new InfrastructureException(ex);
		}
	}


	/**
	 * Borra un registro
	 * @param traza
	 * @throws InfrastructureException
	 */
	public void makeTransient(Traza traza) throws InfrastructureException {
		try {
			logger.debug("makeTransient ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().delete(traza);
			getHibernateTemplate().flush();
			logger.debug("makeTransient fin");
		} catch (HibernateException ex) {
			logger.error("makeTransient failed",ex);
			throw new InfrastructureException(ex);
		}
	}


	/**
	 * Borra un registro
	 * @param Traza
	 * @throws InfrastructureException
	 */
//	public void makeTransient(Traza traza, Session session) throws InfrastructureException {
//		try {
//			session.delete(traza);
//		} catch (HibernateException ex) {
//			throw new InfrastructureException(ex);
//		}
//	}


	/**
	 * Devuelve el objeto que tiene el id
	 * @param id
	 * @return
	 * @throws InfrastructureException
	 */
	public Traza getById(Long id) throws InfrastructureException {
		Traza traza;
		try {
			logger.debug("getById ini");
			traza = (Traza)getHibernateTemplate().load(Traza.class, id);			
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		logger.debug("getById fin");
		return traza;
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