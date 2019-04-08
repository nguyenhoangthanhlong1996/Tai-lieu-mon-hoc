package client.object;

import client.Config;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class Timeline extends StackPane {

    String time;
    @FXML Label lblTime;

    public Timeline(String time) {
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
