package com.example.thanhlong_pc.baithuchanh81gptb2android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import static android.view.ViewGroup.LayoutParams.FILL_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static android.widget.LinearLayout.VERTICAL;

public class MainActivity extends AppCompatActivity {

    //₫ịnh nghĩa các ₫ối tượng giao diện
    TextView lblA;
    TextView lblB;
    TextView lblC;
    EditText txtA;
    EditText txtB;
    EditText txtC;
    Button btnStart;
    TextView lblKetqua;
    TextView lblX1;
    TextView lblX2;
    String strVonghiem, strConghiem;
    String strA, strB, strC;
    String strBatdau;
    //₫ịnh nghĩa các biến cần dùng
    double a, b, c;
    double delta;
    double x1, x2;

    //Hàm nhập 3 tham số a, b, c của phương trình
    private void NhapABC() {
        //mã hóa 3 tham số a, b, c từ chuỗi thành số thực
        a = Double.valueOf(txtA.getText().toString());
        b = Double.valueOf(txtB.getText().toString());
        c = Double.valueOf(txtC.getText().toString());
    }

    //Hàm giải phương trình bậc 2
    private void GiaiPT() {
        //tính biệt số delta
        delta = b * b - 4 * a * c;
        //kiểm tra biệt số delta
        if (delta >= 0) {
            //tính 2 nghiệm thực
            x1 = (-b + Math.sqrt(delta)) / 2 / a;
            x2 = (-b - Math.sqrt(delta)) / 2 / a;
        }
    }

    //Hàm xuất kết quả của phương trình bậc 2
    private void XuatKetqua() {
        //kiểm tra biệt số delta ₫ể ra quyết ₫ịnh xử lý
        if (delta >= 0) { //trường hợp có 2 nghiệm thực
            lblKetqua.setText(strConghiem);
            lblX1.setText("x1 = " + x1);
            lblX2.setText("x2 = " + x2);
        } else { //trường hợp vô nghiệm
            lblKetqua.setText(strVonghiem);
            lblX1.setText("");
            lblX2.setText("");
        }
    }

    //tác vụ nhập dữ liệu, giải phương trình, hiển thị kết quả
    public void btnStart_Click(View view) {
        //1. Nhập 3 tham số a, b, c của phương trình
        NhapABC();
        //2. Giải phương trình
        GiaiPT();
        //3. Hiển thị kết quả
        XuatKetqua();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //xác ₫ịnh các chuỗi ₫ược dùng trong ứng dụng
        strA = getResources().getString(R.string.strA);
        strB = getResources().getString(R.string.strB);
        strC = getResources().getString(R.string.strC);
        strBatdau = getResources().getString(R.string.strBatdau);
        strVonghiem = getResources().getString(R.string.strVonghiem);
        strConghiem = getResources().getString(R.string.strConghiem);

//        //Sử dụng giao diện tạo bằng XML LinearLayout
//        UseXMLLinearLayout();
//        //Sử dụng giao diện tạo bằng XML AbsoluteLayout
//        UseXMLAbsoluteLayout();
//        //Sử dụng giao diện tạo bằng XML TableLayout
//        UseXMLTableLayout();
//        //Sử dụng giao diện tạo bằng XML GridLayout
//        UseXMLGridLayout();
    }

    void UseCodeUI() {
        //tạo và kết hợp cửa sổ giao diện cho ứng dụng
        setContentView(CreateView());
    }

    void Map() {
        //xác ₫ịnh các ₫ối tượng giao diện cần truy xuất
        txtA = (EditText) findViewById(R.id.txtA);
        txtB = (EditText) findViewById(R.id.txtB);
        txtC = (EditText) findViewById(R.id.txtC);
        lblKetqua = (TextView) findViewById(R.id.lblKetqua);
        lblX1 = (TextView) findViewById(R.id.lblX1);
        lblX2 = (TextView) findViewById(R.id.lblX2);
    }

    void UseXMLLinearLayout() {
        //tạo và kết hợp cửa sổ giao diện cho ứng dụng
        setContentView(R.layout.linearlayout);
        Map();
    }

    void UseXMLAbsoluteLayout() {
        //tạo và kết hợp cửa sổ giao diện cho ứng dụng
        setContentView(R.layout.absolutelayout);
        Map();
    }

    void UseXMLTableLayout() {
        //tạo và kết hợp cửa sổ giao diện cho ứng dụng
        setContentView(R.layout.tablelayout);
        Map();
    }

    void UseXMLGridLayout() {
        //tạo và kết hợp cửa sổ giao diện cho ứng dụng
        setContentView(R.layout.gridlayout);
        Map();
    }

    //tác vụ tạo giao diện cho ứng dụng bằng code
    private LinearLayout CreateView() {
        //tạo Layout LinearLayout miêu tả toàn bộ cửa sổ
        LinearLayout linearLayout = new LinearLayout(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(FILL_PARENT, WRAP_CONTENT);
        linearLayout.setLayoutParams(params);
        linearLayout.setOrientation(VERTICAL);
        //tạo 1 TableLayout chứa 3 hàng 2 cột ₫ể chứa 3 textbox nhập liệu a,b,c
        TableLayout tl = new TableLayout(this);
        tl.setPadding(0, 10, 0, 0);
        tl.setStretchAllColumns(true);
        //tạo hàng ₫ầu ₫ể nhập tham số a
        TableRow tableRow = new TableRow(this);
        tableRow.setPadding(0, 10, 0, 0);
        //tạo 1 TextView ₫ể hiển thị thông báo yêu cầu nhập tham số a
        lblA = new TextView(this);
        lblA.setText(strA);
        tableRow.addView(lblA);
        //tạo 1 ExitText ₫ể người dùng nhập tham số a
        txtA = new EditText(this);
        tableRow.addView(txtA);
        //add hàng nhập tham số a vào tableLayout
        tl.addView(tableRow);
        //tạo hàng thứ 2 ₫ể nhập tham số b
        tableRow = new TableRow(this);
        tableRow.setPadding(0, 10, 0, 0);
        //tạo 1 TextView ₫ể hiển thị thông báo yêu cầu nhập tham số b
        lblB = new TextView(this);
        lblB.setText(strB);
        tableRow.addView(lblB);
        //tạo 1 ExitText ₫ể người dùng nhập tham số b
        txtB = new EditText(this);
        tableRow.addView(txtB);
        //add hàng nhập tham số b vào tableLayout
        tl.addView(tableRow);
        //tạo hàng thứ 3 ₫ể nhập tham số c
        tableRow = new TableRow(this);
        tableRow.setPadding(0, 10, 0, 0);
        //tạo 1 TextView ₫ể hiển thị thông báo yêu cầu nhập tham số c
        lblC = new TextView(this);
        lblC.setText(strC);
        tableRow.addView(lblC);
        //tạo 1 ExitText ₫ể người dùng nhập tham số c
        txtC = new EditText(this);
        tableRow.addView(txtC);
        //add hàng nhập tham số b vào tableLayout
        tl.addView(tableRow);
        //add table vào Layout
        linearLayout.addView(tl);
        //tạo button và add vào linearLayout
        btnStart = new Button(this);
        btnStart.setText(strBatdau);
        linearLayout.addView(btnStart);
        //thiết lập ₫ối tượng xử lý sự kiện Click vào button
        btnStart.setOnClickListener(submitListener);
        //tạo 3 TextView hiển thị kết quả và add vào linearLauout
        lblKetqua = new TextView(this);
        linearLayout.addView(lblKetqua);
        lblX1 = new TextView(this);
        linearLayout.addView(lblX1);
        lblX2 = new TextView(this);
        linearLayout.addView(lblX2);
        return linearLayout;
    }

    //tạo ₫ối tượng xử lý sự kiện Click chuột trên button
    private View.OnClickListener submitListener = new View.OnClickListener() {
        public void onClick(View view) {
            //gọi hàm giải phương trình bậc 2
            btnStart_Click(view);
        }
    };
}
