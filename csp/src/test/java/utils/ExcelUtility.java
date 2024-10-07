package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

	static Workbook wb = new XSSFWorkbook();
	static FileOutputStream fileOut;
	static DateFormat dateFormat = new SimpleDateFormat("MMddyyyy");
	static String date1 = dateFormat.format(new Date());
	static String fileName = "CSP_Test_Data_Issues" + "-" + date1;
	//static String filePath = System.getProperty("user.dir") + "\\src\\" + "Test Data Issues";
	static String filePath = System.getProperty("user.dir") + "/src/" + "Test Data Issues";
	static org.apache.poi.ss.usermodel.Sheet sheet; 

	public static void createDir() throws IOException {
		boolean flag = false;
		File dir = new File(filePath);
		if (!dir.exists()) {
			dir.mkdir();
		}
		File[] dirContents = dir.listFiles();

		deleteFilesOlderThan7days(dirContents);
		
		for (int i = 0; i < dirContents.length; i++) {
			if (dirContents[i].getName().equalsIgnoreCase(fileName+".xlsx")) {
				flag = true;
			}
		}

		if (flag) {
			//FileInputStream fis = new FileInputStream(filePath +"\\"+  fileName + ".xlsx");
			FileInputStream fis = new FileInputStream(filePath +"/"+  fileName + ".xlsx");
			wb = new XSSFWorkbook(fis);
		}
	}

	public static void createSheet(String sheetName) {
		boolean flag = false;
		int totalSheets = wb.getNumberOfSheets();
		for (int i = 0; i < totalSheets; i++) {
			if (wb.getSheetAt(i).getSheetName().equalsIgnoreCase(sheetName))
				flag = true;
		}
		if (!flag) {
			sheet = wb.createSheet(sheetName);
			createHeaderRow();
		}
		
		sheet = wb.getSheet(sheetName);
	}

	public static void createHeaderRow()
	{
		CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
	    Font font = sheet.getWorkbook().createFont();
	    font.setBold(true);
	    font.setFontHeightInPoints((short) 12);
	    cellStyle.setFont(font);
	 
	    Row row = sheet.createRow(0);
	    Cell cellTitle = row.createCell(0);
	 
	    cellTitle.setCellStyle(cellStyle);
	    cellTitle.setCellValue("B2B");
	 
	    Cell cellAuthor = row.createCell(1);
	    cellAuthor.setCellStyle(cellStyle);
	    cellAuthor.setCellValue("Product");
	}
	
	public static void addinValidProductToExcel(String sheetName, String product, String b2b) throws IOException
	{
		createDir();
		createSheet(sheetName);
		int b2bColumnID = 0;
		int prodcutColumnID=1;
		int lastRowID = sheet.getPhysicalNumberOfRows();
		Iterator<Row> iterator = sheet.iterator();
        
		/*
		 * while (iterator.hasNext()) { Row nextRow = iterator.next(); Iterator<Cell>
		 * cellIterator = nextRow.cellIterator();
		 * 
		 * while (cellIterator.hasNext()) { Cell cell = cellIterator.next();
		 * 
		 * if(cell.getStringCellValue().contentEquals("B2B")) { b2bColumnID =
		 * cell.getColumnIndex(); }
		 * if(cell.getStringCellValue().contentEquals("Product")) { prodcutColumnID =
		 * cell.getColumnIndex(); } } }
		 */
        Row row = sheet.createRow(lastRowID + 1);
        Cell b2bCol = row.createCell(b2bColumnID);
        b2bCol.setCellValue(b2b);
        
        Cell productCol = row.createCell(prodcutColumnID);
        productCol.setCellValue(product);
        closeExcel();
    }

	public static void closeExcel() throws IOException {
		//fileOut = new FileOutputStream(filePath + "\\" + fileName + ".xlsx");
		fileOut = new FileOutputStream(filePath + "/" + fileName + ".xlsx");
		wb.write(fileOut);
		fileOut.close();
	}
	
	public static void deleteFilesOlderThan7days(File[] dirContents)
	{
		long diff;
		
		for (int i = 0; i < dirContents.length; i++) {
			diff = new Date().getTime() - dirContents[i].lastModified();
			if (diff> (7 * 24 * 60 *60 *1000)) {
				dirContents[i].delete();
			}
		}
	}
}
