package selenium.page;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage {

    @FindBy(how= How.CSS, using = "input[name=\"login\"]" )
    private SelenideElement loginInput;

    @FindBy(how= How.CSS, using = "input[name=\"password\"]" )
    private SelenideElement passwordInput;

    @FindBy(how= How.CSS, using = "button[id=\"login-btn\"]" )
    private SelenideElement signUpButton;


    public void setUserName(String userName) {
        loginInput.setValue(userName);
    }

    public void setPassword(String password) {
        passwordInput.setValue(password);
    }

    public void clickLoginButton() {
        signUpButton.click();
    }

    public String getAlertMessage() {
        return Selenide.$("p[class=\"alert__content\"]").getText();
    }
}
