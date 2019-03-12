package VerteilterTerminKalender.view;

import VerteilterTerminKalender.MainApp;
import VerteilterTerminKalender.i18n.I18nUtil;
import VerteilterTerminKalender.constants.FXConstants;
import VerteilterTerminKalender.model.classes.UserImpl;
import VerteilterTerminKalender.model.interfaces.User;
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
 * This Class controls the Registration Window.
 *
 * @author Johannes Gerwert
 * @version 12.03.2019
 */
public class RegisterLayoutController {

    //A reference to the main program.
    private MainApp mainApp;

    @FXML
    private TextField registerEMailTextfield;
    @FXML
    private TextField registerNameTextfield;
    @FXML
    private TextField registerFirstNameTextfield;
    @FXML
    private PasswordField registerPasswordfield;
    @FXML
    private PasswordField registerPassConfirmField;


    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
    }

    /**
     * The Login-Window opens, when clicking on the 'Login'-Button.
     */
    @FXML
    public void handleReturnLogin(){
        mainApp.initLoginLayout();
    }

    /**
     * When clicking on the 'Register'-Button, the entered data is fetched.
     * With the fetched data a new User will be created on the server.
     * Then the Main window will be displayed.
     */
    @FXML
    public void handleRegister(){
        User currentUser;
        String email;
        String password = null;
        String name;
        String firstName;

        email = registerEMailTextfield.getText();
        name = registerNameTextfield.getText();
        firstName = registerFirstNameTextfield.getText();

        if( registerPasswordfield.getText().equals(registerPassConfirmField.getText()) ){
            password = registerPasswordfield.getText();
        }

        System.out.println(email);
        if(password != null){
            System.out.println(password);
        }else{
            System.out.println("Passwort ungültig");
        }
        System.out.println(name);
        System.out.println(firstName);


        mainApp.initRootLayout();
    }
}
