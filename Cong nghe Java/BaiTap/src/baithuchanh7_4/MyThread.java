package baithuchanh7_4;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MyThread extends Thread {
	// â‚«á»‹nh nghÄ©a cÃ¡c háº±ng, thuá»™c tÃ­nh cáº§n dÃ¹ng
	final double PI = 3.1416;
	final int xCell = 30; // â‚«á»™ rá»™ng cá»§a má»—i cell (pixel)
	final int yCell = 30; // â‚«á»™ cao cá»§a má»—i cell (pixel)
	public Boolean fstart; // tráº¡ng thÃ¡i Start cá»§a thread
	public Boolean fstop; // tráº¡ng thÃ¡i Start cá»§a thread
	public Boolean fsuspend; // tráº¡ng thÃ¡i Suspend cá»§a thread
	public Boolean WaitOne = false; // tráº¡ng thÃ¡i chá»? truy xuáº¥t cell
	public BufferedImage Pic; // icon miÃªu táº£ thread
	int xCount; // â‚«á»™ rá»™ng vÃ¹ng cháº¡y cá»§a thread (sá»‘ cell)
	int yCount; // â‚«á»™ cao vÃ¹ng cháº¡y cá»§a thread (sá»‘ cell)
	int tgchay; // thá»?i gian tÃ­nh trÆ°á»›c má»—i bÆ°á»›c cháº¡y cá»§a thread
	public Point Pos = new Point(); // vá»‹ trÃ­ cá»§a thread trong vÃ¹ng cháº¡y
	double dblGocChay; // gÃ³c cháº¡y cá»§a thread
	double tx, ty; // bÆ°á»›c tÄƒng theo x vÃ  y
	MainForm frm; // â‚«á»‘i tÆ°á»£ng quáº£n lÃ½ Form á»©ng dá»¥ng
	// hÃ m khá»Ÿi táº¡o cÃ¡c thÃ´ng sá»‘ cá»§a thread

	public MyThread(Random rnd, int xMax, int yMax, MainForm frm) {
		xCount = xMax;
		yCount = yMax;
		this.frm = frm;
		Pos.x = (int) (rnd.nextInt(xCount));
		Pos.y = (int) (rnd.nextInt(yCount));
		dblGocChay = ChinhGocChay(rnd.nextInt(360));
		tgchay = 1500 + 100 * rnd.nextInt(100);
	}

	// â‚«á»‹nh nghÄ©a hÃ m giáº£ láº­p cÃ´ng viá»‡c tÃ­nh toÃ¡n cá»§a thread
	void MySleep(long count) {
		long i, j, k = 0;
		for (i = 0; i < count; i++)
			for (j = 0; j < 64000; j++)
				k = k + 1;
	}

	// tÃ¡c vá»¥ chá»©c nÄƒng cá»§a thread
	public void run() {
		int x1, y1;
		int x2, y2;
		int x, y;
		boolean kq = true;
		try {
			// xin khóa truy xuất cell xuất phát (do thuộc tính Pos qui ₫ịnh)
			frm.mutList[Pos.y][Pos.x].acquire();
			while (fstart) { // láº·p trong khi chÆ°a cÃ³ yÃªu cáº§u káº¿t thÃºc
				// xÃ¡c â‚«á»‹nh tá»?a â‚«á»™ hiá»‡n hÃ nh cá»§a thread
				x1 = Pos.x;
				y1 = Pos.y;
				// hiá»ƒn thá»‹ icon cá»§a thread á»Ÿ (x1,y1)
				frm.gh.drawImage(Pic, xCell * x1, yCell * y1, null);
				// xÃ¡c â‚«á»‹nh mÃ u váº½ hÆ°á»›ng cháº¡y cá»§a icon
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
				if (tx >= 0 && ty >= 0) { // hiá»‡n mÅ©i tÃªn gÃ³c dÆ°á»›i pháº£i
					x = xCell * x1 + xCell - 2;
					y = yCell * y1 + yCell - 2;
					frm.gh.drawLine(x, y, x - 10, y);
					frm.gh.drawLine(x, y, x, y - 10);
				} else if (tx >= 0 && ty < 0) { // hiá»‡n mÅ©i tÃªn gÃ³c trÃªn pháº£i
					x = xCell * x1 + xCell - 2;
					y = yCell * y1 + 2;
					frm.gh.drawLine(x, y, x - 10, y);
					frm.gh.drawLine(x, y, x, y + 10);
				} else if (tx < 0 && ty >= 0) { // hiá»‡n mÅ©i tÃªn gÃ³c dÆ°á»›i trÃ¡i
					x = xCell * x1 + 2;
					y = yCell * y1 + yCell - 2;
					frm.gh.drawLine(x, y, x + 10, y);
					frm.gh.drawLine(x, y, x, y - 10);
				} else { // hiá»‡n mÅ©i tÃªn gÃ³c trÃªn trÃ¡i
					x = xCell * x1 + 2;
					y = yCell * y1 + 2;
					frm.gh.drawLine(x, y, x + 10, y);
					frm.gh.drawLine(x, y, x, y + 10);
				}
				// giáº£ láº­p thá»±c hiá»‡n cÃ´ng viá»‡c cá»§a thread
				MySleep(tgchay);
				// xÃ¡c â‚«á»‹nh vá»‹ trÃ­ má»›i cá»§a thread
				HieuchinhVitri();
				x2 = Pos.x;
				y2 = Pos.y;
				// thử xin khóa truy xuất cell mới (x2,y2)
				while (true) {
					kq = frm.mutList[y2][x2].tryAcquire(30, TimeUnit.MILLISECONDS);
					if (kq == true || fstart == false)
						break;
				}
				// XÃ³a icon á»Ÿ vá»‹ trÃ­ cÅ©
				frm.gh.drawImage(frm.blackPic, xCell * x1, yCell * y1, null);
				// mở khóa cell cũ (x1,y1) cho các thread khác truy xuất
				frm.mutList[y1][x1].release();
				if (kq == false && fstart == false) { // xÃ³a thread
					this.stop();
					this.fstop = true;
					return;
				}
			}
		} catch (Exception e) {
			this.stop();
		}
		// xÃ³a icon cá»§a thread trÆ°á»›c khi ngá»«ng
		x1 = Pos.x;
		y1 = Pos.y;
		frm.gh.drawImage(frm.blackPic, xCell * x1, yCell * y1, null);
		// mở khóa cell (x1,y1) cho các thread khác truy xuất
		frm.mutList[y1][x1].release();
		// dá»«ng Thread
		fstop = true;
		stop();
	}

	// =================================================
	// Hiá»‡u chá»‰nh gÃ³c cháº¡y cá»§a thread
	// â‚«á»ƒ trÃ¡nh cÃ¡c trÆ°á»?ng há»£p thread cháº¡y tháº³ng â‚«á»©ng hay ngang
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
	// TÃ­nh gÃ³c pháº£n xáº¡ má»›i khi thread â‚«á»¥ng thÃ nh â‚«á»©ng (bÃªn trÃ¡i
	// hay pháº£i).
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
	// TÃ­nh gÃ³c pháº£n xáº¡ má»›i khi thread â‚«á»¥ng thÃ nh ngang (trÃªn hay
	// dÆ°á»›i).
	// =========================================================
	double DoiGocChayY(double dblGocChay) {
		return ChinhGocChay(360 - dblGocChay);
	}

	// =========================================================
	// Hiá»‡u chá»‰nh vá»‹ trÃ­ cá»§a thread
	// =========================================================
	public void HieuchinhVitri() {
		int x, y;
		x = Pos.x;
		y = Pos.y;
		if (x == 0 || x == xCount - 1 || y == 0 || y == yCount - 1) {
			// icon â‚«á»¥ng thÃ nh ngang hay dá»?c -> thay â‚«á»•i gÃ³c cháº¡y
			if (x == 0 || x == xCount - 1)
				dblGocChay = DoiGocChayX(dblGocChay);
			else if (y == 0 || y == yCount - 1)
				dblGocChay = DoiGocChayY(dblGocChay);
		}
		// Hiá»‡u chá»‰nh tá»?a â‚«á»™ x cá»§a thread
		tx = 2 * Math.cos(dblGocChay * PI / 180);
		x = x + (int) tx;
		if (x < 0)
			x = 0;
		else if (x >= xCount)
			x = xCount - 1;
		// Hiá»‡u chá»‰nh tá»?a â‚«á»™ y cá»§a thread
		ty = 2 * Math.sin(dblGocChay * PI / 180);
		y = y + (int) ty;
		if (y < 0)
			y = 0;
		else if (y >= yCount)
			y = yCount - 1;
		// chá»‰nh gÃ³c cháº¡y khi â‚«á»¥ng 1 trong 4 gÃ³c
		if (x == 0 && y == 0) // gÃ³c trÃªn trÃ¡i
			ChinhGocChay(dblGocChay + 45);
		else if (x == 0 && y == yCount - 1) // gÃ³c dÆ°á»›i trÃ¡i
			ChinhGocChay(dblGocChay + 45);
		else if (x == xCount - 1 && y == 0) // gÃ³c trÃªn pháº£i
			ChinhGocChay(dblGocChay + 45);
		else if (x == xCount - 1 && y == yCount - 1) // gÃ³c dÆ°á»›i pháº£i
			ChinhGocChay(dblGocChay + 45);
		// LÆ°u vá»‹ trÃ­ má»›i
		Pos.x = (int) x;
		Pos.y = (int) y;
	}
}
