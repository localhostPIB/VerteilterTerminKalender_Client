package VerteilterTerminKalender.view.menubar;


import VerteilterTerminKalender.MainApp;
import VerteilterTerminKalender.model.classes.EventFxImpl;
import VerteilterTerminKalender.model.interfaces.EventFx;
import VerteilterTerminKalender.model.interfaces.User;
import VerteilterTerminKalender.service.classes.EventInviteServiceImpl;
import VerteilterTerminKalender.service.classes.EventServiceImpl;
import VerteilterTerminKalender.service.classes.UserServiceImpl;
import VerteilterTerminKalender.service.interfaces.EventInviteService;
import VerteilterTerminKalender.service.interfaces.EventService;
import VerteilterTerminKalender.service.interfaces.UserService;
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
import java.util.ArrayList;
import java.util.List;


/**
 * Controller class for changing events in a new, separate window.
 *
 * @author Michelle Blau
 */

public class CreateInviteController implements FXMLDialogController {

    private MainApp mainApp;
    private Stage dialogStage;
    private EventService eventService = new EventServiceImpl();
    private UserService userService = new UserServiceImpl();
    private EventInviteService eventInviteService = new EventInviteServiceImpl();

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
    private TextField inviteEmailTextField;
    @FXML
    private ListView<User> inviteUserListView;

    //Error-Labels-------------------
    @FXML
    private Label eventChoiceBoxErrorLabel;
    @FXML
    private Label inviteEmailErrorLabel;
    @FXML
    private Label inviteOwnEmailErrorLabel;
    @FXML
    private Label inviteListViewErrorLabel;


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
     * Creates an invitation after validating user input
     */
    @FXML
    private void handleBtnAddInvite(){
        if(validateListView()) {
            if (validateChoice()) {
                int eventId = eventFxChoiceBox.getValue().getEventId().getValue();

                int userCount = inviteUserListView.getItems().size();
                int [] userIdArray = new int[userCount];

                int i = 0;
                for(User user : inviteUserListView.getItems()){
                    int userId = Integer.parseInt(user.getUserId());
                    userIdArray[i] = userId;
                    i++;
                }

                eventInviteService.sendInviteToUsers(eventId, userIdArray);

                Sync.all(this.mainApp, this.mainApp.getUser().getUserId());

                this.dialogStage.close();
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
        String email = inviteEmailTextField.getText();
        String ownEmail = this.mainApp.getUser().getEmail();

        if(StringValidator.isStringEmptyOrNull(email) || !userService.isUserExistingByEmail(email)){
            FxUtil.showErrorLabel(inviteEmailErrorLabel);
            result = false;
        }else{inviteEmailErrorLabel.setVisible(false);}

        if(email.equals(ownEmail)){
            FxUtil.showErrorLabel(inviteOwnEmailErrorLabel);
            result = false;
        }else{inviteOwnEmailErrorLabel.setVisible(false);}

        return result;
    }

    /**
     * Checks if the ListView has users in it
     * @return true if users are inside, else false
     */
    private boolean validateListView() {
        boolean result = true;

        if(inviteUserListView.getItems().size() == 0){
            FxUtil.showErrorLabel(inviteListViewErrorLabel);
            result = false;
        }else{inviteListViewErrorLabel.setVisible(false);}

        return result;
    }

    /**
     * Adds an existing User from the Database to the "inviteUserListView"
     */
    @FXML
    private void handleAddUserToListView(){
        if(validateInput()){
            String email = inviteEmailTextField.getText();
            User invitedUser = userService.getUserByEmail(email);
            if(!inviteUserListView.getItems().contains(invitedUser)) {
                inviteUserListView.getItems().add(invitedUser);
            }

            inviteEmailTextField.clear();
        }
    }

    /**
     * Deletes an existing User from the "inviteUserListView"
     */
    @FXML
    private void handleBtnDeleteInvite(){
        System.out.println(inviteUserListView.getEditingIndex());
        int selectedIndex = inviteUserListView.getEditingIndex();
        if(selectedIndex != -1) {
            inviteUserListView.getItems().remove(selectedIndex);
        }
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
     * choosing an item inside "eventInviteChoiceBox"
     * @return lambda expression
     */
    private ChangeListener<? super Number> getEventFxChoiceBoxListener() {
        ChangeListener<Number> lambda = new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                int currentChoice = (Integer) newValue;
                if (currentChoice != -1) {
                    EventFx chosenEventFx = eventFxChoiceBox.getItems().get(currentChoice);

                    LocalDate startDate = chosenEventFx.getStartTime().getValue().toLocalDate();
                    eventDatePicker1.setValue(startDate);

                    LocalDate endDate = chosenEventFx.getEndTime().getValue().toLocalDate();
                    eventDatePicker2.setValue(endDate);

                    eventDetailsTextField.setText(chosenEventFx.toString());

                }else{
                    eventDatePicker1.setValue(null);
                    eventDatePicker2.setValue(null);
                    eventDetailsTextField.setText("");

                }
            }
        };
        return lambda;
    }
}

