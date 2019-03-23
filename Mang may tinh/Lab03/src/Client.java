import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;



public class Client {
	static Thread threadSendData;
	public static void main(String[] args) {
		try {
			Scanner scanner = new Scanner(System.in);
			Socket socket = new Socket("localhost", 3000);
			System.out.println("Đã kết nối tới server");
			PrintWriter pw = new PrintWriter(socket.getOutputStream());
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			//Gửi dữ liệu
			threadSendData = new Thread(new Runnable() {
				@Override
				public void run() {
					String message;
					while (true) {
						message = scanner.nextLine();
						pw.println(message);
						pw.flush();
					}
				}
			});
			threadSendData.start();
			//Nhận dữ liệu
			String message = "";
			while ((message = reader.readLine()) != null ) {
				System.out.println("[server]: "+ message);
			}
		} catch (UnknownHostException e) {
			System.out.println("Không tìm thấy host");
		} catch (IOException e) {
			System.out.println("Bị mất kết nối với server");
			threadSendData.stop();
			System.exit(0);
		}
	}

}
