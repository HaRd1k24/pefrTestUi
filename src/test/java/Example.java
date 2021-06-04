import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.ConfigProperties;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import page.AuthPage;
import page.InsideLetterPage;
import page.MainPage;


public class Example {

    static WebDriver driver;
    private static final Logger log = LogManager.getRootLogger();
    static MainPage mainPage;
    static AuthPage authPage;
    static InsideLetterPage insideLetterPage;

    @BeforeAll
    static void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        System.setProperty("webdriver.chrome.driver", "bin/chromedriver.exe");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        mainPage = new MainPage(driver);
        authPage = new AuthPage(driver);
        insideLetterPage = new InsideLetterPage(driver);

        driver.get(ConfigProperties.getProperty("db.url"));
        log.debug("Проверяем что находимся на этом сайте");
        Assertions.assertEquals(ConfigProperties.getProperty("db.url"), "https://mail.ru/");
        log.debug("Открыли сайт");
    }

    @Test()
    @DisplayName("Авторизация")
    void test() {
        mainPage.fieldLogin(ConfigProperties.getProperty("db.login"));
        mainPage.acceptLogin();
        mainPage.fieldPassword(ConfigProperties.getProperty("db.password"));
        mainPage.acceptPassword();

    }

    @Test
    @DisplayName("Удостоверяемся что авторизировались")
    void test2() {
        authPage.checkResult();

    }

    @Test
    @DisplayName("Авторизация,подтверждение что находимся на входящих сообщениях,удаление сообщения")
    void test3() {
        authPage.letterClick();
        insideLetterPage.search();
        insideLetterPage.deleteLetter();

    }

    @AfterAll
    static void exitUp() {
        driver.quit();
    }
}

