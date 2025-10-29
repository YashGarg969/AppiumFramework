package pages.CalculatorScreens;

import base.BasePage;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

/**
 * {@code HistoryPage} represents the Calculator app's history screen
 * where previously performed calculations are displayed.
 * <p>
 * This class follows the Page Object Model (POM) design pattern,
 * encapsulating all UI locators and user actions related to the
 * History screen, such as verifying stored results, clearing history,
 * and closing the page.
 *
 * @author  Yash Garg
 */

public class HistoryPage extends BasePage {

    @FindBy(id = "com.vivo.calculator:id/history_item_layout")
    WebElement ITEM_LAYOUT;

    @FindBy(id = "com.vivo.calculator:id/calculator_result")
    WebElement RESULT_TEXT;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"com.vivo.calculator:id/vbutton_title\" and @text=\"OK\"]")
    WebElement OK_BTN;

    @FindBy(xpath = "//android.widget.Button[@resource-id=\"com.vivo.calculator:id/delete_history\"]")
    WebElement CLEAR_HISTORY_BTN;

    @FindBy(xpath = "//android.widget.LinearLayout[@content-desc=\"No history\"]")
    WebElement NO_HISTORY_LAYOUT;

    @FindBy(xpath = "//android.widget.TextView[@text=\"No history\"]")
    WebElement NO_HISTORY_TEXT;

    @FindBy(xpath = "//android.widget.Button[@content-desc=\"Close history\"]")
    WebElement CLOSE_HISTORY_PAGE;

    public HistoryPage(AndroidDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    @Step("Verifying Result Present In History)")
    public HistoryPage verifyResultPresent(String expectedResult)
    {
        boolean isItemLayoutPresent = ITEM_LAYOUT.isDisplayed();
        Assert.assertTrue(isItemLayoutPresent);
        String actualResult = RESULT_TEXT.getText();
        Assert.assertTrue(actualResult.equalsIgnoreCase(expectedResult));
        return this;
    }

    @Step("Clicking On Clear History Button")
    public HistoryPage clickOnClearHistory()
    {
        CLEAR_HISTORY_BTN.click();
        return this;
    }

    @Step("Verifying No History")
    public HistoryPage verifyNoHistoryPresent()
    {
        boolean isNoHistoryLayoutPresent = NO_HISTORY_LAYOUT.isDisplayed();
        Assert.assertTrue(isNoHistoryLayoutPresent);

        String noHistoryText = NO_HISTORY_TEXT.getText();
        Assert.assertTrue(noHistoryText.equalsIgnoreCase("No history"));
        return this;
    }

    @Step("Confirming To Perform Clear History")
    public HistoryPage confirmClearHistory() throws InterruptedException {

        wait.until(ExpectedConditions.visibilityOf(OK_BTN));
        OK_BTN.click();
        return this;
    }

    @Step("Closing History Page")
    public HistoryPage closeHistoryPage()
    {
        CLOSE_HISTORY_PAGE.click();
        return this;
    }
}
