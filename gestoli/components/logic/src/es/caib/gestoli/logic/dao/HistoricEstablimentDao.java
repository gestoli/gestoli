package es.caib.gestoli.logic.dao;

import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.exception.InfrastructureException;
import es.caib.gestoli.logic.model.HistoricEstabliment;


/**
 * Home object for domain model class Establiment
 * @see es.caib.gestoli.logic.model.Establiment
 * 
 */
public class HistoricEstablimentDao {
	private HibernateTemplate hibernateTemplate;
	
	/**
	 * Devuelve el objeto que tiene el id
	 * @param id
	 * @return
	 * @throws InfrastructureException
	 */
	public HistoricEstabliment getById(Long id) throws InfrastructureException {
		try {
			String q = "from HistoricEstabliment where id = " + id;
			List historics = getHibernateTemplate().find(q);
			if (historics.size() > 0)
				return (HistoricEstabliment)historics.get(0);

		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		return null;		
	}
	
	/**
	 * Actualiza un registro
	 * @param HistoricEstabliment
	 * @throws InfrastructureException
	 */
	public void makePersistent(HistoricEstabliment historicEstabliment) throws InfrastructureException {
		try {
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			if (historicEstabliment.getId() != null) {
				getHibernateTemplate().evict(this.getById(historicEstabliment.getId()));
				getHibernateTemplate().update(historicEstabliment);
			} else {
				getHibernateTemplate().save(historicEstabliment);
			}
			
			// Informamos el idOriginal
			if (historicEstabliment.getIdOriginal() == null) {
				historicEstabliment.setIdOriginal(historicEstabliment.getId());
			}
			
			getHibernateTemplate().flush();
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
	}
	
	/**
	 * Recupera tots els registres.
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection findAll() throws InfrastructureException {
		Collection col;
		try {
			col = getHibernateTemplate().find("from HistoricEstabliment order by id desc");            
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		return col;
	}

	/**
	 * Recupera tots els registres d'alta i baixa.
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection findAllAltaBaixa() {
		Collection col;
		try {
			col = getHibernateTemplate().find("from HistoricEstabliment where esAltaBaixa = true order by id desc");            
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		return col;
	}
	
	/**
	 * Recupera todos los registros activos
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection findAllActivos(Long campanyaId) throws InfrastructureException {
		Collection col;
		try {
			logger.debug("findAllActivos ini");
			col = getHibernateTemplate().find("from HistoricEstabliment as est where est.actiu = true and est.campanya.id = "+campanyaId+" and dataBaixa is null and esAltaBaixa = false order by est.nom");
		} catch (HibernateException ex) {
			logger.error("findAllActivos failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findAllActivos fin");
		return col;
	}
	
	public Collection findAllActivosProductorsOliva(Long campanyaId) throws InfrastructureException {
		Collection col;
		try {
			logger.debug("findAllActivos ini");
			col = getHibernateTemplate().find("from HistoricEstabliment as est where est.actiu = true and est.campanya.id = "+campanyaId+" and dataBaixa is null and esAltaBaixa = false and est.olivaTaula=true order by est.nom");
		} catch (HibernateException ex) {
			logger.error("findAllActivos failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findAllActivos fin");
		return col;
	}
	
	//comprova si ja existeix aquest establiment a l'històric d'establiments
	/**
	 * Retorna l'objecte amb codi establiment passat al paràmetre id
	 * @param id 
	 * @return
	 * @throws InfrastructureException
	 */
	public HistoricEstabliment getByIdHist(Long id, Long campanya, Integer tipusEst ) throws InfrastructureException {
		List establiments;
		try {
			logger.debug("getByIdhist ini");
			String q = "from HistoricEstabliment as hest " +
					"where hest.establiment.id = '"+id+"'" +
					" and hest.campanya.id = '"+campanya+"'" +
					" and hest.tipusEstabliment.id = '"+tipusEst+"'" +
					" and hest.dataBaixa = null " +
					" and hest.actiu = true ";
			establiments = getHibernateTemplate().find(q);
			if (establiments.size() > 0) {
				return (HistoricEstabliment) establiments.get(0);
			}

		} catch (HibernateException ex) {
			logger.error("getByIdhist failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getByIdHist fi");
		return null;	
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

	private static Logger logger = Logger.getLogger(HistoricEstablimentDao.class);
}