package es.caib.gestoli.logic.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.exception.InfrastructureException;
import es.caib.gestoli.logic.model.QualitatAPPCCEtapa;

public class QualitatAPPCCEtapaDao {

	private static Logger logger = Logger.getLogger(QualitatAPPCCEtapaDao.class);
	private HibernateTemplate hibernateTemplate;
	
	/**
	 * Actualiza un registro
	 * @param qualitatAPPCCEtapa
	 * @throws InfrastructureException
	 */
	public void makePersistent(QualitatAPPCCEtapa qualitatAPPCCEtapa) throws InfrastructureException {
		try {
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().saveOrUpdate(qualitatAPPCCEtapa);
			getHibernateTemplate().flush();
		} catch (HibernateException ex) {
			logger.error("makePersistent failed", ex);
			throw new InfrastructureException(ex);
		}
	}
	
	
	/**
	 * QualitatAPPCCEtapa amb la id indicada.
	 * 
	 * @param id
	 */
	public QualitatAPPCCEtapa getById(Long id) throws InfrastructureException {
		QualitatAPPCCEtapa qualitatAPPCCEtapa = null;
		try {
			qualitatAPPCCEtapa = (QualitatAPPCCEtapa)getHibernateTemplate().load(QualitatAPPCCEtapa.class, id);
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		return qualitatAPPCCEtapa;
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
			String q = "select form from QualitatAPPCCEtapa form " +
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
	 * @param QualitatAPPCCEtapa
	 * @throws InfrastructureException
	 */
	public void makeTransient(QualitatAPPCCEtapa appccEtapa)
	throws InfrastructureException {
		try {
			logger.debug("makeTransient ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().delete(appccEtapa);
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
