package es.caib.gestoli.logic.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.exception.InfrastructureException;
import es.caib.gestoli.logic.model.QualitatDepartament;

public class QualitatDepartamentDao {
	private static Logger logger = Logger.getLogger(QualitatDepartamentDao.class);
	private HibernateTemplate hibernateTemplate;
	
	/**
	 * QualitatDepartament amb la id indicada.
	 * 
	 * @param id
	 */
	public QualitatDepartament getById(Long id) throws InfrastructureException {
		QualitatDepartament qualitatDepartament = null;
		try {
			qualitatDepartament = (QualitatDepartament)getHibernateTemplate().load(QualitatDepartament.class, id);
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		return qualitatDepartament;
	}

	/**
	 * Retorna un llista de totes les causes de noConformitat.
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public List findAll() throws InfrastructureException {
		List causes;
		try {
			logger.debug("findAll ini");
			String q = "select form from QualitatDepartament form " +
			"order by form.nom";
			causes = getHibernateTemplate().find(q);
			logger.debug("findAll ini");
		} catch (HibernateException ex) {
			logger.error("findAll failed", ex);
			throw new InfrastructureException(ex);
		}
		return causes;
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
