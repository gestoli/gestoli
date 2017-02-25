package es.caib.gestoli.logic.dao;

import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.exception.InfrastructureException;
import es.caib.gestoli.logic.model.Marca;


/**
 * Home object for domain model class Marca
 * @see es.caib.gestoli.logic.model.Marca
 * @author Carlos Pérez
 */
public class MarcaDao {
	private static Logger logger = Logger.getLogger(MarcaDao.class);
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
			col = getHibernateTemplate().find("from Marca as mar order by mar.nom"); 
		} catch (HibernateException ex) {
			logger.error("findAll failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findAll fin");
		return col;
	}


	/**
	 * Recupera todos los registros activos
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection findAllActivos() throws InfrastructureException {
		Collection col;
		try {
			logger.debug("findAllActivos ini");
			col = getHibernateTemplate().find("from Marca as mar where mar.actiu = true order by mar.nom"); 
		} catch (HibernateException ex) {
			logger.error("findAllActivos failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findAllActivos fin");
		return col;
	}


	/**
	 * Devuelve el objeto que tiene el id
	 * @param id
	 * @return
	 * @throws InfrastructureException
	 */
	public Marca getById(Long id) throws InfrastructureException {
		Marca marca;
		try {
			logger.debug("getById ini");
			marca = (Marca)getHibernateTemplate().load(Marca.class, id);	
		} catch (HibernateException ex) {
			logger.error("getById failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getById fin");
		return marca;
	}


	/**
	 * Devuelve id  marca de la BBDD a partir de su nombre
	 * @params name
	 * @return
	 * @throws InfrastructureException
	 */
	public Long getMarca(String name) throws InfrastructureException {
		try {
			logger.debug("getMarca ini");
			List<Marca> marcas = getHibernateTemplate().findByNamedParam("from Marca where nom = :nom", "nom", name);
			if (marcas.size() > 0) {
				logger.debug("getMarca fin");
				return (Long)(marcas.get(0).getId());
			}
		} catch (HibernateException ex) {
			logger.error("getMarca failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getMarca fin");
		return null;
	}


	/**
	 * Actualiza un registro
	 * @param marca
	 * @throws InfrastructureException
	 */
	public void makePersistent(Marca marca) throws InfrastructureException {
		try {
			logger.debug("makePersistent ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().saveOrUpdate(marca);
			getHibernateTemplate().flush();
			logger.debug("makePersistent fin");
		} catch (HibernateException ex) {
			logger.error("makePersistent failed", ex);
			throw new InfrastructureException(ex);
		}
	}

	
	/**
	 * Borra un registro
	 * @param marca
	 * @throws InfrastructureException
	 */
	public void makeTransient(Marca marca)
	throws InfrastructureException {
		try {
			logger.debug("makeTransient ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().delete(marca);
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