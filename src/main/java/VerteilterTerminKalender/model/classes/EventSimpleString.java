package VerteilterTerminKalender.model.classes;

import VerteilterTerminKalender.view.model.EvenSimplerStringProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventSimpleString {
    private EvenSimplerStringProperty eventId;
    private EvenSimplerStringProperty location;

    private EvenSimplerStringProperty startTime;
    private EvenSimplerStringProperty endTime;
    private EvenSimplerStringProperty allDay;
    private EvenSimplerStringProperty repeat;
    private EvenSimplerStringProperty note;
    private EvenSimplerStringProperty userId;
}
