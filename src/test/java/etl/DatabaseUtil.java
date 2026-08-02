package etl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * ==========================================================
 * Class Name : DatabaseUtil
 * Module     : ETL Automation Framework
 * Description: Handles all Database Operations
 * Author     : Manikanta
 * ==========================================================
 */
public final class DatabaseUtil {

    private static Connection connection;

    // Prevent Object Creation
    private DatabaseUtil() {

    }

    /**
     * Opens Database Connection.
     */
    public static void connect() {

        try {

            connection = DriverManager.getConnection(
                    ConfigReader.getProperty("db.url"),
                    ConfigReader.getProperty("db.username"),
                    ConfigReader.getProperty("db.password"));

            System.out.println("Database Connected Successfully.");

        } catch (SQLException e) {

            throw new RuntimeException("Unable to connect Database.", e);

        }

    }

    /**
     * Returns Database Connection.
     */
    public static Connection getConnection() {

        return connection;

    }

    /**
     * Closes Database Connection.
     */
    public static void closeConnection() {

        try {

            if (connection != null && !connection.isClosed()) {

                connection.close();

                System.out.println("Database Connection Closed.");

            }

        } catch (SQLException e) {

            throw new RuntimeException("Unable to close Database Connection.", e);

        }

    }

    /**
     * Executes SELECT COUNT(*) Query.
     *
     * @param query SQL Query
     * @return Record Count
     */
    public static int getRecordCount(String query) {

        int count = 0;

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            if (resultSet.next()) {

                count = resultSet.getInt(1);

            }

        } catch (SQLException e) {

            throw new RuntimeException("Unable to execute Count Query.", e);

        }

        return count;

    }

    /**
     * Executes SELECT Query.
     *
     * @param query SQL Query
     * @return ResultSet
     */
    public static ResultSet executeQuery(String query) {

        try {

            Statement statement = connection.createStatement();

            return statement.executeQuery(query);

        } catch (SQLException e) {

            throw new RuntimeException("Unable to execute Query.", e);

        }

    }

    /**
     * Executes INSERT, UPDATE and DELETE Queries.
     *
     * @param query SQL Query
     * @return Number of Rows Updated
     */
    public static int executeUpdate(String query) {

        try (Statement statement = connection.createStatement()) {

            return statement.executeUpdate(query);

        } catch (SQLException e) {

            throw new RuntimeException("Unable to execute Update Query.", e);

        }

    }

    /**
     * Returns First Column Value from Result.
     *
     * @param query SQL Query
     * @return Object
     */
    public static Object getSingleValue(String query) {

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            if (resultSet.next()) {

                return resultSet.getObject(1);

            }

        } catch (SQLException e) {

            throw new RuntimeException("Unable to fetch Value.", e);

        }

        return null;

    }

}