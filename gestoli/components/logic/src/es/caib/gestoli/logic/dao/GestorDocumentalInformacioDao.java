package es.caib.gestoli.logic.dao;

import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.exception.InfrastructureException;
import es.caib.gestoli.logic.model.GestorDocumentalInformacio;


/**
 * Home object for domain model class Informacio
 * @see es.caib.gestoli.logic.model.Informacio
 * 
 */
public class GestorDocumentalInformacioDao {
	private static Logger logger = Logger.getLogger(GestorDocumentalInformacioDao.class);
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
			col = getHibernateTemplate().find("from GestorDocumentalInformacio as inf order by inf.data desc");			
		} catch (HibernateException ex) {
			logger.error("findAll failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findAll fin");
		return col; 

	}
	
	/**
	 * Recupera todos los registros
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection findAllByEstat(Integer estat) throws InfrastructureException {
		Collection col;
		try {
			logger.debug("findAll ini");
			col = getHibernateTemplate().find("from GestorDocumentalInformacio as inf where inf.estat="+estat+" order by inf.data desc");			
		} catch (HibernateException ex) {
			logger.error("findAll failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findAll fin");
		return col; 

	}

	
	
	/**
	 * Recupera todos los registros con Filtro de categoria
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection findAllFiltre(Integer categoria) throws InfrastructureException {
		Collection col;
		try {
			logger.debug("findAllFiltre ini");
			col = getHibernateTemplate().find("from GestorDocumentalInformacio as inf where inf.gestorDocumentalCategoriaInformacio.id="+categoria+" order by inf.data desc");			
		} catch (HibernateException ex) {
			logger.error("findAllFiltre failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findAllFiltre fin");
		return col; 
	}
	
	/**
	 * Recupera todos los registros con Filtro de categoria
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection findAllFiltreIEstat(Integer categoria, Integer estat) throws InfrastructureException {
		Collection col;
		try {
			logger.debug("findAllFiltre ini");
			col = getHibernateTemplate().find("from GestorDocumentalInformacio as inf where inf.gestorDocumentalCategoriaInformacio.id="+categoria+" and inf.estat="+estat+" order by inf.data desc");			
		} catch (HibernateException ex) {
			logger.error("findAllFiltre failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findAllFiltre fin");
		return col; 
	}
	
	
	/**
	 * Recupera todos los registros relacionados con los establecimientos
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection findUsuarisByInformacio(Integer idInformacio) throws InfrastructureException {
		Collection col;
		try {
			logger.debug("findUsuarisByInformacio ini");
			String q = "select inf.usuaris from GestorDocumentalInformacio as inf where inf.id = "+idInformacio;
			
			col = getHibernateTemplate().find(q);			
		} catch (HibernateException ex) {
			logger.error("findUsuarisByInformacio failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findUsuarisByInformacio fin");
		return col; 

	}
	
//	/**
//	 * Recupera todos los registros relacionados con los olivicultores
//	 * @return
//	 * @throws InfrastructureException
//	 */
//	public Collection findOlivicultorsByInformacio(Integer idInformacio) throws InfrastructureException {
//		Collection col;
//		try {
//			logger.debug("findOlivicultorsByInformacio ini");
//			String q = "select inf.olivicultors from Informacio as inf where inf.id = "+idInformacio;
//			
//			col = getHibernateTemplate().find(q);			
//		} catch (HibernateException ex) {
//			logger.error("findOlivicultorsByInformacio failed", ex);
//			throw new InfrastructureException(ex);
//		}
//		logger.debug("findOlivicultorsByInformacio fin");
//		return col; 
//
//	}

	/**
	 * Devuelve el objeto que tiene el id
	 * @param id
	 * @return
	 * @throws InfrastructureException
	 */
	public GestorDocumentalInformacio getById(Integer id) throws InfrastructureException {

		List infos;
		try {
			logger.debug("getById ini");
			String q = "from GestorDocumentalInformacio as inf where inf.id = '"+id+"'";
			infos = getHibernateTemplate().find(q);
			if (infos.size() > 0) {
				logger.debug("getById fin");
				return (GestorDocumentalInformacio)infos.get(0);
			}

		} catch (HibernateException ex) {
			logger.error("getById failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getById fin");
		return null;		

	}

	
//	/**
//	 * Indica si existen informaciones
//	 * @return
//	 * @throws InfrastructureException
//	 */
//	public boolean existenInformaciones() throws InfrastructureException {
//		try {
//			logger.debug("existenInformaciones ini");
//			
//			Collection infos = findAll();
//			if ((infos != null) && (infos.size() > 0)) {
//				logger.debug("existenInformaciones fin");
//				return true;
//			}
//		} catch (HibernateException ex) {
//			logger.error("existenInformaciones failed", ex);
//			throw new InfrastructureException(ex);
//		}
//		logger.debug("existenInformaciones fin");
//		return false;		
//	}


	/**
	 * Actualiza un registro
	 * @param Informacio
	 * @throws InfrastructureException
	 */
	public void makePersistent(GestorDocumentalInformacio informacio) throws InfrastructureException {
		try {
			logger.debug("makePersistent ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().saveOrUpdate(informacio);
			getHibernateTemplate().flush();
			logger.debug("makePersistent fin");
		} catch (HibernateException ex) {
			logger.error("makePersistent failed", ex);
			throw new InfrastructureException(ex);
		}
	}

	/**
	 * Actualiza un registro
	 * @param Informacio
	 * @throws InfrastructureException
	 */
	public void makePersistent(GestorDocumentalInformacio informacio, Session session) throws InfrastructureException {
		try {
			logger.debug("makePersistent ini");
			session.setFlushMode(FlushMode.ALWAYS);
			session.saveOrUpdate(informacio);
			session.flush();
			logger.debug("makePersistent fin");
		} catch (HibernateException ex) {
			logger.error("makePersistent failed", ex);
			throw new InfrastructureException(ex);
		}
	}

	/**
	 * Borra un registro
	 * @param Informacio
	 * @throws InfrastructureException
	 */
	public void makeTransient(GestorDocumentalInformacio informacio)
	throws InfrastructureException {
		try {
			logger.debug("makeTransient ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().delete(informacio);
			getHibernateTemplate().flush();
			logger.debug("makeTransient fin");
		} catch (HibernateException ex) {
			logger.error("makeTransient failed",ex);
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