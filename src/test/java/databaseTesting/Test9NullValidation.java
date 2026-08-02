package databaseTesting;

import java.sql.ResultSet;

//import utilities.DatabaseUtil;

public class Test9NullValidation {

    public static void main(String[] args) {

        DatabaseUtil.connect();

        try {

            ResultSet rs = DatabaseUtil.executeQuery(

                    "SELECT * FROM CUSTOMER_TARGET " +
                    "WHERE NAME IS NULL " +
                    "OR CITY IS NULL " +
                    "OR SALARY IS NULL");

            boolean nullFound = false;

            while (rs.next()) {

                nullFound = true;

                System.out.println("--------------------------");

                System.out.println("Customer ID : "
                        + rs.getInt("CUSTOMER_ID"));

                System.out.println("Name : "
                        + rs.getString("NAME"));

                System.out.println("City : "
                        + rs.getString("CITY"));

                System.out.println("Salary : "
                        + rs.getInt("SALARY"));

            }

            if (!nullFound) {

                System.out.println("No NULL Records Found");

            }

            rs.close();

        }

        catch (Exception e) {

            e.printStackTrace();

        }

        finally {

            DatabaseUtil.closeConnection();

        }

    }

}