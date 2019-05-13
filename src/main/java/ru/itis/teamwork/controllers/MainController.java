package ru.itis.teamwork.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.itis.teamwork.client.VkService;

public class MainController {
    private static final Logger log = LoggerFactory.getLogger(MainController.class);
    private static final String DEFAULT_NAME = "mysterious person";

    private VkService vkService = new VkService();

    /**
     * Event handler invoked when the user submits their name.
     *
     * Invokes an asynchronous task which will communicate
     * the user's name to the server and update the message
     * label with the server's response.
     *
     * If invoked again before an in progress task completes,
     * the in progress task is cancelled and a new task is issued
     * with the current value of the name fields.
     */
    /*@FXML
    private void sayHello() {
        messageLabel.setText("");

        String name = createFullName();
        vkService.setName(name);

        vkService.setOnSucceeded(event -> {
            log.debug(
                    "Said hello to " + name + ", response " + vkService.getValue()
            );

            messageLabel.setText(
                    vkService.getValue()
            );
        });

        vkService.setOnFailed(event ->
                log.error(
                        "Unable to say hello to " + name,
                        vkService.getException()
                )
        );

        vkService.restart();
    }*/

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Text actionTarget;

    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) {
        actionTarget.setText("Sign in button pressed");
    }


    /**
     * Helper function which constructs the user's full name
     * from their input first and last names.
     *
     * @return the user's full name (or a default name if the user has not entered any name).
     */
    /*private String createFullName() {
        StringBuilder builder = new StringBuilder();

        String firstName = firstNameField.getText();
        String lastName  = lastNameField.getText();

        if (!StringUtils.isEmpty(firstName)) {
            builder.append(firstName);
        }

        if (!StringUtils.isEmpty(lastName)) {
            if (builder.length() > 0) {
                builder.append(" ");
            }
            builder.append(lastName);
        }

        if (builder.length() == 0) {
            builder.append(DEFAULT_NAME);
        }

        return builder.toString();
    }*/
}
