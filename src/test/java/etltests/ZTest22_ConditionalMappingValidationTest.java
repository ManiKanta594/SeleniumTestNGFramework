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
 * Test Name   : Test22_ConditionalMappingValidationTest
 * Module      : ETL Automation Framework
 * Description : Validate Conditional Mapping
 * Author      : Manikanta
 * ==========================================================
 *
 * Business Scenario
 * ----------------------------------------------------------
 * Validate Salary Band mapping.
 *
 * Salary <= 30000        -> LOW
 * Salary 30001-60000     -> MEDIUM
 * Salary > 60000         -> HIGH
 *
 * Expected Result
 * ----------------------------------------------------------
 * Target Salary Band should match the calculated band.
 *
 * SQL Concepts Used
 * ----------------------------------------------------------
 * CASE
 * BETWEEN
 * INNER JOIN
 */

public class ZTest22_ConditionalMappingValidationTest extends BaseTest {

    @Test(priority = 22,
          description = "Validate Conditional Mapping")
    public void validateConditionalMapping() {

        // Start Extent Report
        ReportManager.startTest(
                "Conditional Mapping Validation",
                "Validate Salary Band Mapping");

        // SQL Query
        String mappingValidationQuery =
                "SELECT "
              + "T.CUSTOMER_ID, "
              + "T.CUSTOMER_NAME, "
              + "T.SALARY, "
              + "CASE "
              + "WHEN T.SALARY <= 30000 THEN 'LOW' "
              + "WHEN T.SALARY BETWEEN 30001 AND 60000 THEN 'MEDIUM' "
              + "ELSE 'HIGH' "
              + "END AS EXPECTED_BAND, "
              + "T.SALARY_BAND "
              + "FROM CUSTOMER_TARGET T "
              + "WHERE CASE "
              + "WHEN T.SALARY <= 30000 THEN 'LOW' "
              + "WHEN T.SALARY BETWEEN 30001 AND 60000 THEN 'MEDIUM' "
              + "ELSE 'HIGH' "
              + "END <> T.SALARY_BAND";

        // Variable Declaration
        Statement statement = null;
        ResultSet resultSet = null;

        int mismatchCount = 0;

        try {

            // Create Statement
            statement =
                    DatabaseUtil.getConnection().createStatement();

            // Execute Query
            resultSet =
                    statement.executeQuery(mappingValidationQuery);

            System.out.println("==================================================================");
            System.out.println("         CONDITIONAL MAPPING VALIDATION");
            System.out.println("==================================================================");

            while (resultSet.next()) {

                mismatchCount++;

                int customerId =
                        resultSet.getInt("CUSTOMER_ID");

                String customerName =
                        resultSet.getString("CUSTOMER_NAME");

                double salary =
                        resultSet.getDouble("SALARY");

                String expectedBand =
                        resultSet.getString("EXPECTED_BAND");

                String actualBand =
                        resultSet.getString("SALARY_BAND");

                System.out.println("Customer ID        : " + customerId);
                System.out.println("Customer Name      : " + customerName);
                System.out.println("Salary             : " + salary);
                System.out.println("Expected Band      : " + expectedBand);
                System.out.println("Actual Band        : " + actualBand);
                System.out.println("Validation Status  : FAILED");
                System.out.println("------------------------------------------------------------------");

                ReportManager.info(
                        "Customer ID : " + customerId
                      + " | Name : " + customerName
                      + " | Expected : " + expectedBand
                      + " | Actual : " + actualBand);

            }

            System.out.println("==================================================================");
            System.out.println("Mapping Errors : " + mismatchCount);
            System.out.println("==================================================================");

            Assert.assertEquals(
                    mismatchCount,
                    0,
                    "Conditional Mapping Validation Failed.");

            ReportManager.pass(
                    "Conditional Mapping Validation Passed.");

        }

        catch (Exception e) {

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