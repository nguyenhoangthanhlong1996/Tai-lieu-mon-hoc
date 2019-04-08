package client.controllers;



import client.Config;
import client.object.Conversation;
import client.object.Message;
import client.object.Timeline;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;


public class Chat {

    @FXML
    private ImageView ivMyAvatar;

    @FXML
    private Circle circleMyStatus;

    @FXML
    private Label lblMyName;

    @FXML
    private JFXTextField txtSearch;

    @FXML
    private VBox vboxListConversation;

    @FXML
    private ImageView ivAvatar;

    @FXML
    private Circle circleStatus;

    @FXML
    private Label lblName;

    @FXML
    private Label lblLastVisit;

    @FXML
    private ScrollPane scrollViewChat;

    @FXML
    private VBox vboxViewChat;

    @FXML
    private JFXTextArea txtInputMessage;

    //Hàm khởi chạy khi giao diện được load lên
    @FXML
    void initialize() {
        vboxViewChat.prefWidthProperty().bind(scrollViewChat.widthProperty());
        vboxListConversation.getChildren().add(new Conversation("","thanhlong", "Nguyễn Hoàng Thanh Long","xin chào bạn","2 ngày", 50));
        vboxListConversation.getChildren().add(new Conversation("","thihoa", "Nguyễn Thị hoa","Alo alo à á ằ","15 phút", 3));
        vboxListConversation.getChildren().add(new Conversation("","group", "abc def","123 5465 465awea2","1 tuần", 2));

        vboxViewChat.getChildren().add(new Timeline("10:30 08/04/2019"));

        vboxViewChat.getChildren().add(new Message("", "sdj fmsdfhe4rer43  545 45","20:15","", true));
        vboxViewChat.getChildren().add(new Message("", "ewefwefwef wefwef","12:12","Đã xem", false));
        vboxViewChat.getChildren().add(new Message("", "ewefwefwef wefwef","12:12","Đã xem", false));
        vboxViewChat.getChildren().add(new Timeline("10:30 08/04/2019"));
        vboxViewChat.getChildren().add(new Message("", "ewefwefwef wefwef","12:12","Đã xem", false));
        vboxViewChat.getChildren().add(new Message("", "ewefwefwef wefwef","12:12","Đã xem", false));
        vboxViewChat.getChildren().add(new Timeline("10:30 08/04/2019"));
        vboxViewChat.getChildren().add(new Message("", "ewefwefwef wefwef","12:12","Đã xem", true));
        vboxViewChat.getChildren().add(new Message(Config.getAvatarWoman().toString(), "ewefwefwef wefwef","12:12","Đã xem", false));
        vboxViewChat.getChildren().add(new Message(Config.getAvatarGroup().toString(), "ewefwefwef wefwef","12:12","Đã xem", true));

        scrollViewChat.setVvalue(scrollViewChat.getVmax());
    }

    @FXML
    void onKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            System.out.println("Bạn vừa nhấn enter");
        }
    }
}

