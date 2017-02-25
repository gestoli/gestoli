package es.caib.gestoli.front.spring.views;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

import es.caib.gestoli.front.util.Idioma;
import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;
import es.caib.gestoli.logic.model.DescomposicioPlantacio;
import es.caib.gestoli.logic.model.Establiment;
import es.caib.gestoli.logic.model.Olivicultor;
import es.caib.gestoli.logic.model.Plantacio;

public class GenerarPdfMO09 extends AbstractPdfView {
	private static Logger logger = Logger.getLogger(GenerarPdfMO09.class);

	private OliInfraestructuraEjb oliInfraestructuraEjb;
	private HibernateTemplate hibernateTemplate;
	
	protected void buildPdfDocument(
        Map model,
        Document document,
        PdfWriter writer,
        HttpServletRequest request,
        HttpServletResponse response)
        throws Exception {

		document = null;
		writer = null;
		
		String idEstabliment = request.getParameter("idEstabliment");
    	
		oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
		Establiment establiment = oliInfraestructuraEjb.establimentAmbId(Long.parseLong(idEstabliment));
		

		SimpleDateFormat dateF = new SimpleDateFormat("dd/MM/yyyy");
		DecimalFormat numberDecimalFormat = new DecimalFormat("###,###,##0.00");

		
		logger.debug("1.- Iniciando proceso");

		// Compilar archivo
		logger.debug("2.- Leyendo el stream del jrxml");
	    InputStream is = this.getClass().getClassLoader().getResourceAsStream("es/caib/gestoli/logic/resources/MO09.jrxml");
		logger.debug("3.- Compilando el report");
	    JasperReport report = JasperCompileManager.compileReport(is);
	    

		// Data Source
		logger.debug("4.- Asociando los datos");
		Collection col=new ArrayList();
		JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(col);
		
		//Parametros
		logger.debug("5.- Cargando parametros");
		HashMap params = new HashMap();
		
		//Texte (prefix "t")
		params.put("ttitol", missatge("pdf.MO09.titol", request));
		params.put("tpeu",missatge("pdf.MO09.peu", request));
		params.put("timatgeDO","es/caib/gestoli/logic/resources/do.jpg");
		
		params.put("tdades",missatge("pdf.MO09.dades", request));
		params.put("tnom",missatge("pdf.MO09.nom", request));
		params.put("tnomRepresentant",missatge("pdf.MO09.nomRepresentant", request));
		params.put("tadreca",missatge("pdf.MO09.adreca", request));
		params.put("tcp",missatge("pdf.MO09.cp", request));
		params.put("tcif",missatge("pdf.MO09.cif", request));
		params.put("tdni",missatge("pdf.MO09.dni", request));
		params.put("tlocalitat",missatge("pdf.MO09.localitat", request));
		params.put("ttelefon",missatge("pdf.MO09.telefon", request));
		params.put("tparraf1",missatge("pdf.MO09.parraf1", request));
		params.put("tparraf2",missatge("pdf.MO09.parraf2", request));
		params.put("tparraf3",missatge("pdf.MO09.parraf3", request));
		params.put("tparraf4",missatge("pdf.MO09.parraf4", request));
		params.put("tparraf5",missatge("pdf.MO09.parraf5", request));
		params.put("tparraf6",missatge("pdf.MO09.parraf6", request));
		params.put("tparraf7",missatge("pdf.MO09.parraf7", request));
		params.put("tparraf8",missatge("pdf.MO09.parraf8", request));
		params.put("tparraf9",missatge("pdf.MO09.parraf9", request));
		params.put("tparraf10",missatge("pdf.MO09.parraf10", request));
		params.put("trequisitsIncomplerts",missatge("pdf.MO09.requisitsIncomplerts", request));
		params.put("tnotaReqIncomplerts",missatge("pdf.MO09.notaReqIncomplerts", request));
		params.put("tpresident",missatge("pdf.MO09.president", request));

		//Dades (prefix "d")
		params.put("dnom", establiment.getNom());
		params.put("dnomRepresentant", establiment.getSolicitant().getNom());
		params.put("dadreca", establiment.getDireccio());
		params.put("dcp", establiment.getCodiPostal());
		params.put("dcif", establiment.getCif());
		params.put("ddni", establiment.getSolicitant().getNif());
		params.put("dlocalitat", establiment.getPoblacio().getNom());
		params.put("dtelefon", establiment.getTelefon());
		params.put("dRB", establiment.getCodiRB() == null ? "" : establiment.getCodiRB());
		params.put("dRC", establiment.getCodiRC() == null ? "" : establiment.getCodiRC());
		params.put("dRT", establiment.getCodiRT() == null ? "" : establiment.getCodiRT());


		logger.debug("6.- Cargando los datos al report");
		JasperPrint jasperPrint = JasperFillManager.fillReport( report, params, ds );

		// Configure exporter and set parameters
		ServletOutputStream out = response.getOutputStream();
		response.setContentType("application/pdf");
		response.setHeader("Cache-Control", "store");
		response.setHeader("Pragma", "cache");            
		response.setHeader("Content-Disposition","attachment; filename=MO09.pdf");
		
		logger.debug("7.- Exportando el report a pdf");
	    byte[] bytes = JasperExportManager.exportReportToPdf(jasperPrint);
	    for (int i = 0; i < bytes.length; i ++) {
	    	out.write(bytes[i]);
	    }
	    out.close();
	    out.flush();

	    
    }

	
	private String missatge(String clave, HttpServletRequest request) {
		String valor = getApplicationContext().getMessage(clave, null,
				Idioma.getLocale(request));
		return valor;
	}
	

	public OliInfraestructuraEjb getOliInfraestructuraEjb() {
		return oliInfraestructuraEjb;
	}

	public void setOliInfraestructuraEjb(OliInfraestructuraEjb oliInfraestructuraEjb) {
		this.oliInfraestructuraEjb = oliInfraestructuraEjb;
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