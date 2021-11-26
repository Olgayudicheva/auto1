package auto1.page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.function.Consumer;

public class AuthPage {
    By bySignInButton = new By.ByXPath("//button[@data-modal-id=\"new-log-reg\"]");
    By byLoginInput = new By.ByXPath("//input[@name=\"email\" and not(contains(@class,\"hide\")) and @type=\"text\"]");
    By byPasswordInput = new By.ByXPath("//input[@name=\"password\" and not(contains(@class,\"hide\"))]");
    By byEnterButton = new By.ByXPath("//button[contains(text(), 'Войти') and not(contains(text(), 'аккаунт'))]");

    private final WebDriver driver;

    public AuthPage(WebDriver driver) {
        this.driver = driver;
        if (!"https://otus.ru/".equals(driver.getCurrentUrl())) {
            throw new IllegalStateException("This is not the login page");
        }
        if (driver.findElements(bySignInButton).size()==0) {
            throw new IllegalStateException("This is not the login page");
        }
    }

    public MainPage auth (String email, String password){
        driver.findElement(bySignInButton).click();
        // Thread.sleep(1000);
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(byLoginInput));
        WebElement loginElement = driver.findElement(byLoginInput);
        WebElement passwordElement = driver.findElement(byPasswordInput);
        WebElement loginButtonElement = driver.findElement(byEnterButton);
        loginElement.sendKeys(email);
        passwordElement.sendKeys(password);
        loginButtonElement.click();
        // Thread.sleep(1000);
        System.out.println("COOKIE:");
        driver.manage().getCookies().forEach(new Consumer<Cookie>() {
            @Override
            public void accept(Cookie cookie) {
                System.out.println(cookie);
            }
        });
        return new MainPage(driver);
    }
}
