package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class YandexDiskPage extends HomePage {

    public YandexDiskPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    private static final String TRASH = "//div[contains(text(), 'Trash')]/ancestor::div//div[contains(@class, 'file-icon_dir_trash')]";
    private static final String NOTIFICATION_POPUP = "//div[contains(@class, 'notifications__item_trash')]";
    private static final String ITEMS_COUNT = "//div[contains(@class, 'ns-view-resources')]";
    private static final String RESTORE_BTN = " //button[contains(@class, '_nb-small-button _init ns-action')]";
    private static final String GO_TO_DISK = "//div/a[contains(@class, 'crumbs__link ns-action')]";
    private static final String SELECT_IMAGE = "//div[contains(text(), 'Sea')]";
    private static final String EMPTY_TRASH = "//button/span[text()='Empty']";
    private static final String TRASH_CONTENT = "//button/span[contains(text(), 'Empty Trash')]";
    private static final String AVATAR = "//div[contains(@class, 'avatar_size_m')]";
    private static final String LOG_OFF = "//span[text()='Log out']";

    @FindBy(xpath = SELECT_IMAGE)
    private WebElement selectImage;

    @FindBy(xpath = AVATAR)
    private WebElement avatar;

    @FindBy(xpath = LOG_OFF)
    private WebElement logOff;

    @FindBy(xpath = EMPTY_TRASH)
    private List<WebElement> emptyTrash;

    @FindBy(xpath = TRASH_CONTENT)
    private List<WebElement> trashContent;

    @FindBy(xpath = TRASH)
    private WebElement trash;

    @FindBy(xpath = NOTIFICATION_POPUP)
    private List<WebElement> notificationPopup;

    @FindBy(xpath = ITEMS_COUNT)
    private WebElement itemsCount;

    @FindBy(xpath = RESTORE_BTN)
    private WebElement restoreBtn;

    @FindBy(xpath = GO_TO_DISK)
    private WebElement goToDisk;


    public YandexDiskPage removeImage() {
        new Actions(driver).dragAndDrop(selectImage, trash).build().perform();
        waitForElement(notificationPopup);
        return new YandexDiskPage(driver);
    }

    public boolean isNotificationDisplayed() {
        return notificationPopup.get(0).isDisplayed();
    }

    public int getItemsCount() {
        JavascriptExecutor jsExec = (JavascriptExecutor) driver;
        Object result = jsExec.executeScript("var elements = document.querySelectorAll('div.ns-view-container-desc>div'); return elements;");
        int count = ((ArrayList<?>) result).size();
        return count;
    }

    public YandexDiskPage restoreItem() {
        new Actions(driver).moveToElement(trash).doubleClick().perform();
        waitForElement(trashContent);
        selectImage.click();
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript( "arguments[0].click();", restoreBtn );
        waitForElement(emptyTrash);
        goToDisk.click();
        return new YandexDiskPage(driver);
    }

    public void logOut() {
        avatar.click();
        logOff.click();
    }

}

