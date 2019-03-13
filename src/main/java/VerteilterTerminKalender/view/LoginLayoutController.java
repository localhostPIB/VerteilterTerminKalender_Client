package VerteilterTerminKalender.view;



import VerteilterTerminKalender.MainApp;
import VerteilterTerminKalender.i18n.I18nUtil;
import VerteilterTerminKalender.constants.FXConstants;
import VerteilterTerminKalender.model.interfaces.User;
import VerteilterTerminKalender.service.interfaces.UserService;
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
public class LoginLayoutController {


     //A reference to the main program.
    private MainApp mainApp;

    @FXML
    private TextField loginEMailTextfield;
    @FXML
    private PasswordField loginPasswordField;

    //private UserService userService =

    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
    }

    /**
     * The Registration-Window opens, when clicking on the 'Register'-Button
     */
    @FXML
    private void handleOpenRegistration(){
        try {
            Stage primaryStage = mainApp.getPrimaryStage();

            FXMLLoader loader = new FXMLLoader();
            ResourceBundle bundle = I18nUtil.getLoginResourceBundle();
            loader.setLocation(MainApp.class
                    .getResource(FXConstants.PATH_REGISTER_LAYOUT));
            loader.setResources(bundle);
            AnchorPane registerPane = loader.load();
            Scene scene = new Scene(registerPane);
            primaryStage.setScene(scene);
            RegisterLayoutController controller = loader.getController();
            controller.setMainApp(mainApp);

            primaryStage.show();

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * The Userinput is fetched from the GUI.
     * If the entered Login data matches the User data, the matching User is set
     * as the User. Then the main window will be displayed.
     */
    @FXML
    private void handleLogin(){
        User currentUser;
        String enteredEmail;
        String enteredPassword;

        enteredEmail = loginEMailTextfield.getText();

        //currentUser =
        enteredPassword = loginPasswordField.getText();

        System.out.println(enteredEmail);
        System.out.println(enteredPassword);

        if(true){
            mainApp.initRootLayout();
        }
    }
}
