package databaseTesting;

import java.sql.ResultSet;

//import utilities.DatabaseUtil;

public class Test7ExtraRecordsValidation {

    public static void main(String[] args) {

        DatabaseUtil.connect();

        try {

            ResultSet rs = DatabaseUtil.executeQuery(

                    "SELECT * FROM CUSTOMER_TARGET " +
                    "MINUS " +
                    "SELECT * FROM CUSTOMER_SOURCE");

            boolean extraRecordFound = false;

            System.out.println("===== Extra Records =====");

            while (rs.next()) {

                extraRecordFound = true;

                System.out.println("--------------------------------");

                System.out.println("Customer ID : " +
                        rs.getInt("CUSTOMER_ID"));

                System.out.println("Name        : " +
                        rs.getString("NAME"));

                System.out.println("City        : " +
                        rs.getString("CITY"));

                System.out.println("Salary      : " +
                        rs.getInt("SALARY"));

            }

            if (!extraRecordFound) {

                System.out.println("No Extra Records Found");

            }

            rs.close();

        }

        catch (Exception e) {

            e.printStackTrace();

        }

        finally {

            DatabaseUtil.closeConnection();

        }
        
        /*
         * TestNG Assertion

Instead of just printing:

Assert.assertFalse(
        extraRecordFound,
        "Extra Records Found in Target");

If any extra record exists, the test fails.
         */

    }

}