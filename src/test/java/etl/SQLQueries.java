package etl;

/**
 * ==========================================================
 * Class Name : SQLQueries
 * Module     : ETL Automation Framework
 * Description: Stores all SQL Queries
 * Author     : Manikanta
 * ==========================================================
 */
public final class SQLQueries {

    // Prevent Object Creation
    private SQLQueries() {

    }

    /*==========================================================
     * ROW COUNT VALIDATION
     *==========================================================*/

    public static final String SOURCE_ROW_COUNT =
            "SELECT COUNT(*) FROM CUSTOMER_SOURCE";

    public static final String TARGET_ROW_COUNT =
            "SELECT COUNT(*) FROM CUSTOMER_TARGET";


    /*==========================================================
     * COLUMN COUNT VALIDATION
     *==========================================================*/

    public static final String SOURCE_COLUMN_COUNT =
            "SELECT COUNT(*) FROM USER_TAB_COLUMNS "
          + "WHERE TABLE_NAME='CUSTOMER_SOURCE'";

    public static final String TARGET_COLUMN_COUNT =
            "SELECT COUNT(*) FROM USER_TAB_COLUMNS "
          + "WHERE TABLE_NAME='CUSTOMER_TARGET'";


    /*==========================================================
     * MISSING RECORDS
     *==========================================================*/

    public static final String MISSING_RECORDS =
            "SELECT * FROM CUSTOMER_SOURCE "
          + "MINUS "
          + "SELECT * FROM CUSTOMER_TARGET";


    /*==========================================================
     * EXTRA RECORDS
     *==========================================================*/

    public static final String EXTRA_RECORDS =
            "SELECT * FROM CUSTOMER_TARGET "
          + "MINUS "
          + "SELECT * FROM CUSTOMER_SOURCE";


    /*==========================================================
     * SALARY MISMATCH
     *==========================================================*/

    public static final String SALARY_MISMATCH =
            "SELECT S.CUSTOMER_ID, "
          + "S.SALARY SOURCE_SALARY, "
          + "T.SALARY TARGET_SALARY "
          + "FROM CUSTOMER_SOURCE S "
          + "JOIN CUSTOMER_TARGET T "
          + "ON S.CUSTOMER_ID=T.CUSTOMER_ID "
          + "WHERE S.SALARY<>T.SALARY";

}