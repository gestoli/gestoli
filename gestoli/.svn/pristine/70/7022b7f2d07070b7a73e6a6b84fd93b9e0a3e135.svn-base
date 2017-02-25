package es.caib.gestoli.logic.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.exception.InfrastructureException;
import es.caib.gestoli.logic.model.Pais;


public class PaisDao {

	private static Logger logger = Logger.getLogger(PaisDao.class);
	private HibernateTemplate hibernateTemplate;
	
	/**
	 * Actualiza un registro
	 * @param pais
	 * @throws InfrastructureException
	 */
	public void makePersistent(Pais pais) throws InfrastructureException {
		try {
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().saveOrUpdate(pais);
			getHibernateTemplate().flush();
		} catch (HibernateException ex) {
			logger.error("makePersistent failed", ex);
			throw new InfrastructureException(ex);
		}
	}
	
	/**
	 * Pais amb la id indicada.
	 * 
	 * @param id
	 */
	public Pais getById(Long id) throws InfrastructureException {
		Pais pais = null;
		try {
			pais = (Pais)getHibernateTemplate().load(Pais.class, id);
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		return pais;
	}

	/**
	 * Llistat de tots els països.
	 * 
	 * @return
	 */
	public List getAllPaisos() throws InfrastructureException {
		List paisos = null;
		try {
			paisos = getHibernateTemplate().find("select pa from Pais pa order by pa.nomcat asc");
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		return paisos;
	}
	
	/**
	 * Pais amb el nom indicat.
	 * 
	 * @return
	 */
	public Pais getByNom(String nom) throws InfrastructureException {
		List paisos = null;
		try {
			paisos = getHibernateTemplate().find(
					"select pa from Pais pa " +
					"where upper(pa.nomcas) = '" + nom.toUpperCase().replace("'", "''") + "' " +
					"or    upper(pa.nomcat) = '" + nom.toUpperCase().replace("'", "''") + "' " +
					"or    upper(pa.iso) = '" + nom.toUpperCase() + "'"			
			);
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		if (paisos.size() > 0){
			return (Pais)paisos.get(0);
		}
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
