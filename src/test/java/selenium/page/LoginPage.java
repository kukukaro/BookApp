package selenium.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class LoginPage {

    @FindBy(how= How.CSS, using = "input[name=\"login\"]" )
    private SelenideElement loginInput;

    @FindBy(how= How.CSS, using = "input[name=\"password\"]" )
    private SelenideElement passwordInput;

    @FindBy(how= How.CSS, using = "button[id=\"login-btn\"]" )
    private SelenideElement signUpButton;


    public HomePage logInSuccessfully(String userName, String password){

        setUserName(userName);
        setPassword(password);
        clickLoginButton();

        loginInput.shouldNotBe(Condition.visible, Duration.of(10, ChronoUnit.SECONDS));

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
        loginInput.setValue(userName);
    }

    private void setPassword(String password) {
        passwordInput.setValue(password);
    }

    private void clickLoginButton() {
        signUpButton.click();
    }

    public String getAlertMessage() {
        return Selenide.$("p[class=\"alert__content\"]").getText();
    }
}
