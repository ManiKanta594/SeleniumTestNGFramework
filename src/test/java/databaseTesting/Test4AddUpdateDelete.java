package databaseTesting;

import java.sql.ResultSet;

public class Test4AddUpdateDelete {

    public static void main(String[] args) {

        DatabaseUtil.connect();

        try {

            // ==============================
            // INSERT
            // ==============================
            System.out.println("===== INSERT EMPLOYEE =====");

            int insertRows = DatabaseUtil.executeUpdate(
                    "INSERT INTO EMPLOYEE VALUES(?,?,?,?)",
                    104,
                    "Robert",
                    "Testing",
                    55000);

            System.out.println("Rows Inserted : " + insertRows);

            // ==============================
            // VERIFY INSERT
            // ==============================
            System.out.println("\n===== VERIFY INSERT =====");

            ResultSet rs = DatabaseUtil.executeQuery(
                    "SELECT * FROM EMPLOYEE WHERE EMP_ID = ?",
                    104);

            while (rs.next()) {

                System.out.println("Employee ID   : " + rs.getInt("EMP_ID"));
                System.out.println("Employee Name : " + rs.getString("EMP_NAME"));
                System.out.println("Department    : " + rs.getString("DEPARTMENT"));
                System.out.println("Salary        : " + rs.getInt("SALARY"));
            }

            // ==============================
            // UPDATE
            // ==============================
            System.out.println("\n===== UPDATE EMPLOYEE =====");

            int updateRows = DatabaseUtil.executeUpdate(
                    "UPDATE EMPLOYEE SET SALARY = ? WHERE EMP_ID = ?",
                    70000,
                    104);

            System.out.println("Rows Updated : " + updateRows);

            // ==============================
            // VERIFY UPDATE
            // ==============================
            System.out.println("\n===== VERIFY UPDATE =====");

            rs = DatabaseUtil.executeQuery(
                    "SELECT * FROM EMPLOYEE WHERE EMP_ID = ?",
                    104);

            while (rs.next()) {

                System.out.println("Employee ID   : " + rs.getInt("EMP_ID"));
                System.out.println("Employee Name : " + rs.getString("EMP_NAME"));
                System.out.println("Department    : " + rs.getString("DEPARTMENT"));
                System.out.println("Updated Salary: " + rs.getInt("SALARY"));
            }

            // ==============================
            // DELETE
            // ==============================
            System.out.println("\n===== DELETE EMPLOYEE =====");

            int deleteRows = DatabaseUtil.executeUpdate(
                    "DELETE FROM EMPLOYEE WHERE EMP_ID = ?",
                    104);

            System.out.println("Rows Deleted : " + deleteRows);

            // ==============================
            // VERIFY DELETE
            // ==============================
            System.out.println("\n===== VERIFY DELETE =====");

            rs = DatabaseUtil.executeQuery(
                    "SELECT * FROM EMPLOYEE WHERE EMP_ID = ?",
                    104);

            if (!rs.next()) {

                System.out.println("Employee Record Deleted Successfully");

            } else {

                System.out.println("Employee Record Still Exists");

            }

            rs.close();

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            DatabaseUtil.closeConnection();

        }

    }
}