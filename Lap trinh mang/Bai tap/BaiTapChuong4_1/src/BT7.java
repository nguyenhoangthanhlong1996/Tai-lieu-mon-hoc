import java.util.Scanner;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class BT7 {
    static String LayIPTuHostname(String hostname) {
        try {
            InetAddress address = InetAddress.getByName(hostname);
            return address.getHostAddress();
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            return null;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập hostname: ");
        String hostname = scanner.nextLine();
        String ketqua = LayIPTuHostname(hostname);
        if (ketqua != null) {
            System.out.println("IP: " + ketqua);
        } else {
            System.out.println("Không tìm thấy host");
        }
    }
}
