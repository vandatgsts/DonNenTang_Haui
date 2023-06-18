package com.example.quanlygiaotrinh.info_Screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.quanlygiaotrinh.Adapter.AdapterProcressManager;
import com.example.quanlygiaotrinh.Database.DBHelper;
import com.example.quanlygiaotrinh.Database.GetTimeInPhone;
import com.example.quanlygiaotrinh.MainActivity;
import com.example.quanlygiaotrinh.R;

import java.util.ArrayList;

public class ProgressManagement extends AppCompatActivity {
    ArrayList<Procress> listProcess = new ArrayList<>();
    RecyclerView recyclerView;
    Button btnAdd;
    ImageView btnBack;
    DBHelper dbHelper;
    int bookId;
    AdapterProcressManager adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_management);
        getId();
        dbHelper = MainActivity.dbHelper;
        Intent intent = getIntent();
        bookId = intent.getIntExtra("bookId", 0);
        listProcess = dbHelper.getProcessesByBookId(bookId);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AdapterProcressManager(listProcess, this);
        recyclerView.setAdapter(adapter);

        //  AddAction();
        btnBack.setOnClickListener(v -> {
            finish();
        });
        btnAdd.setOnClickListener(v -> {
            ShowDialog();

        });
    }

    void getId() {
        btnBack = findViewById(R.id.process_manage_btn_arrowback);
        recyclerView = findViewById(R.id.process_manage_recy);
        btnAdd = findViewById(R.id.process_manage_btn_add);
    }

    void ShowDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Sử dụng LayoutInflater để inflate file XML custom_dialog.xml
        LayoutInflater inflater = LayoutInflater.from(this);
        View dialogView = inflater.inflate(R.layout.dialog_new_process, null);

        // Tìm kiếm các phần tử trong dialogView
        EditText nameProcess = dialogView.findViewById(R.id.new_process_name);
        EditText content = dialogView.findViewById(R.id.new_process_content);
        // Thiết lập nội dung và xử lý sự kiện cho các phần tử trong dialogView
        final Procress[] newPrcress = new Procress[1];
        builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name=nameProcess.getText().toString();
                String content1=content.getText().toString();
                newPrcress[0] = new Procress(name,
                        content1,
                        GetTimeInPhone.GetDateTime(),
                        new ArrayList<>(),
                        1,
                        bookId);
                listProcess.add(newPrcress[0]);
                adapter.notifyDataSetChanged();
                dbHelper.addProcress(newPrcress[0]);
            }
        });

        // Thiết lập giao diện và hiển thị AlertDialog
        builder.setView(dialogView);
        AlertDialog dialog = builder.create();
        dialog.show();

    }

}