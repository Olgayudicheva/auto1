package auto1.page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PrivateCabinetPage {
    By aboutMe = new By.ByXPath("//div[contains(@class,'nav ') and not(contains(@style,'display: none'))]//a[@title=\"О себе\"]");

    private final WebDriver driver;
    public PrivateCabinetPage (WebDriver driver) {
        this.driver = driver;
    }

    public void clicToAboutMe() {
        driver.findElement(aboutMe).click();
    }
}
