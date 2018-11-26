package baithuchanh5_2;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BorderFrame extends FrGPTB2 {
	public BorderFrame() { // constructor của frame
		super();
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
