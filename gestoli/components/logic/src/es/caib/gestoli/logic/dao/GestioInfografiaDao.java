package es.caib.gestoli.logic.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.exception.InfrastructureException;
import es.caib.gestoli.logic.model.GestioInfografia;

public class GestioInfografiaDao {

	private static Logger logger = Logger.getLogger(GestioInfografiaDao.class);
	private HibernateTemplate hibernateTemplate;
	
	/**
	 * Actualiza un registro
	 * @param GestioInfografia
	 * @throws InfrastructureException
	 */
	public void makePersistent(GestioInfografia GestioInfografia) throws InfrastructureException {
		try {
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().saveOrUpdate(GestioInfografia);
			getHibernateTemplate().flush();
		} catch (HibernateException ex) {
			logger.error("makePersistent failed", ex);
			throw new InfrastructureException(ex);
		}
	}
	
	/**
	 * GestioInfografia amb la id indicada.
	 * 
	 * @param id
	 */
	public GestioInfografia getById(Long id) throws InfrastructureException {
		GestioInfografia GestioInfografia = null;
		try {
			GestioInfografia = (GestioInfografia)getHibernateTemplate().load(GestioInfografia.class, id);
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		return GestioInfografia;
	}

	/**
	 * Retorna un llista de totes les informacions Utils.
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public List findAll() throws InfrastructureException {
		List informacions;
		try {
			logger.debug("findAll ini");
			String q = "select form from GestioInfografia form " +
			"order by form.dataAlta desc, form.id desc";
			informacions = getHibernateTemplate().find(q);
			logger.debug("findAll ini");
		} catch (HibernateException ex) {
			logger.error("findAll failed", ex);
			throw new InfrastructureException(ex);
		}
		return informacions;
	}
	
	/**
	 * Borra un registro
	 * @param GestioInfografia
	 * @throws InfrastructureException
	 */
	public void makeTransient(GestioInfografia gestioInfografia)
	throws InfrastructureException {
		try {
			logger.debug("makeTransient ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().delete(gestioInfografia);
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
