package baitapcongnghejava;

import java.io.*;
import java.util.*;

public class Bai2_1 {

    static int demcach = 1; //đếm số cách giải
    static int n = 8; //bàn cờ n*n
    static int quanhau[] = new int[n]; //lưu một cách sắp xếp
    static boolean cot[] = new boolean[n]; //lưu trạng thái của các cột
    static boolean cheophu[] = new boolean[2 * n - 1]; //lưu trạng thái của các đường chéo phụ
    static boolean cheochinh[] = new boolean[2 * n - 1]; //lưu trạng thái của các đường chéo chính

    //Khởi tạo các mảng lưu trạng thái ban đầu
    static void KhoiTao() {
        //ban đầu tất cả các cột đều còn trống (tức là chưa có con hậu nào được đặt ở bất kỳ cột nào)
        for (int i = 0; i < n; i++) {
            cot[i] = true;
        }
        //ban đầu tất cả các đường chéo chính, chéo phụ đều còn trống)
        for (int i = 0; i < 2 * n - 1; i++) {
            cheophu[i] = true;
            cheochinh[i] = true;
        }
    }

    //Hàm in ra 1 cách sắp xếp
    static void InCachSapXepQuanHau() {
        System.out.println("Cách số " + demcach++ + " :");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print("+---");
            }
            System.out.print("+\n");
            for (int j = 0; j < n; j++) {
                if (quanhau[i] == j) {
                    System.out.print("|\u2006\u2006\u2655\u2006\u2006");
                } else {
                    System.out.print("|   ");
                }
            }
            System.out.print("|\n");
        }
        for (int j = 0; j < n; j++) {
            System.out.print("+---");
        }
        System.out.println("+");
    }

    //Hàm xếp 1 quân hậu
    static void XepQuanHau(int d) { //truyền vào tham số là vị trí dòng
        for (int c = 0; c < n; c++) {
            if (cot[c] == true && cheophu[d + c] == true && cheochinh[d - c + n - 1] == true) {
                quanhau[d] = c; //quân hậu ở hàng thứ d sẽ được xếp ở cột thứ c
                cot[c] = false; //báo hiệu cột này đã có 1 quân hậu được xếp
                cheophu[d + c] = false; //báo hiệu đường chéo phụ này đã có 1 quân hậu được xếp
                cheochinh[d - c + n - 1] = false; //báo hiệu đường chéo chính này đã có 1 quân hậu được xếp
                if (d == n - 1) { //kiểm tra xem đã sắp xong con hậu cuối cùng hay chưa
                    InCachSapXepQuanHau();
                } else {
                    XepQuanHau(d + 1); //thực hiện sắp con hậu ở dòng kế tiếp
                }
                //đưa các trạng thái về ban đầu để xét cho các cách khác
                cot[c] = true;
                cheophu[d + c] = true;
                cheochinh[d - c + n - 1] = true;
            }
        }
    }

    public static void main(String[] args) {
        KhoiTao();
        XepQuanHau(0);
    }
}
