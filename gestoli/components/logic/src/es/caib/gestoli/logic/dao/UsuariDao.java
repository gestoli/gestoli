package es.caib.gestoli.logic.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.exception.InfrastructureException;
import es.caib.gestoli.logic.model.Usuari;


/**
 * Home object for domain model class Usuari
 * @see es.caib.gestoli.logic.model.Usuari
 * @author Oriol Barnés
 */
public class UsuariDao {
	private static Logger logger = Logger.getLogger(UsuariDao.class);
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
			col = getHibernateTemplate().find("from Usuari as usu order by usu.usuari");			
		} catch (HibernateException ex) {
			logger.error("findAll failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findAll fin");
		return col;
	}

	/**
	 * Recupera todos los registros d'usuaris no olivicultors
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection findAllNoOlivicultors() {
		Collection col;
		try {
			logger.debug("findAll ini");
			String q = 	"select distinct usu " +
						"from Usuari as usu " +
						"left join usu.grups gr " +
						"where gr.id != 'OLI_OLIVICULTOR' " +
						"order by usu.usuari";
			col = getHibernateTemplate().find(q);			
		} catch (HibernateException ex) {
			logger.error("findAll failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findAll fin");
		return col;
	}
	
	public Collection findUsuarisEstabliment(Long idEstabliment) {
		Collection col;
		try {
			logger.debug("findAll ini");
			String q = 	" select distinct usu " +
						" from Usuari as usu " +
						" left join usu.establiments est " +
						" where est.id = "+idEstabliment+
						" order by usu.usuari";
			col = getHibernateTemplate().find(q);			
		} catch (HibernateException ex) {
			logger.error("findAll failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("findAll fin");
		return col;
	}

	/**
	 * Recupera todos los registros activos
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection findAllActivos() throws InfrastructureException {
		Collection col;
		try {
			logger.debug("findAll ini");
			col = getHibernateTemplate().find("from Usuari as usu where usu.actiu = true order by usu.usuari");			
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
	public Usuari getById(Long id) throws InfrastructureException {
//		Usuari usuari;
//		try {
//			logger.debug("getById ini");
//			usuari = (Usuari)getHibernateTemplate().load(Usuari.class, id);	
//			
//		} catch (HibernateException ex) {
//			logger.error("getById failed", ex);
//			throw new InfrastructureException(ex);
//		}
//		logger.debug("getById fin");
//		return usuari;
//		
//		
		List usuaris;
		try {
			logger.debug("getById ini");
			String q = "from Usuari as usu where usu.id = "+id;
			usuaris = getHibernateTemplate().find(q);
			if (usuaris.size() > 0) {
				logger.debug("getById fin");
				return (Usuari)usuaris.get(0);
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
	public Usuari getByUsuario(String usuario) throws InfrastructureException {
		List usuarios;

		try {
			String q = "from Usuari as usu where usu.usuari = '" + usuario + "'";
			usuarios = getHibernateTemplate().find(q);
			if (usuarios.size() > 0) {
				logger.debug("getByUsuario fin");
				return (Usuari)usuarios.get(0);
			}
		} catch (HibernateException ex) {
			logger.error("getByUsuario failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getByUsuario fin");
		return null;
	}


	/**
	 * Devuelve un usuario de la BBDD a partir de su nombre
	 * @params name
	 * @return
	 * @throws InfrastructureException
	 */
	public Long getUsuari(String name) throws InfrastructureException {
		List usuarios;

		try {
			logger.debug("getUsuari ini");
			String q = "select usu.id from Usuari as usu where usu.usuari = '"+name+"'";
			usuarios = getHibernateTemplate().find(q);
			if (usuarios.size() > 0) {
				logger.debug("getUsuari fin");
				return (Long) usuarios.get(0);
			}
		} catch (HibernateException ex) {
			logger.error("getUsuari failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getUsuari fin");
		return null;
	}

	/**
	 * Devuelve un usuario de la BBDD a partir de su nombre
	 * @params name
	 * @return
	 * @throws InfrastructureException
	 */
	public List getEmailsByGrup(String grup) throws InfrastructureException {
		List mails;
		try {
			logger.debug("getEmailsByGrup ini");
			String q = "select usu.email from Usuari usu where usu in (select elements (gru.usuaris) from Grup gru where gru.id = '"+grup+"' ) ";
			mails = getHibernateTemplate().find(q);
			if (mails.size() > 0) {
				return mails;
			}
		} catch (HibernateException ex) {
			logger.error("getEmailsByGrup failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("getEmailsByGrup fin");
		return null;
	}

	public boolean pertanyEstablimentAUsuari(Long idEstabliment, Long idUsuari){
		List resposta = new ArrayList();
		List resposta2 = new ArrayList();

		try {
			logger.debug("pertanyEstablimentAUsuari ini");
			String q = "select e " +
					"from Establiment e " +
					"left join e.usuaris u " +
					"where " +
					"	e.id = "+idEstabliment+
					"	and u.id = "+idUsuari;
			resposta = getHibernateTemplate().find(q);
			
//			String w = "from " +
//			"	Usuari u, Grup g " +
//			"where " +
//			"	u.id in g.usuaris" +
//			"	and (g.nom = 'OLI_OLIVICULTOR' or g.nom = 'OLI_ENVASADOR' or g.nom = 'OLI_PRODUCTOR')"+
//			"	and u.id = "+idUsuari;
//			resposta2 = getHibernateTemplate().find(w);
//			
//			if (resposta.size() > 0 || resposta2.size() > 0) {
			if (resposta.size() > 0) {
				logger.debug("pertanyEstablimentAUsuari fin");
				return true;
			}
		} catch (HibernateException ex) {
			logger.error("pertanyEstablimentAUsuari failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("pertanyEstablimentAUsuari fin");
		return false;
	}
	
	public boolean pertanyOlivicultorAUsuari(Long idOlivicultor, Long idUsuari){
		List resposta = new ArrayList();

		try {
			logger.debug("pertanyOlivicultorAUsuari ini");
			String q = "from " +
					"	Usuari u, Olivicultor o " +
					"where " +
					"	o.usuari.id = u.id " +
					"	and o.id = "+idOlivicultor+
					"	and u.id = "+idUsuari;

			resposta = getHibernateTemplate().find(q);
			if (resposta.size() > 0) {
				logger.debug("pertanyOlivicultorAUsuari fin");
				return true;
			}
		} catch (HibernateException ex) {
			logger.error("pertanyOlivicultorAUsuari failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("pertanyOlivicultorAUsuari fin");
		return false;
	}
	
	public boolean pertanyGrupAUsuari(Long idGrup, Long idUsuari){
		List resposta = new ArrayList();

		try {
			logger.debug("pertanyGrupAUsuari ini");
			String q = "from " +
					"	Usuari u, Grup g " +
					"where " +
					"	g.usuari.id = u.id " +
					"	and g.id = "+idGrup+
					"	and u.id = "+idUsuari;

			resposta = getHibernateTemplate().find(q);
			if (resposta.size() > 0) {
				logger.debug("pertanyGrupAUsuari fin");
				return true;
			}
		} catch (HibernateException ex) {
			logger.error("pertanyGrupAUsuari failed", ex);
			throw new InfrastructureException(ex);
		}
		logger.debug("pertanyGrupAUsuari fin");
		return false;
	}
	
	/**
	 * Actualiza un registro
	 * @param usuari
	 * @throws InfrastructureException
	 */
	public void makePersistent(Usuari usuari) throws InfrastructureException {
		try {
			logger.debug("makePersistent ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
//			if (usuari.getId() != null) {
//				getHibernateTemplate().evict(this.getById(usuari.getId()));
//				getHibernateTemplate().update(usuari);
//			}
//			else getHibernateTemplate().save(usuari);
			getHibernateTemplate().saveOrUpdate(usuari);
			getHibernateTemplate().flush();
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
	public void makeTransient(Usuari usuari) throws InfrastructureException {
		try {
			logger.debug("makeTransient ini");
			getHibernateTemplate().setFlushMode(HibernateTemplate.FLUSH_ALWAYS);
			getHibernateTemplate().delete(usuari);
			getHibernateTemplate().flush();
			logger.debug("makeTransient fin");
		} catch (HibernateException ex) {
			logger.error("makeTransient failed",ex);
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