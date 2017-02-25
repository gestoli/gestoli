package es.caib.gestoli.logic.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.exception.InfrastructureException;
import es.caib.gestoli.logic.model.Establiment;
import es.caib.gestoli.logic.model.Lot;
import es.caib.gestoli.logic.model.SortidaDiposit;
import es.caib.gestoli.logic.model.SortidaLot;
import es.caib.gestoli.logic.util.Constants;


/**
 * Home object for domain model class SortidaLot
 * @see es.caib.gestoli.logic.model.SortidaLot
 * @author Carlos Perez Claro
 */
public class SortidaLotDao {
	private static Logger logger = Logger.getLogger(SortidaLotDao.class);
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
			String q = "from SortidaLot as slot where 1 = 1 ";
			if (valid != null && valid.booleanValue()) q += " and slot.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and slot.valid = false ";
			col = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("findAll failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findAll fin");
		return col;
	}

	
	/**
	 * Retorna true si existen salidas de lotes asociados a zonas
	 * 
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection findByLot(Long idLot, Boolean valid) throws InfrastructureException {
		Collection col;
		try {
			logger.debug("findByLot ini");
			// Ponemos where 1 = 1 para que no nos de error cuando se añada el and si se añade.
			String q = "from SortidaLot as slot where slot.lot.id =  "+idLot;
			if (valid != null && valid.booleanValue()) q += " and slot.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and slot.valid = false ";
			q += " order by slot.id desc";
			col = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("findByLot failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findAll fin");
		return col;
	}
	
	/**
	 * Devuelve SortidaLot  por establecimiento
	 * @param 
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection findByEstablecimiento(Long estId, Boolean valid) throws InfrastructureException {
		Collection col;
		try {
			logger.debug("findByEstablecimiento ini");
			String q = "from SortidaLot as slot where " +
			"slot.zona.establiment.id = "+ estId ;
			if (valid != null && valid.booleanValue()) q += " and slot.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and slot.valid = false ";
			q += "order by slot.id";
			col = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("findByEstablecimiento failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findByEstablecimiento fin");
		return col;
	}

	/**
	 * Retorna SortidaLot  per establiment entre dates
	 * @param 
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection findByEstablecimientoEntreDates(Long estId, Boolean valid, Date dataInici, Date dataFi) {
		Collection col;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String di = sdf.format(dataInici);
			String df = sdf.format(dataFi);
			logger.debug("findByEstablecimiento ini");
			String q = "from SortidaLot as slot where " +
			"slot.vendaData is not null and slot.vendaData >= '"+ di +"' and slot.vendaData <= '"+ df +"' and " +
			"slot.zona.establiment.id = "+ estId ;
			if (valid != null && valid.booleanValue()) q += " and slot.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and slot.valid = false ";
			q += " and (slot.lot.olivaTaula is null or slot.lot.olivaTaula = false) ";
			q += "order by slot.id";
			col = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("findByEstablecimiento failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findByEstablecimiento fin");
		return col;
		
	}
	
	/**
	 * Retorna SortidaLot  per establiment entre dates
	 * @param 
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection findOlivaTaulaByEstablecimientoEntreDates(Long estId, Boolean valid, Date dataInici, Date dataFi) {
		Collection col;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String di = sdf.format(dataInici);
			String df = sdf.format(dataFi);
			logger.debug("findByEstablecimiento ini");
			String q = "from SortidaLot as slot where " +
			"slot.vendaData is not null and slot.vendaData >= '"+ di +"' and slot.vendaData <= '"+ df +"' and " +
			"slot.zona.establiment.id = "+ estId ;
			if (valid != null && valid.booleanValue()) q += " and slot.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and slot.valid = false ";
			q += " and slot.lot.olivaTaula = true ";
			q += "order by slot.id";
			col = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("findByEstablecimiento failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findByEstablecimiento fin");
		return col;
		
	}
	
	/**
	 * Devuelve el objeto que tiene el id
	 * @param id
	 * @return
	 * @throws InfrastructureException
	 */
	public SortidaLot getById(Long id) throws InfrastructureException {
		SortidaLot sortida;
		try {
			logger.debug("getById ini");
			sortida = (SortidaLot)getHibernateTemplate().load(SortidaLot.class, id);	
		} catch (HibernateException ex) {
			logger.error("getById failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getById fin");
		return sortida;
	}
	
	/**
	 * Recupera el SortidaLot por la traza 
	 * @return
	 * @throws InfrastructureException
	 */
	public SortidaLot findByTraza(Long idTraza, Boolean valid) throws InfrastructureException {
		List col;
		SortidaLot sortidaLot = null;
		try {
			logger.debug("findByTraza ini");
			String q = "from SortidaLot slo where slo.traza.id="+idTraza;
			if (valid != null && valid.booleanValue()) q += " and slo.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and slo.valid = false ";
			col = getHibernateTemplate().find(q);
			if(col!= null && col.size()>0){
				sortidaLot = (SortidaLot)col.get(0);
			}
		} catch (HibernateException ex) {
			logger.error("findByTraza failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findByTraza fin");
		return sortidaLot;
	}


	/**
	 * Retorna true si existen salidas de lotes asociados a zonas
	 * 
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public boolean existenSalidasLoteAsociadasZonas(Long idZona, Boolean valid) throws InfrastructureException {
		List salida;
		try {
			logger.debug("existenSalidasLoteAsociadasZonas ini");
			String q = "select count(sl.id) from SortidaLot sl " +
			"where sl.zona.id = " + idZona + " ";
			if (valid != null && valid.booleanValue()) q += " and sl.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and sl.valid = false ";
			salida = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("existenSalidasLoteAsociadasZonas failed", ex);
			throw new InfrastructureException(ex);
		}
		if (salida != null && salida.size()> 0 && ((Long)salida.get(0)).intValue() > 0) {
			logger.debug("existenSalidasLoteAsociadasZonas fin");
			return true;
		}
		logger.debug("existenSalidasLoteAsociadasZonas fin");
		return false;
	}
	
	
	/**
	 * Retorna true si existen salidas de lotes asociados al lot
	 * 
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public boolean existeixenSortidesLotAssociadesLot(Long idLot, Boolean valid) {
		List salida;
		try {
			logger.debug("existeixnSortidesLotAssociadesLot ini");
			String q = "select count(sl.id) from SortidaLot sl " +
			"where sl.lot.id = " + idLot + " ";
			if (valid != null && valid.booleanValue()) q += " and sl.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and sl.valid = false ";
			salida = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("existeixnSortidesLotAssociadesLot failed", ex);
			throw new InfrastructureException(ex);
		}
		if (salida != null && salida.size()> 0 && ((Long)salida.get(0)).intValue() > 0) {
			logger.debug("existeixnSortidesLotAssociadesLot fin");
			return true;
		}
		logger.debug("existeixnSortidesLotAssociadesLot fin");
		return false;
	}
	

	/**
	 * Actualiza un registro
	 * @param sortida
	 * @throws InfrastructureException
	 */
	public void makePersistent(SortidaLot sortida) throws InfrastructureException {
		try {
			logger.debug("makePersistent ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().saveOrUpdate(sortida);
			getHibernateTemplate().flush();
		} catch (HibernateException ex) {
			logger.error("makePersistent failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("makePersistent fin");
	}

	/**
	 * Borra un registro
	 * @param sortida
	 * @throws InfrastructureException
	 */
	public void makeTransient(SortidaLot sortida)
	throws InfrastructureException {
		try {
			logger.debug("makeTransient ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().delete(sortida);
		} catch (HibernateException ex) {
			logger.error("makeTransient fin", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("makeTransient fin");
	}


	/**
	 * Devuelve SortidaLotVendaEntreDiasEnEstablecimiento por establecimiento y entre 2 fechas
	 * @param 
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection getSortidaLotVendaEntreDiasEnEstablecimiento(Date finicio, Date ffin, Long estId, Boolean valid) throws InfrastructureException {
		Collection col;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String fi = df.format(finicio);
		String ff = df.format(ffin);
		try {
			logger.debug("getSortidaLotVendaEntreDiasEnEstablecimiento ini");
			String q = 	" from SortidaLot slo " +
						"where slo.vendaData >= '"+fi+"' " +
						" and slo.vendaData <= '"+ff+"' " +
						" and slo.accioSortida = 'v' ";
			if (estId != null) q += "and slo.lot.zona.establiment.id = " +estId+ " ";
			if (valid != null && valid.booleanValue()) q += " and slo.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and slo.valid = false ";
			q += " and (slo.lot.olivaTaula is null or slo.lot.olivaTaula = false) ";
			q += " order by slo.vendaData desc";
			col = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("getSortidaLotVendaEntreDiasEnEstablecimiento failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getSortidaLotVendaEntreDiasEnEstablecimiento fin");
		return col;
	}
	
	/**
	 * Devuelve SortidaLotVendaEntreDiasEnEstablecimiento por establecimiento y entre 2 fechas
	 * @param 
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection getSortidaLotVendaOlivaTaulaEntreDiasEnEstablecimiento(Date finicio, Date ffin, Long estId, Boolean valid) throws InfrastructureException {
		Collection col;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String fi = df.format(finicio);
		String ff = df.format(ffin);
		try {
			logger.debug("getSortidaLotVendaEntreDiasEnEstablecimiento ini");
			String q = 	" from SortidaLot slo " +
						"where slo.vendaData >= '"+fi+"' " +
						" and slo.vendaData <= '"+ff+"' " +
						" and slo.accioSortida = 'v' ";
			if (estId != null) q += "and slo.lot.zona.establiment.id = " +estId+ " ";
			if (valid != null && valid.booleanValue()) q += " and slo.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and slo.valid = false ";
			q += " and slo.lot.olivaTaula = true ";
			q += " order by slo.vendaData desc";
			col = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("getSortidaLotVendaEntreDiasEnEstablecimiento failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getSortidaLotVendaEntreDiasEnEstablecimiento fin");
		return col;
	}
	
	/**
	 * Devuelve SortidaLotVendaEntreDiasEnEstablecimiento por establecimiento y entre 2 fechas
	 * @param 
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection getSortidaLotVendaCategoriaEntreDiasEnEstablecimiento(Date finicio, Date ffin, Long estId, Long categoriaId, Boolean valid) throws InfrastructureException {
		Collection col;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String fi = df.format(finicio);
		String ff = df.format(ffin);
		try {
			logger.debug("getSortidaLotVendaEntreDiasEnEstablecimiento ini");
			String q = 	"select slo.vendaNumeroBotelles, slo.lot.etiquetatge.tipusEnvas.volum " +
						"from SortidaLot slo " +
						"where slo.vendaData >= '"+fi+"' " +
						" and slo.vendaData <= '"+ff+"' " +
						" and slo.accioSortida = 'v' " +
						" and slo.lot.partidaOli.categoriaOli.id = " + categoriaId + " ";
			if (estId != null) q += "and slo.lot.zona.establiment.id = " +estId+ " ";
			if (valid != null && valid.booleanValue()) q += " and slo.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and slo.valid = false ";
			q += " order by slo.vendaData desc";
			col = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("getSortidaLotVendaEntreDiasEnEstablecimiento failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getSortidaLotVendaEntreDiasEnEstablecimiento fin");
		return col;
	}

	/**
	 * Retorna suma de sortides d'un lot donat, fins a una data concreta
	 * @param 
	 * @return
	 * @throws InfrastructureException
	 */
	public Integer getSumaSortidesLotFinsData(Long idLot, Date data, Boolean valid) {
		Long botelles = 0L;
		Collection col;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String fi = df.format(data);
		try {
			logger.debug("getSumaSortidesLotFinsData ini");
			String q = 	" select sum(slo.vendaNumeroBotelles) " +
						"from SortidaLot slo " +
						"where slo.lot.id = " + idLot + " " +
						"and slo.vendaData > '" + fi + "' " +
						"and slo.accioSortida = 'v' " +
						"and (slo.lot.datafi is null or slo.lot.datafi > '" + fi + "') ";
			if (valid != null && valid.booleanValue()) q += " and slo.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and slo.valid = false ";
			
			col = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("getSumaSortidesLotFinsData failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getSumaSortidesLotFinsData fin");
		
		if (col != null && col.size() > 0){
			botelles = ((Long)col.iterator().next());
			if (botelles == null) botelles = 0L;
		}
		return botelles.intValue();
	}
	
	/**
	 * Retorna suma de sortides d'un lot donat, fins a una data concreta
	 * @param 
	 * @return
	 * @throws InfrastructureException
	 */
	public Double getSumaSortidesLotsFinsData(Long[] idLots, Date data, Boolean valid) {
		Double botelles = 0.0;
		Collection col;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String fi = df.format(data);
		try {
			logger.debug("getSumaSortidesLotFinsData ini");
			String q = 	"select sum(slo.vendaNumeroBotelles * slo.lot.etiquetatge.tipusEnvas.volum) " +
						"from SortidaLot slo " +
						"where slo.lot.id in (:idLots) " +
						"and slo.vendaData > '" + fi + "' " +
						"and slo.accioSortida = 'v' ";
			if (valid != null && valid.booleanValue()) q += " and slo.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and slo.valid = false ";
			
			col = getHibernateTemplate().findByNamedParam(q, "idLots", idLots);
		} catch (HibernateException ex) {
			logger.error("getSumaSortidesLotFinsData failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getSumaSortidesLotFinsData fin");
		
		if (col != null && col.size() > 0){
			botelles = ((Double)col.iterator().next());
			if (botelles == null) botelles = 0.0;
		}
		return botelles;
	}
	
	/**
	 * Devuelve TotalOliComercialitzatByCategorias y entre 2 fechas o por temporada 
	 * @param 
	 * @return
	 * @throws InfrastructureException
	 */
	public Double getTotalOliComercialitzatByCategorias(Long temporadaId, Date dataInici, Date dataFin, Object[] categorias,Long temporadaActual, Boolean valid) throws InfrastructureException {
		Collection col;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Double numero = new Double(0);
		try {
			logger.debug("getTotalOliComercialitzatByCategorias ini");
			
			// String q = "select sum(slo.lot.litresEnvassats) from SortidaLot slo where slo.accioSortida = 'v'"; 
			
			String q = "select slo from SortidaLot slo where slo.accioSortida = 'v'";

			if(dataInici != null || dataFin != null){
				if(dataInici != null){
					String fi = df.format(dataInici);
					q += " and  slo.vendaData >= :dataInici ";
				}
				if(dataFin != null){
					String ff = df.format(dataFin);
					q += " and  slo.vendaData <= :dataFin ";
				}

				q += " and slo.lot.zona.establiment.campanya.id="+temporadaActual;

			}else{
				q += " and slo.lot.zona.establiment.campanya.id = :temporadaId ";
			}

			if(categorias != null && categorias.length > 0){
				q += " and slo.lot.partidaOli.categoriaOli.id in(:categorias) ";
			}
			if (valid != null && valid.booleanValue()) q += " and slo.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and slo.valid = false ";
			
			int numParams = 0;
			if(dataInici != null || dataFin != null){
				if (dataInici != null) numParams++;
				if (dataFin != null) numParams++;
			} else {
				numParams++;
			}
			if (categorias != null && categorias.length > 0) numParams++;
			String[] paramNames = new String[numParams];
			Object[] values = new Object[numParams];
			
			int numParam = -1;
			if (dataInici != null) {
				numParam++;
				paramNames[numParam] = "dataInici";
				values[numParam] = dataInici;
			}
			if (dataFin != null) {
				numParam++;
				paramNames[numParam] = "dataFin";
				values[numParam] = dataFin;
			}
			if(dataInici == null && dataFin == null) {
				numParam++;
				paramNames[numParam] = "temporadaId";
				values[numParam] = temporadaId;
			}
			if (categorias != null && categorias.length > 0) {
				numParam++;
				paramNames[numParam] = "categorias";
				values[numParam] = categorias;
			}

			col = getHibernateTemplate().findByNamedParam(q, paramNames, values);
			
			SortidaLot sortidaLot = null;
			Iterator ite = col.iterator();
			while (ite.hasNext()){
				sortidaLot = (SortidaLot) ite.next();
				if(sortidaLot.getLot().getLitresEnvassats() != null && sortidaLot.getLot().getNumeroBotellesInicials()!= null && sortidaLot.getVendaNumeroBotelles()!= null ){
					double litrosPorBotella = sortidaLot.getLot().getLitresEnvassats().doubleValue()/sortidaLot.getLot().getNumeroBotellesInicials().doubleValue();
					double litrosVendidos = sortidaLot.getVendaNumeroBotelles().doubleValue()* litrosPorBotella;
					numero = new Double (numero.doubleValue() + litrosVendidos);
				}
			}
			
			
		} catch (HibernateException ex) {
			logger.error("getTotalOliComercialitzatByCategorias failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getTotalOliComercialitzatByCategorias fin");
		return numero;
	}

	/**
	 * Devuelve TotalOliComercialitzatByCategorias y entre 2 fechas o por temporada 
	 * @param 
	 * @return
	 * @throws InfrastructureException
	 */
	public Double getTotalOliComercialitzatByCategoriasEntreDates(Date dataInici, Date dataFin, Object[] categorias) throws InfrastructureException {
		Collection col;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Double numero = new Double(0);
		try {
			logger.debug("getTotalOliComercialitzatByCategoriasEntreDates ini");
			
			String q = "select slo from SortidaLot slo where slo.accioSortida = 'v'";
			if(dataInici != null){
				String fi = df.format(dataInici);
				q += " and  slo.vendaData >= '"+ fi +"'" ;
			}
			if(dataFin != null){
				String ff = df.format(dataFin);
				q += " and  slo.vendaData <= '" + ff + "'";
			}
			q += " and slo.valid = true ";
			if(categorias != null && categorias.length > 0){
				q += " and slo.lot.partidaOli.categoriaOli.id in(:categorias) ";
				col = getHibernateTemplate().findByNamedParam(q, "categorias", categorias);
			} else {
				col = getHibernateTemplate().find(q);
			}
			
			SortidaLot sortidaLot = null;
			Iterator ite = col.iterator();
			while (ite.hasNext()){
				sortidaLot = (SortidaLot) ite.next();
				if(sortidaLot.getLot().getLitresEnvassats() != null && sortidaLot.getLot().getNumeroBotellesInicials()!= null && sortidaLot.getVendaNumeroBotelles()!= null ){
					double litrosPorBotella = sortidaLot.getLot().getLitresEnvassats().doubleValue()/sortidaLot.getLot().getNumeroBotellesInicials().doubleValue();
					double litrosVendidos = sortidaLot.getVendaNumeroBotelles().doubleValue()* litrosPorBotella;
					numero = new Double (numero.doubleValue() + litrosVendidos);
				}
			}
			
			
		} catch (HibernateException ex) {
			logger.error("getTotalOliComercialitzatByCategoriasEntreDates failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getTotalOliComercialitzatByCategoriasEntreDates fin");
		return numero;
	}
	
	/**
	 * Devuelve dos map uno con establecimientos y otro con el numero de litros de aceite vendido por estrablecimiento 
	 * @param 
	 * @return
	 * @throws InfrastructureException
	 */
	public List getTotalOliComercialitzatByEstablecimiento(Date dataInici, Date dataFin, Boolean valid) throws InfrastructureException {
		Collection col;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		List mapList = new ArrayList();
		try {
			logger.debug("getTotalOliComercialitzatByEstablecimiento ini");
			
			// Aceite vendido de lotes
			String q = "select slo from SortidaLot slo where slo.accioSortida = 'v'"; 

			if(dataInici != null && !dataInici.equals("")){
				if(dataInici != null){
					String fi = df.format(dataInici);
					q += " and  slo.vendaData >= '"+ fi+"'" ;
				}
			}
			if(dataFin != null && !dataFin.equals("")){
				if(dataFin != null){
					String ff = df.format(dataFin);
					q += " and  slo.vendaData <= '"+ff+"'" ;
				}
			}
			q += " and slo.lot.zona.establiment.campanya.id= (select max(cam.id) from Campanya cam)";			
			if (valid != null && valid.booleanValue()) q += " and slo.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and slo.valid = false ";
			q += " order by slo.lot.zona.establiment.nom asc";
			
			col = getHibernateTemplate().find(q);
			HashMap establimentMap = new HashMap();
			HashMap establimentOliComercialitzatMap = new HashMap();
			Establiment establiment = new Establiment();
			
			for (Iterator it=col.iterator(); it.hasNext();){
				SortidaLot sortidaLot = (SortidaLot)it.next();
				if(sortidaLot.getLot()!= null){
					establiment = sortidaLot.getLot().getZona().getEstabliment();
					double totalLitros = 0;	
					double litrosPorBotella = 0;
					double litrosVendidos = 0;
					if(sortidaLot.getLot().getLitresEnvassats() != null && sortidaLot.getLot().getNumeroBotellesInicials()!= null && sortidaLot.getVendaNumeroBotelles()!= null ){
						litrosPorBotella = sortidaLot.getLot().getEtiquetatge().getTipusEnvas().getVolum();
						litrosVendidos = sortidaLot.getVendaNumeroBotelles().doubleValue()* litrosPorBotella;
					}
					if(establimentMap.get(establiment.getId())!= null && establimentOliComercialitzatMap.get(establiment.getId())!= null){
						totalLitros = ((Double)establimentOliComercialitzatMap.get(establiment.getId())).doubleValue()+litrosVendidos;
						establimentOliComercialitzatMap.put(establiment.getId(), Double.valueOf(String.valueOf(totalLitros)));
					}else{
						establimentMap.put(establiment.getId(), establiment);
						establimentOliComercialitzatMap.put(establiment.getId(), Double.valueOf(String.valueOf(litrosVendidos)));
					}
				}				
			}
			
			
			// Aceite vendido a granel de depositos
			
			q = "select sordip from SortidaDiposit sordip where sordip.traza.tipus = "+Constants.CODI_TRAZA_TIPUS_VENDA_OLI;
			
			if(dataInici != null && !dataInici.equals("")){
				if(dataInici != null){
					String fi = df.format(dataInici);
					q += " and  sordip.data >= '"+ fi+"'" ;
				}
			}
			if(dataFin != null && !dataFin.equals("")){
				if(dataFin != null){
					String ff = df.format(dataFin);
					q += " and  sordip.data <= '"+ff+"'" ;
				}
			}
			q += " and sordip.establiment.campanya.id= (select max(cam.id) from Campanya cam)";			
			if (valid != null && valid.booleanValue()) q += " and sordip.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and sordip.valid = false ";
			q += " order by sordip.establiment.nom asc";			
			col = getHibernateTemplate().find(q);
			
			for (Iterator it=col.iterator(); it.hasNext();){
				SortidaDiposit sortidaDiposit = (SortidaDiposit)it.next();
				establiment = sortidaDiposit.getEstabliment();
				double litrosVendidos = 0;
				double totalLitros = 0;	
				if(sortidaDiposit.getLitres() != null ){
					litrosVendidos = sortidaDiposit.getLitres().doubleValue();
				}
				if(establimentMap.get(establiment.getId())!= null && establimentOliComercialitzatMap.get(establiment.getId())!= null){
					totalLitros = ((Double)establimentOliComercialitzatMap.get(establiment.getId())).doubleValue()+litrosVendidos;
					establimentOliComercialitzatMap.put(establiment.getId(), Double.valueOf(String.valueOf(totalLitros)));
				}else{
					establimentMap.put(establiment.getId(), establiment);
					establimentOliComercialitzatMap.put(establiment.getId(), Double.valueOf(String.valueOf(litrosVendidos)));
				}
							
			}
			
			
			mapList.add(establimentMap);
			mapList.add(establimentOliComercialitzatMap);
			
		} catch (HibernateException ex) {
			logger.error("getTotalOliComercialitzatByEstablecimiento failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getTotalOliComercialitzatByEstablecimiento fin");
		return mapList;
	}

	
	/**
	 * Devuelve los lotes que han salido de un establecimiento entre 2 fechas dadas
	 * @param fi
	 * @param ff
	 * @param idEst
	 * @return
	 */
	public Collection getSortidesLotesEntreFechasAndEstablecimiento(Date finicio, Date ffin, Long idEst, Boolean valid){
		
		Collection col;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String fi = df.format(finicio);
		String ff = df.format(ffin);

		try {
			logger.debug("getSortidesLotesEntreFechasAndEstablecimiento inicio");
//			String q = "from SortidaLot sorlot where " +
//			"(sorlot.vendaData >= '"+fi+"' and sorlot.vendaData <= '"+ff+"' or sorlot.canviZonaData >= '"+fi+"' and sorlot.canviZonaData <= '"+ff+"')"
//			+"and sorlot.lot.zona.establiment.idOriginal in(select est.idOriginal from Establiment est where est.id = " +idEst+ ")";
//			if (valid != null && valid.booleanValue()) q += " and sorlot.valid = true ";
//			if (valid != null && !valid.booleanValue()) q += " and sorlot.valid = false ";
			//Modificamos la query anterior para que no se tomen las salidas de lote causadas por un cambio de zona solo por venta
			String q = "from SortidaLot sorlot where " +
			"sorlot.vendaData >= '"+fi+"' and sorlot.vendaData <= '"+ff+"'"
			+" and sorlot.accioSortida ='v'"
			+" and sorlot.lot.zona.establiment.idOriginal in(select est.idOriginal from Establiment est where est.id = " +idEst+ ")";
			if (valid != null && valid.booleanValue()) q += " and sorlot.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and sorlot.valid = false ";
			col = getHibernateTemplate().find(q);
			
		} catch (HibernateException ex) {
			logger.error("getSortidesLotesEntreFechasAndEstablecimiento failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getSortidesLotesEntreFechasAndEstablecimiento fin");
		return col;
		
	}
	
	/**
	 * Retorna SortidaLot  per Marca
	 * @param 
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection findByMarca(Long idMarca, Boolean valid) {
		Collection col;
		try {
			logger.debug("findByMarca ini");
			String q = "from SortidaLot as sl where sl.lot.etiquetatge.marca.id = "+ idMarca;
			if (valid != null && valid.booleanValue()) q += " and sl.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and sl.valid = false ";
			q += " order by sl.id desc";
			col = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("findByMarca failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findByMarca fin");
		return col;
	}

	/**
	 * Retorna SortidaLot  per lot de botella buida
	 * @param 
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection findByLotbotellaBuida(String lot, Boolean valid) {
		Collection col;
		try {
			logger.debug("findByLotbotellaBuida ini");
			String q = "from SortidaLot as sl where sl.lot.numeroLot = '" + lot + "'";
			if (valid != null && valid.booleanValue()) q += " and sl.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and sl.valid = false ";
			q += " order by sl.id desc";
			col = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("findByLotbotellaBuida failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findByLotbotellaBuida fin");
		return col;
	}
	
	/**
	 * Devuelve SortidaLot  por lotes
	 * @param 
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection findByLots(Long[] lotsId, Boolean valid) throws InfrastructureException {
		Collection col;
		try {
			logger.debug("findByLots ini");
			String q = "from SortidaLot as sl where sl.lot.id in (:lotsId)";
			if (valid != null && valid.booleanValue()) q += " and sl.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and sl.valid = false ";
			q += " order by sl.id desc";
			col = getHibernateTemplate().findByNamedParam(q, "lotsId", lotsId);
		} catch (HibernateException ex) {
			logger.error("findByLots failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findByLots fin");
		return col;
	}
	
	
	/**
	 * Devuelve SortidaLot  por lotes
	 * @param 
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection getSortidaLotVendaFiltre(Date dataInici, Date dataFi, Long establimentId, String document, String motiuVenda) throws InfrastructureException {
		Collection col;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String di = df.format(dataInici);
		String dfi = df.format(dataFi);
		try {
			logger.debug("getSortidaLotVendaFiltre ini");
			String q = "from SortidaLot as sl where " +
					" sl.vendaData >= '"+di+"' and sl.vendaData <= '"+dfi+"' " +
					" and sl.lot.zona.establiment.idOriginal in(select est.idOriginal from Establiment est where est.id = " +establimentId+ ")";
			if (!document.equals(null) && !document.equals("")) q += " and upper(sl.vendaNumeroDocument) like upper('%"+document+"%') ";
			if (!motiuVenda.equals(null) && !motiuVenda.equals("")) q += " and upper(sl.vendaMotiu) like upper('%"+motiuVenda+"%') ";
			q += " order by sl.id desc";
			col = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("getSortidaLotVendaFiltre failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getSortidaLotVendaFiltre fin");
		return col;
	}
	
	
	/**
	 * Devuelve SortidaLot  por lotes
	 * @param 
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection getSortidaLotById(Long sortidaLotId) throws InfrastructureException {
		Collection sl = null;
		try {
			logger.debug("getSortidaLotById ini");
			String q = "from SortidaLot as sl where sl.id = :sortidaLotId)";
			sl = getHibernateTemplate().findByNamedParam(q, "sortidaLotId", sortidaLotId);
		} catch (HibernateException ex) {
			logger.error("getSortidaLotById failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getSortidaLotById fin");
		return sl;
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