package VerteilterTerminKalender.view.menubar;

import VerteilterTerminKalender.MainApp;
import VerteilterTerminKalender.model.classes.EventFxImpl;
import VerteilterTerminKalender.model.interfaces.EventFx;
import VerteilterTerminKalender.service.classes.EventServiceImpl;
import VerteilterTerminKalender.service.interfaces.EventService;
import VerteilterTerminKalender.validators.ObjectValidator;
import javafx.scene.control.Label;
import VerteilterTerminKalender.util.FxUtil;
import VerteilterTerminKalender.validators.StringValidator;
import VerteilterTerminKalender.view.interfaces.FXMLDialogController;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


/**
 * Controller class for creating events in a new, separate window.
 *
 * @author Michelle Blau
 */

public class CreateEventController implements FXMLDialogController {

    private MainApp mainApp;
    private Stage dialogStage;
    private EventService eventService = new EventServiceImpl();

//User-Input---------------------
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
//Success-Label-------------------
    @FXML
    private Label eventCreateSuccessLabel;

    @Override
    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
    }

    @Override
    public void setDialogStage(Stage dialogStage){
        this.dialogStage = dialogStage;
    }

    /**
     * Creates a new Event after validating user input
     * The new Event will be saved on the Server, but not inside the GUI
     */
    @FXML
    private void handleBtnAdd(){
        if(validateInput()){
            String location                 = eventLocationTextField.getText();
            LocalDate startLocalDate        = eventDatePicker1.getValue();
            String starttimeString          = eventStarttimeTextField1.getText();
            String endtimeString            = eventEndtimeTextField1.getText();
            LocalDate endLocalDate          = eventDatePicker2.getValue();
            String note                     = eventNoteTextArea.getText();
            int repeat                      = Integer.parseInt(eventRepeatTextField.getText());
            Boolean allday                  = eventAllDayCheckbox.isSelected();

            LocalTime startLocalTime = LocalTime.parse(starttimeString);
            LocalDateTime starttime = LocalDateTime.of(startLocalDate, startLocalTime);
            LocalTime endLocalTime = LocalTime.parse(endtimeString);
            LocalDateTime endtime = LocalDateTime.of(endLocalDate, endLocalTime);

            int userId = Integer.parseInt(mainApp.getUser().getUserId());

            EventFx tmpEvent = new EventFxImpl(location, starttime, endtime, allday, repeat, note, userId);
            int response = eventService.newEvent(tmpEvent);


            System.out.println("Response: " + response);
            FxUtil.showSuccessLabel(eventCreateSuccessLabel);

            mainApp.getEventFXList().add(tmpEvent); //TODO Objekt in FX-Liste einfügen?
            System.out.println("EventFXList: " + mainApp.getEventFXList() + "\n"); //TODO entfernen
        }

    }


    /**
     * Validates user input and shows error messages inside the GUI
     *
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
}
