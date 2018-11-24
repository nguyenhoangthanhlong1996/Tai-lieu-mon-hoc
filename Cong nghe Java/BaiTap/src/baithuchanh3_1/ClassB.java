package baithuchanh3_1;

public class ClassB extends ClassA {
	public ClassB() {
		super();
		d = 1.1416;
		System.out.println("Constructor for ClassB() is running...");
	}

	public ClassB(int i, double d) {
		super(i, d);
		this.d = d;
		System.out.println("Constructor for ClassB(i,d) is running...");
	}

	void finalizer() {
		System.out.println("Destructor for ClassB is running...");
	}

	public int func1() {
		// i = 1; //bi loi
		d = 1;
		System.out.println("Ham ClassB::func1 chay.");
		return 1;
	}

	public void func2() {
		d = 2;
		System.out.println("Ham ClassB::func2 chay.");
	}
}
