import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class MyCtr {

    @FXML
    private ScrollPane scroll;

    @FXML
    private VBox vbox;

    @FXML
    void initialize() {

    }

    @FXML
    void Add(ActionEvent event) {
        for (int i=0;i<100;i++)
            vbox.getChildren().add(new MessageItemTest());
    }
}
