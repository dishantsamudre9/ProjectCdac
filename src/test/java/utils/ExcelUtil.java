package utils;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/*
 ExcelUtil class reads data from Excel file.
 Used for Data Driven Testing.
*/

public class ExcelUtil {

	public static Object[][] getLoginData() {

	    Object[][] data = null;

	    try {

	        String path = System.getProperty("user.dir")
	                + "/src/test/resources/testdata.xlsx";

	        System.out.println("Excel Path: " + path);

	        FileInputStream fis = new FileInputStream(path);

	        Workbook wb = new XSSFWorkbook(fis);

	        Sheet sheet = wb.getSheet("LoginData");

	        int rows = sheet.getPhysicalNumberOfRows();
	        int cols = sheet.getRow(0).getPhysicalNumberOfCells();

	        data = new Object[rows - 1][cols];

	        for (int i = 1; i < rows; i++) {

	            Row row = sheet.getRow(i);

	            for (int j = 0; j < cols; j++) {

	                data[i - 1][j] =
	                        row.getCell(j).toString();
	            }
	        }

	        wb.close();

	    } catch (Exception e) {

	        e.printStackTrace();
	    }

	    return data;
	}
}