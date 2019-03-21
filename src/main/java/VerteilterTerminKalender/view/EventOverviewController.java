package VerteilterTerminKalender.view;

import VerteilterTerminKalender.MainApp;
import VerteilterTerminKalender.builders.ServiceObjectBuilder;
import VerteilterTerminKalender.model.interfaces.EventFx;
import VerteilterTerminKalender.service.classes.UserServiceImpl;
import VerteilterTerminKalender.service.interfaces.UserService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.time.LocalDateTime;

public class EventOverviewController {

    private MainApp mainApp;
    private RootLayoutController rootLayoutController;
    private UserService userService = ServiceObjectBuilder.getUserService();

    private EventFx assignedEvent;

    @FXML
    private Label labelEventDetails;

    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
        this.rootLayoutController = mainApp.getRootLayoutController();
    }

    public void setup(EventFx event){
        this.assignedEvent = event;
        labelEventDetails.setText(assignedEvent.toString());
    }

    @FXML
    private void handleEventSelected(){
        LocalDateTime endTime = assignedEvent.getEndTime().getValue();
        String endTimeString = endTime.getDayOfMonth() + "." + endTime.getMonthValue() + "."
                + endTime.getYear() + " " + endTime.getHour() + ":" + endTime.getMinute();

        LocalDateTime startTime = assignedEvent.getStartTime().getValue();
        String startTimeString = startTime.getDayOfMonth() + "." + startTime.getMonthValue() + "."
                + startTime.getYear() + " " + startTime.getHour() + ":" + endTime.getMinute();

        String owner = mainApp.getUser().getName() + " " + mainApp.getUser().getLastName();
        Integer ownerId;
        if(assignedEvent.getUserId() != null){
            ownerId = assignedEvent.getUserId().getValue();
            owner = ownerId.toString();
        }

        rootLayoutController.setLabelDisplayedEventDescription(assignedEvent.getNote().getValueSafe());
        rootLayoutController.setLabelDisplayedEventEndTime(endTimeString);
        rootLayoutController.setLabelDisplayedEventStartTime(startTimeString);
        rootLayoutController.setLabelDisplayedEventPlace(assignedEvent.getLocation().getValueSafe());
        rootLayoutController.setLabelDisplayedEventOwner(owner);
    }
}
