package auto1;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class HomeWork2 {


    public HomeWork2() throws ParseException {
    }

    @BeforeAll
    static void before() {
        //Не могу проверить на linux и windows, у меня MacBook
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("Mac".toLowerCase())) {
            System.setProperty("webdriver.chrome.driver", "chromedriver_mac");
        } else if (os.contains("windows".toLowerCase())) {
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        } else {
            System.setProperty("webdriver.chrome.driver", "chromedriver_linux");
        }
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

    void authOTUS(WebDriver driver, String login, String password) throws InterruptedException {
        driver.get("https://otus.ru");
        driver.findElement(new By.ByClassName("header2__auth")).click();
        Thread.sleep(1000);
        WebElement loginElement = driver.findElement(new By.ByXPath("/html/body/div[2]/div/div/div/div[3]/div[2]/div[2]/form/div[2]/input"));
        WebElement passwordElement = driver.findElement(new By.ByXPath("/html/body/div[2]/div/div/div/div[3]/div[2]/div[2]/form/div[3]/input"));
        WebElement loginButtonElement = driver.findElement(new By.ByXPath("/html/body/div[2]/div/div/div/div[3]/div[2]/div[2]/form/div[4]/button"));
        loginElement.sendKeys(login);
        passwordElement.sendKeys(password);
        loginButtonElement.click();
        Thread.sleep(1000);
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
        return driver.findElement(new By.ByXPath("/html/body/div[1]/div/header[2]/div/div[3]/div"));
    }

    WebElement getMyOfficeElement(WebDriver driver) {
        return driver.findElement(new By.ByXPath("/html/body/div[1]/div/header[2]/div/div[3]/div/div[1]/div[2]/a[2]"));
    }

    WebElement getAboutMeElement(WebDriver driver) {
        return driver.findElement(new By.ByXPath("/html/body/div[1]/div/div[3]/div/div/div/a[3]"));
    }

    WebElement getNameElement(WebDriver driver) {
        return driver.findElement(new By.ByXPath("//*[@id=\"id_fname\"]"));
    }

    WebElement getLatNameElement(WebDriver driver) {
        return driver.findElement(new By.ByXPath("//*[@id=\"id_fname_latin\"]"));
    }

    WebElement getLastNameElement(WebDriver driver) {
        return driver.findElement(new By.ByXPath("//*[@id=\"id_lname\"]"));
    }

    WebElement getLatLastNameElement(WebDriver driver) {
        return driver.findElement(new By.ByXPath("//*[@id=\"id_lname_latin\"]"));
    }

    WebElement getBlogNameElement(WebDriver driver) {
        return driver.findElement(new By.ByXPath("//*[@id=\"id_blog_name\"]"));
    }

    WebElement getBirthDateElement(WebDriver driver) {
        return driver.findElement(new By.ByXPath("/html/body/div[1]/div/div[5]/div[3]/div[2]/div[2]/div/form/div[1]/div[1]/div/div[4]/div/div/input"));
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

        WebDriver driver = new ChromeDriver(options);

        try {
            driver.manage().window().maximize();
            authOTUS(driver, "test.olga123456@gmail.com", "passpass1");
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

            driver.findElement(new By.ByXPath("/html/body/div[1]/div/div[5]/div[3]/div[2]/div[2]/div/form/div[2]/div/div/button[2]")).click();
        } finally {
            Thread.sleep(5000);
            driver.quit();
        }
    }

    void checkData() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--headless");
        options.addArguments("--start-maximised");

        WebDriver driver = new ChromeDriver(options);

        try {
            driver.manage().window().maximize();
            authOTUS(driver, "test.olga123456@gmail.com", "passpass1");
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
                Contact contact = contactsForTest.get(i);
                String selectedType = contactElement.findElement(new By.ByXPath("./div[1]/div[1]/div[1]/div[1]/label/div")).getText();
                Assertions.assertEquals(contact.type, selectedType);
                Assertions.assertEquals(contact.text, lastContactInput.getAttribute("value"));
            }
        } finally {
            Thread.sleep(5000);
            driver.quit();
        }
    }

    List<WebElement> getContactsListElements(WebDriver driver) {
        return driver.findElements(new By.ByXPath("/html/body/div[1]/div/div[5]/div[3]/div[2]/div[2]/div/form/div[1]/div[3]/div[2]/div[2]/div/div[@data-num and not(contains(@class,'hide'))]"));
    }

    void deleteAllContact(WebDriver driver) throws InterruptedException {
        List<WebElement> contacts = getContactsListElements(driver);;
        while (!contacts.isEmpty()) {
            WebElement last = contacts.get(contacts.size()-1);
            WebElement deleteButton = last.findElement(new By.ByXPath("./div[3]/div[2]/button"));
            deleteButton.click();
            Thread.sleep(300);
            contacts = getContactsListElements(driver);
        }

    }

    void addContact(WebDriver driver) {
        WebElement addContact = driver.findElement(new By.ByXPath("/html/body/div[1]/div/div[5]/div[3]/div[2]/div[2]/div/form/div[1]/div[3]/div[2]/div[2]/button"));

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
        int randomIndex = ThreadLocalRandom.current().nextInt(0, contactTypes.size() - 1);
        contactTypes.get(randomIndex).click();

        String selectedType = lastElement.findElement(new By.ByXPath("./div[1]/div[1]/div[1]/div[1]/label/div")).getText();
        contactsForTest.add(new Contact(selectedType, unText));
    }
}
