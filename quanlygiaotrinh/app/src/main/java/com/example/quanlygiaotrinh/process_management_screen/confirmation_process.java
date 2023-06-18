package com.example.quanlygiaotrinh.process_management_screen;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlygiaotrinh.Adapter.AdapterConfirmProcess;
import com.example.quanlygiaotrinh.Database.Book;
import com.example.quanlygiaotrinh.Database.DBHelper;
import com.example.quanlygiaotrinh.MainActivity;
import com.example.quanlygiaotrinh.R;

import java.util.ArrayList;

public class confirmation_process extends AppCompatActivity {
    ArrayList<Book> listBook=new ArrayList<>();
    TextView cd;
    TextView approved;
    TextView complete;
    ImageView btn_Back;
    RecyclerView recyclerView;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation_process);
        GetId();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listBook=dbHelper.getBooksByStatus(1);
        AdapterConfirmProcess adapterConfirmProcess=new AdapterConfirmProcess(listBook);
        recyclerView.setAdapter(adapterConfirmProcess);
        View1();
        View2();
        View3();
        btn_Back.setOnClickListener(v -> {
            finish();
        });
    }
    void GetId(){
        dbHelper= MainActivity.dbHelper;
        btn_Back=findViewById(R.id.confirm_prcocss_btn_arrowback);
        cd=findViewById(R.id.confirm_process_menu_cd);
        approved=findViewById(R.id.confirm_process_menu_approved);
        complete=findViewById(R.id.confirm_process_menu_complete);
        recyclerView=findViewById(R.id.confirm_process_recy);
    }
    void View1(){
        cd.setOnClickListener(v -> {
            cd.setBackgroundResource(R.drawable.shape_no_boder);
            approved.setBackground(null);
            complete.setBackground(null);
            listBook=dbHelper.getBooksByStatus(1);
            AdapterConfirmProcess adapterConfirmProcess=new AdapterConfirmProcess(listBook);
            recyclerView.setAdapter(adapterConfirmProcess);
        });
    }
void View2(){
        approved.setOnClickListener(v -> {
            approved.setBackgroundResource(R.drawable.shape_no_boder);
            cd.setBackground(null);
            complete.setBackground(null);
            listBook=dbHelper.getBooksByStatus(2);
            AdapterConfirmProcess adapterConfirmProcess=new AdapterConfirmProcess(listBook);
            recyclerView.setAdapter(adapterConfirmProcess);
        });
    }
void View3(){
        complete.setOnClickListener(v -> {
            complete.setBackgroundResource(R.drawable.shape_no_boder);
            approved.setBackground(null);
            cd.setBackground(null);
            listBook=dbHelper.getBooksByStatus(3);
            AdapterConfirmProcess adapterConfirmProcess=new AdapterConfirmProcess(listBook);
            recyclerView.setAdapter(adapterConfirmProcess);
        });
    }

}