package caua;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Main extends JFrame {

	private JPanel contentPane;
	private JTextField txtAddress;
	private JTextField txtPort;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 486, 354);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblHostname = new JLabel("Address");
		lblHostname.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblHostname.setBounds(33, 40, 91, 34);
		contentPane.add(lblHostname);

		JLabel lblPort = new JLabel("Port");
		lblPort.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPort.setBounds(33, 72, 91, 34);
		contentPane.add(lblPort);

		txtAddress = new JTextField();
		txtAddress.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtAddress.setBounds(156, 46, 268, 22);
		contentPane.add(txtAddress);
		txtAddress.setColumns(10);

		txtPort = new JTextField();
		txtPort.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtPort.setColumns(10);
		txtPort.setBounds(156, 78, 76, 22);
		contentPane.add(txtPort);

		JButton btnGetInfo = new JButton("Get Info");
		btnGetInfo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnGetInfo.setBounds(288, 77, 136, 40);
		contentPane.add(btnGetInfo);

		JLabel lblThong = new JLabel("Th\u00F4ng tin Socket");
		lblThong.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblThong.setBounds(33, 135, 199, 34);
		contentPane.add(lblThong);

		JTextArea txtInfo = new JTextArea();
		txtInfo.setEditable(false);
		txtInfo.setForeground(new Color(128, 0, 0));
		txtInfo.setFont(new Font("Courier New", Font.PLAIN, 14));
		txtInfo.setBounds(35, 173, 389, 121);
		contentPane.add(txtInfo);

		btnGetInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String host = null;
				try {
					 host = txtAddress.getText();
					int port = Integer.parseInt(txtPort.getText());
					Socket socket = new Socket(host, port);
					String info = "Host name: " + socket.getInetAddress().getHostName() + "\n" + "Host address: "
							+ socket.getInetAddress().getHostAddress() + "\n" + "Port: " + socket.getPort() + "\n"
							+ "Local port: " + socket.getLocalPort() + "\n" + "Localhost: "
							+ socket.getInetAddress().getLocalHost() + "\n" + "Address: "
							+ socket.getInetAddress().getAddress() + "\n";
					txtInfo.setText(info);
				} catch (NumberFormatException e) {
					txtInfo.setText("Giá trị port nhập vào không hợp lệ");
				} catch (UnknownHostException e) {
					txtInfo.setText("Không tìm thấy host " + host);
				} catch (IOException e) {
					txtInfo.setText(e.getMessage());
				}
			}
		});
	}
}
