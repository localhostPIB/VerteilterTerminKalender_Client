package VerteilterTerminKalender.view;

import VerteilterTerminKalender.MainApp;
import VerteilterTerminKalender.model.interfaces.EventInvite;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class InviteOverviewController {

    private MainApp mainApp;

    private EventInvite invite;

    @FXML
    private Label invitationEventName;
    @FXML
    private Label invitationInviterName;

    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
    }

    public void setup(EventInvite invite){
        this.invite = invite;

        //invitationEventName.setText();

    }
}
