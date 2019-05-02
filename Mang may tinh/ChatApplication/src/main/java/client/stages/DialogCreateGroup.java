package client.stages;

import client.Config;
import client.controllers.CreateGroup;
import client.socket.SingletonConnect;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import share.entity.User;

import java.io.IOException;
import java.util.List;

public class DialogCreateGroup extends Stage {

    CreateGroup createGroupCtr;

    public DialogCreateGroup(ClientApp app, SingletonConnect connect) throws IOException {
        FXMLLoader loader = new FXMLLoader(Config.getPathViewCreateGroup());
        Parent parent = loader.load();
        createGroupCtr = (CreateGroup) loader.getController();
        createGroupCtr.setStage(this);
        createGroupCtr.setApp(app);
        createGroupCtr.setConnect(connect);
        Scene scene = new Scene(parent);
        setScene(scene);
        setResizable(false);
        initModality(Modality.APPLICATION_MODAL);
    }

    public void display(List<User> listUser) {
        createGroupCtr.setListUser(listUser);
        showAndWait();
    }
}
