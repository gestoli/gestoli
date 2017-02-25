package es.caib.gestoli.logic.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.exception.InfrastructureException;
import es.caib.gestoli.logic.model.QualitatPuestoTreball;

public class QualitatPuestoTreballDao {

	private static Logger logger = Logger.getLogger(QualitatPuestoTreballDao.class);
	private HibernateTemplate hibernateTemplate;
	
	/**
	 * Actualiza un registro
	 * @param qualitatPuestoTreball
	 * @throws InfrastructureException
	 */
	public void makePersistent(QualitatPuestoTreball qualitatPuestoTreball) throws InfrastructureException {
		try {
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().saveOrUpdate(qualitatPuestoTreball);
			getHibernateTemplate().flush();
		} catch (HibernateException ex) {
			logger.error("makePersistent failed", ex);
			throw new InfrastructureException(ex);
		}
	}
	
	/**
	 * QualitatPuestoTreball amb la id indicada.
	 * 
	 * @param id
	 */
	public QualitatPuestoTreball getById(Long id) throws InfrastructureException {
		QualitatPuestoTreball qualitatPuestoTreball = null;
		try {
			qualitatPuestoTreball = (QualitatPuestoTreball)getHibernateTemplate().load(QualitatPuestoTreball.class, id);
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		return qualitatPuestoTreball;
	}

	/**
	 * Retorna un llista de tot el personal associats amb un establiment.
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public List findByEstabliment(Long establimentId) throws InfrastructureException {
		List puesto;
		try {
			logger.debug("findByEstabliment ini");
			String q = "select pues from QualitatPuestoTreball pues " +
			"where pues.establiment.id = " + establimentId + " " +
			"order by pues.nivellJerarquic";
			puesto = getHibernateTemplate().find(q);
			logger.debug("findByEstabliment ini");
		} catch (HibernateException ex) {
			logger.error("findByEstabliment failed", ex);
			throw new InfrastructureException(ex);
		}
		return puesto;
	}
	
	/**
	 * Retorna un llista de tot el personal associats amb un establiment.
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public List findByNivell(Long establimentId, Integer nivell) throws InfrastructureException {
		List puesto;
		try {
			logger.debug("findByNivell ini");
			String q = "select pues from QualitatPuestoTreball pues " +
			"where pues.establiment.id = " + establimentId + " " +
			"and   pues.nivellJerarquic = " + nivell + " " +
			"order by pues.id";
			puesto = getHibernateTemplate().find(q);
			logger.debug("findByNivell ini");
		} catch (HibernateException ex) {
			logger.error("findByNivell failed", ex);
			throw new InfrastructureException(ex);
		}
		return puesto;
	}

	/**
	 * Borra un registro
	 * @param QualitatPuestoTreball
	 * @throws InfrastructureException
	 */
	public void makeTransient(QualitatPuestoTreball puesto)
	throws InfrastructureException {
		try {
			logger.debug("makeTransient ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().delete(puesto);
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
