package es.caib.gestoli.front.spring.views;

import java.io.InputStream;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import es.caib.gestoli.logic.model.QualitatDescripcioPersonal;
import es.caib.gestoli.logic.model.QualitatFormacioEvaluacio;
import es.caib.gestoli.logic.model.QualitatFormacioEvaluacio;
import es.caib.gestoli.logic.model.QualitatPlaFormacio;

public class GenerarPdfQualitatFormacioEvaluacio extends AbstractPdfView {
	private static Logger logger = Logger.getLogger(GenerarPdfQualitatFormacioEvaluacio.class);
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
		
		String idFormacio = request.getParameter("idFormacio");
		
		//FORMACIO
		oliQualitatEjb.setHibernateTemplate(getHibernateTemplate());
		//Collection evaluacions = oliQualitatEjb.evaluacioCercaTotsPerFormacio(Long.valueOf(idFormacio));
		QualitatPlaFormacio formacio = oliQualitatEjb.formacioAmbId(Long.valueOf(idFormacio));
		
		
		// Compilar archivo
		logger.debug("2.- Leyendo el stream del jrxml");
	    InputStream is = this.getClass().getClassLoader().getResourceAsStream("es/caib/gestoli/logic/resources/qualitatFormacioEvaluacio.jrxml");
		logger.debug("3.- Compilando el report");
	    JasperReport report = JasperCompileManager.compileReport(is);

		// Data Source
		logger.debug("4.- Asociando los datos");
		JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource( getDataSourceFormacioEvaluacio(formacio, request) );
		
		//Parametros
		logger.debug("5.- Cargando parametros");
		HashMap params = new HashMap();
		
		params.put("ttitol", missatge("pdf.qualitat.formacioEvaluacio.titol", request));
		params.put("tpeu",missatge("pdf.qualitat.formacioEvaluacio.peu", request));
		
		params.put("ttreballador",missatge("pdf.qualitat.formacioEvaluacio.treballador", request));
		params.put("teficaciaFormacio",missatge("pdf.qualitat.formacioEvaluacio.eficaciaFormacio", request));
		params.put("tactivitatSupervisio",missatge("pdf.qualitat.formacioEvaluacio.activitatSupervisio", request));
		params.put("tdataSupervisio",missatge("pdf.qualitat.formacioEvaluacio.dataSupervisio", request));
		params.put("tresponsableSupervisio",missatge("pdf.qualitat.formacioEvaluacio.responsableSupervisio", request));
		params.put("tnoConformitat",missatge("pdf.qualitat.formacioEvaluacio.noConformitat", request));
		
		logger.debug("6.- Cargando los datos al report");
		JasperPrint jasperPrint = JasperFillManager.fillReport( report, params, ds );

		// Configure exporter and set parameters
		ServletOutputStream out = response.getOutputStream();
		response.setContentType("application/pdf");
		response.setHeader("Cache-Control", "store");
		response.setHeader("Pragma", "cache");            
		response.setHeader("Content-Disposition", "attachment; filename=EvaluacionsFormacio_"+idFormacio+".pdf");
		
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
		private String eficaciaFormacio;
		private String activitatSupervisio;
		private String dataSupervisio;
		private String respSupervisio;
		private String noConformitat;
		
		public FilaReport(String treballador, String eficaciaFormacio, String activitatSupervisio,
				String dataSupervisio, String respSupervisio, String noConformitat) {

			this.treballador = treballador;
			this.eficaciaFormacio = eficaciaFormacio;
			this.activitatSupervisio = activitatSupervisio;
			this.dataSupervisio = dataSupervisio;
			this.respSupervisio = respSupervisio;
			this.noConformitat = noConformitat;
			
		}

		public String getTreballador() {
			return treballador;
		}

		public void setTreballador(String treballador) {
			this.treballador = treballador;
		}

		public String getEficaciaFormacio() {
			return eficaciaFormacio;
		}

		public void setEficaciaFormacio(String eficaciaFormacio) {
			this.eficaciaFormacio = eficaciaFormacio;
		}

		public String getActivitatSupervisio() {
			return activitatSupervisio;
		}

		public void setActivitatSupervisio(String activitatSupervisio) {
			this.activitatSupervisio = activitatSupervisio;
		}

		public String getDataSupervisio() {
			return dataSupervisio;
		}

		public void setDataSupervisio(String dataSupervisio) {
			this.dataSupervisio = dataSupervisio;
		}

		public String getRespSupervisio() {
			return respSupervisio;
		}

		public void setRespSupervisio(String respSupervisio) {
			this.respSupervisio = respSupervisio;
		}

		public String getNoConformitat() {
			return noConformitat;
		}

		public void setNoConformitat(String noConformitat) {
			this.noConformitat = noConformitat;
		}

		
	}
	
	
	public Collection getDataSourceFormacioEvaluacio(QualitatPlaFormacio formacio, HttpServletRequest request){
		List<Object[]> formats = new ArrayList<Object[]>();
		
		//Emplenam el resultat
		Collection col=new ArrayList();
		FilaReport filaReport;

		String supervisor = null;
		if (formacio.getSupervisorFormacio() != null){
			supervisor = formacio.getSupervisorFormacio().getNom() + " " + formacio.getSupervisorFormacio().getLlinatges();
		}
		
		Set<QualitatFormacioEvaluacio> personal = new HashSet<QualitatFormacioEvaluacio>();
		if (request.getParameter("idEvaluacio")!=null){
			try {
				personal.add(oliQualitatEjb.evaluacioAmbId(Long.valueOf(request.getParameter("idEvaluacio"))));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			personal = formacio.getPersonalAssistent();
		}
		
		for (QualitatFormacioEvaluacio evaluacio : personal){
			String efectiva;
			if (evaluacio.getSatisfactori()!=null && evaluacio.getSatisfactori()==true)
				efectiva = "Si";
			else
				efectiva = "No";
			
			String noConformitat = null;
			if (evaluacio.getNoConformitats().size() > 0){
				noConformitat = evaluacio.getNoConformitats().iterator().next().getDescripcio();
			}
			String treballador = null;
			if (evaluacio.getTreballador() != null){
				treballador = evaluacio.getTreballador().getNom() + " " + evaluacio.getTreballador().getLlinatges();
			}
			
			String dataSupervisio = null;
			if (formacio.getDataSupervisio() != null){
				dataSupervisio = formacio.getDataSupervisio().toString();
			}
			filaReport = new FilaReport(
					treballador,
					efectiva,
					formacio.getActivitatSupervisio(),
					dataSupervisio,
					supervisor,
					noConformitat);
			
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
