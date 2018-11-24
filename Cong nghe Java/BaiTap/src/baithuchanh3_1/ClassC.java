package baithuchanh3_1;

public class ClassC extends ClassB {
	public ClassC() {
		super();
		d = 1.1416;
		System.out.println("Constructor for ClassC() is running...");
	}

	public ClassC(int i, double d) {
		super(i, d);
		this.d = d;
		System.out.println("Constructor for ClassC(i,d) is running...");
	}

	void finalizer() {
		System.out.println("Destructor for ClassC is running...");
	}

	public int func1() {
		// i = 1; //bi loi
		d = 1;
		System.out.println("Ham ClassC::func1 chay.");
		return 1;
	}

	public void func2() {
		d = 2;
		System.out.println("Ham ClassC::func2 chay.");
	}

	public void func3() {
		d = 2;
		System.out.println("Ham ClassC::func3 chay.");
	}

	protected void func4() {
		System.out.println("Ham ClassC::func4 chay.");
	}
}
