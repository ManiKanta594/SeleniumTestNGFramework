package etltests;

import org.testng.Assert;
import org.testng.annotations.Test;

import etl.BaseTest;
import etl.DatabaseUtil;
import etl.ReportManager;
import etl.SQLQueries;

/**
 * ==========================================================
 * Test Name   : Test01_RowCountValidationTest
 * Module      : ETL Automation Framework
 * Description : Validate Source and Target Row Count
 * Author      : Manikanta
 * ==========================================================
 */
public class Test1_RowCountValidationTest extends BaseTest {

    @Test(priority = 1, description = "Validate Source and Target Row Count")
    public void validateRowCount() {

        
        // Execute Source Query
        int sourceCount =
                DatabaseUtil.getRecordCount(SQLQueries.SOURCE_ROW_COUNT);

        // Execute Target Query
        int targetCount =
                DatabaseUtil.getRecordCount(SQLQueries.TARGET_ROW_COUNT);

        // Print Results
        System.out.println("===========================================");
        System.out.println("Source Row Count : " + sourceCount);
        System.out.println("Target Row Count : " + targetCount);
        System.out.println("===========================================");

        // Report Logging
        ReportManager.info("Source Row Count : " + sourceCount);
        ReportManager.info("Target Row Count : " + targetCount);

        // Validation
        Assert.assertEquals(
                sourceCount,
                targetCount,
                "Row Count Validation Failed.");

       

    }

}