package es.caib.gestoli.front.spring.views;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import es.caib.gestoli.front.util.Idioma;
import es.caib.gestoli.logic.interfaces.OliConsultaEjb;
import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;
import es.caib.gestoli.logic.model.DescomposicioPartidaOliva;
import es.caib.gestoli.logic.model.DescomposicioPlantacio;
import es.caib.gestoli.logic.model.Olivicultor;
import es.caib.gestoli.logic.util.Constants;

public class GenerarPdfCartilla extends AbstractPdfView {

	private OliConsultaEjb oliConsultaEjb;
	private OliInfraestructuraEjb oliInfraestructuraEjb;
	private HibernateTemplate hibernateTemplate;

	protected void buildPdfDocument(
			Map model,
			Document document,
			PdfWriter writer,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		Iterator olivicultors = ((Collection) model.get("llistat")).iterator();

		// Configuració de la pàgina.
		document.setPageSize(PageSize.A4);
		document.setMargins(50, 50, 20, 20);
		document.setPageCount(0);

		DecimalFormat numberDecimalFormat = new DecimalFormat("###,###,##0.00");
		DecimalFormat numberFormat = new DecimalFormat("###,###,##0");
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		// Fonts.
		Font font12Normal = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL);
		Font font12Bold = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD);
		Font font12BoldBlanca = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD, new Color(255, 255, 255));
		Font font10Normal = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL);
		Font font10TachadoRojo = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.STRIKETHRU, new Color(255, 0, 0));
		Font font10Bold = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);
		Font font10BoldBlanca = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, new Color(255, 255, 255));
//		Font font11Bold = FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD);
		Font font11BoldBlanca = FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD, new Color(255, 255, 255));
		Font footerFont = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL);

		// Color de fons de cel·les.
//		Color colorFons = new Color(224, 223, 221);  // Gris
		Color colorFons = new Color(20, 85, 43);  // Verd

		// Peu de pàgina.
		HeaderFooter footer = new HeaderFooter(new Phrase(missatge("pdf.cartilla.peu", request), footerFont), false);
		footer.setAlignment(HeaderFooter.ALIGN_CENTER);
		footer.setBorderWidth(0);
		footer.setTop(5);
		document.setFooter(footer);

		document.open();

		while (olivicultors.hasNext()) {
			Olivicultor olivicultor = (Olivicultor) olivicultors.next();
			oliConsultaEjb.setHibernateTemplate(getHibernateTemplate());
			Iterator itDescomposicio = oliConsultaEjb.findDescomposicioPlantacioPerOlivicultor(olivicultor.getId()).iterator();

			// Logo.
			Image logo = Image.getInstance(getBytesFromFile("es/caib/gestoli/logic/resources/do.jpg"));
			logo.scaleToFit(40, 39);
			logo.setAlignment(Image.ALIGN_LEFT);
			logo.setIndentationLeft(20);
			document.add(logo);
			
			// Títol.
			String title = missatge("pdf.cartilla.titol", request);
			PdfPTable pdfPTableTitle = new PdfPTable(1);
			pdfPTableTitle.setHeaderRows(0);
			pdfPTableTitle.setWidthPercentage(65);
			pdfPTableTitle.getDefaultCell().setBorderWidth(0.5f);
			pdfPTableTitle.getDefaultCell().setPadding(15);
			pdfPTableTitle.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			pdfPTableTitle.getDefaultCell().setBackgroundColor(colorFons);
			pdfPTableTitle.addCell(new Phrase(title, font12BoldBlanca));
			document.add(pdfPTableTitle);

			Paragraph espacioPeq = new Paragraph(" ");
			espacioPeq.setFont(FontFactory.getFont(FontFactory.HELVETICA, 6));
			document.add(espacioPeq);

			/*********** TAULA DE DESCOMPOSICIONS ***********/
			PdfPTable pdfPTableDescomposicion = new PdfPTable(7);
			pdfPTableDescomposicion.setHeaderRows(0);
			pdfPTableDescomposicion.setWidthPercentage(100);
			pdfPTableDescomposicion.getDefaultCell().setBorderWidth(0);
//			pdfPTableDescomposicion.getDefaultCell().setBorderWidthTop(1);
			pdfPTableDescomposicion.getDefaultCell().setPaddingLeft(20);
			pdfPTableDescomposicion.getDefaultCell().setPaddingRight(20);
			pdfPTableDescomposicion.getDefaultCell().setPaddingTop(5);
			pdfPTableDescomposicion.getDefaultCell().setPaddingBottom(5);
			pdfPTableDescomposicion.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_CENTER);

			// Taula (cel·les).
			float[] columnas = {21, 10, 10, 12, 12, 10, 10, 15};
			PdfPTable pdfPTableTabla = new PdfPTable(columnas);
			pdfPTableTabla.setHeaderRows(0);
			pdfPTableTabla.setWidthPercentage(100);
			pdfPTableTabla.getDefaultCell().setBorderWidth(0);
			pdfPTableTabla.getDefaultCell().setPadding(0);
			pdfPTableTabla.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_LEFT);

			/*********** Campanya ***********/
			PdfPCell celdaTabla = new PdfPCell(new Phrase(missatge("pdf.cartilla.campanya", request) + " " + olivicultor.getCampanya().getNom(), font12Bold));
			celdaTabla.setColspan(6);
			celdaTabla.setPaddingLeft(60);
			celdaTabla.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			celdaTabla.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
			celdaTabla.setBorderWidth(0);
			celdaTabla.setPaddingBottom(10);
			celdaTabla.setPaddingTop(10);
			pdfPTableTabla.addCell(celdaTabla);

			String codis = "";
			if ((olivicultor.getCodiDO() != null) && (!olivicultor.getCodiDO().equals(""))) {
				codis = missatge("pdf.cartilla.ra", request) + "-" + olivicultor.getCodiDO();
			}
			if ((olivicultor.getCodiDOExperimental() != null) && (!olivicultor.getCodiDOExperimental().equals(""))) {
				if (codis.equals("")) {
					codis = missatge("pdf.cartilla.re", request) + "-" + olivicultor.getCodiDOExperimental();
				} else {
					codis += "\n" + missatge("pdf.cartilla.re", request) + "-" + olivicultor.getCodiDOExperimental();
				}
			}
			if ((olivicultor.getCodiDOPOliva() != null) && (!olivicultor.getCodiDOPOliva().equals(""))) {
				if (codis.equals("")) {
					codis = missatge("pdf.cartilla.rt", request) + "-" + olivicultor.getCodiDOPOliva();
				} else {
					codis += "\n" + missatge("pdf.cartilla.rt", request) + "-" + olivicultor.getCodiDOPOliva();
				}
			}

			/*********** RA + RE + RT ***********/
			celdaTabla = new PdfPCell(new Phrase(codis, font11BoldBlanca));
			celdaTabla.setColspan(2);
			celdaTabla.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			celdaTabla.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
			celdaTabla.setBorderWidth(0.5f);
			celdaTabla.setPaddingBottom(10);
			celdaTabla.setPaddingTop(10);
			celdaTabla.setBackgroundColor(colorFons);
			pdfPTableTabla.addCell(celdaTabla);

			/*********** Titular ***********/
			celdaTabla = new PdfPCell(new Phrase(missatge("pdf.cartilla.titular", request) + " " + olivicultor.getNom(), font11BoldBlanca));
			celdaTabla.setColspan(8);
			celdaTabla.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
			celdaTabla.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
			celdaTabla.setBorderWidth(0.5f);
			celdaTabla.setPaddingBottom(10);
			celdaTabla.setPaddingTop(10);
			celdaTabla.setPaddingLeft(5);
			celdaTabla.setBackgroundColor(colorFons);
			pdfPTableTabla.addCell(celdaTabla);

			/*********** Descomposició - Municipi ***********/
			celdaTabla = new PdfPCell(new Phrase(missatge("pdf.cartilla.municipi", request), font10BoldBlanca));
			celdaTabla.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			celdaTabla.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
			celdaTabla.setBorderWidth(0.5f);
			celdaTabla.setPaddingBottom(8);
			celdaTabla.setPaddingTop(8);
			celdaTabla.setBackgroundColor(colorFons);
			pdfPTableTabla.addCell(celdaTabla);

			/*********** Descomposició - Poligon ***********/
			celdaTabla = new PdfPCell(new Phrase(new Phrase(missatge("pdf.cartilla.poligon", request), font10BoldBlanca)));
			celdaTabla.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			celdaTabla.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
			celdaTabla.setBorderWidth(0.5f);
			celdaTabla.setPaddingBottom(8);
			celdaTabla.setPaddingTop(8);
			celdaTabla.setBackgroundColor(colorFons);
			pdfPTableTabla.addCell(celdaTabla);

			/*********** Descomposició - Parcela ***********/
			celdaTabla = new PdfPCell(new Phrase(missatge("pdf.cartilla.parcela", request), font10BoldBlanca));
			celdaTabla.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			celdaTabla.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
			celdaTabla.setBorderWidth(0.5f);
			celdaTabla.setPaddingBottom(8);
			celdaTabla.setPaddingTop(8);
			celdaTabla.setBackgroundColor(colorFons);
			pdfPTableTabla.addCell(celdaTabla);

			/*********** Descomposició - Superficie ***********/
			celdaTabla = new PdfPCell(new Phrase(missatge("pdf.cartilla.superficie", request), font10BoldBlanca));
			celdaTabla.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			celdaTabla.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
			celdaTabla.setBorderWidth(0.5f);
			celdaTabla.setPaddingBottom(8);
			celdaTabla.setPaddingTop(8);
			celdaTabla.setBackgroundColor(colorFons);
			pdfPTableTabla.addCell(celdaTabla);

			/*********** Descomposició - Oliveres ***********/
			celdaTabla = new PdfPCell(new Phrase(missatge("pdf.cartilla.nombreOliveres", request), font10BoldBlanca));
			celdaTabla.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			celdaTabla.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
			celdaTabla.setBorderWidth(0.5f);
			celdaTabla.setPaddingBottom(8);
			celdaTabla.setPaddingTop(8);
			celdaTabla.setBackgroundColor(colorFons);
			pdfPTableTabla.addCell(celdaTabla);

			/*********** Descomposició - Varietat ***********/
			celdaTabla = new PdfPCell(new Phrase(missatge("pdf.cartilla.varietatOlivera", request), font10BoldBlanca));
			celdaTabla.setColspan(2);
			celdaTabla.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			celdaTabla.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
			celdaTabla.setBorderWidth(0.5f);
			celdaTabla.setPaddingBottom(8);
			celdaTabla.setPaddingTop(8);
			celdaTabla.setBackgroundColor(colorFons);
			pdfPTableTabla.addCell(celdaTabla);

			/*********** Descomposició - Producció ***********/
			celdaTabla = new PdfPCell(new Phrase(missatge("pdf.cartilla.produccioMaxima", request), font10BoldBlanca));
			celdaTabla.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			celdaTabla.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
			celdaTabla.setBorderWidth(0.5f);
			celdaTabla.setPaddingBottom(8);
			celdaTabla.setPaddingTop(8);
			celdaTabla.setBackgroundColor(colorFons);
			pdfPTableTabla.addCell(celdaTabla);

			// Generam les files de la taula.
			double maxProduccioTotal = 0.0;
			double maxProduccioTotalDO = 0.0;
			double maxProduccioTotalnoDO = 0.0;
			double maxProduccioTotalOlivaTaula = 0.0;

			while (itDescomposicio.hasNext()) {
				DescomposicioPlantacio descomposicio = (DescomposicioPlantacio)itDescomposicio.next();

				Boolean olivaTaula = descomposicio.getVarietatOliva().getOlivaTaula()==null?false:descomposicio.getVarietatOliva().getOlivaTaula();
				
				if (!olivaTaula) {
					/*********** Descomposició - Municipi ***********/
					celdaTabla = new PdfPCell(new Phrase(descomposicio.getPlantacio().getMunicipi().getNom(), font10Normal));
					celdaTabla.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
					celdaTabla.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
					celdaTabla.setPaddingLeft(3);
					celdaTabla.setBorderWidth(0.5f);
					pdfPTableTabla.addCell(celdaTabla);
	
					/*********** Descomposició - Poligon ***********/
					celdaTabla = new PdfPCell(new Phrase(descomposicio.getPlantacio().getPoligon(), font10Normal));
					celdaTabla.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
					celdaTabla.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
					celdaTabla.setPaddingLeft(3);
					celdaTabla.setBorderWidth(0.5f);
					pdfPTableTabla.addCell(celdaTabla);
	
					/*********** Descomposició - Parcela ***********/
					celdaTabla = new PdfPCell(new Phrase(descomposicio.getPlantacio().getParcela(), font10Normal));
					celdaTabla.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
					celdaTabla.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
					celdaTabla.setPaddingLeft(3);
					celdaTabla.setBorderWidth(0.5f);
					pdfPTableTabla.addCell(celdaTabla);
	
					/*********** Descomposició - Superficie ***********/
					celdaTabla = new PdfPCell(new Phrase(numberDecimalFormat.format(descomposicio.getSuperficie().doubleValue()), font10Normal));
					celdaTabla.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
					celdaTabla.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
					celdaTabla.setPaddingRight(5);
					celdaTabla.setBorderWidth(0.5f);
					pdfPTableTabla.addCell(celdaTabla);
	
					/*********** Descomposició - Oliveres ***********/
					celdaTabla = new PdfPCell(new Phrase(numberFormat.format(descomposicio.getNumeroOliveres()), font10Normal));
					celdaTabla.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
					celdaTabla.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
					celdaTabla.setBorderWidth(0.5f);
					pdfPTableTabla.addCell(celdaTabla);
	
					/*********** Descomposició - Varietat ***********/
					String varietatOlivera = "";
					if (descomposicio.getVarietatOliva().getExperimental()){
						varietatOlivera = descomposicio.getVarietatOliva().getNomVarietat();
					} else {
						varietatOlivera = descomposicio.getVarietatOliva().getNom();
					}
					celdaTabla = new PdfPCell(new Phrase(varietatOlivera, font10Normal));
					celdaTabla.setColspan(2);
					celdaTabla.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
					celdaTabla.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
					celdaTabla.setBorderWidth(0.5f);
					pdfPTableTabla.addCell(celdaTabla);
	
					/*********** Descomposició - Producció ***********/
					if (olivicultor.getCodiDOPOliva()!=null && olivicultor.getCodiDOPOliva()!="" && descomposicio.getVarietatOliva().getId()==Constants.VARIETAT_OLIVA_MALLORQUINA) {
						DescomposicioPlantacio dpot = oliInfraestructuraEjb.descomposicioPlantacioByPlantacioIdVariedadId(descomposicio.getPlantacio().getId(), Constants.VARIETAT_OLIVA_MALLORQUINA_TAULA);
						celdaTabla = new PdfPCell(new Phrase(numberDecimalFormat.format(descomposicio.getMaxProduccio().doubleValue())+"\n("+numberDecimalFormat.format(dpot.getMaxProduccio().doubleValue())+")*", font10Normal));
					}
					else celdaTabla = new PdfPCell(new Phrase(numberDecimalFormat.format(descomposicio.getMaxProduccio().doubleValue()), font10Normal));
					celdaTabla.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
					celdaTabla.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
					celdaTabla.setPaddingRight(5);
					celdaTabla.setBorderWidth(0.5f);
					pdfPTableTabla.addCell(celdaTabla);
	
					maxProduccioTotal += descomposicio.getMaxProduccio().doubleValue();
					if (descomposicio.getVarietatOliva().getAutoritzada().booleanValue()) {
						maxProduccioTotalDO += descomposicio.getMaxProduccio().doubleValue();
					} else {
						maxProduccioTotalnoDO += descomposicio.getMaxProduccio().doubleValue();
					}
				} else {
					maxProduccioTotalOlivaTaula += descomposicio.getMaxProduccio().doubleValue();
				}
			}

			// Afegim dues linees buides.
			for (int i = 0; i < 2; i++) {
				for (int x = 0; x < 7; x++) {
					celdaTabla = new PdfPCell(new Phrase(" ", font12Normal));
					celdaTabla.setBorderWidth(0.5f);
					celdaTabla.setPaddingRight(10);
					if (x == 5) celdaTabla.setColspan(2);
					pdfPTableTabla.addCell(celdaTabla);
				}
			}

			if (maxProduccioTotalOlivaTaula>0) {
				/*********** Producció màxima DO ***********/

				celdaTabla = new PdfPCell(new Phrase(missatge("pdf.cartilla.aclaracio.produccio.olivaTaula", request), footerFont));
				celdaTabla.setColspan(8);
				celdaTabla.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
				celdaTabla.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
				celdaTabla.setBorderWidth(0);
				celdaTabla.setPaddingBottom(2);
				celdaTabla.setPaddingTop(2);
				pdfPTableTabla.addCell(celdaTabla);

			}
			/*********** Producció màxima DO ***********/
			// Deixam la primera cel·la en blanc.
			celdaTabla = new PdfPCell(new Phrase(" ", font12Normal));
			celdaTabla.setBorderWidth(0.5f);
			celdaTabla.setPaddingRight(10);
			celdaTabla.setBorderWidth(0);
			pdfPTableTabla.addCell(celdaTabla);

			celdaTabla = new PdfPCell(new Phrase(missatge("pdf.cartilla.produccioMaximaTotalDO", request), font10Bold));
			celdaTabla.setColspan(4);
			celdaTabla.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
			celdaTabla.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
			celdaTabla.setBorderWidth(0);
			celdaTabla.setPaddingBottom(2);
			celdaTabla.setPaddingTop(10);
			pdfPTableTabla.addCell(celdaTabla);

			celdaTabla = new PdfPCell(new Phrase(numberDecimalFormat.format(maxProduccioTotalDO) + " " + missatge("pdf.cartilla.kgs", request), font10Bold));
			celdaTabla.setColspan(3);
			celdaTabla.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
			celdaTabla.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
			celdaTabla.setBorderWidth(0);
			celdaTabla.setPaddingBottom(2);
			celdaTabla.setPaddingTop(10);
			pdfPTableTabla.addCell(celdaTabla);

			/*********** Producció màxima no DO ***********/
			// Deixam la primera cel·la en blanc.
			celdaTabla = new PdfPCell(new Phrase(" ", font12Normal));
			celdaTabla.setBorderWidth(0.5f);
			celdaTabla.setPaddingRight(10);
			celdaTabla.setBorderWidth(0);
			pdfPTableTabla.addCell(celdaTabla);

			celdaTabla = new PdfPCell(new Phrase(missatge("pdf.cartilla.produccioMaximaTotalNoDO", request), font10Bold));
			celdaTabla.setColspan(4);
			celdaTabla.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
			celdaTabla.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
			celdaTabla.setBorderWidth(0);
			celdaTabla.setPaddingBottom(5);
			celdaTabla.setPaddingTop(2);
			pdfPTableTabla.addCell(celdaTabla);

			celdaTabla = new PdfPCell(new Phrase(numberDecimalFormat.format(maxProduccioTotalnoDO) + " " + missatge("pdf.cartilla.kgs", request), font10Bold));
			celdaTabla.setColspan(3);
			celdaTabla.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
			celdaTabla.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
			celdaTabla.setBorderWidth(0);
			celdaTabla.setPaddingBottom(5);
			celdaTabla.setPaddingTop(2);
			pdfPTableTabla.addCell(celdaTabla);

			/*********** Producció màxima total ***********/
			// Deixam la primera cel·la en blanc.
			celdaTabla = new PdfPCell(new Phrase(" ", font12Normal));
			celdaTabla.setBorderWidth(0.5f);
			celdaTabla.setPaddingRight(10);
			celdaTabla.setBorderWidth(0);
			pdfPTableTabla.addCell(celdaTabla);

			celdaTabla = new PdfPCell(new Phrase(missatge("pdf.cartilla.produccioMaximaTotal", request), font10Bold));
			celdaTabla.setColspan(4);
			celdaTabla.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
			celdaTabla.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
			celdaTabla.setBorderWidth(0);
			celdaTabla.setPaddingBottom(10);
			celdaTabla.setPaddingTop(5);
			pdfPTableTabla.addCell(celdaTabla);
			
			celdaTabla = new PdfPCell(new Phrase(numberDecimalFormat.format(maxProduccioTotal) + " " + missatge("pdf.cartilla.kgs", request), font10Bold));
			celdaTabla.setColspan(3);
			celdaTabla.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
			celdaTabla.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
			celdaTabla.setBorderWidth(0);
			celdaTabla.setPaddingBottom(10);
			celdaTabla.setPaddingTop(5);
			pdfPTableTabla.addCell(celdaTabla);
			
			/*********** Producció màxima total oliva taula ***********/
			// Deixam la primera cel·la en blanc.
			celdaTabla = new PdfPCell(new Phrase(" ", font12Normal));
			celdaTabla.setBorderWidth(0.5f);
			celdaTabla.setPaddingRight(10);
			celdaTabla.setBorderWidth(0);
			pdfPTableTabla.addCell(celdaTabla);

			celdaTabla = new PdfPCell(new Phrase(missatge("pdf.cartilla.produccioMaximaTotalOlivaTaula", request), font10Bold));
			celdaTabla.setColspan(4);
			celdaTabla.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
			celdaTabla.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
			celdaTabla.setBorderWidth(0);
			celdaTabla.setPaddingBottom(10);
			celdaTabla.setPaddingTop(5);
			pdfPTableTabla.addCell(celdaTabla);

			celdaTabla = new PdfPCell(new Phrase(numberDecimalFormat.format(maxProduccioTotalOlivaTaula) + " " + missatge("pdf.cartilla.kgs", request), font10Bold));
			celdaTabla.setColspan(3);
			celdaTabla.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
			celdaTabla.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
			celdaTabla.setBorderWidth(0);
			celdaTabla.setPaddingBottom(10);
			celdaTabla.setPaddingTop(5);
			pdfPTableTabla.addCell(celdaTabla);

			// Pintam la taula principal.
			document.add(pdfPTableTabla);

			Paragraph espacio = new Paragraph(" ");
			document.add(espacio);

			oliConsultaEjb.setHibernateTemplate(getHibernateTemplate());
			Iterator itPartida = oliConsultaEjb.findDescomposicioPartidaOlivaPerOlivicultor(olivicultor.getId()).iterator();

			/*********** TAULA DE DESCOMPOSICIONS ***********/
			pdfPTableDescomposicion = new PdfPTable(6);
			pdfPTableDescomposicion.setHeaderRows(0);
			pdfPTableDescomposicion.setWidthPercentage(100);
			pdfPTableDescomposicion.getDefaultCell().setBorderWidth(0);
//			pdfPTableDescomposicion.getDefaultCell().setBorderWidthTop(1);
			pdfPTableDescomposicion.getDefaultCell().setPaddingLeft(20);
			pdfPTableDescomposicion.getDefaultCell().setPaddingRight(20);
			pdfPTableDescomposicion.getDefaultCell().setPaddingTop(5);
			pdfPTableDescomposicion.getDefaultCell().setPaddingBottom(5);
			pdfPTableDescomposicion.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_CENTER);

			// Taula (cel·les).
			float[] columnasEntrega = {15, 30, 15, 15, 10, 15};
			pdfPTableTabla = new PdfPTable(columnasEntrega);
			pdfPTableTabla.setHeaderRows(0);
			pdfPTableTabla.setWidthPercentage(100);
			pdfPTableTabla.getDefaultCell().setBorderWidth(0);
			pdfPTableTabla.getDefaultCell().setPadding(0);
			pdfPTableTabla.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_LEFT);

			/*********** Entregues d'oliva ***********/
			celdaTabla = new PdfPCell(new Phrase(missatge("pdf.cartilla.entreguesOliva", request) + " ", font11BoldBlanca));
			celdaTabla.setColspan(7);
			celdaTabla.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
			celdaTabla.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
			celdaTabla.setBorderWidth(0.5f);
			celdaTabla.setPaddingBottom(10);
			celdaTabla.setPaddingTop(10);
			celdaTabla.setPaddingLeft(5);
			celdaTabla.setBackgroundColor(colorFons);
			pdfPTableTabla.addCell(celdaTabla);

			/*********** Entregues - Data ***********/
			celdaTabla = new PdfPCell(new Phrase(missatge("pdf.cartilla.data", request), font10BoldBlanca));
			celdaTabla.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			celdaTabla.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
			celdaTabla.setBorderWidth(0.5f);
			celdaTabla.setPaddingBottom(8);
			celdaTabla.setPaddingTop(8);
			celdaTabla.setBackgroundColor(colorFons);
			pdfPTableTabla.addCell(celdaTabla);

			/*********** Entregues - Tafona ***********/
			celdaTabla = new PdfPCell(new Phrase(new Phrase(missatge("pdf.cartilla.tafona", request), font10BoldBlanca)));
			celdaTabla.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			celdaTabla.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
			celdaTabla.setBorderWidth(0.5f);
			celdaTabla.setPaddingBottom(8);
			celdaTabla.setPaddingTop(8);
			celdaTabla.setBackgroundColor(colorFons);
			pdfPTableTabla.addCell(celdaTabla);

			/*********** Entregues  - Varietat ***********/
			celdaTabla = new PdfPCell(new Phrase(missatge("pdf.cartilla.varietat", request), font10BoldBlanca));
			celdaTabla.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			celdaTabla.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
			celdaTabla.setBorderWidth(0.5f);
			celdaTabla.setPaddingBottom(8);
			celdaTabla.setPaddingTop(8);
			celdaTabla.setBackgroundColor(colorFons);
			pdfPTableTabla.addCell(celdaTabla);

			/*********** Entregues - Estat ***********/
			celdaTabla = new PdfPCell(new Phrase(missatge("pdf.cartilla.estatOliva1", request), font10BoldBlanca));
			celdaTabla.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			celdaTabla.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
			celdaTabla.setBorderWidth(0.5f);
			celdaTabla.setPaddingBottom(8);
			celdaTabla.setPaddingTop(8);
			celdaTabla.setBackgroundColor(colorFons);
			pdfPTableTabla.addCell(celdaTabla);

			/*********** Entregues - Kilos ***********/
			celdaTabla = new PdfPCell(new Phrase(missatge("pdf.cartilla.Kgs", request), font10BoldBlanca));
			celdaTabla.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			celdaTabla.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
			celdaTabla.setBorderWidth(0.5f);
			celdaTabla.setPaddingBottom(8);
			celdaTabla.setPaddingTop(8);
			celdaTabla.setBackgroundColor(colorFons);
			pdfPTableTabla.addCell(celdaTabla);

			/*********** Entregues - Acumulats ***********/
			celdaTabla = new PdfPCell(new Phrase(missatge("pdf.cartilla.KgsAcumulats", request), font10BoldBlanca));
			celdaTabla.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			celdaTabla.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
			celdaTabla.setBorderWidth(0.5f);
			celdaTabla.setPaddingBottom(8);
			celdaTabla.setPaddingTop(8);
			celdaTabla.setBackgroundColor(colorFons);
			pdfPTableTabla.addCell(celdaTabla);

			Map kgsAcumulats = new HashMap();
			while (itPartida.hasNext()) {
				DescomposicioPartidaOliva descomposicioPartidaOliva = (DescomposicioPartidaOliva) itPartida.next();

				/*********** Entregues - Data ***********/
				if (!descomposicioPartidaOliva.getPartidaOliva().getValid().booleanValue()) {
					celdaTabla = new PdfPCell(new Phrase(dateFormat.format(descomposicioPartidaOliva.getPartidaOliva().getData()), font10TachadoRojo));
				} else {
					celdaTabla = new PdfPCell(new Phrase(dateFormat.format(descomposicioPartidaOliva.getPartidaOliva().getData()), font10Normal));
				}
				celdaTabla.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
				celdaTabla.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
				celdaTabla.setBorderWidth(0.5f);
				pdfPTableTabla.addCell(celdaTabla);

				/*********** Entregues - Tafona ***********/
				if (!descomposicioPartidaOliva.getPartidaOliva().getValid().booleanValue()) {
					celdaTabla = new PdfPCell(new Phrase(new Phrase(descomposicioPartidaOliva.getPartidaOliva().getZona().getEstabliment().getNom(), font10TachadoRojo)));
				} else {
					celdaTabla = new PdfPCell(new Phrase(new Phrase(descomposicioPartidaOliva.getPartidaOliva().getZona().getEstabliment().getNom(), font10Normal)));
				}
				celdaTabla.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
				celdaTabla.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
				celdaTabla.setPaddingLeft(3);
				celdaTabla.setBorderWidth(0.5f);
				pdfPTableTabla.addCell(celdaTabla);

				/*********** Entregues - Varietat ***********/
				String varietatOli = "";
				if (descomposicioPartidaOliva.getDescomposicioPlantacio().getVarietatOliva().getExperimental()){
					varietatOli = descomposicioPartidaOliva.getDescomposicioPlantacio().getVarietatOliva().getNomVarietat();
				} else {
					varietatOli = descomposicioPartidaOliva.getDescomposicioPlantacio().getVarietatOliva().getNom();
				}
				if (!descomposicioPartidaOliva.getPartidaOliva().getValid().booleanValue()) {
					celdaTabla = new PdfPCell(new Phrase(varietatOli, font10TachadoRojo));
				} else {
					celdaTabla = new PdfPCell(new Phrase(varietatOli, font10Normal));
				}
				celdaTabla.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
				celdaTabla.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
				celdaTabla.setBorderWidth(0.5f);
				pdfPTableTabla.addCell(celdaTabla);

				/*********** Entregues - Estat ***********/
				if (!descomposicioPartidaOliva.getPartidaOliva().getValid().booleanValue()) {
					celdaTabla = new PdfPCell(new Phrase(descomposicioPartidaOliva.getPartidaOliva().getSana().booleanValue()?missatge("pdf.cartilla.sana", request):missatge("pdf.cartilla.noSana", request), font10TachadoRojo));
				} else {
					celdaTabla = new PdfPCell(new Phrase(descomposicioPartidaOliva.getPartidaOliva().getSana().booleanValue()?missatge("pdf.cartilla.sana", request):missatge("pdf.cartilla.noSana", request), font10Normal));
				}
				celdaTabla.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
				celdaTabla.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
				celdaTabla.setBorderWidth(0.5f);
				pdfPTableTabla.addCell(celdaTabla);

				/*********** Entregues - Kilos ***********/
				if (!descomposicioPartidaOliva.getPartidaOliva().getValid().booleanValue()) {
					celdaTabla = new PdfPCell(new Phrase(numberDecimalFormat.format(descomposicioPartidaOliva.getKilos().doubleValue()), font10TachadoRojo));
				} else {
					celdaTabla = new PdfPCell(new Phrase(numberDecimalFormat.format(descomposicioPartidaOliva.getKilos().doubleValue()), font10Normal));
				}
				celdaTabla.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
				celdaTabla.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
				celdaTabla.setPaddingRight(5);
				celdaTabla.setBorderWidth(0.5f);
				pdfPTableTabla.addCell(celdaTabla);

				/*********** Entregues - Acumulats ***********/
				if (!descomposicioPartidaOliva.getPartidaOliva().getValid().booleanValue()) {
					celdaTabla = new PdfPCell(new Phrase("", font10Normal));
				} else {
					// Sumam els kilos totals.
					String nomOliv = descomposicioPartidaOliva.getDescomposicioPlantacio().getVarietatOliva().getNom();
					if (kgsAcumulats.containsKey(nomOliv)) {
						double acumulat = descomposicioPartidaOliva.getKilos().doubleValue() + ((Double)kgsAcumulats.get(nomOliv)).doubleValue();
						kgsAcumulats.put(nomOliv, new Double(acumulat));
					} else {
						kgsAcumulats.put(nomOliv, descomposicioPartidaOliva.getKilos());
					}
					celdaTabla = new PdfPCell(new Phrase(numberDecimalFormat.format(((Double)kgsAcumulats.get(nomOliv)).doubleValue()), font10Normal));
				}
				celdaTabla.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
				celdaTabla.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
				celdaTabla.setPaddingRight(5);
				celdaTabla.setBorderWidth(0.5f);
				pdfPTableTabla.addCell(celdaTabla);
			}

			// Afegim dues linees buides.
			for (int i = 0; i < 2; i++) {
				for (int x = 0; x < 6; x++) {
					celdaTabla = new PdfPCell(new Phrase(" ", font12Normal));
					celdaTabla.setBorderWidth(0.5f);
					celdaTabla.setPaddingRight(10);
					pdfPTableTabla.addCell(celdaTabla);
				}
			}
			
			document.add(pdfPTableTabla);

			document.newPage();
		}

		document.close();

		response.setHeader("Content-Disposition","attachment; filename=cartilla.pdf");
	}

	/**
	 * Inyección de la dependencia oliConsultaEjb
	 * @param oliConsultaEjb La clase a inyectar.
	 */
	public void setOliConsultaEjb(OliConsultaEjb oliConsultaEjb) {
		this.oliConsultaEjb = oliConsultaEjb;
	}
	
	/**
	 * Inyección de la dependencia oliConsultaEjb
	 * @param oliConsultaEjb La clase a inyectar.
	 */
	public void setOliInfraestructuraEjb(OliInfraestructuraEjb oliInfraestructuraEjb) {
		this.oliInfraestructuraEjb = oliInfraestructuraEjb;
	}

	private String missatge(String clave, HttpServletRequest request) {
		String valor = getApplicationContext().getMessage(clave, null, Idioma.getLocale(request));
		return valor;
	}

	/**
	 * Returns the contents of the file in a byte array.
	 * @param nomArxiu
	 * @return
	 * @throws IOException
	 */
	public byte[] getBytesFromFile(String nomArxiu) throws IOException {
		InputStream is = this.getClass().getClassLoader().getResourceAsStream(nomArxiu);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[256];
		int cuenta;
		while ((cuenta=is.read(buffer)) > 0) {
			baos.write(buffer,0,cuenta);
		}
		byte[] array = baos.toByteArray();
		return array;
	}

	/**
	 * set the hibernate template.
	 * @param hibernateTemplate the hibernate spring template.
	 * @spring.property ref="hibernateTemplate"
	 */
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
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
