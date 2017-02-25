package es.caib.gestoli.logic.dao;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.exception.InfrastructureException;
import es.caib.gestoli.logic.model.QualitatControl;

public class QualitatControlDao {
	private static Logger logger = Logger.getLogger(QualitatControlDao.class);
	private HibernateTemplate hibernateTemplate;
	
	/**
	 * QualitatControl amb la id indicada.
	 * 
	 * @param id
	 */
	public QualitatControl getById(Long id) throws InfrastructureException {
		QualitatControl qualitatControl = null;
		try {
			qualitatControl = (QualitatControl)getHibernateTemplate().load(QualitatControl.class, id);
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		return qualitatControl;
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
