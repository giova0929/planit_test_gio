package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ContactPage extends BasePage {

    private By submitButton = By.cssSelector("a.btn-contact");
    private By errorMessage = By.id("forename-err");
    private By successMessage = By.cssSelector(".alert-success");
    private By forenameField = By.id("forename");
    private By emailField = By.id("email");
    private By messageField = By.id("message");

    public ContactPage(WebDriver driver) {
        super(driver);
    }

    public void clickSubmit() {
        click(submitButton);
    }

    public void fillMandatoryFields(String forename, String email, String message) {
        type(forenameField, forename);
        type(emailField, email);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(messageField));
        type(messageField, message);
    }
    public boolean isErrorDisplayed(String fornameRequired) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isSuccessMessageDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert-success")));
            return true; //
        } catch (TimeoutException e) {
            return false;
        }
    }

}