package etl;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Utility class for Excel operations.
 * Used for ETL Test Data and Result Reporting.
 */
public final class ExcelUtil {

    private ExcelUtil() {

    }

    /**
     * Reads data from Excel.
     */
    public static String readCellData(String filePath,
                                      String sheetName,
                                      int rowNumber,
                                      int columnNumber) {

        try (FileInputStream fis = new FileInputStream(filePath);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            XSSFSheet sheet = workbook.getSheet(sheetName);

            DataFormatter formatter = new DataFormatter();

            return formatter.formatCellValue(
                    sheet.getRow(rowNumber).getCell(columnNumber));

        } catch (IOException e) {

            throw new RuntimeException("Unable to read Excel file.", e);

        }

    }

    /**
     * Writes data into Excel.
     */
    public static void writeCellData(String filePath,
                                     String sheetName,
                                     int rowNumber,
                                     int columnNumber,
                                     String value) {

        try (FileInputStream fis = new FileInputStream(filePath);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            XSSFSheet sheet = workbook.getSheet(sheetName);

            Row row = sheet.getRow(rowNumber);

            if (row == null) {
                row = sheet.createRow(rowNumber);
            }

            Cell cell = row.getCell(columnNumber);

            if (cell == null) {
                cell = row.createCell(columnNumber);
            }

            cell.setCellValue(value);

            fis.close();

            FileOutputStream fos = new FileOutputStream(filePath);

            workbook.write(fos);

            fos.close();

        } catch (IOException e) {

            throw new RuntimeException("Unable to write Excel file.", e);

        }

    }

}