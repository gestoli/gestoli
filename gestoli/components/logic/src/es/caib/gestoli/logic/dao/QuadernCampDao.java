package es.caib.gestoli.logic.dao;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.exception.InfrastructureException;
import es.caib.gestoli.logic.model.QuadernCamp;

public class QuadernCampDao {

	private static Logger logger = Logger.getLogger(QuadernCampDao.class);
	private HibernateTemplate hibernateTemplate;
	
	/**
	 * Actualiza un registro
	 * @param QuadernCamp
	 * @throws InfrastructureException
	 */
	public void makePersistent(QuadernCamp quadernCamp) throws InfrastructureException {
		try {
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().saveOrUpdate(quadernCamp);
			getHibernateTemplate().flush();
		} catch (HibernateException ex) {
			logger.error("makePersistent failed", ex);
			throw new InfrastructureException(ex);
		}
	}
	
	/**
	 * QuadernCamp amb la id indicada.
	 * 
	 * @param id
	 */
	public QuadernCamp getById(Long id) throws InfrastructureException {
		QuadernCamp quadernCamp = null;
		try {
			quadernCamp = (QuadernCamp)getHibernateTemplate().load(QuadernCamp.class, id);
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		return quadernCamp;
	}

	/**
	 * Retorna un llista de tot els olivicultors que tenen associat qualque quadern de camp
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public List findOlivicultors() throws InfrastructureException {
		List olivicultors;
		
		try {
			logger.debug("findOlivicultors ini");
			String q = "select distinct form.olivicultor from QuadernCamp form " +
			"order by form.olivicultor.nom";
			olivicultors = getHibernateTemplate().find(q);
			logger.debug("findOlivicultors ini");
		} catch (HibernateException ex) {
			logger.error("findOlivicultors failed", ex);
			throw new InfrastructureException(ex);
		}
		return olivicultors;
	}

	
	/**
	 * Retorna un llista de tot els quaderns de cmap associats amb un olivicultor.
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public List findByOlivicultor(Long olivicultorId, Date dataInici, Date dataFi) throws InfrastructureException {
		List quadernsCamp;
		
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String dInici = df.format(dataInici);
		String dFi = df.format(dataFi);

		
		try {
			logger.debug("findByOlivicultor ini");
			String q = "select form from QuadernCamp form " +
			"where form.olivicultor.id = " + olivicultorId + " " +
			"and   form.data >= '" + dInici + "' and form.data <= '" + dFi + "' " +
			"order by form.data desc";
			quadernsCamp = getHibernateTemplate().find(q);
			logger.debug("findByOlivicultor ini");
		} catch (HibernateException ex) {
			logger.error("findByOlivicultor failed", ex);
			throw new InfrastructureException(ex);
		}
		return quadernsCamp;
	}

	/**
	 * Borra un registro
	 * @param QuadernCamp
	 * @throws InfrastructureException
	 */
	public void makeTransient(QuadernCamp quadernCamp)
	throws InfrastructureException {
		try {
			logger.debug("makeTransient ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().delete(quadernCamp);
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
