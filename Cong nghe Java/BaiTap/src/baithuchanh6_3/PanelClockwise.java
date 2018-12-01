package baithuchanh6_3;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.borland.jbcl.model.AbstractGraphModel;

public class PanelClockwise extends JPanel {
	Image imgKimGio, imgKimPhut, imgKimGiay;
	int sizePanel;
	int heightKimGio, widthKimGio;
	int heightKimPhut, widthKimPhut;
	int heightKimGiay, widthKimGiay;
	double angleKimGio = 0, angleKimPhut = 0, angleKimGiay = 0;
	Timer timer;

	public PanelClockwise(int sizeFrame, int heightTitleBar) {
		setOpaque(false);
		sizePanel = sizeFrame / 2;
		int pos = (sizeFrame / 2) - sizePanel / 2;
		setBounds(pos, pos - heightTitleBar, sizePanel, sizePanel);
		try {
			imgKimGio = ImageIO.read(getClass().getClassLoader().getResource("baithuchanh6_3\\kimgio.png"));
			imgKimPhut = ImageIO.read(getClass().getClassLoader().getResource("baithuchanh6_3\\kimphut.png"));
			imgKimGiay = ImageIO.read(getClass().getClassLoader().getResource("baithuchanh6_3\\kimgiay.png"));
			int ratioKimGio = imgKimGio.getHeight(null) / imgKimGio.getWidth(null);
			int ratioKimPhut = imgKimPhut.getHeight(null) / imgKimPhut.getWidth(null);
			int ratioKimGiay = imgKimGiay.getHeight(null) / imgKimGiay.getWidth(null);
			// Thiết lập kim giờ
			heightKimGio = sizePanel - 120;
			widthKimGio = heightKimGio / ratioKimGio;
			imgKimGio = imgKimGio.getScaledInstance(widthKimGio, heightKimGio, Image.SCALE_DEFAULT);
			// Thiết lập kim phút
			heightKimPhut = sizePanel - 20;
			widthKimPhut = heightKimPhut / ratioKimPhut;
			imgKimPhut = imgKimPhut.getScaledInstance(widthKimPhut, heightKimPhut, Image.SCALE_DEFAULT);
			// Thiết lập kim giây
			heightKimGiay = sizePanel;
			widthKimGiay = heightKimGiay / ratioKimGiay;
			imgKimGiay = imgKimGiay.getScaledInstance(widthKimGiay, heightKimGiay, Image.SCALE_DEFAULT);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void startMiliSecond() {
		timer = new javax.swing.Timer(1, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Calendar date = Calendar.getInstance();
				int hour = date.get(Calendar.HOUR_OF_DAY);
				int minute = date.get(Calendar.MINUTE);
				int second = date.get(Calendar.SECOND);
				int millis = date.get(Calendar.MILLISECOND);
				angleKimGiay = (second * 6) + (millis * 0.006);
				angleKimPhut = (minute * 6) + (second * 0.1) + (millis * 0.0001);
				angleKimGio = (hour * 30) + (minute * 0.5) + (second * (0.5 / 60)) + (millis * ((0.5 / 60) / 1000));
				repaint();
			}
		});
		timer.start();
	}

	public void startSecond() {
		timer = new javax.swing.Timer(1, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Calendar date = Calendar.getInstance();
				int hour = date.get(Calendar.HOUR_OF_DAY);
				int minute = date.get(Calendar.MINUTE);
				int second = date.get(Calendar.SECOND);
				angleKimGiay = (second * 6);
				angleKimPhut = (minute * 6) + (second * 0.1);
				angleKimGio = (hour * 30) + (minute * 0.5) + (second * (0.5 / 60));
				repaint();
			}
		});
		timer.start();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		AffineTransform trans;

		// vẽ kim giây
		trans = new AffineTransform();
		trans.translate(sizePanel / 2 - widthKimGiay / 2, sizePanel / 2 - heightKimGiay / 2);
		trans.rotate(Math.toRadians(angleKimGiay), widthKimGiay / 2, heightKimGiay / 2);
		g2d.drawImage(imgKimGiay, trans, this);
		// vẽ kim phút
		trans = new AffineTransform();
		trans.translate(sizePanel / 2 - widthKimPhut / 2, sizePanel / 2 - heightKimPhut / 2);
		trans.rotate(Math.toRadians(angleKimPhut), widthKimPhut / 2, heightKimPhut / 2);
		g2d.drawImage(imgKimPhut, trans, this);
		// vẽ kim giờ
		trans = new AffineTransform();
		trans.translate(sizePanel / 2 - widthKimGio / 2, sizePanel / 2 - heightKimGio / 2);
		trans.rotate(Math.toRadians(angleKimGio), widthKimGio / 2, heightKimGio / 2);
		g2d.drawImage(imgKimGio, trans, this);
	}
}
