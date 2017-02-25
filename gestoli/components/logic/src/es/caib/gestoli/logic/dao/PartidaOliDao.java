package es.caib.gestoli.logic.dao;

import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;


import es.caib.gestoli.logic.exception.InfrastructureException;
import es.caib.gestoli.logic.model.PartidaOli;
import es.caib.gestoli.logic.util.Constants;


/**
 * Home object for domain model class DescomposicioPartidaOliva
 * @see es.caib.gestoli.logic.model.DescomposicioPartidaOlivaDao
 * 
 */
public class PartidaOliDao {
	private static Logger logger = Logger.getLogger(PartidaOliDao.class);
	private HibernateTemplate hibernateTemplate;


	/**
	 * Recupera todos los registros
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection findAll(Boolean valid) throws InfrastructureException {
		Collection col;
		try {
			logger.debug("findAll ini");
			// Ponemos where 1 = 1 para que no nos de error cuando se añada el and si se añade.
			String q = "from PartidaOli as par where 1 = 1 ";
			if (valid != null && valid.booleanValue()) q += "and par.esActiu = true ";
			if (valid != null && !valid.booleanValue()) q += "and par.esActiu = false ";
			q += "order by par.nom";
			col = getHibernateTemplate().find(q);
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
	public Collection findAllVisibles(Boolean valid) throws InfrastructureException {
		Collection col;
		try {
			logger.debug("findAllVisibles ini");
			// Ponemos where 1 = 1 para que no nos de error cuando se añada el and si se añade.
			String q = "from PartidaOli as par where par.esVisualitza = true ";
			if (valid != null && valid.booleanValue()) q += "and par.esActiu = true ";
			if (valid != null && !valid.booleanValue()) q += "and par.esActiu = false ";
			q += "order by par.nom";
			col = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("findAllVisibles failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findAllVisibles fin");
		return col;
	}

	
	/**
	 * Recupera todos los registros de l'establiment indicat
	 * @param idEstabliment
	 * @return
	 */
	public Collection findAllByEstabliment(Long idEstabliment, Boolean valid) {
		Collection col;
		try {
			logger.debug("findAllByEstabliment ini");
			String q = "from PartidaOli as par where 1 = 1 ";
			if (valid != null && valid.booleanValue()) q += "and par.esActiu = true ";
			if (valid != null && !valid.booleanValue()) q += "and par.esActiu = false ";
			if(idEstabliment != null) q += "and par.establiment.id = " + idEstabliment + " ";
			q += "order by par.nom";
			col = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("findAllByEstabliment failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findAllByEstabliment fin");
		return col;
	}

	/**
	 * Recupera todos los registros de l'establiment indicat
	 * @param idEstabliment
	 * @return
	 */
	public Collection findAllVisiblesByEstabliment(Long idEstabliment, Boolean valid) {
		Collection col;
		try {
			logger.debug("findAllVisiblesByEstabliment ini");
			String q = "from PartidaOli as par where par.esVisualitza = true ";
			if (valid != null && valid.booleanValue()) q += "and par.esActiu = true ";
			if (valid != null && !valid.booleanValue()) q += "and par.esActiu = false ";
			if(idEstabliment != null) q += "and par.establiment.id = " + idEstabliment + " ";
			q += "order by par.nom";
			col = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("findAllVisiblesByEstabliment failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findAllVisiblesByEstabliment fin");
		return col;
	}

	/**
	 * Recupera todos los registros excepte els de DO
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection findAllExceptDO(Boolean valid) throws InfrastructureException {
		Collection col;
		try {
			logger.debug("findAllExceptDO ini");
			String q = "from PartidaOli as par where 1 = 1 ";
			if (valid != null && valid.booleanValue()) q += "and par.esActiu = true ";
			if (valid != null && !valid.booleanValue()) q += "and par.esActiu = false ";
			q += "and par.categoriaOli.id != " + Constants.CATEGORIA_DO + " "; // Categoria de DO
			q += "order by par.nom";
			col = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("findAllexceptDO failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findAllExceptDO fin");
		return col;
	}
	
	/**
	 * Recupera todos los registros excepte els de DO
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection findAllVisiblesExceptDO(Boolean valid) throws InfrastructureException {
		Collection col;
		try {
			logger.debug("findAllVisiblesExceptDO ini");
			String q = "from PartidaOli as par where par.esVisualitza = true ";
			if (valid != null && valid.booleanValue()) q += "and par.esActiu = true ";
			if (valid != null && !valid.booleanValue()) q += "and par.esActiu = false ";
			q += "and par.categoriaOli.id != " + Constants.CATEGORIA_DO + " "; // Categoria de DO
			q += "order by par.nom";
			col = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("findAllVisiblesExceptDO failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findAllVisiblesExceptDO fin");
		return col;
	}
	
	
//	/**
//	 * Retorna un llista de tots els nom de partida associats amb
//	 * un establiment determinat.
//	 * @return
//	 * @throws InfrastructureException
//	 */
//	public List findByEstabliment(Long establimentId, Boolean valid) throws InfrastructureException {
//		List partides;
//		try {
//			logger.debug("findByEstabliment ini");
//			String q = "from PartidaOli po " +
//			"where po.establiment.id = " + establimentId + " ";
//			if (valid != null && valid.booleanValue()) q += "and po.esActiu = true ";
//			if (valid != null && !valid.booleanValue()) q += "and po.esActiu = false ";
//			q += "order by po.id";
//			partides = getHibernateTemplate().find(q);
//		} catch (HibernateException ex) {
//			logger.error("findByEstabliment failed", ex);
//			throw new InfrastructureException(ex);
//		}
//		logger.debug("findByEstabliment fin");
//		return partides;
//	}

	/**
	 * Recupera todos los registros excepte els de DO
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection findByEstablimentExceptDO(Long establimentId, Boolean valid) throws InfrastructureException {
		Collection col;
		try {
			logger.debug("findAllExceptDO ini");
			String q = "from PartidaOli as par " +
			"where par.establiment.id = " + establimentId  + " ";
			if (valid != null && valid.booleanValue()) q += "and par.esActiu = true ";
			if (valid != null && !valid.booleanValue()) q += "and par.esActiu = false ";
			q += "and par.categoriaOli.id != " + Constants.CATEGORIA_DO + " "; // Categoria de DO
			q += "order by par.nom";
			col = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("findAllexceptDO failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findAllExceptDO fin");
		return col;
	}
	
	/**
	 * Recupera todos los registros excepte els de DO
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection findVisiblesByEstablimentExceptDO(Long establimentId, Boolean valid) throws InfrastructureException {
		Collection col;
		try {
			logger.debug("findVisiblesByEstablimentExceptDO ini");
			String q = "from PartidaOli as par " +
			"where par.establiment.id = " + establimentId  + " and par.esVisualitza = true ";
			if (valid != null && valid.booleanValue()) q += "and par.esActiu = true ";
			if (valid != null && !valid.booleanValue()) q += "and par.esActiu = false ";
			q += "and par.categoriaOli.id != " + Constants.CATEGORIA_DO + " "; // Categoria de DO
			q += "order by par.nom";
			col = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("findVisiblesByEstablimentExceptDO failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findVisiblesByEstablimentExceptDO fin");
		return col;
	}
	
	/**
	 * Retorna true si existeix el nom de partida en l'establiment
	 * 
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public boolean existeixPartidaOliEnEstabliment(Long idEstabliment, String partidaOli, Boolean valid) throws InfrastructureException {
		List partidas;
		try {
			logger.debug("existenPartidasAsociadasZonas ini");
			String q = "select count(po.id) from PartidaOli po " +
			"where po.establiment.id = " + idEstabliment + " " +
			"and po.nom = " + partidaOli + " ";
			if (valid != null && valid.booleanValue()) q += "and po.esActiu = true ";
			if (valid != null && !valid.booleanValue()) q += "and po.esActiu = false ";
			partidas = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("existenPartidasAsociadasZonas failed", ex);
			throw new InfrastructureException(ex);
		}
		if (partidas != null && partidas.size()> 0 && ((Long)partidas.get(0)).intValue() > 0) {
			logger.debug("existenPartidasAsociadasZonas fin");
			return true;
		}
		logger.debug("existenPartidasAsociadasZonas fin");
		return false;
	}
	
	/**
	 * Comprova si la partida d'oli ja existeix
	 * @param lid
	 * @return
	 */
	public Boolean existeixPartidaEstabliment(
			Long partidaOliId,
			String partidaOliNom, 
			Long establimentId) {
		List partides;
		try {
			logger.debug("existeixPartidaEstabliment ini");
			String q = 	"select count(par.id) from PartidaOli par " +
						"where par.nom = '" + partidaOliNom.replace("'", "''") + "' " +
						"and par.establiment.id = " + establimentId + " ";
			if (partidaOliId != null) q += "and par.id != " + partidaOliId + " ";
			partides = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("existeixPartidaEstabliment failed", ex);
			throw new InfrastructureException(ex);
		}
		if (partides != null && partides.size()> 0 && ((Long)partides.get(0)).intValue() > 0) {
			logger.debug("existeixPartidaEstabliment fin");
			return true;
		}
		logger.debug("existeixPartidaEstabliment fin");
		return false;
	}
	
	/**
	 * Comprova si la partida d'oli ja existeix
	 * @param lid
	 * @return
	 */
	public PartidaOli getByNomAndEstabliment(
			String partidaOliNom, 
			Long establimentId) {
		List partides;
		try {
			logger.debug("getByNomAndEstabliment ini");
			String q = 	"from PartidaOli par " +
						"where par.nom = '" + partidaOliNom.replace("'", "''") + "' " +
						"and par.establiment.id = " + establimentId + " ";
			partides = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("existeixPartidaEstabliment failed", ex);
			throw new InfrastructureException(ex);
		}
		if (partides != null && partides.size()> 0) {
			logger.debug("getByNomAndEstabliment fin");
			return (PartidaOli)partides.get(0);
		}
		logger.debug("getByNomAndEstabliment fin");
		return null;
	}

	/**
	 * Retorna un llista de totes les partides associades amb un etiquetatge
	 * @return
	 * @throws InfrastructureException
	 */
	public List findByEtiquetage(Long etiId, Boolean valid) throws InfrastructureException {
		List etiquetatges;
		try {
			logger.debug("findByEtiquetaje ini");
			String q = "select distinct lot.partidaOli from Lot lot " +
			"where lot.etiquetatge.id = " +etiId;
			if (valid != null && valid.booleanValue()) q += " and lot.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and lot.valid = false ";
			q += "order by lot.partidaOli.nom";
			etiquetatges = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("findByEtiquetaje failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findByEtiquetaje fin");
		return etiquetatges;
	}
	
	
	/**
	 * Actualiza un registro
	 * @param partida
	 * @throws InfrastructureException
	 */
	public void makePersistent(PartidaOli partida) throws InfrastructureException {
		try {
			logger.debug("makePersistent ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			if(partida.getId()!= null){
				getHibernateTemplate().saveOrUpdate(partida);
			}else{
				getHibernateTemplate().save(partida);
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
	 * @param partida
	 * @throws InfrastructureException
	 */
	public void makePersistent(PartidaOli partida, Session session) throws InfrastructureException {
		try {
			logger.debug("makePersistent ini");
			session.setFlushMode(FlushMode.ALWAYS);
			if(partida.getId()!= null){
				session.saveOrUpdate(partida);
			}else{
				session.save(partida);
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
	 * @param partida
	 * @throws InfrastructureException
	 */
	public void makeTransient(PartidaOli partida) throws InfrastructureException {
		try {
			logger.debug("makeTransient ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().delete(partida);
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
	public PartidaOli getById(Long id) throws InfrastructureException {
		PartidaOli partida;
		try {
			logger.debug("getById ini");
			partida = (PartidaOli)getHibernateTemplate().load(PartidaOli.class, id);	
			logger.debug("getById fin");
		} catch (HibernateException ex) {
			logger.error("getById failed", ex);
			throw new InfrastructureException(ex);
		}
		return partida;
	}

	/**
	 * Cerca partides d'oli traslladades
	 * @param lid
	 * @return
	 */
	public PartidaOli findByIdTrasllat(Long partidaOliId) {
		List partides;
		try {
			logger.debug("findByIdTrasllat ini");
			String q = 	"from PartidaOli par " +
						"where par.idTrasllat = " + partidaOliId + " ";
			
			partides = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("findByIdTrasllat failed", ex);
			throw new InfrastructureException(ex);
		}
		if (partides != null && partides.size()> 0 ) {
			logger.debug("findByIdTrasllat fin");
			return (PartidaOli)partides.get(0);
		}
		logger.debug("findByIdTrasllat fin");
		return null;
	}
	
	public Boolean partidaCorrecteAnalitica(Long partidaOliId, Long dipositId){
		List partides;
		try {
			logger.debug("partidaCorrecteAnalitica ini");
			String q = 	"select par.id " +
						"from PartidaOli par left outer join par.diposits d " +
						"where par.id = " + partidaOliId + " " +
						"and d.id != " + dipositId + " ";
			partides = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("partidaCorrecteAnalitica failed", ex);
			throw new InfrastructureException(ex);
		}
		if (partides != null && partides.size()> 0 ) {
			logger.debug("partidaCorrecteAnalitica fin");
			return false;
		}
		logger.debug("partidaCorrecteAnalitica fin");
		return true;
	}
	
	public boolean teAnalitica(PartidaOli partida) {
		List partides;
		try {
			logger.debug("teAnalitica ini");
			String q = 	"select ana.id " +
						"from Analitica ana " +
						"where ana.partidaOli.id = " + partida.getId() + " " +
						"and ana.analisiFisicoQuimicValid = true";
			partides = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("teAnalitica failed", ex);
			throw new InfrastructureException(ex);
		}
		if (partides != null && partides.size()> 0 ) {
			logger.debug("teAnalitica fin");
			return true;
		}
		logger.debug("teAnalitica fin");
		return false;
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