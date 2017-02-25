package es.caib.gestoli.logic.dao;

import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.exception.InfrastructureException;
import es.caib.gestoli.logic.model.HistoricDescomposicioPlantacio;


/**
 * Home object for domain model class DescomposicioPlantacio
 * @see es.caib.gestoli.logic.model.DescomposicioPlantacioDao
 * 
 */
public class HistoricDescomposicioPlantacioDao {
	private static Logger logger = Logger.getLogger(HistoricDescomposicioPlantacioDao.class);
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
			col = getHibernateTemplate().find("from HistoricDescomposicioPlantacio as pl order by pl.id");
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
	public Collection findAllOrdered() throws InfrastructureException {
		Collection col;
		try {
			logger.debug("findAllOrdered ini");
			col = getHibernateTemplate().find("from HistoricDescomposicioPlantacio as pl order by pl.varietatOliva.autoritzada, pl.varietatOliva.nom"); 
		} catch (HibernateException ex) {
			logger.error("findAllOrdered failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findAllOrdered fin");
		return col;
	}


	/**
	 * Recupera todos los registros
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection findDescomposicioPlantacioPerOlivicultor(Long idOlivicultor) throws InfrastructureException {
		Collection col;
		try {
			logger.debug("findDescomposicioPlantacioPerOlivicultor ini");
			col = getHibernateTemplate().find("from HistoricDescomposicioPlantacio as pl " +
					"where pl.plantacio.finca.olivicultor.id = "+idOlivicultor+" and " +
					"pl.plantacio.actiu = true and pl.plantacio.finca.actiu = true " +
			"order by pl.plantacio.municipi, pl.plantacio.poligon, pl.plantacio.parcela, pl.varietatOliva.nom"); 
		} catch (HibernateException ex) {
			logger.error("findDescomposicioPlantacioPerOlivicultor failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findDescomposicioPlantacioPerOlivicultor fin");
		return col;
	}


	/**
	 * Recupera todos los registros
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection getDescPlantacioActivasAmbFinca(Long idFinca) throws InfrastructureException {
		Collection col;
		try {
			logger.debug("getDescPlantacioActivasAmbFinca ini");
			col = getHibernateTemplate().find("from HistoricDescomposicioPlantacio as dpl where dpl.plantacio.actiu = true and dpl.plantacio.finca.id = "+idFinca+" order by dpl.varietatOliva.autoritzada, dpl.varietatOliva.nom"); 
		} catch (HibernateException ex) {
			logger.error("getDescPlantacioActivasAmbFinca failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getDescPlantacioActivasAmbFinca fin");
		return col;
	}


	/**
	 * Recupera todos los registros
	 * @return
	 * @throws InfrastructureException
	 */
	public List getDescPlantacioActivasByOlivicultor(Long idOlivicultor) throws InfrastructureException {
		List col;
		try {
			logger.debug("getDescPlantacioActivasByOlivicultor ini");
			String q = "from HistoricDescomposicioPlantacio as descomposicion " +
					"where descomposicion.plantacio.actiu = true " +
					"and descomposicion.plantacio.finca.olivicultor.id = "+idOlivicultor+" and descomposicion.plantacio.finca.actiu = true) ";
			col = getHibernateTemplate().find(q); 
		} catch (HibernateException ex) {
			logger.error("getDescPlantacioActivasByOlivicultor failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getDescPlantacioActivasByOlivicultor fin");
		return col;
	}



	
	

	/**
	 * Actualiza un registro
	 * @param descomposicioplantacio
	 * @throws InfrastructureException
	 */
	public void makePersistent(HistoricDescomposicioPlantacio descomposicioplantacio) throws InfrastructureException {
		try {
			logger.debug("makePersistent ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().saveOrUpdate(descomposicioplantacio);
			// Informamos el idOriginal
			if (descomposicioplantacio.getIdOriginal() == null) {
				descomposicioplantacio.setIdOriginal(descomposicioplantacio.getId());
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
	 * @param descomposicioplantacio
	 * @throws InfrastructureException
	 */
	public void makePersistent(HistoricDescomposicioPlantacio descomposicioplantacio, Session session) throws InfrastructureException {
		try {
			session.setFlushMode(FlushMode.ALWAYS);
			if(descomposicioplantacio.getId() != null){
				session.update(descomposicioplantacio);
			}else{
				session.save(descomposicioplantacio);				
			}
			session.flush();
			logger.debug("makePersistent fin");
		} catch (HibernateException ex) {
			logger.error("makePersistent failed", ex);
			throw new InfrastructureException(ex);
		}
	}


	/**
	 * Borra un registro
	 * @param plantacio
	 * @throws InfrastructureException
	 */
	public void makeTransient(HistoricDescomposicioPlantacio descomposicioplantacio) throws InfrastructureException {
		try {
			logger.error("makeTransient ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().delete(descomposicioplantacio);
			getHibernateTemplate().flush();
			logger.error("makeTransient fin");
		} catch (HibernateException ex) {
			logger.error("makeTransient failed", ex);
			throw new InfrastructureException(ex);
		}
	}


	/**
	 * Devuelve el objeto que tiene el id
	 * @param id
	 * @return
	 * @throws InfrastructureException
	 */
	public HistoricDescomposicioPlantacio getById(Long id) throws InfrastructureException {
		HistoricDescomposicioPlantacio descomposicioPlantacio;
		try {
			logger.debug("getById ini");
			descomposicioPlantacio = (HistoricDescomposicioPlantacio)getHibernateTemplate().load(HistoricDescomposicioPlantacio.class, id);	
		} catch (HibernateException ex) {
			logger.error("getById failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getById fin");
		return descomposicioPlantacio;
	}


	/**
	 * Devuelve la coleccion que tiene el id
	 * @param id
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection getByIdPlantacio(Long id) throws InfrastructureException {
		Collection col;
		try {
			logger.debug("getByIdPlantacio ini");
			String q = "from HistoricDescomposicioPlantacio as pl where pl.plantacio.id = '"+id+"'"; 
			col = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("getByIdPlantacio failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getByIdPlantacio fin");
		return col;
	}


	/**
	 * Devuelve la descomposicion de plantacion 
	 * @param idPlantacio
	 * @param idVariedad
	 * @return
	 * @throws InfrastructureException
	 */
	public List getByIdPlantacioIdVariedad(Long idPlantacio,Integer idVariedad) throws InfrastructureException {

		List col;
		try {
			logger.debug("getByIdPlantacioIdVariedad ini");
			String q = "from HistoricDescomposicioPlantacio as dpo where dpo.plantacio.id = "+idPlantacio+" and dpo.varietatOliva.id= "+idVariedad; 
			col = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("getByIdPlantacioIdVariedad failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getByIdPlantacioIdVariedad fin");
		return col;
	}
	
	
	
	/**
	 * Devuelve las superficies de cada variedad de oliva 
	 * @param idPlantacio
	 * @param idVariedad
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection getSuperficiesVarietatsDO(Long idTemporada) throws InfrastructureException {

		Collection col=null;
		
		try {

			logger.debug("getSuperficiesVarietatsDO ini");
			String q = "select dpo.varietatOliva.nom, sum(dpo.superficie) from HistoricDescomposicioPlantacio as dpo where";
			q+= " dpo.plantacio.actiu=true and dpo.plantacio.finca.actiu=true";
			q+= " and dpo.plantacio.finca.olivicultor.altaDO=true and dpo.plantacio.finca.olivicultor.campanya.id="+idTemporada;
			q+= " and dpo.plantacio.finca.olivicultor.cartilla=true";
			q+= " group by dpo.varietatOliva.nom order by sum(dpo.superficie) desc";
			col = getHibernateTemplate().find(q);
			
		} catch (HibernateException ex) {
			logger.error("getSuperficiesVarietatsDO failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getSuperficiesVarietatsDO fin");
		return col;
	}
	
	
	
	/**
	 * Devuelve el numero de arboles de cada variedad de oliva 
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection getNumeroArbresVarietatsDO(Long idTemporada) throws InfrastructureException {

		Collection col=null;
		
		try {

			logger.debug("getNumeroArbresVarietatsDO ini");
			String q = "select dpo.varietatOliva.nom, sum(dpo.numeroOliveres) from HistoricDescomposicioPlantacio as dpo where";
			q+= " dpo.plantacio.actiu=true and dpo.plantacio.finca.actiu=true";
			q+= " and dpo.plantacio.finca.olivicultor.altaDO=true and dpo.plantacio.finca.olivicultor.campanya.id="+idTemporada;
			q+= " and dpo.plantacio.finca.olivicultor.cartilla=true";
			q+= " group by dpo.varietatOliva.nom order by sum(dpo.numeroOliveres) desc";
			col = getHibernateTemplate().find(q);
			
		} catch (HibernateException ex) {
			logger.error("getNumeroArbresVarietatsDO failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getNumeroArbresVarietatsDO fin");
		return col;
	}

	
	/**
	 * Devuelve las superficies de cada variedad de oliva, por cada tipo, y su municipio 
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection getSuperficiesVarietats(Long idTemporada) throws InfrastructureException {

		Collection col=null;
		
		try {

			logger.debug("getSuperficiesVarietats ini");
			String q = 
				"select dpo.plantacio.municipi.nom as municipi, dpo.varietatOliva.nom as varietat, sum(dpo.superficie) as superficieTotal, " +
				"sum(case when dpo.varietatOliva.autoritzada is true then dpo.superficie else 0 end) as superficieDO, " +
				"sum(case when dpo.varietatOliva.experimental is true then dpo.superficie else 0 end) as superficieNoDO " +
				"from 	HistoricDescomposicioPlantacio as dpo " +
				"where  dpo.plantacio.actiu=true " +
				"and 	dpo.plantacio.finca.actiu=true " + 
				"and    dpo.plantacio.finca.olivicultor.altaDO=true " +
				"and 	dpo.plantacio.finca.olivicultor.campanya.id= "+idTemporada + " " +
				"group by dpo.plantacio.municipi.nom, dpo.varietatOliva.nom " +
				"order by sum(dpo.plantacio.municipi.nom)";
			col = getHibernateTemplate().find(q);
			
		} catch (HibernateException ex) {
			logger.error("getSuperficiesVarietatsDO failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getSuperficiesVarietatsDO fin");
		return col;
	}
	
	/**
	 * Totes les descomposicions que tenen la varietat d'oliva indicada.
	 * @param idVarietat
	 * @return
	 */
	public Collection findByVarietat(Integer idVarietat) {
		try {
			String q =	"from HistoricDescomposicioPlantacio " +
						"where varietatOliva.id= " + idVarietat;
			Collection col = getHibernateTemplate().find(q);
			if (col.size() > 0)
				return col;
			return null;
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
	}
	
	/**
	 * Totes les varietats que pertanyen a les plantacions indicades.
	 * 		Les plantacions es passen en un String separades per coma.
	 * @param ids
	 * @return
	 */
	public Collection findByPlantacions(String ids) {
		try {
			String q =	"select distinct dp.varietatOliva, dp.produccioRestant " +
						"from HistoricDescomposicioPlantacio dp " +
						"where dp.plantacio.id in (" + ids + ") " +
						"and dp.varietatOliva.actiu = true " +
						"order by dp.varietatOliva.nomVarietat, dp.varietatOliva.nom asc ";
			Collection col = getHibernateTemplate().find(q);
			if (col.size() > 0)
				return col;
			return null;
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
	}
	
	/**
	 * La producció restant total de l'olivicultor.
	 * @param idOlivicutor
	 * @return
	 */
	public Double obtenirTotalProduccioRestantOlivicultor(Long idOlivicutor) {
		try {
			Collection col;
			String q =	"select dp.produccioRestant " +
						"from HistoricFinca f, HistoricPlantacio p, HistoricDescomposicioPlantacio dp " +
						"where f.olivicultor.id = " + idOlivicutor + " " +
						"and p.finca.id = f.id " +
						"and dp.plantacio.id = p.id ";
			col = getHibernateTemplate().find(q);
			Double total = 0.0;
			for (Object obj : col){
				Double rest = (Double)obj;
				if (rest == null) rest = 0.0;
				total += rest;
			}
			return total;
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