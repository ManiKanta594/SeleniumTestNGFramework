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
 * Test Name   : Test21_TransformationValidationTest
 * Module      : ETL Automation Framework
 * Description : Validate ETL Transformation Logic
 * Author      : Manikanta
 * ==========================================================
 *
 * Business Scenario
 * ----------------------------------------------------------
 * Validate transformed Salary after ETL.
 *
 * Transformation Rule
 * ----------------------------------------------------------
 * TARGET_SALARY = SOURCE_SALARY + 10%
 *
 * Expected Result
 * ----------------------------------------------------------
 * Source Salary * 1.10 should be equal to Target Salary.
 *
 * SQL Concepts Used
 * ----------------------------------------------------------
 * ROUND()
 * Arithmetic Operators
 * INNER JOIN
 */

public class ZTest21_TransformationValidationTest extends BaseTest {

    @Test(priority = 21,
          description = "Validate ETL Transformation Logic")
    public void validateTransformationLogic() {

       

        // SQL Query
        String transformationQuery =
                "SELECT "
              + "S.CUSTOMER_ID, "
              + "S.CUSTOMER_NAME, "
              + "S.SALARY AS SOURCE_SALARY, "
              + "ROUND(S.SALARY * 1.10,2) AS EXPECTED_SALARY, "
              + "T.SALARY AS TARGET_SALARY "
              + "FROM CUSTOMER_SOURCE S "
              + "INNER JOIN CUSTOMER_TARGET T "
              + "ON S.CUSTOMER_ID = T.CUSTOMER_ID "
              + "WHERE ROUND(S.SALARY * 1.10,2) <> T.SALARY";

        // Variable Declaration
        Statement statement = null;
        ResultSet resultSet = null;

        int transformationErrors = 0;

        try {

            // Create Statement
            statement =
                    DatabaseUtil.getConnection().createStatement();

            // Execute Query
            resultSet =
                    statement.executeQuery(transformationQuery);

            System.out.println("======================================================================");
            System.out.println("              TRANSFORMATION VALIDATION RESULTS");
            System.out.println("======================================================================");

            while (resultSet.next()) {

                transformationErrors++;

                int customerId =
                        resultSet.getInt("CUSTOMER_ID");

                String customerName =
                        resultSet.getString("CUSTOMER_NAME");

                double sourceSalary =
                        resultSet.getDouble("SOURCE_SALARY");

                double expectedSalary =
                        resultSet.getDouble("EXPECTED_SALARY");

                double targetSalary =
                        resultSet.getDouble("TARGET_SALARY");

                System.out.println("Customer ID       : " + customerId);
                System.out.println("Customer Name     : " + customerName);
                System.out.println("Source Salary     : " + sourceSalary);
                System.out.println("Expected Salary   : " + expectedSalary);
                System.out.println("Target Salary     : " + targetSalary);
                System.out.println("Validation Status : FAILED");
                System.out.println("---------------------------------------------------------------------");

                ReportManager.info(
                        "Customer ID : " + customerId
                      + " | Name : " + customerName
                      + " | Source : " + sourceSalary
                      + " | Expected : " + expectedSalary
                      + " | Target : " + targetSalary);

            }

            System.out.println("======================================================================");
            System.out.println("Transformation Errors : " + transformationErrors);
            System.out.println("======================================================================");

            Assert.assertEquals(
                    transformationErrors,
                    0,
                    "Transformation Validation Failed.");

           
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