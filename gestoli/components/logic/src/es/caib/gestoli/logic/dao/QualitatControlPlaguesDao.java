package es.caib.gestoli.logic.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.exception.InfrastructureException;
import es.caib.gestoli.logic.model.QualitatControlPlagues;

public class QualitatControlPlaguesDao {

	private static Logger logger = Logger.getLogger(QualitatControlPlaguesDao.class);
	private HibernateTemplate hibernateTemplate;
	
	/**
	 * Actualiza un registro
	 * @param qualitatControlPlagues
	 * @throws InfrastructureException
	 */
	public void makePersistent(QualitatControlPlagues qualitatControlPlagues) throws InfrastructureException {
		try {
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().saveOrUpdate(qualitatControlPlagues);
			getHibernateTemplate().flush();
		} catch (HibernateException ex) {
			logger.error("makePersistent failed", ex);
			throw new InfrastructureException(ex);
		}
	}
	
	
	/**
	 * QualitatControlPlagues amb la id indicada.
	 * 
	 * @param id
	 */
	public QualitatControlPlagues getById(Long id) throws InfrastructureException {
		QualitatControlPlagues qualitatControlPlagues = null;
		try {
			qualitatControlPlagues = (QualitatControlPlagues)getHibernateTemplate().load(QualitatControlPlagues.class, id);
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		return qualitatControlPlagues;
	}

	/**
	 * Retorna un llista de tot els controls de plagues associats a un establiment.
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public List findByEstabliment(Long establimentId) throws InfrastructureException {
		List plagues;
		try {
			logger.debug("findByEstabliment ini");
			String q = "select form from QualitatControlPlagues form " +
			"where form.establiment.id = " + establimentId + " " +
			"order by form.isResponsableIntern, form.id desc";
			plagues = getHibernateTemplate().find(q);
			logger.debug("findByEstabliment ini");
		} catch (HibernateException ex) {
			logger.error("findByEstabliment failed", ex);
			throw new InfrastructureException(ex);
		}
		return plagues;
	}
	
	
	/**
	 * Borra un registro
	 * @param QualitatControlPlagues
	 * @throws InfrastructureException
	 */
	public void makeTransient(QualitatControlPlagues controlPlagues)
	throws InfrastructureException {
		try {
			logger.debug("makeTransient ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().delete(controlPlagues);
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
