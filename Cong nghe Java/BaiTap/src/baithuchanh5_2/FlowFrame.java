package baithuchanh5_2;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FlowFrame extends FrGPTB2 {
	public FlowFrame() { // constructor của frame
		super();
		// thiết lập LayoutManager cho Frame
		this.setLayout(new FlowLayout());
		// tạo Label và add nó vào Panel
		labelA = new Label("Nhập a : ");
		this.add(labelA);
		// tạo Textbox và add nó vào Panel
		txtA = new TextField(20);
		this.add(txtA);
		// tạo Label và add nó vào Panel
		labelB = new Label("Nhập b : ");
		this.add(labelB);
		// tạo Textbox và add nó vào Panel
		txtB = new TextField(20);
		this.add(txtB);
		// tạo Label và add nó vào Panel
		labelC = new Label("Nhập c : ");
		this.add(labelC);
		// tạo Textbox và add nó vào Panel
		txtC = new TextField(20);
		this.add(txtC);
		// tạo Button và add nó vào Panel
		btnStart = new Button("Bắt đầu giải");
		// ₫ịnh nghĩa hàm xử lý click chuột trên Button
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				giaiPTB2(); // gọi hàm giải ptb2
			}
		});
		this.add(btnStart); // add Button vào Panel
		// tạo Label kết quả và add nó vào Panel
		lblKetqua = new Label("");
		this.add(lblKetqua);
		// tạo Label x1= và add nó vào Panel
		lblX1 = new Label("");
		this.add(lblX1);
		// tạo Label x2= và add nó vào Panel
		lblX2 = new Label("9");
		this.add(lblX2);
	}
}
