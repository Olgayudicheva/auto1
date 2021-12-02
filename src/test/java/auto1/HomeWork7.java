package auto1;

import auto1.page_objects.AuthPage;
import auto1.page_objects.MainPage;
import auto1.page_objects.PrivateCabinetPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.util.ArrayList;
import java.util.List;

public class HomeWork7 {
    static final Logger LOGGER = LogManager.getLogger(HomeWork7.class);
    ArrayList<PrivateCabinetPage.Contact> contactsForTest = new ArrayList<>();

    WebDriver driver;

    @AfterEach
    void after() {
        driver.quit();
    }

    String fName = "Ольга";
    String lName = "Юдичева";
    String fNameLat = "Olga";
    String lNameLat = "YU";
    String blogName = "OlgaYu";
    String dob = "01.01.1990";

    AbstractDriverOptions options(WebDriverFactory.WebDriverName webDriverName) {
        switch (webDriverName) {
            case CHROME:
                return new ChromeOptions();
            case SAFARI:
                return new SafariOptions();
            case FIREFOX:
                return new FirefoxOptions();
        }
        return new ChromeOptions();
    }

    @Test
    void test1() {
        WebDriverFactory.WebDriverName webDriverName = WebDriverFactory.WebDriverName.parseString(System.getProperty("browser"));

        driver = WebDriverFactory.create(webDriverName,options(webDriverName));

        driver.manage().window().maximize();

        driver.get("https://otus.ru");
        AuthPage authPage = new AuthPage(driver);
        MainPage mainPage = authPage.auth(System.getProperty("login"), System.getProperty("password"));
        PrivateCabinetPage privateCabinetPage = mainPage.goToPrivateCabinet();
        privateCabinetPage.clickToAboutMe();
        privateCabinetPage.setFirstName(fName);
        privateCabinetPage.setFirstNameLat(fNameLat);
        privateCabinetPage.setSecondName(lName);
        privateCabinetPage.setSecondNameLat(lNameLat);
        privateCabinetPage.setNameBlog(blogName);
        privateCabinetPage.setDOB(dob);

        privateCabinetPage.deleteAllContact();
        PrivateCabinetPage.Contact c1 = privateCabinetPage.addRandomContact();
        contactsForTest.add(c1);
        PrivateCabinetPage.Contact c2 = privateCabinetPage.addRandomContact();
        contactsForTest.add(c2);

        privateCabinetPage.save();

        LOGGER.info("---Сброс куков---");
        driver.manage().deleteAllCookies();
        LOGGER.info("---Проверка данных---");
        driver.get("https://otus.ru");
        authPage = new AuthPage(driver);
        mainPage = authPage.auth(System.getProperty("login"), System.getProperty("password"));
        privateCabinetPage = mainPage.goToPrivateCabinet();
        privateCabinetPage.clickToAboutMe();
        privateCabinetPage.checkFirstName(fName);
        privateCabinetPage.checkFirstNameLat(fNameLat);
        privateCabinetPage.checkSecondName(lName);
        privateCabinetPage.checkSecondNameLat(lNameLat);
        privateCabinetPage.checkNameBlog(blogName);
        privateCabinetPage.checkDOB(dob);

        privateCabinetPage.checkContactList(contactsForTest);


    }

}
