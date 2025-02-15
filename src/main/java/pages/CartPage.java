package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {
    private By totalPriceLocator = By.xpath("/html/body/div[2]/div/form/table/tfoot/tr[1]/td/strong");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public String getTotalPrice() {
        waitForElement(totalPriceLocator);
        return getText(totalPriceLocator).trim();
    }
}
