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
import es.caib.gestoli.logic.model.Analitica;


/**
 * Home object for domain model class Analitica
 * @see es.caib.gestoli.logic.model.Analitica
 * 
 */
public class AnaliticaDao {
	private static Logger logger = Logger.getLogger(AnaliticaDao.class);
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
			String q = "from Analitica ana where 1 = 1 ";
			if (valid != null && valid.booleanValue()) q += " and ana.analisiFisicoQuimicValid = true ";
			if (valid != null && !valid.booleanValue()) q += " and ana.analisiFisicoQuimicValid = false ";
			q += " order by ana.id";
			col = getHibernateTemplate().find(q); 
		} catch (HibernateException ex) {
			logger.error("findAll failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findAll fin");
		return col; 

	}
	
	/**
	 * Recupera todos los registros entre dos fechas
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection findAllEntreDates(Date dataInici, Date dataFi, Boolean valid) throws InfrastructureException {
		Collection col;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String di = sdf.format(dataInici);
			String df = sdf.format(dataFi);
			
			logger.debug("findAllEntreDates ini");
			// Ponemos where 1 = 1 para que no nos de error cuando se añada el and si se añade.
			String q = "from Analitica ana where ana.data >= '"+ di +"' and ana.data <= '"+ df +"'";
			if (valid != null && valid.booleanValue()) q += " and ana.analisiFisicoQuimicValid = true ";
			if (valid != null && !valid.booleanValue()) q += " and ana.analisiFisicoQuimicValid = false ";
			q += " order by ana.id";
			col = getHibernateTemplate().find(q); 
		} catch (HibernateException ex) {
			logger.error("findAllEntreDates failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findAllEntreDates fin");
		return col; 

	}


	/**
	 * Devuelve el objeto que tiene el id
	 * @param id
	 * @return
	 * @throws InfrastructureException
	 */
	public Analitica getById(Long id) throws InfrastructureException {
		List analiticas;
		try {
			logger.debug("getById ini");
			String q = "from Analitica as ana where ana.id = " + id;
			analiticas = getHibernateTemplate().find(q);
			if (analiticas.size() > 0) {
				logger.debug("getById fin");
				return (Analitica)analiticas.get(0);
			}
		} catch (HibernateException ex) {
			logger.error("getById failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getById fin");
		return null;		

	}


	/**
	 * Retorna un llista de totes les Analiticas associades amb
	 * un deposito.
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public List findByDiposit(Long dipositId) throws InfrastructureException {
		List analiticas;
		try {
			logger.debug("findByDiposit ini");
			// JUAN CARLOS: Select anterior. Solo mostraba analíticas por depósito y campaña. Se deja comentada por si sólo quieren consultar las analíticas por campanya 
			// y no por la vida del depósito.
//			String q = "from Analitica ana " +
//			"where ana.diposit.id = " + dipositId;
			
			String q = "from 	Analitica ana " +
					   "where 	ana.diposit.idOriginal in " +
					   "		(select dip.idOriginal " +
					   "		 from 	Diposit dip " +
					   "		 where 	dip.id = " + dipositId + ") " +
					   " and 	ana.analisiFisicoQuimicValid = true " +
					   "order by ana.data desc";
			
			analiticas = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("findByDiposit failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findByDiposit fin");
		return analiticas;
	}
	
	/**
	 * Retorna un llista de totes les Analiticas associades amb
	 * un deposito.
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public List findByEstabliment(Long establimentId) throws InfrastructureException {
		List analiticas;
		try {
			logger.debug("findByDiposit ini");
			// JUAN CARLOS: Select anterior. Solo mostraba analíticas por depósito y campaña. Se deja comentada por si sólo quieren consultar las analíticas por campanya 
			// y no por la vida del depósito.
//			String q = "from Analitica ana " +
//			"where ana.diposit.id = " + dipositId;
			
			String q = "from Analitica ana " +
					   "where ana.establiment.idOriginal in (select est.idOriginal from Establiment est where est.id = " + establimentId + ") order by ana.data desc";
			
			analiticas = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("findByDiposit failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findByDiposit fin");
		return analiticas;
	}
	
	public List findByEstabliment(Long establimentId, Boolean valid) throws InfrastructureException {
		List analiticas;
		try {
			logger.debug("findByDiposit ini");
			// JUAN CARLOS: Select anterior. Solo mostraba analíticas por depósito y campaña. Se deja comentada por si sólo quieren consultar las analíticas por campanya 
			// y no por la vida del depósito.
//			String q = "from Analitica ana " +
//			"where ana.diposit.id = " + dipositId;
			
			String q = "from Analitica ana " +
					   "where " +
					   "	ana.establiment.idOriginal in (select est.idOriginal from Establiment est where est.id = " + establimentId + ") ";
					if (valid != null && valid.booleanValue()) q += " and ana.analisiFisicoQuimicValid = true ";
					if (valid != null && !valid.booleanValue()) q += " and ana.analisiFisicoQuimicValid = false ";
					q += " order by ana.data desc";
			
			analiticas = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("findByDiposit failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findByDiposit fin");
		return analiticas;
	}
	
	/**
	 * Retorna un llista de totes les Analiticas associades amb
	 * un deposito realitzades entre dues dates.
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public List findByEstablimentEntreDates(Date dataInici, Date dataFi, Long establimentId, Boolean valid) throws InfrastructureException {
		List analiticas;
		try {
			logger.debug("findByEstablimentEntreDates ini");
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String di = sdf.format(dataInici);
			String df = sdf.format(dataFi);
			
			String q = "from Analitica ana " +
					   "where " +
					   "	ana.establiment.idOriginal in (select est.idOriginal from Establiment est where est.id = " + establimentId + ") " +
					   "and ana.data >= '"+ di +"' and ana.data <= '"+ df +"'";
					if (valid != null && valid.booleanValue()) q += " and ana.analisiFisicoQuimicValid = true ";
					if (valid != null && !valid.booleanValue()) q += " and ana.analisiFisicoQuimicValid = false ";
					q += " order by ana.data desc";
			
			analiticas = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("findByEstablimentEntreDates failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findByEstablimentEntreDates fin");
		return analiticas;
	}
	
	/**
	 * Recupera el Analitica por la traza 
	 * @return
	 * @throws InfrastructureException
	 */
	public Analitica findByTraza(Long idTraza) throws InfrastructureException {
		List col;
		Analitica analitica = null;
		try {
			logger.debug("findByTraza ini");
			String q = "from Analitica as ana where ana.traza.id="+idTraza;
			col = getHibernateTemplate().find(q);
			if(col!= null && col.size()>0){
				analitica = (Analitica)col.get(0);
			}
		} catch (HibernateException ex) {
			logger.error("findByTraza failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findByTraza fin");
		return analitica;
	}
	
	/**
	 * Retorna true si existen analiticas asociadas a depositos
	 * 
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public boolean existenAnaliticasAsociadasDeposito(Long idDiposit) throws InfrastructureException {
		List analitica;
		try {
			logger.debug("existenAnaliticasAsociadasDeposito ini");
			String q = "select count(ana.id) from Analitica ana " +
			"where ana.diposit.id = " + idDiposit + " ";
			analitica = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("existenAnaliticasAsociadasDeposito failed", ex);
			throw new InfrastructureException(ex);
		}
		if (analitica != null && analitica.size()> 0 && ((Long)analitica.get(0)).intValue() > 0) {
			logger.debug("existenAnaliticasAsociadasDeposito fin");
			return true;
		}
		logger.debug("existenAnaliticasAsociadasDeposito fin");
		return false;
	}

	/**
	 * Actualiza un registro
	 * @param analitica
	 * @throws InfrastructureException
	 */
	public void makePersistent(Analitica analitica) throws InfrastructureException {
		try {
			logger.debug("makePersistent ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().saveOrUpdate(analitica);
			getHibernateTemplate().flush();
			logger.debug("makePersistent fin");
		} catch (HibernateException ex) {
			logger.error("makePersistent failed", ex);
			throw new InfrastructureException(ex);
		}
	}


	/**
	 * Actualiza un registro
	 * @param analitica
	 * @param session
	 * @throws InfrastructureException
	 */
	public void makePersistent(Analitica analitica, Session session) throws InfrastructureException {
		try {
			logger.debug("makePersistent ini");
			session.setFlushMode(FlushMode.ALWAYS);
			session.saveOrUpdate(analitica);
			session.flush();
			logger.debug("makePersistent fin");
		} catch (HibernateException ex) {
			logger.error("makePersistent failed", ex);
			throw new InfrastructureException(ex);
		}
	}


	/**
	 * Borra un registro
	 * @param Analitica
	 * @throws InfrastructureException
	 */
	public void makeTransient(Analitica analitica)
	throws InfrastructureException {
		try {
			logger.debug("makeTransient ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().delete(analitica);
			getHibernateTemplate().flush();
			logger.debug("makeTransient fin");
		} catch (HibernateException ex) {
			logger.error("makeTransient failed", ex);
			throw new InfrastructureException(ex);
		}
	}


	/*
	 * Retorna un llista de tots els Analiticas
	 * que tenen un id que està a dins la llista que se passa com a
	 * paràmetre i que siguin de tipus <i>partida de aceite</i>.
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
				String query ="from Analitica as ana" +					
				" where ana.id in (:seleccio) " ;						

//				Query q = getHibernateTemplate().createQuery(query);
//				q.setParameterList("seleccio", seleccio);								
//				c = q.list();
				c = getHibernateTemplate().findByNamedParam(query, "seleccio", seleccio);

				// Ordena els resltats segons l'ordre de la selecció
				ArrayList infoOrdenat = new ArrayList();
				for (int nsel = 0; nsel < seleccio.length; nsel++) {
					Long cid = (Long)seleccio[nsel];
					for (Iterator it = c.iterator(); it.hasNext();) {
						Analitica info = (Analitica)it.next();
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
	 * Devuelve la ultima analitica que se hizo ha un deposito
	 * @param 
	 * @return
	 * @throws InfrastructureException
	 */
	public Analitica findUltimaByDeposito(Long dipId) throws InfrastructureException {
		List analiticas;
		try {
			logger.debug("findUltimaByDeposito ini");
			String q = "from Analitica ana where " +
			"ana.diposit.id = "+ dipId +" and ana.analisiFisicoQuimicValid = true " +
			"order by ana.data desc, ana.id desc";

			analiticas = getHibernateTemplate().find(q);
			if (analiticas.size() > 0) {
				logger.debug("findUltimaByDeposito fin");
				return (Analitica) analiticas.get(0);
			}
		} catch (HibernateException ex) {
			logger.error("findUltimaByDeposito failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findUltimaByDeposito fin");
		return null;
	}
	
	/**
	 * Devuelve la ultima analitica que se hizo a un deposito
	 * @param 
	 * @return
	 * @throws InfrastructureException
	 */
	public Analitica findUltimaByPartida(Long parId) throws InfrastructureException {
		List analiticas;
		try {
			logger.debug("findUltimaByPartida ini");
			String q = "from Analitica ana where " +
			"ana.partidaOli.id = "+ parId +" and ana.analisiFisicoQuimicValid = true " +
			"order by ana.data desc, ana.id desc";

			analiticas = getHibernateTemplate().find(q);
			if (analiticas.size() > 0) {
				logger.debug("findUltimaByPartida fin");
				return (Analitica) analiticas.get(0);
			}
		} catch (HibernateException ex) {
			logger.error("findUltimaByPartida failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findUltimaByPartida fin");
		return null;
	}

	
	
	/**
	 * Devuelve las analiticas filtradas por temporada/establecimiento/validez a partir de una fecha inicial
	 * @param 
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection getAnaliticasByDesqualificatEstablimentData (Long establimentId, Date dataInici, Date dataFi, Boolean aOliDesqualificat) throws InfrastructureException {
		
		Collection col=null;
		
		try{
			logger.debug("getAnaliticasByDesqualificatEstablimentData ini");
	
			String q = "from Analitica ana where ana.analisiFisicoQuimicValid=true ";
			q += "and ana.varietatOli.id " + (aOliDesqualificat ? "!=" : "=") + " 1";
			
			if (establimentId!=null){
				q += "and ana.establiment.id="+establimentId+" ";
			}
			
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			if(dataInici != null){
				String inici = df.format(dataInici);
				q += "and  ana.data >= '"+inici+"' ";
			}
			if(dataFi != null){
				String fi = df.format(dataFi);
				q += "and  ana.data <= '"+fi+"' ";
			}
			
			q+= " order by ana.id";
			
			col=getHibernateTemplate().find(q);
	
			logger.debug("getAnaliticasByDesqualificatEstablimentData fin");
			
		}catch (HibernateException ex) {
			logger.error("getAnaliticasByDesqualificatEstablimentData failed", ex);
			throw new InfrastructureException(ex);
		}
	
		return col;
		
	}
	
	
	/**
	 * Devuelve verdadero si ha sido qualificado por una analitica anterior
	 * @param 
	 * @return
	 * @throws InfrastructureException
	 */
	public boolean entradaDepositoConAnaliticaAnteriorQueLoQualifica (Long analiticaId) throws InfrastructureException {
		
		try{
			logger.debug("entradaDepositoConAnaliticaAnteriorQueLoQualifica ini");
/*
			String q = "from Analitica ana where ana.desqualificatDiposit='"+aOliDesqualificat+"'";
			
			if (establimentId!=null){
				q+=" and ana.establiment.id="+establimentId;
			}
			
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			if(dataInici != null){
				String fi = df.format(dataInici);
				q += " and  ana.data >= '"+fi+"' ";
			}
			
			q+= " order by ana.id";
			
			col=getHibernateTemplate().find(q);
	*/
			logger.debug("entradaDepositoConAnaliticaAnteriorQueLoQualifica fin");
			
		}catch (HibernateException ex) {
			logger.error("entradaDepositoConAnaliticaAnteriorQueLoQualifica failed", ex);
			throw new InfrastructureException(ex);
		}
	
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