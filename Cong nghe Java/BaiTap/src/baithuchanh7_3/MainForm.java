package baithuchanh7_3;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class MainForm extends JFrame {
	// ₫ịnh nghĩa các thuộc tính cần dùng
	public Image blackPic;
	MyThread[] threadLst;
	final int xCell = 30;
	final int yCell = 30;
	final int xCount = 25;
	final int yCount = 20;
	Graphics gh;
	// tạo ₫ối tượng sinh số ngẫu nhiên
	public Random rnd = new Random();

	// tác vụ khởi tạo form
	public MainForm() {
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		// thiết lập tác vụ xử lý sự kiện ấn phím trên form
		addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(java.awt.event.KeyEvent evt) {
				formKeyPressed(evt);
			}
		});
		// thiết lập lại kích thước form theo yêu cầu
		this.setSize(xCount * 30, yCount * 30);
		this.setLocation(0, 0);
		// thiết lập màu nền ₫en cho form
		this.getContentPane().setBackground(Color.BLACK);
		// ₫ọc bitmap miêu tả cell nền từ file
		URL url;
		try {
			url = this.getClass().getClassLoader().getResource("images/Black.bmp");
			blackPic = ImageIO.read(url);
		} catch (Exception e) {
		}
		// tạo danh sách chứa 26 thread từ A-Z
		threadLst = new MyThread[26];
		int i;
		// Lặp thiết lập trạng thái ban ₫ầu cho 26 thread từ A-Z
		for (i = 0; i < 26; i++) {
			threadLst[i] = new MyThread(rnd, xCount, yCount, this);
			threadLst[i].fstop = threadLst[i].fsuspend = threadLst[i].fstart = false;
			char c = (char) (i + 65);
			try { // ₫ọc bitmap miêu tả thread c từ file
				url = this.getClass().getClassLoader().getResource("images/Image" + c + ".bmp");
				threadLst[i].Pic = ImageIO.read(url);
			} catch (Exception e) {
				System.out.println(e.toString());
			}
		}
	}

	// tác vụ xử lý việc ấn phím
	private void formKeyPressed(java.awt.event.KeyEvent evt) {
		// lưu ₫ối tượng Graphics của form ₫ể vẽ icon khi cần
		gh = this.getGraphics();
		// xác ₫ịnh mã phím ấn, nếu không phải từ A-Z thì phớt lờ
		int newch = evt.getKeyCode();
		if (newch < 0x41 || newch > 0x5a)
			return;
		// xác ₫ịnh chức năng mà user muốn và thực hiện
		if (evt.isControlDown() && evt.isShiftDown()) { // dừng Thread
			threadLst[newch - 65].fstart = false;
		} else if (evt.isControlDown() && evt.isAltDown()) { // tạm dừng thread
			if (threadLst[newch - 65].fstart && !threadLst[newch - 65].fsuspend) {
				threadLst[newch - 65].suspend();
				threadLst[newch - 65].fsuspend = true;
			}
		} else if (evt.isControlDown()) { // giảm ₫ộ ưu tiên tối thiểu
			threadLst[newch - 65].setPriority(Thread.MIN_PRIORITY);
		} else if (evt.isAltDown()) { // cho thread chạy lại
			if (threadLst[newch - 65].fstart && threadLst[newch - 65].fsuspend) {
				threadLst[newch - 65].resume();
				threadLst[newch - 65].fsuspend = false;
			}
		} else if (evt.isShiftDown()) { // tăng ₫ộ ưu tiên tối ₫a
			threadLst[newch - 65].setPriority(Thread.MAX_PRIORITY);
		} else { // tạo mới thread và bắt ₫ầu chạy
			if (!threadLst[newch - 65].fstart) {
				threadLst[newch - 65].fstart = true;
				threadLst[newch - 65].fsuspend = false;
				threadLst[newch - 65].start();
			}
		}
	}
}
