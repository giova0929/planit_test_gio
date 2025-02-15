package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ShopPage extends BasePage {

    private By startShoppingButton = By.cssSelector("a.btn.btn-success.btn-large");
    private By cartButton = By.xpath("//a[contains(text(),'Cart')]");

    public ShopPage(WebDriver driver) {
        super(driver);
    }

    public void goToShop() {
        click(startShoppingButton);
    }

    public void addProductToCart(String productName, int quantity) {
        By productLocator = By.xpath("//h4[contains(text(),'" + productName + "')]/following-sibling::p/a");
        for (int i = 0; i < quantity; i++) {
            click(productLocator);
        }
    }

    public void goToCart() {
        click(cartButton);
    }
}
