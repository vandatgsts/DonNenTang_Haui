package com.example.bai4_4;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText inputName,inputCMND,inputInforNew;
    RadioGroup radioGroup;
    CheckBox checkDocSach,checkDocBao,checkDocCode;
    Button guithongtin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findID();
        guithongtin.setOnClickListener(v -> {

        });

    }
    void findID()
    {
        inputName=findViewById(R.id.inputName);
        inputCMND=findViewById(R.id.inputCMND);
        inputInforNew=findViewById(R.id.inputInfonew);
        radioGroup=findViewById(R.id.radio);
        checkDocBao=findViewById(R.id.checkdocbao);
        checkDocSach=findViewById(R.id.checkdocsach);
        checkDocCode=findViewById(R.id.checkdoccode);
        guithongtin=findViewById(R.id.btnguithongin);
    }
    void ShowDialog()
    {
        if(inputName.getText().toString().isEmpty() || inputName.getText().toString().length()<3)
        {   inputName.requestFocus();
            inputName.selectAll();
            Toast.makeText(this, "ten phai lon hon 3 ky ty", Toast.LENGTH_SHORT).show();
            return;
        }
        if(inputCMND.getText().toString().length()!=9)
        {
            inputCMND.requestFocus();
            inputCMND.selectAll();
            Toast.makeText(this, "ten CMND co 9 ky tu", Toast.LENGTH_SHORT).show();
            return;
        }
        if()

    }
}