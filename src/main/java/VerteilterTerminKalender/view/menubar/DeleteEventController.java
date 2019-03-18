package VerteilterTerminKalender.view.menubar;

import VerteilterTerminKalender.MainApp;
import VerteilterTerminKalender.model.interfaces.EventFx;
import VerteilterTerminKalender.service.classes.EventServiceImpl;
import VerteilterTerminKalender.service.interfaces.EventService;
import VerteilterTerminKalender.util.FxUtil;
import VerteilterTerminKalender.validators.ObjectValidator;
import VerteilterTerminKalender.view.interfaces.FXMLDialogController;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class DeleteEventController implements FXMLDialogController {

    private MainApp mainApp;
    private Stage dialogStage;

    private EventService eventService = new EventServiceImpl();

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
            //TODO Termin in GUI und DB entfernen, EventFx'e vergleichbar machen (Comparable?)
            EventFx chosenEventFx = eventFxChoiceBox.getValue();
            int chosenEventFxId = chosenEventFx.getEventId().getValue();
            eventService.deleteEventFx(chosenEventFxId);

            mainApp.getEventFXList().remove(chosenEventFx);
            System.out.println("EventFxListe nach Löschen: " + mainApp.getEventFXList());
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
