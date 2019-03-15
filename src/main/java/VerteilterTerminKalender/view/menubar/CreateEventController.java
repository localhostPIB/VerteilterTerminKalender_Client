package VerteilterTerminKalender.view.menubar;

import VerteilterTerminKalender.MainApp;
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


/**
 * Controller class for creating events in a new, separate window.
 *
 * @author Michelle Blau
 */

public class CreateEventController implements FXMLDialogController {

    private MainApp mainApp;
    private Stage dialogStage;

//User-Input---------------------
    @FXML
    private TextField eventLocationTextField;
    @FXML
    private DatePicker eventDatePicker;
    @FXML
    private TextField eventStarttimeTextField;
    @FXML
    private TextField eventEndtimeTextField;
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

    @Override
    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
    }

    @Override
    public void setDialogStage(Stage dialogStage){
        this.dialogStage = dialogStage;
    }

    @FXML
    private void handleBtnAdd(){
        if(validateInput()){
//            String location = eventLocationTextField.getText();
//            LocalDate starttime = eventDatePicker.getValue();
//            LocalDate endtime = eventEndTimeDatePicker.getValue();
//            String note = eventNoteTextArea.getText();
//            int repeat = Integer.parseInt(eventRepeatTextField.getText());
//            Boolean allday = eventAllDayCheckbox.isSelected();

            //EventFx tmpEvent = new EventFxImpl(location, starttime, endtime, allday, repeat, note, 10 );

        }

    }

    private boolean validateInput(){
        boolean result = true;

        if(StringValidator.isStringEmptyOrNull(eventLocationTextField.getText())){
            FxUtil.showErrorLabel(eventLocationErrorLabel);
            result = false;
        }else{eventLocationErrorLabel.setVisible(false);}

        if(ObjectValidator.isObjectNull(eventDatePicker.getValue())){
            FxUtil.showErrorLabel(eventDateErrorLabel);
            result = false;
        }else{eventDateErrorLabel.setVisible(false);}

        if(StringValidator.isStringEmptyOrNull(eventStarttimeTextField.getText())){
            FxUtil.showErrorLabel(eventStarttimeErrorLabel);
            result = false;
        }else{eventStarttimeErrorLabel.setVisible(false);}

        if(StringValidator.isStringEmptyOrNull(eventEndtimeTextField.getText())){
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
