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
 * Test Name   : Test08_CityValidationTest
 * Module      : ETL Automation Framework
 * Description : Validate City between Source and Target
 * Author      : Manikanta
 * ==========================================================
 *
 * Business Scenario:
 * Verify that the customer city loaded into the Target table
 * exactly matches the city available in the Source table.
 *
 * Expected Result:
 * Zero city mismatches.
 *
 * SQL Logic:
 * INNER JOIN Source and Target using CUSTOMER_ID and identify
 * records where Source City and Target City are different.
 */
public class Test8_CityValidationTest extends BaseTest {

    @Test(priority = 8, description = "Validate Customer City")
    public void validateCity() {

       

        // SQL Query used to identify City mismatches
        String cityValidationQuery =
                "SELECT "
              + "S.CUSTOMER_ID, "
              + "S.CUSTOMER_NAME, "
              + "S.CITY AS SOURCE_CITY, "
              + "T.CITY AS TARGET_CITY "
              + "FROM CUSTOMER_SOURCE S "
              + "INNER JOIN CUSTOMER_TARGET T "
              + "ON S.CUSTOMER_ID = T.CUSTOMER_ID "
              + "WHERE S.CITY <> T.CITY";

        // Database Variables
        Statement statement = null;
        ResultSet resultSet = null;

        // Stores total number of mismatched records
        int mismatchCount = 0;

        try {

            // Create SQL Statement
            statement = DatabaseUtil.getConnection().createStatement();

            // Execute City Validation Query
            resultSet = statement.executeQuery(cityValidationQuery);

            System.out.println("==============================================================");
            System.out.println("                 CITY VALIDATION RESULTS");
            System.out.println("==============================================================");

            // Read all mismatched records
            while (resultSet.next()) {

                mismatchCount++;

                // Get values from ResultSet
                int customerId =
                        resultSet.getInt("CUSTOMER_ID");

                String customerName =
                        resultSet.getString("CUSTOMER_NAME");

                String sourceCity =
                        resultSet.getString("SOURCE_CITY");

                String targetCity =
                        resultSet.getString("TARGET_CITY");

                // Print mismatch details
                System.out.println("Customer ID       : " + customerId);
                System.out.println("Customer Name     : " + customerName);
                System.out.println("Source City       : " + sourceCity);
                System.out.println("Target City       : " + targetCity);
                System.out.println("Validation Status : FAILED");
                System.out.println("--------------------------------------------------------------");

                // Add mismatch details to Extent Report
                ReportManager.info(
                        "Customer ID : " + customerId
                      + " | Name : " + customerName
                      + " | Source City : " + sourceCity
                      + " | Target City : " + targetCity);
            }

            // Print final result
            System.out.println("Total City Mismatches : " + mismatchCount);
            System.out.println("==============================================================");

            ReportManager.info(
                    "Total City Mismatches : " + mismatchCount);

            // Expected result is zero mismatched records
            Assert.assertEquals(
                    mismatchCount,
                    0,
                    "City Validation Failed. City mismatches found.");

            

        } catch (Exception e) {

            ReportManager.fail(
                    "City Validation Error : " + e.getMessage());

            Assert.fail(
                    "City Validation execution failed : " + e.getMessage());

        } finally {

            // Close database resources created by this test
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