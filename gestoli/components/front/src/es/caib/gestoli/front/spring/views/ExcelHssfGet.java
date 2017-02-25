package es.caib.gestoli.front.spring.views;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelHssfGet {

	/**
	 * This method is used to read the data's from an excel file.
	 * @param fileInputStream - Excel file in InputStream content.
	 */
	public List readExcelFileInputStream(InputStream fileInputStream, String version){
		
		List cellDataList = new ArrayList();
		
		
		if ("2003".equals(version)){
			try{
				/**
				* Create a new instance for POIFSFileSystem class
				*/
				POIFSFileSystem fsFileSystem = new POIFSFileSystem(fileInputStream);
	
				/**
				* Create a new instance for HSSFWorkBook Class
				*/
				HSSFWorkbook workBook = new HSSFWorkbook(fsFileSystem);
				HSSFSheet hssfSheet = workBook.getSheetAt(0);
				
				/**
				* Iterate the rows and cells of the spreadsheet
				* to get all the datas.
				*/
				Iterator rowIterator = hssfSheet.rowIterator();
				while (rowIterator.hasNext()) {
					HSSFRow hssfRow = (HSSFRow) rowIterator.next();
					Iterator iterator = hssfRow.cellIterator();
					List cellTempList = new ArrayList();
					while (iterator.hasNext()) {
						HSSFCell hssfCell = (HSSFCell) iterator.next();
						switch(hssfCell.getCellType()){
	                        case HSSFCell.CELL_TYPE_BLANK: cellTempList.add(""); break;
	                        //case HSSFCell.CELL_TYPE_BOOLEAN: cellTempList.add(hssfCell.getBooleanCellValue()); break;
	                        //case HSSFCell.CELL_TYPE_FORMULA: informacionFila.add(hssfCell.getStringCellValue()) ; break;
	                        case HSSFCell.CELL_TYPE_NUMERIC: 
	                        	cellTempList.add(String.valueOf((int)(hssfCell.getNumericCellValue())));
	                            /*if(DateUtil.isCellDateFormatted(hssfCell)) {
	                              //informacionFila.add(hssfCell.getDateCellValue());
	                              informacionFila.add(ApiSimex.formatearFecha(hssfCell.toString()));
	                            } else {
	                              informacionFila.add(hssfCell.getNumericCellValue());
	                            }*/
	                            break;
	                        case HSSFCell.CELL_TYPE_STRING: cellTempList.add(hssfCell.getStringCellValue()) ; break;
	                        default: cellTempList.add(hssfCell.getStringCellValue()); break;
						}
						//cellTempList.add(hssfCell);
					}
					cellDataList.add(cellTempList);
				}
				
			}catch (Exception r) {
				r.printStackTrace();			
			}
		} else {
		
	
			try	{
	//			FileInputStream fileInputStream = new FileInputStream(fileName);
	
				/**
				* Create a new instance for POIFSFileSystem class
				*/
				//POIFSFileSystem fsFileSystem = new POIFSFileSystem(fileInputStream);
	
				/**
				* Create a new instance for HSSFWorkBook Class
				*/
				//HSSFWorkbook workBook = new HSSFWorkbook(fsFileSystem);
				//HSSFSheet hssfSheet = workBook.getSheetAt(0);
	
				XSSFWorkbook workBook = new XSSFWorkbook(fileInputStream); 
				XSSFSheet hssfSheet = workBook.getSheetAt(0);
	
				/**
				* Iterate the rows and cells of the spreadsheet
				* to get all the datas.
				*/
				Iterator rowIterator = hssfSheet.rowIterator();
	
				while (rowIterator.hasNext()) {
					XSSFRow hssfRow = (XSSFRow) rowIterator.next();
					Iterator iterator = hssfRow.cellIterator();
					List cellTempList = new ArrayList();
					while (iterator.hasNext()) {
						XSSFCell hssfCell = (XSSFCell) iterator.next();
						switch(hssfCell.getCellType()){
                        case XSSFCell.CELL_TYPE_BLANK: cellTempList.add(""); break;
                        //case HSSFCell.CELL_TYPE_BOOLEAN: cellTempList.add(hssfCell.getBooleanCellValue()); break;
                        //case HSSFCell.CELL_TYPE_FORMULA: informacionFila.add(hssfCell.getStringCellValue()) ; break;
                        case XSSFCell.CELL_TYPE_NUMERIC: 
                        	cellTempList.add(String.valueOf((int)(hssfCell.getNumericCellValue())));
                            /*if(DateUtil.isCellDateFormatted(hssfCell)) {
                              //informacionFila.add(hssfCell.getDateCellValue());
                              informacionFila.add(ApiSimex.formatearFecha(hssfCell.toString()));
                            } else {
                              informacionFila.add(hssfCell.getNumericCellValue());
                            }*/
                            break;
                        case XSSFCell.CELL_TYPE_STRING: cellTempList.add(hssfCell.getStringCellValue()) ; break;
                        default: cellTempList.add(hssfCell.getStringCellValue()); break;
					}
					//	cellTempList.add(hssfCell.toString());
					}
					cellDataList.add(cellTempList);
				}
			} catch (Exception e) {
				e.printStackTrace();	
			}
		}
		
		return cellDataList;
	}
	
	/**
	 * Devuelve un listado con el contenido del Excel pasado.
	 * @param byteArray. Excel con formato de byteArray.
	 * @return
	 */
	public List readExcelByteArray(byte[] byteArray){
		InputStream is = new ByteArrayInputStream(byteArray);
		List llistat = readExcelFileInputStream(is, "2003");
		if (llistat.size() == 0){
			is = new ByteArrayInputStream(byteArray);
			llistat = readExcelFileInputStream(is, "2007");
		}
		
		return llistat;
	}
	
}
