package client.controllers;

import client.objectUI.UserSelect;
import client.socket.SingletonConnect;
import client.stages.ClientApp;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import share.data.CreateGroupData;
import share.entity.User;
import share.protocol.Request;
import share.protocol.RequestType;
import share.util.Base64Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class CreateGroup {

    Stage stage;
    ClientApp app;
    SingletonConnect connect;
    List<User> listUser;
    File fileAvatar;
    CreateGroupData groupData;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setApp(ClientApp app) {
        this.app = app;
    }

    public void setConnect(SingletonConnect connect) {
        this.connect = connect;
    }

    public void setListUser(List<User> listUser) {
        this.listUser = listUser;
        list.clear();
        for (User u : listUser) {
            list.add(new UserSelect(u.getUsername()));
        }
    }

    @FXML
    private TableView<UserSelect> tableUser;
    @FXML
    private TableColumn<UserSelect, String> colUsername;
    @FXML
    private TableColumn<UserSelect, String> colSelected;
    @FXML
    private ImageView ivAvatarGroup;
    @FXML
    private TextField txtSearchUsername;
    @FXML
    private TextField txtNameGroup;

    ObservableList<UserSelect> list;
    FilteredList filter;

    @FXML
    void initialize() {
        groupData = new CreateGroupData();
        list = FXCollections.observableArrayList();
        filter = new FilteredList(list, e -> true);
        colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        colSelected.setCellValueFactory(new PropertyValueFactory<>("select"));
        tableUser.setItems(list);
        search(null);
    }

    @FXML
    void search(KeyEvent event) {
        txtSearchUsername.textProperty().addListener((observable, oldValue, newValue) -> {
            filter.setPredicate((Predicate<? super UserSelect>) us -> {
                String value = newValue.toLowerCase();
                if (newValue.isEmpty() || newValue == null)
                    return true;
                if (us.getUsername().contains(value))
                    return true;
                return false;
            });
        });
        SortedList sort = new SortedList(filter);
        sort.comparatorProperty().bind(tableUser.comparatorProperty());
        tableUser.setItems(sort);
    }

    @FXML
    void chooseAvatarGroup(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        //Thiết lập bộ lọc file
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Chọn file ảnh (<=500 KB)", "*.jpg", "*.png");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setTitle("Chọn ảnh đại diện nhóm");
        //Hiện cửa sổ chọn file
        fileAvatar = fileChooser.showOpenDialog(stage);
        if (fileAvatar != null) {
            if (fileAvatar.length() > 512000) {
                //app.showAlert("Kích thước ảnh lớn hơn 500 KB", "Vui lòng chọn ảnh khác, ảnh đại diện của bạn sẽ không được lưu khi kích thước lớn hơn 500 KB");
                fileAvatar = null;
                ivAvatarGroup.setImage(null);
            } else {
                ivAvatarGroup.setImage(new Image("file:" + fileAvatar.getAbsolutePath()));
            }
        } else {
            ivAvatarGroup.setImage(null);
        }
    }

    @FXML
    void createGroup(ActionEvent event) {
        List<String> users = new ArrayList<>();
        ObservableList<UserSelect> list = tableUser.getItems();
        for (UserSelect us : list) {
            if (us.getSelect().isSelected())
                users.add(us.getUsername());
        }
        String nameGroup = txtNameGroup.getText().trim();
        String avatarGroup = (fileAvatar != null) ? Base64Utils.encoder(fileAvatar.getAbsolutePath()) : "";
        groupData.setNameGroup(nameGroup);
        groupData.setAvatarGroup(avatarGroup);
        groupData.setUsers(users);
        if (!groupData.isValid()) {
            String errs = "";
            for (String s : groupData.getErrors())
                errs += s + "\n";
            app.showAlert("Dữ liệu tạo tạo nhóm không hợp lệ", errs);
        } else {
            //Gửi resquest create group lên Server
            connect.sendRequest(new Request(RequestType.CREATE_CONVERSATION_GROUP, groupData));
            stage.close();
        }
    }
}
