package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TutorialsPage extends BasePage {

    public TutorialsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h4[contains(@class,'tutnav-card-title')]")
    private List<WebElement> tutorialCards;

    /**
     * Select a tutorial card by name.
     */
    public void selectTutorial(String tutorialName) {

        waitUtil.waitForPageLoad();

        for (WebElement tutorial : tutorialCards) {

            if (tutorial.getText().trim().equalsIgnoreCase(tutorialName)) {

                jsUtil.scrollIntoView(tutorial);

                waitUtil.waitForClickable(tutorial).click();

                return;
            }
        }

        throw new RuntimeException("Tutorial not found : " + tutorialName);
    }
}