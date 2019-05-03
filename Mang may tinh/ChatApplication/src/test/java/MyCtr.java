import client.Config;
import client.objectUI.MessageItem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.awt.*;
import java.io.IOException;


public class MyCtr {

    @FXML
    private ScrollPane scroll;

    @FXML
    private VBox vbox;

    @FXML
    void initialize() {

    }

    @FXML
    void Add(ActionEvent event) {
        HBox wrap = new HBox();
        ImageView ivAvatar= new ImageView();
        ivAvatar.setImage(new Image(Config.getAvatarMan().toString()));
        ivAvatar.setFitHeight(70);
        ivAvatar.setFitWidth(70);
        VBox vboxMessage = new VBox();
        vboxMessage.setAlignment(Pos.CENTER_LEFT);
        vboxMessage.setPadding(new Insets(0,0,0,10));
        vboxMessage.setBackground(new Background(new BackgroundFill(Color.web("#fff"), CornerRadii.EMPTY,Insets.EMPTY)));
        Label lblContent = new Label("abc def");
        Label lblTime = new Label("12:34");
        vboxMessage.getChildren().addAll(lblContent, lblTime);
        wrap.getChildren().addAll(ivAvatar,vboxMessage);
        vbox.getChildren().addAll(wrap);
    }
}
