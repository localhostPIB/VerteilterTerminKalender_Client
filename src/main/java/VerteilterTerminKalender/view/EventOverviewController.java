package VerteilterTerminKalender.view;

import VerteilterTerminKalender.MainApp;
import VerteilterTerminKalender.model.interfaces.EventFx;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.time.LocalDateTime;

/**
 * This class controls the JavaFX-elements that display the events.
 * @author Johannes Gerwert
 * @version 20.03.2019
 */
public class EventOverviewController {

    private MainApp mainApp;
    private RootLayoutController rootLayoutController;

    private EventFx assignedEvent;

    @FXML
    private Label labelEventDetails;

    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
        this.rootLayoutController = mainApp.getRootLayoutController();
    }

    /**
     * Important initializations are made. Should be called right after setting the mainApp when creating
     * the controller.
     *
     * The displayed event is assigned. Then the text describing the event is set.
     *
     * @param event
     */
    public void setup(EventFx event){
        this.assignedEvent = event;
        labelEventDetails.setText(assignedEvent.toString());
    }

    /**
     * This method will be called when the User clicks on an event.
     * The details of the event are then displayed in the detail-view.
     */
    @FXML
    private void handleEventSelected(){
        LocalDateTime endTime = assignedEvent.getEndTime().getValue();
        String endTimeString = endTime.getDayOfMonth() + "." + endTime.getMonthValue() + "."
                + endTime.getYear() + " " + endTime.getHour() + ":" + endTime.getMinute();

        LocalDateTime startTime = assignedEvent.getStartTime().getValue();
        String startTimeString = startTime.getDayOfMonth() + "." + startTime.getMonthValue() + "."
                + startTime.getYear() + " " + startTime.getHour() + ":" + endTime.getMinute();

        rootLayoutController.setLabelDisplayedEventDescription(assignedEvent.getNote().getValueSafe());
        rootLayoutController.setLabelDisplayedEventEndTime(endTimeString);
        rootLayoutController.setLabelDisplayedEventStartTime(startTimeString);
        rootLayoutController.setLabelDisplayedEventPlace(assignedEvent.getLocation().getValueSafe());
    }
}
