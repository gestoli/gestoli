package es.caib.gestoli.logic.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.exception.InfrastructureException;
import es.caib.gestoli.logic.model.QualitatAPPCCPlaVerificacio;

public class QualitatAPPCCPlaVerificacioDao {

	private static Logger logger = Logger.getLogger(QualitatAPPCCPlaVerificacioDao.class);
	private HibernateTemplate hibernateTemplate;
	
	/**
	 * Actualiza un registro
	 * @param qualitatAPPCCPlaVerificacio
	 * @throws InfrastructureException
	 */
	public void makePersistent(QualitatAPPCCPlaVerificacio qualitatAPPCCPlaVerificacio) throws InfrastructureException {
		try {
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().saveOrUpdate(qualitatAPPCCPlaVerificacio);
			getHibernateTemplate().flush();
		} catch (HibernateException ex) {
			logger.error("makePersistent failed", ex);
			throw new InfrastructureException(ex);
		}
	}
	
	
	/**
	 * QualitatAPPCCPlaVerificacio amb la id indicada.
	 * 
	 * @param id
	 */
	public QualitatAPPCCPlaVerificacio getById(Long id) throws InfrastructureException {
		QualitatAPPCCPlaVerificacio qualitatAPPCCPlaVerificacio = null;
		try {
			qualitatAPPCCPlaVerificacio = (QualitatAPPCCPlaVerificacio)getHibernateTemplate().load(QualitatAPPCCPlaVerificacio.class, id);
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		return qualitatAPPCCPlaVerificacio;
	}

	/**
	 * Retorna un llista de totes les etapes d'APPCC associats a un establiment.
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public List findByEstabliment(Long establimentId) throws InfrastructureException {
		List etapes;
		try {
			logger.debug("findByEstabliment ini");
			String q = "select form from QualitatAPPCCPlaVerificacio form " +
			"where form.establiment.id = " + establimentId + " " +
			"order by form.id";
			etapes = getHibernateTemplate().find(q);
			logger.debug("findByEstabliment ini");
		} catch (HibernateException ex) {
			logger.error("findByEstabliment failed", ex);
			throw new InfrastructureException(ex);
		}
		return etapes;
	}
	
	
	/**
	 * Borra un registro
	 * @param QualitatAPPCCPlaVerificacio
	 * @throws InfrastructureException
	 */
	public void makeTransient(QualitatAPPCCPlaVerificacio appccPlaVerificacio)
	throws InfrastructureException {
		try {
			logger.debug("makeTransient ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().delete(appccPlaVerificacio);
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
	
}
