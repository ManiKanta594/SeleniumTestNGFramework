package ai;

import com.aventstack.extentreports.ExtentTest;

/**
 * ==========================================================
 * Class Name : AIReportManager
 * Package    : ai
 * Author     : Manikanta
 * ==========================================================
 *
 * Purpose
 * ----------------------------------------------------------
 * This class writes AI-generated suggestions into
 * the Extent Report.
 *
 * Real Time Usage
 * ----------------------------------------------------------
 * Whenever a Selenium Test fails,
 * AI suggestions will automatically be
 * displayed inside the Extent Report.
 *
 * ==========================================================
 */

public class AIReportManager {

    /**
     * ======================================================
     * Method Name : addAIAnalysis()
     * ======================================================
     *
     * Purpose
     * ------------------------------------------------------
     * Adds AI response into Extent Report.
     *
     * Parameters
     * ------------------------------------------------------
     * extentTest -> Current Test Instance
     * aiResponse -> AI Generated Suggestion
     *
     * ======================================================
     */

    public static void addAIAnalysis
            (ExtentTest extentTest,
             String aiResponse) {

        // ==================================================
        // Validate AI Response
        // ==================================================

        if (aiResponse == null ||
                aiResponse.isBlank()) {

            extentTest.info(
                    "AI Analysis Not Available.");

            return;
        }

        // ==================================================
        // Add Heading
        // ==================================================

        extentTest.info(
                "======================================");

        extentTest.info(
                "AI FAILURE ANALYSIS");

        extentTest.info(
                "======================================");

        // ==================================================
        // Add AI Response
        // ==================================================

        extentTest.info(aiResponse);

        // ==================================================
        // End Section
        // ==================================================

        extentTest.info(
                "======================================");

    }

}