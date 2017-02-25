package es.caib.gestoli.front.spring.views;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
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
import es.caib.gestoli.logic.model.EntradaLot;
import es.caib.gestoli.logic.model.SortidaLot;

public class GenerarPdfLlibreEmbotellat extends AbstractPdfView {
	private static Logger logger = Logger.getLogger(GenerarPdfLlibreEmbotellat.class);
	private OliInfraestructuraEjb oliInfraestructuraEjb;
	private HibernateTemplate hibernateTemplate;
	private OliConsultaEjb oliConsultaEjb;
	
	
	private String entrada;
	private String tancamentLlibres;
	private String venta;
	private String promocio;
	private String accioVenta;
	private String canviZona;
	

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
		String mesura = request.getParameter("mesura");
    	
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
	
		logger.debug("1.- Iniciando proceso");

		// Compilar archivo
		logger.debug("2.- Leyendo el stream del jrxml");
	    InputStream is = this.getClass().getClassLoader().getResourceAsStream("es/caib/gestoli/logic/resources/llibreEmbotellatOli.jrxml");
		logger.debug("3.- Compilando el report");
	    JasperReport report = JasperCompileManager.compileReport(is);

		// Data Source
		logger.debug("4.- Asociando los datos");
		JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource( getDataSourceEmbotellatOli(finicio,ffin,idEst,mesura.equalsIgnoreCase("l"), request, entrada) );
		
		//Parametros
		logger.debug("5.- Cargando parametros");
		HashMap params = new HashMap();
		params.put("resHeAcidesa",missatge("pdf.llibres.embotellat.resHeAcidesa", request));
		params.put("resData",missatge("pdf.llibres.data", request));
		params.put("resNomEst",missatge("pdf.llibres.tafona", request));
		params.put("nomEst"," "+nomEst);
		params.put("resHeLitres",missatge("pdf.llibres.embotellat.resHeLitres", request));
		params.put("resHeDiposit",missatge("pdf.llibres.embotellat.resHeDiposit", request));
		params.put("resHeData",missatge("pdf.llibres.embotellat.resHeData", request));
		params.put("resPeu",missatge("pdf.llibres.embotellat.peu", request));
		params.put("resPagina",missatge("pdf.llibres.embotellat.pagina", request));
		params.put("resImg","es/caib/gestoli/logic/resources/do.jpg");
		params.put("resHeDesti",missatge("pdf.llibres.embotellat.resHeDesti", request));
		params.put("resHeEmbotellat",missatge("pdf.llibres.embotellat.resHeEmbotellat", request));
		params.put("resHeSortides",missatge("pdf.llibres.embotellat.resHeSortides", request));
		params.put("resLlibreEmbotellatOli",missatge("pdf.llibres.embotellat.resLlibreEmbotellatOli", request));
		params.put("resHeEnvasos",missatge("pdf.llibres.embotellat.resHeEnvasos", request));
		params.put("resHeSortida",missatge("pdf.llibres.embotellat.resHeSortida", request));
		params.put("resHeNumEnvasos",missatge("pdf.llibres.embotellat.resHeNumEnvasos", request));
		params.put("resHeVolUnitari",missatge("pdf.llibres.embotellat.resHeVolUnitari", request));
		params.put("resHeLot",missatge("pdf.llibres.embotellat.resHeLot", request));
		params.put("resHeDocument",missatge("pdf.llibres.embotellat.resHeDocument", request));
		params.put("resHeNumContraetiquetes",missatge("pdf.llibres.embotellat.resHeNumContraetiquetes", request));
		params.put("resHeIncidencias",missatge("pdf.llibres.embotellat.resHeIncidencias", request));

		if(mesura.equalsIgnoreCase("kg")){
			params.put("resHeLitres",missatge("pdf.llibres.embotellat.resHeQuilos", request));
		}else{
			params.put("resHeLitres",missatge("pdf.llibres.embotellat.resHeLitres", request));
		}
		
		logger.debug("6.- Cargando los datos al report");
		JasperPrint jasperPrint = JasperFillManager.fillReport( report, params, ds );

		// Configure exporter and set parameters
		ServletOutputStream out = response.getOutputStream();
		response.setContentType("application/pdf");
		response.setHeader("Cache-Control", "store");
		response.setHeader("Pragma", "cache");            
		response.setHeader("Content-Disposition","attachment; filename=llibreControlEmbotellat.pdf");
		
		logger.debug("7.- Exportando el report a pdf");
	    byte[] bytes = JasperExportManager.exportReportToPdf(jasperPrint);
	    for (int i = 0; i < bytes.length; i ++) {
	    	out.write(bytes[i]);
	    }
	    out.close();
	    out.flush();

	    
    }
    
	
	
	public class FilaReport implements java.io.Serializable {

		public FilaReport(Boolean pageChange, String emData, String emAcidesa,
				String emDiposit, String emNumEm, String emVolUnitari,
				String emLitres, String emLot, String emNumContraetiq, String outData,
				String outNumEm, String outVolUnitari, String outLitres,
				String outLot, String outDocument, String outDesti,
				String incidencias, Boolean entValid, Boolean sorValid, Boolean etiValid) {
			super();
			this.pageChange = pageChange;
			this.emData = emData;
			this.emAcidesa = emAcidesa;
			this.emDiposit = emDiposit;
			this.emNumEm = emNumEm;
			this.emVolUnitari = emVolUnitari;
			this.emLitres = emLitres;
			this.emLot = emLot;
			this.emNumContraetiq = emNumContraetiq;
			this.outData = outData;
			this.outNumEm = outNumEm;
			this.outVolUnitari = outVolUnitari;
			this.outLitres = outLitres;
			this.outLot = outLot;
			this.outDocument = outDocument;
			this.outDesti = outDesti;
			this.incidencias = incidencias;
			
			String tagI = "<s style='color: red; text-decoration: line-through;'>";
			String tagF = "</s>";
			
			if (entValid != null && !entValid.booleanValue()) {
				this.emData = tagI + this.emData + tagF;
				this.emAcidesa = tagI + this.emAcidesa + tagF;
				this.emDiposit = tagI + this.emDiposit + tagF;
				this.emNumEm = tagI + this.emNumEm + tagF;
				this.emVolUnitari = tagI + this.emVolUnitari + tagF;
				this.emLitres = tagI + this.emLitres + tagF;
				this.emLot = tagI + this.emLot + tagF;
				this.emNumContraetiq = tagI + this.emNumContraetiq + tagF;
			} else if (entValid != null && entValid.booleanValue() && etiValid != null && !etiValid.booleanValue()) {
				this.emNumContraetiq = tagI + this.emNumContraetiq + tagF;
			}
			
			if (sorValid != null && !sorValid.booleanValue()) {
				this.outData = tagI + this.outData + tagF;
				this.outNumEm = tagI + this.outNumEm + tagF;
				this.outVolUnitari = tagI + this.outVolUnitari + tagF;
				this.outLitres = tagI + this.outLitres + tagF;
				this.outLot = tagI + this.outLot + tagF;
				this.outDocument = tagI + this.outDocument + tagF;
				this.outDesti = tagI + this.outDesti + tagF;
			}
		}
		
		private Boolean pageChange;
		private String emData;
		private String emAcidesa;
		private String emDiposit;
		private String emNumEm;
		private String emVolUnitari;
		private String emLitres;
		private String emLot;
		private String emNumContraetiq;
		private String outData;
		private String outNumEm;
		private String outVolUnitari;
		private String outLitres;
		private String outLot;
		private String outDocument;
		private String outDesti;
		private String incidencias;
		
		public Boolean getPageChange() {
			return pageChange;
		}
		public void setPageChange(Boolean pageChange) {
			this.pageChange = pageChange;
		}
		public String getEmData() {
			return emData;
		}
		public void setEmData(String emData) {
			this.emData = emData;
		}
		public String getEmAcidesa() {
			return emAcidesa;
		}
		public void setEmAcidesa(String emAcidesa) {
			this.emAcidesa = emAcidesa;
		}
		public String getEmDiposit() {
			return emDiposit;
		}
		public void setEmDiposit(String emDiposit) {
			this.emDiposit = emDiposit;
		}
		public String getEmNumEm() {
			return emNumEm;
		}
		public void setEmNumEm(String emNumEm) {
			this.emNumEm = emNumEm;
		}
		public String getEmVolUnitari() {
			return emVolUnitari;
		}
		public void setEmVolUnitari(String emVolUnitari) {
			this.emVolUnitari = emVolUnitari;
		}
		public String getEmLitres() {
			return emLitres;
		}
		public void setEmLitres(String emLitres) {
			this.emLitres = emLitres;
		}
		public String getEmNumContraetiq() {
			return emNumContraetiq;
		}
		public void setEmNumContraetiq(String emNumContraetiq) {
			this.emNumContraetiq = emNumContraetiq;
		}
		public String getOutData() {
			return outData;
		}
		public void setOutData(String outData) {
			this.outData = outData;
		}
		public String getOutNumEm() {
			return outNumEm;
		}
		public void setOutNumEm(String outNumEm) {
			this.outNumEm = outNumEm;
		}
		public String getOutVolUnitari() {
			return outVolUnitari;
		}
		public void setOutVolUnitari(String outVolUnitari) {
			this.outVolUnitari = outVolUnitari;
		}
		public String getOutLitres() {
			return outLitres;
		}
		public void setOutLitres(String outLitres) {
			this.outLitres = outLitres;
		}
		public String getOutLot() {
			return outLot;
		}
		public void setOutLot(String outLot) {
			this.outLot = outLot;
		}
		public String getOutDocument() {
			return outDocument;
		}
		public void setOutDocument(String outDocument) {
			this.outDocument = outDocument;
		}
		public String getOutDesti() {
			return outDesti;
		}
		public void setOutDesti(String outDesti) {
			this.outDesti = outDesti;
		}
		public String getIncidencias() {
			return incidencias;
		}
		public void setIncidencias(String incidencias) {
			this.incidencias = incidencias;
		}
		public String getEmLot() {
			return emLot;
		}
		public void setEmLot(String emLot) {
			this.emLot = emLot;
		}
		


	}


	
	public Collection getDataSourceEmbotellatOli(Date finicio,Date ffin,Long idEst, boolean mesuraLitros, HttpServletRequest request,String entrada) throws Exception{
		Collection col=new ArrayList();
		FilaReport filaReport;
		
		SimpleDateFormat dateF = new SimpleDateFormat("dd/MM/yyyy");
		DecimalFormat numberDecimalFormat = new DecimalFormat("###,###,##0.00");
		
		oliConsultaEjb.setHibernateTemplate(getHibernateTemplate());
		ArrayList lotsEntradaArray = new ArrayList(oliConsultaEjb.getEntradesLotesEntreFechasAndEstablecimiento(finicio,ffin,idEst, entrada));
 
		oliConsultaEjb.setHibernateTemplate(getHibernateTemplate());
		ArrayList lotsSortidaArray = new ArrayList(oliConsultaEjb.getSortidesLotesEntreFechasAndEstablecimiento(finicio,ffin,idEst));

		ArrayList filaUnica;
		if (lotsEntradaArray.size()>lotsSortidaArray.size()){
			filaUnica = new ArrayList(lotsEntradaArray.size());
		}else{
			filaUnica = new ArrayList(lotsSortidaArray.size());
		}
		
		int indA, indB;
		SortidaLot sorA, sorB;
		for (indA=0;indA<lotsSortidaArray.size()-1; indA++){
			sorA=(SortidaLot)lotsSortidaArray.get(indA);
			for (indB=indA+1;indB<lotsSortidaArray.size(); indB++){
				sorB=(SortidaLot)lotsSortidaArray.get(indB);
				Date fA,fB;
				if (sorA.getAccioSortida().equals(accioVenta)){
					fA=sorA.getVendaData();
				}else{
					fA=sorA.getCanviZonaData();
				}
				if (sorB.getAccioSortida().equals(accioVenta)){
					fB=sorB.getVendaData();
				}else{
					fB=sorB.getCanviZonaData();
				}
				if(fA.after(fB)){
					lotsSortidaArray.set(indA, sorB);
					lotsSortidaArray.set(indB, sorA);
					sorA=sorB;
				}
			}
		}

		int elemento=0;	    
	    while (lotsEntradaArray.size()>elemento && lotsSortidaArray.size()>elemento){
	    	
	    	EntradaLot entLot = (EntradaLot)lotsEntradaArray.get(elemento);
	    	SortidaLot sorLot = (SortidaLot)lotsSortidaArray.get(elemento);
	    	
	    	Long id = entLot.getId();
	    	Boolean valid = entLot.getValid();

	    	String outData;
	    	String sorDesti;
	    	if (sorLot.getAccioSortida().equals(accioVenta)){
	    		outData=dateF.format(sorLot.getVendaData());
	    		sorDesti=sorLot.getVendaDestinatari();
	    		if (sorDesti.equals(venta)){
	    			sorDesti = missatge("pdf.llibres.embotellat.venta", request);
	    		}else if (sorDesti.equals(promocio)){
	    			sorDesti = missatge("pdf.llibres.embotellat.promocio", request);
	    		}
	    	}else{
	    		outData=dateF.format(sorLot.getCanviZonaData());
	    		sorDesti=sorLot.getCanviZonaObservacions();
	    	}
	    	
	    	String entLotProcedencia="";
	    	boolean dev = false;
	    	if (entLot.getDipositProcedencia().equals(entrada)){
	    		entLotProcedencia = missatge("pdf.llibres.embotellat.entrada", request);
	    	}else if(entLot.getDipositProcedencia().equals(tancamentLlibres)){
	    		entLotProcedencia = missatge("pdf.llibres.embotellat.tancamentLlibres", request);
	    	}else if(entLot.getDipositProcedencia().equals("Devolució de botelles") ||
	    			 entLot.getDipositProcedencia().equals("Devolución de botellas")){
	    		entLotProcedencia = missatge("pdf.llibres.embotellat.devolucio", request);
	    		dev = true;
	    	}else{
	    		oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
	    		entLotProcedencia=oliInfraestructuraEjb.dipositAmbId(new Long(entLot.getDipositProcedencia())).getCodiAssignat();
	    	}
	    	
	    	Double volumUni = entLot.getLot().getEtiquetatge().getTipusEnvas().getVolum();
	    	
	    	
	    	String entNumEm = "";
			String entLitros="";
			try{
				if (dev){
					entNumEm = numberDecimalFormat.format(entLot.getBotelles());
					if (mesuraLitros){
						entLitros=numberDecimalFormat.format(entLot.getBotelles() * volumUni.doubleValue());
					}else{
						entLitros=numberDecimalFormat.format((double)0.916 * entLot.getBotelles() * volumUni.doubleValue());
					}
				} else {
					entNumEm = numberDecimalFormat.format(entLot.getLot().getLitresEnvassats().doubleValue() / volumUni.doubleValue());
					if (mesuraLitros){
						entLitros=numberDecimalFormat.format(entLot.getLot().getLitresEnvassats());
					}else{
						entLitros=numberDecimalFormat.format((double)0.916 * entLot.getLot().getLitresEnvassats().doubleValue());
					}
				} 
			}catch (Exception e) {
			}
			
			String sorLitros="";
			//double litrosPorBotella = sorLot.getLot().getLitresEnvassats().doubleValue()/sorLot.getLot().getNumeroBotellesInicials().doubleValue();
			double litrosPorBotella = sorLot.getLot().getEtiquetatge().getTipusEnvas().getVolum().doubleValue();
			
			try{
				if (mesuraLitros){
					// sorLitros=numberDecimalFormat.format(sorLot.getLot().getLitresEnvassats());
					sorLitros = numberDecimalFormat.format(litrosPorBotella * sorLot.getVendaNumeroBotelles().doubleValue());
				}else{
					// sorLitros=numberDecimalFormat.format((double)0.916 * sorLot.getLot().getLitresEnvassats().doubleValue());
					sorLitros=numberDecimalFormat.format((double)0.916 * litrosPorBotella * sorLot.getVendaNumeroBotelles().doubleValue());
				}
			}catch (Exception e) {
			}
	    	
	    	String numContraetiq="";
	    	if (entLot.getNumerosContraetiquetes()!=null){
	    		numContraetiq=entLot.getNumerosContraetiquetes();
	    	}
	    	if (entLot.getLot().getNumeroLot() != null && !entLot.getLot().getNumeroLot().equals("")){
	    		numContraetiq +=(numContraetiq.equals("")? "" : " - ") + "Num. Lot: " + entLot.getLot().getNumeroLot();
	    	}
	    	
	    	String tipoDocumento="";
	    	if (sorLot.getVendaTipusDocument()!=null){
	    		tipoDocumento=sorLot.getVendaTipusDocument()+" - "+sorLot.getVendaNumeroDocument();
	    	}
	    	
	    	String vendaNumeroBotelles="";
	    	if (sorLot.getVendaNumeroBotelles()!=null){
	    		vendaNumeroBotelles = sorLot.getVendaNumeroBotelles().toString();
	    	}
			
	    	/* TODO: COMPROVAR DARRER PARAMETRE I FER ALGO TIPO entLot.getLot().numeroetiquetes > 0 */
//			filaReport = new FilaReport(
//					new Boolean (true),
//					dateF.format(entLot.getData()),
//					numberDecimalFormat.format(entLot.getAcidesa()),
//					entLotProcedencia,
//					entLot.getLot().getNumeroBotellesInicials().toString(),
//					numberDecimalFormat.format(entLot.getLot().getLitresEnvassats().doubleValue()/entLot.getLot().getNumeroBotellesActuals().doubleValue()),
//					entLitros,
//					entLot.getLot().getCodiAssignat(),
//					numContraetiq,
//					outData,
//					vendaNumeroBotelles,
//					numberDecimalFormat.format(litrosPorBotella),
//					sorLitros,
//					sorLot.getLot().getCodiAssignat(),
//					tipoDocumento,
//					sorDesti,
//					"",
//					entLot.getValid(),
//					sorLot.getValid(),
//					new Boolean(!(entLot.getNumerosContraetiquetes() != null && (entLot.getLot().getNumeroEtiquetaInicial() == null && entLot.getLot().getNumeroEtiquetaFinal() == null))));

			filaReport = new FilaReport(
				new Boolean (true),
				dateF.format(entLot.getData()),
				numberDecimalFormat.format(entLot.getAcidesa()),
				entLotProcedencia,
				//entLot.getLot().getNumeroBotellesInicials().toString(),
				entNumEm,
				//numberDecimalFormat.format(entLot.getLot().getLitresEnvassats().doubleValue()/entLot.getLot().getNumeroBotellesActuals().doubleValue()),
				numberDecimalFormat.format(volumUni),
				entLitros,
				entLot.getLot().getCodiAssignat(),
				numContraetiq,
				outData,
				vendaNumeroBotelles,
				numberDecimalFormat.format(litrosPorBotella),
				sorLitros,
				sorLot.getLot().getCodiAssignat(),
				tipoDocumento,
				sorDesti,
				"",
				entLot.getValid(),
				sorLot.getValid(),
				new Boolean((entLot.getNumerosContraetiquetes() != null )));

			filaUnica.add(elemento, filaReport);
			
			elemento++;
			
	    }
	    
	    
	    if (lotsEntradaArray.size()>elemento){
	    	
	    	while (lotsEntradaArray.size()>elemento){
	    	
		    	EntradaLot entLot = (EntradaLot)lotsEntradaArray.get(elemento);
	
		    	
		    	String entLotProcedencia="";
		    	boolean dev = false;
		    	if (entLot.getDipositProcedencia().equals(entrada)){
		    		entLotProcedencia = missatge("pdf.llibres.embotellat.entrada", request);
		    	}else if(entLot.getDipositProcedencia().equals(tancamentLlibres)){
		    		entLotProcedencia = missatge("pdf.llibres.embotellat.tancamentLlibres", request);
		    	}else if(entLot.getDipositProcedencia().equals("Devolució de botelles") ||
		    			 entLot.getDipositProcedencia().equals("Devolución de botellas")){
		    		entLotProcedencia = missatge("pdf.llibres.embotellat.devolucio", request);
		    		dev = true;
		    	}else{
		    		oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
		    		entLotProcedencia=oliInfraestructuraEjb.dipositAmbId(new Long(entLot.getDipositProcedencia())).getCodiAssignat();
		    	}
		    	
		    	Double volumUni = entLot.getLot().getEtiquetatge().getTipusEnvas().getVolum();
		    	
		    	String entNumEm="";
				String entLitros="";
				try{
					if (dev){
						entNumEm = numberDecimalFormat.format(entLot.getBotelles());
						if (mesuraLitros){
							entLitros=numberDecimalFormat.format(entLot.getBotelles() * volumUni.doubleValue());
						}else{
							entLitros=numberDecimalFormat.format((double)0.916 * entLot.getBotelles() * volumUni.doubleValue());
						}
					} else {
						entNumEm = numberDecimalFormat.format(entLot.getLot().getLitresEnvassats().doubleValue() / volumUni.doubleValue());
						if (mesuraLitros){
							entLitros=numberDecimalFormat.format(entLot.getLot().getLitresEnvassats());
						}else{
							entLitros=numberDecimalFormat.format((double)0.916 * entLot.getLot().getLitresEnvassats().doubleValue());
						}
					} 
				}catch (Exception e) {
				}

		    	String numContraetiq="";
		    	if (entLot.getNumerosContraetiquetes()!=null){
		    		numContraetiq=entLot.getNumerosContraetiquetes();
		    	}
		    	if (entLot.getLot().getNumeroLot() != null && !entLot.getLot().getNumeroLot().equals("")){
		    		numContraetiq +=(numContraetiq.equals("")? "" : " - ") + "Num. Lot: " + entLot.getLot().getNumeroLot();
		    	}

		    	/* TODO: REVISAR EL DARRER PARAMETRE PERQUE CONTRAETIQUETES I NUM INICIAL === NULL */
//				filaReport = new FilaReport(
//						new Boolean (true),
//						dateF.format(entLot.getData()),
//						numberDecimalFormat.format(entLot.getAcidesa()),
//						entLotProcedencia,
//						entLot.getLot().getNumeroBotellesActuals().toString(),
//						numberDecimalFormat.format(entLot.getLot().getLitresEnvassats().doubleValue()/entLot.getLot().getNumeroBotellesActuals().doubleValue()),
//						entLitros,
//						entLot.getLot().getCodiAssignat(),
//						numContraetiq,
//						"",
//						"",
//						"",
//						"",
//						"",
//						"",
//						"",
//						"",
//						entLot.getValid(),
//						null,
//						new Boolean(!(entLot.getNumerosContraetiquetes() != null && (entLot.getLot().getNumeroEtiquetaInicial() == null && entLot.getLot().getNumeroEtiquetaFinal() == null))));
	
				filaReport = new FilaReport(
						new Boolean (true),
						dateF.format(entLot.getData()),
						numberDecimalFormat.format(entLot.getAcidesa()),
						entLotProcedencia,
						//entLot.getLot().getNumeroBotellesActuals().toString(),
						entNumEm,
						//numberDecimalFormat.format(entLot.getLot().getLitresEnvassats().doubleValue()/entLot.getLot().getNumeroBotellesActuals().doubleValue()),
						numberDecimalFormat.format(volumUni),
						entLitros,
						entLot.getLot().getCodiAssignat(),
						numContraetiq,
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						entLot.getValid(),
						null,
						new Boolean((entLot.getNumerosContraetiquetes() != null )));
				
				filaUnica.add(elemento, filaReport);
				
				elemento++;
			
	    	}
	    	
	    }else if (lotsSortidaArray.size()>elemento){
	    	
	    	while (lotsSortidaArray.size()>elemento){
	    	
		    	SortidaLot sorLot = (SortidaLot)lotsSortidaArray.get(elemento);
	
		    	String outData;
		    	String sorDesti;
		    	if (sorLot.getAccioSortida().equals(accioVenta)){
		    		outData=dateF.format(sorLot.getVendaData());
		    		sorDesti=sorLot.getVendaDestinatari();
		    		if (sorDesti.equals(venta)){
		    			sorDesti = missatge("pdf.llibres.embotellat.venta", request);
		    		}else if (sorDesti.equals(promocio)){
		    			sorDesti = missatge("pdf.llibres.embotellat.promocio", request);
		    		}
		    	}else{
		    		outData=dateF.format(sorLot.getCanviZonaData());
		    		sorDesti=sorLot.getCanviZonaObservacions();
		    	}
				
				String sorLitros="";
				//double litrosPorBotella = sorLot.getLot().getLitresEnvassats().doubleValue()/sorLot.getLot().getNumeroBotellesInicials().doubleValue();
				double litrosPorBotella = sorLot.getLot().getEtiquetatge().getTipusEnvas().getVolum().doubleValue();
				
				try{
					if (mesuraLitros){
						// sorLitros=numberDecimalFormat.format(sorLot.getLot().getLitresEnvassats());
						sorLitros = numberDecimalFormat.format(litrosPorBotella * sorLot.getVendaNumeroBotelles().doubleValue());
					}else{
						// sorLitros=numberDecimalFormat.format((double)0.916 * sorLot.getLot().getLitresEnvassats().doubleValue());
						sorLitros=numberDecimalFormat.format((double)0.916 * litrosPorBotella * sorLot.getVendaNumeroBotelles().doubleValue());
					}
				}catch (Exception e) {
				}
				
		    	String tipoDocumento="";
		    	if (sorLot.getVendaTipusDocument()!=null){
		    		tipoDocumento=sorLot.getVendaTipusDocument()+" - "+sorLot.getVendaNumeroDocument();
		    	}
		    	
		    	
				filaReport = new FilaReport(
						new Boolean (true),
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						outData,
						sorLot.getVendaNumeroBotelles().toString(),
						numberDecimalFormat.format(litrosPorBotella),
						sorLitros,
						sorLot.getLot().getCodiAssignat(),
						tipoDocumento,
						sorDesti,
						"",
						null,
						sorLot.getValid(),
						null);
	
				filaUnica.add(elemento, filaReport);
				
				elemento++;
			
	    	}
	    	
	    	
	    }
	    
		
	    int x;
	    for (x=0;x<filaUnica.size();x++){
	    	col.add(filaUnica.get(x));
	    }

		return col;
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
	 * Inyección de la dependencia oliConsultaEjb
	 * @param oliConsultaEjb La clase a inyectar.
	 */
	public void setOliConsultaEjb(OliConsultaEjb oliConsultaEjb) {
		this.oliConsultaEjb = oliConsultaEjb;
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





	public String getEntrada() {
		return entrada;
	}
	public void setEntrada(String entrada) {
		this.entrada = entrada;
	}

	public void setTancamentLlibres(String tancamentLlibres) {
		this.tancamentLlibres = tancamentLlibres;
	}

	public String getVenta() {
		return venta;
	}





	public void setVenta(String venta) {
		this.venta = venta;
	}





	public String getPromocio() {
		return promocio;
	}





	public void setPromocio(String promocio) {
		this.promocio = promocio;
	}





	public String getAccioVenta() {
		return accioVenta;
	}





	public void setAccioVenta(String accioVenta) {
		this.accioVenta = accioVenta;
	}





	public String getCanviZona() {
		return canviZona;
	}





	public void setCanviZona(String canviZona) {
		this.canviZona = canviZona;
	}

	
}