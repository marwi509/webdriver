package hello;

import hello.pages.*;
import no.marcus.mrfridge.Application;
import no.marcus.mrfridge.UserCredentials;
import org.junit.After;
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

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:5000")
public class DemoTest {

    @Value("${local.server.port}")
    private int port;

    private final UserCredentials correctCredentials = new UserCredentials("username", "pass");

    private WebDriver driver;

    @Before
    public void setup() {
        driver = new FirefoxDriver();
    }

    @After
    public void tearDown() {
        driver.close();
    }
/*
    @Test
    public void test() {
        String url = "http://localhost:" + port + "/start";
        driver = new FirefoxDriver();
        driver.get(url);

        registerUser(driver);
        login(driver, correctCredentials);

        assertEquals("Welcome, " + correctCredentials.getUsername() + "!", driver.findElement(By.className("jumbotron")).getText());

    }

    @Test
    public void testIncorrect() {
        String url = "http://localhost:" + port + "/start";
        driver = new FirefoxDriver();
        driver.get(url);

        registerUser(driver);
        login(driver, new UserCredentials(correctCredentials.getUsername(), "password"));

        assertEquals("Incorrect credentials!", driver.findElement(By.className("jumbotron")).getText());

    }
    */

    @Test
    public void testLoginSuccessfulCredentials() {
        StartPage startPage = StartPage.open(driver, port)
                .registerUser(correctCredentials);

        LoginResultPage resultPage = startPage.login(correctCredentials);

        assertEquals("Welcome, " + correctCredentials.getUsername() + "!", resultPage.getResultText());
    }

    @Test
    public void testLoginIncorrectCredentials() {
        StartPage startPage = StartPage.open(driver, port)
                .registerUser(correctCredentials);

        LoginResultPage resultPage = startPage.login(new UserCredentials(correctCredentials.getUsername(), "incorrect"));

        assertEquals("Incorrect credentials!", resultPage.getResultText());
    }

}
