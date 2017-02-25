
package es.caib.gestoli.logic.ejb;

import java.util.Iterator;
import java.util.Set;

import javax.ejb.SessionContext;

import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.dao.OlivicultorDao;
import es.caib.gestoli.logic.dao.UsuariDao;
import es.caib.gestoli.logic.model.Grup;
import es.caib.gestoli.logic.model.Idioma;
import es.caib.gestoli.logic.model.Usuari;


/**
 *
 * <!-- begin-user-doc -->
 * A generated session bean
 * <!-- end-user-doc -->
 * *
 * <!-- begin-xdoclet-definition --> 
 * @ejb.bean name="OliUsuariEjb"	
 *           description="An EJB named OliUsuariEjb"
 *           display-name="OliUsuariEjb"
 *           jndi-name="OliUsuariEjb"
 *           local-jndi-name="OliUsuariEjbLocal"
 *           type="Stateless" 
 *           transaction-type="Container"
 * @ejb.transaction type="Required"
 * 
 * <!-- end-xdoclet-definition --> 
 */


public abstract class OliUsuariEjbBean implements javax.ejb.SessionBean {
	
	private UsuariDao usuariDao;
	private OlivicultorDao olivicultorDao;
	private SessionContext sessionContext;
	private HibernateTemplate hibernateTemplate;

	
	private static final String ROL_TAFONA = "OLI_PRODUCTOR";
	private static final String ROL_ENVASADOR = "OLI_ENVASADOR";
		
	/**
	 * Emmagatzema el SessionContext per poder-lo emprar als
	 * mètodes de negoci de l'EJB.
	 * 
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * <!-- end-xdoclet-definition --> 
	 */
	public void setSessionContext(SessionContext context) {
        sessionContext = context;
    }

	/**
	 * Crea una instància de tots els DAOs necessàris per
	 * als mètodes de negoci.
	 *
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.create-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * <!-- end-xdoclet-definition --> 
	 */
	public void ejbCreate() {
		usuariDao = new UsuariDao();
		olivicultorDao = new OlivicultorDao();
	}

	/**
	 * Només serveix per poder especificar els permisos amb
	 * xdoclet.
	 * 
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * <!-- end-xdoclet-definition --> 
	 */
	public void ejbRemove() {}



	/**
	 * Retorna el registre de tipus usuari que té el login
	 * especificat.
	 * 
	 * @return el registre de tipus usuari
	 *
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * <!-- end-xdoclet-definition --> 
	 */
	public Usuari verificaLogin() {
		String login = sessionContext.getCallerPrincipal().getName();
		if (login == null) return null;
		usuariDao.setHibernateTemplate(getHibernateTemplate());
		Usuari u = usuariDao.getByUsuario(login);
		if (u == null) {
			// 20090421 obarnes: creo que nunca entra porque seycon ya valida que este usuario no existe
			Idioma idioma = new Idioma();
			idioma.setId("ca");
			u = new Usuari(idioma, new Boolean(true), login, login);
			usuariDao.setHibernateTemplate(getHibernateTemplate());
			usuariDao.makePersistent(u);
		}
		return u;
	}

	
	
	/**
	 * Comprova si l'establiment és correcte per un determinat usuari
	 * 
	 * @param idEstabliment
	 * @param usuari
	 * @return
	 * 
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * <!-- end-xdoclet-definition --> 
	 */
	public boolean verificaEstabliment(Long idEstabliment, Long idUsuari){
		usuariDao.setHibernateTemplate(getHibernateTemplate());
		return usuariDao.pertanyEstablimentAUsuari(idEstabliment, idUsuari);
	}
	
	/**
	 * Comprova si l'olivicultor és correcte per un determinat usuari
	 * 
	 * @param idOlivicultor
	 * @param usuari
	 * @return
	 * 
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * <!-- end-xdoclet-definition --> 
	 */
	public boolean verificaOlivicultor(Long idOlivicultor, Long idUsuari){
		usuariDao.setHibernateTemplate(getHibernateTemplate());
		return usuariDao.pertanyOlivicultorAUsuari(idOlivicultor, idUsuari);
	}
	
	/**
	 * Comprova si un grup és correcte per un determinat usuari.
	 * 
	 * @param idGrup
	 * @param usuari
	 * @return
	 * 
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * <!-- end-xdoclet-definition --> 
	 */
	public boolean verificaGrup(Long idGrup, Long idUsuari){
		usuariDao.setHibernateTemplate(getHibernateTemplate());
		return usuariDao.pertanyGrupAUsuari(idGrup, idUsuari);
	}
	
	
	/**
	 * Comprueba si existen olivicultores altaDO en la BBDD <!--
	 * begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public boolean existenOlivicultoresAltaDOConFincas() {
		olivicultorDao.setHibernateTemplate(getHibernateTemplate());
		return olivicultorDao.existenOlivicultoresAltaDOConFincas().booleanValue();
	}

	
	/**
	 * set the hibernate template.
	 * @param hibernateTemplate the hibernate spring template.
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * <!-- end-xdoclet-definition --> 
	 */
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate){
		this.hibernateTemplate = hibernateTemplate;
	}


	/**
	 * get the hibernate template.
	 * @return the hibernate spring template.
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * <!-- end-xdoclet-definition --> 
	 */
	public HibernateTemplate getHibernateTemplate(){
		return this.hibernateTemplate;
	}


}