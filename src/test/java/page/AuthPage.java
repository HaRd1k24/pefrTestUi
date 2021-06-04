package page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class AuthPage {
    private static final Logger log = LogManager.getRootLogger();

    public AuthPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
    }

    @FindBy(xpath = "//div[@data-parent='true']")
    private WebElement check;

    @FindBy(xpath = "//a[@data-uidl-id='16226367891678813193']")
    private WebElement clickLetter;

    public void checkResult() {
        log.info("Проверяем что находимся на входящих сообщениях");
        Assertions.assertNotNull(check.getText());
    }

    public void letterClick(){
        log.info("Кликаем на первое письмо");
        clickLetter.click();
        log.info("Провалеваемся в него");
    }
}
