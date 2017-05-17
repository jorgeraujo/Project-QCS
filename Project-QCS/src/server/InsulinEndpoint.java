package server;

import server.Insulin;
import javax.xml.ws.Endpoint;

public class InsulinEndpoint {
	public static void main(String[] args) {
		Insulin insulin = new Insulin();
		Endpoint endpoint = Endpoint.publish("http://localhost:8081/insulin", insulin);
	}
}
