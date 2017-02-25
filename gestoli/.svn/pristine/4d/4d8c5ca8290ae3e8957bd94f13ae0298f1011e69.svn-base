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
import es.caib.gestoli.logic.model.SortidaDiposit;
import es.caib.gestoli.logic.model.SortidaLot;
import es.caib.gestoli.logic.model.SortidaPartida;
import es.caib.gestoli.logic.util.Constants;


/**
 * Home object for domain model class SortidaLot
 * @see es.caib.gestoli.logic.model.SortidaLot
 * @author Carlos Perez Claro
 */
public class SortidaPartidaDao {
	private static Logger logger = Logger.getLogger(SortidaPartidaDao.class);
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
			String q = "from SortidaPartida as slot where 1 = 1 ";
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
	 * Retorna SortidaPartida  per establiment entre dates
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
			String q = "from SortidaPartida as spartida where " +
			"spartida.vendaData is not null and spartida.vendaData >= '"+ di +"' and spartida.vendaData <= '"+ df +"' and " +
			"spartida.establiment.id = "+ estId ;
			if (valid != null && valid.booleanValue()) q += " and spartida.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and spartida.valid = false ";
			//q += " and (spartida.partidaOliva.olivaTaula is null or spartida.lot.olivaTaula = false) ";
			q += "order by spartida.id";
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
	public SortidaPartida getById(Long id) throws InfrastructureException {
		SortidaPartida sortida;
		try {
			logger.debug("getById ini");
			sortida = (SortidaPartida)getHibernateTemplate().load(SortidaPartida.class, id);	
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
	public SortidaPartida findByTraza(Long idTraza, Boolean valid) throws InfrastructureException {
		List col;
		SortidaPartida sortidaPartida = null;
		try {
			logger.debug("findByTraza ini");
			String q = "from SortidaLot slo where slo.traza.id="+idTraza;
			if (valid != null && valid.booleanValue()) q += " and slo.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and slo.valid = false ";
			col = getHibernateTemplate().find(q);
			if(col!= null && col.size()>0){
				sortidaPartida = (SortidaPartida)col.get(0);
			}
		} catch (HibernateException ex) {
			logger.error("findByTraza failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findByTraza fin");
		return sortidaPartida;
	}


	/**
	 * Actualiza un registro
	 * @param sortida
	 * @throws InfrastructureException
	 */
	public void makePersistent(SortidaPartida sortida) throws InfrastructureException {
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
	public void makeTransient(SortidaPartida sortida)
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
	 * Devuelve SortidaVendaOlivaTaulaCruaGranelEntreDiasEnEstablecimiento por establecimiento y entre 2 fechas
	 * @param 
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection getSortidaVendaOlivaTaulaCruaGranelEntreDiasEnEstablecimiento(Date finicio, Date ffin, Long estId, Boolean valid) throws InfrastructureException {
		Collection col;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String fi = df.format(finicio);
		String ff = df.format(ffin);
		try {
			logger.debug("getSortidaVendaOlivaTaulaCruaGranelEntreDiasEnEstablecimiento ini");
			String q = 	" from SortidaPartida slo " +
						"where slo.vendaData >= '"+fi+"' " +
						" and slo.vendaData <= '"+ff+"' " +
						" and slo.accion = 'v' ";
			if (estId != null) q += "and slo.establiment.id = " +estId+ " ";
			if (valid != null && valid.booleanValue()) q += " and slo.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and slo.valid = false ";
			q += " order by slo.vendaData desc";
			col = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("getSortidaVendaOlivaTaulaCruaGranelEntreDiasEnEstablecimiento failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getSortidaVendaOlivaTaulaCruaGranelEntreDiasEnEstablecimiento fin");
		return col;
	}
	
	/**
	 * Retorna suma de sortides d'un lot donat, fins a una data concreta
	 * @param 
	 * @return
	 * @throws InfrastructureException
	 */
	public Long getSumaSortidesOlivaTaulaCruaGranelFinsData(Long idPartida, Date data, Boolean valid) {
		Long kilos = 0L;
		Collection col;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String fi = df.format(data);
		try {
			logger.debug("getSumaSortidesOlivaTaulaCruaGranelFinsData ini");
			String q = 	" select sum(slo.vendaNumeroBotelles) " +
						"from SortidaPartida slo " +
						"where slo.id = " + idPartida + " " +
						"and slo.vendaData > '" + fi + "' " +
						"and slo.accioSortida = 'v' ";
			if (valid != null && valid.booleanValue()) q += " and slo.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and slo.valid = false ";
			
			col = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("getSumaSortidesOlivaTaulaCruaGranelFinsData failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getSumaSortidesOlivaTaulaCruaGranelFinsData fin");
		
		if (col != null && col.size() > 0){
			kilos = ((Long)col.iterator().next());
			if (kilos == null) kilos = 0L;
		}
		return kilos;
	}
	
	/**
	 * Retorna suma de sortides d'un lot donat, fins a una data concreta
	 * @param 
	 * @return
	 * @throws InfrastructureException
	 */
	public Double getSumaSortidesOlivaTaulaCruaGranelFinsData(Long[] idPartides, Date data, Boolean valid) {
		Double kilos = 0.0;
		Collection col;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String fi = df.format(data);
		try {
			logger.debug("getSumaSortidesOlivaTaulaCruaGranelFinsData ini");
			String q = 	"select sum(slo.vendaKilos) " +
						"from SortidaPartida slo " +
						"where slo.id in (:idPartides) " +
						"and slo.vendaData > '" + fi + "' " +
						"and slo.accioSortida = 'v' ";
			if (valid != null && valid.booleanValue()) q += " and slo.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and slo.valid = false ";
			
			col = getHibernateTemplate().findByNamedParam(q, "idPartides", idPartides);
		} catch (HibernateException ex) {
			logger.error("getSumaSortidesOlivaTaulaCruaGranelFinsData failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getSumaSortidesOlivaTaulaCruaGranelFinsData fin");
		
		if (col != null && col.size() > 0){
			kilos = ((Double)col.iterator().next());
			if (kilos == null) kilos = 0.0;
		}
		return kilos;
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