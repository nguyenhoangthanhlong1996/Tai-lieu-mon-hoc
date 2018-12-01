package baithuchanh7_4;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import java.util.Random;
import java.util.concurrent.Semaphore;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class MainForm extends JFrame {
	// â‚«á»‹nh nghÄ©a cÃ¡c thuá»™c tÃ­nh cáº§n dÃ¹ng
	public Semaphore[][] mutList;
	public Image blackPic;
	MyThread[] threadLst;
	final int xCell = 30;
	final int yCell = 30;
	final int xCount = 25;
	final int yCount = 20;
	Graphics gh;
	// táº¡o â‚«á»‘i tÆ°á»£ng sinh sá»‘ ngáº«u nhiÃªn
	public Random rnd = new Random();

	// tÃ¡c vá»¥ khá»Ÿi táº¡o form
	public MainForm() {
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		// thiáº¿t láº­p tÃ¡c vá»¥ xá»­ lÃ½ sá»± kiá»‡n áº¥n phÃ­m trÃªn form
		addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(java.awt.event.KeyEvent evt) {
				formKeyPressed(evt);
			}
		});
		// thiáº¿t láº­p láº¡i kÃ­ch thÆ°á»›c form theo yÃªu cáº§u
		this.setSize(xCount * 30, yCount * 30);
		this.setLocation(0, 0);
		// thiáº¿t láº­p mÃ u ná»?n â‚«en cho form
		this.getContentPane().setBackground(Color.BLACK);
		// â‚«á»?c bitmap miÃªu táº£ cell ná»?n tá»« file
		URL url;
		try {
			url = this.getClass().getClassLoader().getResource("images/Black.bmp");
			blackPic = ImageIO.read(url);
		} catch (Exception e) {
		}
		// táº¡o danh sÃ¡ch chá»©a 26 thread tá»« A-Z
		threadLst = new MyThread[26];
		int i;
		// tạo ma trận semaphore nhị phân ₫ể bảo vệ các cell màn hình
		mutList = new Semaphore[yCount][xCount];
		int h, cot = 0;
		for (h = 0; h < yCount; h++)
			for (cot = 0; cot < xCount; cot++)
				mutList[h][cot] = new Semaphore(1);
		// Láº·p thiáº¿t láº­p tráº¡ng thÃ¡i ban â‚«áº§u cho 26 thread tá»« A-Z
		for (i = 0; i < 26; i++) {
			threadLst[i] = new MyThread(rnd, xCount, yCount, this);
			threadLst[i].fstop = threadLst[i].fsuspend = threadLst[i].fstart = false;
			char c = (char) (i + 65);
			try { // â‚«á»?c bitmap miÃªu táº£ thread c tá»« file
				url = this.getClass().getClassLoader().getResource("images/Image" + c + ".bmp");
				threadLst[i].Pic = ImageIO.read(url);
			} catch (Exception e) {
				System.out.println(e.toString());
			}
		}
	}

	// tÃ¡c vá»¥ xá»­ lÃ½ viá»‡c áº¥n phÃ­m
	private void formKeyPressed(java.awt.event.KeyEvent evt) {
		// lÆ°u â‚«á»‘i tÆ°á»£ng Graphics cá»§a form â‚«á»ƒ váº½ icon khi cáº§n
		gh = this.getGraphics();
		// xÃ¡c â‚«á»‹nh mÃ£ phÃ­m áº¥n, náº¿u khÃ´ng pháº£i tá»« A-Z thÃ¬ phá»›t lá»?
		int newch = evt.getKeyCode();
		if (newch < 0x41 || newch > 0x5a)
			return;
		// xÃ¡c â‚«á»‹nh chá»©c nÄƒng mÃ  user muá»‘n vÃ  thá»±c hiá»‡n
		if (evt.isControlDown() && evt.isShiftDown()) { // dá»«ng Thread
			threadLst[newch - 65].fstart = false;
		} else if (evt.isControlDown() && evt.isAltDown()) { // táº¡m dá»«ng thread
			if (threadLst[newch - 65].fstart && !threadLst[newch - 65].fsuspend) {
				threadLst[newch - 65].suspend();
				threadLst[newch - 65].fsuspend = true;
			}
		} else if (evt.isControlDown()) { // giáº£m â‚«á»™ Æ°u tiÃªn tá»‘i thiá»ƒu
			threadLst[newch - 65].setPriority(Thread.MIN_PRIORITY);
		} else if (evt.isAltDown()) { // cho thread cháº¡y láº¡i
			if (threadLst[newch - 65].fstart && threadLst[newch - 65].fsuspend) {
				threadLst[newch - 65].resume();
				threadLst[newch - 65].fsuspend = false;
			}
		} else if (evt.isShiftDown()) { // tÄƒng â‚«á»™ Æ°u tiÃªn tá»‘i â‚«a
			threadLst[newch - 65].setPriority(Thread.MAX_PRIORITY);
		} else { // táº¡o má»›i thread vÃ  báº¯t â‚«áº§u cháº¡y
			if (!threadLst[newch - 65].fstart) {
				threadLst[newch - 65].fstart = true;
				threadLst[newch - 65].fsuspend = false;
				threadLst[newch - 65].start();
			}
		}
	}
}
