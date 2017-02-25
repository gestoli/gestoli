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
import es.caib.gestoli.logic.model.Elaboracio;


/**
 * Home object for domain model class Elaboracio
 * @see es.caib.gestoli.logic.model.Elaboracio
 * @author Juan Carlos GarcÃƒÂ­a
 */
public class ElaboracioDao {
	private static Logger logger = Logger.getLogger(ElaboracioDao.class);
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
			String q = "from Elaboracio as ela where 1 = 1 ";
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
	public Elaboracio getById(Long id, Boolean valid) throws InfrastructureException {
		List elaboraciones;
		try {
			logger.debug("getById ini");
			//elaboracio = (Elaboracio)getHibernateTemplate().load(Elaboracio.class, id);	
			String q = "from Elaboracio as ela where ela.id = " + id + " ";
			if (valid != null && valid.booleanValue()) q += "and ela.valid = true ";
			if (valid != null && !valid.booleanValue()) q += "and ela.valid = false ";
			elaboraciones = getHibernateTemplate().find(q);
			if (elaboraciones.size() > 0) {
				return (Elaboracio)elaboraciones.get(0);
			}
			
			
		} catch (HibernateException ex) {
			logger.error("getById failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getById fin");
		return null;
	}

	
	/**
	 * Recupera el Elaboracio por la traza 
	 * @return
	 * @throws InfrastructureException
	 */
	public Elaboracio findByTraza(Long idTraza, Boolean valid) throws InfrastructureException {
		List col;
		Elaboracio elaboracio = null;
		try {
			logger.debug("findByTraza ini");
			String q = "from Elaboracio as ela where ela.traza.id=" + idTraza + " ";
			if (valid != null && valid.booleanValue()) q += " and ela.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and ela.valid = false ";
			col = getHibernateTemplate().find(q);
			if(col!= null && col.size()>0){
				elaboracio = (Elaboracio)col.get(0);
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
			String q = "select max(ela.numeroElaboracio) from Elaboracio as ela where ela.data = '"+fecha+"' and " +
			"ela.id in (select ent.elaboracio.id from EntradaDiposit ent where ent.establiment.id = "+establimentId+") ";
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
	public void makePersistent(Elaboracio elaboracio) throws InfrastructureException {
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
	public void makePersistent(Elaboracio elaboracio, Session session) throws InfrastructureException {
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
	public void makeTransient(Elaboracio elaboracio)
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
			String q = "select distinct pao.elaboracio from PartidaOliva pao where pao.elaboracio.data >= '" + fi + "' and pao.elaboracio.data <= '" + ff + "' and pao.zona.establiment.id = " + estId + " ";
			if (valid != null && valid.booleanValue()) q += "and pao.elaboracio.valid = true ";
			if (valid != null && !valid.booleanValue()) q += "and pao.elaboracio.valid = false ";
			if (asc) {
				q += " order by pao.elaboracio.data asc, pao.elaboracio.numeroElaboracio asc";
			} else {
				q += " order by pao.elaboracio.data desc, pao.elaboracio.numeroElaboracio desc";
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
	 * Devuelve la cantidad total de kilos de aceite elaborado de una temporada o entre fechas de la temporada actual
	 * @param temporadaId
	 * @param dataInici
	 * @param dataFin
	 * @return
	 * @throws InfrastructureException
	 */
	public Double getTotalOliElaborat(Long temporadaId,Date dataInici,Date dataFin,Long temporadaActual, Boolean valid) throws InfrastructureException {
		Collection col;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Double total = null;
		try {

			logger.debug("getTotalOliElaborat ini");
			String q = "select distinct pao.elaboracio from PartidaOliva pao where pao.elaboracio is not null"; 
			if(dataInici!= null || dataFin != null){
				if(dataInici != null){
					String fi = df.format(dataInici);
					q = q+ " and  pao.elaboracio.data >= '"+fi+"' ";
				}
				if(dataFin != null){
					String ff = df.format(dataFin);
					q = q+ " and  pao.elaboracio.data <= '"+ff+"' ";
				}
				//q = q+" and pao.zona.establiment.campanya.id= (select max(cam.id) from Campanya cam)";
				q = q+" and pao.zona.establiment.campanya.id= "+temporadaActual;
			} else {
				q = q+ " and pao.zona.establiment.campanya.id="+temporadaId;
			}
			if (valid != null && valid.booleanValue()) q += " and pao.elaboracio.valid = true ";
			if (valid != null && !valid.booleanValue()) q += " and pao.elaboracio.valid = false ";
			
			col = getHibernateTemplate().find(q);
			double litres = 0;
			for (Iterator it=col.iterator();it.hasNext();){
				Elaboracio elaboracion = (Elaboracio)it.next();
				if(elaboracion.getLitres()!= null){
					litres+=  elaboracion.getLitres().doubleValue();						
				}				
			}
			total = Double.valueOf(String.valueOf(litres));	
			logger.debug("getTotalOliElaborat fin");
		} catch (HibernateException ex) {
			logger.error("getTotalOliElaborat failed", ex);
			throw new InfrastructureException(ex);
		}
		return total;
	}

	/**
	 * Devuelve la cantidad total de kilos de aceite elaborado de una temporada o entre fechas de la temporada actual
	 * @param temporadaId
	 * @param dataInici
	 * @param dataFin
	 * @return
	 * @throws InfrastructureException
	 */
	public Double getTotalOliElaboratEntreDates(Date dataInici,Date dataFin) throws InfrastructureException {
		Collection col;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Double total = null;
		try {

			logger.debug("getTotalOliElaboratEntreDates ini");
			String q = "select distinct pao.elaboracio from PartidaOliva pao where pao.elaboracio is not null"; 
			if(dataInici != null){
				String fi = df.format(dataInici);
				q = q+ " and  pao.elaboracio.data >= '"+fi+"' ";
			}
			if(dataFin != null){
				String ff = df.format(dataFin);
				q = q+ " and  pao.elaboracio.data <= '"+ff+"' ";
			}
			q += " and pao.elaboracio.valid = true ";
			col = getHibernateTemplate().find(q);

			double litres = 0;
			for (Iterator it=col.iterator();it.hasNext();){
				Elaboracio elaboracion = (Elaboracio)it.next();
				if(elaboracion.getLitres()!= null){
					litres+=  elaboracion.getLitres().doubleValue();						
				}				
			}
			total = Double.valueOf(String.valueOf(litres));	
			logger.debug("getTotalOliElaboratEntreDates fin");
		} catch (HibernateException ex) {
			logger.error("getTotalOliElaboratEntreDates failed", ex);
			throw new InfrastructureException(ex);
		}
		return total;
	}
	
	
	/**
	 * Devuelve la cantidad total de kilos de aceite elaborado por mes
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection getTotalOliElaboratTafonaPerMes(Long temporadaId, Long establimentId, Boolean valid) throws InfrastructureException {
		Collection col;
		
		double [] sumas = new double[12];
		for (int i=0;i<sumas.length;i++){
			sumas[i]=0;
		}
		
		SimpleDateFormat df = new SimpleDateFormat("MM");
		
		try {

			logger.debug("getTotalOliElaboratTafonaPerMes ini");
			String q = "select distinct pao.elaboracio from PartidaOliva pao where pao.elaboracio is not null"; 
			q += " and pao.zona.establiment.campanya.id="+temporadaId;
			q += " and pao.zona.establiment.id="+establimentId + " ";
			if (valid != null && valid.booleanValue()) q += "and pao.elaboracio.valid = true ";
			if (valid != null && !valid.booleanValue()) q += "and pao.elaboracio.valid = false ";
			col = getHibernateTemplate().find(q);
			
			for (Iterator it=col.iterator();it.hasNext();){
				Elaboracio elaboracion = (Elaboracio)it.next();
				if(elaboracion.getLitres()!= null){
					Date fechaElab = elaboracion.getData();
					int indice = Integer.parseInt(df.format(fechaElab));
					sumas[indice-1]+= elaboracion.getLitres().doubleValue();				
				}				
			}
			
			col = new ArrayList();
			
			for (int i=0;i<sumas.length;i++){
				col.add(new Double(sumas[i]));
			}
			
			logger.debug("getTotalOliElaboratTafonaPerMes fin");
		} catch (HibernateException ex) {
			logger.error("getTotalOliElaboratTafonaPerMes failed", ex);
			throw new InfrastructureException(ex);
		}

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
	
	/**
	 * Devuelve elaboraciones de aceite por lote de talco y establecimiento
	 * @param 
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection getElaboracionesByLotTalc(String lTalc, Long idEstabliment, Boolean valid) {
		Collection col;
		try {
			logger.debug("getElaboracionesByLotTalc fin");
			String q = 	"select distinct(pao.elaboracio) " +
						"from PartidaOliva pao " +
						"where pao.zona.establiment.id = "+ idEstabliment + " " +
						"and pao.elaboracio.talcLot = '" + lTalc + "'";
						if (valid != null && valid.booleanValue()) q += "and pao.elaboracio.valid = true ";
						if (valid != null && !valid.booleanValue()) q += "and pao.elaboracio.valid = false ";
					   q+=" order by pao.elaboracio.data desc, pao.elaboracio.numeroElaboracio desc";
			col = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("getElaboracionesByLotTalc failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getElaboracionesByLotTalc fin");
		return col;
	}
	
	public Elaboracio getElaboracioByPartidaOliva(Long idPartida, Boolean valid) {
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
				return (Elaboracio)col.get(0);
			}
		} catch (HibernateException ex) {
			logger.error("getElaboracioByPartidaOliva failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getElaboracioByPartidaOliva fin");
		return null;
	}
	
	/**
	 * Comprova si la partida d'oli tÃƒÂ© elaboracions associats
	 * @param lid
	 * @return
	 */
	public Boolean existeixenElaboracionsAssociadesPartidesOli(Long idPartidaOli, Boolean valid) {
		List elaboracions;
		try {
			logger.debug("existeixenElaboracionsAssociadesPartidesOli ini");
			String q = "select count(ela.id) from Elaboracio ela " +
			"where ela.partidaOli.id = " + idPartidaOli + " ";
			if (valid != null && valid.booleanValue()) q += "and ela.valid = true ";
			if (valid != null && !valid.booleanValue()) q += "and ela.valid = false ";
			elaboracions = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("existeixenElaboracionsAssociadesPartidesOli failed", ex);
			throw new InfrastructureException(ex);
		}
		if (elaboracions != null && elaboracions.size()> 0 && ((Long)elaboracions.get(0)).intValue() > 0) {
			logger.debug("existeixenElaboracionsAssociadesPartidesOli fin");
			return true;
		}
		logger.debug("existeixenElaboracionsAssociadesPartidesOli fin");
		return false;
	}
	
	/**
	 * Recupera todos los lotes de talco del establecimiento
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection findLotsTalcPerEstabliment(Long establimentId, Boolean valid) throws InfrastructureException {
		Collection col;
		try {
			logger.debug("findLotsTalcPerEstabliment ini");
			String q = "select distinct ela.talcLot from Elaboracio as ela where ela.partidaOli.establiment.id = "+ establimentId + " ";
			if (valid != null && valid.booleanValue()) q += "and ela.valid = true ";
			if (valid != null && !valid.booleanValue()) q += "and ela.valid = false ";
			col = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("findLotsTalcPerEstabliment failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findLotsTalcPerEstabliment fin");
		return col;
	}
	
	public Integer getDarrerNumeroByEstabliment(Long idEstabliment, Boolean valid){
		Collection elaboracions = getElaboracionesByEstablecimiento(idEstabliment, valid);
		
		Integer numero = 0;
		
		for(Iterator it = elaboracions.iterator(); it.hasNext();){
			Elaboracio e = (Elaboracio)it.next();
			
			if(e.getAutoNumDocRendiment() > numero){
				numero = e.getAutoNumDocRendiment();
			}
		}
		return numero;
	}
	
	public Integer seguentNumeroByEstabliment(Long idEstabliment, Boolean valid){
		return getDarrerNumeroByEstabliment(idEstabliment, valid) + 1;
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
