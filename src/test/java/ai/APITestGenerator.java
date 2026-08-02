package ai;

/**
 * ==========================================================
 * Class Name : APITestGenerator
 * Package    : ai
 * Author     : Manikanta
 * ==========================================================
 *
 * Purpose
 * ----------------------------------------------------------
 * This class generates REST API Test Scenarios
 * using OpenAI.
 *
 * Real Time Usage
 * ----------------------------------------------------------
 * Instead of manually preparing API test cases,
 * AI generates Positive, Negative and Boundary
 * test scenarios automatically.
 *
 * Example
 * ----------------------------------------------------------
 * Input
 * -----
 * Login API
 *
 * Output
 * ------
 * HTTP Method
 * Request Body
 * Positive Test Cases
 * Negative Test Cases
 * Status Code Validation
 * Boundary Test Cases
 *
 * ==========================================================
 */

public class APITestGenerator {

    /**
     * ======================================================
     * Method Name : generateAPITestCases()
     * ======================================================
     *
     * Purpose
     * ------------------------------------------------------
     * Generates API Test Cases using OpenAI.
     *
     * Parameter
     * ------------------------------------------------------
     * API Name
     *
     * Return
     * ------------------------------------------------------
     * Generated API Test Cases
     *
     * ======================================================
     */

    public static String generateAPITestCases(String apiName) {

        try {

            // ==================================================
            // Step 1
            // Build API Prompt
            // ==================================================

            String prompt =
                    PromptBuilder.buildAPIPrompt(apiName);

            // ==================================================
            // Step 2
            // Send Prompt to OpenAI
            // ==================================================

            String response =
                    OpenAIClient.askAI(prompt);

            // ==================================================
            // Step 3
            // Return AI Response
            // ==================================================

            return response;

        }

        catch (Exception e) {

            return "Unable to Generate API Test Cases.\n\n"
                    + e.getMessage();

        }

    }

}