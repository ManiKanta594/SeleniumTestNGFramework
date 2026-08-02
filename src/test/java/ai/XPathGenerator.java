package ai;

/**
 * ==========================================================
 * Class Name : XPathGenerator
 * Package    : ai
 * Author     : Manikanta
 * ==========================================================
 *
 * Purpose
 * ----------------------------------------------------------
 * This class generates XPath using OpenAI.
 *
 * Real Time Usage
 * ----------------------------------------------------------
 * Instead of manually writing XPath,
 * AI generates the locator based on
 * element description.
 *
 * Example
 * ----------------------------------------------------------
 * Input :
 *
 * Email Textbox with placeholder Enter Email
 *
 * Output :
 *
 * //input[@placeholder='Enter Email']
 *
 * ==========================================================
 */

public class XPathGenerator {

    /**
     * ======================================================
     * Method Name : generateXPath()
     * ======================================================
     *
     * Purpose
     * ------------------------------------------------------
     * Sends element description to AI
     * and returns generated XPath.
     *
     * Parameter
     * ------------------------------------------------------
     * Element Description
     *
     * Return
     * ------------------------------------------------------
     * AI Generated XPath
     *
     * ======================================================
     */

    public static String generateXPath(String elementDescription) {

        try {

            // ==================================================
            // Step 1
            // Build AI Prompt
            // ==================================================

            String prompt =
                    PromptBuilder.buildXPathPrompt(
                            elementDescription);

            // ==================================================
            // Step 2
            // Send Prompt to OpenAI
            // ==================================================

            String response =
                    OpenAIClient.askAI(prompt);

            // ==================================================
            // Step 3
            // Return Generated XPath
            // ==================================================

            return response;

        }

        catch (Exception e) {

            return "Unable to Generate XPath.\n\n"
                    + e.getMessage();

        }

    }

}