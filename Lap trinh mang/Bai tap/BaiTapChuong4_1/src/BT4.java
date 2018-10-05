import java.util.Scanner;

public class BT4 {
	public static void main(String[] args) {
		String[] mang_thang = new String[] { "0", "31", "28 hoặc 29", "31", "30", "31", "30", "31", "31", "30", "31",
				"30", "31" };
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.print("Nhập tháng: ");
			try {
				int thang = Integer.parseInt(scanner.next());
				if (thang < 1 || thang > 12) {
					System.out.println("Không có tháng này");
				} else {
					System.out.println("Tháng " + thang + " có " + mang_thang[thang] + " ngày");
					break;
				}
			} catch (NumberFormatException e) {
				System.out.println("Phải nhập bằng số");
			}
		}
	}
}
