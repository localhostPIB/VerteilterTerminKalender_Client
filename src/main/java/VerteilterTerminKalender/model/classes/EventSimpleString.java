package VerteilterTerminKalender.model.classes;

import VerteilterTerminKalender.view.model.EvenSimplerStringProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Wrapper-Class for EventFx-Objects
 * Used for printing the contents of EventFx-Objects
 * inside a Tableview (JavaFX) automatically
 * @author Michelle Blau
 */

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
