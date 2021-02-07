package selenide.page;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static com.codeborne.selenide.Selenide.$;

public class AddBookForm {

    private final By addTitleHeader = By.cssSelector("form h2.sub-title");
    private final By titleInput = By.cssSelector("input[name=\"title\"]");
    private final By authorInput = By.cssSelector("input[name=\"author\"]");
    private final By yearInput = By.cssSelector("input[name=\"year\"]");
    private final By addTitleButton = By.cssSelector("button[name=\"submit-button\"]");
    private final By alertMessage = By.cssSelector("p.alert__content");

    public AddBookForm() {
        $(addTitleHeader).shouldBe(Condition.visible, Duration.of(10, ChronoUnit.SECONDS));
    }

    public HomePage addNewTitleSuccessfully(String title, String author, String year) {
        setTitle(title);
        setAuthor(author);
        setYear(year);
        clickAddTitleButton();
        $(addTitleHeader).shouldNotBe(Condition.visible, Duration.of(10, ChronoUnit.SECONDS));
        return new HomePage();
    }

    public AddBookForm addNewTitleUnsuccessfully(String title, String author, String year) {
        setTitle(title);
        setAuthor(author);
        setYear(year);
        clickAddTitleButton();
        return this;
    }

    private void setTitle(String title) {
        $(titleInput).setValue(title);
    }

    private void setAuthor(String author) {
        $(authorInput).setValue(author);
    }

    private void setYear(String year) {
        $(yearInput).setValue(year);
    }

    private void clickAddTitleButton() {
        $(addTitleButton).click();
    }

    public String getAlertMessage() {
        return $(alertMessage).getText();
    }
}
