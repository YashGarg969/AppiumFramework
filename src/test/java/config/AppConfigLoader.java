package config;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import utilities.TestHelper;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * {@code AppConfigLoader} is responsible for loading environment-specific
 * application configuration details from a JSON file.
 * <p>
 * This class reads configuration values such as:
 * <ul>
 *     <li>Application package and activity names</li>
 *     <li>Appium server URL</li>
 * </ul>
 * It ensures that all tests and drivers are dynamically configured
 * without requiring hard-coded values in the test code.
 * </p>
 *
 * @see base.BaseTest
 * @author Yash Garg
 */
public class AppConfigLoader {

    private static ObjectMapper mapper= new ObjectMapper();


    /**
     * Loads application configurations from the JSON file.
     *
     * @return a {@link Map} containing key-value pairs from {@code appConfig.json}
     * @throws IOException if the configuration file is missing or unreadable.
     */
    public static Map<String,String> getAppConfigs() throws IOException {
        return mapper.readValue(new File("src/test/java/resources/appConfig.json"),Map.class);
    }

    public static Map<String,String> getAppConfigByIndex(int i) throws IOException {
        JsonNode jsonNode = TestHelper.extractJsonFromJsonArray(new File("src/test/java/resources/appConfig.json"), i);
        return mapper.convertValue(jsonNode, Map.class);
    }
}
