import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
	static Thread threadSendData;
	public static void main(String[] args) {
		try {
			Scanner scanner = new Scanner(System.in);
			ServerSocket serverSocket = new ServerSocket(3000);
			System.out.println("Server đã bật, đang lắng nghe...");
			Socket socket = serverSocket.accept();
			System.out.println("Có kết nối với client");
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
				System.out.println("[client]: "+ message);
			}
		} catch (IOException e) {
			System.out.println("Bị mất kết nối với client");
			threadSendData.stop();
			System.exit(0);
		}
	}
}
