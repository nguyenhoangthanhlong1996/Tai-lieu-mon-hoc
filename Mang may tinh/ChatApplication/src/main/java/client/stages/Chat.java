package client.stages;

import client.Config;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Chat extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(Config.getPathViewChat());
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.setMinWidth(1020);
        stage.setMinHeight(700);
        stage.show();
    }

    public void show() {
        Application.launch();
    }
}
