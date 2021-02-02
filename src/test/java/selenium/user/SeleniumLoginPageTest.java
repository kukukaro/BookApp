package selenium.user;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import selenium.page.HomePage;
import selenium.page.LoginPage;

import static org.assertj.core.api.Assertions.assertThat;

public class SeleniumLoginPageTest {
    private WebDriver driver;

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void setupTest() {
        driver = new ChromeDriver();
        driver.get("https://ta-ebookrental-fe.herokuapp.com/login");
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void logWithValidData() {

        String userName = "karo1";
        String password = "karo111";

        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = loginPage.logInSuccessfully(userName, password);

        assertThat(homePage.isHomePage()).isTrue();
    }


    @Test
    public void logWithNotMatchingPassword() {

        String userName = "karo1";
        String password = "karo11";

        LoginPage loginPage = new LoginPage(driver);
        loginPage.logInUnsuccessfully(userName, password);

        assertThat(loginPage.getAlertMessage()).isEqualToIgnoringCase("Login failed");
    }

    @Test
    public void logWithEmptyPassword() {

        String userName = "karo1";
        String password = "";

        LoginPage loginPage = new LoginPage(driver);
        loginPage.logInUnsuccessfully(userName, password);

        assertThat(loginPage.getAlertMessage()).isEqualToIgnoringCase("You can't leave fields empty");
    }

    @Test
    public void logWithEmptyLogin() {

        String userName = "";
        String password = "karo1";

        LoginPage loginPage = new LoginPage(driver);
        loginPage.logInUnsuccessfully(userName, password);

        assertThat(loginPage.getAlertMessage()).isEqualToIgnoringCase("You can't leave fields empty");
    }

    @Test
    public void logWithEmptyData() {

        String userName = "";
        String password = "";

        LoginPage loginPage = new LoginPage(driver);
        loginPage.logInUnsuccessfully(userName, password);

        assertThat(loginPage.getAlertMessage()).isEqualToIgnoringCase("You can't leave fields empty");
    }

}
