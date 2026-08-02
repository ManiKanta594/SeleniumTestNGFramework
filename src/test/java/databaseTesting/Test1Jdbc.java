package databaseTesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Test1Jdbc {

    public static void main(String[] args) {

        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String username = "mani";
        String password = "Mani123";

        try {

            Connection con = DriverManager.getConnection(url, username, password);

            System.out.println("Database Connected Successfully");

            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM EMPLOYEE");

            while (rs.next()) {

                System.out.println("---------------------------");
                System.out.println("Employee ID   : " + rs.getInt("EMP_ID"));
                System.out.println("Employee Name : " + rs.getString("EMP_NAME"));
                System.out.println("Department    : " + rs.getString("DEPARTMENT"));
                System.out.println("Salary        : " + rs.getInt("SALARY"));

            }

            rs.close();
            stmt.close();
            con.close();

        } catch (Exception e) {

            e.printStackTrace();

        }
    }
}