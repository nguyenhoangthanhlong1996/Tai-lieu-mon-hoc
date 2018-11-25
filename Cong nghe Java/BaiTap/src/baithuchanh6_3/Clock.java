package baithuchanh6_3;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Clock extends JPanel {
	JFrame frame;
	Image imgDongHo, imgKimGio, imgKimPhut, imgKimGiay;
	final int sizeDongHo = 500;
	final int sizeFrame = 700;
	final double scaleKimDongHo = 1.5;
	double angle = 0;
	Timer timer;

	public Clock() {
		try {
			setBackground(Color.RED);
			frame = new JFrame("Clock");
			frame.setSize(sizeFrame, sizeFrame);
			frame.add(this);
			frame.setVisible(true);
			imgDongHo = ImageIO.read(getClass().getClassLoader().getResource("baithuchanh6_3\\dongho.png"));
			imgKimGio = ImageIO.read(getClass().getClassLoader().getResource("baithuchanh6_3\\kimgio.png"));
			imgKimPhut = ImageIO.read(getClass().getClassLoader().getResource("baithuchanh6_3\\kimphut.png"));
			imgKimGiay = ImageIO.read(getClass().getClassLoader().getResource("baithuchanh6_3\\kimgiay.png"));
			repaint();
			timer = new Timer(200, new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					angle++;
					repaint();
				}
			});
			timer.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); 
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		//g2d.drawImage(imgDongHo,getWidth() / 2 - sizeDongHo / 2,getHeight() / 2 - sizeDongHo / 2, sizeDongHo, sizeDongHo,this);
		AffineTransform transform = new AffineTransform();
		transform.rotate(Math.toRadians(angle), getWidth()/2,getHeight()/2);
		g2d.drawImage(imgKimGio, transform, null);
		
	}
}
