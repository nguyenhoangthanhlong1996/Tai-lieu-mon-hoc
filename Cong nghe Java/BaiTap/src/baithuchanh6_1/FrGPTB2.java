package baithuchanh6_1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;

import com.borland.jbcl.layout.XYConstraints;
import com.borland.jbcl.layout.XYLayout;

public class FrGPTB2 extends JFrame {
	// ₫ịnh nghĩa các biến tham khảo ₫ến các ₫ối tượng giao diện
	private JLabel labelA;
	private JTextField txtA;
	private JLabel labelB;
	private JTextField txtB;
	private JLabel labelC;
	private JTextField txtC;
	private JButton btnStart;
	private JLabel lblKetqua;
	private JLabel lblX1;
	private JLabel lblX2;
	// ₫ịnh nghĩa các biến cần dùng
	double a, b, c;
	double delta;
	double x1, x2;

	// Hàm nhập 3 tham số a, b, c của phương trình
	private void NhapABC() {
		// mã hóa dữ liệu chuỗi thành giá trị số Double
		a = Double.valueOf(txtA.getText());
		b = Double.valueOf(txtB.getText());
		c = Double.valueOf(txtC.getText());
	}

	// Hàm giải phương trình bậc 2
	private void GiaiPT() {
		// tính biệt số delta
		delta = b * b - 4 * a * c;
		// kiểm tra biệt số delata và quyết ₫ịnh xử lý
		if (delta >= 0) { // trường hợp có 2 nghiệm thực
			x1 = (-b + Math.sqrt(delta)) / 2 / a;
			x2 = (-b - Math.sqrt(delta)) / 2 / a;
		} else { // trường hợp vô nghiệm
			lblKetqua.setText("Phương trình vô nghiệm !");
			lblX1.setText("");
			lblX2.setText("");
		}
	}

	// Hàm xuất kết quả của phương trình bậc 2
	private void XuatKetqua() {
		// kiểm tra biệt số delata và quyết ₫ịnh xử lý
		if (delta >= 0) { // trường hợp có 2 nghiệm thực
			lblKetqua.setText("Phương trình có 2 nghiệm :");
			lblX1.setText("x1 = " + x1);
			lblX2.setText("x2 = " + x2);
		} else { // trường hợp vô nghiệm
			lblKetqua.setText("Phương trình vô nghiệm !");
			lblX1.setText("");
			lblX2.setText("");
		}
	}

	// hàm giải phương trình bậc 2 theo 3 tham số nhập
	void giaiPTB2() {
		// 1. Nhập 3 tham số a, b, c của phương trình
		NhapABC();
		// 2. Giải phương trình
		GiaiPT();
		// 3. Hiển thị kết quả
		XuatKetqua();
	}

	public FrGPTB2() { // constructor của frame
		super("Giải phương trình bậc 2");
		setSize(325, 240); // thiết lập kích thước frame
		// ₫ịnh nghĩa hàm xử lý sự kiện ₫óng Form
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		// tạo JPanel & thiết lập LayoutManager cho JPanel
		JPanel mPanel = new JPanel();
		mPanel.setLayout(new XYLayout());
		// tạo Label và add nó vào Panel ở vị trí & kích thước mong muốn
		labelA = new JLabel("Nhập a : ");
		mPanel.add(labelA, new XYConstraints(10, 10, 80, 20));
		// tạo JTextField và add nó vào Panel ở vị trí & kích thước mong muốn
		txtA = new JTextField("");
		mPanel.add(txtA, new XYConstraints(100, 10, 200, 20));
		// tương tự, tạo JLabel và JTextbox ₫ể nhập b
		labelB = new JLabel("Nhập b : ");
		mPanel.add(labelB, new XYConstraints(10, 40, 80, 20));
		txtB = new JTextField("");
		mPanel.add(txtB, new XYConstraints(100, 40, 200, 20));
		// tương tự, tạo JLabel và JTextbox ₫ể nhập c
		labelC = new JLabel("Nhập c : ");
		mPanel.add(labelC, new XYConstraints(10, 70, 80, 20));
		txtC = new JTextField("");
		mPanel.add(txtC, new XYConstraints(100, 70, 200, 20));
		// tạo JButton ₫ể bắt ₫ầu giải
		btnStart = new JButton("Bắt đầu giải");
		// ₫ịnh nghĩa hàm xử lý click chuột trên Button
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				giaiPTB2();
			}
		});
		// add Button vào Panel với vị trí & kích thước mong muốn
		mPanel.add(btnStart, new XYConstraints(100, 100, 100, 20));
		// tạo JLabel và add nó vào Panel
		lblKetqua = new JLabel("");
		mPanel.add(lblKetqua, new XYConstraints(10, 130, 280, 20));
		// tạo JLabel X1 và add nó vào Panel
		lblX1 = new JLabel("");
		mPanel.add(lblX1, new XYConstraints(10, 150, 280, 20));
		// tạo JLabel X2 và add nó vào Panel
		lblX2 = new JLabel("");
		mPanel.add(lblX2, new XYConstraints(10, 170, 280, 20));
		add(mPanel);
	}
}
