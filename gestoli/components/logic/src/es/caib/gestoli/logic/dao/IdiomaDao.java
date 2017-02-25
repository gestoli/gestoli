package es.caib.gestoli.logic.dao;

import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.exception.InfrastructureException;
import es.caib.gestoli.logic.model.Idioma;


/**
 * Home object for domain model class Idioma
 * @see es.caib.gestoli.logic.model.Idioma
 * @author Oriol Barnés
 */
public class IdiomaDao {
	private static Logger logger = Logger.getLogger(IdiomaDao.class);
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
			col = getHibernateTemplate().find("from Idioma");
		} catch (HibernateException ex) {
			logger.error("findAll failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findAll fin");
		return col;
	}


	/**
	 * Actualiza un registro
	 * @param idioma
	 * @throws InfrastructureException
	 */
	public void makePersistent(Idioma idioma) throws InfrastructureException {
		try {
			logger.debug("makePersistent ini");
			String q = "select idi.id from Idioma idi where idi.id = :id";
			List id = getHibernateTemplate().findByNamedParam(q, "id", idioma.getId());
			// ATENCION!!
			// Hay que hacer esta validación, porque la PK es de tipo ASSIGNED,
			// si fuera NATIVE (secuencia de hibernate) con un saveOrUpdate() es suficiente
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			if (id != null && id.size() == 1) {
				getHibernateTemplate().update(idioma);
			} else {
				getHibernateTemplate().save(idioma);
			}
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