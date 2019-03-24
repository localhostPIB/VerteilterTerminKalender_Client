package VerteilterTerminKalender.view.menubar;


import VerteilterTerminKalender.MainApp;
import VerteilterTerminKalender.builders.ModelObjectBuilder;
import VerteilterTerminKalender.builders.ServiceObjectBuilder;
import VerteilterTerminKalender.model.classes.EventFxImpl;
import VerteilterTerminKalender.model.interfaces.EventFx;
import VerteilterTerminKalender.service.classes.EventServiceImpl;
import VerteilterTerminKalender.service.interfaces.EventService;
import VerteilterTerminKalender.util.FxUtil;
import VerteilterTerminKalender.util.Sync;
import VerteilterTerminKalender.validators.ObjectValidator;
import VerteilterTerminKalender.validators.StringValidator;
import VerteilterTerminKalender.view.interfaces.FXMLDialogController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


/**
 * Controller class for editing events in a new, separate window.
 *
 * @author Michelle Blau
 */

public class ChangeEventController implements FXMLDialogController {

    private MainApp mainApp;
    private Stage dialogStage;
    private EventService eventService = ServiceObjectBuilder.getEventService();

    //User-Input---------------------
    @FXML
    ChoiceBox<EventFx> eventFxChoiceBox;
    @FXML
    private TextField eventLocationTextField;
    @FXML
    private DatePicker eventDatePicker1;
    @FXML
    private DatePicker eventDatePicker2;
    @FXML
    private TextField eventStarttimeTextField1;
    @FXML
    private TextField eventEndtimeTextField1;
    @FXML
    private TextArea eventNoteTextArea;
    @FXML
    private TextField eventRepeatTextField;
    @FXML
    private CheckBox eventAllDayCheckbox;
    //Error-Labels-------------------
    @FXML
    private Label eventLocationErrorLabel;
    @FXML
    private Label eventStarttimeErrorLabel;
    @FXML
    private Label eventEndtimeErrorLabel;
    @FXML
    private Label eventNoteErrorLabel;
    @FXML
    private Label eventRepeatErrorLabel;
    @FXML
    private Label eventDateErrorLabel;
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
     * Changes an existing Event after validating user input
     */
    @FXML
    private void handleBtnChange(){
        if(validateChoice()) {
            if (validateInput()) {
                String location = eventLocationTextField.getText();
                LocalDate startLocalDate = eventDatePicker1.getValue();
                String starttimeString = eventStarttimeTextField1.getText();
                String endtimeString = eventEndtimeTextField1.getText();
                LocalDate endLocalDate = eventDatePicker2.getValue();
                String note = eventNoteTextArea.getText();
                int repeat = Integer.parseInt(eventRepeatTextField.getText());
                Boolean allday = eventAllDayCheckbox.isSelected();

                LocalTime startLocalTime = LocalTime.parse(starttimeString);
                LocalDateTime starttime = LocalDateTime.of(startLocalDate, startLocalTime);
                LocalTime endLocalTime = LocalTime.parse(endtimeString);
                LocalDateTime endtime = LocalDateTime.of(endLocalDate, endLocalTime);

                int userId = Integer.parseInt(mainApp.getUser().getUserId());

                EventFx chosenEventFx = eventFxChoiceBox.getValue();
                int chosenEventID = chosenEventFx.getEventId().getValue();

                EventFx changedEventFx = new EventFxImpl(location, starttime, endtime, allday, repeat, note, userId, chosenEventID);

                eventService.modifyEventFx(changedEventFx);

                Sync.all(this.mainApp, this.mainApp.getUser().getUserId());
                eventFxChoiceBox.setItems(FXCollections.observableArrayList());
                eventFxChoiceBox.setItems(this.mainApp.getEventFXList());
            }
        }
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

        if(StringValidator.isStringEmptyOrNull(eventLocationTextField.getText())){
            FxUtil.showErrorLabel(eventLocationErrorLabel);
            result = false;
        }else{eventLocationErrorLabel.setVisible(false);}

        if(ObjectValidator.isObjectNull(eventDatePicker1.getValue()) || ObjectValidator.isObjectNull(eventDatePicker2.getValue())){
            FxUtil.showErrorLabel(eventDateErrorLabel);
            result = false;
        }else{eventDateErrorLabel.setVisible(false);}

        if(!StringValidator.isTimeFormatted(eventStarttimeTextField1.getText())){
            FxUtil.showErrorLabel(eventStarttimeErrorLabel);
            result = false;
        }else{eventStarttimeErrorLabel.setVisible(false);}

        if(!StringValidator.isTimeFormatted(eventEndtimeTextField1.getText())){
            FxUtil.showErrorLabel(eventEndtimeErrorLabel);
            result = false;
        }else{eventEndtimeErrorLabel.setVisible(false);}

        if(StringValidator.isStringEmptyOrNull(eventNoteTextArea.getText())){
            FxUtil.showErrorLabel(eventNoteErrorLabel);
            result = false;
        }else{eventNoteErrorLabel.setVisible(false);}

        if(StringValidator.isStringEmptyOrNull(eventRepeatTextField.getText()) || !StringValidator.isNumber(eventRepeatTextField.getText())){
            FxUtil.showErrorLabel(eventRepeatErrorLabel);
            result = false;
        }else{eventRepeatErrorLabel.setVisible(false);}

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
     * Creates a lambda-Expression that writes the contents
     * of a chosen event inside the control-elements of the
     * window
     * @return lambda, called by choosing an item in the ChoiceBox
     */
    private ChangeListener<? super Number> getEventFxChoiceBoxListener() {
        ChangeListener<Number> lambda = new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                int currentChoice = (Integer) newValue;
                if (currentChoice != -1) {
                    EventFx chosenEventFx = eventFxChoiceBox.getItems().get(currentChoice);

                    eventLocationTextField.setText(chosenEventFx.getLocation().getValue());

                    LocalDate startDate = chosenEventFx.getStartTime().getValue().toLocalDate();
                    eventDatePicker1.setValue(startDate);

                    LocalDate endDate = chosenEventFx.getEndTime().getValue().toLocalDate();
                    eventDatePicker2.setValue(endDate);

                    LocalTime startTime = chosenEventFx.getStartTime().getValue().toLocalTime();
                    eventStarttimeTextField1.setText(startTime.toString());

                    LocalTime endTime = chosenEventFx.getEndTime().getValue().toLocalTime();
                    eventEndtimeTextField1.setText(endTime.toString());

                    eventNoteTextArea.setText(chosenEventFx.getNote().getValue());
                    eventRepeatTextField.setText(chosenEventFx.getRepeat().getValue().toString());
                    eventAllDayCheckbox.setSelected(chosenEventFx.getAllDay().getValue());
                }else{
                    eventLocationTextField.setText("");
                    eventDatePicker1.setValue(null);
                    eventDatePicker2.setValue(null);
                    eventStarttimeTextField1.setText("");
                    eventEndtimeTextField1.setText("");
                    eventNoteTextArea.setText("");
                    eventRepeatTextField.setText("");
                    eventAllDayCheckbox.setSelected(false);
                }
            }
        };
        return lambda;
    }
}

