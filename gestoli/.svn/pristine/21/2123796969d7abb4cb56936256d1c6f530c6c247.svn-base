package es.caib.gestoli.logic.dao;

import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.exception.InfrastructureException;
import es.caib.gestoli.logic.model.VarietatOli;


/**
 * Home object for domain model class VarietatOli
 * @see es.caib.gestoli.logic.model.VarietatOli
 * @author Oriol Barnés
 */
public class VarietatOliDao {
	private static Logger logger = Logger.getLogger(VarietatOliDao.class);
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
			col = getHibernateTemplate().find("from VarietatOli as vol order by vol.nom"); 
			logger.debug("findAll fin");
		} catch (HibernateException ex) {
			logger.error("findAll failed", ex);
			throw new InfrastructureException(ex);
		}
		return col;
	}
	
	
	/**
	 * Devuelve el objeto que tiene el id
	 * @param id
	 * @return
	 * @throws InfrastructureException
	 */
	public VarietatOli getById(Integer id) throws InfrastructureException {
				
		List variedades;
		try {
			logger.debug("getById ini");
			String q = "from VarietatOli as vol where vol.id = '"+id+"'";
			variedades = getHibernateTemplate().find(q);
			if (variedades.size() > 0) {
				logger.debug("getById fin");
				return (VarietatOli)variedades.get(0);
			}

		} catch (HibernateException ex) {
			logger.error("getById failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getById fin");
		return null;		
		
	}
	
	
	/**
	 * Actualiza un registro
	 * @param varietatOli
	 * @throws InfrastructureException
	 */
	public void makePersistent(VarietatOli varietatOli) throws InfrastructureException {
		try {
			logger.debug("makePersistent ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().saveOrUpdate(varietatOli);
			getHibernateTemplate().flush();
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