package es.caib.gestoli.logic.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.exception.InfrastructureException;
import es.caib.gestoli.logic.model.InformeCampanya;

public class InformeCampanyaDao {

	private static Logger logger = Logger.getLogger(InformeCampanyaDao.class);
	private HibernateTemplate hibernateTemplate;
	
	/**
	 * Actualiza un registro
	 * @param informeCampanya
	 * @throws InfrastructureException
	 */
	public void makePersistent(InformeCampanya informeCampanya) throws InfrastructureException {
		try {
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().saveOrUpdate(informeCampanya);
			getHibernateTemplate().flush();
		} catch (HibernateException ex) {
			logger.error("makePersistent failed", ex);
			throw new InfrastructureException(ex);
		}
	}
	
	/**
	 * InformeCampanya amb la id indicada.
	 * 
	 * @param id
	 */
	public InformeCampanya getById(Long id) throws InfrastructureException {
		InformeCampanya informeCampanya = null;
		try {
			informeCampanya = (InformeCampanya)getHibernateTemplate().load(InformeCampanya.class, id);
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		return informeCampanya;
	}

	/**
	 * Retorna un llista de tots els informes de la campanya.
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public List findAll() throws InfrastructureException {
		List informacions;
		try {
			logger.debug("findAll ini");
			String q = "select form from InformeCampanya form " +
			"order by form.tipus, form.campanya.id desc, form.any desc, form.dataAlta desc";
			informacions = getHibernateTemplate().find(q);
			logger.debug("findAll ini");
		} catch (HibernateException ex) {
			logger.error("findAll failed", ex);
			throw new InfrastructureException(ex);
		}
		return informacions;
	}
	
	/**
	 * Retorna un llista dels informes de la campanya segons tipus (true->campanya / false->any)
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public List findByTipus(Boolean tipus) throws InfrastructureException {
		List informacions;
		try {
			logger.debug("findByTipus ini");
			String q = "select form from InformeCampanya form " +
			"where form.tipus is " + tipus + " " +
			"order by form.tipus, form.campanya.id desc, form.any desc, form.dataAlta desc";
			informacions = getHibernateTemplate().find(q);
			logger.debug("findByTipus ini");
		} catch (HibernateException ex) {
			logger.error("findByTipus failed", ex);
			throw new InfrastructureException(ex);
		}
		return informacions;
	}

	/**
	 * Borra un registro
	 * @param InformeCampanya
	 * @throws InfrastructureException
	 */
	public void makeTransient(InformeCampanya informeCampanya)
	throws InfrastructureException {
		try {
			logger.debug("makeTransient ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().delete(informeCampanya);
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
