package ru.itis.teamwork.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/hello")
public class WebSocketServerEndpoint {
    private static final Logger log = LoggerFactory.getLogger(WebSocketServerEndpoint.class);

    /**
     * Service a hello request.
     *
     * @param session the current websocket session.
     * @param message a name to which the server should respond hello to.
     * @return the response string "Hello " + message;
     */
    @OnMessage
    public String onMessage(Session session, String message) {
        log.debug("WebSocketServer received request for: " + message + " being processed for session " + session.getId());

        return "Hello " + message;
    }

    /**
     * Logs an error if one is detected.
     *
     * @param session the websocket session to which is in error.
     * @param throwable the detected error to be logged.
     */
    @OnError
    public void onError(Session session, Throwable throwable) {
        log.error("WebSocketServer encountered error for session " + session.getId(), throwable);
    }
}