package api.techglobal_assignment;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ReadingExcelFile_Demo {

    static Logger logger = LogManager.getLogger(ReadingExcelFile_Demo.class);

    public static void main(String[] args) throws IOException {


        String filePath = "test_data/Data_new.xlsx";

        FileInputStream fileInputStream = new FileInputStream(filePath);
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet sheet = workbook.getSheet("Sheet1");

        int lastRow = sheet.getLastRowNum();
        logger.debug("The last row number is " + lastRow);





    }
}