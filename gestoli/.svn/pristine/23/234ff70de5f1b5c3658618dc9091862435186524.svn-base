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
import es.caib.gestoli.logic.model.QualitatFormacioEvaluacio;
import es.caib.gestoli.logic.model.QualitatPlaFormacio;

public class GenerarPdfQualitatPlaFormacio extends AbstractPdfView {
	private static Logger logger = Logger.getLogger(GenerarPdfQualitatPlaFormacio.class);
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
		Collection formacions = oliQualitatEjb.formacioCercaTotsPerEstabliment(est.getId());
		
		// Compilar archivo
		logger.debug("2.- Leyendo el stream del jrxml");
	    InputStream is = this.getClass().getClassLoader().getResourceAsStream("es/caib/gestoli/logic/resources/qualitatPlaFormacio.jrxml");
		logger.debug("3.- Compilando el report");
	    JasperReport report = JasperCompileManager.compileReport(is);

		// Data Source
		logger.debug("4.- Asociando los datos");
		JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource( getDataSourcePlaFormacio(formacions, request) );
		
		//Parametros
		logger.debug("5.- Cargando parametros");
		HashMap params = new HashMap();
		
		params.put("ttitol", missatge("pdf.qualitat.plaFormacio.titol", request));
		params.put("tpeu",missatge("pdf.qualitat.plaFormacio.peu", request));
		
		params.put("ttreballador",missatge("pdf.qualitat.plaFormacio.treballador", request));
		params.put("tfrecuenciaFormacio",missatge("pdf.qualitat.plaFormacio.frecuenciaFormacio", request));
		params.put("tdataDarreraFormacio",missatge("pdf.qualitat.plaFormacio.dataDarreraFormacio", request));
		params.put("taccioFormativa",missatge("pdf.qualitat.plaFormacio.accioFormativa", request));
		params.put("tdataRealitzacio",missatge("pdf.qualitat.plaFormacio.dataRealitzacio", request));
		params.put("tresponsable",missatge("pdf.qualitat.plaFormacio.responsable", request));
		params.put("tduracio",missatge("pdf.qualitat.plaFormacio.duracio", request));
		params.put("tevaluacio",missatge("pdf.qualitat.plaFormacio.evaluacio", request));
		
		logger.debug("6.- Cargando los datos al report");
		JasperPrint jasperPrint = JasperFillManager.fillReport( report, params, ds );

		// Configure exporter and set parameters
		ServletOutputStream out = response.getOutputStream();
		response.setContentType("application/pdf");
		response.setHeader("Cache-Control", "store");
		response.setHeader("Pragma", "cache");            
		response.setHeader("Content-Disposition", "attachment; filename=plaFormacions.pdf");
		
		logger.debug("7.- Exportando el report a pdf");
	    byte[] bytes = JasperExportManager.exportReportToPdf(jasperPrint);
	    for (int i = 0; i < bytes.length; i ++) {
	    	out.write(bytes[i]);
	    }
	    out.close();
	    out.flush();
	    
	}
		
	
	public class FilaReport implements java.io.Serializable {
		
		private String treballador;
		private String frecFormacio;
		private String fecLastFormacio;
		private String dataFormacio;
		private String respFormacio;
		private String duracioFormacio;
		private String evaluacio;
		
		public FilaReport(String treballador, String frecFormacio, String fecLastFormacio,
				String dataFormacio, String respFormacio, String duracioFormacio, String evaluacio) {

			this.treballador = treballador;
			this.frecFormacio = frecFormacio;
			this.fecLastFormacio = fecLastFormacio;
			this.dataFormacio = dataFormacio;
			this.respFormacio = respFormacio;
			this.duracioFormacio = duracioFormacio;
			this.evaluacio = evaluacio;
			
		}

		public String getTreballador() {
			return treballador;
		}

		public void setTreballador(String treballador) {
			this.treballador = treballador;
		}

		public String getFrecFormacio() {
			return frecFormacio;
		}

		public void setFrecFormacio(String frecFormacio) {
			this.frecFormacio = frecFormacio;
		}

		public String getFecLastFormacio() {
			return fecLastFormacio;
		}

		public void setFecLastFormacio(String fecLastFormacio) {
			this.fecLastFormacio = fecLastFormacio;
		}

		public String getDataFormacio() {
			return dataFormacio;
		}

		public void setDataFormacio(String dataFormacio) {
			this.dataFormacio = dataFormacio;
		}

		public String getRespFormacio() {
			return respFormacio;
		}

		public void setRespFormacio(String respFormacio) {
			this.respFormacio = respFormacio;
		}

		public String getDuracioFormacio() {
			return duracioFormacio;
		}

		public void setDuracioFormacio(String duracioFormacio) {
			this.duracioFormacio = duracioFormacio;
		}

		public String getEvaluacio() {
			return evaluacio;
		}

		public void setEvaluacio(String evaluacio) {
			this.evaluacio = evaluacio;
		}

		
	}
	
	
	public Collection getDataSourcePlaFormacio(Collection<QualitatPlaFormacio> formacions, HttpServletRequest request){
		List<Object[]> formats = new ArrayList<Object[]>();
		
		//Emplenam el resultat
		Collection col=new ArrayList();
		FilaReport filaReport;

		for (QualitatPlaFormacio formacio : formacions){
			for(QualitatFormacioEvaluacio personal : formacio.getPersonalAssistent()){
				
				String duracioTipus = "";
				if ("h".equals(formacio.getDuracioTipus())){
					duracioTipus = missatge("qualitat.plaFormacio.duracioTipus.hores", request);
				}else if ("d".equals(formacio.getDuracioTipus())){
					duracioTipus = missatge("qualitat.plaFormacio.duracioTipus.dies", request);
				}else if ("s".equals(formacio.getDuracioTipus())){
					duracioTipus = missatge("qualitat.plaFormacio.duracioTipus.setmanes", request);
				}
				
				String frecuencia = "";
				if ((formacio.getFrecuencia() != null) &&	(!("".equals(formacio.getFrecuencia())))){
					frecuencia = missatge("qualitat.frecuencia." + formacio.getFrecuencia(), request);
				}
				
				String treballador = "";
				if (personal.getTreballador() != null){
					treballador = personal.getTreballador().getNom() + " " + personal.getTreballador().getLlinatges();
				}
				
				String dataPrevista = "";
				if (formacio.getDataPrevista() != null){
					dataPrevista = formacio.getDataPrevista().toString();
				}
				
				filaReport = new FilaReport(
						treballador,
						frecuencia,
						null,
						dataPrevista,
						(formacio.getIsResponsableIntern())?formacio.getResponsableIntern().getNom() + " " + formacio.getResponsableIntern().getLlinatges():formacio.getResponsableExtern(),
						formacio.getDuracio() + " " + duracioTipus,
						null);
				
				col.add(filaReport);
				
				/*
				Object[] obj = new Object[2];
				obj[0] = personal.getNom() + " " + personal.getLlinatges(); 
				obj[1] = null;
				obj[2] = null;
				obj[3] = formacio.getDataPrevista();
				if (formacio.getIsResponsableIntern())
					obj[4] = formacio.getResponsableIntern().getNom() + " " + formacio.getResponsableIntern().getLlinatges();
				else
					obj[4] = formacio.getResponsableExtern();
				obj[5] = formacio.getDuracio() + " " + formacio.getDuracioTipus();
				obj[6] = null;
				
				formats.add(obj);*/
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
