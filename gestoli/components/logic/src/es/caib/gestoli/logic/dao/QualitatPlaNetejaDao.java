package es.caib.gestoli.logic.dao;

import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.exception.InfrastructureException;
import es.caib.gestoli.logic.model.QualitatPlaNeteja;

public class QualitatPlaNetejaDao {

	private static Logger logger = Logger.getLogger(QualitatPlaNetejaDao.class);
	private HibernateTemplate hibernateTemplate;
	
	/**
	 * Actualiza un registro
	 * @param qualitatPlaNeteja
	 * @throws InfrastructureException
	 */
	public void makePersistent(QualitatPlaNeteja qualitatPlaNeteja) throws InfrastructureException {
		try {
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().saveOrUpdate(qualitatPlaNeteja);
			getHibernateTemplate().flush();
		} catch (HibernateException ex) {
			logger.error("makePersistent failed", ex);
			throw new InfrastructureException(ex);
		}
	}
	
	/**
	 * QualitatPlaNeteja amb la id indicada.
	 * 
	 * @param id
	 */
	public QualitatPlaNeteja getById(Long id) throws InfrastructureException {
		QualitatPlaNeteja qualitatPlaNeteja = null;
		try {
			qualitatPlaNeteja = (QualitatPlaNeteja)getHibernateTemplate().load(QualitatPlaNeteja.class, id);
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		return qualitatPlaNeteja;
	}

	/**
	 * Retorna un llista de tot el personal associats amb un establiment.
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection plansNetejaPerEstabliment(Long establimentId) throws InfrastructureException {
		Collection neteja;
		try {
			logger.debug("findByEstabliment ini");
			String q = "select form from QualitatPlaNeteja form " +
			"where form.establiment.id = " + establimentId + " " +
			"order by form.codi desc";
			neteja = getHibernateTemplate().find(q);
			logger.debug("findByEstabliment ini");
		} catch (HibernateException ex) {
			logger.error("findByEstabliment failed", ex);
			throw new InfrastructureException(ex);
		}
		return neteja;
	}

	
	/**
	 * Borra un registro
	 * @param QualitatPlaNeteja
	 * @throws InfrastructureException
	 */
	public void makeTransient(QualitatPlaNeteja plaNeteja)
	throws InfrastructureException {
		try {
			logger.debug("makeTransient ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().delete(plaNeteja);
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
