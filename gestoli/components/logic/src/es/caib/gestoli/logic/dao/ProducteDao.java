package es.caib.gestoli.logic.dao;

import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.exception.InfrastructureException;
import es.caib.gestoli.logic.model.Municipi;
import es.caib.gestoli.logic.model.Producte;


/**
 * Home object for domain model class Marca
 * @see es.caib.gestoli.logic.model.Marca
 * @author Carlos Pérez
 */
public class ProducteDao {
	private static Logger logger = Logger.getLogger(ProducteDao.class);
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
			col = getHibernateTemplate().find("from Producte as pro order by pro.nom"); 
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
	public Collection findAllActivos() throws InfrastructureException {
		Collection col;
		try {
			logger.debug("findAllActivos ini");
			col = getHibernateTemplate().find("from Producte as pro where pro.actiu = true order by pro.nom"); 
		} catch (HibernateException ex) {
			logger.error("findAllActivos failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findAllActivos fin");
		return col;
	}

	/**
	 * Recupera todos los registros activos
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection findAllAmbEstabliment(Long idEst) throws InfrastructureException {
		Collection col;
		try {
			logger.debug("findAllAmbEstabliment ini");
			col = getHibernateTemplate().find("from Producte as pro where pro.establiment.id = " + idEst + " order by pro.nom"); 
		} catch (HibernateException ex) {
			logger.error("findAllAmbEstabliment failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findAllAmbEstabliment fin");
		return col;
	}
	
	/**
	 * Recupera todos los registros activos
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection findActiusAmbEstabliment(Long idEst) throws InfrastructureException {
		Collection col;
		try {
			logger.debug("findActiusAmbEstabliment ini");
			col = getHibernateTemplate().find("from Producte as pro where pro.actiu = true and pro.establiment.id = " + idEst + " order by pro.nom"); 
		} catch (HibernateException ex) {
			logger.error("findActiusAmbEstabliment failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findActiusAmbEstabliment fin");
		return col;
	}
	

	/**
	 * Devuelve el objeto que tiene el id
	 * @param id
	 * @return
	 * @throws InfrastructureException
	 */
	public Producte getById(Long id) throws InfrastructureException {
		Producte producte;
		try {
			logger.debug("getById ini");
			producte = (Producte)getHibernateTemplate().load(Producte.class, id);	
		} catch (HibernateException ex) {
			logger.error("getById failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getById fin");
		return producte;
	}

	/**
	 * Producte amb el nom indicat.
	 * 
	 * @return
	 */
	public List getByNom(String nom, Long idEstabliment) throws InfrastructureException {
		List productes = null;
		try {
			productes = getHibernateTemplate().find(
					"select pr from Producte pr " +
					"where pr.establiment.id = " + idEstabliment + " " +
					"and upper(pr.nom) = '" + nom.toUpperCase().replace("'", "''") + "'"
			);
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		
		return productes;
	}
	
	
	/**
	 * Comprova si el producte ja existeix
	 * @param lid
	 * @return
	 */
	public Boolean existeixProducteEstabliment(
			Long id,
			String nom, 
			Long idEst) {
		List productes;
		try {
			logger.debug("existeixProducteEstabliment ini");
			String q = 	"select count(pro.id) from Producte pro " +
						"where pro.nom = '" + nom.replace("'", "''") + "' " +
						"and pro.establiment.id = " + idEst + " ";
			if (id != null) q += "and pro.id != " + id + " ";
			productes = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("existeixProducteEstabliment failed", ex);
			throw new InfrastructureException(ex);
		}
		if (productes != null && productes.size()> 0 && ((Long)productes.get(0)).intValue() > 0) {
			logger.debug("existeixProducteEstabliment fin");
			return true;
		}
		logger.debug("existeixProducteEstabliment fin");
		return false;
	}

	/**
	 * Actualiza un registro
	 * @param marca
	 * @throws InfrastructureException
	 */
	public void makePersistent(Producte producte) throws InfrastructureException {
		try {
			logger.debug("makePersistent ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().saveOrUpdate(producte);
			getHibernateTemplate().flush();
			logger.debug("makePersistent fin");
		} catch (HibernateException ex) {
			logger.error("makePersistent failed", ex);
			throw new InfrastructureException(ex);
		}
	}

	
	/**
	 * Borra un registro
	 * @param marca
	 * @throws InfrastructureException
	 */
	public void makeTransient(Producte producte)
	throws InfrastructureException {
		try {
			logger.debug("makeTransient ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().delete(producte);
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