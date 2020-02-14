import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.UnexpectedException;

/**
 * Created by anithat on 11/25/2019.
 */
public class LoginWithoutContextTest {

    public AndroidDriver<AndroidElement> driver;
    public WebDriverWait wait;

    @BeforeMethod
    public void setup() throws MalformedURLException, UnexpectedException {
        DesiredCapabilities capabilities = new DesiredCapabilities().android();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "7.0");
//        capabilities.setCapability("deviceName","Pixel XL API 28");
//        capabilities.setCapability("udid", "emulator-5556");
        capabilities.setCapability("deviceName", "Galaxy Nexus API 28");
        capabilities.setCapability("udid", "emulator-5554");
        capabilities.setCapability("autoGrantPermissions", true);
        capabilities.setCapability("browserName","");
        capabilities.setCapability("appPackage","com.variablesmobile");
        capabilities.setCapability("appActivity","com.variablesmobile.Variables_Mobile");
        capabilities.setCapability("uiautomator2ServerLaunchTimeout","50000");
        capabilities.setCapability("--session-override",true);
        capabilities.setCapability("noRest", true);

        driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
//        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver,1000);
    }


    @Test
    public void logintesting() throws InterruptedException{
//        By userNameTxt = By.xpath("//*[@name='userNameField']//input");
//        By passwordTxt = By.xpath("//*[@name='passwordTxt']//input");
//        By loginButton = By.xpath("//*[@name='loginButton']");

//        By userNameTxt = By.xpath("//*[@class='android.widget.EditText' and @bounds='[92,298][628,376]']");
//        By passwordTxt = By.xpath("//*[@class='android.widget.EditText' and @bounds='[92,452][628,530]']");
//        By loginButton = By.xpath("//*[@class='android.widget.Button' and @bounds='[92,528][628,606]']");

        By userNameTxt = By.xpath("//*[@class='android.widget.EditText' and @resource-id='widget-id18']");
        By passwordTxt = By.xpath("//*[@class='android.widget.EditText' and @resource-id='widget-id21']");
        By loginButton = By.xpath("//*[@class='android.widget.Button' and @text='Sign in']");

        wait.until(ExpectedConditions.visibilityOfElementLocated(userNameTxt)).click();
        driver.findElement(userNameTxt).sendKeys("admin");
        driver.findElement(passwordTxt).sendKeys("admin");
        driver.findElement(loginButton).click();
        Thread.sleep(30000);
        By labellocator = By.xpath("//*[@class='android.view.View' and @text = 'LoggedIn as Admin']");
        Assert.assertTrue(driver.findElement(labellocator).isDisplayed());
    }

    /**
     * Method that gets invoked after test.
     * Dumps browser log and
     * Closes the browser
     */
    @AfterMethod
    public void tearDown() throws Exception {

        //Gets browser logs if available.
        driver.quit();
    }


}
