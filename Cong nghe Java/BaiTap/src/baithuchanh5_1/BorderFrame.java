package baithuchanh5_1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class BorderFrame extends Frame {
	// ₫ịnh nghĩa các biến tham khảo ₫ến các ₫ối tượng giao diện

	private Label labelA;
	private TextField txtA;
	private Label labelB;
	private TextField txtB;
	private Label labelC;
	private TextField txtC;
	private Button btnStart;
	private Label lblKetqua;
	private Label lblX1;
	private Label lblX2;

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

	public BorderFrame() { // constructor của frame
		super("Giải phương trình bậc 2");
		setSize(350, 230); /// thiết lập kích thước frame
		// ₫ịnh nghĩa hàm xử lý sự kiện ₫óng Form
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		// thiết lập LayoutManager cho Frame
		this.setLayout(new BorderLayout());
		// tạo Panel chứa các label và các textbox nhập liệu
		Panel mPanel = new Panel();
		// khai báo layout cho Panel có 3 hàng 2 cột
		mPanel.setLayout(new GridLayout(3, 2));
		// tạo Label và add nó vào Panel
		labelA = new Label("Nhập a : ");
		mPanel.add(labelA);
		// tạo Textbox và add nó vào Panel
		txtA = new TextField("");
		mPanel.add(txtA);
		// tạo Label và add nó vào Panel
		labelB = new Label("Nhập b : ");
		mPanel.add(labelB);
		// tạo Textbox và add nó vào Panel
		txtB = new TextField("");
		mPanel.add(txtB);
		// tạo Label và add nó vào Panel
		labelC = new Label("Nhập c : ");
		mPanel.add(labelC);
		// tạo Textbox và add nó vào Panel
		txtC = new TextField("");
		mPanel.add(txtC);
		// add Panel vào Frame
		this.add(mPanel, BorderLayout.NORTH);
		// tạo Panel có 3 cột ₫ể chứa Button ở giữa
		mPanel = new Panel();
		mPanel.setLayout(new GridLayout(1, 3));
		mPanel.add(new Label(""));
		// tạo Button và add nó vào Panel
		btnStart = new Button("Bắt ₫ầu giải");
		// ₫ịnh nghĩa hàm xử lý click chuột trên Button
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				giaiPTB2(); // gọi hàm giải ptb2
			}
		});
		mPanel.add(btnStart); // add Button vào Panel
		mPanel.add(new Label(""));
		mPanel.setSize(30, 25);
		this.add(mPanel, BorderLayout.CENTER); // add Panel vào Frame
		mPanel = new Panel();
		mPanel.setLayout(new GridLayout(3, 1));
		// tạo Label kết quả và add nó vào Panel
		lblKetqua = new Label("");
		mPanel.add(lblKetqua);
		// tạo Label x1= và add nó vào Panel
		lblX1 = new Label("");
		mPanel.add(lblX1);
		// tạo Label x2= và add nó vào Panel
		lblX2 = new Label("9");
		mPanel.add(lblX2);
		// add Panel vào Frame
		this.add(mPanel, BorderLayout.SOUTH);
	}
}
