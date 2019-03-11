package VerteilterTerminKalender.util;

import VerteilterTerminKalender.model.classes.EventFxImpl;
import VerteilterTerminKalender.model.interfaces.EventFx;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FxUtil {

    public static EventFx convertMapToEventFx(Map<String, Object> eventMap){
        EventFx eventFx = new EventFxImpl();

        //EventId
        IntegerProperty eventId = new SimpleIntegerProperty();
        eventId.setValue(Integer.valueOf(eventMap.get("eventId").toString()));
        eventFx.setEventId(eventId);

        //Location

        StringProperty location = new SimpleStringProperty();
        location.setValue(eventMap.get("location").toString());
        eventFx.setLocation(location);

        //StartTime
        StringProperty startTime = new SimpleStringProperty();
        ArrayList<Integer> startTimeArray = (ArrayList<Integer>) eventMap.get("startTime");
        String dateTimeString = convertMapWithTimeAndDateToString(startTimeArray);
        startTime.setValue(dateTimeString);
        eventFx.setStarttime(startTime);


        //endTime
        StringProperty endTime = new SimpleStringProperty();
        ArrayList<Integer> endTimeArray = (ArrayList<Integer>) eventMap.get("endTime");
        String endTimeString = convertMapWithTimeAndDateToString(endTimeArray);
        endTime.setValue(endTimeString);
        eventFx.setEndtime(endTime);


        //allDay
        StringProperty allDay = new SimpleStringProperty();
        allDay.setValue(eventMap.get("allDay").toString());
        eventFx.setAllday(allDay);


        //repeate
        IntegerProperty repeate = new SimpleIntegerProperty();
        repeate.setValue(Integer.valueOf(eventMap.get("repeat").toString()));
        eventFx.setRepeat(repeate);


        //note
        StringProperty note = new SimpleStringProperty();
        note.setValue(eventMap.get("note").toString());
        eventFx.setNote(note);

        //duration
        //TODO abklären in welchem Format. Danach implementieren

        return eventFx;
    }


    /**
     * Transfer a Map (from JSON) to a String
     * @param dateTimeArray
     * @return
     */
    public static String convertMapWithTimeAndDateToString(ArrayList<Integer> dateTimeArray){

        StringBuilder builder = new StringBuilder();

        //TODO Eventuell in einer For Schleife machen, wenn Format klar ist
        builder.append(dateTimeArray.get(0).toString());
        builder.append("-");
        builder.append(dateTimeArray.get(1).toString());
        builder.append("-");
        builder.append(dateTimeArray.get(2).toString());
        builder.append("-");
        builder.append(dateTimeArray.get(3).toString());
        builder.append("-");
        builder.append(dateTimeArray.get(4).toString());

       return builder.toString();

    }

}
