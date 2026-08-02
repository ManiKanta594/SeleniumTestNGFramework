package ai;

/**
 * ==========================================================
 * Class Name : TestCaseGenerator
 * Package    : ai
 * Author     : Manikanta
 * ==========================================================
 *
 * Purpose
 * ----------------------------------------------------------
 * This class generates Manual and Automation
 * Test Cases using OpenAI.
 *
 * Real Time Usage
 * ----------------------------------------------------------
 * Instead of manually writing test cases,
 * AI generates:
 *
 * ✓ Positive Test Cases
 * ✓ Negative Test Cases
 * ✓ Boundary Test Cases
 * ✓ Edge Test Cases
 *
 * Example
 * ----------------------------------------------------------
 * Input
 * -----
 * Login Page
 *
 * Output
 * ------
 * Complete Test Scenarios
 *
 * ==========================================================
 */

public class TestCaseGenerator {

    /**
     * ======================================================
     * Method Name : generateTestCases()
     * ======================================================
     *
     * Purpose
     * ------------------------------------------------------
     * Generates Test Cases using OpenAI.
     *
     * Parameter
     * ------------------------------------------------------
     * Feature Name
     *
     * Return
     * ------------------------------------------------------
     * Generated Test Cases
     *
     * ======================================================
     */

    public static String generateTestCases(String feature) {

        try {

            // ==================================================
            // Step 1
            // Build Prompt
            // ==================================================

            String prompt =
                    PromptBuilder.buildTestCasePrompt(feature);

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

            return "Unable to Generate Test Cases.\n\n"
                    + e.getMessage();

        }

    }

}