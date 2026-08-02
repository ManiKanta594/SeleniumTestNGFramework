package databaseTesting;

import java.sql.ResultSet;

//import utilities.DatabaseUtil;

public class Test6MissingRecordsValidation {

    public static void main(String[] args) {

        DatabaseUtil.connect();

        try {

            ResultSet rs = DatabaseUtil.executeQuery(

                    "SELECT * FROM CUSTOMER_SOURCE " +
                    "MINUS " +
                    "SELECT * FROM CUSTOMER_TARGET");

            boolean missingFound = false;

            System.out.println("===== Missing Records =====");

            while (rs.next()) {

                missingFound = true;

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

            if (!missingFound) {

                System.out.println("No Missing Records Found");

            }

            rs.close();

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            DatabaseUtil.closeConnection();

        }

    }

}