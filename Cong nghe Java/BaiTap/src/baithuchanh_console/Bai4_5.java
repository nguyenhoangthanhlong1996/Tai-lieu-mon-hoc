package baithuchanh_console;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Bai4_5 {
	public static void main(String[] args) {
		// tạo dữ liệu và ghi lên file dạng nhị phân
		//WriteData("d:\\data.bin");
		// ₫ọc dữ liệu từ file dạng nhị phân vào biến chương trình
		 ReadData("d:\\data.bin");
	}

	// hàm tạo dữ liệu và ghi lên file dạng nhị phân
	public static void WriteData(String path) {
		try {
			// 1. tạo ₫ối tượng quản lý file xuất
			FileOutputStream fout = new FileOutputStream(path);
			// 2. tạo ₫ối tượng quản lý file xuất có ₫ệm (nếu cần hiệu quả)
			BufferedOutputStream bouts = new BufferedOutputStream(fout);
			// 3. tạo ₫ối tượng ghi file text
			PrintWriter out = new PrintWriter(bouts);
			// 4. xử lý dữ liệu theo yêu cầu chương trình
			int i = -15;
			double d = -1.5;
			String s = "Nguyễn Văn Hiệp";
			Boolean b = true;
			// 5. ghi dữ liệu ra file
			out.println(s); // xuất chuỗi rồi xuống hàng
			out.print(b);
			out.print(", "); // xuất trị luận lý và dấu ngăn
			out.print(i);
			out.print(", "); // xuất số nguyên và dấu ngăn
			out.print(d);
			out.println(", "); // xuất số thực và dấu ngăn rồi xuống hàng
			// 6. ₫óng các ₫ối tượng ₫ược dùng lại
			out.close();
			bouts.close();
			fout.close();
		} catch (Exception e) {
		}
	}// hết hàm WriteData
		// hàm ₫ọc dữ liệu từ file dạng nhị phân vào biến chương trình

	public static void ReadData(String path) {
		try {
			// 1. tạo ₫ối tượng quản lý file nhập
			FileInputStream fin = new FileInputStream(path);
			// 2. tạo ₫ối tượng quản lý file nhập có ₫ệm (nếu cần hiệu quả)
			BufferedInputStream bins = new BufferedInputStream(fin);
			// 3. tạo ₫ối tượng ₫ọc file nhị phân
			Scanner in = new Scanner(bins);
			// khai báo dấu ngăn các thành phần text trong file
			in.useDelimiter(",\\s*");
			// 4. ₫ịnh nghĩa các biến theo yêu cầu chương trình
			int i;
			double d;
			String s;
			Boolean b;
			// 5. ₫ọc dữ liệu từ file vào các biến
			s = in.nextLine(); // ₫ọc chuỗi UTF-8
			b = in.nextBoolean(); // ₫ọc trị luận lý
			i = in.nextInt(); // ₫ọc trị nguyên 32 bit
			d = in.nextDouble(); // ₫ọc trị thực 64 bit
			// 6. ₫óng các ₫ối tượng ₫ược dùng lại
			in.close(); // thiết lập ₫iểm dừng ở ₫ây ₫ể kiểm tra kết quả vừa ₫ọc vào
			bins.close();
			fin.close();
		} catch (Exception e) {
		}
	} // hết hàm ReadData
}
