package es.caib.gestoli.logic.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.exception.InfrastructureException;
import es.caib.gestoli.logic.model.GestorDocumentalDocument;


/**
 * Home object for domain model class Informacio
 * @see es.caib.gestoli.logic.model.Informacio
 * 
 */
public class GestorDocumentalDocumentDao {
	private static Logger logger = Logger.getLogger(GestorDocumentalDocumentDao.class);
	private HibernateTemplate hibernateTemplate;


//	/**
//	 * Recupera todos los registros
//	 * @return
//	 * @throws InfrastructureException
//	 */
//	public Collection findAll() throws InfrastructureException {
//		Collection col;
//		try {
//			logger.debug("findAll ini");
//			col = getHibernateTemplate().find("from Document as doc");			
//		} catch (HibernateException ex) {
//			logger.error("findAll failed", ex);
//			throw new InfrastructureException(ex);
//		}
//		logger.debug("findAll fin");
//		return col; 
//
//	}


	/**
	 * Devuelve el objeto que tiene el id
	 * @param id
	 * @return
	 * @throws InfrastructureException
	 */
	public GestorDocumentalDocument getById(Integer id) throws InfrastructureException {

		List infos;
		try {
			logger.debug("getById ini");
			String q = "from GestorDocumentalDocument as doc where doc.id = '"+id+"'";
			infos = getHibernateTemplate().find(q);
			if (infos.size() > 0) {
				logger.debug("getById fin");
				return (GestorDocumentalDocument)infos.get(0);
			}

		} catch (HibernateException ex) {
			logger.error("getById failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getById fin");
		return null;		

	}

	/**
	 * Recupera todos los registros por informacion
	 * @return
	 * @throws InfrastructureException
	 */
	public List findByInformacio(Integer infoId) throws InfrastructureException {
		List col;
		try {
			logger.debug("findByInformacio ini");
			String q = "from GestorDocumentalDocument as doc " +
					"where doc.gestorDocumentalInformacio.id = " + infoId + " " +
					"order by doc.id";
			
			col = getHibernateTemplate().find(q);			
		} catch (HibernateException ex) {
			logger.error("findByInformacio failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findByInformacio fin");
		return col; 

	}

	/**
	 * Actualiza un registro
	 * @param Informacio
	 * @throws InfrastructureException
	 */
	public void makePersistent(GestorDocumentalDocument document) throws InfrastructureException {
		try {
			logger.debug("makePersistent ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().saveOrUpdate(document);
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
	public void makePersistent(GestorDocumentalDocument document, Session session) throws InfrastructureException {
		try {
			logger.debug("makePersistent ini");
			session.setFlushMode(FlushMode.ALWAYS);
			session.saveOrUpdate(document);
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
	public void makeTransient(GestorDocumentalDocument document)
	throws InfrastructureException {
		try {
			logger.debug("makeTransient ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().delete(document);
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