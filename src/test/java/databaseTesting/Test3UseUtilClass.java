package databaseTesting;

import java.sql.ResultSet;

//import databaseTesting.DatabaseUtil;

public class Test3UseUtilClass {

    public static void main(String[] args) {

        DatabaseUtil.connect();

        ResultSet rs = DatabaseUtil.executeQuery(

                "SELECT * FROM EMPLOYEE WHERE EMP_ID = ?",

                102);

        try {

            while (rs.next()) {

                System.out.println(rs.getInt("EMP_ID"));

                System.out.println(rs.getString("EMP_NAME"));

                System.out.println(rs.getString("DEPARTMENT"));

                System.out.println(rs.getInt("SALARY"));

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        DatabaseUtil.closeConnection();

    }

}