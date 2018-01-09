import PageObjects.Constants;
import PageObjects.LoginPage;
import PageObjects.YandexDiskPage;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static PageObjects.Constants.URL;
import static java.util.concurrent.TimeUnit.SECONDS;

public class ActionsTest {
    private WebDriver driver;
    //private DesiredCapabilities desiredCapabilities;

    //@Parameters({"browserType"})
    @BeforeClass()
    public void init(/*String browserType*/) {

        /*if(browserType.equals("firefox")){
            desiredCapabilities = DesiredCapabilities.firefox();
            desiredCapabilities.setPlatform(Platform.WINDOWS);
        }else if (browserType.equals("IE")){
            desiredCapabilities= DesiredCapabilities.internetExplorer();
            desiredCapabilities.setPlatform(Platform.ANY);
        } else if (browserType.equals("chrome")){
            desiredCapabilities= DesiredCapabilities.chrome();
            desiredCapabilities.setPlatform(Platform.WINDOWS);
        }*/

        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.setCapability("platformName", Platform.WINDOWS);
        try {
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver.get(URL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, SECONDS);
    }

    @Test
    public void signIn() {
        boolean isPageOpened = new LoginPage(driver).signInAccount(Constants.USERNAME, Constants.PASSWORD).isPageOpened();
        Assert.assertTrue(isPageOpened, "The page is not opened");
    }
    @Test(dependsOnMethods = "signIn")
    public void verifyInitialCount() {
        int count = new YandexDiskPage(driver).getItemsCount();
        Assert.assertEquals(count, 8);
    }

    @Test(dependsOnMethods = "verifyInitialCount")
    public void dragImage() {
        boolean isRemoved = new YandexDiskPage(driver).removeImage().isNotificationDisplayed();
        Assert.assertTrue(isRemoved, "element is moved to trash");
    }

    @Test(dependsOnMethods = "dragImage")
    public void verifyCount() {
        int count = new YandexDiskPage(driver).getItemsCount();
        Assert.assertEquals(count, 7);
    }

    @Test(dependsOnMethods = "verifyCount")
    public void restoreImage() {
        int count = new YandexDiskPage(driver).restoreItem().getItemsCount();
        Assert.assertEquals(count, 8);
    }

    @Test
    public void takeScreenshot() {
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File("screenshot.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public void quit() {
        new YandexDiskPage(driver).logOut();
        driver.quit();
    }

}
