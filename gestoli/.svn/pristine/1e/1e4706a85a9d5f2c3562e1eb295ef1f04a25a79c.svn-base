package es.caib.gestoli.logic.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.exception.InfrastructureException;
import es.caib.gestoli.logic.model.QualitatPlaControlTransport;

public class QualitatPlaControlTransportDao {

	private static Logger logger = Logger.getLogger(QualitatPlaControlTransportDao.class);
	private HibernateTemplate hibernateTemplate;
	
	/**
	 * Actualiza un registro
	 * @param qualitatPlaControlTransportDao
	 * @throws InfrastructureException
	 */
	public void makePersistent(QualitatPlaControlTransport qualitatPlaControlTransport) throws InfrastructureException {
		try {
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().saveOrUpdate(qualitatPlaControlTransport);
			getHibernateTemplate().flush();
		} catch (HibernateException ex) {
			logger.error("makePersistent failed", ex);
			throw new InfrastructureException(ex);
		}
	}
	
	/**
	 * QualitatPlaControlTransport amb la id indicada.
	 * 
	 * @param id
	 */
	public QualitatPlaControlTransport getById(Long id) throws InfrastructureException {
		QualitatPlaControlTransport qualitatPlaControlTransport = null;
		try {
			qualitatPlaControlTransport = (QualitatPlaControlTransport)getHibernateTemplate().load(QualitatPlaControlTransport.class, id);
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		return qualitatPlaControlTransport;
	}

	/**
	 * Retorna un llista de tot els plans de manteniment associats a equipis d'un establiment.
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public List findByEstabliment(Long establimentId) throws InfrastructureException {
		List control;
		try {
			logger.debug("findByEstabliment ini");
			String q = "select form from QualitatPlaControlTransport form " +
			"where form.establiment.id = " + establimentId + " " +
			"order by form.id desc";
			control = getHibernateTemplate().find(q);
			logger.debug("findByEstabliment ini");
		} catch (HibernateException ex) {
			logger.error("findByEstabliment failed", ex);
			throw new InfrastructureException(ex);
		}
		return control;
	}

	/**
	 * Borra un registro
	 * @param QualitatPlaControlTransport
	 * @throws InfrastructureException
	 */
	public void makeTransient(QualitatPlaControlTransport manteniment)
	throws InfrastructureException {
		try {
			logger.debug("makeTransient ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().delete(manteniment);
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
