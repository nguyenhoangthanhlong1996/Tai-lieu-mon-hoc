package baithuchanh3_1;

public class Main {

	public static void main(String[] args) {
		// xuất mã nguồn mỗi lệnh trước khi thực thi nó ₫ể ta dễ quan sát kết quả thực
		// thi từng lệnh
		System.out.println("Lenh T_A ca = new ClassA(model); chay. Hay quan sat trinh tu cac constructor.");
		T_A ca = new ClassA();
		System.out.println("Lenh ca.func1(); chay. Hay quan sat ham cu the nao chay");
		ca.func1();
		System.out.println("Lenh ca.func2(); chay. Hay quan sat ham cu the nao chay");
		ca.func2();
		System.out.println("Lenh  ca = new ClassB (model); chay. Hay quan sat trinh tu cac constructor.");
		ca = new ClassB();
		System.out.println("Lenh ca.func1(); chay. Hay quan sat ham cu the nao chay");
		ca.func1();
		System.out.println("Lenh ca.func2(); chay. Hay quan sat ham cu the nao chay");

		ca.func2();
		System.out.println("Lenh ca = new ClassC (model,1,2); chay. Hay quan sat trinh tu cac constructor.");
		ca = new ClassC(1, 2);
		System.out.println("Lenh ca.func1(); chay. Hay quan sat ham cu the nao chay");
		ca.func1();
		System.out.println("Lenh ca.func2(); chay. Hay quan sat ham cu the nao chay");
		ca.func2();
		System.out.println("Lenh ClassA cc = new ClassC(model); chay. Hay quan sat ham cu the nao chay");
		ClassA cc = new ClassC();
		System.out.println("Lenh cc.func3(); chay. Hay quan sat ham cu the nao chay"); 
	}
}
