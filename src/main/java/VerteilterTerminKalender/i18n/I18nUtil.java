package VerteilterTerminKalender.i18n;

import java.util.ResourceBundle;

/**
 * Bundles the Access-Points of the Resource Bundles to provide easy access.
 *
 * @author Johannes Gerwert
 *
 * @version 11.03.2019
 *
 */
public class I18nUtil {

    /**
     * The base names of the Resource Bundles.
     */
    private static final String I18N_BASENAME_LOGIN = "i18n.login";
    private static final String I18N_BASENAME_COMPONENTS = "i18n.components";


    /**
     * The Resource Bundles.
     */
    private static ResourceBundle resourceBundleLogin;
    private static ResourceBundle resourceBundleComponents;



    //This static block loads the Resource Bundles from the file system when the class gets loaded.
    static {
        resourceBundleLogin = ResourceBundle.getBundle(I18N_BASENAME_LOGIN);

        resourceBundleComponents = ResourceBundle.getBundle(I18N_BASENAME_COMPONENTS);

    }

    /**
     * Enables access to the Resource Bundles needed for the Login and
     * Registration windows.
     *
     * @return The Login Resource Bundle.
     */
    public static ResourceBundle getLoginResourceBundle(){
        return resourceBundleLogin;
    }

    /**
     * Enables access to the Resource Bundles needed for the Graphical User Interface.
     *
     * @return The Components Resource Bundle.
     */
    public static ResourceBundle getComponentsResourceBundle() {
        return resourceBundleComponents;
    }
}