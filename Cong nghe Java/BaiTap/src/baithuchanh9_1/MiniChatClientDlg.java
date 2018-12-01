package baithuchanh9_1;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class MiniChatClientDlg extends JFrame implements MessageListener {

	// Giao diện
	private JPanel contentPane;
	private JTextField txtMessage;
	private JButton btnConnect;
	private JButton btnGroups;
	private JButton btnLogin;
	private JButton btnUsers;
	private JButton btnSend;
	private JButton btnLogout;
	private JButton btnDisConnect;
	private JList lbGroups;
	private JList lbUsers;
	private JList lbContent;
	// Các thuộc tính cần dùng
	private Socket socket; // socket giao tiếp với server
	private boolean connected = false; // cờ trạng thái làm việc của client
	private ReceivingThread receivingThread; // handle ₫ến thread con chờ reply
	// các hằng miêu tả request hiện hành
	private static final int FSGLIST = 0;
	private static final int FSLOGIN = 1;
	private static final int FSLOGOUT = 2;
	private static final int FSULIST = 3;
	private static final int FSMESG = 4;
	private int fstate = FSMESG;
	PrintWriter writer;

	public MiniChatClientDlg() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 748, 558);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnConnect = new JButton("Connect");

		btnConnect.setBounds(10, 11, 89, 23);
		contentPane.add(btnConnect);

		btnGroups = new JButton("Groups");

		btnGroups.setBounds(108, 11, 89, 23);
		contentPane.add(btnGroups);

		btnLogin = new JButton("Login");
		btnLogin.setBounds(212, 11, 89, 23);
		contentPane.add(btnLogin);

		btnUsers = new JButton("Users");
		btnUsers.setBounds(311, 11, 89, 23);
		contentPane.add(btnUsers);

		btnSend = new JButton("Send");
		btnSend.setBounds(416, 11, 89, 23);
		contentPane.add(btnSend);

		btnLogout = new JButton("Logout");
		btnLogout.setBounds(519, 11, 89, 23);
		contentPane.add(btnLogout);

		btnDisConnect = new JButton("DisConnect");
		btnDisConnect.setBounds(618, 11, 89, 23);
		contentPane.add(btnDisConnect);

		JLabel lblDanhSchCc = new JLabel("Danh s\u00E1ch c\u00E1c nh\u00F3m");
		lblDanhSchCc.setBounds(20, 45, 137, 14);
		contentPane.add(lblDanhSchCc);

		JLabel lblDanhSchCc_1 = new JLabel("Danh s\u00E1ch c\u00E1c user trong nh\u00F3m");
		lblDanhSchCc_1.setBounds(10, 266, 172, 14);
		contentPane.add(lblDanhSchCc_1);

		JLabel lblNiDungCn = new JLabel("N\u1ED9i dung c\u1EA7n g\u1EEDi");
		lblNiDungCn.setBounds(192, 45, 137, 14);
		contentPane.add(lblNiDungCn);

		JLabel lblNiDungChat = new JLabel("N\u1ED9i dung chat trong nh\u00F3m");
		lblNiDungChat.setBounds(192, 104, 137, 14);
		contentPane.add(lblNiDungChat);

		lbGroups = new JList();
		lbGroups.setBounds(10, 70, 147, 179);
		contentPane.add(lbGroups);

		lbUsers = new JList();
		lbUsers.setBounds(10, 291, 147, 179);
		contentPane.add(lbUsers);

		lbContent = new JList();
		lbContent.setBounds(192, 129, 530, 341);
		contentPane.add(lbContent);

		txtMessage = new JTextField();

		txtMessage.setBounds(192, 70, 515, 20);
		contentPane.add(txtMessage);
		txtMessage.setColumns(10);

		MiniChatClientDlg _self = this;
		// Sự kiện nhấn nút Connect
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// tạo form nhập thông tin về chat server
				ConnectDlg dlg = new ConnectDlg(new javax.swing.JFrame(), true, "Nhập thông tin về server");
				dlg.show();
				if (dlg.fOk) { // nếu người dùng click OK thì chạy tiếp
					try {
						// tạo socket nối kết chat server
						socket = new Socket(InetAddress.getByName(dlg.strAddress), dlg.intPort);
						// tạo và chạy thread chờ nhận reply từ chat server
						receivingThread = new ReceivingThread(_self, socket);
						receivingThread.start();
						// tạo ₫ối tượng phục vụ gởi request ₫ến server
						writer = new PrintWriter(socket.getOutputStream());
					} // xử lý lỗi ngoại lệ
					catch (Exception e) {
						e.printStackTrace();
					}
					// thiết lập trạng thái ₫ã kết nối
					connected = true;
				}
			}
		});

		// Sự kiện nhấn nút Groups
		btnGroups.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mesg = "GLIST ";
				SendMessage(writer, mesg);
				fstate = FSGLIST;
			}
		});

		// Sự kiện nhấn nút Login
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// tạo form nhập thông tin về user
				LoginDlg dlg;
				if (lbGroups.getSelectedValue() == null) {
					dlg = new LoginDlg(new JFrame(), true, "Nhập thông tin login", "");
				} else {
					dlg = new LoginDlg(new JFrame(), true, "Nhập thông tin login", lbGroups.getSelectedValue().toString());
				}
				dlg.show();
				if (dlg.fOk) { // nếu người dùng click OK thì chạy tiếp
					String request;
					request = "LOGIN " + dlg.GroupName + "," + dlg.UserName;
					SendMessage(writer, request);
					fstate = FSLOGIN;
				}
			}
		});

		// Sự kiện nhấn nút Users
		btnUsers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mesg = "ULIST ";
				SendMessage(writer, mesg);
				fstate = FSULIST;
			}
		});

		// Sự kiện nhấn nút Send
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SendToGroup();
			}
		});

		// Sự kiện nhấn nút Logout
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mesg;
				mesg = "LOGOU ";
				SendMessage(writer, mesg);
				fstate = FSLOGOUT;
			}
		});

		// Sự kiện nhấn nút DisConnect
		btnDisConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
					SendMessage(writer, "LOGOU ");
					fstate = FSLOGOUT;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		// Sự kiện txtMessage có phím được nhấn
		txtMessage.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				// nếu user gỏ Enter thì gởi chuỗi vừa nhập ₫i
				if (evt.getKeyCode() == 10)
					SendToGroup();
			}
		});

		// xóa nội dung các List và JTextField
		txtMessage.setText("");
		DefaultListModel model = new DefaultListModel();
		lbGroups.setModel(model);
		model = new DefaultListModel();
		lbUsers.setModel(model);
		model = new DefaultListModel();
		lbContent.setModel(model);
	}

	// hàm gởi 1 request ₫ến server
	private void SendMessage(PrintWriter writer, String mesg) {
		// hàm gởi 1 request ₫ến server rồiflush PrintWriter
		try {
			writer.println(mesg);
			writer.flush();
		} // xử lý lỗi ngoại lệ
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	// hàm gởi 1 message ₫ến các thành viên của nhóm (thông qua server)
	private void SendToGroup() {
		String mesg = txtMessage.getText();
		if (mesg != "") {
			SendMessage(writer, "SEND " + mesg);
		}
		fstate = FSMESG;
	}

	// hàm xử lý reply của server
	public void messageReceived(Socket sock, String mesg) {
		int status;
		if (mesg.compareTo("CLOSE ") == 0) {
			return;
		}
		switch (fstate) { // xử lý reply theo trạng thái lệnh request hiện hành
		case FSLOGIN:
			Do_login(mesg);
			break;
		case FSLOGOUT: // logout
			Do_logout(mesg);
			break;
		case FSMESG: // group list
			Do_receive(mesg);
			break;
		case FSGLIST: // user list
			Do_glist(mesg);
			break;
		case FSULIST: // broadcast message
			Do_ulist(mesg);
		}
		fstate = FSMESG;
	}

	// hàm xử lý reply của request Login
	private void Do_login(String mesg) {
	}

	// hàm xử lý reply của request Logout
	private void Do_logout(String mesg) {
		if (mesg.charAt(0) == '0') {
			return;
		}
		DefaultListModel lmUsers = (DefaultListModel) lbUsers.getModel();
		lmUsers.clear();
		DefaultListModel lmContent = (DefaultListModel) lbContent.getModel();
		lmContent.clear();
	}

	// hàm xử lý reply của request GLIST
	private void Do_glist(String mesg) {
		int i;
		DefaultListModel lmGroups = (DefaultListModel) lbGroups.getModel();
		lmGroups.clear();
		if (mesg.charAt(0) == '0') {
			return;
		}
		mesg = mesg.substring(2);
		// tokenize message to retrieve user name and message body
		java.util.StringTokenizer tokenizer = new java.util.StringTokenizer(mesg, ",");
		int size = tokenizer.countTokens();
		for (i = 0; i < size; i++) {
			lmGroups.addElement(tokenizer.nextToken());
		}
	}

	// hàm xử lý reply của request ULIST
	private void Do_ulist(String mesg) {
		String gname;
		int i;
		DefaultListModel lmUsers = (DefaultListModel) lbUsers.getModel();
		lmUsers.clear();
		if (mesg.charAt(0) == '0') {
			return;
		}
		if (mesg.length() < 3) {
			return;
		}
		mesg = mesg.substring(2);
		// tokenize message to retrieve user name and message body
		java.util.StringTokenizer tokenizer = new java.util.StringTokenizer(mesg, ",");
		int size = tokenizer.countTokens();
		for (i = 0; i < size; i++) {
			lmUsers.addElement(tokenizer.nextToken());
		}
	}

	// hàm xử lý reply của request MESG
	private void Do_receive(String mesg) {
		DefaultListModel lmContent = (DefaultListModel) lbContent.getModel();
		// append new message
		lmContent.addElement(mesg);
	}
}
