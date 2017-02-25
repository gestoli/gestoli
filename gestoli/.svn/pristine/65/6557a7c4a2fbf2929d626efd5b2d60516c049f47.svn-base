package es.caib.gestoli.logic.dao;

import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.exception.InfrastructureException;
import es.caib.gestoli.logic.model.Solicitant;


/**
 * Home object for domain model class TipusEnvas
 * @see es.caib.gestoli.logic.model.TipusEnvas
 */
public class SolicitanteDao {
	private static Logger logger = Logger.getLogger(SolicitanteDao.class);
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
			col = getHibernateTemplate().find("from Solicitant");
		} catch (HibernateException ex) {
			logger.error("findAll failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findAll fin");
		return col;
	}


	/**
	 * Actualiza un registro
	 * @param solicitant
	 * @throws InfrastructureException
	 */
	public Long makePersistent(Solicitant solicitant) throws InfrastructureException {
		try {
			logger.debug("makePersistent ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().saveOrUpdate(solicitant);
			getHibernateTemplate().flush();
			
		} catch (HibernateException ex) {
			logger.error("makePersistent failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("makePersistent fin");
		return solicitant.getId();
	}


	/**
	 * Borra un registro
	 * @param solicitant
	 * @throws InfrastructureException
	 */
	public void makeTransient(Solicitant solicitant)
	throws InfrastructureException {
		try {
			logger.debug("makeTransient ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().delete(solicitant);
			getHibernateTemplate().flush();
		} catch (HibernateException ex) {
			logger.error("makeTransient failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("makeTransient fin");
	}


	/**
	 * Devuelve el objeto que tiene el id
	 * @param id
	 * @return
	 * @throws InfrastructureException
	 */
	public Solicitant getById(Long id) throws InfrastructureException {
		List solicitantes;
		try {
			logger.debug("getById ini");
			
			String q = "from Solicitant as sol where sol.id = '"+id+"'";
			solicitantes = getHibernateTemplate().find(q);
			if (solicitantes.size() > 0) {
				logger.debug("getById fin");
				return (Solicitant)solicitantes.get(0);
			}

		} catch (HibernateException ex) {
			logger.error("getById failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getById fin");
		return null;	
	}

	
	/**
	 * Devuelve el nombre del solicitante usando el id de establecimiento
	 * @param id
	 * @return
	 * @throws InfrastructureException
	 */
	public String getNameByEstablimentId(Long id) throws InfrastructureException {
		List solicitante;
		try {
			logger.debug("getNameByEstablimentId ini");
			
			String q = "select est.solicitant.nom from Establiment as est where est.id = '"+id+"'";
			solicitante = getHibernateTemplate().find(q);
			if (solicitante.size() > 0) {
				logger.debug("getNameByEstablimentId fin");
				return (String)solicitante.get(0);
			}

		} catch (HibernateException ex) {
			logger.error("getNameByEstablimentId failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getNameByEstablimentId fin");
		return null;	
	}
	
	
	
	/**
	 * Devuelve el nif del solicitante usando el id de establecimiento
	 * @param id
	 * @return
	 * @throws InfrastructureException
	 */
	public String getNifByEstablimentId(Long id) throws InfrastructureException {
		List solicitante;
		try {
			logger.debug("getNifByEstablimentId ini");
			
			String q = "select est.solicitant.nif from Establiment as est where est.id = '"+id+"'";
			solicitante = getHibernateTemplate().find(q);
			if (solicitante.size() > 0) {
				logger.debug("getNifByEstablimentId fin");
				return (String)solicitante.get(0);
			}

		} catch (HibernateException ex) {
			logger.error("getNifByEstablimentId failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getNifByEstablimentId fin");
		return null;	
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