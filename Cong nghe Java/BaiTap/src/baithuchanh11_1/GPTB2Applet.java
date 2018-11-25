package baithuchanh11_1;

import java.applet.Applet;
import java.awt.Button;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GPTB2Applet extends Applet {
	// ₫ịnh nghĩa các thuộc tính của Applet
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

	// hàm giải phương trình bậc 2 theo 3 tham số nhập
	void giaiPTB2() {
		// ₫ịnh nghĩa các biến cần dùng
		double a, b, c;
		double delta;
		double x1, x2;
		// mã hóa dữ liệu chuỗi thành giá trị số Double
		a = Double.parseDouble(txtA.getText());
		b = Double.parseDouble(txtB.getText());
		c = Double.parseDouble(txtC.getText());
		// tính biệt số delta
		delta = b * b - 4 * a * c;
		// kiểm tra biệt số delata và quyết ₫ịnh xử lý
		if (delta >= 0) { // trường hợp có 2 nghiệm thực
			x1 = (-b + Math.sqrt(delta)) / 2 / a;
			x2 = (-b - Math.sqrt(delta)) / 2 / a;
			lblKetqua.setText("Phuong trinh co 2 nghiem :");
			lblX1.setText("x1 = " + x1);
			lblX2.setText("x2 = " + x2);
		} else { // trường hợp vô nghiệm
			lblKetqua.setText("Phuong trinh vo nghiem !");
			lblX1.setText("");
			lblX2.setText("");
		}
	}

	// hàm khởi tạo trạng thái ₫ầu cho applet
	public void init() {
		// doc tham so moi truong va thiet lap kich thuoc applet
		 int r = Integer.parseInt(this.getParameter("rong"));
		 int c = Integer.parseInt(this.getParameter("cao"));
		 this.setSize(r, c);
//		// thiết lập kích thước frame
//		this.setSize(350, 180);
		// thiết lập LayoutManager cho Frame
		this.setLayout(new GridLayout(7, 1));
		// tạo Panel chứa label và textbox A
		Panel mPanel = new Panel();
		// khai báo layout cho Panel có 2 cột
		mPanel.setLayout(new GridLayout(1, 2));
		// tạo Label và add nó vào Panel
		labelA = new Label("Nhập a : ");
		mPanel.add(labelA);
		// tạo Textbox và add nó vào Panel
		txtA = new TextField("");
		mPanel.add(txtA);
		// add Panel vào Frame
		add(mPanel);
		// tạo Panel chứa label và textbox B
		mPanel = new Panel();
		// khai báo layout cho Panel có 2 cột
		mPanel.setLayout(new GridLayout(1, 2));
		// tạo Label và add nó vào Panel
		labelB = new Label("Nhập b : ");
		mPanel.add(labelB);
		// tạo Textbox và add nó vào Panel
		txtB = new TextField("");
		mPanel.add(txtB);
		// add Panel vào Frame
		add(mPanel);
		// tạo Panel chứa label và textbox C
		mPanel = new Panel();
		// khai báo layout cho Panel có 2 cột
		mPanel.setLayout(new GridLayout(1, 2));
		// tạo Label và add nó vào Panel
		labelC = new Label("Nhập c : ");
		mPanel.add(labelC);
		// tạo Textbox và add nó vào Panel
		txtC = new TextField("");
		mPanel.add(txtC);
		// add Panel vào Frame
		add(mPanel);
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
		add(mPanel); // add Panel vào Frame
		// tạo Panel chứa Label kết quả
		mPanel = new Panel();
		// khai báo layout cho Panel có 2 cột
		mPanel.setLayout(new GridLayout(1, 1));
		// tạo Label kết quả và add nó vào Panel
		lblKetqua = new Label("");
		mPanel.add(lblKetqua);
		add(mPanel); // add Panel vào Frame
		// tạo Panel chứa Label x1 =
		mPanel = new Panel();
		// khai báo layout cho Panel có 2 cột
		mPanel.setLayout(new GridLayout(1, 1));
		// tạo Label x1= và add nó vào Panel
		lblX1 = new Label("");
		mPanel.add(lblX1);
		add(mPanel); // add Panel vào Frame
		// tạo Panel chứa Label x2 =
		mPanel = new Panel();
		mPanel.setLayout(new GridLayout(1, 1));
		// tạo Label x2= và add nó vào Panel
		lblX2 = new Label("");
		mPanel.add(lblX2);
		add(mPanel); // add Panel vào Frame
	}
}
