package hello;

import no.marcus.mrfridge.UserCredentials;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class StartPage {

    private final WebDriver driver;

    @FindBy(id = "usernamer")
    private WebElement usernameRegField;
    @FindBy(id = "passwordr")
    private WebElement passwordRegField;
    @FindBy(id = "submitRegister")
    private WebElement registerButton;

    @FindBy(id = "username")
    private WebElement usernameLoginField;
    @FindBy(id = "password")
    private WebElement passwordLoginField;
    @FindBy(id = "submitlogin")
    private WebElement loginButton;

    public StartPage(WebDriver driver) {
        this.driver = driver;
    }

    public static StartPage open(WebDriver driver, int port) {
        driver.get("http://localhost:" + port + "/start");
        return PageFactory.initElements(driver, StartPage.class);
    }

    public StartPage registerUser(UserCredentials credentials) {
        usernameRegField.sendKeys(credentials.getUsername());
        passwordRegField.sendKeys(credentials.getPassword());

        driver.findElement(By.id("submitRegister")).click();
        return this;
    }


    public LoginResultPage login(UserCredentials credentials) {
        usernameLoginField.sendKeys(credentials.getUsername());
        passwordLoginField.sendKeys(credentials.getPassword());

        driver.findElement(By.id("submitLogin")).click();
        return PageFactory.initElements(driver, LoginResultPage.class);
    }

}
