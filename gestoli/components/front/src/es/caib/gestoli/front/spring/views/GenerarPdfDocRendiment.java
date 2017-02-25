package es.caib.gestoli.front.spring.views;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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

import es.caib.gestoli.front.util.Idioma;
import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;
import es.caib.gestoli.logic.model.DescomposicioPartidaOliva;
import es.caib.gestoli.logic.model.Elaboracio;
import es.caib.gestoli.logic.model.Establiment;
import es.caib.gestoli.logic.model.Olivicultor;
import es.caib.gestoli.logic.model.PartidaOliva;

public class GenerarPdfDocRendiment extends AbstractPdfView {
	private static Logger logger = Logger.getLogger(GenerarPdfDocRendiment.class);

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
		String idElaboracio = request.getParameter("idElaboracio");
    	
		oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
		Establiment establiment = oliInfraestructuraEjb.establimentAmbId(new Long (idEstabliment));
		Elaboracio elaboracioOli = oliInfraestructuraEjb.elaboracioAmbId(new Long(idElaboracio), true);
		String entradesDiposit = oliInfraestructuraEjb.findResumEntradaDiposiOliByElaboracio(new Long(idElaboracio));
		String sortidesOlivicultor = oliInfraestructuraEjb.findResumsortidaOlivicultorByElaboracio(new Long(idElaboracio));
		

		logger.debug("1.- Iniciando proceso");

		// Compilar archivo
		logger.debug("2.- Leyendo el stream del jrxml");
	    InputStream is = this.getClass().getClassLoader().getResourceAsStream("es/caib/gestoli/logic/resources/docRendimentReport.jrxml");
		logger.debug("3.- Compilando el report");
	    JasperReport report = JasperCompileManager.compileReport(is);

		// Data Source
		logger.debug("4.- Asociando los datos");
		Collection col=new ArrayList();
		JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource( getDataSourceDocRendiment(elaboracioOli) );
		
		//Parametros
		logger.debug("5.- Cargando parametros");
		HashMap params = new HashMap();
		
		//Texte (prefix "t")
		params.put("ttitol", missatge("pdf.docRendiment.titol", request));
		params.put("tpeu",missatge("pdf.docRendiment.peu", request));
		params.put("timatgeDO","es/caib/gestoli/logic/resources/do.jpg");
		
		params.put("ttafona",missatge("pdf.docRendiment.tafona", request));
		params.put("tcif",missatge("pdf.docRendiment.cif", request));
		params.put("tadreca",missatge("pdf.docRendiment.adreca", request));
		params.put("tautonumero",missatge("pdf.docRendiment.autonumero", request));
		
		params.put("tinfoElaboracio", missatge("pdf.docRendiment.infoElaboracio", request));
		params.put("tdata",missatge("pdf.docRendiment.data", request));
		params.put("tolivicultor",missatge("pdf.docRendiment.olivicultor", request));
		params.put("tkgoliva",missatge("pdf.docRendiment.kgoliva", request));
		params.put("tvarietats",missatge("pdf.docRendiment.varietats", request));
		params.put("tlitresresult",missatge("pdf.docRendiment.litresresult", request));
		params.put("tacidesa",missatge("pdf.docRendiment.acidesa", request));
		params.put("trendiment",missatge("pdf.docRendiment.rendiment", request));
		
		params.put("tlitresolivicultor",missatge("pdf.docRendiment.litresolivicultor", request));
		params.put("tlitresdiposit",missatge("pdf.docRendiment.litresdiposit", request));
		
		params.put("tinfoEntrades", missatge("pdf.docRendiment.infoEntrades", request));
		params.put("tentradesDiposit", missatge("pdf.docRendiment.entrades.diposit", request));
		params.put("tsortidesOlivicultors", missatge("pdf.docRendiment.sortides.olivicultor", request));
		
		//Dades (prefix "d")
		params.put("dtafona", establiment.getNom());
		params.put("dcif", establiment.getCif());
		params.put("dadreca", establiment.getDireccio());
		params.put("dautonumero", elaboracioOli.getNumeroElaboracio().toString());
		
		params.put("dentradesDiposit", entradesDiposit);
		params.put("dsortidesOlivicultors", sortidesOlivicultor);
		
		

		logger.debug("6.- Cargando los datos al report");
		JasperPrint jasperPrint = JasperFillManager.fillReport( report, params, ds );

		// Configure exporter and set parameters
		ServletOutputStream out = response.getOutputStream();
		response.setContentType("application/pdf");
		response.setHeader("Cache-Control", "store");
		response.setHeader("Pragma", "cache");            
		response.setHeader("Content-Disposition","attachment; filename="+missatge("pdf.docRendiment.filename", request)+".pdf");
		
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


	
	private Collection getDataSourceDocRendiment(Elaboracio elaboracioOli){
		Collection col=new ArrayList();
		
		SimpleDateFormat dateF = new SimpleDateFormat("dd/MM/yyyy");
		DecimalFormat numberDecimalFormat = new DecimalFormat("###,###,##0.00");

		
		Double rendiment = (elaboracioOli.getLitres() * 100) / elaboracioOli.getTotalKilos();
		
		Collection partidesOlives = elaboracioOli.getPartidaOlivas();
		
		List<Object[]> files = new ArrayList<Object[]>();
		
		for(Iterator it=partidesOlives.iterator();it.hasNext();){
			PartidaOliva po = (PartidaOliva)it.next();
			
			//Olivicultor
			Olivicultor olivicultor = po.getOlivicultor();
			
			//Descomposicions
			Collection descOlives = po.getDescomposicioPartidesOlives();
			
			Double kilosOliva = 0.0;
			String varietats = "";
			Double litres = 0.0;
			
			
			for(Iterator it2=descOlives.iterator(); it2.hasNext();){
				DescomposicioPartidaOliva descOliva = (DescomposicioPartidaOliva)it2.next();
				
				if(descOliva.getPartidaOliva().getOlivicultor().getId().equals(olivicultor.getId())){
					kilosOliva += descOliva.getKilos();
					varietats = descOliva.getPartidaOliva().getVarietatsQuilos();
				}
			}
			
			litres = kilosOliva * rendiment / 100;
			
			Object[] fila  = new Object[5];
			fila[0] = olivicultor;
			fila[1] = kilosOliva;
			fila[2] = varietats;
			fila[3] = litres;
			fila[4] = po.getId();
			
			files.add(fila);
			
		}
		
		
		try{
			FilaReportDocRendiment filaReport;
			
			for(Object[] o: files){
				
				Olivicultor oliv = (Olivicultor)o[0];
				Double kilosOliva = (Double)o[1];
				String varietats = (String)o[2];
				Double litres = (Double)o[3];
				Long partidaOliva = (Long)o[4];
				
				filaReport = new FilaReportDocRendiment(
							String.valueOf(partidaOliva),
							String.valueOf(oliv.getId()),
							elaboracioOli.getDataFormat(),
							oliv.getNom(),
							numberDecimalFormat.format(kilosOliva),
							varietats,
							numberDecimalFormat.format(litres),
							numberDecimalFormat.format(elaboracioOli.getAcidesa()),
							numberDecimalFormat.format(rendiment),
							""
							);
					
					col.add(filaReport);
			}

		} catch(Exception e){
			e.printStackTrace();
		}
		
		return col;
	}
	
	public class FilaReportDocRendiment implements java.io.Serializable {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public FilaReportDocRendiment(
				String idPartidaOliva,
				String idOlivicultor,
				String data,
				String nomOlivicultor,
				String kgOliva,
				String varietats,
				String litres,
				String acidesa,
				String rendiment,
				String litresRetirats) {
			super();
			this.idPartidaOliva = idPartidaOliva;
			this.idOlivicultor = idOlivicultor;
			this.data = data;
			this.nomOlivicultor = nomOlivicultor;
			this.kgOliva = kgOliva;
			this.varietats = varietats;
			this.litres = litres;
			this.acidesa = acidesa;
			this.rendiment = rendiment;
			this.litresRetirats = litresRetirats;
			
		}
		private String idPartidaOliva;
		private String idOlivicultor;
		private String data;
		private String nomOlivicultor;
		private String kgOliva;
		private String varietats;
		private String litres;
		private String acidesa;
		private String rendiment;
		private String litresRetirats;
		
		public String getIdPartidaOliva() {
			return idPartidaOliva;
		}
		public void setIdPartidaOliva(String idPartidaOliva) {
			this.idPartidaOliva = idPartidaOliva;
		}
		public String getIdOlivicultor() {
			return idOlivicultor;
		}
		public void setIdOlivicultor(String idOlivicultor) {
			this.idOlivicultor = idOlivicultor;
		}
		public String getData() {
			return data;
		}
		public void setData(String data) {
			this.data = data;
		}
		public String getNomOlivicultor() {
			return nomOlivicultor;
		}
		public void setNomOlivicultor(String nomOlivicultor) {
			this.nomOlivicultor = nomOlivicultor;
		}
		public String getKgOliva() {
			return kgOliva;
		}
		public void setKgOliva(String kgOliva) {
			this.kgOliva = kgOliva;
		}
		public String getVarietats() {
			return varietats;
		}
		public void setVarietats(String varietats) {
			this.varietats = varietats;
		}
		public String getLitres() {
			return litres;
		}
		public void setLitres(String litres) {
			this.litres = litres;
		}
		public String getAcidesa() {
			return acidesa;
		}
		public void setAcidesa(String acidesa) {
			this.acidesa = acidesa;
		}
		public String getRendiment() {
			return rendiment;
		}
		public void setRendiment(String rendiment) {
			this.rendiment = rendiment;
		}
		public String getLitresRetirats() {
			return litresRetirats;
		}
		public void setLitresRetirats(String litresRetirats) {
			this.litresRetirats = litresRetirats;
		}
		
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