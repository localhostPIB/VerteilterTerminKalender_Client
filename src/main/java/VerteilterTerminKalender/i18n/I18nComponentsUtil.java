package VerteilterTerminKalender.i18n;

import java.util.ResourceBundle;

/**
 * Provides internationalised Strings for usage in the Graphical User Interface.
 * Used Resource File: Components
 *
 * @author Johannes Gerwert
 * @version 12.03.2019
 */
public abstract class I18nComponentsUtil {

    private static final String APPLICATION_NAME = "application-name";

    private static final String MENU_FILE = "menu-file";
    private static final String MENU_EDIT = "menu-edit";
    private static final String MENU_HELP = "menu-help";

    private static final String LABEL_MONDAY = "label-monday";


    private static ResourceBundle resourceBundle;

    static {
        resourceBundle = I18nUtil.getComponentsResourceBundle();
    }


    public static String getApplicationName(){
        return resourceBundle.getString(APPLICATION_NAME);
    }



    public static String getMenuFile(){
        return resourceBundle.getString(MENU_FILE);
    }

    public static String getMenuEdit(){
        return resourceBundle.getString(MENU_EDIT);
    }

    public static String getMenuHelp(){
        return resourceBundle.getString(MENU_HELP);
    }



    public static String getLabelMonday(){
        return resourceBundle.getString(LABEL_MONDAY);
    }
}
