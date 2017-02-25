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
import es.caib.gestoli.logic.model.Olivicultor;
import es.caib.gestoli.logic.model.Plantacio;

public class GenerarPdfMO08 extends AbstractPdfView {
	private static Logger logger = Logger.getLogger(GenerarPdfMO08.class);

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
		
		String idOlivicultor = request.getParameter("idOlivicultor");
    	
		oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
		Olivicultor olivicultor = oliInfraestructuraEjb.olivicultorAmbId(Long.parseLong(idOlivicultor));
		

		SimpleDateFormat dateF = new SimpleDateFormat("dd/MM/yyyy");
		DecimalFormat numberDecimalFormat = new DecimalFormat("###,###,##0.00");

		
		logger.debug("1.- Iniciando proceso");

		// Compilar archivo
		logger.debug("2.- Leyendo el stream del jrxml");
	    InputStream is = this.getClass().getClassLoader().getResourceAsStream("es/caib/gestoli/logic/resources/MO08.jrxml");
		logger.debug("3.- Compilando el report");
	    JasperReport report = JasperCompileManager.compileReport(is);
	    
	 // Data Source
		logger.debug("4.- Asociando los datos");
		JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource( getDataSourceMO08(olivicultor) );

//		// Data Source
//		logger.debug("4.- Asociando los datos");
//		Collection col=new ArrayList();
//		JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(col);
		
		//Parametros
		logger.debug("5.- Cargando parametros");
		HashMap params = new HashMap();
		
		//Texte (prefix "t")
		params.put("ttitol", missatge("pdf.MO08.titol", request));
		params.put("tpeu",missatge("pdf.MO08.peu", request));
		params.put("timatgeDO","es/caib/gestoli/logic/resources/do.jpg");
		
		params.put("ttitularPlantacions",missatge("pdf.MO08.titularPlantacions", request));
		params.put("tnomRepresentant",missatge("pdf.MO08.nomRepresentant", request));
		params.put("ttitularParceles",missatge("pdf.MO08.titularParceles", request));
		params.put("tdireccio",missatge("pdf.MO08.direccio", request));
		params.put("tcp",missatge("pdf.MO08.cp", request));
		params.put("tdni",missatge("pdf.MO08.dni", request));
		params.put("tpoblacio",missatge("pdf.MO08.poblacio", request));
		params.put("ttelefon",missatge("pdf.MO08.telefon", request));
		params.put("tparraf1",missatge("pdf.MO08.parraf1", request));
		params.put("tparraf2",missatge("pdf.MO08.parraf2", request));
		params.put("tparraf3",missatge("pdf.MO08.parraf3", request));
		params.put("tparraf4",missatge("pdf.MO08.parraf4", request));
		params.put("tparraf8",missatge("pdf.MO08.parraf8", request));
		params.put("tparraf5",missatge("pdf.MO08.parraf5", request));
		params.put("tparraf6",missatge("pdf.MO08.parraf6", request));
		params.put("tparraf7",missatge("pdf.MO08.parraf7", request));
		params.put("trequisitsIncomplerts",missatge("pdf.MO08.requisitsIncomplerts", request));
		params.put("tnotaReqIncomplerts",missatge("pdf.MO08.notaReqIncomplerts", request));
		params.put("tdescripcioPlantacions",missatge("pdf.MO08.descripcioPlantacions", request));
		params.put("tnomFinca",missatge("pdf.MO08.nomFinca", request));
		params.put("tmunicipi",missatge("pdf.MO08.municipi", request));
		params.put("tpoligon",missatge("pdf.MO08.poligon", request));
		params.put("tparcela",missatge("pdf.MO08.parcela", request));
		params.put("tsuperficie",missatge("pdf.MO08.superficie", request));
		params.put("tedat",missatge("pdf.MO08.edat", request));
		params.put("tsr",missatge("pdf.MO08.sr", request));
		params.put("tnumOliveres",missatge("pdf.MO08.numOliveres", request));
		params.put("ttotal",missatge("pdf.MO08.total", request));
		params.put("tvarietal",missatge("pdf.MO08.varietal", request));
		params.put("tpresident",missatge("pdf.MO08.president", request));
		params.put("tdata",missatge("pdf.MO08.data", request));
		params.put("tvigencia",missatge("pdf.MO08.vigencia", request));

		//Dades (prefix "d")
		params.put("dnomRepresentant", olivicultor.getNom());
		params.put("ddni", olivicultor.getNif());
		params.put("ddireccio", olivicultor.getDireccio());
		params.put("dpoblacio", olivicultor.getPoblacio().getNom());
		params.put("dcp", olivicultor.getCodiPostal());
		params.put("dtelefon", olivicultor.getTelefon());
		params.put("dRA", olivicultor.getCodiDO() == null ? "" : olivicultor.getCodiDO());
		params.put("dRE", olivicultor.getCodiDOExperimental() == null ? "" : olivicultor.getCodiDOExperimental());
		params.put("dRT", olivicultor.getCodiDOPOliva() == null ? "" : olivicultor.getCodiDOPOliva());


		logger.debug("6.- Cargando los datos al report");
		JasperPrint jasperPrint = JasperFillManager.fillReport( report, params, ds );

		// Configure exporter and set parameters
		ServletOutputStream out = response.getOutputStream();
		response.setContentType("application/pdf");
		response.setHeader("Cache-Control", "store");
		response.setHeader("Pragma", "cache");            
		response.setHeader("Content-Disposition","attachment; filename=MO08.pdf");
		
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
	
	
	
	private Collection getDataSourceMO08(Olivicultor olivicultor){
		Collection col=new ArrayList();
		
		try{
			FilaReportMO08 filaReport;
			
			SimpleDateFormat dateF = new SimpleDateFormat("dd/MM/yyyy");
			DecimalFormat numberDecimalFormat = new DecimalFormat("###,###,##0.00");
			
			DecimalFormat numberIntegerFormat = new DecimalFormat("###,###,##0");
			
			List<Plantacio> plantacions = oliInfraestructuraEjb.PlantacioActivasByIdOlivicultor(olivicultor.getId());		
			
			for(Plantacio plantacio: plantacions){
				
				//Secar o Regadiu
				String secaRegadiu = "";
				
				if(plantacio.getRegadiu() == null){
					secaRegadiu = "-";
				} else {
					if(plantacio.getRegadiu()){
						secaRegadiu = "R";
					} else {
						secaRegadiu = "S";
					}
				}
				
				
				Set descomposicionsPlantacio = plantacio.getDescomposicioPlantacios();
				Double superficie = new Double(0);
				Integer numOliveres = new Integer(0);
				List<String> varietats = new ArrayList<String>();
				
				for(Iterator it = descomposicionsPlantacio.iterator(); it.hasNext();){
					DescomposicioPlantacio dp = (DescomposicioPlantacio)it.next();
					if (!dp.getVarietatOliva().getOlivaTaula() && 
							((olivicultor.getCodiDO2() != null && dp.getVarietatOliva().getAutoritzada() == true) || 
							(olivicultor.getCodiDOExperimental2() != null && dp.getVarietatOliva().getAutoritzada() == false))) {
						superficie += dp.getSuperficie();
						
						numOliveres += dp.getNumeroOliveres();
						if(!varietats.contains(dp.getVarietatOliva().getNom())){
							varietats.add(dp.getVarietatOliva().getNom());
						}
					}
				}
				
				String varietatsStr = "";
				for(String s: varietats){
					varietatsStr += s + " ";
				}
				
				if (numOliveres > 0 || superficie > 0) {
					filaReport = new FilaReportMO08(
							plantacio.getFinca().getNom(),
							plantacio.getMunicipi().getNom(),
							plantacio.getPoligon(),
							plantacio.getParcela(),
							numberDecimalFormat.format(superficie),
							numberIntegerFormat.format(plantacio.getAnyPlantacio()),
							secaRegadiu,
							numberIntegerFormat.format(numOliveres),
							varietatsStr);
					
					col.add(filaReport);
				}
					
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		
		return col;
	}
	
	public class FilaReportMO08 implements java.io.Serializable {
		
		public FilaReportMO08(
				String nomFinca, 
				String municipi, 
				String poligon, 
				String parcela,
				String superficie,
				String edat,
				String sr,
				String total,
				String varietal) {
			super();
			this.nomFinca = nomFinca;
			this.municipi = municipi;
			this.poligon = poligon;
			this.parcela = parcela;
			this.superficie = superficie;
			this.edat = edat;
			this.sr = sr;
			this.total = total;
			this.varietal = varietal;
			
			/*String tagI = "<s style='color: red; text-decoration: line-through;'>";
			String tagF = "</s>";
			this.nomFinca = tagI + this.nomFinca + tagF;
			this.municipi = tagI + this.municipi + tagF;
			this.poligon = tagI + this.poligon + tagF;
			this.parcela = tagI + this.parcela + tagF;
			this.superficie = tagI + this.superficie + tagF;
			this.edat = tagI + this.edat + tagF;
			this.sr = tagI + this.sr + tagF;
			this.total = tagI + this.total + tagF;
			this.varietal = tagI + this.varietal + tagF;*/
		}
		private String nomFinca;
		private String municipi; 
		private String poligon;
		private String parcela;
		private String superficie;
		private String edat;
		private String sr;
		private String total;
		private String varietal;
		public String getNomFinca() {
			return nomFinca;
		}
		public void setNomFinca(String nomFinca) {
			this.nomFinca = nomFinca;
		}
		public String getMunicipi() {
			return municipi;
		}
		public void setMunicipi(String municipi) {
			this.municipi = municipi;
		}
		public String getPoligon() {
			return poligon;
		}
		public void setPoligon(String poligon) {
			this.poligon = poligon;
		}
		public String getParcela() {
			return parcela;
		}
		public void setParcela(String parcela) {
			this.parcela = parcela;
		}
		public String getSuperficie() {
			return superficie;
		}
		public void setSuperficie(String superficie) {
			this.superficie = superficie;
		}
		public String getEdat() {
			return edat;
		}
		public void setEdat(String edat) {
			this.edat = edat;
		}
		public String getSr() {
			return sr;
		}
		public void setSr(String sr) {
			this.sr = sr;
		}
		public String getTotal() {
			return total;
		}
		public void setTotal(String total) {
			this.total = total;
		}
		public String getVarietal() {
			return varietal;
		}
		public void setVarietal(String varietal) {
			this.varietal = varietal;
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