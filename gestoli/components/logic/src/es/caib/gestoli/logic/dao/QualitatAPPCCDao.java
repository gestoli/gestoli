package es.caib.gestoli.logic.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.exception.InfrastructureException;
import es.caib.gestoli.logic.model.QualitatAPPCC;
import es.caib.gestoli.logic.model.QualitatAPPCCProducte;

public class QualitatAPPCCDao {
	private static Logger logger = Logger.getLogger(QualitatAPPCCDao.class);
	private HibernateTemplate hibernateTemplate;
	
	/**
	 * Actualiza un registro
	 * @param qualitatAPPCC
	 * @throws InfrastructureException
	 */
	public void makePersistent(QualitatAPPCC qualitatAPPCC) throws InfrastructureException {
		try {
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().saveOrUpdate(qualitatAPPCC);
			getHibernateTemplate().flush();
		} catch (HibernateException ex) {
			logger.error("makePersistent failed", ex);
			throw new InfrastructureException(ex);
		}
	}
	
	/**
	 * QualitatAPPCC amb la id indicada.
	 * 
	 * @param id
	 */
	public QualitatAPPCC getById(Long id) throws InfrastructureException {
		QualitatAPPCC qualitatAPPCC = null;
		try {
			qualitatAPPCC = (QualitatAPPCC)getHibernateTemplate().load(QualitatAPPCC.class, id);
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		return qualitatAPPCC;
	}
	
	/**
	 * Retorna l'APPCC associat a un establiment.
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public QualitatAPPCC findByEstabliment(Long establimentId) throws InfrastructureException {
		List appcc;
		try {
			logger.debug("findByEstabliment ini");
			String q = "select form from QualitatAPPCC form " +
			"where form.establiment.id = " + establimentId + " ";
			appcc = getHibernateTemplate().find(q);
			logger.debug("findByEstabliment ini");
		} catch (HibernateException ex) {
			logger.error("findByEstabliment failed", ex);
			throw new InfrastructureException(ex);
		}
		if (appcc.size() > 0){
			return (QualitatAPPCC)appcc.get(0);
		} else {
			return null;
		}
	}
	
	
	// ** PRODUCTES ** //
	
	/**
	 * Actualiza un registro
	 * @param qualitatAPPCCProducte
	 * @throws InfrastructureException
	 */
	public void makePersistentProducte(QualitatAPPCCProducte qualitatAPPCCProducte) throws InfrastructureException {
		try {
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().saveOrUpdate(qualitatAPPCCProducte);
			getHibernateTemplate().flush();
		} catch (HibernateException ex) {
			logger.error("makePersistentProducte failed", ex);
			throw new InfrastructureException(ex);
		}
	}
	
	/**
	 * QualitatAPPCCProducte amb la id indicada.
	 * 
	 * @param id
	 */
	public QualitatAPPCCProducte getProducteById(Long id) throws InfrastructureException {
		QualitatAPPCCProducte qualitatAPPCCProducte = null;
		try {
			qualitatAPPCCProducte = (QualitatAPPCCProducte)getHibernateTemplate().load(QualitatAPPCCProducte.class, id);
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		return qualitatAPPCCProducte;
	}
	
	/**
	 * Retorna un llista de tots els productes associats a una appcc
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public List findProductesByAPPCC(Long aPPCCId) throws InfrastructureException {
		List accions;
		try {
			logger.debug("findProductesByAPPCC ini");
			String q = "select form from QualitatAPPCCProducte form " +
			"where form.appcc.id = " + aPPCCId + " " +
			"order by form.descripcio";
			accions = getHibernateTemplate().find(q);
			logger.debug("findProductesByAPPCC ini");
		} catch (HibernateException ex) {
			logger.error("findProductesByAPPCC failed", ex);
			throw new InfrastructureException(ex);
		}
		return accions;
	}

	/**
	 * Borra un registro
	 * @param QualitatAPPCCProducte
	 * @throws InfrastructureException
	 */
	public void makeTransientProducte(QualitatAPPCCProducte aPPCCProducte)
	throws InfrastructureException {
		try {
			logger.debug("makeTransientProducte ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().delete(aPPCCProducte);
			getHibernateTemplate().flush();
			logger.debug("makeTransientProducte fin");
		} catch (HibernateException ex) {
			logger.error("makeTransientProducte failed", ex);
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
