package client;

import java.net.MalformedURLException;
import java.net.URL;

public class Config {
    //Đường dẫn tới thư mục chứa các file FXML
    static final URL PATH_RESOURCES_VIEWS = Main.class.getResource("views");
    //Đường dẫn tới thư mục chứa các file CSS
    static final URL PATH_RESOURCES_STYLES = Main.class.getResource("styles");
    //Đường dẫn tới thư mục chứa các file ảnh
    static final URL PATH_RESOURCES_IMAGES = Main.class.getResource("images");

    //Trả về URL icon
    public static URL getPathIcon() {
        try {
            //Tên file fxml cho giao diện đăng nhập
            String nameFile = "logo.png";
            return new URL(PATH_RESOURCES_IMAGES.toString() + "/" + nameFile);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

    //Trả về URL giao diện đăng nhập
    public static URL getPathViewSignIn() {
        try {
            //Tên file fxml cho giao diện đăng nhập
            String nameFile = "SignIn.fxml";
            return new URL(PATH_RESOURCES_VIEWS.toString() + "/" + nameFile);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

    //Trả về URL giao diện đăng ký
    public static URL getPathViewSignUp() {
        try {
            //Tên file fxml cho giao diện đăng ký
            String nameFile = "SignUp.fxml";
            return new URL(PATH_RESOURCES_VIEWS.toString() + "/" + nameFile);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

    //Trả về URL giao diện chính của ứng dụng chat
    public static URL getPathViewChat() {
        try {
            //Tên file fxml cho giao diện chat chính
            String nameFile = "Chat.fxml";
            return new URL(PATH_RESOURCES_VIEWS.toString() + "/" + nameFile);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

    //Trả về URL giao diện chính của dialog tạo nhóm
    public static URL getPathViewCreateGroup() {
        try {
            //Tên file fxml cho giao diện chat chính
            String nameFile = "CreateGroup.fxml";
            return new URL(PATH_RESOURCES_VIEWS.toString() + "/" + nameFile);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

    //Trả về URL giao diện 1 item liên hệ
    public static URL getPathViewContactItem() {
        try {
            //Tên file fxml cho item hội thoại
            String nameFile = "ContactItem.fxml";
            return new URL(PATH_RESOURCES_VIEWS.toString() + "/" + nameFile);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

    //Trả về URL giao diện 1 item cuộc hội thoại
    public static URL getPathViewConversationItem() {
        try {
            //Tên file fxml cho item hội thoại
            String nameFile = "ConversationItem.fxml";
            return new URL(PATH_RESOURCES_VIEWS.toString() + "/" + nameFile);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

    //Trả về URL giao diện 1 item timeline
    public static URL getPathViewTimelineItem() {
        try {
            //Tên file fxml cho item hội thoại
            String nameFile = "TimelineItem.fxml";
            return new URL(PATH_RESOURCES_VIEWS.toString() + "/" + nameFile);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

    //Trả về URL giao diện 1 item tin nhắn
    public static URL getPathViewMessageItem() {
        try {
            //Tên file fxml cho item hội thoại
            String nameFile = "MessageItem.fxml";
            return new URL(PATH_RESOURCES_VIEWS.toString() + "/" + nameFile);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

    //Trả về URL của avatar man
    public static URL getAvatarMan() {
        try {
            //Tên file fxml cho giao diện chat chính
            String nameFile = "man.png";
            return new URL(PATH_RESOURCES_IMAGES.toString() + "/" + nameFile);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

    //Trả về URL của avatar woman
    public static URL getAvatarWoman() {
        try {
            //Tên file fxml cho giao diện chat chính
            String nameFile = "woman.png";
            return new URL(PATH_RESOURCES_IMAGES.toString() + "/" + nameFile);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

    //Trả về URL của avatar group
    public static URL getAvatarGroup() {
        try {
            //Tên file fxml cho giao diện chat chính
            String nameFile = "group.png";
            return new URL(PATH_RESOURCES_IMAGES.toString() + "/" + nameFile);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
