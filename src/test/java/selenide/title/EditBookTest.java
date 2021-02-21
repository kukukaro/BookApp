package selenide.title;

import com.codeborne.selenide.Configuration;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import selenide.model.Book;
import selenide.page.AddBookForm;
import selenide.page.EditBookForm;
import selenide.page.HomePage;
import selenide.page.LoginPage;

import static org.assertj.core.api.Assertions.assertThat;

public class EditBookTest {
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
    public void shouldEditBookSuccessfully() {
        Book book = Book.createRandomBook();
        AddBookForm newBookForm = homePage.clickAddNewButton();
        HomePage homePage = newBookForm.addNewTitleSuccessfully(book);
        assertThat(homePage.isBookAdded(book)).isTrue();

        EditBookForm editBookForm = homePage.clickEditButton(book);
    }

    @Test
    public void shouldNotUpdateBookWithEmptyTitle() {

    }

    @Test
    public void shouldNotUpdateBookWithEmptyAuthor() {

    }

    @Test
    public void shouldNotUpdateBookWithEmptyYear() {

    }

    @Test
    public void shouldNotUpdateBookWithInvalidYearData() {

    }
}