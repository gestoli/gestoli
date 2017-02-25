package es.caib.gestoli.logic.dao;

import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.exception.InfrastructureException;
import es.caib.gestoli.logic.model.Plantacio;


/**
 * Home object for domain model class Plantacio
 * @see es.caib.gestoli.logic.model.Plantacio
 * 
 */
public class PlantacioDao {
	private static Logger logger = Logger.getLogger(PlantacioDao.class);
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
			col = getHibernateTemplate().find("from Plantacio as pl order by pl.id"); 
		} catch (HibernateException ex) {
			logger.error("findAll failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findAll fin");
		return col;
	}


	/**
	 * Retorna true si existen fincas asociadas a olivicultores
	 * @return
	 * @throws InfrastructureException
	 */
	public boolean existenPlantacionesAsociadasFincas(Long idFinca) throws InfrastructureException {
		List plantacions;
		try {
			logger.debug("existenPlantacionesAsociadasFincas ini");
			String q = "select count(pla.id) from Plantacio pla where pla.finca.id = " + idFinca + " ";
			plantacions = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("existenPlantacionesAsociadasFincas failed", ex);
			throw new InfrastructureException(ex);
		}
		if (plantacions != null && plantacions.size()> 0 && ((Long)plantacions.get(0)).intValue() > 0) {
			logger.debug("existenPlantacionesAsociadasFincas fin");
			return true;
		}
		logger.debug("existenPlantacionesAsociadasFincas fin");
		return false;
	}


	/**
	 * Devuelve el objeto que tiene el id de finca fincaId
	 * @param fincaId
	 * @return
	 * @throws InfrastructureException
	 */
	public List getByFincaId(Long fincaId) throws InfrastructureException {
		List plantaciones;
		try {
			logger.debug("getByFincaId ini");
			String q =	"from Plantacio as pl " +
						"where pl.finca.id = " + fincaId + " " +
						"and (pl.deBaixa is null or pl.deBaixa = false) " +
						"order by pl.municipi, pl.poligon, pl.parcela";
			plantaciones = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("getByFincaId failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getByFincaId fin");
		return plantaciones;
	}


	/**
	 * Devuelve el objeto que tiene el id de finca fincaId
	 * @param fincaId
	 * @return
	 * @throws InfrastructureException
	 */
	public List getActivasByFincaId(Long fincaId) throws InfrastructureException {
		List plantaciones;
		try {
			logger.debug("getActivasByFincaId ini");
			String q = "from Plantacio as pl where pl.actiu = true and pl.finca.id = " + fincaId;
			plantaciones = getHibernateTemplate().find(q);

		} catch (HibernateException ex) {
			logger.error("getActivasByFincaId failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getActivasByFincaId fin");
		return plantaciones;
	}

	/**
	 * Devuelve el objeto que tiene el id de finca fincaId
	 * @param fincaId
	 * @return
	 * @throws InfrastructureException
	 */
	public List getActivasConDescomposicionByFincaId(Long fincaId) throws InfrastructureException {
		List plantaciones;
		try {
			logger.debug("getActivasConDescomposicionByFincaId ini");
			
			String q = "select distinct dpl.plantacio from DescomposicioPlantacio as dpl" +
			" where dpl.plantacio.finca.id = " +fincaId+
			" and dpl.plantacio.actiu = true" +
			" and dpl.plantacio.finca.actiu = true";
			
			plantaciones = getHibernateTemplate().find(q);

		} catch (HibernateException ex) {
			logger.error("getActivasConDescomposicionByFincaId failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getActivasConDescomposicionByFincaId fin");
		return plantaciones;
	}

	/**
	 * Devuelve el objeto que tiene el id de finca fincaId
	 * @param fincaId
	 * @return
	 * @throws InfrastructureException
	 */
	public List getActivasByOlivicultorId(Long olivicultorId) throws InfrastructureException {
		List plantaciones;
		try {
			logger.debug("getActivasByOlivicultorId ini");
			
			String q = "from Plantacio as pl where pl.actiu = true" +
			" and pl.finca in( from Finca as finca where finca.olivicultor.id = "+olivicultorId+" and finca.actiu = true)" ;
			
			plantaciones = getHibernateTemplate().find(q);

		} catch (HibernateException ex) {
			logger.error("getActivasByOlivicultorId failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getActivasByOlivicultorId fin");
		return plantaciones;
	}

	/**
	 * Devuelve el objeto que tiene el id de finca fincaId
	 * @param olivicultorId
	 * @return
	 * @throws InfrastructureException
	 */
	public List getActivasConDescomposicionesByOlivicultorId(Long olivicultorId) throws InfrastructureException {
		List plantaciones;
		try {
			logger.debug("getActivasConDescomposicionByFincaId ini");
			
			//			String q = "from Plantacio as pla" +
			//					" where pla.actiu = true" +
			//					" and pla.finca.actiu = true" +
			//					" and pla.finca.olivicultor.id = "+olivicultorId+
			//					" and pla in (select dpl.plantacio from DescomposicioPlantacio as dpl where dpl.plantacio.finca.olivicultor.id = "+olivicultorId+" )" ;				
			//
			String q = "select distinct dpl.plantacio from DescomposicioPlantacio as dpl" +
			" where dpl.plantacio.finca.olivicultor.id = "+olivicultorId +
			" and dpl.plantacio.actiu = true" +
			" and dpl.plantacio.finca.actiu = true";

			plantaciones = getHibernateTemplate().find(q);

		} catch (HibernateException ex) {
			logger.error("getActivasConDescomposicionesByOlivicultorId failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getActivasConDescomposicionByFincaId fin");
		return plantaciones;
	}




	/**
	 * Recupera todos los registros de una finca
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection findAllIdPlantacioActivas(Long idFinca) throws InfrastructureException {
		Collection col;
		try {
			logger.debug("findAllIdPlantacioActivas ini");
			
			String q = "select plantacio.id from Plantacio as plantacio where plantacio.actiu = true and plantacio.finca.id = "+idFinca+" order by plantacio.id"; 
			
			col = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("findAllIdPlantacioActivas failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findAllIdPlantacioActivas fin");
		return col;
	}



	/**
	 * Actualiza un registro
	 * @param plantacio
	 * @throws InfrastructureException
	 */
	public void makePersistent(Plantacio plantacio) throws InfrastructureException {
		try {
			logger.debug("makePersistent ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().saveOrUpdate(plantacio);
			// Informamos el idOriginal
			if (plantacio.getIdOriginal() == null) {
				plantacio.setIdOriginal(plantacio.getId());
			}
			getHibernateTemplate().flush();
		} catch (HibernateException ex) {
			logger.error("makePersistent failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("makePersistent fin");
	}





	/**
	 * Actualiza un registro
	 * @param plantacio
	 * @param session
	 * @throws InfrastructureException
	 */
	public void makePersistent(Plantacio plantacio, Session session) throws InfrastructureException {
		try {
			logger.debug("makePersistent ini");
			session.setFlushMode(FlushMode.ALWAYS);
			session.saveOrUpdate(plantacio);
			session.flush();
		} catch (HibernateException ex) {
			logger.error("makePersistent failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("makePersistent fin");
	}

	/**
	 * Borra un registro
	 * @param plantacio
	 * @throws InfrastructureException
	 */
	public void makeTransient(Plantacio plantacio) throws InfrastructureException {
		try {
			logger.debug("makeTransient ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().delete(plantacio);
			getHibernateTemplate().flush();
		} catch (HibernateException ex) {
			logger.error("makeTransient failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("makeTransient fin");
	}

	/**
	 * Devuelve el objeto que tiene el id
	 * @param id
	 * @return
	 * @throws InfrastructureException
	 */
	public Plantacio getById(Long id) throws InfrastructureException {
		Plantacio plantacio;
		try {
			logger.debug("getById ini");
			plantacio = (Plantacio)getHibernateTemplate().load(Plantacio.class, id);	
		} catch (HibernateException ex) {
			logger.error("getById failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getById fin");
		return plantacio;
	}

	/**
	 * Llista les plantacions que estan donades de baixa.
	 * @return
	 */
	public List findPlantacionsDeBaixa() throws InfrastructureException {
		try {
			String q = "from Plantacio p where p.deBaixa = true";
			List plantacions = getHibernateTemplate().find(q);
			if (plantacions.size() > 0) {
				return plantacions;
			}
			return null;
		} catch (HibernateException ex) {
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