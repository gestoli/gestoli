package es.caib.gestoli.logic.dao;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.exception.InfrastructureException;
import es.caib.gestoli.logic.model.PartidaElaboracio;


/**
 * Home object for domain model class DescomposicioPartidaOliva
 * @see es.caib.gestoli.logic.model.DescomposicioPartidaOlivaDao
 * 
 */
public class PartidaElaboracioDao {
	private static Logger logger = Logger.getLogger(PartidaElaboracioDao.class);
	private HibernateTemplate hibernateTemplate;


	/**
	 * Recupera todos los registros
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection findAll(Boolean valid) throws InfrastructureException {
		Collection col;
		try {
			logger.debug("findAll ini");
			// Ponemos where 1 = 1 para que no nos de error cuando se añada el and si se añade.
			String q = "from PartidaElaboracio as pel where 1 = 1 ";
			q += "order by pel.id";
			col = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("findAll failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findAll fin");
		return col;
	}

	/**
	 * Actualiza un registro
	 * @param partidaElaboracio
	 * @throws InfrastructureException
	 */
	public void makePersistent(PartidaElaboracio partidaElaboracio) throws InfrastructureException {
		try {
			logger.debug("makePersistent ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			if(partidaElaboracio.getId()!= null){
				getHibernateTemplate().saveOrUpdate(partidaElaboracio);
			}else{
				getHibernateTemplate().save(partidaElaboracio);
			}			
			getHibernateTemplate().flush();
		} catch (HibernateException ex) {
			logger.error("makePersistent failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("makePersistent fin");
	}


	/**
	 * Actualiza un registro
	 * @param partidaOliva
	 * @throws InfrastructureException
	 */
	public void makePersistent(PartidaElaboracio partidaElaboracio, Session session) throws InfrastructureException {
		try {
			logger.debug("makePersistent ini");
			session.setFlushMode(FlushMode.ALWAYS);
			if(partidaElaboracio.getId()!= null){
				session.saveOrUpdate(partidaElaboracio);
			}else{
				session.save(partidaElaboracio);
			}
			session.flush();
			logger.debug("makePersistent fin");
		} catch (HibernateException ex) {
			logger.error("makePersistent failed", ex);
			throw new InfrastructureException(ex);
		}
	}


	/**
	 * Borra un registro
	 * @param partidaOliva
	 * @throws InfrastructureException
	 */
	public void makeTransient(PartidaElaboracio partidaElaboracio) throws InfrastructureException {
		try {
			logger.debug("makeTransient ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().delete(partidaElaboracio);
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
	public PartidaElaboracio getById(Long id) throws InfrastructureException {
		PartidaElaboracio partidaElaboracio;
		try {
			logger.debug("getById ini");
			partidaElaboracio = (PartidaElaboracio)getHibernateTemplate().load(PartidaElaboracio.class, id);	
			logger.debug("getById fin");
		} catch (HibernateException ex) {
			logger.error("getById failed", ex);
			throw new InfrastructureException(ex);
		}
		return partidaElaboracio;
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