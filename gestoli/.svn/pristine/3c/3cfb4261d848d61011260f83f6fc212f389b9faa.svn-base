package es.caib.gestoli.logic.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.exception.InfrastructureException;
import es.caib.gestoli.logic.model.QualitatProveidorRGSA;

public class QualitatProveidorRGSADao {

	private static Logger logger = Logger.getLogger(QualitatProveidorRGSADao.class);
	private HibernateTemplate hibernateTemplate;
	
	/**
	 * Actualiza un registro
	 * @param qualitatSubministre
	 * @throws InfrastructureException
	 */
	public void makePersistent(QualitatProveidorRGSA qualitatProveidorRGSA) throws InfrastructureException {
		try {
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().saveOrUpdate(qualitatProveidorRGSA);
			getHibernateTemplate().flush();
		} catch (HibernateException ex) {
			logger.error("makePersistent failed", ex);
			throw new InfrastructureException(ex);
		}
	}
	
	/**
	 * QualitatProveidorRGSA amb la id indicada.
	 * 
	 * @param id
	 */
	public QualitatProveidorRGSA getById(Long id) throws InfrastructureException {
		QualitatProveidorRGSA qualitatProveidorRGSA = null;
		try {
			qualitatProveidorRGSA = (QualitatProveidorRGSA)getHibernateTemplate().load(QualitatProveidorRGSA.class, id);
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		return qualitatProveidorRGSA;
	}

	/**
	 * Retorna un llista de tot els RGSA associats amb un proveidor.
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public List findByProveidor(Long proveidorId) throws InfrastructureException {
		List subministres;
		try {
			logger.debug("findByProveidor ini");
			String q = "select subm from QualitatProveidorRGSA rgsa " +
			"where rgsa.proveidor.codi = " + proveidorId + " " +
			"order by rgsa.id";
			subministres = getHibernateTemplate().find(q);
			logger.debug("findByEstabliment fin");
		} catch (HibernateException ex) {
			logger.error("findByEstabliment failed", ex);
			throw new InfrastructureException(ex);
		}
		return subministres;
	}

	/**
	 * Borra un registro
	 * @param QualitatProveidorRGSA
	 * @throws InfrastructureException
	 */
	public void makeTransient(QualitatProveidorRGSA rgsa)
	throws InfrastructureException {
		try {
			logger.debug("makeTransient ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().delete(rgsa);
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
