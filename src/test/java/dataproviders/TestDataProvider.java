package dataproviders;

import org.testng.annotations.DataProvider;

public final class TestDataProvider {

    private TestDataProvider() {
        throw new IllegalStateException("Utility class");
    }

    @DataProvider(name = "loginData")
    public static Object[][] loginData() {

        return new Object[][]{
                {"admin@gmail.com", "admin123"},
                {"user@gmail.com", "user123"},
                {"invalid@gmail.com", "invalid123"}
        };
    }

}