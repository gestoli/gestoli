package es.caib.gestoli.logic.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.exception.InfrastructureException;
import es.caib.gestoli.logic.model.DocumentInspeccio;

public class DocumentInspeccioDao {
	private static Logger logger = Logger.getLogger(DocumentInspeccioDao.class);
	private HibernateTemplate hibernateTemplate;
	
	/**
	 * Actualiza un registro
	 * @param documentInspeccio
	 * @throws InfrastructureException
	 */
	public void makePersistent(DocumentInspeccio documentInspeccio) throws InfrastructureException {
		try {
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().saveOrUpdate(documentInspeccio);
			getHibernateTemplate().flush();
		} catch (HibernateException ex) {
			logger.error("makePersistent failed", ex);
			throw new InfrastructureException(ex);
		}
	}
	
	/**
	 * DocumentInspeccio amb la id indicada.
	 * 
	 * @param id
	 */
	public DocumentInspeccio getById(Long id) throws InfrastructureException {
		DocumentInspeccio documentInspeccio = null;
		try {
			documentInspeccio = (DocumentInspeccio)getHibernateTemplate().load(DocumentInspeccio.class, id);
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		return documentInspeccio;
	}
	
	/**
	 * Recupera todos los registros por establecimiento
	 * @return
	 * @throws InfrastructureException
	 */
	public List findByEstabliment(Long idEstabliment) throws InfrastructureException {
		List col;
		try {
			logger.debug("findByEstabliment ini");
			String q = "from DocumentInspeccio as doc " +
					"where doc.establiment is not null and " +
					"doc.establiment.id = " + idEstabliment + " " +
					"order by doc.tipus, doc.data desc";
			
			col = getHibernateTemplate().find(q);			
		} catch (HibernateException ex) {
			logger.error("findByEstabliment failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findByEstabliment fin");
		return col; 
	}
	
	/**
	 * Recupera todos los registros por informacion
	 * @return
	 * @throws InfrastructureException
	 */
	public List findByOlivicultor(Long idOlivicultor) throws InfrastructureException {
		List col;
		try {
			logger.debug("findByOlivicultor ini");
			String q = "from DocumentInspeccio as doc " +
					"where doc.olivicultor is not null and " +
					"doc.olivicultor.id = " + idOlivicultor + " " +
					"order by doc.tipus, doc.data desc";
			
			col = getHibernateTemplate().find(q);			
		} catch (HibernateException ex) {
			logger.error("findByOlivicultor failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findByOlivicultor fin");
		return col; 
	}
	
	/**
	 * Borra un registro
	 * @param DocumentInspeccio
	 * @throws InfrastructureException
	 */
	public void makeTransient(DocumentInspeccio document)
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