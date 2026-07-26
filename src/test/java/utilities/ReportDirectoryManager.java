package utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import constants.FrameworkConstants;

public final class ReportDirectoryManager {

    private static final Logger LOGGER =
            LogManager.getLogger(ReportDirectoryManager.class);

    private ReportDirectoryManager() {
        throw new IllegalStateException("Utility class");
    }

    public static void prepareReportDirectories() {

        try {

            File latestFolder =
                    new File(FrameworkConstants.LATEST_REPORT_PATH);

            File previousFolder =
                    new File(FrameworkConstants.PREVIOUS_REPORT_PATH);

            LOGGER.info("Preparing report directories...");

            // Delete Previous folder
            if (previousFolder.exists()) {

                LOGGER.info("Deleting Previous report folder.");

                FileUtils.deleteDirectory(previousFolder);
            }

            // Copy Latest -> Previous
            if (latestFolder.exists()) {

                LOGGER.info("Copying Latest report to Previous.");

                FileUtils.copyDirectory(latestFolder, previousFolder);

                LOGGER.info("Deleting existing Latest report folder.");

                FileUtils.deleteDirectory(latestFolder);
            }

            // Create Latest folder
            FileUtils.forceMkdir(latestFolder);

            LOGGER.info("Created Latest report folder.");

            // Create Latest Screenshot folder
            FileUtils.forceMkdir(
                    new File(FrameworkConstants.SCREENSHOT_PATH));

            LOGGER.info("Created Screenshot folder.");

            LOGGER.info("Report directories prepared successfully.");

        } catch (IOException e) {

            LOGGER.error("Unable to prepare report directories.", e);

            throw new RuntimeException(
                    "Unable to prepare report directories.", e);
        }
    }
}