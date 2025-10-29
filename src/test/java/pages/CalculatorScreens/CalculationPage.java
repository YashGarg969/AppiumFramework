package pages.CalculatorScreens;

import base.BasePage;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

/**
 * {@code CalculationPage} represents the main calculator screen of the mobile app.
 * <p>
 * This Page Object encapsulates all locators and actions related to performing
 * mathematical operations, entering numbers, verifying calculation results, and
 * navigating to the History screen.
 * </p>
 *
 * @author Yash Garg
 */
public class CalculationPage extends BasePage {

    @FindBy(id = "com.vivo.calculator:id/formula")
    WebElement TEXT_AREA;
    
    @FindBy(id = "com.vivo.calculator:id/result")
    WebElement RESULT_TEXT_AREA;
    
    @FindBy(id = "com.vivo.calculator:id/formula")
    WebElement RESULT_AREA;

    @FindBy(xpath = "//android.widget.ImageButton[@content-desc=\"Decimal point\"]")
    WebElement DECIMAL_BTN;

    @FindBy(xpath = "//android.widget.ImageButton[@content-desc=\"Plus\"]")
    WebElement ADD_BTN;

    @FindBy(xpath = "//android.widget.ImageButton[@content-desc=\"Minus\"]")
    WebElement SUBTRACT_BTN;

    @FindBy(xpath = "//android.widget.ImageButton[@content-desc=\"Multiply\"]")
    WebElement MULTIPLY_BTN;

    @FindBy(xpath ="//android.widget.ImageButton[@content-desc=\"Divide\"]")
    WebElement DIVIDE_BTN;

    @FindBy(xpath = "//android.widget.ImageButton[@content-desc=\"=\"]")
    WebElement EQUALS_BTN;

    @FindBy(xpath = "//android.widget.ImageButton[@content-desc=\"Percent\"]")
    WebElement PERCENT_BTN;

    @FindBy(xpath = "//android.widget.ImageButton[@content-desc=\"Clear\"]")
    WebElement AC_BTN;

    @FindBy(xpath = "//android.widget.ImageButton[@content-desc=\"Delete\"]")
    WebElement DELETE_BTN;

    @FindBy(xpath = "//android.widget.Button[@content-desc=\"Open history\"]")
    WebElement OPEN_HISTORY_BTN;

    public static final String NUMBER_BTN_XPATH= "//android.widget.ImageButton[@content-desc= '%s']";


    public CalculationPage(AndroidDriver driver)
    {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    @Step("Perform Operation")
    public CalculationPage doOperation(char operation)
    {
        switch (operation)
        {
            case '+' -> ADD_BTN.click();
            case '-' -> SUBTRACT_BTN.click();
            case '*' -> MULTIPLY_BTN.click();
            case '/' -> DIVIDE_BTN.click();
            case '%' -> PERCENT_BTN.click();
            case '=' -> EQUALS_BTN.click();
            default -> throw new RuntimeException(operation +" operation is not present.");
        }
        return this;
    }

    @Step("Entering Digit")
    public CalculationPage pressNumber(int num)
    {
        if(num<0)
            throw new NumberFormatException("Negative digits not allowed");
        else if(num>9)
            throw new NumberFormatException("Please send one digit at a time");
        String xpath = String.format(NUMBER_BTN_XPATH, num);
        WebElement NUMBER_BTN= driver.findElement(By.xpath(xpath));
        NUMBER_BTN.click();
        return this;
    }

    @Step("Entering Decimal")
    public CalculationPage pressDecimalBtn()
    {
        DECIMAL_BTN.click();
        return this;
    }

    @Step("Clicking AC Button")
    public CalculationPage clickOnACBtn()
    {
        AC_BTN.click();
        return this;
    }

    @Step("Pressing Back Button")
    public CalculationPage clickOnClearBtn()
    {
        DELETE_BTN.click();
        return this;
    }

    @Step("Opening History Page")
    public CalculationPage openHistoryPage()
    {
        wait.until(ExpectedConditions.visibilityOf(OPEN_HISTORY_BTN));
        OPEN_HISTORY_BTN.click();
        return this;
    }

    @Step("Verifying Result Of Operation")
    public CalculationPage verifyResult(String expectedResult)
    {
        String actualResult = RESULT_TEXT_AREA.getText();
        Assert.assertTrue(expectedResult.equalsIgnoreCase(actualResult),String.format("Expected: %s, Found: %s",expectedResult,actualResult));
        return this;
    }

    @Step("Verifying Result Of Operation After Performing Equals")
    public CalculationPage verifyResultAreaAfterPerformingEquals(String expectedResult)
    {
        String actualResult = RESULT_AREA.getText();
        Assert.assertTrue(expectedResult.equalsIgnoreCase(actualResult),String.format("Expected: %s, Found: %s",expectedResult,actualResult));
        return this;
    }

    @Step("Verifying The Expression Entered")
    public CalculationPage verifyCorrectExpressionEntered(String expectedExpression)
    {
        String actualExpression= TEXT_AREA.getText();
        Assert.assertTrue(expectedExpression.equalsIgnoreCase(actualExpression),String.format("Expected: %s, Found: %s",expectedExpression,actualExpression));
        return this;
    }
}