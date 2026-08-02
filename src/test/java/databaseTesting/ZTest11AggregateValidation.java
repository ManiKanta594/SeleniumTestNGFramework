package databaseTesting;

import java.sql.ResultSet;

import org.testng.Assert;

//import utilities.DatabaseUtil;

public class ZTest11AggregateValidation {

    public static void main(String[] args) {

        DatabaseUtil.connect();

        try {

            ResultSet source = DatabaseUtil.executeQuery(
                    "SELECT SUM(SALARY) TOTAL FROM CUSTOMER_SOURCE");

            ResultSet target = DatabaseUtil.executeQuery(
                    "SELECT SUM(SALARY) TOTAL FROM CUSTOMER_TARGET");

            double sourceTotal = 0;
            double targetTotal = 0;

            if (source.next()) {
                sourceTotal = source.getDouble("TOTAL");
            }

            if (target.next()) {
                targetTotal = target.getDouble("TOTAL");
            }

            System.out.println("Source Total : " + sourceTotal);
            System.out.println("Target Total : " + targetTotal);

            Assert.assertEquals(sourceTotal, targetTotal,
                    "Aggregate Validation Failed");

            System.out.println("Aggregate Validation Passed");

            source.close();
            target.close();

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            DatabaseUtil.closeConnection();

        }

    }

}