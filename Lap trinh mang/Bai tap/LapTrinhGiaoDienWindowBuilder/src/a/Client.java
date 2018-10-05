package a;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;

public class Client extends JFrame {

	private JPanel contentPane;
	private JTextField txtIP;
	private JTextField txtPort;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client frame = new Client();
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
	public Client() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 279);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblClient = new JLabel("CLIENT");
		lblClient.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblClient.setBounds(176, 30, 71, 22);
		contentPane.add(lblClient);
		
		txtIP = new JTextField();
		txtIP.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtIP.setBounds(50, 83, 153, 20);
		contentPane.add(txtIP);
		txtIP.setColumns(10);
		
		txtPort = new JTextField();
		txtPort.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtPort.setBounds(273, 83, 92, 20);
		contentPane.add(txtPort);
		txtPort.setColumns(10);
		
		JButton btnGetInfo = new JButton("Get Info");
		btnGetInfo.setBounds(160, 132, 89, 23);
		contentPane.add(btnGetInfo);
		
		JLabel lblIp = new JLabel("IP");
		lblIp.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblIp.setBounds(18, 86, 22, 14);
		contentPane.add(lblIp);
		
		JLabel lblPort = new JLabel("Port");
		lblPort.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPort.setBounds(229, 86, 34, 14);
		contentPane.add(lblPort);
		
		JLabel lblInfo = new JLabel("");
		lblInfo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblInfo.setBounds(18, 200, 390, 14);
		contentPane.add(lblInfo);
		
		//Nhấn nút Get Info
		btnGetInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Socket socket = new Socket(txtIP.getText(), Integer.parseInt(txtPort.getText()));
					lblInfo.setText(socket.toString());
				} catch (NumberFormatException e) {
					lblInfo.setText("Giá trị port phải là số nguyên");
					e.printStackTrace();
				} catch (UnknownHostException e) {
					lblInfo.setText(e.getMessage());
					e.printStackTrace();
				} catch (IOException e) {
					lblInfo.setText(e.getMessage());
					e.printStackTrace();
				}
			}
		});
	}
}
