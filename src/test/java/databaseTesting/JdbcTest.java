package databaseTesting;

import java.sql.*;

public class JdbcTest {

    public static void main(String[] args) throws Exception {

       
    	  String url = "jdbc:oracle:thin:@localhost:1521:xe";
          String username = "mani";
          String password = "Mani123";   // Enter your actual password

          try {

              Connection con = DriverManager.getConnection(url, username, password);

              if (con != null) {
                  System.out.println("Database Connected Successfully");
              }

              con.close();

          } catch (Exception e) {

              e.printStackTrace();

          }
    }
}