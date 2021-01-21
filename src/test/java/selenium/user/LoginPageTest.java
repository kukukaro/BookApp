package selenium.user;

import com.codeborne.selenide.Selenide;
import org.junit.Test;
import selenium.page.LoginPage;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginPageTest {

    @Test
    public void logWithValidData() {

        String userName = "karo1";
        String password = "karo111";

        Selenide.open("https://ta-ebookrental-fe.herokuapp.com/login");

        LoginPage loginPage = Selenide.page(LoginPage.class);
        loginPage.setUserName(userName);
        loginPage.setPassword(password);
        loginPage.clickLoginButton();


        loginPage.hashCode();



//        WebElement titlesContent = driver.findElement(By.cssSelector("h2.sub-title"));
//        String contentAfterLog = titlesContent.getText();
//        System.out.println(contentAfterLog);
//        String correctContentAfterLog = "Titles catalog";
//
//        boolean isPassed = contentAfterLog.equalsIgnoreCase(correctContentAfterLog);
//        assertThat(isPassed).isTrue();

    }

    @Test
    public void logWithNotMatchingPassword() {

        String userName = "karo1";
        String password = "karo11";

        Selenide.open("https://ta-ebookrental-fe.herokuapp.com/login");

        LoginPage loginPage = Selenide.page(LoginPage.class);
        loginPage.setUserName(userName);
        loginPage.setPassword(password);
        loginPage.clickLoginButton();


        String loginFailedMessage = "Login failed";

        boolean isPassed = loginFailedMessage.equalsIgnoreCase(loginPage.getAlertMessage());
        assertThat(isPassed).isTrue();
    }
}
