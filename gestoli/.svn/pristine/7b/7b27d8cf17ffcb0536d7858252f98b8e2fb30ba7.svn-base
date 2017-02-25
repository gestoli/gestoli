package es.caib.gestoli.logic.dao;

import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.exception.InfrastructureException;
import es.caib.gestoli.logic.model.CategoriaInformacio;


/**
 * Home object for domain model class Establiment
 * @see es.caib.gestoli.logic.model.Establiment
 * 
 */
public class CategoriaInformacioDao {
	private static Logger logger = Logger.getLogger(CategoriaInformacioDao.class);
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
			col = getHibernateTemplate().find("from CategoriaInformacio as catInf order by catInf.nom"); 
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
	public CategoriaInformacio getById(Integer id) throws InfrastructureException {

		List categorias;
		try {
			logger.debug("getById ini");
			String q = "from CategoriaInformacio as cat where cat.id = " + id;
			categorias = getHibernateTemplate().find(q);
			if (categorias.size() > 0) {
				logger.debug("getById fin");
				return (CategoriaInformacio)categorias.get(0);
			}
		} catch (HibernateException ex) {
			logger.error("getById failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getById fin");
		return null;		
	}

	
	
	/**
	 * Devuelve verdadero si existen informaciones asociadas
	 * @param id
	 * @return
	 * @throws InfrastructureException
	 */
	public boolean existenInformacionesAsociadasToCategoria(Integer id) throws InfrastructureException {

		List info;
		try {
			logger.debug("existenInformacionesAsociadasToCategoria ini");
			String q = "from Informacio as inf where inf.categoriaInformacio.id = " + id;
			info = getHibernateTemplate().find(q);
			if (info.size() > 0) {
				logger.debug("getById fin");
				return true;
			}
		} catch (HibernateException ex) {
			logger.error("existenInformacionesAsociadasToCategoria failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("existenInformacionesAsociadasToCategoria fin");
		return false;		
	}
	

	
	
	/**
	 * Devuelve el objeto que tiene el id
	 * @param id
	 * @return
	 * @throws InfrastructureException
	 */
	public boolean existeOtraCategoriaConNombre(String nom, Integer id) throws InfrastructureException {

		List info;
		try {
			logger.debug("existeOtraCategoriaConNombre ini");
			String q = "from CategoriaInformacio as cat where cat.nom = :nom and cat.id != " + id;
			info = getHibernateTemplate().findByNamedParam(q, "nom", nom);
			if (info.size() > 0) {
				logger.debug("existeOtraCategoriaConNombre fin");
				return true;
			}
		} catch (HibernateException ex) {
			logger.error("existeOtraCategoriaConNombre failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("existeOtraCategoriaConNombre fin");
		return false;		
	}
	
	
	
	

	/**
	 * Actualiza un registro
	 * @param Diposit
	 * @throws InfrastructureException
	 */
	public void makePersistent(CategoriaInformacio catInf) throws InfrastructureException {
		try {
			logger.debug("makePersistent ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().saveOrUpdate(catInf);
			getHibernateTemplate().flush();
			logger.debug("makePersistent fin");
		} catch (HibernateException ex) {
			logger.error("makePersistent failed", ex);
			throw new InfrastructureException(ex);
		}
	}


	/**
	 * Actualiza un registro
	 * @param Diposit
	 * @param session
	 * @throws InfrastructureException
	 */
	public void makePersistent(CategoriaInformacio catInf, Session session) throws InfrastructureException {
		try {
			session.setFlushMode(FlushMode.ALWAYS);
			logger.debug("makePersistent ini");
			session.saveOrUpdate(catInf);
			session.flush();
			logger.debug("makePersistent fin");
		} catch (HibernateException ex) {
			logger.error("makePersistent failed", ex);
			throw new InfrastructureException(ex);
		}
	}


	/**
	 * Borra un registro
	 * @param Diposit
	 * @throws InfrastructureException
	 */
	public void makeTransient(CategoriaInformacio catInf)
	throws InfrastructureException {
		try {
			logger.debug("makeTransient ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().delete(catInf);
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
