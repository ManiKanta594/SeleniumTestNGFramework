package utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import constants.FrameworkConstants;

public final class ReportDirectoryManager {

    private ReportDirectoryManager() {
        throw new IllegalStateException("Utility class");
    }

    public static void prepareReportDirectories() {

        try {

            File latestFolder =
                    new File(FrameworkConstants.LATEST_REPORT_PATH);

            File previousFolder =
                    new File(FrameworkConstants.PREVIOUS_REPORT_PATH);

            // Delete Previous folder
            if (previousFolder.exists()) {
                FileUtils.deleteDirectory(previousFolder);
            }

            // Copy Latest -> Previous
            if (latestFolder.exists()) {
                FileUtils.copyDirectory(latestFolder, previousFolder);

                // Delete Latest folder
                FileUtils.deleteDirectory(latestFolder);
            }

            // Create Latest folder
            FileUtils.forceMkdir(latestFolder);

            // Create Latest Screenshot folder
            FileUtils.forceMkdir(
                    new File(FrameworkConstants.SCREENSHOT_PATH));

        } catch (IOException e) {

            throw new RuntimeException(
                    "Unable to prepare report directories.", e);
        }
    }
}