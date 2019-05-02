package client.objectUI;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;

public class UserSelect {
    SimpleStringProperty username;
    CheckBox select;

    public UserSelect(String username) {
        this.username = new SimpleStringProperty(username);
        this.select = new CheckBox();
    }

    public String getUsername() {
        return username.get();
    }

    public SimpleStringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public CheckBox getSelect() {
        return select;
    }

    public void setSelect(CheckBox select) {
        this.select = select;
    }
}
