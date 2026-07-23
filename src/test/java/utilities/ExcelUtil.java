package utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import constants.FrameworkConstants;

public final class ExcelUtil {

    private ExcelUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static Object[][] getTestData(String sheetName) {

        try (FileInputStream fis = new FileInputStream(
                FrameworkConstants.TESTDATA_PATH + "TestData.xlsx");
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            XSSFSheet sheet = workbook.getSheet(sheetName);

            int rowCount = sheet.getPhysicalNumberOfRows();
            int columnCount = sheet.getRow(0).getPhysicalNumberOfCells();

            Object[][] data = new Object[rowCount - 1][columnCount];

            DataFormatter formatter = new DataFormatter();

            for (int i = 1; i < rowCount; i++) {

                for (int j = 0; j < columnCount; j++) {

                    data[i - 1][j] = formatter.formatCellValue(
                            sheet.getRow(i).getCell(j));
                }
            }

            return data;

        } catch (IOException e) {

            throw new RuntimeException("Unable to read Excel file.", e);
        }
    }
}