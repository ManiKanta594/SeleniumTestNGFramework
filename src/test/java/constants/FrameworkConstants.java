package constants;

public final class FrameworkConstants {

    private FrameworkConstants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String PROJECT_PATH =
            System.getProperty("user.dir") + "\\";

    public static final String TESTDATA_PATH =
            PROJECT_PATH + "src\\test\\resources\\testdata\\";

    /*=========================
            REPORTS
     =========================*/

    public static final String REPORT_PATH =
            PROJECT_PATH + "target\\Reports\\";

    public static final String LATEST_REPORT_PATH =
            REPORT_PATH + "Latest\\";

    public static final String PREVIOUS_REPORT_PATH =
            REPORT_PATH + "Previous\\";

    public static final String REPORT_FILE_NAME =
            "AutomationReport.html";

    /*=========================
          SCREENSHOTS
     =========================*/

    public static final String SCREENSHOT_PATH =
            LATEST_REPORT_PATH + "Screenshots\\";

    public static final String PREVIOUS_SCREENSHOT_PATH =
            PREVIOUS_REPORT_PATH + "Screenshots\\";
}