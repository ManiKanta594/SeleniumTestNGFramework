package ai;

/**
 * ==========================================================
 * Class Name  : PromptBuilder
 * Package     : ai
 * Author      : Manikanta
 * ==========================================================
 *
 * Purpose
 * ----------------------------------------------------------
 * This class is responsible for creating prompts
 * that will be sent to OpenAI.
 *
 * Advantages
 * ----------------------------------------------------------
 * 1. Centralized Prompt Management
 * 2. Easy to Maintain
 * 3. Reusable Across Framework
 * 4. Supports Multiple AI Features
 *
 * AI Features
 * ----------------------------------------------------------
 * ✓ Failure Analysis
 * ✓ XPath Generation
 * ✓ SQL Query Generation
 * ✓ API Test Case Generation
 * ✓ Test Case Generation
 *
 * ==========================================================
 */

public class PromptBuilder {

    /**
     * ======================================================
     * Method Name : buildFailurePrompt()
     * ======================================================
     *
     * Purpose
     * ------------------------------------------------------
     * Builds AI prompt for Selenium Failure Analysis.
     *
     * Input
     * ------------------------------------------------------
     * Exception Message
     *
     * Output
     * ------------------------------------------------------
     * AI Prompt
     *
     * ======================================================
     */

    public static String buildFailurePrompt(String exceptionMessage) {

        return
                "You are a Selenium Automation Expert.\n\n"

              + "Analyze the following Selenium Exception.\n\n"

              + "Exception : " + exceptionMessage + "\n\n"

              + "Provide the following information:\n"

              + "1. Root Cause\n"
              + "2. Possible Reason\n"
              + "3. Suggested Fix\n"
              + "4. Best Practice\n"
              + "5. Confidence Level";

    }

    /**
     * ======================================================
     * Method Name : buildXPathPrompt()
     * ======================================================
     *
     * Purpose
     * ------------------------------------------------------
     * Generates XPath using AI.
     *
     * ======================================================
     */

    public static String buildXPathPrompt(String elementDescription) {

        return
                "Generate the best XPath for the following element.\n\n"

              + "Element Description : "

              + elementDescription

              + "\n\n"

              + "Rules:\n"

              + "- Use stable attributes.\n"
              + "- Avoid absolute XPath.\n"
              + "- Prefer Relative XPath.\n"
              + "- Return only XPath.";

    }

    /**
     * ======================================================
     * Method Name : buildSQLPrompt()
     * ======================================================
     *
     * Purpose
     * ------------------------------------------------------
     * Generates SQL Query.
     *
     * ======================================================
     */

    public static String buildSQLPrompt(String requirement) {

        return
                "Generate Oracle SQL Query.\n\n"

              + "Requirement : "

              + requirement

              + "\n\n"

              + "Return optimized SQL only.";

    }

    /**
     * ======================================================
     * Method Name : buildTestCasePrompt()
     * ======================================================
     *
     * Purpose
     * ------------------------------------------------------
     * Generates Manual Test Cases.
     *
     * ======================================================
     */

    public static String buildTestCasePrompt(String feature) {

        return
                "Generate Manual Test Cases.\n\n"

              + "Feature : "

              + feature

              + "\n\n"

              + "Include:\n"

              + "Positive Test Cases\n"
              + "Negative Test Cases\n"
              + "Boundary Test Cases";

    }

    /**
     * ======================================================
     * Method Name : buildAPIPrompt()
     * ======================================================
     *
     * Purpose
     * ------------------------------------------------------
     * Generates API Test Scenarios.
     *
     * ======================================================
     */

    public static String buildAPIPrompt(String apiName) {

        return
                "Generate REST API Test Cases.\n\n"

              + "API : "

              + apiName

              + "\n\n"

              + "Include:\n"

              + "HTTP Methods\n"
              + "Status Codes\n"
              + "Positive Tests\n"
              + "Negative Tests";

    }

}