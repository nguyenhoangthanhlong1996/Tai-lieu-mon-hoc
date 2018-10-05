package a;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends JFrame {

	final int port = 3000;
    private ServerSocket serverSocket;
	private JPanel contentPane;
	JLabel lblInfo;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Server frame = new Server();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public Server() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 527, 153);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblServer = new JLabel("SERVER");
		lblServer.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblServer.setBounds(211, 30, 71, 33);
		contentPane.add(lblServer);
		
		lblInfo = new JLabel("lblInfo");
		lblInfo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblInfo.setBounds(28, 74, 453, 19);
		contentPane.add(lblInfo);
		
		ServerListener();
	}
	
	
	public void ServerListener() {
		try {
			serverSocket = new ServerSocket(port);
			//Show info server
			String info = String.format("IP: %1$s, Port: %2$s", serverSocket.getInetAddress().getLoopbackAddress(), serverSocket.getLocalPort());
			lblInfo.setText(info);
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					while (true) {
						//Server listen
						Socket socket;
						try {
							socket = serverSocket.accept();
							ConnectionToClient cts = new ConnectionToClient();
				            cts.start();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}).start();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //Mở kết nối server
	}
}
