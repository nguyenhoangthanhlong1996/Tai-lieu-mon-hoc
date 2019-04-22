package server.stages;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import server.Config;

public class Server extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Config.getPathViewServer());
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }
}
