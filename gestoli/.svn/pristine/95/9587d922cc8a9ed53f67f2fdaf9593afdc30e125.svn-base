package es.caib.gestoli.logic.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.exception.InfrastructureException;
import es.caib.gestoli.logic.model.QualitatProveidorAvaluacio;

public class QualitatProveidorAvaluacioDao {

	private static Logger logger = Logger.getLogger(QualitatProveidorAvaluacioDao.class);
	private HibernateTemplate hibernateTemplate;
	
	/**
	 * Actualiza un registro
	 * @param qualitatProveidorAvaluacio
	 * @throws InfrastructureException
	 */
	public void makePersistent(QualitatProveidorAvaluacio qualitatProveidorAvaluacio) throws InfrastructureException {
		try {
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().saveOrUpdate(qualitatProveidorAvaluacio);
			getHibernateTemplate().flush();
		} catch (HibernateException ex) {
			logger.error("makePersistent failed", ex);
			throw new InfrastructureException(ex);
		}
	}
	
	/**
	 * QualitatProveidorAvaluacio amb la id indicada.
	 * 
	 * @param id
	 */
	public QualitatProveidorAvaluacio getById(Long id) throws InfrastructureException {
		QualitatProveidorAvaluacio qualitatProveidorAvaluacio = null;
		try {
			qualitatProveidorAvaluacio = (QualitatProveidorAvaluacio)getHibernateTemplate().load(QualitatProveidorAvaluacio.class, id);
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		return qualitatProveidorAvaluacio;
	}
	

	/**
	 * Retorna un llista de totes les verificacions associats a un control de plagues.
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public List findByProveidor(Long proveidorId) throws InfrastructureException {
		List verificacions;
		try {
			logger.debug("findByProveidor ini");
			String q = "select form from QualitatProveidorAvaluacio form " +
			"where form.proveidor.codi = " + proveidorId + " " +
			"order by form.dataVerificacio desc, form.id desc";
			verificacions = getHibernateTemplate().find(q);
			logger.debug("findByProveidor ini");
		} catch (HibernateException ex) {
			logger.error("findByProveidor failed", ex);
			throw new InfrastructureException(ex);
		}
		return verificacions;
	}
	

	/**
	 * Borra un registro
	 * @param QualitatProveidorAvaluacio
	 * @throws InfrastructureException
	 */
	public void makeTransient(QualitatProveidorAvaluacio ProveidorAvaluacio)
	throws InfrastructureException {
		try {
			logger.debug("makeTransient ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().delete(ProveidorAvaluacio);
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
