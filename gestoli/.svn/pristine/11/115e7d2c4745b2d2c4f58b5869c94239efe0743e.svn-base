package es.caib.gestoli.logic.dao;

import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.exception.InfrastructureException;
import es.caib.gestoli.logic.model.HistoricZona;
import es.caib.gestoli.logic.model.Zona;


/**
 * Home object for domain model class Establiment
 * @see es.caib.gestoli.logic.model.Establiment
 * 
 */
public class HistoricZonaDao {
	private static Logger logger = Logger.getLogger(HistoricZonaDao.class);
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
			col = getHibernateTemplate().find("from HistoricZona as zon order by zon.nom");			
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
	public Zona getById(Long id) throws InfrastructureException {

		List zonas;
		try {
			logger.debug("getById ini");
			String q = "from HistoricZona as zon where zon.id = '"+id+"'";
			zonas = getHibernateTemplate().find(q);
			if (zonas.size() > 0) {
				logger.debug("getById fin");
				return (Zona)zonas.get(0);
			}

		} catch (HibernateException ex) {
			logger.error("getById failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getById fin");
		return null;		

	}


	/**
	 * Retorna un llista de totes les zones associades amb
	 * un establiment determinat.
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public List findActiusByEstabliment(Long establimentId) throws InfrastructureException {
		List zonas;
		try {
			logger.debug("findActiusByEstabliment ini");
			String q = "from HistoricZona zon " +
			"where zon.establiment.id = '"+establimentId+"' " +
			"and zon.actiu = 'true' " +
			"order by zon.nom";
			zonas = getHibernateTemplate().find(q);;

		} catch (HibernateException ex) {
			logger.error("findActiusByEstabliment failed",ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findActiusByEstabliment fin");
		return zonas;
	}





	/**
	 * Retorna un llista de totes les zones associades amb
	 * un establiment determinat.
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public List findByEstabliment(Long establimentId) throws InfrastructureException {
		List zonas;
		try {
			logger.debug("findByEstabliment ini");
			String q = "from HistoricZona zon " +
			"where zon.establiment.id = '"+establimentId+"' " +
			"order by zon.nom";
			zonas = getHibernateTemplate().find(q);

		} catch (HibernateException ex) {
			logger.error("findByEstabliment failed,ex");
			throw new InfrastructureException(ex);
		}
		logger.debug("findByEstabliment fin");
		return zonas;
	}


	/**
	 * Retorna un llista de totes les zones ficticias associades amb
	 * un establiment determinat.
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public List findFicticiaByEstabliment(Long establimentId) throws InfrastructureException {
		List zonas;
		try {
			logger.debug("findFicticiaByEstabliment ini");
			String q = "from HistoricZona zon " +
			"where zon.fictici =true and zon.establiment.id = '"+establimentId+"' " +
			"order by zon.nom";
			zonas = getHibernateTemplate().find(q);;

		} catch (HibernateException ex) {
			logger.error("findFicticiaByEstabliment failed,ex");
			throw new InfrastructureException(ex);
		}
		logger.debug("findFicticiaByEstabliment fin");
		return zonas;
	}

	/**
	 * Retorna la zona per defecte per
	 * un establiment determinat.
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public Zona findZonaRetornByEstabliment(Long establimentId) throws InfrastructureException {
		List zonas = null;
		try {
			logger.debug("findZonaRetornByEstabliment ini");
			String q = "from HistoricZona zon " +
			"where zon.defecteTrasllat =true and zon.establiment.id = '"+establimentId+"' " +
			"order by zon.nom";
			zonas = getHibernateTemplate().find(q);;

		} catch (HibernateException ex) {
			logger.error("findZonaRetornByEstabliment failed,ex");
			throw new InfrastructureException(ex);
		}
		logger.debug("findZonaRetornByEstabliment fin");
		if(zonas != null && !zonas.isEmpty()){
			return (Zona)zonas.get(0);
		} else {
			return null;
		}
	}

	/**
	 * Retorna true si existen zonas activas de un establiment
	 * 
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public Boolean existenZonasActiusEnEstabliment(Long establimentId) throws InfrastructureException {
		List zonas;
		try {
			logger.debug("existenZonasActiusEnEstabliment ini");
			String q = "select count (zon.id) from HistoricZona zon " +
			"where zon.establiment.id = " + establimentId + " " +
			"and zon.actiu = 'true' ";
			zonas = getHibernateTemplate().find(q);

		} catch (HibernateException ex) {
			logger.error("existenZonasActiusEnEstabliment failed,ex");
			throw new InfrastructureException(ex);
		}
		if (zonas!= null && zonas.size()> 0 && ((Long)zonas.get(0)).intValue() > 0){
			logger.debug("existenZonasActiusEnEstabliment fin");
			return new Boolean(true);
		}	
		logger.debug("existenZonasActiusEnEstabliment fin");	
		return new Boolean(false);
	}


	/**
	 * Retorna true si existen establecimientos asociados a usuarios
	 * 
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public boolean existenZonasAsociadasEstablecimientos(Long idEstabliment) throws InfrastructureException {
		List zona;
		try {
			logger.debug("existenZonasAsociadasEstablecimientos ini");
			String q = "select count(zon.id) from HistoricZona zon " +
			"where zon.establiment.id = " + idEstabliment + " ";
			zona = getHibernateTemplate().find(q);

		} catch (HibernateException ex) {
			logger.error("existenZonasAsociadasEstablecimientos failed",ex);
			throw new InfrastructureException(ex);
		}
		if (zona != null && zona.size()> 0 && ((Long)zona.get(0)).intValue() > 0){
			logger.debug("existenZonasAsociadasEstablecimientos fin");
			return true;
		}
		logger.debug("existenZonasAsociadasEstablecimientos fin");
		return false;
	}


	/**
	 * Retorna true si la zona es ficticia
	 * 
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public boolean zonaFicticia(Long id) throws InfrastructureException {
		List zona;
		try {
			logger.debug("zonaFicticia ini");
			String q = "select zon.fictici from HistoricZona zon " +
			"where zon.id = " + id + " ";
			zona =  getHibernateTemplate().find(q);
			if (zona != null && zona.size()> 0 && ((Boolean)zona.get(0)).booleanValue() == true){
				logger.debug("zonaFicticia fin");
				return true;
			}

		} catch (HibernateException ex) {
			logger.error("zonaFicticia fin",ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("zonaFicticia fin");
		return false;
	}	



	/**
	 * Actualiza un registro
	 * @param Zona
	 * @throws InfrastructureException
	 */
	public void makePersistent(HistoricZona zona) throws InfrastructureException {
		try {
			logger.debug("makePersistent ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().saveOrUpdate(zona);
			// Informamos el idOriginal
			if (zona.getIdOriginal() == null) {
				zona.setIdOriginal(zona.getId());
			}
			getHibernateTemplate().flush();
			logger.debug("makePersistent fin");
		} catch (HibernateException ex) {
			logger.error("makePersistent failed", ex);
			throw new InfrastructureException(ex);
		}
	}

	/**
	 * Actualiza un registro
	 * @param Zona
	 * @throws InfrastructureException
	 */
	public void makePersistent(HistoricZona zona, Session session) throws InfrastructureException {
		try {
			logger.debug("makePersistent ini");
			session.setFlushMode(FlushMode.ALWAYS);
			session.saveOrUpdate(zona);
			session.flush();
			logger.debug("makePersistent fin");
		} catch (HibernateException ex) {
			logger.error("makePersistent failed", ex);
			throw new InfrastructureException(ex);
		}
	}

	/**
	 * Borra un registro
	 * @param Zona
	 * @throws InfrastructureException
	 */
	public void makeTransient(HistoricZona zona)
	throws InfrastructureException {
		try {
			logger.debug("makeTransient ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().delete(zona);
			getHibernateTemplate().flush();
			logger.debug("makeTransient fin");
		} catch (HibernateException ex) {
			logger.error("makeTransient failed",ex);
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