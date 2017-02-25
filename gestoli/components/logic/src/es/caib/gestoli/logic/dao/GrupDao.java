package es.caib.gestoli.logic.dao;

import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.exception.InfrastructureException;
import es.caib.gestoli.logic.model.Grup;


/**
 * Home object for domain model class Grup
 * @see es.caib.gestoli.logic.model.Grup
 * @author Oriol Barnés
 */
public class GrupDao {
	private static Logger logger = Logger.getLogger(GrupDao.class);
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
			col = getHibernateTemplate().find("from Grup as grupo order by grupo.nom");            
		} catch (HibernateException ex) {
			logger.error("findAll failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findAll fin");
		return col;
	}


	/**
	 * Actualiza un registro
	 * @param grup
	 * @throws InfrastructureException
	 */
	public void makePersistent(Grup grup) throws InfrastructureException {
		try {
			logger.debug("makePersistent ini");
			String q = "select gru.id from Grup gru where gru.id = :id"; 
			List id = getHibernateTemplate().findByNamedParam(q, "id", grup.getId());
			// ATENCION!!
			// Hay que hacer esta validación, porque la PK es de tipo ASSIGNED,
			// si fuera NATIVE (secuencia de hibernate) con un saveOrUpdate() es suficiente
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			if (id != null && id.size() == 1) {
				getHibernateTemplate().update(grup);
			} else {
				getHibernateTemplate().save(grup);
			}
			getHibernateTemplate().flush();
			logger.debug("makePersistent fin");
		} catch (HibernateException ex) {
			logger.error("makePersistent failed", ex);
			throw new InfrastructureException(ex);
		}
	}


	/**
	 * Devuelve el objeto que tiene el id
	 * @param id
	 * @return
	 * @throws InfrastructureException
	 */
	public Grup getById(String id) throws InfrastructureException {
		Grup grupo;
		try {
			logger.debug("getById ini");
			grupo = (Grup)getHibernateTemplate().load(Grup.class, id);	
		} catch (HibernateException ex) {
			logger.error("getById failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getById fin");
		return grupo;
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