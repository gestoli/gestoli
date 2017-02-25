package es.caib.gestoli.logic.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.exception.InfrastructureException;
import es.caib.gestoli.logic.model.QualitatPlaManteniment;

public class QualitatPlaMantenimentDao {

	private static Logger logger = Logger.getLogger(QualitatPlaMantenimentDao.class);
	private HibernateTemplate hibernateTemplate;
	
	/**
	 * Actualiza un registro
	 * @param qualitatPlaMantenimentDao
	 * @throws InfrastructureException
	 */
	public void makePersistent(QualitatPlaManteniment qualitatPlaManteniment) throws InfrastructureException {
		try {
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().saveOrUpdate(qualitatPlaManteniment);
			getHibernateTemplate().flush();
		} catch (HibernateException ex) {
			logger.error("makePersistent failed", ex);
			throw new InfrastructureException(ex);
		}
	}
	
	/**
	 * QualitatPlaManteniment amb la id indicada.
	 * 
	 * @param id
	 */
	public QualitatPlaManteniment getById(Long id) throws InfrastructureException {
		QualitatPlaManteniment qualitatPlaManteniment = null;
		try {
			qualitatPlaManteniment = (QualitatPlaManteniment)getHibernateTemplate().load(QualitatPlaManteniment.class, id);
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		return qualitatPlaManteniment;
	}

	/**
	 * Retorna un llista de tot els plans de manteniment associats a equipis d'un establiment.
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public List findByEstabliment(Long establimentId) throws InfrastructureException {
		List manteniment;
		try {
			logger.debug("findByEstabliment ini");
			String q = "select form from QualitatPlaManteniment form " +
			"where form.equip.establiment.id = " + establimentId + " " +
			"order by form.actiu desc, form.equip.nom, form.id desc";
			manteniment = getHibernateTemplate().find(q);
			logger.debug("findByEstabliment ini");
		} catch (HibernateException ex) {
			logger.error("findByEstabliment failed", ex);
			throw new InfrastructureException(ex);
		}
		return manteniment;
	}

	/**
	 * Borra un registro
	 * @param QualitatPlaManteniment
	 * @throws InfrastructureException
	 */
	public void makeTransient(QualitatPlaManteniment manteniment)
	throws InfrastructureException {
		try {
			logger.debug("makeTransient ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().delete(manteniment);
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
