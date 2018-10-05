import java.util.Scanner;

public class BT6 {
	static void KiemTraChuoiCon(String s1, String s2) {
		if (s2.contains(s1)) {
			System.out.println("Chuỗi S1 là con chuỗi S2");
		} else {
			System.out.println("Chuỗi S1 không là con chuỗi S2");
		}
	}
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String s1 = "abc";
		String s2 = "123 abc";
		KiemTraChuoiCon(s1, s2);
	}
}
