package VerteilterTerminKalender.view;

import VerteilterTerminKalender.MainApp;
import VerteilterTerminKalender.builders.ServiceObjectBuilder;
import VerteilterTerminKalender.model.classes.EventFxImpl;
import VerteilterTerminKalender.model.interfaces.EventFx;
import VerteilterTerminKalender.model.interfaces.EventInvite;
import VerteilterTerminKalender.service.classes.EventServiceImpl;
import VerteilterTerminKalender.service.interfaces.EventInviteService;
import VerteilterTerminKalender.service.interfaces.EventService;
import VerteilterTerminKalender.util.Sync;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * This class controls the JavaFX-elements that display the invitations.
 *
 * @author Johannes Gerwert
 * @version 21.03.2019
 */
public class InviteOverviewController {

    private MainApp mainApp;

    private EventInvite invite;
    private EventFx event;

    private EventService eventService = ServiceObjectBuilder.getEventService();
    private EventInviteService eventInviteService = ServiceObjectBuilder.getEventInviteService();

    @FXML
    private Label invitationEventName;
    @FXML
    private Label invitationEventLocation;

    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
    }

    /**
     * Important initializations are made. Should be called right after setting the mainApp when creating
     * the controller.
     *
     * The displayed invite and the corresponding event are set. Then the text describing the invitation
     * is set.
     *
     * @param invite The invitation that is represented by this FX-element.
     */
    public void setup(EventInvite invite){
        this.invite = invite;
        this.event = eventService.getEventByEventId(this.invite.getEventId());

        if(this.event == null){
            invitationEventName.setText("ERROR");
            invitationEventLocation.setText("ERROR");
        }else {
            invitationEventName.setText(this.event.toString());
            invitationEventLocation.setText(this.event.getLocation().getValue());
        }
    }

    /**
     * If the User clicks on the accept-invitation-button, this method will be called.
     * The corresponding invitation will be accepted.
     */
    @FXML
    private void handleAcceptInvite(){
        int userId = Integer.parseInt(this.mainApp.getUser().getUserId());
        eventInviteService.acceptInvite(invite);
        Sync.all(this.mainApp, this.mainApp.getUser().getUserId());

        EventFx acceptedEventFx = eventService.getEventByEventId(invite.getEventId());

        EventFx tmpEvent = new EventFxImpl(acceptedEventFx.getLocation().getValue(),
                acceptedEventFx.getStartTime().getValue(),
                acceptedEventFx.getEndTime().getValue(),
                acceptedEventFx.getAllDay().getValue(),
                acceptedEventFx.getRepeat().getValue(),
                acceptedEventFx.getNote().getValue(),
                userId);
        int response = eventService.newEvent(tmpEvent);

        Sync.all(this.mainApp,this.mainApp.getUser().getUserId());
    }

    /**
     * If the User clicks on the decline-invitation-button, this method will be called.
     * The corresponding invitation will be declined.
     */
    @FXML
    private void handleDeclineInvite(){
        eventInviteService.declineInvite(invite);
        Sync.all(this.mainApp, this.mainApp.getUser().getUserId());
    }
}
