package etltests;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import org.testng.Assert;
import org.testng.annotations.Test;

import etl.BaseTest;
import etl.DatabaseUtil;
import etl.ReportManager;

/**
 * ==========================================================
 * Test Name   : Test19_SourceTargetDataComparisonTest
 * Module      : ETL Automation Framework
 * Description : Compare Source and Target Data
 * Author      : Manikanta
 * ==========================================================
 *
 * Business Scenario
 * ----------------------------------------------------------
 * Compare complete Source and Target records.
 *
 * Expected Result
 * ----------------------------------------------------------
 * No records should exist in Source that are
 * missing in Target.
 *
 * SQL Concepts Used
 * ----------------------------------------------------------
 * MINUS
 * ResultSetMetaData
 */

public class ZTest19_SourceTargetDataComparisonTest extends BaseTest {

    @Test(priority = 19,
          description = "Compare Source and Target Data")
    public void compareSourceAndTargetData() {

      
        // SQL Query
        String comparisonQuery =
                "SELECT * FROM CUSTOMER_SOURCE "
              + "MINUS "
              + "SELECT * FROM CUSTOMER_TARGET";

        // Variable Declaration
        Statement statement = null;
        ResultSet resultSet = null;

        int mismatchCount = 0;

        try {

            // Create Statement
            statement =
                    DatabaseUtil.getConnection().createStatement();

            // Execute Query
            resultSet =
                    statement.executeQuery(comparisonQuery);

            // Read Column Information
            ResultSetMetaData metaData =
                    resultSet.getMetaData();

            int columnCount =
                    metaData.getColumnCount();

            System.out.println("==================================================================");
            System.out.println("             SOURCE TO TARGET DATA COMPARISON");
            System.out.println("==================================================================");

            while (resultSet.next()) {

                mismatchCount++;

                System.out.println("Mismatch Record : " + mismatchCount);

                for (int column = 1; column <= columnCount; column++) {

                    String columnName =
                            metaData.getColumnName(column);

                    Object value =
                            resultSet.getObject(column);

                    System.out.println(columnName + " : " + value);

                    ReportManager.info(
                            columnName + " : " + value);

                }

                System.out.println("------------------------------------------------------------------");

            }

            System.out.println("Total Mismatch Records : "
                    + mismatchCount);

            System.out.println("==================================================================");

            Assert.assertEquals(
                    mismatchCount,
                    0,
                    "Source and Target Data Mismatch Found.");

            

        }

        catch (Exception e) {

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