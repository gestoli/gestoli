package es.caib.gestoli.logic.dao;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.exception.InfrastructureException;
import es.caib.gestoli.logic.model.SortidaOrujo;

/**
 * Home object for domain model class SortidaOrujo
 * @see es.caib.gestoli.logic.model.SortidaOrujo
 */
public class SortidaOrujoDao {

	private HibernateTemplate hibernateTemplate;
	
	/**
	 * Recupera tots els registres.
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection findAll() throws InfrastructureException {
		Collection col;
		try {
			col = getHibernateTemplate().find("from SortidaOrujo order by id desc");
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		return col;
	}
	
	/**
	 * Recupera tots els registres.
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection findAllValides(Boolean valid) throws InfrastructureException {
		Collection col;
		try {
			col = getHibernateTemplate().find("from SortidaOrujo where valid = " + valid + " order by id desc");
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		return col;
	}
	
	/**
	 * Recupera tots els registres.
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection findAllAmbEstabliment(Long establimentId, Boolean valid) throws InfrastructureException {
		Collection col;
		try {
			col = getHibernateTemplate().find(
					"from SortidaOrujo " +
					"where establiment.id = " + establimentId + " " +
					"and valid = " + valid + " " +
					"order by id desc");
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		return col;
	}
	
	/**
	 * Recupera tots els registres.
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection findAllAmbEstablimentEntreDates(Date dataInici, Date dataFi, Long establimentId, Boolean valid) throws InfrastructureException {
		Collection col;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String di = sdf.format(dataInici);
			String df = sdf.format(dataFi);
			
			col = getHibernateTemplate().find(
					"from SortidaOrujo " +
					"where establiment.id = " + establimentId + " " +
					"and data >= '" + di + "' and data <= '" + df + "' " +
					"and valid = " + valid + " " +
					"order by id desc");
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		return col;
	}

	/**
	 * Recupera tots els registres.
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection findAllEntreDates(Date dataInici, Date dataFi, Boolean valid) throws InfrastructureException {
		Collection col;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String di = sdf.format(dataInici);
			String df = sdf.format(dataFi);
			
			col = getHibernateTemplate().find(
					"from SortidaOrujo " +
					"where data >= '" + di + "' and data <= '" + df + "' " +
					"and valid = " + valid + " " +
					"order by id desc");
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		return col;
	}
	
	/**
	 * Torna l'objecte que té la id.
	 * @param id
	 * @return
	 * @throws InfrastructureException
	 */
	public SortidaOrujo getById(Long id) {
		try {
			String q = "from SortidaOrujo where id = '" + id + "' ";
			List sortides = getHibernateTemplate().find(q);
			if (sortides.size() > 0) {
				return (SortidaOrujo)sortides.get(0);
			}
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		return null;
	}
	
	/**
	 * Actualiza un registro
	 * @param sortidaOrujo
	 * @throws InfrastructureException
	 */
	public void makePersistent(SortidaOrujo sortidaOrujo) throws InfrastructureException {
		try {
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().saveOrUpdate(sortidaOrujo);
			getHibernateTemplate().flush();
		} catch (HibernateException ex) {
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

	private static Logger logger = Logger.getLogger(SortidaOrujoDao.class);
}
