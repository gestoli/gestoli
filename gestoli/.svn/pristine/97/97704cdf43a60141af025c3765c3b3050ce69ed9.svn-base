package es.caib.gestoli.front.spring.views;

import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.AcroFields;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import com.lowagie.text.pdf.PdfWriter;

import es.caib.gestoli.front.util.Idioma;
import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;
import es.caib.gestoli.logic.model.Analitica;
import es.caib.gestoli.logic.model.AnaliticaValor;
import es.caib.gestoli.logic.model.DescomposicioPartidaOliva;
import es.caib.gestoli.logic.model.PartidaOliva;
import es.caib.gestoli.logic.util.Constants;

public class GenerarPdfAcompanyamentOliva extends AbstractPdfView {
	private static Logger logger = Logger.getLogger(GenerarPdfAcompanyamentOliva.class);

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
		
		String idPartida = request.getParameter("idPartida");
		
		SimpleDateFormat dateF = new SimpleDateFormat("dd/MM/yyyy");
		DecimalFormat numberDecimalFormat = new DecimalFormat("###,###,##0.00");
		
    	
		oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
		PartidaOliva po = oliInfraestructuraEjb.partidaAmbId(Long.parseLong(idPartida));
		
		String titular = po.getOlivicultor().getNom();
		String data = dateF.format(po.getData());
		String numInscrit = "RT-" + po.getOlivicultor().getCodiDOPOliva();
		
		Collection dpos = po.getDescomposicioPartidesOlives();
		
		
		for (Iterator idpo = dpos.iterator(); idpo.hasNext();) {
			DescomposicioPartidaOliva dpo = (DescomposicioPartidaOliva)idpo.next();
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			
			PdfReader reader = new PdfReader(this.getClass().getClassLoader().getResourceAsStream("es/caib/gestoli/logic/resources/documentAcompanyament.pdf"));
			
			PdfStamper stamp = new PdfStamper(reader, baos);
			AcroFields formulariPdf = stamp.getAcroFields();
			
			formulariPdf.setField("DOCNUM", po.getNumeroDocument());
			formulariPdf.setField("TITULAR", titular);
			formulariPdf.setField("DATA", data);
			formulariPdf.setField("NUMINSCRIT", numInscrit);
			formulariPdf.setField("POLIGON", dpo.getDescomposicioPlantacio().getPlantacio().getPoligon());
			formulariPdf.setField("PARCELA", dpo.getDescomposicioPlantacio().getPlantacio().getParcela());
			formulariPdf.setField("MUNICIPI", dpo.getDescomposicioPlantacio().getPlantacio().getMunicipi().getNom());
			formulariPdf.setField("KG", numberDecimalFormat.format(dpo.getKilos()));
			stamp.setFormFlattening(true);
			stamp.close();
			
			byte[] contingut = baos.toByteArray();

			// Configure exporter and set parameters
			ServletOutputStream out = response.getOutputStream();
			response.setContentType("application/pdf");
			response.setHeader("Cache-Control", "store");
			response.setHeader("Pragma", "cache");            
			response.setHeader("Content-Disposition","attachment; filename=documentAcompanyament.pdf");
			
			for (int i = 0; i < contingut.length; i ++) {
		    	out.write(contingut[i]);
		    }
		    out.close();
		    out.flush();
			
		}
		
		
	    
//		
//		
//		Long idEst = new Long(idE);
//	
//		logger.debug("1.- Iniciando proceso");
//
//		// Compilar archivo
//		logger.debug("2.- Leyendo el stream del jrxml");
//	    InputStream is = this.getClass().getClassLoader().getResourceAsStream("es/caib/gestoli/logic/resources/analiticaReport.jrxml");
//		logger.debug("3.- Compilando el report");
//	    JasperReport report = JasperCompileManager.compileReport(is);
//
//		// Data Source
//		logger.debug("4.- Asociando los datos");
//		Collection col=new ArrayList();
//		JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(col);
//		
//		
//		//Parametros
//		logger.debug("5.- Cargando parametros");
//		HashMap params = new HashMap();
//		params.put("resPeu",missatge("pdf.analitica.peu", request));
//		params.put("resImg","es/caib/gestoli/logic/resources/do.jpg");
//		params.put("resNomEst",missatge("pdf.analitica.tafona", request));
//		params.put("resDeposit",missatge("pdf.analitica.resDeposit", request));
//		params.put("resAnalisisSensorial",missatge("pdf.analitica.resAnalisisSensorial", request));
//		params.put("resNombreLab",missatge("pdf.analitica.resNombreLab", request));
//		params.put("resVariedad",missatge("pdf.analitica.resVariedad", request));
//		params.put("resFechaRecepMuestra",missatge("pdf.analitica.resFechaRecepMuestra", request));
//		params.put("resAnalitica",missatge("pdf.analitica.resAnalitica", request));
//		params.put("resFechaCataMuestra",missatge("pdf.analitica.resFechaCataMuestra", request));
//		params.put("resObservaciones",missatge("pdf.analitica.resObservaciones", request));
//		params.put("resAnalisisFisQui",missatge("pdf.analitica.resAnalisisFisQui", request));
//		params.put("resFechaInicioAnalitica",missatge("pdf.analitica.resFechaInicioAnalitica", request));
//		params.put("resFechaFinAnalitica",missatge("pdf.analitica.resFechaFinAnalitica", request));
//		params.put("resAnaliticaValida",missatge("pdf.analitica.resAnaliticaValida", request));
//		params.put("resAnalisisGeneral",missatge("pdf.analitica.resAnalisisGeneral", request));
//
//		params.put("nomEst"," "+nomEst);
//		params.put("nomDiposit"," "+nomDiposit);
//		params.put("nombreLabSens",analitica.getAnalisiSensorialNomLaboratori());
//		params.put("variedadSens",analitica.getVarietatOli().getNom());
//		params.put("fechaRecepMuestraSens",dateF.format(analitica.getAnalisiSensorialDataRecepcio()));
//		params.put("fechaCataMuestraSens",dateF.format(analitica.getAnalisiSensorialDataTast()));
//		params.put("observacionesSens",analitica.getAnalisiSensorialObservacions());
//		params.put("nombreLabFisQui",analitica.getAnalisiFisicoQuimicNomLaboratori());
//		params.put("fechaRecepMuestraFisQui",dateF.format(analitica.getAnalisiFisicoQuimicDataRecepcio()));
//		params.put("fechaInicioAnaliticaFisQui",dateF.format(analitica.getAnalisiFisicoQuimicDataInici()));
//		params.put("fechaFinAnaliticaFisQui",dateF.format(analitica.getAnalisiFisicoQuimicDataFi()));
//		params.put("observacionesGenerales",analitica.getAnalisiFisicoQuimicObservacions());
//	
//		//if (analitica.getAnalisiFisicoQuimicValid().booleanValue()){
//		if (analitica.getVarietatOli().getId().intValue() == Constants.VARIETAT_VERGE_EXTRA){
//			params.put("analiticaValida",missatge("pdf.analitica.analiticaValida",request));
//		}else{
//			params.put("analiticaValida",missatge("pdf.analitica.analiticaInvalida",request));
//		}
//		
//		
//		//SUBREPORT SENSORIAL
//		JRBeanCollectionDataSource dsSensorial = new JRBeanCollectionDataSource( getDataSourceAnalitica(analitica, Constants.PARAMETRE_TIPUS_ANALISI_SENSORIAL));
//	    
//		InputStream isSensorial = this.getClass().getClassLoader().getResourceAsStream("es/caib/gestoli/logic/resources/analiticaReport_parametres.jrxml");
//	    JasperReport subreportSensorial = JasperCompileManager.compileReport(isSensorial);
//	    
//	    params.put("SUBREPORT_SENSORIAL", subreportSensorial);
//	    params.put("DS_SENSORIAL", dsSensorial);
//	    
//	    
//	    //SUBREPORT FISICOQUIMIC
//		JRBeanCollectionDataSource dsFisicoQuimic = new JRBeanCollectionDataSource( getDataSourceAnalitica(analitica, Constants.PARAMETRE_TIPUS_ANALISI_FISICOQUIMIC));
//	    
//		InputStream isFisicoQuimic = this.getClass().getClassLoader().getResourceAsStream("es/caib/gestoli/logic/resources/analiticaReport_parametres.jrxml");
//	    JasperReport subreportFisicoQuimic = JasperCompileManager.compileReport(isFisicoQuimic);
//	    
//	    params.put("SUBREPORT_FISICOQUIMIC", subreportFisicoQuimic);
//	    params.put("DS_FISICOQUIMIC", dsFisicoQuimic);
//
//	    
//	    
//
//		logger.debug("6.- Cargando los datos al report");
//		JasperPrint jasperPrint = JasperFillManager.fillReport( report, params, ds );
//
//		// Configure exporter and set parameters
//		ServletOutputStream out = response.getOutputStream();
//		response.setContentType("application/pdf");
//		response.setHeader("Cache-Control", "store");
//		response.setHeader("Pragma", "cache");            
//		response.setHeader("Content-Disposition","attachment; filename=analitica.pdf");
//		
//		logger.debug("7.- Exportando el report a pdf");
//	    byte[] bytes = JasperExportManager.exportReportToPdf(jasperPrint);
//	    for (int i = 0; i < bytes.length; i ++) {
//	    	out.write(bytes[i]);
//	    }
//	    out.close();
//	    out.flush();
//
//	    
    }

	
	
	private Collection getDataSourceAnalitica(Analitica analitica, int tipusControl){
		Collection col=new ArrayList();
		
		try{
			FilaReportAnalitica filaReport;
			
			SimpleDateFormat dateF = new SimpleDateFormat("dd/MM/yyyy");
			DecimalFormat numberDecimalFormat = new DecimalFormat("###,###,##0.00");
			
			DecimalFormat numberIntegerFormat = new DecimalFormat("###,###,##0");
			
			Set valorsAnalitica = analitica.getAnaliticaValor();
			
			for(Iterator it=valorsAnalitica.iterator(); it.hasNext();){
				
				AnaliticaValor analiticaValor = (AnaliticaValor)it.next();
				
				if(analiticaValor.getTipusControl() == tipusControl){
					filaReport = new FilaReportAnalitica(
							analiticaValor.getNom(),
							analiticaValor.getValor());
					
					col.add(filaReport);
				} 
					
			}
			if (tipusControl == Constants.PARAMETRE_TIPUS_ANALISI_FISICOQUIMIC && analitica.getAnalisiFisicoQuimicAcidesa() != null){
				filaReport = new FilaReportAnalitica(
						"Acidesa",
						numberDecimalFormat.format(analitica.getAnalisiFisicoQuimicAcidesa()));
				
				col.add(filaReport);
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		
		return col;
	}
	
	public class FilaReportAnalitica implements java.io.Serializable {
		
		public FilaReportAnalitica(
				String nom,
				String valor) {
			super();
			this.nom = nom;
			this.valor = valor;
		}
		
		private String nom;
		private String valor;
		
		public String getNom() {
			return nom;
		}
		public void setNom(String nom) {
			this.nom = nom;
		}
		public String getValor() {
			return valor;
		}
		public void setValor(String valor) {
			this.valor = valor;
		} 

	}

	
	
	
	private String missatge(String clave, HttpServletRequest request) {
		String valor = getApplicationContext().getMessage(clave, null, Idioma.getLocale(request));
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