package selenium.user;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginTest {
    WebDriver driver;

    @Before
    public void startDriver() {
        System.setProperty("webdriver.chrome.driver", "c:\\selenium-drivers\\Chrome\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://ta-ebookrental-fe.herokuapp.com/login");
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void logWithValidData() {

        String userName = "karo1";
        String password = "karo111";

        fillLoginFormAndSend(userName, password);

        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(webDriver -> driver.findElement(By.cssSelector("p[class=\"alert__content\"]")));


        WebElement titlesContent = driver.findElement(By.cssSelector("h2.sub-title"));
        String contentAfterLog = titlesContent.getText();
        System.out.println(contentAfterLog);
        String correctContentAfterLog = "Titles catalog";

        boolean isPassed = contentAfterLog.equalsIgnoreCase(correctContentAfterLog);
        assertThat(isPassed).isTrue();

    }

    @Test
    public void logWithNotMatchingPassword() {

        String userName = "karo1";
        String password = "karo11";

        fillLoginFormAndSend(userName, password);

        String loginFailedMessage = "Login failed";

        boolean isPassed = loginFailedMessage.equalsIgnoreCase(getAlertMessage());
        assertThat(isPassed).isTrue();
    }

    @Test
    public void logWithEmptyPassword() {

        String userName = "karo1";
        String password = "";

        fillLoginFormAndSend(userName, password);

        String loginFailedMessage = "You can't leave fields empty";

        boolean isPassed = loginFailedMessage.equalsIgnoreCase(getAlertMessage());
        assertThat(isPassed).isTrue();
    }

    @Test
    public void logWithEmptyLogin() {

        String userName = "";
        String password = "karo1";

        fillLoginFormAndSend(userName, password);

        String loginFailedMessage = "You can't leave fields empty";

        boolean isPassed = loginFailedMessage.equalsIgnoreCase(getAlertMessage());
        assertThat(isPassed).isTrue();
    }

    @Test
    public void logWithEmptyData() {

        String userName = "";
        String password = "";

        fillLoginFormAndSend(userName, password);

        String loginFailedMessage = "You can't leave fields empty";

        boolean isPassed = loginFailedMessage.equalsIgnoreCase(getAlertMessage());
        assertThat(isPassed).isTrue();
    }

    private void fillLoginFormAndSend(String login, String password) {
        WebElement inputLogin = driver.findElement(By.cssSelector("input[name=\"login\"]"));
        inputLogin.sendKeys(login);

        WebElement inputPassword = driver.findElement(By.cssSelector("input[name=\"password\"]"));
        inputPassword.sendKeys(password);

        WebElement signUpButton = driver.findElement(By.cssSelector("button[id=\"login-btn\"]"));
        signUpButton.click();

    }

    private String getAlertMessage() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(webDriver -> driver.findElement(By.cssSelector("p[class=\"alert__content\"]")));

        WebElement alertMessage = driver.findElement(By.cssSelector("p[class=\"alert__content\"]"));
        return alertMessage.getText();
    }
}
