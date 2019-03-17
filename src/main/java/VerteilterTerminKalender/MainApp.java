package VerteilterTerminKalender;

import VerteilterTerminKalender.model.interfaces.*;
import VerteilterTerminKalender.util.FxUtil;
import VerteilterTerminKalender.view.LoginLayoutController;
import VerteilterTerminKalender.constants.FXConstants;
import VerteilterTerminKalender.i18n.I18nUtil;
import VerteilterTerminKalender.view.RootLayoutController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import sun.applet.Main;

import java.awt.*;
import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

/**
 * Main Program
 * Initialises the Graphical User Interface.
 *
 * The functionality of the UI-Elements is provided by their respective
 * Controllers and FXML-files.
 *
 * @author Johannes Gerwert
 * @author Michelle Blau
 * @version 12.03.2019
 */
public class MainApp extends Application {
    static MainApp app;

    private Stage primaryStage;
    private AnchorPane loginAnchorPane;
    private BorderPane rootBorderPane;

    private User user;
    private GregorianCalendar displayedDate;

    @Setter private ObservableList<EventFx> eventFXList = FXCollections.observableArrayList();
    @Setter @Getter private  ObservableList<EventInvite> eventInvitesList = FXCollections.observableArrayList();
    @Setter private ObservableList<EventDecline> eventDeclinesList = FXCollections.observableArrayList();
    @Setter private ObservableList<EventParticipate> eventParticipatesList = FXCollections.observableArrayList();



    /**
     * Constructor
     * Does currently nothing.
     */
    public MainApp(){
        app = this;
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
        this.primaryStage.getIcons().add(new Image(FXConstants.PATH_APPLICATION_IMAGE));

        // initialize Calendar with current date
        displayedDate = new GregorianCalendar();

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
            loginAnchorPane = loader.load();
            Scene scene = new Scene(loginAnchorPane);
            primaryStage.setScene(scene);
            LoginLayoutController controller = loader.getController();
            controller.setMainApp(this);

            FxUtil.putStageInCenterOfScreen(primaryStage);

            primaryStage.show();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Initialises the Main window.
     */
    public void initRootLayout(){
        try{
            FXMLLoader loader = new FXMLLoader();
            ResourceBundle bundle = I18nUtil.getComponentsResourceBundle();
            loader.setLocation(MainApp.class
                    .getResource(FXConstants.PATH_ROOT_LAYOUT));
            loader.setResources(bundle);
            rootBorderPane = loader.load();
            Scene scene = new Scene(rootBorderPane);
            primaryStage.setScene(scene);
            RootLayoutController controller = loader.getController();
            controller.setMainApp(this);
            controller.setup();

            primaryStage.setMaximized(true); //Todo: maximiert automatisch terminansicht
            primaryStage.show();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public Stage getPrimaryStage(){
        return this.primaryStage;
    }

    public User getUser(){
        return this.user;
    }

    public void setUser(User newUser){
        this.user = newUser;
    }

    public ObservableList<EventFx> getEventFXList(){
        return this.eventFXList;
    }

    public void setEventFXList(ObservableList<EventFx> newList){
        this.eventFXList = newList;
    }

    public GregorianCalendar getDisplayedDate(){
        return this.displayedDate;
    }

    public void setDisplayedDate(GregorianCalendar calendar){
        this.displayedDate = calendar;
    }

    public static MainApp getMainApp(){
        return app;
    }

    public void setEventInvites(EventInvite eventInvite){
        eventInvitesList.add(eventInvite);

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
