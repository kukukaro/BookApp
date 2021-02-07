package selenide.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class HomePage {

    private final By pageTitle = By.cssSelector("h2.sub-title");
    private final By addNewButton = By.cssSelector("button[name=\"add-title-button\"]");
    private final By bookTitle = By.cssSelector("div.titles-list__item__title.list__item__col.list__item__col--primary");

    public boolean isHomePage() {
        String correctContentAfterLog = "Titles catalog";

        return correctContentAfterLog.equalsIgnoreCase($(pageTitle).getText());
    }

    public AddBookForm clickAddNewButton() {
        $(addNewButton).click();
        return new AddBookForm();
    }

    public boolean isBookAdded(String title) {
        Selenide.sleep(1000);
        return $$(bookTitle).find(Condition.text(title)).exists();
    }
}
