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
 * Test Name   : Test18_BusinessRuleValidationTest
 * Module      : ETL Automation Framework
 * Description : Validate Business Rules
 * Author      : Manikanta
 * ==========================================================
 *
 * Business Scenario
 * ----------------------------------------------------------
 * Validate the following business rules:
 *
 * 1. Salary should be greater than Zero.
 * 2. Age should be between 18 and 60.
 * 3. Bonus should not be negative.
 *
 * Expected Result
 * ----------------------------------------------------------
 * Zero Business Rule Violations.
 *
 * SQL Concepts Used
 * ----------------------------------------------------------
 * CASE
 * WHEN
 * ELSE
 */

public class ZTest18_BusinessRuleValidationTest extends BaseTest {

    @Test(priority = 18,
            description = "Validate Business Rules")
    public void validateBusinessRules() {

       

        // SQL Query
        String businessRuleQuery =
                "SELECT "
              + "CUSTOMER_ID, "
              + "CUSTOMER_NAME, "
              + "AGE, "
              + "SALARY, "
              + "BONUS, "
              + "CASE "
              + "WHEN SALARY <= 0 THEN 'INVALID SALARY' "
              + "WHEN AGE NOT BETWEEN 18 AND 60 THEN 'INVALID AGE' "
              + "WHEN BONUS < 0 THEN 'INVALID BONUS' "
              + "ELSE 'VALID' "
              + "END AS VALIDATION_STATUS "
              + "FROM CUSTOMER_TARGET "
              + "WHERE SALARY <= 0 "
              + "OR AGE NOT BETWEEN 18 AND 60 "
              + "OR BONUS < 0";

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
                    statement.executeQuery(businessRuleQuery);

            System.out.println("======================================================================");
            System.out.println("                 BUSINESS RULE VALIDATION");
            System.out.println("======================================================================");

            while(resultSet.next()) {

                invalidRecords++;

                int customerId =
                        resultSet.getInt("CUSTOMER_ID");

                String customerName =
                        resultSet.getString("CUSTOMER_NAME");

                int age =
                        resultSet.getInt("AGE");

                double salary =
                        resultSet.getDouble("SALARY");

                double bonus =
                        resultSet.getDouble("BONUS");

                String status =
                        resultSet.getString("VALIDATION_STATUS");

                System.out.println("Customer ID        : " + customerId);
                System.out.println("Customer Name      : " + customerName);
                System.out.println("Age                : " + age);
                System.out.println("Salary             : " + salary);
                System.out.println("Bonus              : " + bonus);
                System.out.println("Validation Result  : " + status);
                System.out.println("---------------------------------------------------------------------");

                ReportManager.info(
                        "Customer ID : " + customerId
                      + " | Name : " + customerName
                      + " | Validation : " + status);

            }

            System.out.println("======================================================================");
            System.out.println("Business Rule Violations : " + invalidRecords);
            System.out.println("======================================================================");

            Assert.assertEquals(
                    invalidRecords,
                    0,
                    "Business Rule Validation Failed.");

            

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