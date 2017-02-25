package es.caib.gestoli.logic.dao;

import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.exception.InfrastructureException;
import es.caib.gestoli.logic.model.VarietatOliva;


/**
 * Home object for domain model class VarietatOliva
 * @see es.caib.gestoli.logic.model.VarietatOliva
 * @author Oriol Barnés
 */
public class VarietatOlivaDao {
	private static Logger logger = Logger.getLogger(VarietatOlivaDao.class);
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
			col = getHibernateTemplate().find("from VarietatOliva as vov order by vov.nom"); 
			logger.debug("findAll fin");
		} catch (HibernateException ex) {
			logger.error("findAll failed", ex);
			throw new InfrastructureException(ex);
		}
		return col;
	}

	/**
	 * Recupera todos los registros
	 * @return
	 * @throws InfrastructureException
	 */
	public List getVarietatsOlivaByOlivicultor(Long idOlivicultor) throws InfrastructureException {
		List col;
		try {
			logger.debug("getVarietatsOlivaByOlivicultor inbi");
			col = getHibernateTemplate().find("select distinct dpl.varietatOliva from DescomposicioPlantacio as dpl where dpl.plantacio.finca in( from Finca as fin where fin.olivicultor.id = "+idOlivicultor+")");			
		} catch (HibernateException ex) {
			logger.error("getVarietatsOlivaByOlivicultor failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getVarietatsOlivaByOlivicultor fin");
		return col;
	}
	
	/**
	 * Recupera todos los registros
	 * @return
	 * @throws InfrastructureException
	 */
	public List getTotesVarietatsOlivaByOlivicultor(Long idOlivicultor) throws InfrastructureException {
		List col;
		try {
			logger.debug("getVarietatsOlivaByOlivicultor inbi");
			col = getHibernateTemplate().find("select dpl.varietatOliva from DescomposicioPlantacio as dpl where dpl.plantacio.finca in( from Finca as fin where fin.olivicultor.id = "+idOlivicultor+")");			
		} catch (HibernateException ex) {
			logger.error("getVarietatsOlivaByOlivicultor failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getVarietatsOlivaByOlivicultor fin");
		return col;
	}
	
	/**
	 * Recupera tots el registres de varietats d'oliva d'un olivicultor a una plantació.
	 * @return
	 * @throws InfrastructureException
	 */
	public List getVarietatsOlivaByOlivicultorPlantacio(Long idOlivicultor, Long idPlantacio) throws InfrastructureException {
		List col;
		try {
			col = getHibernateTemplate().find(
					"select distinct dp.varietatOliva " +
					"from DescomposicioPlantacio dp " +
					"where dp.plantacio.finca.id in ( " +
					"	select f.id " +
					"	from Finca f, Plantacio p " +
					"	where f.olivicultor.id = " + idOlivicultor + " " +
					"	and p.finca.id = f.id " +
					"	and p.id = " + idPlantacio + " " +
					")");
		} catch (HibernateException ex) {
			logger.error("getVarietatsOlivaByOlivicultor failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getVarietatsOlivaByOlivicultor fin");
		return col;
	}

	/**
	 * Recupera todos los registros
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection findAllOrdered() throws InfrastructureException {
		Collection col;
		try {
			logger.debug("findAll ini");
			col = getHibernateTemplate().find(
					"from VarietatOliva as vov " +
					"where vov.actiu = true " +
					"order by vov.autoritzada desc, vov.nom");			
		} catch (HibernateException ex) {
			logger.error("findAll failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findAll fin");
		return col;
	}
	
	/**
	 * Recupera todos los registros
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection findAllNoExperimentalsOrdered() throws InfrastructureException {
		Collection col;
		try {
			col = getHibernateTemplate().find(
					"from VarietatOliva " +
					"where (experimental is null or experimental = false) " +
					"and autoritzada = true " +
					"order by nom asc");			
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		return col;
	}

	/**
	 * Devuelve el objeto que tiene el id
	 * @param id
	 * @return
	 * @throws InfrastructureException
	 */
	public VarietatOliva getById(Integer id) throws InfrastructureException {

		List variedades;
		try {
			logger.debug("getById ini");
			String q = "from VarietatOliva as vo where vo.id = '"+id+"'";
			variedades = getHibernateTemplate().find(q);
			if (variedades.size() > 0) {
				logger.debug("getById fin");
				return (VarietatOliva)variedades.get(0);
			}

		} catch (HibernateException ex) {
			logger.error("getById failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getById fin");
		return null;		

	}


	/**
	 * Actualiza un registro
	 * @param varietatOliva
	 * @throws InfrastructureException
	 */
	public void makePersistent(VarietatOliva varietatOliva) throws InfrastructureException {
		try {
			logger.debug("makePersistent ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().saveOrUpdate(varietatOliva);
			getHibernateTemplate().flush();
			logger.debug("makePersistent fin");
		} catch (HibernateException ex) {
			logger.error("makePersistent failed", ex);
			throw new InfrastructureException(ex);
		}
	}

	/**
	 * Varietat d'oliva que té la id indicada.
	 */
	public VarietatOliva getById(Long id) throws InfrastructureException {
		VarietatOliva varietatOliva;
		try {
			varietatOliva = (VarietatOliva)getHibernateTemplate().load(VarietatOliva.class, id);
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		return varietatOliva;
	}

	/**
	 * Varietats d'oliva experimentals.
	 * @return
	 */
	public Collection findAllExperimentals() {
		Collection col;
		try {
			col = getHibernateTemplate().find(
					"from VarietatOliva " +
					"where experimental = true " +
					"and actiu = true " +
					"order by nomVarietat asc ");			
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		return col;
	}
	
	/**
	 * Varietats d'oliva de taula.
	 * @return
	 */
	public Collection findAllOlivaTaula() {
		Collection col;
		try {
			col = getHibernateTemplate().find(
					"from VarietatOliva " +
					"where olivaTaula = true " +
					"and actiu = true " +
					"order by nomVarietat asc ");			
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		return col;
	}
	
	/**
	 * Esborra un registre.
	 * @param olivicultor
	 * @throws InfrastructureException
	 */
	public void makeTransient(VarietatOliva varietatOliva) throws InfrastructureException {
		try {
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().delete(varietatOliva);
			getHibernateTemplate().flush();
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
	}

	/**
	 * Varietats d'oliva actives.
	 * @return
	 */
	public Collection findActius() {
		Collection col = getHibernateTemplate().find(
				"from VarietatOliva " +
				"where actiu = true " +
				"order by nomVarietat asc ");	
		return col;
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