package page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class InsideLetterPage {
    private static final Logger log = LogManager.getRootLogger();

    public InsideLetterPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
    }

    @FindBy(xpath = "//span[@class='button2 button2_has-ico button2_has-ico-s " +
            "button2_status_block " +
            "button2_clean button2_hover-support']")
    private WebElement clickRemember;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement delete;


    public void search() {
        log.info("Жму кнопку отписаться от рассылки");
        clickRemember.click();
        log.info("Открывается под меню");
    }

    public void deleteLetter() {
        log.info("Нахожу кнопку отписаться и удалить");
        delete.click();
        log.info("Жму кнопку отписаться и удалить");
    }
}
