package es.caib.gestoli.logic.dao;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.exception.InfrastructureException;
import es.caib.gestoli.logic.model.CategoriaOli;
import es.caib.gestoli.logic.model.Diposit;
import es.caib.gestoli.logic.model.EntradaDiposit;
import es.caib.gestoli.logic.model.SortidaDiposit;
import es.caib.gestoli.logic.util.Constants;


/**
 * Home object for domain model class EntradaDiposit
 * @see es.caib.gestoli.logic.model.EntradaDiposit
 * @author Juan Carlos GarcÃ­a
 */
public class EntradaDipositDao {
	private static Logger logger = Logger.getLogger(EntradaDipositDao.class);
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
			String q = "from EntradaDiposit as edi where 1 = 1 ";
			if (valid != null && valid.booleanValue()) q += " and edi.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and edi.valid = false ";
			col = getHibernateTemplate().find(q);
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
	public EntradaDiposit getById(Long id) throws InfrastructureException {
		EntradaDiposit entrada;
		try {
			logger.debug("getById ini");
			entrada = (EntradaDiposit)getHibernateTemplate().load(EntradaDiposit.class, id);	
		} catch (HibernateException ex) {
			logger.error("getById failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getById fin");
		return entrada;
	}


	/**
	 * Devuelve EntradaDiposit  por establecimiento
	 * @param 
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection findByEstablecimiento(Long estId, Boolean valid) throws InfrastructureException {
		Collection col;
		try {
			logger.debug("findByEstablecimiento ini");
			String q = "from EntradaDiposit edi where " +
			"edi.establiment.id = "+ estId;
			q += " and edi.diposit.establiment.id ="+ estId;
			if (valid != null && valid.booleanValue()) q += " and edi.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and edi.valid = false ";
			q += " order by edi.id";
			col = getHibernateTemplate().find(q);
			
		} catch (HibernateException ex) {
			logger.error("findByEstablecimiento failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findByEstablecimiento fin");
		return col;
	}
	/**
	 * Recupera el EntradaDiposit por la traza 
	 * @return
	 * @throws InfrastructureException
	 */
	public EntradaDiposit findByTraza(Long idTraza, Boolean valid) throws InfrastructureException {
		List col;
		EntradaDiposit entradaDiposit = null;
		try {
			logger.debug("findByTraza ini");
			String q = "from EntradaDiposit as edi where edi.traza.id="+idTraza;
			if (valid != null && valid.booleanValue()) q += " and edi.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and edi.valid = false ";
			q += " order by edi.traza.id desc ";
			col = getHibernateTemplate().find(q);
			if(col!= null && col.size()>0){
				entradaDiposit = (EntradaDiposit)col.get(0);
			}
		} catch (HibernateException ex) {
			logger.error("findByTraza failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findByTraza fin");
		return entradaDiposit;
	}
	
	/**
	 * Recupera las entradas de deposito segun el tipo y establecimiento
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection findEntradesDipositByTipusIEstabliment(Long idEstabliment, Integer tipus, Boolean valid) throws InfrastructureException {
		List col;
		try {
			logger.debug("findProcessosByTipusIEstabliment ini");
			String q = "from EntradaDiposit as edi where edi.traza.tipus="+tipus;
			q += " and edi.establiment.id="+idEstabliment;
			if (valid != null && valid.booleanValue()) q += " and edi.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and edi.valid = false ";
			col = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("findProcessosByTipusIEstabliment failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findProcessosByTipusIEstabliment fin");
		return col;
	}

	
	/**
	 * Devuelve la ultima entrada a un deposito
	 * @param 
	 * @return
	 * @throws InfrastructureException
	 */
	public EntradaDiposit findUltimaByDeposito(Long dipId, Boolean valid) throws InfrastructureException {
		List entradas;
		try {
			logger.debug("findUltimaByDeposito ini");
			String q = "from EntradaDiposit edi where " +
			"edi.diposit.id = "+ dipId +" " +
			"and edi.id = (select max(ent.id) from EntradaDiposit ent where ent.diposit.id = edi.diposit.id and ent.valid = edi.valid) ";
			if (valid != null && valid.booleanValue()) q += " and edi.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and edi.valid = false ";
			entradas = getHibernateTemplate().find(q);
			if (entradas.size() > 0) {
				logger.debug("findUltimaByDeposito fin");
				return (EntradaDiposit) entradas.get(0);
			}
		} catch (HibernateException ex) {
			logger.error("findUltimaByDeposito failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findUltimaByDeposito fin");
		return null;
	}

	/**
	 * Devuelve la següent SortidaDiposit de un deposito
	 * @param 
	 * @return
	 * @throws InfrastructureException
	 */
	public EntradaDiposit findSeguentByTrazaDeposito(Long dipId, Long trazaId) throws InfrastructureException {
		List entradas;
		try {
			logger.debug("findSeguentByTrazaDeposito ini");
			String q = "from EntradaDiposit edi where " +
			" edi.diposit.id = " + dipId +
			" and edi.valid = true" +
			" and edi.id = (select min(id) from EntradaDiposit where traza.id > " + trazaId + " and diposit.id = " + dipId + ")" +
			" order by edi.data desc, edi.id desc";
			entradas = getHibernateTemplate().find(q);
			if (entradas.size() > 0) {
				logger.debug("findSeguentByTrazaDeposito fin");
				return (EntradaDiposit) entradas.get(0);
			}
		} catch (HibernateException ex) {
			logger.error("findSeguentByTrazaDeposito failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findSeguentByTrazaDeposito fin");
		return null;
	}
	
	/**
	 * Devuelve la anterior EntradaDiposit de un deposito
	 * @param 
	 * @return
	 * @throws InfrastructureException
	 */
	public EntradaDiposit findAnteriorByTrazaDeposito(Long dipId, Long trazaId) throws InfrastructureException {
		List entradas;
		try {
			logger.debug("findAnteriorByTrazaDeposito ini");
			String q = "from EntradaDiposit edi where " +
			" edi.diposit.id = " + dipId +
			" and edi.valid = true" +
			" and edi.id = (select max(id) from EntradaDiposit where traza.id < " + trazaId + " and diposit.id = " + dipId + ")" +
			" order by edi.data asc, edi.id asc";
			entradas = getHibernateTemplate().find(q);
			if (entradas.size() > 0) {
				logger.debug("findAnteriorByTrazaDeposito fin");
				return (EntradaDiposit) entradas.get(0);
			}
		} catch (HibernateException ex) {
			logger.error("findAnteriorByTrazaDeposito failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findAnteriorByTrazaDeposito fin");
		return null;
	}
	
	/**
	 * Devuelve la anterior EntradaDiposit de un deposito
	 * @param 
	 * @return
	 * @throws InfrastructureException
	 */
	public EntradaDiposit findAnteriorByData(Long idDiposit, Date data) {
		List entradas;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		try {
			logger.debug("findAnteriorByData ini");
			String fi = df.format(data); 
			String q = 	"from EntradaDiposit edi where " +
						" edi.diposit.id = " + idDiposit +
						" and edi.valid = true" +
						" and edi.id = (select max(id) from EntradaDiposit where data < '" + fi + "' and diposit.id = " + idDiposit + ")" +
						" order by edi.data asc, edi.id asc";
			entradas = getHibernateTemplate().find(q);
			if (entradas.size() > 0) {
				logger.debug("findAnteriorByTrazaDeposito fin");
				return (EntradaDiposit) entradas.get(0);
			}
		} catch (HibernateException ex) {
			logger.error("findAnteriorByData failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findAnteriorByData fin");
		return null;
	}
	/**
	 * Devuelve entradas aceite que en la elaboracion se ha quedado el olivicultor por establecimiento
	 * @param 
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection findEntradaDiposiOliRetiratPropietarioByEstablecimiento(Long estId, Boolean valid) throws InfrastructureException {
		Collection col;
		try {
			logger.debug("findEntradaDiposiOliRetiratPropietarioByEstablecimiento fin");
			String q = "from EntradaDiposit edi where " +
			"edi.establiment.id = "+ estId +" " +
			"and edi.diposit.fictici = true "+ 
			"and edi.diposit.id in (select sdi.dipositBySdiCoddor.id from SortidaDiposit sdi where sdi.dipositBySdiCoddde is null)";
			if (valid != null && valid.booleanValue()) q += " and edi.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and edi.valid = false ";
			col = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("findEntradaDiposiOliRetiratPropietarioByEstablecimiento failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findEntradaDiposiOliRetiratPropietarioByEstablecimiento fin");
		return col;
	}

	/**
	 * Devuelve entradas aceite que en la elaboracion se ha quedado el olivicultor por establecimiento y elaboración
	 * @param 
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection findEntradaDipositOliRetiratPropietarioByEstablecimientoAndElaboracio(Long estId, Long elaId, Boolean valid) throws InfrastructureException {
		Collection col;
		try {
			logger.debug("findEntradaDipositOliRetiratPropietarioByEstablecimientoAndElaboracio fin");
			String q = "from EntradaDiposit edi where " +
			"edi.establiment.id = "+ estId + " " +
			"and edi.elaboracio.id = " + elaId + " " +
			"and edi.diposit.fictici = true "+ 
			"and edi.diposit.id in (select sdi.dipositBySdiCoddor.id from SortidaDiposit sdi where sdi.dipositBySdiCoddde is null)";
			if (valid != null && valid.booleanValue()) q += " and edi.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and edi.valid = false ";
			col = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("findEntradaDipositOliRetiratPropietarioByEstablecimientoAndElaboracio failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findEntradaDipositOliRetiratPropietarioByEstablecimientoAndElaboracio fin");
		return col;
	}
	
	/**
	 * Devuelve entradas aceite que en la elaboracion se ha quedado el olivicultor por establecimiento
	 * @param 
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection findOliRetiratPropietarioByElaboracio(Long elabId, Boolean valid) throws InfrastructureException {
		List entradas;
		try {
			logger.debug("findOliRetiratPropietarioByElaboracio ini");
			String q = "from EntradaDiposit edi where " +
			"edi.elaboracio.id = "+ elabId +" " +
			"and edi.diposit.fictici = true "+ 
			"and edi.diposit.id in (select sdi.dipositBySdiCoddor.id from SortidaDiposit sdi where sdi.dipositBySdiCoddde is null)";
			if (valid != null && valid.booleanValue()) q += " and edi.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and edi.valid = false ";
			entradas = getHibernateTemplate().find(q);
			
			logger.debug("findOliRetiratPropietarioByElaboracio fin");
			return entradas;
		} catch (HibernateException ex) {
			logger.error("findOliRetiratPropietarioByElaboracio failed", ex);
			throw new InfrastructureException(ex);
		}
	}


	/**
	 * Devuelve entradas aceite que en la elaboracion que no se ha quedado el olivicultor por establecimiento
	 * @param 
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection findEntradaDiposiOliByElaboracio(Long elaId, Boolean valid) throws InfrastructureException {
		Collection col;
		try {
			logger.debug("findEntradaDiposiOliByElaboracio ini");
			String q = "from EntradaDiposit edi where " +
			"edi.elaboracio.id = "+ elaId + " and edi.diposit.fictici = false";
			if (valid != null && valid.booleanValue()) q += " and edi.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and edi.valid = false ";
			col = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("findEntradaDiposiOliByElaboracio failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findEntradaDiposiOliByElaboracio fin");
		return col;
	}
	
	
	/**
	 * Devuelve entradas aceite para un deposito
	 * @param 
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection findEntradaDiposiOliByDiposit(Long dipositId, Boolean valid) throws InfrastructureException {
		Collection col;
		try {
			logger.debug("findEntradaDiposiOliByDiposit ini");
			String q = "from EntradaDiposit edi where " +
			"edi.diposit.id = "+ dipositId + " ";
			if (valid != null && valid.booleanValue()) q += " and edi.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and edi.valid = false ";
			q += "order by edi.data asc";
			col = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("findEntradaDiposiOliByDiposit failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findEntradaDiposiOliByDiposit fin");
		return col;
	}
	
	/**
	 * Devuelve entradas aceite para un deposito i elaboració
	 * @param 
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection findEntradaDiposiOliByDipositAndElaboracio(Long dipositId, Long idElaboracio, Boolean valid) throws InfrastructureException {
		Collection col;
		try {
			logger.debug("findEntradaDiposiOliByDipositAndElaboracio ini");
			String q = "from EntradaDiposit edi where " +
			"edi.diposit.id = "+ dipositId + " " +
			"and edi.elaboracio.id = "+ idElaboracio + " and edi.diposit.fictici = false";
			if (valid != null && valid.booleanValue()) q += " and edi.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and edi.valid = false ";
			q += "order by edi.data asc";
			col = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("findEntradaDiposiOliByDipositAndElaboracio failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findEntradaDiposiOliByDipositAndElaboracio fin");
		return col;
	}
	
	/**
	 * Retorna true si existen entradas de depositos asociadas a depositos
	 * 
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public boolean existenEntradasDepositoAsociadasDeposito(Long idDiposit, Boolean valid) throws InfrastructureException {
		List entrada;
		try {
			logger.debug("existenEntradasDepositoAsociadasDeposito ini");
			String q = "select count(ed.id) from EntradaDiposit ed " +
			"where ed.diposit.id = " + idDiposit + " ";
			if (valid != null && valid.booleanValue()) q += " and ed.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and ed.valid = false ";
			entrada = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("existenEntradasDepositoAsociadasDeposito failed", ex);
			throw new InfrastructureException(ex);
		}
		if (entrada != null && entrada.size()> 0 && ((Long)entrada.get(0)).intValue() > 0) {
			logger.debug("existenEntradasDepositoAsociadasDeposito fin");
			return true;
		}
		logger.debug("existenEntradasDepositoAsociadasDeposito fin");
		return false;
	}
	
	/**
	 * Actualiza un registro
	 * @param entrada
	 * @throws InfrastructureException
	 */
	public void makePersistent(EntradaDiposit entrada) throws InfrastructureException {
		try {
			logger.debug("makePersistent ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().saveOrUpdate(entrada);
			getHibernateTemplate().flush();
			logger.debug("makePersistent fin");
		} catch (HibernateException ex) {
			logger.error("makePersistent failed", ex);
			throw new InfrastructureException(ex);
		}
	}

	
	/**
	 * Actualiza un registro
	 * @param entrada
	 * @param session
	 * @throws InfrastructureException
	 */
	public void makePersistent(EntradaDiposit entrada, Session session) throws InfrastructureException {
		try {
			logger.debug("makePersistent ini");
			session.setFlushMode(FlushMode.ALWAYS);
			session.saveOrUpdate(entrada);
			session.flush();
			logger.debug("makePersistent fin");
		} catch (HibernateException ex) {
			logger.error("makePersistent failed", ex);
			throw new InfrastructureException(ex);
		}
	}


	/**
	 * Borra un registro
	 * @param entrada
	 * @throws InfrastructureException
	 */
	public void makeTransient(EntradaDiposit entrada)
	throws InfrastructureException {
		try {
			logger.debug("makeTransient ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().delete(entrada);
			getHibernateTemplate().flush();
			logger.debug("makeTransient fin");
		} catch (HibernateException ex) {
			logger.error("makeTransient failed", ex);
			throw new InfrastructureException(ex);
		}
	}


	/**
	 * Devuelve la cantidad total de litros de aceite elaborado de una temporada o entre fechas de la temporada actual de unas determinadas categorias
	 * @param temporadaId
	 * @param dataInici
	 * @param dataFin
	 * @return
	 * @throws InfrastructureException
	 */
	public Double getTotalOliByCategorias(Long temporadaId,Date dataInici,Date dataFin,Object[] categorias, Boolean valid) throws InfrastructureException {
		Collection col;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Double numero = null;

		try {
			logger.debug("getTotalOliByCategorias ini");

			String q = "select sum(edi.litres) from EntradaDiposit edi where edi.litres > 0"; 
			if(dataInici!= null || dataFin != null){
				if(dataInici != null){
					String fi = df.format(dataInici);
					q = q+ " and  edi.data >= '"+fi+"' ";
				}
				if(dataFin != null){
					String ff = df.format(dataFin);
					q = q+ " and  edi.data <= '"+ff+"' ";
				}
				q = q+" and edi.establiment.campanya.id= (select max(cam.id) from Campanya cam)";
			} else {
				q = q+ " and edi.establiment.campanya.id="+temporadaId;
			}

			if(categorias!= null && categorias.length >0){
				q = q+" and edi.diposit.partidaOli.categoriaOli.id in(:categorias) ";
			}
			if (valid != null && valid.booleanValue()) q += " and edi.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and edi.valid = false ";

//			Query query = getHibernateTemplate().createQuery(q);
			if(categorias!= null && categorias.length >0){
//				query.setParameterList("categorias", categorias);
				col = getHibernateTemplate().findByNamedParam(q, "categorias", categorias);
			} else {
				col = getHibernateTemplate().find(q);
			}
//			col = query.list();
			while (col.iterator().hasNext()){
				numero = (Double)col.iterator().next();
				break;
			}
		} catch (HibernateException ex) {
			logger.error("getTotalOliByCategorias failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getTotalOliByCategorias fin");
		return numero;
	}

	/**
	 * Devuelve la cantidad total de litros de aceite elaborado de una temporada o entre fechas de la temporada actual de unas determinadas categorias
	 * @param temporadaId
	 * @param dataInici
	 * @param dataFin
	 * @return
	 * @throws InfrastructureException
	 */
	public Double getTotalOliByCategoriasEntreDates(Date dataInici,Date dataFin,Object[] categorias) throws InfrastructureException {
		Collection col;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Double numero = null;

		try {
			logger.debug("getTotalOliByCategoriasEntreDates ini");

			String q = "select sum(edi.litres) from EntradaDiposit edi where edi.litres > 0"; 
			if(dataInici != null){
				String fi = df.format(dataInici);
				q = q+ " and  edi.data >= '"+fi+"' ";
			}
			if(dataFin != null){
				String ff = df.format(dataFin);
				q = q+ " and  edi.data <= '"+ff+"' ";
			}
			q += " and edi.valid = true ";
			
			if(categorias!= null && categorias.length >0){
				q = q+" and edi.categoriaOli.id in(:categorias) ";
				col = getHibernateTemplate().findByNamedParam(q, "categorias", categorias);
			} else {
				col = getHibernateTemplate().find(q);
			}
			while (col.iterator().hasNext()){
				numero = (Double)col.iterator().next();
				break;
			}
		} catch (HibernateException ex) {
			logger.error("getTotalOliByCategoriasEntreDates failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getTotalOliByCategoriasEntreDates fin");
		return numero;
	}
	
	/**
	 * Devuelve los depositos que han entrado en un establecimiento entre 2 fechas dadas
	 * @param fi
	 * @param ff
	 * @param idEst
	 * @return
	 */
	public Collection getEntradasDipositEntreFechasAndEstablecimiento(Date finicio, Date ffin, Long idEst, Boolean valid){
		
		Collection col;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String fi = df.format(finicio);
		String ff = df.format(ffin);

		try {
			logger.debug("getEntradasDipositEntreFechasAndEstablecimiento inicio");
			String q = "from EntradaDiposit entdip where " +
			"entdip.data >= '"+fi+"' and entdip.data <= '"+ff+"' and entdip.establiment.id = "+ idEst + " " +
			"and entdip.litres > 0";
			if (valid != null && valid.booleanValue()) q += " and entdip.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and entdip.valid = false ";
			q += " order by entdip.data, entdip.id";
			col = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("getEntradasDipositEntreFechasAndEstablecimiento failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getEntradasDipositEntreFechasAndEstablecimiento fin");
		return col;
		
	}
	
	public Collection getEntradasDipositEntreFechasPartidaAndEstablecimiento(Date data, Long idPartida, Long idEst, Boolean valid) {
		Collection col;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String di = df.format(data);

		try {
			logger.debug("getEntradasDipositEntreFechasPartidaAndEstablecimiento inicio");
			String q = "from EntradaDiposit entdip where " +
			"entdip.data >= '"+di+"' and entdip.partidaOli.id = " + idPartida + " and entdip.establiment.id = "+ idEst + " " +
			"and entdip.litres > 0";
			if (valid != null && valid.booleanValue()) q += " and entdip.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and entdip.valid = false ";
			q += " order by entdip.data, entdip.id";
			col = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("getEntradasDipositEntreFechasPartidaAndEstablecimiento failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getEntradasDipositEntreFechasPartidaAndEstablecimiento fin");
		return col;
	}
	
	public Collection getEntradasDipositEntreFechasDipositAndEstablecimiento(Date finicio, Date ffin, Long idEst, Long idDip, Boolean valid){
		
		Collection col;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String fi = df.format(finicio);
		String ff = df.format(ffin);

		try {
			logger.debug("getEntradasDipositEntreFechasAndEstablecimiento inicio");
			String q = "from EntradaDiposit entdip where " +
			"entdip.data >= '"+fi+"' and entdip.data <= '"+ff+"' and entdip.diposit.id = "+idDip+" and entdip.establiment.id = "+ idEst + " " +
			"and entdip.litres > 0";
			if (valid != null && valid.booleanValue()) q += " and entdip.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and entdip.valid = false ";
			q += " order by entdip.data, entdip.id";
			col = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("getEntradasDipositEntreFechasAndEstablecimiento failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getEntradasDipositEntreFechasAndEstablecimiento fin");
		return col;
		
	}
	
	public Collection getEntradasDipositEntreFechasAndDiposit(Date finicio, Date ffin, Long idDip, Boolean valid) {
		Collection col;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String fi = df.format(finicio);
		String ff = df.format(ffin);

		try {
			logger.debug("getEntradasDipositEntreFechasAndDiposit inicio");
			String q = "from EntradaDiposit entdip where " +
			"entdip.data >= '"+fi+"' and entdip.data <= '"+ff+"' and entdip.diposit.id = "+idDip+" " +
			"and entdip.litres > 0";
			if (valid != null && valid.booleanValue()) q += " and entdip.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and entdip.valid = false ";
			q += " order by entdip.data, entdip.id";
			col = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("getEntradasDipositEntreFechasAndDiposit failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getEntradasDipositEntreFechasAndDiposit fin");
		return col;
	}
	
	/**
	 * Devuelve la suma de los cambios de calificacion de No DO a DO
	 * @param 
	 * @return
	 * @throws InfrastructureException
	 */
	public Double getSumaNoDoToDoByCampanyaEstabliment (Long temporadaId, Object[] trazasPadre, Boolean valid) throws InfrastructureException {
		
		Double suma=null;
		Collection col=null;
		try{
			logger.debug("getSumaNoDoToDoByCampanyaEstabliment ini");
	
			String q = "select sum(ana.litresAnalitzats) from EntradaDiposit edi, Analitica ana " +
					"where edi.establiment.campanya.id="+temporadaId+" "+  
					"and edi.traza.id in (:trazasPadre) " +
					"and edi.diposit.id = ana.diposit.id";
			if (valid != null && valid.booleanValue()) q += " and edi.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and edi.valid = false ";
			
			if (trazasPadre.length > 0) {
				col = getHibernateTemplate().findByNamedParam(q, "trazasPadre", trazasPadre);
			} else {
				suma = new Double(0);
			}
						
			if (col != null){
				Double d = (Double)col.iterator().next();
				if (d != null) {
					suma = d;
				} else {
					suma = new Double(0);
				}
			}
			
			
			logger.debug("getSumaNoDoToDoByCampanyaEstabliment fin");
			
		}catch (HibernateException ex) {
			logger.error("getSumaNoDoToDoByCampanyaEstabliment failed", ex);
			throw new InfrastructureException(ex);
		}
		return suma;
		
	}
	
	/**
	 * Devuelve la suma de los cambios de calificacion de DO a no DO
	 * @param 
	 * @return
	 * @throws InfrastructureException
	 */
	public Double getSumaDoToNoDoByCampanyaEstabliment (Long temporadaId, Object[] trazasPadre, Boolean valid) throws InfrastructureException {
		
		Double suma=null;
		Collection col=null;
		try{
			logger.debug("getSumaDoToNoDoByCampanyaEstabliment ini");
	
//			String q = "select sum(ana.litresDesqualificats) from EntradaDiposit edi, Analitica ana " +
			String q = "select sum(ana.litresAnalitzats) from EntradaDiposit edi, Analitica ana " +
					"where edi.establiment.campanya.id="+temporadaId+" "+  
					"and edi.traza.id in (:trazasPadre) " +
					"and edi.diposit.id = ana.diposit.id";
			if (valid != null && valid.booleanValue()) q += " and edi.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and edi.valid = false ";
			
			if (trazasPadre.length > 0) {
				col = getHibernateTemplate().findByNamedParam(q, "trazasPadre", trazasPadre);
			} else {
				suma = new Double(0);
			}
						
			if (col != null) {
				Double d = (Double)col.iterator().next();
				if (d != null) {
					suma = d;
				} else {
					suma = new Double(0);
				}
			}
			
			
			logger.debug("getSumaDoToNoDoByCampanyaEstabliment fin");
			
		}catch (HibernateException ex) {
			logger.error("getSumaDoToNoDoByCampanyaEstabliment failed", ex);
			throw new InfrastructureException(ex);
		}
		return suma;
		
	}
	
	/**
	 * Devuelve la suma de los cambios de calificacion de DO a no DO en salidas
	 * @param 
	 * @return
	 * @throws InfrastructureException
	 */
	public Double getSumaDoToNoDoByEstabliment(Date dataInici, Date dataFi, Long establimentId) throws InfrastructureException {
		
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String inici = df.format(dataInici);
		
		Double suma=null;
		Collection col=null;
		try{
			logger.debug("getSumaDoToNoDoByEstabliment ini");
	
			String q = 	"select sum(edi.litres) " +
						"  from EntradaDiposit edi " +
						" 		left outer join edi.traza.trazasForTtrCodtrapare t " +
						" where edi.diposit.fictici = true " +
						"	and edi.categoriaOli.id = 1 " +
						"   and edi.valid = true " +
						"   and edi.data >= '" + inici + "' " +  
						"	and t.id in (select sdi.traza.id " +
						"				  from SortidaDiposit sdi " +
						"				 where sdi.categoriaOli.id = 3";
			if (establimentId != null) {
				q += " and sdi.establiment.id = " + establimentId + ") ";
			} else {
				q += ")";
			}
			if (dataFi != null) {
				String fi = df.format(dataFi);
				q += " and edi.data <= '" + fi + "' ";
			}
			if (establimentId != null) {
				q += " and edi.establiment.id = " + establimentId + " ";
			}
			col = getHibernateTemplate().find(q);
						
			if (col != null) {
				Double d = (Double)col.iterator().next();
				if (d != null) {
					suma = d;
				} else {
					suma = new Double(0);
				}
			}
			
			logger.debug("getSumaDoToNoDoByEstabliment fin");
			
		}catch (HibernateException ex) {
			logger.error("getSumaDoToNoDoByEstabliment failed", ex);
			throw new InfrastructureException(ex);
		}
		return suma;
		
	}
		
	/**
	 * Comprova si la partida d'oli tÃ© entrades de dipÃ²sit associats
	 * @param lid
	 * @return
	 */
	public Boolean existeixenEntradesDipositsAssociatsPartidesOli(Long idPartidaOli, Boolean valid) {
		List entrades;
		try {
			logger.debug("existeixenEntradesDipositsAssociatsPartidesOli ini");
			String q = "select count(edi.id) from EntradaDiposit edi " +
			"where edi.partidaOli.id = " + idPartidaOli + " ";
			if (valid != null && valid.booleanValue()) q += "and edi.valid = true ";
			if (valid != null && !valid.booleanValue()) q += "and edi.valid = false ";
			entrades = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("existeixenEntradesDipositsAssociatsPartidesOli failed", ex);
			throw new InfrastructureException(ex);
		}
		if (entrades != null && entrades.size()> 0 && ((Long)entrades.get(0)).intValue() > 0) {
			logger.debug("existeixenElaboracionsAssociadesPartidesOli fin");
			return true;
		}
		logger.debug("existeixenElaboracionsAssociadesPartidesOli fin");
		return false;
	}
	
	/**
	 * Retorna les entrades d'oli efectuades en un dipòsit dins a una data donada
	 * @param fi
	 * @param ff
	 * @param idEst
	 * @return
	 */
	public Double getSumaEntradesDipositPerDipositFinsData(Long idDiposit, Date data, Boolean valid){
		
		Collection col;
		Double suma = 0.0;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String fi = df.format(data);

		try {
			logger.debug("getSumaEntradesDipositPerDipositFinsData inicio");
			String q = "select sum(entdip.litres) from EntradaDiposit entdip where " +
			"entdip.data <= '"+fi+"' and entdip.diposit.id = "+ idDiposit;
			if (valid != null && valid.booleanValue()) q += " and entdip.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and entdip.valid = false ";
			col = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("getSumaEntradesDipositPerDipositFinsData failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getEntradesDipositPerDipositFinsData fin");
		if (col != null && col.size() > 0){
			suma = (Double)col.iterator().next();
			if (suma == null) suma = 0.0;
		}
		return suma;
		
	}
	
	/**
	 * Retorna les entrades d'oli efectuades en uns dipòsits dins a una data donada
	 * @param fi
	 * @param ff
	 * @param idEst
	 * @return
	 */
	public Double getSumaEntradesDipositsFinsData(Long[] idDiposits, Date data, Boolean valid){
		
		Collection col;
		Double suma = 0.0;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String fi = df.format(data);

		try {
			logger.debug("getSumaEntradesDipositPerDipositFinsData inicio");
			String q = "select sum(entdip.litres) from EntradaDiposit entdip where " +
			"entdip.data <= '"+fi+"' and entdip.diposit.id in (:idDiposits) ";
			if (valid != null && valid.booleanValue()) q += " and entdip.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and entdip.valid = false ";
			col = getHibernateTemplate().findByNamedParam(q, "idDiposits", idDiposits);
		} catch (HibernateException ex) {
			logger.error("getSumaEntradesDipositPerDipositFinsData failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getEntradesDipositPerDipositFinsData fin");
		if (col != null && col.size() > 0){
			suma = (Double)col.iterator().next();
			if (suma == null) suma = 0.0;
		}
		return suma;
		
	}
	
	/**
	 * Retorna les entrades d'oli efectuades en dipòsits de tafona provinents d'oli de envasadora dins a una data donada
	 * @param fi
	 * @param ff
	 * @param idEst
	 * @return
	 */
	public Collection getEntradesDipositEnvasadoraEntreFechasAndEstablecimiento(Date dataInici, Date dataFi, Long establimentId) {
		Collection col;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			logger.debug("getEntradesDipositEnvasadoraEntreFechasAndEstablecimiento inicio");
			String fi = df.format(dataInici);
			String ff = df.format(dataFi);
			String q = 	"select edi " +
						"  from EntradaDiposit edi " +
						"  left join edi.diposit d " +
						"  left join d.zonaOrigenTrasllat z " +
						" where edi.data >= '"+fi+"' " +
						"   and edi.data <= '"+ff+"' " +
						"   and edi.valid = true " +
						"   and d.deEnvasadora = true " +
						"   and (z is null" +
						"		or z.establiment.id = " + establimentId +	") " +
						"   and edi.litres > 0 " +
						"   and edi.establiment.id =" + establimentId;
			col = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("getEntradesDipositEnvasadoraEntreFechasAndEstablecimiento failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getEntradesDipositEnvasadoraEntreFechasAndEstablecimiento fin");
		return col;
	}
	
	/**
	 * Retorna la categoria d'oli de la última entrada abans d'una data donada
	 * @param fi
	 * @param ff
	 * @param idEst
	 * @return
	 */
	public CategoriaOli getCategoriaUltimaEntradesDipositPerDipositFinsData(Long idDiposit, Date data, Boolean valid){
		
		CategoriaOli cat = null;
		Collection col;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String fi = df.format(data);

		try {
			logger.debug("getCategoriaUltimaEntradesDipositPerDipositFinsData inicio");
			String q = 	"select entdip.categoriaOli " +
						"from EntradaDiposit entdip " +
						"where entdip.data <= '"+fi+"' " +
						"and entdip.diposit.id = "+ idDiposit + " " +
						"and entdip.data = (select max(edi.data) " +
						"					from EntradaDiposit edi" +
						"					where edi.data <= '"+fi+"' " +
						"					and edi.diposit.id = "+ idDiposit;
			if (valid != null && valid.booleanValue()) q += " and edi.valid = true) ";
			if (valid != null && !valid.booleanValue()) q += " and edi.valid = false) ";
			if (valid != null && valid.booleanValue()) q += " and entdip.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and entdip.valid = false ";
			q += "order by entdip.id desc";
			col = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("getSumaEntradesDipositPerDipositFinsData failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getEntradesDipositPerDipositFinsData fin");
		if (col != null && col.size() > 0 ){
			cat = (CategoriaOli)col.iterator().next();
		}
		return cat;
		
	}
	
	/**
	 * Retorna la quantitat d'oli que s'ha analitzat com a DO
	 * @param fi
	 * @param ff
	 * @param idEst
	 * @return
	 */
	public Double getQuantitatOliAnalisiDOEntreDates(Date dataInici, Date dataFi) {
		Collection col;
		Double suma = 0.0;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			logger.debug("getQuantitatOliAnalisiDOEntreDates inicio");
			String fi = df.format(dataInici);
			String ff = df.format(dataFi);
			String q = 	"select sum(edi.litres) " +
						"  from EntradaDiposit edi " +						
						" where edi.data >= '"+fi+"' " +
						"   and edi.data <= '"+ff+"' " +
						"   and edi.valid = true " +
						"   and edi.observacions = 'ANALITICA. DO'";
			col = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("getQuantitatOliAnalisiDOEntreDates failed", ex);
			throw new InfrastructureException(ex);
		}
		if (col != null && col.size() > 0){
			suma = (Double)col.iterator().next();
			if (suma == null) suma = 0.0;
		}
		logger.debug("getQuantitatOliAnalisiDOEntreDates fin");
		return suma;
		
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


	public EntradaDiposit findSeguentByTrazaDeposito(
			Diposit dipositBySdiCoddor, Long trazaId) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
