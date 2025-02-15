package tests;

import org.testng.annotations.Test;
import pages.HomePage;

public class HomePageTest extends BaseTest {

    @Test
    public void navigateToShop() {
        HomePage homePage = new HomePage(driver);
        homePage.clickStartShopping();
    }
}
