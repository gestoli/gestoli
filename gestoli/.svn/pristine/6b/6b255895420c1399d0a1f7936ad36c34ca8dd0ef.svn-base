package es.caib.gestoli.front.spring.views;

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
import es.caib.gestoli.logic.model.CategoriaOli;
import es.caib.gestoli.logic.model.EntradaDiposit;
import es.caib.gestoli.logic.model.Olivicultor;
import es.caib.gestoli.logic.model.PartidaOli;
import es.caib.gestoli.logic.model.SortidaDiposit;
import es.caib.gestoli.logic.util.Constants;

public class GenerarPdfLlibreExistenciesOli extends AbstractPdfView {
	private static Logger logger = Logger.getLogger(GenerarPdfLlibreExistenciesOli.class);

	private OliConsultaEjb oliConsultaEjb;
	private OliInfraestructuraEjb oliInfraestructuraEjb;
	private HibernateTemplate hibernateTemplate;
	private String perdua;
	private String embotellat;
	private String mescla;
	private String desqualificat;
	
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
	    InputStream is = this.getClass().getClassLoader().getResourceAsStream("es/caib/gestoli/logic/resources/llibreExistenciesOli.jrxml");
		logger.debug("3.- Compilando el report");
	    JasperReport report = JasperCompileManager.compileReport(is);

		// Data Source
		logger.debug("4.- Asociando los datos");
		JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource( getDataSourceExistenciesOli(finicio,ffin,idEst,mesura.equalsIgnoreCase("l"),request) );
		
		//Parametros
		logger.debug("5.- Cargando parametros");
		HashMap params = new HashMap();
		params.put("resLlibreExistenciesOli",missatge("pdf.llibres.existencies.resLlibreExistenciesOli", request));
		params.put("resNomEst",missatge("pdf.llibres.tafona", request));
		params.put("nomEst"," "+nomEst);
		params.put("resHeNumElab",missatge("pdf.llibres.existencies.resHeNumElab", request));
		params.put("resHeAcidesa",missatge("pdf.llibres.existencies.resHeAcidesa", request));
		params.put("resHeLitres",missatge("pdf.llibres.existencies.resHeLitres", request));
		params.put("resPeu",missatge("pdf.llibres.existencies.peu", request));
		params.put("resPagina",missatge("pdf.llibres.existencies.pagina", request));
		params.put("resImg","es/caib/gestoli/logic/resources/do.jpg");
		params.put("resHeObservacions",missatge("pdf.llibres.existencies.resHeObservacions", request));
		params.put("resHeDiposit",missatge("pdf.llibres.existencies.resHeDiposit", request));
		params.put("resHeData",missatge("pdf.llibres.existencies.resHeData", request));
		params.put("resHeDesti",missatge("pdf.llibres.existencies.resHeDesti", request));
		params.put("resHeEntrades",missatge("pdf.llibres.existencies.resHeEntrades", request));
		params.put("resHeSortides",missatge("pdf.llibres.existencies.resHeSortides", request));
		params.put("resSumaAnterior",missatge("pdf.llibres.existencies.resSumaAnterior", request));
		params.put("resSumaParcial",missatge("pdf.llibres.existencies.resSumaParcial", request));
		
		if(mesura.equalsIgnoreCase("kg")){
			params.put("resHeLitres",missatge("pdf.llibres.existencies.resHeQuilos", request));
		}else{
			params.put("resHeLitres",missatge("pdf.llibres.existencies.resHeLitres", request));
		}
		
		logger.debug("6.- Cargando los datos al report");
		JasperPrint jasperPrint = JasperFillManager.fillReport( report, params, ds );

		// Configure exporter and set parameters
		ServletOutputStream out = response.getOutputStream();
		response.setContentType("application/pdf");
		response.setHeader("Cache-Control", "store");
		response.setHeader("Pragma", "cache");            
		response.setHeader("Content-Disposition","attachment; filename=llibreControlExistencies.pdf");
		
		logger.debug("7.- Exportando el report a pdf");
	    byte[] bytes = JasperExportManager.exportReportToPdf(jasperPrint);
	    for (int i = 0; i < bytes.length; i ++) {
	    	out.write(bytes[i]);
	    }
	    out.close();
	    out.flush();

	    
    }
    
	
	
	public class FilaReport implements java.io.Serializable {

		public FilaReport(String inData, String inElab, String inAcidesa,
				String inLitres, String inDiposit, String inObservacions,
				String outData, String outDiposit, String outLitres,
				String outAcidesa, String outDesti, String outObservacions,
				String sumaEntradasArriba, String sumaEntradasAbajo,
				String sumaSortidesArriba, String sumaSortidesAbajo,
				Boolean pageChange, Boolean entValid, Boolean sorValid, 
				Double inLitresNumeric, Double outLitresNumeric) {
			super();
			this.inData = inData;
			this.inElab = inElab;
			this.inAcidesa = inAcidesa;
			this.inLitres = inLitres;
			this.inDiposit = inDiposit;
			this.inObservacions = inObservacions;
			this.outData = outData;
			this.outDiposit = outDiposit;
			this.outLitres = outLitres;
			this.outAcidesa = outAcidesa;
			this.outDesti = outDesti;
			this.outObservacions = outObservacions;
			this.sumaEntradasArriba = sumaEntradasArriba;
			this.sumaEntradasAbajo = sumaEntradasAbajo;
			this.sumaSortidesArriba = sumaSortidesArriba;
			this.sumaSortidesAbajo = sumaSortidesAbajo;
			this.pageChange = pageChange;
			
			String tagI = "<s style='color: red; text-decoration: line-through;'>";
			String tagF = "</s>";
			
			if (entValid != null && !entValid.booleanValue()) {
				this.inData = tagI + this.inData + tagF;
				this.inElab = tagI + this.inElab + tagF;
				this.inAcidesa = tagI + this.inAcidesa + tagF;
				this.inLitres = tagI + this.inLitres + tagF;
				this.inDiposit = tagI + this.inDiposit + tagF;
				this.inObservacions = tagI + this.inObservacions + tagF;
			} else {
				this.inLitresNumeric = inLitresNumeric;
			}
			
			if (sorValid != null && !sorValid.booleanValue()) {
				this.outData = tagI + this.outData + tagF;
				this.outDiposit = tagI + this.outDiposit + tagF;
				this.outLitres = tagI + this.outLitres + tagF;
				this.outAcidesa = tagI + this.outAcidesa + tagF;
				this.outDesti = tagI + this.outDesti + tagF;
				this.outObservacions = tagI + this.outObservacions + tagF;
			} else {
				this.outLitresNumeric = outLitresNumeric;
			}
			
			
		}
		
		private String inData;
		private String inElab;
		private String inAcidesa;
		private String inLitres;
		private String inDiposit;
		private String inObservacions;
		private String outData;
		private String outDiposit;
		private String outLitres;
		private String outAcidesa;
		private String outDesti;
		private String outObservacions;
		private String sumaEntradasArriba;
		private String sumaEntradasAbajo;
		private String sumaSortidesArriba;
		private String sumaSortidesAbajo;
		private Boolean pageChange;
		private Double inLitresNumeric;
		private Double outLitresNumeric;
		
		public String getInData() {
			return inData;
		}
		public void setInData(String inData) {
			this.inData = inData;
		}
		public String getInElab() {
			return inElab;
		}
		public void setInElab(String inElab) {
			this.inElab = inElab;
		}
		public String getInAcidesa() {
			return inAcidesa;
		}
		public void setInAcidesa(String inAcidesa) {
			this.inAcidesa = inAcidesa;
		}
		public String getInLitres() {
			return inLitres;
		}
		public void setInLitres(String inLitres) {
			this.inLitres = inLitres;
		}
		public String getInDiposit() {
			return inDiposit;
		}
		public void setInDiposit(String inDiposit) {
			this.inDiposit = inDiposit;
		}
		public String getInObservacions() {
			return inObservacions;
		}
		public void setInObservacions(String inObservacions) {
			this.inObservacions = inObservacions;
		}
		public String getOutData() {
			return outData;
		}
		public void setOutData(String outData) {
			this.outData = outData;
		}
		public String getOutDiposit() {
			return outDiposit;
		}
		public void setOutDiposit(String outDiposit) {
			this.outDiposit = outDiposit;
		}
		public String getOutLitres() {
			return outLitres;
		}
		public void setOutLitres(String outLitres) {
			this.outLitres = outLitres;
		}
		public String getOutAcidesa() {
			return outAcidesa;
		}
		public void setOutAcidesa(String outAcidesa) {
			this.outAcidesa = outAcidesa;
		}
		public String getOutDesti() {
			return outDesti;
		}
		public void setOutDesti(String outDesti) {
			this.outDesti = outDesti;
		}
		public String getOutObservacions() {
			return outObservacions;
		}
		public void setOutObservacions(String outObservacions) {
			this.outObservacions = outObservacions;
		}
		public Boolean getPageChange() {
			return pageChange;
		}
		public void setPageChange(Boolean pageChange) {
			this.pageChange = pageChange;
		}
		public String getSumaEntradasArriba() {
			return sumaEntradasArriba;
		}
		public void setSumaEntradasArriba(String sumaEntradasArriba) {
			this.sumaEntradasArriba = sumaEntradasArriba;
		}
		public String getSumaEntradasAbajo() {
			return sumaEntradasAbajo;
		}
		public void setSumaEntradasAbajo(String sumaEntradasAbajo) {
			this.sumaEntradasAbajo = sumaEntradasAbajo;
		}
		public String getSumaSortidesArriba() {
			return sumaSortidesArriba;
		}
		public void setSumaSortidesArriba(String sumaSortidesArriba) {
			this.sumaSortidesArriba = sumaSortidesArriba;
		}
		public String getSumaSortidesAbajo() {
			return sumaSortidesAbajo;
		}
		public void setSumaSortidesAbajo(String sumaSortidesAbajo) {
			this.sumaSortidesAbajo = sumaSortidesAbajo;
		}
		public Double getInLitresNumeric() {
			return inLitresNumeric;
		}
		public void setInLitresNumeric(Double inLitresNumeric) {
			this.inLitresNumeric = inLitresNumeric;
		}
		public Double getOutLitresNumeric() {
			return outLitresNumeric;
		}
		public void setOutLitresNumeric(Double outLitresNumeric) {
			this.outLitresNumeric = outLitresNumeric;
		}

	}


	
	public Collection getDataSourceExistenciesOli(Date finicio,Date ffin,Long idEst,boolean mesuraLitros, HttpServletRequest request) throws Exception{
		Collection col=new ArrayList();
		FilaReport filaReport;
		
		SimpleDateFormat dateF = new SimpleDateFormat("dd/MM/yyyy");
		DecimalFormat numberDecimalFormat = new DecimalFormat("###,###,##0.00");
		
		String cambioPagina=String.valueOf("0");
		
		oliConsultaEjb.setHibernateTemplate(getHibernateTemplate());
		ArrayList dipositsEntradaArray = new ArrayList(oliConsultaEjb.getEntradasDipositEntreFechasAndEstablecimiento(finicio,ffin,idEst));
 
		oliConsultaEjb.setHibernateTemplate(getHibernateTemplate());
		ArrayList dipositsSortidaArray = new ArrayList(oliConsultaEjb.getSortidesDipositEntreFechasAndEstablecimiento(finicio,ffin,idEst));

		ArrayList filaUnica;
		if (dipositsEntradaArray.size()>dipositsSortidaArray.size()){
			filaUnica = new ArrayList(dipositsEntradaArray.size());
		}else{
			filaUnica = new ArrayList(dipositsSortidaArray.size());
		}
		
		
		double sumaEntradasActual = 0;
		double sumaEntradasArriba = 0;
		double sumaSalidasActual = 0;
		double sumaSalidasArriba = 0;

		int elemento=0;	    
	    while (dipositsEntradaArray.size()>elemento && dipositsSortidaArray.size()>elemento){
	    	
	    	sumaEntradasArriba = sumaEntradasActual;
	    	sumaSalidasArriba = sumaSalidasActual;
	    		    	
	    	EntradaDiposit entDip = (EntradaDiposit)dipositsEntradaArray.get(elemento);
	    	SortidaDiposit sorDip = (SortidaDiposit)dipositsSortidaArray.get(elemento);
	    	
			String elaboracio="";
			if (entDip.getElaboracio()!=null){
				elaboracio=entDip.getElaboracio().getNumeroElaboracio().toString();
//				if (entDip.getElaboracio().getVarietatOli().getId() == 1){
//					entObserv = missatge("pdf.llibres.existencies.noDo", request);
//				} else if (entDip.getElaboracio().getVarietatOli().getId() == 2){
//					entObserv = missatge("pdf.llibres.existencies.pendent", request);
//				}
			}
			
			String entObserv="";
			if (entDip.getCategoriaOli()!=null){
				if (entDip.getCategoriaOli().getId().equals(Constants.CATEGORIA_NO_DO)){
					entObserv = missatge("pdf.llibres.existencies.noDo", request);
				} else if (entDip.getCategoriaOli().getId().equals(Constants.CATEGORIA_PENDENT)){
					entObserv = missatge("pdf.llibres.existencies.pendent", request);
				} else {
					entObserv = missatge("pdf.llibres.existencies.Do", request);
				}
			}
			
			if (entDip.getObservacions()!=null){
				entObserv += entDip.getObservacions();
			}
			
			String sorObserv="";
//			PartidaOli p = sorDip.getDipositBySdiCoddor().getPartidaOli();
//			if (p != null){
//				if (p.getEsDO()){
//					sorObserv = missatge("pdf.llibres.existencies.Do", request);
//				} else if (p.getEsQualificada()){
//					sorObserv = missatge("pdf.llibres.existencies.pendent", request);
//				} else {
//					sorObserv = missatge("pdf.llibres.existencies.noDo", request);
//				}
//			}
			CategoriaOli c = sorDip.getCategoriaOli();
			if (c != null){
				if (c.getId().equals(Constants.CATEGORIA_DO)){
					sorObserv = missatge("pdf.llibres.existencies.Do", request);
				} else if (c.getId().equals(Constants.CATEGORIA_PENDENT)){
					sorObserv = missatge("pdf.llibres.existencies.pendent", request);
				} else {
					sorObserv = missatge("pdf.llibres.existencies.noDo", request);
				}
			}
			
			if (sorDip.getObservacions()!=null){
				sorObserv += sorDip.getObservacions();
			}
			
			String sorDesti="";
			if (sorDip.getDesti()!=null){
				if (sorDip.getDesti().equals(mescla)){
					sorDesti=missatge("pdf.llibres.existencies.mescla", request);
				}else if (sorDip.getDesti().equals(perdua)){
					sorDesti=missatge("pdf.llibres.existencies.perdua", request);
				}else if (sorDip.getDesti().equals(embotellat)){					
					sorDesti=missatge("pdf.llibres.existencies.embotellat", request);
				}else{
					sorDesti=sorDip.getDesti();
				}
			} else {
				Olivicultor olivicultor = sorDip.getOlivicultor();
				if (olivicultor != null){
					sorDesti = missatge("pdf.llibres.existencies.retirat", request) + olivicultor.getNom();
				}
			}
			
			String entLitros="";
			// obarnes 20091223: s'ha modificat la plantilla de iReport per a que les sumes les faci
			// internament, no que es passi un valor de camp. Aixi, sumaEntradasActual i la resta no serveixen
			// de res, però per si de cas, les deixem per a futures comprobacions de valors correctes.
			Double entLitrosNumeric = new Double(0);
			try{
				if (mesuraLitros){
					entLitros=numberDecimalFormat.format(entDip.getLitres());
					entLitrosNumeric = entDip.getLitres();
					if (entDip.getValid().booleanValue()) {
						sumaEntradasActual+=entDip.getLitres().doubleValue();
					}
				}else{
					entLitros=numberDecimalFormat.format((double)0.916 * entDip.getLitres().doubleValue());
					entLitrosNumeric = new Double(0.916 * entDip.getLitres().doubleValue());
					if (entDip.getValid().booleanValue()) {
						sumaEntradasActual+=(double)0.916 * entDip.getLitres().doubleValue();
					}
				}
			}catch (Exception e) {
			}
			
			String sorLitros="";
			Double sorLitrosNumeric = new Double(0);
			try{
				if (mesuraLitros){
					sorLitros=numberDecimalFormat.format(sorDip.getLitres());
					sorLitrosNumeric = sorDip.getLitres();
					if (sorDip.getValid().booleanValue()) {
						sumaSalidasActual+=sorDip.getLitres().doubleValue();
					}
				}else{
					sorLitros=numberDecimalFormat.format((double)0.916 * sorDip.getLitres().doubleValue());
					sorLitrosNumeric = new Double(0.916 * sorDip.getLitres().doubleValue());
					if (sorDip.getValid().booleanValue()) {
						sumaSalidasActual+=(double)0.916 * sorDip.getLitres().doubleValue();
					}
				}
			}catch (Exception e) {
			}
			
			String entAcidesa="";
			try{
				entAcidesa=numberDecimalFormat.format(entDip.getAcidesa());
			}catch (Exception e) {
			}
			
			String sorAcidesa="";
			try{
				sorAcidesa=numberDecimalFormat.format(sorDip.getAcidesa());
			}catch (Exception e) {
			}
			
			String dipositDeSortida = "";
			if (sorDip.getDipositBySdiCoddor()!=null && sorDip.getDipositBySdiCoddor().getCodiAssignat()!=null){
				dipositDeSortida=sorDip.getDipositBySdiCoddor().getCodiAssignat();
			}
			
			
			filaReport = new FilaReport(
				dateF.format(entDip.getData()),
				elaboracio,
				entAcidesa,
				entLitros,
				entDip.getDiposit().getCodiAssignat(), 
				entObserv,
				dateF.format(sorDip.getData()),
				dipositDeSortida,
				sorLitros,
				sorAcidesa, 
				sorDesti,
				sorObserv,
				numberDecimalFormat.format(sumaEntradasArriba),
				numberDecimalFormat.format(sumaEntradasActual),
				numberDecimalFormat.format(sumaSalidasArriba),
				numberDecimalFormat.format(sumaSalidasActual),
				new Boolean(true),
				entDip.getValid(),
				sorDip.getValid(),
				entLitrosNumeric,
				sorLitrosNumeric);

			filaUnica.add(elemento, filaReport);
			
			elemento++;
			
	    }
	    
	    
	    if (dipositsEntradaArray.size()>elemento){
	    	
	    	while (dipositsEntradaArray.size()>elemento){
		    	
		    	sumaEntradasArriba=sumaEntradasActual;
		    	sumaSalidasArriba=sumaSalidasActual;
	    		
		    	EntradaDiposit entDip = (EntradaDiposit)dipositsEntradaArray.get(elemento);
		    	
				String elaboracio="";
				if (entDip.getElaboracio()!=null){
					elaboracio=entDip.getElaboracio().getNumeroElaboracio().toString();
				}
		    	
//				String entObserv="";
//				if (entDip.getElaboracio()!=null){
//					if (entDip.getElaboracio().getVarietatOli().getId() == 1){
//						entObserv = missatge("pdf.llibres.existencies.noDo", request);
//					} else if (entDip.getElaboracio().getVarietatOli().getId() == 2){
//						entObserv = missatge("pdf.llibres.existencies.pendent", request);
//					}
//				}
				String entObserv="";
				if (entDip.getCategoriaOli()!=null){
					if (entDip.getCategoriaOli().getId().equals(Constants.CATEGORIA_NO_DO)){
						entObserv = missatge("pdf.llibres.existencies.noDo", request);
					} else if (entDip.getCategoriaOli().getId().equals(Constants.CATEGORIA_PENDENT)){
						entObserv = missatge("pdf.llibres.existencies.pendent", request);
					} else {
						entObserv = missatge("pdf.llibres.existencies.Do", request);
					}
				}
				if (entDip.getObservacions()!=null){
					entObserv += entDip.getObservacions();
				}
		    	
				String entLitros="";
				Double entLitrosNumeric = new Double(0);
				try{
					if (mesuraLitros){
						entLitros=numberDecimalFormat.format(entDip.getLitres());
						entLitrosNumeric = entDip.getLitres();
						if(entDip.getValid().booleanValue()){
							sumaEntradasActual+=entDip.getLitres().doubleValue();
						}
						
					}else{
						entLitros=numberDecimalFormat.format((double)0.916 * entDip.getLitres().doubleValue());
						entLitrosNumeric = new Double(0.916 * entDip.getLitres().doubleValue());
						if (entDip.getValid().booleanValue()) {
							sumaEntradasActual+=(double)0.916 * entDip.getLitres().doubleValue();
						}
					}
				}catch (Exception e) {
				}
				
				String entAcidesa="";
				try{
					entAcidesa=numberDecimalFormat.format(entDip.getAcidesa());
				}catch (Exception e) {
				}
				
				filaReport = new FilaReport(
					dateF.format(entDip.getData()),
					elaboracio,
					entAcidesa,
					entLitros,
					entDip.getDiposit().getCodiAssignat(), 
					entObserv,
					"",
					"",
					"",
					"", 
					"",
					"",
					numberDecimalFormat.format(sumaEntradasArriba),
					numberDecimalFormat.format(sumaEntradasActual),
					numberDecimalFormat.format(sumaSalidasArriba),
					numberDecimalFormat.format(sumaSalidasActual),
					new Boolean(true),
					entDip.getValid(),
					null,
					entLitrosNumeric,
					null);
	
				filaUnica.add(elemento, filaReport);
				
				
				
				elemento++;
				
	    	}
	    	
	    }else if (dipositsSortidaArray.size()>elemento){
	    	
	    	while (dipositsSortidaArray.size()>elemento){
		    	
		    	sumaEntradasArriba=sumaEntradasActual;
		    	sumaSalidasArriba=sumaSalidasActual;
	    		
		    	SortidaDiposit sorDip = (SortidaDiposit)dipositsSortidaArray.get(elemento);
				
				String sorObserv="";
//				PartidaOli p = sorDip.getDipositBySdiCoddor().getPartidaOli();
//				if (p != null){
//					if (p.getEsDO()){
//						sorObserv = missatge("pdf.llibres.existencies.Do", request);
//					} else if (p.getEsQualificada()){
//						sorObserv = missatge("pdf.llibres.existencies.pendent", request);
//					} else {
//						sorObserv = missatge("pdf.llibres.existencies.noDo", request);
//					}
//				}
		    	CategoriaOli c = sorDip.getCategoriaOli();
				if (c != null){
					if (c.getId().equals(Constants.CATEGORIA_DO)){
						sorObserv = missatge("pdf.llibres.existencies.Do", request);
					} else if (c.getId().equals(Constants.CATEGORIA_PENDENT)){
						sorObserv = missatge("pdf.llibres.existencies.pendent", request);
					} else {
						sorObserv = missatge("pdf.llibres.existencies.noDo", request);
					}
				}
				if (sorDip.getObservacions()!=null){
					sorObserv += sorDip.getObservacions();
				}
				
				String sorDesti="";
				if (sorDip.getDesti()!=null){
					if (sorDip.getDesti().equals(mescla)){
						sorDesti=missatge("pdf.llibres.existencies.mescla", request);
					}else if (sorDip.getDesti().equals(perdua)){
						sorDesti=missatge("pdf.llibres.existencies.perdua", request);
					}else if (sorDip.getDesti().equals(embotellat)){					
						sorDesti=missatge("pdf.llibres.existencies.embotellat", request);
					}else{
						sorDesti=sorDip.getDesti();
					}
				} else {
					Olivicultor olivicultor = sorDip.getOlivicultor();
					if (olivicultor != null){
						sorDesti = missatge("pdf.llibres.existencies.retirat", request) + olivicultor.getNom();
					}
				}
				
				String sorLitros="";
				Double sorLitrosNumeric = new Double(0);
				try{
					if (mesuraLitros){
						sorLitros=numberDecimalFormat.format(sorDip.getLitres());
						sorLitrosNumeric = sorDip.getLitres();
						if (sorDip.getValid().booleanValue()) {
							sumaSalidasActual+=sorDip.getLitres().doubleValue();
						}
						
					}else{
						sorLitros=numberDecimalFormat.format((double)0.916 * sorDip.getLitres().doubleValue());
						sorLitrosNumeric = new Double(0.916 * sorDip.getLitres().doubleValue());
						if (sorDip.getValid().booleanValue()) {
							sumaSalidasActual+=(double)0.916 * sorDip.getLitres().doubleValue();
						}
					}
				}catch (Exception e) {
				}
		    	
				String sorAcidesa="";
				try{
					sorAcidesa=numberDecimalFormat.format(sorDip.getAcidesa());
				}catch (Exception e) {
				}
				
				String dipositDeSortida = "";
				if (sorDip.getDipositBySdiCoddor()!=null && sorDip.getDipositBySdiCoddor().getCodiAssignat()!=null){
					dipositDeSortida=sorDip.getDipositBySdiCoddor().getCodiAssignat();
				}
				
				filaReport = new FilaReport(
						"",
						"",
						"",
						"",
						"", 
						"",
						dateF.format(sorDip.getData()),
						dipositDeSortida,
						sorLitros,
						sorAcidesa, 
						sorDesti,
						sorObserv,
						numberDecimalFormat.format(sumaEntradasArriba),
						numberDecimalFormat.format(sumaEntradasActual),
						numberDecimalFormat.format(sumaSalidasArriba),
						numberDecimalFormat.format(sumaSalidasActual),
						new Boolean(true),
						null,
						sorDip.getValid(),
						null,
						sorLitrosNumeric);
	
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
	
    

	/**
	 * Inyección de la dependencia oliConsultaEjb
	 * 
	 * @param oliConsultaEjb
	 *            La clase a inyectar.
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



	public String getPerdua() {
		return perdua;
	}



	public void setPerdua(String perdua) {
		this.perdua = perdua;
	}



	public String getEmbotellat() {
		return embotellat;
	}



	public void setEmbotellat(String embotellat) {
		this.embotellat = embotellat;
	}



	public String getMescla() {
		return mescla;
	}



	public void setMescla(String mescla) {
		this.mescla = mescla;
	}



	public String getDesqualificat() {
		return desqualificat;
	}



	public void setDesqualificat(String desqualificat) {
		this.desqualificat = desqualificat;
	}

	
}