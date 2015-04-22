package hello;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.net.URL;

import no.marcus.mrfridge.Application;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({"server.port=5000"})
public class EndToEndTest {

    @Value("${local.server.port}")
    private int port;

    WebDriver driver = new FirefoxDriver();
    private URL base;
    private RestTemplate template;

    @Before
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:" + port + "/");
        template = new TestRestTemplate();

    }

    @After
    public void after() {
        driver.close();
    }

    @Test
    public void test(){}


    @Test
    public void webdriver() throws InterruptedException {
        System.getProperties().setProperty("webdriver.chrome.driver", "/usr/bin/google-chrome");

        driver.get("http://localhost:" + port + "/start");
        //WebElement element = driver.findElement(By.id("main-part"));
        WebElement element = driver.findElement(By.className("jumbotron"));

        assertTrue(element.getText().contains("This is the start page"));
    }




}
