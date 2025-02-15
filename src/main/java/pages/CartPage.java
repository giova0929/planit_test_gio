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
        String totalPrice = getText(totalPriceLocator).trim();
        String priceWithoutSymbol = totalPrice.replaceAll("[^0-9\\.]", "");
        return String.format("%.2f", Double.parseDouble(priceWithoutSymbol));
    }


    public String getProductPrice(String productName) {
        String productPriceXpath = String.format("//td[contains(text(), '%s')]/following-sibling::td[1]", productName);
        System.out.println("XPath for the product price: " + productPriceXpath);

        By productPrice = By.xpath(productPriceXpath);
        waitForElement(productPrice);
        String price = getText(productPrice).trim();
        System.out.println("Price retrieved for " + productName + ": " + price);
        return price.replaceAll("[^0-9\\.]", "");
    }


    public String getProductSubtotal(String productName) {
        String productSubtotalXpath = String.format("//tr[td[contains(text(), '%s')]]/td[4]", productName);
        System.out.println("XPath for the product subtotal: " + productSubtotalXpath);

        By productSubtotal = By.xpath(productSubtotalXpath);
        waitForElement(productSubtotal);

        String subtotal = getText(productSubtotal).trim();
        System.out.println("Subtotal retrieved: '" + subtotal + "'");

        if (subtotal.isEmpty()) {
            throw new AssertionError("Subtotal for the product not found.");
        }
        return subtotal.replaceAll("[^0-9\\.]", "");
    }
}
