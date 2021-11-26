package auto1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Handler;

public class HomeWork6 {


    public HomeWork6() throws ParseException {
    }

    static final Logger LOGGER = LogManager.getLogger(HomeWork6.class);

    @BeforeAll
    static void before() {
        WebDriverManager.chromedriver().setup();
    }
    WebDriver driver;
    @AfterEach
    void after() {
        driver.quit();
    }
    class Contact {
        String type;
        String text;

        public Contact(String type, String text) {
            this.type = type;
            this.text = text;
        }
    }

    String randomString(int size, String sym) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            char s = sym.toCharArray()[ThreadLocalRandom.current().nextInt(0, sym.length() - 1)];

            stringBuilder.append(s);
        }
        return stringBuilder.toString();
    }

    void authOTUS(WebDriver driver, String loginString, String passwordString) throws InterruptedException {
        driver.get("https://otus.ru");
        driver.findElement(new By.ByClassName("header2__auth")).click();
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(new By.ByXPath("//input[@name=\"email\" and not(contains(@class,\"hide\")) and @type=\"text\"]")));
        WebElement login = driver.findElement(new By.ByXPath("//input[@name=\"email\" and not(contains(@class,\"hide\")) and @type=\"text\"]"));
        WebElement password = driver.findElement(new By.ByXPath("//input[@name=\"password\" and not(contains(@class,\"hide\"))]"));
        WebElement loginButton = driver.findElement(new By.ByXPath("//button[contains(text(), 'Войти') and not(contains(text(), 'аккаунт'))]"));
        login.sendKeys(loginString);
        password.sendKeys(passwordString);
        loginButton.click();
    }

    Date randomDate(Date from, Date to) {
        long fromMs = from.getTime();
        long toMs = to.getTime();
        long randomDateMs = ThreadLocalRandom.current().nextLong(fromMs, toMs);
        return new Date(randomDateMs);
    }

    String startDate = "01.01.1960";
    String endDate = "01.01.2010";

    WebElement getHeaderMenuElement(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(new By.ByXPath("//div[@class=\"header2-menu\"]")));
        return driver.findElement(new By.ByXPath("//div[@class=\"header2-menu\"]"));
    }

    WebElement getMyOfficeElement(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(new By.ByXPath("//a[@title=\"Личный кабинет\"]")));
        return driver.findElement(new By.ByXPath("//a[@title=\"Личный кабинет\"]"));
    }

    WebElement getAboutMeElement(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(new By.ByXPath("//div[contains(@class,'nav ') and not(contains(@style,'display: none'))]//a[@title=\"О себе\"]")));
        return driver.findElement(new By.ByXPath("//div[contains(@class,'nav ') and not(contains(@style,'display: none'))]//a[@title=\"О себе\"]"));
    }

    WebElement getNameElement(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(new By.ByXPath("//*[@id=\"id_fname\"]")));
        return driver.findElement(new By.ByXPath("//*[@id=\"id_fname\"]"));
    }

    WebElement getLatNameElement(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(new By.ByXPath("//*[@id=\"id_fname_latin\"]")));
        return driver.findElement(new By.ByXPath("//*[@id=\"id_fname_latin\"]"));

    }

    WebElement getLastNameElement(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(new By.ByXPath("//*[@id=\"id_lname\"]")));
        return driver.findElement(new By.ByXPath("//*[@id=\"id_lname\"]"));
    }

    WebElement getLatLastNameElement(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(new By.ByXPath("//*[@id=\"id_lname_latin\"]")));
        return driver.findElement(new By.ByXPath("//*[@id=\"id_lname_latin\"]"));
    }

    WebElement getBlogNameElement(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(new By.ByXPath("//*[@id=\"id_blog_name\"]")));
        return driver.findElement(new By.ByXPath("//*[@id=\"id_blog_name\"]"));
    }

    WebElement getBirthDateElement(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(new By.ByXPath("//*[@name=\"date_of_birth\"]")));
        return driver.findElement(new By.ByXPath("//input [@name=\"date_of_birth\"]"));
    }

    String latSym = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
    String cSym = "йцукенгшщзхъфывапролджэячсмитьбюЙЦУКЕНГШЩЗХЪФЫВАПРОЛДЖЭЯЧСМИТЬБЮ";
    String nameString = randomString(12, cSym);
    String latNameString = randomString(12, latSym);
    String lastNameString = randomString(12, cSym);
    String latLastNameString = randomString(12, latSym);
    String blogNameString = randomString(12, cSym + latSym);
    SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("dd.MM.yyyy");
    Date randomDateBirth = randomDate(dateTimeFormatter.parse(startDate), dateTimeFormatter.parse(endDate));
    ArrayList<Contact> contactsForTest = new ArrayList<>();

    @Test
    void test1() throws InterruptedException, ParseException {
        fillData();
        checkData();
    }

    void fillData() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--headless");
        options.addArguments("--start-maximised");

        driver = new ChromeDriver(options);

            driver.manage().window().maximize();
            authOTUS(driver, System.getProperty("login"), System.getProperty("password"));

            WebElement headerMenu = getHeaderMenuElement(driver);
            new Actions(driver).moveToElement(headerMenu).perform();
            WebElement myOffice = getMyOfficeElement(driver);
            myOffice.click();
            WebElement aboutMe = getAboutMeElement(driver);
            aboutMe.click();

            WebElement nameElement = getNameElement(driver);
            WebElement latNameElement = getLatNameElement(driver);
            WebElement lastNameElement = getLastNameElement(driver);
            WebElement latLastNameElement = getLatLastNameElement(driver);
            WebElement blogName = getBlogNameElement(driver);
            WebElement dataBirth = getBirthDateElement(driver);

            nameElement.clear();
            nameElement.sendKeys(nameString);
            latNameElement.clear();
            latNameElement.sendKeys(latNameString);
            lastNameElement.clear();
            lastNameElement.sendKeys(lastNameString);
            latLastNameElement.clear();
            latLastNameElement.sendKeys(latLastNameString);
            blogName.clear();
            blogName.sendKeys(blogNameString);


            dataBirth.clear();
            dataBirth.sendKeys(dateTimeFormatter.format(randomDateBirth));

            deleteAllContact(driver);
            addContact(driver);
            addContact(driver);
            WebDriverWait wait = new WebDriverWait(driver,10);
            wait.until(ExpectedConditions.visibilityOfElementLocated(new By.ByXPath("//button[@title=\"Сохранить и заполнить позже\"]")));
            driver.findElement(new By.ByXPath("//button[@title=\"Сохранить и заполнить позже\"]")).click();

    }

    void checkData() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--headless");
        options.addArguments("--start-maximised");

        if(driver!=null) {
            driver.quit();
        }
        driver = new ChromeDriver(options);

            driver.manage().window().maximize();
            authOTUS(driver, System.getProperty("login"), System.getProperty("password"));
            WebElement headerMenu = getHeaderMenuElement(driver);
            new Actions(driver).moveToElement(headerMenu).perform();
            WebElement myOffice = getMyOfficeElement(driver);
            myOffice.click();
            WebElement aboutMe = getAboutMeElement(driver);
            aboutMe.click();

            WebElement nameElement = getNameElement(driver);
            WebElement latNameElement = getLatNameElement(driver);
            WebElement lastNameElement = getLastNameElement(driver);
            WebElement latLastNameElement = getLatLastNameElement(driver);
            WebElement blogName = getBlogNameElement(driver);
            WebElement dataBirth = getBirthDateElement(driver);

            Assertions.assertEquals(nameElement.getAttribute("value"), nameString);
            Assertions.assertEquals(latNameElement.getAttribute("value"), latNameString);
            Assertions.assertEquals(lastNameElement.getAttribute("value"), lastNameString);
            Assertions.assertEquals(latLastNameElement.getAttribute("value"), latLastNameString);
            Assertions.assertEquals(blogName.getAttribute("value"), blogNameString);
            Assertions.assertEquals(dataBirth.getAttribute("value"), dateTimeFormatter.format(randomDateBirth));

            List<WebElement> contactsList = getContactsListElements(driver);
            Assertions.assertEquals(contactsList.size(), contactsForTest.size());
            for(int i=0;i<contactsForTest.size();i++) {
                WebElement contactElement = contactsList.get(i);
                WebElement lastContactInput = contactElement.findElement(new By.ByXPath(".//input[starts-with(@id,'id_contact') and contains(@type,'text')]"));
                String selectedType = contactElement.findElement(new By.ByXPath("./div[1]/div[1]/div[1]/div[1]/label/div")).getText();
                String value = lastContactInput.getAttribute("value");
                Contact contact = null;
                for (Contact c : contactsForTest) {
                    if (c.text.equals(value)) {
                        contact = c;
                    }
                }
                Assertions.assertNotNull(contact);
                Assertions.assertEquals(contact.text, value);
                Assertions.assertEquals(contact.type, selectedType);
            }
    }

    List<WebElement> getContactsListElements(WebDriver driver) {
        //WebDriverWait wait = new WebDriverWait(driver,10);
        //wait.until(ExpectedConditions.visibilityOfElementLocated(new By.ByXPath("//div[@data-num and not(contains(@class,'hide'))]")));
        return driver.findElements(new By.ByXPath("//div[@data-num and not(contains(@class,'hide'))]"));
    }

    void deleteAllContact(WebDriver driver) throws InterruptedException {
        List<WebElement> contacts = getContactsListElements(driver);
        LOGGER.info("---Удаление всех контактов---");
        while (!contacts.isEmpty()) {
            WebElement last = contacts.get(contacts.size()-1);
            WebElement deleteButton = last.findElement(new By.ByXPath("./div[3]/div[2]/button"));
            deleteButton.click();
            contacts = getContactsListElements(driver);
        }

    }

    void addContact(WebDriver driver) {
        LOGGER.info("---Добавление контакта---");
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(new By.ByXPath("//button[text()=\"Добавить\"]")));
        WebElement addContact = driver.findElement(new By.ByXPath("//button[text()=\"Добавить\"]"));

        addContact.click();
        List<WebElement> contacts = getContactsListElements(driver);
        WebElement lastElement = contacts.get(contacts.size() - 1);
        WebElement lastContactSelected = lastElement.findElement(new By.ByClassName("js-custom-select-presentation"));
        WebElement lastContactInput = lastElement.findElement(new By.ByXPath(".//input[starts-with(@id,'id_contact') and contains(@type,'text')]"));


        new Actions(driver).moveToElement(lastContactInput).perform();

        String unText = UUID.randomUUID().toString();
        lastContactInput.sendKeys(unText);
        lastContactSelected.click();
        WebElement contatsTypeMenu = lastElement.findElement(new By.ByXPath(".//div[@class=\"lk-cv-block__select-scroll lk-cv-block__select-scroll_service js-custom-select-options\"]"));
        List<WebElement> contactTypes = contatsTypeMenu.findElements(new By.ByXPath(".//*"));
        int randomIndex = ThreadLocalRandom.current().nextInt(1, contactTypes.size() - 1);
        contactTypes.get(randomIndex).click();

        String selectedType = lastElement.findElement(new By.ByXPath("./div[1]/div[1]/div[1]/div[1]/label/div")).getText();
        contactsForTest.add(new Contact(selectedType, unText));
    }
}
