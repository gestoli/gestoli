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
import es.caib.gestoli.logic.model.Bota;
import es.caib.gestoli.logic.model.ElaboracioOliva;
import es.caib.gestoli.logic.model.EntradaLot;
import es.caib.gestoli.logic.model.PartidaOliva;
import es.caib.gestoli.logic.model.SortidaLot;
import es.caib.gestoli.logic.util.Constants;


/**
 * GenerarPdfDeclaracioMensual
 * @author obarnes
 */
public class GenerarPdfDeclaracioMensualOlivaTaula extends AbstractPdfView {
	private static Logger logger = Logger.getLogger(GenerarPdfDeclaracioMensualOlivaTaula.class);
	private OliConsultaEjb oliConsultaEjb;
	private OliInfraestructuraEjb oliInfraestructuraEjb;
	private HibernateTemplate hibernateTemplate;
	
    private String olivaVerda;
	private String olivaTrencada;
	private String olivaNegra;
	
	private Double totalVerda;
	private Double totalTrencada;
	private Double totalNegra;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void buildPdfDocument(
        Map model,
        Document document,
        PdfWriter writer,
        HttpServletRequest request,
        HttpServletResponse response)
        throws Exception {
		
		document = null;
		writer = null;
		
		DecimalFormat numberDecimalFormat = new DecimalFormat("###,###,##0.00");
		
		String fi = request.getParameter("dataInici");
		String ff = request.getParameter("dataFi");
		String idE = request.getParameter("idEst");
		Long idEst = new Long(idE);
    	
		oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
		oliConsultaEjb.setHibernateTemplate(getHibernateTemplate());
		
		//NOM ESTABLIMENT
		String nomEst = oliInfraestructuraEjb.establimentAmbId(idEst).getNom();
		
		
		//DATES
		SimpleDateFormat dateF = new SimpleDateFormat("dd/MM/yyyy");
		Date finicio = null;
		Date ffin = null;
		String mesDeclaracio = "";
		String anyDeclaracio = "";
		try {
		    finicio = dateF.parse(fi);
		    ffin = dateF.parse(ff);
		    
		    Calendar mesDeclaracioCal = Calendar.getInstance();
		    mesDeclaracioCal.setTime(finicio);
		    
		    anyDeclaracio = String.valueOf(mesDeclaracioCal.get(Calendar.YEAR));
		    
		    switch(mesDeclaracioCal.get(Calendar.MONTH)){
		    	case 0: mesDeclaracio = "Gener"; break;
		    	case 1: mesDeclaracio = "Febrer"; break;
		    	case 2: mesDeclaracio = "Març"; break;
		    	case 3: mesDeclaracio = "Abril"; break;
		    	case 4: mesDeclaracio = "Maig"; break;
		    	case 5: mesDeclaracio = "Juny"; break;
		    	case 6: mesDeclaracio = "Juliol"; break;
		    	case 7: mesDeclaracio = "Agost"; break;
		    	case 8: mesDeclaracio = "Setembre"; break;
		    	case 9: mesDeclaracio = "Octubre"; break;
		    	case 10: mesDeclaracio = "Novembre"; break;
		    	case 11: mesDeclaracio = "Desembre"; break;
		    }
		    
		} catch (ParseException ex) {
		    finicio=Calendar.getInstance().getTime();
		    ffin=Calendar.getInstance().getTime();
		}
		
		//ENTRADES
		Collection entradesOliva = oliInfraestructuraEjb.findPartidesOlivaTaulaByEstablimentEntreDates(idEst,finicio,ffin,true);
		
		Double totalQuilosEntradaOliva = 0.0;
		
		for(Iterator it=entradesOliva.iterator(); it.hasNext();){
			PartidaOliva po = (PartidaOliva)it.next();
			if (po.getEsOlivaDeTaula()) totalQuilosEntradaOliva += po.getTotalKilos();
		}
		
		Collection elaboracions = oliConsultaEjb.olivaElaboradaConsulta(finicio, ffin, idEst, true, true);
		
		Double totalElaboracioVerda = 0.0;
		Double totalElaboracioTrencada = 0.0;
		Double totalElaboracioNegra = 0.0;
		
		for(Iterator it=elaboracions.iterator(); it.hasNext();){
			ElaboracioOliva e = (ElaboracioOliva)it.next();
			
			if(e.getBota().getTipusOlivaTaula().equals(Constants.TIPUS_OLIVA_TAULA_VERDA)){
				totalElaboracioVerda += e.getTotalKilos();
			} else if(e.getBota().getTipusOlivaTaula().equals(Constants.TIPUS_OLIVA_TAULA_TRENCADA)) {
				totalElaboracioTrencada += e.getTotalKilos();
			} else if(e.getBota().getTipusOlivaTaula().equals(Constants.TIPUS_OLIVA_TAULA_NEGRA)) {
				totalElaboracioNegra += e.getTotalKilos();
			}
		}
		
		
		
		
		//SORTIDES
		
		Collection sortidesLot = oliConsultaEjb.getSortidesLotsOlivaTaulaEntreDadesAndEstabliment(finicio, ffin, idEst, true);
		
		Double totalLotSortidaVerda = 0.0;
		Double totalLotSortidaTrencada = 0.0;
		Double totalLotSortidaNegra = 0.0;
		
		for(Iterator it=sortidesLot.iterator(); it.hasNext();){
			SortidaLot sl = (SortidaLot)it.next();
			
			if(sl.getLot().getTipusOlivaTaula().equals(Constants.TIPUS_OLIVA_TAULA_VERDA)){
				totalLotSortidaVerda += sl.getVendaLitres();
			} else if(sl.getLot().getTipusOlivaTaula().equals(Constants.TIPUS_OLIVA_TAULA_TRENCADA)) {
				totalLotSortidaTrencada += sl.getVendaLitres();
			} else if(sl.getLot().getTipusOlivaTaula().equals(Constants.TIPUS_OLIVA_TAULA_NEGRA)) {
				totalLotSortidaNegra += sl.getVendaLitres();
			}
		}
		
		// Descontarem les devolucions a les sortides (per a fer-ho bé s'haurien de contabilitzar com a entrades..., no?)
		Collection entradesLot = oliConsultaEjb.getEntradesLotsDevolucioEntreDadesAndEstabliment(finicio, ffin, idEst, true);
		
		for(Iterator it=entradesLot.iterator(); it.hasNext();){
			EntradaLot el = (EntradaLot)it.next();
			
			if (el.getEsDevolucio() != null && el.getEsDevolucio().booleanValue() == true) {
				if(el.getLot().getTipusOlivaTaula().equals(Constants.TIPUS_OLIVA_TAULA_VERDA)){
					totalLotSortidaVerda -= el.getLitres();
				} else if (el.getLot().getTipusOlivaTaula().equals(Constants.TIPUS_OLIVA_TAULA_TRENCADA)){
					totalLotSortidaTrencada += el.getLitres();
				} else if (el.getLot().getTipusOlivaTaula().equals(Constants.TIPUS_OLIVA_TAULA_NEGRA)){
					totalLotSortidaNegra += el.getLitres();
				}
			}
		}
		
		
		//EXISTENCIES DEL DARRER DIA DE MES
   		
		// Cerca totes les botes de un establecimiento no vacios y que pertenecen a unas determinadas categorias
	   	Collection existenciesBota = oliConsultaEjb.findBotasActivasByEstablecimientoAndData(idEst, ffin);
	   
	   	// Cerca tots los lotes de un establecimiento no vacios y que pertenecen a unas determinadas categorias
	    Collection existenciesEmbotellat = oliConsultaEjb.findLotsOlivaTaulaDisponiblesByEstablecimientoAndData(idEst, ffin);

	    Double envasadaVerda = 0.0;
	    Double envasadaTrencada = 0.0;
	    Double envasadaNegra = 0.0;
	    
	    
	    for(Iterator it=existenciesEmbotellat.iterator(); it.hasNext();){
			//existencia[0] = id lot
			//existencia[1] = codi lot
			//existencia[2] = litres
			//existencia[3] = quilos
			//existencia[4] = categoria (objecte CategoriaOli)
	    	Object[] existencia = (Object[])it.next();
			
			if(existencia[4].equals(Constants.TIPUS_OLIVA_TAULA_VERDA)){
				envasadaVerda += (Double)existencia[2];
			} else if(existencia[4].equals(Constants.TIPUS_OLIVA_TAULA_TRENCADA)){
				envasadaTrencada += (Double)existencia[2];
			} else if(existencia[4].equals(Constants.TIPUS_OLIVA_TAULA_NEGRA)){
				envasadaNegra += (Double)existencia[2];
			}
	    }
	    
		// Compilar archivo
		logger.debug("2.- Leyendo el stream del jrxml");
	    InputStream is = this.getClass().getClassLoader().getResourceAsStream("es/caib/gestoli/logic/resources/declaracioMensualOlivaTaula.jrxml");
		logger.debug("3.- Compilando el report");
	    JasperReport report = JasperCompileManager.compileReport(is);

		// Data Source
		logger.debug("4.- Asociando los datos");
		JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource( getDataSourceEntradaOliva(existenciesBota) );
		
		//Parametros
		logger.debug("5.- Cargando parametros");
		HashMap params = new HashMap();
		
		//TEXTES FIXES
		
		//General
		params.put("toliva_verda","Oliva verda");
		params.put("toliva_trencada","Oliva trencada");
		params.put("toliva_negra","Oliva negra");
		
		
		//Capçalera
		params.put("timatgeDO","es/caib/gestoli/logic/resources/do.jpg");
		params.put("ttitol","DECLARACIÓ MENSUAL PRODUCTORS D'OLIVA DE TAULA");
		params.put("tnomest"," NOM DEL PRODUCTOR: ");
		params.put("tmesdeclaracio"," DECLARACIÓ DEL MES DE: ");
		
		//Entrades
		params.put("tentrades"," ENTRADES");
		params.put("tentradesoliva"," ENTRADES D'OLIVA");
		params.put("telaborada"," OLIVA ELABORADA");
		params.put("telaborada_verda"," A: Oliva verda");
		params.put("telaborada_trencada"," B: Oliva trencada");
		params.put("telaborada_negra"," C: Oliva negra");
		
		//Sortides
		params.put("tsortides"," SORTIDES D'OLIVA DE LES INSTAL·LACIONS");
		params.put("tsortidesenvasada","ENVASADA (litres)");
		
		//Existències
		params.put("texistencies"," EXISTÈNCIES del DARRER DIA DE MES");
		params.put("texistenciesbota","EN BOTA");
		params.put("texistenciesnumbot","Núm. bota");
		params.put("texistencieslitres","Kg");
		params.put("texistenciestotal_verda","TOTAL VERDA");
		params.put("texistenciestotal_trencada","TOTAL TRENCADA");
		params.put("texistenciestotal_negra","TOTAL NEGRA");
		params.put("texistenciesembotellat","ENVASADA");
		params.put("texistenciesenvasat_verda","Verda");
		params.put("texistenciesenvasat_trencada","Trencada");
		params.put("texistenciesenvasat_negra","Negra");
		
		//Peu
		params.put("tpeudata"," Data:");
		params.put("tpeusignatura"," Signatura:");
		
		
		
		//DADES
		
		//Capçalera
		params.put("dnomest",nomEst);
		params.put("dmesdeclaracio",mesDeclaracio + " - " + anyDeclaracio);
		
		//Entrades
		params.put("dentradesoliva",numberDecimalFormat.format(totalQuilosEntradaOliva) + " Kilos");
		params.put("delaborada_verda", numberDecimalFormat.format(totalElaboracioVerda) + " Kilos");
		params.put("delaborada_trencada", numberDecimalFormat.format(totalElaboracioTrencada) + " Kilos");
		params.put("delaborada_negra", numberDecimalFormat.format((totalElaboracioNegra)) + " Kilos");
		
		//Sortides
		params.put("dsortida_verda", numberDecimalFormat.format(totalLotSortidaVerda));
		params.put("dsortida_trencada", numberDecimalFormat.format(totalLotSortidaTrencada));
		params.put("dsortida_negra", numberDecimalFormat.format(totalLotSortidaNegra));
		
		//Existencies
		params.put("dtotalbota_verda",  numberDecimalFormat.format(totalVerda));
		params.put("dtotalbota_trencada",  numberDecimalFormat.format(totalTrencada));
		params.put("dtotalbota_negra",  numberDecimalFormat.format(totalNegra));
		
		params.put("dtotalEnvasat_verda",  numberDecimalFormat.format(envasadaVerda));
		params.put("dtotalEnvasat_trencada",  numberDecimalFormat.format(envasadaTrencada));
		params.put("dtotalEnvasat_negra",  numberDecimalFormat.format(envasadaNegra));
		
		
		logger.debug("6.- Cargando los datos al report");
		JasperPrint jasperPrint = JasperFillManager.fillReport( report, params, ds );

		// Configure exporter and set parameters
		ServletOutputStream out = response.getOutputStream();
		response.setContentType("application/pdf");
		response.setHeader("Cache-Control", "store");
		response.setHeader("Pragma", "cache");            
		response.setHeader("Content-Disposition", "attachment; filename=declaracioMensual_"+mesDeclaracio + "_" + anyDeclaracio+".pdf");
		
		logger.debug("7.- Exportando el report a pdf");
	    byte[] bytes = JasperExportManager.exportReportToPdf(jasperPrint);
	    for (int i = 0; i < bytes.length; i ++) {
	    	out.write(bytes[i]);
	    }
	    out.close();
	    out.flush();

    }
    
	
	public class FilaReport implements java.io.Serializable {
		
		private static final long serialVersionUID = 1L;

		private String bot_verda;
		private String lit_verda;
		private String bot_trencada;
		private String lit_trencada;
		private String bot_negra;
		private String lit_negra;
		
		public FilaReport(String bot_verda, String lit_verda, String bot_trencada,
				String lit_trencada, String bot_negra, String lit_negra) {

			this.bot_verda = bot_verda;
			this.lit_verda = lit_verda;
			this.bot_trencada = bot_trencada;
			this.lit_trencada = lit_trencada;
			this.bot_negra = bot_negra;
			this.lit_negra = lit_negra;
			
		}

		public String getBot_verda() {
			return bot_verda;
		}

		public void setBot_verda(String bot_verda) {
			this.bot_verda = bot_verda;
		}

		public String getLit_verda() {
			return lit_verda;
		}

		public void setLit_verda(String lit_verda) {
			this.lit_verda = lit_verda;
		}

		public String getBot_trencada() {
			return bot_trencada;
		}

		public void setBot_trencada(String bot_trencada) {
			this.bot_trencada = bot_trencada;
		}

		public String getLit_trencada() {
			return lit_trencada;
		}

		public void setLit_trencada(String lit_trencada) {
			this.lit_trencada = lit_trencada;
		}

		public String getBot_negra() {
			return bot_negra;
		}

		public void setBot_negra(String bot_negra) {
			this.bot_negra = bot_negra;
		}

		public String getLit_negra() {
			return lit_negra;
		}

		public void setLit_negra(String lit_negra) {
			this.lit_negra = lit_negra;
		}

		
		
	}
	
	
	

	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Collection getDataSourceEntradaOliva(Collection existenciesBota){
		
		//Desglosam les existencies per categoria
		List<Object[]> olivaVerda = new ArrayList<Object[]>();
		List<Object[]> olivaTrencada = new ArrayList<Object[]>();
		List<Object[]> olivaNegra = new ArrayList<Object[]>();
		
		for(Iterator it=existenciesBota.iterator(); it.hasNext();){
			//existencia[0] = id diposit
			//existencia[1] = codi dipòsit
			//existencia[2] = litres existencies
			//existencia[3] = kilos existencies
			//existencia[4] = categoria (Objecte CategoriaOli)
			//existencia[5] = olivicultor es propietari (camp Boolean)
			Bota bota = (Bota)it.next();
			
			Object[] o = new Object[2];
			o[0] = bota.getIdentificador();
			o[1] = bota.getKgOlivaRestant();
			
			if(bota.getTipusOlivaTaula().equals(Constants.TIPUS_OLIVA_TAULA_VERDA)){
				olivaVerda.add(o);
			} else if(bota.getTipusOlivaTaula().equals(Constants.TIPUS_OLIVA_TAULA_TRENCADA)){
				olivaTrencada.add(o);
			} else if(bota.getTipusOlivaTaula().equals(Constants.TIPUS_OLIVA_TAULA_NEGRA)){
				olivaNegra.add(o);
			}
		}
		
		
		//Emplenam el resultat
		Collection col=new ArrayList();
		FilaReport filaReport;
		
		DecimalFormat numberDecimalFormat = new DecimalFormat("###,###,##0.00");
		
		//Inicialitzam totals
		totalVerda = 0.0;
		totalTrencada = 0.0;
		totalNegra = 0.0;
		
		for(int i=0; i<olivaVerda.size() || i<olivaTrencada.size() || i<olivaNegra.size(); i++){
			String botverda = "";
			Double litverda = null;
			String bottrencada = "";
			Double littrencada = null;
			String botnegra = "";
			Double litnegra = null;
			
			if(i < olivaVerda.size()){
				botverda = (String)olivaVerda.get(i)[0];
				litverda = (Double)olivaVerda.get(i)[1];
				totalVerda += (Double)olivaVerda.get(i)[1];
			}
			
			if(i <  olivaTrencada.size()){
				bottrencada = (String)olivaTrencada.get(i)[0];
				littrencada = (Double)olivaTrencada.get(i)[1];
				totalTrencada += (Double)olivaTrencada.get(i)[1];
			}
			
			if(i <  olivaNegra.size()){
				botnegra = (String)olivaNegra.get(i)[0];
				litnegra = (Double)olivaNegra.get(i)[1];
				totalNegra += (Double)olivaNegra.get(i)[1];
			}
			
			filaReport = new FilaReport(
					botverda,
					(litverda != null)?numberDecimalFormat.format(litverda):"",
							bottrencada,
					(littrencada != null)?numberDecimalFormat.format(littrencada):"",
							botnegra,
					(litnegra != null)?numberDecimalFormat.format(litnegra):"");
			
			col.add(filaReport);
		}
		if (col.size() % 13 > 0 || col.size()==0) {
			int filesBuides = 13 - (col.size() % 13);
			for (int i = 0; i <=filesBuides; i++) {
				filaReport = new FilaReport(
						"",
						"",
						"",
						"",
						"",
						"");
				
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


	@SuppressWarnings("unused")
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




	public String getOlivaNegra() {
		return olivaNegra;
	}




	public void setOlivaNegra(String olivaNegra) {
		this.olivaNegra = olivaNegra;
	}




	public String getOlivaTrencada() {
		return olivaTrencada;
	}




	public void setOlivaTrencada(String olivaTrencada) {
		this.olivaTrencada = olivaTrencada;
	}




	public String getOlivaVerda() {
		return olivaVerda;
	}




	public void setOlivaVerda(String olivaVerda) {
		this.olivaVerda = olivaVerda;
	}




	public OliConsultaEjb getOliConsultaEjb() {
		return oliConsultaEjb;
	}

	
	
}