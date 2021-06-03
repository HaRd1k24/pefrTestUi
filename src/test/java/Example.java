
import org.apache.logging.log4j.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Example {
    private static final String url = "https://mail.ru/";
    private static final String Login = "lev-trapeznikov";
    private static final String Password = "Le12345678";
    static WebDriver driver;
    static WebDriverWait wait;
    private static final Logger log = LogManager.getRootLogger();

    @BeforeAll
    static void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        System.setProperty("webdriver.chrome.driver", "bin/chromedriver.exe");

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, 10,200);
        driver.manage().window().maximize();
    }

    @Test
    @DisplayName("Авторизация")
    void test() {
        log.debug("Открываем сайт");
        driver.get(url);
        Assertions.assertEquals(url, "https://mail.ru/");
        log.debug("Открыли сайт");

        log.info("Воддим логин ");
        WebElement login = driver.findElement(By.xpath("//input[@name='login']"));
        login.sendKeys(Login);

        log.info("Кликаем и переходим на ввод пароля");
        WebElement sumbit = driver.findElement(By.xpath("//button[@data-testid='enter-password']"));
        sumbit.click();

        log.info("Вводим пароль");
        WebElement password = driver.findElement(By.xpath("//input[@type='password']"));
        password.sendKeys(Password);

        log.info("Заходим в майл");
        WebElement toOpen = driver.findElement(By.xpath("//button[@data-testid='login-to-mail']"));
        toOpen.click();
        log.info("Зашли и перекидывает на входящие сообщения");



        log.info("Проверяем что находимся на входящих сообщениях");
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-parent=\"true\"]")));

        Assertions.assertNotNull(element.getText());



        WebElement element1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-uidl-id='16226367891678813193']")));


        Actions oAction = new Actions(driver);
        oAction.contextClick(element1).sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN).perform();
        driver.navigate().back();



    }


}

