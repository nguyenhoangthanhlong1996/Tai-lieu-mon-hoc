package baithuchanh5_2;

public class Main {
	public static void main(String[] args) {
		FrGPTB2 frame;
		// D�ng Flow Layout
		//frame = new FlowFrame();
		// D�ng Grid Layout
		//frame = new GridFrame();
		// D�ng Grid Bag Layout
		//frame = new GridBagFrame();
		// D�ng Border Layout
		frame = new BorderFrame();
		frame.init();
		frame.setVisible(true);
	}
}
