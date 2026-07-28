package reports;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public final class ExcelStyle {

    private ExcelStyle() {

    }

    public static CellStyle createTitleStyle(XSSFWorkbook workbook) {

        CellStyle style = workbook.createCellStyle();

        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short)16);
        font.setColor(IndexedColors.WHITE.getIndex());

        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);

        style.setFillForegroundColor(
                IndexedColors.DARK_BLUE.getIndex());

        style.setFillPattern(
                FillPatternType.SOLID_FOREGROUND);

        applyBorder(style);

        return style;
    }

    public static CellStyle createSectionStyle(XSSFWorkbook workbook) {

        CellStyle style = workbook.createCellStyle();

        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short)12);

        style.setFont(font);

        style.setFillForegroundColor(
                IndexedColors.GREY_25_PERCENT.getIndex());

        style.setFillPattern(
                FillPatternType.SOLID_FOREGROUND);

        applyBorder(style);

        return style;
    }

    public static CellStyle createLabelStyle(XSSFWorkbook workbook) {

        CellStyle style = workbook.createCellStyle();

        Font font = workbook.createFont();
        font.setBold(true);

        style.setFont(font);

        applyBorder(style);

        return style;
    }

    public static CellStyle createValueStyle(XSSFWorkbook workbook) {

        CellStyle style = workbook.createCellStyle();

        applyBorder(style);

        return style;
    }

    public static CellStyle createPassStyle(XSSFWorkbook workbook) {

        CellStyle style = createValueStyle(workbook);

        style.setFillForegroundColor(
                IndexedColors.LIGHT_GREEN.getIndex());

        style.setFillPattern(
                FillPatternType.SOLID_FOREGROUND);

        return style;
    }

    public static CellStyle createFailStyle(XSSFWorkbook workbook) {

        CellStyle style = createValueStyle(workbook);

        style.setFillForegroundColor(
                IndexedColors.ROSE.getIndex());

        style.setFillPattern(
                FillPatternType.SOLID_FOREGROUND);

        return style;
    }

    public static CellStyle createSkipStyle(XSSFWorkbook workbook) {

        CellStyle style = createValueStyle(workbook);

        style.setFillForegroundColor(
                IndexedColors.LIGHT_YELLOW.getIndex());

        style.setFillPattern(
                FillPatternType.SOLID_FOREGROUND);

        return style;
    }

    private static void applyBorder(CellStyle style) {

        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
    }

}