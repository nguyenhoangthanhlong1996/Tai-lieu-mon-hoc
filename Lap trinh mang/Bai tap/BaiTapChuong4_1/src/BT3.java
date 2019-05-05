import java.util.Scanner;

public class BT3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Nhập số thứ 1: ");
            int a = Integer.parseInt(scanner.next());
            System.out.print("Nhập số thứ 2: ");
            int b = Integer.parseInt(scanner.next());
            System.out.println("Tổng 2 số là: " + (a + b));
        } catch (NumberFormatException e) {
            System.out.println("Số không hợp lệ");
        }
    }
}
