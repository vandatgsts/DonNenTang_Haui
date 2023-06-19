package com.example.a48;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    Button nhap;
    EditText id, ten;
    RadioGroup radioGroup;
    RadioButton ct, tv;
    ArrayList<employee> arrayListemployee;
    Adapter adapter;
    employee employee1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getwidget();
        arrayListemployee = new ArrayList<>();
        employee1 = new chinhthuc();
        employee1.setName("son1");
        employee1.setId("3");
        arrayListemployee.add(employee1);
        adapter = new Adapter(this,R.layout.activity_item,arrayListemployee);
        lv.setAdapter(adapter);
    }

    private void getwidget() {
        lv = (ListView) findViewById(R.id.lv);
        nhap = (Button) findViewById(R.id.Btnnhap);
        id = (EditText) findViewById(R.id.txtmanvz);
        ten = (EditText) findViewById(R.id.txttennvz);
        radioGroup = (RadioGroup) findViewById(R.id.rdgrz);
        ct = (RadioButton)findViewById(R.id.ractz);
        tv = (RadioButton)findViewById(R.id.ratvz);
        nhap.setOnClickListener(a);



    }

    View.OnClickListener a = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(view==nhap){
                String ten1 = ten.getText().toString()+"";
                String id1 = id.getText().toString()+"";
                if(radioGroup.getCheckedRadioButtonId()==R.id.ractz)
                {
                    employee1 = new chinhthuc();
                    employee1.setId(id1);
                    employee1.setName(ten1);
                }
                else {
                    employee1 = new thoivu();
                    employee1.setId(id1);
                    employee1.setName(ten1);

                }
                arrayListemployee.add(employee1);
               // adapter.notifyDataSetChanged();
                lv.setAdapter(adapter);

            }

        }
    };
}