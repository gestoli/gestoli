package es.caib.gestoli.logic.dao;

import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.exception.InfrastructureException;
import es.caib.gestoli.logic.model.Arxiu;


/**
 * Home object for domain model class Arxiu
 * @see es.caib.gestoli.logic.model.Arxiu
 * @author Oriol Barnés
 */
public class ArxiuDao {
	private static Logger logger = Logger.getLogger(ArxiuDao.class);
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
			col = getHibernateTemplate().find("from Arxiu");
		} catch (HibernateException ex) {
			logger.error("findAll failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findAll fin");
		return col;
	}

	
	/**
	 * Actualiza un registro
	 * @param arxiu
	 * @throws InfrastructureException
	 */
	public void makePersistent(Arxiu arxiu) throws InfrastructureException {
		try {
			logger.debug("makePersistent ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().saveOrUpdate(arxiu);
			getHibernateTemplate().flush();
			logger.debug("makePersistent fin");
		} catch (HibernateException ex) {
			logger.error("makePersistent failed", ex);
			throw new InfrastructureException(ex);
		}
	}
	
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws InfrastructureException
	 */
	public Arxiu getById(Long id) throws InfrastructureException {
		try {
			logger.debug("getById ini");
			if (id != null) {
				String q = "from Arxiu as arx where arx.id = '" + id + "'";
				
				if(getHibernateTemplate() != null){
					List arxius = getHibernateTemplate().find(q);
					if ((arxius != null) && (arxius.size() > 0)) {
						return (Arxiu)arxius.get(0);
					}
				}
			}
		}  catch (HibernateException ex) {
			logger.error("getById failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getById fin");
		return null;
	}

	
	/**
	 * 
	 * @param arxiu
	 * @throws InfrastructureException
	 */
	public void makeTransient(Arxiu arxiu)	throws InfrastructureException {
		try {
			logger.debug("makeTransient ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().delete(arxiu);
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
