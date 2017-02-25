package es.caib.gestoli.logic.dao;

import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.exception.InfrastructureException;
import es.caib.gestoli.logic.model.HistoricOlivicultor;


/**
 * Home object for domain model class Establiment
 * @see es.caib.gestoli.logic.model.Establiment
 * 
 */
public class HistoricOlivicultorDao {
	private HibernateTemplate hibernateTemplate;
	
	/**
	 * Devuelve el objeto que tiene el id
	 * @param id
	 * @return
	 * @throws InfrastructureException
	 */
	public HistoricOlivicultor getById(Long id) throws InfrastructureException {
		try {
			String q = "from HistoricOlivicultor where id = " + id;
			List historics = getHibernateTemplate().find(q);
			if (historics.size() > 0)
				return (HistoricOlivicultor)historics.get(0);

		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		return null;		
	}
	
	/**
	 * Actualiza un registro
	 * @param HistoricOlivicultor
	 * @throws InfrastructureException
	 */
	public void makePersistent(HistoricOlivicultor historicOlivicultor) throws InfrastructureException {
		try {
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			if (historicOlivicultor.getId() != null) {
				getHibernateTemplate().evict(this.getById(historicOlivicultor.getId()));
				getHibernateTemplate().update(historicOlivicultor);
			} else {
				getHibernateTemplate().save(historicOlivicultor);
			}
			
			// Informamos el idOriginal
			if (historicOlivicultor.getIdOriginal() == null) {
				historicOlivicultor.setIdOriginal(historicOlivicultor.getId());
			}
			
			getHibernateTemplate().flush();
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
	}
	
	/**
	 * Recupera tots els registres.
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection findAll() throws InfrastructureException {
		Collection col;
		try {
			col = getHibernateTemplate().find("from HistoricOlivicultor order by id desc");            
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		return col;
	}

	/**
	 * Recupera tots els registres d'altes i baixes
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection findAllAltaBaixa() {
		Collection col;
		try {
			col = getHibernateTemplate().find("from HistoricOlivicultor where esAltaBaixa = true order by id desc");            
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		return col;
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

	private static Logger logger = Logger.getLogger(HistoricOlivicultorDao.class);
}