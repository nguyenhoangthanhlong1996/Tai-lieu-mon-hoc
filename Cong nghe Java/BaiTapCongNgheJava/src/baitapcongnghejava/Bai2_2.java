package baitapcongnghejava;

import java.io.*;
import java.util.*;

public class Bai2_2 {

    static String strInput = ""; //Lưu chuỗi do người dùng nhập
    static Scanner scanner = new Scanner(System.in); //Đối tượng xử lý input
    static int strLength = 0; //Lưu độ dài chuỗi do người dùng nhập
    static boolean charUsed[]; //Kiểm soát trạng thái các ký tự trong chuỗi của người dùng nhập đã sử dụng hay chưa
    static char words[]; //Lưu các ký tự (hình thành 1 cách sắp từ)
    static int count = 1; //đếm số cách tạo từ

    //Hàm cho phép người dùng nhập
    static void NguoiDungNhap() {
        try {
            System.out.print("Hay nhap 1 chuoi: ");
            strInput = scanner.next();
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    //Hàm khởi tạo các biến
    static void KhoiTao() {
        strLength = strInput.length();
        charUsed = new boolean[strLength];
        words = new char[strLength];
        for (int i = 0; i < strLength; i++) {
            charUsed[i] = false;
        }
    }

    //Hàm tìm cách sắp thành 1 từ
    static void SapTu(int k) {
        int i;
        for (i = 0; i < strLength; i++) {
            //kiểm tra kí tự thứ i đã được dùng hay chưa
            if (charUsed[i] == false) { //nếu chưa
                words[k] = strInput.charAt(i); //gán vào mảng tạo từ
                charUsed[i] = true; //đánh dấu kí tự thứ i đã được dùng
                //kiểm tra xem đã xét đến kí tự cuối cùng chưa
                if (k == strLength - 1) {
                    InTu();
                } else {
                    SapTu(k + 1); //thực hiện sắp kí tự kế tiếp
                }
                charUsed[i] = false;
            }
        }
    }

    //Hàm in ra cách sắp xếp của 1 từ
    static void InTu() {
        System.out.print(String.format("Từ thứ %1$d: ", count++));
        for (int i = 0; i < strLength - 1; i++) {
            System.out.print(words[i] + " - ");
        }
        System.out.print(words[strLength - 1]);
        System.out.println("");
    }

    public static void main(String[] args) {
        NguoiDungNhap();
        KhoiTao();
        SapTu(0);
    }
}
