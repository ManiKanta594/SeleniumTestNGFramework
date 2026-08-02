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
 * Test Name   : Test25_CDCValidationTest
 * Module      : ETL Automation Framework
 * Description : Validate Change Data Capture (CDC)
 * Author      : Manikanta
 * ==========================================================
 *
 * Business Scenario
 * ----------------------------------------------------------
 * Validate that every record marked for INSERT,
 * UPDATE or DELETE in the CDC table is correctly
 * reflected in the Target table.
 *
 * Expected Result
 * ----------------------------------------------------------
 * No CDC records should be missed.
 *
 * SQL Concepts Used
 * ----------------------------------------------------------
 * EXISTS
 * CASE
 * INNER JOIN
 */

public class ZTest25_CDCValidationTest extends BaseTest {

    @Test(priority = 25,
            description = "Validate Change Data Capture")
    public void validateCDC() {

        
        // SQL Query
        String cdcValidationQuery =
                "SELECT "
              + "C.CUSTOMER_ID, "
              + "C.CUSTOMER_NAME, "
              + "C.OPERATION_TYPE, "
              + "CASE "
              + "WHEN EXISTS ( "
              + "SELECT 1 "
              + "FROM CUSTOMER_TARGET T "
              + "WHERE T.CUSTOMER_ID = C.CUSTOMER_ID ) "
              + "THEN 'AVAILABLE' "
              + "ELSE 'MISSING' "
              + "END AS LOAD_STATUS "
              + "FROM CUSTOMER_CDC C";

        // Variable Declaration
        Statement statement = null;
        ResultSet resultSet = null;

        int failedRecords = 0;

        try {

            // Create Statement
            statement =
                    DatabaseUtil.getConnection().createStatement();

            // Execute Query
            resultSet =
                    statement.executeQuery(cdcValidationQuery);

            System.out.println("==============================================================");
            System.out.println("              CHANGE DATA CAPTURE VALIDATION");
            System.out.println("==============================================================");

            while (resultSet.next()) {

                String loadStatus =
                        resultSet.getString("LOAD_STATUS");

                if ("MISSING".equals(loadStatus)) {

                    failedRecords++;

                    int customerId =
                            resultSet.getInt("CUSTOMER_ID");

                    String customerName =
                            resultSet.getString("CUSTOMER_NAME");

                    String operation =
                            resultSet.getString("OPERATION_TYPE");

                    System.out.println("Customer ID      : " + customerId);
                    System.out.println("Customer Name    : " + customerName);
                    System.out.println("Operation Type   : " + operation);
                    System.out.println("Load Status      : " + loadStatus);
                    System.out.println("Validation       : FAILED");
                    System.out.println("--------------------------------------------------------------");

                    ReportManager.info(
                            "Customer ID : " + customerId
                          + " | Name : " + customerName
                          + " | Operation : " + operation
                          + " | Status : " + loadStatus);

                }

            }

            System.out.println("==============================================================");
            System.out.println("CDC Validation Failures : " + failedRecords);
            System.out.println("==============================================================");

            Assert.assertEquals(
                    failedRecords,
                    0,
                    "CDC Validation Failed.");

           

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