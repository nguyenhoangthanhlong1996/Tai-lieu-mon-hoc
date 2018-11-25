package baithuchanh5_1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.*;

public class FrameGPTB2 extends Frame {
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
	// ₫ịnh nghĩa các biến cần dùng
	double a, b, c;
	double delta;
	double x1, x2;

	public FrameGPTB2() { // constructor của frame
		super("Giải phương trình bậc 2");
		setSize(350, 230); /// thiết lập kích thước frame
		// ₫ịnh nghĩa hàm xử lý sự kiện ₫óng Form
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		KhoiTao();
	}

	// khởi tạo các đối tượng giao diện
	public void KhoiTao() {
		labelA = new Label("Nhập a : ");
		txtA = new TextField(20);
		labelB = new Label("Nhập b : ");
		txtB = new TextField(20);
		labelC = new Label("Nhập c : ");
		txtC = new TextField(20);
		btnStart = new Button("Bắt đầu giải");
		// ₫ịnh nghĩa hàm xử lý click chuột trên Button
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				giaiPTB2(); // gọi hàm giải ptb2
			}
		});
		lblKetqua = new Label("");
		lblX1 = new Label("");
		lblX2 = new Label("");
	}

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

	// hàm thiết kế dạng Flow Layout
	void UseFlowLayout() {
		// thiết lập LayoutManager cho Frame là FlowLayout
		this.setLayout(new FlowLayout());
		// add các phần tử vào Frame
		this.add(labelA);
		this.add(txtA);
		this.add(labelB);
		this.add(txtB);
		this.add(labelC);
		this.add(txtC);
		this.add(btnStart);
		this.add(lblKetqua);
		this.add(lblX1);
		this.add(lblX2);
		// hiển thị frame
		setVisible(true);
	}

	// hàm thiết kế dạng Grid Layout
	void UseGridLayout() {
		// thiết lập LayoutManager cho Frame
		this.setLayout(new GridLayout(7, 1));
		// tạo Panel chứa label và textbox A
		Panel mPanel = new Panel();
		// khai báo layout cho Panel có 2 cột
		mPanel.setLayout(new GridLayout(1, 2));
		mPanel.add(labelA);
		mPanel.add(txtA);
		// add Panel vào Frame
		this.add(mPanel);
		mPanel = new Panel();
		// khai báo layout cho Panel có 2 cột
		mPanel.setLayout(new GridLayout(1, 2));
		mPanel.add(labelB);
		mPanel.add(txtB);
		// add Panel vào Frame
		this.add(mPanel);
		// tạo Panel chứa label và textbox C
		mPanel = new Panel();
		// khai báo layout cho Panel có 2 cột
		mPanel.setLayout(new GridLayout(1, 2));
		mPanel.add(labelC);
		mPanel.add(txtC);
		this.add(mPanel);
		// tạo Panel có 3 cột ₫ể chứa Button ở giữa
		mPanel = new Panel();
		mPanel.setLayout(new GridLayout(1, 3));
		mPanel.add(new Label(""));
		mPanel.add(btnStart);
		mPanel.add(new Label(""));
		this.add(mPanel);
		this.add(lblKetqua);
		this.add(lblX1);
		this.add(lblX2);
		// hiển thị frame
		setVisible(true);
	}

	// hàm thiết kế dạng GridBag Layout
	void UseGridBagLayout() {
		// thiết lập LayoutManager cho Frame
		this.setLayout(new GridBagLayout());
		// Tạo đối tượng dùng để xác định vị trí của phần tử khi đưa vào GridBag
		GridBagConstraints c = new GridBagConstraints();
		// thêm labelA vào
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = 0.1;
		this.add(labelA, c);
		// thêm txtA vào
		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.weightx = 0.1;
		this.add(txtA, c);
		// thêm labelB vào
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = 0.1;
		this.add(labelB, c);
		// thêm labelA vào
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.weightx = 1;
		this.add(txtB, c);
		// thêm labelC vào
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = 0.1;
		this.add(labelC, c);
		// thêm txtC vào
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.weightx = 1;
		this.add(txtC, c);
		// thêm btnStart vào
		c.gridx = 1;
		c.gridy = 3;
		c.gridwidth = 1;
		c.gridheight = 1;
		this.add(btnStart, c);
		// thêm lblKetqua vào
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.weightx = 0.1;
		this.add(lblKetqua, c);
		// thêm lblX1 vào
		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.weightx = 0.1;
		this.add(lblX1, c);
		// thêm lblX2 vào
		c.gridx = 0;
		c.gridy = 6;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.weightx = 0.1;
		this.add(lblX2, c);
		// hiển thị frame
		setVisible(true);
	}

	// hàm thiết kế dạng Border Layout
	void UseBorderLayout() {
		// thiết lập LayoutManager cho Frame
		this.setLayout(new BorderLayout());
		// tạo Panel chứa các label và các textbox nhập liệu
		Panel mPanel = new Panel();
		// khai báo layout cho Panel có 3 hàng 2 cột
		mPanel.setLayout(new GridLayout(3, 2));
		mPanel.add(labelA);	
		mPanel.add(txtA);		
		mPanel.add(labelB);		
		mPanel.add(txtB);		
		mPanel.add(labelC);	
		mPanel.add(txtC);
		// add Panel vào Frame
		this.add(mPanel, BorderLayout.NORTH);
		// tạo Panel có 3 cột ₫ể chứa Button ở giữa
		mPanel = new Panel();
		mPanel.setLayout(new GridLayout(1, 3));
		mPanel.add(new Label(""));
		mPanel.add(btnStart); // add Button vào Panel
		mPanel.add(new Label(""));
		mPanel.setSize(30, 25);
		this.add(mPanel, BorderLayout.CENTER); // add Panel vào Frame
		mPanel = new Panel();
		mPanel.setLayout(new GridLayout(3, 1));
		mPanel.add(lblKetqua);
		mPanel.add(lblX1);
		mPanel.add(lblX2);
		// add Panel vào Frame
		this.add(mPanel, BorderLayout.SOUTH);
		// hiển thị frame
		setVisible(true);
	}
}
