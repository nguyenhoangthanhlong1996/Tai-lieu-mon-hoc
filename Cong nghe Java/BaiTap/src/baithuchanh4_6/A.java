package baithuchanh4_6;

import java.io.Serializable;

public class A implements Serializable {
	private int intA1;
	private double dblA2;
	private B pab;

	public A() {
	}

	public void init(int a1, double a2, B p) {
		this.intA1 = a1;
		this.dblA2 = a2;
		this.pab = p;
	}
}
