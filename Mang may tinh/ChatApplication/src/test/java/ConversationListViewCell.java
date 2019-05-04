import client.Config;
import client.objectUI.ConversationItem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class ConversationListViewCell extends ListCell<Conversation> {
    @FXML
    private HBox mainNode;

    @FXML
    private ImageView ivAvatar;

    @FXML
    private Label lblName;

    @FXML
    private Label lblShortentContent;

    @FXML
    private Label lblLastMessageTime;

    @FXML
    private Label lblNumberUnreadMessage;

    FXMLLoader mLLoader;

    @Override
    protected void updateItem(Conversation item, boolean empty) {
        super.updateItem(item, empty);
        if(empty || item == null) {
            setText(null);
            setGraphic(null);
        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("ConversationItem.fxml"));
                mLLoader.setController(this);
                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            lblName.setText(item.getName());
            lblName.setStyle("-fx-text-fill: black");
            lblShortentContent.setText(item.getShortenContent());
            lblShortentContent.setStyle("-fx-text-fill: black");
            lblLastMessageTime.setStyle("-fx-text-fill: black");
            ivAvatar.setImage(new Image(Config.getAvatarMan().toString()));
            setText(null);
            setGraphic(mainNode);
        }
    }
}
