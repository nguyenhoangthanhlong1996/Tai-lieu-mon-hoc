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
import javafx.util.Callback;

import java.awt.*;
import java.io.IOException;
import java.util.function.Predicate;


public class MyCtr {

    @FXML
    private ListView<Conversation> listView;
    @FXML
    private JFXTextField txtSearch;
    ObservableList<Conversation> listConversation;
    ChangeListener<Conversation> listener;
    FilteredList<Conversation> filterConversation;

    @FXML
    void initialize() {
        listConversation = FXCollections.observableArrayList();
//        //for (int i=0;i<100;i++)
//            listConversation.addAll(
//                    new Conversation(1, null, "long", "Thanh Long", "abc", "12:25", 0),
//                    new Conversation(2, null, "hue", "Gia Hue", "def", "07:25", 0)
//            );
        listView.setItems(listConversation);
        listView.setCellFactory(param -> new ConversationListViewCell());
        filterConversation = new FilteredList<>(listConversation, s->true);
        listView.getStylesheets().add("ListView.css");
        listener = (observable, oldValue, newValue) -> {
            System.out.println(newValue.getIdConversation());
        };
        listView.getSelectionModel().selectedItemProperty().addListener(listener);
    }

    @FXML
    void Add(ActionEvent event) {
        listView.getSelectionModel().selectedItemProperty().removeListener(listener);
        listConversation.clear();
        listConversation.addAll(
                new Conversation(1, null, "tam", "Thanh Tam", "abc", "12:25", 0),
                new Conversation(2, null, "bao", "Gia Bao", "def", "07:25", 0)
        );
        listView.getSelectionModel().selectedItemProperty().addListener(listener);
    }

    @FXML
    void filterTextChange(KeyEvent event) {
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filterConversation.setPredicate((Predicate<? super Conversation>)c -> {
                if (newValue == null || newValue.isEmpty())
                    return true;
                String valueFilter = newValue.trim().toLowerCase();
                if (c.getUsername().contains(valueFilter))
                    return true;
                if (c.getName().contains(valueFilter))
                    return true;
                if (c.getShortenContent().contains(valueFilter))
                    return true;
                return false;
            });
        });
        SortedList<Conversation> sortedList = new SortedList<>(filterConversation);
        listView.setItems(sortedList);
    }
}
