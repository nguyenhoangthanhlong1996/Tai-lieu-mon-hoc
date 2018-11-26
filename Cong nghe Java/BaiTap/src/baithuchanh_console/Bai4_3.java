package baithuchanh_console;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Bai4_3 {
	public static void main(String[] args) throws IOException {
		// ₫ịnh nghĩa các biến ₫ối tượng ₫ọc/ghi luồng ký tự
		FileReader in = null;
		FileWriter out = null;
		// tiếp tục ở slide kế
		try {
			// tạo các ₫ối tượng ₫ọc/ghi luồng ký tự
			in = new FileReader("src\\baithuchanh_console\\in.txt");
			out = new FileWriter("src\\baithuchanh_console\\out.txt");
			int c;
			// lặp ₫ọc/ghi từng ký tự
			while ((c = in.read()) != -1)
				out.write(c);
			// nếu có file nhập và chưa ₫óng thì ₫óng file lại
			if (in != null)
				in.close();
			// nếu có file xuất và chưa ₫óng thì ₫óng file lại
			if (out != null)
				out.close();
		} catch (Exception e) {
		}
	}
}
