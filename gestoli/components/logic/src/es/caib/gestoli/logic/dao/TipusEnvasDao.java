package es.caib.gestoli.logic.dao;

import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.exception.InfrastructureException;
import es.caib.gestoli.logic.model.TipusEnvas;


/**
 * Home object for domain model class TipusEnvas
 * @see es.caib.gestoli.logic.model.TipusEnvas
 * @author Oriol Barnés
 */
public class TipusEnvasDao {
	private static Logger logger = Logger.getLogger(TipusEnvasDao.class);
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
			col = getHibernateTemplate().find("from TipusEnvas as ten order by ten.volum");			
		} catch (HibernateException ex) {
			logger.error("findAll failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findAll fin");
		return col;
	}

	/**
	 * Recupera todos los registros activos
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection findAllActius() throws InfrastructureException {
		Collection col;
		try {
			logger.debug("findAllActius ini");
			col = getHibernateTemplate().find("from TipusEnvas as ten where ten.actiu='true' order by ten.volum");
			
		} catch (HibernateException ex) {
			logger.error("findAllActius failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findAllActius fin");
		return col;
	}


	/**
	 * Actualiza un registro
	 * @param tipusEnvas
	 * @throws InfrastructureException
	 */
	public void makePersistent(TipusEnvas tipusEnvas) throws InfrastructureException {
		try {
			logger.debug("makePersistent ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().saveOrUpdate(tipusEnvas);
			getHibernateTemplate().flush();
			logger.debug("makePersistent fin");
		} catch (HibernateException ex) {
			logger.error("makePersistent failed", ex);
			throw new InfrastructureException(ex);
		}
	}



	/**
	 * Devuelve el objeto que tiene el id
	 * @param id
	 * @return
	 * @throws InfrastructureException
	 */
	public TipusEnvas getById(Long id) throws InfrastructureException {
		TipusEnvas tipusEnvas;
		try {
			logger.debug("getById ini");
			tipusEnvas = (TipusEnvas)getHibernateTemplate().load(TipusEnvas.class, id);			
		} catch (HibernateException ex) {
			logger.error("getById failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getById fin");
		return tipusEnvas;
	}


	/**
	 * Borra un registro
	 * @param tipusEnvas
	 * @throws InfrastructureException
	 */
	public void makeTransient(TipusEnvas tipusEnvas)
	throws InfrastructureException {
		try {
			logger.debug("makeTransient ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().delete(tipusEnvas);
			getHibernateTemplate().flush();
			logger.debug("makeTransient fin");
		} catch (HibernateException ex) {
			logger.error("makeTransient failed",ex);
			throw new InfrastructureException(ex);
		}
	}


	/**
	 * Devuelve un tipo de envase de la BBDD a partir de su volum, color y materialTipusEnvas
	 * @param materialTipusEnvas
	 * @param color
	 * @param volum
	 * @return
	 * @throws InfrastructureException
	 */
	public Long getTipusEnvas(Integer materialTipusEnvas, Integer color, Double volum) throws InfrastructureException {
		List tiposEnvases;
		try {
			logger.debug("getTipusEnvas ini");
			String q = "select ten.id from TipusEnvas as ten where ten.volum = '" + volum + "' AND ten.color = '" + color + "' AND ten.materialTipusEnvas = '" + materialTipusEnvas + "'";
			tiposEnvases = getHibernateTemplate().find(q);
			if (tiposEnvases.size() > 0) {
				logger.debug("getTipusEnvas fin");
				return (Long) tiposEnvases.get(0);
			}
		} catch (HibernateException ex) {
			logger.error("getTipusEnvas failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getTipusEnvas fin");
		return null;
	}


	/**
	 * Devuelve si se modifica de activo a no activo, no debe tener ningún etiquetaje asociado
	 * @param tipusEnvasId
	 * @return
	 * @throws InfrastructureException
	 */
	public boolean existenEtiquetatgesActiusEnTipusEnvas(Long tipusEnvasId) throws InfrastructureException {
		List etiquetatges;

		try {
			logger.debug("existenEtiquetatgesEnTipusEnvas ini");
			String q = "select count(eti.id) from Etiquetatge eti where eti.tipusEnvas.id = " + tipusEnvasId + " and eti.actiu = true and eti.tipusEnvas.actiu = true";
			etiquetatges = getHibernateTemplate().find(q);
			if (etiquetatges.size() > 0) {
				if (((Long)etiquetatges.get(0)).intValue() > 0) {
					logger.debug("existenEtiquetatgesEnTipusEnvas fin");
					return (true);
				}
			}
		} catch (HibernateException ex) {
			logger.error("existenEtiquetatgesEnTipusEnvas failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("existenEtiquetatgesEnTipusEnvas fin");
		return (false);
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