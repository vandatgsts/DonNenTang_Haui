package com.example.quanlygiaotrinh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.quanlygiaotrinh.Acceptance_Screen.Acceptance;
import com.example.quanlygiaotrinh.Database.DBHelper;
import com.example.quanlygiaotrinh.Database.ListData;
import com.example.quanlygiaotrinh.Register_Screen.Register;
import com.example.quanlygiaotrinh.info_Screen.info_GiaoTrinh;
import com.example.quanlygiaotrinh.process_management_screen.confirmation_process;

public class MainActivity extends AppCompatActivity {
    TextView btn_service;
    TextView btn_info;
    TextView btn_process;
    TextView btn_acceptance;
    public static DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getId();
        dbHelper=new DBHelper(this);
//        dbHelper.clearDatabase();
        if (ListData.listTeacher.isEmpty())
            ListData.AddTeacher();
        btn_service.setOnClickListener(v -> {
            screenToScreen(Register.class);
        });
        btn_process.setOnClickListener(v -> {
            screenToScreen(confirmation_process.class);
        });
        btn_info.setOnClickListener(v -> {
            screenToScreen(info_GiaoTrinh.class);
        });
        btn_acceptance.setOnClickListener(v -> {
            screenToScreen(Acceptance.class);
        });
    }

    void getId() {
        btn_service = findViewById(R.id.btn_service);
        btn_info = findViewById(R.id.btn_info);
        btn_process = findViewById(R.id.btn_process);
        btn_acceptance = findViewById(R.id.btn_acceptance);
    }

    void screenToScreen(Class toScreen) {
        startActivity(new Intent(getApplicationContext(), toScreen));
    }
}