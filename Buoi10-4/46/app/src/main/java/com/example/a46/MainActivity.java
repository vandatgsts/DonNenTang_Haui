package com.example.a46;

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
    Button btnnhap,btnthoat;
    EditText manv, tennv;
    RadioGroup rdgr;
    RadioButton rdbtntv;
    RadioButton rdbtnct;
    ListView lv;
    ArrayList<Employee> arremployee = new ArrayList<Employee>();
    ArrayAdapter<Employee> adapter = null;
    Employee employee;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWidget();
    }

    private void getWidget() {
         btnnhap = (Button) findViewById(R.id.btnnhap);
         manv = (EditText) findViewById(R.id.editmanv);
         tennv = (EditText) findViewById(R.id.edittennv);
         rdgr = (RadioGroup) findViewById(R.id.radiogr);
         rdbtnct = (RadioButton) findViewById(R.id.rdchinhthuc);
         rdbtntv =  (RadioButton) findViewById(R.id.rdthoivu);
         btnthoat = (Button)findViewById(R.id.btnthoat);
         lv = (ListView) findViewById(R.id.listviewa);
         btnnhap.setOnClickListener(a);
         btnthoat.setOnClickListener(a);
         // khai triển adapter

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,arremployee);
        lv.setAdapter(adapter);





    }
    View.OnClickListener a = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(view==btnnhap){
                String ten = tennv.getText().toString();
                String id = manv.getText().toString();
                if(rdgr.getCheckedRadioButtonId()==R.id.rdchinhthuc){

                    employee = new imfulltime();
                    employee.setId(id);
                    employee.setName(ten);
                }
                else {
                    employee = new imparttime();
                    employee.setId(id);
                    employee.setName(ten);
                }
                arremployee.add(employee);
                adapter.notifyDataSetChanged();
                lv.setAdapter(adapter);









            }
            //nut thoat
            else if (view==btnthoat) {
                finish();

            }

        }
    };
}