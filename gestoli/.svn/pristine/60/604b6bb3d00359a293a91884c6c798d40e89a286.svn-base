package es.caib.gestoli.front.spring.views;

import java.io.InputStream;
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

import es.caib.gestoli.front.spring.views.GenerarPdfQualitatPlaManteniment.FilaReport;
import es.caib.gestoli.front.util.Idioma;
import es.caib.gestoli.logic.interfaces.OliQualitatEjb;
import es.caib.gestoli.logic.model.Establiment;
import es.caib.gestoli.logic.model.QualitatAPPCCEtapa;
import es.caib.gestoli.logic.model.QualitatAPPCCEtapaPerill;
import es.caib.gestoli.logic.model.QualitatPlaManteniment;

public class GenerarPdfQualitatAPPCCEtapes extends AbstractPdfView {
	private static Logger logger = Logger.getLogger(GenerarPdfQualitatAPPCCEtapes.class);
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
		
		//Long id = Long.valueOf(request.getParameter("id"));

    	HttpSession session = request.getSession();
		Establiment est = (Establiment)session.getAttribute(establimentSessionKey);
		
		//FORMACIO
		oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
		Collection etapes = oliQualitatEjb.aPPCCEtapaCercaTotsPerEstabliment(est.getId());
		
		// Compilar archivo
		logger.debug("2.- Leyendo el stream del jrxml");
	    InputStream is = this.getClass().getClassLoader().getResourceAsStream("es/caib/gestoli/logic/resources/qualitatAPPCCEtapes.jrxml");
		logger.debug("3.- Compilando el report");
	    JasperReport report = JasperCompileManager.compileReport(is);

		// Data Source
		logger.debug("4.- Asociando los datos");
		JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource( getDataSourceAPPCCEtapes(etapes, request) );
		
		//Parametros
		logger.debug("5.- Cargando parametros");
		HashMap params = new HashMap();
		
		params.put("ttitol", missatge("pdf.qualitat.aPPCC.etapa.titol", request));
		params.put("tpeu",missatge("pdf.qualitat.aPPCC.etapa.peu", request));
		
		params.put("tetapa",missatge("pdf.qualitat.aPPCC.etapa.etapa", request));
		params.put("ttipus",missatge("pdf.qualitat.aPPCC.etapa.tipus", request));
		params.put("tdetall",missatge("pdf.qualitat.aPPCC.etapa.detall", request));
		params.put("tcausa",missatge("pdf.qualitat.aPPCC.etapa.causa", request));
		params.put("tprevencio",missatge("pdf.qualitat.aPPCC.etapa.prevencio", request));
		params.put("tcontrol",missatge("pdf.qualitat.aPPCC.etapa.control", request));
		params.put("tprobabilitat",missatge("pdf.qualitat.aPPCC.etapa.probabilitat", request));
		params.put("tgravetat",missatge("pdf.qualitat.aPPCC.etapa.gravetat", request));
		params.put("tsignificatiu",missatge("pdf.qualitat.aPPCC.etapa.significatiu", request));
		
		logger.debug("6.- Cargando los datos al report");
		JasperPrint jasperPrint = JasperFillManager.fillReport( report, params, ds );

		// Configure exporter and set parameters
		ServletOutputStream out = response.getOutputStream();
		response.setContentType("application/pdf");
		response.setHeader("Cache-Control", "store");
		response.setHeader("Pragma", "cache");            
		response.setHeader("Content-Disposition", "attachment; filename=analisisPeligros.pdf");
		
		logger.debug("7.- Exportando el report a pdf");
	    byte[] bytes = JasperExportManager.exportReportToPdf(jasperPrint);
	    for (int i = 0; i < bytes.length; i ++) {
	    	out.write(bytes[i]);
	    }
	    out.close();
	    out.flush();
	    
	}
	
	
	public class FilaReport implements java.io.Serializable {
		
		private String etapa;
		private String tipus;
		private String detall;
		private String causa;
		private String prevencio;
		private String probabilitat;
		private String gravetat;
		private String significatiu;
		private String last;
		
		public FilaReport(String etapa,	String tipus, String detall, String causa,
				String prevencio, String probabilitat, String gravetat,	String significatiu,
				String last) {
			this.etapa = etapa;
			this.tipus = tipus;
			this.detall = detall;
			this.causa = causa;
			this.prevencio = prevencio;
			this.probabilitat = probabilitat;
			this.gravetat = gravetat;
			this.significatiu = significatiu;
			this.last = last;
		}

		public String getEtapa() {
			return etapa;
		}

		public void setEtapa(String etapa) {
			this.etapa = etapa;
		}

		public String getTipus() {
			return tipus;
		}

		public void setTipus(String tipus) {
			this.tipus = tipus;
		}

		public String getDetall() {
			return detall;
		}

		public void setDetall(String detall) {
			this.detall = detall;
		}

		public String getCausa() {
			return causa;
		}

		public void setCausa(String causa) {
			this.causa = causa;
		}

		public String getPrevencio() {
			return prevencio;
		}

		public void setPrevencio(String prevencio) {
			this.prevencio = prevencio;
		}

		public String getProbabilitat() {
			return probabilitat;
		}

		public void setProbabilitat(String probabilitat) {
			this.probabilitat = probabilitat;
		}

		public String getGravetat() {
			return gravetat;
		}

		public void setGravetat(String gravetat) {
			this.gravetat = gravetat;
		}

		public String getSignificatiu() {
			return significatiu;
		}

		public void setSignificatiu(String significatiu) {
			this.significatiu = significatiu;
		}

		public String getLast() {
			return last;
		}

		public void setLast(String last) {
			this.last = last;
		}

		
	}
	
	

	public Collection getDataSourceAPPCCEtapes(Collection<QualitatAPPCCEtapa> etapes, HttpServletRequest request){
		List<Object[]> formats = new ArrayList<Object[]>();
		
		//Emplenam el resultat
		Collection col=new ArrayList();
		FilaReport filaReport;

		for (QualitatAPPCCEtapa etapa : etapes){
				
				String nomEtapa = etapa.getNom();
				int pos = 0;
				String last="No";
				for (QualitatAPPCCEtapaPerill perill : etapa.getPerills()){
					pos++;
					if (pos == etapa.getPerills().size()){
						last="Si";
					}
					
					String significatiu = "No";
					if (perill.getProbabilitat() + perill.getGravetat() >= 5){
						significatiu = "Si";
					}
					
					filaReport = new FilaReport(
							nomEtapa,
							missatge("qualitat.appcc.etapa.perill.tipus."+perill.getTipus(), request),
							perill.getDetall(),
							perill.getCausa(),
							missatge("qualitat.appcc.etapa.perill.prevencio."+perill.getPrevencio(), request),
							missatge("qualitat.appcc.etapa.perill.probabilitat."+perill.getProbabilitat(), request),
							missatge("qualitat.appcc.etapa.perill.gravetat."+perill.getGravetat(), request),
							significatiu,
							last);
					
					
					col.add(filaReport);
					nomEtapa = null;
					
				}
				
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
