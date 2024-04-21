package commons;

import java.io.File;
import java.io.FileInputStream;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class excelUtil {

	public static String excelPath=System.getProperty("user.dir")+File.separator+"src/main/resources/testdata/";
	static XSSFWorkbook workbook;
	
	//Fetch data from excel sheet provided as input to data provider .Input : test data name(Excel Name) and sheet name
	public static Object[][] getTestData(String testDataName, String sheetName) {

		Object[][] data = null;

		try {
			FileInputStream excelFile = new FileInputStream(excelPath+testDataName);
			workbook = new XSSFWorkbook(excelFile);
			XSSFSheet sheet = workbook.getSheet(sheetName);

			int rowCount = sheet.getLastRowNum();
			int colCount = sheet.getRow(1).getLastCellNum();

			data = new Object[rowCount][colCount];

			for (int i = 0; i < rowCount; i++) {
				for (int j = 0; j < colCount; j++) {
					data[i][j]=sheet.getRow(i+1).getCell(j).toString();
				}
			}
			workbook.close();
		} catch (Exception ex) {
			ex.getMessage();
		}

		return data;
	}

}
