package baitapcongnghejava;

import java.io.*;
import java.util.*;

public class Bai2_1 {

    //định nghĩa các thuộc tính dữ liệu
    static final int MAX = 7; //chỉ số max của hàng và cột
    static int cachso = 0; //số cách sắp đã tìm được

    static int[] hang = new int[MAX + 1]; //danh sách chỉ số hàng đã thử trong từng cột
    static int c; //vị trị cột đang sắp
//Điểm nhập của chương trình

    public static void main(String[] args) {
// khởi động trạng thái xuất phát
        for (c = 0; c <= MAX; c++) {
            hang[c] = -1;
        }
        cachso = 0;
//bắt đầu đặt con hậu ở cột 0 vào hàng 0
        hang[0] = 0;
//bắt đầu tìm hàng cho con hậu ở cột thứ 2 (chỉ số là 1)
        c = 1;
        while (Tim1cach()) {
// tìm được cách sắp 8 con hậu
            InKetqua();
//lùi lại cột trước để tiếp tục tìm cách sắp khác
            c--;
        }
// hết cách --> dừng chương trình
        System.out.println("Số nghiệm là " + cachso);
        return;
    }
//cố gắng tìm 1 phương án sắp 8 con hậu

    static boolean Tim1cach() {
        int h;
        while (c <= MAX) {
// tìm chỉ số hàng cho con hậu ở cột c
            h = timhang(c);
            if (h >= 0) { //nếu tìm được
//lưu hàng vào danh sách kết quả rồi tăng chỉ số cột để tiếp tục
                hang[c++] = h;
                continue;
            }
//trường hợp không tìm được hàng cho con hậu ở cột c
            if (c > 0) {
//nếu cột c không phải là cột đầu tiên thì :
//xét lại cột c từ hàng đầu rồi lùi cột c
                hang[c--] = -1;
                continue;
            }
//hết cách
            return false;
        }

        return true;
    }//hết hàm Tim1cach
//hàm cố gắng tìm chỉ số hàng cho con hậu ở cột c
//trả về chỉ số hàng tìm được hay -1 để báo sai

    static int timhang(int c) {
        int h, hmin = hang[c] + 1;
        for (h = hmin; h <= MAX; h++) // thử hàng h
        {
            if (testvitri(h, c)) {
                return h;
            }
        }
        return -1;
    }
//hàm kiểm tra xem có thể đặt con hậu ở vị trí h,c ?
//trả về TRUE nếu được, FALSE nếu không

    static boolean testvitri(int h, int c) {
        int c1, h1;
//cột c đang kiểm tra nên chưa có con hậu nào
// xem có con hậu nào nằm ở hàng h ?
        for (c1 = 0; c1 < c; c1++) {
            if (hang[c1] == h) {
                return false;
            }
        }
// xem có con hậu nào nằm trên đường chéo trên trái - dưới phải
        c1 = c - 1;
        h1 = h - 1;
        while (c1 >= 0 && h1 >= 0) {
            if (hang[c1] == h1) {
                return false;
            }
            h1--;
            c1--;
        }
// xem có con hậu nào nằm trên đường chéo trên phải - dưới trái
        c1 = c - 1;
        h1 = h + 1;
        while (c1 >= 0 && h1 <= MAX) {
            if (hang[c1] == h1) {
                return false;
            }
            h1++;
            c1--;
        }
        return true;
    }
//hàm in kết quả 1 cách sắp 8 con hậu theo yêu cầu

    static void InKetqua() {
        int h, c;
        System.out.println("Cách sắp thứ : " + (++cachso));
        for (h = 0; h <= MAX; h++) {
// Hiển thị hàng lưới ngang của bàn cờ
            for (c = 0; c <= MAX; c++) {
                System.out.print("+---");
            }
            System.out.println("+");
// Hiển thị nội dung hàng thứ h của bàn cờ
            for (c = 0; c <= MAX; c++) {
                if (hang[c] == h) {
                    System.out.print("| x ");
                } else {
                    System.out.print("| ");
                }
            }
            System.out.println("|");
        }

// Hiển thị hàng lưới ngang cuối cùng của bàn cờ
        for (c = 0; c <= MAX; c++) {
            System.out.print("+---");
        }
        System.out.println("+");
    }
}
