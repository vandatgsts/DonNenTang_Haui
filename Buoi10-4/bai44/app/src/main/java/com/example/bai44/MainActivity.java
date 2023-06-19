package com.example.bai44;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button guitt;
    EditText hoten, cmnd, ttbs;
    CheckBox chkdocbao, chkdocsach, chekcode;
    RadioGroup radiogr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWidget();
        registerForContextMenu(guitt);
    }

    private void getWidget() {
        guitt = (Button) findViewById(R.id.btnguitt);
        hoten = (EditText) findViewById(R.id.editten);
        cmnd = (EditText) findViewById(R.id.editcmnd);
        ttbs = (EditText) findViewById(R.id.editbosung);

        chkdocbao = (CheckBox) findViewById(R.id.checkdocbao);
        chkdocsach = (CheckBox) findViewById(R.id.checkdocsach);
        chekcode = (CheckBox) findViewById(R.id.checkdoccode);
        radiogr = (RadioGroup) findViewById(R.id.groupradio);
//        guitt.setOnClickListener(nut);


    }

    //    View.OnClickListener nut = new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            if (view == guitt) {
    // kiem tra thong tin ten  hợp lệ
    void SendInfo() {
        String ten = hoten.getText() + "";
        ten = ten.trim();// lệnh giúp bỏ hết giấu cách đi
        if (ten.length() < 3) {
            hoten.requestFocus();
            hoten.selectAll();
            Toast.makeText(MainActivity.this, "Tên phải lớn hơn 3 kí tự", Toast.LENGTH_LONG).show();
            return;
        }
        // kiểm tra thông tin của cmnd chứng minh nhân dân phải đúng bằng 9
        String cmnd1 = cmnd.getText() + "";
        cmnd1 = cmnd1.trim();// bỏ hết khoảng trắng
        if (cmnd1.length() != 9)// độ dài của string
        {
            cmnd.requestFocus();
            cmnd.selectAll();
            Toast.makeText(MainActivity.this, "cccd phải đúng 9 kí tự má ơi", Toast.LENGTH_LONG).show();
            return;
        }
        //check bang cau
        String bangcap = "";
        int id = radiogr.getCheckedRadioButtonId();// lệnh này để lấy ra id
        if (id == -1) {
            Toast.makeText(MainActivity.this, "vui lòng chọn bằng cấp!!!", Toast.LENGTH_LONG).show();
            return;
        }
        RadioButton a = (RadioButton) findViewById(id);//sauu khi lấy được id mình sẽ ánh xạ vào id để lấy ra bằng cấp
        bangcap = a.getText() + "";

        // check sở thích
        String Sothich = ", ";
        if (chekcode.isChecked()) {
            Sothich += chekcode.getText() + ", ";


        }
        if (chkdocbao.isChecked()) {
            Sothich += chkdocbao.getText() + ", ";
        }
        if (chkdocsach.isChecked()) {
            Sothich += chkdocsach.getText();
        }
        // lấy ra thông tin bổ xung
        String ttbs1 = ttbs.getText() + "";
        // AlertDialog.Builder là một lớp trong Android được sử dụng
        // để xây dựng và hiển thị một hộp thoại (dialog) cảnh báo (alert dialog) trên màn hình thiết bị di động.
        // Hộp thoại cảnh báo là một thành phần giao diện người dùng (UI) dùng để cung cấp thông báo
        // hoặc cảnh báo đến người dùng, yêu cầu họ xác nhận, đồng ý hoặc từ chối một hành động.

        AlertDialog.Builder buider = new AlertDialog.Builder(MainActivity.this);
        buider.setTitle("THÔNG TIN CÁ NHÂN");
        // thiết lập nút đồng ý và sử lí sự kiện khi bấm nút đóng
        buider.setPositiveButton("ĐÓNG", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        String dulieu = "";
        dulieu += ten + "\n";
        dulieu += cmnd1 + "\n";
        dulieu += bangcap + "\n";
        dulieu += Sothich + "\n";
        dulieu += "----------------------------" + "\n";
        dulieu += ttbs1;
        buider.setMessage(dulieu); // thiết lập nội dung cho mess
        buider.create().show();//tạo và show ra màn hình

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemSendInfo:
                SendInfo();
                break;
            case R.id.itemClose:
                System.exit(0);
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemSendInfo:
                SendInfo();
                break;
            case R.id.itemClose:
                System.exit(0);
                break;

        }
        return super.onContextItemSelected(item);
    }
}