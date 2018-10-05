import java.util.Scanner;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class BT8 {
	static String ThongTinSocket(String hostname, int port) {
		try {
			Socket socket = new Socket(hostname, 80);
			return socket.toString();
		} catch (UnknownHostException e) {
			return null;
		} catch (IOException e) {
			return null;
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Nhập hostname: ");
		String hostname = scanner.nextLine();
		String ketqua = ThongTinSocket(hostname, 80);
		if (ketqua != null) {
			System.out.println("Thông tin của socket: " + ketqua);
		} else {
			System.out.println("Không thể lấy thông tin socket");
		}
	}
}
