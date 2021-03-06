package VerteilterTerminKalender.view;

import VerteilterTerminKalender.MainApp;
import VerteilterTerminKalender.builders.ServiceObjectBuilder;
import VerteilterTerminKalender.model.classes.UserImpl;
import VerteilterTerminKalender.model.interfaces.User;
import VerteilterTerminKalender.service.classes.EventServiceImpl;
import VerteilterTerminKalender.service.classes.UserServiceImpl;
import VerteilterTerminKalender.service.interfaces.EventService;
import VerteilterTerminKalender.service.interfaces.UserService;
import VerteilterTerminKalender.util.FxUtil;
import VerteilterTerminKalender.util.Sync;
import VerteilterTerminKalender.validators.StringValidator;
import VerteilterTerminKalender.view.interfaces.FXMLController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import static VerteilterTerminKalender.validators.RegisterValidator.hasEnoughCharacters;
import static VerteilterTerminKalender.validators.RegisterValidator.isEmail;

/**
 * This Class controls the Registration Window.
 *
 * @author Johannes Gerwert
 * @author Michelle Blau
 * @version 12.03.2019
 */
public class RegisterLayoutController implements FXMLController {

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

    @FXML
    private Label registerEmailErrorLabel;
    @FXML
    private Label registerEmailAlreadyInUseErrorLabel;
    @FXML
    private Label registerPasswordLengthErrorLabel;
    @FXML
    private Label registerPasswordErrorLabel;
    @FXML
    private Label registerNameErrorLabel;
    @FXML
    private Label registerFirstNameErrorLabel;


    private UserService userService = ServiceObjectBuilder.getUserService();

    @Override
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
        User tmpUser = new UserImpl();
        String email        = registerEMailTextfield.getText();
        String name         = registerNameTextfield.getText();
        String firstName    = registerFirstNameTextfield.getText();
        String password     = registerPasswordfield.getText();
        String passConfirm  = registerPassConfirmField.getText();

        tmpUser.setEmail(email);
        tmpUser.setName(firstName);
        tmpUser.setLastName(name);
        tmpUser.setPassword(password);

        if(validateInput(tmpUser, passConfirm)){
            userService.createUser(tmpUser); //Create new user in Database
            User newUser = userService.getUserByEmail(tmpUser.getEmail()); //retrieve new User by passing the email
            mainApp.setUser(newUser);

            Sync.initiateConnection(mainApp, newUser.getUserId() );

            mainApp.initRootLayout();
        }
    }


    /**
     * Checks if user input is incorrect and shows error messages
     * if this is the case
     * @param user contains the user-input inside its attributes
     * @param passConfirm second input of the password for conformation
     * @return true if user-input is correct, else false
     */
    private boolean validateInput(User user, String passConfirm) {
        boolean result = true;

        if(!isEmail(user.getEmail())){
            FxUtil.showErrorLabel(registerEmailErrorLabel);
            result = false;
        }else{registerEmailErrorLabel.setVisible(false);}

        if(userService.isUserExistingByEmail(user.getEmail())){
            FxUtil.showErrorLabel(registerEmailAlreadyInUseErrorLabel);
            result = false;
        }else{registerEmailAlreadyInUseErrorLabel.setVisible(false);}

        if(!user.getPassword().equals(passConfirm)){
            FxUtil.showErrorLabel(registerPasswordErrorLabel);
            result = false;
        }else{registerPasswordErrorLabel.setVisible(false);}

        if(!hasEnoughCharacters(user.getPassword())){
            FxUtil.showErrorLabel(registerPasswordLengthErrorLabel);
            result = false;
        }else{registerPasswordLengthErrorLabel.setVisible(false);}

        if(!StringValidator.isNotStringEmptyOrNull(user.getLastName())){
            FxUtil.showErrorLabel(registerNameErrorLabel);
            result = false;
        }else{registerNameErrorLabel.setVisible(false);}

        if(!StringValidator.isNotStringEmptyOrNull(user.getName())){
            FxUtil.showErrorLabel(registerFirstNameErrorLabel);
            result = false;
        }else{registerFirstNameErrorLabel.setVisible(false);}

        return result;
    }
}
