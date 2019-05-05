package client.objectUI;

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
import share.util.Base64Utils;

import java.io.IOException;


public class MessageItem extends HBox {
    BorderPane borderPane;
    ImageView ivAvatar;
    VBox vBox;
    HBox hBox;
    StackPane stackPane1;
    StackPane stackPane2;
    Label lblContent;
    Label lblTime;
    Label lblStatus;
    Font font1;
    Font font2;

    public MessageItem(String avatar, String content, String time, String status, boolean isMyMessage) {
        init();
        if (avatar != null) {
            if (!avatar.equals("")) {
                try {
                    ivAvatar.setImage(Base64Utils.getImageFromBase64String(avatar));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                ivAvatar.setImage(new Image(Config.getAvatarUnknow().toString()));
            }
        } else {
            ivAvatar.setImage(null);
        }
        lblContent.setText(content);
        lblTime.setText(time);
        lblStatus.setText(status);
        if (isMyMessage) {
            setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        } else {
            setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        }
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
        font1 = new Font("Courier New", 14);
        font2 = new Font("Courier New", 17);
        //Thêm vào phần tử chính
        getChildren().addAll(borderPane, vBox);
        //Thêm vào phần tử BorderPane
        BorderPane.setAlignment(ivAvatar, Pos.CENTER);
        borderPane.setTop(ivAvatar);
        //Thêm vào phần tử Vbox
        vBox.getChildren().addAll(lblContent, hBox);
        //Thêm vào phần tử Hbox
        hBox.getChildren().addAll(stackPane1, stackPane2);
        //Thêm vào phần tử StackPane1
        stackPane1.getChildren().add(lblTime);
        //Thêm vào phần tử StackPane2
        stackPane2.getChildren().add(lblStatus);
        //Thiết lập phần tử Hbox chính
        setPadding(new Insets(0, 10, 10, 10));
        setSpacing(5);
        setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        setMinWidth(USE_COMPUTED_SIZE);
        setMinHeight(USE_COMPUTED_SIZE);
        setMaxWidth(USE_COMPUTED_SIZE);
        setMaxHeight(USE_COMPUTED_SIZE);
        setPrefHeight(USE_COMPUTED_SIZE);
        setPrefWidth(USE_COMPUTED_SIZE);
        //Thiết lập borderpane
        borderPane.setMaxHeight(USE_PREF_SIZE);
        borderPane.setMaxWidth(USE_PREF_SIZE);
        borderPane.setMinHeight(USE_PREF_SIZE);
        borderPane.setMinWidth(USE_PREF_SIZE);
        borderPane.setPrefHeight(71);
        borderPane.setPrefWidth(62);
        //Thiết lập ivAvatar
        ivAvatar.setFitHeight(50);
        ivAvatar.setFitWidth(50);
        setPickOnBounds(true);
        //Thiết lập phần tử Vbox
        vBox.setAlignment(Pos.CENTER_LEFT);
        vBox.setSpacing(5);
        vBox.setStyle("-fx-background-color: white; -fx-background-radius: 15;");
        vBox.setPadding(new Insets(10, 10, 10, 10));
        //Thiết lập lblContent
        lblContent.setWrapText(true);
        lblContent.setFont(font2);
        //Thiết lập Hbox
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setPrefHeight(5);
        //Thiết lập StackPane1
        stackPane1.setMinWidth(70);
        //Thiết lập lblTime
        lblTime.setTextFill(Color.web("#1c07ff"));
        lblTime.setWrapText(true);
        StackPane.setAlignment(lblTime, Pos.CENTER_LEFT);
        lblTime.setFont(font1);
        //Thiết lập StackPane2
        stackPane2.setAlignment(Pos.CENTER_RIGHT);
        stackPane2.setMinWidth(70);
        stackPane2.setPrefHeight(150);
        HBox.setHgrow(stackPane2, Priority.ALWAYS);
        //Thiết lập lblStatus
        lblStatus.setAlignment(Pos.CENTER_RIGHT);
        lblStatus.setTextFill(Color.web("#1c07ff"));
        lblStatus.setWrapText(true);
        lblStatus.setFont(font1);
    }
}
