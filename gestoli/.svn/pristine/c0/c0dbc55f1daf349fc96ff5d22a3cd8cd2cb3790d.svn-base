package es.caib.gestoli.logic.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.exception.InfrastructureException;
import es.caib.gestoli.logic.model.QualitatDescripcioPersonal;

public class QualitatDescripcioPersonalDao {

	private static Logger logger = Logger.getLogger(QualitatDescripcioPersonalDao.class);
	private HibernateTemplate hibernateTemplate;
	
	/**
	 * Actualiza un registro
	 * @param qualitatDescripcioPersonal
	 * @throws InfrastructureException
	 */
	public void makePersistent(QualitatDescripcioPersonal qualitatDescripcioPersonal) throws InfrastructureException {
		try {
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().saveOrUpdate(qualitatDescripcioPersonal);
			getHibernateTemplate().flush();
		} catch (HibernateException ex) {
			logger.error("makePersistent failed", ex);
			throw new InfrastructureException(ex);
		}
	}
	
	/**
	 * QualitatDescripcioPersonal amb la id indicada.
	 * 
	 * @param id
	 */
	public QualitatDescripcioPersonal getById(Long id) throws InfrastructureException {
		QualitatDescripcioPersonal qualitatDescripcioPersonal = null;
		try {
			qualitatDescripcioPersonal = (QualitatDescripcioPersonal)getHibernateTemplate().load(QualitatDescripcioPersonal.class, id);
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		return qualitatDescripcioPersonal;
	}

	/**
	 * Retorna un llista de tot el personal associats amb un establiment.
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public List findByEstabliment(Long establimentId) throws InfrastructureException {
		List personal;
		try {
			logger.debug("findByEstabliment ini");
			String q = "select pers from QualitatDescripcioPersonal pers " +
			"where pers.establiment.id = " + establimentId + " " +
			"order by pers.codi";
			personal = getHibernateTemplate().find(q);
			logger.debug("findByEstabliment ini");
		} catch (HibernateException ex) {
			logger.error("findByEstabliment failed", ex);
			throw new InfrastructureException(ex);
		}
		return personal;
	}

	/**
	 * Borra un registro
	 * @param QualitatDescripcioPersonal
	 * @throws InfrastructureException
	 */
	public void makeTransient(QualitatDescripcioPersonal personal)
	throws InfrastructureException {
		try {
			logger.debug("makeTransient ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().delete(personal);
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
