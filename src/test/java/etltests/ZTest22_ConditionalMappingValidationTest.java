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
 * Test Name   : Test22_ConditionalMappingValidationTest
 * Module      : ETL Automation Framework
 * Description : Validate Conditional Mapping
 * Author      : Manikanta
 * ==========================================================
 *
 * Business Scenario
 * ----------------------------------------------------------
 * Validate Salary Band mapping.
 *
 * Salary <= 30000        -> LOW
 * Salary 30001-60000     -> MEDIUM
 * Salary > 60000         -> HIGH
 *
 * Expected Result
 * ----------------------------------------------------------
 * Target Salary Band should match the calculated band.
 *
 * SQL Concepts Used
 * ----------------------------------------------------------
 * CASE
 * BETWEEN
 * INNER JOIN
 */

public class ZTest22_ConditionalMappingValidationTest extends BaseTest {

	@Test(priority = 22,
		      description = "Validate Conditional Mapping")
		public void validateConditionalMapping() {

		    ReportManager.info("Starting Conditional Mapping Validation...");
		    ReportManager.info("Validating Salary Band Mapping between Source Logic and Target Table.");

		    String mappingValidationQuery =
		            "SELECT "
		          + "T.CUSTOMER_ID, "
		          + "T.CUSTOMER_NAME, "
		          + "T.SALARY, "
		          + "CASE "
		          + "WHEN T.SALARY <= 30000 THEN 'LOW' "
		          + "WHEN T.SALARY BETWEEN 30001 AND 60000 THEN 'MEDIUM' "
		          + "ELSE 'HIGH' "
		          + "END AS EXPECTED_BAND, "
		          + "T.SALARY_BAND "
		          + "FROM CUSTOMER_TARGET T "
		          + "WHERE CASE "
		          + "WHEN T.SALARY <= 30000 THEN 'LOW' "
		          + "WHEN T.SALARY BETWEEN 30001 AND 60000 THEN 'MEDIUM' "
		          + "ELSE 'HIGH' "
		          + "END <> T.SALARY_BAND";

		    Statement statement = null;
		    ResultSet resultSet = null;

		    int mismatchCount = 0;

		    try {

		        ReportManager.info("Executing SQL Query...");

		        statement = DatabaseUtil.getConnection().createStatement();

		        resultSet = statement.executeQuery(mappingValidationQuery);

		        System.out.println("==================================================================");
		        System.out.println("         CONDITIONAL MAPPING VALIDATION");
		        System.out.println("==================================================================");

		        while (resultSet.next()) {

		            mismatchCount++;

		            int customerId = resultSet.getInt("CUSTOMER_ID");
		            String customerName = resultSet.getString("CUSTOMER_NAME");
		            double salary = resultSet.getDouble("SALARY");
		            String expectedBand = resultSet.getString("EXPECTED_BAND");
		            String actualBand = resultSet.getString("SALARY_BAND");

		            System.out.println("Customer ID       : " + customerId);
		            System.out.println("Customer Name     : " + customerName);
		            System.out.println("Salary            : " + salary);
		            System.out.println("Expected Band     : " + expectedBand);
		            System.out.println("Actual Band       : " + actualBand);
		            System.out.println("Validation Status : FAILED");
		            System.out.println("-------------------------------------------------------------");

		            ReportManager.fail(
		                    "Customer ID : " + customerId
		                  + " | Name : " + customerName
		                  + " | Salary : " + salary
		                  + " | Expected : " + expectedBand
		                  + " | Actual : " + actualBand);

		        }

		        ReportManager.info("Total Mapping Errors : " + mismatchCount);

		        System.out.println("==================================================================");
		        System.out.println("Mapping Errors : " + mismatchCount);
		        System.out.println("==================================================================");

		        if (mismatchCount == 0) {

		            ReportManager.info("No Conditional Mapping mismatches found.");
		            ReportManager.pass("Conditional Mapping Validation Passed Successfully.");

		        } else {

		            ReportManager.fail("Conditional Mapping Validation Failed.");
		            ReportManager.fail("Total Mapping Errors : " + mismatchCount);

		            Assert.fail("Conditional Mapping Validation Failed.");

		        }

		    }
		    catch (Exception e) {

		        ReportManager.fail("Exception Occurred : " + e.getMessage());

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