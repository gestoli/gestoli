package es.caib.gestoli.logic.dao;

import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.exception.InfrastructureException;
import es.caib.gestoli.logic.model.Pais;
import es.caib.gestoli.logic.model.QualitatDescripcioInstalacio;


public class QualitatDescripcioInstalacioDao {

	private static Logger logger = Logger.getLogger(QualitatDescripcioInstalacioDao.class);
	private HibernateTemplate hibernateTemplate;
	
	/**
	 * Actualiza un registro
	 * @param pais
	 * @throws InfrastructureException
	 */
	public void makePersistent(QualitatDescripcioInstalacio qualitatDescripcioInstalacio) throws InfrastructureException {
		try {
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().saveOrUpdate(qualitatDescripcioInstalacio);
			getHibernateTemplate().flush();
		} catch (HibernateException ex) {
			logger.error("makePersistent failed", ex);
			throw new InfrastructureException(ex);
		}
	}
	
	/**
	 * Pais amb la id indicada.
	 * 
	 * @param id
	 */
	public QualitatDescripcioInstalacio getById(Long id) throws InfrastructureException {
		QualitatDescripcioInstalacio qualitatDescripcioInstalacio = null;
		try {
			qualitatDescripcioInstalacio = (QualitatDescripcioInstalacio)getHibernateTemplate().load(QualitatDescripcioInstalacio.class, id);
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		return qualitatDescripcioInstalacio;
	}

	/**
	 * Recupera la descripcio d'un establiment.
	 * @return
	 * @throws InfrastructureException
	 */
	public QualitatDescripcioInstalacio getByEstabliment(Long establimentId) throws InfrastructureException {
		List col;
		try {
			logger.debug("findByEstabliment ini");
			col = getHibernateTemplate().find(
					"from QualitatDescripcioInstalacio " +
					"where establiment.id = " + establimentId);
			logger.debug("findByEstabliment ini");
		} catch (HibernateException ex) {
			logger.error("findByEstabliment failed", ex);
			throw new InfrastructureException(ex);
		}
		if (col.size() > 0){
			return (QualitatDescripcioInstalacio)col.get(0);
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
