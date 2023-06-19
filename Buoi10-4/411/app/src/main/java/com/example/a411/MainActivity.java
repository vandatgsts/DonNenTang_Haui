package com.example.a411;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Spinner spinDanhMuc;
    EditText editMaSP,editTenSP,editSoLuong;
    Button btnNhap;
    ListView lvSanPham;
    ArrayList<Catalog> arraySpinner=new ArrayList<Catalog>();
    ArrayAdapter<Catalog> adapterSpinner =null;
    //khai báo cặp đối tượng dùng cho listView
    ArrayList<product> arrayListView=new ArrayList<product>();
    ArrayAdapter<product> adapterListView=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWitget();
        //addEventsFormWidGets();
    }

    private void fakeDataCatalog() {
        Catalog cat1=new Catalog("1","SamSung");
        Catalog cat2=new Catalog("2","Nokia");
        Catalog cat3=new Catalog("3","IPAD");
        Catalog cat4=new Catalog("4","HTC");
        arraySpinner.add(cat1);
        arraySpinner.add(cat2);
        arraySpinner.add(cat3);
        arraySpinner.add(cat4);
        adapterSpinner.notifyDataSetChanged();
    }

    private void getWitget() {
        editMaSP = (EditText) findViewById(R.id.txtmasp);
        editTenSP = (EditText) findViewById(R.id.txttensp);
        spinDanhMuc = (Spinner) findViewById(R.id.txtdm);
        btnNhap = (Button) findViewById(R.id.btnnhap);
        lvSanPham = (ListView) findViewById(R.id.lv);
        adapterSpinner=new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,arraySpinner);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_list_item_1);
        fakeDataCatalog();
        spinDanhMuc.setAdapter(adapterSpinner);
        spinDanhMuc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        btnNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String masp = editMaSP.getText()+"";
                String tensp = editTenSP.getText()+"";
                Catalog cat1=new Catalog(masp,tensp);
                arraySpinner.add(cat1);
                adapterSpinner.notifyDataSetChanged();

            }
        });
//cấu hình cho listView
        adapterListView=new ArrayAdapter<product>(this, android.R.layout.simple_list_item_1,arrayListView );
        lvSanPham.setAdapter(adapterListView);


    }
}