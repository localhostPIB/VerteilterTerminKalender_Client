package VerteilterTerminKalender.model.classes;

import javafx.beans.property.*;
import lombok.Getter;
import lombok.Setter;
import VerteilterTerminKalender.model.interfaces.EventFx;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

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
//private StringProperty duration;

//TODO Brauchen wir die UserIds zusätzlich in den Terminen?
//private IntegerProperty userid;

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



    @Override
    public String toString(){
        //String formattedDate = this.startTime.getValue().toLocalDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
        //String resultString =  formattedDate + ", " + this.location.getValue();

        StringBuffer stringBuffer = new StringBuffer();
        String formattedNote = "";
        if(this.note.getValue().length() >= 13) {
            formattedNote = this.note.getValue().substring(0, 12);
            if(formattedNote.contains(" ")){
                String[] splitArray = formattedNote.split(" ");
                formattedNote = splitArray[0];
            }
            stringBuffer.append(formattedNote);
            stringBuffer.append("...");
        }else{
            formattedNote = this.note.getValue();
            stringBuffer.append(formattedNote);
        }

        String starttime = this.startTime.getValue().toLocalTime().format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT));
        String endtime = this.endTime.getValue().toLocalTime().format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT));


        stringBuffer.append(" ");
        stringBuffer.append(starttime);
        stringBuffer.append("-");
        stringBuffer.append(endtime);


        return stringBuffer.toString();
    }


    @Override
    public boolean equals(Object o){
        if (o instanceof EventFx){
            EventFx vergleichsEventFx = (EventFxImpl) o;
            int vergleichdsID = vergleichsEventFx.getEventId().getValue();
            int eigeneID = this.eventId.getValue();

            if (eigeneID == vergleichdsID){
                return true;
            }
        }
        return false;
    }




}
