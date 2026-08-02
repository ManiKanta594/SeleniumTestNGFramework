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
 * Test Name   : Test28_RejectRecordValidationTest
 * Module      : ETL Automation Framework
 * Description : Validate Rejected Records
 * Author      : Manikanta
 * ==========================================================
 *
 * Business Scenario
 * ----------------------------------------------------------
 * Invalid source records should not be loaded into
 * CUSTOMER_TARGET.
 *
 * They should be stored in ERROR_TABLE along with
 * the proper rejection reason.
 *
 * Expected Result
 * ----------------------------------------------------------
 * Every invalid record should exist in ERROR_TABLE.
 *
 * SQL Concepts Used
 * ----------------------------------------------------------
 * NOT EXISTS
 * EXISTS
 * INNER JOIN
 */

public class ZTest28_RejectRecordValidationTest extends BaseTest {

    @Test(priority = 28,
            description = "Validate Reject Records")
    public void validateRejectRecords() {

        // Start Report
        ReportManager.startTest(
                "Reject Record Validation",
                "Validate Error Table Records");

        // SQL Query
        String rejectValidationQuery =
                "SELECT "
              + "S.CUSTOMER_ID, "
              + "S.CUSTOMER_NAME, "
              + "S.EMAIL "
              + "FROM CUSTOMER_SOURCE S "
              + "WHERE S.EMAIL IS NULL "
              + "AND NOT EXISTS ( "
              + "SELECT 1 "
              + "FROM ERROR_TABLE E "
              + "WHERE E.CUSTOMER_ID = S.CUSTOMER_ID )";

        // Variable Declaration
        Statement statement = null;
        ResultSet resultSet = null;

        int missingRejectedRecords = 0;

        try {

            // Create Statement
            statement =
                    DatabaseUtil.getConnection().createStatement();

            // Execute Query
            resultSet =
                    statement.executeQuery(rejectValidationQuery);

            System.out.println("==============================================================");
            System.out.println("            REJECT RECORD VALIDATION");
            System.out.println("==============================================================");

            while (resultSet.next()) {

                missingRejectedRecords++;

                int customerId =
                        resultSet.getInt("CUSTOMER_ID");

                String customerName =
                        resultSet.getString("CUSTOMER_NAME");

                String email =
                        resultSet.getString("EMAIL");

                System.out.println("Customer ID        : " + customerId);
                System.out.println("Customer Name      : " + customerName);
                System.out.println("Email              : " + email);
                System.out.println("Expected Location  : ERROR_TABLE");
                System.out.println("Validation Status  : FAILED");
                System.out.println("--------------------------------------------------------------");

                ReportManager.info(
                        "Customer ID : " + customerId
                      + " | Name : " + customerName
                      + " | Missing from ERROR_TABLE");

            }

            System.out.println("==============================================================");
            System.out.println("Missing Reject Records : " + missingRejectedRecords);
            System.out.println("==============================================================");

            Assert.assertEquals(
                    missingRejectedRecords,
                    0,
                    "Reject Record Validation Failed.");

            ReportManager.pass(
                    "Reject Record Validation Passed.");

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