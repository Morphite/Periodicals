package ua.nure.leonov.summarytask.manager;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * The Enum of MessageManager which change resources by locales and get values by keys in properties
 */
public enum MessageManager {

    /** The instance of MessageManager */
    INSTANCE;

    /** Private messageManager */
    private ResourceBundle messageManager;

    /** Final ResourceBundle of messages */
    private final String RESOURCE_NAME = "resources.messages";

    MessageManager() {
        messageManager = ResourceBundle.getBundle(RESOURCE_NAME, Locale.getDefault());
    }

    /**
     * Method change messages by locale
     * @param locale the locale of message
     */
    public void changeResource(Locale locale){
        messageManager = ResourceBundle.getBundle(RESOURCE_NAME, locale);
    }

    /**
     * Method return value by key (message in messages.properties)
     * @param key the key of property
     * @return string value by key
     */
    public String getProperty(String key){
        return messageManager.getString(key);
    }
}
