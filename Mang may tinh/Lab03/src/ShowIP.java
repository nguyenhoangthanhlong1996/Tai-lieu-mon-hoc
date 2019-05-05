import java.net.InetAddress;
import java.net.UnknownHostException;

public class ShowIP {
    public static void main(String[] args) {
        try {
            InetAddress address = InetAddress.getLocalHost();
            System.out.println("Địa chỉ IP của máy tính là: " + address.getHostAddress());
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
