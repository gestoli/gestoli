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
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.exception.InfrastructureException;
import es.caib.gestoli.logic.model.Diposit;
import es.caib.gestoli.logic.model.EntradaDiposit;
import es.caib.gestoli.logic.model.SortidaDiposit;
import es.caib.gestoli.logic.model.SortidaLot;
import es.caib.gestoli.logic.util.Constants;


/**
 * Home object for domain model class Establiment
 * @see es.caib.gestoli.logic.model.Establiment
 * 
 */
public class DipositDao {
	private static Logger logger = Logger.getLogger(DipositDao.class);
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
			col = getHibernateTemplate().find("from Diposit as dip order by dip.id"); 
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
	public Diposit getById(Long id) throws InfrastructureException {

		List diposits;
		try {
			logger.debug("getById ini");
			String q = "from Diposit as di where di.id = " + id;
			diposits = getHibernateTemplate().find(q);
			if (diposits.size() > 0) {
				logger.debug("getById fin");
				return (Diposit)diposits.get(0);
			}
		} catch (HibernateException ex) {
			logger.error("getById failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getById fin");
		return null;		
	}


	/**
	 * Retorna un llista de totes les diposits associades amb
	 * un zona determinat.
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public List findActiusByZona(Long zonaId) throws InfrastructureException {
		List diposits;
		try {
			logger.debug("findActiusByZona ini");
			String q = "from Diposit dip " +
			"where dip.zona.id = " + zonaId + " " +
			"and dip.actiu = 'true' " +
			"order by dip.id";
			diposits = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("findActiusByZona failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findActiusByZona fin");
		return diposits;
	}

	
	/**
	 * Retorna un llista de totes les diposits associades amb
	 * un zona determinat.
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public List findActiusByZonaNoTraslladats(Long zonaId) throws InfrastructureException {
		List diposits;
		try {
			logger.debug("findActiusByZona ini");
			String q = "from Diposit dip " +
			"where dip.zona.id = " + zonaId + " " +
			"and dip.actiu = 'true' " +
			"and dip not in( select elements(tdi.diposits) from Trasllat tdi where tdi.acceptatTrasllat = true and tdi.retornatEstablimentOrigen = false and tdi.traslladant = false) "+
			"order by dip.id";
			diposits = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("findActiusByZona failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findActiusByZona fin");
		return diposits;
	}

	/**
	 * Retorna un llista de totes les diposits associades amb
	 * un zona determinat.
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public List findActiusNoFicticioByZona(Long zonaId) throws InfrastructureException {
		List diposits;
		try {
			logger.debug("findActiusNoFicticioByZona ini");
			String q = "from Diposit dip " +
			"where dip.zona.id = " + zonaId + " " +
			"and dip.actiu = 'true' " +
			"and dip.fictici = 'false' " +
			"order by dip.id";
			diposits = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("findActiusNoFicticioByZona failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findActiusNoFicticioByZona fin");
		return diposits;
	}
	
	/**
	 * Retorna un llista de totes les diposits associades amb
	 * un zona determinat.
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public List findActiusByEstabliment(Long establimentId) throws InfrastructureException {
		List diposits;
		try {
			logger.debug("findByEstabliment ini");
			String q = "select distinct dip from Diposit dip " +
			" where ((dip.zonaOrigenTrasllat.id is not null and dip.zonaOrigenTrasllat.id in (select z.id from Zona z where z.establiment.id = " + establimentId + ")) or" +
				  "  (dip.zonaOrigenTrasllat is null and dip.establiment.id = " + establimentId + ")) " +
			" and dip.actiu = 'true' " +
			" order by dip.codiAssignat";
			diposits = getHibernateTemplate().find(q);
			logger.debug("findByEstabliment ini");
		} catch (HibernateException ex) {
			logger.error("findByEstabliment failed", ex);
			throw new InfrastructureException(ex);
		}
		return diposits;
	}
	
	/**
	 * Retorna un llista de totes les diposits associades amb
	 * un zona determinat.
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public List findDipositsQueHanPassatPerEstabliment(Long establimentId) throws InfrastructureException {
		List diposits;
		try {
			logger.debug("findDipositsQueHanPassatPerEstabliment ini");
			String q = "select distinct edi.diposit from EntradaDiposit edi " +
			" where edi.establiment.id = " + establimentId + " " +
			" order by edi.diposit.codiAssignat";
			diposits = getHibernateTemplate().find(q);
			logger.debug("findDipositsQueHanPassatPerEstabliment ini");
		} catch (HibernateException ex) {
			logger.error("findDipositsQueHanPassatPerEstabliment failed", ex);
			throw new InfrastructureException(ex);
		}
		return diposits;
	}
	

	/**
	 * Retorna un llista de totes les diposits associades amb
	 * un establiment.
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public List findByEstabliment(Long establimentId) throws InfrastructureException {
		List diposits;
		try {
			logger.debug("findByEstabliment ini");
			String q = "select dip from Diposit dip " +
			"where dip.zona.actiu = true " +
			"and dip.zona.establiment.id = " + establimentId + " " +
			"order by dip.codiAssignat";
			diposits = getHibernateTemplate().find(q);
			logger.debug("findByEstabliment ini");
		} catch (HibernateException ex) {
			logger.error("findByEstabliment failed", ex);
			throw new InfrastructureException(ex);
		}
		return diposits;
	}
	
	/**
	 * Retorna un llista de totes les diposits associades amb
	 * un establiment en una data determinada.
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection findByEstablimentEnData(Long establimentId, Date data) throws InfrastructureException {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Collection diposits;
		try {
			logger.debug("findByEstabliment ini");
			String de = df.format(data);
//			String q = "select distinct ed.diposit " +
//					"from EntradaDiposit ed " +
//					"where ed.traza.id = (select max(ed2.traza.id) " +
//									"from EntradaDiposit ed2 " +
//									"where ed.diposit = ed2.diposit " +
//									"and ed2.data <= '" + de + "' " +
//									"and ed2.valid = true) " +
//					"and ed.establiment.id = " + establimentId + " " +
//					"order by ed.diposit.codiAssignat";
			
			
			String q = "select distinct edi_coddip " +
						"from( " +
						"select ed.*, max(ed.edi_codtra) over (partition by ed.edi_coddip) as max_traza " +
						"from oli_entrada_diposit ed " +
						"where edi_data <= '" + de + "'" +
						" and edi_valid = true " +
						") t " +
						"where edi_codtra = max_traza " +
						"and edi_codest = " + establimentId + " ";
						
			
			Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(q);
			diposits =   query.list();
			
			logger.debug("findByEstabliment ini");
		} catch (HibernateException ex) {
			logger.error("findByEstabliment failed", ex);
			throw new InfrastructureException(ex);
		}
		return diposits;
	}
	
	/**
	 * Retorna un llista de totes les diposits associades amb
	 * un establiment en una data determinada, amb una categoria.
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection findByEstablimentCategoriaEnData(Long establimentId, Long categoriaId, Date data) throws InfrastructureException {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Collection diposits;
		
		
		
		
		try {
			logger.debug("findByEstablimentCategoriaEnData ini");
			String de = df.format(data);
			
			String q = "select distinct edi_coddip " +
			"from( " +
			"select ed.*, max(ed.edi_codtra) over (partition by ed.edi_coddip) as max_traza " +
			"from oli_entrada_diposit ed " +
			"where edi_data <= '" + de + "' " +
			") t " +
			"where edi_codtra = max_traza " +
			"and edi_valid = true ";
			if (establimentId != null) {
				q += "and edi_codest = " + establimentId + " ";
			}
			q += "and edi_codcao = " + categoriaId + " " +
			"order by edi_coddip";

			Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(q);
			diposits =   query.list();
			
			logger.debug("findByEstablimentCategoriaEnData ini");
		} catch (HibernateException ex) {
			logger.error("findByEstablimentCategoriaEnData failed", ex);
			throw new InfrastructureException(ex);
		}
		return diposits;
	}
	
	public Diposit findDipositFicticiByEstabliment(Long establimentId) throws InfrastructureException {
		String q = "select dip from Diposit dip " +
		"where " +
		" dip.establiment.id = " + establimentId + 
		" and dip.fictici = true " +
		" order by dip.codiAssignat";
		List diposits = getHibernateTemplate().find(q);
		
		for(int i=0; i<diposits.size(); i++){
			Diposit d = (Diposit)diposits.get(i);
			if(d.getFictici()) return d;
		}
		
		return null;
	}
	
	/**
	 * Retorna un llista de tots els diposits no ficticis associats amb
	 * un establiment.
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public List findNoFicticiByEstabliment(Long establimentId) throws InfrastructureException {
		List diposits;
		try {
			logger.debug("findNoFicticiByEstabliment ini");
			String q = "select dip from Diposit dip " +
			"where dip.zona.actiu = true " +
			"and dip.fictici = false " +
			"and dip.zona.establiment.id = " + establimentId + " " +
			"order by dip.codiAssignat";
			diposits = getHibernateTemplate().find(q);
			logger.debug("findNoFicticiByEstabliment ini");
		} catch (HibernateException ex) {
			logger.error("findNoFicticiByEstabliment failed", ex);
			throw new InfrastructureException(ex);
		}
		return diposits;
	}
	
	/**
	 * Retorna un llista de totes les diposits associades amb
	 * un establiment.
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public boolean existeixenTraslladatsByEstabliment(Long establimentId, String codiDiposit) throws InfrastructureException {
		List diposits;
		try {
			codiDiposit = codiDiposit.replaceAll("'", "''");
			logger.debug("findByEstabliment ini");
			String q = "select t from Trasllat t " +
			"left join t.diposits dip " +
			"where " +
			" t.establimentByTdiCodeor = " + establimentId + " " +
			"and dip.codiOriginal is not null " +
			"and upper(dip.codiOriginal) = '"+codiDiposit.toUpperCase()+"' " +
			" and ( " +
			"		(t.acceptatTrasllat = true and t.retornatEstablimentOrigen = false and t.traslladant = false) or " +
			"		(t.acceptatTrasllat = false and t.retornatEstablimentOrigen = true and t.traslladant = true)" +
			") " +
			"order by dip.codiAssignat";
			System.out.println(q);
			diposits = getHibernateTemplate().find(q);
			logger.debug("findByEstabliment ini");
		} catch (HibernateException ex) {
			logger.error("findByEstabliment failed", ex);
			throw new InfrastructureException(ex);
		}
		return diposits.size() > 0;
	}


	/**
	 * Retorna un llista de tots els diposits associats amb les zones
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public List findByZona(Long zonaId) throws InfrastructureException {
		List establiments;
		try {
			logger.debug("findByZona ini");
			String q = "select dip from Diposit dip " +
			"where dip.zona.id = " +zonaId+ " " +
			"order by dip.id";
			establiments = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("findByZona failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findByZona fin");
		return establiments;
	}

	/**
	 * Retorna un llista de tots els diposits buits
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public List findVaciosByEstabliment(Long establimentId) throws InfrastructureException {
		List diposits;
		try {
			logger.debug("findVaciosByEstabliment ini");
			String q = "from Diposit dip " +
			"where dip.establiment.id = " +establimentId+ " " +
			"and dip.actiu=true "+
			"and dip.fictici=false "+
			"and (dip.volumActual is null or dip.volumActual = 0) "+
			"order by dip.id";
			
			diposits = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("findVaciosByEstabliment failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findVaciosByEstabliment fin");
		return diposits;
	}

	/**
	 * Retorna un llista de tots els diposits associats amb les zones
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public List findNoVaciosByZonaAndCategorias(Long zonaId, Object[] categorias) throws InfrastructureException {
		List diposits;
		try {
			logger.debug("findNoVaciosByZonaAndCategorias ini");
			String q = "select dip from Diposit dip " +
			"where dip.zona.id = " +zonaId+ " " +
			"and dip.actiu=true "+
			"and dip.fictici =false " +
			"and dip.volumActual > 0 "+
			"and dip.partidaOli.categoriaOli.id in(:categorias) "+
			"order by dip.id";
			diposits = getHibernateTemplate().findByNamedParam(q, "categorias", categorias);
		} catch (HibernateException ex) {
			logger.error("findNoVaciosByZonaAndCategorias failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findNoVaciosByZonaAndCategorias fin");
		return diposits;
	}
	
	/**
	 * Retorna un llista de tots els diposits associats amb les zones
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public List findNoVaciosNiTraslladatsByZonaAndCategorias(Long zonaId, Object[] categorias) throws InfrastructureException {
		List diposits;
		try {
			logger.debug("findNoVaciosNiTraslladatsByZonaAndCategorias ini");
			String q = "select dip from Diposit dip " +
			"where dip.zona.id = " +zonaId+ " " +
			"and dip.actiu=true "+
			"and dip.fictici = 'false' " +
			"and dip.volumActual > 0 "+
			"and (dip.zonaOrigenTrasllat is null or dip.zona.establiment = dip.zonaOrigenTrasllat.establiment) "+
			"and dip.partidaOli.categoriaOli.id in(:categorias) "+
			"order by dip.id";
			diposits = getHibernateTemplate().findByNamedParam(q, "categorias", categorias);
		} catch (HibernateException ex) {
			logger.error("findNoVaciosNiTraslladatsByZonaAndCategorias failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findNoVaciosNiTraslladatsByZonaAndCategorias fin");
		return diposits;
	}
	

	/**
	 * Retorna un llista de tots els diposits associats aa un establecimiento de unas categorias determinadas
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public List findNoVaciosByEstablecimientoAndCategorias(Long establimentId, Object[] categorias) throws InfrastructureException {
		List diposits;
		try {
			logger.debug("findNoVaciosByEstablecimientoAndCategorias ini");
			String q = "select dip from Diposit dip " +
			"where dip.actiu=true "+
			"and dip.fictici = 'false' " +
			"and dip.volumActual > 0";
			if(establimentId!= null){
				q = q+" and dip.establiment.id = " +establimentId ;
			} else {
				q += " and dip.establiment.campanya.id = (select max(cam.id) from Campanya cam) ";
			}
			if(categorias!= null && categorias.length >0){
				q = q+" and dip.partidaOli.categoriaOli.id in(:categorias) ";
			}
			q = q +	" order by dip.id";						
			if(categorias!= null && categorias.length >0){
				diposits = getHibernateTemplate().findByNamedParam(q, "categorias", categorias);
			} else {
				diposits = getHibernateTemplate().find(q);
			}
		} catch (HibernateException ex) {
			logger.error("findNoVaciosByEstablecimientoAndCategorias failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findNoVaciosByEstablecimientoAndCategorias fin");
		return diposits;
	}

	/**
	 * Retorna un llista de tots els diposits associats aa una zona de unas categorias determinadas y no pendientes de traslado
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public List findNoVaciosByZonaAndCategoriasNoPendnientesDeTraslado(Long zonaId, Object[] categorias) throws InfrastructureException {
		List diposits;
		try {
			logger.debug("findNoVaciosByZonaAndCategoriasNoPendnientesDeTraslado ini");
			String q = "select dip from Diposit dip " +
			"where dip.zona.id = " +zonaId+ " " +
			"and dip.actiu=true "+
			"and dip.fictici = 'false' " +
			"and dip.volumActual > 0 "+
			"and dip not in( select elements(tdi.diposits) from Trasllat tdi where (tdi.acceptatTrasllat = true or tdi.acceptatTrasllat is null) and tdi.data > now() ) ";
					
			if(categorias!= null && categorias.length >0){
				q = q+" and dip.partidaOli.categoriaOli.id in(:categorias) ";
			}
			q = q +	" order by dip.id";						
			if(categorias!= null && categorias.length >0){
				diposits = getHibernateTemplate().findByNamedParam(q, "categorias", categorias);
			} else {
				diposits = getHibernateTemplate().find(q);
			}
		} catch (HibernateException ex) {
			logger.error("findNoVaciosByZonaAndCategoriasNoPendnientesDeTraslado failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findNoVaciosByZonaAndCategoriasNoPendnientesDeTraslado fin");
		return diposits;
	}

	/**
	 * Retorna un llista de tots els diposits vacios o no llenos de unas determinadas categorias associats amb les zones
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public List findNoLlenosByZonaAndCategorias(Long zonaId,Object[] categorias) throws InfrastructureException {
		List diposits;
		try {
			logger.debug("findNoLlenosByZonaAndCategorias ini");
			String q = "select dip " +
			"from Diposit dip " +
			"left join dip.partidaOli par " +
			"where dip.zona.id = " +zonaId+ " " +
			"and dip.actiu=true "+
			"and dip.fictici = 'false' " +
			"and ( (dip.volumActual is null) " +
			"	or (dip.volumActual = 0) " +
			"	or ((dip.capacitat-dip.volumActual>0) " +
			"		and (par.categoriaOli.id in (:categorias) ) )" +
			" ) "+
			"order by dip.id";			
			diposits = getHibernateTemplate().findByNamedParam(q, "categorias", categorias);
		} catch (HibernateException ex) {
			logger.error("findNoLlenosByZonaAndCategorias failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findNoLlenosByZonaAndCategorias fin");
		return diposits;
	}


	/**
	 * Retorna true si existen depÃƒÂ³sitos activos en una zona
	 * un zona determinat.
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public Boolean existenDipositsActiusEnZona(Long zonaId) throws InfrastructureException {
		List diposits;
		try {
			logger.debug("existenDipositsActiusEnZona ini");
			String q = "select count (dip.id) from Diposit dip " +
			"where dip.zona.id = " + zonaId + " " +
			"and dip.actiu = 'true' ";
			diposits = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("existenDipositsActiusEnZona failed", ex);
			throw new InfrastructureException(ex);
		}
		if (diposits!= null && diposits.size()> 0 && ((Long)diposits.get(0)).intValue() > 0) {
			logger.debug("existenDipositsActiusEnZona fin");
			return new Boolean(true);
		}
		logger.debug("existenDipositsActiusEnZona fin");
		return new Boolean(false);
	}


	/**
	 * Retorna true si existen depÃƒÂ³sitos asociados a zonas
	 * 
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public boolean existenDepositosAsociadosZonas(Long idZona) throws InfrastructureException {
		List diposits;
		try {
			logger.debug("existenDepositosAsociadosZonas ini");
			String q = "select count(dip.id) from Diposit dip " +
			"where dip.zona.id = " + idZona + " ";
			diposits = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("existenDepositosAsociadosZonas failed", ex);
			throw new InfrastructureException(ex);
		}
		if (diposits != null && diposits.size()> 0 && ((Long)diposits.get(0)).intValue() > 0) {
			logger.debug("existenDepositosAsociadosZonas fin");
			return true;
		}
		logger.debug("existenDepositosAsociadosZonas fin");
		return false;
	}

	/**
	 * Actualiza un registro
	 * @param Diposit
	 * @throws InfrastructureException
	 */
	public void makePersistent(Diposit diposit) throws InfrastructureException {
		try {
			logger.debug("makePersistent ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().saveOrUpdate(diposit);
			// Informamos el idOriginal
			if (diposit.getIdOriginal() == null) {
				diposit.setIdOriginal(diposit.getId());
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
	 * @param Diposit
	 * @param session
	 * @throws InfrastructureException
	 */
	public void makePersistent(Diposit diposit, Session session) throws InfrastructureException {
		try {
			session.setFlushMode(FlushMode.ALWAYS);
			logger.debug("makePersistent ini");
			session.saveOrUpdate(diposit);
			session.flush();
			logger.debug("makePersistent fin");
		} catch (HibernateException ex) {
			logger.error("makePersistent failed", ex);
			throw new InfrastructureException(ex);
		}
	}


	/**
	 * Borra un registro
	 * @param Diposit
	 * @throws InfrastructureException
	 */
	public void makeTransient(Diposit diposit)
	throws InfrastructureException {
		try {
			logger.debug("makeTransient ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().delete(diposit);
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
			String query ="from Diposit as dip" +					
			" where " +
			" dip.actiu = true " +
			((establimentId != null) ? "and dip.establiment.id = :establimentId " : "") +
			((seleccio != null) ? "and dip.id in (:seleccio) " : "") +
			((zonaId != null) ? "and dip.zona.id = :zonaId " : "") +
			((senseZona) ? "and dip.zona.id is null " : "");

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
						Diposit info = (Diposit)it.next();
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
				String query ="from Diposit as dip" +					
				" where " +
				"dip.actiu = true " +
				"and dip.id in (:seleccio) " ;						

				c = getHibernateTemplate().findByNamedParam(query, "seleccio", seleccio);

				// Ordena els resltats segons l'ordre de la selecciÃƒÂ³
				ArrayList infoOrdenat = new ArrayList();
				for (int nsel = 0; nsel < seleccio.length; nsel++) {
					Long cid = (Long)seleccio[nsel];
					for (Iterator it = c.iterator(); it.hasNext();) {
						Diposit info = (Diposit)it.next();
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
	 * Devuelve la cantidad total de litros de aceite existente de una temporada o entre fechas de la temporada actual de aceite cualificado
	 * @param temporadaId
	 * @param dataInici
	 * @param dataFin
	 * @return
	 * @throws InfrastructureException
	 */
	public Double getExistenciaOliByCategoriaAndFecha(Long temporadaId,Date dataInici,Date dataFin,Object[] categorias) throws InfrastructureException {
		Collection col;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Double numeroEntradas = null;
		Double numeroSalidas = null;
		double totalOli = 0;

		try {
			logger.debug("getExistenciaOliByCategoriaAndFecha ini");

			// Calculamos el total de litros que han entrado
			String q = "select sum(edi.litres) from EntradaDiposit edi where edi.litres > 0 "; 
			if(dataFin != null){
				if(dataFin != null){
					String ff = df.format(dataFin);
					q = q+ " and  edi.data <= '"+ff+"' ";
				}
				q = q+" and edi.establiment.campanya.id= (select max(cam.id) from Campanya cam) and edi.litres > 0";
			} else {
				q = q+ " and edi.establiment.campanya.id="+temporadaId;
			}
			if(categorias!= null && categorias.length >0){
				// q = q+" and edi.categoriaOli.id in(:categorias) ";
				q = q+" and edi.diposit.partidaOli.categoriaOli.id in(:categorias) ";
			}
			if(categorias!= null && categorias.length >0){
				col = getHibernateTemplate().findByNamedParam(q, "categorias", categorias);
			} else {
				col = getHibernateTemplate().find(q);
			}
			
			while (col.iterator().hasNext()){
				numeroEntradas = (Double)col.iterator().next();
				break;
			}

			// Calculamos el total de litros que han salido
			q = "select sum(sdi.litres) from SortidaDiposit sdi where sdi.litres > 0 "; 
			if(dataFin != null){
				if(dataFin != null){
					String ff = df.format(dataFin);
					q = q+ " and  sdi.data <= '"+ff+"' ";
				}
				q = q+" and sdi.establiment.campanya.id= (select max(cam.id) from Campanya cam) and sdi.litres > 0";
			} else {
				q = q+ " and sdi.establiment.campanya.id="+temporadaId;
			}
			if(categorias!= null && categorias.length >0){
				//q = q+" and sdi.categoriaOli.id in(:categorias) ";
				q = q+" and sdi.dipositBySdiCoddor.partidaOli.categoriaOli.id in(:categorias) ";
			}
			if(categorias!= null && categorias.length >0){
				col = getHibernateTemplate().findByNamedParam(q, "categorias", categorias);
			} else {
				col = getHibernateTemplate().find(q);
			}
			while (col.iterator().hasNext()){
				numeroSalidas = (Double)col.iterator().next();
				break;
			}

			if(numeroEntradas != null ){
				totalOli = numeroEntradas.doubleValue();
				if(numeroSalidas != null){
					totalOli = numeroEntradas.doubleValue() - numeroSalidas.doubleValue();
				}
			}

		} catch (HibernateException ex) {
			logger.error("getExistenciaOliByCategoriaAndFecha failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getExistenciaOliByCategoriaAndFecha fin");
		return Double.valueOf(String.valueOf(totalOli));
	}

	public Double getExistenciaOliByCategoriaAndData(Date dataFin, Object[] categorias) {
		Collection col;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Double numeroEntradas = null;
		Double numeroSalidas = null;
		double totalOli = 0;

		try {
			logger.debug("getExistenciaOliByCategoriaAndData ini");

			// Calculamos el total de litros que han entrado
			String q = "select sum(edi.litres) from EntradaDiposit edi where edi.litres > 0 "; 
			if(dataFin != null){
				String ff = df.format(dataFin);
				q = q+ " and edi.data <= '"+ff+"' ";
			}
			if(categorias!= null && categorias.length >0){
				q = q+" and edi.diposit.partidaOli.categoriaOli.id in(:categorias) ";
				col = getHibernateTemplate().findByNamedParam(q, "categorias", categorias);
			} else {
				col = getHibernateTemplate().find(q);
			}
			
			while (col.iterator().hasNext()){
				numeroEntradas = (Double)col.iterator().next();
				break;
			}

			// Calculamos el total de litros que han salido
			q = "select sum(sdi.litres) from SortidaDiposit sdi where sdi.litres > 0 "; 
			if(dataFin != null){
				String ff = df.format(dataFin);
				q = q+ " and  sdi.data <= '"+ff+"' ";
			}
			if(categorias!= null && categorias.length >0){
				q = q+" and sdi.dipositBySdiCoddor.partidaOli.categoriaOli.id in(:categorias) ";
				col = getHibernateTemplate().findByNamedParam(q, "categorias", categorias);
			} else {
				col = getHibernateTemplate().find(q);
			}
			while (col.iterator().hasNext()){
				numeroSalidas = (Double)col.iterator().next();
				break;
			}

			if(numeroEntradas != null ){
				totalOli = numeroEntradas.doubleValue();
				if(numeroSalidas != null){
					totalOli -= numeroSalidas.doubleValue();
				}
			}

		} catch (HibernateException ex) {
			logger.error("getExistenciaOliByCategoriaAndData failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getExistenciaOliByCategoriaAndData fin");
		return Double.valueOf(String.valueOf(totalOli));
	}

	
//	/**
//	 * Devuelve la cantidad total de litros de aceite vendido para un establecimiento y categoria
//	 * @return
//	 * @throws InfrastructureException
//	 */
//	public Double getQuantitatOliVenutDiscriminantQualificacioAndEstabliment (Long temporadaId, Long establimentId, Date dataInici, Object[] categorias) throws InfrastructureException {
//		Collection col;
//		Double numeroVendidos = new Double(0);
//		Double numeroVendidosGranel = new Double(0);
//
//		try {				
//			logger.debug("getQuantitatOliVenutDiscriminantQualificacioAndEstabliment ini");
//
//			// Lotes vendidos
//			
//			String q = "select slot from SortidaLot slot where slot.lot.zona.establiment.campanya.id="+temporadaId+" and slot.lot.litresEnvassats > 0";
//			if (establimentId!=null){
//				q += " and slot.lot.zona.establiment.id="+establimentId;
//			}
//		
//			q += " and slot.accioSortida='v'";
//			q += " and slot.valid = true ";
//			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
//			if(dataInici != null){
//				String fi = df.format(dataInici);
//				q += " and  slot.vendaData >= '"+fi+"' ";
//			}
//			
//			if(categorias!= null && categorias.length >0){
//				q = q+" and slot.lot.partidaOli.categoriaOli.id in(:categorias) ";
//				col = getHibernateTemplate().findByNamedParam(q, "categorias", categorias);
//			} else {
//				col = getHibernateTemplate().find(q);
//			}
//
//			
//			SortidaLot sortidaLot = null;
//			Iterator ite = col.iterator();
//			while (ite.hasNext()){
//				sortidaLot = (SortidaLot) ite.next();
//				if(sortidaLot.getLot().getLitresEnvassats() != null && sortidaLot.getLot().getNumeroBotellesInicials()!= null && sortidaLot.getVendaNumeroBotelles()!= null ){
//					double litrosPorBotella = sortidaLot.getLot().getLitresEnvassats().doubleValue()/sortidaLot.getLot().getNumeroBotellesInicials().doubleValue();
//					double litrosVendidos = sortidaLot.getVendaNumeroBotelles().doubleValue()* litrosPorBotella;
//					numeroVendidos = new Double (numeroVendidos.doubleValue() + litrosVendidos);
//				}
//			}
//			
//			//Aceite a granel vendido
//			
//			q = "select sordip from SortidaDiposit sordip where sordip.traza.tipus = "+Constants.CODI_TRAZA_TIPUS_VENDA_OLI;
//
//			if(dataInici != null ){
//				if(dataInici != null){
//					String fi = df.format(dataInici);
//					q += " and  sordip.data >= '"+fi+"' ";
//				}				
//			}			
//			q += " and sordip.establiment.campanya.id = "+temporadaId ;			
//
//			if(categorias != null && categorias.length > 0){
//				q += " and sordip.partidaOli.categoriaOli.id in(:categorias) ";
//			}
//			q += " and sordip.valid = true ";
//
//			col = getHibernateTemplate().findByNamedParam(q, "categorias", categorias);
//			
//						
//			SortidaDiposit sortidaDiposit = null;
//			Iterator ite2 = col.iterator();
//			while (ite2.hasNext()){
//				sortidaDiposit = (SortidaDiposit) ite2.next();
//				if(sortidaDiposit.getLitres() != null ){
//					numeroVendidosGranel = new Double (numeroVendidosGranel.doubleValue() + sortidaDiposit.getLitres().doubleValue());
//				}
//			}
//			
//			
//			
//		} catch (HibernateException ex) {
//			logger.error("getQuantitatOliVenutDiscriminantQualificacioAndEstabliment failed", ex);
//			throw new InfrastructureException(ex);
//		}
//		logger.debug("getQuantitatOliVenutDiscriminantQualificacioAndEstabliment fin");
//		return Double.valueOf(String.valueOf(numeroVendidos.doubleValue()+ numeroVendidosGranel.doubleValue()));
//		
//	}	
	
	/**
	 * Devuelve la cantidad total de litros de aceite vendido para un establecimiento y categoria
	 * @return
	 * @throws InfrastructureException
	 */
	public Double getQuantitatOliVenutDiscriminantQualificacioAndEstabliment (Long establimentId, Date dataInici, Date dataFi, Object[] categorias) throws InfrastructureException {
		Collection col;
		Double numeroVendidos = new Double(0);
		Double numeroVendidosGranel = new Double(0);

		try {				
			logger.debug("getQuantitatOliVenutDiscriminantQualificacioAndEstabliment ini");
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			String inici = df.format(dataInici);
			// Lots
			String q = 	"select slot " +
						"  from SortidaLot slot " +
						" where slot.lot.litresEnvassats > 0" +
						" and slot.accioSortida='v'" +
						" and slot.valid = true "+
						" and slot.vendaData >= '"+inici+"' ";
			if (dataFi != null) {
				String fi = df.format(dataFi);
				q += " and slot.vendaData <= '"+ fi +"' ";
			}
			if (establimentId!=null) q += " and slot.lot.zona.establiment.id="+establimentId;
			if(categorias!= null && categorias.length >0){
				q = q+" and slot.lot.partidaOli.categoriaOli.id in(:categorias) ";
				col = getHibernateTemplate().findByNamedParam(q, "categorias", categorias);
			} else {
				col = getHibernateTemplate().find(q);
			}
			
			SortidaLot sortidaLot = null;
			Iterator ite = col.iterator();
			while (ite.hasNext()){
				sortidaLot = (SortidaLot) ite.next();
				if(sortidaLot.getLot().getLitresEnvassats() != null && sortidaLot.getLot().getNumeroBotellesInicials()!= null && sortidaLot.getVendaNumeroBotelles()!= null ){
					double litrosPorBotella = sortidaLot.getLot().getEtiquetatge().getTipusEnvas().getVolum();
					double litrosVendidos = sortidaLot.getVendaNumeroBotelles().doubleValue()* litrosPorBotella;
					numeroVendidos += litrosVendidos;
				}
			}
			
			// Granel
			q = "select sordip " +
				"  from SortidaDiposit sordip " +
				" where sordip.traza.tipus = "+Constants.CODI_TRAZA_TIPUS_VENDA_OLI +
				" and  sordip.data >= '"+inici+"' " +
				" and sordip.valid = true ";
			if (dataFi != null) {
				String fi = df.format(dataFi);
				q += " and sordip.data <= '"+ fi +"' ";
			}
			if (establimentId!=null) q += " and sordip.establiment.id="+establimentId;
			if(categorias != null && categorias.length > 0){
				q += " and sordip.partidaOli.categoriaOli.id in(:categorias) ";
				col = getHibernateTemplate().findByNamedParam(q, "categorias", categorias);
			} else {
				col = getHibernateTemplate().find(q);
			}
						
			SortidaDiposit sortidaDiposit = null;
			Iterator ite2 = col.iterator();
			while (ite2.hasNext()){
				sortidaDiposit = (SortidaDiposit) ite2.next();
				if(sortidaDiposit.getLitres() != null ){
					numeroVendidosGranel += sortidaDiposit.getLitres().doubleValue();
				}
			}
		} catch (HibernateException ex) {
			logger.error("getQuantitatOliVenutDiscriminantQualificacioAndEstabliment failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getQuantitatOliVenutDiscriminantQualificacioAndEstabliment fin");
		return Double.valueOf(String.valueOf(numeroVendidos.doubleValue()+ numeroVendidosGranel.doubleValue()));
		
	}
	
//	/**
//	 * Devuelve la cantidad total de litros de aceite elaborado para un establecimiento y categoria
//	 * @return
//	 * @throws InfrastructureException
//	 */
//	public Double getQuantitatOliElaboratDiscriminantQualificacioAndEstabliment (Long temporadaId, Long establimentId, Date dataInici, Object[] categorias) throws InfrastructureException {
//		Collection col;
//		Double numeroElaborado = null;
//		double totalOli = 0;
//
//		try {				
//			logger.debug("getQuantitatOliElaboratDiscriminantQualificacioAndEstabliment ini");
//
//			
//			//Calculamos el total de litros que han entrado
//			String q = "select sum(edi.litres) from EntradaDiposit edi where edi.elaboracio is not null and edi.establiment.campanya.id="+temporadaId;
//			q += " and edi.valid = true ";
//			if (establimentId!=null){
//				q += " and edi.establiment.id="+establimentId;
//			}
//	
//			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
//			if(dataInici != null){
//				String fi = df.format(dataInici);
//				q += " and  edi.data >= '"+fi+"' ";
//			}
//			
//			if(categorias!= null && categorias.length >0){
//				q = q+" and edi.elaboracio.categoriaOli.id in(:categorias) ";
//				col = getHibernateTemplate().findByNamedParam(q, "categorias", categorias);
//			} else {
//				col = getHibernateTemplate().find(q);
//			}
//
//			while (col.iterator().hasNext()){
//				numeroElaborado = (Double)col.iterator().next();
//				break;
//			}
//
//			if (numeroElaborado!=null){
//				totalOli=numeroElaborado.doubleValue();
//			}
//			
//		} catch (HibernateException ex) {
//			logger.error("getQuantitatOliElaboratDiscriminantQualificacioAndEstabliment failed", ex);
//			throw new InfrastructureException(ex);
//		}
//		logger.debug("getQuantitatOliElaboratDiscriminantQualificacioAndEstabliment fin");
//		return Double.valueOf(String.valueOf(totalOli));
//	}		
	
	/**
	 * Devuelve la cantidad total de litros de aceite elaborado para un establecimiento y categoria
	 * @return
	 * @throws InfrastructureException
	 */
	public Double getQuantitatOliElaboratDiscriminantQualificacioAndEstabliment (Long establimentId, Date dataInici, Date dataFi, Object[] categorias) throws InfrastructureException {
		List col;
		Double numeroElaborado = 0.0;
		double totalOli = 0;

		try {				
			logger.debug("getQuantitatOliElaboratDiscriminantQualificacioAndEstabliment ini");
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			String inici = df.format(dataInici);
			
			//Calculamos el total de litros que han entrado
			String q = 	"select sum(edi.litres) " +
						"  from EntradaDiposit edi " +
						" where edi.elaboracio is not null " +
						" and edi.valid = true " +
						" and edi.data >= '"+inici+"' ";
			if (dataFi != null) {
				String fi = df.format(dataFi);
				q += " and edi.data <= '"+ fi +"' ";
			}
			if (establimentId!=null) q += " and edi.establiment.id="+establimentId;
			if(categorias!= null && categorias.length >0){
				q = q+" and edi.elaboracio.categoriaOli.id in(:categorias) ";
				col = getHibernateTemplate().findByNamedParam(q, "categorias", categorias);
			} else {
				col = getHibernateTemplate().find(q);
			}

			if (col != null && col.size() > 0 && col.get(0) != null) totalOli = (Double)col.get(0);

		} catch (HibernateException ex) {
			logger.error("getQuantitatOliElaboratDiscriminantQualificacioAndEstabliment failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getQuantitatOliElaboratDiscriminantQualificacioAndEstabliment fin");
		return totalOli;
	}		
	
	
	
//	/**
//	 * Devuelve la cantidad total de litros de aceite existente para un establecimiento y categoria
//	 * @return
//	 * @throws InfrastructureException
//	 */
//	public Double getQuantitatOliExistenciesDiscriminantQualificacioAndEstabliment (Long temporadaId, Long establimentId, Date dataInici, Object[] categorias) throws InfrastructureException {
//		Collection col;
//		Double numeroEntradas = null;
//		Double numeroSalidas = null;
//		double totalOli = 0;
//
//		try {				
//			logger.debug("getQuantitatOliExistenciesDiscriminantQualificacioAndEstabliment ini");
//
//			
//			//Calculamos el total de litros que han entrado
//			String q = "select sum(edi.litres) from EntradaDiposit edi where edi.establiment.campanya.id="+temporadaId+" and edi.litres > 0 ";
//			q += " and edi.valid = true ";
//			
//			if (establimentId!=null){
//				q += " and edi.establiment.id="+establimentId;
//			}
//			
//			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
//			if(dataInici != null){
//				String fi = df.format(dataInici);
//				q += " and  edi.data <= '"+fi+"' ";
//			}
//			
//			if(categorias!= null && categorias.length >0){
//				q = q+" and edi.diposit.partidaOli.categoriaOli.id in(:categorias) ";
//				col = getHibernateTemplate().findByNamedParam(q, "categorias", categorias);
//			} else {
//				col = getHibernateTemplate().find(q);
//			}
//
//			while (col.iterator().hasNext()){
//				numeroEntradas = (Double)col.iterator().next();
//				break;
//			}
//
//			// Calculamos el total de litros que han salido
//			q = "select sum(sdi.litres) from SortidaDiposit sdi where sdi.establiment.campanya.id="+temporadaId;
//			q += " and sdi.valid = true ";
//			
//			if (establimentId!=null){
//				q += " and sdi.establiment.id="+establimentId;
//			}
//			
//			if(dataInici != null){
//				String fi = df.format(dataInici);
//				q += " and  sdi.data <= '"+fi+"' ";
//			}
//			
//			if(categorias!= null && categorias.length >0){
//				q = q+" and sdi.dipositBySdiCoddor.partidaOli.categoriaOli.id in(:categorias) ";
//				col = getHibernateTemplate().findByNamedParam(q, "categorias", categorias);
//			} else {
//				col = getHibernateTemplate().find(q);
//			}
//
//			while (col.iterator().hasNext()){
//				numeroSalidas = (Double)col.iterator().next();
//				break;
//			}
//
//
//			if(numeroEntradas != null)totalOli+=numeroEntradas.doubleValue();			
//			if(numeroSalidas != null && numeroSalidas.doubleValue() >0 )totalOli-=numeroSalidas.doubleValue();
//			
//		} catch (HibernateException ex) {
//			logger.error("getQuantitatOliExistenciesDiscriminantQualificacioAndEstabliment failed", ex);
//			throw new InfrastructureException(ex);
//		}
//		logger.debug("getQuantitatOliExistenciesDiscriminantQualificacioAndEstabliment fin");
//		return Double.valueOf(String.valueOf(totalOli));
//	}
	
	/**
	 * Devuelve la cantidad total de litros de aceite existente para un establecimiento y categoria
	 * @return
	 * @throws InfrastructureException
	 */
	public Double getQuantitatOliExistenciesDiscriminantQualificacioAndEstabliment (Long establimentId, Date dataInici, Object[] categorias) throws InfrastructureException {
		List col;
		Double numeroEntradas = 0.0;
		Double numeroSalidas = 0.0;
		double totalOli = 0;

		try {				
			logger.debug("getQuantitatOliExistenciesDiscriminantQualificacioAndEstabliment ini");
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			String fi = df.format(dataInici);
			
			//Calculamos el total de litros que han entrado
			String q = 	"select sum(edi.litres) " +
						"  from EntradaDiposit edi " +
						" where edi.litres > 0 " +
						" and edi.valid = true " +
						" and  edi.data <= '"+fi+"' ";			
			if (establimentId!=null) q += " and edi.establiment.id="+establimentId;
			if(categorias!= null && categorias.length >0){
				q = q+" and edi.diposit.partidaOli.categoriaOli.id in(:categorias) ";
				col = getHibernateTemplate().findByNamedParam(q, "categorias", categorias);
			} else {
				col = getHibernateTemplate().find(q);
			}
			if (col != null && col.size() > 0 && col.get(0) != null) numeroEntradas = (Double)col.get(0);

			// Calculamos el total de litros que han salido
			q = 	"select sum(sdi.litres) " +
					"  from SortidaDiposit sdi " +
					" where sdi.valid = true " +
					" and  sdi.data <= '"+fi+"' ";			
			if (establimentId!=null) q += " and sdi.establiment.id="+establimentId;
			if(categorias!= null && categorias.length >0){
				q = q+" and sdi.dipositBySdiCoddor.partidaOli.categoriaOli.id in(:categorias) ";
				col = getHibernateTemplate().findByNamedParam(q, "categorias", categorias);
			} else {
				col = getHibernateTemplate().find(q);
			}
			if (col != null && col.size() > 0 && col.get(0) != null) numeroSalidas = (Double)col.get(0);

			totalOli += numeroEntradas.doubleValue() - numeroSalidas.doubleValue();
		} catch (HibernateException ex) {
			logger.error("getQuantitatOliExistenciesDiscriminantQualificacioAndEstabliment failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getQuantitatOliExistenciesDiscriminantQualificacioAndEstabliment fin");
		return Double.valueOf(String.valueOf(totalOli));
	}
	

	
	
	/**
	 * Devuelve la cantidad total de litros de aceite existente de la temporada anterior
	 * @param temporadaId
	 * @param dataInici
	 * @param dataFin
	 * @return
	 * @throws InfrastructureException
	 */
	public Double getExistenciaOliTempAnteriorByCategoriaAndFecha(Long anteriorTemporadaId,Object[] categorias) throws InfrastructureException {
		Collection col;
		Double numeroEntradas = null;
		Double numeroSalidas = null;
		double totalOli = 0;

		try {				
			logger.debug("getExistenciaOliTempAnteriorByCategoriaAndFecha ini");

			//Calculamos el total de litros que han entrado
			String q = "select sum(edi.litres) from EntradaDiposit edi where edi.establiment.campanya.id="+anteriorTemporadaId;
			if(categorias!= null && categorias.length >0){
				q = q+" and edi.diposit.partidaOli.categoriaOli.id in(:categorias) ";
			}
			//			Query query = getHibernateTemplate().createQuery(q);
			if(categorias!= null && categorias.length >0){
				//				query.setParameterList("categorias", categorias);
				col = getHibernateTemplate().findByNamedParam(q, "categorias", categorias);
			} else {
				col = getHibernateTemplate().find(q);
			}
			//			col = query.list();
			while (col.iterator().hasNext()){
				numeroEntradas = (Double)col.iterator().next();
				break;
			}

			// Calculamos el total de litros que han salido
			q = "select sum(sdi.litres) from SortidaDiposit sdi where sdi.establiment.campanya.id="+anteriorTemporadaId;
			if(categorias!= null && categorias.length >0){
				q = q+" and sdi.dipositBySdiCoddor.partidaOli.categoriaOli.id in(:categorias) ";
			}
			//			query = getHibernateTemplate().createQuery(q);
			if(categorias!= null && categorias.length >0){
				//				query.setParameterList("categorias", categorias);
				col = getHibernateTemplate().findByNamedParam(q, "categorias", categorias);
			} else {
				col = getHibernateTemplate().find(q);
			}
			//			col = query.list();
			while (col.iterator().hasNext()){
				numeroSalidas = (Double)col.iterator().next();
				break;
			}

			if(numeroEntradas != null ){
				totalOli = numeroEntradas.doubleValue();
				if(numeroSalidas != null){
					totalOli = numeroEntradas.doubleValue() - numeroSalidas.doubleValue();
				}
			}

		} catch (HibernateException ex) {
			logger.error("getExistenciaOliTempAnteriorByCategoriaAndFecha failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getExistenciaOliTempAnteriorByCategoriaAndFecha fin");
		return Double.valueOf(String.valueOf(totalOli));
	}	
	
	
	
	
	/**
	 * Devuelve la cantidad total de litros de aceite que ha cambiado de categoria 
	 * @return
	 * @throws InfrastructureException
	 */
	public Double getQuantitatOliCanviatDO (Long temporadaId, Long establimentId, Date dataInici, Object[] categorias, Boolean aOliDesqualificat) throws InfrastructureException {
	
		Double totalOli=null;
		
		try{
			logger.debug("getQuantitatOliCanviatDO ini");
	
			String q = "from Analitica ana where ana.desqualificatDiposit='"+aOliDesqualificat+"'";
			
			if (establimentId!=null){
				q+=" and ana.establiment.id="+establimentId;
			}
			
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			if(dataInici != null){
				String fi = df.format(dataInici);
				q += " and  ana.data >= '"+fi+"' ";
			}
			
			/*
			select ana.id,ana.traza.trazasForTtrCodtrapare 
			
			List trazasPadre = new ArrayList();
			Collection analiticas=getHibernateTemplate().find(q);
			Iterator ite = analiticas.iterator();
			while (ite.hasNext()){
				Analitica ana = (Analitica)ite.next();
				Traza tra = ana.getTraza();
				trazasPadre.add(tra.getId());
			}
			
			Collection col=null;
			
			// Si el aceite pasa de DO a NO DO hay que mirar si la entrada con "pendiente de calificar" tenÃƒÂ­a una analÃƒÂ­tica previa
			// que lo convertÃƒÂ­a en DO
			if (!aOliDesqualificat.booleanValue()){
				
				q = "select sum(edi.litres) from EntradaDiposit edi where edi.establiment.campanya.id="+temporadaId;
				q+= " and edi.traza.id in (:trazasPadre) ";
				
				if(categorias!= null && categorias.length >0){
					
					String [] nombres = {"trazasPadre","categorias"};
					Object [] listas = {trazasPadre, categorias};
					
					q = q+" and edi.partidaOli.categoriaOli.id in(:categorias) ";
					col = getHibernateTemplate().findByNamedParam(q, nombres, listas);
				} else {
					col = getHibernateTemplate().findByNamedParam(q, "trazasPadre", trazasPadre);
				}
				
			}else{
				
				q= "from Analitica ana where ana.id < ";
				
			}

			if (col.size()>0){
				totalOli = (Double)col.iterator().next();
			}
			if (totalOli==null){
				totalOli=new Double(0);
			}
			*/

			logger.debug("getQuantitatOliCanviatDO fin");
		} catch (HibernateException ex) {
			logger.error("getQuantitatOliCanviatDO failed", ex);
			throw new InfrastructureException(ex);
		}
	
		return totalOli;
	
	}

	/**
	 * Informació referent a l'entrada d'oliva al dipòsit.
	 * 
	 * @param dipositId
	 * @return
	 */
	public Object[] getInfoOliva(Long dipositId) {
		String q =	"select el.responsable, el.data " +
					"from Diposit d, " +
					"	EntradaDiposit e, " +
					"	Elaboracio el " +
					"where d.id = " + dipositId + " " +
					"and e.diposit.id = d.id " +
					"and el.id = e.elaboracio.id";
		List info = getHibernateTemplate().find(q);
		Object[] obj = new Object[2];
		if (info.size() > 0) {
			Object[] objInfo = (Object[])(info.get(0));
			obj[0] = objInfo[0];
			obj[1] = objInfo[1];
		}
		return obj;
	}

	/**
	 * Comprova si la partida d'oli tÃƒÂ© dipÃƒÂ²sits associats
	 * @param lid
	 * @return
	 */
	public Boolean existeixenDipositsAssociatsPartidesOli(Long idPartidaOli ) {
		List diposits;
		try {
			logger.debug("existeixenDipositsAssociatsPartidesOli ini");
			String q = "select count(dip.id) from Diposit dip " +
			"where dip.partidaOli.id = " + idPartidaOli + " ";
			diposits = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("existeixenDipositsAssociatsPartidesOli failed", ex);
			throw new InfrastructureException(ex);
		}
		if (diposits != null && diposits.size()> 0 && ((Long)diposits.get(0)).intValue() > 0) {
			logger.debug("existeixenDipositsAssociatsPartidesOli fin");
			return true;
		}
		logger.debug("existeixenDipositsAssociatsPartidesOli fin");
		return false;
	}
	
	/**
	 * Comprova si el dipòsit ha estat buidata posteriorment a la traça esmentada
	 * @param idDiposit
	 * @param idTraza
	 * @return
	 */
	public boolean haEstatBuidatPosteriormentATraza(Long idDiposit, Long idTraza) {
		List entrades;
		List sortides;
		String q = "";
		try {
			logger.debug("haEstatBuidatPosteriormentATraza ini");
			
			// Si el dipòsit ara mateix és buit, significa que ha estat buidat (perogrullada)
			Diposit dip = getById(idDiposit);
			if (dip.getVolumActual() == null || dip.getVolumActual() <= 0) return true;
			
			// Si el dipòsit té alguna entrada sense
			q = "from EntradaDiposit edi " +
				"where edi.diposit.id = " + idDiposit + " " +
				"and edi.traza.id > " + idTraza + " " +
				"order by edi.traza.id";
			entrades = getHibernateTemplate().find(q);
			q = "from SortidaDiposit sdi " +
				"where sdi.dipositBySdiCoddor.id = " + idDiposit + " " +
				"and sdi.traza.id > " + idTraza + " " +
				"order by sdi.traza.id";
			sortides = getHibernateTemplate().find(q);
			
			Double volum = dip.getVolumActual();
			int ent = 0;
			int sort = 0;
			
			while (volum > 0 && (ent < entrades.size() || sort < sortides.size())){
				EntradaDiposit edi = null;
				SortidaDiposit sdi = null;
				
				if (ent < entrades.size()){
					edi = (EntradaDiposit)entrades.get(ent);
				}
				if (sort < sortides.size()){
					sdi = (SortidaDiposit)sortides.get(sort);
				}
				
				if (edi != null && sdi != null){
					if (edi.getTraza().getId() < sdi.getTraza().getId()){
						volum += edi.getLitres();
						ent++;
					} else {
						volum -= sdi.getLitres();
						sort++;
					}
				} else if (edi != null){
					volum += edi.getLitres();
					ent++;
				} else {
					volum -= sdi.getLitres();
					sort++;
				}
			}
			if (volum <= 0) return true;
		} catch (HibernateException ex) {
			logger.error("haEstatBuidatPosteriormentATraza failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("haEstatBuidatPosteriormentATraza fin");
		return false;
	}
	
	
	/**
	 * Retorna els dipòsits associats a una partida.
	 * @param idPartidaOli
	 * @return
	 */
	

	@SuppressWarnings({ "unused", "rawtypes" })
	public List findDipositsAmbPartidaId(Long idPartidaOli){
	
	List diposits;
	try {
		logger.debug("findDipositsAmbPartidaId ini");
		String q = "select dip from Diposit dip " +
		"where dip.partidaOli.id = " + idPartidaOli + " ";
		diposits = getHibernateTemplate().find(q);
	} catch (HibernateException ex) {
		logger.error("existeixenDipositsAssociatsPartidesOli failed", ex);
		throw new InfrastructureException(ex);
	}
	
	return diposits;
	}
	
	
	/**
	 * Comprova si la partida d'oli està a més d'un dipòsit
	 * @param idPartidaOli
	 * @return
	 */
	public Boolean hiHaAltresDipositsAssociatsPartidesOli(Long idPartidaOli ) {
		List diposits;
		try {
			logger.debug("hiHaAltresDipositsAssociatsPartidesOli ini");
			String q = "select count(dip.id) from Diposit dip " +
			"where dip.partidaOli.id = " + idPartidaOli + " ";
			diposits = getHibernateTemplate().find(q);
		} catch (HibernateException ex) {
			logger.error("hiHaAltresDipositsAssociatsPartidesOli failed", ex);
			throw new InfrastructureException(ex);
		}
		int numero = new Integer(0);
		if(diposits.size()>0){
			numero = Integer.parseInt(diposits.get(0).toString());
		}
		if (numero >1) {
			logger.debug("hiHaAltresDipositsAssociatsPartidesOli fin");
			return true;
		}
		logger.debug("hiHaAltresDipositsAssociatsPartidesOli fin");
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
