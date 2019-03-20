package VerteilterTerminKalender.service.classes;

import VerteilterTerminKalender.controller.EventInviteControllerRest;
import VerteilterTerminKalender.model.interfaces.EventDecline;
import VerteilterTerminKalender.model.interfaces.EventInvite;
import VerteilterTerminKalender.model.interfaces.EventParticipate;
import VerteilterTerminKalender.service.interfaces.EventDeclineService;
import VerteilterTerminKalender.service.interfaces.EventInviteService;
import VerteilterTerminKalender.service.interfaces.EventParticipateService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

import static VerteilterTerminKalender.builders.ModelObjectBuilder.*;
import static VerteilterTerminKalender.builders.ServiceObjectBuilder.getEventDeclineService;
import static VerteilterTerminKalender.builders.ServiceObjectBuilder.getEventParticipateService;

public class EventInviteServiceImpl implements EventInviteService {

   private EventInviteControllerRest eventInviteControllerRest = new EventInviteControllerRest();
   private EventParticipateService eventParticipateService = getEventParticipateService();
   private EventDeclineService eventDeclineService = getEventDeclineService();
    private ObjectMapper mapper = new ObjectMapper();


    @Override
    public ObservableList<EventInvite> getAllEventInviteByUserId(String userId) {

        ObservableList<EventInvite> eventInvitesList = null;

        String result = eventInviteControllerRest.getAllEventInviteByUserId(userId);

        try {
            List<EventInvite> eventInviteList = mapper.readValue(result, new TypeReference<List<EventInvite>>() {
            });
            eventInvitesList = FXCollections.observableArrayList(eventInviteList);

            return eventInvitesList;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    //TODO welche Rückgabe?
    @Override
    public void sendInviteToUsers(int eventId, int[] userIds){
        for(int i =0; i<userIds.length;i++){

            EventInvite eventInvite = getEventInviteObject();
            eventInvite.setEventId(eventId);
            eventInvite.setUserId(userIds[i]);
            newEventInvite(eventInvite);

        }

    }

    @Override
    public int newEventInvite(EventInvite eventInvite){
        try {
            String jsonInString = mapper.writeValueAsString(eventInvite);
            Response response = eventInviteControllerRest.newEventInvite(jsonInString);
            return response.getStatus();

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return -1;
    }


    //TODO Methode newParticipate ist noch nicht implementiert
    @Override
    public void acceptInvite(EventInvite eventInvite){
        EventParticipate eventParticipate = getEventParticipateObject();
        eventParticipate.setUserId(eventInvite.getUserId());
        eventParticipate.setEventId(eventInvite.getEventId());

        eventParticipateService.newParticipate(eventParticipate);
        //TODO löschen


    }

    @Override
    public int declineInvite(EventInvite eventInvite){
        EventDecline eventDecline = getEventDeclineObject();

        eventDecline.setUserId(eventInvite.getUserId());
        eventDecline.setEventId(eventInvite.getEventId());

        Response response = eventDeclineService.newEventDecline(eventDecline);
        //TODO Invitation löschen
        return response.getStatus();

    }




}
