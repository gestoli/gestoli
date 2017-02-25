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
import es.caib.gestoli.logic.model.ElaboracioOliva;


/**
 * Home object for domain model class ElaboracioOliva
 * @see es.caib.gestoli.logic.model.ElaboracioOliva
 * @author Juan Carlos GarcÃƒÂ­a
 */
public class ElaboracioOlivaDao {
	private static Logger logger = Logger.getLogger(ElaboracioOlivaDao.class);
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
			// Ponemos where 1 = 1 para que no nos de error cuando se aÃƒÂ±ada el and si se aÃƒÂ±ade.
			String q = "from ElaboracioOliva as ela where 1 = 1 ";
			if (valid != null && valid.booleanValue()) q += "and ela.valid = true ";
			if (valid != null && !valid.booleanValue()) q += "and ela.valid = false ";
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
	public ElaboracioOliva getById(Long id, Boolean valid) throws InfrastructureException {
		List elaboraciones;
		try {
			logger.debug("getById ini");
			//elaboracio = (Elaboracio)getHibernateTemplate().load(Elaboracio.class, id);	
			String q = "from ElaboracioOliva as ela where ela.id = " + id + " ";
			if (valid != null && valid.booleanValue()) q += "and ela.valid = true ";
			if (valid != null && !valid.booleanValue()) q += "and ela.valid = false ";
			elaboraciones = getHibernateTemplate().find(q);
			if (elaboraciones.size() > 0) {
				return (ElaboracioOliva)elaboraciones.get(0);
			}
			
			
		} catch (HibernateException ex) {
			logger.error("getById failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getById fin");
		return null;
	}
	
	/**
	 * Recupera el ElaboracioOliva por la traza 
	 * @return
	 * @throws InfrastructureException
	 */
	public ElaboracioOliva findByTraza(Long idTraza, Boolean valid) throws InfrastructureException {
		List col;
		ElaboracioOliva elaboracio = null;
		try {
			logger.debug("findByTraza ini");
			String q = "from ElaboracioOliva as ela where ela.traza.id=" + idTraza + " ";
			if (valid != null && valid.booleanValue()) q += " and ela.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and ela.valid = false ";
			col = getHibernateTemplate().find(q);
			if(col!= null && col.size()>0){
				elaboracio = (ElaboracioOliva)col.get(0);
			}
		} catch (HibernateException ex) {
			logger.error("findByTraza failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findByTraza fin");
		return elaboracio;
	}
	
	/**
	 * Devuelve el objeto que tiene el id
	 * @param id
	 * @return
	 * @throws InfrastructureException
	 */
	public Integer findNumElaboracioByData(Date data, Long establimentId, Boolean valid) throws InfrastructureException {
		Integer numElaboracio = null;
		Collection col;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yy");
		String fecha = df.format(data);
		try {
			String q = "select max(ela.numeroElaboracio) from ElaboracioOliva as ela where ela.data = '"+fecha+"' and " +
			"ela.establiment = "+establimentId+" ";
			if (valid != null && valid.booleanValue()) q += "and ela.valid = true ";
			if (valid != null && !valid.booleanValue()) q += "and ela.valid = false ";
			col = getHibernateTemplate().find(q);
			while (col.iterator().hasNext()){
				numElaboracio = (Integer)col.iterator().next();
				break;
			}
		} catch (HibernateException ex) {
			logger.error("findNumElaboracioByData failed", ex);
			throw new InfrastructureException(ex);
		}

		if(numElaboracio != null){
			numElaboracio = Integer.valueOf(String.valueOf(numElaboracio.intValue() + 1));
		}else{
			numElaboracio = Integer.valueOf(String.valueOf(1));
		}
		logger.debug("findNumElaboracioByData fin");
		return numElaboracio;
	}

	
	/**
	 * Actualiza un registro
	 * @param elaboracio
	 * @throws InfrastructureException
	 */
	public void makePersistent(ElaboracioOliva elaboracio) throws InfrastructureException {
		try {
			logger.debug("makePersistent ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().saveOrUpdate(elaboracio);
			getHibernateTemplate().flush();
			logger.debug("makePersistent fin");
		} catch (HibernateException ex) {
			logger.error("makePersistent failed", ex);
			throw new InfrastructureException(ex);
		}
	}

	
	/**
	 * Actualiza un registro
	 * @param elaboracio
	 * @param session
	 * @throws InfrastructureException
	 */
	public void makePersistent(ElaboracioOliva elaboracio, Session session) throws InfrastructureException {
		try {
			logger.debug("makePersistent ini");
			session.setFlushMode(FlushMode.ALWAYS);
			session.saveOrUpdate(elaboracio);
			session.flush();
			logger.debug("makePersistent fin");
		} catch (HibernateException ex) {
			logger.error("makePersistent failed", ex);
			throw new InfrastructureException(ex);
		}
	}


	/**
	 * Borra un registro
	 * @param elaboracio
	 * @throws InfrastructureException
	 */
	public void makeTransient(ElaboracioOliva elaboracio)
	throws InfrastructureException {
		try {
			logger.debug("makeTransient ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().delete(elaboracio);
			getHibernateTemplate().flush();
			logger.debug("makeTransient fin");
		} catch (HibernateException ex) {
			logger.error("makeTransient failed", ex);
			throw new InfrastructureException(ex);
		}
	}


	/**
	 * Devuelve elaboraciones de aceite por establecimiento y entre 2 fechas
	 * @param 
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection getElaboracionesEntreDiasEnEstablecimiento(Date finicio, Date ffin, Long estId, boolean asc, Boolean valid) throws InfrastructureException {
		Collection col;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String fi = df.format(finicio);
		String ff = df.format(ffin);

		try {
			logger.debug("getElaboracionesEntreDiasEnEstablecimiento fin");
			String q = "select distinct pel.elaboracio from PartidaElaboracio pel where pel.elaboracio.data >= '" + fi + "' and pel.elaboracio.data <= '" + ff + "' and pel.partida.zona.establiment.id = " + estId + " ";
			if (valid != null && valid.booleanValue()) q += "and pel.elaboracio.valid = true ";
			if (valid != null && !valid.booleanValue()) q += "and pel.elaboracio.valid = false ";
			if (asc) {
				q += " order by pel.elaboracio.data asc, pel.elaboracio.numeroElaboracio asc";
			} else {
				q += " order by pel.elaboracio.data desc, pel.elaboracio.numeroElaboracio desc";
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
	 * Devuelve elaboraciones de aceite por establecimiento y entre 2 fechas
	 * @param 
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection getRegistroElaboracionesEntreDiasEnEstablecimiento(Date finicio, Date ffin, Long estId, boolean asc, Boolean valid) throws InfrastructureException {
		Collection col;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String fi = df.format(finicio);
		String ff = df.format(ffin);

		try {
			logger.debug("getElaboracionesEntreDiasEnEstablecimiento fin");
			String q = "select pel from PartidaElaboracio pel where pel.elaboracio.data >= '" + fi + "' and pel.elaboracio.data <= '" + ff + "' and pel.partida.zona.establiment.id = " + estId + " ";
			if (valid != null && valid.booleanValue()) q += "and pel.elaboracio.valid = true ";
			if (valid != null && !valid.booleanValue()) q += "and pel.elaboracio.valid = false ";
			if (asc) {
				q += " order by pel.elaboracio asc";
			} else {
				q += " order by pel.elaboracio desc";
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
	 * Devuelve elaboraciones de aceite por establecimiento y entre 2 fechas
	 * @param 
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection getElaboracionesByUsuario(Long campanya,Long usuId, Boolean valid) throws InfrastructureException {
		Collection col;
		
		try {
			logger.debug("getElaboracionesByUsuario fin");
			String q = "select distinct(pao.elaboracio) from PartidaOliva pao where" +
					   " pao.zona.establiment.campanya.id = "+ campanya + " ";
					   if(usuId!= null){
						   q+= " and pao.zona.establiment.usuari.id = "+ usuId + " " ;
					   }
						if (valid != null && valid.booleanValue()) q += "and pao.elaboracio.valid = true ";
						if (valid != null && !valid.booleanValue()) q += "and pao.elaboracio.valid = false ";
					   q+=" order by pao.elaboracio.data desc, pao.elaboracio.numeroElaboracio desc";
			col = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("getElaboracionesByUsuario failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getElaboracionesByUsuario fin");
		return col;
	}
	
	/**
	 * Devuelve elaboraciones de aceite por establecimiento y entre 2 fechas
	 * @param 
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection getElaboracionesByEstablecimiento(Long campanya,Long establimentId, Boolean valid) throws InfrastructureException {
		Collection col;
		
		try {
			logger.debug("getElaboracionesByUsuario fin");
			String q = "select distinct(pao.elaboracio) from PartidaOliva pao where" +
					   " pao.zona.establiment.campanya.id = "+ campanya + " ";
					   if(establimentId!= null){
						   q+= " and pao.zona.establiment.id = "+ establimentId + " " ;
					   }
						if (valid != null && valid.booleanValue()) q += "and pao.elaboracio.valid = true ";
						if (valid != null && !valid.booleanValue()) q += "and pao.elaboracio.valid = false ";
					   q+=" order by pao.elaboracio.data desc, pao.elaboracio.numeroElaboracio desc";
			
			System.out.println("CONSULTA:"+q);		   
			col = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("getElaboracionesByUsuario failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getElaboracionesByUsuario fin");
		return col;
	}
	
	/**
	 * Devuelve elaboraciones de aceite por establecimiento y entre 2 fechas
	 * @param 
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection getElaboracionesByEstablecimiento(Long establimentId, Boolean valid) throws InfrastructureException {
		Collection col;
		
		try {
			logger.debug("getElaboracionesByUsuario fin");
			String q = "select distinct(pao.elaboracio) from PartidaOliva pao where" +
					   " pao.zona.establiment.id = "+ establimentId + " ";
					  
						if (valid != null && valid.booleanValue()) q += "and pao.elaboracio.valid = true ";
						if (valid != null && !valid.booleanValue()) q += "and pao.elaboracio.valid = false ";
					   q+=" order by pao.elaboracio.data desc, pao.elaboracio.numeroElaboracio desc";
			col = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("getElaboracionesByUsuario failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getElaboracionesByUsuario fin");
		return col;
	}
	

	
	public ElaboracioOliva getElaboracioByPartidaOliva(Long idPartida, Boolean valid) {
		List col;
		try {
			logger.debug("getElaboracioByPartidaOliva fin");
			String q = 	"select pao.elaboracio " +
						"from PartidaOliva pao " +
						"where pao.id = "+ idPartida + " ";
						if (valid != null && valid.booleanValue()) q += "and pao.elaboracio.valid = true ";
						if (valid != null && !valid.booleanValue()) q += "and pao.elaboracio.valid = false ";
					   q+=" order by pao.elaboracio.data desc, pao.elaboracio.numeroElaboracio desc";
			col = getHibernateTemplate().find(q);
			if(col!= null && col.size()>0){
				return (ElaboracioOliva)col.get(0);
			}
		} catch (HibernateException ex) {
			logger.error("getElaboracioByPartidaOliva failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getElaboracioByPartidaOliva fin");
		return null;
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
