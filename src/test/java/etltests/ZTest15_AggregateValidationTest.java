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
 * Test Name   : Test15_AggregateValidationTest
 * Module      : ETL Automation Framework
 * Description : Validate Aggregate Functions between
 *               Source and Target Tables
 * Author      : Manikanta
 * ==========================================================
 *
 * Business Scenario
 * ----------------------------------------------------------
 * Validate aggregate values after ETL execution.
 *
 * Aggregate Functions
 * -------------------
 * SUM()
 * AVG()
 * MIN()
 * MAX()
 *
 * Expected Result
 * ----------------------------------------------------------
 * Aggregate values should be identical in both tables.
 */

public class ZTest15_AggregateValidationTest extends BaseTest {

    @Test(priority = 15,
          description = "Validate Aggregate Functions")
    public void validateAggregateFunctions() {

      

        // Source Query
        String sourceQuery =
                "SELECT "
              + "SUM(SALARY) TOTAL_SALARY, "
              + "AVG(SALARY) AVG_SALARY, "
              + "MIN(SALARY) MIN_SALARY, "
              + "MAX(SALARY) MAX_SALARY "
              + "FROM CUSTOMER_SOURCE";

        // Target Query
        String targetQuery =
                "SELECT "
              + "SUM(SALARY) TOTAL_SALARY, "
              + "AVG(SALARY) AVG_SALARY, "
              + "MIN(SALARY) MIN_SALARY, "
              + "MAX(SALARY) MAX_SALARY "
              + "FROM CUSTOMER_TARGET";

        Statement statement = null;

        ResultSet sourceResultSet = null;
        ResultSet targetResultSet = null;

        try {

            // Create Statement
            statement = DatabaseUtil.getConnection().createStatement();

            // Execute Queries
            sourceResultSet = statement.executeQuery(sourceQuery);

            sourceResultSet.next();

            double sourceSum =
                    sourceResultSet.getDouble("TOTAL_SALARY");

            double sourceAverage =
                    sourceResultSet.getDouble("AVG_SALARY");

            double sourceMinimum =
                    sourceResultSet.getDouble("MIN_SALARY");

            double sourceMaximum =
                    sourceResultSet.getDouble("MAX_SALARY");

            targetResultSet = statement.executeQuery(targetQuery);

            targetResultSet.next();

            double targetSum =
                    targetResultSet.getDouble("TOTAL_SALARY");

            double targetAverage =
                    targetResultSet.getDouble("AVG_SALARY");

            double targetMinimum =
                    targetResultSet.getDouble("MIN_SALARY");

            double targetMaximum =
                    targetResultSet.getDouble("MAX_SALARY");

            System.out.println("==========================================================");
            System.out.println("            AGGREGATE VALIDATION RESULTS");
            System.out.println("==========================================================");

            System.out.println("Source SUM      : " + sourceSum);
            System.out.println("Target SUM      : " + targetSum);

            System.out.println();

            System.out.println("Source AVG      : " + sourceAverage);
            System.out.println("Target AVG      : " + targetAverage);

            System.out.println();

            System.out.println("Source MIN      : " + sourceMinimum);
            System.out.println("Target MIN      : " + targetMinimum);

            System.out.println();

            System.out.println("Source MAX      : " + sourceMaximum);
            System.out.println("Target MAX      : " + targetMaximum);

            System.out.println("==========================================================");

            ReportManager.info("Source SUM : " + sourceSum);
            ReportManager.info("Target SUM : " + targetSum);

            ReportManager.info("Source AVG : " + sourceAverage);
            ReportManager.info("Target AVG : " + targetAverage);

            ReportManager.info("Source MIN : " + sourceMinimum);
            ReportManager.info("Target MIN : " + targetMinimum);

            ReportManager.info("Source MAX : " + sourceMaximum);
            ReportManager.info("Target MAX : " + targetMaximum);

            Assert.assertEquals(sourceSum, targetSum,
                    "SUM Validation Failed");

            Assert.assertEquals(sourceAverage, targetAverage,
                    "AVG Validation Failed");

            Assert.assertEquals(sourceMinimum, targetMinimum,
                    "MIN Validation Failed");

            Assert.assertEquals(sourceMaximum, targetMaximum,
                    "MAX Validation Failed");

           

        }

        catch (Exception e) {

            ReportManager.fail(e.getMessage());

            Assert.fail(e.getMessage());

        }

        finally {

            try {

                if(sourceResultSet != null)
                    sourceResultSet.close();

                if(targetResultSet != null)
                    targetResultSet.close();

                if(statement != null)
                    statement.close();

            }

            catch(Exception e) {

                e.printStackTrace();

            }

        }

    }

}