package es.caib.gestoli.logic.util;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.apache.log4j.Logger;
import org.springframework.context.support.MessageSourceAccessor;

import es.caib.gestoli.logic.model.Diposit;
import es.caib.gestoli.logic.model.Establiment;
import es.caib.gestoli.logic.model.Trasllat;


public class GeneraPdfVolante {
	
	private static Logger logger = Logger.getLogger(GeneraPdfVolante.class);
	
	private Integer tipusEstablimentTafona;
	private Integer tipusEstablimentEnvasadora;
	private Integer tipusEstablimentTafonaEnvasadora;
	private MessageSourceAccessor messageSourceAccessor;
	
	public GeneraPdfVolante(MessageSourceAccessor messageSourceAccessor, Integer tipusEstablimentTafona, Integer tipusEstablimentEnvasadora, Integer tipusEstablimentTafonaEnvasadora) {
		this.messageSourceAccessor = messageSourceAccessor;
		this.tipusEstablimentEnvasadora = tipusEstablimentEnvasadora;
		this.tipusEstablimentTafona = tipusEstablimentTafona;
		this.tipusEstablimentTafonaEnvasadora = tipusEstablimentTafonaEnvasadora;
	}


	/**
	 * Genera el array de bytes del volante de circulacion para un translado
	 * @param idT
	 * @return
	 */
	public byte[] generarPdfVolantCirculacio(Trasllat trasllat, String solE, String solD, String solENif, String solDNif, Establiment estEmisor, Establiment estDest, double quantitatTotalDesti, String partida) throws Exception{
   	
		SimpleDateFormat dateF = new SimpleDateFormat("dd/MM/yyyy");
		DecimalFormat numberDecimalFormat = new DecimalFormat("###,###,##0.00");

		// Compilar archivo
	    InputStream is = this.getClass().getClassLoader().getResourceAsStream("es/caib/gestoli/logic/resources/volantCirculacioOli.jrxml");
	    JasperReport report = JasperCompileManager.compileReport(is);

		// Data Source
		Collection col = getDataSourceVolant(trasllat);
		JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource( col );
		
		//Parametros
		HashMap params = new HashMap();
		params.put("resPeu",missatge("pdf.volantCirculacio.peu"));
		params.put("resImg","es/caib/gestoli/logic/resources/do.jpg");
		
		params.put("resHeVolantCirculacio",missatge("pdf.volantCirculacio.resHeVolantCirculacio"));
		
		params.put("resReferencia",missatge("pdf.volantCirculacio.resReferencia"));
		params.put("referencia",trasllat.getId().toString());
		
		params.put("resHeDadesEmisor",missatge("pdf.volantCirculacio.resHeDadesEmisor"));
		params.put("resHeDadesDest",missatge("pdf.volantCirculacio.resHeDadesDest"));
		params.put("resHeNomEntitat",missatge("pdf.volantCirculacio.resHeNomEntitat"));
		params.put("resHeRepresentant",missatge("pdf.volantCirculacio.resHeRepresentant"));
		params.put("resHeCIF",missatge("pdf.volantCirculacio.resHeCIF"));
		params.put("resHeDni",missatge("pdf.volantCirculacio.resHeNif"));
		params.put("resHeNumeroIns",missatge("pdf.volantCirculacio.resHeNumeroIns"));
		params.put("resHeDirecc",missatge("pdf.volantCirculacio.resHeDirecc"));
		params.put("resHeLocalitat",missatge("pdf.volantCirculacio.resHeLocalitat"));
		// importante: no eliminar las cadenas en blanco
		params.put("resHeInscrit",missatge("pdf.volantCirculacio.resHeInscrit")+
				"                                         "+missatge("pdf.volantCirculacio.resTafona")+"            "+missatge("pdf.volantCirculacio.resEnvasadora"));
		params.put("resHePartida",missatge("pdf.volantCirculacio.partida"));
//		Establiment estEmisor = trasllat.getEstablimentByTdiCodeor();
				
		String inscritEmisor="";
		String numeroInsEmisor="";
		if (estEmisor.getTipusEstabliment().getId().equals(tipusEstablimentTafona)){
			numeroInsEmisor="RB-"+estEmisor.getCodiRB();
			params.put("tafonaE","X");
			params.put("envasadoraE","");
		}else if (estEmisor.getTipusEstabliment().getId().equals(tipusEstablimentEnvasadora)){
			numeroInsEmisor="RC-"+estEmisor.getCodiRC();
			params.put("tafonaE","");
			params.put("envasadoraE","X");
		}else if (estEmisor.getTipusEstabliment().getId().equals(tipusEstablimentTafonaEnvasadora)){
			numeroInsEmisor="RB-"+estEmisor.getCodiRB() + " / RC-"+ estEmisor.getCodiRC();
			params.put("tafonaE","X");
			params.put("envasadoraE","X");
		}
		
		
		params.put("nomEntitatEmisor",estEmisor.getNom());
		params.put("representantEmisor",solE);
		params.put("cifEmisor",estEmisor.getCif());
		if (solENif != null) {
			params.put("dniEmisor", solENif);
		} else {
			params.put("dniEmisor", "");
		}
		params.put("numeroInsEmisor",numeroInsEmisor);
		if (estEmisor.getDireccio() != null) {
			params.put("direccEmisor", estEmisor.getDireccio());
		} else {
			params.put("direccEmisor", "");
		}
		
		String localitatE = "";
		if (estEmisor.getPoblacio() != null && !estEmisor.getPoblacio().equals("")) {
			localitatE = estEmisor.getPoblacio().getNom();
		}
		if (estEmisor.getCodiPostal() != null && !estEmisor.getCodiPostal().equals("")) {
			localitatE = estEmisor.getCodiPostal() + " " + localitatE;
		}
		params.put("localitatEmisor",localitatE);
			
		String numeroInsDest="";
		if (estDest.getTipusEstabliment().getId().equals(tipusEstablimentTafona)){
			numeroInsDest="RB-"+estDest.getCodiRB();
			params.put("tafonaD","X");
			params.put("envasadoraD","");
		}else if (estDest.getTipusEstabliment().getId().equals(tipusEstablimentEnvasadora)){
			numeroInsDest="RC-"+estDest.getCodiRC();
			params.put("tafonaD","");
			params.put("envasadoraD","X");
		}else if (estDest.getTipusEstabliment().getId().equals(tipusEstablimentTafonaEnvasadora)){
			numeroInsDest="RB-"+estDest.getCodiRB() + " / RC-"+estDest.getCodiRC();
			params.put("tafonaD","X");
			params.put("envasadoraD","X");
		}
		
		params.put("nomEntitatDest",estDest.getNom());
		params.put("cifDest",estDest.getCif());
		params.put("representantDest",solD);
		if (solDNif != null) {
			params.put("dniDest", solDNif);
		} else {
			params.put("dniDest", "");
		}
		params.put("numeroInsDest",numeroInsDest);
		if (estDest.getDireccio() != null) {
			params.put("direccDest", estDest.getDireccio());
		} else {
			params.put("direccDest", "");
		}
		
		String localitatD = "";
		if (estDest.getPoblacio() != null && !estDest.getPoblacio().equals(""))  {
			localitatD = estDest.getPoblacio().getNom();
		}
		if (estDest.getCodiPostal() != null && !estDest.getCodiPostal().equals("")) {
			localitatD = estDest.getCodiPostal() + " " + localitatD;
		}
		params.put("localitatDest",localitatD);
		
		params.put("resHeQuantitat",missatge("pdf.volantCirculacio.resHeQuantitat"));
		params.put("quantitat",numberDecimalFormat.format(quantitatTotalDesti));
		
		params.put("resHeDiposit",missatge("pdf.volantCirculacio.resHeDiposit"));
		params.put("resHeCapacitat",missatge("pdf.volantCirculacio.resHeCapacitat"));
		params.put("resHeAcidesa",missatge("pdf.volantCirculacio.resHeAcidesa"));
		params.put("resHeCategoria",missatge("pdf.volantCirculacio.resHeCategoria"));
		params.put("resHeCarrega",missatge("pdf.volantCirculacio.resHeCarrega"));		
		params.put("resHeDescarrega",missatge("pdf.volantCirculacio.resHeDescarrega"));
		
		
		params.put("resHeDataPrevista",missatge("pdf.volantCirculacio.resHeDataPrevista"));
		params.put("dataPrevista",dateF.format(trasllat.getData()));
		
		params.put("resHeFirmaResEmisor",missatge("pdf.volantCirculacio.resHeFirmaResEmisor"));
		params.put("dataResEmisor",missatge("pdf.volantCirculacio.dataResEmisor"));
		params.put("resHeFirmaResReceptor",missatge("pdf.volantCirculacio.resHeFirmaResReceptor"));
		params.put("dataResReceptor",missatge("pdf.volantCirculacio.dataResReceptor"));
		params.put("resHeFirmaEmisor",missatge("pdf.volantCirculacio.resHeFirmaEmisor"));
		params.put("resHeFirmaReceptor",missatge("pdf.volantCirculacio.resHeFirmaReceptor"));
		params.put("observacions",missatge("pdf.volantCirculacio.observacions"));
		params.put("partida", missatge("pdf.volantCirculacio.partida"));


		JasperPrint jasperPrint = JasperFillManager.fillReport( report, params, ds );

	    byte[] bytes = JasperExportManager.exportReportToPdf(jasperPrint);
	    
		return bytes;
		
	}
	
	
	
	public class FilaReport implements java.io.Serializable {
		
		public FilaReport(String diposit, String capacitat, String acidesa,
				String categoria, String partida) {
			super();
			this.diposit = diposit;
			this.capacitat = capacitat;
			this.acidesa = acidesa;
			this.categoria = categoria;
			this.partida = partida;
		}
		
		private String diposit;
		private String capacitat;
		private String acidesa;
		private String categoria;
		private String partida;
		

		public String getCapacitat() {
			return capacitat;
		}
		public void setCapacitat(String capacitat) {
			this.capacitat = capacitat;
		}
		public String getAcidesa() {
			return acidesa;
		}
		public void setAcidesa(String acidesa) {
			this.acidesa = acidesa;
		}
		public String getCategoria() {
			return categoria;
		}
		public void setCategoria(String categoria) {
			this.categoria = categoria;
		}
		public String getDiposit() {
			return diposit;
		}
		public void setDiposit(String diposit) {
			this.diposit = diposit;
		}
		public String getPartida() {
			return partida;
		}
		public void setPartida(String partida) {
			this.partida = partida;
		}
	}
	
	
	
	/**
	 * @param idT
	 * @return
	 * @throws Exception
	 */
	private Collection getDataSourceVolant(Trasllat trasllat) throws Exception{
		Collection col=new ArrayList();
		FilaReport filaReport;
		
		DecimalFormat numberDecimalFormat = new DecimalFormat("###,###,##0.00");
		
		Iterator ite = trasllat.getDiposits().iterator();
		while (ite.hasNext()){
			Diposit di = (Diposit)ite.next();
			
			filaReport = new FilaReport(
								di.getCodiAssignat(),
								(di.getVolumActual() != null)?numberDecimalFormat.format(di.getVolumActual().doubleValue()):null,
								(di.getAcidesa() != null)?numberDecimalFormat.format(di.getAcidesa().doubleValue()):null,
								(di.getPartidaOli() != null)?di.getPartidaOli().getCategoriaOli().getNom():null,
								(di.getPartidaOli() != null)?di.getPartidaOli().getNom():null);
			
			col.add(filaReport);
			
		}
		
		

		return col;
	}


	private String missatge(String clave) {
		String valor = getMessageSourceAccessor().getMessage(clave, new Locale("ca"));
		return valor;
	}

	
	
	public Integer getTipusEstablimentTafona() {
		return tipusEstablimentTafona;
	}



	public void setTipusEstablimentTafona(Integer tipusEstablimentTafona) {
		this.tipusEstablimentTafona = tipusEstablimentTafona;
	}



	public Integer getTipusEstablimentEnvasadora() {
		return tipusEstablimentEnvasadora;
	}



	public void setTipusEstablimentEnvasadora(Integer tipusEstablimentEnvasadora) {
		this.tipusEstablimentEnvasadora = tipusEstablimentEnvasadora;
	}



	public Integer getTipusEstablimentTafonaEnvasadora() {
		return tipusEstablimentTafonaEnvasadora;
	}



	public void setTipusEstablimentTafonaEnvasadora(
			Integer tipusEstablimentTafonaEnvasadora) {
		this.tipusEstablimentTafonaEnvasadora = tipusEstablimentTafonaEnvasadora;
	}


	public MessageSourceAccessor getMessageSourceAccessor() {
		return messageSourceAccessor;
	}


	public void setMessageSourceAccessor(MessageSourceAccessor messageSourceAccessor) {
		this.messageSourceAccessor = messageSourceAccessor;
	}
	
	
	
}