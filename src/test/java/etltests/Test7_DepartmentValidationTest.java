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
 * Test Name   : Test07_DepartmentValidationTest
 * Module      : ETL Automation Framework
 * Description : Validate Department between Source and Target
 * Author      : Manikanta
 * ==========================================================
 */
public class Test7_DepartmentValidationTest extends BaseTest {

    @Test(priority = 7, description = "Validate Department")
    public void validateDepartment() {

        
        // SQL Query
        String departmentValidationQuery =
                "SELECT "
              + "S.CUSTOMER_ID, "
              + "S.CUSTOMER_NAME, "
              + "S.DEPARTMENT AS SOURCE_DEPARTMENT, "
              + "T.DEPARTMENT AS TARGET_DEPARTMENT "
              + "FROM CUSTOMER_SOURCE S "
              + "INNER JOIN CUSTOMER_TARGET T "
              + "ON S.CUSTOMER_ID = T.CUSTOMER_ID "
              + "WHERE S.DEPARTMENT <> T.DEPARTMENT";

        // Variable Declarations
        Statement statement = null;
        ResultSet resultSet = null;

        int mismatchCount = 0;

        try {

            // Create Statement
            statement = DatabaseUtil.getConnection().createStatement();

            // Execute Query
            resultSet = statement.executeQuery(departmentValidationQuery);

            System.out.println("================================================================================");
            System.out.println("                 DEPARTMENT VALIDATION RESULTS");
            System.out.println("================================================================================");

            while (resultSet.next()) {

                mismatchCount++;

                int customerId = resultSet.getInt("CUSTOMER_ID");
                String customerName = resultSet.getString("CUSTOMER_NAME");
                String sourceDepartment = resultSet.getString("SOURCE_DEPARTMENT");
                String targetDepartment = resultSet.getString("TARGET_DEPARTMENT");

                System.out.println("Customer ID        : " + customerId);
                System.out.println("Customer Name      : " + customerName);
                System.out.println("Source Department  : " + sourceDepartment);
                System.out.println("Target Department  : " + targetDepartment);
                System.out.println("Validation Status  : FAILED");
                System.out.println("--------------------------------------------------------------------------------");

                ReportManager.info(
                        "Customer ID : " + customerId
                      + " | Name : " + customerName
                      + " | Source Department : " + sourceDepartment
                      + " | Target Department : " + targetDepartment);

            }

            System.out.println("Total Department Mismatches : " + mismatchCount);
            System.out.println("================================================================================");

            Assert.assertEquals(
                    mismatchCount,
                    0,
                    "Department Validation Failed.");

           

        } catch (Exception e) {

            ReportManager.fail(e.getMessage());

            Assert.fail(e.getMessage());

        } finally {

            try {

                if (resultSet != null) {
                    resultSet.close();
                }

                if (statement != null) {
                    statement.close();
                }

            } catch (Exception e) {

                e.printStackTrace();

            }

        }

    }

}