
package es.caib.gestoli.logic.ejb;

import java.util.Collection;

import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.dao.ArxiuDao;
import es.caib.gestoli.logic.model.Arxiu;

/**
 *
 * <!-- begin-user-doc -->
 * A generated session bean
 * <!-- end-user-doc -->
 * *
 * <!-- begin-xdoclet-definition --> 
 * @ejb.bean name="OliArxiuEjb"	
 *           description="An EJB named OliArxiuEjb"
 *           display-name="OliArxiuEjb"
 *           jndi-name="OliArxiuEjb"
 *           local-jndi-name="OliArxiuEjbLocal"
 *           type="Stateless" 
 *           transaction-type="Container"
 * @ejb.transaction type="Required"
 * 
 * <!-- end-xdoclet-definition --> 
 */
public abstract class OliArxiuEjbBean implements javax.ejb.SessionBean {

	private ArxiuDao arxiuDao;
	private HibernateTemplate hibernateTemplate;


	/** 
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
		arxiuDao = new ArxiuDao();
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
	public Long arxiuCrear(String nom, Integer tamany, String tipusMime, byte[] contingut) {
		Arxiu arxiu = new Arxiu();
		arxiu.setNom(nom);
		arxiu.setTamany(tamany);
		arxiu.setMime(tipusMime);
		arxiu.setBinari(contingut);
		arxiuDao.setHibernateTemplate(getHibernateTemplate());
		arxiuDao.makePersistent(arxiu);
		return arxiu.getId();
	}

	/** 
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
	public void arxiuModificar(Long id, String nom, Integer tamany, String tipusMime, byte[] contingut) {
		Arxiu arxiu = new Arxiu();
		arxiu.setId(id);
		arxiu.setNom(nom);
		arxiu.setTamany(tamany);
		arxiu.setMime(tipusMime);
		arxiu.setBinari(contingut);
		arxiuDao.setHibernateTemplate(getHibernateTemplate());
		arxiuDao.makePersistent(arxiu);
	}

	/** 
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
	public void arxiuEsborrar(Long id) {
		
		arxiuDao.setHibernateTemplate(getHibernateTemplate());
		Arxiu arxiu = arxiuDao.getById(id);
		if (arxiu != null) {
			arxiuDao.setHibernateTemplate(getHibernateTemplate());
			arxiuDao.makeTransient(arxiu);
		}
		
	}

	/** 
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
	public Collection arxiuCercaTots() {
		arxiuDao.setHibernateTemplate(getHibernateTemplate());
		return arxiuDao.findAll();
	}
	
	/** 
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
	public Arxiu arxiuCercaId(Long id){
		arxiuDao.setHibernateTemplate(getHibernateTemplate());
		return arxiuDao.getById(id);
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