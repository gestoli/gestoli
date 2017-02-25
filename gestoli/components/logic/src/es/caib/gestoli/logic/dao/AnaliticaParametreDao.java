package es.caib.gestoli.logic.dao;

import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.exception.InfrastructureException;
import es.caib.gestoli.logic.model.AnaliticaParametre;
import es.caib.gestoli.logic.model.AnaliticaParametreTipus;


/**
 * Home object for domain model class Analitica
 * @see es.caib.gestoli.logic.model.Analitica
 * 
 */
public class AnaliticaParametreDao {
	private static Logger logger = Logger.getLogger(AnaliticaParametreDao.class);
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
			// Ponemos where 1 = 1 para que no nos de error cuando se añada el and si se añade.
			String q = "from AnaliticaParametre ana ";
			q += " order by ana.id";
			col = getHibernateTemplate().find(q); 
		} catch (HibernateException ex) {
			logger.error("findAll failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findAll fin");
		return col; 

	}
	
	/**
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection findByTipusIVarietat(Long idTipus,  Long idVarietatOli) throws InfrastructureException {
		Collection col;
		try {
			logger.debug("findAll ini");
			// Ponemos where 1 = 1 para que no nos de error cuando se añada el and si se añade.
			String q = "from AnaliticaParametre ana ";
			q += " where ";
			q += " 		ana.analiticaParametreTipus.id = " + idTipus;
			q += " 		and ana.varietatOli.id = " + idVarietatOli;
			q += " order by ana.id";
			col = getHibernateTemplate().find(q); 
		} catch (HibernateException ex) {
			logger.error("findAll failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findAll fin");
		return col; 

	}
	
	/**
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection findByTipus(Long idTipus) throws InfrastructureException {
		Collection col;
		try {
			logger.debug("findAll ini");
			// Ponemos where 1 = 1 para que no nos de error cuando se añada el and si se añade.
			String q = "from AnaliticaParametre ana ";
			q += " where ";
			q += " 		ana.analiticaParametreTipus.id = " + idTipus;
			q += " order by ana.id";
			col = getHibernateTemplate().find(q); 
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
	public AnaliticaParametre getById(Long id) throws InfrastructureException {
		List analiticas;
		try {
			logger.debug("getById ini");
			String q = "from AnaliticaParametre as ana where ana.id = " + id;
			analiticas = getHibernateTemplate().find(q);
			if (analiticas.size() > 0) {
				logger.debug("getById fin");
				return (AnaliticaParametre)analiticas.get(0);
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
	 * @param analitica
	 * @throws InfrastructureException
	 */
	public void makePersistent(AnaliticaParametre analitica) throws InfrastructureException {
		try {
			logger.debug("makePersistent ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().saveOrUpdate(analitica);
			getHibernateTemplate().flush();
			logger.debug("makePersistent fin");
		} catch (HibernateException ex) {
			logger.error("makePersistent failed", ex);
			throw new InfrastructureException(ex);
		}
	}


	/**
	 * Actualiza un registro
	 * @param analitica
	 * @param session
	 * @throws InfrastructureException
	 */
	public void makePersistent(AnaliticaParametre analitica, Session session) throws InfrastructureException {
		try {
			logger.debug("makePersistent ini");
			session.setFlushMode(FlushMode.ALWAYS);
			session.saveOrUpdate(analitica);
			session.flush();
			logger.debug("makePersistent fin");
		} catch (HibernateException ex) {
			logger.error("makePersistent failed", ex);
			throw new InfrastructureException(ex);
		}
	}


	/**
	 * Borra un registro
	 * @param Analitica
	 * @throws InfrastructureException
	 */
	public void makeTransient(AnaliticaParametre analitica)
	throws InfrastructureException {
		try {
			logger.debug("makeTransient ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().delete(analitica);
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