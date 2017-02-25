package es.caib.gestoli.logic.ejb;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.ejb.SessionBean;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.dao.ArxiuDao;
import es.caib.gestoli.logic.dao.DocumentDao;
import es.caib.gestoli.logic.dao.GestorDocumentalCategoriaInformacioDao;
import es.caib.gestoli.logic.dao.GestorDocumentalDocumentDao;
import es.caib.gestoli.logic.dao.GestorDocumentalInformacioDao;
import es.caib.gestoli.logic.dao.QualitatAPPCCControlDao;
import es.caib.gestoli.logic.dao.QualitatAPPCCDao;
import es.caib.gestoli.logic.dao.QualitatAPPCCEtapaDao;
import es.caib.gestoli.logic.dao.QualitatAPPCCEtapaPerillDao;
import es.caib.gestoli.logic.dao.QualitatAPPCCFitxaControlDao;
import es.caib.gestoli.logic.dao.QualitatAPPCCPlaVerificacioDao;
import es.caib.gestoli.logic.dao.QualitatAiguaControlAnaliticDao;
import es.caib.gestoli.logic.dao.QualitatAiguaControlAnaliticVerificacioDao;
import es.caib.gestoli.logic.dao.QualitatAiguaControlOrganolepticDao;
import es.caib.gestoli.logic.dao.QualitatAiguaControlOrganolepticVerificacioDao;
import es.caib.gestoli.logic.dao.QualitatAiguaPuntSortidaDao;
import es.caib.gestoli.logic.dao.QualitatAiguaSubministreDao;
import es.caib.gestoli.logic.dao.QualitatControlDao;
import es.caib.gestoli.logic.dao.QualitatControlPlaguesDao;
import es.caib.gestoli.logic.dao.QualitatControlPlaguesVerificacioDao;
import es.caib.gestoli.logic.dao.QualitatDepartamentDao;
import es.caib.gestoli.logic.dao.QualitatDescripcioEquipsDao;
import es.caib.gestoli.logic.dao.QualitatDescripcioInstalacioDao;
import es.caib.gestoli.logic.dao.QualitatDescripcioPersonalDao;
import es.caib.gestoli.logic.dao.QualitatFormacioEvaluacioDao;
import es.caib.gestoli.logic.dao.QualitatNoConformitatDao;
import es.caib.gestoli.logic.dao.QualitatPlaControlTransportDao;
import es.caib.gestoli.logic.dao.QualitatPlaFormacioDao;
import es.caib.gestoli.logic.dao.QualitatPlaMantenimentControlDao;
import es.caib.gestoli.logic.dao.QualitatPlaMantenimentDao;
import es.caib.gestoli.logic.dao.QualitatPlaNetejaDao;
import es.caib.gestoli.logic.dao.QualitatPlaNetejaRealitzacioDao;
import es.caib.gestoli.logic.dao.QualitatPlaNetejaVerificacioDao;
import es.caib.gestoli.logic.dao.QualitatProveidorAvaluacioDao;
import es.caib.gestoli.logic.dao.QualitatProveidorDao;
import es.caib.gestoli.logic.dao.QualitatProveidorRGSADao;
import es.caib.gestoli.logic.dao.QualitatPuestoTreballDao;
import es.caib.gestoli.logic.dao.QualitatSubministreDao;
import es.caib.gestoli.logic.dao.UsuariDao;
import es.caib.gestoli.logic.model.Arxiu;
import es.caib.gestoli.logic.model.GestorDocumentalCategoriaInformacio;
import es.caib.gestoli.logic.model.GestorDocumentalDocument;
import es.caib.gestoli.logic.model.GestorDocumentalInformacio;
import es.caib.gestoli.logic.model.QualitatAPPCC;
import es.caib.gestoli.logic.model.QualitatAPPCCControl;
import es.caib.gestoli.logic.model.QualitatAPPCCEtapa;
import es.caib.gestoli.logic.model.QualitatAPPCCEtapaPerill;
import es.caib.gestoli.logic.model.QualitatAPPCCFitxaControl;
import es.caib.gestoli.logic.model.QualitatAPPCCFitxaControlHistoric;
import es.caib.gestoli.logic.model.QualitatAPPCCPlaVerificacio;
import es.caib.gestoli.logic.model.QualitatAPPCCProducte;
import es.caib.gestoli.logic.model.QualitatAiguaControlAnalitic;
import es.caib.gestoli.logic.model.QualitatAiguaControlAnaliticVerificacio;
import es.caib.gestoli.logic.model.QualitatAiguaControlOrganoleptic;
import es.caib.gestoli.logic.model.QualitatAiguaControlOrganolepticVerificacio;
import es.caib.gestoli.logic.model.QualitatAiguaPuntSortida;
import es.caib.gestoli.logic.model.QualitatAiguaSubministre;
import es.caib.gestoli.logic.model.QualitatControl;
import es.caib.gestoli.logic.model.QualitatControlPlagues;
import es.caib.gestoli.logic.model.QualitatControlPlaguesVerificacio;
import es.caib.gestoli.logic.model.QualitatDepartament;
import es.caib.gestoli.logic.model.QualitatDescripcioEquip;
import es.caib.gestoli.logic.model.QualitatDescripcioInstalacio;
import es.caib.gestoli.logic.model.QualitatDescripcioPersonal;
import es.caib.gestoli.logic.model.QualitatFormacioEvaluacio;
import es.caib.gestoli.logic.model.QualitatNoConformitat;
import es.caib.gestoli.logic.model.QualitatNoConformitatAccio;
import es.caib.gestoli.logic.model.QualitatPlaControlTransport;
import es.caib.gestoli.logic.model.QualitatPlaFormacio;
import es.caib.gestoli.logic.model.QualitatPlaManteniment;
import es.caib.gestoli.logic.model.QualitatPlaMantenimentControl;
import es.caib.gestoli.logic.model.QualitatPlaNeteja;
import es.caib.gestoli.logic.model.QualitatPlaNetejaRealitzacio;
import es.caib.gestoli.logic.model.QualitatPlaNetejaVerificacio;
import es.caib.gestoli.logic.model.QualitatProveidor;
import es.caib.gestoli.logic.model.QualitatProveidorAvaluacio;
import es.caib.gestoli.logic.model.QualitatProveidorRGSA;
import es.caib.gestoli.logic.model.QualitatPuestoTreball;
import es.caib.gestoli.logic.model.QualitatSubministre;
import es.caib.gestoli.logic.model.Usuari;
import es.caib.gestoli.logic.util.QualitatDateHelper;




/**
 * 
 * <!-- begin-user-doc --> A generated session bean <!-- end-user-doc --> * <!--
 * begin-xdoclet-definition -->
 * 
 * @ejb.bean name="OliQualitatEjb"
 *           description="An EJB named OliQualitatEjb"
 *           display-name="OliQualitatEjb"
 *           jndi-name="OliQualitatEjb"
 *           local-jndi-name="OliQualitatEjbLocal"
 *           type="Stateless"
 *           transaction-type="Container"
 * @ejb.transaction type="Required"
 * 
 *           <!-- end-xdoclet-definition -->
 */

public abstract class OliQualitatEjbBean implements SessionBean {
	private static Logger logger = Logger.getLogger(OliQualitatEjbBean.class);

	
	private QualitatDescripcioInstalacioDao qualitatDescripcioInstalacioDao;
	private QualitatDescripcioEquipsDao qualitatDescripcioEquipDao;
	private QualitatDescripcioPersonalDao qualitatDescripcioPersonalDao;
	private QualitatPuestoTreballDao qualitatPuestoTreballDao;
	private QualitatPlaFormacioDao qualitatPlaFormacioDao;

	private GestorDocumentalCategoriaInformacioDao gestorDocumentalCategoriaInformacioDao;
	private GestorDocumentalDocumentDao gestorDocumentalDocumentDao;
	private GestorDocumentalInformacioDao gestorDocumentalInformacioDao;
	private ArxiuDao arxiuDao;
	private UsuariDao usuariDao;

	private QualitatFormacioEvaluacioDao qualitatFormacioEvaluacioDao;
	private QualitatProveidorDao qualitatProveidorDao;
	private QualitatSubministreDao qualitatSubministreDao;
	private QualitatProveidorRGSADao qualitatProveidorRGSADao;
	private QualitatProveidorAvaluacioDao qualitatProveidorAvaluacioDao;
	private QualitatPlaNetejaDao qualitatPlaNetejaDao;
	private QualitatPlaNetejaRealitzacioDao qualitatPlaNetejaRealitzacioDao;
	private QualitatPlaNetejaVerificacioDao qualitatPlaNetejaVerificacioDao;
	private QualitatPlaMantenimentDao qualitatPlaMantenimentDao;
	private QualitatPlaControlTransportDao qualitatPlaControlTransportDao;
	private QualitatPlaMantenimentControlDao qualitatPlaMantenimentControlDao;
	private QualitatControlPlaguesDao qualitatControlPlaguesDao;
	private QualitatControlPlaguesVerificacioDao qualitatControlPlaguesVerificacioDao;
	private QualitatNoConformitatDao qualitatNoConformitatDao;
	private QualitatDepartamentDao qualitatDepartamentDao;
	private QualitatControlDao qualitatControlDao;
	private QualitatAPPCCDao qualitatAPPCCDao;
	private QualitatAPPCCEtapaDao qualitatAPPCCEtapaDao;
	private QualitatAPPCCEtapaPerillDao qualitatAPPCCEtapaPerillDao;
	private QualitatAPPCCControlDao qualitatAPPCCControlDao;
	private QualitatAPPCCFitxaControlDao qualitatAPPCCFitxaControlDao;
	private QualitatAPPCCPlaVerificacioDao qualitatAPPCCPlaVerificacioDao;
	private DocumentDao documentDao;
	private QualitatAiguaSubministreDao qualitatAiguaSubministreDao;
	private QualitatAiguaPuntSortidaDao qualitatAiguaPuntSortidaDao;
	private QualitatAiguaControlAnaliticDao qualitatAiguaControlAnaliticDao;
	private QualitatAiguaControlOrganolepticDao qualitatAiguaControlOrganolepticDao;
	private QualitatAiguaControlAnaliticVerificacioDao qualitatAiguaControlAnaliticVerificacioDao;
	private QualitatAiguaControlOrganolepticVerificacioDao qualitatAiguaControlOrganolepticVerificacioDao;
	private HibernateTemplate hibernateTemplate;
						   

	/**
	 * Només serveix per poder especificar els permisos amb xdoclet. 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.create-method view-type="both"
	 * @ejb.permission role-name="OLI_ESTADMINISTRADOR"
	 * @ejb.permission role-name="OLI_ESTENCARREGAT"
	 * @ejb.permission role-name="OLI_ESTTREBALLADOR"
	 * @ejb.permission role-name="OLI_ESTCONSULTA"
	 * <!-- end-xdoclet-definition -->
	 */
	public void ejbCreate() {
		qualitatDescripcioInstalacioDao = new QualitatDescripcioInstalacioDao();
		qualitatDescripcioEquipDao = new QualitatDescripcioEquipsDao();
		qualitatDescripcioPersonalDao = new QualitatDescripcioPersonalDao();
		qualitatPuestoTreballDao = new QualitatPuestoTreballDao();
		qualitatPlaFormacioDao = new QualitatPlaFormacioDao();
		gestorDocumentalCategoriaInformacioDao = new GestorDocumentalCategoriaInformacioDao();
		gestorDocumentalDocumentDao = new GestorDocumentalDocumentDao();
		gestorDocumentalInformacioDao = new GestorDocumentalInformacioDao();
		arxiuDao = new ArxiuDao();
		usuariDao = new UsuariDao();
		qualitatFormacioEvaluacioDao = new QualitatFormacioEvaluacioDao();
		qualitatProveidorDao = new QualitatProveidorDao();
		qualitatProveidorAvaluacioDao = new QualitatProveidorAvaluacioDao();
		qualitatSubministreDao = new QualitatSubministreDao();
		qualitatProveidorRGSADao = new QualitatProveidorRGSADao();
		qualitatPlaNetejaDao = new QualitatPlaNetejaDao();
		qualitatPlaNetejaRealitzacioDao = new QualitatPlaNetejaRealitzacioDao();
		qualitatPlaNetejaVerificacioDao = new QualitatPlaNetejaVerificacioDao();
		qualitatPlaMantenimentDao = new QualitatPlaMantenimentDao();
		qualitatPlaMantenimentControlDao = new QualitatPlaMantenimentControlDao();
		qualitatPlaControlTransportDao = new QualitatPlaControlTransportDao();
		qualitatControlPlaguesDao = new QualitatControlPlaguesDao();
		qualitatControlPlaguesVerificacioDao = new QualitatControlPlaguesVerificacioDao();
		qualitatNoConformitatDao = new QualitatNoConformitatDao();
		qualitatDepartamentDao = new QualitatDepartamentDao();
		qualitatControlDao = new QualitatControlDao();
		qualitatAPPCCDao = new QualitatAPPCCDao();
		qualitatAPPCCEtapaDao = new QualitatAPPCCEtapaDao();
		qualitatAPPCCEtapaPerillDao = new QualitatAPPCCEtapaPerillDao();
		qualitatAPPCCControlDao = new QualitatAPPCCControlDao();
		qualitatAPPCCFitxaControlDao = new QualitatAPPCCFitxaControlDao();
		qualitatAPPCCPlaVerificacioDao = new QualitatAPPCCPlaVerificacioDao();
		documentDao = new DocumentDao();
		qualitatAiguaSubministreDao = new QualitatAiguaSubministreDao();
		qualitatAiguaPuntSortidaDao = new QualitatAiguaPuntSortidaDao();
		qualitatAiguaControlAnaliticDao = new QualitatAiguaControlAnaliticDao();
		qualitatAiguaControlOrganolepticDao = new QualitatAiguaControlOrganolepticDao();
		qualitatAiguaControlAnaliticVerificacioDao = new QualitatAiguaControlAnaliticVerificacioDao();
		qualitatAiguaControlOrganolepticVerificacioDao = new QualitatAiguaControlOrganolepticVerificacioDao();
		
	}

	/**
	 * Només serveix per poder especificar els permisos amb xdoclet.
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ESTADMINISTRADOR"
	 * @ejb.permission role-name="OLI_ESTENCARREGAT"
	 * @ejb.permission role-name="OLI_ESTTREBALLADOR"
	 * @ejb.permission role-name="OLI_ESTCONSULTA"
	 * <!-- end-xdoclet-definition -->
	 */
	public void ejbRemove() {
	}

	/**
	 * Només serveix per poder especificar els permisos amb xdoclet. 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ESTADMINISTRADOR"
	 * @ejb.permission role-name="OLI_ESTENCARREGAT"
	 * @ejb.permission role-name="OLI_ESTTREBALLADOR"
	 * @ejb.permission role-name="OLI_ESTCONSULTA"
	 * <!-- end-xdoclet-definition -->
	 */
	public void crearDescripcioInstalacio(QualitatDescripcioInstalacio q){
		
		qualitatDescripcioInstalacioDao.setHibernateTemplate(getHibernateTemplate());
		qualitatDescripcioInstalacioDao.makePersistent(q);
	}
	
	
	/**
	 * Només serveix per poder especificar els permisos amb xdoclet. 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ESTADMINISTRADOR"
	 * @ejb.permission role-name="OLI_ESTENCARREGAT"
	 * @ejb.permission role-name="OLI_ESTTREBALLADOR"
	 * @ejb.permission role-name="OLI_ESTCONSULTA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Long actualitzarDescripcioInstalacio(QualitatDescripcioInstalacio q){
		qualitatDescripcioInstalacioDao.setHibernateTemplate(getHibernateTemplate());
		QualitatDescripcioInstalacio instalacio = qualitatDescripcioInstalacioDao.getById(q.getId());
		instalacio.setSales(q.getSales());
		instalacio.setPlantes(q.getPlantes());
		instalacio.setAmple(q.getAmple());
		instalacio.setLlarg(q.getLlarg());
		instalacio.setVestuaris(q.getVestuaris());
		instalacio.setBanys(q.getBanys());
		instalacio.setOficines(q.getOficines());
		qualitatDescripcioInstalacioDao.makePersistent(instalacio);
		return instalacio.getId();
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public QualitatDescripcioInstalacio descripcioInstalacioAmbId(Long id) {
		qualitatDescripcioInstalacioDao.setHibernateTemplate(getHibernateTemplate());
		return qualitatDescripcioInstalacioDao.getById(id);
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public QualitatDescripcioInstalacio descripcioInstalacioByEstabliment(Long idEstabliment) {
		qualitatDescripcioInstalacioDao.setHibernateTemplate(getHibernateTemplate());
		return qualitatDescripcioInstalacioDao.getByEstabliment(idEstabliment);
	}
	
	
	
	/**
	 * Només serveix per poder especificar els permisos amb xdoclet. 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ESTADMINISTRADOR"
	 * @ejb.permission role-name="OLI_ESTENCARREGAT"
	 * @ejb.permission role-name="OLI_ESTTREBALLADOR"
	 * @ejb.permission role-name="OLI_ESTCONSULTA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Long descripcioEquipCrear(QualitatDescripcioEquip q){
		qualitatDescripcioEquipDao.setHibernateTemplate(getHibernateTemplate());
		qualitatDescripcioEquipDao.makePersistent(q);
		return q.getCodi();
	}
	
	
	/**
	 * Només serveix per poder especificar els permisos amb xdoclet. 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ESTADMINISTRADOR"
	 * @ejb.permission role-name="OLI_ESTENCARREGAT"
	 * @ejb.permission role-name="OLI_ESTTREBALLADOR"
	 * @ejb.permission role-name="OLI_ESTCONSULTA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Long actualitzarDescripcioEquip(QualitatDescripcioEquip q){
		qualitatDescripcioEquipDao.setHibernateTemplate(getHibernateTemplate());
		QualitatDescripcioEquip equip = qualitatDescripcioEquipDao.getById(q.getCodi());
		equip.setCodiUsuari(q.getCodiUsuari());
		equip.setNom(q.getNom());
		equip.setUbicacio(q.getUbicacio());
		equip.setMarca(q.getMarca());
		equip.setDataCompra(q.getDataCompra());
		equip.setNumSerie(q.getNumSerie());
		equip.setEstabliment(q.getEstabliment());
		qualitatDescripcioEquipDao.makePersistent(equip);
		return equip.getCodi();
	}
	
	
	/**
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_ESTADMINISTRADOR"
	 * @ejb.permission role-name="OLI_ESTENCARREGAT"
	 * @ejb.permission role-name="OLI_ESTTREBALLADOR"
	 * @ejb.permission role-name="OLI_ESTCONSULTA"
	 * <!-- end-xdoclet-definition -->
	 */
	public QualitatDescripcioEquip getQualitatDescripcioEquipById(Long id) {
		qualitatDescripcioEquipDao.setHibernateTemplate(getHibernateTemplate());
		return qualitatDescripcioEquipDao.getById(id);
	}
	
	
	/**
	 * Només serveix per poder especificar els permisos amb xdoclet. 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ESTADMINISTRADOR"
	 * @ejb.permission role-name="OLI_ESTENCARREGAT"
	 * @ejb.permission role-name="OLI_ESTTREBALLADOR"
	 * @ejb.permission role-name="OLI_ESTCONSULTA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection getQualitatDescripcioEquipsByEstabliment(Long establimentId) {
		qualitatDescripcioEquipDao.setHibernateTemplate(getHibernateTemplate());
		return qualitatDescripcioEquipDao.getAllByEstabliment(establimentId);
	}
	
	
	/**
	 * Només serveix per poder especificar els permisos amb xdoclet. 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ESTADMINISTRADOR"
	 * @ejb.permission role-name="OLI_ESTENCARREGAT"
	 * @ejb.permission role-name="OLI_ESTTREBALLADOR"
	 * @ejb.permission role-name="OLI_ESTCONSULTA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection vehiclesByEstabliment(Long establimentId) {
		qualitatDescripcioEquipDao.setHibernateTemplate(getHibernateTemplate());
		return qualitatDescripcioEquipDao.getVehiclesByEstabliment(establimentId);
	}

	

	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 * @generated
	 */
	public void qualitatDescripcioEquipEsborrar(Long id) {
		qualitatDescripcioEquipDao.setHibernateTemplate(getHibernateTemplate());
		QualitatDescripcioEquip equip = qualitatDescripcioEquipDao.getById(id);
		if (equip != null) {
			// Borrar entradas no válidas.
//			entradaDipositDao.findEntradaDiposiOliByDiposit(diposit.getId(), new Boolean(false));
//			entradaDipositDao.setHibernateTemplate(getHibernateTemplate());
			
			qualitatDescripcioEquipDao.setHibernateTemplate(getHibernateTemplate());
			qualitatDescripcioEquipDao.makeTransient(equip);
		}
	}
	
	/**
	 * Només serveix per poder especificar els permisos amb xdoclet. 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ESTADMINISTRADOR"
	 * @ejb.permission role-name="OLI_ESTENCARREGAT"
	 * @ejb.permission role-name="OLI_ESTTREBALLADOR"
	 * @ejb.permission role-name="OLI_ESTCONSULTA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Long crearDescripcioPersonal(QualitatDescripcioPersonal q){
		qualitatDescripcioPersonalDao.setHibernateTemplate(getHibernateTemplate());
		qualitatDescripcioPersonalDao.makePersistent(q);
		return q.getCodi();
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public QualitatDescripcioPersonal personalAmbId(Long id) {
		qualitatDescripcioPersonalDao.setHibernateTemplate(getHibernateTemplate());
		return qualitatDescripcioPersonalDao.getById(id);
	}
	
	/**
	 * Cerca tot el personal de l'establiment<!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection personalCercaTotsPerEstabliment(Long idEstabliment) {
		qualitatDescripcioPersonalDao.setHibernateTemplate(getHibernateTemplate());
		return qualitatDescripcioPersonalDao.findByEstabliment(idEstabliment);
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 * @generated
	 */
	public void personalEsborrar(Long id) {
		qualitatDescripcioPersonalDao.setHibernateTemplate(getHibernateTemplate());
		QualitatDescripcioPersonal personal = qualitatDescripcioPersonalDao.getById(id);
		if (personal != null) {
			// Borrar entradas no válidas.
//			entradaDipositDao.findEntradaDiposiOliByDiposit(diposit.getId(), new Boolean(false));
//			entradaDipositDao.setHibernateTemplate(getHibernateTemplate());
			
			qualitatDescripcioPersonalDao.setHibernateTemplate(getHibernateTemplate());
			qualitatDescripcioPersonalDao.makeTransient(personal);
		}
	}
	
	/**
	 * Devuelve una lista de documentos
	 * 
	 * @return collection
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
	public List documentsByPersonal(Long idPersonal) {
		documentDao.setHibernateTemplate(getHibernateTemplate());
		return documentDao.findByPersonal(idPersonal);
	}
	
	/**
	 * Només serveix per poder especificar els permisos amb xdoclet. 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ESTADMINISTRADOR"
	 * @ejb.permission role-name="OLI_ESTENCARREGAT"
	 * @ejb.permission role-name="OLI_ESTTREBALLADOR"
	 * @ejb.permission role-name="OLI_ESTCONSULTA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Long actualitzarPuestoTreball(QualitatPuestoTreball q){
		qualitatPuestoTreballDao.setHibernateTemplate(getHibernateTemplate());
		QualitatPuestoTreball puesto = qualitatPuestoTreballDao.getById(q.getId());
		puesto.setCarrec(q.getCarrec());
		puesto.setFuncions(q.getFuncions());
		puesto.setFormacio(q.getFormacio());
		puesto.setExperiencia(q.getExperiencia());
		qualitatPuestoTreballDao.makePersistent(puesto);
		return puesto.getId();
	}
	
	/**
	 * Només serveix per poder especificar els permisos amb xdoclet. 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ESTADMINISTRADOR"
	 * @ejb.permission role-name="OLI_ESTENCARREGAT"
	 * @ejb.permission role-name="OLI_ESTTREBALLADOR"
	 * @ejb.permission role-name="OLI_ESTCONSULTA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Long crearPuestoTreball (QualitatPuestoTreball q){
		qualitatPuestoTreballDao.setHibernateTemplate(getHibernateTemplate());
		qualitatPuestoTreballDao.makePersistent(q);
		return q.getId();
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public QualitatPuestoTreball puestoAmbId(Long id) {
		qualitatPuestoTreballDao.setHibernateTemplate(getHibernateTemplate());
		return qualitatPuestoTreballDao.getById(id);
	}
	
	/**
	 * Cerca tots els carrecs de l'establiment<!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection puestoCercaTotsPerEstabliment(Long idEstabliment) {
		qualitatPuestoTreballDao.setHibernateTemplate(getHibernateTemplate());
		return qualitatPuestoTreballDao.findByEstabliment(idEstabliment);
	}
	
	/**
	 * Cerca tot els carrecs de l'establiment per nivell Jerarquic<!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection puestoCercaTotsPerNivell(Long idEstabliment, Integer nivell) {
		qualitatPuestoTreballDao.setHibernateTemplate(getHibernateTemplate());
		return qualitatPuestoTreballDao.findByNivell(idEstabliment, nivell);
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 * @generated
	 */
	public void puestoEsborrar(Long id) {
		qualitatPuestoTreballDao.setHibernateTemplate(getHibernateTemplate());
		QualitatPuestoTreball puesto = qualitatPuestoTreballDao.getById(id);
		if (puesto != null) {
			// Borrar entradas no válidas.
//			entradaDipositDao.findEntradaDiposiOliByDiposit(diposit.getId(), new Boolean(false));
//			entradaDipositDao.setHibernateTemplate(getHibernateTemplate());
			
			qualitatPuestoTreballDao.setHibernateTemplate(getHibernateTemplate());
			qualitatPuestoTreballDao.makeTransient(puesto);
		}
	}
	
	
	/**
	 * Només serveix per poder especificar els permisos amb xdoclet. 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ESTADMINISTRADOR"
	 * @ejb.permission role-name="OLI_ESTENCARREGAT"
	 * @ejb.permission role-name="OLI_ESTTREBALLADOR"
	 * @ejb.permission role-name="OLI_ESTCONSULTA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Long crearPlaFormacio(QualitatPlaFormacio q){
		qualitatPlaFormacioDao.setHibernateTemplate(getHibernateTemplate());
		qualitatPlaFormacioDao.makePersistent(q);
		return q.getId();
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public QualitatPlaFormacio formacioAmbId(Long id) {
		qualitatPlaFormacioDao.setHibernateTemplate(getHibernateTemplate());
		return qualitatPlaFormacioDao.getById(id);
	}
	
	/**
	 * Cerca tot el personal de l'establiment<!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection formacioCercaTotsPerEstabliment(Long idEstabliment) {
		qualitatPlaFormacioDao.setHibernateTemplate(getHibernateTemplate());
		return qualitatPlaFormacioDao.findByEstabliment(idEstabliment);
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 * @generated
	 */
	public void formacioEsborrar(Long id) {
		qualitatPlaFormacioDao.setHibernateTemplate(getHibernateTemplate());
		QualitatPlaFormacio formacio = qualitatPlaFormacioDao.getById(id);
		if (formacio != null) {
			qualitatPlaFormacioDao.setHibernateTemplate(getHibernateTemplate());
			qualitatPlaFormacioDao.makeTransient(formacio);
		}
	}
	
	/**
	 * Només serveix per poder especificar els permisos amb xdoclet. 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ESTADMINISTRADOR"
	 * @ejb.permission role-name="OLI_ESTENCARREGAT"
	 * @ejb.permission role-name="OLI_ESTTREBALLADOR"
	 * @ejb.permission role-name="OLI_ESTCONSULTA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Long crearEvaluacio(QualitatFormacioEvaluacio q){
		qualitatFormacioEvaluacioDao.setHibernateTemplate(getHibernateTemplate());
		qualitatFormacioEvaluacioDao.makePersistent(q);
		return q.getId();
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public QualitatFormacioEvaluacio evaluacioAmbId(Long id) {
		qualitatFormacioEvaluacioDao.setHibernateTemplate(getHibernateTemplate());
		return qualitatFormacioEvaluacioDao.getById(id);
	}
	
	/**
	 * Cerca totes les evaluacions de la formacio <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection evaluacioCercaTotsPerFormacio(Long idFormacio) {
		qualitatFormacioEvaluacioDao.setHibernateTemplate(getHibernateTemplate());
		return qualitatFormacioEvaluacioDao.findByFormacio(idFormacio);
	}
	
	/**
	 * Cerca totes les evaluacions del treballador <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection evaluacioCercaTotsPerTreballador(Long idTreballador) {
		qualitatFormacioEvaluacioDao.setHibernateTemplate(getHibernateTemplate());
		return qualitatFormacioEvaluacioDao.findByTreballador(idTreballador);
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 * @generated
	 */
	public void evaluacioEsborrar(Long id) {
		qualitatFormacioEvaluacioDao.setHibernateTemplate(getHibernateTemplate());
		QualitatFormacioEvaluacio evaluacio = qualitatFormacioEvaluacioDao.getById(id);
		if (evaluacio != null) {
			qualitatFormacioEvaluacioDao.setHibernateTemplate(getHibernateTemplate());
			qualitatFormacioEvaluacioDao.makeTransient(evaluacio);
		}
	}
	
	public Long actualitzarProveidor(QualitatProveidor q){
		qualitatProveidorDao.setHibernateTemplate(getHibernateTemplate());
		QualitatProveidor proveidor = qualitatProveidorDao.getById(q.getCodi());
		proveidor.setNom(q.getNom());
		proveidor.setDireccio(q.getDireccio());
		proveidor.setTelefon(q.getTelefon());
		proveidor.setPersonaContacte(q.getPersonaContacte());
		proveidor.setEstabliment(q.getEstabliment());
		proveidor.setObservacions(q.getObservacions());
		qualitatProveidorDao.makePersistent(proveidor);
		return proveidor.getCodi();
	}
	
	/**
	 * Cerca totes les evaluacions del treballador <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Long proveidorCrear(QualitatProveidor q){
		qualitatProveidorDao.setHibernateTemplate(getHibernateTemplate());
		qualitatProveidorDao.makePersistent(q);
		return q.getCodi();
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public QualitatProveidor proveidorAmbId(Long id) {
		qualitatProveidorDao.setHibernateTemplate(getHibernateTemplate());
		return qualitatProveidorDao.getById(id);
	}
	
	/**
	 * Cerca tots els proveidors de l'establiment<!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection proveidorsPerEstabliment(Long idEstabliment) {
		qualitatProveidorDao.setHibernateTemplate(getHibernateTemplate());
		return qualitatProveidorDao.getAllByEstabliment(idEstabliment);
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 * @generated
	 */
	public void proveidorEsborrar(Long id) {
		qualitatProveidorDao.setHibernateTemplate(getHibernateTemplate());
		QualitatProveidor proveidor = qualitatProveidorDao.getById(id);
		if (proveidor != null) {
			// Borrar entradas no válidas.
//			entradaDipositDao.findEntradaDiposiOliByDiposit(diposit.getId(), new Boolean(false));
//			entradaDipositDao.setHibernateTemplate(getHibernateTemplate());
			
			qualitatProveidorDao.setHibernateTemplate(getHibernateTemplate());
			qualitatProveidorDao.makeTransient(proveidor);
		}
	}
	
	
	/* ------------------------------------------------------------------------ */
	/* 								GESTOR DOCUMENTAL 							*/
	/* ------------------------------------------------------------------------ */
	

	public Long actualitzarSubministre(QualitatSubministre q){
		qualitatSubministreDao.setHibernateTemplate(getHibernateTemplate());
		QualitatSubministre subministre = qualitatSubministreDao.getById(q.getCodi());
		subministre.setTipusSubministre(q.getTipusSubministre());
		subministre.setHomologacio(q.getHomologacio());
		subministre.setDeshomologacio(q.getDeshomologacio());
		subministre.setObservacions(q.getObservacions());
		subministre.setProveidor(q.getProveidor());
		qualitatSubministreDao.makePersistent(subministre);
		return subministre.getCodi();
	}
	

	/**
	 * Cerca totes les evaluacions del treballador <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public GestorDocumentalInformacio informacioAmbId(Integer id){
		gestorDocumentalInformacioDao.setHibernateTemplate(getHibernateTemplate());
		return gestorDocumentalInformacioDao.getById(id);
	}
	
	/**
	 * Només serveix per poder especificar els permisos amb xdoclet. 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ESTADMINISTRADOR"
	 * @ejb.permission role-name="OLI_ESTENCARREGAT"
	 * @ejb.permission role-name="OLI_ESTTREBALLADOR"
	 * @ejb.permission role-name="OLI_ESTCONSULTA"
	 * <!-- end-xdoclet-definition -->
	 */
	public boolean usuariHaLeido(Long idUsu, Integer idInfo) {
		gestorDocumentalInformacioDao.setHibernateTemplate(getHibernateTemplate());
		Collection usus = gestorDocumentalInformacioDao.findUsuarisByInformacio(idInfo);
		usuariDao.setHibernateTemplate(getHibernateTemplate());
		Usuari usuari = usuariDao.getById(idUsu);
		Iterator iteUsus = usus.iterator();
		while (iteUsus.hasNext()){
			Usuari usu = (Usuari)iteUsus.next();
			gestorDocumentalInformacioDao.setHibernateTemplate(getHibernateTemplate());
			if (usu.getId().equals(usuari.getId())){
				return true;
			}
		}
		
		return false;
	}
	
	
	/**
	 * Només serveix per poder especificar els permisos amb xdoclet. 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ESTADMINISTRADOR"
	 * @ejb.permission role-name="OLI_ESTENCARREGAT"
	 * @ejb.permission role-name="OLI_ESTTREBALLADOR"
	 * @ejb.permission role-name="OLI_ESTCONSULTA"
	 * <!-- end-xdoclet-definition -->
	 */
	public void usuariLeeInfo(Long idUsu, GestorDocumentalInformacio info){
		Set usus = info.getUsuaris();
		Usuari usu = new Usuari();
		usu.setId(idUsu);
		usus.add(usu);
		info.setUsuaris(usus);
		gestorDocumentalInformacioDao.setHibernateTemplate(getHibernateTemplate());
		gestorDocumentalInformacioDao.makePersistent(info);
	}
	
	/**
	 * Només serveix per poder especificar els permisos amb xdoclet. 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ESTADMINISTRADOR"
	 * @ejb.permission role-name="OLI_ESTENCARREGAT"
	 * @ejb.permission role-name="OLI_ESTTREBALLADOR"
	 * @ejb.permission role-name="OLI_ESTCONSULTA"
	 * <!-- end-xdoclet-definition -->
	 */
	public List documentsByInformacio(Integer idInformacio) {
		gestorDocumentalDocumentDao.setHibernateTemplate(getHibernateTemplate());
		return gestorDocumentalDocumentDao.findByInformacio(idInformacio);
	}
	
	/**
	 * Només serveix per poder especificar els permisos amb xdoclet. 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ESTADMINISTRADOR"
	 * @ejb.permission role-name="OLI_ESTENCARREGAT"
	 * @ejb.permission role-name="OLI_ESTTREBALLADOR"
	 * @ejb.permission role-name="OLI_ESTCONSULTA"
	 * <!-- end-xdoclet-definition -->
	 */
	public GestorDocumentalCategoriaInformacio categoriaInformacioAmbId(Integer id){
		gestorDocumentalCategoriaInformacioDao.setHibernateTemplate(getHibernateTemplate());
		return gestorDocumentalCategoriaInformacioDao.getById(id);
	}
	
	/**
	 * Només serveix per poder especificar els permisos amb xdoclet. 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ESTADMINISTRADOR"
	 * @ejb.permission role-name="OLI_ESTENCARREGAT"
	 * @ejb.permission role-name="OLI_ESTTREBALLADOR"
	 * @ejb.permission role-name="OLI_ESTCONSULTA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection categoriaInformacioFindAll(){
		gestorDocumentalCategoriaInformacioDao.setHibernateTemplate(getHibernateTemplate());
		return gestorDocumentalCategoriaInformacioDao.findAll();
	}
	
	/**
	 * Només serveix per poder especificar els permisos amb xdoclet. 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ESTADMINISTRADOR"
	 * @ejb.permission role-name="OLI_ESTENCARREGAT"
	 * @ejb.permission role-name="OLI_ESTTREBALLADOR"
	 * @ejb.permission role-name="OLI_ESTCONSULTA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection llistatInformacioFiltreIEstat(Integer categoria,int estat) {
		gestorDocumentalInformacioDao.setHibernateTemplate(getHibernateTemplate());
		return gestorDocumentalInformacioDao.findAllFiltreIEstat(categoria,estat);
	}
	
	/**
	 * Només serveix per poder especificar els permisos amb xdoclet. 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ESTADMINISTRADOR"
	 * @ejb.permission role-name="OLI_ESTENCARREGAT"
	 * @ejb.permission role-name="OLI_ESTTREBALLADOR"
	 * @ejb.permission role-name="OLI_ESTCONSULTA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection llistatInformacioIEstat(int estat) {
		gestorDocumentalInformacioDao.setHibernateTemplate(getHibernateTemplate());
		return gestorDocumentalInformacioDao.findAllByEstat(estat);
	}
	
	/**
	 * Només serveix per poder especificar els permisos amb xdoclet. 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ESTADMINISTRADOR"
	 * @ejb.permission role-name="OLI_ESTENCARREGAT"
	 * @ejb.permission role-name="OLI_ESTTREBALLADOR"
	 * @ejb.permission role-name="OLI_ESTCONSULTA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection llistatInformacio() {
		gestorDocumentalInformacioDao.setHibernateTemplate(getHibernateTemplate());
		return gestorDocumentalInformacioDao.findAll();
	}
	
	/**
	 * Només serveix per poder especificar els permisos amb xdoclet. 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ESTADMINISTRADOR"
	 * @ejb.permission role-name="OLI_ESTENCARREGAT"
	 * @ejb.permission role-name="OLI_ESTTREBALLADOR"
	 * @ejb.permission role-name="OLI_ESTCONSULTA"
	 * <!-- end-xdoclet-definition -->
	 */
	public boolean existenInformacionesAsociadasCategoria(Integer id){
		gestorDocumentalCategoriaInformacioDao.setHibernateTemplate(getHibernateTemplate());
		return gestorDocumentalCategoriaInformacioDao.existenInformacionesAsociadasToCategoria(id);		
	}
	
	/**
	 * Només serveix per poder especificar els permisos amb xdoclet. 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ESTADMINISTRADOR"
	 * @ejb.permission role-name="OLI_ESTENCARREGAT"
	 * @ejb.permission role-name="OLI_ESTTREBALLADOR"
	 * @ejb.permission role-name="OLI_ESTCONSULTA"
	 * <!-- end-xdoclet-definition -->
	 */
	public void categoriaInformacioEsborrar(Integer id){
		gestorDocumentalCategoriaInformacioDao.setHibernateTemplate(getHibernateTemplate());
		GestorDocumentalCategoriaInformacio cat = gestorDocumentalCategoriaInformacioDao.getById(id);
		if (cat !=null){
			gestorDocumentalCategoriaInformacioDao.setHibernateTemplate(getHibernateTemplate());
			gestorDocumentalCategoriaInformacioDao.makeTransient(cat);
		}
	}
	
	/**
	 * Només serveix per poder especificar els permisos amb xdoclet. 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ESTADMINISTRADOR"
	 * @ejb.permission role-name="OLI_ESTENCARREGAT"
	 * @ejb.permission role-name="OLI_ESTTREBALLADOR"
	 * @ejb.permission role-name="OLI_ESTCONSULTA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Integer informacioCategoriaCrear(String nom, String desc){		
		GestorDocumentalCategoriaInformacio cat = new GestorDocumentalCategoriaInformacio();
		cat.setNom(nom);
		cat.setDescripcio(desc);
		gestorDocumentalCategoriaInformacioDao.setHibernateTemplate(getHibernateTemplate());
		gestorDocumentalCategoriaInformacioDao.makePersistent(cat);
		return cat.getId();
	}
	
	/**
	 * Només serveix per poder especificar els permisos amb xdoclet. 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ESTADMINISTRADOR"
	 * @ejb.permission role-name="OLI_ESTENCARREGAT"
	 * @ejb.permission role-name="OLI_ESTTREBALLADOR"
	 * @ejb.permission role-name="OLI_ESTCONSULTA"
	 * <!-- end-xdoclet-definition -->
	 */
	public void informacioCategoriaModificar(Integer id, String nom, String desc){
		gestorDocumentalCategoriaInformacioDao.setHibernateTemplate(getHibernateTemplate());
		GestorDocumentalCategoriaInformacio cat = gestorDocumentalCategoriaInformacioDao.getById(id);
		cat.setNom(nom);
		cat.setDescripcio(desc);
		gestorDocumentalCategoriaInformacioDao.setHibernateTemplate(getHibernateTemplate());
		gestorDocumentalCategoriaInformacioDao.makePersistent(cat);
	}
	
	/**
	 * Només serveix per poder especificar els permisos amb xdoclet. 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ESTADMINISTRADOR"
	 * @ejb.permission role-name="OLI_ESTENCARREGAT"
	 * @ejb.permission role-name="OLI_ESTTREBALLADOR"
	 * @ejb.permission role-name="OLI_ESTCONSULTA"
	 * <!-- end-xdoclet-definition -->
	 */
	public boolean existeOtraCategoriaConNombre(String nom, Integer id){
		gestorDocumentalCategoriaInformacioDao.setHibernateTemplate(getHibernateTemplate());
		return gestorDocumentalCategoriaInformacioDao.existeOtraCategoriaConNombre(nom,id);		
	}
	
	/**
	 * Només serveix per poder especificar els permisos amb xdoclet. 
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ESTADMINISTRADOR"
	 * @ejb.permission role-name="OLI_ESTENCARREGAT"
	 * @ejb.permission role-name="OLI_ESTTREBALLADOR"
	 * @ejb.permission role-name="OLI_ESTCONSULTA"
	 * <!-- end-xdoclet-definition -->
	 */
	public void documentEsborrar(Integer id){
		
		gestorDocumentalDocumentDao.setHibernateTemplate(getHibernateTemplate());
		GestorDocumentalDocument document = gestorDocumentalDocumentDao.getById(id);
		if (document.getArxiu() != null) {
			arxiuDao.setHibernateTemplate(getHibernateTemplate());
			Arxiu arxiu = arxiuDao.getById(document.getArxiu());
			arxiuDao.setHibernateTemplate(getHibernateTemplate());
			arxiuDao.makeTransient(arxiu);
		}
		
		gestorDocumentalDocumentDao.makeTransient(document);
	}
	
	
	/**
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ESTADMINISTRADOR"
	 * @ejb.permission role-name="OLI_ESTENCARREGAT"
	 * @ejb.permission role-name="OLI_ESTTREBALLADOR"
	 * @ejb.permission role-name="OLI_ESTCONSULTA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Integer informacioCrear(GestorDocumentalInformacio informacio){		
		gestorDocumentalInformacioDao.setHibernateTemplate(getHibernateTemplate());
		gestorDocumentalInformacioDao.makePersistent(informacio);
		return informacio.getId();
	}
	
	/**
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ESTADMINISTRADOR"
	 * @ejb.permission role-name="OLI_ESTENCARREGAT"
	 * @ejb.permission role-name="OLI_ESTTREBALLADOR"
	 * @ejb.permission role-name="OLI_ESTCONSULTA"
	 * <!-- end-xdoclet-definition -->
	 */
	public void informacioModificar(GestorDocumentalInformacio informacio, Boolean informar){
		if (informar != null && informar.booleanValue()) { // Borramos las relaciones vistas por el usuario
			informacio.getEstabliments().clear();
			informacio.getUsuaris().clear();
		}
		
		gestorDocumentalInformacioDao.setHibernateTemplate(getHibernateTemplate());
		gestorDocumentalInformacioDao.makePersistent(informacio);
	}
	
	/**
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ESTADMINISTRADOR"
	 * @ejb.permission role-name="OLI_ESTENCARREGAT"
	 * @ejb.permission role-name="OLI_ESTTREBALLADOR"
	 * @ejb.permission role-name="OLI_ESTCONSULTA"
	 * <!-- end-xdoclet-definition -->
	 */
	public void informacioEsborrar(GestorDocumentalInformacio informacio){
		gestorDocumentalInformacioDao.setHibernateTemplate(getHibernateTemplate());
		gestorDocumentalInformacioDao.makeTransient(informacio);
	}
	
	/**
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ESTADMINISTRADOR"
	 * @ejb.permission role-name="OLI_ESTENCARREGAT"
	 * @ejb.permission role-name="OLI_ESTTREBALLADOR"
	 * @ejb.permission role-name="OLI_ESTCONSULTA"
	 * <!-- end-xdoclet-definition -->
	 */
	public void documentCrear(GestorDocumentalDocument document, Arxiu arxiu){
		
		if (arxiu != null) {
			arxiuDao.setHibernateTemplate(getHibernateTemplate());
			arxiuDao.makePersistent(arxiu);
		}
		document.setArxiu(arxiu.getId());
		
		gestorDocumentalDocumentDao.setHibernateTemplate(getHibernateTemplate());
		gestorDocumentalDocumentDao.makePersistent(document);
	}
	
	/**
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ESTADMINISTRADOR"
	 * @ejb.permission role-name="OLI_ESTENCARREGAT"
	 * @ejb.permission role-name="OLI_ESTTREBALLADOR"
	 * @ejb.permission role-name="OLI_ESTCONSULTA"
	 * <!-- end-xdoclet-definition -->
	 */
	public void documentModificar(GestorDocumentalDocument document, Arxiu arxiu){
		
		if (arxiu != null) {
			arxiuDao.setHibernateTemplate(getHibernateTemplate());
			arxiuDao.makePersistent(arxiu);
		}
		
		gestorDocumentalDocumentDao.setHibernateTemplate(getHibernateTemplate());
		gestorDocumentalDocumentDao.makePersistent(document);
	}
	
	/**
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ESTADMINISTRADOR"
	 * @ejb.permission role-name="OLI_ESTENCARREGAT"
	 * @ejb.permission role-name="OLI_ESTTREBALLADOR"
	 * @ejb.permission role-name="OLI_ESTCONSULTA"
	 * <!-- end-xdoclet-definition -->
	 */
	public GestorDocumentalDocument documentAmbId(Integer id){
		gestorDocumentalDocumentDao.setHibernateTemplate(getHibernateTemplate());
		return gestorDocumentalDocumentDao.getById(id);
	}
	
	/* ------------------------------------------------------------------------ */
	/* 							FI GESTOR DOCUMENTAL 							*/
	/* ------------------------------------------------------------------------ */	
	
	/**
	 * Només serveix per poder especificar els permisos amb xdoclet. 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ESTADMINISTRADOR"
	 * @ejb.permission role-name="OLI_ESTENCARREGAT"
	 * @ejb.permission role-name="OLI_ESTTREBALLADOR"
	 * @ejb.permission role-name="OLI_ESTCONSULTA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Long crearSubministre(QualitatSubministre q){
		qualitatSubministreDao.setHibernateTemplate(getHibernateTemplate());
		qualitatSubministreDao.makePersistent(q);
		return q.getCodi();
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public QualitatSubministre subministreAmbId(Long id) {
		qualitatSubministreDao.setHibernateTemplate(getHibernateTemplate());
		return qualitatSubministreDao.getById(id);
	}
	
	/**
	 * Cerca tots els subministres d'un proveidor<!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection subministrePerProveidor(Long idProveidor) {
		qualitatSubministreDao.setHibernateTemplate(getHibernateTemplate());
		return qualitatSubministreDao.findByProveidor(idProveidor);
	}
	
	/**
	 * Cerca tots els subministres d'un establiment<!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection subministresPerEstabliment(Long idEstabliment) {
		qualitatSubministreDao.setHibernateTemplate(getHibernateTemplate());
		return qualitatSubministreDao.findByEstabliment(idEstabliment);
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 * @generated
	 */
	public void subministreEsborrar(Long id) {
		qualitatSubministreDao.setHibernateTemplate(getHibernateTemplate());
		QualitatSubministre subministre = qualitatSubministreDao.getById(id);
		if (subministre != null) {
			
			qualitatSubministreDao.setHibernateTemplate(getHibernateTemplate());
			qualitatSubministreDao.makeTransient(subministre);
		}
	}
	
	/**
	 * Només serveix per poder especificar els permisos amb xdoclet. 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ESTADMINISTRADOR"
	 * @ejb.permission role-name="OLI_ESTENCARREGAT"
	 * @ejb.permission role-name="OLI_ESTTREBALLADOR"
	 * @ejb.permission role-name="OLI_ESTCONSULTA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Long crearProveidorRGSA(QualitatProveidorRGSA q){
		qualitatProveidorRGSADao.setHibernateTemplate(getHibernateTemplate());
		qualitatProveidorRGSADao.makePersistent(q);
		return q.getId();
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public QualitatProveidorRGSA proveidorRGSAAmbId(Long id) {
		qualitatProveidorRGSADao.setHibernateTemplate(getHibernateTemplate());
		return qualitatProveidorRGSADao.getById(id);
	}
	
	/**
	 * Cerca tots els proveidorRGSAs d'un proveidor<!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection proveidorRGSAPerProveidor(Long idProveidor) {
		qualitatProveidorRGSADao.setHibernateTemplate(getHibernateTemplate());
		return qualitatProveidorRGSADao.findByProveidor(idProveidor);
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 * @generated
	 */
	public void proveidorRGSAEsborrar(Long id) {
		qualitatProveidorRGSADao.setHibernateTemplate(getHibernateTemplate());
		QualitatProveidorRGSA proveidorRGSA = qualitatProveidorRGSADao.getById(id);
		if (proveidorRGSA != null) {
			qualitatProveidorRGSADao.setHibernateTemplate(getHibernateTemplate());
			qualitatProveidorRGSADao.makeTransient(proveidorRGSA);
		}
	}
	
	/**
	 * Només serveix per poder especificar els permisos amb xdoclet. 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ESTADMINISTRADOR"
	 * @ejb.permission role-name="OLI_ESTENCARREGAT"
	 * @ejb.permission role-name="OLI_ESTTREBALLADOR"
	 * @ejb.permission role-name="OLI_ESTCONSULTA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Long crearProveidorAvaluacio(QualitatProveidorAvaluacio q){
		qualitatProveidorAvaluacioDao.setHibernateTemplate(getHibernateTemplate());
		qualitatProveidorAvaluacioDao.makePersistent(q);
		return q.getId();
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public QualitatProveidorAvaluacio proveidorAvaluacioAmbId(Long id) {
		qualitatProveidorAvaluacioDao.setHibernateTemplate(getHibernateTemplate());
		return qualitatProveidorAvaluacioDao.getById(id);
	}
	
	/**
	 * Cerca tot el personal de l'establiment<!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection avaluacionsPerProveidor(Long idProveidor) {
		qualitatProveidorAvaluacioDao.setHibernateTemplate(getHibernateTemplate());
		return qualitatProveidorAvaluacioDao.findByProveidor(idProveidor);
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 * @generated
	 */
	public void proveidorAvaluacioEsborrar(Long id) {
		qualitatProveidorAvaluacioDao.setHibernateTemplate(getHibernateTemplate());
		QualitatProveidorAvaluacio plagues = qualitatProveidorAvaluacioDao.getById(id);
		if (plagues!= null) {
			qualitatProveidorAvaluacioDao.setHibernateTemplate(getHibernateTemplate());
			qualitatProveidorAvaluacioDao.makeTransient(plagues);
		}
	}

	/**
	 * Cerca totes les evaluacions del treballador <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Long actualitzarPlaNeteja(QualitatPlaNeteja q){
		qualitatPlaNetejaDao.setHibernateTemplate(getHibernateTemplate());
		QualitatPlaNeteja pla = qualitatPlaNetejaDao.getById(q.getCodi());
		pla.setProducte(q.getProducte());
		pla.setDosis(q.getDosis());
		pla.setElementPlanta(q.getElementPlanta());
		pla.setEquip(q.getEquip());
		pla.setAccio(q.getAccio());
		pla.setFrequencia(q.getFrequencia());
		pla.setResponsable(q.getResponsable());
		pla.setRespVerificacio(q.getRespVerificacio());
		pla.setPeriodicitatVerificacio(q.getPeriodicitatVerificacio());
		pla.setEstabliment(q.getEstabliment());
		qualitatPlaNetejaDao.makePersistent(pla);
		return pla.getCodi();
	}

	/**
	 * Només serveix per poder especificar els permisos amb xdoclet. 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ESTADMINISTRADOR"
	 * @ejb.permission role-name="OLI_ESTENCARREGAT"
	 * @ejb.permission role-name="OLI_ESTTREBALLADOR"
	 * @ejb.permission role-name="OLI_ESTCONSULTA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Long crearPlaManteniment(QualitatPlaManteniment q){
		qualitatPlaMantenimentDao.setHibernateTemplate(getHibernateTemplate());
		qualitatPlaMantenimentDao.makePersistent(q);
		return q.getId();
	}

	
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public QualitatPlaManteniment mantenimentAmbId(Long id) {
		qualitatPlaMantenimentDao.setHibernateTemplate(getHibernateTemplate());
		return qualitatPlaMantenimentDao.getById(id);
	}

	
	/**
	 * Només serveix per poder especificar els permisos amb xdoclet. 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ESTADMINISTRADOR"
	 * @ejb.permission role-name="OLI_ESTENCARREGAT"
	 * @ejb.permission role-name="OLI_ESTTREBALLADOR"
	 * @ejb.permission role-name="OLI_ESTCONSULTA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Long crearPlaNeteja(QualitatPlaNeteja q){
		qualitatPlaNetejaDao.setHibernateTemplate(getHibernateTemplate());
		qualitatPlaNetejaDao.makePersistent(q);
		return q.getCodi();
	}
	
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public QualitatPlaNeteja plaNetejaAmbId(Long id) {
		qualitatPlaNetejaDao.setHibernateTemplate(getHibernateTemplate());
		return qualitatPlaNetejaDao.getById(id);
	}
	
	/**
	 * Cerca tot el personal de l'establiment<!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection plansNetejaPerEstabliment(Long idEstabliment) {
		qualitatPlaNetejaDao.setHibernateTemplate(getHibernateTemplate());
		return qualitatPlaNetejaDao.plansNetejaPerEstabliment(idEstabliment);
	}
	
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 * @generated
	 */
	public void plaNetejaEsborrar(Long id) {
		qualitatPlaNetejaDao.setHibernateTemplate(getHibernateTemplate());
		QualitatPlaNeteja pla = qualitatPlaNetejaDao.getById(id);
		if (pla != null) {
			qualitatPlaNetejaDao.setHibernateTemplate(getHibernateTemplate());
			qualitatPlaNetejaDao.makeTransient(pla);
		}
	}
	
	
	/**
	 * Només serveix per poder especificar els permisos amb xdoclet. 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ESTADMINISTRADOR"
	 * @ejb.permission role-name="OLI_ESTENCARREGAT"
	 * @ejb.permission role-name="OLI_ESTTREBALLADOR"
	 * @ejb.permission role-name="OLI_ESTCONSULTA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Long crearPlaNetejaRealitzacio(QualitatPlaNetejaRealitzacio q){
		qualitatPlaNetejaRealitzacioDao.setHibernateTemplate(getHibernateTemplate());
		qualitatPlaNetejaRealitzacioDao.makePersistent(q);
		return q.getId();
	}
	
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public QualitatPlaNetejaRealitzacio netejaRealitzacioAmbId(Long id) {
		qualitatPlaNetejaRealitzacioDao.setHibernateTemplate(getHibernateTemplate());
		return qualitatPlaNetejaRealitzacioDao.getById(id);
	}
	
	/**
	 * Cerca tot el personal de l'establiment<!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection netejaRealitzacioPerPlaNeteja(Long idManteniment) {
		qualitatPlaNetejaRealitzacioDao.setHibernateTemplate(getHibernateTemplate());
		return qualitatPlaNetejaRealitzacioDao.findByNeteja(idManteniment);
	}
	
	
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 * @generated
	 */
	public void netejaRealitzacioEsborrar(Long id) {
		qualitatPlaMantenimentControlDao.setHibernateTemplate(getHibernateTemplate());
		QualitatPlaNetejaRealitzacio netejaRealitzacio = qualitatPlaNetejaRealitzacioDao.getById(id);
		if (netejaRealitzacio != null) {
			qualitatPlaNetejaRealitzacioDao.setHibernateTemplate(getHibernateTemplate());
			qualitatPlaNetejaRealitzacioDao.makeTransient(netejaRealitzacio);
		}
	}
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public QualitatPlaNetejaVerificacio netejaVerificacioAmbId(Long id) {
		qualitatPlaNetejaVerificacioDao.setHibernateTemplate(getHibernateTemplate());
		return qualitatPlaNetejaVerificacioDao.getById(id);
	}
	
	/**
	 * Cerca tot el personal de l'establiment<!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection netejaVerificacioPerPlaNeteja(Long idNeteja) {
		qualitatPlaNetejaVerificacioDao.setHibernateTemplate(getHibernateTemplate());
		return qualitatPlaNetejaVerificacioDao.findByNeteja(idNeteja);
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 * @generated
	 */
	public void netejaVerificacioEsborrar(Long id) {
		qualitatPlaNetejaVerificacioDao.setHibernateTemplate(getHibernateTemplate());
		QualitatPlaNetejaVerificacio netejaVerificacio = qualitatPlaNetejaVerificacioDao.getById(id);
		if (netejaVerificacio != null) {
			qualitatPlaNetejaVerificacioDao.setHibernateTemplate(getHibernateTemplate());
			qualitatPlaNetejaVerificacioDao.makeTransient(netejaVerificacio);
		}
	}
	
	/**
	 * Només serveix per poder especificar els permisos amb xdoclet. 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ESTADMINISTRADOR"
	 * @ejb.permission role-name="OLI_ESTENCARREGAT"
	 * @ejb.permission role-name="OLI_ESTTREBALLADOR"
	 * @ejb.permission role-name="OLI_ESTCONSULTA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Long crearPlaNetejaVerificacio(QualitatPlaNetejaVerificacio q){
		qualitatPlaNetejaVerificacioDao.setHibernateTemplate(getHibernateTemplate());
		qualitatPlaNetejaVerificacioDao.makePersistent(q);
		return q.getId();
	}
	

	
	/**
	 * Cerca tot el personal de l'establiment<!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection mantenimentCercaTotsPerEstabliment(Long idEstabliment) {
		qualitatPlaMantenimentDao.setHibernateTemplate(getHibernateTemplate());
		return qualitatPlaMantenimentDao.findByEstabliment(idEstabliment);
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 * @generated
	 */
	public void mantenimentEsborrar(Long id) {
		qualitatPlaMantenimentDao.setHibernateTemplate(getHibernateTemplate());
		QualitatPlaManteniment manteniment = qualitatPlaMantenimentDao.getById(id);
		if (manteniment != null) {
			qualitatPlaMantenimentDao.setHibernateTemplate(getHibernateTemplate());
			qualitatPlaMantenimentDao.makeTransient(manteniment);
		}
	}
	
	
	/**
	 * Només serveix per poder especificar els permisos amb xdoclet. 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ESTADMINISTRADOR"
	 * @ejb.permission role-name="OLI_ESTENCARREGAT"
	 * @ejb.permission role-name="OLI_ESTTREBALLADOR"
	 * @ejb.permission role-name="OLI_ESTCONSULTA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Long crearPlaMantenimentControl(QualitatPlaMantenimentControl q){
		qualitatPlaMantenimentControlDao.setHibernateTemplate(getHibernateTemplate());
		qualitatPlaMantenimentControlDao.makePersistent(q);
		return q.getId();
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public QualitatPlaMantenimentControl mantenimentControlAmbId(Long id) {
		qualitatPlaMantenimentControlDao.setHibernateTemplate(getHibernateTemplate());
		return qualitatPlaMantenimentControlDao.getById(id);
	}
	
	/**
	 * Cerca tot el personal de l'establiment<!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection mantenimentControlCercaTotsPerManteniment(Long idManteniment) {
		qualitatPlaMantenimentControlDao.setHibernateTemplate(getHibernateTemplate());
		return qualitatPlaMantenimentControlDao.findByManteniment(idManteniment);
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 * @generated
	 */
	public void mantenimentControlEsborrar(Long id) {
		qualitatPlaMantenimentControlDao.setHibernateTemplate(getHibernateTemplate());
		QualitatPlaMantenimentControl mantenimentControl = qualitatPlaMantenimentControlDao.getById(id);
		if (mantenimentControl != null) {
			qualitatPlaMantenimentControlDao.setHibernateTemplate(getHibernateTemplate());
			qualitatPlaMantenimentControlDao.makeTransient(mantenimentControl);
		}
	}
	
	
	/**
	 * Només serveix per poder especificar els permisos amb xdoclet. 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ESTADMINISTRADOR"
	 * @ejb.permission role-name="OLI_ESTENCARREGAT"
	 * @ejb.permission role-name="OLI_ESTTREBALLADOR"
	 * @ejb.permission role-name="OLI_ESTCONSULTA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Long crearControlPlagues(QualitatControlPlagues q){
		qualitatControlPlaguesDao.setHibernateTemplate(getHibernateTemplate());
		qualitatControlPlaguesDao.makePersistent(q);
		return q.getId();
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public QualitatControlPlagues controlPlaguesAmbId(Long id) {
		qualitatControlPlaguesDao.setHibernateTemplate(getHibernateTemplate());
		return qualitatControlPlaguesDao.getById(id);
	}
	
	/**
	 * Cerca tot el personal de l'establiment<!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection controlPlaguesCercaTotsPerEstabliment(Long idEstabliment) {
		qualitatControlPlaguesDao.setHibernateTemplate(getHibernateTemplate());
		return qualitatControlPlaguesDao.findByEstabliment(idEstabliment);
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 * @generated
	 */
	public void controlPlaguesEsborrar(Long id) {
		qualitatControlPlaguesDao.setHibernateTemplate(getHibernateTemplate());
		QualitatControlPlagues plagues = qualitatControlPlaguesDao.getById(id);
		if (plagues!= null) {
			qualitatControlPlaguesDao.setHibernateTemplate(getHibernateTemplate());
			qualitatControlPlaguesDao.makeTransient(plagues);
		}
	}
	

	/**
	 * Només serveix per poder especificar els permisos amb xdoclet. 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ESTADMINISTRADOR"
	 * @ejb.permission role-name="OLI_ESTENCARREGAT"
	 * @ejb.permission role-name="OLI_ESTTREBALLADOR"
	 * @ejb.permission role-name="OLI_ESTCONSULTA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Long crearControlPlaguesVerificacio(QualitatControlPlaguesVerificacio q){
		qualitatControlPlaguesVerificacioDao.setHibernateTemplate(getHibernateTemplate());
		qualitatControlPlaguesVerificacioDao.makePersistent(q);
		return q.getId();
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public QualitatControlPlaguesVerificacio controlPlaguesVerificacioAmbId(Long id) {
		qualitatControlPlaguesVerificacioDao.setHibernateTemplate(getHibernateTemplate());
		return qualitatControlPlaguesVerificacioDao.getById(id);
	}
	
	/**
	 * Cerca tot el personal de l'establiment<!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection controlPlaguesVerificacioCercaTotsPerPlaga(Long idPlaga) {
		qualitatControlPlaguesVerificacioDao.setHibernateTemplate(getHibernateTemplate());
		return qualitatControlPlaguesVerificacioDao.findByPlaga(idPlaga);
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 * @generated
	 */
	public void controlPlaguesVerificacioEsborrar(Long id) {
		qualitatControlPlaguesVerificacioDao.setHibernateTemplate(getHibernateTemplate());
		QualitatControlPlaguesVerificacio plagues = qualitatControlPlaguesVerificacioDao.getById(id);
		if (plagues!= null) {
			qualitatControlPlaguesVerificacioDao.setHibernateTemplate(getHibernateTemplate());
			qualitatControlPlaguesVerificacioDao.makeTransient(plagues);
		}
	}
	
	
	/**
	 * Només serveix per poder especificar els permisos amb xdoclet. 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ESTADMINISTRADOR"
	 * @ejb.permission role-name="OLI_ESTENCARREGAT"
	 * @ejb.permission role-name="OLI_ESTTREBALLADOR"
	 * @ejb.permission role-name="OLI_ESTCONSULTA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Long crearPlaControlTransport(QualitatPlaControlTransport q){
		qualitatPlaControlTransportDao.setHibernateTemplate(getHibernateTemplate());
		qualitatPlaControlTransportDao.makePersistent(q);
		return q.getId();
	}
	
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public QualitatPlaControlTransport plaControlTransportAmbId(Long id) {
		qualitatPlaControlTransportDao.setHibernateTemplate(getHibernateTemplate());
		return qualitatPlaControlTransportDao.getById(id);
	}
	
	/**
	 * Cerca tot el personal de l'establiment<!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection plansControlTransportPerEstabliment(Long idEstabliment) {
		qualitatPlaControlTransportDao.setHibernateTemplate(getHibernateTemplate());
		return qualitatPlaControlTransportDao.findByEstabliment(idEstabliment);
	}
	
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 * @generated
	 */
	public void plaControlTransportEsborrar(Long id) {
		qualitatPlaControlTransportDao.setHibernateTemplate(getHibernateTemplate());
		QualitatPlaControlTransport pla = qualitatPlaControlTransportDao.getById(id);
		if (pla != null) {
			qualitatPlaControlTransportDao.setHibernateTemplate(getHibernateTemplate());
			qualitatPlaControlTransportDao.makeTransient(pla);
		}
	}
	
	/**
	 * Només serveix per poder especificar els permisos amb xdoclet. 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ESTADMINISTRADOR"
	 * @ejb.permission role-name="OLI_ESTENCARREGAT"
	 * @ejb.permission role-name="OLI_ESTTREBALLADOR"
	 * @ejb.permission role-name="OLI_ESTCONSULTA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Long crearNoConformitat(QualitatNoConformitat q){
		qualitatNoConformitatDao.setHibernateTemplate(getHibernateTemplate());
		qualitatNoConformitatDao.makePersistent(q);
		return q.getId();
	}
	
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public QualitatNoConformitat noConformitatAmbId(Long id) {
		qualitatNoConformitatDao.setHibernateTemplate(getHibernateTemplate());
		return qualitatNoConformitatDao.getById(id);
	}
	
	/**
	 * Cerca totes les noConformitats de l'establiment<!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection noConformitatCercaTotsPerEstabliment(Long idEstabliment) {
		qualitatNoConformitatDao.setHibernateTemplate(getHibernateTemplate());
		return qualitatNoConformitatDao.findByEstabliment(idEstabliment);
	}
	
	/**
	 * Cerca totes les noConformitats d'un tipus de l'establiment<!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection noConformitatCercaTotsPerTipus(Long idEstabliment, Long idTipus) {
		qualitatNoConformitatDao.setHibernateTemplate(getHibernateTemplate());
		return qualitatNoConformitatDao.findByTipus(idEstabliment, idTipus);
	}
	
	/**
	 * Cerca totes les noConformitats d'un tipus de l'establiment<!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection noConformitatCercaTotsPerControl(Long idControl) {
		qualitatNoConformitatDao.setHibernateTemplate(getHibernateTemplate());
		return qualitatNoConformitatDao.findByControl(idControl);
	}
	
	
	
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 * @generated
	 */
	public void noConformitatEsborrar(Long id) {
		qualitatNoConformitatDao.setHibernateTemplate(getHibernateTemplate());
		QualitatNoConformitat noConformitats = qualitatNoConformitatDao.getById(id);
		if (noConformitats!= null) {
			qualitatNoConformitatDao.setHibernateTemplate(getHibernateTemplate());
			qualitatNoConformitatDao.makeTransient(noConformitats);
		}
	}
	
	/**
	 * Només serveix per poder especificar els permisos amb xdoclet. 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ESTADMINISTRADOR"
	 * @ejb.permission role-name="OLI_ESTENCARREGAT"
	 * @ejb.permission role-name="OLI_ESTTREBALLADOR"
	 * @ejb.permission role-name="OLI_ESTCONSULTA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Long crearNoConformitatAccio(QualitatNoConformitatAccio q){
		qualitatNoConformitatDao.setHibernateTemplate(getHibernateTemplate());
		qualitatNoConformitatDao.makePersistentAccio(q);
		return q.getId();
	}	
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public QualitatNoConformitatAccio noConformitatAccioAmbId(Long id) {
		qualitatNoConformitatDao.setHibernateTemplate(getHibernateTemplate());
		return qualitatNoConformitatDao.getAccioById(id);
	}
	
	/**
	 * Cerca totes les accions de la noConformitat <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection noConformitatAccioCercaTotsPerNoConformitat(Long idNoConformitat) {
		qualitatNoConformitatDao.setHibernateTemplate(getHibernateTemplate());
		return qualitatNoConformitatDao.findAccionsByNoConformitat(idNoConformitat);
	}

	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Long noConformitatAccioMaxCodiPerNoConformitat(Long idNoConformitat) {
		qualitatNoConformitatDao.setHibernateTemplate(getHibernateTemplate());
		return qualitatNoConformitatDao.findMaxAccioByNoConformitat(idNoConformitat);
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 * @generated
	 */
	public void noConformitatAccioEsborrar(Long id) {
		qualitatNoConformitatDao.setHibernateTemplate(getHibernateTemplate());
		QualitatNoConformitatAccio accions = qualitatNoConformitatDao.getAccioById(id);
		if (accions!= null) {
			qualitatNoConformitatDao.setHibernateTemplate(getHibernateTemplate());
			qualitatNoConformitatDao.makeTransientAccio(accions);
		}
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public QualitatDepartament departamentAmbId(Long id) {
		qualitatDepartamentDao.setHibernateTemplate(getHibernateTemplate());
		return qualitatDepartamentDao.getById(id);
	}
	
	/**
	 * Cerca tot el personal de l'establiment<!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection departamentCercaTots() {
		qualitatDepartamentDao.setHibernateTemplate(getHibernateTemplate());
		return qualitatDepartamentDao.findAll();
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public QualitatControl controlAmbId(Long id) {
		qualitatControlDao.setHibernateTemplate(getHibernateTemplate());
		return qualitatControlDao.getById(id);
	}
	
	/**
	 * Només serveix per poder especificar els permisos amb xdoclet. 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ESTADMINISTRADOR"
	 * @ejb.permission role-name="OLI_ESTENCARREGAT"
	 * @ejb.permission role-name="OLI_ESTTREBALLADOR"
	 * @ejb.permission role-name="OLI_ESTCONSULTA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Long crearAPPCC(QualitatAPPCC q){
		qualitatAPPCCDao.setHibernateTemplate(getHibernateTemplate());
		qualitatAPPCCDao.makePersistent(q);
		return q.getId();
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public QualitatAPPCC aPPCCAmbId(Long id) {
		qualitatAPPCCDao.setHibernateTemplate(getHibernateTemplate());
		return qualitatAPPCCDao.getById(id);
	}
	
	/**
	 * Cerca l'APPCC de l'establiment<!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public QualitatAPPCC aPPCCCercaPerEstabliment(Long idEstabliment) {
		qualitatAPPCCDao.setHibernateTemplate(getHibernateTemplate());
		return qualitatAPPCCDao.findByEstabliment(idEstabliment);
	}
	
	/**
	 * Només serveix per poder especificar els permisos amb xdoclet. 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ESTADMINISTRADOR"
	 * @ejb.permission role-name="OLI_ESTENCARREGAT"
	 * @ejb.permission role-name="OLI_ESTTREBALLADOR"
	 * @ejb.permission role-name="OLI_ESTCONSULTA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Long crearAPPCCProducte(QualitatAPPCCProducte q){
		qualitatAPPCCDao.setHibernateTemplate(getHibernateTemplate());
		qualitatAPPCCDao.makePersistentProducte(q);
		return q.getId();
	}	
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public QualitatAPPCCProducte aPPCCProducteAmbId(Long id) {
		qualitatAPPCCDao.setHibernateTemplate(getHibernateTemplate());
		return qualitatAPPCCDao.getProducteById(id);
	}
	
	/**
	 * Cerca totes les accions de la noConformitat <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection aPPCCProducteCercaTotsPerAPPCC(Long idAPPCC) {
		qualitatAPPCCDao.setHibernateTemplate(getHibernateTemplate());
		return qualitatAPPCCDao.findProductesByAPPCC(idAPPCC);
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 * @generated
	 */
	public void aPPCCProducteEsborrar(Long id) {
		qualitatAPPCCDao.setHibernateTemplate(getHibernateTemplate());
		QualitatAPPCCProducte productes = qualitatAPPCCDao.getProducteById(id);
		if (productes!= null) {
			qualitatAPPCCDao.setHibernateTemplate(getHibernateTemplate());
			qualitatAPPCCDao.makeTransientProducte(productes);
		}
	}
	

	/**
	 * Només serveix per poder especificar els permisos amb xdoclet. 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ESTADMINISTRADOR"
	 * @ejb.permission role-name="OLI_ESTENCARREGAT"
	 * @ejb.permission role-name="OLI_ESTTREBALLADOR"
	 * @ejb.permission role-name="OLI_ESTCONSULTA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Long crearAPPCCEtapa(QualitatAPPCCEtapa q){
		qualitatAPPCCEtapaDao.setHibernateTemplate(getHibernateTemplate());
		qualitatAPPCCEtapaDao.makePersistent(q);
		return q.getId();
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public QualitatAPPCCEtapa aPPCCEtapaAmbId(Long id) {
		qualitatAPPCCEtapaDao.setHibernateTemplate(getHibernateTemplate());
		return qualitatAPPCCEtapaDao.getById(id);
	}
	
	/**
	 * Cerca tot el personal de l'establiment<!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection aPPCCEtapaCercaTotsPerEstabliment(Long idEstabliment) {
		qualitatAPPCCEtapaDao.setHibernateTemplate(getHibernateTemplate());
		return qualitatAPPCCEtapaDao.findByEstabliment(idEstabliment);
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 * @generated
	 */
	public void aPPCCEtapaEsborrar(Long id) {
		qualitatAPPCCEtapaDao.setHibernateTemplate(getHibernateTemplate());
		QualitatAPPCCEtapa plagues = qualitatAPPCCEtapaDao.getById(id);
		if (plagues!= null) {
			qualitatAPPCCEtapaDao.setHibernateTemplate(getHibernateTemplate());
			qualitatAPPCCEtapaDao.makeTransient(plagues);
		}
	}
	

	/**
	 * Només serveix per poder especificar els permisos amb xdoclet. 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ESTADMINISTRADOR"
	 * @ejb.permission role-name="OLI_ESTENCARREGAT"
	 * @ejb.permission role-name="OLI_ESTTREBALLADOR"
	 * @ejb.permission role-name="OLI_ESTCONSULTA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Long crearAPPCCEtapaPerill(QualitatAPPCCEtapaPerill q){
		qualitatAPPCCEtapaPerillDao.setHibernateTemplate(getHibernateTemplate());
		qualitatAPPCCEtapaPerillDao.makePersistent(q);
		return q.getId();
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public QualitatAPPCCEtapaPerill aPPCCEtapaPerillAmbId(Long id) {
		qualitatAPPCCEtapaPerillDao.setHibernateTemplate(getHibernateTemplate());
		return qualitatAPPCCEtapaPerillDao.getById(id);
	}
	
	/**
	 * Cerca tot el personal de l'establiment<!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection aPPCCEtapaPerillCercaTotsPerEtapa(Long idEtapa) {
		qualitatAPPCCEtapaPerillDao.setHibernateTemplate(getHibernateTemplate());
		return qualitatAPPCCEtapaPerillDao.findByEtapa(idEtapa);
	}
	
	/**
	 * Cerca tots els perills de l'establiment<!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection aPPCCPerillCercaTotsPerEstabliment(Long idEstabliment) {
		qualitatAPPCCEtapaPerillDao.setHibernateTemplate(getHibernateTemplate());
		return qualitatAPPCCEtapaPerillDao.findByEstabliment(idEstabliment);
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 * @generated
	 */
	public void aPPCCEtapaPerillEsborrar(Long id) {
		qualitatAPPCCEtapaPerillDao.setHibernateTemplate(getHibernateTemplate());
		QualitatAPPCCEtapaPerill plagues = qualitatAPPCCEtapaPerillDao.getById(id);
		if (plagues!= null) {
			qualitatAPPCCEtapaPerillDao.setHibernateTemplate(getHibernateTemplate());
			qualitatAPPCCEtapaPerillDao.makeTransient(plagues);
		}
	}
	
	/**
	 * Només serveix per poder especificar els permisos amb xdoclet. 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ESTADMINISTRADOR"
	 * @ejb.permission role-name="OLI_ESTENCARREGAT"
	 * @ejb.permission role-name="OLI_ESTTREBALLADOR"
	 * @ejb.permission role-name="OLI_ESTCONSULTA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Long crearAPPCCControl(QualitatAPPCCControl q){
		qualitatAPPCCControlDao.setHibernateTemplate(getHibernateTemplate());
		qualitatAPPCCControlDao.makePersistent(q);
		return q.getId();
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public QualitatAPPCCControl aPPCCControlAmbId(Long id) {
		qualitatAPPCCControlDao.setHibernateTemplate(getHibernateTemplate());
		return qualitatAPPCCControlDao.getById(id);
	}
	
	/**
	 * Cerca tot el personal de l'establiment<!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection aPPCCControlCercaTotsPerPerill(Long idPerill) {
		qualitatAPPCCControlDao.setHibernateTemplate(getHibernateTemplate());
		return qualitatAPPCCControlDao.findByPerill(idPerill);
	}
	
	/**
	 * Cerca tot el personal de l'establiment<!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection aPPCCControlCercaPCCPerEstabliment(Long idEstabliment) {
		qualitatAPPCCControlDao.setHibernateTemplate(getHibernateTemplate());
		return qualitatAPPCCControlDao.findPCCByEstabliment(idEstabliment);
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 * @generated
	 */
	public void aPPCCControlEsborrar(Long id) {
		qualitatAPPCCControlDao.setHibernateTemplate(getHibernateTemplate());
		QualitatAPPCCControl plagues = qualitatAPPCCControlDao.getById(id);
		if (plagues!= null) {
			qualitatAPPCCControlDao.setHibernateTemplate(getHibernateTemplate());
			qualitatAPPCCControlDao.makeTransient(plagues);
		}
	}
	
	
	/**
	 * Només serveix per poder especificar els permisos amb xdoclet. 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ESTADMINISTRADOR"
	 * @ejb.permission role-name="OLI_ESTENCARREGAT"
	 * @ejb.permission role-name="OLI_ESTTREBALLADOR"
	 * @ejb.permission role-name="OLI_ESTCONSULTA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Long crearAPPCFitxaCControl(QualitatAPPCCFitxaControl q){
		qualitatAPPCCFitxaControlDao.setHibernateTemplate(getHibernateTemplate());
		qualitatAPPCCFitxaControlDao.makePersistentFitxa(q);
		return q.getId();
	}	
	
	/**
	 * Només serveix per poder especificar els permisos amb xdoclet. 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ESTADMINISTRADOR"
	 * @ejb.permission role-name="OLI_ESTENCARREGAT"
	 * @ejb.permission role-name="OLI_ESTTREBALLADOR"
	 * @ejb.permission role-name="OLI_ESTCONSULTA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Long crearAPPCCFitxaControlHistoric(QualitatAPPCCFitxaControlHistoric q){
		qualitatAPPCCFitxaControlDao.setHibernateTemplate(getHibernateTemplate());
		qualitatAPPCCFitxaControlDao.makePersistentHistoric(q);
		return q.getId();
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public QualitatAPPCCFitxaControlHistoric aPPCCFitxaControlHistoricAmbId(Long id) {
		qualitatAPPCCFitxaControlDao.setHibernateTemplate(getHibernateTemplate());
		return qualitatAPPCCFitxaControlDao.getHistoricById(id);
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public QualitatAPPCCFitxaControl aPPCCFitxaControlAmbId(Long id) {
		qualitatAPPCCFitxaControlDao.setHibernateTemplate(getHibernateTemplate());
		return qualitatAPPCCFitxaControlDao.getFitxaById(id);
	}
	
	/**
	 * Cerca la darrera fitxa de control del control<!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public QualitatAPPCCFitxaControlHistoric aPPCCFitxaControlPerControl(Long idControl) {
		qualitatAPPCCFitxaControlDao.setHibernateTemplate(getHibernateTemplate());
		return qualitatAPPCCFitxaControlDao.findByControl(idControl);
	}
	
	
	/**
	 * Només serveix per poder especificar els permisos amb xdoclet. 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ESTADMINISTRADOR"
	 * @ejb.permission role-name="OLI_ESTENCARREGAT"
	 * @ejb.permission role-name="OLI_ESTTREBALLADOR"
	 * @ejb.permission role-name="OLI_ESTCONSULTA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Long crearAPPCCPlaVerificacio(QualitatAPPCCPlaVerificacio q){
		qualitatAPPCCPlaVerificacioDao.setHibernateTemplate(getHibernateTemplate());
		qualitatAPPCCPlaVerificacioDao.makePersistent(q);
		return q.getId();
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public QualitatAPPCCPlaVerificacio aPPCCPlaVerificacioAmbId(Long id) {
		qualitatAPPCCPlaVerificacioDao.setHibernateTemplate(getHibernateTemplate());
		return qualitatAPPCCPlaVerificacioDao.getById(id);
	}
	
	/**
	 * Cerca tot el personal de l'establiment<!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection aPPCCPlaVerificacioCercaTotsPerEstabliment(Long idEstabliment) {
		qualitatAPPCCPlaVerificacioDao.setHibernateTemplate(getHibernateTemplate());
		return qualitatAPPCCPlaVerificacioDao.findByEstabliment(idEstabliment);
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 * @generated
	 */
	public void aPPCCPlaVerificacioEsborrar(Long id) {
		qualitatAPPCCPlaVerificacioDao.setHibernateTemplate(getHibernateTemplate());
		QualitatAPPCCPlaVerificacio plagues = qualitatAPPCCPlaVerificacioDao.getById(id);
		if (plagues!= null) {
			qualitatAPPCCPlaVerificacioDao.setHibernateTemplate(getHibernateTemplate());
			qualitatAPPCCPlaVerificacioDao.makeTransient(plagues);
		}
	}
	
	
	/* ------------------------------------------------------------------------ */
	/* 								CONTROL ABASTAMENT AIGUA					*/
	/* ------------------------------------------------------------------------ */
	


	/**
	 * Només serveix per poder especificar els permisos amb xdoclet. 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ESTADMINISTRADOR"
	 * @ejb.permission role-name="OLI_ESTENCARREGAT"
	 * @ejb.permission role-name="OLI_ESTTREBALLADOR"
	 * @ejb.permission role-name="OLI_ESTCONSULTA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Long crearAiguaSubministre(QualitatAiguaSubministre q){
		
		qualitatAiguaSubministreDao.setHibernateTemplate(getHibernateTemplate());
		qualitatAiguaSubministreDao.makePersistent(q);
		return q.getId();
	}
	
	
	/**
	 * Només serveix per poder especificar els permisos amb xdoclet. 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ESTADMINISTRADOR"
	 * @ejb.permission role-name="OLI_ESTENCARREGAT"
	 * @ejb.permission role-name="OLI_ESTTREBALLADOR"
	 * @ejb.permission role-name="OLI_ESTCONSULTA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Long actualitzarAiguaSubministre(QualitatAiguaSubministre q){
		qualitatAiguaSubministreDao.setHibernateTemplate(getHibernateTemplate());
		QualitatAiguaSubministre subministre = qualitatAiguaSubministreDao.getById(q.getId());
		subministre.setNetejaInstalacio(q.getNetejaInstalacio());
		subministre.setHigienePersonal(q.getHigienePersonal());
		subministre.setNetejaRoba(q.getNetejaRoba());
		subministre.setElaboracioProductes(q.getElaboracioProductes());
		subministre.setXarxaPublica(q.getXarxaPublica());
		subministre.setAbastamentPropi(q.getAbastamentPropi());
		subministre.setCloracio(q.getCloracio());
		subministre.setOzonitzacio(q.getOzonitzacio());
		subministre.setFiltracio(q.getFiltracio());
		subministre.setDescalcificacio(q.getDescalcificacio());
		subministre.setSortides(q.getSortides());
		subministre.setResponsablePla(q.getResponsablePla());
		qualitatAiguaSubministreDao.makePersistent(subministre);
		return subministre.getId();
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public QualitatAiguaSubministre aiguaSubministreAmbId(Long id) {
		qualitatAiguaSubministreDao.setHibernateTemplate(getHibernateTemplate());
		return qualitatAiguaSubministreDao.getById(id);
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public QualitatAiguaSubministre aiguaSubministreByEstabliment(Long idEstabliment) {
		qualitatAiguaSubministreDao.setHibernateTemplate(getHibernateTemplate());
		return qualitatAiguaSubministreDao.getByEstabliment(idEstabliment);
	}
	
	
	/**
	 * Només serveix per poder especificar els permisos amb xdoclet. 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ESTADMINISTRADOR"
	 * @ejb.permission role-name="OLI_ESTENCARREGAT"
	 * @ejb.permission role-name="OLI_ESTTREBALLADOR"
	 * @ejb.permission role-name="OLI_ESTCONSULTA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Long crearAiguaPuntSortida(QualitatAiguaPuntSortida q){
		
		qualitatAiguaPuntSortidaDao.setHibernateTemplate(getHibernateTemplate());
		qualitatAiguaPuntSortidaDao.makePersistent(q);
		return q.getId();
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public QualitatAiguaPuntSortida aiguaPuntSortidaAmbId(Long id) {
		qualitatAiguaPuntSortidaDao.setHibernateTemplate(getHibernateTemplate());
		return qualitatAiguaPuntSortidaDao.getById(id);
	}
	
	/**
	 * Cerca tot el personal de l'establiment<!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection aiguaPuntsSortidaPerEstabliment(Long establimentId) {
		qualitatAiguaPuntSortidaDao.setHibernateTemplate(getHibernateTemplate());
		return qualitatAiguaPuntSortidaDao.findByEstabliment(establimentId);
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 * @generated
	 */
	public void aiguaPuntSortidaEsborrar(Long id) {
		qualitatAiguaPuntSortidaDao.setHibernateTemplate(getHibernateTemplate());
		QualitatAiguaPuntSortida sortida = qualitatAiguaPuntSortidaDao.getById(id);
		if (sortida!= null) {
			qualitatAiguaPuntSortidaDao.setHibernateTemplate(getHibernateTemplate());
			qualitatAiguaPuntSortidaDao.makeTransient(sortida);
		}
	}
	
	/**
	 * Només serveix per poder especificar els permisos amb xdoclet. 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ESTADMINISTRADOR"
	 * @ejb.permission role-name="OLI_ESTTREBALLADOR"
	 * @ejb.permission role-name="OLI_ESTCONSULTA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Long crearAiguaControlAnalitic(QualitatAiguaControlAnalitic q){
		
		qualitatAiguaControlAnaliticDao.setHibernateTemplate(getHibernateTemplate());
		qualitatAiguaControlAnaliticDao.makePersistent(q);
		return q.getId();
	}
	
	
	/**
	 * Només serveix per poder especificar els permisos amb xdoclet. 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ESTADMINISTRADOR"
	 * @ejb.permission role-name="OLI_ESTENCARREGAT"
	 * @ejb.permission role-name="OLI_ESTTREBALLADOR"
	 * @ejb.permission role-name="OLI_ESTCONSULTA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Long actualitzarAiguaControlAnalitic(QualitatAiguaControlAnalitic q){
		qualitatAiguaControlAnaliticDao.setHibernateTemplate(getHibernateTemplate());
		QualitatAiguaControlAnalitic sortida = qualitatAiguaControlAnaliticDao.getById(q.getId());
		sortida.setFrequencia(q.getFrequencia());
		sortida.setResponsable(q.getResponsable());
		qualitatAiguaControlAnaliticDao.makePersistent(sortida);
		return sortida.getId();
	}
	
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public QualitatAiguaControlAnalitic aiguaControlAnaliticAmbId(Long id) {
		qualitatAiguaControlAnaliticDao.setHibernateTemplate(getHibernateTemplate());
		return qualitatAiguaControlAnaliticDao.getById(id);
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public QualitatAiguaControlAnalitic aiguaControlAnaliticByEstabliment(Long idEstabliment) {
		qualitatAiguaControlAnaliticDao.setHibernateTemplate(getHibernateTemplate());
		return qualitatAiguaControlAnaliticDao.getByEstabliment(idEstabliment);
	}
	
	
	/**
	 * Només serveix per poder especificar els permisos amb xdoclet. 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ESTADMINISTRADOR"
	 * @ejb.permission role-name="OLI_ESTENCARREGAT"
	 * @ejb.permission role-name="OLI_ESTTREBALLADOR"
	 * @ejb.permission role-name="OLI_ESTCONSULTA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Long crearAiguaControlOrganoleptic(QualitatAiguaControlOrganoleptic q){
		
		qualitatAiguaControlOrganolepticDao.setHibernateTemplate(getHibernateTemplate());
		qualitatAiguaControlOrganolepticDao.makePersistent(q);
		return q.getId();
	}
	
	
	/**
	 * Només serveix per poder especificar els permisos amb xdoclet. 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ESTADMINISTRADOR"
	 * @ejb.permission role-name="OLI_ESTENCARREGAT"
	 * @ejb.permission role-name="OLI_ESTTREBALLADOR"
	 * @ejb.permission role-name="OLI_ESTCONSULTA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Long actualitzarAiguaControlOrganoleptic(QualitatAiguaControlOrganoleptic q){
		qualitatAiguaControlOrganolepticDao.setHibernateTemplate(getHibernateTemplate());
		QualitatAiguaControlOrganoleptic sortida = qualitatAiguaControlOrganolepticDao.getById(q.getId());
		sortida.setFrequencia(q.getFrequencia());
		sortida.setResponsable(q.getResponsable());
		qualitatAiguaControlOrganolepticDao.makePersistent(sortida);
		return sortida.getId();
	}
	
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public QualitatAiguaControlOrganoleptic aiguaControlOrganolepticAmbId(Long id) {
		qualitatAiguaControlOrganolepticDao.setHibernateTemplate(getHibernateTemplate());
		return qualitatAiguaControlOrganolepticDao.getById(id);
	}
	
	
	/**
	 * Només serveix per poder especificar els permisos amb xdoclet. 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ESTADMINISTRADOR"
	 * @ejb.permission role-name="OLI_ESTENCARREGAT"
	 * @ejb.permission role-name="OLI_ESTTREBALLADOR"
	 * @ejb.permission role-name="OLI_ESTCONSULTA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Long crearAiguaControlAnaliticVerificacio(QualitatAiguaControlAnaliticVerificacio q){
		qualitatAiguaControlAnaliticVerificacioDao.setHibernateTemplate(getHibernateTemplate());
		qualitatAiguaControlAnaliticVerificacioDao.makePersistent(q);
		return q.getId();
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public QualitatAiguaControlAnaliticVerificacio aiguaControlAnaliticVerificacioAmbId(Long id) {
		qualitatAiguaControlAnaliticVerificacioDao.setHibernateTemplate(getHibernateTemplate());
		return qualitatAiguaControlAnaliticVerificacioDao.getById(id);
	}
	
	/**
	 * Cerca tot el personal de l'establiment<!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection aiguaControlAnaliticVerificacioPerControl(Long idControl) {
		qualitatAiguaControlAnaliticVerificacioDao.setHibernateTemplate(getHibernateTemplate());
		return qualitatAiguaControlAnaliticVerificacioDao.findByPlaga(idControl);
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 * @generated
	 */
	public void aiguaControlAnaliticVerificacioEsborrar(Long id) {
		qualitatAiguaControlAnaliticVerificacioDao.setHibernateTemplate(getHibernateTemplate());
		QualitatAiguaControlAnaliticVerificacio control = qualitatAiguaControlAnaliticVerificacioDao.getById(id);
		if (control!= null) {
			for(Iterator it = control.getNoConformitats().iterator(); it.hasNext();){
				QualitatNoConformitat nc = (QualitatNoConformitat)it.next();
				nc.setControl(null);
				//control.getNoConformitats().remove(nc);
				qualitatNoConformitatDao.makeTransient(nc);
			}
			qualitatAiguaControlAnaliticVerificacioDao.makeTransient(control);
		}
	}
	
	/**
	 * Només serveix per poder especificar els permisos amb xdoclet. 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ESTADMINISTRADOR"
	 * @ejb.permission role-name="OLI_ESTENCARREGAT"
	 * @ejb.permission role-name="OLI_ESTTREBALLADOR"
	 * @ejb.permission role-name="OLI_ESTCONSULTA"
	 * <!-- end-xdoclet-definition -->
	 */
	public Long crearAiguaControlOrganolepticVerificacio(QualitatAiguaControlOrganolepticVerificacio q){
		qualitatAiguaControlOrganolepticVerificacioDao.setHibernateTemplate(getHibernateTemplate());
		qualitatAiguaControlOrganolepticVerificacioDao.makePersistent(q);
		return q.getId();
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public QualitatAiguaControlOrganolepticVerificacio aiguaControlOrganolepticVerificacioAmbId(Long id) {
		qualitatAiguaControlOrganolepticVerificacioDao.setHibernateTemplate(getHibernateTemplate());
		return qualitatAiguaControlOrganolepticVerificacioDao.getById(id);
	}
	
	/**
	 * Cerca tot el personal de l'establiment<!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 */
	public Collection aiguaControlOrganolepticVerificacioPerControl(Long idControl) {
		qualitatAiguaControlOrganolepticVerificacioDao.setHibernateTemplate(getHibernateTemplate());
		return qualitatAiguaControlOrganolepticVerificacioDao.findByPlaga(idControl);
	}
	
	/**
	 * 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" <!-- end-xdoclet-definition -->
	 * @generated
	 */
	public void aiguaControlOrganolepticVerificacioEsborrar(Long id) {
		qualitatAiguaControlOrganolepticVerificacioDao.setHibernateTemplate(getHibernateTemplate());
		QualitatAiguaControlOrganolepticVerificacio control = qualitatAiguaControlOrganolepticVerificacioDao.getById(id);
		if (control!= null) {
			for(Iterator it = control.getNoConformitats().iterator(); it.hasNext();){
				QualitatNoConformitat nc = (QualitatNoConformitat)it.next();
				nc.setControl(null);
				//control.getNoConformitats().remove(nc);
				qualitatNoConformitatDao.makeTransient(nc);
			}
			qualitatAiguaControlOrganolepticVerificacioDao.makeTransient(control);
		}
	}
	
	
	/*********************************************************
	 * *********************** AVISOS ************************
	 * ***************************************************** */
	
	/**
	 * Cerca les tasques pendents dels plans de NetejaRealitzacio associats a un establiment.
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" 
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection netejaRealitzacioCercaPendentsPerEstabliment(Long idEstabliment) {
		List pendents = new ArrayList();
		java.util.Date today = new java.util.Date();
		
		qualitatPlaNetejaDao.setHibernateTemplate(getHibernateTemplate());
		Collection plansNeteja = qualitatPlaNetejaDao.plansNetejaPerEstabliment(idEstabliment);
		
		if (plansNeteja.size() > 0){
			Iterator itNeteja = plansNeteja.iterator();
			while(itNeteja.hasNext()){
			//for (Object plaNetejaObj : plansNeteja){
				QualitatPlaNeteja plaNeteja =  (QualitatPlaNeteja)itNeteja.next(); 
				qualitatPlaNetejaRealitzacioDao.setHibernateTemplate(getHibernateTemplate());
				List netejaRealitzacions = qualitatPlaNetejaRealitzacioDao.findByNeteja(plaNeteja.getCodi());
		
				if (netejaRealitzacions.size() > 0){
					Long id = 0l;
					Iterator itRealitzacio = netejaRealitzacions.iterator();
					while(itRealitzacio.hasNext()){
					//for (Object realitzacioObj : netejaRealitzacions){
						QualitatPlaNetejaRealitzacio realitzacio = (QualitatPlaNetejaRealitzacio) itRealitzacio.next();
						if (id != realitzacio.getNeteja().getCodi()){
							if (today.compareTo(QualitatDateHelper.afegirData(realitzacio.getDataRealitzacio(), realitzacio.getNeteja().getFrequencia())) == 1){
								pendents.add(plaNeteja);
							}
							id = realitzacio.getNeteja().getCodi();
						}
					}
				} else {
					pendents.add(plaNeteja);
				}
				
			}
				
		}
		
		return pendents;
	}
	
	/**
	 * Cerca les tasques pendents dels plans de NetejaRealitzacio associats a un establiment.
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" 
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection netejaVerificacioCercaPendentsPerEstabliment(Long idEstabliment) {
		List pendents = new ArrayList();
		java.util.Date today = new java.util.Date();
		
		qualitatPlaNetejaDao.setHibernateTemplate(getHibernateTemplate());
		Collection plansNeteja = qualitatPlaNetejaDao.plansNetejaPerEstabliment(idEstabliment);
		
		if (plansNeteja.size() > 0){
			Iterator itNeteja = plansNeteja.iterator();
			while(itNeteja.hasNext()){
			//for (Object plaNetejaObj : plansNeteja){
				QualitatPlaNeteja plaNeteja =  (QualitatPlaNeteja)itNeteja.next(); 
				qualitatPlaNetejaVerificacioDao.setHibernateTemplate(getHibernateTemplate());
				List netejaVerificacions = qualitatPlaNetejaVerificacioDao.findByNeteja(plaNeteja.getCodi());
		
				if (netejaVerificacions.size() > 0){
					Long id = 0l;
					Iterator itVerificacio = netejaVerificacions.iterator();
					while(itVerificacio.hasNext()){
					//for (Object realitzacioObj : netejaRealitzacions){
						QualitatPlaNetejaVerificacio verificacio = (QualitatPlaNetejaVerificacio) itVerificacio.next();
						if (id != verificacio.getNeteja().getCodi()){
							if (today.compareTo(QualitatDateHelper.afegirData(verificacio.getDataVerificacio(), verificacio.getNeteja().getPeriodicitatVerificacio())) == 1){
								pendents.add(plaNeteja);
							}
							id = verificacio.getNeteja().getCodi();
						}
					}
				} else {
					pendents.add(plaNeteja);
				}
				
			}
				
		}
		
		return pendents;
	}
	
	
	/**
	 * Cerca les tasques pendents dels plans de NetejaRealitzacio associats a un establiment.
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" 
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection mantenimentRealitzacioCercaPendentsPerEstabliment(Long idEstabliment) {
		List pendents = new ArrayList();
		java.util.Date today = new java.util.Date();
		
		qualitatPlaMantenimentDao.setHibernateTemplate(getHibernateTemplate());
		Collection plansManteniment = qualitatPlaMantenimentDao.findByEstabliment(idEstabliment);
		
		if (plansManteniment.size() > 0){
			Iterator itManteniment = plansManteniment.iterator();
			while(itManteniment.hasNext()){
			//for (Object plaNetejaObj : plansNeteja){
				QualitatPlaManteniment plaManteniment =  (QualitatPlaManteniment)itManteniment.next(); 
				qualitatPlaMantenimentControlDao.setHibernateTemplate(getHibernateTemplate());
				List mantenimentControls = qualitatPlaMantenimentControlDao.findByManteniment(plaManteniment.getId());
		
				if (mantenimentControls.size() > 0){
					Long id = 0l;
					Iterator itControl = mantenimentControls.iterator();
					while(itControl.hasNext()){
					//for (Object realitzacioObj : netejaRealitzacions){
						QualitatPlaMantenimentControl control = (QualitatPlaMantenimentControl) itControl.next();
						if (id != control.getManteniment().getId()){
							if (today.compareTo(QualitatDateHelper.afegirData(control.getDataRealitzacio(), control.getManteniment().getFrecuencia())) == 1){
								pendents.add(plaManteniment);
							}
							id = control.getManteniment().getId();
						}
					}
				} else {
					pendents.add(plaManteniment);
				}
				
			}
				
		}
		
		return pendents;
	}
	
	/**
	 * Cerca les tasques pendents dels plans de NetejaRealitzacio associats a un establiment.
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" 
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection mantenimentVerificacioCercaPendentsPerEstabliment(Long idEstabliment) {
		List pendents = new ArrayList();
		
		qualitatPlaMantenimentDao.setHibernateTemplate(getHibernateTemplate());
		Collection plansManteniment = qualitatPlaMantenimentDao.findByEstabliment(idEstabliment);
		
		if (plansManteniment.size() > 0){
			Iterator itManteniment = plansManteniment.iterator();
			while(itManteniment.hasNext()){
			//for (Object plaNetejaObj : plansNeteja){
				QualitatPlaManteniment plaManteniment =  (QualitatPlaManteniment)itManteniment.next(); 
				qualitatPlaMantenimentControlDao.setHibernateTemplate(getHibernateTemplate());
				List mantenimentControls = qualitatPlaMantenimentControlDao.findByManteniment(plaManteniment.getId());
		
				if (mantenimentControls.size() > 0){
					Iterator itControl = mantenimentControls.iterator();
					while(itControl.hasNext()){
					//for (Object realitzacioObj : netejaRealitzacions){
						QualitatPlaMantenimentControl control = (QualitatPlaMantenimentControl) itControl.next();
						if (control.getDataVerificacio() == null){
							pendents.add(control);
						}
					}
				}
				
			}
				
		}
		
		return pendents;
	}
	
	
	/**
	 * Cerca les tasques pendents dels plans de NetejaRealitzacio associats a un establiment.
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" 
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection controlPlaguesVerificacioCercaPendentsPerEstabliment(Long idEstabliment) {
		List pendents = new ArrayList();
		java.util.Date today = new java.util.Date();
		
		qualitatControlPlaguesDao.setHibernateTemplate(getHibernateTemplate());
		Collection controlsPlagues = qualitatControlPlaguesDao.findByEstabliment(idEstabliment);
		
		if (controlsPlagues.size() > 0){
			Iterator itControl = controlsPlagues.iterator();
			while(itControl.hasNext()){
			//for (Object plaNetejaObj : plansNeteja){
				QualitatControlPlagues controlPlagues =  (QualitatControlPlagues)itControl.next(); 
				qualitatControlPlaguesVerificacioDao.setHibernateTemplate(getHibernateTemplate());
				List verificacionsPlagues = qualitatControlPlaguesVerificacioDao.findByPlaga(controlPlagues.getId());
		
				if (verificacionsPlagues.size() > 0){
					//Long id = 0l;
					Iterator itVerificiacio = verificacionsPlagues.iterator();
					//while(itVerificiacio.hasNext()){
					//for (Object realitzacioObj : netejaRealitzacions){
						QualitatControlPlaguesVerificacio verificacio = (QualitatControlPlaguesVerificacio) itVerificiacio.next();
						//if (id != verificacio.getManteniment().getId()){
						if (!"".equalsIgnoreCase(verificacio.getControlPlaga().getFrecuencia())){ 
							if (today.compareTo(QualitatDateHelper.afegirData(verificacio.getDataVerificacio(), verificacio.getControlPlaga().getFrecuencia())) == 1){
								pendents.add(controlPlagues);
							}
						//	id = control.getManteniment().getId();
						//}
						} else if (!"".equalsIgnoreCase(verificacio.getControlPlaga().getFrecSeguiment())){
							if (today.compareTo(QualitatDateHelper.afegirData(verificacio.getDataVerificacio(), verificacio.getControlPlaga().getFrecSeguiment())) == 1){
								pendents.add(controlPlagues);
							}							
						}
					//}
				} else {
					pendents.add(controlPlagues);
				}
				
			}
				
		}
		
		return pendents;
	}
	
	
	/**
	 * Cerca les tasques pendents dels plans de NetejaRealitzacio associats a un establiment.
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" 
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection proveidorsVerificacioCercaPendentsPerEstabliment(Long idEstabliment) {
		List pendents = new ArrayList();
		java.util.Date today = new java.util.Date();
		
		qualitatProveidorDao.setHibernateTemplate(getHibernateTemplate());
		Collection proveidors = qualitatProveidorDao.getAllByEstabliment(idEstabliment);
		
		if (proveidors.size() > 0){
			Iterator itProveidor = proveidors.iterator();
			while(itProveidor.hasNext()){
			//for (Object plaNetejaObj : plansNeteja){
				QualitatProveidor proveidor =  (QualitatProveidor)itProveidor.next(); 
				qualitatProveidorAvaluacioDao.setHibernateTemplate(getHibernateTemplate());
				List verificacionsProveidors = qualitatProveidorAvaluacioDao.findByProveidor(proveidor.getCodi());
		
				if (verificacionsProveidors.size() > 0){
					//Long id = 0l;
					Iterator itVerificiacio = verificacionsProveidors.iterator();
					//while(itVerificiacio.hasNext()){
					//for (Object realitzacioObj : netejaRealitzacions){
						QualitatProveidorAvaluacio verificacio = (QualitatProveidorAvaluacio) itVerificiacio.next();
						//if (id != verificacio.getManteniment().getId()){
							if (today.compareTo(verificacio.getProximaAvaluacio()) == 1){
								pendents.add(proveidor);
							}
						//	id = control.getManteniment().getId();
						//}
					//}
				} else {
					pendents.add(proveidor);
				}
				
			}
				
		}
		
		return pendents;
	}
	


	/**
	 * Cerca les tasques pendents dels plans de NetejaRealitzacio associats a un establiment.
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" 
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection controlAiguaVerificacioCercaPendentsPerEstabliment(Long idEstabliment) {
		List pendents = new ArrayList();
		java.util.Date today = new java.util.Date();
		
		qualitatAiguaControlOrganolepticDao.setHibernateTemplate(getHibernateTemplate());
		Collection controlsAigues = qualitatAiguaControlOrganolepticDao.findByEstabliment(idEstabliment);
		
		if (controlsAigues.size() > 0){
			Iterator itControl = controlsAigues.iterator();
			while(itControl.hasNext()){
			//for (Object plaNetejaObj : plansNeteja){
				QualitatAiguaControlOrganoleptic controlAigues =  (QualitatAiguaControlOrganoleptic)itControl.next(); 
				qualitatAiguaControlOrganolepticVerificacioDao.setHibernateTemplate(getHibernateTemplate());
				List verificacionsAigues = qualitatAiguaControlOrganolepticVerificacioDao.findByPlaga(controlAigues.getId());
		
				if (verificacionsAigues.size() > 0){
					//Long id = 0l;
					Iterator itVerificiacio = verificacionsAigues.iterator();
					//while(itVerificiacio.hasNext()){
					//for (Object realitzacioObj : netejaRealitzacions){
						QualitatAiguaControlOrganolepticVerificacio verificacio = (QualitatAiguaControlOrganolepticVerificacio) itVerificiacio.next();
						//if (id != verificacio.getManteniment().getId()){
						if (!"".equalsIgnoreCase(verificacio.getControlOrganoleptic().getFrequencia())){ 
							if (today.compareTo(QualitatDateHelper.afegirData(verificacio.getDataVerificacio(), verificacio.getControlOrganoleptic().getFrequencia())) == 1){
								pendents.add(controlAigues);
							}
						//	id = control.getManteniment().getId();
						//}
						}
					//}
				} else {
					pendents.add(controlAigues);
				}
				
			}
				
		}
		
		return pendents;
	}
	

	/**
	 * Cerca les tasques pendents dels plans de NetejaRealitzacio associats a un establiment.
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" 
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection APPCCControlCercaPendentsPerEstabliment(Long idEstabliment) {
		List pendents = new ArrayList();
		
		// Devuelve el listado de PCs o PCCs despues de responder a las preguntas de los tests.
		Collection appccs = aPPCCControlCercaPCCPerEstabliment(idEstabliment);
		
		if (appccs.size() > 0){
			Iterator itAPPCC = appccs.iterator();
		
			while(itAPPCC.hasNext()){
				//for (Object plaNetejaObj : plansNeteja){
				QualitatAPPCCControl controlAPPCCs =  (QualitatAPPCCControl)itAPPCC.next(); 

				QualitatAPPCCFitxaControlHistoric controlHistoric = aPPCCFitxaControlPerControl(controlAPPCCs.getId());
		
				if (controlHistoric == null){
					pendents.add(controlAPPCCs);
				}
		
			}
				
		}
		
		return pendents;
	}
	
	/**
	 * Cerca les tasques pendents dels plans de NetejaRealitzacio associats a un establiment.
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" 
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection APPCCVerificacioCercaPendentsPerEstabliment(Long idEstabliment) {
		List pendents = new ArrayList();
		java.util.Date today = new java.util.Date();
		
		// Devuelve el listado de PCs o PCCs despues de responder a las preguntas de los tests.
		Collection appccs = aPPCCControlCercaPCCPerEstabliment(idEstabliment);
		
		if (appccs.size() > 0){
			Iterator itAPPCC = appccs.iterator();
		
			while(itAPPCC.hasNext()){
				//for (Object plaNetejaObj : plansNeteja){

				QualitatAPPCCControl controlAPPCCs =  (QualitatAPPCCControl)itAPPCC.next(); 

				QualitatAPPCCFitxaControlHistoric controlHistoric = aPPCCFitxaControlPerControl(controlAPPCCs.getId());
		
				if (controlHistoric != null){
					if (!"".equalsIgnoreCase(controlHistoric.getFreqVigilancia())){ 
						if (controlAPPCCs.getDataVerificacio() != null){
							if (today.compareTo(QualitatDateHelper.afegirData(controlAPPCCs.getDataVerificacio(), controlHistoric.getFreqVigilancia())) == 1){
								pendents.add(controlHistoric);
							}
						} else {
							pendents.add(controlHistoric);
						}
					}
				}
				
			}
				
		}
		
		return pendents;
	}
	
	
	/**
	 * Cerca totes les noConformitats no tancades de l'establiment
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR" 
	 * <!-- end-xdoclet-definition -->
	 */
	public Collection noConformitatsObertesPerEstabliment(Long idEstabliment) {
		qualitatNoConformitatDao.setHibernateTemplate(getHibernateTemplate());
		return qualitatNoConformitatDao.findObertesByEstabliment(idEstabliment);
	}
	
	
	
	/**
	 * Només serveix per poder especificar els permisos amb xdoclet. 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ESTADMINISTRADOR"
	 * @ejb.permission role-name="OLI_ESTENCARREGAT"
	 * @ejb.permission role-name="OLI_ESTTREBALLADOR"
	 * @ejb.permission role-name="OLI_ESTCONSULTA"
	 * <!-- end-xdoclet-definition -->
	 */
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate){
		this.hibernateTemplate = hibernateTemplate;
	}


	/**
	 * Només serveix per poder especificar els permisos amb xdoclet. 
	 * <!-- begin-xdoclet-definition -->
	 * 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ESTADMINISTRADOR"
	 * @ejb.permission role-name="OLI_ESTENCARREGAT"
	 * @ejb.permission role-name="OLI_ESTTREBALLADOR"
	 * @ejb.permission role-name="OLI_ESTCONSULTA"
	 * <!-- end-xdoclet-definition -->
	 */
	public HibernateTemplate getHibernateTemplate(){
		return this.hibernateTemplate;
	}
	
	
}