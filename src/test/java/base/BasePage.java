package base;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * BasePage serves as the parent class for all Page Object classes in the Appium framework.
 * <p>
 * It encapsulates the common properties and methods shared across different pages,
 * such as explicit waits, and utility actions like scrolling or sleeping.
 * </p>
 *
 * @author Yash Garg
 */
public class BasePage {

    protected AndroidDriver driver;
    protected WebDriverWait wait;

    public BasePage(AndroidDriver driver)
    {
        this.driver=driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Makes the current thread sleep for a specified number of seconds.
     * @param sec duration in seconds for which the thread should pause execution.
     * @throws InterruptedException if the sleep is interrupted.
     */
    public void sleep(int sec) throws InterruptedException {
        Thread.sleep(sec* 1000L);
    }

    /**
     * Scrolls the screen until the specified element becomes visible.
     * @param element the {@link WebElement} to which the screen should scroll.
     */
    public void scrollToElement(WebElement element)
    {
        // TODO: Implement scroll logic (e.g., using TouchAction or UiScrollable)
    }
}