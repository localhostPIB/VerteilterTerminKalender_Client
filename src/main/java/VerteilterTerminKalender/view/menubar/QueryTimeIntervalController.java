package VerteilterTerminKalender.view.menubar;

import VerteilterTerminKalender.MainApp;
import VerteilterTerminKalender.builders.ModelObjectBuilder;
import VerteilterTerminKalender.builders.ServiceObjectBuilder;
import VerteilterTerminKalender.model.classes.EventSimpleString;
import VerteilterTerminKalender.model.interfaces.EventFx;
import VerteilterTerminKalender.service.classes.EventServiceImpl;
import VerteilterTerminKalender.service.interfaces.EventService;
import VerteilterTerminKalender.util.FxUtil;
import VerteilterTerminKalender.validators.ObjectValidator;
import VerteilterTerminKalender.view.interfaces.FXMLDialogController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.stream.Collectors;


/**
 * Controller class for viewing Events of a given
 * time interval
 *
 * @author Michelle Blau
 */

public class QueryTimeIntervalController implements FXMLDialogController, Initializable {

    private MainApp mainApp;
    private Stage dialogStage;
    private EventService eventService = ServiceObjectBuilder.getEventService();

//User-Input---------------------
    @FXML
    private DatePicker eventDatePicker1;
    @FXML
    private DatePicker eventDatePicker2;
//TableView----------------------
    @FXML
    private TableView<EventSimpleString> eventTableView;
    @FXML
    private TableColumn<EventSimpleString, String> startTimeColumn;
    @FXML
    private TableColumn<EventSimpleString, String>  endTimeColumn;
    @FXML
    private TableColumn<EventSimpleString, String> locationColumn;
    @FXML
    private TableColumn<EventSimpleString, String> noteColumn;

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
     * Shows the Events after the time interval has been entered
     */
    @FXML
    private void handleBtnShow(){
        if(validateInput()){
            String currentUserId = this.mainApp.getUser().getUserId();
            ObservableList<EventFx> eventFxObservableList = eventService.getAllEvents(currentUserId);

            List<EventFx> list = eventFxObservableList.stream().filter(createIntervalPredicate()).sorted(FxUtil.createEventFxComparatorByStartTime()).collect(Collectors.toList());

            ObservableList<EventFx> filteredEventFxObservableList = FXCollections.observableList(list);
            List<EventSimpleString> simpleStringList = new ArrayList<>();

            for(EventFx eventFx : filteredEventFxObservableList){
                simpleStringList.add(ModelObjectBuilder.getEventSimpleStringObject(eventFx));
            }

            ObservableList<EventSimpleString> finalList = FXCollections.observableArrayList(simpleStringList);

            eventTableView.getItems().setAll(finalList);

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

        if(FxUtil.isDateChoiceIncorrect(eventDatePicker1, eventDatePicker2)){
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

    /**
     * Configures the columns of the TableView
     * Tells the TableView which attributes of the Events are
     * in which column
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        startTimeColumn.setCellValueFactory(new PropertyValueFactory<EventSimpleString, String>("startTime"));
        endTimeColumn.setCellValueFactory(new PropertyValueFactory<EventSimpleString, String>("endTime"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<EventSimpleString, String>("location"));
        noteColumn.setCellValueFactory(new PropertyValueFactory<EventSimpleString, String>("note"));

    }
}
