import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class Controller {
    @FXML
    private Label lbl;
    @FXML
    private Text txt;

    @FXML
    public void initialize() {
        lbl.setText("Hoàng");
        txt.setText("À Á Ã");
    }
}
