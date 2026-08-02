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
 * Test Name   : Test16_LookupValidationTest
 * Module      : ETL Automation Framework
 * Description : Validate Lookup Data
 * Author      : Manikanta
 * ==========================================================
 *
 * Business Scenario
 * ----------------------------------------------------------
 * Every Department available in CUSTOMER_TARGET
 * should exist in DEPARTMENT_MASTER table.
 *
 * Expected Result
 * ----------------------------------------------------------
 * No invalid Departments should exist.
 *
 * SQL Concepts Used
 * ----------------------------------------------------------
 * DISTINCT
 * NOT EXISTS
 */

public class ZTest16_LookupValidationTest extends BaseTest {

    @Test(priority = 16,
            description = "Validate Lookup Data")
    public void validateLookupData() {

        // Start Extent Report
        ReportManager.startTest(
                "Lookup Validation",
                "Validate Department Lookup");

        // SQL Query
        String lookupValidationQuery =
                "SELECT DISTINCT T.DEPARTMENT "
              + "FROM CUSTOMER_TARGET T "
              + "WHERE NOT EXISTS ( "
              + "SELECT 1 "
              + "FROM DEPARTMENT_MASTER D "
              + "WHERE D.DEPARTMENT_NAME = T.DEPARTMENT )";

        // Variable Declaration
        Statement statement = null;
        ResultSet resultSet = null;

        int invalidDepartmentCount = 0;

        try {

            // Create Statement
            statement =
                    DatabaseUtil.getConnection().createStatement();

            // Execute Query
            resultSet =
                    statement.executeQuery(lookupValidationQuery);

            System.out.println("=========================================================");
            System.out.println("            LOOKUP VALIDATION RESULTS");
            System.out.println("=========================================================");

            while(resultSet.next()) {

                invalidDepartmentCount++;

                String department =
                        resultSet.getString("DEPARTMENT");

                System.out.println("Invalid Department : "
                        + department);

                System.out.println("Validation Status  : FAILED");

                System.out.println("---------------------------------------------------------");

                ReportManager.info(
                        "Invalid Department : "
                                + department);

            }

            System.out.println("=========================================================");
            System.out.println("Invalid Departments : "
                    + invalidDepartmentCount);
            System.out.println("=========================================================");

            Assert.assertEquals(
                    invalidDepartmentCount,
                    0,
                    "Invalid Lookup Values Found.");

            ReportManager.pass(
                    "Lookup Validation Passed.");

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