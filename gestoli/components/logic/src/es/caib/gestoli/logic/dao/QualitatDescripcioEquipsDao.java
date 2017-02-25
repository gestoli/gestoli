package es.caib.gestoli.logic.dao;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.exception.InfrastructureException;
import es.caib.gestoli.logic.model.QualitatDescripcioEquip;
import es.caib.gestoli.logic.model.QualitatDescripcioInstalacio;
import es.caib.gestoli.logic.model.QualitatDescripcioPersonal;


public class QualitatDescripcioEquipsDao {

	private static Logger logger = Logger.getLogger(QualitatDescripcioEquipsDao.class);
	private HibernateTemplate hibernateTemplate;
	
	/**
	 * Actualiza un registro
	 * @param QualitatDescripcioEquip
	 * @throws InfrastructureException
	 */
	public void makePersistent(QualitatDescripcioEquip qualitatDescripcioEquip) throws InfrastructureException {
		try {
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().saveOrUpdate(qualitatDescripcioEquip);
			getHibernateTemplate().flush();
		} catch (HibernateException ex) {
			logger.error("makePersistent failed", ex);
			throw new InfrastructureException(ex);
		}
	}
	
	/**
	 * QualitatDescripcioEquip amb la id indicada.
	 * 
	 * @param id
	 */
	public QualitatDescripcioEquip getById(Long id) throws InfrastructureException {
		QualitatDescripcioEquip qualitatDescripcioEquip = null;
		try {
			qualitatDescripcioEquip = (QualitatDescripcioEquip)getHibernateTemplate().load(QualitatDescripcioEquip.class, id);
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		return qualitatDescripcioEquip;
	}
	

	/**
	 * Recupera tots els registres.
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection getAllByEstabliment(Long establimentId) throws InfrastructureException {
		Collection  col;
		try {
			logger.debug("findByEstabliment ini");
			col = getHibernateTemplate().find(
					"from QualitatDescripcioEquip " +
					"where establiment.id = " + establimentId + " " +
					"order by codi");
			logger.debug("findByEstabliment ini");
		} catch (HibernateException ex) {
			logger.error("findByEstabliment failed", ex);
			throw new InfrastructureException(ex);
		}
		return col;
	}
	
	
	/**
	 * Recupera tots els equips que són vehicles
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection getVehiclesByEstabliment(Long establimentId) throws InfrastructureException {
		Collection  col;
		try {
			logger.debug("findByEstabliment ini");
			col = getHibernateTemplate().find(
					"from QualitatDescripcioEquip " +
					"where establiment.id = " + establimentId + " " +
					"and esVehicle = " + true + " " +
					"order by codi");
			logger.debug("findByEstabliment ini");
		} catch (HibernateException ex) {
			logger.error("findByEstabliment failed", ex);
			throw new InfrastructureException(ex);
		}
		return col;
	}

	
	/**
	 * Borra un registro
	 * @param QualitatDescripcioEquip
	 * @throws InfrastructureException
	 */
	public void makeTransient(QualitatDescripcioEquip equip)
	throws InfrastructureException {
		try {
			logger.debug("makeTransient ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().delete(equip);
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
