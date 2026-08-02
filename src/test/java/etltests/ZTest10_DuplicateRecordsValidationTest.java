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
 * Test Name   : Test10_DuplicateRecordsValidationTest
 * Module      : ETL Automation Framework
 * Description : Validate Duplicate Customer Records
 * Author      : Manikanta
 * ==========================================================
 *
 * Business Scenario:
 * Every CUSTOMER_ID should be unique in the Target table.
 *
 * Expected Result:
 * No duplicate CUSTOMER_ID should exist.
 *
 * SQL Logic:
 * GROUP BY CUSTOMER_ID
 * HAVING COUNT(*) > 1
 */
public class ZTest10_DuplicateRecordsValidationTest extends BaseTest {

    @Test(priority = 10,
          description = "Validate Duplicate Records")
    public void validateDuplicateRecords() {

        ReportManager.startTest(
                "Duplicate Record Validation",
                "Validate Duplicate CUSTOMER_ID");

        // SQL Query
        String duplicateQuery =
                "SELECT CUSTOMER_ID, COUNT(*) TOTAL_RECORDS "
              + "FROM CUSTOMER_TARGET "
              + "GROUP BY CUSTOMER_ID "
              + "HAVING COUNT(*) > 1";

        Statement statement = null;
        ResultSet resultSet = null;

        int duplicateCount = 0;

        try {

            statement =
                    DatabaseUtil.getConnection().createStatement();

            resultSet =
                    statement.executeQuery(duplicateQuery);

            System.out.println("==========================================================");
            System.out.println("         DUPLICATE RECORD VALIDATION");
            System.out.println("==========================================================");

            while (resultSet.next()) {

                duplicateCount++;

                int customerId =
                        resultSet.getInt("CUSTOMER_ID");

                int totalRecords =
                        resultSet.getInt("TOTAL_RECORDS");

                System.out.println("Customer ID       : " + customerId);
                System.out.println("Duplicate Records : " + totalRecords);
                System.out.println("Validation Status : FAILED");
                System.out.println("----------------------------------------------------------");

                ReportManager.info(
                        "Customer ID : "
                        + customerId
                        + " Duplicate Count : "
                        + totalRecords);

            }

            System.out.println("Total Duplicate Customers : "
                    + duplicateCount);

            Assert.assertEquals(
                    duplicateCount,
                    0,
                    "Duplicate Records Found.");

            ReportManager.pass(
                    "No Duplicate Records Found.");

        } catch (Exception e) {

            ReportManager.fail(e.getMessage());

            Assert.fail(e.getMessage());

        } finally {

            try {

                if (resultSet != null)
                    resultSet.close();

                if (statement != null)
                    statement.close();

            } catch (Exception e) {

                e.printStackTrace();

            }

        }

    }

}