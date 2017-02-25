package es.caib.gestoli.logic.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.exception.InfrastructureException;
import es.caib.gestoli.logic.model.QualitatSubministre;
import es.caib.gestoli.logic.model.QualitatSubministre;

public class QualitatSubministreDao {

	private static Logger logger = Logger.getLogger(QualitatSubministreDao.class);
	private HibernateTemplate hibernateTemplate;
	
	/**
	 * Actualiza un registro
	 * @param qualitatSubministre
	 * @throws InfrastructureException
	 */
	public void makePersistent(QualitatSubministre qualitatSubministre) throws InfrastructureException {
		try {
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().saveOrUpdate(qualitatSubministre);
			getHibernateTemplate().flush();
		} catch (HibernateException ex) {
			logger.error("makePersistent failed", ex);
			throw new InfrastructureException(ex);
		}
	}
	
	/**
	 * QualitatSubministre amb la id indicada.
	 * 
	 * @param id
	 */
	public QualitatSubministre getById(Long id) throws InfrastructureException {
		QualitatSubministre qualitatSubministre = null;
		try {
			qualitatSubministre = (QualitatSubministre)getHibernateTemplate().load(QualitatSubministre.class, id);
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		return qualitatSubministre;
	}

	
	
	/**
	 * Retorna un llista de subministres associats amb un establiment.
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public List findByEstabliment(Long establimentId) throws InfrastructureException {
		List subministres;
		try {
			logger.debug("findByProveidor ini");
			String q = "select subm from QualitatSubministre subm, QualitatProveidor prov " +
			"where prov.establiment.id = " + establimentId + " " +
			"and prov.codi = subm.proveidor.codi " +
			"order by subm.codi";
			subministres = getHibernateTemplate().find(q);
			logger.debug("findByEstabliment fin");
		} catch (HibernateException ex) {
			logger.error("findByEstabliment failed", ex);
			throw new InfrastructureException(ex);
		}
		return subministres;
	}
	
	
	
	/**
	 * Retorna un llista de tot el personal associats amb un proveidor.
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public List findByProveidor(Long proveidorId) throws InfrastructureException {
		List subministres;
		try {
			logger.debug("findByProveidor ini");
			String q = "select subm from QualitatSubministre subm " +
			"where subm.proveidor.codi = " + proveidorId + " " +
			"order by subm.codi";
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
	 * @param QualitatSubministre
	 * @throws InfrastructureException
	 */
	public void makeTransient(QualitatSubministre subministre)
	throws InfrastructureException {
		try {
			logger.debug("makeTransient ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().delete(subministre);
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
