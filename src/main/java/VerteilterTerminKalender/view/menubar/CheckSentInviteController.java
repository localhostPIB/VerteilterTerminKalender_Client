package VerteilterTerminKalender.view.menubar;


import VerteilterTerminKalender.MainApp;
import VerteilterTerminKalender.builders.ServiceObjectBuilder;
import VerteilterTerminKalender.model.interfaces.EventDeclineUser;
import VerteilterTerminKalender.model.interfaces.EventFx;
import VerteilterTerminKalender.model.interfaces.Person;
import VerteilterTerminKalender.service.classes.EventDeclineServiceImpl;
import VerteilterTerminKalender.service.classes.EventParticipateServiceImpl;
import VerteilterTerminKalender.service.interfaces.EventDeclineService;
import VerteilterTerminKalender.service.interfaces.EventParticipateService;
import VerteilterTerminKalender.util.FxUtil;
import VerteilterTerminKalender.validators.ObjectValidator;
import VerteilterTerminKalender.view.interfaces.FXMLDialogController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javax.xml.ws.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/**
 * Controller class for viewing the status of
 * sent invitations
 * @author Michelle Blau
 */

public class CheckSentInviteController implements FXMLDialogController {

    private MainApp mainApp;
    private Stage dialogStage;
    private EventDeclineService eventDeclineService = ServiceObjectBuilder.getEventDeclineService();
    private EventParticipateService eventParticipateService = ServiceObjectBuilder.getEventParticipateService();

    //User-Input---------------------
    @FXML
    ChoiceBox<EventFx> eventFxChoiceBox;
    @FXML
    private DatePicker eventDatePicker1;
    @FXML
    private DatePicker eventDatePicker2;
    @FXML
    private TextField eventDetailsTextField;
    @FXML
    private ListView<EventDeclineUser> inviteDeclinedListView;
    @FXML
    private ListView<Person> inviteAcceptedListView;

    //Error-Labels-------------------
    @FXML
    private Label eventChoiceBoxErrorLabel;

    /**
     * Sets the mainApp-Object of this controller
     * Initializes the eventFxChoiceBox and adds
     * a listener to it
     * @param mainApp
     */
    @Override
    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
        eventFxChoiceBox.setItems(mainApp.getEventFXList());

        eventFxChoiceBox.getSelectionModel()
                .selectedIndexProperty()
                .addListener(getEventFxChoiceBoxListener());
    }


    @Override
    public void setDialogStage(Stage dialogStage){
        this.dialogStage = dialogStage;
    }

    /**
     * Closes the current window
     */
    @FXML
    private void handleBtnOK(){

        this.dialogStage.close();
    }

    /**
     * Closes current Stage
     */
    @FXML
    private void handleBtnCancel(){
        this.dialogStage.close();
    }


    /**
     * creates a lambda expression that sets the contents of the textfields upon
     * choosing an item inside "eventFxChoiceBox"
     * @return lambda expression
     */
    private ChangeListener<? super Number> getEventFxChoiceBoxListener() {
        ChangeListener<Number> lambda = new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                int currentChoice = (Integer) newValue;
                if (currentChoice != -1) {
                    EventFx chosenEventFx = eventFxChoiceBox.getItems().get(currentChoice);
                    int chosenEventFxId = chosenEventFx.getEventId().getValue();

                    LocalDate startDate = chosenEventFx.getStartTime().getValue().toLocalDate();
                    eventDatePicker1.setValue(startDate);

                    LocalDate endDate = chosenEventFx.getEndTime().getValue().toLocalDate();
                    eventDatePicker2.setValue(endDate);

                    eventDetailsTextField.setText(chosenEventFx.toString() + ", " + chosenEventFx.getLocation().getValue());

                    List<EventDeclineUser> eventDeclineUsers = eventDeclineService.getUserWhoDeclined(chosenEventFxId);
                    inviteDeclinedListView.getItems().clear();
                    inviteDeclinedListView.getItems().setAll(eventDeclineUsers);

                    ArrayList<Person> participants = eventParticipateService.getParticipants(chosenEventFxId);
                    inviteAcceptedListView.getItems().clear();
                    inviteAcceptedListView.getItems().setAll(participants);


                }else{
                    eventDatePicker1.setValue(null);
                    eventDatePicker2.setValue(null);
                    eventDetailsTextField.setText("");
                    inviteDeclinedListView.getItems().clear();
                    inviteAcceptedListView.getItems().clear();

                }
            }
        };
        return lambda;
    }
}

