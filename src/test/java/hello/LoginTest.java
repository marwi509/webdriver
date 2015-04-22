package hello;


import hello.pages.LoginResultPage;
import hello.pages.StartPage;
import no.marcus.mrfridge.Application;
import no.marcus.mrfridge.UserCredentials;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:5000")
public class LoginTest {

    @Value("${local.server.port}")
    private int port;

    private final UserCredentials correctUserCredentials = new UserCredentials("Username", "Password");
    private final UserCredentials incorrectUserCredentials = new UserCredentials("InUsername", "InPassword");
    private WebDriver driver;

    @Before
    public void setup() {
        driver = new FirefoxDriver();
    }

    @After
    public void close() {
        driver.close();
    }

    @Test
    public void LoginWithCorrectCredentials_ShouldDisplayWelcomeMessage() {
        StartPage startPage = StartPage.open(driver, port);
        startPage.registerUser(correctUserCredentials);

        LoginResultPage resultPage = startPage.login(correctUserCredentials);

        Assert.assertEquals(resultPage.getMessage(), "Welcome, " + correctUserCredentials.getUsername() + "!");
    }

    @Test
    public void LoginWithIncorrectCredentials_ShouldDisplayErrorMessage() {
        StartPage startPage = StartPage.open(driver, port);
        startPage.registerUser(correctUserCredentials);

        LoginResultPage resultPage = startPage.login(incorrectUserCredentials);

        Assert.assertEquals(resultPage.getMessage(), "Incorrect credentials!");
    }

}
