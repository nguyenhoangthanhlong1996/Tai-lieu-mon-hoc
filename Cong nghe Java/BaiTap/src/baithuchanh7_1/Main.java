package baithuchanh7_1;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class Main extends JFrame {

	private JPanel contentPane;
	private JTextField txtPath;
	Process prc;

	
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
		setBounds(100, 100, 544, 188);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNha = new JLabel("Nh\u1EADp \u0111\u01B0\u1EDDng d\u1EABn file ch\u01B0\u01A1ng tr\u00ECnh c\u1EA7n ch\u1EA1y:");
		lblNha.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNha.setBounds(10, 11, 301, 14);
		contentPane.add(lblNha);
		
		txtPath = new JTextField();
		txtPath.setEditable(false);
		txtPath.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtPath.setBounds(10, 36, 396, 20);
		contentPane.add(txtPath);
		txtPath.setColumns(10);
		
		JButton btnBrowse = new JButton("Browse..");
		
		btnBrowse.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnBrowse.setBounds(416, 35, 98, 23);
		contentPane.add(btnBrowse);
		
		JButton btnStart = new JButton("Start");
		
		btnStart.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnStart.setBounds(55, 94, 98, 23);
		contentPane.add(btnStart);
		
		JButton btnStop = new JButton("Stop");
		
		btnStop.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnStop.setBounds(171, 94, 98, 23);
		contentPane.add(btnStop);
		
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//tạo và hiển thị ₫ối tượng duyệt hệ thống file
				JFileChooser openFileDlg = new JFileChooser();
				if (openFileDlg.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) return;
				//hiển thị ₫ường dẫn file ₫ược chọn vào textbox
				txtPath.setText(openFileDlg.getSelectedFile().getPath());
			}
		});
		
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//khởi chạy chương trình do người dùng chọn
				try {
					prc = Runtime.getRuntime().exec(txtPath.getText());
				} catch (IOException e1) {
					System.out.println(e.toString());
					e1.printStackTrace();
				}
			}
		});
		
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//dừng và thoát chương trình
				prc.destroy();
			}
		});
	}
}
