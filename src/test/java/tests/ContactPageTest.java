package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ContactPage;
import pages.HomePage;
import utils.ErrorMessageCatalog;

public class ContactPageTest extends BaseTest {

    @Test
    public void verifyErrorMessages() {
        HomePage homePage = new HomePage(driver);
        ContactPage contactPage = new ContactPage(driver);
        homePage.goToContactPage();
        contactPage.clickSubmit();
        Assert.assertTrue(contactPage.isErrorDisplayed(ErrorMessageCatalog.FORNAME_REQUIRED), ErrorMessageCatalog.FORNAME_REQUIRED);
        Assert.assertTrue(contactPage.isErrorDisplayed(ErrorMessageCatalog.EMAIL_REQUIRED), ErrorMessageCatalog.EMAIL_REQUIRED);
        Assert.assertTrue(contactPage.isErrorDisplayed(ErrorMessageCatalog.MESSAGE_REQUIRED), ErrorMessageCatalog.MESSAGE_REQUIRED);

        contactPage.fillMandatoryFields("John Doe", "john@example.com", "This is a test message.");
        contactPage.clickSubmit();
        Assert.assertFalse(contactPage.isErrorDisplayed(ErrorMessageCatalog.FORNAME_REQUIRED),
                "Error messages should be gone after filling the fields");

    }

    @Test(invocationCount = 5)
    public void validateSuccessfulSubmission() {
        HomePage homePage = new HomePage(driver);
        ContactPage contactPage = new ContactPage(driver);
        homePage.goToContactPage();
        contactPage.fillMandatoryFields("Jane Doe", "jane@example.com", "Automated test message.");
        contactPage.clickSubmit();
        Assert.assertTrue(contactPage.isSuccessMessageDisplayed(), "Success message should be displayed");
    }
}