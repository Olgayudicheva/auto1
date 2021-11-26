package auto1.page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    By myMenu = new By.ByXPath("//div[@class=\"header2-menu\"]");
    By privateCabinet = new By.ByXPath("//a[@title=\"Личный кабинет\"]");

    private final WebDriver driver;
    public MainPage (WebDriver driver) {
        this.driver = driver;
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(myMenu));
        /*if (!"https://otus.ru/".equals(driver.getCurrentUrl())) {
            throw new IllegalStateException("This is not the login page");
        }
        if (driver.findElements(bySignInButton).size()==0) {
            throw new IllegalStateException("This is not the login page");
        }*/
    }
    public PrivateCabinetPage goToPrivateCabinet (){
        driver.findElement(myMenu).click();
        driver.findElement(privateCabinet).click();
        return new PrivateCabinetPage(driver);
    }
}
