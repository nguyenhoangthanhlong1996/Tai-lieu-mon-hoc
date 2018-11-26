package baithuchanh5_2;

import java.awt.Button;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GridFrame extends FrGPTB2 {
	public GridFrame() { // constructor của frame
		super();
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
		this.add(mPanel);
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
		this.add(mPanel);
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
		this.add(mPanel);
		// tạo Panel có 3 cột ₫ể chứa Button ở giữa
		mPanel = new Panel();
		mPanel.setLayout(new GridLayout(1, 3));
		mPanel.add(new Label(""));
		// tạo Button và add nó vào Panel
		btnStart = new Button("Bắt đầu giải");
		// ₫ịnh nghĩa hàm xử lý click chuột trên Button
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				giaiPTB2(); // gọi hàm giải ptb2
			}
		});
		mPanel.add(btnStart); // add Button vào Panel
		mPanel.add(new Label(""));
		this.add(mPanel); // add Panel vào Frame
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
