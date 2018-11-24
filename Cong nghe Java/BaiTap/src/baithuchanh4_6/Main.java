package baithuchanh4_6;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Main {
	public static void Create_SaveObject() {
		// khởi tạo ₫ối tượng b theo lược ₫ồ trên
		B b = new B();
		b.init(2, 2.345);
		b.Setba(1, 1.234, b);
		b.Setpba(3, 3.1416, b);
		b.Setpba1(4, 4.567, b);
		// ghi ₫ối tượng b
		try {
			FileOutputStream file = new FileOutputStream("d:\\persist.bin");
			ObjectOutputStream out = new ObjectOutputStream(file);
			out.writeObject(b);
			out.flush();
			out.close();
			file.close();
		} catch (java.io.IOException IOE) {
			System.out.print("IOException");
		}
	}

	public static void ReadObject() {
		try {
			FileInputStream file = new FileInputStream("d:\\persist.bin");
			ObjectInputStream input = new ObjectInputStream(file);
			B b = (B) input.readObject();
			input.close();
			file.close();
		} catch (java.io.IOException IOE) {
			System.out.print("IOException");
		} catch (ClassNotFoundException cnfe) {
			System.out.print("ClassNotFoundException");
		}
	}
	
	public static void main(String[] args) {
		Create_SaveObject();
		ReadObject();
	}
}