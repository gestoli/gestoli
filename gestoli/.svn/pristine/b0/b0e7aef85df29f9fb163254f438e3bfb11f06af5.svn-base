package es.caib.gestoli.logic.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.exception.InfrastructureException;
import es.caib.gestoli.logic.model.Campanya;


/**
 * Home object for domain model class Taxa
 * @see es.caib.gestoli.logic.model.Taxa
 * @author Oriol Barnés
 */
public class CampanyaDao {
	private static Logger logger = Logger.getLogger(CampanyaDao.class);
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
			col = getHibernateTemplate().find("from Campanya as cam order by cam.id desc"); 
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
	public Long getNumeroDescomposicionsActivesCampanyaActual() throws InfrastructureException {
		Collection col;
		Long num = new Long(0);
		try {
			logger.debug("getNumeroDescomposicionsActivesCampanyaActual ini");
			String q = "select count(dpl.id) " +
			"from DescomposicioPlantacio dpl " +
			"where dpl.plantacio.actiu = true " +
			"and dpl.plantacio.finca.actiu = true " +
			"and dpl.plantacio.finca.olivicultor.altaDO = true "+
			"and dpl.plantacio.finca.olivicultor.campanya.id= "+getCampanyaActual();
			
			col = getHibernateTemplate().find(q);
			if (col.iterator().hasNext()){
				num = (Long)col.iterator().next();
			}
		} catch (HibernateException ex) {
			logger.error("getNumeroDescomposicionsActivesCampanyaActual failed", ex);
			throw new InfrastructureException(ex);
		} catch (Exception ex) {
			logger.error("EXCEPTION", ex);
		}
		logger.debug("getNumeroDescomposicionsActivesCampanyaActual fin");
		return num;
	}	


	/**
	 * Recupera todos los registros
	 * @return
	 * @throws InfrastructureException
	 */
	public Long getCampanyaActual() throws InfrastructureException {
		Collection col;
		Long campanyaId = null;
		try {
			logger.debug("getCampanyaActual ini");
			col = getHibernateTemplate().find("select max(cam.id) from Campanya cam");
			while (col.iterator().hasNext()){
				campanyaId = (Long)col.iterator().next();
				break;
			}
		} catch (HibernateException ex) {
			logger.error("getCampanyaActual failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getCampanyaActual fin");
		return campanyaId;
	}




	/**
	 * Cerca una campanya amb id
	 * @param id
	 * @return
	 * @throws InfrastructureException
	 */
	public Campanya campanyaAmbId(Integer id) throws InfrastructureException {
		Collection col;
		Campanya campanya = null;
		try {
			logger.debug("campanyaAmbId ini");
			col = getHibernateTemplate().find("from Campanya cam where cam.id = " + id);
			while (col.iterator().hasNext()){
				campanya = (Campanya)col.iterator().next();
				break;
			}
		} catch (HibernateException ex) {
			logger.error("campanyaAmbId failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("campanyaAmbId fin");
		return campanya;
	}

	public Date getDataFi(Long id) throws InfrastructureException {
		Collection campanyes = findAll();

		Long idSeguent = null;
		
		//Cerquem la id de la següent campanya on la seva data d'inici menys un dia serà la data de fi
		for(Iterator it=campanyes.iterator(); it.hasNext();){
			Campanya c = (Campanya)it.next();
			
			if(c.getId() > id && (idSeguent == null || (idSeguent != null && idSeguent > c.getId()))){
				idSeguent = c.getId();
			}
		}
		
		if(idSeguent == null){
			return null;
		} else {
			//Cerquem la data inici de la campanya seguent
			Campanya campanyaSeguent = campanyaAmbId(idSeguent.intValue());
			Date dataIniciCampanyaSeguent = campanyaSeguent.getData();
			
			//La data fi de la campanya que busquem serà la data inici de la següent campanya menys un dia
			Calendar calDataFiCampanya = Calendar.getInstance();
			calDataFiCampanya.setTime(dataIniciCampanyaSeguent);
			
			calDataFiCampanya.add(Calendar.DAY_OF_MONTH, -1);
			
			return calDataFiCampanya.getTime();
		}
		
	}

	/**
	 * Actualiza un registro
	 * @param campanya
	 * @throws InfrastructureException
	 */
	public void makePersistent(Campanya campanya) throws InfrastructureException {
		try {
			logger.debug("makePersistent ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().saveOrUpdate(campanya);
			getHibernateTemplate().flush();
			logger.debug("makePersistent fin");
		} catch (HibernateException ex) {
			logger.error("makePersistent failed", ex);
			throw new InfrastructureException(ex);
		}
	}


	/**
	 * Actualiza un registro
	 * @param campanya
	 * @throws InfrastructureException
	 */
	public void makePersistent(Campanya campanya, Session session) throws InfrastructureException {
		try {
			logger.debug("makePersistent ini");
			session.setFlushMode(FlushMode.ALWAYS);
			session.saveOrUpdate(campanya);
			session.flush();
			logger.debug("makePersistent fin");
		} catch (HibernateException ex) {
			logger.error("makePersistent failed", ex);
			throw new InfrastructureException(ex);
		}
	}


	/**
	 * Retorna true si existe otra campaña con el mismo nombre
	 * @param nom
	 * @return
	 * @throws InfrastructureException
	 */
	public boolean existeCampanyaMismoNombre(String nom) throws InfrastructureException {
		List campanyas;
		try {
			logger.debug("existeCampanyaMismoNombre ini");
			String q = "select count(cam.id) from Campanya cam " +
			"where cam.nom = '" + nom + "'";
//			Query query = HibernateUtil.getSession().createQuery(q);
//			campanyas = query.list();
			campanyas = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("existeCampanyaMismoNombre failed", ex);
			throw new InfrastructureException(ex);
		}
		if (campanyas != null && campanyas.size() > 0 && ((Long)campanyas.get(0)).intValue() > 0) {
			logger.debug("existeCampanyaMismoNombre fin");
			return true;
		}
		logger.debug("existeCampanyaMismoNombre fin");
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