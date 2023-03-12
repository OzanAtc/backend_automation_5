package apachi_poi_excel;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

public class WritingMultipleDataToExcel {

    public static void main(String[] args) throws IOException {

        String filePath = "test_data/WritingData.xlsx";

        XSSFWorkbook workbook = new XSSFWorkbook();

        XSSFSheet sheet = workbook.createSheet("Sheet 2");

        Object[][] employeeData = {

                {"EmpID", "Name", "Title"},
                {101 , "TechGlobal", "DevOps"},
                {102 , "Ulan", "Developer"},
                {103 , "Abe", "Instructor"},
        };

        //getting the length of the multidimensional array
        //it will be our reference that for number of rows we will put into the excel file.
        int rowLength = employeeData.length;
        System.out.println("Length of the multidimensional array " + rowLength);

        //getting the cell length on the row
        int cellLength = employeeData[0].length;
        System.out.println("Length of the multidimensional array " + cellLength);

        //Creating the rows on the Excel file

        for (int r = 0; r < rowLength; r++) {
            //getting the corresponding row
            XSSFRow row = sheet.createRow(r);
            for (int c = 0; c < cellLength; c++) {
                XSSFCell cell = row.createCell(c);

                // Getting the corresponding data from the employeeData based on the
                // indexes of the nest loop
                Object cellValue = employeeData[r][c];
                // check each data from the multidimensional array and write it into the excel file.


                if (cellValue instanceof String)
                    cell.setCellValue((String) cellValue);

                if (cellValue instanceof Integer)
                    cell.setCellValue((Integer) cellValue);

                if (cellValue instanceof Boolean)
                    cell.setCellValue((Boolean) cellValue);

            }
        }

        FileOutputStream fileOutputStream = new FileOutputStream(filePath);
        workbook.write(fileOutputStream);
        workbook.close();

    }
}