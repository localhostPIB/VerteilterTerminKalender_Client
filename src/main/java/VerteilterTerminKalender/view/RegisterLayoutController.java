package VerteilterTerminKalender.view;

import VerteilterTerminKalender.MainApp;
import VerteilterTerminKalender.i18n.I18nUtil;
import constants.FXConstants;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import jfxtras.styles.jmetro8.JMetro;

import java.io.IOException;
import java.util.ResourceBundle;

public class RegisterLayoutController {

    private MainApp mainApp;

    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
    }

    @FXML
    public void handleReturnLogin(){
        try {
            Stage primaryStage = mainApp.getPrimaryStage();

            FXMLLoader loader = new FXMLLoader();
            ResourceBundle bundle = I18nUtil.getLoginResourceBundle();
            loader.setLocation(MainApp.class
                    .getResource(FXConstants.PATH_LOGIN_LAYOUT));
            loader.setResources(bundle);
            AnchorPane loginPane = loader.load();
            Scene scene = new Scene(loginPane);
            //new JMetro(JMetro.Style.LIGHT).applyTheme(scene);
            primaryStage.setScene(scene);
            LoginLayoutController controller = loader.getController();
            controller.setMainApp(mainApp);

            primaryStage.show();

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
