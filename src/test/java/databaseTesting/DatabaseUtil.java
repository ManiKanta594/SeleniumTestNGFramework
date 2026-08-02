package databaseTesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DatabaseUtil {

    private static final String URL =
            "jdbc:oracle:thin:@localhost:1521:xe";

    private static final String USERNAME = "mani";

    private static final String PASSWORD = "Mani123";

    private static Connection connection;

    // Open Connection
    public static void connect() {

        try {

            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            System.out.println("Database Connected Successfully");

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    // Close Connection
    public static void closeConnection() {

        try {

            if (connection != null) {

                connection.close();

                System.out.println("Database Connection Closed");

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    // Execute Select Query
    public static ResultSet executeQuery(String query, Object... params) {

        try {

            PreparedStatement ps = connection.prepareStatement(query);

            for (int i = 0; i < params.length; i++) {

                ps.setObject(i + 1, params[i]);

            }

            return ps.executeQuery();

        } catch (Exception e) {

            e.printStackTrace();

        }

        return null;

    }
    
    public static int executeUpdate(String query, Object... params) {

        try {

            PreparedStatement ps = connection.prepareStatement(query);

            for (int i = 0; i < params.length; i++) {

                ps.setObject(i + 1, params[i]);

            }

            return ps.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();

        }

        return 0;
    }

}