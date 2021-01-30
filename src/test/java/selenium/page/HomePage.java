package selenium.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    protected static final By pageTitle = By.cssSelector("h2.sub-title");

    private final WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isHomePage() {
        String correctContentAfterLog = "Titles catalog";
        return correctContentAfterLog.equalsIgnoreCase(driver.findElement(pageTitle).getText());
    }
}
