package VerteilterTerminKalender.view.menubar;

import VerteilterTerminKalender.MainApp;
import VerteilterTerminKalender.constants.FXConstants;
import VerteilterTerminKalender.view.interfaces.FXMLDialogController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sun.applet.Main;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for the "About"-Window
 */
public class AboutController implements FXMLDialogController, Initializable {

    private MainApp mainApp;
    private Stage dialogStage;

    @FXML
    private ImageView imageView;

    @Override
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @Override
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Called automatically after the FXML is Loaded. Places an image inside
     * the dialogStage
     * @param location not used
     * @param resources not used
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image image = new Image(FXConstants.PATH_ABOUT_IMAGE);
        imageView.setImage(image);
    }
}
