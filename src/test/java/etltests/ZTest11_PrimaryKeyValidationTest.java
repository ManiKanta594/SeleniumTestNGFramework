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
 * Test Name   : Test11_PrimaryKeyValidationTest
 * Module      : ETL Automation Framework
 * Description : Validate Primary Key Integrity
 * Author      : Manikanta
 * ==========================================================
 *
 * Business Scenario:
 * Verify every CUSTOMER_ID exists only once in the Target table.
 *
 * Expected Result:
 * No duplicate Primary Keys.
 *
 * SQL Logic:
 * GROUP BY CUSTOMER_ID
 * HAVING COUNT(*) > 1
 */
public class ZTest11_PrimaryKeyValidationTest extends BaseTest {

    @Test(priority = 11,
          description = "Validate Primary Key")

    public void validatePrimaryKey() {

        // Start Report
        ReportManager.startTest(
                "Primary Key Validation",
                "Validate Duplicate Primary Keys");

        // SQL Query
        String primaryKeyValidationQuery =
                "SELECT CUSTOMER_ID, COUNT(*) TOTAL_RECORDS "
              + "FROM CUSTOMER_TARGET "
              + "GROUP BY CUSTOMER_ID "
              + "HAVING COUNT(*) > 1";

        // Variable Declaration
        Statement statement = null;
        ResultSet resultSet = null;

        int duplicatePrimaryKeys = 0;

        try {

            // Create Statement
            statement =
                    DatabaseUtil.getConnection().createStatement();

            // Execute Query
            resultSet =
                    statement.executeQuery(primaryKeyValidationQuery);

            System.out.println("========================================================");
            System.out.println("          PRIMARY KEY VALIDATION RESULTS");
            System.out.println("========================================================");

            while (resultSet.next()) {

                duplicatePrimaryKeys++;

                int customerId =
                        resultSet.getInt("CUSTOMER_ID");

                int totalRecords =
                        resultSet.getInt("TOTAL_RECORDS");

                System.out.println("Customer ID        : " + customerId);
                System.out.println("Duplicate Records  : " + totalRecords);
                System.out.println("Validation Status  : FAILED");
                System.out.println("--------------------------------------------------------");

                ReportManager.info(
                        "Customer ID : "
                      + customerId
                      + " | Duplicate Count : "
                      + totalRecords);

            }

            System.out.println("========================================================");
            System.out.println("Duplicate Primary Keys : "
                    + duplicatePrimaryKeys);
            System.out.println("========================================================");

            Assert.assertEquals(
                    duplicatePrimaryKeys,
                    0,
                    "Duplicate Primary Keys Found.");

            ReportManager.pass(
                    "Primary Key Validation Passed.");

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