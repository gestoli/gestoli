package es.caib.gestoli.front.spring.views;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import es.caib.gestoli.logic.interfaces.OliQualitatEjb;
import es.caib.gestoli.logic.model.Establiment;
import es.caib.gestoli.logic.model.QualitatNoConformitat;
import es.caib.gestoli.logic.model.QualitatNoConformitatAccio;

public class GenerarPdfQualitatNoConformitat extends AbstractPdfView {
	private static Logger logger = Logger.getLogger(GenerarPdfQualitatNoConformitat.class);
	private OliQualitatEjb oliQualitatEjb;
	private HibernateTemplate hibernateTemplate;
    private String establimentSessionKey;

	protected void buildPdfDocument(
        Map model,
        Document document,
        PdfWriter writer,
        HttpServletRequest request,
        HttpServletResponse response)
        throws Exception {
		
		document = null;
		writer = null;
		

    	HttpSession session = request.getSession();
		Establiment est = (Establiment)session.getAttribute(establimentSessionKey);
		
		
		//NoConformitat
		String idNoConformitat = request.getParameter("idNoConformitat");
		oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
		QualitatNoConformitat noConformitat = oliQualitatEjb.noConformitatAmbId(Long.valueOf(idNoConformitat));
		Collection<QualitatNoConformitatAccio> accions = oliQualitatEjb.noConformitatAccioCercaTotsPerNoConformitat(noConformitat.getId());
		
		// Compilar archivo
		logger.debug("2.- Leyendo el stream del jrxml");
	    InputStream is = this.getClass().getClassLoader().getResourceAsStream("es/caib/gestoli/logic/resources/qualitatNoConformitat.jrxml");
		logger.debug("3.- Compilando el report");
	    JasperReport report = JasperCompileManager.compileReport(is);

		// Data Source
		logger.debug("4.- Asociando los datos");
		JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource( getDataSourceNoConformitat(accions, request) );
		
		//Parametros
		logger.debug("5.- Cargando parametros");
		HashMap params = new HashMap();
		
		//Texte (prefix "t")
		params.put("ttitol", missatge("pdf.qualitat.noConformitat.titol", request));
		params.put("tpeu",missatge("pdf.qualitat.noConformitat.peu", request));
		
		params.put("tiniciacio",missatge("pdf.qualitat.noConformitat.iniciacio", request));
		params.put("tiniciacio2",missatge("pdf.qualitat.noConformitat.iniciacio2", request));
		params.put("ttractament",missatge("pdf.qualitat.noConformitat.tractament", request));
		params.put("ttractament2",missatge("pdf.qualitat.noConformitat.tractament2", request));
		params.put("ttancament",missatge("pdf.qualitat.noConformitat.tancament", request));
		params.put("ttancament2",missatge("pdf.qualitat.noConformitat.tancament2", request));
		
		params.put("tid",missatge("pdf.qualitat.noConformitat.id", request));
		params.put("tdataNoConformitat",missatge("pdf.qualitat.noConformitat.dataNoConformitat", request));
		params.put("tresponsableDeteccio",missatge("pdf.qualitat.noConformitat.responsableDeteccio", request));
		params.put("tdescripcio",missatge("pdf.qualitat.noConformitat.descripcio", request));
		params.put("tcausa",missatge("pdf.qualitat.noConformitat.causa", request));
		params.put("tidAccio",missatge("pdf.qualitat.noConformitat.idAccio", request));
		params.put("taccio",missatge("pdf.qualitat.noConformitat.accio", request));
		params.put("tresponsableAccio",missatge("pdf.qualitat.noConformitat.responsableAccio", request));
		params.put("tfinalitzacio",missatge("pdf.qualitat.noConformitat.dataFinalitzacio", request));
		params.put("tdataFiPrevista",missatge("pdf.qualitat.noConformitat.dataFiPrevista", request));
		params.put("tdataTancament",missatge("pdf.qualitat.noConformitat.dataTancament", request));
		
		//Dades (prefix "d")
		params.put("did", noConformitat.getId().toString());
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String dataNoConformitat = "";
		if (noConformitat.getDataNoConformitat() != null){
			dataNoConformitat = sdf.format(noConformitat.getDataNoConformitat());
		}
		params.put("ddataNoConformitat", dataNoConformitat);
		String responsableDeteccio = "";
		if (noConformitat.getResponsableDeteccio() != null){
			responsableDeteccio = noConformitat.getResponsableDeteccio().getNom() + " " + noConformitat.getResponsableDeteccio().getLlinatges();
		}
		params.put("dresponsableDeteccio", responsableDeteccio);
		params.put("ddescripcio", noConformitat.getDescripcio());
		params.put("dcausa", noConformitat.getCausa());
		String dataTancament = "";
		if (noConformitat.getDataTancament() != null){
			dataTancament = sdf.format(noConformitat.getDataTancament());
			//dataTancament = noConformitat.getDataTancament().toString();
		}
		params.put("ddataTancament", dataTancament);
		
		logger.debug("6.- Cargando los datos al report");
		JasperPrint jasperPrint = JasperFillManager.fillReport( report, params, ds );

		// Configure exporter and set parameters
		ServletOutputStream out = response.getOutputStream();
		response.setContentType("application/pdf");
		response.setHeader("Cache-Control", "store");
		response.setHeader("Pragma", "cache");            
		response.setHeader("Content-Disposition", "attachment; filename=noConformitat.pdf");
		
		logger.debug("7.- Exportando el report a pdf");
	    byte[] bytes = JasperExportManager.exportReportToPdf(jasperPrint);
	    for (int i = 0; i < bytes.length; i ++) {
	    	out.write(bytes[i]);
	    }
	    out.close();
	    out.flush();
	    
	}
		
	
	public class FilaReport implements java.io.Serializable {
		
		private String idAccio;
		private String accio;
		private String responsableAccio;
		private String dataPrevista;
		private String dataTancament;
		
		public FilaReport(String idAccio, String accio, String responsableAccio,
				String dataPrevista, String dataTancament) {

			this.idAccio = idAccio;
			this.accio = accio;
			this.responsableAccio = responsableAccio;
			this.dataPrevista = dataPrevista;
			this.dataTancament = dataTancament;
			
		}

		public String getIdAccio() {
			return idAccio;
		}

		public void setIdAccio(String idAccio) {
			this.idAccio = idAccio;
		}

		public String getAccio() {
			return accio;
		}

		public void setAccio(String accio) {
			this.accio = accio;
		}

		public String getResponsableAccio() {
			return responsableAccio;
		}

		public void setResponsableAccio(String responsableAccio) {
			this.responsableAccio = responsableAccio;
		}

		public String getDataPrevista() {
			return dataPrevista;
		}

		public void setDataPrevista(String dataPrevista) {
			this.dataPrevista = dataPrevista;
		}

		public String getDataTancament() {
			return dataTancament;
		}

		public void setDataTancament(String dataTancament) {
			this.dataTancament = dataTancament;
		}

	}
	
	
	public Collection getDataSourceNoConformitat(Collection<QualitatNoConformitatAccio> accions, HttpServletRequest request){
		List<Object[]> formats = new ArrayList<Object[]>();
		
		//Emplenam el resultat
		Collection col=new ArrayList();
		FilaReport filaReport;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		for (QualitatNoConformitatAccio accio : accions){

			String responsable = "";
			if (accio.getResponsableAccio() != null){
				responsable = accio.getResponsableAccio().getNom() + " " + accio.getResponsableAccio().getLlinatges();
			}

			String dataPrevista = "";
			if (accio.getDataFiPrevista() != null){
				dataPrevista = sdf.format(accio.getDataFiPrevista());
			}
			
			String dataTancament = "";
			if (accio.getDataTancament() != null){
				dataTancament = sdf.format(accio.getDataTancament());
			}
			
			filaReport = new FilaReport(
					accio.getCodi().toString(),
					accio.getAccio(),
					responsable,
					dataPrevista,
					dataTancament);
			
			col.add(filaReport);
				
		}
		
		return col;
		
	}

    
    /**
	 * Injecció de la dependència establimentSessionKey
	 *
	 * @param establimentSessionKey La classe a injectar.
	 */
	public void setEstablimentSessionKey(String establimentSessionKey) {
		this.establimentSessionKey = establimentSessionKey;
	}

	
	/**
	 * Inyección de la dependencia oliConsultaEjb
	 * @param oliConsultaEjb La clase a inyectar.
	 */
	public void setOliQualitatEjb(OliQualitatEjb oliQualitatEjb) {
		this.oliQualitatEjb = oliQualitatEjb;
	}
	
	public OliQualitatEjb getOliQualitatEjb() {
		return oliQualitatEjb;
	}


	private String missatge(String clave, HttpServletRequest request) {
		String valor = getApplicationContext().getMessage(clave, null,
				Idioma.getLocale(request));
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
