package tests;

import base.BaseTest;
import dataproviders.CalculatorDataProvider;
import org.testng.annotations.Test;
import pages.CalculatorScreens.CalculationPage;
import pages.CalculatorScreens.HistoryPage;

/**
 * {@code CalculatorTest} contains end-to-end UI automation tests for a Calculator mobile app.
 * <p>
 * This class extends {@link BaseTest}, leveraging its Appium driver setup and teardown.
 * Tests are data-driven and utilize the {@link CalculatorDataProvider} to supply
 * calculation inputs dynamically.
 * </p>
 *
 * @author Yash Garg
 */
public class CalculatorTest extends BaseTest {

    @Test(testName = "Basic Calculation Test", dataProvider = "basicCalculationData", dataProviderClass = CalculatorDataProvider.class)
    public void testCalculation(int n1, int n2, char op, int expectedResult)
    {
        CalculationPage calculationPage= new CalculationPage(driver);
        calculationPage
                .pressNumber(n1)
                .doOperation(op)
                .pressNumber(n2)
                .verifyResult(String.valueOf(expectedResult))
                .doOperation('=')
                .verifyResultAreaAfterPerformingEquals(String.valueOf(expectedResult))
                .clickOnACBtn();
    }

    @Test(testName = "AC Button Test", dataProvider = "basicCalculationData", dataProviderClass = CalculatorDataProvider.class)
    public void testACButton(int n1, int n2, char op, int expectedResult)
    {
        CalculationPage calculationPage= new CalculationPage(driver);
        calculationPage
                .pressNumber(n1)
                .doOperation(op)
                .pressNumber(n2)
                .verifyResult(String.valueOf(expectedResult))
                .doOperation('=')
                .verifyResultAreaAfterPerformingEquals(String.valueOf(expectedResult))
                .clickOnACBtn()
                .verifyResultAreaAfterPerformingEquals("");
    }

    @Test(testName = "Verify Results ON History Page Test", dataProvider = "basicCalculationData", dataProviderClass = CalculatorDataProvider.class)
    public void testResultsOnHistoryPage(int n1, int n2, char op, int expectedResult)
    {
        CalculationPage calculationPage= new CalculationPage(driver);
        calculationPage
                .pressNumber(n1)
                .doOperation(op)
                .pressNumber(n2)
                .doOperation('=')
                .verifyResultAreaAfterPerformingEquals(String.valueOf(expectedResult))
                .clickOnACBtn()
                .openHistoryPage();

        HistoryPage historyPage= new HistoryPage(driver);
        historyPage
                .verifyResultPresent("="+expectedResult)
                .closeHistoryPage();
    }


    @Test(testName = "Clear History Test", dataProvider = "basicCalculationData", dataProviderClass = CalculatorDataProvider.class)
    public void testClearHistory(int n1, int n2, char op, int expectedResult) throws InterruptedException {
        CalculationPage calculationPage= new CalculationPage(driver);
        calculationPage
                .pressNumber(n1)
                .doOperation(op)
                .pressNumber(n2)
                .doOperation('=')
                .verifyResultAreaAfterPerformingEquals(String.valueOf(expectedResult))
                .clickOnACBtn()
                .openHistoryPage();

        HistoryPage historyPage= new HistoryPage(driver);
        historyPage
                .clickOnClearHistory()
                .confirmClearHistory();

        calculationPage
                .openHistoryPage();

        historyPage
                .verifyNoHistoryPresent()
                .closeHistoryPage();
    }
}
