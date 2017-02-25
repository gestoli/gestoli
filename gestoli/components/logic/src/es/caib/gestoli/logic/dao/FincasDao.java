package es.caib.gestoli.logic.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.exception.InfrastructureException;
import es.caib.gestoli.logic.model.Finca;


/**
 * Home object for domain model class Fincas
 * @see es.caib.gestoli.logic.model.Fincas
 * 
 */
public class FincasDao {
	private static Logger logger = Logger.getLogger(FincasDao.class);
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
			col = getHibernateTemplate().find("from Finca as finca order by finca.nom"); 
		} catch (HibernateException ex) {
			logger.error("findAll failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findAll fin");
		return col;
	}


	/**
	 * Recupera todos los registros de un olivicultor
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection findAllOlivicultor(Long idOlivicultor) throws InfrastructureException {
		Collection col;
		try {
			logger.debug("findAllOlivicultor ini");
			String q = "from Finca as finca where finca.olivicultor.id ="+idOlivicultor+" order by finca.nom";
			col = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("findAllOlivicultor failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findAllOlivicultor fin");
		return col;
	}


	/**
	 * Recupera todos los registros de un olivicultor
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection findAllIdOlivicultor(Long idOlivicultor) throws InfrastructureException {
		Collection col;
		try {
			logger.debug("findAllIdOlivicultor ini");
			String q = "select finca.id from Finca as finca where finca.olivicultor.id = "+idOlivicultor+" order by finca.id"; 
			col = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("findAllIdOlivicultor failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findAllIdOlivicultor fin");
		return col;
	}


	/**
	 * Retorna un llista de totes les finques associades amb els olivicultors
	 * @return
	 * @throws InfrastructureException
	 */
	public List findByOlivicultor(Long olivicultorId) throws InfrastructureException {
		List establiments;
		try {
			logger.debug("findByOlivicultor ini");
			String q = "select fin from Finca fin where fin.olivicultor.id = " +olivicultorId+ " order by fin.id";
			establiments = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("findByOlivicultor failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findByOlivicultor fin");
		return establiments;
	}


	/**
	 * Retorna un llista de totes les finques associades amb els olivicultors
	 * @return
	 * @throws InfrastructureException
	 */
	public List findActiusByOlivicultor(Long olivicultorId) throws InfrastructureException {
		List establiments;
		try {
			logger.debug("findActiusByOlivicultor ini");
			String q = "select fin from Finca fin where fin.olivicultor.id = " +olivicultorId+ " and fin.actiu = 'true' order by fin.id";
			establiments = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("findActiusByOlivicultor failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findActiusByOlivicultor fin");
		return establiments;
	}


	/**
	 * Retorna un llista de totes les finques associades amb els olivicultors
	 * @return
	 * @throws InfrastructureException
	 */
	public List fincasOlivicultorCercaTotsActiusConPlantacionesConDescomposicion(Long olivicultorId) throws InfrastructureException {
		List finques;
		try {
			logger.debug("fincasOlivicultorCercaTotsActiusConPlantacionesConDescomposicion ini");
//			String q = "select distinct pla.finca from Plantacio as pla" +
//			" where pla.actiu = true" +
//			" and pla.finca.actiu = true" +
//			" and pla.finca.olivicultor.id = "+olivicultorId+
//			" and pla.id in (select dpl.plantacio.id from DescomposicioPlantacio as dpl where dpl.plantacio.finca.olivicultor.id = "+olivicultorId+")";
			
			String q = "select distinct pla.finca from Plantacio as pla" +
			" where pla.actiu = true" +
			" and pla.finca.actiu = true" +
			" and pla.finca.olivicultor.id = "+olivicultorId+
			" and size(pla.descomposicioPlantacios) > 0";
			finques = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("fincasOlivicultorCercaTotsActiusConPlantacionesConDescomposicion failed", ex);
			throw new InfrastructureException(ex);
		}
		catch (Exception ex) {
			logger.error("fincasOlivicultorCercaTotsActiusConPlantacionesConDescomposicion failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("fincasOlivicultorCercaTotsActiusConPlantacionesConDescomposicion fin");
		return finques;
	}


	/**
	 * Retorna el id del olivicultor asociado a una finca
	 * @return
	 * @throws InfrastructureException
	 */
	public Long findOlivicultorDeFinca(Long idFinca) throws InfrastructureException {
		List olivicultores;
		try {
			logger.debug("findOlivicultorDeFinca ini");
			String q = "select fin.olivicultor.id from Finca fin where fin.id = " +idFinca;
			olivicultores = getHibernateTemplate().find(q);
			if (olivicultores.size() > 0) {
				logger.debug("findOlivicultorDeFinca fin");
				return (Long) olivicultores.get(0);
			}
		} catch (HibernateException ex) {
			logger.error("findOlivicultorDeFinca failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findOlivicultorDeFinca fin");
		return null;
	}


	/**
	 * Retorna true si existen fincas asociadas a olivicultores
	 * @return
	 * @throws InfrastructureException
	 */
	public boolean existenFincasAsociadasOlivicultores(Long idOlivicultor) throws InfrastructureException {
		List fincas;
		try {
			logger.debug("existenFincasAsociadasOlivicultores ini");
			String q = "select count(fin.id) from Finca fin " +
			"where fin.olivicultor.id = " + idOlivicultor + " ";
			fincas = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("existenFincasAsociadasOlivicultores failed", ex);
			throw new InfrastructureException(ex);
		}
		if (fincas != null && fincas.size()> 0 && ((Long)fincas.get(0)).intValue() > 0) {
			logger.debug("existenFincasAsociadasOlivicultores fin");
			return true;
		}
		logger.debug("existenFincasAsociadasOlivicultores fin");
		return false;
	}


	/**
	 * Devuelve el objeto que tiene el id
	 * @param id
	 * @return
	 * @throws InfrastructureException
	 */
	public Finca getById(Long id) throws InfrastructureException {
		Finca finca;
		try {
			logger.debug("getById ini");
			finca = (Finca)getHibernateTemplate().load(Finca.class, id);	
		} catch (HibernateException ex) {
			logger.error("getById failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getById fin");
		return finca;
	}


	/**
	 * Devuelve una finca de la BBDD a partir de su nombre e idOlivicultor
	 * @params nom
	 * @params idOlivicultor 
	 * @return
	 * @throws InfrastructureException
	 */
	public Long getFincaName(String nombreFinca, String idOlivicultor) throws InfrastructureException {
		List fincas;
		try {
			logger.debug("getFincaName fin");
			String q = "select finca.id from Finca as finca where finca.nom = '"+nombreFinca+"' and finca.olivicultor.id = '"+idOlivicultor+"'";
			fincas = getHibernateTemplate().find(q);
			if (fincas.size() > 0) {
				logger.debug("getFincaName fin");
				return (Long) fincas.get(0);
			}
		} catch (HibernateException ex) {
			logger.error("getFincaName failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getFincaName fin");
		return null;
	}
	
	
	
	/**
	 * Devuelve la suma de la superficie de las fincas entre 2 valores y temporada
	 * @return
	 * @throws InfrastructureException
	 */
	public Double getSuperfFincasDO(Long idTemporada, Double valorInicial, Double valorFinal) throws InfrastructureException {

		Collection col=null;
		Double res = new Double(0);
		
		try {

			logger.debug("getSuperfFincasDO ini");
			
			String q="select dpl.plantacio.finca.id, sum(dpl.superficie) from DescomposicioPlantacio dpl where dpl.plantacio.finca.olivicultor.campanya.id="+idTemporada;
			q+= " and dpl.plantacio.finca.olivicultor.altaDO=true";
			q+= " and dpl.plantacio.finca.olivicultor.cartilla=true";
			q+= " and dpl.plantacio.actiu=true and dpl.plantacio.finca.actiu=true";
			q+= " group by dpl.plantacio.finca.id";
			col = getHibernateTemplate().find(q);
			
			Iterator i = col.iterator();
			List fincasRango = new ArrayList();
			while (i.hasNext()){
				Object [] o = (Object[])i.next();
				Double superf = (Double)o[1];
				if (superf.compareTo(valorInicial)>0 && superf.compareTo(valorFinal)<=0){
					Long id = (Long)o[0];
					fincasRango.add(id);
				}
			}
			
			if (fincasRango.size()>0){
				
				q = "select sum(dpl.superficie) from DescomposicioPlantacio dpl";
				q +=" where dpl.plantacio.finca.id in (:rango)";
				
				col = getHibernateTemplate().findByNamedParam(q, "rango", fincasRango);

				while (col.iterator().hasNext()){
					res = (Double)col.iterator().next();
					break;
				}
			}
			
		} catch (HibernateException ex) {
			logger.error("getSuperfFincasDO failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getSuperfFincasDO fin");
		return res;
	}


	/**
	 * Actualiza un registro
	 * @param usuari
	 * @throws InfrastructureException
	 */
	public void makePersistent(Finca finca) throws InfrastructureException {
		try {
			logger.debug("makePersistent ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().saveOrUpdate(finca);
			// Informamos el idOriginal
			if (finca.getIdOriginal() == null) {
				finca.setIdOriginal(finca.getId());
			}
			getHibernateTemplate().flush();
			logger.debug("makePersistent fin");
		} catch (HibernateException ex) {
			logger.error("makePersistent failed", ex);
			throw new InfrastructureException(ex);
		}
	}


	/**
	 * Actualiza un registro
	 * @param usuari
	 * @throws InfrastructureException
	 */
	public void makePersistent(Finca finca, Session session) throws InfrastructureException {
		try {
			logger.debug("makePersistent ini");
			session.setFlushMode(FlushMode.ALWAYS);
			session.saveOrUpdate(finca);
			session.flush();
			logger.debug("makePersistent fin");
		} catch (HibernateException ex) {
			logger.error("makePersistent failed", ex);
			throw new InfrastructureException(ex);
		}
	}


	/**
	 * Borra un registro
	 * @param usuari
	 * @throws InfrastructureException
	 */
	public void makeTransient(Finca finca) throws InfrastructureException {
		try {
			logger.debug("makeTransient ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().delete(finca);
			getHibernateTemplate().flush();
			logger.debug("makeTransient fin");
		} catch (HibernateException ex) {
			logger.error("makeTransient failed", ex);
			throw new InfrastructureException(ex);
		}
	}
	
	/**
	 * Llista les finques que estan donades de baixa.
	 * @return
	 */
	public List findFinquesDeBaixa() throws InfrastructureException {
		try {
			String q =	"from Finca f where f.deBaixa = true";
			List finques = getHibernateTemplate().find(q);
			if (finques.size() > 0) {
				return finques;
			}
			return null;
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
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
