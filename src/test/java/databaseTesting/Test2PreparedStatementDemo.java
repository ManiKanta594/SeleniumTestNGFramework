package databaseTesting;

import java.sql.*;

public class Test2PreparedStatementDemo {

    public static void main(String[] args) {

        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String username = "mani";
        String password = "Mani123";

        try {

            Connection con = DriverManager.getConnection(url, username, password);

            String query = "SELECT * FROM EMPLOYEE WHERE EMP_ID = ?";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, 102);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                System.out.println("Employee ID   : " + rs.getInt("EMP_ID"));
                System.out.println("Employee Name : " + rs.getString("EMP_NAME"));
                System.out.println("Department    : " + rs.getString("DEPARTMENT"));
                System.out.println("Salary        : " + rs.getInt("SALARY"));

            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

}