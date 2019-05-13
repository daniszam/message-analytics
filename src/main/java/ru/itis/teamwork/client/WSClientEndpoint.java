package ru.itis.teamwork.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.*;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@ClientEndpoint
public class WSClientEndpoint {
    private static final Logger log = LoggerFactory.getLogger(WSClientEndpoint.class);

    private final String name;
    private String response;
    private Throwable exception;

    private final CountDownLatch messageLatch = new CountDownLatch(1);

    private static final int REQUEST_TIMEOUT_SECS = 10;

    public WSClientEndpoint(String name) {
        this.name = name;
    }

    @OnOpen
    public void onOpen(Session session) {
        try {
            log.debug("Sending request: '" + name + "' with session " + session.getId());
            session.getBasicRemote().sendText(name);
        } catch (IOException e) {
            log.error("Unable to connect to hello server: ", e);
        }
    }

    @OnMessage
    public void processResponse(Session session, String message) {
        log.debug("Received response: '" + message + "' for request: '" + name + "' with session " + session.getId());
        response = message;
        messageLatch.countDown();
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        log.error("Communication error, saying hello to '" + name + "' with session " + session.getId(), throwable);
        exception = throwable;
        messageLatch.countDown();
    }

    /**
     * Blocks until either the server sends a response to the request, an communication error occurs
     * or the communication request times out.
     *
     * @return the server response message.
     * @throws TimeoutException     if the server does not respond before the timeout value is reached.
     * @throws InterruptedException if the communication thread is interrupted (e.g. thread.interrupt() is invoked on it for cancellation purposes).
     * @throws IOException          if a communication error occurs.
     */
    public String getResponse() throws TimeoutException, InterruptedException, IOException {
        if (messageLatch.await(REQUEST_TIMEOUT_SECS, TimeUnit.SECONDS)) {
            if (exception != null) {
                throw new IOException("Unable to say hello", exception);
            }
            return response;
        } else {
            throw new TimeoutException("Timed out awaiting server hello response for " + name);
        }
    }
}