package reports;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import utilities.ExecutionSummary;

public final class ExcelStatisticsWriter {

    private ExcelStatisticsWriter() {

    }

    public static void write(XSSFWorkbook workbook,
                             XSSFSheet sheet,
                             ExecutionSummary summary,
                             int startRow) {

        CellStyle labelStyle = ExcelStyle.createLabelStyle(workbook);
        CellStyle valueStyle = ExcelStyle.createValueStyle(workbook);
        CellStyle passStyle = ExcelStyle.createPassStyle(workbook);
        CellStyle failStyle = ExcelStyle.createFailStyle(workbook);
        CellStyle skipStyle = ExcelStyle.createSkipStyle(workbook);

        createRow(sheet, startRow++, "Total Tests :", summary.getTotalTests(),
                labelStyle, valueStyle);

        createRow(sheet, startRow++, "Executed Tests :", summary.getExecutedTests(),
                labelStyle, valueStyle);

        createRow(sheet, startRow++, "Passed Tests :", summary.getPassedTests(),
                labelStyle, passStyle);

        createRow(sheet, startRow++, "Failed Tests :", summary.getFailedTests(),
                labelStyle, failStyle);

        createRow(sheet, startRow++, "Skipped Tests :", summary.getSkippedTests(),
                labelStyle, skipStyle);

        double passPercentage = 0;
        double failPercentage = 0;

        if (summary.getExecutedTests() > 0) {

            passPercentage =
                    (summary.getPassedTests() * 100.0)
                            / summary.getExecutedTests();

            failPercentage =
                    (summary.getFailedTests() * 100.0)
                            / summary.getExecutedTests();
        }

        createPercentageRow(sheet,
                startRow++,
                "Pass Percentage :",
                String.format("%.2f %%", passPercentage),
                labelStyle,
                passStyle);

        createPercentageRow(sheet,
                startRow,
                "Fail Percentage :",
                String.format("%.2f %%", failPercentage),
                labelStyle,
                failStyle);
    }

    private static void createRow(XSSFSheet sheet,
                                  int rowNumber,
                                  String label,
                                  int value,
                                  CellStyle labelStyle,
                                  CellStyle valueStyle) {

        Row row = sheet.createRow(rowNumber);
        row.setHeightInPoints(22);

        Cell labelCell = row.createCell(0);
        labelCell.setCellValue(label);
        labelCell.setCellStyle(labelStyle);

        Cell valueCell = row.createCell(1);
        valueCell.setCellValue(value);
        valueCell.setCellStyle(valueStyle);
    }

    private static void createPercentageRow(XSSFSheet sheet,
                                            int rowNumber,
                                            String label,
                                            String value,
                                            CellStyle labelStyle,
                                            CellStyle valueStyle) {

        Row row = sheet.createRow(rowNumber);
        row.setHeightInPoints(22);

        Cell labelCell = row.createCell(0);
        labelCell.setCellValue(label);
        labelCell.setCellStyle(labelStyle);

        Cell valueCell = row.createCell(1);
        valueCell.setCellValue(value);
        valueCell.setCellStyle(valueStyle);
    }

}