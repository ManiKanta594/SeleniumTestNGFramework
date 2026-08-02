package etltests;

import java.sql.ResultSet;
import java.sql.Statement;

import org.testng.Assert;
import org.testng.annotations.Test;

import etl.BaseTest;
import etl.DatabaseUtil;
import etl.ReportManager;

/**
 * ==========================================================
 * Test Name   : Test24_IncrementalLoadValidationTest
 * Module      : ETL Automation Framework
 * Description : Validate Incremental Data Load
 * Author      : Manikanta
 * ==========================================================
 *
 * Business Scenario
 * ----------------------------------------------------------
 * Only records modified after the previous ETL execution
 * should be loaded into the Target table.
 *
 * Expected Result
 * ----------------------------------------------------------
 * Every modified Source record should exist in Target.
 *
 * SQL Concepts Used
 * ----------------------------------------------------------
 * INNER JOIN
 * NOT EXISTS
 * DATE Comparison
 */

public class ZTest24_IncrementalLoadValidationTest extends BaseTest {

    @Test(priority = 24,
            description = "Validate Incremental Load")
    public void validateIncrementalLoad() {

        // Start Report
        ReportManager.startTest(
                "Incremental Load Validation",
                "Validate Incremental ETL Load");

        // SQL Query
        String incrementalLoadQuery =
                "SELECT "
              + "S.CUSTOMER_ID, "
              + "S.CUSTOMER_NAME, "
              + "S.LAST_UPDATED_DATE "
              + "FROM CUSTOMER_SOURCE S "
              + "WHERE S.LAST_UPDATED_DATE > "
              + "(SELECT MAX(LOAD_DATE) FROM ETL_AUDIT) "
              + "AND NOT EXISTS ( "
              + "SELECT 1 "
              + "FROM CUSTOMER_TARGET T "
              + "WHERE T.CUSTOMER_ID = S.CUSTOMER_ID )";

        Statement statement = null;
        ResultSet resultSet = null;

        int missingIncrementalRecords = 0;

        try {

            statement =
                    DatabaseUtil.getConnection().createStatement();

            resultSet =
                    statement.executeQuery(incrementalLoadQuery);

            System.out.println("============================================================");
            System.out.println("          INCREMENTAL LOAD VALIDATION");
            System.out.println("============================================================");

            while (resultSet.next()) {

                missingIncrementalRecords++;

                int customerId =
                        resultSet.getInt("CUSTOMER_ID");

                String customerName =
                        resultSet.getString("CUSTOMER_NAME");

                java.sql.Date lastUpdated =
                        resultSet.getDate("LAST_UPDATED_DATE");

                System.out.println("Customer ID      : " + customerId);
                System.out.println("Customer Name    : " + customerName);
                System.out.println("Last Updated     : " + lastUpdated);
                System.out.println("Validation       : FAILED");
                System.out.println("------------------------------------------------------------");

                ReportManager.info(
                        "Customer ID : " + customerId
                      + " | Name : " + customerName
                      + " | Last Updated : " + lastUpdated);

            }

            System.out.println("Missing Incremental Records : "
                    + missingIncrementalRecords);

            Assert.assertEquals(
                    missingIncrementalRecords,
                    0,
                    "Incremental Load Validation Failed.");

            ReportManager.pass(
                    "Incremental Load Validation Passed.");

        }

        catch (Exception e) {

            ReportManager.fail(e.getMessage());

            Assert.fail(e.getMessage());

        }

        finally {

            try {

                if (resultSet != null)
                    resultSet.close();

                if (statement != null)
                    statement.close();

            }

            catch (Exception e) {

                e.printStackTrace();

            }

        }

    }

}