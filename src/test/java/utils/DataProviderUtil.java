package utils;

import org.testng.annotations.DataProvider;

public class DataProviderUtil {

    @DataProvider(name = "DataFromExcel")
    public static Object[][] getDataFromExcelFileWithDataProvider() {

        // opening the Excel file
    ExcelUtil.openExcelFile("PetDataToRead", "PetData");

        // Storing converted data to Multi Dimensional Array
    Object[][] dataArray = ExcelUtil.convertListOfList_To_MultiDimensionalArray(ExcelUtil.getValues());

        // closing the Excel file
    ExcelUtil.closingExcelFile();

    return dataArray;

    }
}
