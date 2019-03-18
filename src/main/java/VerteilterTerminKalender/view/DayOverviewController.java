package VerteilterTerminKalender.view;

import VerteilterTerminKalender.MainApp;
import VerteilterTerminKalender.model.interfaces.EventFx;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.util.GregorianCalendar;
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
    private ObservableList<EventFx> eventsOfTheDay;

    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
    }

    public void setup(GregorianCalendar today){
        this.today = today;
        this.dayOfMonth = today.get(GregorianCalendar.DAY_OF_MONTH);
        this.month = today.get(GregorianCalendar.MONTH) + 1;
        this.year = today.get(GregorianCalendar.YEAR);

        ObservableList<EventFx> allEvents = mainApp.getEventFXList();
        ListIterator<EventFx> iterator;

        Stream<EventFx> stream = allEvents.stream().filter(a -> (a.getStartTime().getValue().getYear() == this.year)
                                    || (a.getEndTime().getValue().getYear() == this.year))
                          .filter(a -> (a.getStartTime().getValue().getMonthValue() == this.month)
                                    || (a.getEndTime().getValue().getMonthValue() == this.month))
                          .filter(a -> (a.getStartTime().getValue().getDayOfMonth() == this.dayOfMonth)
                                    || (a.getEndTime().getValue().getDayOfMonth() == this.dayOfMonth));

        this.eventsOfTheDay = FXCollections.observableArrayList();

        eventsOfTheDay = stream
                .collect(() -> FXCollections.observableArrayList(), (c,e) -> c.add(e), (c1,c2) -> c1.addAll(c2));

        iterator = eventsOfTheDay.listIterator();

        labelDayOfMonth.setText(Integer.toString(dayOfMonth));

        for(int i = 1; i <= 3; i++){
            if(iterator.hasNext()){
                String previewEventName = iterator.next().toString();
                Label labelPreviewEvent = new Label(previewEventName);
                BorderPane centerTheLabel = new BorderPane();
                centerTheLabel.setCenter(labelPreviewEvent);
                vBoxPreviewEvents.getChildren().add(centerTheLabel);
            }
        }
    }
}
