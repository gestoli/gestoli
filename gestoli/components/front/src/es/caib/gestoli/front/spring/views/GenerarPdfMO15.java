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

public class GenerarPdfMO15 extends AbstractPdfView {
	private static Logger logger = Logger.getLogger(GenerarPdfMO15.class);

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
		Establiment establiment = null;
		
		if(idEstabliment != null){
			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
			establiment = oliInfraestructuraEjb.establimentAmbId(Long.parseLong(idEstabliment));
		}
		
		String idOlivicultor = request.getParameter("idOlivicultor");
		Olivicultor olivicultor = null;
		
		if(idOlivicultor != null){
			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
			olivicultor = oliInfraestructuraEjb.olivicultorAmbId(Long.parseLong(idOlivicultor));
		}
		

		SimpleDateFormat dateF = new SimpleDateFormat("dd/MM/yyyy");
		DecimalFormat numberDecimalFormat = new DecimalFormat("###,###,##0.00");

		
		logger.debug("1.- Iniciando proceso");

		// Compilar archivo
		logger.debug("2.- Leyendo el stream del jrxml");
	    InputStream is = this.getClass().getClassLoader().getResourceAsStream("es/caib/gestoli/logic/resources/MO15.jrxml");
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
		params.put("ttitol", missatge("pdf.MO15.titol", request));
		params.put("tpeu",missatge("pdf.MO15.peu", request));
		params.put("timatgeDO","es/caib/gestoli/logic/resources/do.jpg");
		
		params.put("tdades",missatge("pdf.MO15.dades", request));
		params.put("tnom",missatge("pdf.MO15.nom", request));
		params.put("tnomRepresentant",missatge("pdf.MO15.nomRepresentant", request));
		params.put("tadreca",missatge("pdf.MO15.adreca", request));
		params.put("tcp",missatge("pdf.MO15.cp", request));
		params.put("tcif",missatge("pdf.MO15.cif", request));
		params.put("tdni",missatge("pdf.MO15.dni", request));
		params.put("tlocalitat",missatge("pdf.MO15.localitat", request));
		params.put("ttelefon",missatge("pdf.MO15.telefon", request));
		params.put("tparraf1",missatge("pdf.MO15.parraf1", request));
		params.put("tparraf2",missatge("pdf.MO15.parraf2", request));
		params.put("tparraf3",missatge("pdf.MO15.parraf3", request));
		params.put("tparraf4",missatge("pdf.MO15.parraf4", request));
		params.put("tparraf5",missatge("pdf.MO15.parraf5", request));
		params.put("tparraf6",missatge("pdf.MO15.parraf6", request));
		params.put("tparraf7",missatge("pdf.MO15.parraf7", request));
		params.put("tparraf8",missatge("pdf.MO15.parraf8", request));
		params.put("tparraf9",missatge("pdf.MO15.parraf9", request));
		params.put("tmembres",missatge("pdf.MO15.membres", request));
		params.put("tpresident",missatge("pdf.MO15.president", request));
		params.put("tdata",missatge("pdf.MO15.data", request));

		
		//Dades (prefix "d")
		if(establiment != null){
			params.put("dnom", establiment.getNom());
			params.put("dnomRepresentant", establiment.getSolicitant().getNom());
			params.put("dadreca", establiment.getDireccio());
			params.put("dcp", establiment.getCodiPostal());
			params.put("dcif", establiment.getCif());
			params.put("ddni", establiment.getSolicitant().getNif());
			params.put("dlocalitat", establiment.getPoblacio().getNom());
			params.put("dtelefon", establiment.getTelefon());
		} else if(olivicultor != null) {
			params.put("dnom", olivicultor.getNom());
			params.put("dnomRepresentant", "");
			params.put("dadreca", olivicultor.getDireccio());
			params.put("dcp", olivicultor.getCodiPostal());
			params.put("dcif", olivicultor.getNif());
			params.put("ddni", "");
			params.put("dlocalitat", olivicultor.getPoblacio().getNom());
			params.put("dtelefon", olivicultor.getTelefon());
		}


		logger.debug("6.- Cargando los datos al report");
		JasperPrint jasperPrint = JasperFillManager.fillReport( report, params, ds );

		// Configure exporter and set parameters
		ServletOutputStream out = response.getOutputStream();
		response.setContentType("application/pdf");
		response.setHeader("Cache-Control", "store");
		response.setHeader("Pragma", "cache");            
		response.setHeader("Content-Disposition","attachment; filename=MO15.pdf");
		
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