package baithuchanh11_1;

import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {
	public static void main(String[] args) {
		// tạo frame giao diện
		Frame fr = new Frame();
		fr.setSize(350, 180);
		fr.setLayout(new GridLayout(1, 1));
		// tạo applet và add nó vào frame
		GPTB2Applet apl = new GPTB2Applet();
		apl.init();
		fr.add(apl);
		// ₫ịnh nghĩa hàm xử lý sự kiện ₫óng Form
		fr.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		fr.setVisible(true);
	}
}
