import client.objectUI.ConversationItem;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import share.dao.MessageDAO;
import share.data.FileInfoData;
import share.entity.Message;
import share.util.FileUtils;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.function.Predicate;


public class MyCtr {

    Stage stage;

    @FXML
    private ListView<Conversation> listView;
    @FXML
    private JFXTextField txtSearch;
    ObservableList<Conversation> listConversation;
    ChangeListener<Conversation> listener;
    FilteredList<Conversation> filterConversation;

    MessageDAO messageDAO;

    void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    void initialize() {
        messageDAO = new MessageDAO();
//        listConversation = FXCollections.observableArrayList();
////        //for (int i=0;i<100;i++)
////            listConversation.addAll(
////                    new Conversation(1, null, "long", "Thanh Long", "abc", "12:25", 0),
////                    new Conversation(2, null, "hue", "Gia Hue", "def", "07:25", 0)
////            );
//        listView.setItems(listConversation);
//        listView.setCellFactory(param -> new ConversationListViewCell());
//        filterConversation = new FilteredList<>(listConversation, s -> true);
//        listView.getStylesheets().add("ListView.css");
//        listener = (observable, oldValue, newValue) -> {
//            System.out.println(newValue.getIdConversation());
//        };
//        listView.getSelectionModel().selectedItemProperty().addListener(listener);
    }

    @FXML
    void Add(ActionEvent event) {
//        File attachment = null;
//        FileChooser fileChooser = new FileChooser();
//        fileChooser.setTitle("Chọn tệp");
//        //Hiện cửa sổ chọn file
//        attachment = fileChooser.showOpenDialog(stage);
//        if (attachment != null) {
//            if (attachment.length() > 2097152) {
//                System.out.println("Tệp quá lơn1");
//                attachment = null;
//            } else {
//                FileInfoData fileInfoData = FileUtils.getFileInfo(attachment.getAbsolutePath(), "");
//                if (fileInfoData != null) {
//                     if (messageDAO.createMessage(447,"long","5848154845",fileInfoData.getDataBytes())) {
//                        System.out.println("Lưu thành công");
//                    }
//                }
//            }
//        }


        Message message = messageDAO.getMessageById(729);
        if (message !=null ){
            FileChooser fileChooser = new FileChooser();

            //Set extension filter for text files
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("File", "*."+message.getAttachmentExtension());
            fileChooser.getExtensionFilters().add(extFilter);
            fileChooser.setInitialFileName(message.getAttachmentName());
            //Show save file dialog
            File file = fileChooser.showSaveDialog(stage);

            if (file != null) {
                byte[] data = message.getAttachment();
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

        }
    }

    @FXML
    void filterTextChange(KeyEvent event) {
//        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
//            filterConversation.setPredicate((Predicate<? super Conversation>) c -> {
//                if (newValue == null || newValue.isEmpty())
//                    return true;
//                String valueFilter = newValue.trim().toLowerCase();
//                if (c.getUsername().contains(valueFilter))
//                    return true;
//                if (c.getName().contains(valueFilter))
//                    return true;
//                if (c.getShortenContent().contains(valueFilter))
//                    return true;
//                return false;
//            });
//        });
//        SortedList<Conversation> sortedList = new SortedList<>(filterConversation);
//        listView.setItems(sortedList);
    }
}
