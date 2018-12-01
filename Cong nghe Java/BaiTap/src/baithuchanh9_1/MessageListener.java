package baithuchanh9_1;

import java.net.Socket;

public interface MessageListener {
	//₫ịnh nghĩa tác vụ ₫ược cung cấp bởi interface
	void messageReceived(Socket socket, String s);
}
