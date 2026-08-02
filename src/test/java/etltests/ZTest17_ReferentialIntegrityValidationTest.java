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
 * Test Name   : Test17_ReferentialIntegrityValidationTest
 * Module      : ETL Automation Framework
 * Description : Validate Referential Integrity
 * Author      : Manikanta
 * ==========================================================
 *
 * Business Scenario
 * ----------------------------------------------------------
 * Every Department available in CUSTOMER_TARGET
 * should exist in DEPARTMENT_MASTER.
 *
 * If a department does not exist in the master table,
 * it is considered an orphan record.
 *
 * Expected Result
 * ----------------------------------------------------------
 * Zero orphan records.
 *
 * SQL Concepts Used
 * ----------------------------------------------------------
 * LEFT JOIN
 * IS NULL
 */

public class ZTest17_ReferentialIntegrityValidationTest extends BaseTest {

    @Test(priority = 17,
          description = "Validate Referential Integrity")
    public void validateReferentialIntegrity() {

        

        // SQL Query
        String referentialIntegrityQuery =
                "SELECT "
              + "T.CUSTOMER_ID, "
              + "T.CUSTOMER_NAME, "
              + "T.DEPARTMENT "
              + "FROM CUSTOMER_TARGET T "
              + "LEFT JOIN DEPARTMENT_MASTER D "
              + "ON T.DEPARTMENT = D.DEPARTMENT_NAME "
              + "WHERE D.DEPARTMENT_NAME IS NULL";

        // Variable Declaration
        Statement statement = null;
        ResultSet resultSet = null;

        int orphanRecordCount = 0;

        try {

            // Create Statement
            statement =
                    DatabaseUtil.getConnection().createStatement();

            // Execute Query
            resultSet =
                    statement.executeQuery(referentialIntegrityQuery);

            System.out.println("=================================================================");
            System.out.println("          REFERENTIAL INTEGRITY VALIDATION");
            System.out.println("=================================================================");

            while (resultSet.next()) {

                orphanRecordCount++;

                int customerId =
                        resultSet.getInt("CUSTOMER_ID");

                String customerName =
                        resultSet.getString("CUSTOMER_NAME");

                String department =
                        resultSet.getString("DEPARTMENT");

                System.out.println("Customer ID       : " + customerId);
                System.out.println("Customer Name     : " + customerName);
                System.out.println("Department        : " + department);
                System.out.println("Validation Status : FAILED");
                System.out.println("-----------------------------------------------------------------");

                ReportManager.info(
                        "Customer ID : " + customerId
                      + " | Customer Name : " + customerName
                      + " | Invalid Department : " + department);

            }

            System.out.println("=================================================================");
            System.out.println("Orphan Records : " + orphanRecordCount);
            System.out.println("=================================================================");

            Assert.assertEquals(
                    orphanRecordCount,
                    0,
                    "Referential Integrity Validation Failed.");

            

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