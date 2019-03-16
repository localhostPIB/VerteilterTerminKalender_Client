package VerteilterTerminKalender.service.classes;

import VerteilterTerminKalender.controller.EventControllerRest;
import VerteilterTerminKalender.model.classes.EventFxImpl;
import VerteilterTerminKalender.model.interfaces.EventFx;
import VerteilterTerminKalender.service.interfaces.EventService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import javax.ws.rs.core.Response;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

import static VerteilterTerminKalender.util.FxUtil.convertMapToEventFx;

public class EventServiceImpl implements EventService {

    EventControllerRest eventControllerRest = new EventControllerRest();
    ObjectMapper mapper = new ObjectMapper();

    public ArrayList<EventFx> getAllEvents(String userid) {
        ArrayList<EventFx> eventFxArrayList = new ArrayList<EventFx>();
        List<Map<String, Object>> dataAsMap = null;

        String result = eventControllerRest.getEventByUserId(userid);
        Map<String, Object> map = new HashMap<String, Object>();

        try {
             dataAsMap = mapper.readValue(result, List.class);


            Iterator<Map<String, Object>> iterator = dataAsMap.listIterator();
            while (iterator.hasNext()){
                Map<String, Object> eventMap = iterator.next();

                EventFx eventFx = convertMapToEventFx(eventMap);
                eventFxArrayList.add(eventFx);

            }


            // map = mapper.readValue(result, new TypeReference<Map<String, String>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }



        return eventFxArrayList;
    }

    public String getEventByUserId(String userId){
        String response = eventControllerRest.getEventByUserId(userId);
        return  response;
    }

    public String deleteEvent(int eventid) {

        String response = eventControllerRest.deleteEventByUserId(eventid);

        return response;

        }

    public String findIdByEmail(String email) {
        String response = eventControllerRest.findIdByEmail(email);
        System.out.println(response);
        return response;
    }


    public int newEvent(EventFx event){
        ObjectMapper mapper = new ObjectMapper();

        ObjectNode eventAsJsonObject = mapper.createObjectNode();

        //location
        eventAsJsonObject.put("location", event.getLocation().getValue());

        //startTime
        ArrayNode startTime = eventAsJsonObject.putArray("startTime");
        LocalDateTime startTimeDate = event.getStartTime().getValue();
        startTime.add(startTimeDate.getYear());
        startTime.add(startTimeDate.getMonthValue());
        startTime.add(startTimeDate.getDayOfMonth());
        startTime.add(startTimeDate.getHour());
        startTime.add(startTimeDate.getMinute());

        //endTime
        ArrayNode endTime = eventAsJsonObject.putArray("endTime");
        LocalDateTime endTimeDate = event.getStartTime().getValue();
        endTime.add(endTimeDate.getYear());
        endTime.add(endTimeDate.getMonthValue());
        endTime.add(endTimeDate.getDayOfMonth());
        endTime.add(endTimeDate.getHour());
        endTime.add(endTimeDate.getMinute());

        //allDay
        eventAsJsonObject.put("allDay", event.getAllDay().getValue());

        //repeat
        eventAsJsonObject.put("repeat", event.getRepeat().getValue());

        //note
        eventAsJsonObject.put("note", event.getNote().getValue());

        //userId
        eventAsJsonObject.put("userId", event.getUserId().getValue());


        Response response = eventControllerRest.newEvent(eventAsJsonObject.toString());
            return response.getStatus();
    }

    public static void main(String[] args) {
        EventServiceImpl es = new EventServiceImpl();
        es.findIdByEmail("john@smith.com");
        //es.getEventByUserId("101");
    }

}
