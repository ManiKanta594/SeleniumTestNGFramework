package dataproviders;

import org.testng.annotations.DataProvider;

import utilities.ExcelUtil;

public class ExcelDataProviderAllTests {

	@DataProvider(name = "RegistrationData")
    public Object[][] getRegistrationData() {

		 return ExcelUtil.getExecutionData("Registration");

    }
}
