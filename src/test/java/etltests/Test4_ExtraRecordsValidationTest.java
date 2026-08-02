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
 * Test Name   : Test04_ExtraRecordsValidationTest
 * Module      : ETL Automation Framework
 * Description : Validate Extra Records between
 *               Source and Target Tables
 * Author      : Manikanta
 * ==========================================================
 */
public class Test4_ExtraRecordsValidationTest extends BaseTest {

    @Test(priority = 4,
          description = "Validate Extra Records")
    public void validateExtraRecords() {

        
        Statement statement = null;
        ResultSet resultSet = null;

        int extraRecords = 0;

        try {

            // Create Database Statement
            statement = DatabaseUtil.getConnection().createStatement();

            // Execute SQL Query
            resultSet = statement.executeQuery(SQLQueries.EXTRA_RECORDS);

            // Count Extra Records
            while (resultSet.next()) {

                extraRecords++;

            }

            // Print Result
            System.out.println("=======================================");
            System.out.println("Extra Records : " + extraRecords);
            System.out.println("=======================================");

            // Log to Report
            ReportManager.info("Extra Records : " + extraRecords);

            // Validation
            Assert.assertEquals(
                    extraRecords,
                    0,
                    "Extra Records Found in Target Table.");

          

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