package hello;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginResultPage {

    @FindBy(className = "jumbotron")
    private WebElement resultText;

    public String getResultText() {
        return resultText.getText();
    }
}
