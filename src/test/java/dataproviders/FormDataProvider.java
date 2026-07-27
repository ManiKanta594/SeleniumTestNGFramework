package dataproviders;

import org.testng.annotations.DataProvider;

public class FormDataProvider {

    @DataProvider(name = "formData")
    public Object[][] getFormData() {

        return new Object[][] {

                {"Mani","mani@gmail.com","9876543210","Hyderabad"},

                {"Ravi","ravi@gmail.com","9123456789","Bangalore"},

                {"Rahul","rahul@gmail.com","9988776655","Chennai"}

        };

    }

}