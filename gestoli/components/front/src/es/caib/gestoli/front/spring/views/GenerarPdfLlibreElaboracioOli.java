package es.caib.gestoli.front.spring.views;

import java.io.InputStream;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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
import es.caib.gestoli.logic.interfaces.OliProcessosEjb;
import es.caib.gestoli.logic.model.DescomposicioPartidaOliva;
import es.caib.gestoli.logic.model.Elaboracio;
import es.caib.gestoli.logic.model.EntradaDiposit;
import es.caib.gestoli.logic.model.PartidaOliva;


/**
 * GenerarPdfLlibreElaboracioOli
 * @author obarnes
 */public class GenerarPdfLlibreElaboracioOli extends AbstractPdfView {
	private static Logger logger = Logger.getLogger(GenerarPdfLlibreElaboracioOli.class);
	private OliConsultaEjb oliConsultaEjb;
	private OliInfraestructuraEjb oliInfraestructuraEjb;
	private OliProcessosEjb oliProcessosEjb;
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
	
		logger.debug("1.- Recuperando datos");
		oliConsultaEjb.setHibernateTemplate(getHibernateTemplate());
	    Iterator elaboraciones = oliConsultaEjb.oliElaboratConsulta(finicio, ffin, idEst, true, null).iterator();
 
		// Compilar archivo
		logger.debug("2.- Leyendo el stream del jrxml");
	    InputStream is = this.getClass().getClassLoader().getResourceAsStream("es/caib/gestoli/logic/resources/llibreElaboracioOli.jrxml");
		logger.debug("3.- Compilando el report");
	    JasperReport report = JasperCompileManager.compileReport(is);

		// Data Source
		logger.debug("4.- Asociando los datos");
		JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource( getDataSourceElaboracioOli(elaboraciones, mesura.equals("l")) );
		
		//Parametros
		logger.debug("5.- Cargando parametros");
		HashMap params = new HashMap();
		params.put("resLlibreElaboracioOli",missatge("pdf.llibres.elaboracio.titol", request));
		params.put("resData",missatge("pdf.llibres.data", request));
		params.put("resNomEst",missatge("pdf.llibres.tafona", request));
		params.put("nomEst"," "+nomEst);
		params.put("resHeNumEntrada",missatge("pdf.llibres.elaboracio.numEntrada", request));
		params.put("resHeVarietat",missatge("pdf.llibres.elaboracio.varietat", request));
		params.put("resHeDataElaboracio",missatge("pdf.llibres.elaboracio.dataEntrada", request));
		params.put("resPeu",missatge("pdf.llibres.elaboracio.peu", request));
		params.put("resPagina",missatge("pdf.llibres.elaboracio.pagina", request));
		params.put("resImg","es/caib/gestoli/logic/resources/do.jpg");
		params.put("resNomRes",missatge("pdf.llibres.elaboracio.nomResponsable", request));
		params.put("resNumElaboracio",missatge("pdf.llibres.elaboracio.numElaboracio", request));
		params.put("resHeKgOlivaTotal",missatge("pdf.llibres.elaboracio.kgOlivaTotal", request));
		params.put("resHeMallorquina",missatge("pdf.llibres.elaboracio.mallorquina", request));
		params.put("resHeArbequina",missatge("pdf.llibres.elaboracio.arbequina", request));
		params.put("resHePicual",missatge("pdf.llibres.elaboracio.picual", request));
		params.put("resHeExperimental",missatge("pdf.llibres.elaboracio.experimental", request));
		params.put("resHeAcidesa",missatge("pdf.llibres.elaboracio.acidesa", request));
		params.put("resHeNumDiposit",missatge("pdf.llibres.elaboracio.numDiposit", request));
		params.put("resHeObservacions",missatge("pdf.llibres.elaboracio.observacions", request));
		params.put("resHeTalcMarca",missatge("pdf.llibres.elaboracio.talcMarca", request));
		params.put("resHeTalcLot",missatge("pdf.llibres.elaboracio.talcLot", request));
		params.put("resHeTalcQuantitat",missatge("pdf.llibres.elaboracio.talcQuantitat", request));
		
		if(mesura.equalsIgnoreCase("kg")){
			params.put("resHeTotalLitres",missatge("pdf.llibres.elaboracio.totalKgOli", request));
		}else{
			params.put("resHeTotalLitres",missatge("pdf.llibres.elaboracio.totalLlitres", request));
		}
		
		logger.debug("6.- Cargando los datos al report");
		JasperPrint jasperPrint = JasperFillManager.fillReport( report, params, ds );

		// Configure exporter and set parameters
		ServletOutputStream out = response.getOutputStream();
		response.setContentType("application/pdf");
		response.setHeader("Cache-Control", "store");
		response.setHeader("Pragma", "cache");            
		response.setHeader("Content-Disposition","attachment; filename=llibreControlElaboracio.pdf");
		
		logger.debug("7.- Exportando el report a pdf");
	    byte[] bytes = JasperExportManager.exportReportToPdf(jasperPrint);
	    for (int i = 0; i < bytes.length; i ++) {
	    	out.write(bytes[i]);
	    }
	    out.close();
	    out.flush();

	    
    }
    
	
	
	public class FilaReport implements java.io.Serializable {

		public FilaReport(String fecha, String numEntrada,
				String dataElaboracio, String kgOlivaTotal, String mallorquina,
				String arbequina, String picual, String experimental,
				String acidesa, String totalLitres, String totalMallorquina,
				String totalArbequina, String totalPicual, String totalExperimental,
				String numDiposit, String numElaboracio, String talcMarca, 
				String talcLot, String talcQuantitat, String nomRes, 
				String observacions, String idElaboracio, Boolean valid) {
			super();
			this.fecha = fecha;
			this.numEntrada = numEntrada;
			this.dataElaboracio = dataElaboracio;
			this.kgOlivaTotal = kgOlivaTotal;
			this.mallorquina = mallorquina;
			this.arbequina = arbequina;
			this.picual = picual;
			this.experimental = experimental;
			this.acidesa = acidesa;
			this.totalLitres = totalLitres;
			this.totalMallorquina = totalMallorquina;
			this.totalArbequina = totalArbequina;
			this.totalPicual = totalPicual;
			this.totalExperimental = totalExperimental;
			this.numDiposit = numDiposit;
			this.numElaboracio = numElaboracio;
			this.talcMarca = talcMarca;
			this.talcLot = talcLot;
			this.talcQuantitat = talcQuantitat;
			this.nomRes = nomRes;
			this.observacions = observacions;
			this.idElaboracio = idElaboracio;
			
			if (!valid.booleanValue()) {
				String tagI = "<s style='color: red; text-decoration: line-through;'>";
				String tagF = "</s>";
				this.fecha = tagI + this.fecha + tagF;
				this.numElaboracio = tagI + this.numElaboracio + tagF;
				this.nomRes = tagI + this.nomRes + tagF;
				this.numEntrada = tagI + this.numEntrada + tagF;
				this.dataElaboracio = tagI + this.dataElaboracio + tagF;
				this.kgOlivaTotal = tagI + this.kgOlivaTotal + tagF;
				this.mallorquina = tagI + this.mallorquina + tagF;
				this.arbequina = tagI + this.arbequina + tagF;
				this.picual = tagI + this.picual + tagF;
				this.experimental = tagI + this.experimental + tagF;
				this.totalLitres = tagI + this.totalLitres + tagF;
				this.totalMallorquina = tagI + this.totalMallorquina + tagF;
				this.totalArbequina = tagI + this.totalArbequina + tagF;
				this.totalPicual = tagI + this.totalPicual + tagF;
				this.totalExperimental = tagI + this.totalExperimental + tagF;
				this.acidesa = tagI + this.acidesa + tagF;
				this.numDiposit = tagI + this.numDiposit + tagF;
				this.talcMarca = tagI + this.talcMarca + tagF;
				this.talcQuantitat = tagI + this.talcQuantitat + tagF;
				this.talcLot = tagI + this.talcLot + tagF;
				this.observacions = tagI + this.observacions + tagF;
			}
		}
	
		private String fecha;
		private String numEntrada;
		private String dataElaboracio;
		private String kgOlivaTotal;
		private String mallorquina;
		private String arbequina;
		private String picual;
		private String experimental;
		private String acidesa;
		private String totalLitres;
		private String totalMallorquina;
		private String totalArbequina;
		private String totalPicual;
		private String totalExperimental;
		private String numDiposit;
		private String numElaboracio;
		private String talcMarca;
		private String talcLot;
		private String talcQuantitat;
		private String nomRes;
		private String observacions;
		private String idElaboracio;
		
		public String getFecha() {
			return fecha;
		}
		public void setFecha(String fecha) {
			this.fecha = fecha;
		}
		public String getNumEntrada() {
			return numEntrada;
		}
		public void setNumEntrada(String numEntrada) {
			this.numEntrada = numEntrada;
		}
		public String getDataElaboracio() {
			return dataElaboracio;
		}
		public void setDataElaboracio(String dataElaboracio) {
			this.dataElaboracio = dataElaboracio;
		}
		public String getKgOlivaTotal() {
			return kgOlivaTotal;
		}
		public void setKgOlivaTotal(String kgOlivaTotal) {
			this.kgOlivaTotal = kgOlivaTotal;
		}
		public String getMallorquina() {
			return mallorquina;
		}
		public void setMallorquina(String mallorquina) {
			this.mallorquina = mallorquina;
		}
		public String getArbequina() {
			return arbequina;
		}
		public void setArbequina(String arbequina) {
			this.arbequina = arbequina;
		}
		public String getPicual() {
			return picual;
		}
		public void setPicual(String picual) {
			this.picual = picual;
		}
		public String getExperimental() {
			return experimental;
		}
		public void setExperimental(String experimental) {
			this.experimental = experimental;
		}
		public String getAcidesa() {
			return acidesa;
		}
		public void setAcidesa(String acidesa) {
			this.acidesa = acidesa;
		}
		public String getTotalLitres() {
			return totalLitres;
		}
		public void setTotalLitres(String totalLitres) {
			this.totalLitres = totalLitres;
		}
		public String getTotalMallorquina() {
			return totalMallorquina;
		}
		public void setTotalMallorquina(String totalMallorquina) {
			this.totalMallorquina = totalMallorquina;
		}
		public String getTotalArbequina() {
			return totalArbequina;
		}
		public void setTotalArbequina(String totalArbequina) {
			this.totalArbequina = totalArbequina;
		}
		public String getTotalPicual() {
			return totalPicual;
		}
		public void setTotalPicual(String totalPicual) {
			this.totalPicual = totalPicual;
		}
		public String getTotalExperimental() {
			return totalExperimental;
		}
		public void setTotalExperimental(String totalExperimental) {
			this.totalExperimental = totalExperimental;
		}
		public String getNumDiposit() {
			return numDiposit;
		}
		public void setNumDiposit(String numDiposit) {
			this.numDiposit = numDiposit;
		}
		public String getNumElaboracio() {
			return numElaboracio;
		}
		public void setNumElaboracio(String numElaboracio) {
			this.numElaboracio = numElaboracio;
		}
		public String getTalcMarca() {
			return talcMarca;
		}
		public void setTalcMarca(String talcMarca) {
			this.talcMarca = talcMarca;
		}
		public String getTalcLot() {
			return talcLot;
		}
		public void setTalcLot(String talcLot) {
			this.talcLot = talcLot;
		}
		public String getTalcQuantitat() {
			return talcQuantitat;
		}
		public void setTalcQuantitat(String talcQuantitat) {
			this.talcQuantitat = talcQuantitat;
		}
		public String getNomRes() {
			return nomRes;
		}
		public void setNomRes(String nomRes) {
			this.nomRes = nomRes;
		}
		public String getObservacions() {
			return observacions;
		}
		public void setObservacions(String observacions) {
			this.observacions = observacions;
		}
		public String getIdElaboracio() {
			return idElaboracio;
		}
		public void setIdElaboracio(String idElaboracio) {
			this.idElaboracio = idElaboracio;
		}

	}


	
	public Collection getDataSourceElaboracioOli(Iterator i, boolean mesuraLitros){
		Collection col=new ArrayList();
		FilaReport filaReport;
		
		SimpleDateFormat dateF = new SimpleDateFormat("dd/MM/yyyy");
		DecimalFormat numberDecimalFormat = new DecimalFormat("###,###,##0.00");
		
		String idElaboracio=String.valueOf("0");
				
		while(i.hasNext()){
			Elaboracio ela = (Elaboracio)i.next();
			idElaboracio = ela.getId().toString();
			
			ArrayList arrParOli = new ArrayList(ela.getPartidaOlivas());
			Collections.sort(arrParOli);  
			
			Iterator iteParOli = arrParOli.iterator();			
			
			String mallorquina=" ";
			String arbequina=" ";
			String picual=" ";
			String experimental=" ";
			
			String numeroPartida=" ";
			String kgPartida=" ";
			
			String dipositsString= " ";
			try {
				oliProcessosEjb.setHibernateTemplate(getHibernateTemplate());
				Iterator dipositsIte = oliProcessosEjb.findEntradaDiposiOliByElaboracio(ela.getId(), null);
				
				while (dipositsIte.hasNext()){
					EntradaDiposit d = (EntradaDiposit)dipositsIte.next();
					
					if (d.getDiposit().getCodiAssignat()!=null){
						if (!dipositsString.equals(" ")){
							dipositsString+=", ";
						}
						dipositsString+=d.getDiposit().getCodiAssignat();
					}
					
				}
				
			} catch (RemoteException e) {
				logger.error("EXCEPTION", e);
			}
			
			double kMallorquina = 0.0;
			double kArbequina = 0.0;
			double kPicual = 0.0;
			double kExperimental = 0.0;
			double kTotal = 0.0;
			double lTotal = ela.getLitres().doubleValue();
			
			while (iteParOli.hasNext()){
				PartidaOliva parOli = (PartidaOliva)iteParOli.next();
				
				numeroPartida=parOli.getNumeroEntrada().toString();
				kgPartida=parOli.getTotalQuilos();
				
				double lpMallorquina = 0.0;
				double lpArbequina = 0.0;
				double lpPicual = 0.0;
				double lpExperimental = 0.0;
				double kpTotal = parOli.getTotalKilos();
//				double lTotal = ela.getLitres().doubleValue();
    			
				if (parOli.getNomVarietat().equals("mezcla")){
					
					Iterator iteDescomp = parOli.getDescomposicioPartidesOlives().iterator();
					
					while (iteDescomp.hasNext()) {
						DescomposicioPartidaOliva d = (DescomposicioPartidaOliva)iteDescomp.next();
						
						if(d.getDescomposicioPlantacio().getVarietatOliva().getNom().equals("Mallorquina")){
							mallorquina="X";
							lpMallorquina += d.getKilos();
						}else if (d.getDescomposicioPlantacio().getVarietatOliva().getNom().equals("Arbequina")){
							arbequina="X";
							lpArbequina += d.getKilos();
						}else if (d.getDescomposicioPlantacio().getVarietatOliva().getNom().equals("Picual")){
							picual="X";
							lpPicual += d.getKilos();
						}else if (d.getDescomposicioPlantacio().getVarietatOliva().getNom().equals("Experimental")){
							experimental="X";
							lpExperimental += d.getKilos();
						}
//						kpTotal += d.getKilos();
					}
					
				}else if (parOli.getNomVarietat().equals("Mallorquina")){
					mallorquina="X";
					lpMallorquina = kpTotal;
				}else if (parOli.getNomVarietat().equals("Arbequina")){
					arbequina="X";
					lpArbequina = kpTotal;
				}else if (parOli.getNomVarietat().equals("Picual")){
					picual="X";
					lpPicual = kpTotal;
				}else if (parOli.getNomVarietat().equals("Experimental")){
					experimental="X";
					lpExperimental = kpTotal;
				}
				
				kMallorquina += lpMallorquina;
				kArbequina += lpArbequina;
				kPicual += lpPicual;
				kExperimental += lpExperimental;
				kTotal += kpTotal;
				
				lpMallorquina = kMallorquina * lTotal / kTotal;
				lpArbequina = kArbequina * lTotal / kTotal;
				lpPicual = kPicual * lTotal / kTotal;
				lpExperimental = kExperimental * lTotal / kTotal;
				
				if(!mesuraLitros){
					lTotal *= (double)0.916;
					lpMallorquina *= (double)0.916;
    				lpArbequina *= (double)0.916;
    				lpPicual *= (double)0.916;
    				lpExperimental *= (double)0.916;
				}
				
				filaReport = new FilaReport(
						dateF.format(ela.getData()),
						numeroPartida,
						parOli.getDataFormat(),
						kgPartida,
						mallorquina,
						arbequina,
						picual,
						experimental,
						numberDecimalFormat.format(ela.getAcidesa()), 
						numberDecimalFormat.format(lTotal),
						numberDecimalFormat.format(lpMallorquina),
						numberDecimalFormat.format(lpArbequina),
						numberDecimalFormat.format(lpPicual),
						numberDecimalFormat.format(lpExperimental),
						dipositsString,
						ela.getNumeroElaboracio().toString(),
						ela.getTalcMarcaComercial(),
						ela.getTalcLot(),
						numberDecimalFormat.format(ela.getTalcQuantitat()),
						" "+ela.getResponsable(),
						ela.getObservacions(),
						idElaboracio,
						ela.getValid());
				
				col.add(filaReport);
				
				mallorquina=" ";
				arbequina=" ";
				picual=" ";
				experimental=" ";
					
			}
	
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




	public void setOliProcessosEjb(OliProcessosEjb oliProcessosEjb) {
		this.oliProcessosEjb = oliProcessosEjb;
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