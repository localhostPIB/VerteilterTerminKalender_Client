package VerteilterTerminKalender.view.menubar;


import VerteilterTerminKalender.MainApp;
import VerteilterTerminKalender.model.classes.EventParticipateImpl;
import VerteilterTerminKalender.model.classes.UserImpl;
import VerteilterTerminKalender.model.interfaces.EventDeclineUser;
import VerteilterTerminKalender.model.interfaces.EventFx;
import VerteilterTerminKalender.model.interfaces.User;
import VerteilterTerminKalender.service.classes.*;
import VerteilterTerminKalender.service.interfaces.*;
import VerteilterTerminKalender.util.FxUtil;
import VerteilterTerminKalender.validators.ObjectValidator;
import VerteilterTerminKalender.validators.StringValidator;
import VerteilterTerminKalender.view.interfaces.FXMLDialogController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static VerteilterTerminKalender.util.FxUtil.createEventFxComparatorByStartTime;


/**
 * Controller class for changing events in a new, separate window.
 *
 * @author Michelle Blau
 */

public class CheckSentInviteController implements FXMLDialogController {

    private MainApp mainApp;
    private Stage dialogStage;
    private EventDeclineService eventDeclineService = new EventDeclineServiceImpl();
    private EventParticipateService eventParticipateService = new EventParticipateServiceImpl();

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
    private ListView<UserImpl> inviteAcceptedListView;

    //Error-Labels-------------------
    @FXML
    private Label eventChoiceBoxErrorLabel;


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
     * Validates user choice from the choiceBox and shows
     * error messages inside the GUI
     * @return true if user input is correct, else false
     */
    private boolean validateChoice(){
        boolean result = true;

        if(ObjectValidator.isObjectNull(eventFxChoiceBox.getValue())){
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
     * Checks if the ListView has users in it
     * @return true if users are inside, else false
     */
    private boolean validateListView() {
        boolean result = true;

//        if(inviteUserListView.getItems().size() == 0){
//            FxUtil.showErrorLabel(inviteListViewErrorLabel);
//            result = false;
//        }else{inviteListViewErrorLabel.setVisible(false);}

        return result;
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
                    inviteDeclinedListView.getItems().setAll(eventDeclineUsers);

                    ArrayList<UserImpl> participants = eventParticipateService.getParticipants(chosenEventFxId);
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

