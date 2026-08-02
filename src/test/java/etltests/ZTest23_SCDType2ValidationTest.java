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
 * Test Name   : Test23_SCDType2ValidationTest
 * Module      : ETL Automation Framework
 * Description : Validate Slowly Changing Dimension Type-2
 * Author      : Manikanta
 * ==========================================================
 *
 * Business Scenario
 * ----------------------------------------------------------
 * SCD Type-2 maintains historical records.
 *
 * Every active record should have:
 *
 * CURRENT_FLAG = 'Y'
 * END_DATE = NULL
 *
 * Historical records should have:
 *
 * CURRENT_FLAG = 'N'
 * END_DATE NOT NULL
 *
 * Expected Result
 * ----------------------------------------------------------
 * No invalid SCD records.
 */

public class ZTest23_SCDType2ValidationTest extends BaseTest {

    @Test(priority = 23,
          description = "Validate SCD Type-2")
    public void validateSCDType2() {

        // Start Report
        ReportManager.startTest(
                "SCD Type-2 Validation",
                "Validate Historical Records");

        // SQL Query
        String scdValidationQuery =
                "SELECT CUSTOMER_ID, "
              + "CUSTOMER_NAME, "
              + "CURRENT_FLAG, "
              + "START_DATE, "
              + "END_DATE "
              + "FROM CUSTOMER_TARGET "
              + "WHERE (CURRENT_FLAG='Y' AND END_DATE IS NOT NULL) "
              + "OR (CURRENT_FLAG='N' AND END_DATE IS NULL)";

        Statement statement = null;
        ResultSet resultSet = null;

        int invalidRecords = 0;

        try {

            statement =
                    DatabaseUtil.getConnection().createStatement();

            resultSet =
                    statement.executeQuery(scdValidationQuery);

            System.out.println("============================================================");
            System.out.println("              SCD TYPE-2 VALIDATION");
            System.out.println("============================================================");

            while(resultSet.next()) {

                invalidRecords++;

                System.out.println("Customer ID      : "
                        + resultSet.getInt("CUSTOMER_ID"));

                System.out.println("Customer Name    : "
                        + resultSet.getString("CUSTOMER_NAME"));

                System.out.println("Current Flag     : "
                        + resultSet.getString("CURRENT_FLAG"));

                System.out.println("Start Date       : "
                        + resultSet.getDate("START_DATE"));

                System.out.println("End Date         : "
                        + resultSet.getDate("END_DATE"));

                System.out.println("Validation       : FAILED");

                System.out.println("------------------------------------------------------------");

                ReportManager.info(
                        "Customer ID : "
                        + resultSet.getInt("CUSTOMER_ID"));

            }

            System.out.println("Invalid Records : "
                    + invalidRecords);

            Assert.assertEquals(
                    invalidRecords,
                    0,
                    "SCD Type-2 Validation Failed.");

            ReportManager.pass(
                    "SCD Type-2 Validation Passed.");

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