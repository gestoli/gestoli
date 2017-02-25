package es.caib.gestoli.logic.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.exception.InfrastructureException;
import es.caib.gestoli.logic.model.QualitatControlPlaguesVerificacio;

public class QualitatControlPlaguesVerificacioDao {

	private static Logger logger = Logger.getLogger(QualitatControlPlaguesVerificacioDao.class);
	private HibernateTemplate hibernateTemplate;
	
	/**
	 * Actualiza un registro
	 * @param qualitatControlPlaguesVerificacio
	 * @throws InfrastructureException
	 */
	public void makePersistent(QualitatControlPlaguesVerificacio qualitatControlPlaguesVerificacio) throws InfrastructureException {
		try {
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().saveOrUpdate(qualitatControlPlaguesVerificacio);
			getHibernateTemplate().flush();
		} catch (HibernateException ex) {
			logger.error("makePersistent failed", ex);
			throw new InfrastructureException(ex);
		}
	}
	
	/**
	 * QualitatControlPlaguesVerificacio amb la id indicada.
	 * 
	 * @param id
	 */
	public QualitatControlPlaguesVerificacio getById(Long id) throws InfrastructureException {
		QualitatControlPlaguesVerificacio qualitatControlPlaguesVerificacio = null;
		try {
			qualitatControlPlaguesVerificacio = (QualitatControlPlaguesVerificacio)getHibernateTemplate().load(QualitatControlPlaguesVerificacio.class, id);
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		return qualitatControlPlaguesVerificacio;
	}
	

	/**
	 * Retorna un llista de totes les verificacions associats a un control de plagues.
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public List findByPlaga(Long plagaId) throws InfrastructureException {
		List verificacions;
		try {
			logger.debug("findByPlaga ini");
			String q = "select form from QualitatControlPlaguesVerificacio form " +
			"where form.controlPlaga.id = " + plagaId + " " +
			"order by form.dataVerificacio desc, form.id desc";
			verificacions = getHibernateTemplate().find(q);
			logger.debug("findByPlaga ini");
		} catch (HibernateException ex) {
			logger.error("findByPlaga failed", ex);
			throw new InfrastructureException(ex);
		}
		return verificacions;
	}
	

	/**
	 * Borra un registro
	 * @param QualitatControlPlaguesVerificacio
	 * @throws InfrastructureException
	 */
	public void makeTransient(QualitatControlPlaguesVerificacio controlPlaguesVerificacio)
	throws InfrastructureException {
		try {
			logger.debug("makeTransient ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().delete(controlPlaguesVerificacio);
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
