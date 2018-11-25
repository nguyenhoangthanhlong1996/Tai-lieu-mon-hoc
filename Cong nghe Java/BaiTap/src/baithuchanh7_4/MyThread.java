package baithuchanh7_4;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MyThread extends Thread {
	// ₫ịnh nghĩa các hằng, thuộc tính cần dùng
	final double PI = 3.1416;
	final int xCell = 30; // ₫ộ rộng của mỗi cell (pixel)
	final int yCell = 30; // ₫ộ cao của mỗi cell (pixel)
	public Boolean fstart; // trạng thái Start của thread
	public Boolean fstop; // trạng thái Start của thread
	public Boolean fsuspend; // trạng thái Suspend của thread
	public Boolean WaitOne = false; // trạng thái chờ truy xuất cell
	public BufferedImage Pic; // icon miêu tả thread
	int xCount; // ₫ộ rộng vùng chạy của thread (số cell)
	int yCount; // ₫ộ cao vùng chạy của thread (số cell)
	int tgchay; // thời gian tính trước mỗi bước chạy của thread
	public Point Pos = new Point(); // vị trí của thread trong vùng chạy
	double dblGocChay; // góc chạy của thread
	double tx, ty; // bước tăng theo x và y
	MainForm frm; // ₫ối tượng quản lý Form ứng dụng
	// hàm khởi tạo các thông số của thread

	public MyThread(Random rnd, int xMax, int yMax, MainForm frm) {
		xCount = xMax;
		yCount = yMax;
		this.frm = frm;
		Pos.x = (int) (rnd.nextInt(xCount));
		Pos.y = (int) (rnd.nextInt(yCount));
		dblGocChay = ChinhGocChay(rnd.nextInt(360));
		tgchay = 1500 + 100 * rnd.nextInt(100);
	}

	// ₫ịnh nghĩa hàm giả lập công việc tính toán của thread
	void MySleep(long count) {
		long i, j, k = 0;
		for (i = 0; i < count; i++)
			for (j = 0; j < 64000; j++)
				k = k + 1;
	}

	// tác vụ chức năng của thread
	public void run() {
		int x1, y1;
		int x2, y2;
		int x, y;
		boolean kq = true;
		try {
			// xin khóa truy xuất cell xuất phát (do thuộc tính Pos qui ₫ịnh)
			frm.mutList[Pos.y][Pos.x].acquire();
			while (fstart) { // lặp trong khi chưa có yêu cầu kết thúc
				// xác ₫ịnh tọa ₫ộ hiện hành của thread
				x1 = Pos.x;
				y1 = Pos.y;
				// hiển thị icon của thread ở (x1,y1)
				frm.gh.drawImage(Pic, xCell * x1, yCell * y1, null);
				// xác ₫ịnh màu vẽ hướng chạy của icon
				Color c = new Color(Pic.getRGB(1, 1), true);
				int yR, yG, yB;
				if (c.getRed() > 128)
					yR = 0;
				else
					yR = 255;
				if (c.getGreen() > 128)
					yG = 0;
				else
					yG = 255;
				if (c.getBlue() > 128)
					yB = 0;
				else
					yB = 255;
				frm.gh.setColor(new Color(yR, yG, yB));
				if (tx >= 0 && ty >= 0) { // hiện mũi tên góc dưới phải
					x = xCell * x1 + xCell - 2;
					y = yCell * y1 + yCell - 2;
					frm.gh.drawLine(x, y, x - 10, y);
					frm.gh.drawLine(x, y, x, y - 10);
				} else if (tx >= 0 && ty < 0) { // hiện mũi tên góc trên phải
					x = xCell * x1 + xCell - 2;
					y = yCell * y1 + 2;
					frm.gh.drawLine(x, y, x - 10, y);
					frm.gh.drawLine(x, y, x, y + 10);
				} else if (tx < 0 && ty >= 0) { // hiện mũi tên góc dưới trái
					x = xCell * x1 + 2;
					y = yCell * y1 + yCell - 2;
					frm.gh.drawLine(x, y, x + 10, y);
					frm.gh.drawLine(x, y, x, y - 10);
				} else { // hiện mũi tên góc trên trái
					x = xCell * x1 + 2;
					y = yCell * y1 + 2;
					frm.gh.drawLine(x, y, x + 10, y);
					frm.gh.drawLine(x, y, x, y + 10);
				}
				// giả lập thực hiện công việc của thread
				MySleep(tgchay);
				// xác ₫ịnh vị trí mới của thread
				HieuchinhVitri();
				x2 = Pos.x;
				y2 = Pos.y;
				// thử xin khóa truy xuất cell mới (x2,y2)
				while (true) {
					kq = frm.mutList[y2][x2].tryAcquire(30, TimeUnit.MILLISECONDS);
					if (kq == true || fstart == false)
						break;
				}
				// Xóa icon ở vị trí cũ
				frm.gh.drawImage(frm.blackPic, xCell * x1, yCell * y1, null);
				// mở khóa cell cũ (x1,y1) cho các thread khác truy xuất
				frm.mutList[y1][x1].release();
				if (kq == false && fstart == false) { // xóa thread
					this.stop();
					this.fstop = true;
					return;
				}
			}
		} catch (Exception e) {
			this.stop();
		}
		// xóa icon của thread trước khi ngừng
		x1 = Pos.x;
		y1 = Pos.y;
		frm.gh.drawImage(frm.blackPic, xCell * x1, yCell * y1, null);
		// mở khóa cell (x1,y1) cho các thread khác truy xuất
		frm.mutList[y1][x1].release();
		// dừng Thread
		fstop = true;
		stop();
	}

	// =================================================
	// Hiệu chỉnh góc chạy của thread
	// ₫ể tránh các trường hợp thread chạy thẳng ₫ứng hay ngang
	// =================================================
	double ChinhGocChay(double dblGocChay) {
		double goc = dblGocChay;
		if (0 <= goc && goc < 90)
			return 45;
		if (90 <= goc && goc < 180)
			return 135;
		if (180 <= goc && goc < 270)
			return 225;
		if (270 <= goc)
			return 315;
		return goc;
	}

	// =========================================================
	// Tính góc phản xạ mới khi thread ₫ụng thành ₫ứng (bên trái hay phải).
	// =========================================================
	double DoiGocChayX(double dblGocChay) {
		double goc;
		if (dblGocChay > 0 && dblGocChay < 180)
			goc = 180 - dblGocChay;
		else
			goc = 180 + 360 - dblGocChay;
		return ChinhGocChay(goc);
	}

	// =========================================================
	// Tính góc phản xạ mới khi thread ₫ụng thành ngang (trên hay dưới).
	// =========================================================
	double DoiGocChayY(double dblGocChay) {
		return ChinhGocChay(360 - dblGocChay);
	}

	// =========================================================
	// Hiệu chỉnh vị trí của thread
	// =========================================================
	public void HieuchinhVitri() {
		int x, y;
		x = Pos.x;
		y = Pos.y;
		if (x == 0 || x == xCount - 1 || y == 0 || y == yCount - 1) {
			// icon ₫ụng thành ngang hay dọc -> thay ₫ổi góc chạy
			if (x == 0 || x == xCount - 1)
				dblGocChay = DoiGocChayX(dblGocChay);
			else if (y == 0 || y == yCount - 1)
				dblGocChay = DoiGocChayY(dblGocChay);
		}
		// Hiệu chỉnh tọa ₫ộ x của thread
		tx = 2 * Math.cos(dblGocChay * PI / 180);
		x = x + (int) tx;
		if (x < 0)
			x = 0;
		else if (x >= xCount)
			x = xCount - 1;
		// Hiệu chỉnh tọa ₫ộ y của thread
		ty = 2 * Math.sin(dblGocChay * PI / 180);
		y = y + (int) ty;
		if (y < 0)
			y = 0;
		else if (y >= yCount)
			y = yCount - 1;
		// chỉnh góc chạy khi ₫ụng 1 trong 4 góc
		if (x == 0 && y == 0) // góc trên trái
			ChinhGocChay(dblGocChay + 45);
		else if (x == 0 && y == yCount - 1) // góc dưới trái
			ChinhGocChay(dblGocChay + 45);
		else if (x == xCount - 1 && y == 0) // góc trên phải
			ChinhGocChay(dblGocChay + 45);
		else if (x == xCount - 1 && y == yCount - 1) // góc dưới phải
			ChinhGocChay(dblGocChay + 45);
		// Lưu vị trí mới
		Pos.x = (int) x;
		Pos.y = (int) y;
	}
}
