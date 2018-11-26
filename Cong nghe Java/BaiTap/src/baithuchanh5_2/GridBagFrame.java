package baithuchanh5_2;

import java.awt.Button;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GridBagFrame extends FrGPTB2 {
	public GridBagFrame() { // constructor của frame
		super();
		// thiết lập LayoutManager cho Frame
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		// tạo Label và add nó vào Panel
		labelA = new Label("Nhập a : ");
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = 0.1;
		this.add(labelA, c);
		// tạo Textbox và add nó vào Panel
		txtA = new TextField(20);
		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.weightx = 0.1;
		this.add(txtA, c);
		// tạo Label và add nó vào Panel
		labelB = new Label("Nhập b : ");
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = 0.1;
		this.add(labelB, c);
		// tạo Textbox và add nó vào Panel
		txtB = new TextField(20);
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.weightx = 1;
		this.add(txtB, c);
		// tạo Label và add nó vào Panel
		labelC = new Label("Nhập c : ");
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = 0.1;
		this.add(labelC, c);
		// tạo Textbox và add nó vào Panel
		txtC = new TextField(20);
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.weightx = 1;
		this.add(txtC, c);
		// tạo Button và add nó vào Panel
		btnStart = new Button("Bắt ₫ầu giải");
		// ₫ịnh nghĩa hàm xử lý click chuột trên Button
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				giaiPTB2(); // gọi hàm giải ptb2
			}
		});
		c.gridx = 1;
		c.gridy = 3;
		c.gridwidth = 1;
		c.gridheight = 1; // c.weighty = 0.1;
		this.add(btnStart, c);
		// tạo Label kết quả và add nó vào Panel
		lblKetqua = new Label(String.format("%50s", ""));
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.weightx = 0.1;
		this.add(lblKetqua, c);
		// tạo Label x1= và add nó vào Panel
		lblX1 = new Label(String.format("%50s", ""));
		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.weightx = 0.1;
		this.add(lblX1, c);
		// tạo Label x2= và add nó vào Panel
		lblX2 = new Label(String.format("%50s", ""));
		c.gridx = 0;
		c.gridy = 6;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.weightx = 0.1;
		this.add(lblX2, c);
	}
}
