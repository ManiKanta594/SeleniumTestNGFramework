package etl;

import org.testng.Assert;

/**
 * ==========================================================
 * Class Name : ValidationUtil
 * Description: Contains all ETL Validation Methods
 * Author      : Manikanta
 * ==========================================================
 */
public final class ValidationUtil {

    // Prevent Object Creation
    private ValidationUtil() {

    }

    /**
     * Validates Source and Target Row Count.
     */
    public static void validateRowCount() {

        int sourceCount =
                DatabaseUtil.getRecordCount(SQLQueries.SOURCE_ROW_COUNT);

        int targetCount =
                DatabaseUtil.getRecordCount(SQLQueries.TARGET_ROW_COUNT);

        System.out.println("----------------------------------------");
        System.out.println("Source Row Count : " + sourceCount);
        System.out.println("Target Row Count : " + targetCount);
        System.out.println("----------------------------------------");

        Assert.assertEquals(
                sourceCount,
                targetCount,
                "Row Count Validation Failed.");

        System.out.println("Row Count Validation Passed.");

    }

}