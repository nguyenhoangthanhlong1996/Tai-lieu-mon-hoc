import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainTest extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml"));
        primaryStage.setScene(new Scene(loader.load()));
        MyCtr myCtr = loader.getController();
        myCtr.setStage(primaryStage);
        primaryStage.show();
    }
}
