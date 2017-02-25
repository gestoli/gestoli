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

import es.caib.gestoli.front.util.Idioma;
import es.caib.gestoli.logic.interfaces.OliQualitatEjb;
import es.caib.gestoli.logic.model.Establiment;
import es.caib.gestoli.logic.model.QualitatPlaManteniment;

public class GenerarPdfQualitatPlaManteniment extends AbstractPdfView {
	private static Logger logger = Logger.getLogger(GenerarPdfQualitatPlaManteniment.class);
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
		Collection manteniments = oliQualitatEjb.mantenimentCercaTotsPerEstabliment(est.getId());
		
		// Compilar archivo
		logger.debug("2.- Leyendo el stream del jrxml");
	    InputStream is = this.getClass().getClassLoader().getResourceAsStream("es/caib/gestoli/logic/resources/qualitatPlaManteniment.jrxml");
		logger.debug("3.- Compilando el report");
	    JasperReport report = JasperCompileManager.compileReport(is);

		// Data Source
		logger.debug("4.- Asociando los datos");
		JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource( getDataSourcePlaManteniment(manteniments, request) );
		
		//Parametros
		logger.debug("5.- Cargando parametros");
		HashMap params = new HashMap();
		
		params.put("ttitol", missatge("pdf.qualitat.plaManteniment.titol", request));
		params.put("tpeu",missatge("pdf.qualitat.plaManteniment.peu", request));
		
		params.put("tequip",missatge("pdf.qualitat.plaManteniment.equip", request));
		params.put("tcodi",missatge("pdf.qualitat.plaManteniment.codi", request));
		params.put("tubicacio",missatge("pdf.qualitat.plaManteniment.ubicacio", request));
		params.put("tmarca",missatge("pdf.qualitat.plaManteniment.marca", request));
		params.put("tdataCompra",missatge("pdf.qualitat.plaManteniment.dataCompra", request));
		params.put("tnumSerie",missatge("pdf.qualitat.plaManteniment.numSerie", request));
		params.put("taccions",missatge("pdf.qualitat.plaManteniment.accions", request));
		params.put("tfrecuencia",missatge("pdf.qualitat.plaManteniment.frecuencia", request));
		params.put("tresponsableExec",missatge("pdf.qualitat.plaManteniment.responsableExec", request));
		params.put("tverificacio",missatge("pdf.qualitat.plaManteniment.verificacio", request));
		params.put("tresponsable",missatge("pdf.qualitat.plaManteniment.responsable", request));
		
		logger.debug("6.- Cargando los datos al report");
		JasperPrint jasperPrint = JasperFillManager.fillReport( report, params, ds );

		// Configure exporter and set parameters
		ServletOutputStream out = response.getOutputStream();
		response.setContentType("application/pdf");
		response.setHeader("Cache-Control", "store");
		response.setHeader("Pragma", "cache");            
		response.setHeader("Content-Disposition", "attachment; filename=plaManteniments.pdf");
		
		logger.debug("7.- Exportando el report a pdf");
	    byte[] bytes = JasperExportManager.exportReportToPdf(jasperPrint);
	    for (int i = 0; i < bytes.length; i ++) {
	    	out.write(bytes[i]);
	    }
	    out.close();
	    out.flush();
	    
	}
		
	
	public class FilaReport implements java.io.Serializable {
		
		private String equip;
		private String codi;
		private String ubicacio;
		private String marca;
		private String dataCompra;
		private String numSerie;
		private String accions;
		private String frecuencia;
		private String responsableExec;
		private String verifFrecuencia;
		private String verifResponsable;
		
		public FilaReport(String equip, String codi, String ubicacio, String marca, String dataCompra,
				String numSerie, String accions, String frecuencia, String responsableExec,
				String verifFrecuencia, String verifResponsable) {

			this.equip = equip;
			this.codi = codi;
			this.ubicacio = ubicacio;
			this.marca = marca;
			this.dataCompra = dataCompra;
			this.numSerie = numSerie;
			this.accions = accions;
			this.frecuencia = frecuencia;
			this.responsableExec = responsableExec;
			this.verifFrecuencia = verifFrecuencia;
			this.verifResponsable = verifResponsable;
			
		}

		public String getEquip() {
			return equip;
		}

		public void setEquip(String equip) {
			this.equip = equip;
		}

		public String getCodi() {
			return codi;
		}

		public void setCodi(String codi) {
			this.codi = codi;
		}

		public String getUbicacio() {
			return ubicacio;
		}

		public void setUbicacio(String ubicacio) {
			this.ubicacio = ubicacio;
		}

		public String getMarca() {
			return marca;
		}

		public void setMarca(String marca) {
			this.marca = marca;
		}

		public String getDataCompra() {
			return dataCompra;
		}

		public void setDataCompra(String dataCompra) {
			this.dataCompra = dataCompra;
		}

		public String getNumSerie() {
			return numSerie;
		}

		public void setNumSerie(String numSerie) {
			this.numSerie = numSerie;
		}

		public String getAccions() {
			return accions;
		}

		public void setAccions(String accions) {
			this.accions = accions;
		}

		public String getFrecuencia() {
			return frecuencia;
		}

		public void setFrecuencia(String frecuencia) {
			this.frecuencia = frecuencia;
		}

		public String getResponsableExec() {
			return responsableExec;
		}

		public void setResponsableExec(String responsableExec) {
			this.responsableExec = responsableExec;
		}

		public String getVerifFrecuencia() {
			return verifFrecuencia;
		}

		public void setVerifFrecuencia(String verifFrecuencia) {
			this.verifFrecuencia = verifFrecuencia;
		}

		public String getVerifResponsable() {
			return verifResponsable;
		}

		public void setVerifResponsable(String verifResponsable) {
			this.verifResponsable = verifResponsable;
		}

		
	}
	
	
	public Collection getDataSourcePlaManteniment(Collection<QualitatPlaManteniment> mantenimentns, HttpServletRequest request){
		List<Object[]> formats = new ArrayList<Object[]>();
		
		//Emplenam el resultat
		Collection col=new ArrayList();
		FilaReport filaReport;

		for (QualitatPlaManteniment manteniment : mantenimentns){
							
				String frecuencia = null;
				if ("dia".equals(manteniment.getFrecuencia())){
					frecuencia = missatge("qualitat.frecuencia.diari", request);
				}else if ("sem".equals(manteniment.getFrecuencia())){
					frecuencia = missatge("qualitat.frecuencia.setmanal", request);
				}else if ("qui".equals(manteniment.getFrecuencia())){
					frecuencia = missatge("qualitat.frecuencia.quinzenal", request);
				}else if ("men".equals(manteniment.getFrecuencia())){
					frecuencia = missatge("qualitat.frecuencia.mensual", request);
				}else if ("tri".equals(manteniment.getFrecuencia())){
					frecuencia = missatge("qualitat.frecuencia.trimestral", request);
				}else if ("seme".equals(manteniment.getFrecuencia())){
					frecuencia = missatge("qualitat.frecuencia.semestral", request);
				}else if ("anu".equals(manteniment.getFrecuencia())){
					frecuencia = missatge("qualitat.frecuencia.anual", request);
				}else if ("bia".equals(manteniment.getFrecuencia())){
					frecuencia = missatge("qualitat.frecuencia.bianual", request);
				}else if ("tria".equals(manteniment.getFrecuencia())){
					frecuencia = missatge("qualitat.frecuencia.trianual", request);
				}
				
				String frecuenciaVerificacio = null;
				if ("dia".equals(manteniment.getVerificacioFrecuencia())){
					frecuenciaVerificacio = missatge("qualitat.frecuencia.diari", request);
				}else if ("sem".equals(manteniment.getVerificacioFrecuencia())){
					frecuenciaVerificacio = missatge("qualitat.frecuencia.setmanal", request);
				}else if ("men".equals(manteniment.getVerificacioFrecuencia())){
					frecuenciaVerificacio = missatge("qualitat.frecuencia.mensual", request);
				}else if ("tri".equals(manteniment.getVerificacioFrecuencia())){
					frecuenciaVerificacio = missatge("qualitat.frecuencia.trimestral", request);
				}
					
				String nomEquip = null;
				String idEquip = null;
				String plantaEquip = null;
				String marcaEquip = null;
				String dataEquip = null;
				String numSerieEquip = null;
				String responsable = null;
				String responsableVerificacio = null;
				if (manteniment.getEquip() != null){
					nomEquip = manteniment.getEquip().getNom();
					if (manteniment.getEquip().getCodiUsuari() != null){
						idEquip = manteniment.getEquip().getCodiUsuari().toString();
					}
					if (manteniment.getEquip().getUbicacio() != null){
						plantaEquip = manteniment.getEquip().getUbicacio().getNom().toString();
					}
					marcaEquip = manteniment.getEquip().getMarca();
					if (manteniment.getEquip().getDataCompra() != null){
						dataEquip = manteniment.getEquip().getDataCompra().toString();
					}
					if (manteniment.getEquip().getNumSerie() != null){
						numSerieEquip = manteniment.getEquip().getNumSerie().toString();
					}
					if (manteniment.getIsResponsableIntern()){
						if (manteniment.getResponsableIntern() != null){
							responsable = manteniment.getResponsableIntern().getNom() + " " + manteniment.getResponsableIntern().getLlinatges();
						}
					} else {
						responsable = manteniment.getResponsableExtern();
					}
					if (manteniment.getVerificacioResponsable() != null){
						responsableVerificacio = manteniment.getVerificacioResponsable().getNom() + " " + manteniment.getVerificacioResponsable().getLlinatges();
					}
						
				}
				
				filaReport = new FilaReport(
						nomEquip,
						idEquip,
						plantaEquip,
						marcaEquip,
						dataEquip,
						numSerieEquip,
						manteniment.getAccions(),
						frecuencia,
						responsable,
						frecuenciaVerificacio,
						responsableVerificacio);
				
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
