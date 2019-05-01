import client.Config;
import client.objectUI.MessageItem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;


public class MyCtr {

    @FXML
    private ScrollPane scroll;

    @FXML
    private VBox vbox;

    @FXML
    void initialize() {
//        HBox hBoxWrap = new HBox();
//        ImageView imageView = new ImageView();
//        imageView.setImage(new Image(Config.getAvatarMan().toString()));
//        imageView.setFitWidth(50);
//        imageView.setFitHeight(50);
//        VBox vBox = new VBox();
//        vBox.getChildren().add(new Label("abc"));
//        vBox.getChildren().add(new Label("123456"));
//        hBoxWrap.getChildren().addAll(imageView,vBox);
//
//        vbox.getChildren().add(hBoxWrap);
    }

    @FXML
    void Add(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(Config.getPathViewMessageItem());
        try {
            HBox hBox = new HBox();
            loader.setRoot(hBox);
            hBox = loader.load();
            for (int i=0;i<100;i++) {
                vbox.getChildren().add(new HBox(hBox));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
