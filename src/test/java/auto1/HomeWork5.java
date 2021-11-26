package auto1;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.netty.util.internal.SystemPropertyUtil;
import io.netty.util.internal.logging.Log4J2LoggerFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.asynchttpclient.ws.WebSocketUtils;
import org.junit.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.jupiter.api.Assertions;

import java.time.Duration;
import java.util.List;
import java.util.function.Consumer;

public class HomeWork5 {
    static final Logger LOGGER = LogManager.getLogger(HomeWork5.class);
    WebDriver driver;

    @BeforeAll
    static void before() {
        //System.setProperty("webdriver.chrome.driver", "chromedriver_mac");
        WebDriverManager.chromedriver().setup();
    }

    @AfterEach
    void after() {
        driver.quit();
    }


    @Test
    void testDuckduckgo() {
        //Настройки хром драйвера
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36");
        //options.addArguments("--start-maximised");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        driver.get("https://duckduckgo.com");

        WebElement searchInput = driver.findElement(new By.ByXPath("//*[@id=\"search_form_input_homepage\"]"));
        WebElement buttonSearch = driver.findElement(new By.ByXPath("//*[@id=\"search_button_homepage\"]"));
        searchInput.sendKeys("ОТУС");
        buttonSearch.click();

        LOGGER.info("---Список найденого по слову \"ОТУС\"---");

        List<WebElement> listSearch = driver.findElements(new By.ByClassName("result__body"));
        for (int i = 0; i < listSearch.size(); i++) {
            LOGGER.info((i + 1) + ")");
            LOGGER.info(listSearch.get(i).getText());
        }

        LOGGER.info("---------");

        LOGGER.info("---Проверка первого элемента в списке---");
        WebElement result1 = listSearch.get(0);

        LOGGER.info(result1.getText());
        Assertions.assertTrue(result1.getText().contains("Онлайн‑курсы для профессионалов, дистанционное обучение"));


    }

    @Test
    void testImage() {
        //Настройки хром драйвера
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--kiosk");

        driver = new ChromeDriver(options);
        //driver.manage().window().maximize();

        driver.get("https://demo.w3layouts.com/demos_new/template_demo/03-10-2020/photoflash-liberty-demo_Free/685659620/web/index.html?_ga=2.181802926.889871791.1632394818-2083132868.1632394818");


        //Находим все картики
        List<WebElement> imageList = driver.findElements(new By.ByClassName("image-block"));
        WebElement element = imageList.get(0);

        //Скролл до нужного элемента
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().perform();
        String miniImage = element.findElement(new By.ByClassName("img-fluid")).getAttribute("src");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(new By.ById("fullResImage")));
        WebElement fullResImage = driver.findElement(new By.ById("fullResImage"));
        String fullImage = fullResImage.getAttribute("src");
        Assertions.assertEquals(miniImage, fullImage);

    }

    @Test
    void testOtus() {
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);

        driver.manage().window().maximize();

        driver.get("https://otus.ru");
        driver.findElement(new By.ByClassName("header2__auth")).click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(new By.ByXPath("//input[@name=\"email\" and not(contains(@class,\"hide\")) and @type=\"text\"]")));
        WebElement login = driver.findElement(new By.ByXPath("//input[@name=\"email\" and not(contains(@class,\"hide\")) and @type=\"text\"]"));
        WebElement password = driver.findElement(new By.ByXPath("//input[@name=\"password\" and not(contains(@class,\"hide\"))]"));
        WebElement loginButton = driver.findElement(new By.ByXPath("//button[contains(text(), 'Войти') and not(contains(text(), 'аккаунт'))]"));
        login.sendKeys("test.olga123456@gmail.com");
        password.sendKeys("passpass1");
        loginButton.click();
        LOGGER.info("COOKIE:");
        driver.manage().getCookies().forEach(new Consumer<Cookie>() {
            @Override
            public void accept(Cookie cookie) {
                LOGGER.info(cookie.toString());
            }
        });

    }
}
