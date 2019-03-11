package VerteilterTerminKalender;

import VerteilterTerminKalender.view.LoginLayoutController;
import VerteilterTerminKalender.constants.FXConstants;
import VerteilterTerminKalender.i18n.I18nUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

/**
 * Main Program
 * Initialises the Graphical User Interface.
 *
 * The functionality of the UI-Elements is provided by their respective
 * Controllers and FXML-files.
 *
 * @author Johannes Gerwert
 * @version 11.03.2019
 */
public class MainApp extends Application {

    private Stage primaryStage;
    private AnchorPane loginPane;


    /**
     * Constructor
     * Does currently nothing.
     */
    public MainApp(){

    }

    /**
     * Starts the main program
     *
     * @param primaryStage The main window
     */
    @Override
    public void start(Stage primaryStage){
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle(FXConstants.APPLICATION_NAME);
        initLoginLayout();
    }

    /**
     * Initialises the Login window.
     */
    public void initLoginLayout(){
        try{
            FXMLLoader loader = new FXMLLoader();
            ResourceBundle bundle = I18nUtil.getLoginResourceBundle();
            loader.setLocation(MainApp.class
                .getResource(FXConstants.PATH_LOGIN_LAYOUT));
            loader.setResources(bundle);
            loginPane = loader.load();
            Scene scene = new Scene(loginPane);
            //new JMetro(JMetro.Style.LIGHT).applyTheme(scene);
            primaryStage.setScene(scene);
            LoginLayoutController controller = loader.getController();
            controller.setMainApp(this);

            primaryStage.show();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public Stage getPrimaryStage(){
        return this.primaryStage;
    }

    /**
     * Executes the launch-Method, important for JavaFX.
     * Should not be changed.
     * @param args unused
     */
    public static void main(String[] args){
        launch(args);
    }

}
