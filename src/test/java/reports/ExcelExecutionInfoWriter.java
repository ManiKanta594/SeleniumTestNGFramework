package reports;

import java.time.format.DateTimeFormatter;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import utilities.ExecutionSummary;

public final class ExcelExecutionInfoWriter {

    private ExcelExecutionInfoWriter() {

    }

    public static void write(XSSFWorkbook workbook,
                             XSSFSheet sheet,
                             ExecutionSummary summary,
                             int startRow) {

        CellStyle labelStyle = ExcelStyle.createLabelStyle(workbook);
        CellStyle valueStyle = ExcelStyle.createValueStyle(workbook);

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd-MMM-yyyy hh:mm:ss a");

        createRow(sheet, startRow++, "Browser :", summary.getBrowser(),
                labelStyle, valueStyle);

        createRow(sheet, startRow++, "Environment :", summary.getEnvironment(),
                labelStyle, valueStyle);

        createRow(sheet, startRow++, "Execution Mode :", summary.getExecutionMode(),
                labelStyle, valueStyle);

        createRow(sheet, startRow++, "Driver Mode :", summary.getDriverMode(),
                labelStyle, valueStyle);

        createRow(sheet, startRow++, "Start Time :",
                summary.getStartTime().format(formatter),
                labelStyle, valueStyle);

        createRow(sheet, startRow++, "End Time :",
                summary.getEndTime().format(formatter),
                labelStyle, valueStyle);

        long seconds = summary.getExecutionTimeInSeconds();

        String duration = String.format("%02d:%02d:%02d",
                seconds / 3600,
                (seconds % 3600) / 60,
                seconds % 60);

        createRow(sheet,
                startRow,
                "Execution Duration :",
                duration,
                labelStyle,
                valueStyle);
    }

    private static void createRow(XSSFSheet sheet,
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