package ua.nure.leonov.summarytask.manager;

import java.util.ResourceBundle;

/**
 * The Class of ConfigurationManager which getting paths from config.properties
 */
public class ConfigurationManager {

    /** The final ResourceBundle (config.properties) */
    private final static ResourceBundle resourseBundle = ResourceBundle.getBundle("resources.config");

    private ConfigurationManager() {}

    /**
     * Method return value by key (path in config)
     * @param key the key of property
     * @return string value by key
     */
    public static String getProperty(String key){
        return resourseBundle.getString(key);
    }
}
