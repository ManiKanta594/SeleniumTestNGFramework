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
 * Test Name   : Test27_FactDimensionValidationTest
 * Module      : ETL Automation Framework
 * Description : Validate Fact Table with Dimension Tables
 * Author      : Manikanta
 * ==========================================================
 *
 * Business Scenario
 * ----------------------------------------------------------
 * Every record loaded into FACT_SALES must have
 * valid Dimension Keys.
 *
 * Validate
 *
 * Customer Dimension
 * Product Dimension
 * Date Dimension
 *
 * Expected Result
 * ----------------------------------------------------------
 * No Orphan Records should exist.
 *
 * SQL Concepts Used
 * ----------------------------------------------------------
 * LEFT JOIN
 * INNER JOIN
 * IS NULL
 */

public class ZTest27_FactDimensionValidationTest extends BaseTest {

    @Test(priority = 27,
            description = "Validate Fact Dimension Mapping")
    public void validateFactDimensionMapping() {

        
        // SQL Query
        String factValidationQuery =
                "SELECT "
              + "F.SALES_ID, "
              + "F.CUSTOMER_KEY, "
              + "F.PRODUCT_KEY, "
              + "F.DATE_KEY "
              + "FROM FACT_SALES F "
              + "LEFT JOIN DIM_CUSTOMER C "
              + "ON F.CUSTOMER_KEY = C.CUSTOMER_KEY "
              + "LEFT JOIN DIM_PRODUCT P "
              + "ON F.PRODUCT_KEY = P.PRODUCT_KEY "
              + "LEFT JOIN DIM_DATE D "
              + "ON F.DATE_KEY = D.DATE_KEY "
              + "WHERE C.CUSTOMER_KEY IS NULL "
              + "OR P.PRODUCT_KEY IS NULL "
              + "OR D.DATE_KEY IS NULL";

        Statement statement = null;
        ResultSet resultSet = null;

        int orphanRecords = 0;

        try {

            statement =
                    DatabaseUtil.getConnection().createStatement();

            resultSet =
                    statement.executeQuery(factValidationQuery);

            System.out.println("==============================================================");
            System.out.println("           FACT DIMENSION VALIDATION");
            System.out.println("==============================================================");

            while(resultSet.next()) {

                orphanRecords++;

                int salesId =
                        resultSet.getInt("SALES_ID");

                int customerKey =
                        resultSet.getInt("CUSTOMER_KEY");

                int productKey =
                        resultSet.getInt("PRODUCT_KEY");

                int dateKey =
                        resultSet.getInt("DATE_KEY");

                System.out.println("Sales ID          : " + salesId);
                System.out.println("Customer Key      : " + customerKey);
                System.out.println("Product Key       : " + productKey);
                System.out.println("Date Key          : " + dateKey);
                System.out.println("Validation Status : FAILED");
                System.out.println("--------------------------------------------------------------");

                ReportManager.info(
                        "Sales ID : " + salesId
                      + " | Customer Key : " + customerKey
                      + " | Product Key : " + productKey
                      + " | Date Key : " + dateKey);

            }

            System.out.println("==============================================================");
            System.out.println("Orphan Records : " + orphanRecords);
            System.out.println("==============================================================");

            Assert.assertEquals(
                    orphanRecords,
                    0,
                    "Fact Dimension Validation Failed.");

            

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