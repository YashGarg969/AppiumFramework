package config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * {@code DesiredCapabilitiesLoader} dynamically loads and applies Appium
 * desired capabilities for both emulator and real device test environments.
 * <p>
 * This utility reads capability data from JSON configuration files and
 * uses Java Reflection to invoke matching setter methods in
 * {@link UiAutomator2Options}.
 * </p>
 *
 * @see base.BaseTest
 * @author Yash Garg
 */
public class DesiredCapabilitiesLoader {

    /**
     * Loads desired capabilities for the specified environment and returns
     * a fully configured {@link UiAutomator2Options} object.
     *
     * <p>The environment determines which JSON file is read:</p>
     *
     * @param env the target test environment, e.g., {@code "EMULATOR"} or {@code "REAL"}.
     * @return a configured {@link UiAutomator2Options} instance with all capabilities applied.
     * @throws IOException if the capability file is missing or cannot be parsed.
     */

    public static UiAutomator2Options loadDesiredCapabilities(String env) throws IOException {
        UiAutomator2Options options= new UiAutomator2Options();

        ObjectMapper mapper= new ObjectMapper();
        Map<String,Object> desiredCapsMap;
        if(env.equalsIgnoreCase("EMULATOR"))
            desiredCapsMap= mapper.readValue(new File("src/test/java/resources/desiredCapabilitiesEmulator.json"),Map.class);
        else if(env.equalsIgnoreCase("REAL"))
            desiredCapsMap= mapper.readValue(new File("src/test/java/resources/desiredCapabilities.json"),Map.class);
        else
            throw new IOException("Env: {env} does not exist");


        for (Map.Entry<String, Object> entry : desiredCapsMap.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            String methodName = "set" + key.substring(0, 1).toUpperCase() + key.substring(1);

            try {
                if (value instanceof Boolean) {
                    Method method = UiAutomator2Options.class.getMethod(methodName, boolean.class);
                    method.invoke(options, (Boolean) value);
                } else {
                    Method method = UiAutomator2Options.class.getMethod(methodName, value.getClass());
                    method.invoke(options, value);
                }
            } catch (Exception e) {
                System.out.println("Could not set capability: " + key + " -> " + e.getMessage());
            }
        }
        return options;
    }
}
