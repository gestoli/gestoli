package es.caib.gestoli.logic.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.exception.InfrastructureException;
import es.caib.gestoli.logic.model.QualitatPlaMantenimentControl;

public class QualitatPlaMantenimentControlDao {

	private static Logger logger = Logger.getLogger(QualitatPlaMantenimentControlDao.class);
	private HibernateTemplate hibernateTemplate;
	
	/**
	 * Actualiza un registro
	 * @param qualitatPlaMantenimentControlDao
	 * @throws InfrastructureException
	 */
	public void makePersistent(QualitatPlaMantenimentControl qualitatPlaMantenimentControl) throws InfrastructureException {
		try {
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().saveOrUpdate(qualitatPlaMantenimentControl);
			getHibernateTemplate().flush();
		} catch (HibernateException ex) {
			logger.error("makePersistent failed", ex);
			throw new InfrastructureException(ex);
		}
	}
	
	/**
	 * QualitatPlaMantenimentControl amb la id indicada.
	 * 
	 * @param id
	 */
	public QualitatPlaMantenimentControl getById(Long id) throws InfrastructureException {
		QualitatPlaMantenimentControl qualitatPlaMantenimentControl = null;
		try {
			qualitatPlaMantenimentControl = (QualitatPlaMantenimentControl)getHibernateTemplate().load(QualitatPlaMantenimentControl.class, id);
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		return qualitatPlaMantenimentControl;
	}

	/**
	 * Retorna un llista de tot els plans de mantenimentControl associats a equipis d'un establiment.
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public List findByManteniment(Long mantenimentId) throws InfrastructureException {
		List mantenimentControl;
		try {
			logger.debug("findByManteniment ini");
			String q = "select form from QualitatPlaMantenimentControl form " +
			"where form.manteniment.id = " + mantenimentId + " " +
			"order by form.dataRealitzacio desc, form.id desc";
			mantenimentControl = getHibernateTemplate().find(q);
			logger.debug("findByManteniment ini");
		} catch (HibernateException ex) {
			logger.error("findByManteniment failed", ex);
			throw new InfrastructureException(ex);
		}
		return mantenimentControl;
	}

	/**
	 * Borra un registro
	 * @param QualitatPlaMantenimentControl
	 * @throws InfrastructureException
	 */
	public void makeTransient(QualitatPlaMantenimentControl mantenimentControl)
	throws InfrastructureException {
		try {
			logger.debug("makeTransient ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().delete(mantenimentControl);
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
