package client.controllers;

import client.Config;
import client.objectUI.Contact;
import client.objectUI.Conversation;
import client.objectUI.Message;
import client.objectUI.Timeline;
import client.socket.SingletonConnect;
import client.stages.ClientApp;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import share.entity.User;
import share.protocol.Request;
import share.protocol.RequestType;
import share.util.Base64Utils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Chat implements EventHandler<MouseEvent> {
    //region Biến
    ClientApp app;
    SingletonConnect connect;
    Stage stage;
    Request request;
    double x = 0, y = 0;
    boolean isMaximize = false;
    //Danh sách các user
    List<User> listUser;
    //endregion

    //region FXML Đối tượng UI
    @FXML
    private ImageView ivMyAvatar;
    @FXML
    private Circle circleMyStatus;
    @FXML
    private Label lblMyName;
    @FXML
    private JFXTextField txtSearch;
    @FXML
    private ComboBox<String> cbbFilterConversation;
    @FXML
    private VBox vboxContacts;
    @FXML
    private VBox vboxListContact;
    @FXML
    private VBox vboxConversations;
    @FXML
    private VBox vboxListConversation;
    @FXML
    private VBox vboxConversation;
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
    @FXML
    private HBox titleBar;
    @FXML
    private VBox vboxContactMenu;
    @FXML
    private VBox vboxConversatonMenu;
    @FXML
    private MaterialDesignIconView ivSettingMenu;
    ContextMenu settingMenu;
    //endregion

    //region Phương thức khởi tạo
    public Chat(ClientApp app, Stage stage) {
        this.app = app;
        this.stage = stage;
        listUser = new ArrayList<>();
        connect = SingletonConnect.getInstance();
    }
    //endregion

    //region @FXML init
    //Hàm khởi chạy khi giao diện được load lên
    @FXML
    void initialize() {
        //Khung hiển thị cuộc hội thoại hiện tại ẩn đi
        vboxConversation.setVisible(false);
        //khung hiển thị danh sách các cuộc hội thoại ẩn đi
        vboxConversations.setVisible(false);
        //Gán ràng buộc kích thước chiều rộng khung hiển thị nội dung chat hiện tại bằng khung cuộn hiển thị (Khi khung cuộn thay đổi chiều rộng thì khung chat cũng thay đổi theo)
        vboxViewChat.prefWidthProperty().bind(scrollViewChat.widthProperty());
        //Tạo dữ liệu trong combobox lọc loại cuộc hội thoại
        cbbFilterConversation.getItems().addAll("Tất cả","Cuộc hội thoại riêng tư","Cuộc hội thoại nhóm");
        //Tạo menu cho nút cài đặt
        settingMenu = new ContextMenu();
        MenuItem item1 = new MenuItem("Thiết lập");
        item1.setOnAction(event -> System.out.println("Thiết lập"));
        MenuItem item2 = new MenuItem("Đăng xuất");
        //Chọn đăng xuất
        item2.setOnAction(event -> {
            request = new Request(RequestType.LOGOUT, null);
            connect.sendRequest(request);
        });
        settingMenu.getItems().addAll(item1, item2);

//        vboxListConversation.getChildren().add(new Conversation("","thanhlong", "Nguyễn Hoàng Thanh Long","xin chào bạn","2 ngày", 50));
//        vboxListConversation.getChildren().add(new Conversation("","thihoa", "Nguyễn Thị hoa","Alo alo à á ằ","15 phút", 3));
//        vboxListConversation.getChildren().add(new Conversation("","group", "abc def","123 5465 465awea2","1 tuần", 2));
//
//        vboxViewChat.getChildren().add(new Timeline("10:30 08/04/2019"));
//
//        vboxViewChat.getChildren().add(new Message("", "sdj fmsdfhe4rer43  545 45","20:15","", true));
//        vboxViewChat.getChildren().add(new Message("", "ewefwefwef wefwef","12:12","Đã xem", false));
//        vboxViewChat.getChildren().add(new Message("", "ewefwefwef wefwef","12:12","Đã xem", false));
//        vboxViewChat.getChildren().add(new Timeline("10:30 08/04/2019"));
//        vboxViewChat.getChildren().add(new Message("", "ewefwefwef wefwef","12:12","Đã xem", false));
//        vboxViewChat.getChildren().add(new Message("", "ewefwefwef wefwef","12:12","Đã xem", false));
//        vboxViewChat.getChildren().add(new Timeline("10:30 08/04/2019"));
//        vboxViewChat.getChildren().add(new Message("", "ewefwefwef wefwef","12:12","Đã xem", true));
//        vboxViewChat.getChildren().add(new Message(Config.getAvatarWoman().toString(), "ewefwefwef wefwef","12:12","Đã xem", false));
//        vboxViewChat.getChildren().add(new Message(Config.getAvatarGroup().toString(), "ewefwefwef wefwef","12:12","Đã xem", true));
//
//        scrollViewChat.setVvalue(scrollViewChat.getVmax());
    }
    //endregion

    //region @FXML Xử lý sự kiện thao tác với cửa sổ ứng dụng
    @FXML
    void titleBarDragged(MouseEvent event) {
        stage.setX(event.getScreenX() - x);
        stage.setY(event.getScreenY() - y);
    }

    @FXML
    void titleBarPresssed(MouseEvent event) {
        x = event.getSceneX();
        y = event.getSceneY();
    }

    @FXML
    void minimize(MouseEvent event) {
        stage.setIconified(true);
    }

    @FXML
    void maximize(MouseEvent event) {
        stage.setMaximized(isMaximize = !isMaximize);
    }

    @FXML
    void close(MouseEvent event) {
        Platform.exit();
    }
    //endregion

    //region @FXML Xử lý sự kiện thao tác chính
    @FXML
    void contactsMenuSelected(MouseEvent event) {
        //Xóa active cả 2 menu
        vboxContactMenu.getStyleClass().remove("active-menu-item");
        vboxConversatonMenu.getStyleClass().remove("active-menu-item");
        //active menu hiện tại
        vboxContactMenu.getStyleClass().add("active-menu-item");
        //Hiển thị nội dung tương ứng
        vboxContacts.setVisible(true);
        vboxConversations.setVisible(false);
    }
    @FXML
    void conversationsMenuSelected(MouseEvent event) {
        //Xóa active cả 2 menu
        vboxContactMenu.getStyleClass().remove("active-menu-item");
        vboxConversatonMenu.getStyleClass().remove("active-menu-item");
        //active menu hiện tại
        vboxConversatonMenu.getStyleClass().add("active-menu-item");
        //Hiển thị nội dung tương ứng
        vboxContacts.setVisible(false);
        vboxConversations.setVisible(true);
    }
    @FXML
    void settingMenuSelected(MouseEvent event) {
        settingMenu.show(ivSettingMenu, event.getScreenX(), event.getScreenY());
    }
    @FXML
    void onKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            System.out.println("Bạn vừa nhấn enter");
        }
    }
    //endregion

    //region Các hàm xử lý
    //Hàm gán thông tin người dùng lên giao diện
    public void  showMyInfo() {
        User user = connect.user;
        if (user != null) {
            lblMyName.setText(user.getName());
            try {
                ivMyAvatar.setImage(Base64Utils.getImageFromBase64String(user.getAvatar()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //Hàm sẽ gửi các request ban đầu để lấy các dữ liệu cần thiết
    public void initDataChat() {

    }
    //gửi request lấy list user
    public void requestListUser() {
        //Lấy danh sách tất cả các user
        request = new Request(RequestType.GET_LIST_USER, null);
        connect.sendRequest(request);
    }
    //endregion

    //region Các hàm làm mới UI
    public void refreshUI_ListUser(List<User> list) {
        Platform.runLater(() -> {
            vboxListContact.getChildren().clear();
            listUser = list;
            for (User user : listUser) {
                Contact contact = new Contact(user.getAvatar(), user.getUsername(), user.getName(), user.getGender(), null, user.isOnline());
                contact.setOnMousePressed(this);
                vboxListContact.getChildren().add(contact);
            }
        });
    }

    //Làm mới giao diện lại như ban đầu
    public void clearInfo() {
        
    }

    //Xử lý sự kiện click chuột chọn trên giao diện
    @Override
    public void handle(MouseEvent event) {
        Contact contact = (Contact) event.getSource();
        System.out.println(contact.getUsername());
    }
}

