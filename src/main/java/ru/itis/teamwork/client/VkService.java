package ru.itis.teamwork.client;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class VkService extends Service<String> {
    private final StringProperty name = new SimpleStringProperty(this, "name");

    /**
     * The name property is set as an input parameter for a service execution.
     * @return the name property.
     */
    public final StringProperty nameProperty() { return name; }
    public final void setName(String value) { name.set(value); }
    public final String getName() { return name.get(); }

    @Override
    protected Task<String> createTask() {
        return new MessagesTask(getName());
    }
}
