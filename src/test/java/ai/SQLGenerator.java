package ai;

/**
 * ==========================================================
 * Class Name : SQLGenerator
 * Package    : ai
 * Author     : Manikanta
 * ==========================================================
 *
 * Purpose
 * ----------------------------------------------------------
 * This class generates Oracle SQL Queries
 * using OpenAI.
 *
 * Real Time Usage
 * ----------------------------------------------------------
 * Automation Engineers and ETL Testers can
 * generate SQL queries by providing only
 * the business requirement.
 *
 * Example
 * ----------------------------------------------------------
 * Input
 * -----
 * Find duplicate customer records.
 *
 * Output
 * ------
 * SELECT CUSTOMER_ID,
 * COUNT(*)
 * FROM CUSTOMER
 * GROUP BY CUSTOMER_ID
 * HAVING COUNT(*) > 1;
 *
 * ==========================================================
 */

public class SQLGenerator {

    /**
     * ======================================================
     * Method Name : generateSQL()
     * ======================================================
     *
     * Purpose
     * ------------------------------------------------------
     * Generates Oracle SQL Query
     * using OpenAI.
     *
     * Parameter
     * ------------------------------------------------------
     * SQL Requirement
     *
     * Return
     * ------------------------------------------------------
     * Generated SQL Query
     *
     * ======================================================
     */

    public static String generateSQL(String requirement) {

        try {

            // ==================================================
            // Step 1
            // Build SQL Prompt
            // ==================================================

            String prompt =
                    PromptBuilder.buildSQLPrompt(requirement);

            // ==================================================
            // Step 2
            // Send Prompt to OpenAI
            // ==================================================

            String response =
                    OpenAIClient.askAI(prompt);

            // ==================================================
            // Step 3
            // Return SQL Query
            // ==================================================

            return response;

        }

        catch (Exception e) {

            return "Unable to Generate SQL.\n\n"
                    + e.getMessage();

        }

    }

}