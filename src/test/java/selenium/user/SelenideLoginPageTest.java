package selenium.user;

import com.codeborne.selenide.Configuration;
import org.junit.BeforeClass;
import org.junit.Test;
import selenium.selenide.HomePage;
import selenium.selenide.LoginPage;

import static org.assertj.core.api.Assertions.assertThat;

public class SelenideLoginPageTest {

    @BeforeClass
    public static void setUp() {
        Configuration.baseUrl = "https://ta-ebookrental-fe.herokuapp.com/";
    }

    @Test
    public void logWithValidData() {
        String userName = "karo1";
        String password = "karo111";

        HomePage homePage = new LoginPage().open().logInSuccessfully(userName, password);
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
        LoginPage loginPage = new LoginPage();
        return loginPage.open().logInUnsuccessfully(userName, password);
    }
}
