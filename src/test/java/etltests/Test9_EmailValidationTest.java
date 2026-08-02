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
 * Test Name   : Test09_EmailValidationTest
 * Module      : ETL Automation Framework
 * Description : Validate Customer Email between Source and Target
 * Author      : Manikanta
 * ==========================================================
 *
 * Business Scenario:
 * Verify that Customer Email loaded into the Target table
 * exactly matches the Source table.
 *
 * Expected Result:
 * Zero Email mismatches.
 *
 * SQL Logic:
 * Join Source and Target using CUSTOMER_ID and compare EMAIL.
 */
public class Test9_EmailValidationTest extends BaseTest {

    @Test(priority = 9, description = "Validate Customer Email")
    public void validateEmail() {

     

        // SQL Query
        String emailValidationQuery =
                "SELECT "
              + "S.CUSTOMER_ID, "
              + "S.CUSTOMER_NAME, "
              + "S.EMAIL AS SOURCE_EMAIL, "
              + "T.EMAIL AS TARGET_EMAIL "
              + "FROM CUSTOMER_SOURCE S "
              + "INNER JOIN CUSTOMER_TARGET T "
              + "ON S.CUSTOMER_ID = T.CUSTOMER_ID "
              + "WHERE S.EMAIL <> T.EMAIL";

        // Variable Declarations
        Statement statement = null;
        ResultSet resultSet = null;

        int mismatchCount = 0;

        try {

            // Create Statement
            statement = DatabaseUtil.getConnection().createStatement();

            // Execute Query
            resultSet = statement.executeQuery(emailValidationQuery);

            System.out.println("======================================================================");
            System.out.println("                  EMAIL VALIDATION RESULTS");
            System.out.println("======================================================================");

            while (resultSet.next()) {

                mismatchCount++;

                int customerId = resultSet.getInt("CUSTOMER_ID");
                String customerName = resultSet.getString("CUSTOMER_NAME");
                String sourceEmail = resultSet.getString("SOURCE_EMAIL");
                String targetEmail = resultSet.getString("TARGET_EMAIL");

                System.out.println("Customer ID       : " + customerId);
                System.out.println("Customer Name     : " + customerName);
                System.out.println("Source Email      : " + sourceEmail);
                System.out.println("Target Email      : " + targetEmail);
                System.out.println("Validation Status : FAILED");
                System.out.println("----------------------------------------------------------------------");

                ReportManager.info(
                        "Customer ID : " + customerId
                      + " | Name : " + customerName
                      + " | Source Email : " + sourceEmail
                      + " | Target Email : " + targetEmail);

            }

            System.out.println("Total Email Mismatches : " + mismatchCount);
            System.out.println("======================================================================");

            ReportManager.info("Total Email Mismatches : " + mismatchCount);

            Assert.assertEquals(
                    mismatchCount,
                    0,
                    "Email Validation Failed.");

           

        } catch (Exception e) {

            ReportManager.fail(e.getMessage());

            Assert.fail(e.getMessage());

        } finally {

            try {

                if (resultSet != null) {
                    resultSet.close();
                }

                if (statement != null) {
                    statement.close();
                }

            } catch (Exception e) {

                e.printStackTrace();

            }

        }

    }

}