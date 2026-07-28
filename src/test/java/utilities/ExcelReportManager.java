package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import constants.FrameworkConstants;
import reports.ExcelDashboardUtil;

public final class ExcelReportManager {

    private static final String FILE_PATH =
            FrameworkConstants.TESTDATA_PATH + "RegistrationData.xlsx";

    private static final String SUMMARY_SHEET = "Execution Summary";

    private ExcelReportManager() {

    }

    /**
     * Creates Execution Summary sheet if it does not exist.
     */
    public static void createSummarySheet() {

        try (FileInputStream fis = new FileInputStream(FILE_PATH);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            XSSFSheet sheet = workbook.getSheet(SUMMARY_SHEET);

            if (sheet == null) {

                workbook.createSheet(SUMMARY_SHEET);

                try (FileOutputStream fos = new FileOutputStream(FILE_PATH)) {
                    workbook.write(fos);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException("Unable to create Execution Summary sheet.", e);
        }
    }

    /**
     * Writes execution dashboard.
     */
    public static void writeExecutionSummary(ExecutionSummary summary) {

        try (FileInputStream fis = new FileInputStream(FILE_PATH);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            XSSFSheet sheet = workbook.getSheet(SUMMARY_SHEET);

            if (sheet == null) {
                sheet = workbook.createSheet(SUMMARY_SHEET);
            }

            ExcelDashboardUtil.generateDashboard(
                    workbook,
                    sheet,
                    summary);

            try (FileOutputStream fos = new FileOutputStream(FILE_PATH)) {
                workbook.write(fos);
            }

        } catch (IOException e) {
            throw new RuntimeException("Unable to write execution summary.", e);
        }
    }
}