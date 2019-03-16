package VerteilterTerminKalender.sse;

import VerteilterTerminKalender.MainApp;
import VerteilterTerminKalender.model.interfaces.EventInvite;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.sse.InboundSseEvent;
import javax.ws.rs.sse.SseEventSource;
import java.util.function.Consumer;

import static VerteilterTerminKalender.util.FxUtil.convertMapToEventInvite;

/**
 * Client side example for consuming SSE
 */
public class SSEClient
{

	public static void sseCient(int userId, int lastinviteid) throws Exception {


		//last invite id should be latest id of EventInvite / Invitation - Object in the CLIENT-Side,
		//if none just put 0


		String url = "http://127.0.0.1:8000/sse/invitation/" + userId + "?lastinviteid=" + lastinviteid;

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
		MainApp mainApp = MainApp.getMainApp();
		EventInvite eventInvite = convertMapToEventInvite(data);

		mainApp.setEventInvites(eventInvite);


	};

	//Error
	private static Consumer<Throwable> onError = Throwable::printStackTrace;

	//Connection close and there is nothing to receive
	private static Runnable onComplete = () -> {

	};

}
