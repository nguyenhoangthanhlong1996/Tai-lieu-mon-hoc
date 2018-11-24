package baithuchanh3_1;

public class ClassA implements T_A{
	private int i;
	protected double d;

	public ClassA() {
		super();
		i = 1;
		d = 1.1416;
		System.out.println("Constructor for ClassA() is running...");
	}

	public ClassA(int i, double d) {
		super();
		this.i = i;
		this.d = d;
		System.out.println("Constructor for ClassA(i,d) is running...");
	}

	void finalizer() {
		System.out.println("Destructor for ClassA is running...");
	}

	public int func1() {
		i = 1;
		d = 1;
		System.out.println("Ham ClassA::func1 chay.");
		return 1;
	}

	public void func2() {
		i = 2;
		d = 2;
		System.out.println("Ham ClassA::func2 chay.");
	}

	private void func3() {
		System.out.println("Ham ClassA::func3 chay.");
	}

	protected void func4() {
		System.out.println("Ham ClassA::func4 chay.");
	}

	public void func5() {
		System.out.println("Ham ClassA::func5 chay.");
		func3(); // lien ket tinh --> khong da xa
		func4(); // lien ket dong --> da xa
	}
}
