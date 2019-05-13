package ru.itis.teamwork.client;

import javafx.concurrent.Task;
import org.glassfish.tyrus.client.ClientManager;
import ru.itis.teamwork.server.WebSocketServer;

import javax.websocket.DeploymentException;
import java.io.IOException;
import java.net.URI;
import java.util.concurrent.TimeoutException;

public class MessagesTask extends Task<String> {
    private static final String SERVER_ENDPOINT_ADDRESS =
            WebSocketServer.SERVER_ADDRESS + "/hello";

    private final String name;

    /**
     * Creates a new task for server communication.
     *
     * @param name the request name to send to the server.
     */
    public MessagesTask(String name) {
        this.name = name;
    }

    /**
     * Sends the requested name passed in the Task constructor to the server endpoint.
     * A new connection is established for the request.
     *
     * @return the response from the server.
     * @throws IOException      if there was an error communication with the server.
     * @throws TimeoutException if communication with the server timed out before a response was received.
     */
    @Override
    protected String call() throws IOException, TimeoutException {
        String response = null;

        WSClientEndpoint clientEndpoint = new WSClientEndpoint(
                name
        );

        try {
            ClientManager client = ClientManager.createClient();
            client.connectToServer(
                    clientEndpoint,
                    URI.create(SERVER_ENDPOINT_ADDRESS)
            );

            response = clientEndpoint.getResponse();
        } catch (DeploymentException e) {
            throw new IOException(e);
        } catch (InterruptedException e) {
            Thread.interrupted();
        }

        return response;
    }

}
