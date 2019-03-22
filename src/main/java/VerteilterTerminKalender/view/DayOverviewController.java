package VerteilterTerminKalender.view;

import VerteilterTerminKalender.MainApp;
import VerteilterTerminKalender.model.interfaces.EventFx;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Stream;

/**
 * This class controls the JavaFX-elements that display the days of the calendar.
 * @author Johannes Gerwert
 * @version 20.03.2019
 */
public class DayOverviewController {

    private MainApp mainApp;
    private RootLayoutController rootLayoutController;

    @FXML
    private Label labelDayOfMonth;
    @FXML
    private VBox vBoxPreviewEvents;

    private GregorianCalendar today;
    private int dayOfMonth;
    private int month;
    private int year;

    private String todayString;

    private ObservableList<EventFx> allEvents;
    private ObservableList<EventFx> eventsOfTheDay;

    /**
     * The mainApp is set.
     * Additionally important initializations that depend on the mainApp are made.
     * @param mainApp The mainApp.
     */
    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
        this.rootLayoutController = mainApp.getRootLayoutController();

        this.allEvents = this.mainApp.getEventFXList();
        addEventListener();
    }

    /**
     * Important initializations are made. Should be called right after setting the mainApp when creating
     * the controller.
     *
     * The date of the day is set both as a Gregorian Calendar Object and as integer.
     * Then a String representing this date is created.
     *
     * All events of the day are fetched from the event list.
     * Then listeners are assigned.
     *
     * Finally the label representing the date is set and the preview events are loaded.
     *
     * @param today The date of the day represented by this JavaFX-element.
     */
    public void setup(GregorianCalendar today){
        this.today = (GregorianCalendar) today.clone();
        this.dayOfMonth = today.get(GregorianCalendar.DAY_OF_MONTH);
        this.month = today.get(GregorianCalendar.MONTH) + 1;
        this.year = today.get(GregorianCalendar.YEAR);

        this.todayString = dayOfMonth + "." + month + "." + year;

        eventsOfTheDay = filterEvents(allEvents);
        addEventPreviewListener();

        addDateChangeListeners();

        labelDayOfMonth.setText(Integer.toString(dayOfMonth));

        updatePreviewEvents();
    }

    /**
     * Adds a listener to the list containing all events.
     *
     * If an event matching this date is added, it will be added to the list containing the events of the day.
     * If the displayed date matches this date, the added event is also added to the list of the rootLayout.
     *
     * If an event matching this date is removed, it will be removed from the list containing the events of the day.
     * If the displayed date matches this date, the removed event is also removed from the list of the rootLayout.
     *
     * If the order of events in the list is changed, the list containing the events of the day is cleared and
     * the recreated.
     * If the displayed date matches this date, the same is done to the corresponding list in the rootLayout.
     */
    private void addEventListener(){
        allEvents.addListener(new ListChangeListener<EventFx>() {
            @Override
            public void onChanged(Change<? extends EventFx> c) {
                while(c.next()){
                    if(c.wasAdded()){
                        for(EventFx eventFx : c.getAddedSubList()){
                            if(checkEvent(eventFx)){
                                eventsOfTheDay.add(eventFx);
                                if(checkDisplayedDate()){
                                   rootLayoutController.addEventOfDisplayedDate(eventFx);
                                }
                            }
                        }
                    }

                    if(c.wasRemoved()){
                        for(EventFx eventFx : c.getRemoved()){
                            eventsOfTheDay.remove(eventFx);
                            if(checkDisplayedDate()) {
                                rootLayoutController.removeEventOfDisplayedDate(eventFx);
                            }
                        }
                    }

                    if(c.wasPermutated()){
                        eventsOfTheDay.clear();
                        eventsOfTheDay.addAll(filterEvents(allEvents));
                        if(checkDisplayedDate()) {
                            rootLayoutController.assignEventsOfDisplayedDate(eventsOfTheDay);
                        }
                    }
                }
            }
        });
    }

    /**
     * A listener is added to the list containing the events of the day.
     * If the list is changed, the list will be updated.
     */
    private void addEventPreviewListener(){
        eventsOfTheDay.addListener(new ListChangeListener<EventFx>() {
            @Override
            public void onChanged(Change<? extends EventFx> c) {
                while(c.next()){
                    if(c.wasAdded()){
                        updatePreviewEvents();
                    }

                    if(c.wasRemoved()){
                        updatePreviewEvents();
                    }

                    if(c.wasPermutated()){
                        updatePreviewEvents();
                    }
                }
            }
        });
    }

    /**
     * Updates the events previewed in the calendar view.
     * First the VBox is cleared. Then up to three events are displayed.
     */
    private void updatePreviewEvents(){

        vBoxPreviewEvents.getChildren().clear();

        ListIterator<EventFx> iterator = eventsOfTheDay.listIterator();

        for(int i = 0; i < 3; i++){
            if(iterator.hasNext()){
                String previewEventName = iterator.next().toString();
                Label labelPreviewEvent = new Label(previewEventName);
                BorderPane centerTheLabel = new BorderPane();
                centerTheLabel.setCenter(labelPreviewEvent);
                vBoxPreviewEvents.getChildren().add(centerTheLabel);
            }
        }
    }

    /**
     * Checks if an event is an event of this date.
     *
     * @param originalEvent The checked event.
     * @return Returns true if the checked event is an event of this date, returns false otherwise.
     */
    private boolean checkEvent(EventFx originalEvent){
        boolean isEventOfToday = false;

        if(originalEvent.getStartTime().getValue().getYear() <= this.year
                && originalEvent.getEndTime().getValue().getYear() >= this.year){

            if(originalEvent.getStartTime().getValue().getMonthValue() <= this.month
                    && originalEvent.getEndTime().getValue().getMonthValue() >= this.month){

                if(originalEvent.getStartTime().getValue().getDayOfMonth() <= this.dayOfMonth
                        && originalEvent.getEndTime().getValue().getDayOfMonth() >= this.dayOfMonth){
                    isEventOfToday = true;
                }
            }
        }

        return isEventOfToday;
    }

    /**
     * Checks if this date is set as the displayed date.
     *
     * @return Returns true if this date is equal to the displayed date, returns false otherwise.
     */
    private boolean checkDisplayedDate(){
        boolean todayIsDisplayedDate = false;
        GregorianCalendar displayedDate = mainApp.getDisplayedDate();

        if(displayedDate.get(GregorianCalendar.YEAR) == this.year){
            if(displayedDate.get(GregorianCalendar.MONTH)+1 == this.month ){
                if(displayedDate.get(GregorianCalendar.DAY_OF_MONTH) == this.dayOfMonth){
                    todayIsDisplayedDate = true;
                }
            }
        }

        return todayIsDisplayedDate;
    }

    /**
     * Filters the events of a given list based on the year, month and dayOfMonth of this date.
     *
     * @param originalList The list that should be filtered.
     * @return The filtered list.
     */
    private ObservableList<EventFx> filterEvents(ObservableList<EventFx> originalList){

        ObservableList<EventFx> filteredList;

        Stream<EventFx> stream = originalList.stream().filter(a -> (a.getStartTime().getValue().getYear() <= this.year)
                                    && (a.getEndTime().getValue().getYear() >= this.year))
                          .filter(a -> (a.getStartTime().getValue().getMonthValue() <= this.month)
                                    && (a.getEndTime().getValue().getMonthValue() >= this.month))
                          .filter(a -> (a.getStartTime().getValue().getDayOfMonth() <= this.dayOfMonth)
                                    && (a.getEndTime().getValue().getDayOfMonth() >= this.dayOfMonth));

        filteredList = stream.collect(FXCollections::observableArrayList, List::add, List::addAll);

        return filteredList;
    }

    /**
     * Adds listeners to the year, month and dayOfMonth properties of the mainApp.
     * If the selected date matches this date, the events of this date are displayed and
     * the text in the detail view is set.
     */
    private void addDateChangeListeners(){
        mainApp.getDisplayedYearProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if(checkDisplayedDate()){
                    rootLayoutController.assignEventsOfDisplayedDate(eventsOfTheDay);
                    rootLayoutController.setTpSelectedDateText(todayString);
                }
            }
        });

        mainApp.getDisplayedMonthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if(checkDisplayedDate()){
                    rootLayoutController.assignEventsOfDisplayedDate(eventsOfTheDay);
                    rootLayoutController.setTpSelectedDateText(todayString);
                }
            }
        });

        mainApp.getDisplayedDayProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if(checkDisplayedDate()){
                    rootLayoutController.assignEventsOfDisplayedDate(eventsOfTheDay);
                    rootLayoutController.setTpSelectedDateText(todayString);
                }
            }
        });
    }

    /**
     * This method is called when this JavaFX-element is clicked.
     * The displayed date in the mainApp is set as this date.
     */
    @FXML
    private void handleDateSelected(){
        mainApp.setDisplayedDate(today);
    }

}
