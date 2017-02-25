package es.caib.gestoli.logic.dao;


import java.util.Collection;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.exception.InfrastructureException;
import es.caib.gestoli.logic.model.QualitatAiguaControlOrganoleptic;


public class QualitatAiguaControlOrganolepticDao {

	private static Logger logger = Logger.getLogger(QualitatAiguaControlOrganolepticDao.class);
	private HibernateTemplate hibernateTemplate;
	
	/**
	 * Actualiza un registro
	 * @param pais
	 * @throws InfrastructureException
	 */
	public void makePersistent(QualitatAiguaControlOrganoleptic qualitatAiguaControlOrganoleptic) throws InfrastructureException {
		try {
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().saveOrUpdate(qualitatAiguaControlOrganoleptic);
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
	public QualitatAiguaControlOrganoleptic getById(Long id) throws InfrastructureException {
		QualitatAiguaControlOrganoleptic qualitatAiguaControlOrganoleptic = null;
		try {
			qualitatAiguaControlOrganoleptic = (QualitatAiguaControlOrganoleptic)getHibernateTemplate().load(QualitatAiguaControlOrganoleptic.class, id);
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		return qualitatAiguaControlOrganoleptic;
	}
	
	
	/**
	 * Recupera tots els registres.
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection findByEstabliment(Long establimentId) throws InfrastructureException {
		Collection  col;
		try {
			logger.debug("findByEstabliment ini");
			col = getHibernateTemplate().find(
					"from QualitatAiguaControlOrganoleptic " +
					"where establiment.id = " + establimentId + " " +
					"order by id");
			logger.debug("findByEstabliment fin");
		} catch (HibernateException ex) {
			logger.error("findByEstabliment failed", ex);
			throw new InfrastructureException(ex);
		}
		return col;
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
