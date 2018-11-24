package baitapcongnghejava;

import java.io.*;
import java.util.*;

public class Bai1_4 {

    public static void main(String[] args) {
        ArrayList<SoHang> DaySoHang1 = new ArrayList<>();
        DaySoHang1.add(new SoHang(-2, 3));
        DaySoHang1.add(new SoHang(-1, 2));
        DaySoHang1.add(new SoHang(5, 3));

        ArrayList<SoHang> DaySoHang2 = new ArrayList<>();
        DaySoHang2.add(new SoHang(4, 6));
        DaySoHang2.add(new SoHang(-3, 8));

        DaThuc dt1 = new DaThuc(DaySoHang1);
        DaThuc dt2 = new DaThuc(DaySoHang2);

        System.out.println("Đa thức 1 gốc: ");
        dt1.InDaThuc();
        System.out.println("Đa thức 2 gốc: ");
        dt2.InDaThuc();
        System.out.println("Đa thức 1 sau khi chuẩn hóa: ");
        dt1.ChuanHoaDaThuc();
        dt1.InDaThuc();
        System.out.println("Đa thức 2 sau khi chuẩn hóa: ");
        dt2.ChuanHoaDaThuc();
        dt2.InDaThuc();
        System.out.println("Kết quả đa thức 1 nhân đa thức 2: ");
        DaThuc dtkq = dt1.NhanDaThuc(dt2);
        dtkq.ChuanHoaDaThuc();
        dtkq.InDaThuc();
    }
}

class SoHang {

    public float HeSo;
    public int SoMu;

    public SoHang(float hs, int sm) {
        HeSo = hs;
        SoMu = sm;
    }

    public String ChuoiSoHang() {
        return String.format("%1$sX^%2$d", Math.abs(HeSo), SoMu);
    }
}

class DaThuc {

    public ArrayList<SoHang> DaySoHang;

    public DaThuc(ArrayList<SoHang> daysohang) {
        DaySoHang = daysohang;
    }

    //Hàm hoán vị 2 số hạng
    public void HoanVi(int vtsh1, int vtsh2) {
        SoHang temp = DaySoHang.get(vtsh1);
        DaySoHang.set(vtsh1, DaySoHang.get(vtsh2));
        DaySoHang.set(vtsh2, temp);
    }

    //Hàm sắp xếp đa thức theo thứ tự số mũ giảm dần
    public void SapXepDaThuc() {
        int i, j, vtmax, soluong = DaySoHang.size();
        for (i = 0; i < soluong - 1; i++) {
            vtmax = i;
            for (j = i + 1; j < soluong; j++) {
                if (DaySoHang.get(vtmax).SoMu < DaySoHang.get(j).SoMu) {
                    vtmax = j;
                }
            }
            HoanVi(i, vtmax);
        }
    }

    //Hàm chuẩn hóa đa thức
    public void ChuanHoaDaThuc() {
        SapXepDaThuc();
        for (int i = 0; i < DaySoHang.size(); i++) {
            float S = DaySoHang.get(i).HeSo;
            int j = i + 1;
            while (j < DaySoHang.size() && DaySoHang.get(j).SoMu == DaySoHang.get(i).SoMu) {
                S += DaySoHang.get(j).HeSo;
                DaySoHang.remove(j);
            }
            DaySoHang.get(i).HeSo = S;
        }
    }

    //Hàm nhân với 1 đa thức khác
    public DaThuc NhanDaThuc(DaThuc dathuc) {
        ArrayList<SoHang> temp = new ArrayList<>();
        for (int i = 0; i < DaySoHang.size(); i++) {
            for (int j = 0; j < dathuc.DaySoHang.size(); j++) {
                float heso = DaySoHang.get(i).HeSo * dathuc.DaySoHang.get(j).HeSo;
                int somu = DaySoHang.get(i).SoMu + dathuc.DaySoHang.get(j).SoMu;
                temp.add(new SoHang(heso, somu));
            }
        }
        return new DaThuc(temp);
    }

    //Hàm in đa thức ra màn hình
    public void InDaThuc() {
        int soluong = DaySoHang.size();
        if (soluong == 0) {
            return;
        }
        String strDaThuc = "";
        if (DaySoHang.get(0).HeSo >= 0) {
            strDaThuc = DaySoHang.get(0).ChuoiSoHang() + "";
        } else {
            strDaThuc = "- " + DaySoHang.get(0).ChuoiSoHang() + "";
        }
        for (int i = 1; i < soluong; i++) {
            if (DaySoHang.get(i).HeSo >= 0) {
                strDaThuc += " + " + DaySoHang.get(i).ChuoiSoHang();
            } else {
                strDaThuc += " - " + DaySoHang.get(i).ChuoiSoHang();
            }
        }
        System.out.println(strDaThuc);
    }
}
