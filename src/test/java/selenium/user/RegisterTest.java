package selenium.user;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.assertj.core.api.Assertions.assertThat;

public class RegisterTest {


    @Test
    public void registerWithValidData()  {
        System.setProperty("webdriver.chrome.driver", "c:\\selenium-drivers\\Chrome\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://ta-ebookrental-fe.herokuapp.com/register");

        String userName = RandomStringUtils.randomAlphabetic(10);
        String password = "Karo1";

        WebElement inputLogin = driver.findElement(By.cssSelector("input[name=\"login\"]"));
        inputLogin.sendKeys(userName);

        WebElement inputPassword = driver.findElement(By.cssSelector("input[name=\"password\"]"));
        inputPassword.sendKeys(password);

        WebElement inputConfirmPassword = driver.findElement(By.cssSelector("input[name=\"password-repeat\"]"));
        inputConfirmPassword.sendKeys(password);

        WebElement signUpButton = driver.findElement(By.cssSelector("button[id=\"register-btn\"]"));
        signUpButton.click();

        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(webDriver -> driver.findElement(By.cssSelector("p[class=\"alert__content\"]")));


        String correctRegisterConfirmation = "You have been successfully registered!";

        WebElement registerPassed = driver.findElement(By.cssSelector("p[class=\"alert__content\"]"));
        String registerPassedConfirmation = registerPassed.getText();

        System.out.println(registerPassedConfirmation);

        boolean isPassed = correctRegisterConfirmation.equals(registerPassedConfirmation);
        System.out.println(isPassed);
        assertThat(isPassed).isTrue();

        driver.quit();
    }
}
