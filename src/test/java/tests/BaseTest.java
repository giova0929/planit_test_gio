package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    protected Properties properties = new Properties();

    @BeforeMethod
    @Parameters({"url"})
    public void setup(@Optional("http://jupiter.cloud.planittesting.com") String url) throws IOException {
        // Cargar propiedades desde el archivo application.properties
        FileInputStream fis = new FileInputStream("src/main/resources/application.properties");
        properties.load(fis);

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setBinary("/Applications/Google Chrome.app/Contents/MacOS/Google Chrome");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(properties.getProperty("timeout"))));

        // Utilizamos la URL proporcionada o cargamos la URL desde properties
        if (url == null) {
            driver.get(properties.getProperty("baseUrl"));
        } else {
            driver.get(url);
        }
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
