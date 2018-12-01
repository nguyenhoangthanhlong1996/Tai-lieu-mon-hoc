package baithuchanh10_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;
import java.net.Socket;

public class ReceivingThread extends Thread {
	// ₫ịnh nghĩa các thuộc tính cần dùng
	private BufferedReader input;
	private MessageListener messageListener;
	private boolean keepListening = true;
	Socket socket;

	// ₫ịnh nghĩa constructor
	public ReceivingThread(MessageListener listener, Socket clientSocket) {
		// gọi cha
		super("ReceivingThread: " + clientSocket);
		// lưu giữ tham số nhận ₫ược từ client gọi
		messageListener = listener;
		socket = clientSocket;
		try {
			// thiết lập timer cho socket
			socket.setSoTimeout(0);
			// tạo ₫ối tượng phục vụ ₫ọc dữ liệu từ xa gởi về
			input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} // xử lý lỗi ngoại lệ
		catch (IOException ioException) {
			ioException.printStackTrace();
		}
	} // hết constructor
		// thuật giải của thread : chờ nhận thông tin và gởi về MessageListener xử lý

	public void run() {
		String message;
		// chờ nhận thông tin ₫ến khi bị stop
		while (keepListening) {
			try {
				// thử ₫ọc 1 thông báo (=1 dòng văn bản)
				message = input.readLine();
				if (message != null) // nếu có thì gởi cho MessageListener xử lý
					messageListener.messageReceived(socket, message);
			}
			// xử lý lỗi ngoại lệ
			catch (InterruptedIOException interruptedIOException) {
				// tiếp tục lặp chờ
				continue;
			} catch (IOException ioException) {
				messageListener.messageReceived(socket, // user name
						"CLOSE "); // message body
				break;
			}
		} // hết vòng lặp chờ
		try {
			// ₫óng ₫ối tượng input
			input.close();
		} // xử lý lỗi ngoại lệ
		catch (IOException ioException) {
			ioException.printStackTrace();
		}
	} // hết hàm run
		// hàm thiết lập trạng thái dừng việc chờ

	public void stopListening() {
		keepListening = false;
	}
}
