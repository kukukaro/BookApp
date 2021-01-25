package selenium.user;

import com.codeborne.selenide.Selenide;
import org.junit.Test;
import selenium.page.HomePage;
import selenium.page.LoginPage;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginPageTest {

    @Test
    public void logWithValidData() {

        String userName = "karo1";
        String password = "karo111";

        Selenide.open("https://ta-ebookrental-fe.herokuapp.com/login");

        LoginPage loginPage = Selenide.page(LoginPage.class);
        HomePage homePage = loginPage.logInSuccessfully(userName, password);

        assertThat(homePage.isHomePage()).isTrue();

    }

    @Test
    public void logWithNotMatchingPassword() {

        String userName = "karo1";
        String password = "karo11";

        LoginPage loginPage = fillLoginFormAndSend(userName, password);

        String loginFailedMessage = "Login failed";

        boolean isPassed = loginFailedMessage.equalsIgnoreCase(loginPage.getAlertMessage());
        assertThat(isPassed).isTrue();
    }



    @Test
    public void logWithEmptyPassword() {

        String userName = "karo1";
        String password = "";

        LoginPage loginPage = fillLoginFormAndSend(userName, password);

        String loginFailedMessage = "You can't leave fields empty";

        boolean isPassed = loginFailedMessage.equalsIgnoreCase(loginPage.getAlertMessage());
        assertThat(isPassed).isTrue();
    }

    @Test
    public void logWithEmptyLogin() {

        String userName = "";
        String password = "karo1";

        LoginPage loginPage = fillLoginFormAndSend(userName, password);

        String loginFailedMessage = "You can't leave fields empty";

        boolean isPassed = loginFailedMessage.equalsIgnoreCase(loginPage.getAlertMessage());
        assertThat(isPassed).isTrue();
    }

    @Test
    public void logWithEmptyData() {

        String userName = "";
        String password = "";

        LoginPage loginPage = fillLoginFormAndSend(userName, password);

        String loginFailedMessage = "You can't leave fields empty";

        boolean isPassed = loginFailedMessage.equalsIgnoreCase(loginPage.getAlertMessage());
        assertThat(isPassed).isTrue();
    }

    private LoginPage fillLoginFormAndSend(String userName, String password) {
        Selenide.open("https://ta-ebookrental-fe.herokuapp.com/login");

        LoginPage loginPage = Selenide.page(LoginPage.class);
        loginPage.logInUnsuccessfully(userName, password);
        return loginPage;
    }
}
