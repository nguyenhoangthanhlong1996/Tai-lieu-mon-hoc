package baithuchanh7_2;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.awt.event.ActionEvent;

public class MyFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtSoThread;

	// ₫ịnh nghĩa các biến cần dùng
	DefaultListModel lmKetqua;
	final int MAXTH = 30;
	double[][] A;
	double[][] B;
	double[][] C;
	boolean fdisp = false;
	int N; // số hàng, cột của các ma trận
	int[] stateLst = new int[MAXTH]; // danh sách trạng thái hoàn thành các thread
	int cnt; // số thread cần chạy
	long t1; // thời ₫iểm bắt ₫ầu tính tích

	// hàm tính các hàng ma trận tích từ sr tới er-1
	public void TinhTich(int sr, int er, int id) {
		int h, c, k;
		for (h = sr; h < er; h++)
			for (c = 0; c < N; c++) {
				double s = 0;
				for (k = 0; k < N; k++)
					s = s + A[h][k] * B[k][c];
				C[h][c] = s;
			}
		stateLst[id] = 1;
	}

	// hàm kiểm tra xem các thread ₫ã xong chưa
	public synchronized void isEnd() {
		int i;
		// lặp kiểm tra các thread con tính xong
		for (i = 0; i < cnt; i++)
			if (stateLst[i] == 0)
				return;
		// các thread con ₫ã tính xong
		if (fdisp)
			return;// nếu ₫ã hiển thị kết quả thì khỏi làm nữa
		fdisp = true;
		// tính và hiển thị thời gian chạy
		long t2 = Calendar.getInstance().getTimeInMillis();

		long diff = t2 - t1;
		long diffS = (diff / 1000) % 60;
		long diffM = diff / (60 * 1000);
		diff = diff % 1000;
		String buf = cnt + " threads ==> Thời gian chạy là " + diffM + " phút " + diffS + " giây " + diff + " ms";
		lmKetqua.addElement(buf);
	}

	public MyFrame() { 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 524, 317);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNhpSThread = new JLabel("Nh\u1EADp s\u1ED1 thread c\u1EA7n d\u00F9ng:");
		lblNhpSThread.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNhpSThread.setBounds(10, 21, 194, 14);
		contentPane.add(lblNhpSThread);

		txtSoThread = new JTextField();
		txtSoThread.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtSoThread.setBounds(214, 18, 152, 20);
		contentPane.add(txtSoThread);
		txtSoThread.setColumns(10);

		JButton btnTinh = new JButton("B\u1EAFt \u0111\u1EA7u t\u00EDnh");

		btnTinh.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnTinh.setBounds(376, 17, 119, 23);
		contentPane.add(btnTinh);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 57, 485, 210);
		contentPane.add(scrollPane);

		JList listKetQua = new JList();
		scrollPane.setViewportView(listKetQua);

		// thiết lập nội dung ban ₫ầu null cho Textbox và Listbox
		txtSoThread.setText("");
		lmKetqua = new DefaultListModel();
		listKetQua.setModel((ListModel) lmKetqua);
		// khởi tạo các ma trận A, B theo số liệu giả
		N = 1000;
		A = new double[N][N];
		B = new double[N][N];
		C = new double[N][N];
		int h, c;
		for (h = 0; h < N; h++)
			for (c = 0; c < N; c++)
				A[h][c] = B[h][c] = c;

		MyFrame self = this;
		btnTinh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String buf;
				// xác ₫ịnh số thread cần chạy
				cnt = Integer.valueOf(txtSoThread.getText().toString());
				t1 = Calendar.getInstance().getTimeInMillis();
				if (cnt == 1) { // dùng thuật giải tuần tự
					TinhTich(0, N, 0);
				} else // dùng thuật giải song song
				{
					int i;
					Thread t;
					for (i = 0; i < cnt - 1; i++) {
						stateLst[i] = 0;
						// tạo thread mới và truyền các tham số cần thiết cho nó biết
						t = new MyThread(i * N / cnt, (i + 1) * N / cnt, i, self);
						t.start();
					}
					// bản thân thread cha sẽ tính N/cnt hàng cuối của ma trận tích
					TinhTich((cnt - 1) * N / cnt, N, cnt - 1);
				}
				isEnd();
			}
		});
	}
}
