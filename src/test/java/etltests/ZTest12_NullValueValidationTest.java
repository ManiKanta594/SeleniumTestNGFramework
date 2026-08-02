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
 * Test Name   : Test12_NullValueValidationTest
 * Module      : ETL Automation Framework
 * Description : Validate Mandatory Columns for NULL Values
 * Author      : Manikanta
 * ==========================================================
 *
 * Business Scenario:
 * Mandatory columns should never contain NULL values
 * after ETL execution.
 *
 * Expected Result:
 * No NULL values should exist.
 *
 * SQL Logic:
 * Check mandatory columns using IS NULL condition.
 */

public class ZTest12_NullValueValidationTest extends BaseTest {

    @Test(priority = 12,
          description = "Validate NULL Values")
    public void validateNullValues() {

        

        // SQL Query
        String nullValidationQuery =
                "SELECT CUSTOMER_ID, "
              + "CUSTOMER_NAME, "
              + "EMAIL, "
              + "CITY, "
              + "DEPARTMENT "
              + "FROM CUSTOMER_TARGET "
              + "WHERE CUSTOMER_NAME IS NULL "
              + "OR EMAIL IS NULL "
              + "OR CITY IS NULL "
              + "OR DEPARTMENT IS NULL";

        // Variable Declaration
        Statement statement = null;
        ResultSet resultSet = null;

        int nullRecordCount = 0;

        try {

            // Create Statement
            statement = DatabaseUtil.getConnection().createStatement();

            // Execute Query
            resultSet = statement.executeQuery(nullValidationQuery);

            System.out.println("====================================================================");
            System.out.println("                 NULL VALUE VALIDATION RESULTS");
            System.out.println("====================================================================");

            while (resultSet.next()) {

                nullRecordCount++;

                int customerId = resultSet.getInt("CUSTOMER_ID");
                String customerName = resultSet.getString("CUSTOMER_NAME");
                String email = resultSet.getString("EMAIL");
                String city = resultSet.getString("CITY");
                String department = resultSet.getString("DEPARTMENT");

                System.out.println("Customer ID       : " + customerId);
                System.out.println("Customer Name     : " + customerName);
                System.out.println("Email             : " + email);
                System.out.println("City              : " + city);
                System.out.println("Department        : " + department);
                System.out.println("Validation Status : FAILED");
                System.out.println("--------------------------------------------------------------------");

                ReportManager.info(
                        "Customer ID : " + customerId
                      + " | Name : " + customerName
                      + " | Email : " + email
                      + " | City : " + city
                      + " | Department : " + department);

            }

            System.out.println("====================================================================");
            System.out.println("Total NULL Records : " + nullRecordCount);
            System.out.println("====================================================================");

            Assert.assertEquals(
                    nullRecordCount,
                    0,
                    "NULL Values Found.");

            

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