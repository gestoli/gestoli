package es.caib.gestoli.logic.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.exception.InfrastructureException;
import es.caib.gestoli.logic.model.QualitatAiguaControlAnaliticVerificacio;

public class QualitatAiguaControlAnaliticVerificacioDao {

	private static Logger logger = Logger.getLogger(QualitatAiguaControlAnaliticVerificacioDao.class);
	private HibernateTemplate hibernateTemplate;
	
	/**
	 * Actualiza un registro
	 * @param qualitatAiguaControlAnaliticVerificacio
	 * @throws InfrastructureException
	 */
	public void makePersistent(QualitatAiguaControlAnaliticVerificacio qualitatAiguaControlAnaliticVerificacio) throws InfrastructureException {
		try {
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().saveOrUpdate(qualitatAiguaControlAnaliticVerificacio);
			getHibernateTemplate().flush();
		} catch (HibernateException ex) {
			logger.error("makePersistent failed", ex);
			throw new InfrastructureException(ex);
		}
	}
	
	/**
	 * QualitatAiguaControlAnaliticVerificacio amb la id indicada.
	 * 
	 * @param id
	 */
	public QualitatAiguaControlAnaliticVerificacio getById(Long id) throws InfrastructureException {
		QualitatAiguaControlAnaliticVerificacio qualitatAiguaControlAnaliticVerificacio = null;
		try {
			qualitatAiguaControlAnaliticVerificacio = (QualitatAiguaControlAnaliticVerificacio)getHibernateTemplate().load(QualitatAiguaControlAnaliticVerificacio.class, id);
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		return qualitatAiguaControlAnaliticVerificacio;
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
			String q = "select form from QualitatAiguaControlAnaliticVerificacio form " +
			"where form.controlAnalitic.id = " + controlId + " " +
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
	 * @param QualitatAiguaControlAnaliticVerificacio
	 * @throws InfrastructureException
	 */
	public void makeTransient(QualitatAiguaControlAnaliticVerificacio controlAnaliticVerificacio)
	throws InfrastructureException {
		try {
			logger.debug("makeTransient ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().delete(controlAnaliticVerificacio);
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
