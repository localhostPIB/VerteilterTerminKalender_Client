package VerteilterTerminKalender.sse;

import VerteilterTerminKalender.MainApp;
import VerteilterTerminKalender.model.interfaces.EventInvite;
import javafx.collections.ObservableList;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.sse.InboundSseEvent;
import javax.ws.rs.sse.SseEventSource;
import java.util.List;
import java.util.function.Consumer;

import static VerteilterTerminKalender.constants.Configuration.BASE_URL;
import static VerteilterTerminKalender.util.FxUtil.convertJsonStringToEventInviteListe;

/**
 * Client side example for consuming SSE
 */
public class SSEClient
{

	public static void sseCient(int userId, int lastinviteid) throws Exception {


		//last invite id should be latest id of EventInvite / Invitation - Object in the CLIENT-Side,
		//if none just put 0


		String url = BASE_URL +"/sse/invitation/" + userId + "?lastinviteid=" + lastinviteid;

		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(url);
		SseEventSource eventSource = SseEventSource.target(target).build();

			eventSource.register(onEvent, onError, onComplete);
			eventSource.open();

			//Consuming events for one hour, uncomment these to consume events indefinitely

		//client.close();
		//System.out.println("End");
	}

	// A new event is received
	private static Consumer<InboundSseEvent> onEvent = (inboundSseEvent) -> {
		String data = inboundSseEvent.readData();
		System.out.println(data);
		MainApp mainApp = MainApp.getMainApp();
		ObservableList<EventInvite> eventInvitesList = mainApp.getEventInvitesList();

		List<EventInvite> EventInviteList = convertJsonStringToEventInviteListe(data);

		

		//mainApp.setEventInvites(eventInvite);


	};

	//Error
	private static Consumer<Throwable> onError = Throwable::printStackTrace;

	//Connection close and there is nothing to receive
	private static Runnable onComplete = () -> {

	};

}
