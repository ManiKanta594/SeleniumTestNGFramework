package utilities;

import java.time.LocalDateTime;

public class ExecutionSummary {

    private static final ExecutionSummary INSTANCE = new ExecutionSummary();

    private int totalTests;
    private int executedTests;
    private int passedTests;
    private int failedTests;
    private int skippedTests;

    private String browser;
    private String environment;
    private String executionMode;
    private String driverMode;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private long executionTimeInSeconds;

    private ExecutionSummary() {
    }

    public static ExecutionSummary getInstance() {
        return INSTANCE;
    }

    // Getters

    public int getTotalTests() {
        return totalTests;
    }

    public int getExecutedTests() {
        return executedTests;
    }

    public int getPassedTests() {
        return passedTests;
    }

    public int getFailedTests() {
        return failedTests;
    }

    public int getSkippedTests() {
        return skippedTests;
    }

    public String getBrowser() {
        return browser;
    }

    public String getEnvironment() {
        return environment;
    }

    public String getExecutionMode() {
        return executionMode;
    }

    public String getDriverMode() {
        return driverMode;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public long getExecutionTimeInSeconds() {
        return executionTimeInSeconds;
    }

    // Setters

    public void setTotalTests(int totalTests) {
        this.totalTests = totalTests;
    }

    public void setExecutedTests(int executedTests) {
        this.executedTests = executedTests;
    }

    public void setPassedTests(int passedTests) {
        this.passedTests = passedTests;
    }

    public void setFailedTests(int failedTests) {
        this.failedTests = failedTests;
    }

    public void setSkippedTests(int skippedTests) {
        this.skippedTests = skippedTests;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public void setExecutionMode(String executionMode) {
        this.executionMode = executionMode;
    }

    public void setDriverMode(String driverMode) {
        this.driverMode = driverMode;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public void setExecutionTimeInSeconds(long executionTimeInSeconds) {
        this.executionTimeInSeconds = executionTimeInSeconds;
    }

    // Counter Methods

    public void incrementPassedTests() {
        passedTests++;
    }

    public void incrementFailedTests() {
        failedTests++;
    }

    public void incrementSkippedTests() {
        skippedTests++;
    }

    public void incrementExecutedTests() {
        executedTests++;
    }

    public void reset() {
        totalTests = 0;
        executedTests = 0;
        passedTests = 0;
        failedTests = 0;
        skippedTests = 0;

        browser = null;
        environment = null;
        executionMode = null;
        driverMode = null;

        startTime = null;
        endTime = null;
        executionTimeInSeconds = 0;
    }
}