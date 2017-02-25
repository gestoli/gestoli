package es.caib.gestoli.logic.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.exception.InfrastructureException;
import es.caib.gestoli.logic.model.QualitatPlaNetejaRealitzacio;

public class QualitatPlaNetejaRealitzacioDao {

	private static Logger logger = Logger.getLogger(QualitatPlaNetejaRealitzacioDao.class);
	private HibernateTemplate hibernateTemplate;
	
	/**
	 * Actualiza un registro
	 * @param qualitatPlaNetejaRealitzacioDao
	 * @throws InfrastructureException
	 */
	public void makePersistent(QualitatPlaNetejaRealitzacio qualitatPlaNetejaRealitzacio) throws InfrastructureException {
		try {
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().saveOrUpdate(qualitatPlaNetejaRealitzacio);
			getHibernateTemplate().flush();
		} catch (HibernateException ex) {
			logger.error("makePersistent failed", ex);
			throw new InfrastructureException(ex);
		}
	}
	
	/**
	 * QualitatPlaNetejaRealitzacio amb la id indicada.
	 * 
	 * @param id
	 */
	public QualitatPlaNetejaRealitzacio getById(Long id) throws InfrastructureException {
		QualitatPlaNetejaRealitzacio qualitatPlaNetejaRealitzacio = null;
		try {
			qualitatPlaNetejaRealitzacio = (QualitatPlaNetejaRealitzacio)getHibernateTemplate().load(QualitatPlaNetejaRealitzacio.class, id);
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		return qualitatPlaNetejaRealitzacio;
	}

	/**
	 * Retorna un llista de tot els plans de NetejaRealitzacio associats a equipis d'un establiment.
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public List findByNeteja(Long netejaId) throws InfrastructureException {
		List NetejaRealitzacio;
		try {
			logger.debug("findByNeteja ini");
			String q = "select form from QualitatPlaNetejaRealitzacio form " +
			"where form.neteja.id = " + netejaId + " " +
			"order by form.dataRealitzacio desc, form.id desc";
			NetejaRealitzacio = getHibernateTemplate().find(q);
			logger.debug("findByNeteja ini");
		} catch (HibernateException ex) {
			logger.error("findByNeteja failed", ex);
			throw new InfrastructureException(ex);
		}
		return NetejaRealitzacio;
	}

	/**
	 * Borra un registro
	 * @param QualitatPlaNetejaRealitzacio
	 * @throws InfrastructureException
	 */
	public void makeTransient(QualitatPlaNetejaRealitzacio netejaRealitzacio)
	throws InfrastructureException {
		try {
			logger.debug("makeTransient ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().delete(netejaRealitzacio);
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
