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
 * Test Name   : Test20_FullTableComparisonTest
 * Module      : ETL Automation Framework
 * Description : Full Source and Target Table Comparison
 * Author      : Manikanta
 * ==========================================================
 *
 * Business Scenario
 * ----------------------------------------------------------
 * Validate complete data reconciliation between
 * Source and Target tables.
 *
 * Validation 1:
 * Records available in Source but missing in Target.
 *
 * Validation 2:
 * Records available in Target but not available in Source.
 *
 * Expected Result
 * ----------------------------------------------------------
 * Zero mismatched records.
 *
 * SQL Concepts Used
 * ----------------------------------------------------------
 * MINUS
 * ResultSetMetaData
 */

public class ZTest20_FullTableComparisonTest extends BaseTest {

    @Test(priority = 20,
            description = "Full Table Comparison")
    public void validateFullTableComparison() {

        // Start Report
        ReportManager.startTest(
                "Full Table Comparison",
                "Validate Complete Source and Target Data");

        // Source Minus Target
        String sourceMinusTargetQuery =
                "SELECT * FROM CUSTOMER_SOURCE "
              + "MINUS "
              + "SELECT * FROM CUSTOMER_TARGET";

        // Target Minus Source
        String targetMinusSourceQuery =
                "SELECT * FROM CUSTOMER_TARGET "
              + "MINUS "
              + "SELECT * FROM CUSTOMER_SOURCE";

        Statement statement = null;

        ResultSet sourceResultSet = null;
        ResultSet targetResultSet = null;

        int sourceMismatchCount = 0;
        int targetMismatchCount = 0;

        try {

            statement =
                    DatabaseUtil.getConnection().createStatement();

            // ===================================================
            // SOURCE MINUS TARGET
            // ===================================================

            sourceResultSet =
                    statement.executeQuery(sourceMinusTargetQuery);

            ResultSetMetaData sourceMetaData =
                    sourceResultSet.getMetaData();

            int sourceColumnCount =
                    sourceMetaData.getColumnCount();

            System.out.println();
            System.out.println("==============================================================");
            System.out.println("SOURCE -> TARGET MISMATCH RECORDS");
            System.out.println("==============================================================");

            while (sourceResultSet.next()) {

                sourceMismatchCount++;

                System.out.println("Mismatch Record : "
                        + sourceMismatchCount);

                for (int i = 1; i <= sourceColumnCount; i++) {

                    System.out.println(
                            sourceMetaData.getColumnName(i)
                            + " : "
                            + sourceResultSet.getObject(i));

                }

                System.out.println("--------------------------------------------------------------");

            }

            // ===================================================
            // TARGET MINUS SOURCE
            // ===================================================

            targetResultSet =
                    statement.executeQuery(targetMinusSourceQuery);

            ResultSetMetaData targetMetaData =
                    targetResultSet.getMetaData();

            int targetColumnCount =
                    targetMetaData.getColumnCount();

            System.out.println();
            System.out.println("==============================================================");
            System.out.println("TARGET -> SOURCE MISMATCH RECORDS");
            System.out.println("==============================================================");

            while (targetResultSet.next()) {

                targetMismatchCount++;

                System.out.println("Mismatch Record : "
                        + targetMismatchCount);

                for (int i = 1; i <= targetColumnCount; i++) {

                    System.out.println(
                            targetMetaData.getColumnName(i)
                            + " : "
                            + targetResultSet.getObject(i));

                }

                System.out.println("--------------------------------------------------------------");

            }

            System.out.println();
            System.out.println("==============================================================");
            System.out.println("SUMMARY");
            System.out.println("==============================================================");
            System.out.println("Source -> Target Mismatches : "
                    + sourceMismatchCount);
            System.out.println("Target -> Source Mismatches : "
                    + targetMismatchCount);
            System.out.println("Total Mismatches            : "
                    + (sourceMismatchCount + targetMismatchCount));
            System.out.println("==============================================================");

            ReportManager.info(
                    "Source -> Target Mismatches : "
                            + sourceMismatchCount);

            ReportManager.info(
                    "Target -> Source Mismatches : "
                            + targetMismatchCount);

            Assert.assertEquals(
                    sourceMismatchCount + targetMismatchCount,
                    0,
                    "Full Table Comparison Failed.");

            ReportManager.pass(
                    "Full Table Comparison Passed.");

        }

        catch (Exception e) {

            ReportManager.fail(e.getMessage());

            Assert.fail(e.getMessage());

        }

        finally {

            try {

                if (sourceResultSet != null)
                    sourceResultSet.close();

                if (targetResultSet != null)
                    targetResultSet.close();

                if (statement != null)
                    statement.close();

            }

            catch (Exception e) {

                e.printStackTrace();

            }

        }

    }

}