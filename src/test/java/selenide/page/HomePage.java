package selenide.page;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class HomePage {

    private By pageTitle = By.cssSelector("h2.sub-title");

    public boolean isHomePage() {
        String correctContentAfterLog = "Titles catalog";

        return correctContentAfterLog.equalsIgnoreCase($(pageTitle).getText());
    }
}
