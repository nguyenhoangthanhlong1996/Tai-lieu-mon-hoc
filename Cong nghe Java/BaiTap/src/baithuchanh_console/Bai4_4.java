package baithuchanh_console;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Bai4_4 {
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
			// 3. tạo ₫ối tượng ghi file nhị phân
			DataOutputStream out = new DataOutputStream(bouts);
			// 4. xử lý dữ liệu theo yêu cầu chương trình
			int i = -15;
			double d = -1.5;
			String s = "Nguyễn Văn Hiệp";
			Boolean b = true;
			// 5. ghi dữ liệu ra file
			out.writeUTF(s); // ghi chuỗi theo cách mã hóa UTF-8
			out.writeBoolean(b); // ghi trị luận lý
			out.writeInt(i); // ghi trị nguyên 32 bit
			out.writeDouble(d); // ghi trị thực 64 bit
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
			DataInputStream in = new DataInputStream(bins);
			// 4. ₫ịnh nghĩa các biến theo yêu cầu chương trình
			int i;
			double d;
			String s;
			Boolean b;
			// 5. ₫ọc dữ liệu từ file vào các biến
			s = in.readUTF(); // ₫ọc chuỗi UTF-8
			b = in.readBoolean(); // ₫ọc trị luận lý
			i = in.readInt(); // ₫ọc trị nguyên 32 bit
			d = in.readDouble(); // ₫ọc trị thực 64 bit
			// 6. ₫óng các ₫ối tượng ₫ược dùng lại
			in.close(); // thiết lập ₫iểm dừng ở ₫ây ₫ể kiểm tra kết quả vừa ₫ọc vào
			bins.close();
			fin.close();
		} catch (Exception e) {
		}
	} // hết hàm ReadData
}
