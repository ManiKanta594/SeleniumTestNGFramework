package seleniumpracticeset2;

import java.io.File;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ZTest17PdfVerification {

    @Test
    public void verifyPDF() throws Exception {

        //==========================================================
        // PDF File Location
        //==========================================================

        File pdfFile =
                new File(System.getProperty("user.home")
                        + "\\Downloads\\Enterprise_Sample_Report.pdf");

        //==========================================================
        // Load PDF Document
        //==========================================================

        PDDocument document =
                Loader.loadPDF(pdfFile);

        //==========================================================
        // Extract Complete PDF Text
        //==========================================================

        PDFTextStripper stripper =
                new PDFTextStripper();

        String pdfText =
                stripper.getText(document);

        System.out.println("========================================");
        System.out.println("PDF CONTENT");
        System.out.println("========================================");
        System.out.println(pdfText);

        //==========================================================
        // Expected Values
        //==========================================================

        String[] expectedValues = {

                // Company Information
                "MANI TECH SOLUTIONS",
                "RPT-2026-001",
                "TBL-CUST-1001",
                "Automation Framework",
                "QA",
                "Customer Portal",
                "1.0.0",

                // Customer Table
                "1001",
                "Rahul Sharma",
                "IT",
                "Hyderabad",
                "45000",

                "1002",
                "Priya Singh",
                "HR",
                "Bangalore",
                "52000",

                "1003",
                "Amit Verma",
                "Finance",
                "Pune",
                "68000",

                "1004",
                "Sneha Rao",
                "QA",
                "Chennai",
                "39000",

                "1005",
                "Harika Devi",
                "Support",
                "72000",

                // Summary
                "Total Records",
                "276000",
                "55200",
                "72000",
                "39000",
                "SUCCESS",

                // Footer
                "Confidential",
                "REF-987654321"

        };

        //==========================================================
        // Soft Assert Object
        //==========================================================

        SoftAssert softAssert = new SoftAssert();

        int passed = 0;
        int failed = 0;

        System.out.println();
        System.out.println("========================================");
        System.out.println("PDF VERIFICATION STARTED");
        System.out.println("========================================");

        //==========================================================
        // Verify Each Expected Value
        //==========================================================

        for (String value : expectedValues) {

            System.out.println("----------------------------------------");
            System.out.println("Verifying : " + value);

            boolean found = pdfText.contains(value);

            if (found) {

                passed++;

                System.out.println("Result     : PASS");

            } else {

                failed++;

                System.out.println("Result     : FAIL");

            }

            softAssert.assertTrue(
                    found,
                    value + " Not Found In PDF");

        }

        //==========================================================
        // Verification Summary
        //==========================================================

        System.out.println();
        System.out.println("========================================");
        System.out.println("PDF VERIFICATION SUMMARY");
        System.out.println("========================================");

        System.out.println("Total Validations : " + expectedValues.length);
        System.out.println("Passed            : " + passed);
        System.out.println("Failed            : " + failed);

        if (failed == 0) {

            System.out.println();
            System.out.println("FINAL RESULT : PASS");

        } else {

            System.out.println();
            System.out.println("FINAL RESULT : FAIL");

        }

        System.out.println("========================================");

        //==========================================================
        // Close PDF
        //==========================================================

        document.close();

        //==========================================================
        // Final Assertion
        //==========================================================

        softAssert.assertAll();

    }

}