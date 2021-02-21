package selenide.title;

import com.codeborne.selenide.Configuration;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import selenide.model.Book;
import selenide.page.AddBookForm;
import selenide.page.HomePage;
import selenide.page.LoginPage;

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
        Book book = Book.createRandomBook();

        AddBookForm newBookForm = homePage.clickAddNewButton();
        HomePage homePage = newBookForm.addNewTitleSuccessfully(book);
        assertThat(homePage.isBookAdded(book)).isTrue();

    }

    @Test
    public void shouldNotAddBookWithEmptyTitle() {
        Book book = Book.createRandomBook();
        book.setTitle("");

        AddBookForm newBookForm = homePage.clickAddNewButton();
        newBookForm.addNewTitleUnsuccessfully(book);
        assertThat(newBookForm.getAlertMessage()).isEqualToIgnoringCase("\"title\" field shouldn't be empty...");
    }

    @Test
    public void shouldNotAddBookWithEmptyAuthor() {
        Book book = Book.createRandomBook();
        book.setAuthor("");

        AddBookForm newBookForm = homePage.clickAddNewButton();
        newBookForm.addNewTitleUnsuccessfully(book);
        assertThat(newBookForm.getAlertMessage()).isEqualToIgnoringCase("\"author\" field shouldn't be empty...");
    }

    @Test
    public void shouldNotAddBookWithEmptyYear() {
        Book book = Book.createRandomBook();
        book.setYear("");

        AddBookForm newBookForm = homePage.clickAddNewButton();
        newBookForm.addNewTitleUnsuccessfully(book);
        assertThat(newBookForm.getAlertMessage()).isEqualToIgnoringCase("\"year\" field shouldn't be empty...");
    }

}
