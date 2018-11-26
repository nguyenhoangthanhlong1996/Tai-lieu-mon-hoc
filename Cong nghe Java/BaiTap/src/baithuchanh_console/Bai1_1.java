package baithuchanh_console;

import java.io.*;
import java.util.*;

public class Bai1_1 {

    //định nghĩa các biến cần dùng
    static double a, b, c; /* static doubles a, b, c; */
    static double delta;
    static double x1, x2;
    static Scanner input;
    static BufferedReader reader;
    static String buf;

    //định nghĩa hàm nhập 3 thông số a,b,c của phương trình bậc 2
    static void NhapABC() {
        try {
            //tạo đối tượng nhập dữ liệu thuộc kiểu cơ bản
            input = new Scanner(System.in);
            //ch�? ngư�?i dùng nhập a
            System.out.print("Nhập a : "); /* System.out.print("Nhập a : ); */
            a = input.nextDouble(); /* a := input.nextDouble(); */
            //ch�? ngư�?i dùng nhập b
            System.out.print("Nhập b : ");
            b = input.nextDouble();
            //ch�? ngư�?i dùng nhập c
            System.out.print("Nhập c : ");
            c = input.nextDouble();
        } catch (Exception e) {} /* catch(Exception e){...} */
    }

    //định nghĩa hàm tính nghiệm của phương trình bậc 2
    static void GiaiPT() { /* static void GiaiPT) { */
        //tính biệt số delta của phương trình
        delta = b * b - 4 * a * c;
        if (delta >= 0) { //nếu có nghiệm thực /* nếu (delta >= 0) */
            x1 = (-b + Math.sqrt(delta)) / (2 * a); /* x1 = (-b + Math.sqrt(delta)) / 2 / a; */
            x2 = (-b - Math.sqrt(delta)) / (2 * a); /* x1 = (-b - Math.sqrt(delta)) / 2 / a; */
        }
    }

    //định nghĩa hàm xuất kết quả
    static void XuatKetqua() {
        if (delta < 0) //báo vô nghiệm /* nếu (delta < 0) */
        {
            System.out.println("Phương trình vô nghiệm");
        } else { //báo có 2 nghiệm /* thì */
            System.out.println("Phương trình có 2 nghiệm thực : ");
            System.out.println("X1 = " + x1);
            System.out.println("X2 = " + x2);
        }
    }

    public static void main(String[] args) {
        NhapABC(); //1. nhập a,b,c
        GiaiPT(); //2. giải phương trình
        XuatKetqua(); //3. xuất kết quả
    }
}
