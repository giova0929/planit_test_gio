package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {
    private By startShoppingButton = By.cssSelector("a.btn.btn-success.btn-large");
    //private By contactLink = By.linkText("Contact");
    private By contactLink = By.cssSelector("a[href='#/contact']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void clickStartShopping() {
        click(startShoppingButton);
    }

    public void goToContactPage() {
        WebElement contactElement = driver.findElement(contactLink);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", contactElement);
    }
}
