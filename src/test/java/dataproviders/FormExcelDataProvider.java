package dataproviders;

import org.testng.annotations.DataProvider;

import utilities.ExcelUtil;

public class FormExcelDataProvider {

    @DataProvider(name = "RegistrationData")
    public Object[][] getRegistrationData() {

        return ExcelUtil.getTestData("Registration");

    }
}