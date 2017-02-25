package es.caib.gestoli.logic.dao;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.exception.InfrastructureException;
import es.caib.gestoli.logic.model.MaterialTipusEnvas;


/**
 * Home object for domain model class MaterialTipusEnvas
 * @see es.caib.gestoli.logic.model.MaterialTipusEnvas
 * @author Oriol Barnés
 */
public class MaterialTipusEnvasDao {
	private static Logger logger = Logger.getLogger(MaterialTipusEnvasDao.class);
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
			col = getHibernateTemplate().find("from MaterialTipusEnvas as men order by men.nom"); 
		} catch (HibernateException ex) {
			logger.error("findAll failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findAll fin");
		return col;
	}


	/**
	 * Devuelve el objeto que tiene el id
	 * @param id
	 * @return
	 * @throws InfrastructureException
	 */
	public MaterialTipusEnvas getById(Integer id) throws InfrastructureException {
		MaterialTipusEnvas materialTipusEnvas;
		try {
			logger.debug("getById ini");
			materialTipusEnvas = (MaterialTipusEnvas)getHibernateTemplate().load(MaterialTipusEnvas.class, id);	
		} catch (HibernateException ex) {
			logger.error("getById failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getById fin");
		return materialTipusEnvas;
	}


	/**
	 * Actualiza un registro
	 * @param materialTipusEnvas
	 * @throws InfrastructureException
	 */
	public void makePersistent(MaterialTipusEnvas materialTipusEnvas) throws InfrastructureException {
		try {
			logger.debug("makePersistent ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().saveOrUpdate(materialTipusEnvas);
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