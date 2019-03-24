package VerteilterTerminKalender.view.menubar;

import VerteilterTerminKalender.MainApp;
import VerteilterTerminKalender.builders.ServiceObjectBuilder;
import VerteilterTerminKalender.model.interfaces.EventFx;
import VerteilterTerminKalender.service.classes.EventServiceImpl;
import VerteilterTerminKalender.service.interfaces.EventService;
import VerteilterTerminKalender.util.FxUtil;
import VerteilterTerminKalender.util.Sync;
import VerteilterTerminKalender.validators.ObjectValidator;
import VerteilterTerminKalender.view.interfaces.FXMLDialogController;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Controller for deleting events of a user
 */
public class DeleteEventController implements FXMLDialogController {

    private MainApp mainApp;
    private Stage dialogStage;
    private EventService eventService = ServiceObjectBuilder.getEventService();

    @FXML
    private ChoiceBox<EventFx> eventFxChoiceBox;
    @FXML
    private Label eventChoiceBoxErrorLabel;
    @FXML
    private Label eventDeleteSuccessLabel;

    /**
     * Deletes the event that is selected inside the ChoiceBox
     */
    @FXML
    void handleBtnDelete(){

        if(validateChoice()){
            EventFx chosenEventFx = eventFxChoiceBox.getValue();
            int chosenEventFxId = chosenEventFx.getEventId().getValue();
            eventService.deleteEventFx(chosenEventFxId);

            Sync.all(this.mainApp, this.mainApp.getUser().getUserId());
            this.eventFxChoiceBox.setItems(this.mainApp.getEventFXList());

            FxUtil.showSuccessLabel(eventDeleteSuccessLabel);
        }

    }

    @Override
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @Override
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        eventFxChoiceBox.setItems(mainApp.getEventFXList());
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
     * closes the current window
     */
    @FXML
    private void handleBtnCancel(){
        this.dialogStage.close();
    }


}
