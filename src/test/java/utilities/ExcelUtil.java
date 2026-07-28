package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import constants.FrameworkConstants;

public final class ExcelUtil {

    private static final String FILE_PATH =
            FrameworkConstants.TESTDATA_PATH + "RegistrationData.xlsx";

    private ExcelUtil() {
        throw new IllegalStateException("Utility class");
    }

    // Read Test Data (Returns Row Number + Input Data)
    public static Object[][] getTestData(String sheetName) {

        try (FileInputStream fis = new FileInputStream(FILE_PATH);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            XSSFSheet sheet = workbook.getSheet(sheetName);

            int rowCount = sheet.getPhysicalNumberOfRows();

            // Read only input columns
            int inputColumns = 4;

            Object[][] data = new Object[rowCount - 1][inputColumns + 1];

            DataFormatter formatter = new DataFormatter();

            for (int i = 1; i < rowCount; i++) {

                // Store Excel Row Number
                data[i - 1][0] = i;

                for (int j = 0; j < inputColumns; j++) {

                    Cell cell = sheet.getRow(i).getCell(j);

                    data[i - 1][j + 1] = formatter.formatCellValue(cell);
                }
            }

            return data;

        } catch (IOException e) {

            throw new RuntimeException("Unable to read Excel file.", e);
        }
    }

    // Get Total Rows
    public static int getRowCount(String sheetName) {

        try (FileInputStream fis = new FileInputStream(FILE_PATH);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            return workbook.getSheet(sheetName).getPhysicalNumberOfRows();

        } catch (IOException e) {

            throw new RuntimeException(e);
        }
    }

    // Get Column Index Using Header
    public static int getColumnIndex(String sheetName, String columnName) {

        try (FileInputStream fis = new FileInputStream(FILE_PATH);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            XSSFSheet sheet = workbook.getSheet(sheetName);

            Row headerRow = sheet.getRow(0);

            for (int i = 0; i < headerRow.getPhysicalNumberOfCells(); i++) {

                if (headerRow.getCell(i).getStringCellValue().trim()
                        .equalsIgnoreCase(columnName)) {

                    return i;
                }
            }

            throw new RuntimeException("Column not found : " + columnName);

        } catch (IOException e) {

            throw new RuntimeException(e);
        }
    }
    
    
    //Overloaded method to find column index using sheet object
    private static int getColumnIndex(XSSFSheet sheet, String columnName) {

        Row headerRow = sheet.getRow(0);

        for (int i = 0; i < headerRow.getPhysicalNumberOfCells(); i++) {

            if (headerRow.getCell(i).getStringCellValue().trim()
                    .equalsIgnoreCase(columnName)) {

                return i;
            }
        }

        throw new RuntimeException("Column not found : " + columnName);
    }

    // Update Cell
    public static void updateCell(String sheetName,
                                  int rowNumber,
                                  String columnName,
                                  String value) {

        try (FileInputStream fis = new FileInputStream(FILE_PATH);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            XSSFSheet sheet = workbook.getSheet(sheetName);

            int columnIndex = getColumnIndex(sheetName, columnName);

            Row row = sheet.getRow(rowNumber);

            if (row == null) {
                row = sheet.createRow(rowNumber);
            }

            Cell cell = row.getCell(columnIndex);

            if (cell == null) {
                cell = row.createCell(columnIndex);
            }

            cell.setCellValue(value);

            fis.close();

            try (FileOutputStream fos = new FileOutputStream(FILE_PATH)) {

                workbook.write(fos);
            }

        } catch (IOException e) {

            throw new RuntimeException("Unable to update Excel.", e);
        }
    }
    

    //Overloaded This method with New Parameters
    public static void updateCell(String tcId,
            String columnName,
            String value) {

    	try (FileInputStream fis = new FileInputStream(FILE_PATH);
    			XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

    			XSSFSheet sheet = workbook.getSheet("Registration");

    			int rowNumber = findRowByTCID(sheet, tcId);

    			int columnIndex = getColumnIndex(sheet, columnName);

    			Row row = sheet.getRow(rowNumber);
	
    			if (row == null) {
    				row = sheet.createRow(rowNumber);
    				}

    			Cell cell = row.getCell(columnIndex);

    			if (cell == null) {
    				cell = row.createCell(columnIndex);
    				}

    			cell.setCellValue(value);

    			try (FileOutputStream fos = new FileOutputStream(FILE_PATH)) {
    				workbook.write(fos);
    			}

    	} catch (IOException e) {
    		throw new RuntimeException("Unable to update Excel.", e);
    	}
}
    
    public static Object[][] getExecutionData(String sheetName) {

        try (FileInputStream fis = new FileInputStream(FILE_PATH);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            XSSFSheet sheet = workbook.getSheet(sheetName);

            DataFormatter formatter = new DataFormatter();

            int rowCount = sheet.getPhysicalNumberOfRows();

            int tcIdColumn = getColumnIndex(sheet, "TC_ID");
            int executeColumn = getColumnIndex(sheet, "Execute");
            int nameColumn = getColumnIndex(sheet, "Name");
            int emailColumn = getColumnIndex(sheet, "Email");
            int phoneColumn = getColumnIndex(sheet, "Phone");
            int addressColumn = getColumnIndex(sheet, "Address");

            ConfigReader config = ConfigReader.getInstance();

            String executionMode = config.getTestExecutionMode().trim().toUpperCase();
            String testCaseIds = config.getTestCaseIds().trim();
            
            List<Object[]> executionData = new ArrayList<>();

            for (int i = 1; i < rowCount; i++) {

                Row row = sheet.getRow(i);

                if (row == null) {
                    continue;
                }

                String tcId = formatter.formatCellValue(row.getCell(tcIdColumn));
                String executeFlag = formatter.formatCellValue(row.getCell(executeColumn));

                // Filtering logic will be implemented next
                // For now, only Execute = Y rows are returned

                if (!shouldExecute(tcId, executeFlag)) {
                    continue;
                }

                System.out.println("--------------------------------");
                System.out.println("TC_ID    : " + tcId);
                System.out.println("Name     : " + formatter.formatCellValue(row.getCell(nameColumn)));
                System.out.println("Email    : " + formatter.formatCellValue(row.getCell(emailColumn)));
                System.out.println("Phone    : " + formatter.formatCellValue(row.getCell(phoneColumn)));
                System.out.println("Address  : " + formatter.formatCellValue(row.getCell(addressColumn)));
                
                executionData.add(new Object[] {
                        tcId,
                        formatter.formatCellValue(row.getCell(nameColumn)),
                        formatter.formatCellValue(row.getCell(emailColumn)),
                        formatter.formatCellValue(row.getCell(phoneColumn)),
                        formatter.formatCellValue(row.getCell(addressColumn))
                });
            }

            return executionData.toArray(new Object[executionData.size()][]);

        } catch (IOException e) {

            throw new RuntimeException("Unable to read Excel file.", e);
        }
    }    
    
    private static boolean shouldExecute(String tcId, String executeFlag) {

        // Skip if Execute column is not Y
        if (!executeFlag.equalsIgnoreCase("Y")) {
            return false;
        }

        ConfigReader config = ConfigReader.getInstance();

        String executionMode = config.getTestExecutionMode().trim().toUpperCase();
        String testCaseIds = config.getTestCaseIds().trim();
      

        switch (executionMode) {

            case "ALL":
                return true;

            case "SINGLE":
                return tcId.equalsIgnoreCase(testCaseIds);

            case "MULTIPLE":
                return isSelectedTestCase(tcId, testCaseIds);

            default:
                throw new RuntimeException(
                        "Invalid execution.mode : " + executionMode);
        }
    }
    
    
    private static int findRowByTCID(XSSFSheet sheet, String tcId) {

        DataFormatter formatter = new DataFormatter();

        int tcIdColumn = getColumnIndex(sheet, "TC_ID");

        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {

            Row row = sheet.getRow(i);

            if (row == null) {
                continue;
            }

            String currentTcId =
                    formatter.formatCellValue(row.getCell(tcIdColumn));

            if (tcId.equalsIgnoreCase(currentTcId)) {
                return i;
            }
        }

        throw new RuntimeException("Test Case ID not found : " + tcId);
    }
    
    private static boolean isSelectedTestCase(String tcId, String testCaseIds) {

        String[] testCases = testCaseIds.split(",");

        for (String testCase : testCases) {

            if (tcId.equalsIgnoreCase(testCase.trim())) {
                return true;
            }
        }

        return false;
    }
    
    public static String[] getTestDataByTCID(String tcId) {

        try (FileInputStream fis = new FileInputStream(FILE_PATH);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            XSSFSheet sheet = workbook.getSheet("Registration");

            DataFormatter formatter = new DataFormatter();

            int rowNumber = findRowByTCID(sheet, tcId);

            Row row = sheet.getRow(rowNumber);

            return new String[] {

                    formatter.formatCellValue(row.getCell(getColumnIndex(sheet, "TC_ID"))),

                    formatter.formatCellValue(row.getCell(getColumnIndex(sheet, "Name"))),

                    formatter.formatCellValue(row.getCell(getColumnIndex(sheet, "Email"))),

                    formatter.formatCellValue(row.getCell(getColumnIndex(sheet, "Phone"))),

                    formatter.formatCellValue(row.getCell(getColumnIndex(sheet, "Address")))
            };

        } catch (IOException e) {

            throw new RuntimeException("Unable to read Excel.", e);

        }
    }
}
