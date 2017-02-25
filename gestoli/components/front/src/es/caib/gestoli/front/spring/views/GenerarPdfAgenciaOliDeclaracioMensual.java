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
import es.caib.gestoli.logic.model.Campanya;
import es.caib.gestoli.logic.model.CategoriaOli;
import es.caib.gestoli.logic.model.Diposit;
import es.caib.gestoli.logic.model.Elaboracio;
import es.caib.gestoli.logic.model.EntradaDiposit;
import es.caib.gestoli.logic.model.EntradaLot;
import es.caib.gestoli.logic.model.Establiment;
import es.caib.gestoli.logic.model.PartidaOliva;
import es.caib.gestoli.logic.model.SortidaDiposit;
import es.caib.gestoli.logic.model.SortidaLot;
import es.caib.gestoli.logic.model.SortidaOrujo;
import es.caib.gestoli.logic.model.Traza;
import es.caib.gestoli.logic.util.Constants;


/**
 * GenerarPdfDeclaracioMensual
 * @author obarnes
 */
public class GenerarPdfAgenciaOliDeclaracioMensual extends AbstractPdfView {
	private static Logger logger = Logger.getLogger(GenerarPdfAgenciaOliDeclaracioMensual.class);
	private OliConsultaEjb oliConsultaEjb;
	private OliInfraestructuraEjb oliInfraestructuraEjb;
	private HibernateTemplate hibernateTemplate;
	
    private String desqualificat;
	private String pendentQualificar;
	private String qualificat;
	
	private String campanyaSessionKey;

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
		Long idEst = new Long(idE);
		Long campanyaId = (Long)request.getSession().getAttribute(campanyaSessionKey);
		
		DecimalFormat numberDecimalFormat = new DecimalFormat("###,###,##0.00");
		
		oliInfraestructuraEjb.setHibernateTemplate(getHibernateTemplate());
		oliConsultaEjb.setHibernateTemplate(getHibernateTemplate());
		
		//CAMPANYA
		Campanya campanya = oliInfraestructuraEjb.campanyaAmbId(campanyaId.intValue());
    	
		//ESTABLIMENT
		Establiment establiment = oliInfraestructuraEjb.establimentAmbId(idEst);
		
		//CATEGORIES
		List categorias = new ArrayList();
   		categorias.add(Integer.valueOf(desqualificat));
   		categorias.add(Integer.valueOf(qualificat));
   		categorias.add(Integer.valueOf(pendentQualificar));
		
		//DATES
		SimpleDateFormat dateF = new SimpleDateFormat("dd/MM/yyyy");
		Date finicio = null;
		Date ffin = null;
		Date diaAnterior = null;
		String diaSignatura = "";
		String mesSignatura = "";
		String anySignatura = "";
		
		String mesDeclaracio = "";
		String anyDeclaracio = "";
		
		boolean primerMescampanya = false;
		
		try {
		    finicio = dateF.parse(fi);
		    ffin = dateF.parse(ff);
		    
		    Calendar mesDeclaracioCal = Calendar.getInstance();
		    mesDeclaracioCal.setTime(finicio);
		    
		    anyDeclaracio = String.valueOf(mesDeclaracioCal.get(Calendar.YEAR));
		    
		    switch(mesDeclaracioCal.get(Calendar.MONTH)){
		    	case 0: mesDeclaracio = "Enero"; break;
		    	case 1: mesDeclaracio = "Febrero"; break;
		    	case 2: mesDeclaracio = "Marzo"; break;
		    	case 3: mesDeclaracio = "Abril"; break;
		    	case 4: mesDeclaracio = "Mayo"; break;
		    	case 5: mesDeclaracio = "Junio"; break;
		    	case 6: mesDeclaracio = "Julio"; break;
		    	case 7: mesDeclaracio = "Agosto"; break;
		    	case 8: mesDeclaracio = "Septiembre"; break;
		    	case 9: mesDeclaracio = "Octubre"; break;
		    	case 10: mesDeclaracio = "Noviembre"; break;
		    	case 11: mesDeclaracio = "Diciembre"; break;
		    }
		    
		    mesDeclaracioCal.add(Calendar.DATE, -1);
		    diaAnterior = mesDeclaracioCal.getTime(); 
		    	
		    //Primer més de campanya...
		    Calendar mesIniciCampanyaCal = Calendar.getInstance();
		    mesIniciCampanyaCal.setTime(campanya.getData());
	   		if (mesDeclaracioCal.get(Calendar.MONTH) == mesIniciCampanyaCal.get(Calendar.MONTH) ||
	   				(mesIniciCampanyaCal.get(Calendar.DAY_OF_MONTH) > 15 && mesDeclaracioCal.get(Calendar.MONTH) == mesIniciCampanyaCal.get(Calendar.MONTH) + 1)){
	   			primerMescampanya = true;
	   		}
	   			
		    Calendar mesSignaturaCal = Calendar.getInstance();
		    mesSignaturaCal.setTime(ffin);
		    
		    anySignatura = String.valueOf(mesSignaturaCal.get(Calendar.YEAR));
		    
		    switch(mesSignaturaCal.get(Calendar.MONTH)){
		    	case 0: mesSignatura = "Enero"; break;
		    	case 1: mesSignatura = "Febrero"; break;
		    	case 2: mesSignatura = "Marzo"; break;
		    	case 3: mesSignatura = "Abril"; break;
		    	case 4: mesSignatura = "Mayo"; break;
		    	case 5: mesSignatura = "Junio"; break;
		    	case 6: mesSignatura = "Julio"; break;
		    	case 7: mesSignatura = "Agosto"; break;
		    	case 8: mesSignatura = "Septiembre"; break;
		    	case 9: mesSignatura = "Octubre"; break;
		    	case 10: mesSignatura = "Noviembre"; break;
		    	case 11: mesSignatura = "Diciembre"; break;
		    }
		    diaSignatura = String.valueOf(mesSignaturaCal.get(Calendar.DAY_OF_MONTH));
		    
		} catch (ParseException ex) {
		    finicio=Calendar.getInstance().getTime();
		    ffin=Calendar.getInstance().getTime();
		    diaAnterior=Calendar.getInstance().getTime();
		}
		
		final Boolean NOMES_DIPOSITS_ENVASADORA = true;
		final Boolean SENSE_DIPOSITS_ENVASADORA = false;
		final Boolean INDIFERENT_DIPOSITS_ENVASADORA = null;
		
		// VARIABLES
		
		// Variables d'existències
		Double totalKgIniciCampanya = 0.0;
		Double totalKgIniciMes = 0.0;
		Double totalKgFinalMes = 0.0;
		Double totalGranelKgIniciMesVE = 0.0;
	    Double totalEnvasatKgIniciMesVE = 0.0;
	    Double totalGranelKgIniciMesV = 0.0;
	    Double totalEnvasatKgIniciMesV = 0.0;
	    Double totalGranelKgFinalMesVE = 0.0;
	    Double totalEnvasatKgFinalMesVE = 0.0;
	    Double totalGranelKgFinalMesV = 0.0;
	    Double totalEnvasatKgFinalMesV = 0.0;
	    // Variables oliva
	    Double totalQuilosEntradaOliva = 0.0;
	    Double kilosOlivaElaboracions = 0.0;
	    // Variables orujo
	    Double kilosSortidaOrujo = 0.0;
		// Variables Oli envasadora
		Double kilosOliEnvasadoraVE = 0.0;
	   	Double kilosOliEnvasadoraV = 0.0;
	   	Double kilosOliDevolucioV = 0.0;
	   	Double kilosOliDevolucioVE = 0.0;
	   	// Variables TAFONA
		Double totalKilosElaboracio = 0.0;
		Double kilosEntradesTrasllats = 0.0;
		Double kilosEntradesDeEnvasadora = 0.0;
		Double kilosOliEmbotellat = 0.0;
		Double kilosSortidesVentesGranel = 0.0;
		Double kilosSortidesPerdues = 0.0;
		// Variables ENVASADORA
	   	Double sortidesVentaGranelVE = 0.0;
		Double sortidesEnvasatVE = 0.0;
		Double sortidesEnvasatExportacioVE = 0.0;
		Double sortidesVentaGranelV = 0.0;
		Double sortidesEnvasatV = 0.0;
		Double sortidesEnvasatExportacioV = 0.0;
		Double sortidesPerduaGranelVE = 0.0;
		Double sortidesPerduaGranelV = 0.0;
		Double sortidesPerduaEnvasatVE = 0.0;
		Double sortidesPerduaEnvasatV = 0.0;
		
		// EXISTENCIES + OLIVA
		// ------------------------------------------------------------------------------------------------
		// Primer dia de Campanya
		if (primerMescampanya) {
			// Cerca tots las diposits de un establecimiento no vacios y que pertenecen a unas determinadas categorias
		   	Calendar iniciCampanyaCal = Calendar.getInstance();
		   	iniciCampanyaCal.setTime(campanya.getData());
		   	iniciCampanyaCal.add(Calendar.DATE, -1);
		   	Collection existenciesGranelPrimerDiaCampanya = oliConsultaEjb.findDipositsActiusNoVaciosByEstablecimientoCategoriasAndData(idEst, categorias.toArray(), iniciCampanyaCal.getTime(), SENSE_DIPOSITS_ENVASADORA);
		
		    for(Iterator it=existenciesGranelPrimerDiaCampanya.iterator(); it.hasNext();){
		    	Object[] existencia = (Object[])it.next(); //existencia[3] = quilos
		    	totalKgIniciCampanya += (Double)existencia[3];
		    }
		}
		
		if (!establiment.getTipusEstabliment().equals(Constants.CODI_ENVASADORA)){
			//EXISTENCIES PRIMER DIA MES TAFONA
			// Cerca tots las diposits de un establecimiento no vacios y que pertenecen a unas determinadas categorias
		   	Collection existenciesGranelPrimerDiaMes = oliConsultaEjb.findDipositsActiusNoVaciosByEstablecimientoCategoriasAndData(idEst, categorias.toArray(), diaAnterior, SENSE_DIPOSITS_ENVASADORA);
		    for(Iterator it=existenciesGranelPrimerDiaMes.iterator(); it.hasNext();){
		    	Object[] existencia = (Object[])it.next();
		    	totalKgIniciMes += (Double)existencia[3]; //existencia[3] = quilos
		    }
		    
		    //EXISTENCIES DARRER DIA MES TAFONA
		    // Cerca tots las diposits de un establecimiento no vacios y que pertenecen a unas determinadas categorias
		   	Collection existenciesGranelDarrerDiaMes = oliConsultaEjb.findDipositsActiusNoVaciosByEstablecimientoCategoriasAndData(idEst, categorias.toArray(), ffin, SENSE_DIPOSITS_ENVASADORA);
		    for(Iterator it=existenciesGranelDarrerDiaMes.iterator(); it.hasNext();){
		    	Object[] existencia = (Object[])it.next();
		    	totalKgFinalMes += (Double)existencia[3]; //existencia[3] = quilos
		    }
		    
		    //OLIVA
		    //Entrades oliva
			Collection entradesOliva = oliInfraestructuraEjb.findPartidesOlivaByEstablimentEntreDates(idEst,finicio,ffin,true);
			for(Iterator it=entradesOliva.iterator(); it.hasNext();){
				PartidaOliva po = (PartidaOliva)it.next();
				totalQuilosEntradaOliva += po.getTotalKilos();
			}
			
			//Entrades per elaboració --> oli produït
			Collection elaboracions = oliConsultaEjb.oliElaboratConsulta(finicio, ffin, idEst, true, true);
			for(Iterator it=elaboracions.iterator(); it.hasNext();){
				Elaboracio e = (Elaboracio)it.next();
				kilosOlivaElaboracions += e.getTotalKilos();
				totalKilosElaboracio += e.getLitres() * 0.916; //multipliquem per 0.916 per passar ho a kilos
			}
			
			//Entrades per trasllats
			Collection entradesTrasllats = oliConsultaEjb.findEntradesDipositTrasllatByEstablimentAndDates(finicio, ffin, idEst);
			for(Iterator it=entradesTrasllats.iterator(); it.hasNext();){
				EntradaDiposit ed = (EntradaDiposit)it.next();
				if(ed != null)
					kilosEntradesTrasllats += ed.getLitres() * 0.916; //multipliquem per 0.916 per passar ho a kilos
			}
			logger.info("Entrades trasllat: " + kilosEntradesTrasllats + "kg");
			
			//Sortides orujo
			Collection sortidesOrujo = oliConsultaEjb.sortidaOrujoConsulta(finicio, ffin, idEst, new Boolean(true));
			for(Iterator it=sortidesOrujo.iterator(); it.hasNext();){
				SortidaOrujo so = (SortidaOrujo)it.next();
				kilosSortidaOrujo += so.getKilos();
			}
		}
		
		if (!establiment.getTipusEstabliment().equals(Constants.CODI_TAFONA)){
			//EXISTENCIES PRIMER DIA MES ENVASADORA
		   	Collection existenciesGranelPrimerDiaMesEnvasadora = oliConsultaEjb.findDipositsActiusNoVaciosByEstablecimientoCategoriasAndData(idEst, categorias.toArray(), diaAnterior, NOMES_DIPOSITS_ENVASADORA);
		    Collection existenciesEmbotellatPrimerDiaMes = oliConsultaEjb.findLotsNoVendidosByEstablecimientoCategoriasAndData(idEst, categorias.toArray(), diaAnterior);
		    
		    for(Iterator it=existenciesGranelPrimerDiaMesEnvasadora.iterator(); it.hasNext();){
		    	Object[] existencia = (Object[])it.next();
		    	if(((CategoriaOli)existencia[4]).getId().equals(Constants.CATEGORIA_NO_DO)){ 
		    		totalGranelKgIniciMesV += (Double)existencia[3]; //existencia[3] = quilos
		    	} else {
		    		totalGranelKgIniciMesVE += (Double)existencia[3]; //existencia[3] = quilos
		    	}
		    }
	    
		    for(Iterator it=existenciesEmbotellatPrimerDiaMes.iterator(); it.hasNext();){
		    	Object[] existencia = (Object[])it.next();
		    	if(((CategoriaOli)existencia[4]).getId().equals(Constants.CATEGORIA_NO_DO)){ 
		    		totalEnvasatKgIniciMesV += (Double)existencia[3]; //existencia[3] = quilos
		    	} else {
		    		totalEnvasatKgIniciMesVE += (Double)existencia[3]; //existencia[3] = quilos
		    	}
		    }
	    
		    //EXISTENCIES DARRER DIA MES ENVASADORA
		    //Envasadora - existenciaes final de mes
		   	Collection existenciesGranelDarrerDiaMesEnvasadora = oliConsultaEjb.findDipositsActiusNoVaciosByEstablecimientoCategoriasAndData(idEst, categorias.toArray(), ffin, NOMES_DIPOSITS_ENVASADORA);
		    Collection existenciesEmbotellatDarrerDiaMes = oliConsultaEjb.findLotsNoVendidosByEstablecimientoCategoriasAndData(idEst, categorias.toArray(), ffin);
		    
		    for(Iterator it=existenciesGranelDarrerDiaMesEnvasadora.iterator(); it.hasNext();){
		    	Object[] existencia = (Object[])it.next();
		    	if(((CategoriaOli)existencia[4]).getId().equals(Constants.CATEGORIA_NO_DO)){ 
		    		totalGranelKgFinalMesV += (Double)existencia[3]; //existencia[3] = quilos
		    	} else {
		    		totalGranelKgFinalMesVE += (Double)existencia[3]; //existencia[3] = quilos
		    	}
		    }
		    
		    for(Iterator it=existenciesEmbotellatDarrerDiaMes.iterator(); it.hasNext();){
		    	Object[] existencia = (Object[])it.next();
		    	if(((CategoriaOli)existencia[4]).getId().equals(Constants.CATEGORIA_NO_DO)){ 
		    		totalEnvasatKgFinalMesV += (Double)existencia[3]; //existencia[3] = quilos
		    	} else {
		    		totalEnvasatKgFinalMesVE += (Double)existencia[3]; //existencia[3] = quilos
		    	}
		    }
		    
		    // Sortides botelles
		    Collection sortidesLots = oliConsultaEjb.getSortidaLotVendaEntreDiasEnEstablecimiento(finicio, ffin, idEst);
		    
		    for(Iterator it=sortidesLots.iterator(); it.hasNext();){
				final String ISO_ESPANYA = "ESP";
				SortidaLot sl = (SortidaLot)it.next();
				logger.info("Sortida lot: " + sl.getVendaLitres()* 0.916 + "kg");
				System.out.println(sl.getVendaLitres()* 0.916);
				if(sl != null){
					if (sl.getVendaMotiu() != null && sl.getVendaMotiu().equals("PERDUA")){
						if(sl.getLot().getPartidaOli().getCategoriaOli().getId().equals(Constants.CATEGORIA_NO_DO)){ 
							sortidesPerduaEnvasatV += sl.getVendaLitres()* 0.916; //multipliquem per 0.916 per passar ho a kilos
						} else {
							sortidesPerduaEnvasatVE += sl.getVendaLitres()* 0.916; //multipliquem per 0.916 per passar ho a kilos
						}
					} else {
						if(sl.getPais() != null) { 
							if (sl.getPais().getIso().equals(ISO_ESPANYA)){
								if(sl.getLot().getPartidaOli().getCategoriaOli().getId().equals(Constants.CATEGORIA_NO_DO)){ 
									sortidesEnvasatV += sl.getVendaLitres()* 0.916; //multipliquem per 0.916 per passar ho a kilos
								} else {
									sortidesEnvasatVE += sl.getVendaLitres()* 0.916; //multipliquem per 0.916 per passar ho a kilos
								}
							} else {
								if(sl.getLot().getPartidaOli().getCategoriaOli().getId().equals(Constants.CATEGORIA_NO_DO)){ 
									sortidesEnvasatExportacioV += sl.getVendaLitres()* 0.916; //multipliquem per 0.916 per passar ho a kilos
								} else {
									sortidesEnvasatExportacioVE += sl.getVendaLitres()* 0.916; //multipliquem per 0.916 per passar ho a kilos
								}	
							}
						} else {
							if(sl.getLot().getPartidaOli().getCategoriaOli().getId().equals(Constants.CATEGORIA_NO_DO)){ 
								sortidesEnvasatV += sl.getVendaLitres()* 0.916; //multipliquem per 0.916 per passar ho a kilos
							} else {
								sortidesEnvasatVE += sl.getVendaLitres()* 0.916; //multipliquem per 0.916 per passar ho a kilos
							}
						}
					}
				}
			}
		}
		
		// ENTRADES
		// ------------------------------------------------------------------------------------------------

		Collection entradesDiposit = oliConsultaEjb.getEntradasDipositEntreFechasAndEstablecimiento(finicio, ffin, idEst);
		
		for(Iterator ite=entradesDiposit.iterator(); ite.hasNext();){
	   		EntradaDiposit ed = (EntradaDiposit)ite.next();
	   		Diposit d = ed.getDiposit();
	   		
	   		// Entrada de envasadora
	   		if ((d.getDeEnvasadora() != null && d.getDeEnvasadora() == true) && (d.getZonaOrigenTrasllat() == null || d.getZonaOrigenTrasllat().getEstabliment().equals(establiment))){
	   			// Si l'entrada prové d'una elaboració, s'enten que primerament s'ha fet l'entrada i la SORTIDA a la tafona
	   			if (ed.getElaboracio() != null) {
		   			if(ed.getCategoriaOli().getId().equals(Constants.CATEGORIA_NO_DO)){
			   			kilosOliEnvasadoraV += ed.getLitres() * 0.916; //multipliquem per 0.916 per passar-ho a kilos
			   		} else {
			   			kilosOliEnvasadoraVE += ed.getLitres() * 0.916;
			   		}
	   			}
	   		}
	   	}
		logger.info("Entrades de tafona: " + (kilosOliEnvasadoraV + kilosOliEnvasadoraVE) + "kg");
		
		Collection entradesDevolucio = oliConsultaEjb.getDevolucionsEntreDatesAndEstabliment(finicio, ffin, idEst);
		for(Iterator itd=entradesDevolucio.iterator(); itd.hasNext();){
	   		EntradaLot el = (EntradaLot)itd.next();
	   		
   			if(el.getLot().getPartidaOli().getCategoriaOli().getId().equals(Constants.CATEGORIA_NO_DO)){
	   			kilosOliDevolucioV += el.getBotelles() * el.getLot().getEtiquetatge().getTipusEnvas().getVolum() * 0.916; //multipliquem per 0.916 per passar-ho a kilos
	   		} else {
	   			kilosOliDevolucioVE += el.getBotelles() * el.getLot().getEtiquetatge().getTipusEnvas().getVolum() * 0.916;
	   		}
	   	}
		
		// SORTIDES
		// ------------------------------------------------------------------------------------------------
		
		Collection sortidesDiposit = oliConsultaEjb.getSortidesDipositEntreFechasAndEstablecimiento(finicio, ffin, idEst, true);
		
		for(Iterator its=sortidesDiposit.iterator(); its.hasNext();){
	   		SortidaDiposit sd = (SortidaDiposit)its.next();
	   		Diposit dor = sd.getDipositBySdiCoddor();
	   		Diposit dde = sd.getDipositBySdiCoddde();
	   		
	   		// Sortida de envasadora
	   		if ((dor.getDeEnvasadora() != null && dor.getDeEnvasadora() == true) && (dor.getZonaOrigenTrasllat() == null || dor.getZonaOrigenTrasllat().getEstabliment().equals(establiment))){
	   			if (dde != null){
		   			// Moviment de envasadora a tafona
	   				if (dde.getFictici() == false){
			   			if (dde.getDeEnvasadora() == null || dde.getDeEnvasadora() == false || (dde.getZonaOrigenTrasllat() != null && !dde.getZonaOrigenTrasllat().getEstabliment().equals(establiment))){
			   				kilosEntradesDeEnvasadora += sd.getLitres() * 0.916;
			   			}
	   				}
	   			} else {
	   				// Sortida a granel
	   				if (sd.getLot() == null){
	   					if (sd.getDesti().equals("PÈRDUA")){
	   						if(sd.getCategoriaOli().getId().equals(Constants.CATEGORIA_NO_DO)){
		   						sortidesPerduaGranelV += sd.getLitres() * 0.916; //multipliquem per 0.916 per passar-ho a kilos
					   		} else {
					   			sortidesPerduaGranelVE += sd.getLitres() * 0.916;
					   		}
   						} else {
		   					if(sd.getCategoriaOli().getId().equals(Constants.CATEGORIA_NO_DO)){
		   						sortidesVentaGranelV += sd.getLitres() * 0.916; //multipliquem per 0.916 per passar-ho a kilos
					   		} else {
					   			sortidesVentaGranelVE += sd.getLitres() * 0.916;
					   		}
   						}
	   				}
	   			}
	   		// Sortida de tafona
	   		} else {
	   			if (dde != null){
		   			// Moviment de tafona a envasadora
		   			if ((dde.getDeEnvasadora() != null && dde.getDeEnvasadora() == true) && (dde.getZonaOrigenTrasllat() == null || dde.getZonaOrigenTrasllat().getEstabliment().equals(establiment))){
		   				if(sd.getCategoriaOli().getId().equals(Constants.CATEGORIA_NO_DO)){
				   			kilosOliEnvasadoraV += sd.getLitres() * 0.916; //multipliquem per 0.916 per passar-ho a kilos
				   		} else {
				   			kilosOliEnvasadoraVE += sd.getLitres() * 0.916;
				   		}
		   			}
	   			} else {
	   				// Moviment de tafona a envasadora (embotellat)
	   				if (sd.getLot() != null){
	   					if(sd.getCategoriaOli().getId().equals(Constants.CATEGORIA_NO_DO)){
				   			kilosOliEnvasadoraV += sd.getLitres() * 0.916; //multipliquem per 0.916 per passar-ho a kilos
				   		} else {
				   			kilosOliEnvasadoraVE += sd.getLitres() * 0.916;
				   		}
	   				} else {
	   					// Sortida a granel
	   					// Si la sortida és del dipòsit fictici, comprovam de quin dipòsit venia realment (mitjançant traces)
	   					if (dor.getFictici()){
	   						for (Iterator ite = sd.getTraza().getTrazasForTtrCodtrapare().iterator(); ite.hasNext();){
	   							Traza te = (Traza)ite.next();
	   							if (te.getTipus().intValue() == Constants.CODI_TRAZA_TIPUS_ENTRADA_DIPOSIT){
	   								EntradaDiposit ed = oliConsultaEjb.findEntradaDipositByTraza(te.getId());
	   								for (Iterator it = ed.getTraza().getTrazasForTtrCodtrapare().iterator(); it.hasNext();){
	   									Traza ts = (Traza)it.next();
	   		   							if (ts.getTipus().intValue() == Constants.CODI_TRAZA_TIPUS_SORTIDA_DIPOSIT){
	   		   								SortidaDiposit sdp = oliConsultaEjb.getSortidaDipositByTraza(ts.getId());
	   		   								Diposit door = sdp.getDipositBySdiCoddor();
	   		   								// Si el dipòsit d'origen era d'envasadora
	   		   								if ((door.getDeEnvasadora() != null && door.getDeEnvasadora() == true) && (door.getZonaOrigenTrasllat() == null || door.getZonaOrigenTrasllat().getEstabliment().equals(establiment))){
		   		   								if(sd.getCategoriaOli().getId().equals(Constants.CATEGORIA_NO_DO)){
			   		   		   						sortidesVentaGranelV += sd.getLitres() * 0.916; //multipliquem per 0.916 per passar-ho a kilos
			   		   					   		} else {
			   		   					   			sortidesVentaGranelVE += sd.getLitres() * 0.916;
			   		   					   		}
	   		   								} else {
	   		   									kilosSortidesVentesGranel += sd.getLitres() * 0.916;
//	   		   									logger.info("Sortida a granel: " + sd.getLitres() + "l - dor NO envasadora");
	   		   								}
	   		   							} else {//if (ts.getTipus().intValue() == Constants.CODI_TRAZA_TIPUS_ELABORACIO){
	   		   								kilosSortidesVentesGranel += sd.getLitres() * 0.916;
//	   		   								logger.info("Sortida a granel: " + sd.getLitres() + "l - Traça no sortida");
	   		   							}
	   		   							break;
	   								}
	   								break;
	   							}
	   						}
	   							   						
	   					} else {
	   						if (sd.getDesti().equals("PÈRDUA")){
	   							kilosSortidesPerdues += sd.getLitres() * 0.916;
	   						} else {
	   							kilosSortidesVentesGranel += sd.getLitres() * 0.916;
//	   							logger.info("Sortida a granel: " + sd.getLitres() + "l - dor NO fictici");
	   						}
	   					}
	   				}
	   			}
	   		}
	   	}
		
		// Compilar archivo
		logger.debug("2.- Leyendo el stream del jrxml");
	    InputStream is = this.getClass().getClassLoader().getResourceAsStream("es/caib/gestoli/logic/resources/agenciaOliDeclaracioMensual.jrxml");
		logger.debug("3.- Compilando el report");
	    JasperReport report = JasperCompileManager.compileReport(is);

		// Data Source
		logger.debug("4.- Asociando los datos");
		Collection col=new ArrayList();
		JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(col);
		
		//Parametros
		logger.debug("5.- Cargando parametros");
		HashMap params = new HashMap();
		
		//DADES
		params.put("dcampana",campanya.getNom());
		params.put("ddiasignatura", diaSignatura);
		params.put("dmessignatura",mesSignatura);
		params.put("danysignatura", anySignatura);
		params.put("dmesdeclaracio",mesDeclaracio);
		params.put("dnif", establiment.getCif());
		params.put("dnom", establiment.getNom());
		params.put("dadreca", establiment.getDireccio());
		params.put("dlocalitat", establiment.getPoblacio().getNom());
		params.put("dprovincia", "Illes Balears");
		params.put("dtelefon", establiment.getTelefon());
		params.put("dcorreu", establiment.getEmail());
		params.put("dcp", establiment.getCodiPostal());
		params.put("dfax", establiment.getFax());
		
		//TAFONA /////////////////////////////////////
		params.put("dkginicicampanya", numberDecimalFormat.format(totalKgIniciCampanya));																				// Existències inici campanya
		params.put("dkginicimes", numberDecimalFormat.format(totalKgIniciMes));																							// Existències inici mes
		params.put("dkgoliproduit", numberDecimalFormat.format(totalKilosElaboracio));																					// Estrades: oli prodüit
		params.put("dkgentradatrasllat", numberDecimalFormat.format(kilosEntradesTrasllats + kilosEntradesDeEnvasadora));												// Entrades: Oli adquirit i/o en dipòsit
		params.put("dkgtotalentradesgranel",numberDecimalFormat.format(totalKilosElaboracio + kilosEntradesTrasllats + kilosEntradesDeEnvasadora));						// Entrades: total entrades mes
		params.put("dkgenvasadorapropia", numberDecimalFormat.format(kilosOliEnvasadoraVE + kilosOliEnvasadoraV));														// Sortides: envasadora pròpia
		params.put("dkgsortidesventesgranel", numberDecimalFormat.format(kilosSortidesVentesGranel));																	// Sortides: altres entitals nacionals
		params.put("dkgtotalsortidestafona", numberDecimalFormat.format(kilosOliEnvasadoraVE + kilosOliEnvasadoraV + kilosSortidesVentesGranel + kilosSortidesPerdues));// Sortides: total sortides mas
		params.put("dkgfinalmes", numberDecimalFormat.format(totalKgFinalMes));																							// Existències final mes
		params.put("dkgentradaoliva", numberDecimalFormat.format(totalQuilosEntradaOliva));																				// Oliva entrada
		params.put("dkgolivaelaboracio", numberDecimalFormat.format(kilosOlivaElaboracions));																			// Oliva molturada
		params.put("dkgsortidaorujo", numberDecimalFormat.format(kilosSortidaOrujo));																					// Producció i sortides d'orujo (coincideixen)
		params.put("dkgsortidesperduesgranel", numberDecimalFormat.format(kilosSortidesPerdues));
		
		//ENVASADORA //////////////////////////////////////
		params.put("dkginicigranelve", numberDecimalFormat.format(totalGranelKgIniciMesVE));																			// Existències inicial granel - AOVE
		params.put("dkginicigranelv", numberDecimalFormat.format(totalGranelKgIniciMesV));																				// Existències inicial granel - AOV
		params.put("dkginicigranel", numberDecimalFormat.format(totalGranelKgIniciMesV + totalGranelKgIniciMesVE));														// Existències inicial granel - Total
		params.put("dkginicienvasatve", numberDecimalFormat.format(totalEnvasatKgIniciMesVE));																			// Existències inicial envasat - AOVE
		params.put("dkginicienvasatv", numberDecimalFormat.format(totalEnvasatKgIniciMesV));																			// Existències inicial envasat - AOV
		params.put("dkginicienvasat", numberDecimalFormat.format(totalEnvasatKgIniciMesV + totalEnvasatKgIniciMesVE));													// Existències inicial envasat - Total
		params.put("dkgtotalinicive", numberDecimalFormat.format(totalGranelKgIniciMesVE + totalEnvasatKgIniciMesVE));													// Existències total inicial - AOVE
		params.put("dkgtotaliniciv", numberDecimalFormat.format(totalGranelKgIniciMesV + totalEnvasatKgIniciMesV));														// Existències total inicial - AOV
		params.put("dkgtotalinici", numberDecimalFormat.format(totalGranelKgIniciMesV + totalEnvasatKgIniciMesV + totalGranelKgIniciMesVE + totalEnvasatKgIniciMesVE));	// Existències total inicial - Total
		params.put("dkgenvasadorapropiave", numberDecimalFormat.format(kilosOliEnvasadoraVE));																			// Entrades: tafona pròpia - AOVE
		params.put("dkgenvasadorapropiav", numberDecimalFormat.format(kilosOliEnvasadoraV));																			// Entrades: tafona pròpia - AOV
		params.put("dkgenvasadorapropia", numberDecimalFormat.format(kilosOliEnvasadoraV + kilosOliEnvasadoraVE));														// Entrades: tafona pròpia - Total
		params.put("dkgdevolucionsve", numberDecimalFormat.format(kilosOliDevolucioVE));																				// Entrades: devolucions botelles - AOVE
		params.put("dkgdevolucionsv", numberDecimalFormat.format(kilosOliDevolucioV));																					// Entrades: devolucions botelles - AOV
		params.put("dkgdevolucions", numberDecimalFormat.format(kilosOliDevolucioVE + kilosOliDevolucioV));																// Entrades: devolucions botelles - Total
		params.put("totalEntradesEnvasadoraVE", numberDecimalFormat.format(kilosOliEnvasadoraVE + kilosOliDevolucioVE));												// Entrades: total entrades - AOVE
		params.put("totalEntradesEnvasadoraV", numberDecimalFormat.format(kilosOliEnvasadoraV + kilosOliDevolucioV));													// Entrades: total entrades - AOV
		params.put("totalEntradesEnvasadora", numberDecimalFormat.format(kilosOliEnvasadoraV + kilosOliEnvasadoraVE + kilosOliDevolucioVE + kilosOliDevolucioV));		// Entrades: total entrades - Total
		params.put("sortidesVentaGranelVE", numberDecimalFormat.format(sortidesVentaGranelVE));																			// Sortides: granel - altres entitats nacionals - AOVE
		params.put("sortidesVentaGranelV", numberDecimalFormat.format(sortidesVentaGranelV));																			// Sortides: granel - altres entitats nacionals - AOV
		params.put("sortidesVentaGranel", numberDecimalFormat.format(sortidesVentaGranelV + sortidesVentaGranelVE));													// Sortides: granel - altres entitats nacionals - AOV
		params.put("sortidesEnvasatVE", numberDecimalFormat.format(sortidesEnvasatVE));																					// Sortides: envasat - mercat interior - AOVE
		params.put("sortidesEnvasatV", numberDecimalFormat.format(sortidesEnvasatV));																					// Sortides: envasat - mercat interior - AOV
		params.put("sortidesEnvasat", numberDecimalFormat.format(sortidesEnvasatV + sortidesEnvasatVE));																// Sortides: envasat - mercat interior - Total
		params.put("sortidesEnvasatExportacioVE", numberDecimalFormat.format(sortidesEnvasatExportacioVE));																// Sortides: envasat - exportació - AOVE
		params.put("sortidesEnvasatExportacioV", numberDecimalFormat.format(sortidesEnvasatExportacioV));																// Sortides: envasat - exportació - AOV
		params.put("sortidesEnvasatExportacio", numberDecimalFormat.format(sortidesEnvasatExportacioV + sortidesEnvasatExportacioVE));									// Sortides: envasat - exportació - Total
		params.put("totalSortidesEnvasadoraVE", numberDecimalFormat.format(sortidesVentaGranelVE + sortidesEnvasatVE + sortidesEnvasatExportacioVE + 
																		   sortidesPerduaGranelVE + sortidesPerduaEnvasatVE));											// Sortides: total sortides mes - AOVE
		params.put("totalSortidesEnvasadoraV", numberDecimalFormat.format(sortidesVentaGranelV + sortidesEnvasatV + sortidesEnvasatExportacioV + 
				   														  sortidesPerduaGranelV + sortidesPerduaEnvasatV));												// Sortides: total sortides mes - AOV
		params.put("totalSortidesEnvasadora", numberDecimalFormat.format(sortidesVentaGranelV + sortidesEnvasatV + sortidesEnvasatExportacioV + 
					  													 sortidesPerduaGranelV + sortidesPerduaEnvasatV + 
																		 sortidesVentaGranelVE + sortidesEnvasatVE + sortidesEnvasatExportacioVE + 
																		 sortidesPerduaGranelVE + sortidesPerduaEnvasatVE));											// Sortides: total sortides mes - Total
		params.put("dkgfinalgranelve", numberDecimalFormat.format(totalGranelKgFinalMesVE));																			// Existències final granel - AOVE
		params.put("dkgfinalgranelv", numberDecimalFormat.format(totalGranelKgFinalMesV));																				// Existéncies final granel - AOV
		params.put("dkgfinalgranel", numberDecimalFormat.format(totalGranelKgFinalMesV + totalGranelKgFinalMesVE));														// Existéncies final granel - Total
		params.put("dkgfinalenvasatve", numberDecimalFormat.format(totalEnvasatKgFinalMesVE));																			// Existències final envasat - AOVE
		params.put("dkgfinalenvasatv", numberDecimalFormat.format(totalEnvasatKgFinalMesV));																			// Existències final envasat - AOV
		params.put("dkgfinalenvasat", numberDecimalFormat.format(totalEnvasatKgFinalMesV + totalEnvasatKgFinalMesVE));													// Existències final envasat - Total
		params.put("dkgtotalfinalve", numberDecimalFormat.format(totalGranelKgFinalMesVE + totalEnvasatKgFinalMesVE));													// Existències total inicial - AOVE
		params.put("dkgtotalfinalv", numberDecimalFormat.format(totalGranelKgFinalMesV + totalEnvasatKgFinalMesV));														// Existències total inicial - AOV
		params.put("dkgtotalfinal", numberDecimalFormat.format(totalGranelKgFinalMesV + totalEnvasatKgFinalMesV + totalGranelKgFinalMesVE + totalEnvasatKgFinalMesVE));	// Existències total inicial - Total
		params.put("sortidesPerduaGranelVE", numberDecimalFormat.format(sortidesPerduaGranelVE));
		params.put("sortidesPerduaGranelV", numberDecimalFormat.format(sortidesPerduaGranelV));
		params.put("sortidesPerduaGranel", numberDecimalFormat.format(sortidesPerduaGranelVE + sortidesPerduaGranelV));
		params.put("sortidesPerduaEnvasatVE", numberDecimalFormat.format(sortidesPerduaEnvasatVE));
		params.put("sortidesPerduaEnvasatV", numberDecimalFormat.format(sortidesPerduaEnvasatV));
		params.put("sortidesPerduaEnvasat", numberDecimalFormat.format(sortidesPerduaEnvasatVE + sortidesPerduaEnvasatV));
		logger.debug("6.- Cargando los datos al report");
		JasperPrint jasperPrint = JasperFillManager.fillReport( report, params, ds );

		// Configure exporter and set parameters
		ServletOutputStream out = response.getOutputStream();
		response.setContentType("application/pdf");
		response.setHeader("Cache-Control", "store");
		response.setHeader("Pragma", "cache");            
		response.setHeader("Content-Disposition", "attachment; filename=declaracionMensual_"+mesDeclaracio + "_" + anyDeclaracio+".pdf");
		
		logger.debug("7.- Exportando el report a pdf");
	    byte[] bytes = JasperExportManager.exportReportToPdf(jasperPrint);
	    for (int i = 0; i < bytes.length; i ++) {
	    	out.write(bytes[i]);
	    }
	    out.close();
	    out.flush();
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

	public void setCampanyaSessionKey(String campanyaSessionKey) {
		this.campanyaSessionKey = campanyaSessionKey;
	}

}