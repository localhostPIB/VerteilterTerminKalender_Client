package VerteilterTerminKalender.i18n;

import java.util.ResourceBundle;

/**
 * Provides internationalised Strings for usage in the Login and Registration windows.
 * Used Resource File: Login
 *
 * @author Johannes Gerwert
 * @version 11.03.2019
 */
public abstract class I18nLoginUtil {

    private static final String LOGIN_E_MAIL = "login-e-mail";
    private static final String LOGIN_PASSWORD = "login-password";
    private static final String LOGIN_BUTTON = "login-button";
    private static final String LOGIN_REGISTER_BUTTON = "login-register-button";

    private static final String REGISTER_E_MAIL = "register-e-mail";
    private static final String REGISTER_NAME = "register-name";
    private static final String REGISTER_FIRST_NAME = "register-first-name";
    private static final String REGISTER_PASSWORD = "register-password";
    private static final String REGISTER_PASS_CONFIRM = "register-pass-confirm";
    private static final String REGISTER_RETURN_LOGIN_BUTTON = "register-return-login-button";
    private static final String REGISTER_BUTTON = "register-button";

    private static ResourceBundle resourceBundle;

    static {
        resourceBundle = I18nUtil.getLoginResourceBundle();
    }

    public static String getLoginEMail(){
        return resourceBundle.getString(LOGIN_E_MAIL);
    }

    public static String getLoginPassword(){
        return resourceBundle.getString(LOGIN_PASSWORD);
    }

    public static String getLoginButton(){
        return resourceBundle.getString(LOGIN_BUTTON);
    }

    public static String getLoginRegisterButton(){
        return resourceBundle.getString(LOGIN_REGISTER_BUTTON);
    }

    public static String getRegisterEMail(){
        return resourceBundle.getString(REGISTER_E_MAIL);
    }

    public static String getRegisterName(){
        return resourceBundle.getString(REGISTER_NAME);
    }

    public static String getRegisterFirstName(){
        return resourceBundle.getString(REGISTER_FIRST_NAME);
    }

    public static String getRegisterPassword(){
        return resourceBundle.getString(REGISTER_PASSWORD);
    }

    public static String getRegisterPassConfirm(){
        return resourceBundle.getString(REGISTER_PASS_CONFIRM);
    }

    public static String getRegisterReturnLoginButton() {
        return resourceBundle.getString(REGISTER_RETURN_LOGIN_BUTTON);
    }

    public static String getRegisterButton(){
        return resourceBundle.getString(REGISTER_BUTTON);
    }

}