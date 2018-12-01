package baithuchanh9_1;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginDlg extends JDialog {

	//₫ịnh nghĩa các thuộc tính cần dùng
	boolean fOk = false;
	String GroupName;
	String UserName;
	//Giao diện
	private final JPanel contentPanel = new JPanel();
	private JTextField txtGroup;
	private JTextField txtUser;
	private JButton btnOK;
	private JButton btnCancel;
	
	public static void main(String[] args) {
		LoginDlg dialog = new LoginDlg(new JFrame(), true,"","");
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
	}

	//hàm constructor cho class LoginDlg
	public LoginDlg(JFrame parent, boolean modal, String title, String gname) {
		super(parent, modal);
		this.setTitle(title);
		setBounds(100, 100, 450, 150);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 434, 154);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		JLabel lblNhpaCh = new JLabel("Nhập tên nhóm");
		lblNhpaCh.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNhpaCh.setBounds(10, 11, 139, 20);
		contentPanel.add(lblNhpaCh);
		
		JLabel lblNhpPortServer = new JLabel("Nhập nickname");
		lblNhpPortServer.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNhpPortServer.setBounds(10, 42, 122, 17);
		contentPanel.add(lblNhpPortServer);
		
		txtGroup = new JTextField();
		txtGroup.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtGroup.setBounds(159, 11, 253, 20);
		contentPanel.add(txtGroup);
		txtGroup.setColumns(10);
		
		txtUser = new JTextField();
		txtUser.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtUser.setColumns(10);
		txtUser.setBounds(159, 40, 253, 20);
		contentPanel.add(txtUser);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 79, 434, 33);
			contentPanel.add(buttonPane);
			buttonPane.setLayout(null);
			{
				btnOK = new JButton("OK");
				
				btnOK.setBounds(142, 0, 61, 27);
				btnOK.setFont(new Font("Tahoma", Font.PLAIN, 15));
				btnOK.setActionCommand("OK");
				buttonPane.add(btnOK);
				getRootPane().setDefaultButton(btnOK);
			}
			{
				btnCancel = new JButton("Cancel");
				
				btnCancel.setBounds(229, 0, 75, 27);
				btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 15));
				btnCancel.setActionCommand("Cancel");
				buttonPane.add(btnCancel);
			}
		}
		
		//Sự kiện nhấn nút OK
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fOk = true;
				GroupName = txtGroup.getText();
				UserName = txtUser.getText();
				setVisible(false);
			}
		});
		
		//Sự kiện nhấn nút Cancel
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fOk = false;
				setVisible(false);
			}
		});
		
		txtGroup.setText(gname);
	}
}
