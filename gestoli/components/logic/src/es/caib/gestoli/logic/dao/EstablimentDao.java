package es.caib.gestoli.logic.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.exception.InfrastructureException;
import es.caib.gestoli.logic.model.Campanya;
import es.caib.gestoli.logic.model.Establiment;
import es.caib.gestoli.logic.util.Constants;


/**
 * Home object for domain model class Establiment
 * @see es.caib.gestoli.logic.model.Establiment
 * @author Carlos Pérez
 */
public class EstablimentDao {
	private static Logger logger = Logger.getLogger(EstablimentDao.class);
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
			col = getHibernateTemplate().find("from Establiment where dataBaixa is null");
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
	public Collection findAllEliminats() throws InfrastructureException {
		Collection col;
		try {
			logger.debug("findAll ini");
			col = getHibernateTemplate().find("from Establiment where dataBaixa is not null");
		} catch (HibernateException ex) {
			logger.error("findAll failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findAll fin");
		return col;
	}

	/**
	 * Recupera todos los registros de una campanya
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection findAllByCampanya(Long campanyaId) throws InfrastructureException {
		Collection col;
		try {
			logger.debug("findAllByCampanya ini");
			col = getHibernateTemplate().find("from Establiment as est where est.campanya.id = "+campanyaId+" and dataBaixa is null order by est.nom");
		} catch (HibernateException ex) {
			logger.error("findAllByCampanya failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findAllByCampanya fin");
		return col;
	}


	/**
	 * Cerca tots els establecimientos per campanya ordenat per RB i RC
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection establimentCercaTotsByCampanyaOrderedByRbRc(Long campanyaId) throws InfrastructureException {
		Collection col;
		try {
			logger.debug("establimentCercaTotsByCampanyaOrderedByRbRc ini");
			col = getHibernateTemplate().find("from Establiment as est where est.campanya.id = "+campanyaId+" and dataBaixa is null order by est.codiRB desc, est.codiRC desc");
		} catch (HibernateException ex) {
			logger.error("establimentCercaTotsByCampanyaOrderedByRbRc failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("establimentCercaTotsByCampanyaOrderedByRbRc fin");
		return col;
	}


	/**
	 * Recupera todos los registros activos
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection findAllActivos(Long campanyaId) throws InfrastructureException {
		Collection col;
		try {
			logger.debug("findAllActivos ini");
			col = getHibernateTemplate().find("from Establiment as est where est.actiu = true and est.campanya.id = "+campanyaId+" and dataBaixa is null order by est.nom");
		} catch (HibernateException ex) {
			logger.error("findAllActivos failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findAllActivos fin");
		return col;
	}
	
	/**
	 * Recupera todos los registros activos
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection findAllActivosProductorsOliva(Long campanyaId) throws InfrastructureException {
		Collection col;
		try {
			logger.debug("findAllActivos ini");
			col = getHibernateTemplate().find("from Establiment as est where est.actiu = true and est.campanya.id = "+campanyaId+" and dataBaixa is null and est.olivaTaula=true order by est.nom");
		} catch (HibernateException ex) {
			logger.error("findAllActivos failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findAllActivos fin");
		return col;
	}
	
	/**
	 * Recupera todos los registros activos
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection findAllEliminats(Long campanyaId) throws InfrastructureException {
		Collection col;
		try {
			logger.debug("findAllActivos ini");
			col = getHibernateTemplate().find("from Establiment as est where est.campanya.id = "+campanyaId+" and dataBaixa is not null order by est.nom");
		} catch (HibernateException ex) {
			logger.error("findAllActivos failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findAllActivos fin");
		return col;
	}
	
	/**
	 * Recupera todos los registros activos menos el establimentId que se indica
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection findRestoEstablimentsActivos(Long campanyaId, Long establimentId) throws InfrastructureException {
		Collection col;
		try {
			logger.debug("findRestoEstablimentsActivos ini");
			col = getHibernateTemplate().find("from Establiment as est" +
											  " where est.actiu = true" +
											  " and est.id is not "+establimentId +
											  " and est.campanya.id = "+campanyaId+" and dataBaixa is null order by est.nom");
		} catch (HibernateException ex) {
			logger.error("findRestoEstablimentsActivos failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findRestoEstablimentsActivos fin");
		return col;
	}

	/**
	 * Recupera todos los registros activos menos el establimentId que se indica
	 * @return
	 * @throws InfrastructureException
	 */
	//OPTIMITZAR 
	public Collection findRestoEstablimentsActivosZonaFicticia(Long campanyaId, Long establimentId) throws InfrastructureException {
		Collection col;
		try {
			logger.debug("findRestoEstablimentsActivosZonaFicticia ini");
			col = getHibernateTemplate().find("from Establiment as est" +
											  " where est.actiu = true" +
											  " and est.id is not "+establimentId +
											  " and est.id in (select distinct zon.establiment.id from Zona as zon where zon.fictici = true)" +
											  " and est.campanya.id = "+campanyaId+" and dataBaixa is null order by est.nom");
		} catch (HibernateException ex) {
			logger.error("findRestoEstablimentsActivosZonaFicticia failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findRestoEstablimentsActivosZonaFicticia fin");
		return col;
	}

	/**
	 * Devuelve el objeto que tiene el id
	 * @param id
	 * @return
	 * @throws InfrastructureException
	 */
	public Establiment getById(Long id) throws InfrastructureException {
		List establiments;
		try {
			logger.debug("getById ini");
			String q = "from Establiment as est where est.id = '"+id+"'";
			establiments = getHibernateTemplate().find(q);
			if (establiments.size() > 0) {
				return (Establiment)establiments.get(0);
			}

		} catch (HibernateException ex) {
			logger.error("getById failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getById fin");
		return null;	
	}
	
	/**
	 * Devuelve el objeto que tiene el id
	 * @param id
	 * @return
	 * @throws InfrastructureException
	 */
	public Establiment getByIdOriginal(Long id) throws InfrastructureException {
		List establiments;
		try {
			logger.debug("getByIdOriginal ini");
			String q = "from Establiment as est where est.idOriginal = '"+id+"'";
			establiments = getHibernateTemplate().find(q);
			if (establiments.size() > 0) {
				return (Establiment)establiments.get(0);
			}

		} catch (HibernateException ex) {
			logger.error("getByIdOriginal failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getByIdOriginal fin");
		return null;	
	}

	/**
	 * Devuelve el email que tiene el establecimiento con id
	 * @param id
	 * @return
	 * @throws InfrastructureException
	 */
	public String getEmail(Long id) throws InfrastructureException {
		List mails;
		try {
			logger.debug("getEmail ini");
			String q = "select est.email from Establiment est where est.id = "+id;
			mails = getHibernateTemplate().find(q);
			if (mails.size() > 0) {
				return (String)mails.get(0);
			}

		} catch (HibernateException ex) {
			logger.error("getEmail failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getEmail fin");
		return null;	
	}

	/**
	 * Devuelve true si el nombre está en la BBDD
	 * @params name, idEstabliment
	 * @return
	 * @throws InfrastructureException
	 */
	public boolean existName(String name, Long idEstabliment) throws InfrastructureException {
		List establecimientos;
		try {
			logger.debug("existName ini");
			establecimientos = getHibernateTemplate().find("from Establiment");
			Iterator it = establecimientos.iterator();
			while (it.hasNext()){
				Establiment es = (Establiment) it.next();
				if (es.getNom().equalsIgnoreCase(name) && !es.getId().equals(idEstabliment))
					logger.debug("existName fin");
				return true;
			}
		} catch (HibernateException ex) {
			logger.error("existName failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("existName fin");
		return false;
	}


	/**
	 * Retorna true si existen establecimientos asociados a usuarios
	 * @return
	 * @throws InfrastructureException
	 */
	public boolean existenEstablecimientosAsociadosUsuarios(Long idUsu) throws InfrastructureException {
		List olivicultors;
		try {
			logger.debug("existenEstablecimientosAsociadosUsuarios ini");
			String q = "select count(est.id) " +
					"from Establiment est " +
					"left join est.usuaris us " +
					"where us.id = " + idUsu;
			olivicultors = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("existenEstablecimientosAsociadosUsuarios failed", ex);
			throw new InfrastructureException(ex);
		}
		if (olivicultors != null && olivicultors.size()> 0 && ((Long)olivicultors.get(0)).intValue() > 0) {
			logger.debug("existenEstablecimientosAsociadosUsuarios fin");
			return true;
		}
		logger.debug("existenEstablecimientosAsociadosUsuarios fin");
		return false;
	}


	/**
	 * Recupera el numero de tafonas activas
	 * @return
	 * @throws InfrastructureException
	 */
	public Long numTafonasByCampanya(Long campanyaId) throws InfrastructureException {
		List establiments;
		try {
			logger.debug("numTafonasByCampanya ini");
			List campanyes = getHibernateTemplate().find("from Campanya where id = " + campanyaId);
			Campanya cam = (Campanya)campanyes.get(0);
			String q = "";
			if (cam.getDataFi() == null) {
				q = "select count(est.id) from Establiment as est where est.actiu = true and est.campanya.id = "+campanyaId+" and est.tipusEstabliment.id != "+Constants.CODI_ENVASADORA;
			} else {
				q = "select count(est.id) from HistoricEstabliment as est where est.actiu = true and est.campanya.id = "+campanyaId+" and est.tipusEstabliment.id != "+Constants.CODI_ENVASADORA;
			}
			
			establiments = getHibernateTemplate().find(q);
			if(establiments!= null && establiments.get(0)!= null ){
				logger.debug("numTafonasByCampanya fin");
				return (Long)establiments.get(0);
			}else{
				logger.debug("numTafonasByCampanya fin");
				return null;
			}
		} catch (HibernateException ex) {
			logger.error("numTafonasByCampanya failed", ex);
			throw new InfrastructureException(ex);
		}	
	
	}
	
	/**
	 * Recupera el numero de tafonas productores d'oliva de taula activas per campanya
	 * @return
	 * @throws InfrastructureException
	 */
	public Long numTafonasRTByCampanya(Long campanyaId) throws InfrastructureException {
		List establiments;
		try {
			logger.debug("numTafonasByCampanya ini");
			List campanyes = getHibernateTemplate().find("from Campanya where id = " + campanyaId);
			Campanya cam = (Campanya)campanyes.get(0);
			String q = "";
			if (cam.getDataFi() == null) {
				q = "select count(est.id) from Establiment as est where est.actiu = true and est.campanya.id = "+campanyaId+" and (est.codiRT is not null and est.codiRT <> '') and est.tipusEstabliment.id != "+Constants.CODI_ENVASADORA;
			} else {
				q = "select count(est.id) from HistoricEstabliment as est where est.actiu = true and est.campanya.id = "+campanyaId+" and (est.codiRT is not null and est.codiRT <> '') and est.tipusEstabliment.id != "+Constants.CODI_ENVASADORA;
			}
			
			establiments = getHibernateTemplate().find(q);
			if(establiments!= null && establiments.get(0)!= null ){
				logger.debug("numTafonasByCampanya fin");
				return (Long)establiments.get(0);
			}else{
				logger.debug("numTafonasByCampanya fin");
				return null;
			}
		} catch (HibernateException ex) {
			logger.error("numTafonasByCampanya failed", ex);
			throw new InfrastructureException(ex);
		}	
	
	}
	
	
	
	
	
	
	/**
	 * Recupera el listado de productores
	 * @return
	 * @throws InfrastructureException
	 */
	public List usuariosConTafonasByCampanya(Long campanyaId) throws InfrastructureException {
		List usuarios;
		try {
			logger.debug("usuariosConTafonasByCampanya ini");
			String q = "select distinct(us) " +
					"from Establiment as est " +
					"left join est.usuaris as us " +
					"where est.actiu = true " +
					"and est.campanya.id = "+campanyaId+" " +
					"and est.tipusEstabliment.id != "+Constants.CODI_ENVASADORA;
			usuarios = getHibernateTemplate().find(q);
			if(usuarios!= null){
				logger.debug("usuariosConTafonasByCampanya fin");
				return usuarios;
			}else{
				logger.debug("usuariosConTafonasByCampanya fin");
				return null;
			}
		} catch (HibernateException ex) {
			logger.error("usuariosConTafonasByCampanya failed", ex);
			throw new InfrastructureException(ex);
		}	
	
	}
	
	/**
	 * Recupera el listado de productores
	 * @return
	 * @throws InfrastructureException
	 */
	public List establecimientosConTafonasByCampanya(Long campanyaId) throws InfrastructureException {
		List estableciminetos;
		try {
			logger.debug("establecimientosConTafonasByCampanya ini");
			String q = "select distinct(est) from Establiment as est where est.actiu = true and est.campanya.id = "+campanyaId+" and est.tipusEstabliment.id != "+Constants.CODI_ENVASADORA;
			estableciminetos = getHibernateTemplate().find(q);
			if(estableciminetos!= null){
				logger.debug("establecimientosConTafonasByCampanya fin");
				return estableciminetos;
			}else{
				logger.debug("establecimientosConTafonasByCampanya fin");
				return null;
			}
		} catch (HibernateException ex) {
			logger.error("establecimientosConTafonasByCampanya failed", ex);
			throw new InfrastructureException(ex);
		}	
	
	}

	/**
	 * Recupera el numero de envasadoras activas
	 * @return
	 * @throws InfrastructureException
	 */
	public Long numEnvasadoraByCampanya(Long campanyaId) throws InfrastructureException {
		List establiments;
		try {
			logger.debug("numEnvasadoraByCampanya ini");
			List campanyes = getHibernateTemplate().find("from Campanya where id = " + campanyaId);
			Campanya cam = (Campanya)campanyes.get(0);
			String q = "";
			if (cam.getDataFi() == null) {
				q = "select count(est.id) from Establiment as est where est.actiu = true and est.campanya.id = "+campanyaId+" and est.tipusEstabliment.id != "+Constants.CODI_TAFONA;
			} else {
				q = "select count(est.id) from HistoricEstabliment as est where est.actiu = true and est.esAltaBaixa = false and est.campanya.id = "+campanyaId+" and est.tipusEstabliment.id != "+Constants.CODI_TAFONA;
			}
			establiments = getHibernateTemplate().find(q);
			if(establiments!= null && establiments.get(0)!= null ){
				logger.debug("numEnvasadoraByCampanya fin");
				return (Long)establiments.get(0);
			}else{
				logger.debug("numEnvasadoraByCampanya fin");
				return null;
			}
		} catch (HibernateException ex) {
			logger.error("numEnvasadoraByCampanya failed", ex);
			throw new InfrastructureException(ex);
		}	
	
	}
	
	
	/**
	 * Recupera el numero de envasadoras activas productores d'oliva de taula
	 * @return
	 * @throws InfrastructureException
	 */
	public Long numEnvasadoraRTByCampanya(Long campanyaId) throws InfrastructureException {
		List establiments;
		try {
			logger.debug("numEnvasadoraByCampanya ini");
			List campanyes = getHibernateTemplate().find("from Campanya where id = " + campanyaId);
			Campanya cam = (Campanya)campanyes.get(0);
			String q = "";
			if (cam.getDataFi() == null) {
				q = "select count(est.id) from Establiment as est where est.actiu = true and est.campanya.id = "+campanyaId+" and (est.codiRT is not null and est.codiRT <> '') and est.tipusEstabliment.id != "+Constants.CODI_TAFONA;
			} else {
				q = "select count(est.id) from HistoricEstabliment as est where est.actiu = true and est.campanya.id = "+campanyaId+" and (est.codiRT is not null and est.codiRT <> '') and est.tipusEstabliment.id != "+Constants.CODI_TAFONA;
			}
			establiments = getHibernateTemplate().find(q);
			if(establiments!= null && establiments.get(0)!= null ){
				logger.debug("numEnvasadoraByCampanya fin");
				return (Long)establiments.get(0);
			}else{
				logger.debug("numEnvasadoraByCampanya fin");
				return null;
			}
		} catch (HibernateException ex) {
			logger.error("numEnvasadoraByCampanya failed", ex);
			throw new InfrastructureException(ex);
		}	
	
	}
	
	
	
	
	/**
	 * Actualiza un registro
	 * @param Establiment
	 * @throws InfrastructureException
	 */
	public void makePersistent(Establiment establiment) throws InfrastructureException {
		try {
			logger.debug("makePersistent ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().saveOrUpdate(establiment);			
			// Informamos el idOriginal
			if (establiment.getIdOriginal() == null) {
				establiment.setIdOriginal(establiment.getId());
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
	 * @param Establiment
	 * @throws InfrastructureException
	 */
	public void makePersistent(Establiment establiment, Session session) throws InfrastructureException {
		try {
			logger.debug("makePersistent ini");
			session.setFlushMode(FlushMode.ALWAYS);
			session.saveOrUpdate(establiment);
			session.flush();
			logger.debug("makePersistent fin");
		} catch (HibernateException ex) {
			logger.error("makePersistent failed", ex);
			throw new InfrastructureException(ex);
		}
	}


	/**
	 * Borra un registro
	 * @param establiment
	 * @throws InfrastructureException
	 */
	public void makeTransient(Establiment establiment)
	throws InfrastructureException {
		try {
			logger.debug("makeTransient ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().delete(establiment);
			getHibernateTemplate().flush();
			logger.debug("makeTransient ini");
		} catch (HibernateException ex) {
			logger.error("makeTransient failed", ex);
			throw new InfrastructureException(ex);
		}
	}


	/**
	 * Devuelve un establecimiento de la BBDD a partir de su nombre
	 * @params name
	 * @return
	 * @throws InfrastructureException
	 */
	public Long getEstablecimiento(String name) throws InfrastructureException {
		List establecimientos;
		try {
			logger.debug("getEstablecimiento ini");
			String q = "select est.id from Establiment as est where est.nom = '"+name+"'";
			establecimientos = getHibernateTemplate().find(q);
			if (establecimientos.size() > 0) {
				logger.debug("getEstablecimiento fin");
				return (Long) establecimientos.get(0);
			}
		} catch (HibernateException ex) {
			logger.error("getEstablecimiento failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getEstablecimiento fin");
		return null;
	}


	/**
	 * Devuelve un establecimiento de la BBDD a partir de su cif
	 * @params cif
	 * @return
	 * @throws InfrastructureException
	 */
	public Long getCif(String cif) throws InfrastructureException {
		List establecimientos;
		try {
			logger.debug("getCif ini");
			String q = "select est.id from Establiment as est where est.cif = '"+cif+"'";
			establecimientos = getHibernateTemplate().find(q);
			if (establecimientos.size() > 0) {
				logger.debug("getCif fin");
				return (Long) establecimientos.get(0);
			}
		} catch (HibernateException ex) {
			logger.error("getCif failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getCif fin");
		return null;
	}
	
	public Establiment getCifResponsable(String cif) throws InfrastructureException {
		List establecimientos;
		try {
			logger.debug("getCif ini");
			String q = "from Establiment as est where est.cifResponsable = '"+cif+"'";
			establecimientos = getHibernateTemplate().find(q);
			if (establecimientos.size() > 0) {
				logger.debug("getCif fin");
				return (Establiment) establecimientos.get(0);
			}
		} catch (HibernateException ex) {
			logger.error("getCif failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getCif fin");
		return null;
	}


	/**
	 * Devuelve establecimiento de la BBDD a partir de su usuario asociado
	 * @params name
	 * @return
	 * @throws InfrastructureException
	 */
	public Establiment getEstablecimientoByUsuari(Long idUsuario) throws InfrastructureException {
		List establecimientos;			
		try {
			logger.debug("getEstablecimientoByUsuari ini");
			String q = "select est " +
					"from Establiment as est " +
					"left join est.usuaris as us " +
					"where us.id = '"+idUsuario+"'";
			establecimientos = getHibernateTemplate().find(q);
			if (establecimientos.size() > 0) {
				logger.debug("getEstablecimientoByUsuari fin");
				return (Establiment) establecimientos.get(0);
			}

		} catch (HibernateException ex) {
			logger.error("getEstablecimientoByUsuari failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getEstablecimientoByUsuari fin");
		return null;
	}


	/**
	 * Devuelve establecimiento de la BBDD a partir de su usuario asociado
	 * @params name
	 * @return
	 * @throws InfrastructureException
	 */
	public Establiment getEstablecimientoActivoByUsuari(Long idUsuario, Long idCampanya) throws InfrastructureException {
		List establecimientos;			
		try {
			logger.debug("getEstablecimientoActivoByUsuari ini");
			String q = "select est " +
					   "from Establiment as est " +
					   "left join est.usuaris as us " +
					   "where us.id = '" + idUsuario + "' " +
					   "and est.actiu = true and est.campanya.id = '"+idCampanya+"'";
			establecimientos = getHibernateTemplate().find(q);
			if (establecimientos.size() > 0) {
				logger.debug("getEstablecimientoActivoByUsuari fin");
				return (Establiment) establecimientos.get(0);
			}
		} catch (HibernateException ex) {
			logger.error("getEstablecimientoActivoByUsuari failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getEstablecimientoActivoByUsuari fin");
		return null;
	}


	/**
	 * Devuelve establecimiento de la BBDD a partir de su marca asociado
	 * @params name
	 * @return
	 * @throws InfrastructureException
	 */
	public Boolean getExistenEnvasadoresActivoByMarca(Long idMarca, Long idCampanya, Integer tipusEstablimentTafonaEnvasadora, Integer tipusEstablimentEnvasadora) throws InfrastructureException {
		List establecimientos;			
		try {
			logger.error("getExistenEnvasadoresActivoByMarca ini");
			String q="from Establiment as est where" +
			" est.actiu = true and" +
			" (est.tipusEstabliment = "+ tipusEstablimentTafonaEnvasadora+" or est.tipusEstabliment = "+ tipusEstablimentEnvasadora+")"+
			" and est in (select elements(mar.establiments) from Marca as mar where mar.id = "+idMarca+")";
			if(idCampanya != null){
				q.concat(" and est.campanya ="+idCampanya);
			}
			establecimientos = getHibernateTemplate().find(q);
			if (establecimientos.size() > 0) {
				logger.error("getExistenEnvasadoresActivoByMarca fin");
				return Boolean.valueOf(true);
			}else{
				logger.error("getExistenEnvasadoresActivoByMarca fin");
				return Boolean.valueOf(false);
			}
		} catch (HibernateException ex) {
			logger.error("getExistenEnvasadoresActivoByMarca failed", ex);
			throw new InfrastructureException(ex);
		}			
	}

	/**
	 * Devuelve establecimiento de la BBDD a partir de su marca asociado
	 * @params name
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection getEstablecimientosByMarca(Long idMarca) throws InfrastructureException {
		Collection establecimientos;			
		try {
			logger.error("getEstablecimientosByMarca ini");
			String q="from Establiment as est where est in (select elements(mar.establiments) from Marca as mar where mar.id = "+idMarca+")";
			establecimientos = getHibernateTemplate().find(q);
		   
			
		} catch (HibernateException ex) {
			logger.error("getEstablecimientosByMarca failed", ex);
			throw new InfrastructureException(ex);
		}
		
		return establecimientos;		
	}

	
	/**
	 * Comprueba si existe un establecimiento con ese usuarioId
	 * @params usuarioId 
	 * @return
	 * @throws InfrastructureException
	 */
	public Long usuarioAsignado(Long campanyaId, Long usuarioId) throws InfrastructureException {
		List establecimientos;
		try {
			logger.debug("usuarioAsignado ini");
			String q = "select est.id " +
					"from Establiment as est " +
					"left join est.usuaris us " +
					"where est.campanya.id = "+campanyaId+" " +
							"and us.id = '"+usuarioId+"'";
			establecimientos = getHibernateTemplate().find(q);
			if (establecimientos.size() > 0) {
				logger.debug("usuarioAsignado fin");
				return (Long) establecimientos.get(0);
			}
		} catch (HibernateException ex) {
			logger.error("usuarioAsignado failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("usuarioAsignado fin");
		return null;
	}


	/**
	 * Devuelve un establiment de la BBDD a partir de su campanya ,cif y nombre
	 * @params campanya
	 * @params cif
	 * @params nombre 
	 * @return
	 * @throws InfrastructureException
	 */
	public Long getCifName(Long campanyaId, String cif, String name) throws InfrastructureException {
		List establecimientos;
		try {
			logger.debug("getCifName ini");
			String q = "select est.id from Establiment as est where est.campanya.id = " + campanyaId + " and est.cif = '" + cif + "' and est.nom = '" + name.replace("'", "\'") + "'";
			establecimientos = getHibernateTemplate().find(q);
			if (establecimientos.size() > 0) {
				logger.debug("getCifName fin");
				return (Long) establecimientos.get(0);
			}
		} catch (HibernateException ex) {
			logger.error("getCifName failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getCifName fin");
		return null;
	}


	/**
	 * Retorna un llista de establecimientos de unos determinados tipos
	 * @return
	 * @throws InfrastructureException
	 */
	public List findEstablimentsByTipos(Long campanyaId,Object[] tipos) throws InfrastructureException {
		List lots;
		try {
			logger.debug("findEstablimentsByTipos ini");
			String q = "from Establiment as est where est.campanya.id = " + campanyaId;
			if(tipos!= null && tipos.length>0){
				q = q + " and est.tipusEstabliment.id in(:tipos) ";
			}
			q = q + " and est.actiu = true order by est.nom";			
			//			Query query = getHibernateTemplate().createQuery(q);
			if (tipos!= null && tipos.length>0) {
				//				query.setParameterList("tipos", tipos);
				lots = getHibernateTemplate().findByNamedParam(q, "tipos", tipos);
			} else {
				lots = getHibernateTemplate().find(q);
			}

		} catch (HibernateException ex) {
			logger.error("findEstablimentsByTipos failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findEstablimentsByTipos fin");
		return lots;
	}


	/**
	 * Comprueba si existe un establecimiento con ese codiRB
	 * @params campanyaId
	 * @params codiRB
	 * @return
	 * @throws InfrastructureException
	 */
	public Long existeCodiRB(Long campanyaId, String codiRB) throws InfrastructureException {
		List establiments;
		try {
			logger.debug("existeCodiRB ini");
			String q = "select est.id from Establiment as est where est.campanya.id = " + campanyaId + " and est.codiRB = '" + codiRB + "'";
			establiments = getHibernateTemplate().find(q);
			if (establiments.size() > 0) {
				logger.debug("existeCodiRB fin");
				return (Long) establiments.get(0);
			}
		} catch (HibernateException ex) {
			logger.error("existeCodiRB failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("existeCodiRB fin");
		return null;
	}


	/**
	 * Comprueba si existe un establecimiento con ese codiRC
	 * @params campanyaId
	 * @params codiRC
	 * @return
	 * @throws InfrastructureException
	 */
	public Long existeCodiRC(Long campanyaId, String codiRC) throws InfrastructureException {
		List establiments;
		try {
			logger.debug("existeCodiRC ini");
			String q = "select est.id from Establiment as est where est.campanya.id = " + campanyaId + " and est.codiRC = '" + codiRC + "'";
			establiments = getHibernateTemplate().find(q);
			if (establiments.size() > 0) {
				logger.debug("existeCodiRC fin");
				return (Long) establiments.get(0);
			}
		} catch (HibernateException ex) {
			logger.error("existeCodiRC failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("existeCodiRC fin");
		return null;
	}

	
	/**
	 * Recupera todos los registros de una campanya
	 * @return
	 * @throws InfrastructureException
	 */
//	public Collection findAllProcessos(Long establimentId) throws InfrastructureException {
//		Collection col;
//		try {
//			logger.debug("findAllProcessos ini");
//			col = getHibernateTemplate().find("from Establiment as est where est.campanya.id = "+cId+" order by est.nom");
//		} catch (HibernateException ex) {
//			logger.error("findAllProcessos failed", ex);
//			throw new InfrastructureException(ex);
//		}
//		logger.debug("findAllProcessos fin");
//		return col;
//	}
	
	//recuperam el darrer codiRB per afegir a la creació d'un establiment
	public String getLastRB() throws InfrastructureException{
		@SuppressWarnings("rawtypes")
		List codis;
		String select = "select est.codiRB from Establiment as est where est.codiRB is not null " +
				" and est.codiRB <> ''"; 
		codis = getHibernateTemplate().find(select);
		List<Integer> ordenats = new ArrayList<Integer>();
		for(Object cod:codis){
			ordenats.add(Integer.parseInt((String)cod));
		}
		Comparator comparator = Collections.reverseOrder();
		Collections.sort(ordenats,comparator);
		int seguent = ordenats.get(0)+1;
		return String.valueOf(seguent);
	}
	
	//recuperam el darrer codiRC per afegir a la creació d'un establiment
	public String getLastRC() throws InfrastructureException{
		@SuppressWarnings("rawtypes")
		List codis;
		String select = "select est.codiRC from Establiment as est where est.codiRC is not null " +
				" and est.codiRC <> ''"; 
		codis = getHibernateTemplate().find(select);
		List<Integer> ordenats = new ArrayList<Integer>();
		for(Object cod:codis){
			ordenats.add(Integer.parseInt((String)cod));
		}
		Comparator comparator = Collections.reverseOrder();
		Collections.sort(ordenats,comparator);
		int seguent = ordenats.get(0)+1;
		return String.valueOf(seguent);
	}
	
	//recuperam el darrer codiRT per afegir a la creació d'un establiment
	public String getLastRT() throws InfrastructureException{
		@SuppressWarnings("rawtypes")
		List codis;
		String select = "select est.codiRT from Establiment as est where est.codiRT is not null " +
				" and est.codiRT <> ''";
		codis = getHibernateTemplate().find(select);
		List<Integer> ordenats = new ArrayList<Integer>();
		for(Object cod:codis){
			ordenats.add(Integer.parseInt((String)cod));
		}
		Comparator comparator = Collections.reverseOrder();
		Collections.sort(ordenats,comparator);
		int seguent = ordenats.get(0)+1;
		return String.valueOf(seguent);
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