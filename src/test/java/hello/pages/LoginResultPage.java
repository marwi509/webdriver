package hello.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginResultPage {

    private final WebDriver driver;

    @FindBy(className = "jumbotron")
    private WebElement elem;

    public LoginResultPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getMessage() {
        return elem.getText();
    }

}
