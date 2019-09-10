import com.oculow.COMPARISON;

import com.oculow.MANAGEMENT;
import com.oculow.Oculow;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
//
public class OculowTest {

    private Oculow oculow;
    private WebDriver driver;
    @BeforeTest
    private void setup(){
        oculow = new Oculow();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        oculow.setBaselineManagement(MANAGEMENT.ASSISTED);
        oculow.setComparison(COMPARISON.IGNORE_AA);
        oculow.setApiKey("YOUR_APIKEY","YOUR_APISECRET");
        oculow.setAppId("oculow"); // This should be changed to your app name.
    }

    @Test
    private void testCaptureScreen() {
        // launch Fire fox and direct it to the Base URL
        driver.get("https://www.oculow.com/");

        String actualTitle = driver.getTitle();
        assert actualTitle.contentEquals("Oculow"); //Assertions should always be added to validate the app.

        oculow.captureScreen(driver, "home page");

        WebElement _el = driver.findElement(By.id("landing-email"));
        _el.sendKeys("diego.ferrand@abstracta.com.uy");

        oculow.captureScreen(driver, "form submit");

        _el.click();

        driver.get("https://www.oculow.com/dashboard/home.html");

        oculow.captureScreen(driver, "dashboard index");
        oculow.captureScreen(driver, "Do not accept baseline");
    }

    @AfterTest
    private void teardown(){
        driver.close();
        oculow.dispose(); //Dispose should be called, this has a assertion to make sure that the execution passed.                                                                                                                                                                                                                                                                                                                                                                                                              
    }

}
