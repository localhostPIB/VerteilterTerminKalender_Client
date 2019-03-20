package VerteilterTerminKalender.view.menubar;

import VerteilterTerminKalender.MainApp;
import VerteilterTerminKalender.model.classes.EventFxImpl;
import VerteilterTerminKalender.model.interfaces.Event;
import VerteilterTerminKalender.model.interfaces.EventFx;
import VerteilterTerminKalender.service.classes.EventServiceImpl;
import VerteilterTerminKalender.service.interfaces.EventService;
import VerteilterTerminKalender.util.FxUtil;
import VerteilterTerminKalender.util.Sync;
import VerteilterTerminKalender.validators.ObjectValidator;
import VerteilterTerminKalender.validators.StringValidator;
import VerteilterTerminKalender.view.interfaces.FXMLDialogController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.stream.Collectors;


/**
 * Controller class for creating events in a new, separate window.
 *
 * @author Michelle Blau
 */

public class QueryTimeIntervalController implements FXMLDialogController, Initializable {

    private MainApp mainApp;
    private Stage dialogStage;
    private EventService eventService = new EventServiceImpl();

//User-Input---------------------
    @FXML
    private DatePicker eventDatePicker1;
    @FXML
    private DatePicker eventDatePicker2;
//TableView----------------------
    @FXML
    private TableView<EventFx> eventTableView;
    @FXML
    private TableColumn<EventFx, String> startTimeColumn;
    @FXML
    private TableColumn<EventFx, String>  endTimeColumn;
    @FXML
    private TableColumn<EventFx, String> locationColumn;
    @FXML
    private TableColumn<EventFx, String> noteColumn;

//Error-Labels-------------------

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

    /**
     * Creates a new Event after validating user input
     * The new Event will be saved on the Server
     */
    @FXML
    private void handleBtnShow(){
        if(validateInput()){
            LocalDate startLocalDate = eventDatePicker1.getValue();
            LocalDate endLocalDate = eventDatePicker2.getValue();

            String currentUserId = this.mainApp.getUser().getUserId();
            ObservableList<EventFx> eventFxObservableList = eventService.getAllEvents(currentUserId);
            List<EventFx> list = eventFxObservableList.stream().filter(createIntervalPredicate()).sorted(FxUtil.createEventFxComparatorByStartTime()).collect(Collectors.toList());

            ObservableList<EventFx> filteredEventFxObservableList = FXCollections.observableList(list);

            eventTableView.getItems().setAll(filteredEventFxObservableList);

        }

    }

    /**
     * Creates a predicate that determines if an event is inside a given time interval
     * @return predicate
     */
    private Predicate<EventFx> createIntervalPredicate() {
        Predicate<EventFx> predicate = new Predicate<EventFx>() {
            @Override
            public boolean test(EventFx eventFx) {
                LocalDateTime startTime = eventFx.getStartTime().getValue();
                LocalDate startLocalDate = startTime.toLocalDate();

                LocalDate cmpStartLocalDate = eventDatePicker1.getValue();
                LocalDate cmpEndLocalDate = eventDatePicker2.getValue();

                boolean isEqualOrAfterStart = (startLocalDate.isEqual(cmpStartLocalDate)) || (startLocalDate.isAfter(cmpStartLocalDate));
                boolean isEqualOrBeforeEnd = (startLocalDate.isEqual(cmpEndLocalDate)) || (startLocalDate.isBefore(cmpEndLocalDate));

                if(isEqualOrAfterStart && isEqualOrBeforeEnd){
                    return true;
                }
                return false;
            }
        };
        return predicate;
    }


    /**
     * Validates user input and shows error messages inside the GUI
     *
     * @return true if user input is correct, else false
     */
    private boolean validateInput(){
        boolean result = true;

        if(ObjectValidator.isObjectNull(eventDatePicker1.getValue()) || ObjectValidator.isObjectNull(eventDatePicker2.getValue())){
            FxUtil.showErrorLabel(eventDateErrorLabel);
            result = false;
        }else{eventDateErrorLabel.setVisible(false);}

        return result;
    }

    /**
     * Closes current Stage
     */
    @FXML
    private void handleBtnCancel(){
        this.dialogStage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        startTimeColumn.setCellValueFactory(new PropertyValueFactory<EventFx, String>("startTime"));
        endTimeColumn.setCellValueFactory(new PropertyValueFactory<EventFx, String>("endTime"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<EventFx, String>("location"));
        noteColumn.setCellValueFactory(new PropertyValueFactory<EventFx, String>("note"));

    }
}
