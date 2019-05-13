package ru.itis.teamwork;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.itis.teamwork.server.WebSocketServer;

import javax.websocket.DeploymentException;


public class App extends Application {
    private static final Logger log = LoggerFactory.getLogger(App.class);

    private static final String MAIN_FXML_FILE = "/styles/fxml/main.fxml";

    private WebSocketServer server;

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Starts a local websocket server for testing purposes
     */
    public void init() throws DeploymentException {
        server = new WebSocketServer();
        server.start();
    }

    /**
     * Stops the local websocket server.
     */
    public void stop() {
        server.stop();
    }

    public void start(Stage stage) throws Exception {
        log.info("Starting Hello JavaFX WebSocket demonstration application");

        log.debug("Loading FXML for main view from: {}", MAIN_FXML_FILE);
        FXMLLoader loader = new FXMLLoader();
        Parent rootNode = loader.load(
                getClass().getResourceAsStream(
                        MAIN_FXML_FILE
                )
        );

        log.debug("Showing JavaFX scene");
        Scene scene = new Scene(rootNode, 350, 275);
        //scene.getStylesheets().add(APPLICATION_STYLE_SHEET);

        stage.setTitle("Hello JavaFX WebSockets");
        stage.setScene(scene);
        stage.show();
    }
}
