package client.controllers;

import client.objectUI.*;
import client.socket.SingletonConnect;
import client.stages.ClientApp;
import client.stages.DialogCreateGroup;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectProperty;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import share.data.*;
import share.entity.User;
import share.protocol.Request;
import share.protocol.RequestType;
import share.util.Base64Utils;
import share.util.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    boolean isFilterContact = true;
    //Lưu file đính kèm hiện tại;
    File attachment = null;
    //Danh sách các liên hệ
    ObservableList<User> listContact;
    //Filter danh sách các liên hệ
    FilteredList filterContact;
    //Bắt sự kiện chọn 1 liên hệ
    ChangeListener<User> listenerContact;
    //Danh sách các cuộc hội thoại
    ObservableList<ConversationData> listConversation;
    //Filter danh sách các cuộc hội thoại
    FilteredList filterConversation;
    //Bắt sự kiện chọn 1 cuộc hội thoại
    ChangeListener<ConversationData> listenerConversation;
    //Danh sách tin nhắn của cuộc hội thoại hiện tại
    List<MessageItem> listMessage;
    //Xử lý sự kiện khi người dùng click chọn 1 user trong danh sách liên hệ
    EventHandler<MouseEvent> eventHandlerListContact;
//    //Xử lý sự kiện khi người dùng click chọn 1 cuộc hội thoại
//    EventHandler<MouseEvent> eventHandlerListConversation;
    //endregion

    //region FXML Đối tượng UI
    @FXML
    private ImageView ivMyAvatar;

    @FXML
    private Circle circleMyStatus;

    @FXML
    private VBox vboxContactMenu;

    @FXML
    private VBox vboxConversatonMenu;

    @FXML
    private MaterialDesignIconView ivSettingMenu;

    @FXML
    private Label lblMyName;

    @FXML
    private JFXTextField txtSearch;

    @FXML
    private VBox vboxContacts;

    @FXML
    private VBox vboxConversations;

    @FXML
    private ComboBox<String> cbbFilterConversation;

    @FXML
    private ListView<User> listViewContact;

    @FXML
    private ListView<ConversationData> listViewConversation;

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
    private HBox hboxAttachment;

    @FXML
    private Label lblPathAttachment;

    @FXML
    private JFXTextField txtInputMessage;

    @FXML
    private HBox titleBar;
    ContextMenu settingMenu;
    //endregion

    //region Phương thức khởi tạo
    public Chat(ClientApp app, Stage stage) {
        this.app = app;
        this.stage = stage;
        listContact = FXCollections.observableArrayList();
        filterContact = new FilteredList<>(listContact, c -> true);
        listConversation = FXCollections.observableArrayList();
        filterConversation = new FilteredList<>(listConversation, c -> true);
        listMessage = new ArrayList<>();
        connect = SingletonConnect.getInstance();
    }
    //endregion

    //region Event Handler
    private void initEventHandler() {
//        //Xử lý sự kiện 1 user được click chọn ở danh sách tất cả các liên hệ
//        eventHandlerListContact = event -> {
//            ContactItem contact = (ContactItem) event.getSource();
//            //Gửi yêu cầu tạo 1 cuộc hội thoại với người được click chọn
//            request = new Request(RequestType.CREATE_CONVERSATION_PRIVATE, contact.getUsername());
//            connect.sendRequest(request);
//        };
        //Xử lý sự kiện chọn 1 liên hệ
        listenerContact = (observable, oldValue, newValue) -> {
            if (newValue != null) {
                //Gửi yêu cầu tạo 1 cuộc hội thoại với người được click chọn
                request = new Request(RequestType.CREATE_CONVERSATION_PRIVATE, newValue.getUsername());
                connect.sendRequest(request);
            }
        };
        //Xử lý sự kiện chọn 1 cuộc hội thoại
        listenerConversation = (observable, oldValue, newValue) -> {
            if (newValue != null) {
                //Gửi yêu cầu lấy danh tin nhắn của cuộc hội thoại này
                int idConversation = newValue.getIdConversation();
                if (currentIdConversation != idConversation) {
                    //Gán hình
                    if (newValue.getAvatar() != null) {
                        try {
                            ivAvatar.setImage(Base64Utils.getImageFromBase64String(newValue.getAvatar()));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    //Gán tên
                    lblName.setText(newValue.getName());
                    circleStatus.setVisible(false);
                    lblLastVisit.setText(null);

                    requestListMessage(idConversation);
                }
            }
        };
    }
    //endregion

    //region @FXML init
    //Hàm khởi chạy khi giao diện được load lên
    @FXML
    void initialize() {
        //Khung hiển thị các tin nhắn của cuộc hội thoại hiện tại ẩn đi
        vboxConversation.setVisible(false);
        //khung hiển thị danh sách các cuộc hội thoại ẩn đi
        vboxConversations.setVisible(false);
        //phần hiển thị tệp đính kèm ẩn đi
        hboxAttachment.setVisible(false);
        //Gán ràng buộc kích thước chiều rộng khung hiển thị nội dung chat hiện tại bằng khung cuộn hiển thị (Khi khung cuộn thay đổi chiều rộng thì khung chat cũng thay đổi theo)
        vboxViewChat.prefWidthProperty().bind(scrollViewChat.widthProperty());
        //Tạo dữ liệu trong combobox lọc loại cuộc hội thoại
        cbbFilterConversation.getItems().addAll("Tất cả", "Cuộc hội thoại riêng tư", "Cuộc hội thoại nhóm");
        cbbFilterConversation.setOnAction(event -> {
            txtSearch.setText("");
            int index = cbbFilterConversation.getSelectionModel().getSelectedIndex();
            switch (index) {
                case 0:
                    filterConversation.setPredicate((Predicate<? super ConversationData>) c -> {
                        return true;
                    });
                    break;
                case 1:
                    filterConversation.setPredicate((Predicate<? super ConversationData>) c -> {
                        return (c.isGroup() == false);
                    });
                    break;
                case 2:
                    filterConversation.setPredicate((Predicate<? super ConversationData>) c -> {
                        return (c.isGroup() == true);
                    });
                    break;
            }
            SortedList<ConversationData> sortedList = new SortedList<>(filterConversation);
            listViewConversation.setItems(sortedList);
        });
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
        //Gán dữ liệu cho listViewContact
        listViewContact.setItems(listContact);
        listViewContact.setCellFactory(param -> new ContactItem());
        //Gán dữ liệu cho listViewConversation
        listViewConversation.setItems(listConversation);
        listViewConversation.setCellFactory(param -> new ConversationItem());
        searchKeyReleased(null);
        //Khởi tạo các xử lý sự kiện
        initEventHandler();
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
        //Gán cờ hiệu
        isFilterContact = true;
        txtSearch.setText("");
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
        //Gán cờ hiệu
        isFilterContact = false;
        txtSearch.setText("");
    }

    @FXML
    void createGroup(ActionEvent event) {
        try {
            dialogCreateGroup = new DialogCreateGroup(app, connect);
            dialogCreateGroup.display(listContact);
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
        if (!isFilterContact) { //Lọc cuộc hội thoại
            txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
                filterConversation.setPredicate((Predicate<? super ConversationData>) c -> {
                    if (newValue == null || newValue.isEmpty())
                        return true;
                    String valueFilter = newValue.trim().toLowerCase();
                    if (c.getUsername() != null && c.getUsername().toLowerCase().contains(valueFilter))
                        return true;
                    if (c.getName() != null && c.getName().toLowerCase().contains(valueFilter))
                        return true;
                    if (c.getShortenContent() != null && c.getShortenContent().toLowerCase().contains(valueFilter))
                        return true;
                    return false;
                });
            });
            SortedList<ConversationData> sortedList = new SortedList<>(filterConversation);
            listViewConversation.setItems(sortedList);
        } else {
            txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
                filterContact.setPredicate((Predicate<? super User>) c -> {
                    if (newValue == null || newValue.isEmpty())
                        return true;
                    String valueFilter = newValue.trim().toLowerCase();
                    if (c.getUsername() != null && c.getUsername().toLowerCase().contains(valueFilter))
                        return true;
                    if (c.getName() != null && c.getName().toLowerCase().contains(valueFilter))
                        return true;
                    return false;
                });
            });
            SortedList<User> sortedList = new SortedList<>(filterContact);
            listViewContact.setItems(sortedList);
        }
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
                if (attachment != null) {
                    FileInfoData fileInfoData = FileUtils.getFileInfo(attachment);
                    if (fileInfoData != null) {
                        request = new Request(RequestType.SEND_MESSAGE, new SendMessageData(currentIdConversation, content, fileInfoData.getDataBytes(), fileInfoData.getFileName(), fileInfoData.getFileExtension()));
                    } else {
                        request = new Request(RequestType.SEND_MESSAGE, new SendMessageData(currentIdConversation, content, null, null, null));
                    }
                } else {
                    request = new Request(RequestType.SEND_MESSAGE, new SendMessageData(currentIdConversation, content, null, null, null));
                }
                connect.sendRequest(request);
                //Làm mới khung nhập tin nhắn
                txtInputMessage.clear();
                //Ẩn đi thông tin tệp đính kèm
                attachment = null;
                lblPathAttachment.setText("");
                hboxAttachment.setVisible(false);
            }
        }
    }


    @FXML
    void fileAttachment(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Chọn tệp");
        //Hiện cửa sổ chọn file
        attachment = fileChooser.showOpenDialog(stage);
        if (attachment != null) {
            if (attachment.length() > 2097152) {
                app.showAlert("Kích thước tệp lớn hơn 2 MB", "Vui lòng chọn tệp có kích thước <= 2 MB");
                attachment = null;
            } else {
                //Hiển thị thông tin tệp đính kèm
                lblPathAttachment.setText(attachment.getName());
                hboxAttachment.setVisible(true);
            }
        }
    }

    @FXML
    void clearAttachment(MouseEvent event) {
        attachment = null;
        lblPathAttachment.setText("");
        hboxAttachment.setVisible(false);
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
            ReadOnlyObjectProperty<User> property = listViewContact.getSelectionModel().selectedItemProperty();
            property.removeListener(listenerContact);
            listContact.clear();
            listContact.addAll(list);
            property.addListener(listenerContact);
        });
    }

    public void refreshUI_ListConversation(List<ConversationData> list) {
        Platform.runLater(() -> {
            ReadOnlyObjectProperty<ConversationData> property = listViewConversation.getSelectionModel().selectedItemProperty();
            property.removeListener(listenerConversation);
            listConversation.clear();
            listConversation.addAll(list);
            property.addListener(listenerConversation);
        });
    }

    public void refreshUI_ListMessage(ListMessageData listMessageData) {
        Platform.runLater(() -> {
            currentIdConversation = listMessageData.getConversationId();
            List<MessageData> list = listMessageData.getListMessageData();
            User user = connect.user;
            String currentUsername = "";
            String currentTimeline = "";
            MessageItem item = null;
            vboxViewChat.getChildren().clear();
            for (MessageData data : list) {
                LocalDateTime localDateTime = data.getTime().toLocalDateTime();
                String dateFormat = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd "));
                String timeFormat = localDateTime.format(DateTimeFormatter.ofPattern("hh:mm"));
                if (!dateFormat.equals(currentTimeline)) {
                    vboxViewChat.getChildren().add(new TimelineItem(dateFormat));
                    currentTimeline = dateFormat;
                }
                if (!data.getSender().equals(currentUsername)) {
                    item = new MessageItem(
                            data.getMessageId(),
                            data.getAvatar(),
                            data.getContent(),
                            data.getAttachmentName(),
                            timeFormat,
                            null,
                            data.getSender().equals(user.getUsername())
                    );
                } else {
                    item = new MessageItem(
                            data.getMessageId(),
                            null,
                            data.getContent(),
                            data.getAttachmentName(),
                            timeFormat,
                            null,
                            data.getSender().equals(user.getUsername())
                    );
                }
                currentUsername = data.getSender();
                vboxViewChat.getChildren().add(item);
            }
            Platform.runLater(() -> scrollViewChat.setVvalue(1.0));
            vboxConversation.setVisible(true);
        });
    }

    public void saveAttachment(FileInfoData fileInfoData) {
        Platform.runLater(() -> {
            //Thực hiện lưu file về máy
            FileChooser fileChooser = new FileChooser();
            //Set extension filter for text files
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("File", "*." + fileInfoData.getFileExtension());
            fileChooser.getExtensionFilters().add(extFilter);
            fileChooser.setInitialFileName(fileInfoData.getFileName());
            //Show save file dialog
            File file = fileChooser.showSaveDialog(stage);

            if (file != null) {
                byte[] data = fileInfoData.getDataBytes();
                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(file.getAbsolutePath());
                    fos.write(data);
                    fos.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    //Làm mới giao diện lại như ban đầu
    public void clearInfo() {
        listContact.clear();
        listConversation.clear();
        listMessage.clear();
        ivAvatar.setImage(null);
        ivMyAvatar.setImage(null);
        lblMyName.setText(null);
        lblName.setText(null);
        contactsMenuSelected(null);
        vboxConversation.setVisible(false);
        cbbFilterConversation.getSelectionModel().selectFirst();
        txtSearch.setText("");
        currentIdConversation = 0;
        isFilterContact = true;
    }
    //endregion
}

