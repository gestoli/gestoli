package es.caib.gestoli.logic.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.exception.InfrastructureException;
import es.caib.gestoli.logic.model.QualitatAiguaControlOrganolepticVerificacio;

public class QualitatAiguaControlOrganolepticVerificacioDao {

	private static Logger logger = Logger.getLogger(QualitatAiguaControlOrganolepticVerificacioDao.class);
	private HibernateTemplate hibernateTemplate;
	
	/**
	 * Actualiza un registro
	 * @param qualitatAiguaControlOrganolepticVerificacio
	 * @throws InfrastructureException
	 */
	public void makePersistent(QualitatAiguaControlOrganolepticVerificacio qualitatAiguaControlOrganolepticVerificacio) throws InfrastructureException {
		try {
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().saveOrUpdate(qualitatAiguaControlOrganolepticVerificacio);
			getHibernateTemplate().flush();
		} catch (HibernateException ex) {
			logger.error("makePersistent failed", ex);
			throw new InfrastructureException(ex);
		}
	}
	
	/**
	 * QualitatAiguaControlOrganolepticVerificacio amb la id indicada.
	 * 
	 * @param id
	 */
	public QualitatAiguaControlOrganolepticVerificacio getById(Long id) throws InfrastructureException {
		QualitatAiguaControlOrganolepticVerificacio qualitatAiguaControlOrganolepticVerificacio = null;
		try {
			qualitatAiguaControlOrganolepticVerificacio = (QualitatAiguaControlOrganolepticVerificacio)getHibernateTemplate().load(QualitatAiguaControlOrganolepticVerificacio.class, id);
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		return qualitatAiguaControlOrganolepticVerificacio;
	}
	

	/**
	 * Retorna un llista de totes les verificacions associats a un control de plagues.
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public List findByPlaga(Long controlId) throws InfrastructureException {
		List verificacions;
		try {
			logger.debug("findByControl ini");
			String q = "select form from QualitatAiguaControlOrganolepticVerificacio form " +
			"where form.controlOrganoleptic.id = " + controlId + " " +
			"order by form.dataVerificacio desc, form.id desc";
			verificacions = getHibernateTemplate().find(q);
			logger.debug("findByControl ini");
		} catch (HibernateException ex) {
			logger.error("findByControl failed", ex);
			throw new InfrastructureException(ex);
		}
		return verificacions;
	}
	

	/**
	 * Borra un registro
	 * @param QualitatAiguaControlOrganolepticVerificacio
	 * @throws InfrastructureException
	 */
	public void makeTransient(QualitatAiguaControlOrganolepticVerificacio controlPlaguesVerificacio)
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
