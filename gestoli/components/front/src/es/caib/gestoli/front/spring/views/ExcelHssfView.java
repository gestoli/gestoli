package es.caib.gestoli.front.spring.views;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.jsp.JspException;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFBorderFormatting;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.hssf.util.HSSFColor;
import org.displaytag.Messages;
import org.displaytag.exception.BaseNestableJspTagException;
import org.displaytag.exception.SeverityEnum;
import org.displaytag.export.BinaryExportView;
import org.displaytag.model.Column;
import org.displaytag.model.ColumnIterator;
import org.displaytag.model.HeaderCell;
import org.displaytag.model.Row;
import org.displaytag.model.RowIterator;
import org.displaytag.model.TableModel;

import es.caib.gestoli.logic.ejb.ServiceProxy;
import es.caib.gestoli.logic.interfaces.OliConsultaEjb;
import es.caib.gestoli.logic.interfaces.OliInfraestructuraEjb;
import es.caib.gestoli.logic.interfaces.OliUsuariEjb;
import es.caib.gestoli.logic.model.Campanya;
import es.caib.gestoli.logic.model.Elaboracio;
import es.caib.gestoli.logic.model.Establiment;
import es.caib.gestoli.logic.model.PartidaOliva;
import es.caib.gestoli.logic.model.SortidaDiposit;
import es.caib.gestoli.logic.model.SortidaLot;
import es.caib.gestoli.logic.model.SortidaOrujo;
import es.caib.gestoli.logic.model.Usuari;

public class ExcelHssfView implements BinaryExportView
{

    private TableModel model; 	// TableModel to render
    private boolean exportFull;	// export full list?
    private boolean header;		// include header in export?
    private boolean decorated;	// decorate export?
    
    private HSSFCellStyle oddStyle;
    private HSSFCellStyle oddStyleLitres;
    private HSSFCellStyle oddStyleKilos;
    private HSSFCellStyle oddStyleBotelles;
    private HSSFCellStyle oddStyleNumber;
    private HSSFCellStyle evenStyle;
    private HSSFCellStyle evenStyleLitres;
    private HSSFCellStyle evenStyleKilos;
    private HSSFCellStyle evenStyleBotelles;
    private HSSFCellStyle evenStyleNumber;
    private HSSFCellStyle subTotalStyle;
    private HSSFCellStyle materiaStyle;
    private HSSFCellStyle headerStyle;
    
    private HSSFCellStyle boldStyle;
    private HSSFCellStyle cellStyle;

    
    private HSSFCellStyle borderTopBoldStyle;
    private HSSFCellStyle borderTopRightBoldStyle;
    private HSSFCellStyle borderRightBoldStyle;
    private HSSFCellStyle borderBottomRightBoldStyle;
    private HSSFCellStyle borderBottomBoldStyle;
    private HSSFCellStyle borderBottomLeftBoldStyle;
    private HSSFCellStyle borderLeftBoldStyle;
    private HSSFCellStyle borderTopLeftBoldStyle;
    private HSSFCellStyle borderTotalBoldStyle;
    private HSSFCellStyle borderTotalBoldCenterStyle;
    private HSSFCellStyle borderBottomBoldGreyStyle;
    private HSSFCellStyle centerBoldStyle;
    private HSSFCellStyle borderTotalBoldBoldStyle;
    private HSSFCellStyle borderTotalBoldBoldCenterStyle;
    private HSSFCellStyle borderTotalBoldBoldRightStyle;
    private HSSFCellStyle borderTotalThinCenterStyle;
    private HSSFCellStyle borderTotalThinGreyStyle;
    private HSSFCellStyle borderTotalBoldCenterGreyStyle;
    private HSSFCellStyle borderTotalThinBoldStyle;
    private HSSFCellStyle borderTotalThinBoldCenterGreyStyle;
    private HSSFCellStyle borderRightThinStyle;
    private HSSFCellStyle greyUnderlineStyle;
    private HSSFCellStyle greyStyle;
    private HSSFCellStyle greyRightStyle;
    
    private HSSFDataFormat dataFormat;
    
    private List<String> listModelId = new ArrayList<String>();
    private List<String> listUnitats = new ArrayList<String>();
    
	private static final String HEADER_PRAGMA = "Pragma";
	private static final String HEADER_EXPIRES = "Expires";
	private static final String HEADER_CACHE_CONTROL = "Cache-Control";
	
	private OliConsultaEjb oliConsultaEjb;
	private OliInfraestructuraEjb oliInfraestructuraEjb;
	private OliUsuariEjb oliUsuariEjb;

    /**
     * @see org.displaytag.export.ExportView#setParameters(TableModel, boolean, boolean, boolean)
     */
    public void setParameters(TableModel tableModel, boolean exportFullList, boolean includeHeader,
        boolean decorateValues)
    {
        this.model = tableModel;
        this.exportFull = exportFullList;
        this.header = includeHeader;
        this.decorated = decorateValues;
    }

    /**
     * @return "application/vnd.ms-excel"
     * @see org.displaytag.export.BaseExportView#getMimeType()
     */
    public String getMimeType()
    {
        return "application/vnd.ms-excel"; //$NON-NLS-1$
    }

    /**
     * @see org.displaytag.export.BinaryExportView#doExport(OutputStream)
     */
	public void doExport(OutputStream out) throws JspException
    {
		try
        {
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet("GestOLI");

            int rowNum = 0;
            int colNum = 0;
            
            this.dataFormat = wb.createDataFormat();
            this.listUnitats.add("l.");
            this.listUnitats.add("kg.");
            this.listUnitats.add("bot.");
            
            boolean autoSizeColums = true;
            
            if (this.header)
            {
            	this.headerStyle = wb.createCellStyle();
                headerStyle.setFillForegroundColor(HSSFColor.BLACK.index);
                headerStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                headerStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
                headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                headerStyle.setWrapText(true);
                headerStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
                headerStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
                headerStyle.setLeftBorderColor(HSSFColor.GREY_40_PERCENT.index);
                headerStyle.setRightBorderColor(HSSFColor.GREY_40_PERCENT.index);
                HSSFFont bold = wb.createFont();
                bold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
                bold.setColor(HSSFColor.YELLOW.index);
                headerStyle.setFont(bold);
                
                HSSFCell cell = null;
                
                
                // Create an header row
            	if (model.getId().contains("partidaRegEla")){
            		
            		sheet.setDefaultColumnWidth(16);
                    sheet.setColumnWidth(0, 3000);
                    sheet.setColumnWidth(1, 4000);
        			sheet.setColumnWidth(2, 3000);
        			sheet.setColumnWidth(3, 3000);
        			sheet.setColumnWidth(4, 3000);
        			sheet.setColumnWidth(5, 4000);
        			sheet.setColumnWidth(6, 3000);
        			sheet.setColumnWidth(7, 3000);
        			sheet.setColumnWidth(8, 2500);
        			sheet.setColumnWidth(9, 2500);
        			sheet.setColumnWidth(10, 2500);
        			sheet.setColumnWidth(11, 3000);
        			sheet.setColumnWidth(12, 3000);
//        			sheet.setColumnWidth(13, 3000);
//        			sheet.setColumnWidth(14, 3000);
//        			sheet.setColumnWidth(15, 3000);
//        			sheet.setColumnWidth(16, 3000);
        			
            		 HSSFRow xlsRow0 = sheet.createRow(rowNum++);
            		 
            		 cell = xlsRow0.createCell(0);
            		 cell.setCellValue(new HSSFRichTextString("NÚM. BOTA"));
            		 cell.setCellStyle(headerStyle);
            		 cell = xlsRow0.createCell(1);
            		 cell.setCellValue(new HSSFRichTextString("NÚM. PARTIDES"));
            		 cell.setCellStyle(headerStyle);
            		 cell = xlsRow0.createCell(2);
            		 cell.setCellValue(new HSSFRichTextString("KG"));
            		 cell.setCellStyle(headerStyle);
            		 cell = xlsRow0.createCell(3);
            		 cell.setCellValue(new HSSFRichTextString("KG CRIBAT"));
            		 cell.setCellStyle(headerStyle);
            		 cell = xlsRow0.createCell(4);
            		 cell.setCellValue(new HSSFRichTextString("TIPUS PRODUCTE"));
            		 cell.setCellStyle(headerStyle);
            		 cell = xlsRow0.createCell(5);
            		 cell.setCellStyle(headerStyle);
            		 cell = xlsRow0.createCell(6);
            		 cell.setCellStyle(headerStyle);
            		 cell = xlsRow0.createCell(7);
            		 cell.setCellValue(new HSSFRichTextString("CONDIMENTACIÓ I FERMENTACIÓ"));
            		 cell.setCellStyle(headerStyle);
            		 cell = xlsRow0.createCell(8);
            		 cell.setCellStyle(headerStyle);
            		 cell = xlsRow0.createCell(9);
            		 cell.setCellStyle(headerStyle);
            		 cell = xlsRow0.createCell(10);
            		 cell.setCellStyle(headerStyle);
            		 cell = xlsRow0.createCell(11);
            		 cell.setCellStyle(headerStyle);
            		 cell = xlsRow0.createCell(12);
            		 cell.setCellStyle(headerStyle);
            	}
                // Create an header row
            	if (model.getId().contains("dp")){
            		
            		sheet.setDefaultColumnWidth(12);
                    sheet.setColumnWidth(0, 3000);
                    sheet.setColumnWidth(1, 4000);
        			sheet.setColumnWidth(2, 3000);
        			sheet.setColumnWidth(3, 3000);
        			sheet.setColumnWidth(4, 4000);
        			sheet.setColumnWidth(5, 3000);
        			sheet.setColumnWidth(6, 3000);
        			sheet.setColumnWidth(7, 2500);
        			sheet.setColumnWidth(8, 2500);
        			sheet.setColumnWidth(9, 2500);
        			sheet.setColumnWidth(10, 3000);
        			sheet.setColumnWidth(11, 3000);
        			
            		 HSSFRow xlsRow0 = sheet.createRow(rowNum++);
            		 HSSFRow xlsRow1 = sheet.createRow(rowNum++);
//            		 HSSFRow xlsRow2 = sheet.createRow(rowNum++);
            		 
            		 cell = xlsRow0.createCell(0);
            		 cell.setCellValue(new HSSFRichTextString("NÚMERO PARTIDA"));
            		 cell.setCellStyle(headerStyle);
            		 cell = xlsRow0.createCell(1);
            		 cell.setCellValue(new HSSFRichTextString("DATA ENTRADA"));
            		 cell.setCellStyle(headerStyle);
            		 cell = xlsRow0.createCell(2);
            		 cell.setCellValue(new HSSFRichTextString("ORIGEN"));
            		 cell.setCellStyle(headerStyle);
            		 cell = xlsRow0.createCell(3);
            		 cell.setCellStyle(headerStyle);
            		 cell = xlsRow0.createCell(4);
            		 cell.setCellStyle(headerStyle);
            		 cell = xlsRow0.createCell(5);
            		 cell.setCellStyle(headerStyle);
            		 cell = xlsRow0.createCell(6);
            		 cell.setCellStyle(headerStyle);
            		 cell = xlsRow0.createCell(7);
            		 cell.setCellValue(new HSSFRichTextString("CONDICIONS TRANSPORT"));
            		 cell.setCellStyle(headerStyle);
            		 cell = xlsRow0.createCell(8);
            		 cell.setCellStyle(headerStyle);
            		 cell = xlsRow0.createCell(9);
            		 cell.setCellStyle(headerStyle);
            		 cell = xlsRow0.createCell(10);
            		 cell.setCellValue(new HSSFRichTextString("TIPUS OLIVA I KGS"));
            		 cell.setCellStyle(headerStyle);
            		 cell = xlsRow0.createCell(11);
            		 cell.setCellStyle(headerStyle);
            		 
            		 cell = xlsRow1.createCell(0);
            		 cell.setCellStyle(headerStyle);
            		 cell = xlsRow1.createCell(1);
            		 cell.setCellStyle(headerStyle);
            		 cell = xlsRow1.createCell(2);
            		 cell.setCellStyle(headerStyle);
            		 cell = xlsRow1.createCell(3);
            		 cell.setCellStyle(headerStyle);
            		 cell = xlsRow1.createCell(4);
            		 cell.setCellValue(new HSSFRichTextString("FINCA"));
            		 cell.setCellStyle(headerStyle);
            		 cell = xlsRow1.createCell(5);
            		 cell.setCellStyle(headerStyle);
            		 cell = xlsRow1.createCell(6);
            		 cell.setCellStyle(headerStyle);
            		 cell = xlsRow1.createCell(7);
            		 cell.setCellStyle(headerStyle);
            		 cell = xlsRow1.createCell(8);
            		 cell.setCellStyle(headerStyle);
            		 cell = xlsRow1.createCell(9);
            		 cell.setCellStyle(headerStyle);
            		 cell = xlsRow1.createCell(10);
            		 cell.setCellStyle(headerStyle);
            		 cell = xlsRow1.createCell(11);
            		 cell.setCellStyle(headerStyle);
            	}
            	if (model.getId().contains("regEnvasat")){
            		
            		sheet.setDefaultColumnWidth(9);
                    sheet.setColumnWidth(0, 4000);
                    sheet.setColumnWidth(1, 4000);
        			sheet.setColumnWidth(2, 2500);
        			sheet.setColumnWidth(3, 2500);
        			sheet.setColumnWidth(4, 2500);
        			sheet.setColumnWidth(5, 5000);
        			sheet.setColumnWidth(6, 4000);
        			sheet.setColumnWidth(7, 4000);
        			sheet.setColumnWidth(8, 6000);
        			sheet.setColumnWidth(9, 4000);
        			sheet.setColumnWidth(10, 4000);
        			sheet.setColumnWidth(11, 6000);
        			sheet.setColumnWidth(12, 4000);
        			
            		 HSSFRow xlsRow0 = sheet.createRow(rowNum++);
            		 
            		 cell = xlsRow0.createCell(0);
            		 cell.setCellValue(new HSSFRichTextString("NÚM. BOTA"));
            		 cell.setCellStyle(headerStyle);
            		 cell = xlsRow0.createCell(1);
            		 cell.setCellValue(new HSSFRichTextString("ENVASAT"));
            		 cell.setCellStyle(headerStyle);
            		 cell = xlsRow0.createCell(2);
            		 cell.setCellStyle(headerStyle);
            		 cell = xlsRow0.createCell(3);
            		 cell.setCellStyle(headerStyle);
            		 cell = xlsRow0.createCell(4);
            		 cell.setCellStyle(headerStyle);
            		 cell = xlsRow0.createCell(5);
            		 cell.setCellStyle(headerStyle);
            		 cell = xlsRow0.createCell(6);
            		 cell.setCellStyle(headerStyle);
            		 cell = xlsRow0.createCell(7);
            		 cell.setCellStyle(headerStyle);
            		 cell = xlsRow0.createCell(8);
            		 cell.setCellValue(new HSSFRichTextString("NÚM LOT"));
            		 cell.setCellStyle(headerStyle);
            		 cell = xlsRow0.createCell(9);
            		 cell.setCellValue(new HSSFRichTextString("COMPROVACIÓ PH"));
            		 cell.setCellStyle(headerStyle);
            		 cell = xlsRow0.createCell(10);
            		 cell.setCellStyle(headerStyle);
            		 cell = xlsRow0.createCell(11);
            		 cell.setCellStyle(headerStyle);
            		 cell = xlsRow0.createCell(12);
            		 cell.setCellStyle(headerStyle);
            	}
				if (model.getId().contains("regSortides")){
					
					sheet.setDefaultColumnWidth(6);
				    sheet.setColumnWidth(0, 6000);
				    sheet.setColumnWidth(1, 4000);
					sheet.setColumnWidth(2, 8000);
					sheet.setColumnWidth(3, 8000);
					sheet.setColumnWidth(4, 4000);
					sheet.setColumnWidth(5, 4000);
					sheet.setColumnWidth(6, 6000);
					
					 HSSFRow xlsRow0 = sheet.createRow(rowNum++);
					 
					 cell = xlsRow0.createCell(0);
					 cell.setCellValue(new HSSFRichTextString("NÚM. LOT"));
					 cell.setCellStyle(headerStyle);
					 cell = xlsRow0.createCell(1);
					 cell.setCellValue(new HSSFRichTextString("DESTINACIÓ"));
					 cell.setCellStyle(headerStyle);
					 cell = xlsRow0.createCell(2);
					 cell.setCellStyle(headerStyle);
					 cell = xlsRow0.createCell(3);
					 cell.setCellStyle(headerStyle);
					 cell = xlsRow0.createCell(4);
					 cell.setCellValue(new HSSFRichTextString("Nº ENVASOS"));
					 cell.setCellStyle(headerStyle);
					 cell = xlsRow0.createCell(5);
					 cell.setCellValue(new HSSFRichTextString("VOLUM"));
					 cell.setCellStyle(headerStyle);
					 cell = xlsRow0.createCell(6);
					 cell.setCellValue(new HSSFRichTextString("CATEGORIA"));
					 cell.setCellStyle(headerStyle);
				}
				if (model.getId().contains("regOlivaCrua")){
					
					sheet.setDefaultColumnWidth(12);
				    sheet.setColumnWidth(0, 8000);
				    sheet.setColumnWidth(1, 4000);
					sheet.setColumnWidth(2, 8000);
					sheet.setColumnWidth(3, 8000);
					sheet.setColumnWidth(4, 4000);
					sheet.setColumnWidth(5, 4000);
					
					 HSSFRow xlsRow0 = sheet.createRow(rowNum++);
					 
					 cell = xlsRow0.createCell(0);
//					 cell.setCellValue(new HSSFRichTextString("DATA SORTIDA"));
					 cell.setCellStyle(headerStyle);
					 cell = xlsRow0.createCell(1);
//					 cell.setCellValue(new HSSFRichTextString("NOM"));
					 cell.setCellStyle(headerStyle);
					 cell = xlsRow0.createCell(2);
//					 cell.setCellValue(new HSSFRichTextString("KILOS"));
					 cell.setCellStyle(headerStyle);
					 cell = xlsRow0.createCell(3);
//					 cell.setCellValue(new HSSFRichTextString("DOCUMENT"));
					 cell.setCellStyle(headerStyle);
					 cell = xlsRow0.createCell(4);
//					 cell.setCellValue(new HSSFRichTextString("DESTINATARI"));
					 cell.setCellStyle(headerStyle);
					 cell = xlsRow0.createCell(5);
//					 cell.setCellValue(new HSSFRichTextString("PAIS"));
					 cell.setCellStyle(headerStyle);
				}
				if (model.getId().contains("regBotaGranel")){
					
					sheet.setDefaultColumnWidth(12);
				    sheet.setColumnWidth(0, 6000);
				    sheet.setColumnWidth(1, 4000);
					sheet.setColumnWidth(2, 8000);
					sheet.setColumnWidth(3, 8000);
					sheet.setColumnWidth(4, 4000);
					sheet.setColumnWidth(5, 4000);
					
					 HSSFRow xlsRow0 = sheet.createRow(rowNum++);
					 
					 cell = xlsRow0.createCell(0);
//					 cell.setCellValue(new HSSFRichTextString("DATA SORTIDA"));
					 cell.setCellStyle(headerStyle);
					 cell = xlsRow0.createCell(1);
//					 cell.setCellValue(new HSSFRichTextString("NOM"));
					 cell.setCellStyle(headerStyle);
					 cell = xlsRow0.createCell(2);
//					 cell.setCellValue(new HSSFRichTextString("KILOS"));
					 cell.setCellStyle(headerStyle);
					 cell = xlsRow0.createCell(3);
//					 cell.setCellValue(new HSSFRichTextString("DOCUMENT"));
					 cell.setCellStyle(headerStyle);
					 cell = xlsRow0.createCell(4);
//					 cell.setCellValue(new HSSFRichTextString("DESTINATARI"));
					 cell.setCellStyle(headerStyle);
					 cell = xlsRow0.createCell(5);
//					 cell.setCellValue(new HSSFRichTextString("PAIS"));
					 cell.setCellStyle(headerStyle);
				}
            	HSSFRow xlsRow = sheet.createRow(rowNum++);
                Iterator iterator = this.model.getHeaderCellList().iterator();
            	
                while (iterator.hasNext())
                {
                    HeaderCell headerCell = (HeaderCell) iterator.next();
                    String columnHeader = headerCell.getTitle();
                    if (columnHeader == null) {
                        columnHeader = StringUtils.capitalize(headerCell.getBeanPropertyName());
                    }
                    if ((columnHeader != null) && (!columnHeader.equals(""))) {
                    	cell = xlsRow.createCell(colNum++);
        	            cell.setCellValue(new HSSFRichTextString(columnHeader));
        	            cell.setCellStyle(headerStyle);
                    }
                }

                if (model.getId().contains("partidaRegEla")){
            		sheet.addMergedRegion(new CellRangeAddress(rowNum - 2, rowNum - 1, 0, 0));
                 	sheet.addMergedRegion(new CellRangeAddress(rowNum - 2, rowNum - 1, 1, 1));
                 	sheet.addMergedRegion(new CellRangeAddress(rowNum - 2, rowNum - 1, 2, 2));
                 	sheet.addMergedRegion(new CellRangeAddress(rowNum - 2, rowNum - 1, 3, 3));
                 	sheet.addMergedRegion(new CellRangeAddress(rowNum - 2, rowNum - 2, 4, 6));
                 	sheet.addMergedRegion(new CellRangeAddress(rowNum - 2, rowNum - 2, 7, 12));
                 	autoSizeColums = false;
            	}
                if (model.getId().contains("dp")){
            		sheet.addMergedRegion(new CellRangeAddress(rowNum - 3, rowNum - 1, 0, 0));
                 	sheet.addMergedRegion(new CellRangeAddress(rowNum - 3, rowNum - 1, 1, 1));
                 	sheet.addMergedRegion(new CellRangeAddress(rowNum - 3, rowNum - 3, 2, 6));
                 	sheet.addMergedRegion(new CellRangeAddress(rowNum - 2, rowNum - 2, 2, 3));
                 	sheet.addMergedRegion(new CellRangeAddress(rowNum - 2, rowNum - 2, 4, 6));
                 	sheet.addMergedRegion(new CellRangeAddress(rowNum - 3, rowNum - 2, 7, 9));
                 	sheet.addMergedRegion(new CellRangeAddress(rowNum - 3, rowNum - 2, 10, 11));
                 	autoSizeColums = false;
            	}
                if (model.getId().contains("regEnvasat")){
            		sheet.addMergedRegion(new CellRangeAddress(rowNum - 2, rowNum - 1, 0, 0));
                 	sheet.addMergedRegion(new CellRangeAddress(rowNum - 2, rowNum - 2, 1, 7));
                 	sheet.addMergedRegion(new CellRangeAddress(rowNum - 2, rowNum - 1, 8, 8));
                 	sheet.addMergedRegion(new CellRangeAddress(rowNum - 2, rowNum - 2, 9, 12));
                 	autoSizeColums = false;
            	}
                if (model.getId().contains("regSortides")){
            		sheet.addMergedRegion(new CellRangeAddress(rowNum - 2, rowNum - 1, 0, 0));
                 	sheet.addMergedRegion(new CellRangeAddress(rowNum - 2, rowNum - 2, 1, 3));
                 	sheet.addMergedRegion(new CellRangeAddress(rowNum - 2, rowNum - 1, 4, 4));
                 	sheet.addMergedRegion(new CellRangeAddress(rowNum - 2, rowNum - 1, 5, 5));
                 	sheet.addMergedRegion(new CellRangeAddress(rowNum - 2, rowNum - 1, 6, 6));
                 	autoSizeColums = false;
            	}
            }
            
            // get the correct iterator (full or partial list according to the exportFull field)
            RowIterator rowIterator = this.model.getRowIterator(this.exportFull);

            createStyles(wb);
            
            
            // Depenent del TableModel haurem de fer una vista o una altre
			if (model.getId().contains("agenciaOliResumMensual")){
				doExportAgenciaOliResumMensual(out, wb, sheet, rowIterator);
				autoSizeColums = true;
				colNum = 16;
			} else if (model.getId().contains("partidaRegEla")){
				doExportEla(out, wb, sheet, rowIterator, rowNum, colNum);
			} else {
				doExportSimple(out, wb, sheet, rowIterator, rowNum, colNum);
			}

            // adjust the column widths
			if (autoSizeColums){
	            int colCount = 0;
	            while (colCount <= colNum) {
	                sheet.autoSizeColumn((short) colCount++);
	            }
			}
			wb.write(out);
        } catch (Exception e) {
            throw new ExcelGenerationException(e);
        }
    }

	/**
	 * Realitza una exportació directe del que es mostra en la taula del displaytag a excel
	 * @param out
	 * @param wb
	 * @param sheet
	 * @param rowIterator
	 * @param rowNum
	 * @param colNumMax
	 * @throws JspException
	 */
	public void doExportSimple(
			OutputStream out,
			HSSFWorkbook wb,
			HSSFSheet sheet,
			RowIterator rowIterator,
			int rowNum,
            int colNumMax) throws JspException {
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
            HSSFRow xlsRow = sheet.createRow(rowNum++);
            int colNum = 0;
            // iterator on columns
            ColumnIterator columnIterator = row.getColumnIterator(this.model.getHeaderCellList());
            
            while (columnIterator.hasNext() && (colNum < colNumMax)) {
                Column column = columnIterator.nextColumn();
                Object value = cleanValue(column.getValue(this.decorated), "");
                if (value.toString().indexOf("inputSubmit") != -1) {
            		value = getInputSubmitValue(value.toString());
            	}
            	if (value.toString().indexOf("<div ") != -1) {
            		value = getDivValue(value.toString());
            	}
                HSSFCell cell = xlsRow.createCell(colNum++);
                cell.setCellStyle(row.getRowNumber() % 2 == 1 ? oddStyle : evenStyle);
                writeCell(value, cell);
            }
            
		}
	}
	
	public void doExportEla(
			OutputStream out,
			HSSFWorkbook wb,
			HSSFSheet sheet,
			RowIterator rowIterator,
			int rowNum,
            int colNumMax) throws JspException {
		
		int iniRow = rowNum;
        int finalRow = rowNum;
        String bota = "";
        boolean odd = false;
        
		while (rowIterator.hasNext()) {
			boolean isBota = true;
			boolean agrupar = false;
			Row row = rowIterator.next();
            HSSFRow xlsRow = sheet.createRow(rowNum++);
            int colNum = 0;
            // iterator on columns
            ColumnIterator columnIterator = row.getColumnIterator(this.model.getHeaderCellList());
            
            while (columnIterator.hasNext() && (colNum < colNumMax)) {
                Column column = columnIterator.nextColumn();
                Object value = cleanValue(column.getValue(this.decorated), "");
                if (value.toString().indexOf("inputSubmit") != -1) {
            		value = getInputSubmitValue(value.toString());
            	}
            	if (value.toString().indexOf("<div ") != -1) {
            		value = getDivValue(value.toString());
            	}
                HSSFCell cell = xlsRow.createCell(colNum++);
                writeCell(value, cell);
                if (isBota) {
                	if ("".equals(bota)) {
                		bota = value.toString();
                	} else 	if (!bota.equals(value.toString())) {
                		finalRow = rowNum - 2;
                		bota = value.toString();
                		agrupar = true;
                		odd = !odd;
                	}
                	isBota = false;
                }
                cell.setCellStyle(odd ? oddStyle : evenStyle);
            }
            if (agrupar) {
            	sheet.addMergedRegion(new CellRangeAddress(iniRow, finalRow, 0, 0));
             	sheet.addMergedRegion(new CellRangeAddress(iniRow, finalRow, 7, 7));
             	sheet.addMergedRegion(new CellRangeAddress(iniRow, finalRow, 8, 8));
             	sheet.addMergedRegion(new CellRangeAddress(iniRow, finalRow, 9, 9));
             	sheet.addMergedRegion(new CellRangeAddress(iniRow, finalRow, 10, 10));
             	sheet.addMergedRegion(new CellRangeAddress(iniRow, finalRow, 11, 11));
             	sheet.addMergedRegion(new CellRangeAddress(iniRow, finalRow, 12, 12));
            	iniRow = finalRow + 1;
            	agrupar = false;
            }
		}
		if (finalRow < rowNum - 2) {
			sheet.addMergedRegion(new CellRangeAddress(finalRow + 1, rowNum - 1, 0, 0));
         	sheet.addMergedRegion(new CellRangeAddress(finalRow + 1, rowNum - 1, 7, 7));
         	sheet.addMergedRegion(new CellRangeAddress(finalRow + 1, rowNum - 1, 8, 8));
         	sheet.addMergedRegion(new CellRangeAddress(finalRow + 1, rowNum - 1, 9, 9));
         	sheet.addMergedRegion(new CellRangeAddress(finalRow + 1, rowNum - 1, 10, 10));
         	sheet.addMergedRegion(new CellRangeAddress(finalRow + 1, rowNum - 1, 11, 11));
         	sheet.addMergedRegion(new CellRangeAddress(finalRow + 1, rowNum - 1, 12, 12));
		}
	}
	
	public void doExportAgenciaOliResumMensual(
			OutputStream out,
			HSSFWorkbook wb,
			HSSFSheet sheet,
			RowIterator rowIterator) throws JspException {
		try{
			
	        // Cream les instancies
	        oliConsultaEjb = ServiceProxy.getInstance().getOliConsultaEjb();
	        oliInfraestructuraEjb = ServiceProxy.getInstance().getOliInfraestructuraEjb();
	        oliUsuariEjb = ServiceProxy.getInstance().getOliUsuariEjb();
	        
	        String[] strDataInici = this.model.getId().split("_"); //Posicions: nom, dataInici, dataFi
	        
	        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	        Date dataInici = formatter.parse(strDataInici[1]); //(Date)rowIterator.next().getColumnIterator(this.model.getHeaderCellList()).nextColumn().getValue(false);
	        Date dataFi = formatter.parse(strDataInici[2]);
	        
	        
	        
	        Campanya campanya = oliInfraestructuraEjb.campanyaAmbId(oliInfraestructuraEjb.campanyaCercaActual().intValue());
	        Usuari usuari = oliUsuariEjb.verificaLogin();
			Establiment establiment = oliInfraestructuraEjb.getEstablecimientoByUsuari(usuari.getId());
			
	        Date dataIniciCampanya = campanya.getData();
			
	        Date dataFiCampanya = campanya.getDataFi();
	        
	        if(dataFiCampanya == null)
	        	dataFiCampanya = new Date();
			
			Long idEstabliment = establiment.getId();
			
			////////////////////////// CONSULTES //////////////////////////////
			List categorias = new ArrayList();
		   	categorias.add(3); //3 = oli qualificat
		   	
		   	double existencia_final_mes = 0.0;
	        double existencia_principi_campanya = 0.0;
    		double total_campanya_entrada = 0.0;
    		double total_campanya_molturada = 0.0;
    		double total_campanya_entradaoli = 0.0;
    		double total_campanya_sortidaoli = 0.0;
    		double total_campanya_sortidaorujo = 0.0;
    		double total_campanya_oliobtingutlitres = 0.0;
	        
			
    		// Cerca tots las diposits de un establecimiento no vacios y que pertenecen a unas determinadas categorias
		   	Collection listaDepositosFinalMes = oliConsultaEjb.findDipositsActiusNoVaciosByEstablecimientoCategoriasAndData(idEstabliment, categorias.toArray(), dataFi);
		   	// Cerca tots los lotes de un establecimiento no vacios y que pertenecen a unas determinadas categorias
	        Collection listaLotesFinalMes = oliConsultaEjb.findLotsNoVendidosByEstablecimientoCategoriasAndData(idEstabliment, categorias.toArray(), dataFi);
	        
	        // Cerca tots las diposits de un establecimiento no vacios y que pertenecen a unas determinadas categorias
		   	Collection listaDepositosPrincipiCampanya = oliConsultaEjb.findDipositsActiusNoVaciosByEstablecimientoCategoriasAndData(idEstabliment, categorias.toArray(), campanya.getData());
		   	// Cerca tots los lotes de un establecimiento no vacios y que pertenecen a unas determinadas categorias
	        Collection listaLotesPrincipiCampanya = oliConsultaEjb.findLotsNoVendidosByEstablecimientoCategoriasAndData(idEstabliment, categorias.toArray(), campanya.getData());
	        
	        Collection entradesOlivaCampanya = oliConsultaEjb.entradesOlivaConsulta(dataIniciCampanya, dataFiCampanya, idEstabliment);
        	Collection elaboracionsCampanya  = oliConsultaEjb.oliElaboratConsulta(dataIniciCampanya, dataFiCampanya, idEstabliment, true, true);
    		Collection sortidesLotsCampanya = oliConsultaEjb.getSortidaLotVendaEntreDiasEnEstablecimiento(dataIniciCampanya, dataFiCampanya, idEstabliment);
    		Collection sortidesGranelCampanya = oliConsultaEjb.getSortidaDipositVendaEntreDiasEnEstablecimiento(dataIniciCampanya, dataFiCampanya, idEstabliment);
    		Collection sortidesOrujoCampanya = oliConsultaEjb.findSortidesOrujoEntreDatesIEstabliment(dataIniciCampanya, dataFiCampanya, idEstabliment, true);
    		
    		
    		for(Iterator it=listaDepositosFinalMes.iterator(); it.hasNext();){
	        	Object[] o = (Object[])it.next();
	        	existencia_final_mes += (Double)o[3]; //3 = kilos
	        }
	        
	        for(Iterator it=listaLotesFinalMes.iterator(); it.hasNext();){
	        	Object[] o = (Object[])it.next();
	        	existencia_final_mes += (Double)o[3]; //3 = kilos
	        }
	        
	        for(Iterator it=listaDepositosPrincipiCampanya.iterator(); it.hasNext();){
	        	Object[] o = (Object[])it.next();
	        	existencia_principi_campanya += (Double)o[3]; //3 = kilos
	        }
	        
	        for(Iterator it=listaLotesPrincipiCampanya.iterator(); it.hasNext();){
	        	Object[] o = (Object[])it.next();
	        	existencia_principi_campanya += (Double)o[3]; //3 = kilos
	        }
			
	        //Emplenam el llistat amb els dies

    		//Emplenam totes les dades
    		for(Iterator it=entradesOlivaCampanya.iterator(); it.hasNext();){
    			PartidaOliva po = (PartidaOliva)it.next();
    			total_campanya_entrada += po.getTotalKilos();
    		}
    		
    		for(Iterator it=elaboracionsCampanya.iterator(); it.hasNext();){
    			Elaboracio e = (Elaboracio)it.next();
    			total_campanya_molturada += e.getTotalKilos();
    			total_campanya_entradaoli += (e.getLitres() * 0.916);
    			total_campanya_oliobtingutlitres += e.getLitres();
    		}
    		
    		for(Iterator it=sortidesLotsCampanya.iterator(); it.hasNext();){
    			SortidaLot sl = (SortidaLot)it.next();
    			total_campanya_sortidaoli += (sl.getVendaLitres() * 0.916);
    		}
    		
    		for(Iterator it=sortidesGranelCampanya.iterator(); it.hasNext();){
    			SortidaDiposit sd = (SortidaDiposit)it.next();
    			total_campanya_sortidaoli += (sd.getLitres() * 0.916);
    		}
    		
    		for(Iterator it=sortidesOrujoCampanya.iterator(); it.hasNext();){
    			SortidaOrujo so = (SortidaOrujo)it.next();
    			total_campanya_sortidaorujo += so.getKilos();
    		}
			///////////////////////////////////////////////////////////////////
			
			
			
		
			//Campanya
			int rowCampanya = 0;
			int colCampanya = 0;
			writeCellStyle("Campaña", creaCell(sheet, rowCampanya, colCampanya));
			writeCellStyle(campanya.getNom(), creaCell(sheet, rowCampanya, colCampanya+1));
			
			//Dades establiment
			int rowEstabliment = rowCampanya+3;
			int colEstabliment = colCampanya;
			
			writeCellStyle("Número RIA", creaCell(sheet, rowEstabliment, colEstabliment, borderTopLeftBoldStyle));
			writeCellStyle(establiment.getCodiRB(), creaCell(sheet, rowEstabliment, colEstabliment+1, borderTopBoldStyle));
			writeCellStyle("NIF", creaCell(sheet, rowEstabliment, colEstabliment+2, borderTopBoldStyle));
			writeCellStyle(establiment.getCif(), creaCell(sheet, rowEstabliment, colEstabliment+3, borderTopRightBoldStyle));
			
			writeCellStyle("Razón social", creaCell(sheet, rowEstabliment+1, colEstabliment, borderLeftBoldStyle));
			sheet.addMergedRegion(new CellRangeAddress(rowEstabliment+1,rowEstabliment+1, colEstabliment+1, colEstabliment+3));
			writeCellStyle(establiment.getNom(), creaCell(sheet, rowEstabliment+1, colEstabliment+1, borderRightBoldStyle));
			writeCellStyle("", creaCell(sheet, rowEstabliment+1, colEstabliment+3, borderRightBoldStyle));
			
			writeCellStyle("Dirección", creaCell(sheet, rowEstabliment+2, colEstabliment, borderLeftBoldStyle));
			sheet.addMergedRegion(new CellRangeAddress(rowEstabliment+2,rowEstabliment+2, colEstabliment+1, colEstabliment+3));
			writeCellStyle(establiment.getDireccio(), creaCell(sheet, rowEstabliment+2, colEstabliment+1, borderRightBoldStyle));
			writeCellStyle("", creaCell(sheet, rowEstabliment+2, colEstabliment+3, borderRightBoldStyle));
			
			writeCellStyle("Localidad", creaCell(sheet, rowEstabliment+3, colEstabliment, borderLeftBoldStyle));
			writeCellStyle(establiment.getPoblacio().getNom(), creaCell(sheet, rowEstabliment+3, colEstabliment+1));
			writeCellStyle("", creaCell(sheet, rowEstabliment+3, colEstabliment+3, borderRightBoldStyle));

			writeCellStyle("Provincia", creaCell(sheet, rowEstabliment+4, colEstabliment, borderLeftBoldStyle));
			writeCellStyle("Illes Balears", creaCell(sheet, rowEstabliment+4, colEstabliment+1));
			writeCellStyle("C.P.", creaCell(sheet, rowEstabliment+4, colEstabliment+2));
			writeCellStyle(establiment.getCodiPostal(), creaCell(sheet, rowEstabliment+4, colEstabliment+3, borderRightBoldStyle));
			
			writeCellStyle("Teléfono", creaCell(sheet, rowEstabliment+5, colEstabliment, borderLeftBoldStyle));
			writeCellStyle(establiment.getTelefon(), creaCell(sheet, rowEstabliment+5, colEstabliment+1));
			writeCellStyle("Fax", creaCell(sheet, rowEstabliment+5, colEstabliment+2));
			writeCellStyle(establiment.getFax(), creaCell(sheet, rowEstabliment+5, colEstabliment+3, borderRightBoldStyle));
			
			writeCellStyle("Correo elect.", creaCell(sheet, rowEstabliment+6, colEstabliment, borderBottomLeftBoldStyle));
			sheet.addMergedRegion(new CellRangeAddress(rowEstabliment+6,rowEstabliment+6, colEstabliment+1, colEstabliment+3));
			writeCellStyle(establiment.getEmail(), creaCell(sheet, rowEstabliment+6, colEstabliment+1, borderBottomBoldGreyStyle));
			writeCellStyle("", creaCell(sheet, rowEstabliment+6, colEstabliment+3, borderBottomRightBoldStyle));

			
			//Moviments d'oli
			int rowMoviment = rowEstabliment + 9;
			int colMoviment = colEstabliment;
			
			writeCell("Movimiento de aceite", creaCell(sheet, rowMoviment, colMoviment, borderTotalThinCenterStyle));
			sheet.addMergedRegion(new CellRangeAddress(rowMoviment,rowMoviment+2,colMoviment,colMoviment));
			writeCell("Oliva virgen", creaCell(sheet, rowMoviment+3, colMoviment, borderTotalThinCenterStyle));
			
			writeCell("Existencias a principio de campaña", creaCell(sheet, rowMoviment, colMoviment+1, borderTotalThinCenterStyle));
			sheet.addMergedRegion(new CellRangeAddress(rowMoviment,rowMoviment+2,colMoviment+1,colMoviment+1));
			HSSFCell cell_existencia_principi_campanya = creaCell(sheet, rowMoviment+3, colMoviment+1, borderTotalThinBoldStyle);
			cell_existencia_principi_campanya.setCellValue(existencia_principi_campanya);
			
			writeCell("Total campaña", creaCell(sheet, rowMoviment, colMoviment+2, borderTotalThinCenterStyle));
			sheet.addMergedRegion(new CellRangeAddress(rowMoviment,rowMoviment,colMoviment+2,colMoviment+3));
			
			writeCell("Entradas de aceite producido", creaCell(sheet, rowMoviment+1, colMoviment+2, borderTotalThinCenterStyle));
			sheet.addMergedRegion(new CellRangeAddress(rowMoviment+1,rowMoviment+2,colMoviment+2,colMoviment+2));
			HSSFCell cell_entrada_total_campanya = creaCell(sheet, rowMoviment+3, colMoviment+2, borderTotalThinBoldStyle);
			cell_entrada_total_campanya.setCellValue(total_campanya_entradaoli);
			
			writeCell("Salidas", creaCell(sheet, rowMoviment+1, colMoviment+3, borderTotalThinCenterStyle));
			sheet.addMergedRegion(new CellRangeAddress(rowMoviment+1,rowMoviment+2,colMoviment+3,colMoviment+3));
			HSSFCell cell_total_campanya_sortidaoli = creaCell(sheet, rowMoviment+3, colMoviment+3, borderTotalThinBoldStyle);
			cell_total_campanya_sortidaoli.setCellValue(total_campanya_sortidaoli);
			
			writeCell("Existencias a final de este mes", creaCell(sheet, rowMoviment, colMoviment+4, borderTotalThinCenterStyle));
			sheet.addMergedRegion(new CellRangeAddress(rowMoviment,rowMoviment+2,colMoviment+4,colMoviment+4));
			HSSFCell cell_existencia_final_mes = creaCell(sheet, rowMoviment+3, colMoviment+4, borderTotalThinBoldStyle);
			cell_existencia_final_mes.setCellValue(existencia_final_mes);
			
			//Detalls sortides oli
			int rowDetallsSortidesOli = rowMoviment + 6;
			int colDetallsSortidesOli = colMoviment;
			
			writeCell("Detalles salidas de aceite", creaCell(sheet, rowDetallsSortidesOli, colDetallsSortidesOli, borderTotalThinCenterStyle));
			sheet.addMergedRegion(new CellRangeAddress(rowDetallsSortidesOli,rowDetallsSortidesOli+1,colDetallsSortidesOli,colDetallsSortidesOli));
			
			writeCell("Mes anterior", creaCell(sheet, rowDetallsSortidesOli+2, colDetallsSortidesOli, borderTotalThinCenterStyle));
			writeCell("Mes actual", creaCell(sheet, rowDetallsSortidesOli+3, colDetallsSortidesOli, borderTotalThinCenterStyle));
			writeCell("Totales", creaCell(sheet, rowDetallsSortidesOli+4, colDetallsSortidesOli, borderTotalThinCenterStyle));
			
			writeCell("Envasadora propia", creaCell(sheet, rowDetallsSortidesOli, colDetallsSortidesOli+1, borderTotalThinCenterStyle));
			sheet.addMergedRegion(new CellRangeAddress(rowDetallsSortidesOli,rowDetallsSortidesOli+1,colDetallsSortidesOli+1,colDetallsSortidesOli+1));
			writeCellStyle("", creaCell(sheet, rowDetallsSortidesOli+2, colDetallsSortidesOli+1, borderTotalThinCenterStyle));
			writeCellStyle("", creaCell(sheet, rowDetallsSortidesOli+3, colDetallsSortidesOli+1, borderTotalThinCenterStyle));
			writeCellStyle("", creaCell(sheet, rowDetallsSortidesOli+4, colDetallsSortidesOli+1, borderTotalThinCenterStyle));
			
			writeCell("Otras entidades", creaCell(sheet, rowDetallsSortidesOli, colDetallsSortidesOli+2, borderTotalThinCenterStyle));
			sheet.addMergedRegion(new CellRangeAddress(rowDetallsSortidesOli,rowDetallsSortidesOli+1,colDetallsSortidesOli+2,colDetallsSortidesOli+2));
			writeCellStyle("", creaCell(sheet, rowDetallsSortidesOli+2, colDetallsSortidesOli+2, borderTotalThinCenterStyle));
			writeCellStyle("", creaCell(sheet, rowDetallsSortidesOli+3, colDetallsSortidesOli+2, borderTotalThinCenterStyle));
			writeCellStyle("", creaCell(sheet, rowDetallsSortidesOli+4, colDetallsSortidesOli+2, borderTotalThinCenterStyle));
			
			writeCell("Almacenar y patrimonio", creaCell(sheet, rowDetallsSortidesOli, colDetallsSortidesOli+3, borderTotalThinCenterStyle));
			sheet.addMergedRegion(new CellRangeAddress(rowDetallsSortidesOli,rowDetallsSortidesOli+1,colDetallsSortidesOli+3,colDetallsSortidesOli+3));
			writeCellStyle("", creaCell(sheet, rowDetallsSortidesOli+2, colDetallsSortidesOli+3, borderTotalThinCenterStyle));
			writeCellStyle("", creaCell(sheet, rowDetallsSortidesOli+3, colDetallsSortidesOli+3, borderTotalThinCenterStyle));
			writeCellStyle("", creaCell(sheet, rowDetallsSortidesOli+4, colDetallsSortidesOli+3, borderTotalThinCenterStyle));
			
			writeCell("Total", creaCell(sheet, rowDetallsSortidesOli, colDetallsSortidesOli+4, borderTotalThinCenterStyle));
			sheet.addMergedRegion(new CellRangeAddress(rowDetallsSortidesOli,rowDetallsSortidesOli+1,colDetallsSortidesOli+4,colDetallsSortidesOli+4));
			writeCellStyle("", creaCell(sheet, rowDetallsSortidesOli+2, colDetallsSortidesOli+4, borderTotalThinCenterStyle));
			writeCellStyle("", creaCell(sheet, rowDetallsSortidesOli+3, colDetallsSortidesOli+4, borderTotalThinCenterStyle));
			writeCellStyle("", creaCell(sheet, rowDetallsSortidesOli+4, colDetallsSortidesOli+4, borderTotalThinCenterStyle));
			
			//Sortides
			int rowSortides = rowDetallsSortidesOli + 7;
			int colSortides = colDetallsSortidesOli;

			writeCell("Salidas (Entradas Almazara) (2)", creaCell(sheet, rowSortides, colSortides, borderTotalThinBoldCenterGreyStyle));
			sheet.addMergedRegion(new CellRangeAddress(rowSortides,rowSortides,colSortides,colSortides+3));
			writeCell("", creaCell(sheet, rowSortides, colSortides+3, borderRightThinStyle));
			
			writeCell("Retiradas payeses", creaCell(sheet, rowSortides+1, colSortides, borderTotalThinGreyStyle));
			sheet.addMergedRegion(new CellRangeAddress(rowSortides+1,rowSortides+1,colSortides,colSortides+2));
			writeCell("", creaCell(sheet, rowSortides+1, colSortides+3, borderTotalThinGreyStyle));
			
			writeCell("Aceite precintado", creaCell(sheet, rowSortides+2, colSortides, borderTotalThinGreyStyle));
			sheet.addMergedRegion(new CellRangeAddress(rowSortides+2,rowSortides+2,colSortides,colSortides+2));
			writeCell("", creaCell(sheet, rowSortides+2, colSortides+3, borderTotalThinGreyStyle));			

			writeCell("Salidas pendiente", creaCell(sheet, rowSortides+3, colSortides, borderTotalThinGreyStyle));
			sheet.addMergedRegion(new CellRangeAddress(rowSortides+3,rowSortides+3,colSortides,colSortides+2));
			writeCell("", creaCell(sheet, rowSortides+3, colSortides+3, borderTotalThinGreyStyle));

			writeCell("AOVE", creaCell(sheet, rowSortides+4, colSortides, greyUnderlineStyle));
			writeCell("", creaCell(sheet, rowSortides+4, colSortides+1, greyStyle));
			writeCell("Total", creaCell(sheet, rowSortides+4, colSortides+2, greyRightStyle));
			writeCell("", creaCell(sheet, rowSortides+4, colSortides+3, greyStyle));
			
			writeCell("AOV", creaCell(sheet, rowSortides+5, colSortides, greyUnderlineStyle));
			writeCell("", creaCell(sheet, rowSortides+5, colSortides+1, greyStyle));
			writeCell("", creaCell(sheet, rowSortides+5, colSortides+2, greyStyle));
			writeCell("", creaCell(sheet, rowSortides+5, colSortides+3, greyStyle));
			
			writeCell("Salidas Envasadora (Envasado Mercado Interior)", creaCell(sheet, rowSortides+7, colSortides, borderTotalThinBoldCenterGreyStyle));
			sheet.addMergedRegion(new CellRangeAddress(rowSortides+7,rowSortides+7,colSortides,colSortides+3));
			writeCell("", creaCell(sheet, rowSortides+7, colSortides+3, borderRightThinStyle));
			
			writeCell("Retiradas payeses", creaCell(sheet, rowSortides+8, colSortides, borderTotalThinGreyStyle));
			sheet.addMergedRegion(new CellRangeAddress(rowSortides+8,rowSortides+8,colSortides,colSortides+2));
			writeCell("", creaCell(sheet, rowSortides+8, colSortides+3, borderTotalThinGreyStyle));
			
			writeCell("Traspaso a envasadora (AOVE)", creaCell(sheet, rowSortides+9, colSortides, borderTotalThinGreyStyle));
			sheet.addMergedRegion(new CellRangeAddress(rowSortides+9,rowSortides+9,colSortides,colSortides+2));
			writeCell("", creaCell(sheet, rowSortides+9, colSortides+3, borderTotalThinGreyStyle));

			writeCell("Salida Embotellado (AOVE)", creaCell(sheet, rowSortides+10, colSortides, borderTotalThinGreyStyle));
			sheet.addMergedRegion(new CellRangeAddress(rowSortides+10,rowSortides+10,colSortides,colSortides+2));
			writeCell("", creaCell(sheet, rowSortides+10, colSortides+3, borderTotalThinGreyStyle));
			
			writeCell("Salida Embotellado (AOV)", creaCell(sheet, rowSortides+11, colSortides, borderTotalThinGreyStyle));
			sheet.addMergedRegion(new CellRangeAddress(rowSortides+11,rowSortides+11,colSortides,colSortides+2));
			writeCell("", creaCell(sheet, rowSortides+11, colSortides+3, borderTotalThinGreyStyle));
			
			//Resum mensual
			int rowResum = rowCampanya;
			int colResum = 8;
			
			writeCell("ANEXO I-e", creaCell(sheet, rowResum, colResum, centerBoldStyle));
			sheet.addMergedRegion(new CellRangeAddress(rowResum,rowResum,colResum,colResum+6)); //first row, last row, first col, last col
			
			writeCell("ALMAZARA: Contabilidad de existencias", creaCell(sheet, rowResum+1, colResum, centerBoldStyle));
			sheet.addMergedRegion(new CellRangeAddress(rowResum+1,rowResum+1,colResum,colResum+6));
			
			writeCell("Resumen mensual de aceite de producción propia (kg)", creaCell(sheet, rowResum+3, colResum, centerBoldStyle));
			sheet.addMergedRegion(new CellRangeAddress(rowResum+3,rowResum+3,colResum,colResum+6));
			
			//Taula amb la informació
			int rowNum = rowResum+5;
			int colNumMax = colResum + 7;
			
			writeCell("Día", creaCell(sheet, rowNum, colResum, borderTotalBoldBoldCenterStyle));
			sheet.addMergedRegion(new CellRangeAddress(rowNum,rowNum+1,colResum,colResum));
			writeCell("", creaCell(sheet, rowNum+1, colResum, borderBottomBoldStyle));
			
			writeCell("", creaCell(sheet, rowNum, colResum+1, borderTotalBoldStyle));
			sheet.addMergedRegion(new CellRangeAddress(rowNum,rowNum,colResum+1,colResum+2));
			
			writeCell("Entrada", creaCell(sheet, rowNum+1, colResum+1, borderTotalBoldCenterStyle));
			writeCell("Molturada", creaCell(sheet, rowNum+1, colResum+2, borderTotalBoldCenterStyle));
			
			writeCell("Productos Obtenidos", creaCell(sheet, rowNum, colResum+3, borderTotalBoldBoldCenterStyle));
			sheet.addMergedRegion(new CellRangeAddress(rowNum,rowNum,colResum+3,colResum+4));
			
			writeCell("Aceite", creaCell(sheet, rowNum+1, colResum+3, borderTotalBoldCenterStyle));
			writeCell("Orujo", creaCell(sheet, rowNum+1, colResum+4, borderTotalBoldCenterStyle));
			
			writeCell("Salida", creaCell(sheet, rowNum, colResum+5, borderTotalBoldBoldCenterStyle));
			sheet.addMergedRegion(new CellRangeAddress(rowNum,rowNum,colResum+5,colResum+6));
			writeCell("", creaCell(sheet, rowNum, colResum+6, borderRightBoldStyle));
			
			writeCell("Aceite", creaCell(sheet, rowNum+1, colResum+5, borderTotalBoldCenterStyle));
			writeCell("Orujo", creaCell(sheet, rowNum+1, colResum+6, borderTotalBoldCenterStyle));
			
			writeCell("Obtenido Aceite (Litros)", creaCell(sheet, rowNum, colResum+7, borderTotalBoldCenterGreyStyle));
			sheet.addMergedRegion(new CellRangeAddress(rowNum,rowNum+1,colResum+7,colResum+7));
			writeCell("", creaCell(sheet, rowNum+1, colResum+7, borderBottomBoldGreyStyle));
			
			
			rowNum = rowNum+2;
			int total_row_ini = rowNum;
			int total_row_fi = rowNum;
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
	            HSSFRow xlsRow = sheet.getRow(rowNum);
	            
	            if(xlsRow == null)
	            	xlsRow = sheet.createRow(rowNum);
	            
	            rowNum++;
	            
	            int colNum = colResum;
	            // iterator on columns
	            ColumnIterator columnIterator = row.getColumnIterator(this.model.getHeaderCellList());
	            
	            while (columnIterator.hasNext() && (colNum <= colNumMax)) {
	                Column column = columnIterator.nextColumn(); 
	                Object value = cleanValue(column.getValue(this.decorated), "");
	                
	                HSSFCell cell = xlsRow.createCell(colNum++);
	                if (colNum > colNumMax || (!columnIterator.hasNext())){
	                	cell.setCellStyle(borderTotalThinGreyStyle);
	                } else {
	                	cell.setCellStyle(cellStyle);
	                }
	                writeCellStyle(value, cell);
	            }
	            if(rowIterator.hasNext()){
	            	total_row_fi++;
	            }
			}
			
			//Total
			HSSFCell celltotal = creaCell(sheet, total_row_fi+1, colResum, borderTotalBoldBoldStyle);
			celltotal.setCellValue("Total Mes");
			
			//Entrada raïm
			CellReference tot_entrada_ini = new CellReference(total_row_ini,colResum+1);
			CellReference tot_entrada_fi = new CellReference(total_row_fi,colResum+1);
			
			HSSFCell cellraim = creaCell(sheet, total_row_fi+1, colResum+1, borderTotalBoldBoldRightStyle);
			cellraim.setCellFormula("ROUND(SUM("+tot_entrada_ini.formatAsString()+":"+tot_entrada_fi.formatAsString()+"), 2)");
			
			//Molturada
			CellReference tot_molturada_ini = new CellReference(total_row_ini,colResum+2);
			CellReference tot_molturada_fi = new CellReference(total_row_fi,colResum+2);
			
			HSSFCell cellmolturada = creaCell(sheet, total_row_fi+1, colResum+2, borderTotalBoldBoldRightStyle);
			cellmolturada.setCellFormula("ROUND(SUM("+tot_molturada_ini.formatAsString()+":"+tot_molturada_fi.formatAsString()+"), 2)");
			
			//Entrada Oli
			CellReference tot_entradaoli_ini = new CellReference(total_row_ini,colResum+3);
			CellReference tot_entradaoli_fi = new CellReference(total_row_fi,colResum+3);
			
			HSSFCell cellentradaoli = creaCell(sheet, total_row_fi+1, colResum+3, borderTotalBoldBoldRightStyle);
			cellentradaoli.setCellFormula("ROUND(SUM("+tot_entradaoli_ini.formatAsString()+":"+tot_entradaoli_fi.formatAsString()+"), 2)");
			
			//Sortida oli
			CellReference tot_sortidaoli_ini = new CellReference(total_row_ini,colResum+4);
			CellReference tot_sortidaoli_fi = new CellReference(total_row_fi,colResum+4);
			
			HSSFCell cellsortidaoli = creaCell(sheet, total_row_fi+1, colResum+4, borderTotalBoldBoldRightStyle);
			cellsortidaoli.setCellFormula("ROUND(SUM("+tot_sortidaoli_ini.formatAsString()+":"+tot_sortidaoli_fi.formatAsString()+"), 2)");
			
			//Sortida orujo
			CellReference tot_sortidaorujo_ini = new CellReference(total_row_ini,colResum+5);
			CellReference tot_sortidaorujo_fi = new CellReference(total_row_fi,colResum+5);
			
			HSSFCell cellsortidaorujo = creaCell(sheet, total_row_fi+1, colResum+5, borderTotalBoldBoldRightStyle);
			cellsortidaorujo.setCellFormula("ROUND(SUM("+tot_sortidaorujo_ini.formatAsString()+":"+tot_sortidaorujo_fi.formatAsString()+"), 2)");
			
			//Oli obtingut
			CellReference tot_olilitres_ini = new CellReference(total_row_ini,colResum+6);
			CellReference tot_olilitres_fi = new CellReference(total_row_fi,colResum+6);
			
			HSSFCell cellolilitres = creaCell(sheet, total_row_fi+1, colResum+6, borderTotalBoldBoldRightStyle);
			cellolilitres.setCellFormula("ROUND(SUM("+tot_olilitres_ini.formatAsString()+":"+tot_olilitres_fi.formatAsString()+"), 2)");
			
			
			//TOTAL CAMPANYA
    		
    		//Total campanya
			HSSFCell celltotalcampanya = creaCell(sheet, total_row_fi+2, colResum, borderTotalBoldBoldStyle);
			celltotalcampanya.setCellValue("Total Campanya");
			
			//Entrada raïm campanya
			HSSFCell cellraimcampanya = creaCell(sheet, total_row_fi+2, colResum+1, borderTotalBoldBoldRightStyle);
			cellraimcampanya.setCellValue(total_campanya_entrada);
			
			//Molturada
			HSSFCell cellmolturadacampanya = creaCell(sheet, total_row_fi+2, colResum+2, borderTotalBoldBoldRightStyle);
			cellmolturadacampanya.setCellValue(total_campanya_molturada);
			
			//Entrada Oli
			HSSFCell cellentradaolicampanya = creaCell(sheet, total_row_fi+2, colResum+3, borderTotalBoldBoldRightStyle);
			cellentradaolicampanya.setCellValue(total_campanya_entradaoli);
			
			//Sortida oli
			HSSFCell cellsortidaolicampanya = creaCell(sheet, total_row_fi+2, colResum+4, borderTotalBoldBoldRightStyle);
			cellsortidaolicampanya.setCellValue(total_campanya_sortidaoli);
			
			//Sortida orujo
			HSSFCell cellsortidaorujocampanya = creaCell(sheet, total_row_fi+2, colResum+5, borderTotalBoldBoldRightStyle);
			cellsortidaorujocampanya.setCellValue(total_campanya_sortidaorujo);
			
			//Oli obtingut
			HSSFCell cellolilitrescampanya = creaCell(sheet, total_row_fi+2, colResum+6, borderTotalBoldBoldRightStyle);
			cellolilitrescampanya.setCellValue(total_campanya_oliobtingutlitres);
			
			
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
    /**
     * Write the value to the cell. Override this method if you have complex data types that may need to be exported.
     * @param value the value of the cell
     * @param cell the cell to write it to
     */

    protected void writeCell(Object value, HSSFCell cell) {
	    	String unitats = comprovaUnitats(value);
	    	int unit = this.listUnitats.indexOf(unitats);
	    	if (comprovarNumeric(value.toString(), unitats)) {
	    		cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
	    		double valor = Double.parseDouble(formatDoble(value.toString(), unitats));
	            cell.setCellValue(valor);
	    		HSSFCellStyle style = cell.getCellStyle();
	    		switch (unit) {
					case 0: cell.setCellStyle(oddOrEven(style, unit)); break;
	    			case 1: cell.setCellStyle(oddOrEven(style, unit)); break;
					case 2: cell.setCellStyle(oddOrEven(style, unit)); break;
					default: cell.setCellStyle(oddOrEven(style, unit)); break;
				}
	    	} else if (value instanceof Date) {
	            cell.setCellValue((Date) value);
	        } else if (value instanceof Calendar) {
	            cell.setCellValue((Calendar) value);
	        } else {
	            cell.setCellValue(new HSSFRichTextString(decodeHTML(stripTags(escapeColumnValue(value)))));
	        }
    }
    
    /**
     * Write the value to the cell without overwritting the style.
     * @param value the value of the cell
     * @param cell the cell to write it to
     */
    protected void writeCellStyle(Object value, HSSFCell cell) {
	    	String unitats = comprovaUnitats(value);
	    	if (comprovarNumeric(value.toString(), unitats)) {
	    		cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
	    		double valor = Double.parseDouble(formatDoble(value.toString(), unitats));
	            cell.setCellValue(valor);
	    	} else if (value instanceof Date) {
	            cell.setCellValue((Date) value);
	        } else if (value instanceof Calendar) {
	            cell.setCellValue((Calendar) value);
	        } else {
	            cell.setCellValue(new HSSFRichTextString(decodeHTML(stripTags(escapeColumnValue(value)))));
	        }
    }

    private HSSFCellStyle oddOrEven(HSSFCellStyle style, int tipus) {
    	HSSFCellStyle outStyle = style;
    	if (style.getFillForegroundColor() == HSSFColor.GREY_25_PERCENT.index) {
    		switch (tipus) {
	    		case 0: outStyle = this.oddStyleLitres; break;
				case 1: outStyle = this.oddStyleKilos; break;
				case 2: outStyle = this.oddStyleBotelles; break;
				default: outStyle = this.oddStyleNumber; break;
    		}
		} else {
			switch (tipus) {
	    		case 0: outStyle = this.evenStyleLitres; break;
				case 1: outStyle = this.evenStyleKilos; break;
				case 2: outStyle = this.evenStyleBotelles; break;
				default: outStyle = this.evenStyleNumber; break;
			}
		}
    	    	
    	
    	return style;
    }
    
    // patch from Karsten Voges
    /**
     * Escape certain values that are not permitted in excel cells.
     * @param rawValue the object value
     * @return the escaped value
     */
    protected String escapeColumnValue(Object rawValue)
    {
        if (rawValue == null)
        {
            return null;
        }
        String returnString = ObjectUtils.toString(rawValue);
        // escape the String to get the tabs, returns, newline explicit as \t \r \n
        returnString = StringEscapeUtils.escapeJava(StringUtils.trimToEmpty(returnString));
        // remove tabs, insert four whitespaces instead
        returnString = StringUtils.replace(StringUtils.trim(returnString), "\\t", "    ");
        // remove the return, only newline valid in excel
        returnString = StringUtils.replace(StringUtils.trim(returnString), "\\r", " ");
        // unescape so that \n gets back to newline
        returnString = StringEscapeUtils.unescapeJava(returnString);
        return returnString;
    }

    /**
     * Wraps IText-generated exceptions.
     * @author Fabrizio Giustina
     * @version $Revision: 1143 $ ($Author: fgiust $)
     */
    static class ExcelGenerationException extends BaseNestableJspTagException
    {

        /**
         * D1597A17A6.
         */
        private static final long serialVersionUID = 899149338534L;

        /**
         * Instantiate a new PdfGenerationException with a fixed message and the given cause.
         * @param cause Previous exception
         */
        public ExcelGenerationException(Throwable cause)
        {
            super(ExcelHssfView.class, Messages.getString("ExcelView.errorexporting"), cause); //$NON-NLS-1$
        }

        /**
         * @see org.displaytag.exception.BaseNestableJspTagException#getSeverity()
         */
        public SeverityEnum getSeverity()
        {
            return SeverityEnum.ERROR;
        }
    }
    
    private String stripTags(String HTMLString) {
        String noHTMLString = "";
        if (HTMLString != null) {
        	noHTMLString = HTMLString.replaceAll("\\<.*?>","");
        }
        return noHTMLString;
    }

    private Object cleanValue(Object item, String mode) {
    	/*
    	 * Clean the object
    	 * 
    	 * \t	The tab character ('\u0009')
    	 * \n 	The newline (line feed) character ('\u000A')
    	 * \r 	The carriage-return character ('\u000D')
    	 */
    	if (mode.equals("F")) {
    		return item.toString().replaceAll("\\t", "").replaceAll("\\n", "").replaceAll("\\r", "").trim();
    	} else {
    		return item.toString().replaceAll("\\t", "").replaceAll("\\n", "").replaceAll("\\r", "").trim();
    	}
    }
    
    private String getInputSubmitValue(String text) {
    	String str = "";
    	String[] inputs = text.split("<input");
    	if (inputs.length > 1) {
	    	for (int i = 0; i < inputs.length; i++) {
	    		if (inputs[i].indexOf("class=\"inputSubmit\"") != -1) {
	    			str = inputs[i].substring(inputs[i].indexOf("value=\"") + 7);
	    			str = str.substring(0, str.indexOf("\""));
	    		}
	    	}
    	} else {
    		str = text;
    	}
    	return str;
    }
    
    private String getDivValue(String text) {
    	try {
    		String up = text.toUpperCase();
    		if (up.contains("TYPE=\"CHECKBOX\"")){
    			if (up.contains("CHECKED")) return "x";
   				return "";
    		}
    		String str = text.substring(text.indexOf("<div "));
        	str = str.substring(str.indexOf(">") + 1).trim();
        	str = str.substring(0, str.indexOf("<")).trim();
        	return str;
    	} catch (Exception ex) {
    		return text;
    	}
    }
    
    private HSSFCell creaCell(HSSFRow fila, int columna) {
    	HSSFCell cell = fila.createCell(columna);
		return cell;
	}
    
    private HSSFCell creaCell(HSSFRow fila, int columna, HSSFCellStyle estil) {
    	HSSFCell cell = fila.createCell(columna);
    	cell.setCellStyle(estil);
		return cell;
	}
    
    private HSSFCell creaCell(HSSFSheet sheet, int numfila, int columna, HSSFCellStyle estil) {
    	HSSFRow fila = sheet.getRow(numfila);
    	
    	if(fila == null){
    		fila = sheet.createRow(numfila);
    	}
    	
    	HSSFCell cell = fila.createCell(columna);
    	cell.setCellStyle(estil);
		return cell;
	}
    
    private HSSFCell creaCell(HSSFSheet sheet, int numfila, int columna) {
    	HSSFRow fila = sheet.getRow(numfila);
    	
    	if(fila == null){
    		fila = sheet.createRow(numfila);
    	}
    	
    	HSSFCell cell = fila.createCell(columna);
		return cell;
	}
    
    private String formatDoble(String value, String text) {
    	String valor = value.trim();
    	valor = valor.replace(text, "").trim();
    	if (valor.indexOf(".") != -1 && valor.indexOf(",") != -1 && valor.indexOf(".") < valor.indexOf(",")){
    		valor = valor.replace(".", "");
    		valor = valor.replace(",", ".");
    	} else if (valor.indexOf(".") != -1 && valor.indexOf(",") != -1){
    		valor = valor.replace(",", "");
    	} else {
    		//valor = valor.replace(".", "");
    		valor = valor.replace(",", ".");
    	}
    	
    	return valor.trim();
    }
    
    private Boolean comprovarNumeric(String text, String unitats) {
    	Boolean esNumeric = false;
    	if (text != null && !text.equals("")){
	    	try {
				esNumeric = StringUtils.isNumeric(formatDoble(text, unitats));
	    	} catch (Exception e) {
			}
    	}
    	return esNumeric;
    }
    
    private String comprovaUnitats(Object text) {
    	for (int i = 0; i < this.listUnitats.size(); i++) {
    		if ((text != null) && (text.toString().trim().endsWith(this.listUnitats.get(i)))) {
    			return this.listUnitats.get(i);
    		}
    	}
    	
    	return "";
    }
    
    private void createStyles(HSSFWorkbook wb) {
    	// Definim la font en negreta.
		HSSFFont bold = wb.createFont();
        bold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        bold.setColor(HSSFColor.BLACK.index);
        
        // Definim la font en negreta.
		HSSFFont underline = wb.createFont();
        underline.setUnderline(HSSFFont.U_SINGLE);
        
        // Estil per les files senars.
        this.oddStyle = wb.createCellStyle();
        this.oddStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        this.oddStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        this.oddStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        this.oddStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        this.oddStyle.setLeftBorderColor(HSSFColor.GREY_40_PERCENT.index);
        this.oddStyle.setRightBorderColor(HSSFColor.GREY_40_PERCENT.index);
        this.oddStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        
        // Estil per les files parells.
        this.evenStyle = wb.createCellStyle();
        this.evenStyle.setFillForegroundColor(HSSFColor.WHITE.index);
        this.evenStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//        this.evenStyle.setRightBorderColor(HSSFColor.WHITE.index);
//        this.evenStyle.setBorderRight(HSSFBorderFormatting.BORDER_THIN);
        this.evenStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        this.evenStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        this.evenStyle.setLeftBorderColor(HSSFColor.GREY_40_PERCENT.index);
        this.evenStyle.setRightBorderColor(HSSFColor.GREY_40_PERCENT.index);
        this.evenStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        
        // Estil per les files senars amb format numèric de litre.
        this.oddStyleLitres = wb.createCellStyle();
        this.oddStyleLitres.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        this.oddStyleLitres.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        this.oddStyleLitres.setDataFormat(this.dataFormat.getBuiltinFormat("#.##0,00 l"));
        this.oddStyleLitres.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        this.oddStyleLitres.setBorderRight(HSSFCellStyle.BORDER_THIN);
        this.oddStyleLitres.setLeftBorderColor(HSSFColor.GREY_40_PERCENT.index);
        this.oddStyleLitres.setRightBorderColor(HSSFColor.GREY_40_PERCENT.index);
      
     	// Estil per les files senars amb format numèric de kilo.
        this.oddStyleKilos = wb.createCellStyle();
        this.oddStyleKilos.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
     	this.oddStyleKilos.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
     	this.oddStyleKilos.setDataFormat(this.dataFormat.getBuiltinFormat("#.##0,00 k\\g"));
     	this.oddStyleKilos.setBorderLeft(HSSFCellStyle.BORDER_THIN);
     	this.oddStyleKilos.setBorderRight(HSSFCellStyle.BORDER_THIN);
     	this.oddStyleKilos.setLeftBorderColor(HSSFColor.GREY_40_PERCENT.index);
     	this.oddStyleKilos.setRightBorderColor(HSSFColor.GREY_40_PERCENT.index);
      
     	// Estil per les files senars amb format numèric de botella.
     	this.oddStyleBotelles = wb.createCellStyle();
     	this.oddStyleBotelles.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
     	this.oddStyleBotelles.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
     	this.oddStyleBotelles.setDataFormat(this.dataFormat.getBuiltinFormat("#.##0,00 \\bot"));
     	this.oddStyleBotelles.setBorderLeft(HSSFCellStyle.BORDER_THIN);
     	this.oddStyleBotelles.setBorderRight(HSSFCellStyle.BORDER_THIN);
     	this.oddStyleBotelles.setLeftBorderColor(HSSFColor.GREY_40_PERCENT.index);
     	this.oddStyleBotelles.setRightBorderColor(HSSFColor.GREY_40_PERCENT.index);
     	
     	// Estil per les files senars amb format numèric.
     	this.oddStyleNumber = wb.createCellStyle();
     	this.oddStyleNumber.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
     	this.oddStyleNumber.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
     	this.oddStyleNumber.setDataFormat(this.dataFormat.getBuiltinFormat("#.##0,00"));
     	this.oddStyleNumber.setBorderLeft(HSSFCellStyle.BORDER_THIN);
     	this.oddStyleNumber.setBorderRight(HSSFCellStyle.BORDER_THIN);
     	this.oddStyleNumber.setLeftBorderColor(HSSFColor.GREY_40_PERCENT.index);
     	this.oddStyleNumber.setRightBorderColor(HSSFColor.GREY_40_PERCENT.index);

     	// Estil per les files parells amb format numèric de litre.
     	this.evenStyleLitres = wb.createCellStyle();
     	this.evenStyleLitres.setFillForegroundColor(HSSFColor.WHITE.index);
     	this.evenStyleLitres.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
     	this.evenStyleLitres.setRightBorderColor(HSSFColor.WHITE.index);
     	this.evenStyleLitres.setDataFormat(this.dataFormat.getBuiltinFormat("#.##0,00 l"));
     	this.evenStyleLitres.setBorderLeft(HSSFCellStyle.BORDER_THIN);
     	this.evenStyleLitres.setBorderRight(HSSFCellStyle.BORDER_THIN);
     	this.evenStyleLitres.setLeftBorderColor(HSSFColor.GREY_40_PERCENT.index);
     	this.evenStyleLitres.setRightBorderColor(HSSFColor.GREY_40_PERCENT.index);
	  
		// Estil per les files parells amb format numèric de kilo.
     	this.evenStyleKilos = wb.createCellStyle();
     	this.evenStyleKilos.setFillForegroundColor(HSSFColor.WHITE.index);
     	this.evenStyleKilos.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
     	this.evenStyleKilos.setRightBorderColor(HSSFColor.WHITE.index);
	 	this.evenStyleKilos.setDataFormat(this.dataFormat.getBuiltinFormat("#.##0,00 k\\g"));
	 	this.evenStyleKilos.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	 	this.evenStyleKilos.setBorderRight(HSSFCellStyle.BORDER_THIN);
	 	this.evenStyleKilos.setLeftBorderColor(HSSFColor.GREY_40_PERCENT.index);
	 	this.evenStyleKilos.setRightBorderColor(HSSFColor.GREY_40_PERCENT.index);
	  
	 	// Estil per les files parells amb format numèric de botella.
	 	this.evenStyleBotelles = wb.createCellStyle();
	 	this.evenStyleBotelles.setFillForegroundColor(HSSFColor.WHITE.index);
	 	this.evenStyleBotelles.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	 	this.evenStyleBotelles.setRightBorderColor(HSSFColor.WHITE.index);
	 	this.evenStyleBotelles.setDataFormat(this.dataFormat.getBuiltinFormat("#.##0,00 \\bot"));
	 	this.evenStyleBotelles.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	 	this.evenStyleBotelles.setBorderRight(HSSFCellStyle.BORDER_THIN);
	 	this.evenStyleBotelles.setLeftBorderColor(HSSFColor.GREY_40_PERCENT.index);
	 	this.evenStyleBotelles.setRightBorderColor(HSSFColor.GREY_40_PERCENT.index);
	 	
	 	// Estil per les files parells amb format numèric de botella.
	 	this.evenStyleNumber = wb.createCellStyle();
	 	this.evenStyleNumber.setFillForegroundColor(HSSFColor.WHITE.index);
	 	this.evenStyleNumber.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	 	this.evenStyleNumber.setRightBorderColor(HSSFColor.WHITE.index);
	 	this.evenStyleNumber.setDataFormat(this.dataFormat.getBuiltinFormat("#.##0,00"));
	 	this.evenStyleNumber.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	 	this.evenStyleNumber.setBorderRight(HSSFCellStyle.BORDER_THIN);
	 	this.evenStyleNumber.setLeftBorderColor(HSSFColor.GREY_40_PERCENT.index);
	 	this.evenStyleNumber.setRightBorderColor(HSSFColor.GREY_40_PERCENT.index);
      
        // Estil pels resultats de sumes.
        this.subTotalStyle = wb.createCellStyle();
        this.subTotalStyle.setFillForegroundColor(HSSFColor.LEMON_CHIFFON.index);
        this.subTotalStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        this.subTotalStyle.setFont(bold);
        this.subTotalStyle.setBorderTop(HSSFBorderFormatting.BORDER_THIN);
        
		// Estil en negreta per les cel·les.
		this.boldStyle = wb.createCellStyle();
		this.boldStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
		this.boldStyle.setWrapText(true);
        this.boldStyle.setFont(bold);
        this.boldStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        this.boldStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        this.boldStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        this.boldStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        
        // Estil per les cel·les.
        this.cellStyle = wb.createCellStyle();
        this.cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
        this.cellStyle.setWrapText(true);
        this.cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        this.cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        this.cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        this.cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        

        // Estilo para los bordes de las celdas
        this.borderTopBoldStyle = wb.createCellStyle();
        this.borderTopBoldStyle.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
        this.borderTopRightBoldStyle = wb.createCellStyle();
        this.borderTopRightBoldStyle.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
        this.borderTopRightBoldStyle.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
        this.borderRightBoldStyle = wb.createCellStyle();
        this.borderRightBoldStyle.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
        this.borderBottomRightBoldStyle = wb.createCellStyle();
        this.borderBottomRightBoldStyle.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
        this.borderBottomRightBoldStyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
        this.borderBottomBoldStyle = wb.createCellStyle();
        this.borderBottomBoldStyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
        this.borderBottomLeftBoldStyle = wb.createCellStyle();
        this.borderBottomLeftBoldStyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
        this.borderBottomLeftBoldStyle.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
        this.borderLeftBoldStyle = wb.createCellStyle();
        this.borderLeftBoldStyle.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
        this.borderTopLeftBoldStyle = wb.createCellStyle();
        this.borderTopLeftBoldStyle.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
        this.borderTopLeftBoldStyle.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
        
        // Borde Total Grueso
        this.borderTotalBoldStyle = wb.createCellStyle();
        this.borderTotalBoldStyle.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
        this.borderTotalBoldStyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
        this.borderTotalBoldStyle.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
        this.borderTotalBoldStyle.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
        
        // Borde Total Grueso + Negrita
        this.borderTotalBoldBoldStyle = wb.createCellStyle();
        this.borderTotalBoldBoldStyle.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
        this.borderTotalBoldBoldStyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
        this.borderTotalBoldBoldStyle.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
        this.borderTotalBoldBoldStyle.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
        this.borderTotalBoldBoldStyle.setFont(bold);

        // Borde Total Grueso + Centrado
        this.borderTotalBoldCenterStyle = wb.createCellStyle();
        this.borderTotalBoldCenterStyle.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
        this.borderTotalBoldCenterStyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
        this.borderTotalBoldCenterStyle.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
        this.borderTotalBoldCenterStyle.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
        this.borderTotalBoldCenterStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        
        // Borde Total Grueso + Negrita + Centrado
        this.borderTotalBoldBoldCenterStyle = wb.createCellStyle();
        this.borderTotalBoldBoldCenterStyle.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
        this.borderTotalBoldBoldCenterStyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
        this.borderTotalBoldBoldCenterStyle.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
        this.borderTotalBoldBoldCenterStyle.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
        this.borderTotalBoldBoldCenterStyle.setFont(bold);
        this.borderTotalBoldBoldCenterStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        
        // Borde Total Grueso + Negrita + Alineado Derecha
        this.borderTotalBoldBoldRightStyle = wb.createCellStyle();
        this.borderTotalBoldBoldRightStyle.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
        this.borderTotalBoldBoldRightStyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
        this.borderTotalBoldBoldRightStyle.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
        this.borderTotalBoldBoldRightStyle.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
        this.borderTotalBoldBoldRightStyle.setFont(bold);
        this.borderTotalBoldBoldRightStyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
        
        // Borde Inferior Grueso + Texto fondo gris
        this.borderBottomBoldGreyStyle = wb.createCellStyle();
        this.borderBottomBoldGreyStyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
        this.borderBottomBoldGreyStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
     	this.borderBottomBoldGreyStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
     	
     	// Borde Total Grueso + Texto fondo gris
     	this.borderTotalBoldCenterGreyStyle = wb.createCellStyle();
     	this.borderTotalBoldCenterGreyStyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
     	this.borderTotalBoldCenterGreyStyle.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
     	this.borderTotalBoldCenterGreyStyle.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
     	this.borderTotalBoldCenterGreyStyle.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
        this.borderTotalBoldCenterGreyStyle.setWrapText(true);
     	this.borderTotalBoldCenterGreyStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
     	this.borderTotalBoldCenterGreyStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        this.borderTotalBoldCenterGreyStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
     	
        // Borde Total Fino + Centrado
        this.borderTotalThinCenterStyle = wb.createCellStyle();
        this.borderTotalThinCenterStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
        this.borderTotalThinCenterStyle.setWrapText(true);
        this.borderTotalThinCenterStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        this.borderTotalThinCenterStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        this.borderTotalThinCenterStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        this.borderTotalThinCenterStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        this.borderTotalThinCenterStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        
        // Borde Total Fino + Texto fondo gris
        this.borderTotalThinGreyStyle = wb.createCellStyle();
        this.borderTotalThinGreyStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        this.borderTotalThinGreyStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        this.borderTotalThinGreyStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        this.borderTotalThinGreyStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        this.borderTotalThinGreyStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
     	this.borderTotalThinGreyStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
     	
        // Borde Total Fino + Texto negrita
        this.borderTotalThinBoldStyle = wb.createCellStyle();
        this.borderTotalThinBoldStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        this.borderTotalThinBoldStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        this.borderTotalThinBoldStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        this.borderTotalThinBoldStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        this.borderTotalThinBoldStyle.setFont(bold);
        
        // Borde Total Fino + Centrado + Texto fondo gris + negrita
        this.borderTotalThinBoldCenterGreyStyle = wb.createCellStyle();
        this.borderTotalThinBoldCenterGreyStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
        this.borderTotalThinBoldCenterGreyStyle.setWrapText(true);
        this.borderTotalThinBoldCenterGreyStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        this.borderTotalThinBoldCenterGreyStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        this.borderTotalThinBoldCenterGreyStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        this.borderTotalThinBoldCenterGreyStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        this.borderTotalThinBoldCenterGreyStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        this.borderTotalThinBoldCenterGreyStyle.setFont(bold);
        this.borderTotalThinBoldCenterGreyStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
     	this.borderTotalThinBoldCenterGreyStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        
     	// Borde Derecho Fino
        this.borderRightThinStyle = wb.createCellStyle();
        this.borderRightThinStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        
        // Texto fondo gris + Subrayado
        this.greyUnderlineStyle = wb.createCellStyle();
        this.greyUnderlineStyle.setFont(underline);
        this.greyUnderlineStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
     	this.greyUnderlineStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
     	
        // Estilo negrita + Centrado
        this.centerBoldStyle = wb.createCellStyle();
        this.centerBoldStyle.setFont(bold);
        this.centerBoldStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        
        // Texto fondo gris
        this.greyStyle = wb.createCellStyle();
        this.greyStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        this.greyStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        
        // Texto fondo gris + Align Right
        this.greyRightStyle = wb.createCellStyle();
        this.greyRightStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        this.greyRightStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        this.greyRightStyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
        
        
    }
    
    private String decodeHTML(String s){
		String resultat = s;
		resultat = resultat.replaceAll("$lt;", "<");
		resultat = resultat.replaceAll("&gt;", ">");
		resultat = resultat.replaceAll("&quot;", "\"");
		resultat = resultat.replaceAll("&amp;", "&");
		resultat = resultat.replaceAll("&#039;", "\'");
		resultat = resultat.replaceAll("&#092;", "\\");
		return resultat;
	}

	
    public void setOliConsultaEjb(OliConsultaEjb oliConsultaEjb) {
		this.oliConsultaEjb = oliConsultaEjb;
	}

	public void setOliInfraestructuraEjb(OliInfraestructuraEjb oliInfraestructuraEjb) {
		this.oliInfraestructuraEjb = oliInfraestructuraEjb;
	}

	public void setOliUsuariEjb(OliUsuariEjb oliUsuariEjb) {
		this.oliUsuariEjb = oliUsuariEjb;
	}

	


    
//	public void setMissatges(Missatges missatges) {
//		this.missatges = missatges;
//	}
}
