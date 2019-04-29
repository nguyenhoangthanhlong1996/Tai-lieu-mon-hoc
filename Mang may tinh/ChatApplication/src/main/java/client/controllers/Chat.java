package client.controllers;

import client.objectUI.ContactItem;
import client.objectUI.ConversationItem;
import client.objectUI.MessageItem;
import client.socket.SingletonConnect;
import client.stages.ClientApp;
import client.stages.DialogCreateGroup;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import share.dao.ConversationDAO;
import share.data.ConversationData;
import share.data.ListMessageData;
import share.data.MessageData;
import share.data.SendMessageData;
import share.entity.Message;
import share.entity.User;
import share.protocol.Request;
import share.protocol.RequestType;
import share.util.Base64Utils;
import share.util.DateTimeUtils;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Chat {
    //region Biến
    ClientApp app;
    SingletonConnect connect;
    Stage stage;
    Request request;
    DialogCreateGroup dialogCreateGroup;
    //Lưu id cuộc thoại hiện tại
    int currentIdConversation = 0;
    double x = 0, y = 0;
    boolean isMaximize = false;
    //Danh sách các user
    List<User> listUser;
    //Danh sách các cuộc hội thoại
    List<ConversationData> listConversation;
    //Danh sách tin nhắn của cuộc hội thoại hiện tại
    List<MessageItem> listMessage;
    //Xử lý sự kiện khi người dùng click chọn 1 user trong danh sách liên hệ
    EventHandler<MouseEvent> eventHandlerListContact;
    //Xử lý sự kiện khi người dùng click chọn 1 cuộc hội thoại
    EventHandler<MouseEvent> eventHandlerListConversation;
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
        listConversation = new ArrayList<>();
        listMessage = new ArrayList<>();
        connect = SingletonConnect.getInstance();
    }
    //endregion

    //region Event Handler
    private void initEventHandler() {
        //Xử lý sự kiện 1 user được click chọn ở danh sách tất cả các liên hệ
        eventHandlerListContact = event -> {
            ContactItem contact = (ContactItem) event.getSource();
            //Gửi yêu cầu tạo 1 cuộc hội thoại với người được click chọn
            request = new Request(RequestType.CREATE_CONVERSATION_PRIVATE, contact.getUsername());
            connect.sendRequest(request);
        };
        //Xử lý sự kiện 1 user được click chọn ở danh sách tất cả các cuộc hội thoại
        eventHandlerListConversation = event -> {
            ConversationItem conversation = (ConversationItem) event.getSource();
            //Gửi yêu cầu lấy danh tin nhắn của cuộc hội thoại này
            int idConversation = conversation.getIdConversation();
            if (currentIdConversation != idConversation) {
                //Gán hình
                if (conversation.getAvatar() != null) {
                    try {
                        ivAvatar.setImage(Base64Utils.getImageFromBase64String(conversation.getAvatar()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                //Gán tên
                lblName.setText(conversation.getName());
                circleStatus.setVisible(false);
                lblLastVisit.setText(null);

                requestListMessage(idConversation);
            }
        };
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
        cbbFilterConversation.getItems().addAll("Tất cả", "Cuộc hội thoại riêng tư", "Cuộc hội thoại nhóm");
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
        searchKeyReleased(null);
        //Khởi tạo các xử lý sự kiện
        initEventHandler();
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
    void createGroup(ActionEvent event) {
        try {
            if (dialogCreateGroup == null)
                dialogCreateGroup = new DialogCreateGroup(app, connect);
            dialogCreateGroup.display(listUser);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void settingMenuSelected(MouseEvent event) {
        settingMenu.show(ivSettingMenu, event.getScreenX(), event.getScreenY());
    }

    @FXML
    void searchKeyReleased(KeyEvent event) {
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {

        });
    }

    @FXML
    void onKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            sendMessage(null);
        }
    }

    @FXML
    void sendMessage(MouseEvent event) {
        if (currentIdConversation != 0) {
            String content = txtInputMessage.getText().trim();
            if (!content.equals("")) {
                request = new Request(RequestType.SEND_MESSAGE, new SendMessageData(currentIdConversation, content));
                connect.sendRequest(request);
                //Làm mới khung nhập tin nhắn
                txtInputMessage.clear();
            }
        }
    }
    //endregion

    //region Các hàm xử lý
    //Hàm gán thông tin người dùng lên giao diện
    public void showMyInfo() {
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
        requestListConversation();
    }

    //gửi request lấy list user
    public void requestListUser() {
        //Lấy danh sách tất cả các user
        request = new Request(RequestType.GET_LIST_USER, null);
        connect.sendRequest(request);
    }

    //gửi request lấy list conversation
    public void requestListConversation() {
        //Lấy danh sách tất cả các user
        request = new Request(RequestType.GET_LIST_CONVERSATION, null);
        connect.sendRequest(request);
    }

    //gửi request lấy list message
    public void requestListMessage(int conversationId) {
        //Lấy danh sách tất cả các user
        request = new Request(RequestType.GET_LIST_MESSAGE, conversationId);
        connect.sendRequest(request);
    }

    public void notifyListMessage(int conversationId) {
        //Nếu đang mở cuộc hội thoại này thì mới gửi yêu cầu lấy danh sách tin nhắn
        if (conversationId == currentIdConversation) {
            requestListConversation();
            requestListMessage(conversationId);
        } else { //Chỉ gửi yêu cầu lấy danh sách các cuộc hội thoại
            requestListConversation();
        }
    }
    //endregion

    //region Các hàm làm mới UI, các hàm này được gọi bởi lớp SingletonConnect
    public void refreshUI_ListUser(List<User> list) {
        Platform.runLater(() -> {
            vboxListContact.getChildren().clear();
            listUser = list;
            for (User user : listUser) {
                ContactItem contact = new ContactItem(user.getAvatar(), user.getUsername(), user.getName(), user.getGender(), null, user.isOnline());
                contact.setOnMousePressed(eventHandlerListContact);
                vboxListContact.getChildren().add(contact);
            }
        });
    }

    public void refreshUI_ListConversation(List<ConversationData> list) {
        Platform.runLater(() -> {
            vboxListConversation.getChildren().clear();
            listConversation = list;
            for (ConversationData cd : listConversation) {
                ConversationItem conversationItem = new ConversationItem(
                        cd.getIdConversation(),
                        cd.getAvatar(),
                        cd.getUsername(),
                        cd.getName(),
                        cd.getShortenContent(),
                        //cd.getLastMessageTime() != null ? DateTimeUtils.getDiffDate(cd.getLastMessageTime()) : null,
                        null,
                        0);
                conversationItem.setOnMousePressed(eventHandlerListConversation);
                vboxListConversation.getChildren().add(conversationItem);
            }
        });
    }

    public void refreshUI_ListMessage(ListMessageData listMessageData) {
        Platform.runLater(() -> {
            vboxViewChat.getChildren().clear();
            currentIdConversation = listMessageData.getConversationId();
            List<MessageData> list = listMessageData.getListMessageData();
            User user = connect.user;
            String currentUsername = "";
            MessageItem item = null;
            for (MessageData data : list) {
                if (!data.getSender().equals(currentUsername)) {
                    item = new MessageItem(
                            data.getAvatar(),
                            data.getContent(),
                            data.getTime().toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")),
                            null,
                            data.getSender().equals(user.getUsername())
                    );
                } else {
                    item = new MessageItem(
                            null,
                            data.getContent(),
                            data.getTime().toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")),
                            null,
                            data.getSender().equals(user.getUsername())
                    );
                }
                currentUsername = data.getSender();
                vboxViewChat.getChildren().add(item);
            }
            scrollViewChat.setVvalue(scrollViewChat.getVmax());
            vboxConversation.setVisible(true);
        });
    }

    //Làm mới giao diện lại như ban đầu
    public void clearInfo() {
        listUser.clear();
        listConversation.clear();
        listMessage.clear();
        ivAvatar.setImage(null);
        ivMyAvatar.setImage(null);
        lblMyName.setText(null);
        lblName.setText(null);
        contactsMenuSelected(null);
        vboxConversation.setVisible(false);
    }
    //endregion
}

