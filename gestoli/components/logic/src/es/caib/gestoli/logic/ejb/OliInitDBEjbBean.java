
package es.caib.gestoli.logic.ejb;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import javax.ejb.SessionBean;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.HibernateTemplate;

import es.caib.gestoli.logic.dao.ArxiuDao;
import es.caib.gestoli.logic.dao.ColorDao;
import es.caib.gestoli.logic.dao.GenDao;
import es.caib.gestoli.logic.dao.MaterialDipositDao;
import es.caib.gestoli.logic.dao.MaterialTipusEnvasDao;
import es.caib.gestoli.logic.dao.VarietatOlivaDao;
import es.caib.gestoli.logic.model.Arxiu;
import es.caib.gestoli.logic.model.Color;
import es.caib.gestoli.logic.model.MaterialDiposit;
import es.caib.gestoli.logic.model.MaterialTipusEnvas;
import es.caib.gestoli.logic.model.VarietatOliva;


/**
 *
 * <!-- begin-user-doc -->
 * A generated session bean
 * <!-- end-user-doc -->
 * *
 * <!-- begin-xdoclet-definition --> 
 * @ejb.bean name="OliInitDBEjb"	
 *           description="An EJB named OliInitDBEjb"
 *           display-name="OliInitDBEjb"
 *           jndi-name="OliInitDBEjb"
 *           local-jndi-name="OliInitDBEjbLocal"
 *           type="Stateless" 
 *           transaction-type="Container"
 * @ejb.transaction type="Required"
 * 
 * <!-- end-xdoclet-definition --> 
 */

public abstract class OliInitDBEjbBean implements SessionBean {
	private static Logger logger = Logger.getLogger(OliInitDBEjbBean.class);

	private GenDao genDao;
	private ColorDao colorDao;
	private MaterialTipusEnvasDao materialTipusEnvasDao;
	private MaterialDipositDao materialDipositDao;
	private ArxiuDao arxiuDao;
	private VarietatOlivaDao varietatOlivaDao;

	private Object[][] colors = {
			{"Blanc", "FFFFFF"}, 
			{"Gris", "AAAAAA"}, 
			{"Negre", "000000"}, 
			{"Vermell", "FF0000"}, 
			{"Verd", "00FF00"}, 
			{"Blau", "0000FF"}, 
			{"Transparent", null} 
	};
	private Object[][] arxius = {  
			{"diposit_acer.gif", "image/gif", new Integer(35), new Integer(43), "es/caib/gestoli/logic/resources/diposit_acer.gif"}, 
			{"diposit_fibra.gif", "image/gif", new Integer(60), new Integer(33), "es/caib/gestoli/logic/resources/diposit_fibra.gif"}, 
			{"diposit_plastic.gif", "image/gif", new Integer(42), new Integer(44), "es/caib/gestoli/logic/resources/diposit_plastic.gif"}, 
			{"botella_plastic.gif", "image/gif", new Integer(28), new Integer(44), "es/caib/gestoli/logic/resources/botella_plastic.gif"}, 
			{"botella_vidre_transparent.gif", "image/gif", new Integer(28), new Integer(42), "es/caib/gestoli/logic/resources/botella_vidre_transparent.gif"}, 
			{"botella_vidre_coloretjat.gif", "image/gif", new Integer(28), new Integer(41), "es/caib/gestoli/logic/resources/botella_vidre_coloretjat.gif"}, 
			{"oliva_arbequina.gif", "image/gif", new Integer(38), new Integer(41), "es/caib/gestoli/logic/resources/oliva_arbequina.gif"}, 
			{"oliva_mallorquina.gif", "image/gif", new Integer(41), new Integer(44), "es/caib/gestoli/logic/resources/oliva_mallorquina.gif"}, 
			{"oliva_taula.gif", "image/gif", new Integer(41), new Integer(44), "es/caib/gestoli/logic/resources/oliva_taula.gif"}, 
			{"oliva_taula_negre.gif", "image/gif", new Integer(41), new Integer(44), "es/caib/gestoli/logic/resources/oliva_taula_negre.gif"}, 
			{"oliva_picual.gif", "image/gif", new Integer(40), new Integer(30), "es/caib/gestoli/logic/resources/oliva_picual.gif"}, 
			{"oliva_altre.gif", "image/gif", new Integer(41), new Integer(25), "es/caib/gestoli/logic/resources/oliva_altre.gif"}, 
			{"llauna_acer.gif", "image/gif", new Integer(50), new Integer(47), "es/caib/gestoli/logic/resources/llauna_acer.gif"} 
	};
	private Object[][] materialsTipusEnvasos = {
			{"Plàstic", new Long(4)}, 
			{"Vidre transparent", new Long(5)}, 
			{"Vidre coloretjat", new Long(6)}, 
			{"Llauna d'acer", new Long(11)} 
	};
	private Object[][] materialsDiposits = {
			{"Acer inoxidable", new Long(1)}, 
			{"Fibra de vidre", new Long(2)}, 
			{"Plàstic", new Long(3)}, 
	};
	private Object[][] varietatsOlives = {
			{"Arbequina", new Boolean(true), new Long(7), "9B403D"}, 
			{"Mallorquina", new Boolean(true), new Long(8), "919C16"}, 
			{"Picual", new Boolean(true), new Long(9), "635262"}, 
			{"Experimental", new Boolean(false), new Long(10), "968B3A"}
	};

//	private Object[][] arxiusTemporal1 = {  
//			{"llauna_acer.gif", "image/gif", new Integer(50), new Integer(47), "es/caib/gestoli/logic/resources/llauna_acer.gif", new Long(11)} 
//	};
//	private Object[][] materialsTipusEnvasosTemporal1 = {
//			{"Llauna d'acer", new Long(11), new Integer(4)} 
//	};

	private HibernateTemplate hibernateTemplate;



	/** 
	 * Creacio del EJB
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
		try {
			genDao = new GenDao();
			colorDao = new ColorDao();
			materialTipusEnvasDao = new MaterialTipusEnvasDao();
			materialDipositDao = new MaterialDipositDao();
			arxiuDao = new ArxiuDao();
			varietatOlivaDao = new VarietatOlivaDao();
		} catch (Exception ex) {
			logger.error("EXCEPTION", ex);
		}
	}


	/**
	 * Només serveix per poder especificar els permisos amb xdoclet.
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
	 * Inicialització de la DB
	 * <!-- begin-xdoclet-definition --> 
	 * @ejb.interface-method view-type="both"
	 * @ejb.permission role-name="OLI_DOGESTOR"
	 * <!-- end-xdoclet-definition --> 
	 */
	public void initDB(HttpServletResponse response) {
		StringBuffer texte = new StringBuffer();
		try {
			colorDao.setHibernateTemplate(getHibernateTemplate());
			response.getOutputStream().println("<br/>Recuperam si els colors estan inserits&nbsp;.....");
			Long numeroColors = colorDao.numeroColors();
			if (numeroColors == null || numeroColors.intValue() == 0) {
				response.getOutputStream().println("<br/>Colors no han estat inserits, anam a inserir tot&nbsp;.....");
				afegirColors(response);
				afegirArxius(response);
				afegirMaterialsTipusEnvasos(response);
				afegirMaterialsDiposits(response);
				afegirVarietatsOlives(response);
				response.getOutputStream().println("<br/><br/><br/><br/>TOT CORRECTE!!!");
			} else {
				response.getOutputStream().println("<br/>Colors ja inserits, no es pot processar rés més!");
			}
		} catch (Exception ex) {
			logger.error("EXCEPTION", ex);
			try {
				response.getOutputStream().println("<br/><br/><br/><br/>" + ex.toString());
				for (int i = 0; i < 10; i++) {
					response.getOutputStream().println("<br/>&nbsp;&nbsp;&nbsp;" + ex.getStackTrace()[i]);
				}
			} catch (Exception e) {}
			
		}
	}


//	/** 
//	 * Inicialització de la DB
//	 * <!-- begin-xdoclet-definition --> 
//	 * @ejb.interface-method view-type="both"
//	 * @ejb.permission role-name="OLI_DOGESTOR"
//	 * <!-- end-xdoclet-definition --> 
//	 */
//	public void initDBTemporal1() {
//		try {
//			afegirArxiusTemporal(arxiusTemporal1);
//			afegirMaterialsTipusEnvasosTemporal(materialsTipusEnvasosTemporal1);
//		} catch (Exception ex) {
//			logger.error("EXCEPTION", ex);
//		}
//	}


	/**
	 * Afegeix colors a la DB
	 * @throws Exception
	 */
	private void afegirColors(HttpServletResponse response) throws Exception {
		response.getOutputStream().println("<br/><br/>COLORS");
		for (int i = 0; i < colors.length; i++) {
			Color color = new Color();
			response.getOutputStream().println("<br/>&nbsp;" + colors[i][0] + "&nbsp;.....");
			color.setNom((String)colors[i][0]);
			color.setValor((String)colors[i][1]);
			colorDao.setHibernateTemplate(getHibernateTemplate());
			colorDao.makePersistent(color);
			response.getOutputStream().println("&nbsp;inserit&nbsp;.....");
			// Update Id
			Integer oldId = color.getId();
			Integer newId = new Integer(i + 1);
			genDao.updateColorId(oldId, newId);
			response.getOutputStream().println("&nbsp;processat!!!");
		}
	}


	/**
	 * Afegeix arxius (imatges) a la DB
	 * @throws Exception
	 */
	private void afegirArxius(HttpServletResponse response) throws Exception {
		response.getOutputStream().println("<br/><br/>ARXIUS");
		for (int i = 0; i < arxius.length; i++) {
			Arxiu arxiu = new Arxiu();
			response.getOutputStream().println("<br/>&nbsp;" + arxius[i][0] + "&nbsp;.....");
			InputStream is = getClass().getClassLoader().getResourceAsStream((String)arxius[i][4]);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] buffer = new byte[256];
			int cuenta;
			while((cuenta=is.read(buffer))>0){ 
				baos.write(buffer,0,cuenta);
			}
			byte[] array = baos.toByteArray();

			arxiu.setNom((String)arxius[i][0]);
			arxiu.setMime((String)arxius[i][1]);
			arxiu.setTamany(new Integer(array.length));
			arxiu.setWidth((Integer)arxius[i][2]);
			arxiu.setHeight((Integer)arxius[i][3]);
			arxiu.setBinari(array);
			arxiuDao.setHibernateTemplate(getHibernateTemplate());
			arxiuDao.makePersistent(arxiu);
			response.getOutputStream().println("&nbsp;inserit&nbsp;.....");
			// Update Id
			Long oldId = arxiu.getId();
			Long newId = new Long(i + 1);
			genDao.updateArxiuId(oldId, newId);

			is.close();
			baos.close();
			response.getOutputStream().println("&nbsp;processat!!!");
		}
	}


//	/**
//	 * Afegeix arxius (imatges) a la DB
//	 * @throws Exception
//	 */
//	private void afegirArxiusTemporal(Object[][] arxiusTemporal) throws Exception {
//		for (int i = 0; i < arxiusTemporal.length; i++) {
//			Arxiu arxiu = new Arxiu(); 
//			InputStream is = getClass().getClassLoader().getResourceAsStream((String)arxiusTemporal[i][4]);
//			ByteArrayOutputStream baos = new ByteArrayOutputStream();
//			byte[] buffer = new byte[256];
//			int cuenta;
//			while((cuenta=is.read(buffer))>0){ 
//				baos.write(buffer,0,cuenta);
//			}
//			byte[] array = baos.toByteArray();
//
//			arxiu.setNom((String)arxiusTemporal[i][0]);
//			arxiu.setMime((String)arxiusTemporal[i][1]);
//			arxiu.setTamany(new Integer(array.length));
//			arxiu.setWidth((Integer)arxiusTemporal[i][2]);
//			arxiu.setHeight((Integer)arxiusTemporal[i][3]);
//			arxiu.setBinari(array);
//			arxiuDao.setHibernateTemplate(getHibernateTemplate());
//			arxiuDao.makePersistent(arxiu);
//			// Update Id
//			Long oldId = arxiu.getId();
//			Long newId = (Long)arxiusTemporal[i][5];
//			genDao.updateArxiuId(oldId, newId);
//
//			is.close();
//			baos.close();
//		}
//	}


	/**
	 * Afegeix materials de tipus de envasos a la DB
	 * @throws Exception
	 */
	private void afegirMaterialsTipusEnvasos(HttpServletResponse response) throws Exception {
		response.getOutputStream().println("<br/><br/>MATERIALS TIPUS ENVAS");
		for (int i = 0; i < materialsTipusEnvasos.length; i++) {
			MaterialTipusEnvas materialTipusEnvas = new MaterialTipusEnvas();
			response.getOutputStream().println("<br/>&nbsp;" + materialsTipusEnvasos[i][0] + ".....");
			materialTipusEnvas.setNom((String)materialsTipusEnvasos[i][0]);
			materialTipusEnvas.setIcona((Long)materialsTipusEnvasos[i][1]);
			materialTipusEnvasDao.setHibernateTemplate(getHibernateTemplate());
			materialTipusEnvasDao.makePersistent(materialTipusEnvas);
			response.getOutputStream().println("&nbsp;inserit&nbsp;.....");
			// Update Id
			Integer oldId = materialTipusEnvas.getId();
			Integer newId = new Integer(i + 1);
			genDao.updateMaterialTipusEnvasId(oldId, newId);
			response.getOutputStream().println("&nbsp;processat!!!");
		}
	}


//	/**
//	 * Afegeix materials de tipus de envasos a la DB
//	 * @throws Exception
//	 */
//	private void afegirMaterialsTipusEnvasosTemporal(Object[][] materialsTipusEnvasosTemporal) throws Exception {
//		for (int i = 0; i < materialsTipusEnvasosTemporal.length; i++) {
//			MaterialTipusEnvas materialTipusEnvas = new MaterialTipusEnvas();
//			materialTipusEnvas.setNom((String)materialsTipusEnvasosTemporal[i][0]);
//			materialTipusEnvas.setIcona((Long)materialsTipusEnvasosTemporal[i][1]);
//			materialTipusEnvasDao.setHibernateTemplate(getHibernateTemplate());
//			materialTipusEnvasDao.makePersistent(materialTipusEnvas);
//			// Update Id
//			Integer oldId = materialTipusEnvas.getId();
//			Integer newId = (Integer)materialsTipusEnvasosTemporal[i][2];
//			genDao.updateMaterialTipusEnvasId(oldId, newId);
//		}
//	}


	/**
	 * Afegeix materials de diposits a la DB
	 * @throws Exception
	 */
	private void afegirMaterialsDiposits(HttpServletResponse response) throws Exception {
		response.getOutputStream().println("<br/><br/>MATERIALS DIPOSITS");
		for (int i = 0; i < materialsDiposits.length; i++) {
			MaterialDiposit materialDiposit = new MaterialDiposit();
			response.getOutputStream().println("<br/>&nbsp;" + materialsDiposits[i][0] + ".....");
			materialDiposit.setNom((String)materialsDiposits[i][0]);
			materialDiposit.setIcona((Long)materialsDiposits[i][1]);
			materialDipositDao.setHibernateTemplate(getHibernateTemplate());
			materialDipositDao.makePersistent(materialDiposit);
			response.getOutputStream().println("&nbsp;inserit&nbsp;.....");
			// Update Id
			Integer oldId = materialDiposit.getId();
			Integer newId = new Integer(i + 1);
			genDao.updateMaterialDipositId(oldId, newId);
			response.getOutputStream().println("&nbsp;processat!!!");
		}
	}


	/**
	 * Afegeix tipus varietats d'oliveres a la DB
	 * @throws Exception
	 */
	private void afegirVarietatsOlives(HttpServletResponse response) throws Exception {
		response.getOutputStream().println("<br/><br/>VARIETATS OLIVES");
		for (int i = 0; i < varietatsOlives.length; i++) {
			VarietatOliva varietatOliva = new VarietatOliva();
			response.getOutputStream().println("<br/>&nbsp;" + varietatsOlives[i][0] + ".....");
			varietatOliva.setNom((String)varietatsOlives[i][0]);
			varietatOliva.setAutoritzada((Boolean)varietatsOlives[i][1]);
			varietatOliva.setIcona((Long)varietatsOlives[i][2]);
			varietatOliva.setColor((String)varietatsOlives[i][3]);
			varietatOlivaDao.setHibernateTemplate(getHibernateTemplate());
			varietatOlivaDao.makePersistent(varietatOliva);
			response.getOutputStream().println("&nbsp;inserit&nbsp;.....");
			// Update Id
			Integer oldId = varietatOliva.getId();
			Integer newId = new Integer(i + 1);
			genDao.updateVarietatOlivaId(oldId, newId);
			response.getOutputStream().println("&nbsp;processat!!!");
		}
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