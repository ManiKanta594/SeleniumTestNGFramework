package databaseTesting;

import java.sql.ResultSet;

//import utilities.DatabaseUtil;

public class Test8DataMismatchValidation {

    public static void main(String[] args) {

        DatabaseUtil.connect();

        try {

            ResultSet rs = DatabaseUtil.executeQuery(

                "SELECT S.CUSTOMER_ID, S.NAME SOURCE_NAME, T.NAME TARGET_NAME, " +
                "S.CITY SOURCE_CITY, T.CITY TARGET_CITY, " +
                "S.SALARY SOURCE_SALARY, T.SALARY TARGET_SALARY " +
                "FROM CUSTOMER_SOURCE S " +
                "JOIN CUSTOMER_TARGET T " +
                "ON S.CUSTOMER_ID=T.CUSTOMER_ID " +
                "WHERE S.NAME<>T.NAME " +
                "OR S.CITY<>T.CITY " +
                "OR S.SALARY<>T.SALARY");

            boolean mismatchFound = false;

            while(rs.next()) {

                mismatchFound = true;

                System.out.println("----------------------------");

                System.out.println("Customer ID : " +
                        rs.getInt("CUSTOMER_ID"));

                System.out.println("Source Name : " +
                        rs.getString("SOURCE_NAME"));

                System.out.println("Target Name : " +
                        rs.getString("TARGET_NAME"));

                System.out.println("Source City : " +
                        rs.getString("SOURCE_CITY"));

                System.out.println("Target City : " +
                        rs.getString("TARGET_CITY"));

                System.out.println("Source Salary : " +
                        rs.getInt("SOURCE_SALARY"));

                System.out.println("Target Salary : " +
                        rs.getInt("TARGET_SALARY"));

            }

            if(!mismatchFound) {

                System.out.println("No Data Mismatch Found");

            }

            rs.close();

        }

        catch(Exception e) {

            e.printStackTrace();

        }

        finally {

            DatabaseUtil.closeConnection();

        }

    }

}