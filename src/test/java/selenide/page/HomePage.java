package selenide.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import selenide.model.Book;

import java.util.Optional;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class HomePage {

    private final By pageTitle = By.cssSelector("h2.sub-title");
    private final By addNewButton = By.cssSelector("button[name=\"add-title-button\"]");
    private final By bookTitle = By.cssSelector("div.titles-list__item__title.list__item__col.list__item__col--primary");
    private final By removeTitleButton = By.cssSelector("button.remove-btn");
    private final By editButton = By.cssSelector("button.edit-btn");


    public boolean isHomePage() {
        String correctContentAfterLog = "Titles catalog";

        return correctContentAfterLog.equalsIgnoreCase($(pageTitle).getText());
    }

    public AddBookForm clickAddNewButton() {
        $(addNewButton).click();
        return new AddBookForm();
    }

    public EditBookForm clickEditButton(Book book) {
        Selenide.sleep(1000);
        findBookRow(book.getTitle(), book.getAuthor(), book.getYear()).ifPresent(
                row -> row.find(editButton).click()
        );
        return new EditBookForm();
    }

    public boolean isBookAdded(Book book) {
        Selenide.sleep(1000);

        return findBookRow(book.getTitle(), book.getAuthor(), book.getYear()).isPresent();
    }

    public void clickRemoveButton(Book book) {
        Selenide.sleep(1000);
        findBookRow(book.getTitle(), book.getAuthor(), book.getYear()).ifPresent(
                row -> row.find(removeTitleButton).click()
        );
    }

    public boolean isBookDeleted(String title, String author, String year) {
        Selenide.sleep(1000);
        return findBookRow(title, author, year).isEmpty();
    }

    private Optional<SelenideElement> findBookRow (String title, String author, String year) {
        ElementsCollection bookTitleCollection = $$(bookTitle).filter(Condition.text(title));
        if (bookTitleCollection.isEmpty()) {
            return Optional.empty();
        }

        for (SelenideElement bookTitleElement : bookTitleCollection) {
            SelenideElement bookEntry = bookTitleElement.parent();
            SelenideElement bookAuthorElement = bookEntry.find(withText(author));
            if (!bookAuthorElement.exists()) {
                continue;
            }
            if (bookEntry.find(withText(year)).exists()){
                return Optional.of(bookEntry.parent());
            }
        }
        return Optional.empty();
    }

}
