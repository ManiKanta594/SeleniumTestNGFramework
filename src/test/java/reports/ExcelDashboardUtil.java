package reports;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import utilities.ExecutionSummary;

public final class ExcelDashboardUtil {

    private ExcelDashboardUtil() {

    }

    public static void generateDashboard(XSSFWorkbook workbook,
                                         XSSFSheet sheet,
                                         ExecutionSummary summary) {

        ExcelDashboardWriter.buildDashboard(
                workbook,
                sheet,
                summary);
    }
}