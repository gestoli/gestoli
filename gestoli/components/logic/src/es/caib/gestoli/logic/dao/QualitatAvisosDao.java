package es.caib.gestoli.logic.dao;

import java.sql.Timestamp;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.exception.InfrastructureException;
import es.caib.gestoli.logic.model.Avis;

public class QualitatAvisosDao {
	private static Logger logger = Logger.getLogger(QualitatAvisosDao.class);
	private HibernateTemplate hibernateTemplate;
	
	/**
	 * Actualiza un registro
	 * @param avis
	 * @throws InfrastructureException
	 */
	public void makePersistent(Avis avis) throws InfrastructureException {
		try {
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().saveOrUpdate(avis);
			getHibernateTemplate().flush();
		} catch (HibernateException ex) {
			logger.error("makePersistent failed", ex);
			throw new InfrastructureException(ex);
		}
	}
	
	/**
	 * Avis amb la id indicada.
	 * 
	 * @param id
	 */
	public Avis getById(Long id) throws InfrastructureException {
		Avis avis = null;
		try {
			avis = (Avis)getHibernateTemplate().load(Avis.class, id);
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		return avis;
	}

	/**
	 * Retorna un llista de tot el personal associats amb un establiment.
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public List findByEstabliment(Long establimentId) throws InfrastructureException {
		List avisos;
		try {
			logger.debug("findByEstabliment ini");
			String q = "select form from Avis form " +
			"where form.establiment.id = " + establimentId + " " +
			"order by form.id desc";
			avisos = getHibernateTemplate().find(q);
			logger.debug("findByEstabliment ini");
		} catch (HibernateException ex) {
			logger.error("findByEstabliment failed", ex);
			throw new InfrastructureException(ex);
		}
		return avisos;
	}

	/**
	 * Devuelve la lista de avisos pendientes de ser tratados de un establecimiento.
	 * @param 
	 * @return
	 * @throws InfrastructureException
	 */
	public List cercaPendentsByEstabliment(Long establimentId) throws InfrastructureException {
		List avisos;
		java.util.Date today = new java.util.Date();
		Timestamp data = new Timestamp(today.getTime());
		 
		try {
			logger.debug("findByEstabliment ini");
			String q = "select form from Avis form " +
			"where form.establiment.id = " + establimentId + " " +
			"and   form.dataSeguent < '" + data + "' " +
			"order by form.id desc";
			avisos = getHibernateTemplate().find(q);
			logger.debug("findByEstabliment ini");
		} catch (HibernateException ex) {
			logger.error("findByEstabliment failed", ex);
			throw new InfrastructureException(ex);
		}
		
		return avisos;
	}
	
	/**
	 * Devuelve boolean true si existen avisos pendientes de ser aceptados 
	 * @param 
	 * @return
	 * @throws InfrastructureException
	 */
	public Boolean existeixenPendentsByEstabliment(Long establimentId) throws InfrastructureException {
		List avisos = cercaPendentsByEstabliment(establimentId);
				
		if(avisos!= null && avisos.size()>0){
			return true;
		} else {
			return false;
		}
	}
		
	/**
	 * Borra un registro
	 * @param Avis
	 * @throws InfrastructureException
	 */
	public void makeTransient(Avis avis)
	throws InfrastructureException {
		try {
			logger.debug("makeTransient ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().delete(avis);
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
