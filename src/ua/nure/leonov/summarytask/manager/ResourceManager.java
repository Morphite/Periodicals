package ua.nure.leonov.summarytask.manager;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * The Enum of ResourceManager which change resources by locales and get values by keys in properties
 */
public enum ResourceManager {

    /** The instance of ResourceManager */
    INSTANCE;

    /** Private resourceManager */
    private ResourceBundle resourceManager;

    /** Final ResourceBundle of text */
    private final String RESOURCE_NAME = "resources.text";

    ResourceManager() {
        resourceManager = ResourceBundle.getBundle(RESOURCE_NAME, Locale.getDefault());
    }

    /**
     * Method change text by locale
     * @param locale the locale of text
     */
    public void changeResource(Locale locale) {
        resourceManager = ResourceBundle.getBundle(RESOURCE_NAME, locale);
    }

    /**
     * Method return value by key (text in text.properties)
     * @param key the key of property
     * @return string value by key
     */
    public String getProperty(String key) {
        return resourceManager.getString(key);
    }
}
