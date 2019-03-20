package VerteilterTerminKalender.view;

import VerteilterTerminKalender.MainApp;
import VerteilterTerminKalender.model.interfaces.EventFx;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class EventOverviewController {

    private MainApp mainApp;

    private EventFx assignedEvent;

    @FXML
    private Label labelEventDetails;

    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
    }

    public void setup(EventFx event){
        this.assignedEvent = event;
        labelEventDetails.setText(assignedEvent.toString());
    }

    @FXML
    private void handleEventSelected(){

    }
}
