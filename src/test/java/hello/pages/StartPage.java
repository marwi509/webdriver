package hello.pages;

import no.marcus.mrfridge.UserCredentials;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class StartPage {

    private final WebDriver driver;
    private final int port;

    private StartPage(WebDriver driver, int port) {
        this.driver = driver;
        this.port = port;
    }

    public static StartPage open(WebDriver driver, int port) {
        driver.get("http://localhost:" + port + "/start");
        return new StartPage(driver, port);
    }

    public StartPage registerUser(UserCredentials credentials) {
        WebElement username = driver.findElement(By.id("usernamer"));
        username.sendKeys(credentials.getUsername());

        WebElement password = driver.findElement(By.id("passwordr"));
        password.sendKeys(credentials.getPassword());

        WebElement registerButton = driver.findElement(By.id("submitRegister"));
        registerButton.click();
        driver.get("http://localhost:" + port + "/start");

        return this;
    }

    public LoginResultPage login(UserCredentials credentials) {


        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys(credentials.getUsername());

        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys(credentials.getPassword());

        WebElement registerButton = driver.findElement(By.id("submitLogin"));
        registerButton.click();

        return PageFactory.initElements(driver, LoginResultPage.class);
    }


}
