package reports;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import utilities.ExecutionSummary;

public final class ExcelDashboardWriter {

    private ExcelDashboardWriter() {

    }

    public static void buildDashboard(XSSFWorkbook workbook,
                                      XSSFSheet sheet,
                                      ExecutionSummary summary) {

        // Clear existing rows
        int lastRow = sheet.getLastRowNum();

        for (int i = lastRow; i >= 0; i--) {

            Row row = sheet.getRow(i);

            if (row != null) {
                sheet.removeRow(row);
            }
        }

        // Remove merged regions
        while (sheet.getNumMergedRegions() > 0) {
            sheet.removeMergedRegion(0);
        }

        // Dashboard Title
        createTitle(workbook, sheet);

        // Blank row
        sheet.createRow(1);

        // ==========================
        // Execution Information
        // ==========================
        createSection(workbook,
                sheet,
                2,
                "Execution Information");

        ExcelExecutionInfoWriter.write(
                workbook,
                sheet,
                summary,
                3);

        // Blank row
        sheet.createRow(10);

        // ==========================
        // Execution Statistics
        // ==========================
        createSection(workbook,
                sheet,
                11,
                "Execution Statistics");

        ExcelStatisticsWriter.write(
                workbook,
                sheet,
                summary,
                12);

        sheet.setColumnWidth(0, 9000);
        sheet.setColumnWidth(1, 12000);

        sheet.createFreezePane(0, 2);
    }

    private static void createTitle(XSSFWorkbook workbook,
                                    XSSFSheet sheet) {

        Row row = sheet.createRow(0);
        row.setHeightInPoints(30);

        Cell cell = row.createCell(0);

        cell.setCellValue("AUTOMATION EXECUTION DASHBOARD");

        CellStyle style =
                ExcelStyle.createTitleStyle(workbook);

        cell.setCellStyle(style);

        sheet.addMergedRegion(
                new CellRangeAddress(
                        0,
                        0,
                        0,
                        1));
    }

    private static void createSection(XSSFWorkbook workbook,
                                      XSSFSheet sheet,
                                      int rowNumber,
                                      String title) {

        Row row = sheet.createRow(rowNumber);
        row.setHeightInPoints(24);

        Cell cell = row.createCell(0);

        cell.setCellValue(title);

        cell.setCellStyle(
                ExcelStyle.createSectionStyle(workbook));

        sheet.addMergedRegion(
                new CellRangeAddress(
                        rowNumber,
                        rowNumber,
                        0,
                        1));
    }

}