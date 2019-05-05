package com.example.thanhlong_pc.docghifilenhiphanandroid;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    //định nghĩa các thuộc tính cần dùng
    TextView txtOutput;
    TextView txtFile;
    File file;

    //hàm kiểm tra thẻ nhớ ngoài có cho đọc/ghi không
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) return true;
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //xác ịnh pathname của file cần ghi/ọ
        String pathSDCard = Environment.getExternalStorageDirectory().getPath();
        file = new File(pathSDCard, "data.bin");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
//xác ịnh các ối tợng giao diện
        txtOutput = (TextView) findViewById(R.id.txtOutput);
        txtFile = (EditText) findViewById(R.id.txtFile);
//kiểm tra xem có thể ghi/ọc không
        if (!isExternalStorageWritable()) return;
//to dữ liệu và ghi chúng ra file
        if (Create_Write_File())
//ọc dữ liệu từ file vào các biến và hiển thị kết qu ể kiểm tra
            Read_Disp_File();
    }

    //hàm to file và thử ghi số biến dữ liệu lên file
    private boolean Create_Write_File() {
        try {
//1. to ối tợng qun lý file xuất

            FileOutputStream fout = new
                    FileOutputStream(file);
//2. to ối tợng qun lý file xuất có ệm (nếu cần hiệu qu)
            BufferedOutputStream bouts = new BufferedOutputStream(fout);
//3. to ối tợng ghi file nhị phân
            DataOutputStream out = new DataOutputStream(bouts);
//4. xử lý dữ liệu theo yêu cầu chng trình
            int i = -15;
            double d = -1.5;
            String s = "Nguyễn Vn Hiệp";
            Boolean b = true;
//5. ghi dữ liệu ra file
            out.writeBoolean(b); //ghi trị luận lý
            out.writeInt(i); //ghi trị nguyên 32 bit
            out.writeDouble(d); //ghi trị thực 64 bit
            out.writeUTF(s); //ghi chuỗi theo cách mã hóa UTF-8
//6. óng các ối tợng ợc dùng li
            out.close();
            bouts.close();
            fout.close();
        } catch (Exception e) {
            txtOutput.setText(txtOutput.getText() + "\n" + e.getMessage());
            return false;
        }
        return true;
    }

    //hàm ọc dữ liệu từ file vào các biến rồi hiển thị nội dung
    private boolean Read_Disp_File() {
        try {
//1. to ối tợng qun lý file nhập
            FileInputStream fin = new FileInputStream(file);
//2. to ối tợng qun lý file nhập có ệm (nếu cần hiệu qu)
            BufferedInputStream bins = new BufferedInputStream(fin);
//3. to ối tợng ọc file nhị phân
            DataInputStream in = new DataInputStream(bins);
//4. ịnh nghĩa các biến theo yêu cầu chng trình
            int i;
            double d;
            String s;
            Boolean b;
//5. ọc dữ liệu từ file vào các biến
            b = in.readBoolean(); //ọc trị luận lý
            i = in.readInt(); //ọc trị nguyên 32 bit
            d = in.readDouble(); //ọc trị thực 64 bit
            s = in.readUTF(); //ọc chuỗi UTF-8
//6. óng các ối tợng ợc dùng li
            in.close();
            bins.close();
            fin.close();
//hiển thị kết qu
            txtOutput.setText(txtOutput.getText() + "\ni=" + i);
            txtOutput.setText(txtOutput.getText() + "\nb=" + b);
            txtOutput.setText(txtOutput.getText() + "\nd=" + d);
            txtOutput.setText(txtOutput.getText() + "\ns=" + s);
        } catch (Exception e) {
            txtOutput.setText(txtOutput.getText() + "\n" + e.getMessage());
            return false;
        }
        return true;
    }
}

