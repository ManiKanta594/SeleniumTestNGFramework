package ai;

import org.testng.annotations.Test;

/**
 * ==========================================================
 * Test Name : AI Demo
 * Package   : ai
 * Author    : Manikanta
 * ==========================================================
 *
 * Purpose
 * ----------------------------------------------------------
 * This class demonstrates all AI features developed
 * in the framework.
 *
 * Features Covered
 * ----------------------------------------------------------
 * ✓ XPath Generator
 * ✓ SQL Generator
 * ✓ API Test Generator
 * ✓ Test Case Generator
 *
 * ==========================================================
 */

public class AIDemo {

    @Test
    public void demoAI() {

        System.out.println("================================================");
        System.out.println("          AI INTEGRATION DEMO");
        System.out.println("================================================");

        // ==============================================
        // Generate XPath
        // ==============================================

        System.out.println();
        System.out.println("Generating XPath...");
        System.out.println();

        String xpath =
                XPathGenerator.generateXPath(
                        "Login Button");

        System.out.println(xpath);

        // ==============================================
        // Generate SQL Query
        // ==============================================

        System.out.println();
        System.out.println("Generating SQL...");
        System.out.println();

        String sql =
                SQLGenerator.generateSQL(
                        "Find Duplicate Customer Records");

        System.out.println(sql);

        // ==============================================
        // Generate API Test Cases
        // ==============================================

        System.out.println();
        System.out.println("Generating API Test Cases...");
        System.out.println();

        String api =
                APITestGenerator.generateAPITestCases(
                        "Login API");

        System.out.println(api);

        // ==============================================
        // Generate Manual Test Cases
        // ==============================================

        System.out.println();
        System.out.println("Generating Test Cases...");
        System.out.println();

        String testCases =
                TestCaseGenerator.generateTestCases(
                        "Customer Registration");

        System.out.println(testCases);

        System.out.println();
        System.out.println("================================================");
        System.out.println("      AI DEMO EXECUTION COMPLETED");
        System.out.println("================================================");

    }

}