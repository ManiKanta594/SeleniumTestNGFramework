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
 * Test Name   : Test14_LengthAndTrimValidationTest
 * Module      : ETL Automation Framework
 * Description : Validate Length, Blank Values and Trim Spaces
 * Author      : Manikanta
 * ==========================================================
 *
 * Business Scenario
 * ----------------------------------------------------------
 * Customer Name
 * should
 *
 * 1. Not be Blank
 * 2. Not contain Leading Spaces
 * 3. Not contain Trailing Spaces
 * 4. Length should not exceed 50 Characters
 *
 * Expected Result
 * ----------------------------------------------------------
 * Zero Invalid Records
 */

public class ZTest14_LengthAndTrimValidationTest extends BaseTest {

    @Test(priority = 14,
          description = "Validate Length and Trim")

    public void validateLengthAndTrim() {

       
        // SQL Query
        String validationQuery =
                "SELECT CUSTOMER_ID, "
              + "CUSTOMER_NAME "
              + "FROM CUSTOMER_TARGET "
              + "WHERE LENGTH(CUSTOMER_NAME) > 50 "
              + "OR CUSTOMER_NAME <> TRIM(CUSTOMER_NAME) "
              + "OR TRIM(CUSTOMER_NAME) IS NULL";

        // Variable Declaration
        Statement statement = null;
        ResultSet resultSet = null;

        int invalidRecordCount = 0;

        try {

            // Create Statement
            statement =
                    DatabaseUtil.getConnection().createStatement();

            // Execute Query
            resultSet =
                    statement.executeQuery(validationQuery);

            System.out.println("==============================================================");
            System.out.println("         LENGTH / TRIM VALIDATION RESULTS");
            System.out.println("==============================================================");

            while(resultSet.next()) {

                invalidRecordCount++;

                int customerId =
                        resultSet.getInt("CUSTOMER_ID");

                String customerName =
                        resultSet.getString("CUSTOMER_NAME");

                System.out.println("Customer ID       : " + customerId);
                System.out.println("Customer Name     : " + customerName);
                System.out.println("Length            : " + customerName.length());
                System.out.println("Validation Status : FAILED");
                System.out.println("--------------------------------------------------------------");

                ReportManager.info(
                        "Customer ID : "
                      + customerId
                      + " | Customer Name : "
                      + customerName);

            }

            System.out.println("==============================================================");
            System.out.println("Invalid Records : " + invalidRecordCount);
            System.out.println("==============================================================");

            Assert.assertEquals(
                    invalidRecordCount,
                    0,
                    "Length / Trim Validation Failed.");

            

        }

        catch(Exception e) {

            ReportManager.fail(e.getMessage());

            Assert.fail(e.getMessage());

        }

        finally {

            try {

                if(resultSet != null)
                    resultSet.close();

                if(statement != null)
                    statement.close();

            }

            catch(Exception e) {

                e.printStackTrace();

            }

        }

    }

}