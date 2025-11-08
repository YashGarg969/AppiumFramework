package base;

import config.AppConfigLoader;
import config.DesiredCapabilitiesLoader;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.ITestContext;
import org.testng.annotations.*;
import org.testng.log4testng.Logger;
import utilities.Utils;

import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.util.Map;

/**
 * <p>
 * BaseTest handles the setup and teardown of the Appium {@link AndroidDriver},
 * loads environment-specific configurations, and integrates utilities such as
 * video recording and Allure reporting.
 * </p>
 *
 * @author Yash Garg
 */

public class BaseTest {
    protected String env;
    protected int index;
    protected int systemPort;
    protected AndroidDriver driver;
    protected String appPackage;
    protected URL appiumServerUrl;
    protected final Logger logger= Logger.getLogger(BaseTest.class);

    /**
     * Initializes the Appium driver before each test run.
     * <p>
     * This method reads environment configurations, loads desired capabilities,
     * connects to the Appium server, and starts screen recording.
     * </p>
     *
     * @param env the testing environment (default = "Emulator").
     * @throws Exception if driver setup fails or configurations are missing.
     */

    @Parameters({"env","index","port"})
    @BeforeTest
    public void setupDriver(@Optional("Emulator") String env, @Optional("0") String index, @Optional("8200") String port) throws Exception {

        this.env= env;
        this.index= Integer.parseInt(index);
        this.systemPort= Integer.parseInt(port);
        logger.info(String.format("Setting up driver for env:- %s",env));

        UiAutomator2Options uiAutomator2Options = DesiredCapabilitiesLoader.loadDesiredCapabilitiesByIndex(env, this.index);
        uiAutomator2Options.setSystemPort(systemPort);
        this.appPackage = String.valueOf(uiAutomator2Options.getAppPackage());
        Map<String, String> appConfigByIndex = AppConfigLoader.getAppConfigByIndex(this.index);
        appiumServerUrl= URI.create(appConfigByIndex.get("appiumServerUrl")).toURL();

        logger.info(String.format("Appium Server is running on %s",appiumServerUrl));
        driver = new AndroidDriver(appiumServerUrl, uiAutomator2Options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Utils.recordScreen(driver);
    }

    /**
     * Cleans up resources after test execution.
     * <p>
     * This method stops video recording, saves and attaches it to the Allure report,
     * removes the tested application, and quits the Appium driver instance.
     * </p>
     */

    @AfterTest
    public void tearDown(ITestContext iTestContext) {
        String testName= iTestContext.getName();
        String videoName= String.format("%s_%s.mp4",testName,systemPort);
        if (driver != null) {
            try {
                Utils.saveVideo(driver,String.format("target/video/%s",videoName));
                Utils.attachVideoToAllure("TestExecutionVideo",String.format("target/video/%s",videoName));

                if (appPackage != null && !appPackage.trim().isEmpty())
                    driver.removeApp(appPackage);

            }
            catch (RuntimeException runtimeException) {
                logger.error(runtimeException.getMessage());
            }
            catch (Exception e) {
                logger.error("Error removing app: " + e.getMessage());
            } finally {
                try {
                    driver.quit();
                } catch (Exception e) {
                    logger.error("Error quitting driver: " + e.getMessage());
                }
            }
        }
    }
}