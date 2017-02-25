package es.caib.gestoli.logic.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.exception.InfrastructureException;
import es.caib.gestoli.logic.model.QualitatAPPCCFitxaControl;
import es.caib.gestoli.logic.model.QualitatAPPCCFitxaControlHistoric;

public class QualitatAPPCCFitxaControlDao {

	private static Logger logger = Logger.getLogger(QualitatAPPCCFitxaControlDao.class);
	private HibernateTemplate hibernateTemplate;
	
	/**
	 * Actualiza un registro
	 * @param qualitatAPPCCFitxaControl
	 * @throws InfrastructureException
	 */
	public void makePersistentFitxa(QualitatAPPCCFitxaControl qualitatAPPCCFitxaControl) throws InfrastructureException {
		try {
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().saveOrUpdate(qualitatAPPCCFitxaControl);
			getHibernateTemplate().flush();
		} catch (HibernateException ex) {
			logger.error("makePersistentFitxa failed", ex);
			throw new InfrastructureException(ex);
		}
	}
	
	/**
	 * Actualiza un registro
	 * @param qualitatAPPCCFitxaHistoricControl
	 * @throws InfrastructureException
	 */
	public void makePersistentHistoric(QualitatAPPCCFitxaControlHistoric qualitatAPPCCFitxaControlHistoric) throws InfrastructureException {
		try {
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().saveOrUpdate(qualitatAPPCCFitxaControlHistoric);
			getHibernateTemplate().flush();
		} catch (HibernateException ex) {
			logger.error("makePersistentHistoric failed", ex);
			throw new InfrastructureException(ex);
		}
	}

	/**
	 * QualitatAPPCCFitxaControlHistoric amb la id indicada.
	 * 
	 * @param id
	 */
	public QualitatAPPCCFitxaControlHistoric getHistoricById(Long id) throws InfrastructureException {
		QualitatAPPCCFitxaControlHistoric qualitatAPPCCFitxaControlHistoric = null;
		try {
			qualitatAPPCCFitxaControlHistoric = (QualitatAPPCCFitxaControlHistoric)getHibernateTemplate().load(QualitatAPPCCFitxaControlHistoric.class, id);
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		return qualitatAPPCCFitxaControlHistoric;
	}
	
	/**
	 * QualitatAPPCCFitxaControl amb la id indicada.
	 * 
	 * @param id
	 */
	public QualitatAPPCCFitxaControl getFitxaById(Long id) throws InfrastructureException {
		QualitatAPPCCFitxaControl qualitatAPPCCFitxaControl = null;
		try {
			qualitatAPPCCFitxaControl = (QualitatAPPCCFitxaControl)getHibernateTemplate().load(QualitatAPPCCFitxaControl.class, id);
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		return qualitatAPPCCFitxaControl;
	}
	
	/**
	 * Retorna un llista de tots els controls d'un perill de l'APPCC.
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public QualitatAPPCCFitxaControlHistoric findByControl(Long controlId) throws InfrastructureException {
		List<QualitatAPPCCFitxaControlHistoric> fitxes;
		try {
			logger.debug("findByControl ini");
			String q = "select form from QualitatAPPCCFitxaControlHistoric form " +
			"where form.fitxa.control.id = " + controlId + " " +
			"order by form.dataModificacio desc";
			fitxes = (List<QualitatAPPCCFitxaControlHistoric>)getHibernateTemplate().find(q);
			logger.debug("findByPerill ini");
		} catch (HibernateException ex) {
			logger.error("findByPerill failed", ex);
			throw new InfrastructureException(ex);
		}
		if (fitxes.size() > 0){
			return fitxes.get(0);
		} else {
			return null;
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
