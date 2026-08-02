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
 * Test Name   : Test29_ETLAuditValidationTest
 * Module      : ETL Automation Framework
 * Description : Validate ETL Audit Table
 * Author      : Manikanta
 * ==========================================================
 *
 * Business Scenario
 * ----------------------------------------------------------
 * Validate ETL Audit information after ETL execution.
 *
 * Audit Validations
 * ------------------------------
 * Source Count
 * Target Count
 * Insert Count
 * Update Count
 * Reject Count
 * Job Status
 * Start Time
 * End Time
 *
 * Expected Result
 * ----------------------------------------------------------
 * ETL Job should complete successfully with
 * correct audit information.
 */

public class ZTest29_ETLAuditValidationTest extends BaseTest {

    @Test(priority = 29,
            description = "Validate ETL Audit Table")
    public void validateETLAudit() {

        // Start Extent Report
        ReportManager.startTest(
                "ETL Audit Validation",
                "Validate ETL Audit Information");

        // SQL Query
        String auditQuery =
                "SELECT "
              + "JOB_NAME, "
              + "SOURCE_RECORDS, "
              + "TARGET_RECORDS, "
              + "INSERT_COUNT, "
              + "UPDATE_COUNT, "
              + "REJECT_COUNT, "
              + "JOB_STATUS, "
              + "START_TIME, "
              + "END_TIME "
              + "FROM ETL_AUDIT";

        // Variable Declaration
        Statement statement = null;
        ResultSet resultSet = null;

        int failedJobs = 0;

        try {

            // Create Statement
            statement =
                    DatabaseUtil.getConnection().createStatement();

            // Execute Query
            resultSet =
                    statement.executeQuery(auditQuery);

            System.out.println("==============================================================");
            System.out.println("              ETL AUDIT VALIDATION");
            System.out.println("==============================================================");

            while (resultSet.next()) {

                String jobName =
                        resultSet.getString("JOB_NAME");

                int sourceRecords =
                        resultSet.getInt("SOURCE_RECORDS");

                int targetRecords =
                        resultSet.getInt("TARGET_RECORDS");

                int insertCount =
                        resultSet.getInt("INSERT_COUNT");

                int updateCount =
                        resultSet.getInt("UPDATE_COUNT");

                int rejectCount =
                        resultSet.getInt("REJECT_COUNT");

                String jobStatus =
                        resultSet.getString("JOB_STATUS");

                java.sql.Timestamp startTime =
                        resultSet.getTimestamp("START_TIME");

                java.sql.Timestamp endTime =
                        resultSet.getTimestamp("END_TIME");

                System.out.println("Job Name         : " + jobName);
                System.out.println("Source Records   : " + sourceRecords);
                System.out.println("Target Records   : " + targetRecords);
                System.out.println("Insert Count     : " + insertCount);
                System.out.println("Update Count     : " + updateCount);
                System.out.println("Reject Count     : " + rejectCount);
                System.out.println("Job Status       : " + jobStatus);
                System.out.println("Start Time       : " + startTime);
                System.out.println("End Time         : " + endTime);

                // Validate Job Status
                if (!"SUCCESS".equalsIgnoreCase(jobStatus)) {

                    failedJobs++;

                    System.out.println("Validation Status : FAILED");

                    ReportManager.fail(
                            "Job Failed : " + jobName);

                } else {

                    System.out.println("Validation Status : PASSED");

                    ReportManager.pass(
                            "Job Passed : " + jobName);

                }

                System.out.println("--------------------------------------------------------------");

            }

            System.out.println("==============================================================");
            System.out.println("Failed Jobs : " + failedJobs);
            System.out.println("==============================================================");

            Assert.assertEquals(
                    failedJobs,
                    0,
                    "ETL Audit Validation Failed.");

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