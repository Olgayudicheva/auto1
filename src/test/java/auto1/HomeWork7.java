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

import java.util.ArrayList;

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

    @Test
    void test1() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        driver = WebDriverFactory.create(WebDriverFactory.WebDriverName.parseString(System.getProperty("browser")),options);

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
