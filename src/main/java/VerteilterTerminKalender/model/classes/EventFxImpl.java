package VerteilterTerminKalender.model.classes;

import VerteilterTerminKalender.model.interfaces.EventFx;
import javafx.beans.property.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Implementation of event fx interface
 * This class represents a event object
 */
@Getter
@Setter
public class EventFxImpl implements EventFx {

    private IntegerProperty eventId;
    private StringProperty location;

    private ObjectProperty<LocalDateTime> startTime;
    private ObjectProperty<LocalDateTime> endTime;
    private BooleanProperty allDay;
    private IntegerProperty repeat;
    private StringProperty note;
    private IntegerProperty userId;


    public EventFxImpl() {
    }


    public EventFxImpl( String location, LocalDateTime startTime, LocalDateTime endTime, boolean allDay, int repeat, String note, int userId, int eventId){
        this.startTime = new SimpleObjectProperty();
        this.endTime = new SimpleObjectProperty();

        this.location = new SimpleStringProperty(location);
        this.startTime.set(startTime);
        this.endTime.set(endTime);
        this.allDay = new SimpleBooleanProperty(allDay);
        this.repeat = new SimpleIntegerProperty(repeat);
        this.note = new SimpleStringProperty(note);
        this.userId = new SimpleIntegerProperty(userId);
        this.eventId = new SimpleIntegerProperty(eventId);

    }

    public EventFxImpl( String location, LocalDateTime startTime, LocalDateTime endTime, boolean allDay, int repeat, String note, int userId){
        this.startTime = new SimpleObjectProperty();
        this.endTime = new SimpleObjectProperty();

        this.location = new SimpleStringProperty(location);
        this.startTime.set(startTime);
        this.endTime.set(endTime);
        this.allDay = new SimpleBooleanProperty(allDay);
        this.repeat = new SimpleIntegerProperty(repeat);
        this.note = new SimpleStringProperty(note);
        this.userId = new SimpleIntegerProperty(userId);
    }


    /**
     * Returns the 13 first characters of the "note"-attribute as well as
     * starttime and endtime
     * If "note" has more than 13 characters "..." will be added
     * @return string e.g. "Presentation 11:00-17:00"
     */
    @Override
    public String toString(){

        StringBuffer stringBuffer = new StringBuffer();
        String formattedNote = "";
        if(this.note.getValue().length() >= 13) {
            formattedNote = this.note.getValue().substring(0, 12);
            if(formattedNote.contains(" ")){
                String[] splitArray = formattedNote.split(" ");
                formattedNote = splitArray[0];
            }
            stringBuffer.append(formattedNote).append("...");
        }else{
            formattedNote = this.note.getValue();
            stringBuffer.append(formattedNote);
        }

        String starttime = this.startTime.getValue().toLocalTime().format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT));
        String endtime = this.endTime.getValue().toLocalTime().format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT));

        stringBuffer.append(" ").append(starttime).append("-").append(endtime);

        return stringBuffer.toString();
    }


    /**
     * Checks if this object is equal to a given object
     * @param o EventFx object
     * @return true, if both EventFx-Objects have the same eventId, else false
     */
    @Override
    public boolean equals(Object o){
        if (o instanceof EventFx){
            EventFx cmpEventFx = (EventFxImpl) o;
            int compareID = cmpEventFx.getEventId().getValue();
            int ownID = this.eventId.getValue();

            if (ownID == compareID){
                return true;
            }
        }
        return false;
    }


}
