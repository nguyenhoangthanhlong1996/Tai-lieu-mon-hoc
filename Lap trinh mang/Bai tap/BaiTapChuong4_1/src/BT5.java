import java.util.Scanner;

public class BT5 {
    static String TachTen(String hovaten) {
        hovaten = hovaten.trim();
        int khoangtrangcuoicung = hovaten.lastIndexOf(" ");
        String ten = hovaten.substring(khoangtrangcuoicung + 1);
        return ten;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập họ và tên: ");
        String hovaten = scanner.nextLine();
        System.out.println("Tên người này là: " + TachTen(hovaten));
    }
}
