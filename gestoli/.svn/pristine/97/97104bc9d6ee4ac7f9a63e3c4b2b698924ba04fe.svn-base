package es.caib.gestoli.logic.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.exception.InfrastructureException;
import es.caib.gestoli.logic.model.QualitatAPPCCEtapaPerill;

public class QualitatAPPCCEtapaPerillDao {

	private static Logger logger = Logger.getLogger(QualitatAPPCCEtapaPerillDao.class);
	private HibernateTemplate hibernateTemplate;
	
	/**
	 * Actualiza un registro
	 * @param qualitatAPPCCEtapaPerill
	 * @throws InfrastructureException
	 */
	public void makePersistent(QualitatAPPCCEtapaPerill qualitatAPPCCPerill) throws InfrastructureException {
		try {
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().saveOrUpdate(qualitatAPPCCPerill);
			getHibernateTemplate().flush();
		} catch (HibernateException ex) {
			logger.error("makePersistent failed", ex);
			throw new InfrastructureException(ex);
		}
	}
	
	
	/**
	 * QualitatAPPCCEtapaPerill amb la id indicada.
	 * 
	 * @param id
	 */
	public QualitatAPPCCEtapaPerill getById(Long id) throws InfrastructureException {
		QualitatAPPCCEtapaPerill qualitatAPPCCEtapaPerill = null;
		try {
			qualitatAPPCCEtapaPerill = (QualitatAPPCCEtapaPerill)getHibernateTemplate().load(QualitatAPPCCEtapaPerill.class, id);
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		return qualitatAPPCCEtapaPerill;
	}

	/**
	 * Retorna un llista de tots els perills d'una etapa de l'APPCC.
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public List findByEtapa(Long etapaId) throws InfrastructureException {
		List perills;
		try {
			logger.debug("findByEtapa ini");
			String q = "select form from QualitatAPPCCEtapaPerill form " +
			"where form.etapa.id = " + etapaId + " " +
			"order by form.tipus, form.id";
			perills = getHibernateTemplate().find(q);
			logger.debug("findByEtapa ini");
		} catch (HibernateException ex) {
			logger.error("findByEtapa failed", ex);
			throw new InfrastructureException(ex);
		}
		return perills;
	}
	
	/**
	 * Retorna un llista de tots els perills d'una etapa de l'APPCC.
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public List findByEstabliment(Long establimentId) throws InfrastructureException {
		List perills;
		try {
			logger.debug("findByEstabliment ini");
			String q = "select form from QualitatAPPCCEtapaPerill form " +
			"where form.etapa.establiment.id = " + establimentId + " " +
			"and (form.probabilitat + form.gravetat) >= 5" + " " +
			"order by form.etapa.order, form.tipus, form.id";
			perills = getHibernateTemplate().find(q);
			logger.debug("findByEstabliment ini");
		} catch (HibernateException ex) {
			logger.error("findByEstabliment failed", ex);
			throw new InfrastructureException(ex);
		}
		return perills;
	}
	
	/**
	 * Borra un registro
	 * @param QualitatAPPCCEtapaPerill
	 * @throws InfrastructureException
	 */
	public void makeTransient(QualitatAPPCCEtapaPerill appccPerill)
	throws InfrastructureException {
		try {
			logger.debug("makeTransient ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().delete(appccPerill);
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
