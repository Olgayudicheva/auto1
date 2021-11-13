package auto1;

import io.netty.util.internal.SystemPropertyUtil;
import org.asynchttpclient.ws.WebSocketUtils;
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

public class Main {
    @BeforeAll
    static void before() {
        System.setProperty("webdriver.chrome.driver", "chromedriver_mac");
    }

    @Test
    void test1() {
        //Настройки хром драйвера
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--start-maximised");

        WebDriver driver = new ChromeDriver(options);
        try {
            driver.get("https://duckduckgo.com");

            WebElement searchInput = driver.findElement(new By.ByXPath("//*[@id=\"search_form_input_homepage\"]"));
            WebElement buttonSearch = driver.findElement(new By.ByXPath("//*[@id=\"search_button_homepage\"]"));
            searchInput.sendKeys("ОТУС");
            buttonSearch.click();

            System.out.println("---Список найденого по слову \"ОТУС\"---");

            List<WebElement> listSearch = driver.findElements(new By.ByClassName("result__body"));
            for (int i = 0; i < listSearch.size(); i++) {
                System.out.println((i + 1) + ")");
                System.out.println(listSearch.get(i).getText());
            }

            System.out.println("---------");

            System.out.println("---Проверка первого элемента в списке---");
            WebElement result1 = listSearch.get(0);

            System.out.println(result1.getText());
            Assertions.assertTrue(result1.getText().contains("Онлайн‑курсы для профессионалов, дистанционное обучение"));
        } finally {
            driver.quit();
        }

    }

    @Test
     void test2() throws InterruptedException {
        //Настройки хром драйвера
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--kiosk");

        WebDriver driver = new ChromeDriver(options);
        try {
            //driver.manage().window().maximize();

            driver.get("https://demo.w3layouts.com/demos_new/template_demo/03-10-2020/photoflash-liberty-demo_Free/685659620/web/index.html?_ga=2.181802926.889871791.1632394818-2083132868.1632394818");


            //Находим все картики
            List<WebElement> imageList = driver.findElements(new By.ByClassName("image-block"));
            WebElement element = imageList.get(0);

            //Скролл до нужного элемента
            Actions actions = new Actions(driver);
            actions.moveToElement(element).click().perform();
            String miniImage = element.findElement(new By.ByClassName("img-fluid")).getAttribute("src");
            Thread.sleep(2000);
            WebElement fullResImage = driver.findElement(new By.ById("fullResImage"));
            String fullImage = fullResImage.getAttribute("src");
            Assertions.assertEquals(miniImage, fullImage);
            Thread.sleep(1000);
        } finally {
            driver.quit();
        }
    }

    @Test
     void test3() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        WebDriver driver = new ChromeDriver(options);

        driver.manage().window().maximize();

        try {
            driver.get("https://otus.ru");
            driver.findElement(new By.ByClassName("header2__auth")).click();
            Thread.sleep(1000);
            WebElement login = driver.findElement(new By.ByXPath("/html/body/div[2]/div/div/div/div[3]/div[2]/div[2]/form/div[2]/input"));
            WebElement password = driver.findElement(new By.ByXPath("/html/body/div[2]/div/div/div/div[3]/div[2]/div[2]/form/div[3]/input"));
            WebElement loginButton = driver.findElement(new By.ByXPath("/html/body/div[2]/div/div/div/div[3]/div[2]/div[2]/form/div[4]/button"));
            login.sendKeys("test.olga123456@gmail.com");
            password.sendKeys("passpass1");
            loginButton.click();
            Thread.sleep(1000);
            System.out.println("COOKIE:");
            driver.manage().getCookies().forEach(new Consumer<Cookie>() {
                @Override
                public void accept(Cookie cookie) {
                    System.out.println(cookie);
                }
            });
            Thread.sleep(1000);
        } finally {
            driver.quit();
        }
    }
}
