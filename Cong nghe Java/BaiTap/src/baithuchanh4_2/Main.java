package baithuchanh4_2;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class Main extends JFrame {

	private JPanel contentPane;
	private JTextField txtPath;

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
		setBounds(100, 100, 619, 403);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("\u0110\u01B0\u1EDDng d\u1EABn file c\u1EA7n hi\u1EC3n th\u1ECB:");
		lblNewLabel.setBounds(12, 13, 178, 19);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		contentPane.add(lblNewLabel);

		txtPath = new JTextField();
		txtPath.setEditable(false);
		txtPath.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtPath.setBounds(12, 37, 456, 22);
		contentPane.add(txtPath);
		txtPath.setColumns(10);

		JButton btnBrowse = new JButton("Browse..");
		btnBrowse.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnBrowse.setBounds(493, 37, 97, 25);
		contentPane.add(btnBrowse);

		JTextArea txtContent = new JTextArea();
		txtContent.setEditable(false);
		txtContent.setBounds(12, 72, 578, 271);
		contentPane.add(txtContent);

		// Sự kiện nhấn nút Browse
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser openFileDlg = new JFileChooser();
				if (openFileDlg.showSaveDialog(null) != JFileChooser.APPROVE_OPTION)
					return;
				String path = openFileDlg.getSelectedFile().getPath();
				txtPath.setText(path);
				try {
					FileInputStream file = new FileInputStream(path);
					int c;
					String content = "";
					while ((c=file.read()) != -1) {
						content+=(char)c;
					}
					txtContent.setText(content);
				} catch (FileNotFoundException e) {
					txtContent.setText("File này không được tìm thấy");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}
}
