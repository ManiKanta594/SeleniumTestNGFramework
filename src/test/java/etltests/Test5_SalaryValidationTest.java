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
 * Test Name   : Test05_SalaryValidationTest
 * Module      : ETL Automation Framework
 * Description : Validate Salary between Source and Target
 * Author      : Manikanta
 * ==========================================================
 */
public class Test5_SalaryValidationTest extends BaseTest {

    @Test(priority = 5, description = "Validate Salary Mismatch")
    public void validateSalaryMismatch() {

        // Start Extent Report
        ReportManager.startTest(
                "Salary Validation",
                "Validate Salary between Source and Target");

        // SQL Query
        String salaryValidationQuery =
                "SELECT "
              + "S.CUSTOMER_ID, "
              + "S.CUSTOMER_NAME, "
              + "S.SALARY AS SOURCE_SALARY, "
              + "T.SALARY AS TARGET_SALARY "
              + "FROM CUSTOMER_SOURCE S "
              + "INNER JOIN CUSTOMER_TARGET T "
              + "ON S.CUSTOMER_ID = T.CUSTOMER_ID "
              + "WHERE S.SALARY <> T.SALARY";

        // Variable Declarations
        Statement statement = null;
        ResultSet resultSet = null;

        int mismatchCount = 0;

        try {

            // Create Statement
            statement = DatabaseUtil.getConnection().createStatement();

            // Execute Query
            resultSet = statement.executeQuery(salaryValidationQuery);

            System.out.println("========================================================================================");
            System.out.printf("%-10s %-25s %-15s %-15s%n",
                    "ID", "NAME", "SOURCE", "TARGET");
            System.out.println("========================================================================================");

            // Read Records
            while (resultSet.next()) {

                mismatchCount++;

                int customerId = resultSet.getInt("CUSTOMER_ID");
                String customerName = resultSet.getString("CUSTOMER_NAME");
                double sourceSalary = resultSet.getDouble("SOURCE_SALARY");
                double targetSalary = resultSet.getDouble("TARGET_SALARY");

                // Print Record
                System.out.printf("%-10d %-25s %-15.2f %-15.2f%n",
                        customerId,
                        customerName,
                        sourceSalary,
                        targetSalary);

                // Report Log
                ReportManager.info(
                        "Customer ID : " + customerId
                      + " | Name : " + customerName
                      + " | Source Salary : " + sourceSalary
                      + " | Target Salary : " + targetSalary);

            }

            System.out.println("========================================================================================");
            System.out.println("Total Salary Mismatches : " + mismatchCount);
            System.out.println("========================================================================================");

            // Validation
            Assert.assertEquals(
                    mismatchCount,
                    0,
                    "Salary Mismatch Found.");

            // Report PASS
            ReportManager.pass("Salary Validation Passed.");

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