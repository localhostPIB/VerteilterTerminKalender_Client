package VerteilterTerminKalender.view;



import VerteilterTerminKalender.MainApp;
import VerteilterTerminKalender.i18n.I18nUtil;
import VerteilterTerminKalender.constants.FXConstants;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

/**
 * This Class controls the Login window.
 *
 * @author Johannes Gerwert
 * @version 11.03.2019
 */
public class LoginLayoutController {

    /**
     * A reference to the main program.
     */
    private MainApp mainApp;

    @FXML
    private TextField loginEMailTextfield;
    @FXML
    private TextField loginPasswordField;

    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
    }

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
            //new JMetro(JMetro.Style.LIGHT).applyTheme(scene);
            primaryStage.setScene(scene);
            RegisterLayoutController controller = loader.getController();
            controller.setMainApp(mainApp);

            primaryStage.show();

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
