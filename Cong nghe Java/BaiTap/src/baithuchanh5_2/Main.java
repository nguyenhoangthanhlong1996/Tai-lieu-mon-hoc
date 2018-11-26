package baithuchanh5_2;

public class Main {
	public static void main(String[] args) {
		FrGPTB2 frame;
		// Dùng Flow Layout
		//frame = new FlowFrame();
		// Dùng Grid Layout
		//frame = new GridFrame();
		// Dùng Grid Bag Layout
		//frame = new GridBagFrame();
		// Dùng Border Layout
		frame = new BorderFrame();
		frame.init();
		frame.setVisible(true);
	}
}
