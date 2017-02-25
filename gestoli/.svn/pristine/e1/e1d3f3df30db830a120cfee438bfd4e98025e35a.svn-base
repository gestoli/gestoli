package es.caib.gestoli.logic.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.exception.InfrastructureException;
import es.caib.gestoli.logic.model.SortidaOliFacturacio;

public class SortidaOliFacturacioDao {
	private static Logger logger = Logger.getLogger(SortidaOliFacturacioDao.class);
	private HibernateTemplate hibernateTemplate;
	
	/**
	 * Actualiza un registro
	 * @param sortidaOliFacturacio
	 * @throws InfrastructureException
	 */
	public void makePersistent(SortidaOliFacturacio sortidaOliFacturacio) throws InfrastructureException {
		try {
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().saveOrUpdate(sortidaOliFacturacio);
			getHibernateTemplate().flush();
		} catch (HibernateException ex) {
			logger.error("makePersistent failed", ex);
			throw new InfrastructureException(ex);
		}
	}
	
	/**
	 * Retorna un llista de totes les sortides d'una importacio concreta i un estat.
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public List findByImportacioEstat(Long idImportacio, Boolean estat) throws InfrastructureException {
		List sortides;
		try {
			logger.debug("findByImportacioEstat ini");
			String q = "select form from SortidaOliFacturacio form " +
			"where form.idImportacio = " + idImportacio + " " +
			"and   form.estat = " + estat + " " +
			"order by form.id";
			sortides = getHibernateTemplate().find(q);
			logger.debug("findByImportacioEstat ini");
		} catch (HibernateException ex) {
			logger.error("findByImportacioEstat failed", ex);
			throw new InfrastructureException(ex);
		}
		return sortides;
	}
	
	/**
	 * Retorna un llista de totes les sortides d'una importacio concreta
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public List findByImportacio(Long idImportacio) throws InfrastructureException {
		List sortides;
		try {
			logger.debug("findByImportacioEstat ini");
			String q = "select form from SortidaOliFacturacio form " +
			"where form.idImportacio = " + idImportacio + " " +
			"order by form.id";
			sortides = getHibernateTemplate().find(q);
			logger.debug("findByImportacioEstat ini");
		} catch (HibernateException ex) {
			logger.error("findByImportacioEstat failed", ex);
			throw new InfrastructureException(ex);
		}
		return sortides;
	}
	
	/**
	 * Retorna el darrer idImportacio creat
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public Long findLastIdImportacio() throws InfrastructureException {
		List sortides;
		try {
			logger.debug("findByImportacioEstat ini");
			String q = "select form from SortidaOliFacturacio form " +
			"order by form.idImportacio desc";
			sortides = getHibernateTemplate().find(q);
			logger.debug("findByImportacioEstat ini");
		} catch (HibernateException ex) {
			logger.error("findByImportacioEstat failed", ex);
			throw new InfrastructureException(ex);
		}
		
		if (sortides.size() > 0){
			return ((SortidaOliFacturacio)(sortides.get(0))).getIdImportacio();
		} else {
			return Long.valueOf("0");
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
