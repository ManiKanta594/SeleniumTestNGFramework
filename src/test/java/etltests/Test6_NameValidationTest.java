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
 * Test Name   : Test06_NameValidationTest
 * Module      : ETL Automation Framework
 * Description : Validate Customer Name between Source and
 *               Target Tables
 * Author      : Manikanta
 * ==========================================================
 */
public class Test6_NameValidationTest extends BaseTest {

    @Test(priority = 6, description = "Validate Customer Name")
    public void validateCustomerName() {

       
        // SQL Query
        String nameValidationQuery =
                "SELECT "
              + "S.CUSTOMER_ID, "
              + "S.CUSTOMER_NAME AS SOURCE_NAME, "
              + "T.CUSTOMER_NAME AS TARGET_NAME "
              + "FROM CUSTOMER_SOURCE S "
              + "INNER JOIN CUSTOMER_TARGET T "
              + "ON S.CUSTOMER_ID = T.CUSTOMER_ID "
              + "WHERE S.CUSTOMER_NAME <> T.CUSTOMER_NAME";

        // Variable Declarations
        Statement statement = null;
        ResultSet resultSet = null;

        int mismatchCount = 0;

        try {

            // Create Statement
            statement = DatabaseUtil.getConnection().createStatement();

            // Execute Query
            resultSet = statement.executeQuery(nameValidationQuery);

            System.out.println("================================================================================");
            System.out.println("                 CUSTOMER NAME VALIDATION RESULTS");
            System.out.println("================================================================================");

            while (resultSet.next()) {

                mismatchCount++;

                int customerId = resultSet.getInt("CUSTOMER_ID");
                String sourceName = resultSet.getString("SOURCE_NAME");
                String targetName = resultSet.getString("TARGET_NAME");

                System.out.println("Customer ID      : " + customerId);
                System.out.println("Source Name      : " + sourceName);
                System.out.println("Target Name      : " + targetName);
                System.out.println("Validation Status: FAILED");
                System.out.println("--------------------------------------------------------------------------------");

                ReportManager.info(
                        "Customer ID : " + customerId
                      + " | Source Name : " + sourceName
                      + " | Target Name : " + targetName);

            }

            System.out.println("Total Name Mismatches : " + mismatchCount);
            System.out.println("================================================================================");

            Assert.assertEquals(
                    mismatchCount,
                    0,
                    "Customer Name Validation Failed.");

            

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