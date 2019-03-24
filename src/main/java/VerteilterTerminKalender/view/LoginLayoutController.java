package VerteilterTerminKalender.view;


import VerteilterTerminKalender.MainApp;
import VerteilterTerminKalender.builders.ServiceObjectBuilder;
import VerteilterTerminKalender.constants.FXConstants;
import VerteilterTerminKalender.i18n.I18nUtil;
import VerteilterTerminKalender.model.interfaces.User;
import VerteilterTerminKalender.service.classes.EventServiceImpl;
import VerteilterTerminKalender.service.classes.UserServiceImpl;
import VerteilterTerminKalender.service.interfaces.EventService;
import VerteilterTerminKalender.service.interfaces.UserService;
import VerteilterTerminKalender.util.FxUtil;
import VerteilterTerminKalender.util.Sync;
import VerteilterTerminKalender.validators.RegisterValidator;
import VerteilterTerminKalender.view.interfaces.FXMLController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * This Class controls the Login window.
 *
 * @author Michelle Blau
 * @author Johannes Gerwert
 */
public class LoginLayoutController implements FXMLController {

     //A reference to the main program.
    private MainApp mainApp;

    private UserService userService = ServiceObjectBuilder.getUserService();

    @FXML
    private TextField loginEMailTextfield;
    @FXML
    private PasswordField loginPasswordField;

    @FXML
    private Label emailErrorLabel;
    @FXML
    private Label userNoExistErrorLabel;
    @FXML
    private Label passwordErrorLabel;



    @Override
    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
    }

    /**
     * The Registration-Window opens, when clicking on the 'Register'-Button
     */
    @FXML
    private void handleOpenRegistration(){
        FxUtil.showScene(this.mainApp, I18nUtil.getLoginResourceBundle(), FXConstants.PATH_REGISTER_LAYOUT);
    }

    /**
     * The Userinput is fetched from the GUI.
     * If the entered Login data matches the User data, the matching User is set
     * as the User. Then the main window will be displayed.
     */
    @FXML
    private void handleLogin(){

        String enteredEmail = loginEMailTextfield.getText();
        if(validateInput()) {
            User loginUser = userService.getUserByEmail(enteredEmail);

            if(validateUserLogin(loginUser)) {

                mainApp.setUser(loginUser);
                Sync.initiateConnection(mainApp, loginUser.getUserId());

                mainApp.initRootLayout();
            }
        }
    }

    /**
     * Validates if the correct password is entered
     * if incorrect input is made, error messages are shown
     * @param loginUser existing user in database
     * @return true if password is correct, else false
     */
    private boolean validateUserLogin(User loginUser) {
        boolean result = true;

        if(!userService.verifyUser(loginUser, loginPasswordField.getText())){
            FxUtil.showErrorLabel(passwordErrorLabel);
            result = false;
        }else{passwordErrorLabel.setVisible(false);}

        return result;
    }

    /**
     * Validates user-Input. If incorrect input is made, error messages are shown
     * @return true, if input is correct, else false
     */
    private boolean validateInput() {
        boolean result = true;

        if(!userService.isUserExistingByEmail(loginEMailTextfield.getText())){
            FxUtil.showErrorLabel(userNoExistErrorLabel);
            result = false;
        }else{userNoExistErrorLabel.setVisible(false);}

        if(!RegisterValidator.isEmail(loginEMailTextfield.getText())){
            FxUtil.showErrorLabel(emailErrorLabel);
            result = false;
        }else{emailErrorLabel.setVisible(false);}

        return result;
    }
}
