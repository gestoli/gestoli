package es.caib.gestoli.logic.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.exception.InfrastructureException;
import es.caib.gestoli.logic.model.QualitatNoConformitat;
import es.caib.gestoli.logic.model.QualitatNoConformitatAccio;

public class QualitatNoConformitatDao {

	private static Logger logger = Logger.getLogger(QualitatNoConformitatDao.class);
	private HibernateTemplate hibernateTemplate;
	
	/**
	 * Actualiza un registro
	 * @param qualitatNoConformitat
	 * @throws InfrastructureException
	 */
	public void makePersistent(QualitatNoConformitat qualitatNoConformitat) throws InfrastructureException {
		try {
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().saveOrUpdate(qualitatNoConformitat);
			getHibernateTemplate().flush();
		} catch (HibernateException ex) {
			logger.error("makePersistent failed", ex);
			throw new InfrastructureException(ex);
		}
	}
	

	/**
	 * QualitatNoConformitat amb la id indicada.
	 * 
	 * @param id
	 */
	public QualitatNoConformitat getById(Long id) throws InfrastructureException {
		QualitatNoConformitat qualitatNoConformitat = null;
		try {
			qualitatNoConformitat = (QualitatNoConformitat)getHibernateTemplate().load(QualitatNoConformitat.class, id);
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		return qualitatNoConformitat;
	}

	/**
	 * Retorna un llista de totes les noConformitats associades a un establiment.
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public List findByEstabliment(Long establimentId) throws InfrastructureException {
		List noConformitats;
		try {
			logger.debug("findByEstabliment ini");
			String q = "select form from QualitatNoConformitat form " +
			"where form.establiment.id = " + establimentId + " " +
			"order by form.dataNoConformitat desc";
			noConformitats = getHibernateTemplate().find(q);
			logger.debug("findByEstabliment ini");
		} catch (HibernateException ex) {
			logger.error("findByEstabliment failed", ex);
			throw new InfrastructureException(ex);
		}
		return noConformitats;
	}

	/**
	 * Retorna un llista de totes les noConformitats associades a un tipus.
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public List findByTipus(Long establimentId, Long tipusId) throws InfrastructureException {
		List noConformitats;
		try {
			logger.debug("findByTipus ini");
			String q = "select form from QualitatNoConformitat form " +
			"where form.establiment.id = " + establimentId + " " +
			"  and form.tipus.id = " + tipusId + " " +
			"order by form.dataNoConformitat desc";
			noConformitats = getHibernateTemplate().find(q);
			logger.debug("findByTipus ini");
		} catch (HibernateException ex) {
			logger.error("findByTipus failed", ex);
			throw new InfrastructureException(ex);
		}
		return noConformitats;
	}
	

	/**
	 * Retorna un llista de totes les noConformitats associades a un control.
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public List findByControl(Long idControl) throws InfrastructureException {
		List noConformitats;
		try {
			logger.debug("findByControl ini");
			String q = "select form from QualitatNoConformitat form " +
			"where form.control.id = " + idControl + " " +
			"order by form.dataNoConformitat desc";
			noConformitats = getHibernateTemplate().find(q);
			logger.debug("findByControl ini");
		} catch (HibernateException ex) {
			logger.error("findByControl failed", ex);
			throw new InfrastructureException(ex);
		}
		return noConformitats;
	}
	
	/**
	 * Borra un registro
	 * @param QualitatNoConformitat
	 * @throws InfrastructureException
	 */
	public void makeTransient(QualitatNoConformitat noConformitat)
	throws InfrastructureException {
		try {
			logger.debug("makeTransient ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().delete(noConformitat);
			getHibernateTemplate().flush();
			logger.debug("makeTransient fin");
		} catch (HibernateException ex) {
			logger.error("makeTransient failed", ex);
			throw new InfrastructureException(ex);
		}
	}

	
	// ** ACCIONS ** //
	
	/**
	 * Actualiza un registro
	 * @param qualitatNoConformitatAccio
	 * @throws InfrastructureException
	 */
	public void makePersistentAccio(QualitatNoConformitatAccio qualitatNoConformitatAccio) throws InfrastructureException {
		try {
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().saveOrUpdate(qualitatNoConformitatAccio);
			getHibernateTemplate().flush();
		} catch (HibernateException ex) {
			logger.error("makePersistentAccio failed", ex);
			throw new InfrastructureException(ex);
		}
	}

	/**
	 * QualitatNoConformitatAccio amb la id indicada.
	 * 
	 * @param id
	 */
	public QualitatNoConformitatAccio getAccioById(Long id) throws InfrastructureException {
		QualitatNoConformitatAccio qualitatNoConformitatAccio = null;
		try {
			qualitatNoConformitatAccio = (QualitatNoConformitatAccio)getHibernateTemplate().load(QualitatNoConformitatAccio.class, id);
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		return qualitatNoConformitatAccio;
	}
	
	/**
	 * Retorna un llista de totes les accions associades a una noConformitat
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public List findAccionsByNoConformitat(Long noConformitatId) throws InfrastructureException {
		List accions;
		try {
			logger.debug("findAccionsByNoConformitat ini");
			String q = "select form from QualitatNoConformitatAccio form " +
			"where form.noConformitat.id = " + noConformitatId + " " +
			"order by form.accio";
			accions = getHibernateTemplate().find(q);
			logger.debug("findAccionsByNoConformitat ini");
		} catch (HibernateException ex) {
			logger.error("findAccionsByNoConformitat failed", ex);
			throw new InfrastructureException(ex);
		}
		return accions;
	}
	
	/**
	 * Retorna el codi més alt de les accions associades a una noConformitat
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public Long findMaxAccioByNoConformitat(Long noConformitatId) throws InfrastructureException {
		List codis;
		try {
			logger.debug("findMaxAccioByNoConformitat ini");
			String q = "select max(form.codi) from QualitatNoConformitatAccio form " +
			"where form.noConformitat.id = " + noConformitatId;
			codis = getHibernateTemplate().find(q);
			logger.debug("findMaxAccioByNoConformitat ini");
		} catch (HibernateException ex) {
			logger.error("findMaxAccioByNoConformitat failed", ex);
			throw new InfrastructureException(ex);
		}
		if (codis.size() > 0){
			if (codis.get(0) == null){
				return Long.valueOf("0");	
			} else {
				return (Long)codis.get(0);
			}
		} else {
			return Long.valueOf("0");
		}
	}
	
	/**
	 * Borra un registro
	 * @param QualitatNoConformitatAccio
	 * @throws InfrastructureException
	 */
	public void makeTransientAccio(QualitatNoConformitatAccio noConformitatAccio)
	throws InfrastructureException {
		try {
			logger.debug("makeTransientAccio ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().delete(noConformitatAccio);
			getHibernateTemplate().flush();
			logger.debug("makeTransientAccio fin");
		} catch (HibernateException ex) {
			logger.error("makeTransientAccio failed", ex);
			throw new InfrastructureException(ex);
		}
	}

	/**
	 * Retorna un llista de totes les noConformitats associades a un establiment.
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public List findObertesByEstabliment(Long establimentId) throws InfrastructureException {
		List noConformitats;
		try {
			logger.debug("findByEstabliment ini");
			String q = "select form from QualitatNoConformitat form " +
			"where form.establiment.id = " + establimentId + " " +
			"and   form.dataTancament is null " +
			"order by form.dataNoConformitat";
			noConformitats = getHibernateTemplate().find(q);
			logger.debug("findByEstabliment ini");
		} catch (HibernateException ex) {
			logger.error("findByEstabliment failed", ex);
			throw new InfrastructureException(ex);
		}
		return noConformitats;
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
