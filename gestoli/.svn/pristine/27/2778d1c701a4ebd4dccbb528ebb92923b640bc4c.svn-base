package es.caib.gestoli.logic.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.exception.InfrastructureException;
import es.caib.gestoli.logic.model.Pais;
import es.caib.gestoli.logic.model.Provincia;

public class ProvinciaDao {

	private static Logger logger = Logger.getLogger(ProvinciaDao.class);
	private HibernateTemplate hibernateTemplate;
	
	/**
	 * Provincia amb la id indicada.
	 * 
	 * @param id
	 */
	public Provincia getById(Long id) throws InfrastructureException {
		Provincia provincia = null;
		try {
			provincia = (Provincia)getHibernateTemplate().load(Provincia.class, id);
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		return provincia;
	}
	
	/**
	 * Provincia amb el nom indicat.
	 * 
	 * @return
	 */
	public Provincia getByNom(String nom) throws InfrastructureException {
		List provincies = null;
		try {
			provincies = getHibernateTemplate().find(
					"select pr from Provincia pr " +
					"where upper(pr.nom) = '" + nom.toUpperCase().replace("'", "''") + "'"
			);
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		if (provincies.size() > 0){
			return (Provincia)provincies.get(0);
		}
		return null;
	}

	/**
	 * Llistat de totes les provincies.
	 * 
	 * @return
	 */
	public List getAllProvincies() throws InfrastructureException {
		List provincies = null;
		try {
			provincies = getHibernateTemplate().find("select pr from Provincia pr order by pr.nom asc");
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		return provincies;
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
