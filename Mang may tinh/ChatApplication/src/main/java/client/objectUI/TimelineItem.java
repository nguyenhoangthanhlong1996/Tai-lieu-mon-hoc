package client.objectUI;

import client.Config;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class TimelineItem extends StackPane {

    String time;
    @FXML Label lblTime;

    public TimelineItem(String time) {
        FXMLLoader loader = new FXMLLoader(Config.getPathViewTimelineItem());
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
            setTime(time);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
        lblTime.setText(time);
    }
}
