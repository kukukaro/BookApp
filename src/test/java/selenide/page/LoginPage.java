package selenide.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    private final By loginInput = By.cssSelector("input[name=\"login\"]");
    private final By passwordInput = By.cssSelector("input[name=\"password\"]");
    private final By signUpButton = By.cssSelector("button[id=\"login-btn\"]");
    private final By alertMessage = By.cssSelector("p[class=\"alert__content\"]");

    public LoginPage open() {
        Selenide.open("login");
        $(loginInput).shouldBe(Condition.visible, Duration.of(10, ChronoUnit.SECONDS));
        return this;
    }

    public HomePage logInSuccessfully(String userName, String password) {

        setUserName(userName);
        setPassword(password);
        clickLoginButton();

        $(loginInput).shouldNotBe(Condition.visible, Duration.of(10, ChronoUnit.SECONDS));

        HomePage homePage = Selenide.page(HomePage.class);
        return homePage;
    }

    public LoginPage logInUnsuccessfully(String userName, String password) {
        setUserName(userName);
        setPassword(password);
        clickLoginButton();

        return this;
    }

    private void setUserName(String userName) {
        $(loginInput).setValue(userName);
    }

    private void setPassword(String password) {
        $(passwordInput).setValue(password);
    }

    private void clickLoginButton() {
        $(signUpButton).click();
    }

    public String getAlertMessage() {
        return $(alertMessage).getText();
    }
}
