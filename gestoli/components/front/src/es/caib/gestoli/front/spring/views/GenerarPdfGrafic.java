package es.caib.gestoli.front.spring.views;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;

import org.apache.log4j.Logger;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfWriter;

import es.caib.gestoli.front.util.Idioma;
import es.caib.gestoli.logic.interfaces.OliConsultaEjb;
import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;
import es.caib.gestoli.logic.model.Campanya;
import es.caib.gestoli.logic.model.Establiment;



public class GenerarPdfGrafic implements Controller {
	private static Logger logger = Logger.getLogger(GenerarPdfGrafic.class);
	
	private ReloadableResourceBundleMessageSource messageSource;
	private CookieLocaleResolver localeResolver;
	private OliConsultaEjb oliConsultaEjb;
	private OliInfraestructuraEjb oliInfraestructuraEjb;
	private Integer tipusEstablimentTafona;
	private Integer tipusEstablimentTafonaEnvasadora;
	private String desqualificat;
	private String qualificat;
	private HibernateTemplate hibernateTemplate;

	// Atención importante, para que el campo etiqueta tenga efecto en las gráficas hay que asociar
	// $F{etiqueta} al gráfico en el jrxml. Actualmente esta desactivado porque se usa el formato automatico
	// {0} - {1} ({2}) que es Nombre - Número (Porcentaje). Se deja el campo por si es necesario el cambio
	// rapido a otro formato distinto de etiqueta.
	public class DataSetChartPie{

		public DataSetChartPie(String key, String etiqueta, Double valor) {
			super();
			this.key = key;
			this.etiqueta = etiqueta;
			this.valor = valor;
		}
		
		private String key;
		private String etiqueta;
		private Double valor;
		
		public String getEtiqueta() {
			return etiqueta;
		}
		public void setEtiqueta(String etiqueta) {
			this.etiqueta = etiqueta;
		}
		public Double getValor() {
			return valor;
		}
		public void setValor(Double valor) {
			this.valor = valor;
		}
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
	}
	
	
	public class DataSetChartBarras{

		
		public DataSetChartBarras(String series1, String series2,
				String series3, String cat1, String cat2, String cat3,
				Double valor1, Double valor2, Double valor3, String etiq1,
				String etiq2, String etiq3) {
			super();
			this.series1 = series1;
			this.series2 = series2;
			this.series3 = series3;
			this.cat1 = cat1;
			this.cat2 = cat2;
			this.cat3 = cat3;
			this.valor1 = valor1;
			this.valor2 = valor2;
			this.valor3 = valor3;
			this.etiq1 = etiq1;
			this.etiq2 = etiq2;
			this.etiq3 = etiq3;
		}
		
		private String series1;
		private String series2;
		private String series3;
		private String cat1;
		private String cat2;
		private String cat3;
		private Double valor1;
		private Double valor2;
		private Double valor3;
		private String etiq1;
		private String etiq2;
		private String etiq3;
		
		public String getSeries1() {
			return series1;
		}
		public void setSeries1(String series1) {
			this.series1 = series1;
		}
		public String getSeries2() {
			return series2;
		}
		public void setSeries2(String series2) {
			this.series2 = series2;
		}
		public String getSeries3() {
			return series3;
		}
		public void setSeries3(String series3) {
			this.series3 = series3;
		}
		public String getCat1() {
			return cat1;
		}
		public void setCat1(String cat1) {
			this.cat1 = cat1;
		}
		public String getCat2() {
			return cat2;
		}
		public void setCat2(String cat2) {
			this.cat2 = cat2;
		}
		public String getCat3() {
			return cat3;
		}
		public void setCat3(String cat3) {
			this.cat3 = cat3;
		}
		public Double getValor1() {
			return valor1;
		}
		public void setValor1(Double valor1) {
			this.valor1 = valor1;
		}
		public Double getValor2() {
			return valor2;
		}
		public void setValor2(Double valor2) {
			this.valor2 = valor2;
		}
		public Double getValor3() {
			return valor3;
		}
		public void setValor3(Double valor3) {
			this.valor3 = valor3;
		}
		public String getEtiq1() {
			return etiq1;
		}
		public void setEtiq1(String etiq1) {
			this.etiq1 = etiq1;
		}
		public String getEtiq2() {
			return etiq2;
		}
		public void setEtiq2(String etiq2) {
			this.etiq2 = etiq2;
		}
		public String getEtiq3() {
			return etiq3;
		}
		public void setEtiq3(String etiq3) {
			this.etiq3 = etiq3;
		}
		
	}
	
	
	
	/**
	 * Exportación de datos en formato PDF
	 * @see SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.validation.BindException)
	 */
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {

		logger.debug("handleRequest ini");
		System.setProperty("java.awt.headless", "true");

		try {
			
			DecimalFormat numberDecimalFormat = new DecimalFormat("###,###,##0.00");
			
			ArrayList datosChart=null;
			InputStream inpPie=this.getClass().getClassLoader().getResourceAsStream("es/caib/gestoli/logic/resources/graficsSuperficie.jrxml");
			logger.debug("01.- Compilando el report pie");
			JasperReport reportPie = JasperCompileManager.compileReport(inpPie);
			
			InputStream inpBarras=this.getClass().getClassLoader().getResourceAsStream("es/caib/gestoli/logic/resources/graficBarras.jrxml");
			logger.debug("02.- Compilando el report barras");
			JasperReport reportBarras = JasperCompileManager.compileReport(inpBarras);
			
			HashMap params=null;
			
			
			logger.debug("03.- Parametros");
			
			Long campanyaId = new Long(0);
			if (request.getParameter("id")!=null && !request.getParameter("id").equals("")){
				campanyaId = new Long (request.getParameter("id"));  
			}
			
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date fi=null,ff=null;
			
			try{
				if (request.getParameter("dataInici")!=null && !request.getParameter("dataInici").equals("")){
					fi=sdf.parse(request.getParameter("dataInici"));
				}
			}catch (Exception e) {
				fi=Calendar.getInstance().getTime();
			}
			
			try{
				if (request.getParameter("dataFi")!=null && !request.getParameter("dataFi").equals("")){
					ff=sdf.parse(request.getParameter("dataFi"));
				}
			}catch (Exception e) {
				ff=Calendar.getInstance().getTime();
			}
			
			
			logger.debug("04.- Report superficie tipo oliva");

			byte[] bytes;
			ArrayList arraysBytes = new ArrayList();
			datosChart = new ArrayList();
			params = new HashMap();
			
			params.put("titulo", missatge("consulta.altresConsultes.grafics.graficSuperficie.titol", request));
			
			oliConsultaEjb.setHibernateTemplate(getHibernateTemplate());
			ArrayList sumasSuperficie = new ArrayList(oliConsultaEjb.getSuperficiesVarietatsDO(campanyaId));
			
			for (int z=0; z<sumasSuperficie.size();z++){
				Object [] o = (Object[]) sumasSuperficie.get(z);
				String key=(String)o[0];
				Double valor=(Double)o[1];
				datosChart.add(new DataSetChartPie(key,key+" ("+numberDecimalFormat.format(valor)+" HA.)",valor));
			}
			

			JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource( datosChart );			
			
			// Rellenando el report
			logger.debug("05.- Cargando los datos al report");
			JasperPrint jasperPrint = JasperFillManager.fillReport(reportPie, params, ds);
			bytes = JasperExportManager.exportReportToPdf(jasperPrint);
			arraysBytes.add(bytes);
			
			logger.debug("06.- Report numero oliveras tipo oliva");
			
			datosChart = new ArrayList();
			params = new HashMap();
			
			
			params.put("titulo", missatge("consulta.altresConsultes.grafics.graficNumOliv.titol", request));
			
			oliConsultaEjb.setHibernateTemplate(getHibernateTemplate());
			ArrayList sumasNumOliv = new ArrayList(oliConsultaEjb.getNumeroArbresVarietatsDO(campanyaId));
			
			numberDecimalFormat = new DecimalFormat("###,###,##0.##");
			
			for (int z=0; z<sumasNumOliv.size();z++){
				Object [] o = (Object[]) sumasNumOliv.get(z);
				String key=(String)o[0];
				Long valor=(Long)o[1];
				datosChart.add(new DataSetChartPie(key,key+" ("+numberDecimalFormat.format(valor.longValue())+")",new Double(String.valueOf(valor.longValue()))));
			}
			
			ds = new JRBeanCollectionDataSource( datosChart );
			
			// Rellenando el report
			logger.debug("07.- Cargando los datos al report");
			jasperPrint = JasperFillManager.fillReport(reportPie, params, ds);
			bytes = JasperExportManager.exportReportToPdf(jasperPrint);
			arraysBytes.add(bytes);
			
			
			logger.debug("08.- Report superficie fincas");
			
			datosChart = new ArrayList();
			params = new HashMap();
			
			params.put("titulo", missatge("consulta.altresConsultes.grafics.graficSuperfFinques.titol", request));

			String menos1=missatge("consulta.altresConsultes.grafics.graficSuperfFinques.menos1",request);
			oliConsultaEjb.setHibernateTemplate(getHibernateTemplate());
			Double menos1v=oliConsultaEjb.getSuperfFincasDO(campanyaId, new Double(0), new Double(1));
			datosChart.add(new DataSetChartPie(menos1,menos1+" ("+numberDecimalFormat.format(menos1v)+" HA.)",menos1v));
			
			String de1a5=missatge("consulta.altresConsultes.grafics.graficSuperfFinques.de1a5",request);
			oliConsultaEjb.setHibernateTemplate(getHibernateTemplate());
			Double de1a5v=oliConsultaEjb.getSuperfFincasDO(campanyaId, new Double(1), new Double(5));
			datosChart.add(new DataSetChartPie(de1a5,de1a5+" ("+numberDecimalFormat.format(de1a5v)+" HA.)",de1a5v));
			
			String de5a10=missatge("consulta.altresConsultes.grafics.graficSuperfFinques.de5a10",request);
			oliConsultaEjb.setHibernateTemplate(getHibernateTemplate());
			Double de5a10v=oliConsultaEjb.getSuperfFincasDO(campanyaId, new Double(5), new Double(10));
			datosChart.add(new DataSetChartPie(de5a10,de5a10+" ("+numberDecimalFormat.format(de5a10v)+" HA.)",de5a10v));
			
			String de10a20=missatge("consulta.altresConsultes.grafics.graficSuperfFinques.de10a20",request);
			oliConsultaEjb.setHibernateTemplate(getHibernateTemplate());
			Double de10a20v=oliConsultaEjb.getSuperfFincasDO(campanyaId, new Double(10), new Double(20));
			datosChart.add(new DataSetChartPie(de10a20,de10a20+" ("+numberDecimalFormat.format(de10a20v)+" HA.)",de10a20v));
			
			String mas20=missatge("consulta.altresConsultes.grafics.graficSuperfFinques.mas20",request);
			oliConsultaEjb.setHibernateTemplate(getHibernateTemplate());
			Double mas20v=oliConsultaEjb.getSuperfFincasDO(campanyaId, new Double(20), new Double(Double.MAX_VALUE));
			datosChart.add(new DataSetChartPie(mas20,mas20+" ("+numberDecimalFormat.format(mas20v)+" HA.)",mas20v));
			

			ds = new JRBeanCollectionDataSource( datosChart );
			
			// Rellenando el report
			logger.debug("09.- Cargando los datos al report");
			jasperPrint = JasperFillManager.fillReport(reportPie, params, ds);
			bytes = JasperExportManager.exportReportToPdf(jasperPrint);		
			arraysBytes.add(bytes);
			
			
			logger.debug("10.- Report produccion por meses");

			oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
			Campanya cam = oliInfraestructuraEjb.campanyaAmbId(new Integer(campanyaId.intValue()));
			Date iniciCampanya = cam.getData();
			SimpleDateFormat mesFormat = new SimpleDateFormat("MM");
			int moduloInicioMes = Integer.parseInt(mesFormat.format(iniciCampanya));
			
        	List tipos = new ArrayList();
        	tipos.add(tipusEstablimentTafona);
        	tipos.add(tipusEstablimentTafonaEnvasadora);
			
        	oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
			Collection tafones = oliInfraestructuraEjb.establimentCercaTotsActivosByTipos(campanyaId, tipos.toArray());
			
			String [] meses = {missatge("consulta.altresConsultes.grafics.graficProduccio.mes.enero",request),
					missatge("consulta.altresConsultes.grafics.graficProduccio.mes.febrero",request),
					missatge("consulta.altresConsultes.grafics.graficProduccio.mes.marzo",request),
					missatge("consulta.altresConsultes.grafics.graficProduccio.mes.abril",request),
					missatge("consulta.altresConsultes.grafics.graficProduccio.mes.mayo",request),
					missatge("consulta.altresConsultes.grafics.graficProduccio.mes.junio",request),
					missatge("consulta.altresConsultes.grafics.graficProduccio.mes.julio",request),
					missatge("consulta.altresConsultes.grafics.graficProduccio.mes.agosto",request),
					missatge("consulta.altresConsultes.grafics.graficProduccio.mes.septiembre",request),
					missatge("consulta.altresConsultes.grafics.graficProduccio.mes.octubre",request),
					missatge("consulta.altresConsultes.grafics.graficProduccio.mes.noviembre",request),
					missatge("consulta.altresConsultes.grafics.graficProduccio.mes.diciembre",request)
			};
			
			
			double [] datosProdTotal= new double [12];
			for (int i=0; i<12; i++){
				datosProdTotal[i]=0;
			}
			
			Iterator tafonesIte = tafones.iterator();

			while (tafonesIte.hasNext()){
				
				datosChart = new ArrayList();
				params = new HashMap();
				
				String ejeX=missatge("consulta.altresConsultes.grafics.graficProduccio.eje.x",request);
				String ejeY=missatge("consulta.altresConsultes.grafics.graficProduccio.eje.y",request);
				String semiTitulo=missatge("consulta.altresConsultes.grafics.graficProduccio.semiTitulo",request);
						
				params.put("ejeX1", ejeX);
				params.put("ejeX2", ejeX);
				params.put("ejeX3", ejeX);
				params.put("ejeY1", ejeY);
				params.put("ejeY2", ejeY);
				params.put("ejeY3", ejeY);
				
				Long [] ids = new Long[3];
				
				for (int i=0; i<3; i++){
					if (tafonesIte.hasNext()){
						Establiment taf = (Establiment)tafonesIte.next(); 
						ids[i]=taf.getId();
						params.put("titulo"+(i+1), semiTitulo+" "+taf.getNom());
					}else{
						ids[i]=null;
						params.put("titulo"+(i+1), "");
					}
				}
				
				
				Double [][] datos = new Double[3][12];
				
				for (int j=0; j<3; j++){
					if (ids[j]!=null){
						Iterator datosElemento = oliConsultaEjb.getTotalOliElaboratTafonaPerMes(campanyaId, ids[j]).iterator();
						int x=0;
						while (datosElemento.hasNext()){
							datos[j][x]=(Double)datosElemento.next();
							datosProdTotal[x]+=datos[j][x].doubleValue();
							x++;
						}
						
					}else{
						for (int e=0; e<12;e++){
							datos[j][e]=null;
						}
					}

				}
	
				for (int m=0; m<12; m++){
					datosChart.add(new DataSetChartBarras("s1","s2","s3",meses[(m+moduloInicioMes-1)%12],meses[(m+moduloInicioMes-1)%12],meses[(m+moduloInicioMes-1)%12],datos[0][(m+moduloInicioMes-1)%12],datos[1][(m+moduloInicioMes-1)%12], datos[2][(m+moduloInicioMes-1)%12],"","",""));
				}

				ds = new JRBeanCollectionDataSource( datosChart );
				
				// Rellenando el report
				logger.debug("11.- Cargando los datos al report");
				jasperPrint = JasperFillManager.fillReport(reportBarras, params, ds);
				bytes = JasperExportManager.exportReportToPdf(jasperPrint);		
				arraysBytes.add(bytes);

			}
			
			
			logger.debug("12.- Grafic de barres de producció total");
			
			datosChart = new ArrayList();
			params = new HashMap();
			
			String ejeX=missatge("consulta.altresConsultes.grafics.graficProduccio.eje.x",request);
			String ejeY=missatge("consulta.altresConsultes.grafics.graficProduccio.eje.y",request);
					
			params.put("ejeX1", ejeX);
			params.put("ejeY1", ejeY);

			params.put("titulo1", missatge("consulta.altresConsultes.grafics.graficProduccio.tituloTotalProd",request));
			params.put("titulo2", "");
			params.put("titulo3", "");
			
			for (int m=0; m<12; m++){
				datosChart.add(new DataSetChartBarras("s1","s2","s3",meses[(m+moduloInicioMes-1)%12],"","",new Double(datosProdTotal[(m+moduloInicioMes-1)%12]),null,null,"","",""));
			}
			
			ds = new JRBeanCollectionDataSource( datosChart );
			
			// Rellenando el report
			logger.debug("13.- Cargando los datos al report");
			jasperPrint = JasperFillManager.fillReport(reportBarras, params, ds);
			bytes = JasperExportManager.exportReportToPdf(jasperPrint);		
			arraysBytes.add(bytes);
			
			
			
			logger.debug("14.- Report quantitat oli comercialitzat DO/No DO");
			
			datosChart = new ArrayList();
			params = new HashMap();
			
        	List lDesqualificats = new ArrayList();
        	lDesqualificats.add(Integer.valueOf(desqualificat));
        	
        	List lQualificats = new ArrayList();
        	lQualificats.add(Integer.valueOf(qualificat));
			
			params.put("titulo", missatge("consulta.altresConsultes.grafics.graficComercialitzat.titol", request));

			String etiqOliDO=missatge("consulta.altresConsultes.grafics.graficComercialitzat.DO",request);
			oliConsultaEjb.setHibernateTemplate(getHibernateTemplate());
			Double litresOliDo=oliConsultaEjb.getTotalOliComercialitzatByCategoriasConsulta(campanyaId,fi,ff,lQualificats.toArray(),campanyaId);
			datosChart.add(new DataSetChartPie(etiqOliDO,etiqOliDO+" ("+numberDecimalFormat.format(litresOliDo)+" l.)",litresOliDo));
			
			String etiqOliNoDO=missatge("consulta.altresConsultes.grafics.graficComercialitzat.NoDO",request);
			oliConsultaEjb.setHibernateTemplate(getHibernateTemplate());
			Double litresOliNoDo=oliConsultaEjb.getTotalOliComercialitzatByCategoriasConsulta(campanyaId,fi,ff,lDesqualificats.toArray(),campanyaId);
			datosChart.add(new DataSetChartPie(etiqOliNoDO,etiqOliNoDO+" ("+numberDecimalFormat.format(litresOliNoDo)+" l.)",litresOliNoDo));

			ds = new JRBeanCollectionDataSource( datosChart );
			
			// Rellenando el report
			logger.debug("15.- Cargando los datos al report");
			jasperPrint = JasperFillManager.fillReport(reportPie, params, ds);
			bytes = JasperExportManager.exportReportToPdf(jasperPrint);		
			arraysBytes.add(bytes);
			

			response.setContentType("application/pdf");
			response.setHeader("Cache-Control", "store");
			response.setHeader("Pragma", "cache");            
			response.setHeader("Content-Disposition", "attachment; filename=grafics.pdf");
			
			ArrayList readers = new ArrayList();
			Iterator paginasIte = arraysBytes.iterator();
			while (paginasIte.hasNext()){
				readers.add(new PdfReader((byte[])paginasIte.next()));
			}
			
			Document document = new Document();
			ByteArrayOutputStream outCopy = new ByteArrayOutputStream();
			PdfWriter writer = PdfWriter.getInstance(document, outCopy);
			document.open();
			PdfContentByte cb = writer.getDirectContent();
			
			PdfImportedPage page;
			
			Iterator readersIte = readers.iterator();
			while (readersIte.hasNext()){
				PdfReader r = (PdfReader)readersIte.next();
				document.newPage();
				page = writer.getImportedPage(r, 1);
				cb.addTemplate(page,0,0);
			}
			
			outCopy.flush();
			document.close();
			outCopy.close();
			
			ServletOutputStream out = response.getOutputStream();
			bytes = outCopy.toByteArray();
			for (int z=0; z<bytes.length;z++){
				out.write(bytes[z]);
			}

			out.close();
			
			logger.debug("handleRequest fin");
		} catch (Exception ex) {
			logger.error("handleRequest failed. EXCEPTION", ex);
			throw new ServletException(ex);
		}

		return null;
	}

	private String missatge(String clave, HttpServletRequest request) {
		String valor = getMessageSource().getMessage(clave, null, Idioma.getLocale(request));
		return valor;
	}

	/**
	 * Inyección de la dependencia oliConsultaEjb
	 * @param oliConsultaEjb La clase a inyectar.
	 */
	public void setOliConsultaEjb(OliConsultaEjb oliConsultaEjb) {
		this.oliConsultaEjb = oliConsultaEjb;
	}


	/**
	 * Metodo 'getResourceBundle'
	 * @return el resourceBundle
	 */
	public ReloadableResourceBundleMessageSource getMessageSource() {
		return messageSource;
	}


	/**
	 * Metodo 'setResourceBundle'
	 * @param resourceBundle el resourceBundle a modificar
	 */
	public void setMessageSource(
			ReloadableResourceBundleMessageSource resourceBundle) {
		this.messageSource = resourceBundle;
	}


	/**
	 * Metodo 'getLocaleResolver'
	 * @return el localeResolver
	 */
	public CookieLocaleResolver getLocaleResolver() {
		return localeResolver;
	}


	/**
	 * Metodo 'setLocaleResolver'
	 * @param localeResolver el localeResolver a modificar
	 */
	public void setLocaleResolver(CookieLocaleResolver localeResolver) {
		this.localeResolver = localeResolver;
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

	public void setOliInfraestructuraEjb(OliInfraestructuraEjb oliInfraestructuraEjb) {
		this.oliInfraestructuraEjb = oliInfraestructuraEjb;
	}

	public void setTipusEstablimentTafona(Integer tipusEstablimentTafona) {
		this.tipusEstablimentTafona = tipusEstablimentTafona;
	}

	public void setTipusEstablimentTafonaEnvasadora(
			Integer tipusEstablimentTafonaEnvasadora) {
		this.tipusEstablimentTafonaEnvasadora = tipusEstablimentTafonaEnvasadora;
	}

	public void setDesqualificat(String desqualificat) {
		this.desqualificat = desqualificat;
	}

	public void setQualificat(String qualificat) {
		this.qualificat = qualificat;
	}



}