package es.caib.gestoli.logic.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.exception.InfrastructureException;
import es.caib.gestoli.logic.model.QualitatAPPCCControl;
import es.caib.gestoli.logic.model.QualitatAPPCCEtapaPerill;

public class QualitatAPPCCControlDao {

	private static Logger logger = Logger.getLogger(QualitatAPPCCControlDao.class);
	private HibernateTemplate hibernateTemplate;
	
	/**
	 * Actualiza un registro
	 * @param qualitatAPPCCControl
	 * @throws InfrastructureException
	 */
	public void makePersistent(QualitatAPPCCControl qualitatAPPCCControl) throws InfrastructureException {
		try {
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().saveOrUpdate(qualitatAPPCCControl);
			getHibernateTemplate().flush();
		} catch (HibernateException ex) {
			logger.error("makePersistent failed", ex);
			throw new InfrastructureException(ex);
		}
	}
	
	/**
	 * QualitatAPPCCControl amb la id indicada.
	 * 
	 * @param id
	 */
	public QualitatAPPCCControl getById(Long id) throws InfrastructureException {
		QualitatAPPCCControl qualitatAPPCCControl = null;
		try {
			qualitatAPPCCControl = (QualitatAPPCCControl)getHibernateTemplate().load(QualitatAPPCCControl.class, id);
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		return qualitatAPPCCControl;
	}

	/**
	 * Retorna un llista de tots els controls d'un perill de l'APPCC.
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public List findByPerill(Long perillId) throws InfrastructureException {
		List controls;
		try {
			logger.debug("findByPerill ini");
			String q = "select form from QualitatAPPCCControl form " +
			"where form.perill.id = " + perillId + " " +
			"order by form.etapa.order, form.id";
			controls = getHibernateTemplate().find(q);
			logger.debug("findByPerill ini");
		} catch (HibernateException ex) {
			logger.error("findByPerill failed", ex);
			throw new InfrastructureException(ex);
		}
		return controls;
	}

	/**
	 * Retorna un llista de tots els controls de tipus PCC o PC d'un establiment
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public List findPCCByEstabliment(Long establimentId) throws InfrastructureException {
		List controls;
		try {
			logger.debug("findPCCByEstabliment ini");
			String q = "select form from QualitatAPPCCControl form " +
			"where form.etapa.establiment.id = " + establimentId + " " +
			"and (form.puntControl = 'pcc' or form.puntControl = 'pc') " +
			"order by form.etapa.order, form.id";
			controls = getHibernateTemplate().find(q);
			logger.debug("findPCCByEstabliment ini");
		} catch (HibernateException ex) {
			logger.error("findPCCByEstabliment failed", ex);
			throw new InfrastructureException(ex);
		}
		return controls;
	}

	
	/**
	 * Borra un registro
	 * @param QualitatAPPCCControl
	 * @throws InfrastructureException
	 */
	public void makeTransient(QualitatAPPCCControl appccControl)
	throws InfrastructureException {
		try {
			logger.debug("makeTransient ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().delete(appccControl);
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
