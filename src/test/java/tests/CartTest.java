package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.ShopPage;

public class CartTest {
    private WebDriver driver;
    private ShopPage shopPage;
    private CartPage cartPage;
    private String appUrl = "http://jupiter.cloud.planittesting.com";

    @BeforeTest
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        shopPage = new ShopPage(driver);
        cartPage = new CartPage(driver);
        shopPage.openUrl(appUrl); // Aseguramos abrir la URL al inicio
    }

    @Test
    public void verifyCartCalculations() {
        shopPage.goToShop(); // Nos aseguramos de hacer clic en "Start Shopping"
        shopPage.addProductToCart("Stuffed Frog", 2);
        shopPage.addProductToCart("Fluffy Bunny", 5);
        shopPage.addProductToCart("Valentine Bear", 3);
        shopPage.goToCart(); // Vamos al carrito después de agregar productos

        String total = cartPage.getTotalPrice();
        System.out.println("Total obtenido: " + total);

        Assert.assertEquals(total, "Total: 116.9", "El total no es correcto");
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
