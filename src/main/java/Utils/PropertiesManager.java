package Utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesManager extends Utilities {

    private Properties properties;
    private String propertiesFilePath;

    /**
     * Constructor to initialize the PropertiesManager with a specified properties file path.
     *
     * @param propertiesFilePath the path to the properties file
     */
    public PropertiesManager(String propertiesFilePath) {
        this.propertiesFilePath = propertiesFilePath;
        properties = new Properties();
        loadProperties();
    }

    /**
     * Loads properties from the specified properties file.
     */
    private void loadProperties() {
        try (FileInputStream fileInputStream = new FileInputStream(propertiesFilePath)) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets a property with the given key and value and saves it to the properties file.
     *
     * @param key   the key of the property
     * @param value the value of the property
     */
    public void setProperty(String key, String value) {
        properties.setProperty(key, value);
        saveProperties();
    }

    /**
     * Saves the properties to the properties file.
     */
    private void saveProperties() {
        try (FileOutputStream fileOutputStream = new FileOutputStream(propertiesFilePath)) {
            properties.store(fileOutputStream, "Updated properties");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves the value of a property with the given key.
     *
     * @param key the key of the property
     * @return the value of the property
     */
    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    /**
     * Main method for testing the PropertiesManager class.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        String propertiesFilePath = "your-properties-file.properties";
        PropertiesManager propertiesManager = new PropertiesManager(propertiesFilePath);

        // Set a new value for a key
        propertiesManager.setProperty("keyName", "desiredValue");

        // Get the value of a key
        String value = propertiesManager.getProperty("keyName");
        System.out.println("Value for keyName: " + value);
    }
}
