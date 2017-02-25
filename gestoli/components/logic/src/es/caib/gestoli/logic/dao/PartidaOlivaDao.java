package es.caib.gestoli.logic.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.exception.InfrastructureException;
import es.caib.gestoli.logic.model.PartidaOliva;


/**
 * Home object for domain model class DescomposicioPartidaOliva
 * @see es.caib.gestoli.logic.model.DescomposicioPartidaOlivaDao
 * 
 */
public class PartidaOlivaDao {
	private static Logger logger = Logger.getLogger(PartidaOlivaDao.class);
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
			String q = "from PartidaOliva as pao where 1 = 1 ";
			if (valid != null && valid.booleanValue()) q += "and pao.valid = true ";
			if (valid != null && !valid.booleanValue()) q += "and pao.valid = false ";
			q += "order by pao.id";
			col = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("findAll failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findAll fin");
		return col;
	}

	
	/**
	 * Retorna un llista de totes les partides d'oliva associades amb
	 * una zona determinada.
	 * @return
	 * @throws InfrastructureException
	 */
	public List findByZona(Long zonaId, Boolean valid) throws InfrastructureException {
		List diposits;
		try {
			logger.debug("findByZona ini");
			String q = "from PartidaOliva pao " +
			"where pao.zona.id = " + zonaId + " ";
			if (valid != null && valid.booleanValue()) q += "and pao.valid = true ";
			if (valid != null && !valid.booleanValue()) q += "and pao.valid = false ";
			q += "order by pao.id";
			diposits = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("findByZona failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findByZona fin");
		return diposits;
	}
	
	/**
	 * Retorna un llista de totes les partides d'oliva associades amb
	 * una zona determinada.
	 * @return
	 * @throws InfrastructureException
	 */
	public List findByEstabliment(Long establimentId, Boolean valid) throws InfrastructureException {
		List partides;
		try {
			logger.debug("findByEstabliment ini");
			String q = "from PartidaOliva pao " +
			"where pao.zona.establiment.id = " + establimentId;
			if (valid != null && valid.booleanValue()) q += "and pao.valid = true ";
			if (valid != null && !valid.booleanValue()) q += "and pao.valid = false ";
			q += "order by pao.id";
			partides = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("findByEstabliment failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findByEstabliment fin");
		return partides;
	}
	
	public List findByEstablimentEntreDates(Long establimentId, Date dataInici, Date dataFi, Boolean valid) throws InfrastructureException {
		List partides;
		try {
			logger.debug("findByEstabliment ini");
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String di = sdf.format(dataInici);
			String df = sdf.format(dataFi);
			
			String q = "from PartidaOliva pao " +
			"where pao.data >= '"+ di +"' and pao.data <= '"+ df +"' and pao.zona.establiment.id = " + establimentId;
			if (valid != null && valid.booleanValue()) q += "and pao.valid = true ";
			if (valid != null && !valid.booleanValue()) q += "and pao.valid = false ";
			q += "order by pao.id";
			partides = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("findByEstabliment failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findByEstabliment fin");
		return partides;
	}
	
	public List findEntradaOlivaTaulaByEstablimentEntreDates(Long establimentId, Date dataInici, Date dataFi, Boolean valid) throws InfrastructureException {
		List partides;
		try {
			logger.debug("findByEstabliment ini");
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String di = sdf.format(dataInici);
			String df = sdf.format(dataFi);
			
			String q = "from PartidaOliva pao " +
			"where pao.data >= '"+ di +"' and pao.data <= '"+ df +"' and pao.zona.establiment.id = " + establimentId;
			if (valid != null && valid.booleanValue()) q += "and pao.valid = true ";
			if (valid != null && !valid.booleanValue()) q += "and pao.valid = false ";
			q += "and pao.olivaTaula = true ";
			q += "order by pao.id";
			partides = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("findByEstabliment failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findByEstabliment fin");
		return partides;
	}

	/**
	 * Retorna un llista de totes les partides d'oliva associades amb
	 * la traça.
	 * @return
	 * @throws InfrastructureException
	 */
	public PartidaOliva findByTraza(Long trazaId, Boolean valid) throws InfrastructureException {
		List list;
		PartidaOliva partida = null;
		try {
			logger.debug("findByTraza ini");
			String q = "from PartidaOliva pao " +
			"where pao.traza.id = " + trazaId + " ";
			if (valid != null && valid.booleanValue()) q += "and pao.valid = true ";
			if (valid != null && !valid.booleanValue()) q += "and pao.valid = false ";
			list = getHibernateTemplate().find(q);
			
			if (list != null && list.size() > 0) {
				partida = (PartidaOliva) list.get(0);
			}
		} catch (HibernateException ex) {
			logger.error("findByTraza failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findByTraza fin");
		return partida;
	}
	
	/**
	 * Retorna un llista de totes les partides d'oliva associades amb
	 * una zona determinada que no han sido utilizadas para la elaboracion de aceite.
	 * @return
	 * @throws InfrastructureException
	 */
	public List findNoUtlizadasByZona(Long zonaId, Boolean valid) throws InfrastructureException {
		List diposits;
		try {
			logger.debug("findNoUtlizadasByZona ini");
			String q = "from PartidaOliva pao " +
			"where pao.zona.id = " + zonaId + " " +
			"and (pao.elaboracio is null or pao.elaboracio.id in (select ela.id from Elaboracio ela where ela.valid = false)) " +
			"and (pao.olivaTaula = false or pao.quantitat > 0) ";
			if (valid != null && valid.booleanValue()) q += "and pao.valid = true ";
			if (valid != null && !valid.booleanValue()) q += "and pao.valid = false ";
			q += "order by pao.id";
			diposits = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("findNoUtlizadasByZona failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findNoUtlizadasByZona fin");
		return diposits;
	}
	
	/**
	 * Retorna un llista de totes les partides d'oliva per oli associades amb
	 * una zona determinada que no han sido utilizadas para la elaboracion de aceite.
	 * @return
	 * @throws InfrastructureException
	 */
	public List findPerOliNoUtlizadasByZona(Long zonaId, Boolean valid) throws InfrastructureException {
		List diposits;
		try {
			logger.debug("findNoUtlizadasByZona ini");
			String q = "from PartidaOliva pao " +
			"where pao.zona.id = " + zonaId + " " +
			"and (pao.olivaTaula is null or pao.olivaTaula = false) " +
			"and (pao.elaboracio is null or pao.elaboracio.id in (select ela.id from Elaboracio ela where ela.valid = false)) ";
			if (valid != null && valid.booleanValue()) q += "and pao.valid = true ";
			if (valid != null && !valid.booleanValue()) q += "and pao.valid = false ";
			q += "order by pao.id";
			diposits = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("findNoUtlizadasByZona failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findNoUtlizadasByZona fin");
		return diposits;
	}
	
	/**
	 * Retorna un llista de totes les partides d'oliva de taula associades amb
	 * una zona determinada que no han sido utilizadas para la elaboracion de aceite.
	 * @return
	 * @throws InfrastructureException
	 */
	public List findOlivaTaulaNoUtlitzadesByZona(Long zonaId, Boolean valid) throws InfrastructureException {
		List diposits;
		try {
			logger.debug("findNoUtlizadasByZona ini");
			String q = "from PartidaOliva pao " +
			"where pao.zona.id = " + zonaId + " " +
			"and pao.olivaTaula = true " +
			"and pao.quantitat > 0 ";
			if (valid != null && valid.booleanValue()) q += "and pao.valid = true ";
			if (valid != null && !valid.booleanValue()) q += "and pao.valid = false ";
			q += "order by pao.id";
			diposits = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("findNoUtlizadasByZona failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findNoUtlizadasByZona fin");
		return diposits;
	}

	/**
	 * Retorna true si existen partides de oliva asociadas a zonas
	 * 
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public boolean existenPartidasAsociadasZonas(Long idZona, Boolean valid) throws InfrastructureException {
		List partidas;
		try {
			logger.debug("existenPartidasAsociadasZonas ini");
			String q = "select count(pao.id) from PartidaOliva pao " +
			"where pao.zona.id = " + idZona + " ";
			if (valid != null && valid.booleanValue()) q += "and pao.valid = true ";
			if (valid != null && !valid.booleanValue()) q += "and pao.valid = false ";
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
	 * Actualiza un registro
	 * @param partidaOliva
	 * @throws InfrastructureException
	 */
	public void makePersistent(PartidaOliva partidaOliva) throws InfrastructureException {
		try {
			logger.debug("makePersistent ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			if(partidaOliva.getId()!= null){
				getHibernateTemplate().saveOrUpdate(partidaOliva);
			}else{
				getHibernateTemplate().save(partidaOliva);
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
	 * @param partidaOliva
	 * @throws InfrastructureException
	 */
	public void makePersistent(PartidaOliva partidaOliva, Session session) throws InfrastructureException {
		try {
			logger.debug("makePersistent ini");
			session.setFlushMode(FlushMode.ALWAYS);
			if(partidaOliva.getId()!= null){
				session.saveOrUpdate(partidaOliva);
			}else{
				session.save(partidaOliva);
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
	 * @param partidaOliva
	 * @throws InfrastructureException
	 */
	public void makeTransient(PartidaOliva partidaOliva) throws InfrastructureException {
		try {
			logger.debug("makeTransient ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().delete(partidaOliva);
			getHibernateTemplate().flush();
		} catch (HibernateException ex) {
			logger.error("makeTransient failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("makeTransient fin");
	}


//	/**
//	 * Borra un registro
//	 * @param PartidaOliva
//	 * @throws InfrastructureException
//	 */
//	public void makeTransient(PartidaOliva partidaOliva, Session session) throws InfrastructureException {
//		try {
//			session.delete(partidaOliva);
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
	public PartidaOliva getById(Long id) throws InfrastructureException {
		PartidaOliva partidaOliva;
		try {
			logger.debug("getById ini");
			partidaOliva = (PartidaOliva)getHibernateTemplate().load(PartidaOliva.class, id);	
			logger.debug("getById fin");
		} catch (HibernateException ex) {
			logger.error("getById failed", ex);
			throw new InfrastructureException(ex);
		}
		return partidaOliva;
	}


	/**
	 * Devuelve el numero de entrada de oliva
	 * @param dataExecucio
	 * @return
	 * @throws InfrastructureException
	 */
	public Integer getNumeroEntrada(Date dataExecucio, Long establimentId, Boolean valid) throws InfrastructureException {
		Integer nuemeroEntrada = null;
		Collection col;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yy");
		String fecha = df.format(dataExecucio);
		try {
			logger.debug("getNumeroEntrada ini");
			String q = "select max(pao.numeroEntrada) from PartidaOliva as pao " +
			"where pao.data = '"+fecha+"' and pao.zona.establiment.id = "+ establimentId + " ";
			if (valid != null && valid.booleanValue()) q += "and pao.valid = true ";
			if (valid != null && !valid.booleanValue()) q += "and pao.valid = false ";
			col = getHibernateTemplate().find(q);
			while (col.iterator().hasNext()){
				nuemeroEntrada = (Integer)col.iterator().next();
				break;
			}
		} catch (HibernateException ex) {
			logger.error("getNumeroEntrada failed", ex);
			throw new InfrastructureException(ex);
		}
		if(nuemeroEntrada!= null){
			nuemeroEntrada = Integer.valueOf(String.valueOf(nuemeroEntrada.intValue() + 1));
		}else{
			nuemeroEntrada = Integer.valueOf(String.valueOf(1));
		}
		logger.debug("getNumeroEntrada fin");
		return nuemeroEntrada;
	}


	public Collection getPartidasTaulaEntreDiasEnEstablecimiento(Date finicio, Date ffin, Long estId, Boolean valid) throws InfrastructureException {
		return getPartidasEntreDiasEnEstablecimiento(finicio, ffin, estId, valid, false);
	}
	
	public Collection getPartidasOliEntreDiasEnEstablecimiento(Date finicio, Date ffin, Long estId, Boolean valid) throws InfrastructureException {
		return getPartidasEntreDiasEnEstablecimiento(finicio, ffin, estId, valid, true);
	}
	
	public Collection getPartidasEntreDiasEnEstablecimiento(Date finicio, Date ffin, Long estId, Boolean valid) throws InfrastructureException {
		return getPartidasEntreDiasEnEstablecimiento(finicio, ffin, estId, valid, null);
	}
	/**
	 * Devuelve partidas de oliva por establecimiento y entre 2 fechas
	 * @param 
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection getPartidasEntreDiasEnEstablecimiento(Date finicio, Date ffin, Long estId, Boolean valid, Boolean oli) throws InfrastructureException {
		Collection col;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String fi = df.format(finicio);
		String ff = df.format(ffin);
		try {
			logger.debug("getPartidasEntreDiasEnEstablecimiento ini");
			String q = "";
			if(estId != null){
				q = "from PartidaOliva as pao where pao.data >= '"+fi+"' and pao.data <= '"+ff+"' and pao.zona.establiment.id = "+ estId +" ";
			}else{
				q = "from PartidaOliva as pao where pao.data >= '"+fi+"' and pao.data <= '"+ff+"' ";
			}			
			if (valid != null && valid.booleanValue()) q += "and pao.valid = true ";
			if (valid != null && !valid.booleanValue()) q += "and pao.valid = false ";
			if (oli != null && oli.booleanValue()) q += "and (pao.olivaTaula is null or pao.olivaTaula = false) ";
			if (oli != null && !oli.booleanValue()) q += "and pao.olivaTaula = true ";
			q += "order by pao.data desc ,pao.numeroEntrada desc";
			col = getHibernateTemplate().find(q);
			logger.debug("getPartidasEntreDiasEnEstablecimiento fin");
		} catch (HibernateException ex) {
			logger.error("getPartidasEntreDiasEnEstablecimiento failed", ex);
			throw new InfrastructureException(ex);
		}
		return col;
	}


	/**
	 * Retorna un llista de tots els partidas d'un establiment
	 * que tenen un id que està a dins la llista que se passa com a
	 * paràmetre i que siguin de tipus <i>partida de aceite</i>.
	 * @param seleccio
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection findInfo(Long establimentId, Long zonaId, Object[] seleccio, boolean senseZona, Boolean valid) throws InfrastructureException {
		Collection c = null;
		try {
			logger.debug("findInfo ini");
			String query ="from PartidaOliva as pao" +					
			" where pao.id is not null " +				
			((establimentId != null) ? "and pao.zona.establiment.id = :establimentId " : "") +
			((seleccio != null) ? "and pao.id in (:seleccio) " : "") +
			((zonaId != null) ? "and pao.zona.id = :zonaId " : "") +
			((senseZona) ? "and pao.zona.id is null " : "");
			if (valid != null && valid.booleanValue()) query += "and pao.valid = true ";
			if (valid != null && !valid.booleanValue()) query += "and pao.valid = false ";

//			Query q = getHibernateTemplate().createQuery(query);
//			if (seleccio != null)
//				q.setParameterList("seleccio", seleccio);
//			if (zonaId != null)
//				q.setLong("zonaId", zonaId.longValue());
//			if (establimentId != null)
//				q.setLong("establimentId", establimentId.longValue());
//			c = q.list();

			int numParams = 0;
			if (seleccio != null) numParams++;
			if (zonaId != null) numParams++;
			if (establimentId != null) numParams++;
			String[] paramNames = new String[numParams];
			Object[] values = new Object[numParams];

			int numParam = -1;
			if (seleccio != null) {
				numParam++;
				paramNames[numParam] = "seleccio";
				values[numParam] = seleccio;
			}
			if (zonaId != null) {
				numParam++;
				paramNames[numParam] = "zonaId";
//				values[numParam] = zonaId.longValue();
				values[numParam] = zonaId;
			}
			if (establimentId != null) {
				numParam++;
				paramNames[numParam] = "establimentId";
//				values[numParam] = establimentId.longValue();
				values[numParam] = establimentId;
			}
			c = getHibernateTemplate().findByNamedParam(query, paramNames, values);

			// Ordena els resltats segons l'ordre de la selecció
			if (seleccio != null) {
				ArrayList infoOrdenat = new ArrayList();
				for (int nsel = 0; nsel < seleccio.length; nsel++) {
					Long cid = (Long)seleccio[nsel];
					for (Iterator it = c.iterator(); it.hasNext();) {
						PartidaOliva info = (PartidaOliva)it.next();
						if (((Long)info.getId()).longValue() == cid.longValue()) {
							infoOrdenat.add(info);
							break;
						}
					}
				}
				c = infoOrdenat;
			}
		} catch (HibernateException ex) {
			logger.error("findInfo failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findInfo ini");
		return c;
	}


	/**
	 * 
	 * @param seleccio
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection findInfo(Object[] seleccio, Boolean valid) throws InfrastructureException {
		Collection c = null;
		try {
			logger.debug("findInfo ini");
			if (seleccio != null) {
				String q = "from PartidaOliva as pao where pao.id in (:seleccio) ";
				if (valid != null && valid.booleanValue()) q += "and pao.valid = true ";
				if (valid != null && !valid.booleanValue()) q += "and pao.valid = false ";
				c = getHibernateTemplate().findByNamedParam(q, "seleccio", seleccio);
				// Ordena els resltats segons l'ordre de la selecció
				ArrayList infoOrdenat = new ArrayList();
				for (int nsel = 0; nsel < seleccio.length; nsel++) {
					Long cid = (Long)seleccio[nsel];
					for (Iterator it = c.iterator(); it.hasNext();) {
						PartidaOliva info = (PartidaOliva)it.next();
						if (((Long)info.getId()).longValue() == cid.longValue()) {
							infoOrdenat.add(info);
							break;
						}
					}
				}
				c = infoOrdenat;
			}
		} catch (HibernateException ex) {
			logger.error("findInfo failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findInfo ini");
		return c;
	}
	
	/**
	 * @return
	 * @throws InfrastructureException
	 */
	public List findByOlivicultorAndEstabliment(Long idOlivicultor, Long idEstabliment, Boolean valid) throws InfrastructureException {
		List partides;
		try {
			logger.debug("findByEstabliment ini");
			String q = "from PartidaOliva pao " +
			"where pao.zona.establiment.id = " + idEstabliment + 
			" and  pao.olivicultor.id = " + idOlivicultor;
			if (valid != null && valid.booleanValue()) q += "and pao.valid = true ";
			if (valid != null && !valid.booleanValue()) q += "and pao.valid = false ";
			q += "order by pao.id";
			partides = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("findByEstabliment failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findByEstabliment fin");
		return partides;
	}

	/**
	 * @return
	 * @throws InfrastructureException
	 */
	public List findByOlivicultorEntreDatesAndEstabliment(Date finicio, Date ffin, Long idOlivicultor, Long idEstabliment, Boolean valid) throws InfrastructureException {
		List partides;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String fi = df.format(finicio);
		String ff = df.format(ffin);
		try {
			logger.debug("findByEstabliment ini");
			String q = "from PartidaOliva pao " +
			"where pao.zona.establiment.id = " + idEstabliment + 
			" and  pao.data >= '"+fi+"' and pao.data <= '"+ff+"'" +
			" and  pao.olivicultor.id = " + idOlivicultor;
			if (valid != null && valid.booleanValue()) q += "and pao.valid = true ";
			if (valid != null && !valid.booleanValue()) q += "and pao.valid = false ";
			q += "order by pao.id";
			partides = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("findByEstabliment failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findByEstabliment fin");
		return partides;
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