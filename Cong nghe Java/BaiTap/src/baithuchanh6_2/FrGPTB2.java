package baithuchanh6_2;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrGPTB2 extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField txtA;
	private JTextField txtB;
	private JTextField txtC;
	private JLabel lblThongBao, lblX1, lblX2;

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
			lblThongBao.setText("Phương trình vô nghiệm !");
			lblX1.setText("");
			lblX2.setText("");
		}
	}

	// Hàm xuất kết quả của phương trình bậc 2
	private void XuatKetqua() {
		// kiểm tra biệt số delata và quyết ₫ịnh xử lý
		if (delta >= 0) { // trường hợp có 2 nghiệm thực
			lblThongBao.setText("Phương trình có 2 nghiệm :");
			lblX1.setText("x1 = " + x1);
			lblX2.setText("x2 = " + x2);
		} else { // trường hợp vô nghiệm
			lblThongBao.setText("Phương trình vô nghiệm !");
			lblX1.setText("");
			lblX2.setText("");
		}
	}

	public FrGPTB2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 436, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNhpA = new JLabel("Nh\u1EADp a:");
		lblNhpA.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNhpA.setBounds(10, 19, 75, 17);
		contentPane.add(lblNhpA);

		txtA = new JTextField();
		txtA.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtA.setBounds(95, 17, 314, 20);
		contentPane.add(txtA);
		txtA.setColumns(10);

		JLabel lblNhpB = new JLabel("Nh\u1EADp b:");
		lblNhpB.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNhpB.setBounds(10, 56, 75, 17);
		contentPane.add(lblNhpB);

		txtB = new JTextField();
		txtB.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtB.setColumns(10);
		txtB.setBounds(95, 54, 314, 20);
		contentPane.add(txtB);

		JLabel lblNhpC = new JLabel("Nh\u1EADp c:");
		lblNhpC.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNhpC.setBounds(10, 85, 75, 17);
		contentPane.add(lblNhpC);

		txtC = new JTextField();
		txtC.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtC.setColumns(10);
		txtC.setBounds(95, 85, 314, 20);
		contentPane.add(txtC);

		JButton btnGiai = new JButton("B\u1EAFt \u0111\u1EA7u gi\u1EA3i");

		btnGiai.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnGiai.setBounds(151, 122, 111, 23);
		contentPane.add(btnGiai);

		lblThongBao = new JLabel("Th\u00F4ng b\u00E1o");
		lblThongBao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblThongBao.setBounds(21, 168, 330, 14);
		contentPane.add(lblThongBao);

		lblX1 = new JLabel("X1");
		lblX1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblX1.setBounds(21, 199, 330, 14);
		contentPane.add(lblX1);

		lblX2 = new JLabel("X2");
		lblX2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblX2.setBounds(21, 230, 330, 14);
		contentPane.add(lblX2);
		
		//xóa nội dung các Label
		lblThongBao.setText("");
		lblX1.setText("");
		lblX2.setText("");

		btnGiai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 1. Nhập 3 tham số a, b, c của phương trình
				NhapABC();
				// 2. Giải phương trình
				GiaiPT();
				// 3. Hiển thị kết quả
				XuatKetqua();
			}
		});
	}
}
