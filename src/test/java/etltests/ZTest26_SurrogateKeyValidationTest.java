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
 * Test Name   : Test26_SurrogateKeyValidationTest
 * Module      : ETL Automation Framework
 * Description : Validate Surrogate Key Generation
 * Author      : Manikanta
 * ==========================================================
 *
 * Business Scenario
 * ----------------------------------------------------------
 * Validate every Business Key has a valid
 * Surrogate Key.
 *
 * Validate
 * 1. Surrogate Key is NOT NULL
 * 2. Surrogate Key is greater than Zero
 * 3. One Business Key maps to only one Surrogate Key
 *
 * Expected Result
 * ----------------------------------------------------------
 * Zero Invalid Records
 *
 * SQL Concepts Used
 * ----------------------------------------------------------
 * GROUP BY
 * HAVING
 * COUNT
 */

public class ZTest26_SurrogateKeyValidationTest extends BaseTest {

    @Test(priority = 26,
            description = "Validate Surrogate Key")
    public void validateSurrogateKey() {

        // Start Extent Report
        ReportManager.startTest(
                "Surrogate Key Validation",
                "Validate Business Key Mapping");

        // SQL Query
        String surrogateKeyQuery =
                "SELECT "
              + "CUSTOMER_ID, "
              + "SURROGATE_KEY, "
              + "COUNT(*) TOTAL_RECORDS "
              + "FROM CUSTOMER_TARGET "
              + "WHERE SURROGATE_KEY IS NULL "
              + "OR SURROGATE_KEY <= 0 "
              + "GROUP BY CUSTOMER_ID, SURROGATE_KEY "
              + "HAVING COUNT(*) >= 1";

        // Variable Declaration
        Statement statement = null;
        ResultSet resultSet = null;

        int invalidRecords = 0;

        try {

            // Create Statement
            statement =
                    DatabaseUtil.getConnection().createStatement();

            // Execute Query
            resultSet =
                    statement.executeQuery(surrogateKeyQuery);

            System.out.println("==============================================================");
            System.out.println("          SURROGATE KEY VALIDATION");
            System.out.println("==============================================================");

            while (resultSet.next()) {

                invalidRecords++;

                int customerId =
                        resultSet.getInt("CUSTOMER_ID");

                int surrogateKey =
                        resultSet.getInt("SURROGATE_KEY");

                int totalRecords =
                        resultSet.getInt("TOTAL_RECORDS");

                System.out.println("Customer ID        : " + customerId);
                System.out.println("Surrogate Key      : " + surrogateKey);
                System.out.println("Duplicate Records  : " + totalRecords);
                System.out.println("Validation Status  : FAILED");
                System.out.println("--------------------------------------------------------------");

                ReportManager.info(
                        "Customer ID : " + customerId
                      + " | Surrogate Key : " + surrogateKey
                      + " | Records : " + totalRecords);

            }

            System.out.println("==============================================================");
            System.out.println("Invalid Surrogate Keys : " + invalidRecords);
            System.out.println("==============================================================");

            Assert.assertEquals(
                    invalidRecords,
                    0,
                    "Surrogate Key Validation Failed.");

            ReportManager.pass(
                    "Surrogate Key Validation Passed.");

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