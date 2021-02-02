package selenium.user;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.assertj.core.api.Assertions.assertThat;

public class RegisterTest {

    private WebDriver driver;

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void setupTest() {
        driver = new ChromeDriver();
        driver.get("https://ta-ebookrental-fe.herokuapp.com/register");
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }


    @Test
    public void registerWithValidData() {

        String userName = RandomStringUtils.randomAlphabetic(10);
        String password = "Karo1";

        String correctRegisterConfirmation = "You have been successfully registered!";
        String registerPassedConfirmation = fillRegisterFormAndSend(userName, password, password);

        boolean isPassed = correctRegisterConfirmation.equals(registerPassedConfirmation);
        assertThat(isPassed).isTrue();
    }

    @Test
    public void registerWithEmptyLogin() {

        String userName = "";
        String password = "Karo1";

        String incorrectRegisterConfirmation = "You can't leave fields empty";
        String registerPassedConfirmation = fillRegisterFormAndSend(userName, password, password);

        boolean isPassed = incorrectRegisterConfirmation.equals(registerPassedConfirmation);
        assertThat(isPassed).isTrue();
    }

    @Test
    public void registerWithEmptyPassword() {

        String userName = RandomStringUtils.randomAlphabetic(10);
        String password = "";

        String incorrectRegisterConfirmation = "You can't leave fields empty";
        String registerPassedConfirmation = fillRegisterFormAndSend(userName, password, password);

        boolean isPassed = incorrectRegisterConfirmation.equals(registerPassedConfirmation);
        assertThat(isPassed).isTrue();
    }

    @Test
    public void registerWithNotMatchingPassword() {

        String userName = RandomStringUtils.randomAlphabetic(10);
        String password = RandomStringUtils.randomAlphabetic(10);
        String password2 = RandomStringUtils.randomAlphabetic(10);

        String incorrectRegisterConfirmation = "The passwords don't match";
        String registerPassedConfirmation = fillRegisterFormAndSend(userName, password, password2);

        boolean isPassed = incorrectRegisterConfirmation.equals(registerPassedConfirmation);
        assertThat(isPassed).isTrue();
    }

    private String fillRegisterFormAndSend(String login, String password, String password2) {
        WebElement inputLogin = driver.findElement(By.cssSelector("input[name=\"login\"]"));
        inputLogin.sendKeys(login);

        WebElement inputPassword = driver.findElement(By.cssSelector("input[name=\"password\"]"));
        inputPassword.sendKeys(password);

        WebElement inputConfirmPassword = driver.findElement(By.cssSelector("input[name=\"password-repeat\"]"));
        inputConfirmPassword.sendKeys(password2);

        WebElement signUpButton = driver.findElement(By.cssSelector("button[id=\"register-btn\"]"));
        signUpButton.click();

        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(webDriver -> driver.findElement(By.cssSelector("p[class=\"alert__content\"]")));

        WebElement registerFailed = driver.findElement(By.cssSelector("p[class=\"alert__content\"]"));
        return registerFailed.getText();
    }
}
