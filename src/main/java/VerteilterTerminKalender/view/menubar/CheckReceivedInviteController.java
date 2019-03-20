package VerteilterTerminKalender.view.menubar;


import VerteilterTerminKalender.MainApp;
import VerteilterTerminKalender.model.interfaces.EventFx;
import VerteilterTerminKalender.model.interfaces.EventInvite;
import VerteilterTerminKalender.service.classes.EventDeclineServiceImpl;
import VerteilterTerminKalender.service.classes.EventInviteServiceImpl;
import VerteilterTerminKalender.service.classes.EventParticipateServiceImpl;
import VerteilterTerminKalender.service.classes.EventServiceImpl;
import VerteilterTerminKalender.service.interfaces.EventDeclineService;
import VerteilterTerminKalender.service.interfaces.EventInviteService;
import VerteilterTerminKalender.service.interfaces.EventParticipateService;
import VerteilterTerminKalender.service.interfaces.EventService;
import VerteilterTerminKalender.util.FxUtil;
import VerteilterTerminKalender.util.Sync;
import VerteilterTerminKalender.validators.ObjectValidator;
import VerteilterTerminKalender.view.interfaces.FXMLDialogController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;


/**
 * Controller class for changing events in a new, separate window.
 *
 * @author Michelle Blau
 */

public class CheckReceivedInviteController implements FXMLDialogController {

    private MainApp mainApp;
    private Stage dialogStage;
    private EventService eventService = new EventServiceImpl();
    private EventInviteService eventInviteService = new EventInviteServiceImpl();
    private EventDeclineService eventDeclineService = new EventDeclineServiceImpl();
    private EventParticipateService eventParticipateService = new EventParticipateServiceImpl();

    //User-Input---------------------
    @FXML
    ChoiceBox<EventInvite> eventInviteChoiceBox;
    @FXML
    private DatePicker eventDatePicker1;
    @FXML
    private DatePicker eventDatePicker2;
    @FXML
    private TextField eventDetailsTextField;
    @FXML
    private TextArea eventNoteTextArea;



    //Error-Labels-------------------
    @FXML
    private Label eventChoiceBoxErrorLabel;


    @Override
    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
        eventInviteChoiceBox.setItems(mainApp.getEventInvitesList());

        eventInviteChoiceBox.getSelectionModel()
                .selectedIndexProperty()
                .addListener(getChoiceBoxListener());
    }


    @Override
    public void setDialogStage(Stage dialogStage){
        this.dialogStage = dialogStage;
    }

    /**
     * Accepts chosen Event
     */
    @FXML
    private void handleBtnAccept(){
        if(validateChoice()){
            EventInvite chosenInvite =  eventInviteChoiceBox.getValue();
            eventInviteService.acceptInvite(chosenInvite);

            Sync.all(this.mainApp, this.mainApp.getUser().getUserId());

            eventInviteChoiceBox.setItems(this.mainApp.getEventInvitesList());
        }
    }


    /**
     * Validates user choice from the choiceBox and shows
     * error messages inside the GUI
     * @return true if user input is correct, else false
     */
    private boolean validateChoice(){
        boolean result = true;

        if(ObjectValidator.isObjectNull(eventInviteChoiceBox.getValue())){
            FxUtil.showErrorLabel(eventChoiceBoxErrorLabel);
            result = false;
        }else{eventChoiceBoxErrorLabel.setVisible(false);}

        return result;
    }

    /**
     * Validates user input and shows error messages inside the GUI
     * @return true if user input is correct, else false
     */
    private boolean validateInput(){
        boolean result = true;

        return result;
    }


    /**
     * Declines chosen Event
     */
    @FXML
    private void handleBtnDecline(){
        if(validateChoice()){
            EventInvite chosenInvite =  eventInviteChoiceBox.getValue();
            eventInviteService.declineInvite(chosenInvite);

            Sync.all(this.mainApp, this.mainApp.getUser().getUserId());

            eventInviteChoiceBox.setItems(this.mainApp.getEventInvitesList());
        }
    }


    /**
     * creates a lambda expression that sets the contents of the textfields upon
     * choosing an item inside "eventInviteChoiceBox"
     * @return lambda expression
     */
    private ChangeListener<? super Number> getChoiceBoxListener() {
        ChangeListener<Number> lambda = new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                int currentChoice = (Integer) newValue;
                if (currentChoice != -1) {

                    EventInvite chosenEventInvite = eventInviteChoiceBox.getItems().get(currentChoice);
                    EventFx chosenEventFx = eventService.getEventByEventId(chosenEventInvite.getEventId());
                    int chosenEventFxId = chosenEventFx.getEventId().getValue();

                    LocalDate startDate = chosenEventFx.getStartTime().getValue().toLocalDate();
                    eventDatePicker1.setValue(startDate);

                    LocalDate endDate = chosenEventFx.getEndTime().getValue().toLocalDate();
                    eventDatePicker2.setValue(endDate);

                    eventDetailsTextField.setText(chosenEventFx.toString() + ", " + chosenEventFx.getLocation().getValue());
                    eventNoteTextArea.setText(chosenEventFx.getNote().getValue());


                }else{
                    eventDatePicker1.setValue(null);
                    eventDatePicker2.setValue(null);
                    eventDetailsTextField.setText("");
                    eventNoteTextArea.setText("");

                }
            }
        };
        return lambda;
    }
}

