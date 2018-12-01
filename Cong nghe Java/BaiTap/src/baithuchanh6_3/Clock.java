package baithuchanh6_3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
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

public class Clock extends JFrame {
	final int sizeFrame = 700;
	PanelClock panelClock = new PanelClock(sizeFrame, 25);
	PanelClockwise panelClockwise = new PanelClockwise(sizeFrame, 25);

	public Clock() {
		setSize(sizeFrame, sizeFrame);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);
		add(panelClockwise);
		add(panelClock);
		setVisible(true);

	}

	public void startTypeMiliSecond() {
		panelClockwise.startMiliSecond();
	}

	public void startTypeSecond() {
		panelClockwise.startSecond();
	}
}
