package es.caib.gestoli.logic.dao;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.exception.InfrastructureException;
import es.caib.gestoli.logic.model.DescomposicioPartidaOliva;


/**
 * Home object for domain model class DescomposicioPartidaOliva
 * @see es.caib.gestoli.logic.model.DescomposicioPartidaOlivaDao
 * 
 */
public class DescomposicioPartidaOlivaDao {
	private static Logger logger = Logger.getLogger(DescomposicioPartidaOlivaDao.class);
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
			col = getHibernateTemplate().find("from DescomposicioPartidaOliva as po order by po.id");
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
	public Collection findDescomposicioPartidaOlivaPerOlivicultor(Long idOlivicultor) throws InfrastructureException {
		Collection col;
		try {
			logger.debug("findDescomposicioPartidaOlivaPerOlivicultor ini");
			col = getHibernateTemplate().find("from DescomposicioPartidaOliva as dpo " +
					"where dpo.partidaOliva.olivicultor.id = " + idOlivicultor + " " +
			"order by dpo.partidaOliva.data, dpo.partidaOliva.numeroEntrada, dpo.descomposicioPlantacio.varietatOliva.nom"); 
		} catch (HibernateException ex) {
			logger.error("findDescomposicioPartidaOlivaPerOlivicultor failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findDescomposicioPartidaOlivaPerOlivicultor fin");
		return col;
	}



	/**
	 * Actualiza un registro
	 * @param descomposicioPartidaOliva
	 * @throws InfrastructureException
	 */
	public void makePersistent(DescomposicioPartidaOliva descomposicioPartidaOliva) throws InfrastructureException {
		try {
			logger.debug("makePersistent ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().saveOrUpdate(descomposicioPartidaOliva);
			getHibernateTemplate().flush();
			logger.debug("makePersistent fin");
		} catch (HibernateException ex) {
			logger.error("makePersistent failed", ex);
			throw new InfrastructureException(ex);
		}
	}

	/**
	 * Borra un registro
	 * @param PartidaOliva
	 * @throws InfrastructureException
	 */
	public void makeTransient(DescomposicioPartidaOliva descomposicioPartidaOliva) throws InfrastructureException {
		try {
			logger.debug("makeTransient ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().delete(descomposicioPartidaOliva);
			getHibernateTemplate().flush();
			logger.debug("makeTransient fin");
		} catch (HibernateException ex) {
			logger.error("makeTransient failed", ex);
			throw new InfrastructureException(ex);
		}
	}


//	/**
//	 * Borra un registro
//	 * @param PartidaOliva
//	 * @throws InfrastructureException
//	 */
//	public void makeTransient(DescomposicioPartidaOliva descomposicioPartidaOliva, Session session) throws InfrastructureException {
//		try {
//			session.delete(descomposicioPartidaOliva);
//		} catch (HibernateException ex) {
//			throw new InfrastructureException(ex);
//		}
//	}


	/**
	 * Devuelve el objeto que tiene el id
	 * @param id
	 * @return
	 * @throws InfrastructureException
	 */
	public DescomposicioPartidaOliva getById(Long id) throws InfrastructureException {
		DescomposicioPartidaOliva descomposicioPartidaOliva;
		try {
			logger.debug("getById ini");
			descomposicioPartidaOliva = (DescomposicioPartidaOliva)getHibernateTemplate().load(DescomposicioPartidaOliva.class, id);	
		} catch (HibernateException ex) {
			logger.error("getById failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getById fin");
		return descomposicioPartidaOliva;
	}


	/**
	 * Devuelve la coleccion que tiene el id
	 * @param id
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection getByIdDescomposicioPlantacio(Long id) throws InfrastructureException {
		Collection col;
		try {
			logger.debug("getByIdPartidaOliva ini");
			String q = "from DescomposicioPartidaOliva as po where po.descomposicioPlantacio.id = '"+id+"'"; 
			col = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("getByIdPartidaOliva failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getByIdPartidaOliva fin");
		return col;
	}

	/**
	 * Devuelve la cantidad total de kilos de todas las descomposiciones de partida de una temporada o entre fechas de la temporada actual
	 * @param temporadaId
	 * @param dataInici
	 * @param dataFin
	 * @return
	 * @throws InfrastructureException
	 */
	public Double getTotalOlivasEntradas(Long temporadaId,Date dataInici,Date dataFin, Long temporadaActual) throws InfrastructureException {
		Collection col;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Double numero = null;
		try {
			logger.debug("getTotalOlivasEntradas ini");
			String q = "select sum(dpo.kilos) from DescomposicioPartidaOliva dpo where dpo.kilos > 0";
			q = q+" and dpo.partidaOliva.valid= true ";
			if(dataInici!= null || dataFin != null){
				if(dataInici != null){
					String fi = df.format(dataInici);
					q = q+ " and  dpo.partidaOliva.data >= '"+fi+"' ";
				}
				if(dataFin != null){
					String ff = df.format(dataFin);
					q = q+ " and  dpo.partidaOliva.data <= '"+ff+"' ";
				}
				if (temporadaActual != null) {
					q = q+" and dpo.partidaOliva.zona.establiment.campanya.id= "+temporadaActual;
				}
			} else {
				q = q+ " and dpo.partidaOliva.zona.establiment.campanya.id="+temporadaId;
			}

			col = getHibernateTemplate().find(q);
			while (col.iterator().hasNext()){
				numero = (Double)col.iterator().next();
				break;
			}
		} catch (HibernateException ex) {
			logger.error("getTotalOlivasEntradas failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getTotalOlivasEntradas fin");
		return numero;
	}
	
	/**
	 * Devuelve la cantidad total de kilos de todas las descomposiciones de partida de una temporada o entre fechas de la temporada actual
	 * @param temporadaId
	 * @param dataInici
	 * @param dataFin
	 * @return
	 * @throws InfrastructureException
	 */
	public Double getTotalOlivasEntradasEntreDates(Date dataInici,Date dataFin) throws InfrastructureException {
		Collection col;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Double numero = null;
		try {
			logger.debug("getTotalOlivasEntradasEntreDates ini");
			String q = "select sum(dpo.kilos) from DescomposicioPartidaOliva dpo where dpo.kilos > 0";
			q +=" and dpo.partidaOliva.valid = true ";
			q +=" and (dpo.partidaOliva.olivaTaula is null or dpo.partidaOliva.olivaTaula = false) ";
			if(dataInici != null){
				String fi = df.format(dataInici);
				q = q+ " and  dpo.partidaOliva.data >= '"+fi+"' ";
			}
			if(dataFin != null){
				String ff = df.format(dataFin);
				q = q+ " and  dpo.partidaOliva.data <= '"+ff+"' ";
			}

			col = getHibernateTemplate().find(q);
			while (col.iterator().hasNext()){
				numero = (Double)col.iterator().next();
				break;
			}
		} catch (HibernateException ex) {
			logger.error("getTotalOlivasEntradasEntreDates failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getTotalOlivasEntradasEntreDates fin");
		return numero;
	}


	
//	/**
//	 * Devuelve la cantidad total de kilos de todas las descomposiciones de partida de una temporada o entre fechas de la temporada actual
//	 * @param temporadaId
//	 * @param dataInici
//	 * @param dataFin
//	 * @return
//	 * @throws InfrastructureException
//	 */
//	public Double getTotalOlivaMolturada(Long temporadaId, Date dataInici, Boolean ambDo, Long establimentId) throws InfrastructureException {
//		Collection col;
//		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
//		Double numero = null;
//		try {
//			logger.debug("getTotalOlivaMolturada ini");
//			
//			// Amb kgs
//			String q = "select sum(dpo.kilos) from DescomposicioPartidaOliva dpo where dpo.kilos is not null and dpo.kilos > 0"; 
//
//			// autoritzada?
//			q += " and dpo.descomposicioPlantacio.varietatOliva.autoritzada = "+ambDo.toString();
//			
//			// Data inici
//			if(dataInici != null){
//				String fi = df.format(dataInici);
//				q = q+ " and  dpo.partidaOliva.data >= '"+fi+"' ";
//			}
//			
//			// Filtre establiment?
//			if (establimentId!=null){
//				q = q+" and dpo.partidaOliva.zona.establiment.id="+establimentId;
//			}
//
//			// Esta elaborado
//			q = q+" and dpo.partidaOliva.elaboracio is not null";
//			q = q+" and dpo.partidaOliva.elaboracio.valid = true";
//			
//			// Ultima campaña
//			q = q+" and dpo.partidaOliva.zona.establiment.campanya.id="+temporadaId;
//				
//			col = getHibernateTemplate().find(q);
//			while (col.iterator().hasNext()){
//				numero = (Double)col.iterator().next();
//				break;
//			}
//		} catch (HibernateException ex) {
//			logger.error("getTotalOlivaMolturada failed", ex);
//			throw new InfrastructureException(ex);
//		}
//		logger.debug("getTotalOlivaMolturada fin");
//		if (numero!=null){
//			return numero;
//		}else{
//			return new Double(0);
//		}
//	}
	
//	/**
//	 * Devuelve la cantidad total de kilos de todas las descomposiciones de partida de una temporada o entre fechas de la temporada actual
//	 * @param temporadaId
//	 * @param dataInici
//	 * @param dataFin
//	 * @return
//	 * @throws InfrastructureException
//	 */
//	public Double getTotalOlivaMolturada(Date dataInici, Boolean ambDo, Long establimentId) throws InfrastructureException {
//		return getTotalOlivaMolturada(dataInici, null, ambDo, establimentId);
//	}
	
	/**
	 * Devuelve la cantidad total de kilos de todas las descomposiciones de partida de una temporada o entre fechas de la temporada actual
	 * @param temporadaId
	 * @param dataInici
	 * @param dataFin
	 * @return
	 * @throws InfrastructureException
	 */
	public Double getTotalOlivaMolturada(Date dataInici, Date dataFi, Boolean ambDo, Long establimentId) throws InfrastructureException {
		List col;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Double numero = 0.0;
		try {
			logger.debug("getTotalOlivaMolturada ini");
			String inici = df.format(dataInici);
			
			String q = 	"select sum(dpo.kilos) " +
						"  from DescomposicioPartidaOliva dpo " +
						" where dpo.kilos is not null " +
						" and dpo.kilos > 0 " +
						" and dpo.descomposicioPlantacio.varietatOliva.autoritzada = "+ambDo.toString()+
						" and dpo.partidaOliva.data >= '"+inici+"' " +
						" and dpo.partidaOliva.elaboracio is not null" +
						" and dpo.partidaOliva.elaboracio.valid = true";
			if (dataFi != null) {
				String fi = df.format(dataFi);
				q += " and dpo.partidaOliva.data <= '"+fi+"' ";
			}
			if (establimentId!=null) q += " and dpo.partidaOliva.zona.establiment.id="+establimentId;

			col = getHibernateTemplate().find(q);
			if (col != null && col.size() > 0 && col.get(0) != null) numero = (Double)col.get(0);
		} catch (HibernateException ex) {
			logger.error("getTotalOlivaMolturada failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getTotalOlivaMolturada fin");
		return numero;
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
