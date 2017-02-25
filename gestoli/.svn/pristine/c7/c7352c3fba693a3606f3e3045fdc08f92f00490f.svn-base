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
import es.caib.gestoli.logic.model.PartidaFonoll;


/**
 * 
 */
public class PartidaFonollDao {
	private static Logger logger = Logger.getLogger(PartidaFonollDao.class);
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
			String q = "from PartidaFonoll as paf where 1 = 1 ";
			if (valid != null && valid.booleanValue()) q += "and paf.valid = true ";
			if (valid != null && !valid.booleanValue()) q += "and paf.valid = false ";
			q += "order by paf.id";
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
	public List findByEstabliment(Long establimentId, Boolean valid) throws InfrastructureException {
		List partides;
		try {
			logger.debug("findByEstabliment ini");
			String q = "from PartidaFonoll paf " +
			"where paf.establiment.id = " + establimentId;
			if (valid != null && valid.booleanValue()) q += "and paf.valid = true ";
			if (valid != null && !valid.booleanValue()) q += "and paf.valid = false ";
			q += "order by paf.id";
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
			
			String q = "from PartidaFonoll paf " +
			"where paf.data >= '"+ di +"' and paf.data <= '"+ df +"' and paf.establiment.id = " + establimentId;
			if (valid != null && valid.booleanValue()) q += "and paf.valid = true ";
			if (valid != null && !valid.booleanValue()) q += "and paf.valid = false ";
			q += "order by paf.id";
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
	public PartidaFonoll findByTraza(Long trazaId, Boolean valid) throws InfrastructureException {
		List list;
		PartidaFonoll partida = null;
		try {
			logger.debug("findByTraza ini");
			String q = "from PartidaFonoll paf " +
			"where paf.traza.id = " + trazaId + " ";
			if (valid != null && valid.booleanValue()) q += "and paf.valid = true ";
			if (valid != null && !valid.booleanValue()) q += "and paf.valid = false ";
			list = getHibernateTemplate().find(q);
			
			if (list != null && list.size() > 0) {
				partida = (PartidaFonoll) list.get(0);
			}
		} catch (HibernateException ex) {
			logger.error("findByTraza failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findByTraza fin");
		return partida;
	}
	

	/**
	 * Actualiza un registro
	 * @param PartidaFonoll
	 * @throws InfrastructureException
	 */
	public void makePersistent(PartidaFonoll PartidaFonoll) throws InfrastructureException {
		try {
			logger.debug("makePersistent ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			if(PartidaFonoll.getId()!= null){
				getHibernateTemplate().saveOrUpdate(PartidaFonoll);
			}else{
				getHibernateTemplate().save(PartidaFonoll);
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
	 * @param PartidaFonoll
	 * @throws InfrastructureException
	 */
	public void makePersistent(PartidaFonoll PartidaFonoll, Session session) throws InfrastructureException {
		try {
			logger.debug("makePersistent ini");
			session.setFlushMode(FlushMode.ALWAYS);
			if(PartidaFonoll.getId()!= null){
				session.saveOrUpdate(PartidaFonoll);
			}else{
				session.save(PartidaFonoll);
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
	 * @param PartidaFonoll
	 * @throws InfrastructureException
	 */
	public void makeTransient(PartidaFonoll PartidaFonoll) throws InfrastructureException {
		try {
			logger.debug("makeTransient ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().delete(PartidaFonoll);
			getHibernateTemplate().flush();
		} catch (HibernateException ex) {
			logger.error("makeTransient failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("makeTransient fin");
	}


//	/**
//	 * Borra un registro
//	 * @param PartidaFonoll
//	 * @throws InfrastructureException
//	 */
//	public void makeTransient(PartidaFonoll PartidaFonoll, Session session) throws InfrastructureException {
//		try {
//			session.delete(PartidaFonoll);
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
	public PartidaFonoll getById(Long id) throws InfrastructureException {
		PartidaFonoll PartidaFonoll;
		try {
			logger.debug("getById ini");
			PartidaFonoll = (PartidaFonoll)getHibernateTemplate().load(PartidaFonoll.class, id);	
			logger.debug("getById fin");
		} catch (HibernateException ex) {
			logger.error("getById failed", ex);
			throw new InfrastructureException(ex);
		}
		return PartidaFonoll;
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
			String q = "select max(paf.numeroEntrada) from PartidaFonoll as paf " +
			"where paf.data = '"+fecha+"' and paf.establiment.id = "+ establimentId + " ";
			if (valid != null && valid.booleanValue()) q += "and paf.valid = true ";
			if (valid != null && !valid.booleanValue()) q += "and paf.valid = false ";
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


	/**
	 * Devuelve partidas de oliva por establecimiento y entre 2 fechas
	 * @param 
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection getPartidasEntreDiasEnEstablecimiento(Date finicio, Date ffin, Long estId, Boolean valid) throws InfrastructureException {
		Collection col;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String fi = df.format(finicio);
		String ff = df.format(ffin);
		try {
			logger.debug("getPartidasEntreDiasEnEstablecimiento ini");
			String q = "";
			if(estId != null){
				q = "from PartidaFonoll as paf where paf.data >= '"+fi+"' and paf.data <= '"+ff+"' and paf.establiment.id = "+ estId +" ";
			}else{
				q = "from PartidaFonoll as paf where paf.data >= '"+fi+"' and paf.data <= '"+ff+"' ";
			}			
			if (valid != null && valid.booleanValue()) q += "and paf.valid = true ";
			if (valid != null && !valid.booleanValue()) q += "and paf.valid = false ";
			q += "order by paf.data desc ,paf.numeroEntrada desc";
			col = getHibernateTemplate().find(q);
			logger.debug("getPartidasEntreDiasEnEstablecimiento fin");
		} catch (HibernateException ex) {
			logger.error("getPartidasEntreDiasEnEstablecimiento failed", ex);
			throw new InfrastructureException(ex);
		}
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