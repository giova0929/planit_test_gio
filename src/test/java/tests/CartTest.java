package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.ShopPage;

public class CartTest extends BaseTest {

    private ShopPage shopPage;
    private CartPage cartPage;

    @Test
    public void verifyCartCalculations() {
        shopPage = new ShopPage(driver);
        cartPage = new CartPage(driver);

        double stuffedFrogPrice = 10.99;
        double fluffyBunnyPrice = 9.99;
        double valentineBearPrice = 14.99;

        int stuffedFrogQuantity = 2;
        int fluffyBunnyQuantity = 5;
        int valentineBearQuantity = 3;

        shopPage.goToShop();
        shopPage.addProductToCart("Stuffed Frog", stuffedFrogQuantity);
        shopPage.addProductToCart("Fluffy Bunny", fluffyBunnyQuantity);
        shopPage.addProductToCart("Valentine Bear", valentineBearQuantity);

        shopPage.goToCart();

        verifyProductPriceAndSubtotal("Stuffed Frog", stuffedFrogQuantity, stuffedFrogPrice);
        verifyProductPriceAndSubtotal("Fluffy Bunny", fluffyBunnyQuantity, fluffyBunnyPrice);
        verifyProductPriceAndSubtotal("Valentine Bear", valentineBearQuantity, valentineBearPrice);

        String total = cartPage.getTotalPrice();
        System.out.println("Total obtained: " + total);

        double expectedTotal = (stuffedFrogQuantity * stuffedFrogPrice) +
                (fluffyBunnyQuantity * fluffyBunnyPrice) +
                (valentineBearQuantity * valentineBearPrice);

        //  formatted to two decimal places for comparison
        Assert.assertEquals(total, String.format("%.2f", expectedTotal), "The total is incorrect");
    }

    private void verifyProductPriceAndSubtotal(String productName, int quantity, double price) {
        String productPrice = cartPage.getProductPrice(productName);
        System.out.println("Price for " + productName + ": " + productPrice);
        Assert.assertEquals(productPrice, String.format("%.2f", price), "The price for " + productName +
                " is incorrect");

        double expectedSubtotal = quantity * price;
        String productSubtotal = cartPage.getProductSubtotal(productName);
        System.out.println("Subtotal for " + productName + ": " + productSubtotal);
        Assert.assertEquals(productSubtotal, String.format("%.2f", expectedSubtotal), "The subtotal for "
                + productName + " is incorrect");
    }
}
