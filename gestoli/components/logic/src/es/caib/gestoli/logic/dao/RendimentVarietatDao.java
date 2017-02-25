package es.caib.gestoli.logic.dao;

import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.exception.InfrastructureException;
import es.caib.gestoli.logic.model.Campanya;
import es.caib.gestoli.logic.model.RendimentVarietat;
import es.caib.gestoli.logic.model.VarietatOliva;

public class RendimentVarietatDao {

	private static Logger logger = Logger.getLogger(RendimentVarietatDao.class);
	private HibernateTemplate hibernateTemplate;
	
	/**
	 * Cerca els rendiments per la campanya indicada.
	 * @param id
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection rendimentVarietatPerCampanya(Long id) throws InfrastructureException {
		try {
			String q =	"from RendimentVarietat " +
						"where campanya.id = " + id + " " +
						"order by varietatOliva.nomVarietat, varietatOliva.nom asc ";
			return getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
	}
	
	/**
	 * Actualiza un registro
	 * @param rendimentVarietat
	 * @throws InfrastructureException
	 */
	public void makePersistent(RendimentVarietat rendimentVarietat) throws InfrastructureException {
		try {
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().saveOrUpdate(rendimentVarietat);
			getHibernateTemplate().flush();
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
	}

	/**
	 * Cerca els rendiments per la campanya i la varietat indicada.
	 * @param campanya
	 * @param varietatOliva
	 * @return
	 */
	public RendimentVarietat findByCampanyaVarietat(Campanya campanya, VarietatOliva varietatOliva) {
		try {
			String q =	"from RendimentVarietat " +
						"where campanya.id = " + campanya.getId() + " " +
						"and varietatOliva.id = " + varietatOliva.getId();
			List rendiments = getHibernateTemplate().find(q);
			if (rendiments.size() > 0)
				return (RendimentVarietat)rendiments.get(0);
			return null;
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
	}
	
	/**
	 * Cerca els rendiments per la campanya i la varietat indicada.
	 * @param campanya
	 * @param varietatOliva
	 * @return
	 */
	public RendimentVarietat findByCampanyaVarietat(Long campanyaId, Integer idVarietat) {
		try {
			String q =	"from RendimentVarietat " +
						"where campanya.id = " + campanyaId + " " +
						"and varietatOliva.id = " + idVarietat;
			List rendiments = getHibernateTemplate().find(q);
			if (rendiments.size() > 0)
				return (RendimentVarietat)rendiments.get(0);
			return null;
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
	}

	/**
	 * Tipus de rendiment per la varietat i campanya indicada.
	 * @param campanyaId
	 * @param idVarietat
	 * @return
	 */
	public String tipusRendimentCampanyaVarietat(Long campanyaId, Integer idVarietat) {
		try {
			String q =	"select tipusRendiment " +
						"from RendimentVarietat " +
						"where campanya.id = " + campanyaId + " " +
						"and varietatOliva.id = " + idVarietat;
			List rendiments = getHibernateTemplate().find(q);
			if (rendiments.size() > 0)
				return (String)rendiments.get(0);
			return null;
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
	}
	
	/**
	 * Rendiment per la varietat i campanya indicada.
	 * @param campanyaId
	 * @param idVarietat
	 * @return
	 */
	public Double rendimentCampanyaVarietat(Long campanyaId, Integer idVarietat) {
		try {
			String q =	"select rendiment " +
						"from RendimentVarietat " +
						"where campanya.id = " + campanyaId + " " +
						"and varietatOliva.id = " + idVarietat;
			List rendiments = getHibernateTemplate().find(q);
			if (rendiments.size() > 0)
				return (Double)rendiments.get(0);
			return null;
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
}
