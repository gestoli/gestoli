package es.caib.gestoli.logic.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.exception.InfrastructureException;
import es.caib.gestoli.logic.model.QualitatPlaNetejaVerificacio;

public class QualitatPlaNetejaVerificacioDao {

	private static Logger logger = Logger.getLogger(QualitatPlaNetejaVerificacioDao.class);
	private HibernateTemplate hibernateTemplate;
	
	/**
	 * Actualiza un registro
	 * @param qualitatPlaNetejaVerificacioDao
	 * @throws InfrastructureException
	 */
	public void makePersistent(QualitatPlaNetejaVerificacio qualitatPlaNetejaVerificacio) throws InfrastructureException {
		try {
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().saveOrUpdate(qualitatPlaNetejaVerificacio);
			getHibernateTemplate().flush();
		} catch (HibernateException ex) {
			logger.error("makePersistent failed", ex);
			throw new InfrastructureException(ex);
		}
	}
	
	/**
	 * QualitatPlaNetejaVerificacio amb la id indicada.
	 * 
	 * @param id
	 */
	public QualitatPlaNetejaVerificacio getById(Long id) throws InfrastructureException {
		QualitatPlaNetejaVerificacio qualitatPlaNetejaVerificacio = null;
		try {
			qualitatPlaNetejaVerificacio = (QualitatPlaNetejaVerificacio)getHibernateTemplate().load(QualitatPlaNetejaVerificacio.class, id);
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		return qualitatPlaNetejaVerificacio;
	}

	/**
	 * Retorna un llista de tot els plans de NetejaVerificacio associats a equipis d'un establiment.
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public List findByNeteja(Long netejaId) throws InfrastructureException {
		List NetejaVerificacio;
		try {
			logger.debug("findByNeteja ini");
			String q = "select form from QualitatPlaNetejaVerificacio form " +
			"where form.neteja.id = " + netejaId + " " +
			"order by form.dataVerificacio desc, form.id desc";
			NetejaVerificacio = getHibernateTemplate().find(q);
			logger.debug("findByNeteja ini");
		} catch (HibernateException ex) {
			logger.error("findByNeteja failed", ex);
			throw new InfrastructureException(ex);
		}
		return NetejaVerificacio;
	}

	/**
	 * Borra un registro
	 * @param QualitatPlaNetejaVerificacio
	 * @throws InfrastructureException
	 */
	public void makeTransient(QualitatPlaNetejaVerificacio netejaVerificacio)
	throws InfrastructureException {
		try {
			logger.debug("makeTransient ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().delete(netejaVerificacio);
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
