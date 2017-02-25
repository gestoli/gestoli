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
import es.caib.gestoli.logic.model.Bota;
import es.caib.gestoli.logic.model.ElaboracioOliva;


/**
 * Home object for domain model class Establiment
 * @see es.caib.gestoli.logic.model.Establiment
 * 
 */
public class BotaDao {
	private static Logger logger = Logger.getLogger(BotaDao.class);
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
			col = getHibernateTemplate().find("from Bota as bot order by bot.id"); 
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
	public Bota getById(Long id) throws InfrastructureException {

		List botes;
		try {
			logger.debug("getById ini");
			String q = "from Bota as bot where bot.id = " + id;
			botes = getHibernateTemplate().find(q);
			if (botes.size() > 0) {
				logger.debug("getById fin");
				return (Bota)botes.get(0);
			}
		} catch (HibernateException ex) {
			logger.error("getById failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getById fin");
		return null;		
	}


	/**
	 * Retorna un llista de totes les botes associades amb
	 * un zona determinada.
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public List findActiusByZona(Long zonaId) throws InfrastructureException {
		List botes;
		try {
			logger.debug("findActiusByZona ini");
			String q = "from Bota bot " +
			"where bot.zona.id = " + zonaId + " " +
			"and bot.actiu = 'true' " +
			"order by bot.id";
			botes = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("findActiusByZona failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findActiusByZona fin");
		return botes;
	}

	
	

	/**
	 * Retorna un llista de totes les diposits associades amb
	 * un establiment.
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public List findByEstabliment(Long establimentId) throws InfrastructureException {
		List botes;
		try {
			logger.debug("findByEstabliment ini");
			String q = "select bot from Bota bot " +
			"where bot.zona.actiu = true " +
			"and bot.zona.establiment.id = " + establimentId + " " +
			"order by bot.id";
			botes = getHibernateTemplate().find(q);
			logger.debug("findByEstabliment ini");
		} catch (HibernateException ex) {
			logger.error("findByEstabliment failed", ex);
			throw new InfrastructureException(ex);
		}
		return botes;
	}
	
	
	
	/**
	 * Retorna un llista de totes les diposits associades amb
	 * un establiment en una data determinada.
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public List findByEstablimentEnData(Long establimentId, Date data) throws InfrastructureException {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		List botes;
		try {
			logger.debug("findByEstabliment ini");
			String de = df.format(data);
			String q = "from Bota bot " +
					"where bot.elaboracio.data <= '" + de + "' " +
					"and bot.actiu = true " +
					"and bot.establiment.id = " + establimentId + " " +
					"order by bot.elaboracio.data";
			botes = getHibernateTemplate().find(q);
			
			logger.debug("findByEstabliment ini");
		} catch (HibernateException ex) {
			logger.error("findByEstabliment failed", ex);
			throw new InfrastructureException(ex);
		}
		return botes;
	}
	
//	/**
//	 * Retorna un llista de totes les diposits associades amb
//	 * un establiment en una data determinada, amb una categoria.
//	 * 
//	 * @return
//	 * @throws InfrastructureException
//	 */
//	public List findByEstablimentCategoriaEnData(Long establimentId, Long categoriaId, Date data) throws InfrastructureException {
//		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
//		List diposits;
//		try {
//			logger.debug("findByEstablimentCategoriaEnData ini");
//			String de = df.format(data);
//			String q = "select distinct ed.diposit " +
//					"from EntradaBota ed " +
//					"where ed.traza.id = (select max(ed2.traza.id) " +
//									"from EntradaBota ed2 " +
//									"where ed.diposit = ed2.diposit " +
//									"and ed2.data <= '" + de + "' " +
//									"and ed2.valid = true) ";
//			if (establimentId != null) {
//				q += "and ed.establiment.id = " + establimentId + " ";
//			}
//			q += "and ed.categoriaOli.id = " + categoriaId + " " +
//					"order by ed.diposit.codiAssignat";
//			diposits = getHibernateTemplate().find(q);
//			
//			logger.debug("findByEstablimentCategoriaEnData ini");
//		} catch (HibernateException ex) {
//			logger.error("findByEstablimentCategoriaEnData failed", ex);
//			throw new InfrastructureException(ex);
//		}
//		return diposits;
//	}
	
	


	/**
	 * Retorna un llista de tots els diposits associats amb les zones
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public List findByZona(Long zonaId) throws InfrastructureException {
		List botes;
		try {
			logger.debug("findByZona ini");
			String q = "select bot from Bota bot " +
			"where bot.zona.id = " +zonaId+ " " +
			"order by bot.id";
			botes = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("findByZona failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findByZona fin");
		return botes;
	}

	/**
	 * Devuelve elaboraciones de aceite por establecimiento y entre 2 fechas
	 * @param 
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection getBotasElaboracionesEntreDiasEnEstablecimiento(Date finicio, Date ffin, Long estId, boolean asc, Boolean valid) throws InfrastructureException {
		Collection col;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String fi = df.format(finicio);
		String ff = df.format(ffin);

		try {
			logger.debug("getElaboracionesEntreDiasEnEstablecimiento fin");
			String q = "select distinct bot from Bota bot where bot.elaboracio.data >= '" + fi + "' and bot.elaboracio.data <= '" + ff + "' and bot.zona.establiment.id = " + estId + " ";
			if (valid != null && valid.booleanValue()) q += "and bot.elaboracio.valid = true ";
			if (valid != null && !valid.booleanValue()) q += "and bot.elaboracio.valid = false ";
			if (asc) {
				q += " order by bot.elaboracio asc";
			} else {
				q += " order by bot.elaboracio desc";
			}
			col = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("getElaboracionesEntreDiasEnEstablecimiento failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getElaboracionesEntreDiasEnEstablecimiento fin");
		return col;
	}

	/**
	 * Actualiza un registro
	 * @param Bota
	 * @throws InfrastructureException
	 */
	public void makePersistent(Bota bota) throws InfrastructureException {
		try {
			logger.debug("makePersistent ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().saveOrUpdate(bota);
			getHibernateTemplate().flush();
			logger.debug("makePersistent fin");
		} catch (HibernateException ex) {
			logger.error("makePersistent failed", ex);
			throw new InfrastructureException(ex);
		}
	}


	/**
	 * Actualiza un registro
	 * @param Bota
	 * @param session
	 * @throws InfrastructureException
	 */
	public void makePersistent(Bota bota, Session session) throws InfrastructureException {
		try {
			session.setFlushMode(FlushMode.ALWAYS);
			logger.debug("makePersistent ini");
			session.saveOrUpdate(bota);
			session.flush();
			logger.debug("makePersistent fin");
		} catch (HibernateException ex) {
			logger.error("makePersistent failed", ex);
			throw new InfrastructureException(ex);
		}
	}


	/**
	 * Borra un registro
	 * @param Bota
	 * @throws InfrastructureException
	 */
	public void makeTransient(Bota bota)
	throws InfrastructureException {
		try {
			logger.debug("makeTransient ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().delete(bota);
			getHibernateTemplate().flush();
			logger.debug("makeTransient fin");
		} catch (HibernateException ex) {
			logger.error("makeTransient failed", ex);
			throw new InfrastructureException(ex);
		}
	}


	/*
	 * Retorna un llista de tots els diposits d'un establiment
	 * que tenen un id que estÃƒÂ  a dins la llista que se passa com a
	 * parÃƒÂ metre i que siguin de tipus <i>partida de aceite</i>.
	 * 
	 * @param seleccio
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection findInfo(Long establimentId, Long zonaId, Object[] seleccio, boolean senseZona) throws InfrastructureException {
		Collection c = null;

		try {
			logger.debug("findInfo ini");
			String query ="from Bota as bot" +					
			" where " +
			" bot.actiu = true " +
			((establimentId != null) ? "and bot.establiment.id = :establimentId " : "") +
			((seleccio != null) ? "and bot.id in (:seleccio) " : "") +
			((zonaId != null) ? "and bot.zona.id = :zonaId " : "") +
			((senseZona) ? "and bot.zona.id is null " : "");

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
				values[numParam] = zonaId;
			}
			if (establimentId != null) {
				numParam++;
				paramNames[numParam] = "establimentId";
				values[numParam] = establimentId;
			}
			c = getHibernateTemplate().findByNamedParam(query, paramNames, values);

			// Ordena els resltats segons l'ordre de la seleccio
			if (seleccio != null) {
				ArrayList infoOrdenat = new ArrayList();
				for (int nsel = 0; nsel < seleccio.length; nsel++) {
					Long cid = (Long)seleccio[nsel];
					for (Iterator it = c.iterator(); it.hasNext();) {
						Bota info = (Bota)it.next();
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

		logger.debug("findInfo fin");
		return c;
	}


	/**
	 * 
	 * @param seleccio
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection findInfo( Object[] seleccio) throws InfrastructureException {
		Collection c = null;

		try {
			logger.debug("findInfo ini");
			if (seleccio != null) {
				String query ="from Bota as bot" +					
				" where " +
				"bot.actiu = true " +
				"and bot.id in (:seleccio) " ;						

				c = getHibernateTemplate().findByNamedParam(query, "seleccio", seleccio);

				// Ordena els resltats segons l'ordre de la selecciÃƒÂ³
				ArrayList infoOrdenat = new ArrayList();
				for (int nsel = 0; nsel < seleccio.length; nsel++) {
					Long cid = (Long)seleccio[nsel];
					for (Iterator it = c.iterator(); it.hasNext();) {
						Bota info = (Bota)it.next();
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

		logger.debug("findInfo fin");
		return c;
	}

	/**
	 * Recupera el Bota por la traza 
	 * @return
	 * @throws InfrastructureException
	 */
	public Bota findByTraza(Long idTraza, Boolean valid) throws InfrastructureException {
		List col;
		Bota bota = null;
		try {
			logger.debug("findByTraza ini");
			String q = "from Bota as bot where bot.traza.id=" + idTraza + " ";
			if (valid != null && valid.booleanValue()) q += "and bot.elaboracio.valid = true ";
			if (valid != null && !valid.booleanValue()) q += "and bot.elaboracio.valid = false ";
			col = getHibernateTemplate().find(q);
			if(col!= null && col.size()>0){
				bota = (Bota)col.get(0);
			}
		} catch (HibernateException ex) {
			logger.error("findByTraza failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findByTraza fin");
		return bota;
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
