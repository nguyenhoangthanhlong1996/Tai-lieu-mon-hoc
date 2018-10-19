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

public class Client extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

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
		setBounds(100, 100, 657, 455);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CLIENT");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(283, 24, 90, 22);
		contentPane.add(lblNewLabel);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(144, 58, 483, 291);
		contentPane.add(textArea);
		
		textField = new JTextField();
		textField.setBounds(144, 362, 390, 32);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnGi = new JButton("G\u1EEDi");
		btnGi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnGi.setBounds(546, 362, 81, 31);
		contentPane.add(btnGi);
		
		JLabel lblNgiDngOnline = new JLabel("Ng\u01B0\u1EDDi d\u00F9ng Online");
		lblNgiDngOnline.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNgiDngOnline.setBounds(12, 32, 128, 22);
		contentPane.add(lblNgiDngOnline);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 58, 120, 336);
		contentPane.add(panel);
	}
}
