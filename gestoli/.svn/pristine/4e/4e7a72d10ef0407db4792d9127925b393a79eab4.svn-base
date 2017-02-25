package es.caib.gestoli.logic.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.exception.InfrastructureException;
import es.caib.gestoli.logic.model.QualitatFormacioEvaluacio;

public class QualitatFormacioEvaluacioDao {

	private static Logger logger = Logger.getLogger(QualitatFormacioEvaluacioDao.class);
	private HibernateTemplate hibernateTemplate;
	
	/**
	 * Actualiza un registro
	 * @param QualitatFormacioEvaluacio
	 * @throws InfrastructureException
	 */
	public void makePersistent(QualitatFormacioEvaluacio QualitatFormacioEvaluacio) throws InfrastructureException {
		try {
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().saveOrUpdate(QualitatFormacioEvaluacio);
			getHibernateTemplate().flush();
		} catch (HibernateException ex) {
			logger.error("makePersistent failed", ex);
			throw new InfrastructureException(ex);
		}
	}
	
	/**
	 * QualitatFormacioEvaluacio amb la id indicada.
	 * 
	 * @param id
	 */
	public QualitatFormacioEvaluacio getById(Long id) throws InfrastructureException {
		QualitatFormacioEvaluacio QualitatFormacioEvaluacio = null;
		try {
			QualitatFormacioEvaluacio = (QualitatFormacioEvaluacio)getHibernateTemplate().load(QualitatFormacioEvaluacio.class, id);
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		return QualitatFormacioEvaluacio;
	}

	/**
	 * Retorna un llista de totes les evaluacions associades amb una formacio.
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public List findByFormacio(Long formacioId) throws InfrastructureException {
		List evaluacions;
		try {
			logger.debug("findByFormacio ini");
			String q = "select eval from QualitatFormacioEvaluacio eval " +
			"where eval.formacio.id = " + formacioId + " " +
			"order by eval.id";
			evaluacions = getHibernateTemplate().find(q);
			logger.debug("findByFormacio fin");
		} catch (HibernateException ex) {
			logger.error("findByFormacio failed", ex);
			throw new InfrastructureException(ex);
		}
		return evaluacions;
	}
	
	/**
	 * Retorna un llista de totes les evaluacions associades amb un treballador.
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public List findByTreballador(Long treballadorId) throws InfrastructureException {
		List evaluacions;
		try {
			logger.debug("findByTreballador ini");
			String q = "select eval from QualitatFormacioEvaluacio eval " +
			"where eval.treballador.id = " + treballadorId + " " +
			"order by eval.id";
			evaluacions = getHibernateTemplate().find(q);
			logger.debug("findByTreballador fin");
		} catch (HibernateException ex) {
			logger.error("findByTreballador failed", ex);
			throw new InfrastructureException(ex);
		}
		return evaluacions;
	}

	/**
	 * Borra un registro
	 * @param QualitatFormacioEvaluacio
	 * @throws InfrastructureException
	 */
	public void makeTransient(QualitatFormacioEvaluacio evaluacio)
	throws InfrastructureException {
		try {
			logger.debug("makeTransient ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().delete(evaluacio);
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
