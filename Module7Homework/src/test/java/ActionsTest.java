import PageObjects.Constants;
import PageObjects.LoginPage;
import PageObjects.YandexDiskPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static PageObjects.Constants.URL;
import static java.util.concurrent.TimeUnit.SECONDS;

public class ActionsTest {
    private WebDriver driver;

    @BeforeClass(alwaysRun = true)
    public void init() {

        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

        driver = new ChromeDriver();
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

    @AfterClass
    public void quit() {
        new YandexDiskPage(driver).logOut();
        driver.quit();
    }

}
