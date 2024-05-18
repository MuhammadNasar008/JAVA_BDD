package Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {

	private static final Properties config = new Properties();
	private static final String PROPERTIES_FILE_PATH = System.getProperty("user.dir") + "/src/test/resources/cucumber.properties";

	/**
	 * Reads properties from the specified properties file and loads them into the config object.
	 *
	 * @return the loaded properties
	 */
	public static Properties readProperties() {
		try (FileInputStream fis = new FileInputStream(PROPERTIES_FILE_PATH)) {
			config.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
			// log.warn(e.getMessage()); // Uncomment if logging is available
		}
		return config;
	}

	/**
	 * Retrieves the value of a specified property from the loaded properties.
	 *
	 * @param property the name of the property
	 * @return the value of the property, or null if not found or empty
	 */
	public static String getPropertyValue(String property) {
		String propertyValue = null;
		try {
			propertyValue = readProperties().getProperty(property);
			if (propertyValue == null || propertyValue.isEmpty()) {
				throw new NullPointerException("Property value is null or empty for: " + property);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// log.info(e.getMessage() + " for property: " + property); // Uncomment if logging is available
		}
		return propertyValue;
	}

}
