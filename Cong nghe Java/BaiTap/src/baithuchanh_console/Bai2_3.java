package baithuchanh_console;

import java.io.*;
import java.util.*;

public class Bai2_3 {

    // Các biến dữ liệu chính
    static int Size;
    static int[][] Banco;
    static ItemRec[] Nuocdi;
    static int SoNuocdi;
    static int SoNghiem;
    
     static void Khoidong() {
        int i, j;
        int row, col;
        String buf;
//tạo đối tượng nhập dữ liệu thuộc kiểu cơ bản
        Scanner input = new Scanner(System.in);
        do //ch�? nhập kích thước bàn c�?
        {
            System.out.print("Nhập số hàng (cột) của bàn c�?: ");
            Size = input.nextInt();
        } while (Size <= 3 || Size > 9);
        do //ch�? nhập chỉ số hàng xuất phát
        {
            System.out.print("Nhập chỉ số hàng xuất phát của con mã : ");
            row = input.nextInt();
        } while (row < 0 || Size <= row);
        do //ch�? nhập chỉ số cột xuất phát
        {
            System.out.print("Nhập chỉ số cột xuất phát của con mã : ");
            col = input.nextInt();
        } while (col < 0 || Size <= col);
//phân phối vùng nhớ chứa array Banco và danh sach Nuocdi
        Banco = new int[Size][Size];
        Nuocdi = new ItemRec[Size * Size];
        for (i = 0; i < Size * Size; i++) {
            Nuocdi[i] = new ItemRec();
        }

//khởi động trạng thái đầu cho các vị trí
        for (i = 0; i < Size; i++) {
            for (j = 0; j < Size; j++) {
                Banco[i][j] = -1; // con mã chưa đi qua ô [i,j]
            }
        }
        SoNghiem = 0;
// Thiết lập nước đi đầu tiên của con mã
        Nuocdi[0].x = col;
        Nuocdi[0].y = row;
        Nuocdi[0].huong = 0;
        SoNuocdi = 0;
//ch�?n khả năng đi tiếp của vị trí đầu tiên của con mã
        Banco[Nuocdi[SoNuocdi].x][Nuocdi[SoNuocdi].y] = 0;
    }
//hàm tìm vị trí kế tiếp của con mã
// trả v�? TRUE nếu tìm được, FALSE nếu không tìm được

    static boolean DiBuocNua() {
        int x = 0, y = 0;
        boolean RetVal;
        RetVal = false;
        do { // lặp tìm vị trí kế
            while (RetVal == false && Nuocdi[SoNuocdi].huong < 8) {
                switch (Nuocdi[SoNuocdi].huong) { //thử hướng đi hiện tại
                    case 0:
                        x = Nuocdi[SoNuocdi].x + 2;
                        y = Nuocdi[SoNuocdi].y - 1;
                        break;
                    case 1:
                        x = Nuocdi[SoNuocdi].x + 1;
                        y = Nuocdi[SoNuocdi].y - 2;
                        break;
                    case 2:
                        x = Nuocdi[SoNuocdi].x - 1;
                        y = Nuocdi[SoNuocdi].y - 2;
                        break;
                    case 3:
                        x = Nuocdi[SoNuocdi].x - 2;
                        y = Nuocdi[SoNuocdi].y - 1;
                        break;
                    case 4:
                        x = Nuocdi[SoNuocdi].x - 2;
                        y = Nuocdi[SoNuocdi].y + 1;
                        break;
                    case 5:
                        x = Nuocdi[SoNuocdi].x - 1;
                        y = Nuocdi[SoNuocdi].y + 2;
                        break;
                    case 6:
                        x = Nuocdi[SoNuocdi].x + 1;

                        y = Nuocdi[SoNuocdi].y + 2;
                        break;
                    case 7:
                        x = Nuocdi[SoNuocdi].x + 2;
                        y = Nuocdi[SoNuocdi].y + 1;
                        break;
                }
                if (0 <= x && x < Size && 0 <= y && y < Size && Banco[x][y] == -1) {
// nếu được thì ghi nhận
                    SoNuocdi = SoNuocdi + 1;
                    Banco[x][y] = SoNuocdi;
                    Nuocdi[SoNuocdi].x = x;
                    Nuocdi[SoNuocdi].y = y;
                    Nuocdi[SoNuocdi].huong = 0;
                    RetVal = true;
                } else // nếu không tìm được vị trí kế tiếp
                {
                    Nuocdi[SoNuocdi].huong = Nuocdi[SoNuocdi].huong + 1;
                }
            }
            if (RetVal == false && SoNuocdi != 0) {
// nếu không tìm được vị trí kế thì lùi con mã 1 bước
                Banco[Nuocdi[SoNuocdi].x][Nuocdi[SoNuocdi].y] = -1;
                SoNuocdi = SoNuocdi - 1;
                Nuocdi[SoNuocdi].huong = Nuocdi[SoNuocdi].huong + 1;
            }
        } while (!RetVal && (SoNuocdi != 0));
        return RetVal;
    }
// In kết quả con mã đi trên bàn c�?

    static void InKetqua() {
        int h, c;
        SoNghiem = SoNghiem + 1;
        System.out.println("Cách đi thứ : " + SoNghiem);
        for (h = 0; h < Size; h++) {
// Hiển thị hàng lưới ngang bàn c�?
            for (c = 0; c < Size; c++) {
                System.out.print("+----");
            }
            System.out.println("+");
// Hiển thị nội dung hàng thứ h bàn c�?
            for (c = 0; c < Size; c++) {
                System.out.format("| %02d ", Banco[h][c]);
            }
            System.out.println("|");
        }
// Hiển thị hàng lưới ngang cuối cùng của bàn c�?
        for (c = 0; c < Size; c++) {
            System.out.print("+----");
        }
        System.out.println("+");
    }

// Chương trình chính
    public static void main(String[] args) {
        Khoidong();
        while (DiBuocNua()) {
            if (SoNuocdi == Size * Size - 1) {
//tìm được 1 cách mới, in cách đi mới tìm được
                InKetqua();
                Banco[Nuocdi[SoNuocdi].x][Nuocdi[SoNuocdi].y] = -1;
//lùi con mã 1 bước
                SoNuocdi = SoNuocdi - 1;
//tiếp tục tìm cách đi khác
                Nuocdi[SoNuocdi].huong = Nuocdi[SoNuocdi].huong + 1;
            }
        }
    }
//hàm khởi động các giá trị đầu của chương trình


    static class ItemRec {

        public int x, y; //vị trí con mã
        public int huong; //mã hướng đi 0-7
    };

}
