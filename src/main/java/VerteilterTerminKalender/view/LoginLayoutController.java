package VerteilterTerminKalender.view;



import VerteilterTerminKalender.MainApp;
import VerteilterTerminKalender.i18n.I18nUtil;
import VerteilterTerminKalender.constants.FXConstants;
import VerteilterTerminKalender.model.interfaces.User;
import VerteilterTerminKalender.service.classes.EventServiceImpl;
import VerteilterTerminKalender.service.classes.UserServiceImpl;
import VerteilterTerminKalender.service.interfaces.EventService;
import VerteilterTerminKalender.service.interfaces.UserService;
import VerteilterTerminKalender.util.FxUtil;
import VerteilterTerminKalender.validators.RegisterValidator;
import VerteilterTerminKalender.validators.StringValidator;
import VerteilterTerminKalender.view.interfaces.FXMLController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

/**
 * This Class controls the Login window.
 *
 * @author Johannes Gerwert
 * @author Michelle Blau
 * @version 12.03.2019
 */
public class LoginLayoutController implements FXMLController {


     //A reference to the main program.
    private MainApp mainApp;

    private UserService userService = new UserServiceImpl();
    private EventService eventService = new EventServiceImpl();

    @FXML
    private TextField loginEMailTextfield;
    @FXML
    private PasswordField loginPasswordField;

    //private UserService userService =

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
     * TODO serverseitig eingegebene daten kontrollieren? wenn daten korrekt, dann termine des aktuellen monats an den client schicken?
     */
    @FXML
    private void handleLogin(){
//        if(validateInput()) {

            User currentUser;
            String enteredEmail = loginEMailTextfield.getText();
            String enteredPassword = loginPasswordField.getText();





            System.out.println(enteredEmail);
            System.out.println(enteredPassword);

            mainApp.initRootLayout();
//        }
    }

    private boolean validateInput() {
        boolean result = true;

//        if(!RegisterValidator.userExists(loginEMailTextfield.getText())){
//
//            result = false;
//        }else{}

        if(RegisterValidator.isEmail(loginEMailTextfield.getText())){

            result = false;
        }else{;}

        if(StringValidator.isStringEmptyOrNull(loginPasswordField.getText())){

            result = false;
        }else{;}



        return result;
    }
}
