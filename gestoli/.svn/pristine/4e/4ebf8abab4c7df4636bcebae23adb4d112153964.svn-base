package es.caib.gestoli.logic.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.exception.InfrastructureException;
import es.caib.gestoli.logic.model.Municipi;
import es.caib.gestoli.logic.model.Provincia;

/**
 * Home object for domain model class Municipi
 * 
 * @see es.caib.gestoli.logic.model.Municipi
 * @author Miquel Angel Amengual <miquelaa@limit.es>
 */
public class MunicipiDao {
	private static Logger logger = Logger.getLogger(MunicipiDao.class);
	private HibernateTemplate hibernateTemplate;
	
	/**
	 * Municipi amb la id indicada.
	 * 
	 * @param id
	 */
	public Municipi getById(Long id) throws InfrastructureException {
		Municipi municipi = null;
		try {
			municipi = (Municipi)getHibernateTemplate().load(Municipi.class, id);
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		return municipi;
	}
	
	/**
	 * Municipi amb el nom indicat.
	 * 
	 * @return
	 */
	public Municipi getByNom(String nom) throws InfrastructureException {
		List municipis = null;
		try {
			municipis = getHibernateTemplate().find(
					"select mu from Municipi mu " +
					"where upper(mu.nom) = '" + nom.toUpperCase().replace("'", "''") + "'"
			);
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		if (municipis.size() > 0){
			return (Municipi)municipis.get(0);
		}
		return null;
	}

	/**
	 * Llistat de tots els municipis.
	 * 
	 * @return
	 */
	public List getAllMunicipis() throws InfrastructureException {
		List municipis = null;
		try {
			municipis = getHibernateTemplate().find("select mu from Municipi mu order by mu.nom asc");
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		return municipis;
	}
	
	/**
	 * set the hibernate template.
	 * 
	 * @param hibernateTemplate the hibernate spring template.
	 * @spring.property ref="hibernateTemplate"
	 *
	 */
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate){
		this.hibernateTemplate = hibernateTemplate;
	}

	/**
	 * get the hibernate template.
	 * 
	 * @return the hibernate spring template.
	 */
	public HibernateTemplate getHibernateTemplate(){
		return this.hibernateTemplate;
	}
}
