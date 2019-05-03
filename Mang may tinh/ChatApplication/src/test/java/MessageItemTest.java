
import client.Config;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class MessageItemTest extends HBox {
    BorderPane borderPane;
    ImageView ivAvatar;
    VBox vBox;
    HBox hBox;
    StackPane stackPane1;
    StackPane stackPane2;
    Label lblContent;
    Label lblTime;
    Label lblStatus;
    Font font;

    public MessageItemTest() {
        init();
        //Phần tử chính
        setPadding(new Insets(0, 10, 10, 10));
        setSpacing(5);
        setMinWidth(USE_COMPUTED_SIZE);
        setMinHeight(USE_COMPUTED_SIZE);
        setMaxWidth(USE_COMPUTED_SIZE);
        setMaxHeight(USE_COMPUTED_SIZE);
        setPrefHeight(USE_COMPUTED_SIZE);
        setPrefWidth(USE_COMPUTED_SIZE);
        //Phần tử bên trái
        borderPane.setMaxHeight(USE_PREF_SIZE);
        borderPane.setMaxWidth(USE_PREF_SIZE);
        borderPane.setMinHeight(USE_PREF_SIZE);
        borderPane.setMinWidth(USE_PREF_SIZE);
        borderPane.setPrefHeight(71);
        borderPane.setPrefWidth(62);
        ivAvatar.setFitHeight(50);
        ivAvatar.setFitWidth(50);
        setPickOnBounds(true);
        ivAvatar.setImage(new Image(Config.getAvatarMan().toString()));
        //Phần tử bên phải
        vBox.setAlignment(Pos.CENTER_LEFT);
        vBox.setSpacing(5);
        vBox.setStyle("-fx-background-color: white; -fx-background-radius: 15;");
        vBox.setPadding(new Insets(10,10,10,10));
        lblContent.setText("sjdhfnm sdfmsdfm sdjfb smd fsdjnf dsf hsdkj faksdj fakdsjadksjf hadskj fhasdiuf hadfkjahdfliasdkfhadkjfadsfkadfalkdsfa  ad fadfasd fasd f asdfasd fas df asdf asd fa sdf asd fasd f");
        lblContent.setWrapText(true);
        lblContent.setFont(font);
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setPrefHeight(5);
        //StackPane1
        stackPane1.setMinWidth(70);
        lblTime.setText("22:31");
        lblTime.setTextFill(Color.web("#1c07ff"));
        lblTime.setWrapText(true);
        StackPane.setAlignment(lblTime,Pos.CENTER_LEFT);
        lblTime.setFont(font);
        //StackPane2
        stackPane2.setAlignment(Pos.CENTER_RIGHT);
        stackPane2.setMinWidth(70);
        stackPane2.setPrefHeight(150);
        HBox.setHgrow(stackPane2, Priority.ALWAYS);
        lblStatus.setAlignment(Pos.CENTER_RIGHT);
        lblStatus.setTextFill(Color.web("#1c07ff"));
        lblStatus.setWrapText(true);
        lblStatus.setFont(font);
        lblStatus.setText("");


    }

    void init() {
        borderPane = new BorderPane();
        ivAvatar = new ImageView();
        vBox = new VBox();
        hBox = new HBox();
        stackPane1 = new StackPane();
        stackPane2 = new StackPane();
        lblContent = new Label();
        lblTime = new Label();
        lblStatus = new Label();
        font = new Font("Courier New",15);
        //Thêm vào phần tử chính
        getChildren().addAll(borderPane,vBox);
        //Thêm vào phần tử BorderPane
        BorderPane.setAlignment(ivAvatar, Pos.CENTER);
        borderPane.setTop(ivAvatar);
        //Thêm vào phần tử Vbox
        vBox.getChildren().addAll(lblContent, hBox);
        //Thêm vào phần tử Hbox
        hBox.getChildren().addAll(stackPane1,stackPane2);
        //Thêm vào phần tử StackPane1
        stackPane1.getChildren().add(lblTime);
        //Thêm vào phần tử StackPane2
        stackPane2.getChildren().add(lblStatus);
    }
}
