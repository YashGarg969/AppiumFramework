package utilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidStartScreenRecordingOptions;
import io.appium.java_client.screenrecording.CanRecordScreen;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Base64;

/**
 * Utility class providing helper methods for screen recording, video file management,
 * and Allure report integration in Appium-based test automation.
 * <p>
 * This class cannot be instantiated (private constructor via Lombok) and
 * contains only static methods to maintain a utility design pattern.
 * </p>
 *
 * @author Yash Garg
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Utils {

    /**
     * Starts screen recording on the connected Android device.
     * <p>
     * Uses Appium’s {@link CanRecordScreen} capability to begin recording
     * with a default time limit of 3 minutes and a bitrate of 5 Mbps.
     * </p>
     *
     * @param driver the {@link AndroidDriver} instance controlling the device.
     * @throws Exception if recording fails to start.
     */

    @Step("Started Screen Recording")
    public static void recordScreen(AndroidDriver driver) throws Exception {
        Thread.sleep(10000);
        try{
            ((CanRecordScreen)(driver)).startRecordingScreen(new AndroidStartScreenRecordingOptions()
                    .withTimeLimit(Duration.ofMinutes(3))
                    .withBitRate(5000000));
        }
        catch (Exception e)
        {
            System.out.println("Failed to save recording"+ e);
            throw new Exception(e);
        }
    }

    /**
     * Stops an ongoing screen recording and returns the recorded video as bytes.
     *
     * @param driver the {@link AndroidDriver} instance controlling the device.
     * @return a byte array representing the recorded video file.
     */

    @Step("Screen Recording Stopped")
    public static byte[] stopRecording(AndroidDriver driver)
    {
        String base64String = ((CanRecordScreen)(driver)).stopRecordingScreen();
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] videoBytes= decoder.decode(base64String);
        return videoBytes;
    }

    /**
     * Saves the recorded video to the specified destination path.
     *
     * @param driver      the {@link AndroidDriver} instance controlling the device.
     * @param destination the file system path where the video will be saved
     * @throws RuntimeException if the file cannot be saved.
     */

    @Step("Saving Recorded Video")
    public static void saveVideo(AndroidDriver driver, String destination)
    {
        byte[] bytes = stopRecording(driver);
        Path path= Paths.get(destination);
        try {
            Files.createDirectories(path.getParent());
            Files.write(path, bytes);
        } catch (IOException e) {
            throw new RuntimeException("Failed To Save Video " + e);
        }
    }

    /**
     * Attaches a recorded video file to the Allure report.
     * <p>
     * If the video file does not exist, an empty byte array is returned to prevent test interruption.
     * </p>
     *
     * @param attachName the display name for the attachment in the Allure report.
     * @param filePath   the path to the recorded video file.
     * @return byte array representing the video, or empty array if file is missing.
     */

    @Step("Attaching Video To Allure")
    @Attachment(value = "{attachName}", type = "video/mp4")
    public static byte[] attachVideoToAllure(String attachName, String filePath) {
        try {
            Path path = Paths.get(filePath);
            if (!Files.exists(path)) {
                return new byte[0];
            }
            return Files.readAllBytes(path);
        } catch (IOException e) {
            throw new RuntimeException("Failed to attach video: " + e.getMessage(), e);
        }
    }
}
