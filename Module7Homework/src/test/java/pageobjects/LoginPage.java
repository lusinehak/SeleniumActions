package pageobjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends pageobjects.HomePage {

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    private static final String LOGIN_BTN = "//a[contains(@class, 'button2_theme_normal')]";
    private static final String USERNAME = "login";
    private static final String PASSWORD = "passwd";
    private static final String CLOSE_WINDOW = "//div[@class='_nb-popup-i']/a";
    private static final String VERIFY_PAGE = "//a[contains(@class, 'logo-link_yandex')]";

    @FindBy(xpath = LOGIN_BTN)
    private WebElement loginBtn;

    @FindBy(name = USERNAME)
    private WebElement username;

    @FindBy(name = PASSWORD)
    private WebElement password;

    @FindBy(xpath = CLOSE_WINDOW)
    private WebElement closeWindow;

    @FindBy(xpath = VERIFY_PAGE)
    private WebElement verifyPage;

    public LoginPage signInAccount(String name, String passwd) {
        loginBtn.click();
        username.sendKeys(name);
        password.sendKeys(passwd);
        new Actions(driver).sendKeys(Keys.RETURN).build().perform();
//        closeWindow.click();
        return new LoginPage(driver);
    }

    public boolean isPageOpened() {
        return verifyPage.isDisplayed();
    }
}
