package i18n;

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


    /**
     * The Resource Bundles.
     */
    private static ResourceBundle resourceBundleLogin;


    /**
     * This static block loads the Resource Bundles from the file system when
     * the class gets loaded.
     */
    static {
        resourceBundleLogin = ResourceBundle.getBundle(I18N_BASENAME_LOGIN);

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
}
