package com.example.quanlygiaotrinh.Register_Screen.Register_Form_Screen;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlygiaotrinh.Database.Book;
import com.example.quanlygiaotrinh.Database.DBHelper;
import com.example.quanlygiaotrinh.Database.ListData;
import com.example.quanlygiaotrinh.MainActivity;
import com.example.quanlygiaotrinh.R;
import com.example.quanlygiaotrinh.Register_Screen.Register;

import java.util.ArrayList;

public class Register_Form extends AppCompatActivity {
    TextView edtNameAuthor;
    EditText edtNameBook;
    EditText edtCode;
    EditText edtNumber;
    EditText edtTime;
    Button btnCance;
    Button btnSend;
    ArrayList<String> viewTeachers = new ArrayList<>();
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_form);
        getId();
        dbHelper=MainActivity.dbHelper;
        edtNameAuthor.setOnClickListener(v -> {
            viewTeachers.clear();
           chooseTeacher();

        });
        btnCance.setOnClickListener(v -> {
            screenToScreen();
        });
        btnSend.setOnClickListener(v -> {
            AddNewBook();
            viewTeachers.clear();
        });
    }

    void getId() {
        edtNameBook = findViewById(R.id.register_edt_name_book);
        edtNumber = findViewById(R.id.register_edt_number);
        btnCance = findViewById(R.id.register_btn_cance);
        edtCode=findViewById(R.id.register_edt_code);
        edtNameAuthor = findViewById(R.id.register_view_name_author);
        edtTime = findViewById(R.id.register_edt_time);
        btnSend = findViewById(R.id.register_btn_send);
    }

    void screenToScreen() {
        startActivity(new Intent(getApplicationContext(), Register.class));
        finish();
    }

    private void chooseTeacher() {
        LayoutInflater layoutInflater = getLayoutInflater();
        View alertLayout = layoutInflater.inflate(R.layout.dialog_choose_teacher, null);
        RecyclerView view = alertLayout.findViewById(R.id.choose_teacher_recy);
        view.setLayoutManager(new LinearLayoutManager(this));
        AdapterChooseTeacher adapter = new AdapterChooseTeacher(ListData.listTeacher,viewTeachers);
        view.setAdapter(adapter);
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setView(alertLayout);
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Handle positive button click
                String ans = "";
                for (String i : viewTeachers)
                    ans += i + ", ";
                edtNameAuthor.setText(ans);
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = alert.create();
        alertDialog.show();

    }

    void AddNewBook() {
        String nameBook = edtNameBook.getText().toString();
        String code = edtCode.getText().toString();
        String numberStr = edtNumber.getText().toString();
        String time = edtTime.getText().toString();
        if (nameBook.isEmpty() || code.isEmpty() || numberStr.isEmpty() || viewTeachers.isEmpty() || time.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        } else {
            int number = Integer.parseInt(numberStr);
            ArrayList<String> selectedTeachers = new ArrayList<>(viewTeachers);
            Book book=new Book(nameBook, code, number,selectedTeachers, time,1,1,null);
            dbHelper.addBook(book);
            Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
            screenToScreen();
        }
    }
}