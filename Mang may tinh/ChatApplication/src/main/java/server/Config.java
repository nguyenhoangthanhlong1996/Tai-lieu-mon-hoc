package server;


import java.net.MalformedURLException;
import java.net.URL;

public class Config {
    static final URL PATH_RESOURCES_VIEWS = Main.class.getResource("views");
    static final URL PATH_RESOURCES_STYLES = Main.class.getResource("styles");
    static final URL PATH_RESOURCES_IMAGES = Main.class.getResource("../images");

    //Trả về URL giao diện chính server
    public static URL getPathViewServer() {
        try {
            //Tên file fxml cho giao diện server
            String nameFile = "Server.fxml";
            return new URL(PATH_RESOURCES_VIEWS.toString() + "/" + nameFile);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
