package baithuchanh_console;

import java.io.*;
import java.util.*;

public class Bai2_1 {

    static int n = 8; //ban co n x n
    static boolean cot[] = new boolean[n]; //luu trang thai cua tat ca cac cot
    static boolean cheophu[] = new boolean[2 * n - 1]; //luu trang thai cua tat ca cac duong cheo phu
    static boolean cheochinh[] = new boolean[2 * n - 1]; //luu trang thai cua tat ca cac duong cheo chinh
    static int quanhau[] = new int[n]; //luu 1 cach xep 8 quan hau
    static int demcach = 1; //dem so cach sap xep

    static void KhoiTao() {
        int i;
        for (i = 0; i < n; i++) {
            cot[i] = true;
        }
        for (i = 0; i < 2 * n - 1; i++) {
            cheophu[i] = cheochinh[i] = true;
        }
    }

    static void XepQuanHau(int d) {
        for (int c = 0; c < n; c++) {
            if (cot[c] == true && cheophu[d + c] == true && cheochinh[d - c + n - 1]) {
                quanhau[d] = c; //quan hau o hang thu d se sap o vi tri cot thu c
                //cap nhat lai trang thai cua cot, cheophu, cheochinh
                cot[c] = false;
                cheophu[d + c] = false;
                cheochinh[d - c + n - 1] = false;
                //kiem tra xem da sap den quan hau o hang cuoi cung hay chua
                if (d == n - 1) {
                    InCachSapXep();
                } else {
                    XepQuanHau(d + 1);
                }
                //cap nhat lai trang thai cua cot, cheophu, cheochinh cho cach xep tiep theo
                cot[c] = true;
                cheophu[d + c] = true;
                cheochinh[d - c + n - 1] = true;
            }
        }
    }

    static void InCachSapXep() {
        System.out.println("Cach sap xep thu " + demcach++ + " : ");
        for (int d = 0; d < n; d++) {

            for (int c = 0; c < n; c++) {
                System.out.print("+---");

            }
            System.out.println("+");
            for (int c = 0; c < n; c++) {
                if (quanhau[d] == c) {
                    System.out.print("|\u2006\u2006\u2655\u2006\u2006");
                } else {
                    System.out.print("|   ");
                }
            }
            System.out.println("|");
        }
        for (int c = 0; c < n; c++) {
            System.out.print("+---");

        }
        System.out.println("+");
    }

    public static void main(String[] args) {

        KhoiTao();
        XepQuanHau(0);
    }
}
