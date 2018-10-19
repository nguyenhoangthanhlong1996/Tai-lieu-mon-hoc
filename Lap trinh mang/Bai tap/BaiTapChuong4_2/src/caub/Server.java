package caub;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Server extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the frame.
	 */
	public Server() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 657, 455);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("SERVER");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(283, 24, 90, 22);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("B\u1EADt server");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(12, 74, 111, 25);
		contentPane.add(btnNewButton);
		
		JButton btnTtServer = new JButton("T\u1EAFt server");
		btnTtServer.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnTtServer.setBounds(12, 112, 111, 25);
		contentPane.add(btnTtServer);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(144, 58, 483, 291);
		contentPane.add(textArea);
		
		textField = new JTextField();
		textField.setBounds(221, 362, 313, 32);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(144, 362, 65, 32);
		contentPane.add(textField_1);
		
		JLabel lblPort = new JLabel("Port");
		lblPort.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPort.setBounds(74, 370, 35, 16);
		contentPane.add(lblPort);
		
		JButton btnGi = new JButton("G\u1EEDi");
		btnGi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnGi.setBounds(546, 362, 81, 31);
		contentPane.add(btnGi);
	}

}
