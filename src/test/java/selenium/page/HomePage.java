package selenium.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class HomePage {

    @FindBy(how= How.CSS, using = "h2.sub-title" )
    private SelenideElement pageTitle;

    public boolean isHomePage() {

        String correctContentAfterLog = "Titles catalog";

        return pageTitle != null && correctContentAfterLog.equalsIgnoreCase(pageTitle.getText());
    }
}
