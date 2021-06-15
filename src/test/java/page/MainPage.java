package page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class MainPage {

    private static final Logger log = LogManager.getRootLogger();


    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
    }

    @FindBy(xpath = "//input[@name='login']")
    private WebElement login;

    @FindBy(xpath = "//button[@data-testid='enter-password']")
    private WebElement clickLogin;

    @FindBy(xpath = "//input[@type='password']")
    private WebElement fieldPassword;

    @FindBy(xpath = "//button[@data-testid='login-to-mail']")
    private WebElement clickPassword;

    public void fieldLogin(String loginAccount) {
        log.info("Воддим логин");
        login.sendKeys(loginAccount);
    }

    public void acceptLogin() {
        clickLogin.click();
        log.info("Кликаем и переходим на ввод пароля");
    }

    public void fieldPassword(String pass) {
        log.info("Вводим пароль");
        fieldPassword.sendKeys(pass);
    }

    public void acceptPassword() {
        log.info("Заходим в майл");
        clickPassword.click();
        log.info("Зашли и перекидывает на входящие сообщения");

    }

}
