package dataproviders;

import org.testng.annotations.DataProvider;

/**
 * {@code CalculatorDataProvider} supplies test data sets for calculator-related tests.
 * <p>
 * This class provides structured input values and expected outputs for
 * basic arithmetic operations such as addition, subtraction, and multiplication.
 * </p>
 *
 * @see tests.CalculatorTest
 * @author Yash Garg
 */
public class CalculatorDataProvider {
    @DataProvider(name = "basicCalculationData")
    public Object[][] getBasicCalculationData()
    {
        return new Object[][]{
                {5,7,'*',35},
                {3,5,'+',8},
                {1,9,'-',-8}
        };
    }
}
