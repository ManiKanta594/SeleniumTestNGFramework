package etltests;

import org.testng.Assert;
import org.testng.annotations.Test;

import etl.BaseTest;
import etl.DatabaseUtil;
import etl.ReportManager;
import etl.SQLQueries;

/**
 * ==========================================================
 * Test Name   : Test02_ColumnCountValidationTest
 * Module      : ETL Automation Framework
 * Description : Validate Source and Target Column Count
 * Author      : Manikanta
 * ==========================================================
 */
public class Test2_ColumnCountValidationTest extends BaseTest {

    @Test(priority = 2,
          description = "Validate Source and Target Column Count")
    public void validateColumnCount() {

        ReportManager.startTest(
                "Column Count Validation",
                "Validate Source and Target Column Count");

        // Execute Queries
        int sourceColumns =
                DatabaseUtil.getRecordCount(SQLQueries.SOURCE_COLUMN_COUNT);

        int targetColumns =
                DatabaseUtil.getRecordCount(SQLQueries.TARGET_COLUMN_COUNT);

        // Print Results
        System.out.println("===========================================");
        System.out.println("Source Column Count : " + sourceColumns);
        System.out.println("Target Column Count : " + targetColumns);
        System.out.println("===========================================");

        // Report Logging
        ReportManager.info("Source Column Count : " + sourceColumns);
        ReportManager.info("Target Column Count : " + targetColumns);

        // Validation
        Assert.assertEquals(
                sourceColumns,
                targetColumns,
                "Column Count Validation Failed.");

        ReportManager.pass("Column Count Validation Passed.");

    }

}