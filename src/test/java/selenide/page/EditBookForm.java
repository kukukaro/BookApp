package selenide.page;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static com.codeborne.selenide.Selenide.$;

public class EditBookForm {
    private final By editTitleHeader = By.cssSelector("button.edit-btn");
    private final By titleInput = By.cssSelector("input[name=\"title\"]");
    private final By authorInput = By.cssSelector("input[name=\"author\"]");
    private final By yearInput = By.cssSelector("input[name=\"year\"]");
    private final By editTitleButton = By.cssSelector("button");
    private final By alertMessage = By.cssSelector("p.alert__content");

    public EditBookForm() {
        $(editTitleHeader).shouldBe(Condition.visible, Duration.of(10, ChronoUnit.SECONDS));
    }

    public HomePage editBookSuccessfully(String title, String author, String year) {
        setTitle(title);
        setAuthor(author);
        setYear(year);
        clickEditTitleButton();
        return new HomePage();
    }

    public EditBookForm editBookUnsuccessfully(String title, String author, String year){
        setTitle(title);
        setAuthor(author);
        setYear(year);
        clickEditTitleButton();
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

    private void clickEditTitleButton() {
        $(editTitleButton).click();
    }

    public String getAlertMessage() {
        return $(alertMessage).getText();
    }
}
