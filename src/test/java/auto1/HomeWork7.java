package auto1;

import auto1.page_objects.AuthPage;
import auto1.page_objects.MainPage;
import auto1.page_objects.PrivateCabinetPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bouncycastle.jcajce.provider.drbg.DRBG;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.LoggerFactory;

import java.text.ParseException;

public class HomeWork7 {
    static final Logger LOGGER = LogManager.getLogger(HomeWork7.class);

    @BeforeAll
    static void before() {
        WebDriverManager.chromedriver().setup();
    }
    @Test
    void test1() throws InterruptedException, ParseException {
        ChromeOptions options = new ChromeOptions();
        WebDriver driver = new ChromeDriver(options);

        driver.manage().window().maximize();

        try {
            driver.get("https://otus.ru");
            AuthPage authPage = new AuthPage(driver);
            MainPage mainPage = authPage.auth("test.olga123456@gmail.com","passpass1");
            PrivateCabinetPage privateCabinetPage = mainPage.goToPrivateCabinet();
            privateCabinetPage.clicToAboutMe();
        } finally {
            driver.quit();
        }
    }
}
