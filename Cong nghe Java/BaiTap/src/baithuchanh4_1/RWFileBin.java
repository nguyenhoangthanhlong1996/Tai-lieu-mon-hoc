package baithuchanh4_1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class RWFileBin { // ₫ịnh nghĩa ₫iểm nhập chương trình
	public static void main(String[] args) throws IOException { // ₫ịnh nghĩa các biến ₫ối tượng ₫ọc/ghi luồng byte
		FileInputStream in = null;
		FileOutputStream out = null;
		try {
			// tạo các ₫ối tượng ₫ọc/ghi luồng byte
			in = new FileInputStream("src\\baithuchanh4_1\\in.bin");
			out = new FileOutputStream("src\\baithuchanh4_1\\out.bin");
			int c;
			// lặp ₫ọc/ghi từng byte
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
