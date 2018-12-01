package baithuchanh10_1;

import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.DefaultListModel;

public class ServerAcceptThread extends Thread {
	// ₫ịnh nghĩa các thuộc tính cần dùng
	ServerSocket serverSocket;
	MiniChatServerDlg serverChat;

	// ₫ịnh nghĩa constructor
	public ServerAcceptThread(MiniChatServerDlg server, ServerSocket sock) {
		serverSocket = sock;
		serverChat = server;
	}

	// thuật giải của thread : chờ nhận yêu cầu nối kết từ client
	public void run() {
		T_UserRec puser;
		try {
			while (true) {
				// chờ 1 yêu cầu
				Socket clientSocket = serverSocket.accept();
				// tạo record chứa các thông tin về yêu cầu
				puser = new T_UserRec();
				puser.sock = clientSocket;
				puser.next = serverChat.m_sock_no_user;
				serverChat.m_sock_no_user = puser;
				// tạo thread chờ nhận request từ client tương ứng
				new ReceivingThread(serverChat, clientSocket).start();
				// hiển thị thông tin về client tương ứng
				DefaultListModel lmContent = (DefaultListModel) serverChat.lbContent.getModel();
				lmContent.addElement("Connection received from: " + clientSocket.getInetAddress());
				serverChat.SendMessage(clientSocket, "Request accepted");
			} // end while
		} // xử lý lỗi ngoại lệ
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
