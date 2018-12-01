package baithuchanh6_3;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class PanelClock extends JPanel {
	Image imgDongHo;
	final int sizeDongHo = 500;

	public PanelClock(int sizeFrame, int heightTitleBar) {
		int pos = (sizeFrame / 2) - (sizeDongHo / 2);
		setBounds(pos,pos - heightTitleBar,sizeDongHo, sizeDongHo);
		try {
			imgDongHo = ImageIO.read(getClass().getClassLoader().getResource("baithuchanh6_3\\dongho.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(imgDongHo,0,0, sizeDongHo, sizeDongHo, this);
	}
}
