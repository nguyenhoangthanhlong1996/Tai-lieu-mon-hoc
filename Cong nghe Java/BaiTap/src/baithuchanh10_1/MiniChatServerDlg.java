package baithuchanh10_1;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.StringTokenizer;

public class MiniChatServerDlg extends JFrame implements MessageListener {

	int m_groupcnt; // số nhóm
	T_UserRec m_sock_no_user = null; // danh sách user chưa login
	T_GroupList m_grouplist[] = new T_GroupList[10]; // danh sách nhóm
	String uname; // tên user
	ServerSocket serverSocket; // socket server dùng ₫ể accept
	final int SERVER_PORT = 256; // port lắng nghe của server
	private JPanel contentPane;
	public JList lbContent;
	private JList lbGroups;

	public MiniChatServerDlg() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 623, 469);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenu mnMenu = new JMenu("Help");
		menuBar.add(mnMenu);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblDanhSchCc = new JLabel("Danh s\u00E1ch c\u00E1c nh\u00F3m");
		lblDanhSchCc.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDanhSchCc.setBounds(10, 11, 164, 14);
		contentPane.add(lblDanhSchCc);

		JLabel lblDanhSchCc_1 = new JLabel("Danh s\u00E1ch c\u00E1c users");
		lblDanhSchCc_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDanhSchCc_1.setBounds(10, 213, 164, 14);
		contentPane.add(lblDanhSchCc_1);

		JLabel lblTrnhTCc = new JLabel("Tr\u00ECnh t\u1EF1 c\u00E1c s\u1EF1 ki\u1EC7n x\u1EA3y ra");
		lblTrnhTCc.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTrnhTCc.setBounds(221, 6, 225, 24);
		contentPane.add(lblTrnhTCc);

		lbGroups = new JList();

		lbGroups.setBounds(10, 36, 142, 152);
		contentPane.add(lbGroups);

		JList lbUsers = new JList();
		lbUsers.setBounds(10, 240, 142, 152);
		contentPane.add(lbUsers);

		lbContent = new JList();
		lbContent.setBounds(172, 36, 414, 356);
		contentPane.add(lbContent);

		lbGroups.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// xác ₫ịnh tên nhóm ₫ược chjọn trong listbox lbGroups
				String gname = lbGroups.getSelectedValue().toString();
				int i, j;
				if (gname == null)
					return;
				// tìm chỉ số nhóm cần tìm trong danh sách nhóm
				for (i = 0; i < m_groupcnt; i++)
					if (gname.equals(m_grouplist[i].name))
						break;
				if (i >= m_groupcnt)
					return;
				T_UserRec ulist = m_grouplist[i].userlist;
				DefaultListModel lmUsers = (DefaultListModel) lbUsers.getModel();
				lmUsers.clear();
				while (ulist != null) {
					lmUsers.addElement(ulist.name);
					ulist = ulist.next;
				}
			}
		});

		// xóa nội dung các List
		DefaultListModel model = new DefaultListModel();
		lbGroups.setModel(model);
		model = new DefaultListModel();
		lbUsers.setModel(model);
		model = new DefaultListModel();
		lbContent.setModel(model);
		// ₫ọc danh sách nhóm từ database và hiển thị
		ReadDisplayGroups();
		// tạo socket ₫ể accept cho server
		try {
			serverSocket = new ServerSocket(SERVER_PORT, 100);
			DefaultListModel lmContent = (DefaultListModel) lbContent.getModel();
			lmContent.addElement("Server listening on port " + SERVER_PORT + " ...");
			// tạo thread chuyên chờ accept cho server
			new ServerAcceptThread(this, serverSocket).start();
		}
		// xử lý lỗi ngoại lệ
		catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	// hàm ₫ọc danh sách nhóm từ database và hiển thị lên listbox
	private void ReadDisplayGroups() {
		String path  = "src\\baithuchanh10_1\\database_minichat.mdb";
		String conStr = "jdbc:ucanaccess://"+path;
		Connection con;
		String newSQL = "Select * from GroupList";
		String[] data = { "dummy" };
		DefaultListModel lmGroups = (DefaultListModel) lbGroups.getModel();
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			// 1. Tao connection mieu ta database can truy xuat
			con = DriverManager.getConnection(conStr, "", "");
			// 2. Tao 1 doi tuong Statement lien ket den connection
			java.sql.Statement stmt = con.createStatement();
			// 4. Tao doi tuong recordset chua ket qua cua lenh SQL
			ResultSet rs = stmt.executeQuery(newSQL);
			// 5. Duyet recordset de xu ly cac record cua no
			int i = 0;
			lmGroups.clear();
			if (rs != null) {
				while (rs.next()) {
					m_grouplist[i] = new T_GroupList();
					m_grouplist[i].name = rs.getString("groupname");
					lmGroups.addElement(m_grouplist[i].name);
					i++;
				}
			}
			m_groupcnt = i;
			// 6. Dong cac dong tuong da tao ra
			rs.close();
			stmt.close();
			con.close();
		} catch (Exception e) {
			System.out.println("Error : " + e);
		}
	}

	// hàm xử lý request của client gởi tới từ socket
	public void messageReceived(Socket sock, String mesg) {
		int status;
		String opcode = mesg.substring(0, 5);
		if (opcode.compareTo("LOGIN") == 0) { // login
			Do_login(sock, mesg);
		} else if (opcode.compareTo("LOGOU") == 0) { // logout
			Do_logout(sock);
		} else if (opcode.compareTo("GLIST") == 0) { // group list
			Do_glist(sock);
		} else if (opcode.compareTo("ULIST") == 0) { // user list
			Do_ulist(sock);
		} else if (opcode.compareTo("CLOSE") == 0) { // user list
			Do_CloseSock(sock);
		} else { // broadcast message
			Do_MulticastMesg(sock, mesg.substring(5));
		}
	}

	// hàm xử lý request GLIST
	private void Do_glist(Socket sock) {
		String mesg, mesg1;
		int i;
		mesg = "1 " + m_grouplist[0].name;
		for (i = 1; i < m_groupcnt; i++) {
			mesg = mesg + "," + m_grouplist[i].name;
		}
		SendMessage(sock, mesg);
	}

	// hàm tìm nhóm của client giao tiếp thông qua socket sock
	private int Findgroup(Socket sock) {
		int i;
		T_UserRec pu;
		for (i = 0; i < m_groupcnt; i++) {
			pu = m_grouplist[i].userlist;
			while (pu != null) {
				if (pu.sock == sock) {
					uname = pu.name;
					return i;
				}
				pu = pu.next;
			}
		}
		return -1;
	}

	// hàm xử lý request ULIST
	private void Do_ulist(Socket sock) {
		String mesg;
		int i = Findgroup(sock);
		if (i < 0 || m_grouplist[i].userlist == null) {
			mesg = "0 ";
		} else {
			T_UserRec pu = m_grouplist[i].userlist;
			mesg = "1 ";
			while (pu != null) {
				mesg = mesg + pu.name;
				pu = pu.next;
				if (pu != null) {
					mesg = mesg + ",";
				}
			}
		}
		SendMessage(sock, mesg);
	}

	// hàm xử lý request MESG
	private void Do_MulticastMesg(Socket sock, String mesg) {
		int i = Findgroup(sock);
		if (i < 0) {
			return;
		}
		mesg = uname + ":" + mesg;
		T_UserRec pu = m_grouplist[i].userlist;
		while (pu != null) {
			SendMessage(pu.sock, mesg);
			pu = pu.next;
		}
	}

	// hàm ₫óng socket
	private void Do_CloseSock(Socket sock) {
		int i = Findgroup(sock);
		T_UserRec pu, pup = null;
		if (i >= 0) {
			pu = m_grouplist[i].userlist;
			while (pu != null && pu.sock != sock) {
				pup = pu;
				pu = pu.next;
			}
			if (pu == m_grouplist[i].userlist) {
				m_grouplist[i].userlist = pu.next;
			} else {
				pup.next = pu.next;
			}
		} else {
			pu = m_sock_no_user;
			while (pu != null && pu.sock != sock) {
				pup = pu;
				pu = pu.next;
			}
			if (pu == m_sock_no_user) {
				m_sock_no_user = pu.next;
			} else {
				pup.next = pu.next;
			}
		}
		try {
			sock.close();
		}
		// handle exception connecting to server
		catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	// hàm xử lý request LOGIN
	private void Do_login(Socket sock, String mesg) {
		T_UserRec pup, pu;
		String gname, uname;
		int i;
		mesg = mesg.substring(6);
		// tokenize message to retrieve user name and message body
		StringTokenizer tokenizer = new StringTokenizer(mesg, ",");
		// ignore messages that do not contain a user
		// name and message body
		if (tokenizer.countTokens() == 2) {
			gname = tokenizer.nextToken();
			uname = tokenizer.nextToken();
			// tim group tuong ung
			for (i = 0; i < m_groupcnt; i++) {
				if (gname.compareTo(m_grouplist[i].name) == 0) {
					break;
				}
			}
			System.out.println(gname + " " + uname + " " + i);
			if (i < m_groupcnt) {
				// tim sokcet trong danh sach chua co user
				pup = pu = m_sock_no_user;
				while (pu != null && pu.sock != sock) {
					pup = pu;
					pu = pu.next;
				}
				if (pu != null) {
					if (pu == m_sock_no_user) {
						m_sock_no_user = pu.next;
					} else {
						pup.next = pu.next;
					}
					pu.next = m_grouplist[i].userlist;
					pu.name = uname;
					m_grouplist[i].userlist = pu;
					uname = "1 ";
					SendMessage(sock, uname);
					return;
				}
			}
		}
		errordisp: uname = "0 ";
		SendMessage(sock, uname);
	}

	// hàm xử lý request LOGOU
	private void Do_logout(Socket sock) {
		int i = Findgroup(sock);
		T_UserRec pu, pup = null;
		if (i >= 0) {
			pu = m_grouplist[i].userlist;
			while (pu != null && pu.sock != sock) {
				pup = pu;
				pu = pu.next;
			}
			if (pu == m_grouplist[i].userlist) {
				m_grouplist[i].userlist = pu.next;
			} else {
				pup.next = pu.next;
			}
			pu.next = m_sock_no_user;
			m_sock_no_user = pu;
			SendMessage(sock, "1 ");
		} else {
			SendMessage(sock, "0 ");
		}
	}

	// hàm xử lý chuỗi reply
	public void SendMessage(Socket sock, String mesg) {
		// send message and flush PrintWriter
		try {
			PrintWriter writer = new PrintWriter(sock.getOutputStream());
			writer.println(mesg);
			writer.flush();
		} // handle exception sending message
		catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}
}
