package es.caib.gestoli.logic.dao;

import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.exception.InfrastructureException;
import es.caib.gestoli.logic.model.Taxa;


/**
 * Home object for domain model class Taxa
 * @see es.caib.gestoli.logic.model.Taxa
 * @author Oriol Barnés
 */
public class TaxaDao {
	private static Logger logger = Logger.getLogger(TaxaDao.class);
	private HibernateTemplate hibernateTemplate;


	/**
	 * Recupera todos los registros
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection findAll() throws InfrastructureException {
		Collection col;
		try {
			logger.debug("findAll ini");
			col = getHibernateTemplate().find("from Taxa");
		} catch (HibernateException ex) {
			logger.error("findAll failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findAll fin");
		return col;
	}

	/**
	 * Recupera todos los registros de una campanya determinada
	 * @param campanyaId
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection findAllByCampanya(Long campanyaId) throws InfrastructureException {
		Collection col;
		try {
			logger.debug("findAllByCampanya ini");
			col = getHibernateTemplate().find("from Taxa as tax where tax.id = "+campanyaId+" order by tax.id"); 
		} catch (HibernateException ex) {
			logger.error("findAllByCampanya failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findAllByCampanya fin");
		return col; 

	}

	/**
	 * Recupera todos los registros
	 * @return
	 * @throws InfrastructureException
	 */
	public Taxa getTaxaActual() throws InfrastructureException {
		Collection col;
		Taxa tax = null;
		try {
			logger.debug("getTaxaActual ini");
			col = getHibernateTemplate().find("from Taxa as tax where tax.id = (select max(cam.id) from Campanya cam)");
			while (col.iterator().hasNext()){
				tax = (Taxa)col.iterator().next();
				break;
			}
		} catch (HibernateException ex) {
			logger.error("getTaxaActual failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getTaxaActual fin");
		return tax;
	}
	//
	//
	//	
	//	/**
	//	 * Actualiza un registro
	//	 * @param taxa
	//	 * @throws InfrastructureException
	//	 */
	//	public void makePersistent(Taxa taxa) throws InfrastructureException {
	//		try {
	//			Session session = getHibernateTemplate();
	//			session.saveOrUpdate(taxa);
	//			session.flush();
	//			logger.debug("makePersistent fin");
	//		} catch (HibernateException ex) {
	//			logger.error("makePersistent failed", ex);
	//			throw new InfrastructureException(ex);
	//		}
	//	}


	/**
	 * Actualiza un registro
	 * @param grup
	 * @throws InfrastructureException
	 */
	public void makePersistent(Taxa taxa) throws InfrastructureException {
		try {
			logger.debug("makePersistent ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			
			List id = getHibernateTemplate().find("select tax.id from Taxa tax where tax.id = " + taxa.getId().intValue() + " ");
			// ATENCION!!
			// Hay que hacer esta validación, porque la PK es de tipo ASSIGNED,
			// si fuera NATIVE (secuencia de hibernate) con un saveOrUpdate() es suficiente
			if (id != null && id.size() == 1) {
				getHibernateTemplate().update(taxa);
			} else {
				getHibernateTemplate().save(taxa);
			}
			getHibernateTemplate().flush();
		} catch (HibernateException ex) {
			logger.error("makePersistent failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("makePersistent fin");
	}


	/**
	 * Actualiza un registro
	 * @param grup
	 * @throws InfrastructureException
	 */
	public void makePersistent(Taxa taxa, Session session) throws InfrastructureException {
		try {
			logger.debug("makePersistent fin");
			session.setFlushMode(FlushMode.ALWAYS);
			session.save(taxa);
			session.flush();
			logger.debug("makePersistent fin");
		} catch (HibernateException ex) {
			logger.error("makePersistent failed", ex);
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