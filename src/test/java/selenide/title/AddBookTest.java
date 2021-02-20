package selenide.title;

import com.codeborne.selenide.Configuration;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import selenide.page.AddBookForm;
import selenide.page.HomePage;
import selenide.page.LoginPage;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class AddBookTest {
    private final static String username = "karo1";
    private final static String password = "karo111";
    private HomePage homePage;

    @BeforeClass
    public static void setUp() {
        Configuration.baseUrl = "https://ta-ebookrental-fe.herokuapp.com/";
    }

    @Before
    public void logIn() {
        homePage = new LoginPage().open().logInSuccessfully(username, password);
        assertThat(homePage.isHomePage()).isTrue();
    }

    @Test
    public void addBookWithValidData() {
        String title = RandomStringUtils.randomAlphabetic(6);
        String author = RandomStringUtils.randomAlphabetic(6);
        Random random = new Random();
        String year = Integer.toString(random.nextInt(2022));

        AddBookForm newBookForm = homePage.clickAddNewButton();
        HomePage homePage = newBookForm.addNewTitleSuccessfully(title, author, year);
        assertThat(homePage.isBookAdded(title, author, year)).isTrue();

    }

    @Test
    public void shouldNotAddBookWithEmptyTitle() {
        String title = "";
        String author = RandomStringUtils.randomAlphabetic(6);
        Random random = new Random();
        String year = Integer.toString(random.nextInt(2022));

        AddBookForm newBookForm = homePage.clickAddNewButton();
        newBookForm.addNewTitleUnsuccessfully(title, author, year);
        assertThat(newBookForm.getAlertMessage()).isEqualToIgnoringCase("\"title\" field shouldn't be empty...");
    }

    @Test
    public void shouldNotAddBookWithEmptyAuthor() {
        String title = RandomStringUtils.randomAlphabetic(6);
        String author = "";
        Random random = new Random();
        String year = Integer.toString(random.nextInt(2022));

        AddBookForm newBookForm = homePage.clickAddNewButton();
        newBookForm.addNewTitleUnsuccessfully(title, author, year);
        assertThat(newBookForm.getAlertMessage()).isEqualToIgnoringCase("\"author\" field shouldn't be empty...");
    }

    @Test
    public void shouldNotAddBookWithEmptyYear() {
        String title = RandomStringUtils.randomAlphabetic(6);
        String author = RandomStringUtils.randomAlphabetic(6);
        String year = "";

        AddBookForm newBookForm = homePage.clickAddNewButton();
        newBookForm.addNewTitleUnsuccessfully(title, author, year);
        assertThat(newBookForm.getAlertMessage()).isEqualToIgnoringCase("\"year\" field shouldn't be empty...");
    }

}
