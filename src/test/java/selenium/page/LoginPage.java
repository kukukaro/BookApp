package selenium.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private final By loginInput = By.cssSelector("input[name=\"login\"]");
    private final By passwordInput = By.cssSelector("input[name=\"password\"]");
    private final By signUpButton = By.cssSelector("button[id=\"login-btn\"]");
    private final By alertMessage = By.cssSelector("p[class=\"alert__content\"]");

    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public HomePage logInSuccessfully(String userName, String password) {

        setUserName(userName);
        setPassword(password);
        clickLoginButton();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(signUpButton));

        return new HomePage(driver);
    }

    public LoginPage logInUnsuccessfully(String userName, String password) {
        setUserName(userName);
        setPassword(password);
        clickLoginButton();

        return this;
    }

    private void setUserName(String userName) {
        driver.findElement(loginInput).sendKeys(userName);
    }

    private void setPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }

    private void clickLoginButton() {
        driver.findElement(signUpButton).click();
    }

    public String getAlertMessage() {

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(alertMessage));
        return driver.findElement(alertMessage).getText();
    }
}
