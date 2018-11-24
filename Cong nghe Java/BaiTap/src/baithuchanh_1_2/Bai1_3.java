package baithuchanh_1_2;

import java.io.*;
import java.util.*;

public class Bai1_3 {

    static final double EPSILON = 0.0000001; //định nghĩa hằng gợi nhớ miêu tả sai số mong muốn
    static Scanner input; //định nghĩa biến đối tượng phục vụ đ�?c dữ liệu

    //Hàm tính giai thừa
    static long GiaiThua(int n) {
        if (n <= 1) {
            return 1;
        }
        return n * GiaiThua(n - 1);
    }

    //Hàm tính thành phần En của S1
    static double EnS1(double x, int n) {
        return (Math.pow(x, n)) / GiaiThua(n);
    }

    //Hàm tính thành phần En của S2
    static double EnS2(double x, int n) {
        return (Math.pow(-1, n)) * ((Math.pow(x, n)) / GiaiThua(n));
    }

    //Hàm tính thành phần En của S3
    static double EnS3(double x, int n) {
        return Math.sin(n * x);
    }

    //Hàm tính công thức S1
    static double TinhS1(double x) {
        double S = 1, En;
        int n = 1;
        do {
            En = EnS1(x, n);
            S += En;
            n++;
        } while (Math.abs(En) >= EPSILON);
        return S;
    }

    //Hàm tính công thức S2
    static double TinhS2(double x) {
        double S = 1, En;
        int n = 1;
        do {
            En = EnS2(x, n);
            S += En;
            n++;
        } while (Math.abs(En) >= EPSILON);
        return S;
    }

    //Hàm tính công thức S3
    static double TinhS3(double x) {
        double S = 1, En;
        int n = 1;
        do {
            En = EnS3(x, n);
            S += En;
            n++;
        } while (Math.abs(En) >= EPSILON);
        return S;
    }

    public static void main(String[] args) {
        double x;
        //tạo đối tượng nhập dữ liệu thuộc kiểu cơ bản
        input = new Scanner(System.in);
        //yêu cầu ngư�?i dùng nhập x
        System.out.print("Nhập x : ");
        x = input.nextDouble();
        //Xuất kết quả S1(x)
        System.out.println("S1(" + x + ")= " + TinhS1(x));
        //Xuất kết quả S2(x)
        System.out.println("S2(" + x + ")= " + TinhS2(x));
        //Xuất kết quả S3(x)
        System.out.println("S3(" + x + ")= " + TinhS3(x));
    }
}
