import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import javafx.scene.web.WebView;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.UnexpectedException;
import java.util.Set;



/**
 * Created by anithat on 12/8/2019.
 */

public class LoginTest {
    public AppiumDriver driver;
    public WebDriverWait wait;

    public static final String app = "https://github.com/anitha-thummalapally/Apps/blob/master/VariablesAPK.apk?raw=true";

    @BeforeMethod
    public void setup() throws MalformedURLException, UnexpectedException {
        DesiredCapabilities capabilities = new DesiredCapabilities().android();
        capabilities.setCapability("deviceName","Galaxy Nexus API 28");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "7.0");
        capabilities.setCapability("udid", "emulator-5554");
        capabilities.setCapability("autoGrantPermissions", true);
//        capabilities.setCapability("chromedriverUseSystemExecutable", true);
        capabilities.setCapability("browserName", "");
        capabilities.setCapability("app",app);
        capabilities.setCapability("appPackage", "com.variablesmobile");
        capabilities.setCapability("appActivity", "com.variablesmobile.Variables_Mobile");
        capabilities.setCapability("uiautomator2ServerLaunchTimeout", "50000");
        capabilities.setCapability("--session-override", true);
        capabilities.setCapability("noRest", true);

        driver = new AppiumDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        wait = new WebDriverWait(driver, 1000);
    }

    @Test
    public void logintesting() throws InterruptedException {

        //Switching context from NATIVE_APP to WEB_VIEW
        Set<String> contexts = driver.getContextHandles();
        for (String context : contexts) {
            System.out.println(context);
        }
        driver.context("WEBVIEW_com.variablesmobile");

        //Locators
        By userNameTxt = By.xpath("//*[@name='userNameField']//input");
        By passwordTxt = By.xpath("//*[@name='passwordField']//input");
        By loginButton = By.xpath("//*[@name='loginButton']");

        //Functionality
        wait.until(ExpectedConditions.visibilityOfElementLocated(userNameTxt)).click();
        driver.findElement(userNameTxt).sendKeys("admin");
        driver.findElement(passwordTxt).sendKeys("admin");
        driver.findElement(loginButton).click();
        Thread.sleep(30000);
        By labellocator = By.xpath("//label[text() = 'LoggedIn as Admin']");
        Assert.assertTrue(driver.findElement(labellocator).isDisplayed());

    }

    @AfterMethod
    public void tearDown() throws Exception {

        //Gets browser logs if available.
        driver.quit();
    }
}
