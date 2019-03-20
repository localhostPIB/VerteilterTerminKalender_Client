package VerteilterTerminKalender.view;

import VerteilterTerminKalender.MainApp;
import VerteilterTerminKalender.constants.FXConstants;
import VerteilterTerminKalender.i18n.I18nUtil;
import VerteilterTerminKalender.model.interfaces.EventFx;
import VerteilterTerminKalender.model.interfaces.EventInvite;
import VerteilterTerminKalender.util.FxUtil;
import VerteilterTerminKalender.view.interfaces.FXMLController;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

public class RootLayoutController implements FXMLController {

    private MainApp mainApp;

    private SimpleIntegerProperty monthProperty;

    private ObservableList<EventFx>  eventsOfDisplayedDate = FXCollections.observableArrayList();
    private EventFx displayedEvent;

    private ObservableList<EventInvite> invitations = FXCollections.observableArrayList();


    @FXML
    private TitledPane tpSelectedDate;

    @FXML
    private VBox vBoxDisplayedEvents;
    @FXML
    private VBox vBoxDisplayedInvitations;
    @FXML
    private VBox vBoxDisplayedEventParticipants;

    @FXML
    private GridPane gridPaneDisplayedDays;

    @FXML
    private Label labelDisplayedEventDescription;
    @FXML
    private Label labelDisplayedEventStartTime;
    @FXML
    private Label labelDisplayedEventEndTime;
    @FXML
    private Label labelDisplayedEventPlace;
    @FXML
    private Label labelDisplayedEventOwner;

    @FXML
    private Label labelMonthAndYear;

    @Override
    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
    }

    public void setup(){
        monthProperty = mainApp.getDisplayedMonthProperty();
        addMonthChangeListener();

        invitations = mainApp.getEventInvitesList();
        addInviteListener();

        addDisplayedEventsListener();

        populateCalendar();
        populateInvitations();
        showEventsOfDisplayedDate();
    }

    private void populateInvitations(){
        for(EventInvite invite : invitations){
            addInvitation(invite);
        }
    }

    private void addInvitation(EventInvite invite){
        try{
            FXMLLoader loader = new FXMLLoader();
            ResourceBundle bundle = I18nUtil.getComponentsResourceBundle();
            loader.setLocation(MainApp.class
                    .getResource(FXConstants.PATH_INVITE_OVERVIEW));
            loader.setResources(bundle);
            AnchorPane inviteOverviewAnchorPane = loader.load();

            vBoxDisplayedInvitations.getChildren().add(inviteOverviewAnchorPane);
            vBoxDisplayedInvitations.getProperties().put(invite.getInviteId(), inviteOverviewAnchorPane);

            InviteOverviewController controller = loader.getController();
            controller.setMainApp(mainApp);
            controller.setup(invite);

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    private void populateCalendar(){
        // fetches the original calendar
        GregorianCalendar displayedDate = mainApp.getDisplayedDate();

        int month = displayedDate.get(GregorianCalendar.MONTH) + 1;

        int year = displayedDate.get(GregorianCalendar.YEAR);

        String monthAndYear = Integer.toString(month)
                + "." + Integer.toString(year);

        labelMonthAndYear.setText(monthAndYear);


        // creates a copy of the calendar that will be modified
        GregorianCalendar operatedCalendar = (GregorianCalendar) displayedDate.clone();
        operatedCalendar.setLenient(true);
        operatedCalendar.set(GregorianCalendar.DAY_OF_MONTH, 1);

        /*
         * Modifies the offset used by this calendar based on the day of week
         * of the first day of the month.
         *
         * Example: If the first day of the month is a Wednesday, this calendar will
         * still display the tuesday and monday of the previous month, so we will need
         * to go 2 days back in time.
         */
        int dayOfWeekOffset = operatedCalendar.get(GregorianCalendar.DAY_OF_WEEK) - 2;
        if(dayOfWeekOffset <= 0){
            dayOfWeekOffset += 7;
        }
        dayOfWeekOffset = -dayOfWeekOffset;
        operatedCalendar.add(GregorianCalendar.DAY_OF_MONTH, dayOfWeekOffset);

        int gridPaneSize = gridPaneDisplayedDays.getChildren().size();
        if(gridPaneSize > 6){
            gridPaneDisplayedDays.getChildren().remove(7, gridPaneSize);
        }
        // creates the days in the grid pane
        for(int y = 1; y < 7; y++){
            for(int x = 0; x < 7; x++){
                try {
                    FXMLLoader loader = new FXMLLoader();
                    ResourceBundle bundle = I18nUtil.getComponentsResourceBundle();
                    loader.setLocation(MainApp.class
                            .getResource(FXConstants.PATH_DAY_OVERVIEW));
                    loader.setResources(bundle);
                    BorderPane dayOverviewBorderPane = loader.load();

                    GridPane.setConstraints(dayOverviewBorderPane, x, y);
                    gridPaneDisplayedDays.getChildren().add(dayOverviewBorderPane);

                    DayOverviewController controller = loader.getController();
                    controller.setMainApp(mainApp);

                    int displayDayOfMonth = operatedCalendar.get(GregorianCalendar.DAY_OF_MONTH);
                    controller.setup(operatedCalendar);
                    operatedCalendar.add(GregorianCalendar.DAY_OF_MONTH, 1);

                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    private void addDisplayedEvent(EventFx eventFX){
        try {
            FXMLLoader loader = new FXMLLoader();
            ResourceBundle bundle = I18nUtil.getComponentsResourceBundle();
            loader.setLocation(MainApp.class
                    .getResource(FXConstants.PATH_EVENT_OVERVIEW));
            loader.setResources(bundle);
            BorderPane eventOverviewBorderPane = loader.load();

            vBoxDisplayedEvents.getChildren().add(eventOverviewBorderPane);
            vBoxDisplayedEvents.getProperties().put(eventFX.getEventId(), eventOverviewBorderPane);

            EventOverviewController controller = loader.getController();
            controller.setMainApp(mainApp);
            controller.setup(eventFX);

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    private void showEventsOfDisplayedDate(){
        vBoxDisplayedEvents.getChildren().clear();
        vBoxDisplayedEvents.getProperties().clear();
        for(EventFx eventFx : eventsOfDisplayedDate){
            addDisplayedEvent(eventFx);
        }
    }

    public void assignEventsOfDisplayedDate(ObservableList<EventFx> newList) {
        this.eventsOfDisplayedDate.clear();
        this.eventsOfDisplayedDate.addAll(newList);
        showEventsOfDisplayedDate();
    }

    public void addEventOfDisplayedDate(EventFx eventFx){
        this.eventsOfDisplayedDate.add(eventFx);
    }

    public void removeEventOfDisplayedDate(EventFx eventFx){
        this.eventsOfDisplayedDate.remove(eventFx);
    }

    private void addDisplayedEventsListener(){
        eventsOfDisplayedDate.addListener(new ListChangeListener<EventFx>() {
            @Override
            public void onChanged(Change<? extends EventFx> c) {
                while(c.next()){
                    if(c.wasAdded()){
                        for(EventFx eventFx : c.getAddedSubList()){
                            addDisplayedEvent(eventFx);
                        }
                        //showEventsOfDisplayedDate();
                    }

                    if(c.wasRemoved()){
                        for(EventFx eventFx : c.getRemoved()){
                            Node node = (Node) vBoxDisplayedEvents.getProperties().get(eventFx.getEventId());
                            vBoxDisplayedEvents.getChildren().remove(node);
                            vBoxDisplayedEvents.getProperties().remove(eventFx.getEventId());
                        }
                    }

                    if(c.wasPermutated()){
                        showEventsOfDisplayedDate();
                    }
                }
            }
        });
    }

    /**
     * Changes the displayed Date to the first day of the previous month.
     */
    @FXML
    private void handlePreviousMonth(){
        GregorianCalendar date = mainApp.getDisplayedDate();
        date.add(GregorianCalendar.MONTH, -1);
        date.set(GregorianCalendar.DAY_OF_MONTH, 1);

        int tempMonth = date.get(GregorianCalendar.MONTH);
        monthProperty.setValue(tempMonth);
    }

    /**
     * Changes the displayed Date to the first day of the next month.
     */
    @FXML
    private void handleNextMonth(){
        GregorianCalendar date = mainApp.getDisplayedDate();
        date.add(GregorianCalendar.MONTH, 1);
        date.set(GregorianCalendar.DAY_OF_MONTH, 1);

        int tempMonth = date.get(GregorianCalendar.MONTH);
        monthProperty.setValue(tempMonth);
    }


    private void addMonthChangeListener(){
        monthProperty.addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                populateCalendar();
            }
        });
    }

    private void addInviteListener(){
        invitations.addListener(new ListChangeListener<EventInvite>() {
            @Override
            public void onChanged(Change<? extends EventInvite> c) {
                while(c.next()){
                    if(c.wasAdded()){
                        for(EventInvite invite : c.getAddedSubList()){
                            addInvitation(invite);
                        }
                    }

                    if(c.wasRemoved()){
                        for(EventInvite invite : c.getRemoved()){
                             Node node = (Node) vBoxDisplayedInvitations.getProperties().get(invite.getInviteId());
                             vBoxDisplayedInvitations.getChildren().remove(node);
                             vBoxDisplayedInvitations.getProperties().remove(invite.getInviteId());
                        }
                    }

                    if(c.wasPermutated()){
                        vBoxDisplayedInvitations.getChildren().clear();
                        vBoxDisplayedInvitations.getProperties().clear();
                        populateInvitations();
                    }
                }
            }
        });
    }


    /**
     * Called by clicking on "Account->Logout" inside the menubar.
     * Logs the user out and shows the login-window
     */
    @FXML
    private void handleLogout(){
        this.mainApp.setUser(null);
        this.mainApp.initLoginLayout();
    }

    /**
     * Called by clicking on "Event->New" inside the menubar.
     * Opens a window where a new event can be created
     */
    @FXML
    private void handleNewEvent(){
        FxUtil.showStage(this.mainApp, I18nUtil.getDialogResourceBundle(), FXConstants.PATH_CREATE_EVENT);
    }

    /**
     * Called by clicking on "Event->Change" inside the menubar.
     * Opens a window where an existing event can be changed
     */
    @FXML
    private void handleChangeEvent(){
        FxUtil.showStage(this.mainApp, I18nUtil.getDialogResourceBundle(), FXConstants.PATH_CHANGE_EVENT);
    }

    /**
     * Called by clicking on "Event->Delete" inside the menubar.
     * Opens a window where an existing event can be deleted
     */
    @FXML
    private void handleDeleteEvent(){
        FxUtil.showStage(this.mainApp, I18nUtil.getDialogResourceBundle(), FXConstants.PATH_DELETE_EVENT);
    }

    /**
     * Called by clicking on "Invitation->New" inside the menubar.
     * Opens a window where a new invitation can be created
     */
    @FXML
    private void handleNewInvitation(){
        FxUtil.showStage(this.mainApp, I18nUtil.getDialogResourceBundle(), FXConstants.PATH_CREATE_INVITATION);
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleClose(){
        Platform.exit();
        System.exit(0);
    }

    /**
     * Opens new "About"-Window
     */
    @FXML
    private void handleAbout(){
        FxUtil.showStage(this.mainApp,I18nUtil.getDialogResourceBundle(), FXConstants.PATH_ABOUT);
    }

    /**
     * Shows new window where the status of a sent invitation can be seen
     */
    @FXML
    private void handleBtnCheckSentInvitation(){
        FxUtil.showStage(this.mainApp,I18nUtil.getDialogResourceBundle(), FXConstants.PATH_CHECK_SENT_INVITE);

    }

    /**
     * Show new window where the status of received invitations can be seen
     */
    @FXML
    private void handleBtnCheckReceivedInvitation(){
        FxUtil.showStage(this.mainApp,I18nUtil.getDialogResourceBundle(), FXConstants.PATH_CHECK_RECEIVED_INVITE);

    }

}
