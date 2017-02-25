package es.caib.gestoli.logic.dao;

import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.exception.InfrastructureException;
import es.caib.gestoli.logic.model.QualitatProveidor;
import es.caib.gestoli.logic.model.QualitatProveidor;

public class QualitatProveidorDao {

	private static Logger logger = Logger.getLogger(QualitatProveidorDao.class);
	private HibernateTemplate hibernateTemplate;
	
	/**
	 * Actualiza un registro
	 * @param qualitatProveidor
	 * @throws InfrastructureException
	 */
	public void makePersistent(QualitatProveidor qualitatProveidor) throws InfrastructureException {
		try {
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().saveOrUpdate(qualitatProveidor);
			getHibernateTemplate().flush();
		} catch (HibernateException ex) {
			logger.error("makePersistent failed", ex);
			throw new InfrastructureException(ex);
		}
	}
	
	/**
	 * QualitatProveidor amb la id indicada.
	 * 
	 * @param id
	 */
	public QualitatProveidor getById(Long id) throws InfrastructureException {
		QualitatProveidor qualitatProveidor = null;
		try {
			qualitatProveidor = (QualitatProveidor)getHibernateTemplate().load(QualitatProveidor.class, id);
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		return qualitatProveidor;
	}


	/**
	 * Recupera tots els registres.
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection getAllByEstabliment(Long establimentId) throws InfrastructureException {
		Collection  col;
		try {
			logger.debug("findByEstabliment ini");
			col = getHibernateTemplate().find(
					"from QualitatProveidor " +
					"where establiment.id = " + establimentId + " " +
					"order by codi");
			logger.debug("findByEstabliment ini");
		} catch (HibernateException ex) {
			logger.error("findByEstabliment failed", ex);
			throw new InfrastructureException(ex);
		}
		return col;
	}

	/**
	 * Borra un registro
	 * @param QualitatProveidor
	 * @throws InfrastructureException
	 */
	public void makeTransient(QualitatProveidor proveidor)
	throws InfrastructureException {
		try {
			logger.debug("makeTransient ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().delete(proveidor);
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
