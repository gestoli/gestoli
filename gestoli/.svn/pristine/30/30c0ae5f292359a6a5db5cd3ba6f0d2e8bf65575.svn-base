package es.caib.gestoli.logic.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.exception.InfrastructureException;
import es.caib.gestoli.logic.model.QualitatPlaFormacio;

public class QualitatPlaFormacioDao {

	private static Logger logger = Logger.getLogger(QualitatPlaFormacioDao.class);
	private HibernateTemplate hibernateTemplate;
	
	/**
	 * Actualiza un registro
	 * @param qualitatPlaFormacio
	 * @throws InfrastructureException
	 */
	public void makePersistent(QualitatPlaFormacio qualitatPlaFormacio) throws InfrastructureException {
		try {
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().saveOrUpdate(qualitatPlaFormacio);
			getHibernateTemplate().flush();
		} catch (HibernateException ex) {
			logger.error("makePersistent failed", ex);
			throw new InfrastructureException(ex);
		}
	}
	
	/**
	 * QualitatPlaFormacio amb la id indicada.
	 * 
	 * @param id
	 */
	public QualitatPlaFormacio getById(Long id) throws InfrastructureException {
		QualitatPlaFormacio qualitatPlaFormacio = null;
		try {
			qualitatPlaFormacio = (QualitatPlaFormacio)getHibernateTemplate().load(QualitatPlaFormacio.class, id);
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		return qualitatPlaFormacio;
	}

	/**
	 * Retorna un llista de tot el personal associats amb un establiment.
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public List findByEstabliment(Long establimentId) throws InfrastructureException {
		List formacio;
		try {
			logger.debug("findByEstabliment ini");
			String q = "select form from QualitatPlaFormacio form " +
			"where form.establiment.id = " + establimentId + " " +
			"order by form.dataPrevista desc, form.id desc";
			formacio = getHibernateTemplate().find(q);
			logger.debug("findByEstabliment ini");
		} catch (HibernateException ex) {
			logger.error("findByEstabliment failed", ex);
			throw new InfrastructureException(ex);
		}
		return formacio;
	}

	/**
	 * Borra un registro
	 * @param QualitatPlaFormacio
	 * @throws InfrastructureException
	 */
	public void makeTransient(QualitatPlaFormacio personal)
	throws InfrastructureException {
		try {
			logger.debug("makeTransient ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().delete(personal);
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
