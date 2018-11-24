package baithuchanh4_6;

import java.io.Serializable;

public class B implements Serializable {
	int intB1;
	private double dblB2;
	private A ba;
	private A pba;
	private A pba1;

	public B() {
	}

	public void init(int b1, double b2) {
		this.intB1 = b1;
		this.dblB2 = b2;
		ba = new A();
		pba = new A();
		pba1 = new A();
	}

	public void Setba(int a1, double a2, B b) {
		this.ba.init(a1, a2, b);
	}

	public void Setpba(int a1, double a2, B b) {
		this.pba.init(a1, a2, b);
	}

	public void Setpba1(int a1, double a2, B b) {
		this.pba1.init(a1, a2, b);
	}
}
