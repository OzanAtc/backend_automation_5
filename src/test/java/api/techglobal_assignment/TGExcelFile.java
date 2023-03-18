package api.techglobal_assignment;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;


public class TGExcelFile {

    static Logger logger = LogManager.getLogger(TGExcelFile.class);

    public static void main(String[] args) throws IOException {


            String excelFilePath = "test_data/TestData_Assignment.xlsx";

            FileInputStream fileInputStream = new FileInputStream(excelFilePath);

            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);

            XSSFSheet sheet = workbook.getSheet("Sheet1");

            String firsName = sheet.getRow(1).getCell(0).getStringCellValue();
            logger.info("First name from the first cell is " + firsName);

            int lastRow = sheet.getLastRowNum();
            logger.info("The last row number is " + lastRow);

            int lastCell = sheet.getRow(1).getLastCellNum();
            logger.info("The last cell number from the file is "+ lastCell);


            for (int r=0; r<=lastRow; r++){
                XSSFRow row = sheet.getRow(r);
                for (int c=0; c<lastCell; c++){

                    XSSFCell cell = row.getCell(c);
                    System.out.print(cell + " | ");
                }
                System.out.println();
            }
        }
    }

