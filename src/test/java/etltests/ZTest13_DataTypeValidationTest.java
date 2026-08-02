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
 * Test Name   : Test13_DataTypeValidationTest
 * Module      : ETL Automation Framework
 * Description : Validate Data Type of Customer Data
 * Author      : Manikanta
 * ==========================================================
 *
 * Business Scenario:
 * Validate that Salary contains only numeric values.
 *
 * Expected Result:
 * No invalid Salary values should exist.
 *
 * SQL Logic:
 * REGEXP_LIKE() is used to identify invalid numeric data.
 */

public class ZTest13_DataTypeValidationTest extends BaseTest {

    @Test(priority = 13,
          description = "Validate Data Type")
    public void validateDataType() {

       

        // SQL Query
        String dataTypeValidationQuery =
                "SELECT CUSTOMER_ID, "
              + "CUSTOMER_NAME, "
              + "SALARY "
              + "FROM CUSTOMER_TARGET "
              + "WHERE NOT REGEXP_LIKE(SALARY,'^[0-9]+(\\.[0-9]+)?$')";

        // Variable Declaration
        Statement statement = null;
        ResultSet resultSet = null;

        int invalidRecordCount = 0;

        try {

            // Create Statement
            statement = DatabaseUtil.getConnection().createStatement();

            // Execute Query
            resultSet = statement.executeQuery(dataTypeValidationQuery);

            System.out.println("====================================================================");
            System.out.println("               DATA TYPE VALIDATION RESULTS");
            System.out.println("====================================================================");

            while (resultSet.next()) {

                invalidRecordCount++;

                int customerId =
                        resultSet.getInt("CUSTOMER_ID");

                String customerName =
                        resultSet.getString("CUSTOMER_NAME");

                String salary =
                        resultSet.getString("SALARY");

                System.out.println("Customer ID       : " + customerId);
                System.out.println("Customer Name     : " + customerName);
                System.out.println("Salary            : " + salary);
                System.out.println("Validation Status : FAILED");
                System.out.println("--------------------------------------------------------------------");

                ReportManager.info(
                        "Customer ID : " + customerId
                      + " | Name : " + customerName
                      + " | Salary : " + salary);

            }

            System.out.println("====================================================================");
            System.out.println("Invalid Records : " + invalidRecordCount);
            System.out.println("====================================================================");

            Assert.assertEquals(
                    invalidRecordCount,
                    0,
                    "Invalid Data Type Found.");

            

        } catch (Exception e) {

            ReportManager.fail(e.getMessage());

            Assert.fail(e.getMessage());

        } finally {

            try {

                if(resultSet != null)
                    resultSet.close();

                if(statement != null)
                    statement.close();

            } catch (Exception e) {

                e.printStackTrace();

            }

        }

    }

}