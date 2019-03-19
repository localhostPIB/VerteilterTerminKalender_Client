package VerteilterTerminKalender.view;

import VerteilterTerminKalender.MainApp;
import VerteilterTerminKalender.model.interfaces.EventFx;
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

public class DayOverviewController {

    private MainApp mainApp;

    @FXML
    private Label labelDayOfMonth;
    @FXML
    private VBox vBoxPreviewEvents;

    private GregorianCalendar today;
    private int dayOfMonth;
    private int month;
    private int year;
    private ObservableList<EventFx> allEvents;
    private ObservableList<EventFx> eventsOfTheDay;

    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;

        this.allEvents = this.mainApp.getEventFXList();
        addEventListener();
    }

    public void setup(GregorianCalendar today){
        this.today = today;
        this.dayOfMonth = today.get(GregorianCalendar.DAY_OF_MONTH);
        this.month = today.get(GregorianCalendar.MONTH) + 1;
        this.year = today.get(GregorianCalendar.YEAR);

        eventsOfTheDay = filterEvents(allEvents);
        addEventPreviewListener();

        labelDayOfMonth.setText(Integer.toString(dayOfMonth));

        updatePreviewEvents();
    }

    private void addEventListener(){
        allEvents.addListener(new ListChangeListener<EventFx>() {
            @Override
            public void onChanged(Change<? extends EventFx> c) {
                while(c.next()){
                    if(c.wasAdded()){
                        for(EventFx eventFx : c.getAddedSubList()){
                            if(checkEvent(eventFx)){
                                eventsOfTheDay.add(eventFx);
                            }
                        }
                    }

                    if(c.wasRemoved()){
                        for(EventFx eventFx : c.getRemoved()){
                            eventsOfTheDay.remove(eventFx);
                        }
                    }

                    if(c.wasPermutated()){
                        eventsOfTheDay.clear();
                        eventsOfTheDay.addAll(filterEvents(allEvents));
                    }
                }
            }
        });
    }

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

    private boolean checkEvent(EventFx originalEvent){
        boolean isEventOfToday = false;

        if(originalEvent.getStartTime().getValue().getYear() == this.year
                || originalEvent.getEndTime().getValue().getYear() == this.year){

            if(originalEvent.getStartTime().getValue().getMonthValue() == this.month
                    || originalEvent.getEndTime().getValue().getMonthValue() == this.month){

                if(originalEvent.getStartTime().getValue().getDayOfMonth() == this.dayOfMonth
                        || originalEvent.getEndTime().getValue().getDayOfMonth() == this.dayOfMonth){
                    isEventOfToday = true;
                }
            }
        }

        return isEventOfToday;
    }

    private boolean checkDisplayedDate(){
        boolean todayIsDisplayedDate = false;
        GregorianCalendar displayedDate = mainApp.getDisplayedDate();

        if(displayedDate.get(GregorianCalendar.YEAR) == this.year){
            if(displayedDate.get(GregorianCalendar.MONTH) == this.month){
                if(displayedDate.get(GregorianCalendar.DAY_OF_MONTH) == this.dayOfMonth){
                    todayIsDisplayedDate = true;
                }
            }
        }

        return todayIsDisplayedDate;
    }

    private ObservableList<EventFx> filterEvents(ObservableList<EventFx> originalList){

        ObservableList<EventFx> filteredList;

        Stream<EventFx> stream = originalList.stream().filter(a -> (a.getStartTime().getValue().getYear() == this.year)
                                    || (a.getEndTime().getValue().getYear() == this.year))
                          .filter(a -> (a.getStartTime().getValue().getMonthValue() == this.month)
                                    || (a.getEndTime().getValue().getMonthValue() == this.month))
                          .filter(a -> (a.getStartTime().getValue().getDayOfMonth() == this.dayOfMonth)
                                     || (a.getEndTime().getValue().getDayOfMonth() == this.dayOfMonth));

        filteredList = stream.collect(FXCollections::observableArrayList, List::add, List::addAll);

        return filteredList;
    }

}
