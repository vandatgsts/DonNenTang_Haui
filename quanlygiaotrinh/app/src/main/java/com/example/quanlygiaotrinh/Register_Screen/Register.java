package com.example.quanlygiaotrinh.Register_Screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quanlygiaotrinh.Database.Book;
import com.example.quanlygiaotrinh.Database.DBHelper;
import com.example.quanlygiaotrinh.MainActivity;
import com.example.quanlygiaotrinh.R;
import com.example.quanlygiaotrinh.Register_Screen.Register_Form_Screen.Register_Form;

import java.util.ArrayList;

public class Register extends AppCompatActivity {
    ImageView btn_back;
    RecyclerView recyclerView;
    TextView btn_biensoan,viewVui;
    ArrayList<Book> listBook=new ArrayList<>();
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getID();
        dbHelper=MainActivity.dbHelper;
        ListView();
        btn_back.setOnClickListener(v -> screenToScreen(MainActivity.class));
        btn_biensoan.setOnClickListener(v -> screenToScreen(Register_Form.class));
    }
    void ListView(){
        listBook= dbHelper.getBooksByStatus(1);
        if(!listBook.isEmpty()) viewVui.setVisibility(View.GONE);
        AdapterRegister adapterRegister=new AdapterRegister(listBook,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapterRegister);
    }
    void getID(){
        recyclerView=findViewById(R.id.register_listview);
        btn_back=findViewById(R.id.register_btn_arrowback);
        btn_biensoan=findViewById(R.id.register_btn_biensoan);
        viewVui=findViewById(R.id.viewvui);
    } void screenToScreen(Class toScreen){
        startActivity(new Intent(Register.this,toScreen));
    }
}