package etltests;

import java.sql.ResultSet;
import java.sql.Statement;

import org.testng.Assert;
import org.testng.annotations.Test;

import etl.BaseTest;
import etl.DatabaseUtil;
import etl.ReportManager;
import etl.SQLQueries;

/**
 * ==========================================================
 * Test Name   : Test03_MissingRecordsValidationTest
 * Module      : ETL Automation Framework
 * Description : Validate Missing Records between
 *               Source and Target Tables
 * Author      : Manikanta
 * ==========================================================
 */
public class Test3_MissingRecordsValidationTest extends BaseTest {

    @Test(priority = 3,
          description = "Validate Missing Records")
    public void validateMissingRecords() {

       

        Statement statement = null;
        ResultSet resultSet = null;

        int missingRecords = 0;

        try {

            // Create Database Statement
            statement = DatabaseUtil.getConnection().createStatement();

            // Execute SQL Query
            resultSet = statement.executeQuery(SQLQueries.MISSING_RECORDS);

            // Count Missing Records
            while (resultSet.next()) {

                missingRecords++;

            }

            // Print Result
            System.out.println("=======================================");
            System.out.println("Missing Records : " + missingRecords);
            System.out.println("=======================================");

            // Log to Report
            ReportManager.info("Missing Records : " + missingRecords);

            // Validate Missing Records
            Assert.assertEquals(
                    missingRecords,
                    0,
                    "Missing Records Found in Target Table.");

            

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