package es.caib.gestoli.front.spring.views;

import java.io.InputStream;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfWriter;

import es.caib.gestoli.front.spring.views.GenerarPdfLlibreExistenciesOli.FilaReport;
import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;
import es.caib.gestoli.logic.model.DescomposicioPlantacio;
import es.caib.gestoli.logic.model.Factura;
import es.caib.gestoli.logic.model.Finca;
import es.caib.gestoli.logic.model.HistoricLlistatFactura;
import es.caib.gestoli.logic.model.Olivicultor;
import es.caib.gestoli.logic.model.Plantacio;
import es.caib.gestoli.logic.model.Taxa;
import es.caib.gestoli.logic.model.VarietatOliva;

public class GenerarPdfEtiquetes extends AbstractPdfView {
	private static Logger logger = Logger.getLogger(GenerarPdfEtiquetes.class);
	private OliInfraestructuraEjb oliInfraestructuraEjb;
	private HibernateTemplate hibernateTemplate;
	
    protected void buildPdfDocument(
        Map model,
        Document document,
        PdfWriter writer,
        HttpServletRequest request,
        HttpServletResponse response)
    throws Exception {

	    Collection olivicultors = (Collection)model.get("llistat");

	    document = null;
		writer = null;
		
		// Compilar archivo
		logger.debug("1.- Leyendo el stream del jrxml");
	    InputStream is = this.getClass().getClassLoader().getResourceAsStream("es/caib/gestoli/logic/resources/etiquetesOlivicultors.jrxml");
		logger.debug("2.- Compilando el report");
	    JasperReport report = JasperCompileManager.compileReport(is);

		// Data Source
		logger.debug("3.- Asociando los datos");
		JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(getDataSourceEtiquetes(olivicultors));
		
		//Parametros
		logger.debug("4.- Cargando parametros");
		HashMap params = new HashMap();
//		params.put("pLogo", "es/caib/gestoli/logic/resources/do.jpg");
		
		logger.debug("5.- Cargando los datos al report");
		JasperPrint jasperPrint = JasperFillManager.fillReport( report, params, ds );

		// Configure exporter and set parameters
		ServletOutputStream out = response.getOutputStream();
		response.setContentType("application/pdf");
		response.setHeader("Cache-Control", "store");
		response.setHeader("Pragma", "cache");            
		response.setHeader("Content-Disposition","attachment; filename=etiquetes.pdf");

		logger.debug("6.- Exportando el report a pdf");
	    byte[] bytes = JasperExportManager.exportReportToPdf(jasperPrint);
	    for (int i = 0; i < bytes.length; i ++) {
	    	out.write(bytes[i]);
	    }
	    out.close();
	    out.flush();
    }

    
    public class FilaReport implements java.io.Serializable {

		public FilaReport(
				String inNom1, String inAdresa1, String inLocalitat1, String inCP1,
				String inNom2, String inAdresa2, String inLocalitat2, String inCP2,
				String inNom3, String inAdresa3, String inLocalitat3, String inCP3) {
			super();
			this.inNom1 = inNom1;
			this.inAdresa1 = inAdresa1;
			this.inLocalitat1 = inLocalitat1;
			this.inCP1 = inCP1;
			this.inNom2 = inNom2;
			this.inAdresa2 = inAdresa2;
			this.inLocalitat2 = inLocalitat2;
			this.inCP2 = inCP2;
			this.inNom3 = inNom3;
			this.inAdresa3 = inAdresa3;
			this.inLocalitat3 = inLocalitat3;
			this.inCP3 = inCP3;
			
		}
		
		private String inNom1;
		private String inAdresa1;
		private String inLocalitat1;
		private String inCP1;
		private String inNom2;
		private String inAdresa2;
		private String inLocalitat2;
		private String inCP2;
		private String inNom3;
		private String inAdresa3;
		private String inLocalitat3;
		private String inCP3;
		public String getInNom1() {
			return inNom1;
		}
		public void setInNom1(String inNom1) {
			this.inNom1 = inNom1;
		}
		public String getInAdresa1() {
			return inAdresa1;
		}
		public void setInAdresa1(String inAdresa1) {
			this.inAdresa1 = inAdresa1;
		}
		public String getInLocalitat1() {
			return inLocalitat1;
		}
		public void setInLocalitat1(String inLocalitat1) {
			this.inLocalitat1 = inLocalitat1;
		}
		public String getInCP1() {
			return inCP1;
		}
		public void setInCP1(String inCP1) {
			this.inCP1 = inCP1;
		}
		public String getInNom2() {
			return inNom2;
		}
		public void setInNom2(String inNom2) {
			this.inNom2 = inNom2;
		}
		public String getInAdresa2() {
			return inAdresa2;
		}
		public void setInAdresa2(String inAdresa2) {
			this.inAdresa2 = inAdresa2;
		}
		public String getInLocalitat2() {
			return inLocalitat2;
		}
		public void setInLocalitat2(String inLocalitat2) {
			this.inLocalitat2 = inLocalitat2;
		}
		public String getInCP2() {
			return inCP2;
		}
		public void setInCP2(String inCP2) {
			this.inCP2 = inCP2;
		}
		public String getInNom3() {
			return inNom3;
		}
		public void setInNom3(String inNom3) {
			this.inNom3 = inNom3;
		}
		public String getInAdresa3() {
			return inAdresa3;
		}
		public void setInAdresa3(String inAdresa3) {
			this.inAdresa3 = inAdresa3;
		}
		public String getInLocalitat3() {
			return inLocalitat3;
		}
		public void setInLocalitat3(String inLocalitat3) {
			this.inLocalitat3 = inLocalitat3;
		}
		public String getInCP3() {
			return inCP3;
		}
		public void setInCP3(String inCP3) {
			this.inCP3 = inCP3;
		}
	}

	/**
     * getDataSourceEtiquetes
     * @param i
     * @return
     */
    public Collection getDataSourceEtiquetes(Collection olivicultors) throws Exception {
		
    	FilaReport filaReport;
		ArrayList files = new ArrayList();
		
	    // Recorremos todos los olivicultores
	    Iterator iter = olivicultors.iterator();
		while(iter.hasNext()) {
			String inNom1 = "";
			String inAdresa1 = "";
			String inLocalitat1 = "";
			String inCP1 = "";
			String inNom2 = "";
			String inAdresa2 = "";
			String inLocalitat2 = "";
			String inCP2 = "";
			String inNom3 = "";
			String inAdresa3 = "";
			String inLocalitat3 = "";
			String inCP3 = "";
			
			Olivicultor olivicultor = (Olivicultor)iter.next();
			inNom1 = olivicultor.getNom();
			inAdresa1 = olivicultor.getDireccio();
			inLocalitat1 = olivicultor.getPoblacio().getNom();
			inCP1 = olivicultor.getCodiPostal();
			
			if (iter.hasNext()){
				olivicultor = (Olivicultor)iter.next();
				inNom2 = olivicultor.getNom();
				inAdresa2 = olivicultor.getDireccio();
				inLocalitat2 = olivicultor.getPoblacio().getNom();
				inCP2 = olivicultor.getCodiPostal();
			}
			if (iter.hasNext()){
				olivicultor = (Olivicultor)iter.next();
				inNom3 = olivicultor.getNom();
				inAdresa3 = olivicultor.getDireccio();
				inLocalitat3 = olivicultor.getPoblacio().getNom();
				inCP3 = olivicultor.getCodiPostal();
			}

			filaReport = new FilaReport(inNom1, 
										inAdresa1, 
										inLocalitat1, 
										inCP1, 
										inNom2, 
										inAdresa2, 
										inLocalitat2, 
										inCP2, 
										inNom3, 
										inAdresa3, 
										inLocalitat3, 
										inCP3);
			files.add(filaReport);
		}
		
		return files;
	}
	

    /**
     * Inyección de la dependencia oliInfraestructuraEjb
     * @param oliInfraestructuraEjb La clase a inyectar.
     */
    public void setOliInfraestructuraEjb(OliInfraestructuraEjb oliInfraestructuraEjb) {
        this.oliInfraestructuraEjb = oliInfraestructuraEjb;
    }

	private String missatge(String clave) {
    	String valor = getApplicationContext().getMessage(clave, null, new Locale("ca"));
    	return valor;
    }

	/**
	 * set the hibernate template.
	 * @param hibernateTemplate the hibernate spring template.
	 * @spring.property ref="hibernateTemplate"
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