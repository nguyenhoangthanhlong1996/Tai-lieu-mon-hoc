package baithuchanh5_2;

import java.awt.Button;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FrGPTB2 extends Frame {
	// ₫ịnh nghĩa các biến tham khảo ₫ến các ₫ối tượng giao diện
	protected Label labelA;
	protected TextField txtA;
	protected Label labelB;
	protected TextField txtB;
	protected Label labelC;
	protected TextField txtC;
	protected Button btnStart;
	protected Label lblKetqua;
	protected Label lblX1;
	protected Label lblX2;

	public FrGPTB2() { // constructor của frame
		super("Giải phương trình bậc 2");
		setSize(350, 230); // thiết lập kích thước frame
		// ₫ịnh nghĩa hàm xử lý sự kiện ₫óng Form
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	// xóa nội dung các Textbox và Label kết quả về null
	public void init() {
		txtA.setText(null);
		txtB.setText(null);
		txtC.setText(null);
		lblKetqua.setText(null);
		lblX1.setText(null);
		lblX2.setText(null);
	}

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
		this.setSize(this.getWidth(), this.getHeight() + 1);
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
}
