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
     * Select tutorial by name.
     */
    public void selectTutorial(String tutorialName) {

        waitUtil.waitForPageLoad();

        System.out.println("====================================");
        System.out.println("Requested Tutorial : " + tutorialName);
        System.out.println("Total Tutorial Cards : " + tutorialCards.size());
        System.out.println("====================================");

        for (WebElement tutorial : tutorialCards) {

            String text = tutorial.getText().trim();

            System.out.println("Tutorial Found : [" + text + "]");

            if (text.equalsIgnoreCase(tutorialName)
                    || text.toLowerCase().contains(tutorialName.toLowerCase())) {

                System.out.println("Matching Tutorial : " + text);

                waitUtil.waitForClickable(tutorial).click();

                return;
            }
        }

        throw new RuntimeException("Tutorial not found : " + tutorialName);
    }
}