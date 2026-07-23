package managers;

import org.openqa.selenium.WebDriver;

import pages.CssPage;
import pages.HomePage;
import pages.HtmlPage;
import pages.JavaPage;
import pages.LoginPage;
import pages.PythonPage;
import pages.SqlPage;
import pages.TutorialsPage;

public class PageObjectManager {

    private final WebDriver driver;

    private LoginPage loginPage;
    private HomePage homePage;
    private TutorialsPage tutorialsPage;
    private PythonPage pythonPage;
    private JavaPage javaPage;
    private HtmlPage htmlPage;
    private SqlPage sqlPage;
    private CssPage cssPage;

    public PageObjectManager(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage getLoginPage() {

        if (loginPage == null) {
            loginPage = new LoginPage(driver);
        }

        return loginPage;
    }

    public HomePage getHomePage() {

        if (homePage == null) {
            homePage = new HomePage(driver);
        }

        return homePage;
    }

    public TutorialsPage getTutorialsPage() {

        if (tutorialsPage == null) {
            tutorialsPage = new TutorialsPage(driver);
        }

        return tutorialsPage;
    }

    public PythonPage getPythonPage() {

        if (pythonPage == null) {
            pythonPage = new PythonPage(driver);
        }

        return pythonPage;
    }

    public JavaPage getJavaPage() {

        if (javaPage == null) {
            javaPage = new JavaPage(driver);
        }

        return javaPage;
    }

    public HtmlPage getHtmlPage() {

        if (htmlPage == null) {
            htmlPage = new HtmlPage(driver);
        }

        return htmlPage;
    }

    public SqlPage getSqlPage() {

        if (sqlPage == null) {
            sqlPage = new SqlPage(driver);
        }

        return sqlPage;
    }

    public CssPage getCssPage() {

        if (cssPage == null) {
            cssPage = new CssPage(driver);
        }

        return cssPage;
    }

}