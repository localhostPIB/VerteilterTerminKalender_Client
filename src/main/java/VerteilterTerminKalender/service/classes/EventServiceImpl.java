package VerteilterTerminKalender.service.classes;

import VerteilterTerminKalender.controller.EventControllerRest;

import VerteilterTerminKalender.model.classes.EventInviteImpl;
import VerteilterTerminKalender.model.interfaces.EventFx;
import VerteilterTerminKalender.model.interfaces.EventInvite;
import VerteilterTerminKalender.service.interfaces.EventService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

import static VerteilterTerminKalender.util.FxUtil.convertMapToEventFx;

public class EventServiceImpl implements EventService {

   private EventControllerRest eventControllerRest = new EventControllerRest();
    private ObjectMapper mapper = new ObjectMapper();


    @Override
    public  ObservableList<EventFx> getAllEvents(String userid) {
       // ArrayList<EventFx> eventFxArrayList = new ArrayList<EventFx>();
        ObservableList<EventFx> eventInvitesList = FXCollections.observableArrayList();
        List<Map<String, Object>> dataAsMap = null;

        String result = eventControllerRest.getEventByUserId(userid);
        Map<String, Object> map = new HashMap<String, Object>();

        try {
             dataAsMap = mapper.readValue(result, List.class);


            Iterator<Map<String, Object>> iterator = dataAsMap.listIterator();
            while (iterator.hasNext()){
                Map<String, Object> eventMap = iterator.next();

                EventFx eventFx = convertMapToEventFx(eventMap);
               // eventFxArrayList.add(eventFx);
                eventInvitesList.add(eventFx);

            }


            // map = mapper.readValue(result, new TypeReference<Map<String, String>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }

        return eventInvitesList;
    }


    //TODO Brauchen wir das überhaupt?
    @Override
    public String getEventByUserId(String userId){
        String response = eventControllerRest.getEventByUserId(userId);
        return  response;
    }

    @Override
    public String deleteEventFx(int eventId) {
        String result = eventControllerRest.deleteEventByUserId(eventId);
        return result;

    }

    @Override
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

    @Override
    public int newEventInvite(EventInvite eventInvite){
        try {
            String jsonInString = mapper.writeValueAsString(eventInvite);
            Response response = eventControllerRest.newEvent(jsonInString);
            return response.getStatus();

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return -1;
    }




    @Override
    public int modifyEventFx(EventFx event) {
        ObjectMapper mapper = new ObjectMapper();

        ObjectNode eventAsJsonObject = mapper.createObjectNode();

        //userId

        eventAsJsonObject.put("eventId",event.getEventId().getValue());

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



        Response response = eventControllerRest.modifyEvent(eventAsJsonObject.toString());
        return response.getStatus();
    }




}
