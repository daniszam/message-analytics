package ru.itis.teamwork.server;

import org.glassfish.tyrus.server.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.itis.teamwork.App;

import javax.websocket.DeploymentException;

public class WebSocketServer {
    private static final Logger log = LoggerFactory.getLogger(App.class);

    private static final String SERVER_HOSTNAME = "localhost";
    private static final int SERVER_PORT = 8080;
    private static final String SERVER_CONTEXT_PATH = "/websocket";

    private Server server;

    public static final String SERVER_ADDRESS =
            "ws://" + SERVER_HOSTNAME + ":" + SERVER_PORT + SERVER_CONTEXT_PATH;

    /**
     * Starts the server executing.
     *
     * @throws DeploymentException if there was an error starting the server and
     *                             deploying the server websocket endpoint to it.
     */
    public void start() throws DeploymentException {
        try {
            log.info("Starting server for " + SERVER_ADDRESS);
            server = new Server(
                    SERVER_HOSTNAME,
                    SERVER_PORT,
                    SERVER_CONTEXT_PATH,
                    null,
                    WebSocketServerEndpoint.class
            );
            server.start();
        } catch (DeploymentException e) {
            server = null;
            throw e;
        }
    }

    /**
     * Shuts down the server.
     */
    public void stop() {
        if (server != null) {
            log.info("Stopping server for " + SERVER_ADDRESS);

            server.stop();
        }
    }
}
