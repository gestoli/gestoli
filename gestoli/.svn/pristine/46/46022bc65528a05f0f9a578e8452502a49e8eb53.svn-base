package es.caib.gestoli.logic.dao;

import java.sql.Timestamp;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.exception.InfrastructureException;
import es.caib.gestoli.logic.model.InformacioUtil;

public class InformacioUtilDao {

	private static Logger logger = Logger.getLogger(InformacioUtilDao.class);
	private HibernateTemplate hibernateTemplate;
	
	/**
	 * Actualiza un registro
	 * @param informacioUtil
	 * @throws InfrastructureException
	 */
	public void makePersistent(InformacioUtil informacioUtil) throws InfrastructureException {
		try {
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().saveOrUpdate(informacioUtil);
			getHibernateTemplate().flush();
		} catch (HibernateException ex) {
			logger.error("makePersistent failed", ex);
			throw new InfrastructureException(ex);
		}
	}
	
	/**
	 * InformacioUtil amb la id indicada.
	 * 
	 * @param id
	 */
	public InformacioUtil getById(Long id) throws InfrastructureException {
		InformacioUtil informacioUtil = null;
		try {
			informacioUtil = (InformacioUtil)getHibernateTemplate().load(InformacioUtil.class, id);
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		return informacioUtil;
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
			String q = "select form from InformacioUtil form " +
			"order by form.actiu desc, form.dataInici desc, form.id desc";
			informacions = getHibernateTemplate().find(q);
			logger.debug("findAll ini");
		} catch (HibernateException ex) {
			logger.error("findAll failed", ex);
			throw new InfrastructureException(ex);
		}
		return informacions;
	}
	
	/**
	 * Retorna un llista de les informacions Utils actives.
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public List findActius() throws InfrastructureException {
		List informacions;
		java.util.Date today = new java.util.Date();
		Timestamp data = new Timestamp(today.getTime());
		try {
			logger.debug("findAll ini");
			String q = "select form from InformacioUtil form " +
			"where form.actiu is true " +
			"and   (form.dataInici is null or form.dataInici < '" + data + "') " +
			"and   (form.dataFinal is null or form.dataFinal > '" + data + "') " +
			"order by form.actiu desc, dataInici desc, form.id desc";
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
	 * @param InformacioUtil
	 * @throws InfrastructureException
	 */
	public void makeTransient(InformacioUtil informacioUtil)
	throws InfrastructureException {
		try {
			logger.debug("makeTransient ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().delete(informacioUtil);
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
