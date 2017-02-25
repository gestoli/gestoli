package es.caib.gestoli.logic.dao;

import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.exception.InfrastructureException;
import es.caib.gestoli.logic.model.Etiquetatge;


/**
 * Home object for domain model class Etiquetatge
 * @see es.caib.gestoli.logic.model.Etiquetatge
 * @author Carlos Pérez
 */
public class EtiquetatgeDao {
	private static Logger logger = Logger.getLogger(EtiquetatgeDao.class);
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
			col = getHibernateTemplate().find("from Etiquetatge");
		} catch (HibernateException ex) {
			logger.error("findAll failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findAll fin");
		return col;
	}


	/**
	 * Devuelve el objeto que tiene el id
	 * @param id
	 * @return
	 * @throws InfrastructureException
	 */
	public Etiquetatge getById(Long id) throws InfrastructureException {
		Etiquetatge etiquetatge;
		try {
			logger.debug("getById ini");
			etiquetatge = (Etiquetatge)getHibernateTemplate().load(Etiquetatge.class, id);	
		} catch (HibernateException ex) {
			logger.error("getById failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getById fin");
		return etiquetatge;
	}


	/**
	 * Devuelve un Etiquetatge de la BBDD a partir de su nombre
	 * @params name
	 * @return
	 * @throws InfrastructureException
	 */
	public Long getEtiquetatge(String name) throws InfrastructureException {
		List etiquetatges;

		try {
			logger.debug("getEtiquetatge ini");
			String q = "select et.id from Etiquetatge et usu where et.nom = '"+name+"'";
			etiquetatges = getHibernateTemplate().find(q);
			if (etiquetatges.size() > 0) {
				logger.debug("getEtiquetatge fin");
				return (Long) etiquetatges.get(0);
			}

		} catch (HibernateException ex) {
			logger.error("getEtiquetatge failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getEtiquetatge fin");
		return null;
	}


	/**
	 * Devuelve un Etiquetatge de la BBDD a partir de su nombre
	 * @params name
	 * @return
	 * @throws InfrastructureException
	 */
	public List getEtiquetatgeByMarca(Long idMarca) throws InfrastructureException {
		List etiquetatges;
		try {
			logger.debug("getEtiquetatgeByMarca ini");			
			String q = "from Etiquetatge et where et.marca.id = "+idMarca;
			etiquetatges = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("getEtiquetatgeByMarca failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getEtiquetatgeByMarca fin");			
		return etiquetatges;
	}

	/**
	 * Retorna tots els etiquetatges de un establiment
	 * @params establimentId
	 * @return
	 * @throws InfrastructureException
	 */
	public List findByEstabliment(Long establimentId) {
		List etiquetatges;
		try {
			logger.debug("findByEstabliment ini");			
			String q = 	"select distinct et " +
						"from Etiquetatge as et " +
						"left join fetch et.marca m " +
						"left join fetch m.establiments e " +
						"where e.id = " + establimentId + " " +
						"order by m.nom, et.tipusEnvas, et.observacions, et.id";
			etiquetatges = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("findByEstabliment failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findByEstabliment fin");			
		return etiquetatges;
	}
	
	/**
	 * Retorna true si existen establecimientos asociados a usuarios
	 * @return
	 * @throws InfrastructureException
	 */
	public boolean existenEtiquetajesAsociadosTiposEnvase(Long idTipEnv) throws InfrastructureException {
		List etiquetaje;
		try {
			logger.debug("existenEtiquetajesAsociadosTiposEnvase ini");			
			String q = "select count(eti.id) from Etiquetatge eti " +
			"where eti.tipusEnvas.id = " + idTipEnv + " ";
			etiquetaje = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("existenEtiquetajesAsociadosTiposEnvase failed", ex);			
			throw new InfrastructureException(ex);
		}
		if (etiquetaje != null && etiquetaje.size()> 0 && ((Long)etiquetaje.get(0)).intValue() > 0) {
			logger.debug("existenEtiquetajesAsociadosTiposEnvase fin");			
			return true;
		}
		logger.debug("existenEtiquetajesAsociadosTiposEnvase fin");			
		return false;
	}


	/**
	 * Actualiza un registro
	 * @param etiquetatge
	 * @throws InfrastructureException
	 */
	public void makePersistent(Etiquetatge etiquetatge) throws InfrastructureException {
		try {
			logger.debug("makePersistent ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().saveOrUpdate(etiquetatge);
			getHibernateTemplate().flush();
			logger.debug("makePersistent fin");
		} catch (HibernateException ex) {
			logger.error("makePersistent failed", ex);
			throw new InfrastructureException(ex);
		}
	}


	/**
	 * Borra un registro
	 * @param etiquetatge
	 * @throws InfrastructureException
	 */
	public void makeTransient(Etiquetatge etiquetatge)
	throws InfrastructureException {
		try {
			logger.debug("makeTransient fin");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().delete(etiquetatge);
			getHibernateTemplate().flush();
			logger.debug("makeTransient fin");
		} catch (HibernateException ex) {
			logger.debug("makeTransient failed", ex);
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
