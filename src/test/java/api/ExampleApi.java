package api;

import io.restassured.http.ContentType;
import org.example.ConfigProperties;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import page.MainPage;

import java.util.Set;

import static io.restassured.RestAssured.given;

public class ExampleApi {
    static WebDriver driver;
    static MainPage mainPage;
    @BeforeEach
    void setUp(){
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        System.setProperty("webdriver.chrome.driver", "bin/chromedriver.exe");

        driver.manage().window().maximize();
        driver = new ChromeDriver(options);

        mainPage = new MainPage(driver);


    }

    private Cookie getCookie(){
        driver.get(ConfigProperties.getProperty("db.url"));
        mainPage.fieldLogin(ConfigProperties.getProperty("db.login"));
        mainPage.acceptLogin();
        mainPage.fieldPassword(ConfigProperties.getProperty("db.password"));
        mainPage.acceptPassword();

        Set<Cookie> cookies = driver.manage().getCookies();
        System.out.println("-----------------------------------------");
        cookies.forEach(System.out::println);

        Cookie acttoken = driver.manage().getCookieNamed("act");
        System.out.println("act " + acttoken);
        return acttoken;
    }

    @Test
    void auth(){
        String cookie = getCookie().toString();
        given().accept(ContentType.JSON).when().cookie(cookie).post("https://e.mail.ru/inbox/?back=1").then().log().all();

    }

    @Test
    void auth1(){
        String cookie = getCookie().toString();
        given().accept(ContentType.JSON).when().cookie(cookie).get("https://e.mail.ru/inbox/").then().log().all();
    }
}
