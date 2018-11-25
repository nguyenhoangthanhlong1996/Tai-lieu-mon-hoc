package baithuchanh7_2;

public class MyThread extends Thread {
	// ₫ịnh nghĩa các thuộc tính cần sử dụng
	int sr, er, id;
	MyFrame frm;

	// ₫ịnh nghĩa tác vụ khởi tạo nhận tham số
	MyThread(int sr, int er, int id, MyFrame frm) {
		super("Demo Thread");
		this.sr = sr;
		this.er = er;
		this.id = id;
		this.frm = frm;
	}
	
	// ₫ịnh nghĩa tác vụ mà thread sẽ chạy
	public void run() {
		frm.TinhTich(sr, er, id); // tính các hàng ma trận tích từ sr tới er-1
		frm.stateLst[id] = 1; // ghi nhận trạng thái hoàn thành
		frm.isEnd(); // gởi thông báo về thread cha
	}
}
