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
import es.caib.gestoli.logic.model.CategoriaOli;
import es.caib.gestoli.logic.model.Elaboracio;
import es.caib.gestoli.logic.model.EntradaDiposit;
import es.caib.gestoli.logic.model.EntradaLot;
import es.caib.gestoli.logic.model.PartidaOliva;
import es.caib.gestoli.logic.model.SortidaDiposit;
import es.caib.gestoli.logic.model.SortidaLot;
import es.caib.gestoli.logic.model.Trasllat;
import es.caib.gestoli.logic.model.Traza;
import es.caib.gestoli.logic.util.Constants;


/**
 * GenerarPdfDeclaracioMensual
 * @author obarnes
 */
public class GenerarPdfDeclaracioMensual extends AbstractPdfView {
	private static Logger logger = Logger.getLogger(GenerarPdfDeclaracioMensual.class);
	private OliConsultaEjb oliConsultaEjb;
	private OliInfraestructuraEjb oliInfraestructuraEjb;
	private HibernateTemplate hibernateTemplate;
	
    private String desqualificat;
	private String pendentQualificar;
	private String qualificat;
	
	private Double totalDO;
	private Double totalPendent;
	private Double totalNoDO;

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
		Collection entradesOliva = oliInfraestructuraEjb.findPartidesOlivaByEstablimentEntreDates(idEst,finicio,ffin,true);
		
		Double totalQuilosEntradaOliva = 0.0;
		
		for(Iterator it=entradesOliva.iterator(); it.hasNext();){
			PartidaOliva po = (PartidaOliva)it.next();
			if (!po.getEsOlivaDeTaula()) totalQuilosEntradaOliva += po.getTotalKilos();
		}
		
		Collection elaboracions = oliConsultaEjb.oliElaboratConsulta(finicio, ffin, idEst, true, true);
		
		
		Double totalElaboracioNoDO = 0.0;
		Double totalElaboracioPendent = 0.0;
		
		for(Iterator it=elaboracions.iterator(); it.hasNext();){
			Elaboracio e = (Elaboracio)it.next();
			
			if(e.getPartidaOli().getCategoriaOli().getId().equals(Constants.CATEGORIA_PENDENT)){
				totalElaboracioPendent += e.getLitres();
			} else {
				totalElaboracioNoDO += e.getLitres();
			}
		}
		
		
		Collection listaTrasllatEntrades = oliConsultaEjb.movimentsEntreEstablimentsEntradaConsulta(finicio, ffin, idEst);
		
		
		double trasllat_do = 0.0;
		double trasllat_pendent = 0.0;
		double trasllat_nodo = 0.0;
		
		for(Iterator it=listaTrasllatEntrades.iterator();it.hasNext();){
			Trasllat trasllat = (Trasllat)it.next();
			
			double quantitat = 0;
			
			if(trasllat.getEsDiposit()){
				if(trasllat.getEstablimentByTdiCodeor().getId().equals(idEst)){
					quantitat = trasllat.getQuantitatRetorn();
				} else {
					quantitat = trasllat.getQuantitatEnviament();
				}
			} else {
				quantitat = trasllat.getQuantitatEnviament();
			}
			
			for(Iterator it2 = trasllat.getTraza().getTrazasForTtrCodtrafill().iterator(); it2.hasNext();){
				Traza t2 = (Traza)it2.next();
				if (t2.getTipus().equals(Constants.CODI_TRAZA_TIPUS_ENTRADA_DIPOSIT)){
					
					oliConsultaEjb.setHibernateTemplate(getHibernateTemplate());
					EntradaDiposit ed = oliConsultaEjb.findEntradaDipositByTraza(t2.getId());
					
					System.out.println("QUANTITAT"+quantitat+" ED LITRES"+ed.getLitres());
					
					if(ed.getPartidaOli().getCategoriaOli().getId().equals(1)){ // 1 = no do
						trasllat_nodo += quantitat;
					} else if(ed.getPartidaOli().getCategoriaOli().getId().equals(2)){ // 2 = pendent
						trasllat_pendent += quantitat;
					} else { // 3 = do
						trasllat_do += quantitat;
					}
					break;
				}
			}
			
		}
		
		//SORTIDES
		Collection sortidesGranel = oliConsultaEjb.getSortidesDipositEntreFechasAndEstablecimiento(finicio, ffin, idEst, true);
		
		
		Double totalGranelSortidaDO = 0.0;
		Double totalGranelSortidaPendent = 0.0;
		Double totalGranelSortidaNoDO = 0.0;
		
		for(Iterator it=sortidesGranel.iterator(); it.hasNext();){
			SortidaDiposit sd = (SortidaDiposit)it.next();
			
			if (sd.getValid() && sd.getDipositBySdiCoddde() == null && sd.getLot() == null){ 
				System.out.println("Sortida: sdi=" + sd.getId() + ", lit=" + sd.getLitres() + " l.");
				if(sd.getCategoriaOli().getId().equals(Constants.CATEGORIA_DO)){
					totalGranelSortidaDO += sd.getLitres();
				} else if(sd.getCategoriaOli().getId().equals(Constants.CATEGORIA_PENDENT)){
					totalGranelSortidaPendent += sd.getLitres();
				} else {
					totalGranelSortidaNoDO += sd.getLitres();
				}
			}
		}
		
		Collection sortidesLot = oliConsultaEjb.getSortidesLotsEntreDadesAndEstabliment(finicio, ffin, idEst, true);
		
		
		Double totalLotSortidaDO = 0.0;
		Double totalLotSortidaNoDO = 0.0;
		
		for(Iterator it=sortidesLot.iterator(); it.hasNext();){
			SortidaLot sl = (SortidaLot)it.next();
			
			if(sl.getLot().getPartidaOli().getCategoriaOli().getId().equals(Constants.CATEGORIA_DO)){
				totalLotSortidaDO += sl.getVendaLitres();
			} else {
				totalLotSortidaNoDO += sl.getVendaLitres();
			}
		}
		
		// Descontarem les devolucions a les sortides (per a fer-ho bé s'haurien de contabilitzar com a entrades..., no?)
		Collection entradesLot = oliConsultaEjb.getEntradesLotsDevolucioEntreDadesAndEstabliment(finicio, ffin, idEst, true);
		
		
		for(Iterator it=entradesLot.iterator(); it.hasNext();){
			EntradaLot el = (EntradaLot)it.next();
			
			if (el.getEsDevolucio() != null && el.getEsDevolucio().booleanValue() == true) {
				if(el.getLot().getPartidaOli().getCategoriaOli().getId().equals(Constants.CATEGORIA_DO)){
					totalLotSortidaDO -= el.getLitres();
				} else {
					totalLotSortidaNoDO -= el.getLitres();
				}
			}
		}
		
		
		//EXISTENCIES DEL DARRER DIA DE MES
		List categorias = new ArrayList();
   		categorias.add(Integer.valueOf(desqualificat));
   		categorias.add(Integer.valueOf(qualificat));
   		categorias.add(Integer.valueOf(pendentQualificar));
   		
		// Cerca tots las diposits de un establecimiento no vacios y que pertenecen a unas determinadas categorias
	   	Collection existenciesGranel = oliConsultaEjb.findDipositsActiusNoVaciosByEstablecimientoCategoriasAndData(idEst, categorias.toArray(), ffin);
	   	
	   	// Cerca tots los lotes de un establecimiento no vacios y que pertenecen a unas determinadas categorias
	    Collection existenciesEmbotellat = oliConsultaEjb.findLotsNoVendidosByEstablecimientoCategoriasAndData(idEst, categorias.toArray(), ffin);
	    

	    Double totalEmbotellatDO = 0.0;
	    Double totalEmbotellatNoDO = 0.0;
	    
	    
	    for(Iterator it=existenciesEmbotellat.iterator(); it.hasNext();){
			//existencia[0] = id lot
			//existencia[1] = codi lot
			//existencia[2] = litres
			//existencia[3] = quilos
			//existencia[4] = categoria (objecte CategoriaOli)
	    	Object[] existencia = (Object[])it.next();
			
			CategoriaOli cat = (CategoriaOli)existencia[4];
			
			if(cat.getId().equals(Constants.CATEGORIA_DO)){
				totalEmbotellatDO += (Double)existencia[2];
			} else if(cat.getId().equals(Constants.CATEGORIA_NO_DO)){
				totalEmbotellatNoDO += (Double)existencia[2];
			}
	    }
	    
		// Compilar archivo
		logger.debug("2.- Leyendo el stream del jrxml");
	    InputStream is = this.getClass().getClassLoader().getResourceAsStream("es/caib/gestoli/logic/resources/declaracioMensual.jrxml");
		logger.debug("3.- Compilando el report");
	    JasperReport report = JasperCompileManager.compileReport(is);

		// Data Source
		logger.debug("4.- Asociando los datos");
		JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource( getDataSourceEntradaOliva(existenciesGranel) );
		
		//Parametros
		logger.debug("5.- Cargando parametros");
		HashMap params = new HashMap();
		
		//TEXTES FIXES
		
		//General
		params.put("tolido","Oli DO");
		params.put("tolinodo","Oli no DO");
		params.put("tolipendent","Oli pendent");
		
		
		//Capçalera
		params.put("timatgeDO","es/caib/gestoli/logic/resources/do.jpg");
		params.put("ttitol","DECLARACIÓ MENSUAL TAFONES + EMBOTELLADORES");
		params.put("tnomest"," NOM DE LA TAFONA: ");
		params.put("tmesdeclaracio"," DECLARACIÓ DEL MES DE: ");
		
		//Entrades
		params.put("tentrades"," ENTRADES");
		params.put("tentradesoliva"," ENTRADES D'OLIVA");
		params.put("tolielaborat"," OLI ELABORAT");
		params.put("tolielaborat_nodo"," A: Oli no DO");
		params.put("tolielaborat_do"," B: Oli destinat a DO (pendent)");
		params.put("tolielaborat_total"," Total d'oli elaborat: A + B=");
		
		//Entrades trasllat
		params.put("tentradestrasllat","ENTRADES TRASLLAT");
		params.put("tentradestrasllat_nodo","A: Oli no DO");
		params.put("tentradestrasllat_pendent","B: Oli pendent");
		params.put("tentradestrasllat_do","C: Oli DO");
		
		//Sortides
		params.put("tsortides"," SORTIDES D'OLI DE LES INSTAL·LACIONS");
		params.put("tsortidesgranel","GRANEL (litres)");
		params.put("tsortidesembotellat","EMBOTELLAT (litres)");
		
		//Existències
		params.put("texistencies"," EXISTÈNCIES del DARRER DIA DE MES");
		params.put("texistenciesgranel","GRANEL");
		params.put("texistenciesnumdip","Núm. diòsit");
		params.put("texistencieslitres","Litres");
		params.put("texistenciestotaldo","TOTAL DO");
		params.put("texistenciestotalpendent","TOTAL PENDENT");
		params.put("texistenciestotalnodo","TOTAL NO DO");
		params.put("texistenciesembotellat","EMBOTELLAT");
		params.put("texistenciesembotellatolido","Oli DO (litres)");
		params.put("texistenciesembotellatolinodo","Oli no DO (litres)");
		params.put("texistenciespeu","*= Sortides que han d'anar acompanyades del volant de circulació validat pel Consell Regulador");
		
		//Peu
		params.put("tpeudata"," Data:");
		params.put("tpeusignatura"," Signatura:");
		
		
		
		//DADES
		
		//Capçalera
		params.put("dnomest",nomEst);
		params.put("dmesdeclaracio",mesDeclaracio + " - " + anyDeclaracio);
		
		//Entrades
		params.put("dentradesoliva",numberDecimalFormat.format(totalQuilosEntradaOliva) + " Kilos");
		params.put("dolielaborat_nodo", numberDecimalFormat.format(totalElaboracioNoDO) + " Litres");
		params.put("dolielaborat_do", numberDecimalFormat.format(totalElaboracioPendent) + " Litres");
		params.put("dolielaborat_total", numberDecimalFormat.format((totalElaboracioNoDO + totalElaboracioPendent)) + " Litres");
		
		//Entrades trasllat
		params.put("ttrasllat_do", numberDecimalFormat.format(trasllat_do));
		params.put("ttrasllat_pendent",  numberDecimalFormat.format(trasllat_pendent));
		params.put("ttrasllat_nodo",  numberDecimalFormat.format(trasllat_nodo));
		
		//Sortides
		params.put("dsortidesgranelDO", numberDecimalFormat.format(totalGranelSortidaDO));
		params.put("dsortidesgranelpendent",  numberDecimalFormat.format(totalGranelSortidaPendent));
		params.put("dsortidesgranelnoDO",  numberDecimalFormat.format(totalGranelSortidaNoDO));
		
		params.put("dsortideslotDO", numberDecimalFormat.format(totalLotSortidaDO));
		params.put("dsortideslotnoDO",  numberDecimalFormat.format(totalLotSortidaNoDO));
		
		params.put("dtotalgranelDO",  numberDecimalFormat.format(totalDO));
		params.put("dtotalgranelPendent",  numberDecimalFormat.format(totalPendent));
		params.put("dtotalgranelNoDO",  numberDecimalFormat.format(totalNoDO));
		
		params.put("dtotalEmbotellatDO",  numberDecimalFormat.format(totalEmbotellatDO));
		params.put("dtotalEmbotellatNoDO",  numberDecimalFormat.format(totalEmbotellatNoDO));
		request.setAttribute("carregant", "");
		
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

		private String dipdo;
		private String litdo;
		private String dippendent;
		private String litpendent;
		private String dipnodo;
		private String litnodo;
		
		public FilaReport(String dipdo, String litdo, String dippendent,
				String litpendent, String dipnodo, String litnodo) {

			this.dipdo = dipdo;
			this.litdo = litdo;
			this.dippendent = dippendent;
			this.litpendent = litpendent;
			this.dipnodo = dipnodo;
			this.litnodo = litnodo;
			
		}

		public String getDipdo() {
			return dipdo;
		}

		public void setDipdo(String dipdo) {
			this.dipdo = dipdo;
		}

		public String getLitdo() {
			return litdo;
		}

		public void setLitdo(String litdo) {
			this.litdo = litdo;
		}

		public String getDippendent() {
			return dippendent;
		}

		public void setDippendent(String dippendent) {
			this.dippendent = dippendent;
		}

		public String getLitpendent() {
			return litpendent;
		}

		public void setLitpendent(String litpendent) {
			this.litpendent = litpendent;
		}

		public String getDipnodo() {
			return dipnodo;
		}

		public void setDipnodo(String dipnodo) {
			this.dipnodo = dipnodo;
		}

		public String getLitnodo() {
			return litnodo;
		}

		public void setLitnodo(String litnodo) {
			this.litnodo = litnodo;
		}
		
	}
	
	
	

	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Collection getDataSourceEntradaOliva(Collection existenciesGranel){
		
		//Desglosam les existencies per categoria
		List<Object[]> olido = new ArrayList<Object[]>();
		List<Object[]> olipendent = new ArrayList<Object[]>();
		List<Object[]> olinodo = new ArrayList<Object[]>();
		
		for(Iterator it=existenciesGranel.iterator(); it.hasNext();){
			//existencia[0] = id diposit
			//existencia[1] = codi dipòsit
			//existencia[2] = litres existencies
			//existencia[3] = kilos existencies
			//existencia[4] = categoria (Objecte CategoriaOli)
			//existencia[5] = olivicultor es propietari (camp Boolean)
			Object[] existencia = (Object[])it.next();
			
			Object[] o = new Object[2];
			o[0] = existencia[1];
			o[1] = existencia[2];
			
			CategoriaOli cat = (CategoriaOli)existencia[4];
			
			if(cat.getId().equals(Constants.CATEGORIA_DO)){
				olido.add(o);
			} else if(cat.getId().equals(Constants.CATEGORIA_PENDENT)){
				olipendent.add(o);
			} else if(cat.getId().equals(Constants.CATEGORIA_NO_DO)){
				olinodo.add(o);
			}
		}
		
		
		//Emplenam el resultat
		Collection col=new ArrayList();
		FilaReport filaReport;
		
		DecimalFormat numberDecimalFormat = new DecimalFormat("###,###,##0.00");
		
		//Inicialitzam totals
		totalDO = 0.0;
		totalPendent = 0.0;
		totalNoDO = 0.0;
		
		for(int i=0; i<olido.size() || i<olipendent.size() || i<olinodo.size(); i++){
			String dipdo = "";
			Double litdo = null;
			String dippendent = "";
			Double litpendent = null;
			String dipnodo = "";
			Double litnodo = null;
			
			if(i < olido.size()){
				dipdo = (String)olido.get(i)[0];
				litdo = (Double)olido.get(i)[1];
				totalDO += (Double)olido.get(i)[1];
			}
			
			if(i <  olipendent.size()){
				dippendent = (String)olipendent.get(i)[0];
				litpendent = (Double)olipendent.get(i)[1];
				totalPendent += (Double)olipendent.get(i)[1];
			}
			
			if(i <  olinodo.size()){
				dipnodo = (String)olinodo.get(i)[0];
				litnodo = (Double)olinodo.get(i)[1];
				totalNoDO += (Double)olinodo.get(i)[1];
			}
			
			filaReport = new FilaReport(
					dipdo,
					(litdo != null)?numberDecimalFormat.format(litdo):"",
					dippendent,
					(litpendent != null)?numberDecimalFormat.format(litpendent):"",
					dipnodo,
					(litnodo != null)?numberDecimalFormat.format(litnodo):"");
			
			col.add(filaReport);
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




	public String getDesqualificat() {
		return desqualificat;
	}




	public void setDesqualificat(String desqualificat) {
		this.desqualificat = desqualificat;
	}




	public String getPendentQualificar() {
		return pendentQualificar;
	}




	public void setPendentQualificar(String pendentQualificar) {
		this.pendentQualificar = pendentQualificar;
	}




	public String getQualificat() {
		return qualificat;
	}




	public void setQualificat(String qualificat) {
		this.qualificat = qualificat;
	}




	public OliConsultaEjb getOliConsultaEjb() {
		return oliConsultaEjb;
	}

	
	
}