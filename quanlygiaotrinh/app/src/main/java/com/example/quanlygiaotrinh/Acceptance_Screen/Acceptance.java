package com.example.quanlygiaotrinh.Acceptance_Screen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlygiaotrinh.Database.Book;
import com.example.quanlygiaotrinh.Database.DBHelper;
import com.example.quanlygiaotrinh.MainActivity;
import com.example.quanlygiaotrinh.R;

import java.util.ArrayList;
import java.util.Arrays;

public class Acceptance extends AppCompatActivity {
    ImageView btnBack;
    TextView txtTotal1, txtTotal2, txtTotal3, txtTotal;
    EditText edtTotal1_1, edtTotal1_2;
    EditText edtTotal2_1, edtTotal2_2, edtTotal2_3;
    EditText edtTotal3_1, edtTotal3_2, edtTotal3_3, edtTotal3_4;
    EditText edtIdeal;
    TextView txtAcepted;
    Button btnSend;
    final int[] number2 = {0, 0, 0};
    final int[] number1 = {0, 0};
    final int[] number3 = {0, 0, 0, 0};
    Spinner spinnerNameBook;
    ArrayList<Book> listBooks2=new ArrayList<>();
    ArrayList<String> dataSpiner=new ArrayList<>();
    DBHelper dbHelper;
    ArrayAdapter<String> adapter;
    int numberAns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceptance);
        GetID();
        // xu ly text
        TxtTotal1();
        TxtTotal2();
        TxtTotal3();
        TxtAns();
        getData();
        Spinner();

        // btn_Send
        //numberAns = Integer.parseInt(txtTotal.getText().toString());
        btnSend.setOnClickListener(v -> {
            numberAns = Integer.parseInt(txtTotal.getText().toString());
            if (numberAns > 100 || Arrays.stream(number1).sum() > 20 || Arrays.stream(number2).sum() > 45 || Arrays.stream(number3).sum() > 35) {
                Toast.makeText(this, "Điểm số không hợp lệ", Toast.LENGTH_SHORT).show();
            } else {
                Book item=listBooks2.get(spinnerNameBook.getSelectedItemPosition());
                item.setStatus(3);
                // neu diem >=60 thi giao trinh dat chuan
                if(numberAns>=60)
                {
                    item.setAns(1);
                }
                else item.setAns(0);
                listBooks2.remove(item);
                dataSpiner.remove(spinnerNameBook.getSelectedItem());
                adapter.notifyDataSetChanged();
                dbHelper.updateBook(item);
            }
        });
        btnBack.setOnClickListener(v -> {// kieu lam da
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
        });

    }
    void getData(){
        dbHelper= MainActivity.dbHelper;
        listBooks2=dbHelper.getBooksByStatus(2);
    }
    void Spinner(){
        for(Book i: listBooks2){
            dataSpiner.add(i.getName());
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, dataSpiner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerNameBook.setAdapter(adapter);
        spinnerNameBook.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    void GetID() {
        txtTotal = findViewById(R.id.accelerate_edt_total);
        txtTotal1 = findViewById(R.id.accelerate_txt_total_1);
        txtTotal2 = findViewById(R.id.accelerate_edt_total_2);
        txtTotal3 = findViewById(R.id.accelerate_edt_total_3);
        edtTotal1_1 = findViewById(R.id.accelerate_txt_total_1_1);
        edtTotal1_2 = findViewById(R.id.accelerate_txt_total_1_2);
        edtTotal2_1 = findViewById(R.id.accelerate_edt_total_2_1);
        edtTotal2_2 = findViewById(R.id.accelerate_edt_total_2_2);
        edtTotal2_3 = findViewById(R.id.accelerate_edt_total_2_3);
        edtTotal3_1 = findViewById(R.id.accelerate_edt_total_3_1);
        edtTotal3_2 = findViewById(R.id.accelerate_edt_total_3_2);
        edtTotal3_3 = findViewById(R.id.accelerate_edt_total_3_3);
        edtTotal3_4 = findViewById(R.id.accelerate_edt_total_3_4);
        edtIdeal = findViewById(R.id.accelerate_edt_idea);
        btnSend = findViewById(R.id.accelerate_btn_send);
        txtAcepted=findViewById(R.id.acceptance_edt_accept);
        spinnerNameBook=findViewById(R.id.accelerate_spinner);
        btnBack=findViewById(R.id.accelerate_btn_arrowback);
    }
    void TxtTotal1() {
        edtTotal1_1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // bắt sự kiện trước khi thay đổi
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // bắt sự kiện trong lúc đổi
            }

            @Override
            public void afterTextChanged(Editable s) {
                // bắt sự kiện sau khi thay đổi
                String value1 = edtTotal1_1.getText().toString();
                number1[0] = Integer.parseInt(value1.isEmpty() ? "0" : value1);
                // check total1
                SetTextColor(number1[0], 10, edtTotal1_1);//doi mau text neu diem >10
                int total = number1[0] + number2[0];
                SetTextColor(total, 20, txtTotal1);
                txtTotal1.setText(total + "");
                // check total ans
                SetTextColor( numberAns, 100, txtTotal);
                numberAns = Arrays.stream(number1).sum() + Arrays.stream(number2).sum() + Arrays.stream(number3).sum();
                txtTotal.setText(( numberAns) + "");
            }
        });
        edtTotal1_2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // bắt sự kiện trước khi thay đổi
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // bắt sự kiện trong lúc đổi
            }

            @Override
            public void afterTextChanged(Editable s) {
                // bắt sự kiện sau khi thay đổi
                String value = edtTotal1_2.getText().toString();
                number1[1] = Integer.parseInt(value.isEmpty() ? "0" : value);
                // check total1
                SetTextColor(number1[1], 10, edtTotal1_2);// doi mau text neu diem >10
                int total = number1[0] + number1[1];
                SetTextColor(total, 20, txtTotal1);
                txtTotal1.setText(total + "");
                // check total ans
                numberAns = Arrays.stream(number1).sum() + Arrays.stream(number2).sum() + Arrays.stream(number3).sum();
                SetTextColor( numberAns, 100, txtTotal);
                txtTotal.setText( numberAns + "");
            }
        });
    }


    void TxtTotal2() {
        edtTotal2_1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // bắt sự kiện trước khi thay đổi
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // bắt sự kiện trong lúc đổi
            }

            @Override
            public void afterTextChanged(Editable s) {
                // bắt sự kiện sau khi thay đổi
                String value1 = edtTotal2_1.getText().toString();
                number2[0] = Integer.parseInt(value1.isEmpty() ? "0" : value1);
                // check total2
                SetTextColor(number2[0], 10, edtTotal2_1);
                int total = number2[0] + number2[1] + number2[2];
                SetTextColor(total, 45, txtTotal2);
                txtTotal2.setText(total + "");
                // check total ans
                numberAns = Arrays.stream(number1).sum() + Arrays.stream(number2).sum() + Arrays.stream(number3).sum();
                SetTextColor( numberAns, 100, txtTotal);
                txtTotal.setText(( numberAns) + "");
            }
        });
        edtTotal2_2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // bắt sự kiện trước khi thay đổi
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // bắt sự kiện trong lúc đổi
            }

            @Override
            public void afterTextChanged(Editable s) {
                // bắt sự kiện sau khi thay đổi
                String value1 = edtTotal2_2.getText().toString();
                number2[1] = Integer.parseInt(value1.isEmpty() ? "0" : value1);
                // check total2
                SetTextColor(number2[1], 5, edtTotal2_2);// doi mau text neu diem >10
                int total = number2[0] + number2[1] + number2[2];
                SetTextColor(total, 45, txtTotal2);
                txtTotal2.setText(total + "");
                // check total ans
                numberAns = Arrays.stream(number1).sum() + Arrays.stream(number2).sum() + Arrays.stream(number3).sum();
                SetTextColor( numberAns, 100, txtTotal);
                txtTotal.setText(( numberAns) + "");
            }
        });
        edtTotal2_3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // bắt sự kiện trước khi thay đổi
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // bắt sự kiện trong lúc đổi
            }

            @Override
            public void afterTextChanged(Editable s) {
                // bắt sự kiện sau khi thay đổi
                String value1 = edtTotal2_3.getText().toString();
                number2[2] = Integer.parseInt(value1.isEmpty() ? "0" : value1);
                //check total2
                SetTextColor(number2[2], 30, edtTotal2_3);// doi mau text neu diem >10
                int total = number2[0] + number2[1] + number2[2];
                SetTextColor(total, 45, txtTotal2);
                txtTotal2.setText(total + "");
                // check total ans
                numberAns = Arrays.stream(number1).sum() + Arrays.stream(number2).sum() + Arrays.stream(number3).sum();
                SetTextColor( numberAns, 100, txtTotal);
                txtTotal.setText(( numberAns) + "");
            }
        });
    }

    void TxtTotal3() {
        edtTotal3_1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // bắt sự kiện trước khi thay đổi
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // bắt sự kiện trong lúc đổi
            }

            @Override
            public void afterTextChanged(Editable s) {
                // bắt sự kiện sau khi thay đổi
                String value1 = edtTotal3_1.getText().toString();
                number3[0] = Integer.parseInt(value1.isEmpty() ? "0" : value1);
                // check total3
                SetTextColor(number3[0], 20, edtTotal3_1);
                int total = number3[0] + number3[1] + number3[2] + number3[3];
                SetTextColor(total, 35, txtTotal3);
                txtTotal3.setText(total + "");
                
                // check total ans
                numberAns = Arrays.stream(number1).sum() + Arrays.stream(number2).sum() + Arrays.stream(number3).sum();
                SetTextColor( numberAns, 100, txtTotal);
                txtTotal.setText(( numberAns) + "");
            }
        });
        edtTotal3_2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // bắt sự kiện trước khi thay đổi
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // bắt sự kiện trong lúc đổi
            }

            @Override
            public void afterTextChanged(Editable s) {
                // bắt sự kiện sau khi thay đổi
                String value1 = edtTotal3_2.getText().toString();
                number3[1] = Integer.parseInt(value1.isEmpty() ? "0" : value1);
                // check total3
                SetTextColor(number3[1], 5, edtTotal3_2);// doi mau text neu diem >10
                int total = number3[0] + number3[1] + number3[2] + number3[3];
                SetTextColor(total, 35, txtTotal3);
                txtTotal3.setText(total + "");
                // check total ans
                numberAns = Arrays.stream(number1).sum() + Arrays.stream(number2).sum() + Arrays.stream(number3).sum();
                SetTextColor( numberAns, 100, txtTotal);
                txtTotal.setText(( numberAns) + "");
            }
        });
        edtTotal3_3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // bắt sự kiện trước khi thay đổi
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // bắt sự kiện trong lúc đổi
            }

            @Override
            public void afterTextChanged(Editable s) {
                // bắt sự kiện sau khi thay đổi
                String value1 = edtTotal3_3.getText().toString();
                number3[2] = Integer.parseInt(value1.isEmpty() ? "0" : value1);
                // check total3
                SetTextColor(number3[2], 5, edtTotal3_3);// doi mau text neu diem >10
                int total = number3[0] + number3[1] + number3[2] + number3[3];
                SetTextColor(total, 35, txtTotal3);
                txtTotal3.setText(total + "");
                // check total ans
                numberAns = Arrays.stream(number1).sum() + Arrays.stream(number2).sum() + Arrays.stream(number3).sum();
                SetTextColor( numberAns, 100, txtTotal);
                txtTotal.setText(( numberAns) + "");
            }
        });
        edtTotal3_4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // bắt sự kiện trước khi thay đổi
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // bắt sự kiện trong lúc đổi
            }

            @Override
            public void afterTextChanged(Editable s) {
                // bắt sự kiện sau khi thay đổi
                String value1 = edtTotal3_4.getText().toString();
                number3[3] = Integer.parseInt(value1.isEmpty() ? "0" : value1);
                // check total3
                SetTextColor(number3[3], 5, edtTotal3_4);// doi mau text neu diem >10
                int total = number3[0] + number3[1] + number3[2] + number3[3];
                txtTotal3.setText(String.valueOf(total));
                SetTextColor(total, 35, txtTotal3);
                txtTotal3.setText(total + "");
                // check total ans
                numberAns = Arrays.stream(number1).sum() + Arrays.stream(number2).sum() + Arrays.stream(number3).sum();
                SetTextColor( numberAns, 100, txtTotal);
                txtTotal.setText(( numberAns) + "");
            }
        });
    }
    void TxtAns(){
        txtTotal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(numberAns>=60 && numberAns<=100) {
                    txtAcepted.setText("Đ");
                    txtAcepted.setTextColor(Color.GREEN);
                }
                else {
                    txtAcepted.setText("KĐ");
                    txtAcepted.setTextColor(Color.RED);
                }
            }
        });
    }

    void SetTextColor(int total, int max, TextView textView) {
        if (total > max) {
            textView.setTextColor(Color.RED);
        } else {
            textView.setTextColor(Color.BLACK);
        }
    }

    void SetTextColor(int total, int max, EditText editText) {
        if (total > max) {
            editText.setTextColor(Color.RED);
        } else {
            editText.setTextColor(Color.BLACK);
        }
    }
}