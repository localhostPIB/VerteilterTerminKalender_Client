package VerteilterTerminKalender.i18n;

import java.util.ResourceBundle;

/**
 * Provides internationalised Strings for usage in the Graphical User Interface.
 * Used Resource File: Components
 *
 * @author Johannes Gerwert
 * @version 11.03.2019
 */
public abstract class I18nComponentsUtil {

    private static final String APPLICATION_NAME = "application-name";


    private static ResourceBundle resourceBundle;

    static {
        resourceBundle = I18nUtil.getComponentsResourceBundle();
    }

    public static String getApplicationName(){
        return resourceBundle.getString(APPLICATION_NAME);
    }
}
