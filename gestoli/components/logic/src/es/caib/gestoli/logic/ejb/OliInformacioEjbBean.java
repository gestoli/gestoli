/**
 * 
 */
package es.caib.gestoli.logic.ejb;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.dao.ArxiuDao;
import es.caib.gestoli.logic.dao.CategoriaInformacioDao;
import es.caib.gestoli.logic.dao.DocumentDao;
import es.caib.gestoli.logic.dao.EstablimentDao;
import es.caib.gestoli.logic.dao.InformacioDao;
import es.caib.gestoli.logic.dao.OlivicultorDao;
import es.caib.gestoli.logic.model.Arxiu;
import es.caib.gestoli.logic.model.CategoriaInformacio;
import es.caib.gestoli.logic.model.Document;
import es.caib.gestoli.logic.model.Establiment;
import es.caib.gestoli.logic.model.Informacio;
import es.caib.gestoli.logic.model.Olivicultor;


/**
 *
 * <!-- begin-user-doc -->
 * A generated session bean
 * <!-- end-user-doc -->
 * *
 * <!-- begin-xdoclet-definition --> 
 * @ejb.bean name="OliInformacioEjb"	
 *           description="An EJB named OliInformacioEjb"
 *           display-name="OliInformacioEjb"
 *           jndi-name="OliInformacioEjb"
 *           local-jndi-name="OliInformacioEjbLocal"
 *           type="Stateless" 
 *           transaction-type="Container"
 * @ejb.transaction type="Required"
 * 
 * <!-- end-xdoclet-definition --> 
 * @generated
 */

public abstract class OliInformacioEjbBean implements javax.ejb.SessionBean {
	
	private InformacioDao informacioDao;
	private CategoriaInformacioDao categoriaInformacioDao;
	private DocumentDao documentDao;
	private ArxiuDao arxiuDao;
	private OlivicultorDao olivicultorDao;
	private EstablimentDao establimentDao;
	private HibernateTemplate hibernateTemplate;
	
	
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
	 * @generated
	 */
	public void ejbCreate() {
		
		informacioDao = new InformacioDao();
		categoriaInformacioDao = new CategoriaInformacioDao();
		documentDao = new DocumentDao();
		arxiuDao = new ArxiuDao();
		olivicultorDao = new OlivicultorDao();
		establimentDao = new EstablimentDao();
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
	 * @generated
	 */
	public void ejbRemove() {}

	
	
	/**
	 * Devuelve una lista de informaciones
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
	public Collection llistatInformacio() {
		informacioDao.setHibernateTemplate(getHibernateTemplate());
		return informacioDao.findAll();
	}
	
	
	
	/**
	 * Devuelve una lista de informaciones filtrada
	 * 
	 * @return collection
	 * 
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * <!-- end-xdoclet-definition --> 
	 */
	public Collection llistatInformacioFiltre(Integer categoria) {
		informacioDao.setHibernateTemplate(getHibernateTemplate());
		return informacioDao.findAllFiltre(categoria);
	}
	
	
	
	/**
	 * Devuelve una lista de informaciones
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
	public List documentsByInformacio(Integer idInformacio) {
		documentDao.setHibernateTemplate(getHibernateTemplate());
		return documentDao.findByInformacio(idInformacio);
	}
	
	
	/**
	 * Recupera todas las categorias 
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * <!-- end-xdoclet-definition --> 
	 */
	public Collection categoriaInformacioFindAll(){
		categoriaInformacioDao.setHibernateTemplate(getHibernateTemplate());
		return categoriaInformacioDao.findAll();
	}
	
	
	/**
	 * Recupera la categoria con ese id
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * <!-- end-xdoclet-definition --> 
	 */
	public CategoriaInformacio categoriaInformacioAmbId(Integer id){
		categoriaInformacioDao.setHibernateTemplate(getHibernateTemplate());
		return categoriaInformacioDao.getById(id);
	}
	
	
	/**
	 * Devuelve verdadero si existe alguna informacion relacionada con la categoria
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * <!-- end-xdoclet-definition --> 
	 */	
	public boolean existenInformacionesAsociadasCategoria(Integer id){
		categoriaInformacioDao.setHibernateTemplate(getHibernateTemplate());
		return categoriaInformacioDao.existenInformacionesAsociadasToCategoria(id);		
	}
	
	
	
	/**
	 * Devuelve verdadero si existe una categoria con ese nombre
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * <!-- end-xdoclet-definition --> 
	 */	
	public boolean existeOtraCategoriaConNombre(String nom, Integer id){
		categoriaInformacioDao.setHibernateTemplate(getHibernateTemplate());
		return categoriaInformacioDao.existeOtraCategoriaConNombre(nom,id);		
	}
	
	
	/**
	 * Borra una categoria
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * <!-- end-xdoclet-definition --> 
	 */	
	public void categoriaInformacioEsborrar(Integer id){
		categoriaInformacioDao.setHibernateTemplate(getHibernateTemplate());
		CategoriaInformacio cat = categoriaInformacioDao.getById(id);
		if (cat !=null){
			categoriaInformacioDao.setHibernateTemplate(getHibernateTemplate());
			categoriaInformacioDao.makeTransient(cat);
		}
	}
	
	
	
	/**
	 * Crea una categoria
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * <!-- end-xdoclet-definition --> 
	 */	
	public Integer informacioCategoriaCrear(String nom, String desc){		
		CategoriaInformacio cat = new CategoriaInformacio();
		cat.setNom(nom);
		cat.setDescripcio(desc);
		categoriaInformacioDao.setHibernateTemplate(getHibernateTemplate());
		categoriaInformacioDao.makePersistent(cat);
		return cat.getId();
	}
	
	
	/**
	 * Modifica una categoria
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * <!-- end-xdoclet-definition --> 
	 */	
	public void informacioCategoriaModificar(Integer id, String nom, String desc){
		categoriaInformacioDao.setHibernateTemplate(getHibernateTemplate());
		CategoriaInformacio cat = categoriaInformacioDao.getById(id);
		cat.setNom(nom);
		cat.setDescripcio(desc);
		categoriaInformacioDao.setHibernateTemplate(getHibernateTemplate());
		categoriaInformacioDao.makePersistent(cat);
	}
	
	
	/**
	 * Modifica una categoria
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * <!-- end-xdoclet-definition --> 
	 */	
	public Informacio informacioAmbId(Integer id){
		informacioDao.setHibernateTemplate(getHibernateTemplate());
		return informacioDao.getById(id);
	}
	
	
	/**
	 * Modifica una categoria
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_ESTADMINISTRADOR"
	 * <!-- end-xdoclet-definition --> 
	 */	
	public Document documentAmbId(Integer id){
		documentDao.setHibernateTemplate(getHibernateTemplate());
		return documentDao.getById(id);
	}
	
	/**
	 * Crea una informacio
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * <!-- end-xdoclet-definition --> 
	 */	
	public Integer informacioCrear(Informacio informacio){		
		informacioDao.setHibernateTemplate(getHibernateTemplate());
		informacioDao.makePersistent(informacio);
		return informacio.getId();
	}
	
	
	/**
	 * Modifica una informacio
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * <!-- end-xdoclet-definition --> 
	 */	
	public void informacioModificar(Informacio informacio, Boolean informar){
		if (informar != null && informar.booleanValue()) { // Borramos las relaciones vistas por el usuario
			informacio.getEstabliments().clear();
			informacio.getOlivicultors().clear();
		}
		
		informacioDao.setHibernateTemplate(getHibernateTemplate());
		informacioDao.makePersistent(informacio);
	}
	
	/**
	 * Modifica una informacio
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * <!-- end-xdoclet-definition --> 
	 */	
	public void informacioEsborrar(Informacio informacio){
		informacioDao.setHibernateTemplate(getHibernateTemplate());
		informacioDao.makeTransient(informacio);
	}
	
	/**
	 * Crea una informacio
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_ESTADMINISTRADOR"
	 * <!-- end-xdoclet-definition --> 
	 */	
	public void documentCrear(Document document, Arxiu arxiu){
		
		if (arxiu != null) {
			arxiuDao.setHibernateTemplate(getHibernateTemplate());
			arxiuDao.makePersistent(arxiu);
		}
		document.setArxiu(arxiu.getId());
		
		documentDao.setHibernateTemplate(getHibernateTemplate());
		documentDao.makePersistent(document);
	}
	
	/**
	 * Crea una informacio
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_ESTADMINISTRADOR"
	 * <!-- end-xdoclet-definition --> 
	 */	
	public void documentModificar(Document document, Arxiu arxiu){
		
		if (arxiu != null) {
			arxiuDao.setHibernateTemplate(getHibernateTemplate());
			arxiuDao.makePersistent(arxiu);
		}
		
		documentDao.setHibernateTemplate(getHibernateTemplate());
		documentDao.makePersistent(document);
	}
	
	/**
	 * Crea una informacio
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_ADMINISTRACIO"
	 * @ejb.permission role-name="OLI_DOCONTROLADOR"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * @ejb.permission role-name="OLI_ESTADMINISTRADOR"
	 * <!-- end-xdoclet-definition --> 
	 */	
	public void documentEsborrar(Integer id){
		
		documentDao.setHibernateTemplate(getHibernateTemplate());
		Document document = documentDao.getById(id);
		if (document.getArxiu() != null) {
			arxiuDao.setHibernateTemplate(getHibernateTemplate());
			Arxiu arxiu = arxiuDao.getById(document.getArxiu());
			arxiuDao.setHibernateTemplate(getHibernateTemplate());
			arxiuDao.makeTransient(arxiu);
		}
		
		documentDao.makeTransient(document);
	}
	
	
	
	/**
	 * Marca que un olivicultor ha leido la informacion
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * <!-- end-xdoclet-definition --> 
	 */	
	public void olivicultorLeeInfo(Long idOli, Informacio info){
		Set olis = info.getOlivicultors();
		Olivicultor oli = new Olivicultor();
		oli.setId(idOli);
		olis.add(oli);
		info.setOlivicultors(olis);
		informacioDao.setHibernateTemplate(getHibernateTemplate());
		informacioDao.makePersistent(info);
	}
	

	
	/**
	 * Marca que un establecimiento ha leido la informacion
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * <!-- end-xdoclet-definition --> 
	 */	
	public void establecimientoLeeInfo(Long idEst, Informacio info){
		Set ests = info.getEstabliments();
		Establiment est = new Establiment();
		est.setId(idEst);
		ests.add(est);
		info.setEstabliments(ests);
		informacioDao.setHibernateTemplate(getHibernateTemplate());
		informacioDao.makePersistent(info);
	}
	
	
	
	
	/**
	 * Indica si un establecimiento ha leido una informacion
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * <!-- end-xdoclet-definition --> 
	 */
	public boolean establecimientoHaLeido(Long idEst, Integer idInfo) {
		informacioDao.setHibernateTemplate(getHibernateTemplate());
		Collection ests = informacioDao.findEstablimentsByInformacio(idInfo);
		establimentDao.setHibernateTemplate(getHibernateTemplate());
		Establiment establiment = establimentDao.getById(idEst);
		Iterator iteEsts = ests.iterator();
		while (iteEsts.hasNext()){
			Establiment est = (Establiment)iteEsts.next();
			informacioDao.setHibernateTemplate(getHibernateTemplate());
			if (est.getIdOriginal().equals(establiment.getIdOriginal())){
				return true;
			}
		}
		
		return false;
	}
	
	
	
	/**
	 * Indica si un olivicultor ha leido una informacion
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * <!-- end-xdoclet-definition --> 
	 */
	public boolean olivicultorHaLeido(Long idOli, Integer idInfo) {
		informacioDao.setHibernateTemplate(getHibernateTemplate());
		Collection olis = informacioDao.findOlivicultorsByInformacio(idInfo);
		olivicultorDao.setHibernateTemplate(getHibernateTemplate());
		Olivicultor olivicultor = olivicultorDao.getById(idOli);
		Iterator iteOlis = olis.iterator();
		while (iteOlis.hasNext()){
			Olivicultor oli = (Olivicultor)iteOlis.next();
			informacioDao.setHibernateTemplate(getHibernateTemplate());
			if (oli.getIdOriginal().equals(olivicultor.getIdOriginal())){
				return true;
			}
		}
		
		return false;
	}
	
	
	/**
	 * Indica si un establecimiento ha leido todos los documentos
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * <!-- end-xdoclet-definition --> 
	 */
	public boolean establecimientoHaLeidoTodo(Long id) {
		informacioDao.setHibernateTemplate(getHibernateTemplate());
		Collection infos = informacioDao.findAll();
		if ((infos != null) && (infos.size() > 0)) {
			Iterator iteInfos = infos.iterator();
			while (iteInfos.hasNext()){
				Informacio info = (Informacio)iteInfos.next();
				
				if (!establecimientoHaLeido(id, info.getId())){
					return false;
				}
				
			}
		}
		return true;
	}


	/**
	 * Indica si un olivicultor ha leido todos los documentos
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * <!-- end-xdoclet-definition --> 
	 */
	public boolean olivicultorHaLeidoTodo(Long id) {
		informacioDao.setHibernateTemplate(getHibernateTemplate());
		Collection infos = informacioDao.findAll();
		Iterator iteInfos = infos.iterator();
		while (iteInfos.hasNext()){
			Informacio info = (Informacio)iteInfos.next();
			if (!olivicultorHaLeido(id, info.getId())){
				return false;
			}
		}
		return true;
	}


	/**
	 * Indica si existen informaciones
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_PRODUCTOR"
	 * @ejb.permission role-name="OLI_ENVASADOR"
	 * @ejb.permission role-name="OLI_OLIVICULTOR"
	 * <!-- end-xdoclet-definition --> 
	 */
	public boolean existenInformaciones() {
		informacioDao.setHibernateTemplate(getHibernateTemplate());
		return informacioDao.existenInformaciones();
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