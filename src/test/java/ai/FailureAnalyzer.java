package ai;

/**
 * ==========================================================
 * Class Name : FailureAnalyzer
 * Package    : ai
 * Author     : Manikanta
 * ==========================================================
 *
 * Purpose
 * ----------------------------------------------------------
 * This class analyzes Selenium failures using OpenAI.
 *
 * Execution Flow
 * ----------------------------------------------------------
 * Selenium Exception
 *        ↓
 * Build Prompt
 *        ↓
 * Send to OpenAI
 *        ↓
 * Receive AI Response
 *        ↓
 * Return Analysis
 *
 * Real Time Usage
 * ----------------------------------------------------------
 * Whenever a Selenium Test Fails,
 * this class will automatically analyze
 * the failure and suggest a possible fix.
 *
 * ==========================================================
 */

public class FailureAnalyzer {

    /**
     * ======================================================
     * Method Name : analyzeFailure()
     * ======================================================
     *
     * Purpose
     * ------------------------------------------------------
     * Sends Selenium Exception to OpenAI
     * and returns AI analysis.
     *
     * Parameter
     * ------------------------------------------------------
     * Exception object
     *
     * Return
     * ------------------------------------------------------
     * AI Response
     *
     * ======================================================
     */

    public static String analyzeFailure(Exception exception) {

        try {

            // ==================================================
            // Step 1
            // Read Selenium Exception Message
            // ==================================================

            String exceptionMessage =
                    exception.getMessage();

            // ==================================================
            // Step 2
            // Generate AI Prompt
            // ==================================================

            String prompt =
                    PromptBuilder.buildFailurePrompt(
                            exceptionMessage);

            // ==================================================
            // Step 3
            // Send Prompt to OpenAI
            // ==================================================

            String aiResponse =
                    OpenAIClient.askAI(prompt);

            // ==================================================
            // Step 4
            // Return AI Response
            // ==================================================

            return aiResponse;

        }

        catch (Exception e) {

            // ==================================================
            // If AI Service is unavailable
            // ==================================================

            return "Unable to analyze failure.\n\n"
                    + e.getMessage();

        }

    }

}