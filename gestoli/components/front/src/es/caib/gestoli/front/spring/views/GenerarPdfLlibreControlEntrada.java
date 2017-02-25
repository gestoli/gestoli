package es.caib.gestoli.front.spring.views;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
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
import es.caib.gestoli.logic.interfaces.OliConsultaEjb;
import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;
import es.caib.gestoli.logic.model.DescomposicioPartidaOliva;
import es.caib.gestoli.logic.model.PartidaOliva;


/**
 * GenerarPdfLlibreControlEntrada
 * @author obarnes
 */
public class GenerarPdfLlibreControlEntrada extends AbstractPdfView {
	private static Logger logger = Logger.getLogger(GenerarPdfLlibreControlEntrada.class);
	private OliConsultaEjb oliConsultaEjb;
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
		
		String fi = request.getParameter("dataInici");
		String ff = request.getParameter("dataFi");
		String idE = request.getParameter("idEst");
    	
		oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
		String nomEst = oliInfraestructuraEjb.establimentAmbId(new Long (idE)).getNom();
		
		SimpleDateFormat dateF = new SimpleDateFormat("dd/MM/yyyy");
		Date finicio = null;
		Date ffin = null;
		try {
		    finicio = dateF.parse(fi);
		    ffin = dateF.parse(ff);
		} catch (ParseException ex) {
		    finicio=Calendar.getInstance().getTime();
		    ffin=Calendar.getInstance().getTime();
		}
		
		Long idEst = new Long(idE);
	
		logger.debug("1.- Recuperando datos");
		oliConsultaEjb.setHibernateTemplate(getHibernateTemplate());
	    Iterator partidas = ((Collection) oliConsultaEjb.findPartidasEntreDiasEnEstablecimiento(finicio,ffin,idEst)).iterator();
	    
	    List<PartidaOliva> llistaPartidas = new ArrayList<PartidaOliva>();
	    
	    while(partidas.hasNext()){
	    	PartidaOliva p = (PartidaOliva)partidas.next();
	    	if (!p.getEsOlivaDeTaula())llistaPartidas.add(p);
	    }

	    
		// Compilar archivo
		logger.debug("2.- Leyendo el stream del jrxml");
	    InputStream is = this.getClass().getClassLoader().getResourceAsStream("es/caib/gestoli/logic/resources/llibreEntradaOliva.jrxml");
		logger.debug("3.- Compilando el report");
	    JasperReport report = JasperCompileManager.compileReport(is);

		// Data Source
		logger.debug("4.- Asociando los datos");
		JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource( getDataSourceEntradaOliva(llistaPartidas) );
		
		//Parametros
		logger.debug("5.- Cargando parametros");
		HashMap params = new HashMap();
		params.put("resLlibreEntradaOliva",missatge("pdf.llibres.control_entrada.titol", request));
		params.put("resData",missatge("pdf.llibres.data", request));
		params.put("resNomEst",missatge("pdf.llibres.tafona", request));
		params.put("nomEst",nomEst);
		params.put("resHeNumEntrada",missatge("pdf.llibres.control_entrada.numEntrada", request));
		params.put("resHeHoraEntrada",missatge("pdf.llibres.control_entrada.horaEntrada", request));
		params.put("resHeVarietat",missatge("pdf.llibres.control_entrada.varietat", request));
		params.put("resHeTitular",missatge("pdf.llibres.control_entrada.titular", request));
		params.put("resHeKg",missatge("pdf.llibres.control_entrada.kgs", request));
		params.put("resHeDataElaboracio",missatge("pdf.llibres.control_entrada.dataElaboracio", request));
		params.put("resPeu",missatge("pdf.llibres.control_entrada.peu", request));
		params.put("resPagina",missatge("pdf.llibres.control_entrada.pagina", request));
		params.put("resImg","es/caib/gestoli/logic/resources/do.jpg");
		
		logger.debug("6.- Cargando los datos al report");
		JasperPrint jasperPrint = JasperFillManager.fillReport( report, params, ds );

		// Configure exporter and set parameters
		ServletOutputStream out = response.getOutputStream();
		response.setContentType("application/pdf");
		response.setHeader("Cache-Control", "store");
		response.setHeader("Pragma", "cache");            
		response.setHeader("Content-Disposition", "attachment; filename=llibreControlEntrada.pdf");
		
		logger.debug("7.- Exportando el report a pdf");
	    byte[] bytes = JasperExportManager.exportReportToPdf(jasperPrint);
	    for (int i = 0; i < bytes.length; i ++) {
	    	out.write(bytes[i]);
	    }
	    out.close();
	    out.flush();

    }
    
	
	
	public class FilaReport implements java.io.Serializable {
		
		private String fecha;
		private String numEntrada;
		private String horaEntrada;
		private String varietat;
		private String titular;
		private String kg;
		private String dataElaboracio;
		
		public FilaReport(String fecha, String numEntrada, String horaEntrada,
				String varietat, String titular, String kg,
				String dataElaboracio, Boolean valid) {

			this.fecha = fecha;
			this.numEntrada = numEntrada;
			this.horaEntrada = horaEntrada;
			this.varietat = varietat;
			this.titular = titular;
			this.kg = kg;
			this.dataElaboracio = dataElaboracio;
			
			if (!valid.booleanValue()) {
				String tagI = "<s style='color: red; text-decoration: line-through;'>";
				String tagF = "</s>";
				
				this.numEntrada = tagI + this.numEntrada + tagF;
				this.horaEntrada = tagI + this.horaEntrada + tagF;
				this.varietat = tagI + this.varietat + tagF;
				this.titular = tagI + this.titular + tagF;
				this.kg = tagI + this.kg + tagF;
				this.dataElaboracio = tagI + this.dataElaboracio + tagF;
			}
			
		}
		
		
		public String getFecha() {
			return this.fecha;
		}
		public void setFecha(String fecha) {
			this.fecha = fecha;
		}
		public String getNumEntrada() {
			return this.numEntrada;
		}
		public void setNumEntrada(String numEntrada) {
			this.numEntrada = numEntrada;
		}
		public String getHoraEntrada() {
			return this.horaEntrada;
		}
		public void setHoraEntrada(String horaEntrada) {
			this.horaEntrada = horaEntrada;
		}
		public String getVarietat() {
			return this.varietat;
		}
		public void setVarietat(String varietat) {
			this.varietat = varietat;
		}
		public String getTitular() {
			return this.titular;
		}
		public void setTitular(String titular) {
			this.titular = titular;
		}
		public String getKg() {
			return this.kg;
		}
		public void setKg(String kg) {
			this.kg = kg;
		}
		public String getDataElaboracio() {
			return this.dataElaboracio;
		}
		public void setDataElaboracio(String dataElaboracio) {
			this.dataElaboracio = dataElaboracio;
		}
	
		
	}
	
	
	

	
	public Collection getDataSourceEntradaOliva(List<PartidaOliva> i){
		Collection col=new ArrayList();
		FilaReport filaReport;
		
		SimpleDateFormat dateF = new SimpleDateFormat("dd/MM/yyyy");
		DecimalFormat numberDecimalFormat = new DecimalFormat("###,###,##0.00");
		
		for(PartidaOliva par: i){
			
			Iterator iteDesPar = par.getDescomposicioPartidesOlives().iterator();
			
			List<DescomposicioPartidaOliva> llistaIteDesPar = new ArrayList<DescomposicioPartidaOliva>();
			
			while (iteDesPar.hasNext()){
				DescomposicioPartidaOliva desPar = (DescomposicioPartidaOliva)iteDesPar.next();
				llistaIteDesPar.add(desPar);
			}
			
			Collections.sort(
					llistaIteDesPar,
					new Comparator() {
						public int compare(Object o1, Object o2) {
							if (o1 == null && o2 == null) return 0;
							if (o1 == null || ((DescomposicioPartidaOliva)o1).getId() == null) return -1;
							if (o2 == null || ((DescomposicioPartidaOliva)o2).getId() == null) return 1;
							Long t1 = ((DescomposicioPartidaOliva)o1).getId();
							Long t2 = ((DescomposicioPartidaOliva)o2).getId();
							return t1.compareTo(t2);
						}
					});
			
			for(DescomposicioPartidaOliva desPar: llistaIteDesPar){
				
//				String titular="RA-"+par.getOlivicultor().getCodiDO();
//				if (!desPar.getDescomposicioPlantacio().getVarietatOliva().getAutoritzada().booleanValue()){
//					if (par.getOlivicultor().getCodiDOExperimental()!=null){
//						if (!par.getOlivicultor().getCodiDOExperimental().equals("")){
//							titular="RE-"+par.getOlivicultor().getCodiDOExperimental();
//						}
//					}
//				}
				
				String titular = "";
				if(par.getOlivicultor()!= null && par.getOlivicultor().getNom()!= null){
					titular= par.getOlivicultor().getNom();
				}				
				
				String dataEla="";
				if (par.getElaboracio()!=null){
					dataEla=par.getElaboracio().getDataFormat();
				}
				
				String var = "";
				if (desPar.getDescomposicioPlantacio().getVarietatOliva().getExperimental()){
					var = desPar.getDescomposicioPlantacio().getVarietatOliva().getNomVarietat();
				} else {
					var = desPar.getDescomposicioPlantacio().getVarietatOliva().getNom();
				}
				filaReport = new FilaReport(
						dateF.format(par.getData()),
						par.getNumeroEntrada().toString(),
						par.getHora(),
						var,
						titular,numberDecimalFormat.format(desPar.getKilos()),
						dataEla, 
						par.getValid());
				
				col.add(filaReport);
				
			}
		}
		return col;
	}
	
    
	

	/**
	 * Inyección de la dependencia oliConsultaEjb
	 * @param oliConsultaEjb La clase a inyectar.
	 */
	public void setOliConsultaEjb(OliConsultaEjb oliConsultaEjb) {
		this.oliConsultaEjb = oliConsultaEjb;
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